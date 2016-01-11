/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.amazontools;

import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.InstanceStateChange;
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.amazonaws.util.json.JSONObject;

import jargs.gnu.CmdLineParser;

import java.io.File;

import java.net.ConnectException;

import java.security.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.StreamCopier;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.userauth.keyprovider.FileKeyProvider;
import net.schmizz.sshj.userauth.keyprovider.PKCS8KeyFile;
import net.schmizz.sshj.xfer.FileSystemFile;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @author Ivica Cardic
 */
public class AMIBuilder extends BaseAMITool {

	public static void main(String[] args) throws Exception {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option baseDirOption = cmdLineParser.addStringOption(
			"base.dir");
		CmdLineParser.Option propertiesFileNameOption =
			cmdLineParser.addStringOption("properties.file.name");
		CmdLineParser.Option imageNameOption = cmdLineParser.addStringOption(
			"image.name");
		CmdLineParser.Option outputOption = cmdLineParser.addStringOption(
			"output");

		cmdLineParser.parse(args);

		final AMIBuilder amiBuilder = new AMIBuilder(
			(String)cmdLineParser.getOptionValue(baseDirOption),
			(String)cmdLineParser.getOptionValue(imageNameOption),
			Boolean.parseBoolean(
				(String)cmdLineParser.getOptionValue(outputOption)),
			(String)cmdLineParser.getOptionValue(propertiesFileNameOption));

		Runtime runtime = Runtime.getRuntime();

		runtime.addShutdownHook(
			new Thread() {

				@Override
				public void run() {
					amiBuilder.destroy();
				}

			});

		try {
			amiBuilder.start();

			amiBuilder.provision();

			amiBuilder.createImage();

			amiBuilder.destroy();
		}
		catch (Exception e) {
			e.printStackTrace();

			amiBuilder.destroy();

			System.exit(-1);

			return;
		}

		System.exit(0);
	}

	public AMIBuilder(
			String baseDirName, String imageName, boolean output,
			String propertiesFileName)
		throws Exception {

		super(propertiesFileName);

		_baseDirName = baseDirName;
		_imageName = imageName;
		_output = output;

		Security.addProvider(new BouncyCastleProvider());

		_provisioners = getProvisioners(properties);
	}

	protected void createImage() {
		CreateImageRequest createImageRequest = new CreateImageRequest();

		createImageRequest.setInstanceId(_instanceId);
		createImageRequest.setName(_imageName);

		CreateImageResult createImageResult = amazonEC2Client.createImage(
			createImageRequest);

		System.out.println("Creating image for instance " + _instanceId);

		boolean created = false;

		for (int i = 0; i < 6; i ++) {
			sleep(30);

			created = isImageCreated(createImageResult.getImageId());

			if (created) {
				System.out.println(
					"Created image " + createImageResult.getImageId());

				break;
			}
		}

		if (!created) {
			System.out.println(
				"Unable to create image " + createImageResult.getImageId());

			deregisterImage(createImageResult.getImageId());
		}
	}

	protected void deregisterImage(String imageId) {
		DeregisterImageRequest deregisterImageRequest =
			new DeregisterImageRequest();

		deregisterImageRequest.setImageId(imageId);

		amazonEC2Client.deregisterImage(deregisterImageRequest);
	}

	protected void destroy() {
		if (_instanceId == null) {
			return;
		}

		terminateInstance(_instanceId);

		amazonEC2Client.shutdown();

		_instanceId = null;
	}

	protected void executeScript(SSHClient sshClient, String scriptFileName)
		throws Exception {

		uploadFile(sshClient, scriptFileName, "/tmp");

		File scriptFile = new File(scriptFileName);

		scriptFileName = "/tmp/" + scriptFile.getName();

		System.out.println("Executing script " + scriptFileName);

		executeSessionCommand(
			sshClient, "chmod +x " + scriptFileName + "; " + scriptFileName);

		System.out.println("Deleting script " + scriptFileName);

		executeSessionCommand(sshClient, "rm " + scriptFileName);
	}

	protected void executeSessionCommand(SSHClient sshClient, String command)
		throws Exception {

		System.out.println("Executing session command: " + command);

		Session session = sshClient.startSession();

		session.allocateDefaultPTY();

		try {
			Session.Command sessionCommand = session.exec(command);

			if (_output) {
				new StreamCopier(session.getInputStream(), System.out).copy();
			}

			sessionCommand.join();
		}
		finally {
			session.close();
		}
	}

	protected Instance getInstance(String instanceId, String state) {
		DescribeInstancesRequest describeInstancesRequest =
			new DescribeInstancesRequest();

		List<Filter> filters = new ArrayList<>();

		Filter filter = new Filter();

		filter.setName("instance-state-name");

		List<String> values = new ArrayList<>();

		values.add(state);

		filter.setValues(values);

		filters.add(filter);

		describeInstancesRequest.setFilters(filters);

		List<String> instanceIds = new ArrayList<>();

		instanceIds.add(instanceId);

		describeInstancesRequest.setInstanceIds(instanceIds);

		DescribeInstancesResult describeInstancesResult =
			amazonEC2Client.describeInstances(describeInstancesRequest);

		List<Reservation> reservations =
			describeInstancesResult.getReservations();

		if (reservations.isEmpty()) {
			return null;
		}

		Reservation reservation = reservations.get(0);

		List<Instance> instances = reservation.getInstances();

		return instances.get(0);
	}

	protected String getKeyFileName() {
		StringBuilder sb = new StringBuilder(6);

		sb.append(System.getProperty("user.home"));
		sb.append(File.separator);
		sb.append(".ssh");
		sb.append(File.separator);
		sb.append(properties.getProperty("key.name"));
		sb.append(".pem");

		return sb.toString();
	}

	protected Map<String, String> getProvisioners(Properties properties) {
		Map<String, String> provisioners = new TreeMap<>();

		Set<String> names = properties.stringPropertyNames();

		for (String name : names) {
			if (!name.contains("provisioners")) {
				continue;
			}

			String value = properties.getProperty(name);

			provisioners.put(name, value);
		}

		return provisioners;
	}

	protected boolean isImageCreated(String imageId) {
		DescribeImagesRequest describeImagesRequest =
			new DescribeImagesRequest();

		List<Filter> filters = new ArrayList<>();

		Filter filter = new Filter();

		filter.setName("state");

		List<String> values = new ArrayList<>();

		values.add("available");

		filter.setValues(values);

		filters.add(filter);

		describeImagesRequest.setFilters(filters);

		List<String> imageIds = new ArrayList<>();

		imageIds.add(imageId);

		describeImagesRequest.setImageIds(imageIds);

		DescribeImagesResult describeImagesResult =
			amazonEC2Client.describeImages(describeImagesRequest);

		List<Image> images = describeImagesResult.getImages();

		return !images.isEmpty();
	}

	protected boolean isZoneAvailable(String zoneName) {
		DescribeAvailabilityZonesResult describeAvailabilityZonesResult =
			amazonEC2Client.describeAvailabilityZones();

		List<AvailabilityZone> availabilityZones =
			describeAvailabilityZonesResult.getAvailabilityZones();

		for (AvailabilityZone availabilityZone : availabilityZones) {
			if (zoneName.equals(availabilityZone.getZoneName())) {
				return true;
			}
		}

		return false;
	}

	protected void provision() throws Exception {
		System.out.println("Connecting via SSH to " + _publicIpAddress);

		SSHClient sshClient = null;

		for (int i = 0; i < 6; i++) {
			sleep(30);

			sshClient = new SSHClient();

			sshClient.addHostKeyVerifier(new PromiscuousVerifier());
			sshClient.setTimeout(
				Integer.parseInt(properties.getProperty("ssh.timeout")) * 1000);

			sshClient.useCompression();

			try {
				sshClient.connect(_publicIpAddress);

				break;
			}
			catch (ConnectException ce) {
				sshClient = null;

				continue;
			}
		}

		if (sshClient == null) {
			throw new RuntimeException(
				"Unable to connect via SSH to " + _publicIpAddress);
		}

		FileKeyProvider fileKeyProvider = new PKCS8KeyFile();

		fileKeyProvider.init(new File(getKeyFileName()));

		sshClient.authPublickey(
			properties.getProperty("ssh.username"), fileKeyProvider);

		try {
			Set<String> keys = _provisioners.keySet();

			for (String key : keys) {
				String provisioner = _provisioners.get(key);

				if (key.contains("shell.inline")) {
					executeSessionCommand(sshClient, provisioner);
				}
				else if (key.contains("shell.script")) {
					String shellScriptFileName = null;

					if (provisioner.startsWith(File.separator)) {
						shellScriptFileName = provisioner;
					}
					else {
						shellScriptFileName =
							_baseDirName + File.separator + provisioner;
					}

					executeScript(sshClient, shellScriptFileName);
				}
				else {
					JSONObject jsonObject = new JSONObject(provisioner);

					String fileName = null;

					String src = jsonObject.getString("src");

					if (src.startsWith(File.separator)) {
						fileName = src;
					}
					else {
						fileName =
							_baseDirName + File.separator +
								jsonObject.getString("src");
					}

					uploadFile(
						sshClient, fileName, jsonObject.getString("dest"));
				}
			}
		}
		finally {
			sshClient.disconnect();
		}
	}

	protected void start() {
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();

		String availabilityZone = properties.getProperty("availability.zone");

		if (!isZoneAvailable(availabilityZone)) {
			throw new RuntimeException("Unavailable zone " + availabilityZone);
		}

		String imageId = properties.getProperty("image.id");

		if (imageId == null) {
			imageId = getImageId(properties.getProperty("image.name"));
		}

		runInstancesRequest.setImageId(imageId);

		runInstancesRequest.setInstanceType(
			properties.getProperty("instance.type"));
		runInstancesRequest.setKeyName(properties.getProperty("key.name"));
		runInstancesRequest.setMaxCount(1);
		runInstancesRequest.setMinCount(1);

		Placement placement = new Placement();

		placement.setAvailabilityZone(availabilityZone);

		runInstancesRequest.setPlacement(placement);

		List<String> securityGroupsIds = new ArrayList<>();

		securityGroupsIds.add(properties.getProperty("security.group.id"));

		runInstancesRequest.setSecurityGroupIds(securityGroupsIds);

		RunInstancesResult runInstancesResult = amazonEC2Client.runInstances(
			runInstancesRequest);

		Reservation reservation = runInstancesResult.getReservation();

		List<Instance> instances = reservation.getInstances();

		if (instances.isEmpty()) {
			throw new RuntimeException("Unable to create instances");
		}

		Instance instance = instances.get(0);

		_instanceId = instance.getInstanceId();
		_publicIpAddress = instance.getPublicIpAddress();

		StringBuilder sb = new StringBuilder(13);

		sb.append("{imageId=");
		sb.append(instance.getImageId());
		sb.append(", instanceId=");
		sb.append(_instanceId);
		sb.append(", instanceType=");
		sb.append(instance.getInstanceType());
		sb.append(", keyName=");
		sb.append(instance.getKeyName());
		sb.append(", reservationId=");
		sb.append(reservation.getReservationId());
		sb.append(", state=");

		InstanceState instanceState = instance.getState();

		sb.append(instanceState.getName());

		sb.append("}");

		System.out.println("Starting instance " + sb.toString());

		boolean running = false;

		int i = 0;

		do {
			System.out.println("Waiting for running instance " + i + "...");

			i = i + 1;

			sleep(30);

			instance = getInstance(_instanceId, "pending");
		}
		while (instance != null);

		instance = getInstance(_instanceId, "running");

		if (instance != null) {
			_publicIpAddress = instance.getPublicIpAddress();

			running = true;

			sb = new StringBuilder(7);

			sb.append("{instanceId=");
			sb.append(_instanceId);
			sb.append(", publicIpAddress=");
			sb.append(_publicIpAddress);
			sb.append(", stat=");

			instanceState = instance.getState();

			sb.append(instanceState.getName());

			sb.append("}");

			System.out.println("Started instance " + sb.toString());
		}

		if (!running) {
			throw new RuntimeException(
				"Unable to start instance " + _instanceId);
		}
	}

	protected void terminateInstance(String instanceId) {
		TerminateInstancesRequest terminateInstancesRequest =
			new TerminateInstancesRequest();

		List<String> instanceIds = terminateInstancesRequest.getInstanceIds();

		instanceIds.add(instanceId);

		TerminateInstancesResult terminateInstancesResult =
			amazonEC2Client.terminateInstances(terminateInstancesRequest);

		List<InstanceStateChange> instanceStateChanges =
			terminateInstancesResult.getTerminatingInstances();

		if (!instanceStateChanges.isEmpty()) {
			System.out.println("Terminated instance " + instanceId);
		}
		else {
			System.out.println("Unable to terminate instance " + instanceId);
		}
	}

	protected void uploadFile(
			SSHClient sshClient, String fileName, String destinationDir)
		throws Exception {

		System.out.println("Uploading file " + fileName);

		SFTPClient sftpClient = sshClient.newSFTPClient();

		try {
			sftpClient.put(new FileSystemFile(fileName), destinationDir);
		}
		finally {
			sftpClient.close();
		}
	}

	private String _baseDirName;
	private String _imageName;
	private String _instanceId;
	private boolean _output;
	private Map<String, String> _provisioners;
	private String _publicIpAddress;

}