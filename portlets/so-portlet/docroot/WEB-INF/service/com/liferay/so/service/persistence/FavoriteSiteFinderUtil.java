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

package com.liferay.so.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class FavoriteSiteFinderUtil {
	public static int countByU_N(long userId, java.lang.String name,
		java.lang.String groupRealName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByU_N(userId, name, groupRealName);
	}

	public static java.util.List<java.lang.Object[]> findByU_N(long userId,
		java.lang.String name, java.lang.String groupRealName, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByU_N(userId, name, groupRealName, start, end);
	}

	public static FavoriteSiteFinder getFinder() {
		if (_finder == null) {
			_finder = (FavoriteSiteFinder)PortletBeanLocatorUtil.locate(com.liferay.so.service.ClpSerializer.getServletContextName(),
					FavoriteSiteFinder.class.getName());

			ReferenceRegistry.registerReference(FavoriteSiteFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(FavoriteSiteFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(FavoriteSiteFinderUtil.class,
			"_finder");
	}

	private static FavoriteSiteFinder _finder;
}