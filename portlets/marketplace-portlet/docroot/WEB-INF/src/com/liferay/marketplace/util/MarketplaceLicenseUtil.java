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

package com.liferay.marketplace.util;

import com.liferay.compat.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.license.util.LicenseManagerUtil;

import java.io.File;

import java.util.Arrays;

/**
 * @author Joan Kim
 */
public class MarketplaceLicenseUtil {

	public static String getOrder(String productEntryName) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("cmd", "GET_ORDER");
		jsonObject.put("hostName", LicenseManagerUtil.getHostName());
		jsonObject.put(
			"ipAddresses",
			StringUtil.merge(LicenseManagerUtil.getIpAddresses()));
		jsonObject.put(
			"macAddresses",
			StringUtil.merge(LicenseManagerUtil.getMacAddresses()));
		jsonObject.put("productEntryName", productEntryName);
		jsonObject.put("serverId", Arrays.toString(getServerIdBytes()));

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		String response = null;

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			MethodKey methodKey = new MethodKey(
				"com.liferay.portal.license.util.LicenseUtil", "sendRequest",
				String.class);

			response = (String)PortalClassInvoker.invoke(
				methodKey, jsonObject.toString());
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
			response);

		return responseJSONObject.getString("orderUuid");
	}

	public static byte[] getServerIdBytes() throws Exception {
		File serverIdFile = new File(_LICENSE_SERVER_ID_FILE_NAME);

		if (!serverIdFile.exists()) {
			return new byte[0];
		}

		return FileUtil.getBytes(serverIdFile);
	}

	public static void registerOrder(String orderUuid, String productEntryName)
		throws Exception {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			MethodKey methodKey = new MethodKey(
				"com.liferay.portal.license.util.LicenseUtil", "registerOrder",
				String.class, String.class, int.class);

			PortalClassInvoker.invoke(
				methodKey, orderUuid, productEntryName, 0);
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}
	}

	private static final String _LICENSE_SERVER_ID_FILE_NAME =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/license/server/serverId";

}