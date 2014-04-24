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
		}
	}

	public AsgardAMIDeployer(String imageName, String propertiesFileName)
		throws Exception {

		super(propertiesFileName);

		_imageName = imageName;

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

		for (int i = 1; i < 20; i++) {
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

			System.out.println(
				"Unable to deploy Auto Scaling Group " + autoScalingGroupName);

			return;
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

		String availabilityZone = properties.getProperty("availability.zone");

		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("checkHealth", "true");
		parameters.put("imageId", getImageId(_imageName));

		String asgardClusterName = properties.getProperty(
			"asgard.cluster.name");

		parameters.put("name", asgardClusterName);

		parameters.put("trafficAllowed", "true");

		_jsonWebServiceClient.doPost(
			"/" + availabilityZone + "/cluster/createNextGroup", parameters);

		sleep(20);

		String autoScalingGroupName = null;
		boolean created = false;

		for (int i = 0; i < 6; i++) {
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

			if (!isInService(autoScalingGroupJSONObject)) {
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

			String instanceStateAutoScalingGroupName =
				instanceStateJSONObject.getString("autoScalingGroupName");

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

	private String _imageName;
	private JSONWebServiceClient _jsonWebServiceClient;

}