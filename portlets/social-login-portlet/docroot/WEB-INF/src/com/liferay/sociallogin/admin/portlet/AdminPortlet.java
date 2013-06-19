/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sociallogin.admin.portlet;

import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.sociallogin.util.tencent.qq.QqConnectUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Terry Jia
 */
public class AdminPortlet extends MVCPortlet {

	public void updateQqAuthSetting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();

		UnicodeProperties properties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");

		CompanyLocalServiceUtil.updatePreferences(companyId, properties);

		QqConnectUtil.updateConnectConfigProperties(
			QqConnectUtil.getClientId(companyId),
			QqConnectUtil.getClientSecret(companyId),
			QqConnectUtil.getRedirectURI(companyId),
			QqConnectUtil.getScope(companyId));
	}

}