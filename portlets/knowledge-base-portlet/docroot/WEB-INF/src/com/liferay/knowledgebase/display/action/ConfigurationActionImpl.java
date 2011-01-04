/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.display.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("selection-method")) {
			updateSelectionMethod(actionRequest);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void updateSelectionMethod(ActionRequest actionRequest) {
		String[] resourcePrimKeys = StringUtil.split(
			ParamUtil.getString(actionRequest, "resourcePrimKeys"));
		String[] assetCategoryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "assetCategoryIds"));
		String[] assetTagNames = StringUtil.split(
			ParamUtil.getString(actionRequest, "assetTagNames"));

		setPreference(actionRequest, "resourcePrimKeys", resourcePrimKeys);
		setPreference(actionRequest, "assetCategoryIds", assetCategoryIds);
		setPreference(actionRequest, "assetTagNames", assetTagNames);
	}

}