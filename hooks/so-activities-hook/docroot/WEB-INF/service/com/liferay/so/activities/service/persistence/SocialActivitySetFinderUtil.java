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

package com.liferay.so.activities.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivitySetFinderUtil {
	public static int countByRelation(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByRelation(userId);
	}

	public static int countByRelationType(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByRelationType(userId, type);
	}

	public static int countByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByUser(userId);
	}

	public static int countByUserGroups(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByUserGroups(userId);
	}

	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findByRelation(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByRelation(userId, start, end);
	}

	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findByRelationType(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByRelationType(userId, type, start, end);
	}

	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findByUser(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByUser(userId, start, end);
	}

	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findByUserGroups(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByUserGroups(userId, start, end);
	}

	public static SocialActivitySetFinder getFinder() {
		if (_finder == null) {
			_finder = (SocialActivitySetFinder)PortletBeanLocatorUtil.locate(com.liferay.so.activities.service.ClpSerializer.getServletContextName(),
					SocialActivitySetFinder.class.getName());

			ReferenceRegistry.registerReference(SocialActivitySetFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SocialActivitySetFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SocialActivitySetFinderUtil.class,
			"_finder");
	}

	private static SocialActivitySetFinder _finder;
}