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

package com.liferay.chat.service.persistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface StatusFinder {
	public java.util.List<java.lang.Object[]> findByModifiedDate(
		long companyId, long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> findBySocialRelationType(
		long userId, int type, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Object[]> findByUsersGroups(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}