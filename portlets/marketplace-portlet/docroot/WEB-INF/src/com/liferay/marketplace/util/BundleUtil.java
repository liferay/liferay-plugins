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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.lang.management.ManagementFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

/**
 * @author Joan Kim
 */
public class BundleUtil {

	public static Map<String, Map<String, Object>> getInstalledBundles() {
		Map<String, Map<String, Object>> bundles =
			new HashMap<String, Map<String, Object>>();

		try {
			MBeanServer mBeanServer =
				ManagementFactory.getPlatformMBeanServer();

			ObjectName objectName = new ObjectName(_BUNDLE_STATE_OBJECT_NAME);

			TabularData tabularData = (TabularData)mBeanServer.invoke(
				objectName, "listBundles", null, null);

			Collection<CompositeData> values =
				(Collection<CompositeData>)tabularData.values();

			for (CompositeData compositeData : values) {
				String state = (String)compositeData.get("State");

				if (!state.equals("ACTIVE")) {
					continue;
				}

				String symbolicName = (String)compositeData.get("SymbolicName");
				String version = (String)compositeData.get("Version");

				if (Validator.isNull(symbolicName) &&
					Validator.isNull(version)) {

					continue;
				}

				Map<String, Object> bundleData = new HashMap<String, Object>();

				bundleData.put("bundleId", compositeData.get("Identifier"));
				bundleData.put("location", compositeData.get("Location"));
				bundleData.put("state", state);
				bundleData.put("version", version);

				bundles.put(symbolicName, bundleData);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return bundles;
	}

	public static Properties getManifestProperties(File file) {
		InputStream inputStream = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry zipEntry = zipFile.getEntry("META-INF/MANIFEST.MF");

			if (zipEntry == null) {
				return null;
			}

			inputStream = zipFile.getInputStream(zipEntry);

			return PropertiesUtil.load(inputStream, StringPool.UTF8);
		}
		catch (Exception e) {
		}
		finally {
			StreamUtil.cleanUp(inputStream);

			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}
		}

		return null;
	}

	public static boolean isActive(
		String bundleSymbolicName, String bundleVersion) {

		Map<String, Map<String, Object>> bundles = getInstalledBundles();

		for (Map.Entry<String, Map<String, Object>> entry :
				bundles.entrySet()) {

			if (!bundleSymbolicName.equals(entry.getKey())) {
				continue;
			}

			Map<String, Object> bundleData = entry.getValue();

			if (bundleVersion.equals(
					GetterUtil.getString(bundleData.get("version")))) {

				return true;
			}
		}

		return false;
	}

	public static void uninstallBundle(
		String bundleSymbolicName, String bundleVersion) {

		try {
			Map<String, Map<String, Object>> bundles = getInstalledBundles();

			List<Long> bundleIds = new ArrayList<Long>();

			for (Map.Entry<String, Map<String, Object>> entry :
					bundles.entrySet()) {

				Map<String, Object> bundleData = null;

				if (bundleSymbolicName.equals(entry.getKey())) {
					bundleData = entry.getValue();
				}

				if ((bundleData == null) || bundleData.isEmpty()) {
					continue;
				}

				long bundleId = 0;

				if (bundleVersion.equals(
						GetterUtil.getString(bundleData.get("version")))) {

					bundleId = GetterUtil.getLong(bundleData.get("bundleId"));
				}

				if (bundleId > 0) {
					bundleIds.add(bundleId);
				}
			}

			if (bundleIds.size() > 0) {
				MBeanServer mBeanServer =
					ManagementFactory.getPlatformMBeanServer();

				ObjectName objectName = new ObjectName(_FRAMEWORK_OBJECT_NAME);

				mBeanServer.invoke(
					objectName, "uninstallBundle",
					bundleIds.toArray(new Object[bundleIds.size()]),
					new String[] {long.class.getName()});
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static String _BUNDLE_STATE_OBJECT_NAME =
		"osgi.core:type=bundleState,version=1.5";

	private static String _FRAMEWORK_OBJECT_NAME =
		"osgi.core:type=framework,version=1.5";

}