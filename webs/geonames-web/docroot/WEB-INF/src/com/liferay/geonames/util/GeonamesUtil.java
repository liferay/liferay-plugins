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

package com.liferay.geonames.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class GeoNamesUtil {

	public static void buildRootGeoNameNode() throws Exception {
		URL url = new URL(_geoNamesDataURL);

		ZipInputStream zipInputStream = new ZipInputStream(url.openStream());

		zipInputStream.getNextEntry();

		buildRootGeoNameNode(zipInputStream);
	}

	public static void buildRootGeoNameNode(InputStream inputStream)
		throws Exception {

		List<GeoName> geoNames = new ArrayList<GeoName>();

		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

			String line = bufferedReader.readLine();

			while (line != null) {
				String[] data = line.split("\t");

				geoNames.add(new GeoName(data));

				line = bufferedReader.readLine();
			}
		}
		finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}
		}

		_rootGeoNameNode = getGeoNameNode(geoNames, 0);
	}

	public static void buildRootGeoNameNode(String filePathName)
		throws Exception {

		File file = new File(filePathName);

		if (!file.exists() || !file.canRead()) {
			buildRootGeoNameNode();
		}
		else {
			buildRootGeoNameNode(new FileInputStream(file));
		}
	}

	public static String getGeoNameJSON(double latitude, double longitude)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		if (_rootGeoNameNode == null) {
			buildRootGeoNameNode();
		}

		GeoNameNode geoNameNode = searchGeoNames(
			_rootGeoNameNode, new GeoName(latitude, longitude), 0);

		GeoName geoName = geoNameNode.getGeoName();

		jsonObject.put("countryCode", geoName.getCountryCode());
		jsonObject.put("name", geoName.getName());

		return jsonObject.toString();
	}

	public static void setGeoNamesDataURL(String geoNamesDataURL)
		throws Exception {

		_geoNamesDataURL = geoNamesDataURL;
	}

	protected static Comparator<GeoName> getComparator(int cuttingDimension) {
		int axis = cuttingDimension % _NUMBER_OF_DIMENSIONS;

		if (axis == 0) {
			return _geoNameXComparator;
		}

		if (axis == 1) {
			return _geoNameYComparator;
		}

		return _geoNameZComparator;
	}

	protected static double getDistance(GeoName geoName1, GeoName geoName2) {
		double dx = geoName1.getX() - geoName2.getX();
		double dy = geoName1.getY() - geoName2.getY();
		double dz = geoName1.getZ() - geoName2.getZ();

		return (dx * dx) + (dy * dy) + (dz * dz);
	}

	protected static double getDistance(
		GeoName geoName1, GeoName geoName2, int cuttingDimension) {

		int axis = cuttingDimension % _NUMBER_OF_DIMENSIONS;

		double delta = 0;

		if (axis == 0) {
			delta = geoName1.getX() - geoName2.getX();
		}
		else if (axis == 1) {
			delta = geoName1.getY() - geoName2.getY();
		}
		else {
			delta = geoName1.getZ() - geoName2.getZ();
		}

		return delta * delta;
	}

	protected static GeoNameNode getGeoNameNode(
		List<GeoName> geoNames, int cuttingDimension) {

		if (geoNames.isEmpty()) {
			return null;
		}

		Collections.sort(geoNames, getComparator(cuttingDimension));

		int index = geoNames.size() / 2;

		GeoName geoName = geoNames.get(index);
		GeoNameNode leftGeoNameNode = getGeoNameNode(
			new ArrayList<GeoName>(geoNames.subList(0, index)),
			cuttingDimension + 1);
		GeoNameNode rightGeoNameNode = getGeoNameNode(
			new ArrayList<GeoName>(
				geoNames.subList(index + 1, geoNames.size())),
			cuttingDimension + 1);

		return new GeoNameNode(geoName, leftGeoNameNode, rightGeoNameNode);
	}

	protected static GeoNameNode searchGeoNames(
		GeoNameNode geoNameNode, GeoName targetGeoName, int cuttingDimension) {

		GeoNameNode closestGeoNameNode = null;
		GeoNameNode nextGeoNameNode = null;
		GeoNameNode previousGeoNameNode = null;

		Comparator<GeoName> comparator = getComparator(cuttingDimension);

		if (comparator.compare(targetGeoName, geoNameNode.getGeoName()) < 0) {
			nextGeoNameNode = geoNameNode.getLeftGeoNameNode();
			previousGeoNameNode = geoNameNode.getRightGeoNameNode();
		}
		else {
			nextGeoNameNode = geoNameNode.getRightGeoNameNode();
			previousGeoNameNode = geoNameNode.getLeftGeoNameNode();
		}

		if (nextGeoNameNode == null) {
			closestGeoNameNode = geoNameNode;
		}
		else {
			closestGeoNameNode = searchGeoNames(
				nextGeoNameNode, targetGeoName, cuttingDimension + 1);
		}

		double currentDistance = getDistance(
			geoNameNode.getGeoName(), targetGeoName);
		double closestDistance = getDistance(
			closestGeoNameNode.getGeoName(), targetGeoName);

		if (currentDistance < closestDistance) {
			closestGeoNameNode = geoNameNode;
			closestDistance = currentDistance;
		}

		if (previousGeoNameNode != null) {
			double oneDimensionalDistance = getDistance(
				geoNameNode.getGeoName(), targetGeoName, cuttingDimension);

			if (oneDimensionalDistance < closestDistance) {
				geoNameNode = searchGeoNames(
					previousGeoNameNode, targetGeoName, cuttingDimension + 1);

				currentDistance = getDistance(
					geoNameNode.getGeoName(), targetGeoName);

				if (currentDistance < closestDistance) {
					closestGeoNameNode = geoNameNode;
				}
			}
		}

		return closestGeoNameNode;
	}

	private static final int _NUMBER_OF_DIMENSIONS = 3;

	private static String _geoNamesDataURL;
	private static Comparator<GeoName> _geoNameXComparator =
		new GeoNameXComparator();
	private static Comparator<GeoName> _geoNameYComparator =
		new GeoNameYComparator();
	private static Comparator<GeoName> _geoNameZComparator =
		new GeoNameZComparator();
	private static GeoNameNode _rootGeoNameNode;

}