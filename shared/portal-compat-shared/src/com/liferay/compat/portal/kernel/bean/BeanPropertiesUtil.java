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

package com.liferay.compat.portal.kernel.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.lang.reflect.Method;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Danny Situ
 * @author Wesley Gong
 */
public class BeanPropertiesUtil
	extends com.liferay.portal.kernel.bean.BeanPropertiesUtil {

	public static void setProperties(Object bean, HttpServletRequest request) {
		Enumeration<String> enu = request.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = request.getParameter(name);

			if (Validator.isNull(value) &&
				(getObjectSilent(bean, name) instanceof Number)) {

				value = String.valueOf(0);
			}

			setPropertySilent(bean, name, value);

			if (name.endsWith("Month")) {
				String dateParam = name.substring(0, name.lastIndexOf("Month"));

				if (request.getParameter(dateParam) != null) {
					continue;
				}

				Class<?> propertyTypeClass = getObjectType(bean, dateParam);

				if ((propertyTypeClass == null) ||
					!propertyTypeClass.equals(Date.class)) {

					continue;
				}

				Date date = getDate(dateParam, request);

				if (date != null) {
					setPropertySilent(bean, dateParam, date);
				}
			}
		}
	}

	public static void setPropertySilent(
		Object bean, String param, Object value) {

		try {
			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			Class<?> clazz = classLoader.loadClass("jodd.bean.BeanUtil");

			Method method = clazz.getMethod(
				"setPropertyForcedSilent",
				new Class[] {Object.class, String.class, Object.class});

			method.invoke(null, bean, param, value);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected static Date getDate(String param, HttpServletRequest request) {
		int month = ParamUtil.getInteger(request, param + "Month");
		int day = ParamUtil.getInteger(request, param + "Day");
		int year = ParamUtil.getInteger(request, param + "Year");
		int hour = ParamUtil.getInteger(request, param + "Hour", -1);
		int minute = ParamUtil.getInteger(request, param + "Minute");

		int amPm = ParamUtil.getInteger(request, param + "AmPm");

		if (amPm == Calendar.PM) {
			hour += 12;
		}

		if (hour == -1) {
			return PortalUtil.getDate(month, day, year);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		try {
			return PortalUtil.getDate(
				month, day, year, hour, minute, user.getTimeZone(), null);
		}
		catch (PortalException pe) {
			return null;
		}
	}

}