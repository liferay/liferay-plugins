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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Properties;

import javax.portlet.PortletPreferences;

/**
 * @author Mika Koivisto
 */
public class SilentPrefsPropsUtil {

	public static boolean getBoolean(long companyId, String name) {
		try {
			return PrefsPropsUtil.getBoolean(companyId, name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getBoolean(PropsUtil.get(name));
	}

	public static boolean getBoolean(
		long companyId, String name, boolean defaultValue) {

		try {
			return PrefsPropsUtil.getBoolean(companyId, name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getBoolean(PropsUtil.get(name), defaultValue);
	}

	public static boolean getBoolean(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getBoolean(preferences, companyId, name);
	}

	public static boolean getBoolean(
		PortletPreferences preferences, long companyId, String name,
		boolean defaultValue) {

		return PrefsPropsUtil.getBoolean(
			preferences, companyId, name, defaultValue);
	}

	public static boolean getBoolean(String name) {
		try {
			return PrefsPropsUtil.getBoolean(name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getBoolean(PropsUtil.get(name));
	}

	public static boolean getBoolean(String name, boolean defaultValue) {

		try {
			return PrefsPropsUtil.getBoolean(name, defaultValue);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getBoolean(PropsUtil.get(name), defaultValue);
	}

	public static String getContent(long companyId, String name) {

		try {
			return PrefsPropsUtil.getContent(companyId, name);
		}
		catch (SystemException se) {
		}

		return PropsUtil.get(name);
	}

	public static String getContent(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getContent(preferences, companyId, name);
	}

	public static String getContent(String name) {
		try {
			return PrefsPropsUtil.getContent(name);
		}
		catch (SystemException e) {
		}

		return PropsUtil.get(name);
	}

	public static double getDouble(long companyId, String name) {

		try {
			return PrefsPropsUtil.getDouble(companyId, name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getDouble(PropsUtil.get(name));
	}

	public static double getDouble(
		long companyId, String name, double defaultValue) {

		try {
			return PrefsPropsUtil.getDouble(companyId, name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getDouble(PropsUtil.get(name), defaultValue);
	}

	public static double getDouble(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getDouble(preferences, companyId, name);
	}

	public static double getDouble(
		PortletPreferences preferences, long companyId, String name,
		double defaultValue) {

		return PrefsPropsUtil.getDouble(
			preferences, companyId, name, defaultValue);
	}

	public static double getDouble(String name) {
		try {
			return PrefsPropsUtil.getDouble(name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getDouble(PropsUtil.get(name));
	}

	public static double getDouble(String name, double defaultValue) {

		try {
			return PrefsPropsUtil.getDouble(name, defaultValue);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getDouble(PropsUtil.get(name), defaultValue);
	}

	public static int getInteger(long companyId, String name) {

		try {
			return PrefsPropsUtil.getInteger(companyId, name);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getInteger(PropsUtil.get(name));
	}

	public static int getInteger(
		long companyId, String name, int defaultValue) {

		try {
			return PrefsPropsUtil.getInteger(companyId, name, defaultValue);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getInteger(PropsUtil.get(name));
	}

	public static int getInteger(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getInteger(preferences, companyId, name);
	}

	public static int getInteger(
		PortletPreferences preferences, long companyId, String name,
		int defaultValue) {

		return PrefsPropsUtil.getInteger(
			preferences, companyId, name, defaultValue);
	}

	public static int getInteger(String name) {
		try {
			return PrefsPropsUtil.getInteger(name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getInteger(PropsUtil.get(name));
	}

	public static int getInteger(String name, int defaultValue) {
		try {
			return PrefsPropsUtil.getInteger(name, defaultValue);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getInteger(PropsUtil.get(name), defaultValue);
	}

	public static long getLong(long companyId, String name) {

		try {
			return PrefsPropsUtil.getLong(companyId, name);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getLong(PropsUtil.get(name));
	}

	public static long getLong(long companyId, String name, long defaultValue) {
		try {
			return PrefsPropsUtil.getLong(companyId, name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getLong(PropsUtil.get(name), defaultValue);
	}

	public static long getLong(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getLong(preferences, companyId, name);
	}

	public static long getLong(
		PortletPreferences preferences, long companyId, String name,
		long defaultValue) {

		return PrefsPropsUtil.getLong(
			preferences, companyId, name, defaultValue);
	}

	public static long getLong(String name) {
		try {
			return PrefsPropsUtil.getLong(name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getLong(PropsUtil.get(name));
	}

	public static long getLong(String name, long defaultValue) {
		try {
			return PrefsPropsUtil.getLong(name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getLong(PropsUtil.get(name), defaultValue);
	}

	public static Properties getProperties(
		PortletPreferences preferences, long companyId, String prefix,
		boolean removePrefix) {

		return PrefsPropsUtil.getProperties(
			preferences, companyId, prefix, removePrefix);
	}

	public static Properties getProperties(
		String prefix, boolean removePrefix) {

		try {
			return PrefsPropsUtil.getProperties(prefix, removePrefix);
		}
		catch (SystemException se) {
		}

		return PropsUtil.getProperties(prefix, removePrefix);
	}

	public static short getShort(long companyId, String name) {
		try {
			return PrefsPropsUtil.getShort(companyId, name);
		}
		catch (SystemException se) {
		}

		return GetterUtil.getShort(PropsUtil.get(name));
	}

	public static short getShort(
		long companyId, String name, short defaultValue) {

		try {
			return PrefsPropsUtil.getShort(companyId, name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getShort(PropsUtil.get(name), defaultValue);
	}

	public static short getShort(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getShort(preferences, companyId, name);
	}

	public static short getShort(
		PortletPreferences preferences, long companyId, String name,
		short defaultValue) {

		return PrefsPropsUtil.getShort(
			preferences, companyId, name, defaultValue);
	}

	public static short getShort(String name) {
		try {
			return PrefsPropsUtil.getShort(name);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getShort(PropsUtil.get(name));
	}

	public static short getShort(String name, short defaultValue) {

		try {
			return PrefsPropsUtil.getShort(name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.getShort(PropsUtil.get(name), defaultValue);
	}

	public static String getString(long companyId, String name) {
		try {
			return PrefsPropsUtil.getString(companyId, name);
		}
		catch (SystemException se) {
		}

		return PropsUtil.get(name);
	}

	public static String getString(
		long companyId, String name, String defaultValue) {

		try {
			return PrefsPropsUtil.getString(companyId, name, defaultValue);
		}
		catch (SystemException e) {
		}

		return GetterUtil.get(PropsUtil.get(name), defaultValue);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name) {

		return PrefsPropsUtil.getString(preferences, companyId, name);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name,
		boolean defaultValue) {

		return PrefsPropsUtil.getString(
			preferences, companyId, name, defaultValue);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name,
		double defaultValue) {

		return PrefsPropsUtil.getString(
			preferences, companyId, name, defaultValue);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name,
		int defaultValue) {

		return PrefsPropsUtil.getString(
			preferences, companyId, name, defaultValue);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name,
		long defaultValue) {

		return PrefsPropsUtil.getString(
			preferences, companyId, name, defaultValue);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name,
		short defaultValue) {

		return PrefsPropsUtil.getString(
			preferences, companyId, name, defaultValue);
	}

	public static String getString(
		PortletPreferences preferences, long companyId, String name,
		String defaultValue) {

		return PrefsPropsUtil.getString(
			preferences, companyId, name, defaultValue);
	}

	public static String getString(String name) {
		try {
			return PrefsPropsUtil.getString(name);
		}
		catch (SystemException se) {
		}

		return PropsUtil.get(name);
	}

	public static String getString(String name, String defaultValue) {
		try {
			return PrefsPropsUtil.getString(name, defaultValue);
		}
		catch (SystemException se) {
		}

		return GetterUtil.get(PropsUtil.get(name), defaultValue);
	}

	public static String[] getStringArray(
		long companyId, String name, String delimiter) {

		try {
			return PrefsPropsUtil.getStringArray(companyId, name, delimiter);
		}
		catch (SystemException se) {
		}

		return StringUtil.split(PropsUtil.get(name), delimiter);
	}

	public static String[] getStringArray(
		long companyId, String name, String delimiter, String[] defaultValue) {

		try {
			return PrefsPropsUtil.getStringArray(
				companyId, name, delimiter, defaultValue);
		}
		catch (SystemException se) {
		}

		String value = PropsUtil.get(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return StringUtil.split(value, delimiter);
	}

	public static String[] getStringArray(
		PortletPreferences preferences, long companyId, String name,
		String delimiter) {

		return PrefsPropsUtil.getStringArray(
			preferences, companyId, name, delimiter);
	}

	public static String[] getStringArray(
		PortletPreferences preferences, long companyId, String name,
		String delimiter, String[] defaultValue) {

		return PrefsPropsUtil.getStringArray(
			preferences, companyId, name, delimiter, defaultValue);
	}

	public static String[] getStringArray(String name, String delimiter) {
		try {
			return PrefsPropsUtil.getStringArray(name, delimiter);
		}
		catch (SystemException se) {
		}

		return StringUtil.split(PropsUtil.get(name), delimiter);
	}

	public static String[] getStringArray(
		String name, String delimiter, String[] defaultValue) {

		try {
			return PrefsPropsUtil.getStringArray(name, delimiter, defaultValue);
		}
		catch (SystemException se) {
		}

		String value = PropsUtil.get(name);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return StringUtil.split(value, delimiter);
	}

	public static String getStringFromNames(long companyId, String... names) {
		try {
			PrefsPropsUtil.getStringFromNames(companyId, names);
		}
		catch (SystemException se) {
		}

		return null;
	}

}