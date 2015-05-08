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

package com.liferay.microblogs.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface MicroblogsEntryFinder {
	public int countByUserId(long userId);

	public int countByU_MU(long userId, long microblogsEntryUserId);

	public int countByU_ATN(long userId, java.lang.String assetTagName);

	public int countByCCNI_ATN(long creatorClassNameId,
		java.lang.String assetTagName);

	public int countByU_T_MU(long userId, int type, long microblogsEntryUserId);

	public int countByCCNI_CCPK_ATN(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName, boolean andOperator);

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end);

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_MU(
		long userId, long microblogsEntryUserId, int start, int end);

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_ATN(
		long userId, java.lang.String assetTagName, int start, int end);

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_ATN(
		long creatorClassNameId, java.lang.String assetTagName, int start,
		int end);

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T_MU(
		long userId, int type, long microblogsEntryUserId, int start, int end);

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCCNI_CCPK_ATN(
		long creatorClassNameId, long creatorClassPK,
		java.lang.String assetTagName, boolean andOperator, int start, int end);
}