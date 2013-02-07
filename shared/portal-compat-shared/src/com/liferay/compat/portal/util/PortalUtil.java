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

package com.liferay.compat.portal.util;

import com.liferay.compat.portal.kernel.portlet.DynamicActionRequest;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.security.auth.FullNameGenerator;

import java.lang.reflect.Method;

import java.util.Enumeration;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalUtil extends com.liferay.portal.util.PortalUtil {

	public static void copyRequestParameters(
		UploadPortletRequest uploadPortletRequest,
		DynamicActionRequest dynamicActionRequest) {

		Enumeration<String> enu = uploadPortletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String param = enu.nextElement();

			String[] values = uploadPortletRequest.getParameterValues(param);

			if ((param == null) || (values == null)) {
				continue;
			}

			dynamicActionRequest.setParameterValues(param, values);
		}
	}

	public static String getFullName(
		String firstName, String middleName, String lastName) {

		try {
			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			Class<?> clazz = classLoader.loadClass(
				"com.liferay.portal.security.auth.FullNameGeneratorFactory");

			Method method = clazz.getMethod("getInstance");

			method.setAccessible(true);

			FullNameGenerator fullNameGenerator =
				(FullNameGenerator)method.invoke(false);

			return fullNameGenerator.getFullName(
				firstName, middleName, lastName);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static UploadPortletRequest getUploadPortletRequest(
		PortletRequest portletRequest) {

		if (portletRequest instanceof DynamicActionRequest) {
			DynamicActionRequest dynamicActionRequest =
				(DynamicActionRequest)portletRequest;

			return dynamicActionRequest.getUploadPortletRequest();
		}
		else {
			return getPortal().getUploadPortletRequest(portletRequest);
		}
	}

	public static String getUserName(BaseModel<?> baseModel) {
		long userId = 0;
		String userName = StringPool.BLANK;

		if (baseModel instanceof AuditedModel) {
			AuditedModel auditedModel = (AuditedModel)baseModel;

			userId = auditedModel.getUserId();
			userName = auditedModel.getUserName();
		}
		else {
			userId = BeanPropertiesUtil.getLongSilent(baseModel, "userId");
			userName = BeanPropertiesUtil.getStringSilent(
				baseModel, "userName");
		}

		if (userId == 0) {
			return StringPool.BLANK;
		}

		if (baseModel.isEscapedModel()) {
			userName = HtmlUtil.unescape(userName);
		}

		userName = getUserName(userId, userName);

		if (baseModel.isEscapedModel()) {
			userName = HtmlUtil.escape(userName);
		}

		return userName;
	}

}