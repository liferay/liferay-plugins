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

package com.liferay.microblogs.service.impl;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.base.MicroblogsEntryServiceBaseImpl;
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.microblogs.service.permission.MicroblogsPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryServiceImpl extends MicroblogsEntryServiceBaseImpl {

	public MicroblogsEntry addMicroblogsEntry(
			long userId, String content, int type, long receiverUserId,
			long receiverMicroblogsEntryId, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MicroblogsPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return microblogsEntryLocalService.addMicroblogsEntry(
			userId, content, type, receiverUserId, receiverMicroblogsEntryId,
			socialRelationType, serviceContext);
	}

	public void deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.DELETE);

		microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntryId);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryFinder.findByUserId(getUserId(), start, end);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
			String assetTagName, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryFinder.findByU_ATN(
			getUserId(), assetTagName, start, end);
	}

	public int getMicroblogsEntriesCount()
		throws PortalException, SystemException {

		return microblogsEntryFinder.countByUserId(getUserId());
	}

	public int getMicroblogsEntriesCount(String assetTagName)
		throws PortalException, SystemException {

		return microblogsEntryFinder.countByU_ATN(getUserId(), assetTagName);
	}

	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.VIEW);

		return microblogsEntryLocalService.getMicroblogsEntry(
			microblogsEntryId);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long microblogsEntryUserId, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryFinder.findByU_MU(
			getUserId(), microblogsEntryUserId, start, end);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long microblogsEntryUserId, int type, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryFinder.findByU_T_MU(
			getUserId(), type, microblogsEntryUserId, start, end);
	}

	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws PortalException, SystemException {

		return microblogsEntryFinder.countByU_MU(
			getUserId(), microblogsEntryUserId);
	}

	public int getUserMicroblogsEntriesCount(
			long microblogsEntryUserId, int type)
		throws PortalException, SystemException {

		return microblogsEntryFinder.countByU_T_MU(
			getUserId(), type, microblogsEntryUserId);
	}

	public MicroblogsEntry updateMicroblogsEntry(
			long microblogsEntryId, String content, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.UPDATE);

		return microblogsEntryLocalService.updateMicroblogsEntry(
			microblogsEntryId, content, socialRelationType, serviceContext);
	}

}