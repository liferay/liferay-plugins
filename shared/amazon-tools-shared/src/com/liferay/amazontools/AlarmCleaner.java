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

		if (isAcknowledged("Do you want to Metric Alarms?")) {
			deleteMetricAlarms();
		}
	}

	public List<String> getActiveAutoScalingGroups() {
		List<String> allAutoScalingGroups = new ArrayList<String>();

		DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult =
			amazonAutoScalingClient.describeAutoScalingGroups();

		List<AutoScalingGroup> autoScalingGroups =
			describeAutoScalingGroupsResult.getAutoScalingGroups();

		for (AutoScalingGroup autoScalingGroup : autoScalingGroups) {
			allAutoScalingGroups.add(
				autoScalingGroup.getAutoScalingGroupName());
		}

		return allAutoScalingGroups;
	}

	private void deleteMetricAlarms() {
		System.out.println("Finding Metric Alarms for deleting...");

		Map<String, String> allMetricAlarms = getAllMetricAlarms();

		List<String> activeAutoScalingGroups = getActiveAutoScalingGroups();

		List<String> allMetricAlarmsForDeleting =
			getAllMetricAlarmsForDeleting(
				allMetricAlarms, activeAutoScalingGroups);

		for (String metricAlarmName : allMetricAlarmsForDeleting) {
			if (isAcknowledged("Delete Metric Alarm " + metricAlarmName +"?")) {
				System.out.println("Deleting Metric Alarm " + metricAlarmName);

				DeleteAlarmsRequest deleteAlarmsRequest =
					new DeleteAlarmsRequest();

				List<String> alarmNames = new ArrayList<String>();

				alarmNames.add(metricAlarmName);

				deleteAlarmsRequest.setAlarmNames(alarmNames);

				amazonCloudWatchClient.deleteAlarms(deleteAlarmsRequest);
			}
		}
	}

	private Map<String, String> getAllMetricAlarms() {
		Map<String, String> metricAlarmsMap = new HashMap<String, String>();

		DescribeAlarmsResult describeAlarmsResult =
			amazonCloudWatchClient.describeAlarms();

		String nextToken = null;
		do {
			if (nextToken != null) {
				DescribeAlarmsRequest describeAlarmsRequest =
					new DescribeAlarmsRequest();
				describeAlarmsRequest.setNextToken(nextToken);

				describeAlarmsResult =
					amazonCloudWatchClient.describeAlarms(
						describeAlarmsRequest);
			}

			List<MetricAlarm> metricAlarms =
				describeAlarmsResult.getMetricAlarms();

			for (MetricAlarm metricAlarm : metricAlarms) {
				String autoScalingGroup = getAutoScalingGroup(
					metricAlarm.getDimensions());

				if (autoScalingGroup != null) {
					metricAlarmsMap.put(
						autoScalingGroup, metricAlarm.getAlarmName());
				}
			}

			nextToken = describeAlarmsResult.getNextToken();

		} while (nextToken != null);

		return metricAlarmsMap;
	}

	private List<String> getAllMetricAlarmsForDeleting(
		Map<String, String> allMetricAlarms,
		List<String> activeAutoScalingGroups) {

		List<String> allMetricAlarmsForDeleting = new ArrayList<String>();

		for (String autoScalingGroupName : allMetricAlarms.keySet()) {
			if (!activeAutoScalingGroups.contains(autoScalingGroupName)) {
				allMetricAlarmsForDeleting.add(
					allMetricAlarms.get(autoScalingGroupName));
			}
		}

		return allMetricAlarmsForDeleting;
	}

	private String getAutoScalingGroup(List<Dimension> dimensions) {
		for (Dimension dimension : dimensions) {
			if (dimension.getName().equals("AutoScalingGroupName")) {
				return dimension.getValue();
			}
		}

		return null;
	}

}