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

package com.liferay.sync.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Shinn Lok
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncVersion {

	public int getBuildNumber() {
		return _buildNumber;
	}

	public String getReleaseNotes() {
		return _releaseNotes;
	}

	public String getUrl() {
		return _url;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	public void setReleaseNotes(String releaseNotes) {
		_releaseNotes = releaseNotes;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private int _buildNumber;
	private String _releaseNotes;
	private String _url;

}