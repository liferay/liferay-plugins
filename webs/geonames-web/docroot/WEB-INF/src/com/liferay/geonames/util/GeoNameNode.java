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

/**
 * @author Matthew Kong
 */
public class GeoNameNode {

	public GeoNameNode(
		GeoName geoName, GeoNameNode leftGeoNameNode,
		GeoNameNode rightGeoNameNode) {

		_geoName = geoName;
		_leftGeoNameNode = leftGeoNameNode;
		_rightGeoNameNode = rightGeoNameNode;
	}

	public GeoName getGeoName() {
		return _geoName;
	}

	public GeoNameNode getLeftGeoNameNode() {
		return _leftGeoNameNode;
	}

	public GeoNameNode getRightGeoNameNode() {
		return _rightGeoNameNode;
	}

	private GeoName _geoName;
	private GeoNameNode _leftGeoNameNode;
	private GeoNameNode _rightGeoNameNode;

}