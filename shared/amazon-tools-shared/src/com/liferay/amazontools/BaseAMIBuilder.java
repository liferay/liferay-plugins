/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;

import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class BaseAMIBuilder {

	public BaseAMIBuilder(String buildFilePath) throws Exception {
		properties = getProperties(buildFilePath);

		amazonEC2Client = getAmazonEC2Client(
			properties.getProperty(_PROPERTY_KEY_ACCESS_KEY),
			properties.getProperty(_PROPERTY_KEY_SECRET_KEY),
			properties.getProperty(_PROPERTY_KEY_ENDPOINT));
	}

	protected AmazonEC2Client getAmazonEC2Client(
		String accessKey, String secretKey, String endpoint) {

		AWSCredentials awsCredentials = new BasicAWSCredentials(
			accessKey, secretKey);

		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(awsCredentials);

		amazonEC2Client.setEndpoint(endpoint);

		return amazonEC2Client;
	}

	protected String getImageId(String imageName) {
		DescribeImagesRequest describeImagesRequest =
			new DescribeImagesRequest();

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();

		filter.setName("name");

		List<String> values = new ArrayList<String>();

		values.add(imageName);

		filter.setValues(values);

		filters.add(filter);

		describeImagesRequest.setFilters(filters);

		DescribeImagesResult describeImagesResult =
			amazonEC2Client.describeImages(describeImagesRequest);

		List<Image> images = describeImagesResult.getImages();

		if (images.isEmpty()) {
			throw new RuntimeException(
				"Image "  + imageName + " doesn't exists");
		}
		else {
			Image image = images.get(0);

			return image.getImageId();
		}
	}

	protected Properties getProperties(String buildFilePath) throws Exception {
		Properties buildProperties = new Properties();

		InputStream inputStream = new FileInputStream(buildFilePath);

		buildProperties.load(inputStream);

		Properties properties = new Properties();

		for (Object key : buildProperties.keySet()) {
			String keyString = (String)key;

			if (keyString.startsWith("#")) {
				continue;
			}

			properties.put(keyString, buildProperties.get(key));
		}

		return properties;
	}

	protected void sleep(long timeout) {
		try {
			TimeUnit.SECONDS.sleep(timeout);
		}
		catch (InterruptedException ie) {
		}
	}

	protected AmazonEC2Client amazonEC2Client;
	protected Properties properties;

	private static final String _PROPERTY_KEY_ACCESS_KEY = "access.key";

	private static final String _PROPERTY_KEY_ENDPOINT = "endpoint";

	private static final String _PROPERTY_KEY_SECRET_KEY = "secret.key";

}