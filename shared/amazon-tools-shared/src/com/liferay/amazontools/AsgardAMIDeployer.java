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

import com.liferay.jsonwebserviceclient.JSONWebServiceClient;
import com.liferay.jsonwebserviceclient.JSONWebServiceClientImpl;

import jargs.gnu.CmdLineParser;

import java.awt.Desktop;

import java.net.URI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Ivica Cardic
 */
public class AsgardAMIDeployer extends BaseAMITool {

	public static void main(String[] args) throws Exception {
		CmdLineParser parser = new CmdLineParser();

		CmdLineParser.Option propertiesFileNameOption = parser.addStringOption(
			"properties.file.name");
		CmdLineParser.Option imageNameOption = parser.addStringOption(
			"image.name");

		parser.parse(args);

		try {
			new AsgardAMIDeployer(
				(String)parser.getOptionValue(propertiesFileNameOption),
				(String)parser.getOptionValue(imageNameOption));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AsgardAMIDeployer(String propertiesFileName, String imageName)
		throws Exception {

		super(propertiesFileName);

		_imageName = imageName;

		_jsonWebServiceClient = getJSONWebServiceClient(
			properties.getProperty("asgard.host.name"),
			Integer.valueOf(properties.getProperty("asgard.host.port")));

		String autoScalingGroupName = createNextAutoScalingGroup();

		if (autoScalingGroupName != null) {
			checkNextAutoScalingGroupDeployment(autoScalingGroupName);
		}
	}

	protected void checkNextAutoScalingGroupDeployment(
			String autoScalingGroupName)
		throws Exception {

		System.out.println(
			"Checking deployment of Auto Scaling Group " +
				autoScalingGroupName + ".");

		String availabilityZone = properties.getProperty("availability.zone");
		String clusterName = properties.getProperty("asgard.cluster.name");

		String showLoadBalancerUrl =
			"/" + availabilityZone + "/loadBalancer/show/" + clusterName +
				".json";

		int attempts = 1;
		boolean deployed = false;

		while (attempts++ < 20) {
			String result = _jsonWebServiceClient.doGet(
				showLoadBalancerUrl, Collections.<String, String>emptyMap());

			JSONObject loadBalancer = new org.json.JSONObject(result);

			JSONArray instanceStates = loadBalancer.getJSONArray(
				"instanceStates");

			boolean inService = true;

			for (int i = 0; i < instanceStates.length(); i++) {
				JSONObject instanceState = instanceStates.getJSONObject(i);

				String curAutoScalingGroupName = instanceState.getString(
					"autoScalingGroupName");

				if (!curAutoScalingGroupName.equals(autoScalingGroupName)) {
					continue;
				}

				String state = instanceState.getString("state");

				if (!state.equals("InService")) {
					inService = false;

					break;
				}
			}

			if (!inService) {
				sleep(30);
			}
			else {
				deployed = true;

				break;
			}
		}

		if (!deployed) {
			String deleteAutoScalingGroupUrl =
				"/" + availabilityZone + "/cluster/delete";

			Map<String, String> parameters = new HashMap<String, String>();

			parameters.put("name", autoScalingGroupName);

			_jsonWebServiceClient.doPost(deleteAutoScalingGroupUrl, parameters);

			System.out.println(
				"Unable to deploy Auto Scaling Group " + autoScalingGroupName);

			return;
		}

		String clusterListUrl = "/" + availabilityZone + "/cluster/list.json";

		String result = _jsonWebServiceClient.doGet(
			clusterListUrl, Collections.<String, String>emptyMap());

		JSONArray clusters = new org.json.JSONArray(result);

		for (int i = 0; i < clusters.length(); i++) {
			JSONObject cluster = clusters.getJSONObject(i);

			String curClusterName = cluster.getString("cluster");

			if (!curClusterName.equals(clusterName)) {
				continue;
			}

			JSONArray autoScalingGroups = cluster.getJSONArray(
				"autoScalingGroups");

			for (int j = 0; j < autoScalingGroups.length(); j++) {
				String curAutoScalingGroupName = autoScalingGroups.getString(j);

				if (curAutoScalingGroupName.equals(autoScalingGroupName)) {
					continue;
				}

				String deactivateAutoScalingGroupUrl =
					"/" + availabilityZone + "/cluster/deactivate";

				Map<String, String> parameters = new HashMap<String, String>();

				parameters.put("name", curAutoScalingGroupName);

				_jsonWebServiceClient.doPost(
					deactivateAutoScalingGroupUrl, parameters);
			}
		}

		System.out.println(
			"Deployed Auto Scaling Group " + autoScalingGroupName);

		String asgardClusterUrl =
			"http://" + properties.getProperty("asgard.host.name") + ":" +
				properties.getProperty("asgard.host.port") + "/" +
				availabilityZone + "/cluster/show/" + clusterName;

		Desktop.getDesktop().browse(URI.create(asgardClusterUrl));
	}

	protected String createNextAutoScalingGroup() throws Exception {
		System.out.println("Creating Auto Scaling Group");

		String availabilityZone = properties.getProperty("availability.zone");

		String createNextGroupUrl =
			"/" + availabilityZone + "/cluster/createNextGroup";

		Map<String, String> parameters = new HashMap<String, String>();

		String imageId = getImageId(_imageName);

		parameters.put("checkHealth", "true");
		parameters.put("imageId", imageId);
		parameters.put("trafficAllowed", "true");

		String clusterName = properties.getProperty("asgard.cluster.name");

		parameters.put("name", clusterName);

		_jsonWebServiceClient.doPost(createNextGroupUrl, parameters);

		sleep(20);

		String showClusterUrl =
			"/" + availabilityZone + "/cluster/show/" + clusterName + ".json";

		String autoScalingGroupName = null;

		int attempts = 1;

		boolean created = false;

		while (attempts++ < 7) {
			String result = _jsonWebServiceClient.doGet(
				showClusterUrl, Collections.<String, String>emptyMap());

			JSONArray autoScalingGroups = new org.json.JSONArray(result);

			JSONObject autoScalingGroup = autoScalingGroups.getJSONObject(
				autoScalingGroups.length() - 1);

			autoScalingGroupName = autoScalingGroup.getString(
				"autoScalingGroupName");

			JSONArray instances = autoScalingGroup.getJSONArray("instances");

			boolean inService = true;

			for (int i = 0; i < instances.length(); i++) {
				JSONObject instance = instances.getJSONObject(i);

				String lifecycleState = instance.getString("lifecycleState");

				if (!lifecycleState.equals("InService")) {
					inService = false;

					break;
				}
			}

			if (!inService) {
				sleep(15);
			}
			else {
				created = true;

				break;
			}
		}

		if (!created) {
			System.out.println(
				"Unable to create Auto Scaling Group " + autoScalingGroupName);

			return null;
		}

		sleep(10);

		System.out.println(
			"Created Auto Scaling Group " + autoScalingGroupName);

		return autoScalingGroupName;
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

	private String _imageName;
	private JSONWebServiceClient _jsonWebServiceClient;

}