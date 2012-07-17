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
public class OAuthApplications_UsersFinderUtil {
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countAll();
	}

	public static int countByO_A(long ownerId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByO_A(ownerId, authorized);
	}

	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findAll(start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.oauth.model.OAuthApplications_Users> findByO_A(
		long ownerId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByO_A(ownerId, authorized, start, end, orderByComparator);
	}

	public static OAuthApplications_UsersFinder getFinder() {
		if (_finder == null) {
			_finder = (OAuthApplications_UsersFinder)PortletBeanLocatorUtil.locate(com.liferay.portal.oauth.service.ClpSerializer.getServletContextName(),
					OAuthApplications_UsersFinder.class.getName());

			ReferenceRegistry.registerReference(OAuthApplications_UsersFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(OAuthApplications_UsersFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(OAuthApplications_UsersFinderUtil.class,
			"_finder");
	}

	private static OAuthApplications_UsersFinder _finder;
}