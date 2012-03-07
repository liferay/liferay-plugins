/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.File;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 */
public class LARImporter {

	public void importResources(
			long companyId, Map<Locale, String> layoutSetPrototypeNameMap,
			File larFile)
		throws Exception {

		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		LayoutSetPrototype layoutSetPrototype =
			LayoutSetPrototypeLocalServiceUtil.addLayoutSetPrototype(
				user.getUserId(), companyId, layoutSetPrototypeNameMap,
				StringPool.BLANK, true, true, new ServiceContext());

		Group group = layoutSetPrototype.getGroup();

		LayoutLocalServiceUtil.importLayouts(
			user.getUserId(), group.getGroupId(), false, getParameterMap(),
			larFile);
	}

	protected Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameters = new HashMap<String, String[]>();

		parameters.put(
			PortletDataHandlerKeys.CATEGORIES,
			new String[] {Boolean.TRUE.toString()});
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
			new String[] {Boolean.TRUE.toString()});
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
			PortletDataHandlerKeys.PORTLET_DATA,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_SETUP,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLET_USER_PREFERENCES,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PORTLETS_MERGE_MODE,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PUBLIC_LAYOUT_PERMISSIONS,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.PUBLISH_TO_REMOTE,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.THEME,
			new String[] {Boolean.FALSE.toString()});
		parameters.put(
			PortletDataHandlerKeys.THEME_REFERENCE,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.USER_ID_STRATEGY,
			new String[] {Boolean.TRUE.toString()});
		parameters.put(
			PortletDataHandlerKeys.USER_PERMISSIONS,
			new String[] {Boolean.TRUE.toString()});

		return parameters;
	}

}