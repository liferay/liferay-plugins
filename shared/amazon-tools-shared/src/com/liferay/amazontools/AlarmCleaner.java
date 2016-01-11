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
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsResult;
import com.amazonaws.services.cloudwatch.model.DeleteAlarmsRequest;
import com.amazonaws.services.cloudwatch.model.DescribeAlarmsRequest;
import com.amazonaws.services.cloudwatch.model.DescribeAlarmsResult;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricAlarm;

import jargs.gnu.CmdLineParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mladen Cikara
 */
public class AlarmCleaner extends BaseAMITool {

	public static void main(String[] args) throws Exception {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option propertiesFileNameOption =
			cmdLineParser.addStringOption("properties.file.name");

		cmdLineParser.parse(args);

		try {
			new AlarmCleaner(
				(String)cmdLineParser.getOptionValue(propertiesFileNameOption));
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);

			return;
		}

		System.exit(0);
	}

	public AlarmCleaner(String propertiesFileName) throws Exception {
		super(propertiesFileName);

		deleteMetricAlarms();
	}

	public List<String> getActiveAutoScalingGroupNames() {
		List<String> autoScalingGroupNames = new ArrayList<>();

		DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult =
			amazonAutoScalingClient.describeAutoScalingGroups();

		List<AutoScalingGroup> autoScalingGroups =
			describeAutoScalingGroupsResult.getAutoScalingGroups();

		for (AutoScalingGroup autoScalingGroup : autoScalingGroups) {
			autoScalingGroupNames.add(
				autoScalingGroup.getAutoScalingGroupName());
		}

		return autoScalingGroupNames;
	}

	protected void deleteMetricAlarms() {
		System.out.println("Deleting metric alarms");

		List<String> inactiveMetricAlarmNames = getInactiveMetricAlarmNames(
			getAutoScalingGroupsMetricAlarmNames(),
			getActiveAutoScalingGroupNames());

		for (String metricAlarmName : inactiveMetricAlarmNames) {
			System.out.println("Deleting metric alarm " + metricAlarmName);

			DeleteAlarmsRequest deleteAlarmsRequest = new DeleteAlarmsRequest();

			List<String> metricAlarmNames = new ArrayList<>();

			metricAlarmNames.add(metricAlarmName);

			deleteAlarmsRequest.setAlarmNames(metricAlarmNames);

			amazonCloudWatchClient.deleteAlarms(deleteAlarmsRequest);
		}
	}

	protected String getAutoScalingGroupName(List<Dimension> dimensions) {
		for (Dimension dimension : dimensions) {
			String name = dimension.getName();

			if (name.equals("AutoScalingGroupName")) {
				return dimension.getValue();
			}
		}

		return null;
	}

	protected Map<String, List<String>> getAutoScalingGroupsMetricAlarmNames() {
		Map<String, List<String>> autoScalingGroupsMetricAlarmNames =
			new HashMap<>();

		DescribeAlarmsResult describeAlarmsResult =
			amazonCloudWatchClient.describeAlarms();

		String nextToken = null;

		do {
			if (nextToken != null) {
				DescribeAlarmsRequest describeAlarmsRequest =
					new DescribeAlarmsRequest();

				describeAlarmsRequest.setNextToken(nextToken);

				describeAlarmsResult = amazonCloudWatchClient.describeAlarms(
					describeAlarmsRequest);
			}

			List<MetricAlarm> metricAlarms =
				describeAlarmsResult.getMetricAlarms();

			for (MetricAlarm metricAlarm : metricAlarms) {
				String autoScalingGroupName = getAutoScalingGroupName(
					metricAlarm.getDimensions());

				if (autoScalingGroupName == null) {
					continue;
				}

				List<String> alarmNames = autoScalingGroupsMetricAlarmNames.get(
					autoScalingGroupName);

				if (alarmNames == null) {
					alarmNames = new ArrayList<>();
				}

				alarmNames.add(metricAlarm.getAlarmName());

				autoScalingGroupsMetricAlarmNames.put(
					autoScalingGroupName, alarmNames);
			}

			nextToken = describeAlarmsResult.getNextToken();
		}
		while (nextToken != null);

		return autoScalingGroupsMetricAlarmNames;
	}

	protected List<String> getInactiveMetricAlarmNames(
		Map<String, List<String>> autoScalingGroupsMetricAlarmNames,
		List<String> activeAutoScalingGroupNames) {

		List<String> inactiveMetricAlarmNames = new ArrayList<>();

		for (String autoScalingGroupName :
				autoScalingGroupsMetricAlarmNames.keySet()) {

			if (activeAutoScalingGroupNames.contains(autoScalingGroupName)) {
				continue;
			}

			inactiveMetricAlarmNames.addAll(
				autoScalingGroupsMetricAlarmNames.get(autoScalingGroupName));
		}

		return inactiveMetricAlarmNames;
	}

}