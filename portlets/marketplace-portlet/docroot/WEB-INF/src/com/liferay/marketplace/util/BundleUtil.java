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
import com.liferay.portal.kernel.util.Validator;

import java.lang.management.ManagementFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

/**
 * @author Joan Kim
 */
public class BundleUtil {

	public static Map<String, Map> getInstalledBundles()
		throws SystemException {

		Map<String, Map> bundles = new HashMap<String, Map>();

		try {
			MBeanServer mBeanServer =
				ManagementFactory.getPlatformMBeanServer();

			ObjectName objectName = new ObjectName(_BUNDLE_STATE_OBJECT_NAME);

			TabularData data = (TabularData)mBeanServer.invoke(
				objectName, "listBundles", null, null);

			Collection<CompositeData> values =
				(Collection<CompositeData>)data.values();

			for (CompositeData compositeData : values) {
				Map<String, Object> bundleDataMap =
					new HashMap<String, Object>();

				String state = (String)compositeData.get("State");

				if (!state.equals("ACTIVE")) {
					continue;
				}

				String bundleSymbolicName = (String)compositeData.get(
					"SymbolicName");

				if (Validator.isNull(bundleSymbolicName)) {
					continue;
				}

				bundleDataMap.put("bundleId", compositeData.get("Identifier"));
				bundleDataMap.put("location", compositeData.get("Location"));
				bundleDataMap.put("version", compositeData.get("Version"));
				bundleDataMap.put("state", state);

				bundles.put(bundleSymbolicName, bundleDataMap);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return bundles;
	}

	public static void uninstallBundle(String bundleSymbolicName)
		throws SystemException {

		try {
			MBeanServer mBeanServer =
				ManagementFactory.getPlatformMBeanServer();

			ObjectName objectName = new ObjectName(_FRAMEWORK_OBJECT_NAME);

			Map<String, Map> bundles = getInstalledBundles();

			Map<String, Object> bundle = bundles.get(bundleSymbolicName);

			if (bundle == null || bundle.isEmpty()) {
				return;
			}

			long bundleId = (Long)bundle.get("bundleId");

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