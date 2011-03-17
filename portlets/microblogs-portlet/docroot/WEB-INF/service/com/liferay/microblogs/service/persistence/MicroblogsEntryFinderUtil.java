/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class MicroblogsEntryFinderUtil {
	public static int countByU_VU(long userId, long viewUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByU_VU(userId, viewUserId);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_VU(
		long userId, long viewUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByU_VU(userId, viewUserId, start, end);
	}

	public static MicroblogsEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (MicroblogsEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.microblogs.service.ClpSerializer.getServletContextName(),
					MicroblogsEntryFinder.class.getName());

			ReferenceRegistry.registerReference(MicroblogsEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(MicroblogsEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(MicroblogsEntryFinderUtil.class,
			"_finder");
	}

	private static MicroblogsEntryFinder _finder;
}