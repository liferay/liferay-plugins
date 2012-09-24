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
public class ApplicationUserFinderUtil {
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countAll();
	}

	public static int countByO(long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByO(ownerId);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findAll(start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> findByO(
		long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByO(ownerId, start, end, orderByComparator);
	}

	public static ApplicationUserFinder getFinder() {
		if (_finder == null) {
			_finder = (ApplicationUserFinder)PortletBeanLocatorUtil.locate(com.liferay.portal.oauth.service.ClpSerializer.getServletContextName(),
					ApplicationUserFinder.class.getName());

			ReferenceRegistry.registerReference(ApplicationUserFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ApplicationUserFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ApplicationUserFinderUtil.class,
			"_finder");
	}

	private static ApplicationUserFinder _finder;
}