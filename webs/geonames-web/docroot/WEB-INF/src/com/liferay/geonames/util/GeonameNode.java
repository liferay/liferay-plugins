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
public class GeonameNode {

	public GeonameNode(
		Geoname geoname, GeonameNode leftGeonameNode,
		GeonameNode rightGeonameNode) {

		_geoname = geoname;
		_leftGeonameNode = leftGeonameNode;
		_rightGeonameNode = rightGeonameNode;
	}

	public Geoname getGeoname() {
		return _geoname;
	}

	public GeonameNode getLeftGeonameNode() {
		return _leftGeonameNode;
	}

	public GeonameNode getRightGeonameNode() {
		return _rightGeonameNode;
	}

	private Geoname _geoname;
	private GeonameNode _leftGeonameNode;
	private GeonameNode _rightGeonameNode;

}