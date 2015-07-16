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

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.autoscaling.model.DeleteLaunchConfigurationRequest;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsRequest;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsResult;
import com.amazonaws.services.autoscaling.model.LaunchConfiguration;
import com.amazonaws.services.autoscaling.model.ResourceInUseException;
import com.amazonaws.services.ec2.model.DeleteVolumeRequest;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Volume;
import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.amazonaws.services.identitymanagement.model.User;

import jargs.gnu.CmdLineParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ivica Cardic
 * @author Mladen Cikara
 */
public class AMICleaner extends BaseAMITool {

	public static void main(String[] args) throws Exception {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option propertiesFileNameOption =
			cmdLineParser.addStringOption("properties.file.name");

		cmdLineParser.parse(args);

		try {
			new AMICleaner(
				(String)cmdLineParser.getOptionValue(propertiesFileNameOption));
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);

			return;
		}

		System.exit(0);
	}

	public AMICleaner(String propertiesFileName) throws Exception {
		super(propertiesFileName);

		if (isAcknowledged("Do you want to delete available Volumes?")) {
			System.out.println("Deleting available Volumes");

			deleteAvailableVolumes();
		}

		if (isAcknowledged(
				"Do you want to delete old Launch Configurations?")) {

			System.out.println("Deleting old Launch Configurations");

			deleteOldLaunchConfigurations();
		}

		if (isAcknowledged("Do you want to delete unused AMIs?")) {
			System.out.println("Deleting unused AMIs");

			deleteOldAMIs();
		}
	}

	protected void deleteAvailableVolumes() {
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
	}

	protected void deleteOldAMIs() {
		Set<String> amisInUse = getAMIsInUse();

		List<String> unusedAMIs = getUnusedAMIs(getUserId(), amisInUse);

		for (String unusedAMI : unusedAMIs) {
			if (isAcknowledged("Delete AMI with ID " + unusedAMI + "?")) {
				deleteAMI(unusedAMI);
			}
		}
	}

	protected void deleteOldLaunchConfigurations() {
		DescribeLaunchConfigurationsRequest
			describeLaunchConfigurationsRequest =
				new DescribeLaunchConfigurationsRequest();

		DescribeLaunchConfigurationsResult describeLaunchConfigurationsResult =
			amazonAutoScalingClient.describeLaunchConfigurations(
				describeLaunchConfigurationsRequest);

		List<LaunchConfiguration> launchConfigurations =
			describeLaunchConfigurationsResult.getLaunchConfigurations();

		for (int i = 0; i < launchConfigurations.size(); i++) {
			DeleteLaunchConfigurationRequest deleteLaunchConfigurationRequest =
				new DeleteLaunchConfigurationRequest();

			LaunchConfiguration launchConfiguration = launchConfigurations.get(
				i);

			deleteLaunchConfigurationRequest.setLaunchConfigurationName(
				launchConfiguration.getLaunchConfigurationName());

			try {
				amazonAutoScalingClient.deleteLaunchConfiguration(
					deleteLaunchConfigurationRequest);
			}
			catch (ResourceInUseException riue) {
			}
		}
	}

	protected Set<String> getAMIsInUse() {
		DescribeInstancesResult describeInstancesResult =
			amazonEC2Client.describeInstances();

		Set<String> amis = new HashSet<String>();

		for (Reservation reservation :
				describeInstancesResult.getReservations()) {

			for (Instance instance : reservation.getInstances()) {
				amis.add(instance.getImageId());
			}
		}

		return amis;
	}

	protected List<String> getUnusedAMIs(String userId, Set<String> amisInUse) {
		DescribeImagesRequest describeImagesRequest =
			new DescribeImagesRequest();

		ArrayList<String> owners = new ArrayList<String>();

		owners.add(userId);

		describeImagesRequest.setOwners(owners);

		DescribeImagesResult describeImagesResult =
			amazonEC2Client.describeImages(describeImagesRequest);

		List<Image> images = describeImagesResult.getImages();

		List<String> unusedAMIs = new ArrayList<String>();

		for (Image image : images) {
			String imageName = image.getName();

			if ((imageName != null) &&
				imageName.startsWith("osb-lcs-") &&
				!amisInUse.contains(image.getImageId())) {

				unusedAMIs.add(image.getImageId());
			}
		}

		return unusedAMIs;
	}

	protected String getUserId() {
		String userId = null;
		try {
			GetUserResult getUserResult =
				amazonIdentityManagementClient.getUser();

			User user = getUserResult.getUser();

			userId = user.getUserId();
		}
		catch (AmazonServiceException e) {
			if (e.getErrorCode().compareTo("AccessDenied") == 0) {
				String msg = e.getMessage();

				int startIndex = msg.indexOf("::");

				int endIndex = msg.indexOf(":", startIndex + 2);

				userId = msg.substring(startIndex + 2, endIndex);
			}
		}

		return userId;
	}

	private void deleteAMI(String unusedAMI) {
		DeregisterImageRequest deregisterImageRequest =
			new DeregisterImageRequest();

		deregisterImageRequest.setImageId(unusedAMI);

		amazonEC2Client.deregisterImage(deregisterImageRequest);
	}

}