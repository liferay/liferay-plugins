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

package com.liferay.microblogs.service.impl;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.microblogs.service.base.MicroblogsEntryServiceBaseImpl;
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.microblogs.service.permission.MicroblogsPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryServiceImpl extends MicroblogsEntryServiceBaseImpl {

	public MicroblogsEntry addMicroblogsEntry(
			long userId, String content, int type, long receiverUserId,
			long receiverEntryId, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MicroblogsPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_ENTRY);

		return microblogsEntryLocalService.addMicroblogsEntry(
			userId, content, type, receiverUserId, receiverEntryId,
			socialRelationType, serviceContext);
	 }

	public void deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.DELETE);

		microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntryId);
	}

	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		MicroblogsEntryPermission.check(
			getPermissionChecker(), microblogsEntryId, ActionKeys.VIEW);

		return MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
			microblogsEntryId);
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