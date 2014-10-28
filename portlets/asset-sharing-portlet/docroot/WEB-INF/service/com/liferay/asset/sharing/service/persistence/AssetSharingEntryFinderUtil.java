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

package com.liferay.asset.sharing.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetSharingEntryFinderUtil {
	public static int countByUserId(long userId, long[] classNameIds,
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByUserId(userId, classNameIds, sharedToClassPKsMap);
	}

	public static java.util.List<java.lang.Object[]> findByUserId(long userId,
		long[] classNameIds,
		java.util.Map<java.lang.Long, long[]> sharedToClassPKsMap, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByUserId(userId, classNameIds, sharedToClassPKsMap,
			start, end);
	}

	public static AssetSharingEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetSharingEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.asset.sharing.service.ClpSerializer.getServletContextName(),
					AssetSharingEntryFinder.class.getName());

			ReferenceRegistry.registerReference(AssetSharingEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetSharingEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetSharingEntryFinderUtil.class,
			"_finder");
	}

	private static AssetSharingEntryFinder _finder;
}