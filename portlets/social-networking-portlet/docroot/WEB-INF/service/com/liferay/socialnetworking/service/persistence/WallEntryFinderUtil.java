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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class WallEntryFinderUtil {
	public static int countByG1_G2_U1_U2(long groupId1, long groupId2,
		long userId1, long userId2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByG1_G2_U1_U2(groupId1, groupId2, userId1, userId2);
	}

	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG1_G2_U1_U2(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG1_G2_U1_U2(groupId1, groupId2, userId1, userId2,
			start, end);
	}

	public static WallEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (WallEntryFinder)PortletBeanLocatorUtil.locate(com.liferay.socialnetworking.service.ClpSerializer.getServletContextName(),
					WallEntryFinder.class.getName());

			ReferenceRegistry.registerReference(WallEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(WallEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(WallEntryFinderUtil.class, "_finder");
	}

	private static WallEntryFinder _finder;
}