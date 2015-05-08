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

package com.liferay.chat.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class StatusFinderUtil {
	public static java.util.List<java.lang.Object[]> findByModifiedDate(
		long companyId, long userId, long modifiedDate, int start, int end) {
		return getFinder()
				   .findByModifiedDate(companyId, userId, modifiedDate, start,
			end);
	}

	public static java.util.List<java.lang.Object[]> findBySocialRelationTypes(
		long userId, int[] types, long modifiedDate, int start, int end) {
		return getFinder()
				   .findBySocialRelationTypes(userId, types, modifiedDate,
			start, end);
	}

	public static java.util.List<java.lang.Object[]> findByUsersGroups(
		long userId, long modifiedDate, java.lang.String[] groupNames,
		int start, int end) {
		return getFinder()
				   .findByUsersGroups(userId, modifiedDate, groupNames, start,
			end);
	}

	public static StatusFinder getFinder() {
		if (_finder == null) {
			_finder = (StatusFinder)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.getServletContextName(),
					StatusFinder.class.getName());

			ReferenceRegistry.registerReference(StatusFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(StatusFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(StatusFinderUtil.class, "_finder");
	}

	private static StatusFinder _finder;
}