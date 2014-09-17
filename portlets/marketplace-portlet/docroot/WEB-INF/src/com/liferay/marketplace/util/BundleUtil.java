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
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.lang.management.ManagementFactory;

import java.util.Collection;
import java.util.HashMap;
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

				String bundleSymbolicName = (String)compositeData.get(
					"SymbolicName");

				if (Validator.isNull(bundleSymbolicName)) {
					continue;
				}

				Map<String, Object> bundleData = new HashMap<String, Object>();

				bundleData.put("bundleId", compositeData.get("Identifier"));
				bundleData.put("location", compositeData.get("Location"));
				bundleData.put("state", state);
				bundleData.put("version", compositeData.get("Version"));

				bundles.put(bundleSymbolicName, bundleData);
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

	public static void uninstallBundle(String bundleSymbolicName) {
		try {
			MBeanServer mBeanServer =
				ManagementFactory.getPlatformMBeanServer();

			ObjectName objectName = new ObjectName(_FRAMEWORK_OBJECT_NAME);

			Map<String, Map<String, Object>> bundles = getInstalledBundles();

			Map<String, Object> bundleData = bundles.get(bundleSymbolicName);

			if ((bundleData == null) || bundleData.isEmpty()) {
				return;
			}

			long bundleId = (Long)bundleData.get("bundleId");

			if (bundleId > 0) {
				mBeanServer.invoke(
					objectName, "uninstallBundle", new Object[] {bundleId},
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