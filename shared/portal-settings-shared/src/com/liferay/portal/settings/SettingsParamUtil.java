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

package com.liferay.portal.settings;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class SettingsParamUtil {

	public static boolean getBoolean(
		Settings settings, HttpServletRequest request, String param) {

		return getBoolean(settings, request, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		Settings settings, HttpServletRequest request, String param,
		boolean defaultValue) {

		String settingsValue = settings.getValue(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			settingsValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static boolean getBoolean(
		Settings settings, PortletRequest portletRequest, String param) {

		return getBoolean(
			settings, portletRequest, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		Settings settings, PortletRequest portletRequest, String param,
		boolean defaultValue) {

		String settingsValue = settings.getValue(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			settingsValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static double getDouble(
		Settings settings, HttpServletRequest request, String param) {

		return getDouble(settings, request, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		Settings settings, HttpServletRequest request, String param,
		double defaultValue) {

		String settingsValue = settings.getValue(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			settingsValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static double getDouble(
		Settings settings, PortletRequest portletRequest, String param) {

		return getDouble(
			settings, portletRequest, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		Settings settings, PortletRequest portletRequest, String param,
		double defaultValue) {

		String settingsValue = settings.getValue(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			settingsValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static int getInteger(
		Settings settings, HttpServletRequest request, String param) {

		return getInteger(settings, request, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		Settings settings, HttpServletRequest request, String param,
		int defaultValue) {

		String settingsValue = settings.getValue(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			settingsValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static int getInteger(
		Settings settings, PortletRequest portletRequest, String param) {

		return getInteger(
			settings, portletRequest, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		Settings settings, PortletRequest portletRequest, String param,
		int defaultValue) {

		String settingsValue = settings.getValue(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			settingsValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static long getLong(
		Settings settings, HttpServletRequest request, String param) {

		return getLong(settings, request, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		Settings settings, HttpServletRequest request, String param,
		long defaultValue) {

		String settingsValue = settings.getValue(param, null);

		long getterUtilValue = GetterUtil.getLong(settingsValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static long getLong(
		Settings settings, PortletRequest portletRequest, String param) {

		return getLong(
			settings, portletRequest, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		Settings settings, PortletRequest portletRequest, String param,
		long defaultValue) {

		String settingsValue = settings.getValue(param, null);

		long getterUtilValue = GetterUtil.getLong(settingsValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static String getString(
		Settings settings, HttpServletRequest request, String param) {

		return getString(settings, request, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		Settings settings, HttpServletRequest request, String param,
		String defaultValue) {

		String settingsValue = settings.getValue(param, null);

		String getterUtilValue = GetterUtil.getString(
			settingsValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static String getString(
		Settings settings, PortletRequest portletRequest, String param) {

		return getString(
			settings, portletRequest, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		Settings settings, PortletRequest portletRequest, String param,
		String defaultValue) {

		String settingsValue = settings.getValue(param, null);

		String getterUtilValue = GetterUtil.getString(
			settingsValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

}