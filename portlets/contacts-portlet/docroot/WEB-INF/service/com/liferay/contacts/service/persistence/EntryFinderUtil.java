/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryFinderUtil {
	public static int countByKeywords(long companyId, long userId,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(companyId, userId, keywords);
	}

	public static int countByKeywords(long userId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(userId, keywords);
	}

	public static int countByU_FN_EA(long userId, java.lang.String[] fullNames,
		java.lang.String[] emailAddresses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByU_FN_EA(userId, fullNames, emailAddresses,
			andOperator);
	}

	public static int countByC_U_FN_EA(long companyId, long userId,
		java.lang.String[] fullNames, java.lang.String[] emailAddresses,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_U_FN_EA(companyId, userId, fullNames,
			emailAddresses, andOperator);
	}

	public static java.util.List<com.liferay.portal.model.BaseModel<?>> findByKeywords(
		long companyId, long userId, java.lang.String keywords, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, userId, keywords, start, end);
	}

	public static java.util.List<com.liferay.contacts.model.Entry> findByKeywords(
		long userId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByKeywords(userId, keywords, start, end);
	}

	public static java.util.List<com.liferay.contacts.model.Entry> findByU_FN_EA(
		long userId, java.lang.String[] fullNames,
		java.lang.String[] emailAddresses, boolean andOperator, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByU_FN_EA(userId, fullNames, emailAddresses,
			andOperator, start, end);
	}

	public static java.util.List<com.liferay.portal.model.BaseModel<?>> findByC_U_FN_EA(
		long companyId, long userId, java.lang.String[] fullNames,
		java.lang.String[] emailAddresses, boolean andOperator, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_U_FN_EA(companyId, userId, fullNames,
			emailAddresses, andOperator, start, end);
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