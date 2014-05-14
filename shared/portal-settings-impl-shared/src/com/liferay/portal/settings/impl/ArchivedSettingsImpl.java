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

package com.liferay.portal.settings.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletItem;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesServiceUtil;
import com.liferay.portal.settings.ArchivedSettings;
import com.liferay.portal.settings.PortletPreferencesSettings;
import com.liferay.portal.settings.Settings;
import com.liferay.portal.util.PortletKeys;

import java.io.IOException;

import java.util.Collection;
import java.util.Date;

import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

/**
 * @author Iván Zaera
 */
public class ArchivedSettingsImpl implements ArchivedSettings {

	public ArchivedSettingsImpl(PortletItem portletItem) {
		_portletItem = portletItem;
	}

	@Override
	public void delete() throws IOException {
		try {
			PortletPreferencesServiceUtil.deleteArchivedPreferences(
				_portletItem.getPortletItemId());
		}
		catch (PortalException pe) {
			throw new IOException("Unable to delete archived settings", pe);
		}
		catch (SystemException se) {
			throw new IOException("Unable to delete archived settings", se);
		}
	}

	@Override
	public Settings getDefaultSettings() {
		return null;
	}

	@Override
	public Date getModifiedDate() {
		return _portletItem.getModifiedDate();
	}

	@Override
	public String getName() {
		return _portletItem.getName();
	}

	@Override
	public Collection<String> getSetKeys() {
		return _getSettings().getSetKeys();
	}

	@Override
	public String getUserName() {
		return _portletItem.getUserName();
	}

	@Override
	public String getValue(String key, String defaultValue) {
		return _getSettings().getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		return _getSettings().getValues(key, defaultValue);
	}

	@Override
	public void reset(String key) {
		_getSettings().reset(key);
	}

	@Override
	public Settings setValue(String key, String value) {
		_getSettings().setValue(key, value);

		return this;
	}

	@Override
	public Settings setValues(String key, String[] values) {
		_getSettings().setValues(key, values);

		return this;
	}

	@Override
	public void store() throws IOException, ValidatorException {
		_getSettings().store();
	}

	private Settings _getSettings() {
		if (_portletPreferencesSettings == null) {
			PortletPreferences portletPreferences = null;

			try {
				long ownerId = _portletItem.getPortletItemId();
				int ownerType = PortletKeys.PREFS_OWNER_TYPE_ARCHIVED;
				long plid = 0;
				String portletId = _portletItem.getPortletId();

				portletPreferences =
					PortletPreferencesLocalServiceUtil.getPreferences(
						_portletItem.getCompanyId(), ownerId, ownerType, plid,
						PortletConstants.getRootPortletId(portletId));
			}
			catch (SystemException se) {
				throw new RuntimeException("Unable to load named settings", se);
			}

			_portletPreferencesSettings = new PortletPreferencesSettings(
				portletPreferences);
		}

		return _portletPreferencesSettings;
	}

	private PortletItem _portletItem;
	private PortletPreferencesSettings _portletPreferencesSettings;

}