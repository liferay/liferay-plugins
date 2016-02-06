/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.service.impl;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.base.MicroblogsEntryServiceBaseImpl;
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.microblogs.service.permission.MicroblogsPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryServiceImpl extends MicroblogsEntryServiceBaseImpl {

	public MicroblogsEntry addMicroblogsEntry(
			long userId, String content, int type, long parentMicroblogsEntryId,
			int socialRelationType, ServiceContext serviceContext)
		throws PortalException {

		MicroblogsPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return microblogsEntryLocalService.addMicroblogsEntry(
			userId, content, type, parentMicroblogsEntryId, socialRelationType,
			serviceContext);
	}

	public MicroblogsEntry deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.DELETE);

		return microblogsEntryLocalService.deleteMicroblogsEntry(
			microblogsEntryId);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(int start, int end)
		throws PortalException {

		return microblogsEntryFinder.findByUserId(
			getGuestOrUserId(), start, end);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
			String assetTagName, int start, int end)
		throws PortalException {

		return microblogsEntryFinder.findByU_ATN(
			getGuestOrUserId(), assetTagName, start, end);
	}

	public int getMicroblogsEntriesCount() throws PortalException {
		return microblogsEntryFinder.countByUserId(getGuestOrUserId());
	}

	public int getMicroblogsEntriesCount(String assetTagName)
		throws PortalException {

		return microblogsEntryFinder.countByU_ATN(
			getGuestOrUserId(), assetTagName);
	}

	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.VIEW);

		return microblogsEntryLocalService.getMicroblogsEntry(
			microblogsEntryId);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long microblogsEntryUserId, int start, int end)
		throws PortalException {

		return microblogsEntryFinder.findByU_MU(
			getGuestOrUserId(), microblogsEntryUserId, start, end);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long microblogsEntryUserId, int type, int start, int end)
		throws PortalException {

		return microblogsEntryFinder.findByU_T_MU(
			getGuestOrUserId(), type, microblogsEntryUserId, start, end);
	}

	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws PortalException {

		return microblogsEntryFinder.countByU_MU(
			getGuestOrUserId(), microblogsEntryUserId);
	}

	public int getUserMicroblogsEntriesCount(
			long microblogsEntryUserId, int type)
		throws PortalException {

		return microblogsEntryFinder.countByU_T_MU(
			getGuestOrUserId(), type, microblogsEntryUserId);
	}

	public MicroblogsEntry updateMicroblogsEntry(
			long microblogsEntryId, String content, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.UPDATE);

		return microblogsEntryLocalService.updateMicroblogsEntry(
			microblogsEntryId, content, socialRelationType, serviceContext);
	}

}