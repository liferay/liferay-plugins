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

		deleteS3Buckets();
	}

	private void deleteBucket(String bucketName) {
		ObjectListing objectListing = amazonS3Client.listObjects(bucketName);

		List<S3ObjectSummary> objectSummaries =
			objectListing.getObjectSummaries();

		if (objectSummaries.size() != 0) {
			DeleteObjectsRequest multiObjectDeleteRequest =
				new DeleteObjectsRequest(bucketName);

			List<DeleteObjectsRequest.KeyVersion> keys =
				new ArrayList<DeleteObjectsRequest.KeyVersion>();

			for (S3ObjectSummary objectSummary : objectSummaries) {
				keys.add(
					new DeleteObjectsRequest.KeyVersion(
						objectSummary.getKey()));
			}

			multiObjectDeleteRequest.setKeys(keys);

			amazonS3Client.deleteObjects(multiObjectDeleteRequest);
		}

		amazonS3Client.deleteBucket(bucketName);
	}

	private void deleteS3Buckets() {
		System.out.println("Finding S3 buckets for deleting...");

		List<String> activeLaunchConfigurationNames =
			getActiveLaunchConfigurationNames();

		List<String> activeAMIs = getAMIForLunchConfigurations(
			activeLaunchConfigurationNames);

		List<String> amiNames = getAMINames(activeAMIs);

		List<String> timestamps = new ArrayList<String>();

		for (String amiName : amiNames) {
			timestamps.add(getTimestamp(amiName));
		}

		List<String> s3BucketNamesForDeleting = getS3BucketNamesForDeleting(
			timestamps);

		for (String s3BucketName : s3BucketNamesForDeleting) {
			if (isAcknowledged("Delete bucket " + s3BucketName +"?")) {
				System.out.println(
					"Deleting Bucket " +
						s3BucketName + " and all its contents");

				deleteBucket(s3BucketName);
			}
		}
	}

	private List<String> getActiveLaunchConfigurationNames() {
		List<String> activeLaunchConfigurationNames = new ArrayList<String>();

		DescribeAutoScalingGroupsResult describeAutoScalingGroupsResult =
			amazonAutoScalingClient.describeAutoScalingGroups();

		List<AutoScalingGroup> autoScalingGroups =
			describeAutoScalingGroupsResult.getAutoScalingGroups();

		for (AutoScalingGroup autoScalingGroup : autoScalingGroups) {
			activeLaunchConfigurationNames.add(
				autoScalingGroup.getLaunchConfigurationName());
		}

		return activeLaunchConfigurationNames;
	}

	private List<String> getAMIForLunchConfigurations(
		List<String> activeLaunchConfigurationNames) {

		DescribeLaunchConfigurationsRequest
			describeLaunchConfigurationsRequest =
				new DescribeLaunchConfigurationsRequest();

		describeLaunchConfigurationsRequest.setLaunchConfigurationNames(
			activeLaunchConfigurationNames);

		DescribeLaunchConfigurationsResult
			describeLaunchConfigurationsResult =
				amazonAutoScalingClient.describeLaunchConfigurations(
					describeLaunchConfigurationsRequest);

		List<String> amis = new ArrayList<String>();

		for (LaunchConfiguration launchConfiguration :
				describeLaunchConfigurationsResult.getLaunchConfigurations()) {

			amis.add(launchConfiguration.getImageId());
		}

		return amis;
	}

	private List<String> getAMINames(List<String> activeAMIs) {
		DescribeImagesRequest describeImagesRequest =
			new DescribeImagesRequest();

		describeImagesRequest.setImageIds(activeAMIs);

		DescribeImagesResult describeImagesResult =
			amazonEC2Client.describeImages(describeImagesRequest);

		List<String> amiNames = new ArrayList<String>();

		for (Image image : describeImagesResult.getImages()) {
			amiNames.add(image.getName());
		}

		return amiNames;
	}

	private List<String> getS3BucketNamesForDeleting(List<String> timestamps) {
		List<String> bucketsForDeleting = new ArrayList<String>();

		List<Bucket> buckets = amazonS3Client.listBuckets();

		for (Bucket bucket : buckets) {
			if (bucket.getName().startsWith("frw-cluster") ||
				bucket.getName().startsWith("lcs-cluster")) {

				String timestamp = getTimestamp(bucket.getName());

				if (!timestamps.contains(timestamp)) {
					bucketsForDeleting.add(bucket.getName());
				}
			}
		}

		return bucketsForDeleting;
	}

	private String getTimestamp(String amiName) {
		int start = amiName.lastIndexOf('-');

		return amiName.substring(start + 1);
	}

}