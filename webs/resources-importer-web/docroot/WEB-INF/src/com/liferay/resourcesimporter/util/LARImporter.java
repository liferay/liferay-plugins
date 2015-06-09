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

package com.liferay.resourcesimporter.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.exportimport.lar.PortletDataHandlerKeys;
import com.liferay.portlet.exportimport.lar.UserIdStrategy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class LARImporter extends BaseImporter {

	@Override
	public void importResources() throws Exception {
		if (_privateLARInputStream != null) {
			LayoutLocalServiceUtil.importLayouts(
				userId, groupId, true, getParameterMap(),
				_privateLARInputStream);
		}

		if (_publicLARInputStream != null) {
			LayoutLocalServiceUtil.importLayouts(
				userId, groupId, false, getParameterMap(),
				_publicLARInputStream);
		}
	}

	public void setLARFile(File file) {
		try {
			setPublicLARInputStream(
				new BufferedInputStream(new FileInputStream(file)));
		}
		catch (FileNotFoundException fnfe) {
			_log.error(fnfe, fnfe);
		}
	}

	public void setLARInputStream(InputStream inputStream) {
		setPublicLARInputStream(inputStream);
	}

	public void setPrivateLARInputStream(InputStream privateLARInputStream) {
		_privateLARInputStream = privateLARInputStream;
	}

	public void setPublicLARInputStream(InputStream publicLARInputStream) {
		_publicLARInputStream = publicLARInputStream;
	}

	protected Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameters = new HashMap<>();

		parameters.put(
			PortletDataHandlerKeys.DELETE_MISSING_LAYOUTS,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.DELETE_PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.LAYOUT_SET_PROTOTYPE_LINK_ENABLED,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.LAYOUT_SET_SETTINGS,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
			new String[] {
				PortletDataHandlerKeys.
					LAYOUTS_IMPORT_MODE_MERGE_BY_LAYOUT_UUID
			});
		parameters.put(
			PortletDataHandlerKeys.LOGO,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PERMISSIONS,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_CONFIGURATION_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_DATA_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_SETUP_ALL,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_USER_PREFERENCES,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLETS_MERGE_MODE,
			new String[] {PortletDataHandlerKeys.PORTLETS_MERGE_MODE_REPLACE});
		parameters.put(
			PortletDataHandlerKeys.THEME_REFERENCE,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.USER_ID_STRATEGY,
			new String[] {UserIdStrategy.CURRENT_USER_ID});

		return parameters;
	}

	private static Log _log = LogFactoryUtil.getLog(LARImporter.class);

	private InputStream _privateLARInputStream;
	private InputStream _publicLARInputStream;

}