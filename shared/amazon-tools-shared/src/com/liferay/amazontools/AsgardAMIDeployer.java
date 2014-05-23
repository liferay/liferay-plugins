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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.autoscaling.AmazonAutoScalingClient;
import com.amazonaws.services.autoscaling.model.AutoScalingGroup;
import com.amazonaws.services.autoscaling.model.CreateOrUpdateTagsRequest;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteVolumeRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Volume;

import com.liferay.jsonwebserviceclient.JSONWebServiceClient;
import com.liferay.jsonwebserviceclient.JSONWebServiceClientImpl;

import jargs.gnu.CmdLineParser;

import java.awt.Desktop;

import java.net.URI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Ivica Cardic
 */
public class AsgardAMIDeployer extends BaseAMITool {

	public static void main(String[] args) throws Exception {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option imageNameOption = cmdLineParser.addStringOption(
			"image.name");
		CmdLineParser.Option propertiesFileNameOption =
			cmdLineParser.addStringOption("properties.file.name");

		cmdLineParser.parse(args);

		try {
			new AsgardAMIDeployer(
				(String)cmdLineParser.getOptionValue(imageNameOption),
				(String)cmdLineParser.getOptionValue(propertiesFileNameOption));
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);
		}
	}

	public AsgardAMIDeployer(String imageName, String propertiesFileName)
		throws Exception {

		super(propertiesFileName);

		_imageName = imageName;

		_amazonAutoScalingClient = getAmazonAutoScalingClient(
			properties.getProperty("access.key"),
			properties.getProperty("secret.key"),
			properties.getProperty("autoscaling.endpoint"));
		_jsonWebServiceClient = getJSONWebServiceClient(
			properties.getProperty("asgard.host.name"),
			Integer.valueOf(properties.getProperty("asgard.host.port")));

		String autoScalingGroupName = createAutoScalingGroup();

		if (autoScalingGroupName != null) {
			checkAutoScalingGroup(autoScalingGroupName);
		}
	}

	protected void checkAutoScalingGroup(String autoScalingGroupName)
		throws Exception {

		System.out.println(
			"Checking Auto Scaling Group " + autoScalingGroupName);

		String asgardClusterName = properties.getProperty(
			"asgard.cluster.name");
		String availabilityZone = properties.getProperty("availability.zone");
		boolean deployed = false;

		for (int i = 1; i < 30; i++) {
			String json = _jsonWebServiceClient.doGet(
				"/" + availabilityZone + "/loadBalancer/show/" +
					asgardClusterName + ".json",
				Collections.<String, String>emptyMap());

			JSONObject loadBalancerJSONObject = new JSONObject(json);

			if (!isInService(loadBalancerJSONObject, autoScalingGroupName)) {
				sleep(30);
			}
			else {
				deployed = true;

				break;
			}
		}

		if (!deployed) {
			Map<String, String> parameters = new HashMap<String, String>();

			parameters.put("name", autoScalingGroupName);

			_jsonWebServiceClient.doPost(
				"/" + availabilityZone + "/cluster/delete", parameters);

			throw new RuntimeException(
				"Unable to deploy Auto Scaling Group " + autoScalingGroupName);
		}

		String json = _jsonWebServiceClient.doGet(
			"/" + availabilityZone + "/cluster/list.json",
			Collections.<String, String>emptyMap());

		JSONArray clustersJSONArray = new JSONArray(json);

		for (int i = 0; i < clustersJSONArray.length(); i++) {
			JSONObject clusterJSONObject = clustersJSONArray.getJSONObject(i);

			String curAsgardClusterName = clusterJSONObject.getString(
				"cluster");

			if (!asgardClusterName.equals(curAsgardClusterName)) {
				continue;
			}

			JSONArray autoScalingGroupsJSONArray =
				clusterJSONObject.getJSONArray("autoScalingGroups");

			for (int j = 0; j < autoScalingGroupsJSONArray.length(); j++) {
				String curAutoScalingGroupName =
					autoScalingGroupsJSONArray.getString(j);

				if (autoScalingGroupName.equals(curAutoScalingGroupName)) {
					continue;
				}

				Map<String, String> parameters = new HashMap<String, String>();

				parameters.put("name", curAutoScalingGroupName);

				_jsonWebServiceClient.doPost(
					"/" + availabilityZone + "/cluster/deactivate", parameters);
			}
		}

		System.out.println(
			"Deployed Auto Scaling Group " + autoScalingGroupName);

		Desktop desktop = Desktop.getDesktop();

		String asgardClusterURL =
			"http://" + properties.getProperty("asgard.host.name") + ":" +
				properties.getProperty("asgard.host.port") + "/" +
					availabilityZone + "/cluster/show/" + asgardClusterName;

		desktop.browse(URI.create(asgardClusterURL));
	}

	protected String createAutoScalingGroup() throws Exception {
		System.out.println("Creating Auto Scaling Group");

		DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult =
			_amazonAutoScalingClient.describeAutoScalingGroups();

		List<AutoScalingGroup> autoScalingGroups =
			describeAutoScalingGroupsResult.getAutoScalingGroups();

		int oldAutoScalingGroupsSize = autoScalingGroups.size();

		String availabilityZone = properties.getProperty("availability.zone");

		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("checkHealth", "true");
		parameters.put("desiredCapacity", "1");
		parameters.put("imageId", getImageId(_imageName));
		parameters.put("min", "1");

		String asgardClusterName = properties.getProperty(
			"asgard.cluster.name");

		parameters.put("name", asgardClusterName);

		parameters.put("trafficAllowed", "true");

		_jsonWebServiceClient.doPost(
			"/" + availabilityZone + "/cluster/createNextGroup", parameters);

		for (int i = 0; i < 12; i++) {
			describeAutoScalingGroupsResult =
				_amazonAutoScalingClient.describeAutoScalingGroups();

			autoScalingGroups =
				describeAutoScalingGroupsResult.getAutoScalingGroups();

			int newAutoScalingGroupsSize = autoScalingGroups.size();

			if (oldAutoScalingGroupsSize == newAutoScalingGroupsSize) {
				sleep(15);
			}
			else {
				break;
			}
		}

		String autoScalingGroupName = null;
		boolean created = false;
		int maxSize = 0;

		for (int i = 0; i < 12; i++) {
			String json = _jsonWebServiceClient.doGet(
				"/" + availabilityZone + "/cluster/show/" + asgardClusterName +
					".json",
				Collections.<String, String>emptyMap());

			JSONArray autoScalingGroupsJSONArray = new JSONArray(json);

			JSONObject autoScalingGroupJSONObject =
				autoScalingGroupsJSONArray.getJSONObject(
					autoScalingGroupsJSONArray.length() - 1);

			autoScalingGroupName = autoScalingGroupJSONObject.getString(
				"autoScalingGroupName");
			maxSize = autoScalingGroupJSONObject.getInt("maxSize");

			List<String> instanceIds = new ArrayList<String>();

			JSONArray instancesJSONArray =
				autoScalingGroupJSONObject.getJSONArray("instances");

			for (int j = 0; j < instancesJSONArray.length(); j++) {
				JSONObject instanceJSONObject =
					instancesJSONArray.getJSONObject(j);

				instanceIds.add(instanceJSONObject.getString("instanceId"));
			}

			if (instanceIds.isEmpty() ||
				!isInService(autoScalingGroupJSONObject)) {

				sleep(15);
			}
			else {
				CreateTagsRequest createTagsRequest = new CreateTagsRequest();

				createTagsRequest.setResources(instanceIds);

				List<Tag> tags = new ArrayList<Tag>();

				Tag tag = new Tag();

				tag.withKey("Name");
				tag.withValue(properties.getProperty("instance.name"));

				tags.add(tag);

				createTagsRequest.setTags(tags);

				amazonEC2Client.createTags(createTagsRequest);

				CreateOrUpdateTagsRequest createOrUpdateTagsRequest =
					new CreateOrUpdateTagsRequest();

				com.amazonaws.services.autoscaling.model.Tag autoScalingTag =
					new com.amazonaws.services.autoscaling.model.Tag();

				autoScalingTag.setKey("Name");
				autoScalingTag.setPropagateAtLaunch(true);
				autoScalingTag.setResourceId(autoScalingGroupName);
				autoScalingTag.setResourceType("auto-scaling-group");
				autoScalingTag.setValue(
					properties.getProperty("instance.name"));

				createOrUpdateTagsRequest.withTags(autoScalingTag);

				_amazonAutoScalingClient.createOrUpdateTags(
					createOrUpdateTagsRequest);

				created = true;

				break;
			}
		}

		if (!created) {
			throw new RuntimeException(
				"Unable to create Auto Scaling Group " + autoScalingGroupName);
		}

		int minSize = Integer.parseInt(
			properties.getProperty("instance.min.size"));

		if (minSize > 1) {
			parameters.clear();

			parameters.put("maxSize", String.valueOf(maxSize));
			parameters.put("minSize", String.valueOf(minSize));
			parameters.put("name", autoScalingGroupName);

			_jsonWebServiceClient.doPost(
				"/" + availabilityZone + "/cluster/resize", parameters);

			for (int i = 0; i < 12; i++) {
				String json = _jsonWebServiceClient.doGet(
					"/" + availabilityZone + "/cluster/show/" +
						asgardClusterName + ".json",
					Collections.<String, String>emptyMap());

				JSONArray autoScalingGroupsJSONArray = new JSONArray(json);

				JSONObject autoScalingGroupJSONObject =
					autoScalingGroupsJSONArray.getJSONObject(
						autoScalingGroupsJSONArray.length() - 1);

				JSONArray instancesJSONArray =
					autoScalingGroupJSONObject.getJSONArray("instances");

				if (instancesJSONArray.length() == 1) {
					sleep(15);
				}
				else {
					break;
				}
			}
		}

		DescribeVolumesRequest describeVolumesRequest =
			new DescribeVolumesRequest();

		Filter filter = new Filter();

		filter.setName("status");

		filter.withValues("available");

		describeVolumesRequest.withFilters(filter);

		DescribeVolumesResult describeVolumesResult =
			amazonEC2Client.describeVolumes(describeVolumesRequest);

		List<Volume> volumes = describeVolumesResult.getVolumes();

		for (int i = 0; i < volumes.size(); i++) {
			DeleteVolumeRequest deleteVolumeRequest = new DeleteVolumeRequest();

			Volume volume = volumes.get(i);

			deleteVolumeRequest.setVolumeId(volume.getVolumeId());

			amazonEC2Client.deleteVolume(deleteVolumeRequest);
		}

		System.out.println(
			"Created Auto Scaling Group " + autoScalingGroupName);

		return autoScalingGroupName;
	}

	protected AmazonAutoScalingClient getAmazonAutoScalingClient(
		String accessKey, String secretKey, String endpoint) {

		AWSCredentials awsCredentials = new BasicAWSCredentials(
			accessKey, secretKey);

		AmazonAutoScalingClient amazonAutoScalingClient =
			new AmazonAutoScalingClient(awsCredentials);

		amazonAutoScalingClient.setEndpoint(endpoint);

		return amazonAutoScalingClient;
	}

	protected JSONWebServiceClient getJSONWebServiceClient(
		String hostName, int hostPort) {

		JSONWebServiceClientImpl jsonWebServiceClient =
			new JSONWebServiceClientImpl();

		jsonWebServiceClient.setHostName(hostName);
		jsonWebServiceClient.setHostPort(hostPort);

		jsonWebServiceClient.afterPropertiesSet();

		return jsonWebServiceClient;
	}

	protected boolean isInService(JSONObject autoScalingGroupJSONObject) {
		JSONArray instancesJSONArray = autoScalingGroupJSONObject.getJSONArray(
			"instances");

		for (int i = 0; i < instancesJSONArray.length(); i++) {
			JSONObject instance = instancesJSONArray.getJSONObject(i);

			String lifecycleState = instance.getString("lifecycleState");

			if (!lifecycleState.equals("InService")) {
				return false;
			}
		}

		return true;
	}

	protected boolean isInService(
		JSONObject loadBalancerJSONObject, String autoScalingGroupName) {

		List<JSONObject> instanceStateJSONObjects = new ArrayList<JSONObject>();

		JSONArray instanceStatesJSONArray = loadBalancerJSONObject.getJSONArray(
			"instanceStates");

		for (int i = 0; i < instanceStatesJSONArray.length(); i++) {
			JSONObject instanceStateJSONObject =
				instanceStatesJSONArray.getJSONObject(i);

			Object instanceStateAutoScalingGroupName =
				instanceStateJSONObject.get("autoScalingGroupName");

			if (autoScalingGroupName.equals(
					instanceStateAutoScalingGroupName)) {

				instanceStateJSONObjects.add(instanceStateJSONObject);
			}
		}

		if (instanceStateJSONObjects.isEmpty()) {
			return false;
		}

		for (int i = 0; i < instanceStateJSONObjects.size(); i++) {
			JSONObject instanceStateJSONObject = instanceStateJSONObjects.get(
				i);

			String state = instanceStateJSONObject.getString("state");

			if (!state.equals("InService")) {
				return false;
			}
		}

		return true;
	}

	private AmazonAutoScalingClient _amazonAutoScalingClient;
	private String _imageName;
	private JSONWebServiceClient _jsonWebServiceClient;

}