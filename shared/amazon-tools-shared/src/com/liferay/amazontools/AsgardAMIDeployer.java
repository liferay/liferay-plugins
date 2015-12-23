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

import com.amazonaws.services.autoscaling.model.AutoScalingGroup;
import com.amazonaws.services.autoscaling.model.CreateOrUpdateTagsRequest;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsResult;
import com.amazonaws.services.ec2.model.AssociateAddressRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;

import com.liferay.jsonwebserviceclient.JSONWebServiceClient;

import jargs.gnu.CmdLineParser;

import java.awt.Desktop;

import java.io.File;

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

		CmdLineParser.Option baseDirOption = cmdLineParser.addStringOption(
			"base.dir");
		CmdLineParser.Option imageNameOption = cmdLineParser.addStringOption(
			"image.name");
		CmdLineParser.Option openAsgardURLOption =
			cmdLineParser.addBooleanOption("open.asgard.url");
		CmdLineParser.Option parallelDeploymentOption =
			cmdLineParser.addBooleanOption("parallel.deployment");
		CmdLineParser.Option propertiesFileNameOption =
			cmdLineParser.addStringOption("properties.file.name");

		cmdLineParser.parse(args);

		try {
			new AsgardAMIDeployer(
				(String)cmdLineParser.getOptionValue(baseDirOption),
				(String)cmdLineParser.getOptionValue(imageNameOption),
				(Boolean)cmdLineParser.getOptionValue(
					openAsgardURLOption, Boolean.FALSE),
				(Boolean)cmdLineParser.getOptionValue(
					parallelDeploymentOption, Boolean.FALSE),
				(String)cmdLineParser.getOptionValue(propertiesFileNameOption));
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);

			return;
		}

		System.exit(0);
	}

	public AsgardAMIDeployer(
			String baseDirName, String imageName, boolean openAsgardURLOption,
			boolean parallelDeployment, String propertiesFileName)
		throws Exception {

		super(propertiesFileName);

		_baseDirName = baseDirName;
		_imageName = imageName;
		_parallelDeployment = parallelDeployment;

		_jsonWebServiceClient = getJSONWebServiceClient(
			properties.getProperty("asgard.host.name"),
			Integer.valueOf(properties.getProperty("asgard.host.port")),
			_baseDirName + File.separator +
				properties.getProperty("asgard.key.store.path"),
			properties.getProperty("asgard.key.store.password"),
			properties.getProperty("asgard.login"),
			properties.getProperty("asgard.password"));

		System.out.println("Creating Auto Scaling Group");

		String autoScalingGroupName = createAutoScalingGroup();

		System.out.println(
			"Created Auto Scaling Group " + autoScalingGroupName);

		int minSize = -1;

		if (!_parallelDeployment) {
			minSize = Integer.parseInt(
				properties.getProperty("instance.min.size"));
		}

		System.out.println(
			"Checking Auto Scaling Group " + autoScalingGroupName);

		List<String> instanceIds = checkAutoScalingGroup(
			autoScalingGroupName, minSize);

		associateElasticIpAddresses(instanceIds);

		deactivateOldScalingGroup(autoScalingGroupName);

		if (openAsgardURLOption) {
			openAsgardURL();
		}

		System.out.println(
			"Deployed Auto Scaling Group " + autoScalingGroupName);
	}

	protected void associateElasticIpAddresses(List<String> instanceIds) {
		System.out.println("Associating Elastic IP addresses");

		if (!properties.containsKey("elastic.ip.addresses")) {
			return;
		}

		String elasticIpAddressesString = properties.getProperty(
			"elastic.ip.addresses");

		if ((elasticIpAddressesString == null) ||
			(elasticIpAddressesString.length() == 0)) {

			return;
		}

		String[] elasticIpAddresses = elasticIpAddressesString.split(",");

		for (int i = 0;
				(i < elasticIpAddresses.length) && (i < instanceIds.size());
					i++) {

			System.out.println(
				"Associating IP address " + elasticIpAddresses[i] +
					" with instance " + instanceIds.get(i));

			AssociateAddressRequest associateAddressRequest =
				new AssociateAddressRequest();

			associateAddressRequest.setInstanceId(instanceIds.get(i));
			associateAddressRequest.setPublicIp(elasticIpAddresses[i]);

			amazonEC2Client.associateAddress(associateAddressRequest);
		}
	}

	protected List<String> checkAutoScalingGroup(
			String autoScalingGroupName, int size)
		throws Exception {

		String asgardClusterName = properties.getProperty(
			"asgard.cluster.name");
		String availabilityZone = properties.getProperty("availability.zone");
		boolean deployed = false;
		JSONObject loadBalancerJSONObject = null;

		for (int i = 1;; i++) {
			String json = _jsonWebServiceClient.doGet(
				"/" + availabilityZone + "/loadBalancer/show/" +
					asgardClusterName + ".json",
				Collections.<String, String>emptyMap());

			loadBalancerJSONObject = new JSONObject(json);

			List<JSONObject> instanceStateJSONObjects =
				getInstanceStateJSONObjects(
					loadBalancerJSONObject, autoScalingGroupName);

			if (size != -1) {
				if (instanceStateJSONObjects.size() < size) {
					System.out.println(
						"Not enough instances started. Waiting " + i + "...");

					sleep(15);

					continue;
				}
			}

			if (!isInService(loadBalancerJSONObject, autoScalingGroupName)) {
				System.out.println(
					"Instances not in service. Waiting " + i + "...");

				sleep(15);
			}
			else {
				deployed = true;

				break;
			}
		}

		if (!deployed) {
			Map<String, String> parameters = new HashMap<>();

			parameters.put("name", autoScalingGroupName);

			_jsonWebServiceClient.doPost(
				"/" + availabilityZone + "/cluster/delete", parameters);

			throw new RuntimeException(
				"Unable to deploy Auto Scaling Group " + autoScalingGroupName);
		}

		List<String> instanceIds = new ArrayList<>();

		List<JSONObject> instanceStateJSONObjects = getInstanceStateJSONObjects(
			loadBalancerJSONObject, autoScalingGroupName);

		for (int i = 0; i < instanceStateJSONObjects.size(); i++) {
			JSONObject instanceStateJSONObject = instanceStateJSONObjects.get(
				i);

			String instanceId = instanceStateJSONObject.getString("instanceId");

			instanceIds.add(instanceId);
		}

		return instanceIds;
	}

	protected String createAutoScalingGroup() throws Exception {
		DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult =
			amazonAutoScalingClient.describeAutoScalingGroups();

		List<AutoScalingGroup> autoScalingGroups =
			describeAutoScalingGroupsResult.getAutoScalingGroups();

		int oldAutoScalingGroupsSize = autoScalingGroups.size();

		String availabilityZone = properties.getProperty("availability.zone");

		Map<String, String> parameters = new HashMap<>();

		parameters.put("checkHealth", "true");
		parameters.put("imageId", getImageId(_imageName));

		String asgardClusterName = properties.getProperty(
			"asgard.cluster.name");

		parameters.put("name", asgardClusterName);

		parameters.put("trafficAllowed", "true");

		if (!_parallelDeployment) {
			parameters.put("desiredCapacity", "1");
			parameters.put("min", "1");
		}

		_jsonWebServiceClient.doPost(
			"/" + availabilityZone + "/cluster/createNextGroup", parameters);

		for (int i = 0; i < 30; i++) {
			describeAutoScalingGroupsResult =
				amazonAutoScalingClient.describeAutoScalingGroups();

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

		for (int i = 0; i < 30; i++) {
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

			List<String> instanceIds = new ArrayList<>();

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

				List<Tag> tags = new ArrayList<>();

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

				amazonAutoScalingClient.createOrUpdateTags(
					createOrUpdateTagsRequest);

				created = true;

				break;
			}
		}

		if (!created) {
			throw new RuntimeException(
				"Unable to create Auto Scaling Group " + autoScalingGroupName);
		}

		if (!_parallelDeployment) {
			int minSize = Integer.parseInt(
				properties.getProperty("instance.min.size"));

			if (minSize > 1) {
				checkAutoScalingGroup(autoScalingGroupName, 1);

				parameters.clear();

				parameters.put("maxSize", String.valueOf(maxSize));
				parameters.put("minSize", String.valueOf(minSize));
				parameters.put("name", autoScalingGroupName);

				_jsonWebServiceClient.doPost(
					"/" + availabilityZone + "/cluster/resize", parameters);

				for (int i = 0; i < 30; i++) {
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
		}

		return autoScalingGroupName;
	}

	protected void deactivateOldScalingGroup(String autoScalingGroupName)
		throws Exception {

		String asgardClusterName = properties.getProperty(
			"asgard.cluster.name");
		String availabilityZone = properties.getProperty("availability.zone");

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

				Map<String, String> parameters = new HashMap<>();

				parameters.put("name", curAutoScalingGroupName);

				_jsonWebServiceClient.doPost(
					"/" + availabilityZone + "/cluster/deactivate", parameters);
			}
		}
	}

	protected List<JSONObject> getInstanceStateJSONObjects(
		JSONObject loadBalancerJSONObject, String autoScalingGroupName) {

		List<JSONObject> instanceStateJSONObjects = new ArrayList<>();

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

		return instanceStateJSONObjects;
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

		List<JSONObject> instanceStateJSONObjects = getInstanceStateJSONObjects(
			loadBalancerJSONObject, autoScalingGroupName);

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

	protected void openAsgardURL() throws Exception {
		Desktop desktop = Desktop.getDesktop();

		StringBuilder sb = new StringBuilder();

		sb.append(properties.getProperty("asgard.host.protocol"));
		sb.append("://");
		sb.append(properties.getProperty("asgard.host.name"));
		sb.append(":");
		sb.append(properties.getProperty("asgard.host.port"));
		sb.append("/");
		sb.append(properties.getProperty("availability.zone"));
		sb.append("/cluster/show/");
		sb.append(properties.getProperty("asgard.cluster.name"));

		desktop.browse(URI.create(sb.toString()));
	}

	private String _baseDirName;
	private String _imageName;
	private JSONWebServiceClient _jsonWebServiceClient;
	private boolean _parallelDeployment;

}