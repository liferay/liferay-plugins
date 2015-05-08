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

package com.liferay.contacts.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class EntryFinderUtil {
	public static int countByKeywords(long companyId, long userId,
		java.lang.String keywords) {
		return getFinder().countByKeywords(companyId, userId, keywords);
	}

	public static int countByKeywords(long userId, java.lang.String keywords) {
		return getFinder().countByKeywords(userId, keywords);
	}

	public static java.util.List<com.liferay.portal.model.BaseModel<?>> findByKeywords(
		long companyId, long userId, java.lang.String keywords, int start,
		int end) {
		return getFinder()
				   .findByKeywords(companyId, userId, keywords, start, end);
	}

	public static java.util.List<com.liferay.contacts.model.Entry> findByKeywords(
		long userId, java.lang.String keywords, int start, int end) {
		return getFinder().findByKeywords(userId, keywords, start, end);
	}

	public static EntryFinder getFinder() {
		if (_finder == null) {
			_finder = (EntryFinder)PortletBeanLocatorUtil.locate(com.liferay.contacts.service.ClpSerializer.getServletContextName(),
					EntryFinder.class.getName());

			ReferenceRegistry.registerReference(EntryFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(EntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(EntryFinderUtil.class, "_finder");
	}

	private static EntryFinder _finder;
}