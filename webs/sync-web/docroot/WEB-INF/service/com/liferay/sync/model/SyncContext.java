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

package com.liferay.sync.model;

import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.model.Group;

import java.util.List;
import java.util.Map;

/**
 * @author Dennis Ju
 */
@JSON
public class SyncContext {

	public String getPluginVersion() {
		return _pluginVersion;
	}

	public int getPortalBuildNumber() {
		return _portalBuildNumber;
	}

	@JSON
	public Map<String, String> getPortletPreferencesMap() {
		return _portletPreferencesMap;
	}

	public long getUserId() {
		return _userId;
	}

	@JSON
	public List<Group> getUserSitesGroups() {
		return _userSitesGroups;
	}

	public boolean isSocialOfficeInstalled() {
		return _socialOfficeInstalled;
	}

	public void setPluginVersion(String pluginVersion) {
		_pluginVersion = pluginVersion;
	}

	public void setPortalBuildNumber(int portalBuildNumber) {
		_portalBuildNumber = portalBuildNumber;
	}

	public void setPortletPreferencesMap(
		Map<String, String> portletPreferencesMap) {

		_portletPreferencesMap = portletPreferencesMap;
	}

	public void setSocialOfficeInstalled(boolean socialOfficeInstalled) {
		_socialOfficeInstalled = socialOfficeInstalled;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserSitesGroups(List<Group> userSitesGroups) {
		_userSitesGroups = userSitesGroups;
	}

	private String _pluginVersion;
	private int _portalBuildNumber;
	private Map<String, String> _portletPreferencesMap;
	private boolean _socialOfficeInstalled;
	private long _userId;
	private List<Group> _userSitesGroups;

}