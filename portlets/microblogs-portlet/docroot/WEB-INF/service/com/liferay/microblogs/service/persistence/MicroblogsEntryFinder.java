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

/**
 * @author Brian Wing Shun Chan
 */
public interface MicroblogsEntryFinder {
	public int countByC_U_T_RU_RE_S_V(long companyId, long[] userIds, int type,
		long receiverUserId, long receiverEntryId, int socialRelationType,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByT_V(java.lang.String[] tagNames, long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByC_U_T_RU_RE_S_V(
		long companyId, long[] userIds, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType, long viewerUserId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_V(
		java.lang.String[] tagNames, long viewerUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}