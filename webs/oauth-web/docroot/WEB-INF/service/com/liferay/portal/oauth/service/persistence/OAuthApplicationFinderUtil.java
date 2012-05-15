/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthApplicationFinderUtil {
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countAll();
	}

	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByName(name);
	}

	public static int countByN_O(java.lang.String name, long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByN_O(name, ownerId);
	}

	public static int countByWebsite(java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByWebsite(website);
	}

	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findAll(start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByName(name, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByN_O(
		java.lang.String name, long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByN_O(name, ownerId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByWebsite(
		java.lang.String website, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByWebsite(website, start, end, orderByComparator);
	}

	public static OAuthApplicationFinder getFinder() {
		if (_finder == null) {
			_finder = (OAuthApplicationFinder)PortletBeanLocatorUtil.locate(com.liferay.portal.oauth.service.ClpSerializer.getServletContextName(),
					OAuthApplicationFinder.class.getName());

			ReferenceRegistry.registerReference(OAuthApplicationFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(OAuthApplicationFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(OAuthApplicationFinderUtil.class,
			"_finder");
	}

	private static OAuthApplicationFinder _finder;
}