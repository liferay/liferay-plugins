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
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsRequest;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsResult;
import com.amazonaws.services.autoscaling.model.LaunchConfiguration;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import jargs.gnu.CmdLineParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mladen Cikara
 */
public class S3Cleaner extends BaseAMITool {

	public static void main(String[] args) throws Exception {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option propertiesFileNameOption =
			cmdLineParser.addStringOption("properties.file.name");

		cmdLineParser.parse(args);

		try {
			new S3Cleaner(
				(String)cmdLineParser.getOptionValue(propertiesFileNameOption));
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);

			return;
		}

		System.exit(0);
	}

	public S3Cleaner(String propertiesFileName) throws Exception {
		super(propertiesFileName);

		deleteBuckets();
	}

	protected void deleteBucket(String bucketName) {
		ObjectListing objectListing = amazonS3Client.listObjects(bucketName);

		List<S3ObjectSummary> objectSummaries =
			objectListing.getObjectSummaries();

		if (!objectSummaries.isEmpty()) {
			DeleteObjectsRequest deleteObjectsRequest =
				new DeleteObjectsRequest(bucketName);

			List<DeleteObjectsRequest.KeyVersion> keyVersions =
				new ArrayList<DeleteObjectsRequest.KeyVersion>();

			for (S3ObjectSummary objectSummary : objectSummaries) {
				keyVersions.add(
					new DeleteObjectsRequest.KeyVersion(
						objectSummary.getKey()));
			}

			deleteObjectsRequest.setKeys(keyVersions);

			amazonS3Client.deleteObjects(deleteObjectsRequest);
		}

		amazonS3Client.deleteBucket(bucketName);
	}

	protected void deleteBuckets() {
		System.out.println("Deleting buckets");

		List<String> timestamps = new ArrayList<String>();

		List<String> imageNames = getImageNames(
			getImageIds(getLaunchConfigurationNames()));

		for (String imageName : imageNames) {
			timestamps.add(getTimestamp(imageName));
		}

		List<String> bucketNames = getBucketNames(timestamps);

		for (String bucketName : bucketNames) {
			System.out.println("Deleting bucket " + bucketName);

			deleteBucket(bucketName);
		}
	}

	protected List<String> getBucketNames(List<String> timestamps) {
		List<String> bucketNames = new ArrayList<String>();

		List<Bucket> buckets = amazonS3Client.listBuckets();

		for (Bucket bucket : buckets) {
			String name = bucket.getName();

			if (name.startsWith("frw-cluster") ||
				name.startsWith("lcs-cluster")) {

				String timestamp = getTimestamp(name);

				if (!timestamps.contains(timestamp)) {
					bucketNames.add(bucket.getName());
				}
			}
		}

		return bucketNames;
	}

	protected List<String> getImageIds(List<String> launchConfigurationNames) {
		List<String> imageIds = new ArrayList<String>();

		DescribeLaunchConfigurationsRequest
			describeLaunchConfigurationsRequest =
				new DescribeLaunchConfigurationsRequest();

		describeLaunchConfigurationsRequest.setLaunchConfigurationNames(
			launchConfigurationNames);

		DescribeLaunchConfigurationsResult
			describeLaunchConfigurationsResult =
				amazonAutoScalingClient.describeLaunchConfigurations(
					describeLaunchConfigurationsRequest);

		for (LaunchConfiguration launchConfiguration :
				describeLaunchConfigurationsResult.getLaunchConfigurations()) {

			imageIds.add(launchConfiguration.getImageId());
		}

		return imageIds;
	}

	protected List<String> getImageNames(List<String> imageIds) {
		List<String> imageNames = new ArrayList<String>();

		DescribeImagesRequest describeImagesRequest =
			new DescribeImagesRequest();

		describeImagesRequest.setImageIds(imageIds);

		DescribeImagesResult describeImagesResult =
			amazonEC2Client.describeImages(describeImagesRequest);

		for (Image image : describeImagesResult.getImages()) {
			imageNames.add(image.getName());
		}

		return imageNames;
	}

	protected List<String> getLaunchConfigurationNames() {
		List<String> launchConfigurationNames = new ArrayList<String>();

		DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult =
			amazonAutoScalingClient.describeAutoScalingGroups();

		List<AutoScalingGroup> autoScalingGroups =
			describeAutoScalingGroupsResult.getAutoScalingGroups();

		for (AutoScalingGroup autoScalingGroup : autoScalingGroups) {
			launchConfigurationNames.add(
				autoScalingGroup.getLaunchConfigurationName());
		}

		return launchConfigurationNames;
	}

	protected String getTimestamp(String imageName) {
		return imageName.substring(imageName.lastIndexOf('-') + 1);
	}

}