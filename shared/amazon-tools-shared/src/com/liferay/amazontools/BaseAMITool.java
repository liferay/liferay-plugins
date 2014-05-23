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
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;

import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class BaseAMITool {

	public BaseAMITool(String propertiesFileName) throws Exception {
		properties = getProperties(propertiesFileName);

		amazonEC2Client = getAmazonEC2Client(
			properties.getProperty("access.key"),
			properties.getProperty("secret.key"),
			properties.getProperty("ec2.endpoint"));
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

		Image image = null;

		for (int i = 0; i < 6; i++) {
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
				sleep(30);

				continue;
			}

			image = images.get(0);

			break;
		}

		if (image == null) {
			throw new RuntimeException(
				"Image " + imageName + " does not exist");
		}

		return image.getImageId();
	}

	protected Properties getProperties(String propertiesFileName)
		throws Exception {

		Properties properties = new Properties();

		InputStream inputStream = new FileInputStream(propertiesFileName);

		properties.load(inputStream);

		Set<Map.Entry<Object, Object>> set = properties.entrySet();

		Iterator<Map.Entry<Object, Object>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<Object, Object> entry = iterator.next();

			String key = (String)entry.getKey();

			if (key.startsWith("#")) {
				iterator.remove();
			}
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

}