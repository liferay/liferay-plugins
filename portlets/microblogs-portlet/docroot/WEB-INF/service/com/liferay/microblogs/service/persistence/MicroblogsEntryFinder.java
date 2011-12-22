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

package com.liferay.microblogs.service.persistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface MicroblogsEntryFinder {
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByU_MU(long userId, long microblogsEntryUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByU_ATN(long userId, java.lang.String assetTagName)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByU_T_MU(long userId, int type, long microblogsEntryUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_MU(
		long userId, long microblogsEntryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_ATN(
		long userId, java.lang.String assetTagName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T_MU(
		long userId, int type, long microblogsEntryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}