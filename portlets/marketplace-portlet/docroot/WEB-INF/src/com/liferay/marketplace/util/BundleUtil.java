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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
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
import java.util.jar.Manifest;
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

	public static List<Map<String, Object>> getInstalledBundles()
		throws SystemException {

		try {
			MBeanServer mBeanServer =
				ManagementFactory.getPlatformMBeanServer();

			ObjectName objectName = new ObjectName(_BUNDLE_STATE_OBJECT_NAME);

			TabularData tabularData = (TabularData)mBeanServer.invoke(
				objectName, "listBundles", null, null);

			Collection<CompositeData> values =
				(Collection<CompositeData>)tabularData.values();

			List<Map<String, Object>> bundles =
				new ArrayList<Map<String, Object>>(values.size());

			for (CompositeData compositeData : values) {
				String state = (String)compositeData.get("State");

				if (!ArrayUtil.contains(_INSTALLED_BUNDLE_STATES, state)) {
					continue;
				}

				String symbolicName = (String)compositeData.get("SymbolicName");

				if (Validator.isNull(symbolicName)) {
					continue;
				}

				Map<String, Object> bundleData = new HashMap<String, Object>();

				bundleData.put("bundleId", compositeData.get("Identifier"));
				bundleData.put("location", compositeData.get("Location"));
				bundleData.put("state", state);
				bundleData.put("symbolicName", symbolicName);

				String version = (String)compositeData.get("Version");

				bundleData.put("version", version);

				bundles.add(bundleData);
			}

			return bundles;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static Manifest getManifest(File file) {
		InputStream inputStream = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry zipEntry = zipFile.getEntry("META-INF/MANIFEST.MF");

			if (zipEntry == null) {
				return null;
			}

			inputStream = zipFile.getInputStream(zipEntry);

			return new Manifest(inputStream);
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

	public static boolean isActive(String symbolicName, String version)
		throws SystemException {

		List<Map<String, Object>> bundles = getInstalledBundles();

		for (Map<String, Object> bundle : bundles) {
			String curSymbolicName = GetterUtil.getString(
				bundle.get("symbolicName"));

			if (!symbolicName.equals(curSymbolicName)) {
				continue;
			}

			String curVersion = GetterUtil.getString(bundle.get("version"));

			if (version.equals(curVersion)) {
				return true;
			}
		}

		return false;
	}

	public static void uninstallBundle(String symbolicName, String version)
		throws SystemException {

		try {
			List<Map<String, Object>> bundles = getInstalledBundles();

			List<Long> bundleIds = new ArrayList<Long>();

			for (Map<String, Object> bundle : bundles) {
				String curSymbolicName = GetterUtil.getString(
					bundle.get("symbolicName"));

				if (!symbolicName.equals(curSymbolicName)) {
					continue;
				}

				String curVersion = GetterUtil.getString(bundle.get("version"));

				if (!version.equals(curVersion)) {
					continue;
				}

				long bundleId = GetterUtil.getLong(bundle.get("bundleId"));

				if (bundleId > 0) {
					bundleIds.add(bundleId);
				}
			}

			if (bundleIds.isEmpty()) {
				return;
			}

			MBeanServer mBeanServer =
				ManagementFactory.getPlatformMBeanServer();

			ObjectName objectName = new ObjectName(_FRAMEWORK_OBJECT_NAME);
			long[] bundleIdsArray = ArrayUtil.toArray(
				bundleIds.toArray(new Long[bundleIds.size()]));

			mBeanServer.invoke(
				objectName, "uninstallBundles", new Object[] {bundleIdsArray},
				new String[] {long[].class.getName()});
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static final String _BUNDLE_STATE_OBJECT_NAME =
		"osgi.core:type=bundleState,version=1.5";

	private static final String _FRAMEWORK_OBJECT_NAME =
		"osgi.core:type=framework,version=1.5";

	private static final String[] _INSTALLED_BUNDLE_STATES = {
		"ACTIVE", "INSTALLED", "RESOLVED"
	};

}