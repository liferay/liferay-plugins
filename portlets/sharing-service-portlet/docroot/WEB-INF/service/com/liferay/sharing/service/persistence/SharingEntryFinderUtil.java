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

package com.liferay.sharing.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SharingEntryFinderUtil {
	public static int countEntriesByUserId(long userId, long[] classNameIds,
		java.util.Map<java.lang.Long, long[]> scopes) {
		return getFinder().countEntriesByUserId(userId, classNameIds, scopes);
	}

	public static java.util.List<java.lang.Object[]> findEntriesByUserId(
		long userId, long[] classNameIds,
		java.util.Map<java.lang.Long, long[]> scopes, int start, int end) {
		return getFinder()
				   .findEntriesByUserId(userId, classNameIds, scopes, start, end);
	}

	public static SharingEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (SharingEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.sharing.service.ClpSerializer.getServletContextName(),
					SharingEntryFinder.class.getName());

			ReferenceRegistry.registerReference(SharingEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SharingEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SharingEntryFinderUtil.class,
			"_finder");
	}

	private static SharingEntryFinder _finder;
}