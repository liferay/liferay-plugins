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
public class GeonamesUtil {

	public static void buildRootGeonameNode() throws Exception {
		URL url = new URL(_geonamesDataURL);

		ZipInputStream zipInputStream = new ZipInputStream(url.openStream());

		zipInputStream.getNextEntry();

		buildRootGeonameNode(zipInputStream);
	}

	public static void buildRootGeonameNode(InputStream inputStream)
		throws Exception {

		List<Geoname> geonames = new ArrayList<Geoname>();

		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

			String line = bufferedReader.readLine();

			while (line != null) {
				String[] data = line.split("\t");

				geonames.add(new Geoname(data));

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

		_rootGeonameNode = getGeonameNode(geonames, 0);
	}

	public static void buildRootGeonameNode(String filePathName)
		throws Exception {

		File file = new File(filePathName);

		if (!file.exists() || !file.canRead()) {
			return;
		}

		buildRootGeonameNode(new FileInputStream(file));
	}

	public static String getGeonameJSON(double latitude, double longitude)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		if (_rootGeonameNode == null) {
			buildRootGeonameNode();
		}

		GeonameNode geonameNode = searchGeonames(
			_rootGeonameNode, new Geoname(latitude, longitude), 0);

		Geoname geoname = geonameNode.getGeoname();

		jsonObject.put("countryCode", geoname.getCountryCode());
		jsonObject.put("name", geoname.getName());

		return jsonObject.toString();
	}

	public static void setGeonamesDataURL(String geonamesDataURL)
		throws Exception {

		_geonamesDataURL = geonamesDataURL;
	}

	protected static Comparator<Geoname> getComparator(int cuttingDimension) {
		int axis = cuttingDimension % _NUMBER_OF_DIMENSIONS;

		if (axis == 0) {
			return _geonameXComparator;
		}

		if (axis == 1) {
			return _geonameYComparator;
		}

		return _geonameZComparator;
	}

	protected static double getDistance(Geoname geoname1, Geoname geoname2) {
		double dx = geoname1.getX() - geoname2.getX();
		double dy = geoname1.getY() - geoname2.getY();
		double dz = geoname1.getZ() - geoname2.getZ();

		return (dx * dx) + (dy * dy) + (dz * dz);
	}

	protected static double getDistance(
		Geoname geoname1, Geoname geoname2, int cuttingDimension) {

		int axis = cuttingDimension % _NUMBER_OF_DIMENSIONS;

		double delta = 0;

		if (axis == 0) {
			delta = geoname1.getX() - geoname2.getX();
		}
		else if (axis == 1) {
			delta = geoname1.getY() - geoname2.getY();
		}
		else {
			delta = geoname1.getZ() - geoname2.getZ();
		}

		return delta * delta;
	}

	protected static GeonameNode getGeonameNode(
		List<Geoname> geonames, int cuttingDimension) {

		if (geonames.isEmpty()) {
			return null;
		}

		Collections.sort(geonames, getComparator(cuttingDimension));

		int index = geonames.size() / 2;

		Geoname geoname = geonames.get(index);
		GeonameNode leftGeonameNode = getGeonameNode(
			new ArrayList<Geoname>(geonames.subList(0, index)),
			cuttingDimension + 1);
		GeonameNode rightGeonameNode = getGeonameNode(
			new ArrayList<Geoname>(
				geonames.subList(index + 1, geonames.size())),
			cuttingDimension + 1);

		return new GeonameNode(geoname, leftGeonameNode, rightGeonameNode);
	}

	protected static GeonameNode searchGeonames(
		GeonameNode geonameNode, Geoname targetGeoname, int cuttingDimension) {

		GeonameNode closestGeonameNode = null;
		GeonameNode nextGeonameNode = null;
		GeonameNode previousGeonameNode = null;

		Comparator<Geoname> comparator = getComparator(cuttingDimension);

		if (comparator.compare(targetGeoname, geonameNode.getGeoname()) < 0) {
			nextGeonameNode = geonameNode.getLeftGeonameNode();
			previousGeonameNode = geonameNode.getRightGeonameNode();
		}
		else {
			nextGeonameNode = geonameNode.getRightGeonameNode();
			previousGeonameNode = geonameNode.getLeftGeonameNode();
		}

		if (nextGeonameNode == null) {
			closestGeonameNode = geonameNode;
		}
		else {
			closestGeonameNode = searchGeonames(
				nextGeonameNode, targetGeoname, cuttingDimension + 1);
		}

		double currentDistance = getDistance(
			geonameNode.getGeoname(), targetGeoname);
		double closestDistance = getDistance(
			closestGeonameNode.getGeoname(), targetGeoname);

		if (currentDistance < closestDistance) {
			closestGeonameNode = geonameNode;
			closestDistance = currentDistance;
		}

		if (previousGeonameNode != null) {
			double oneDimensionalDistance = getDistance(
				geonameNode.getGeoname(), targetGeoname, cuttingDimension);

			if (oneDimensionalDistance < closestDistance) {
				geonameNode = searchGeonames(
					previousGeonameNode, targetGeoname, cuttingDimension + 1);

				currentDistance = getDistance(
					geonameNode.getGeoname(), targetGeoname);

				if (currentDistance < closestDistance) {
					closestGeonameNode = geonameNode;
				}
			}
		}

		return closestGeonameNode;
	}

	private static final int _NUMBER_OF_DIMENSIONS = 3;

	private static String _geonamesDataURL;
	private static Comparator<Geoname> _geonameXComparator =
		new GeonameXComparator();
	private static Comparator<Geoname> _geonameYComparator =
		new GeonameYComparator();
	private static Comparator<Geoname> _geonameZComparator =
		new GeonameZComparator();
	private static GeonameNode _rootGeonameNode;

}