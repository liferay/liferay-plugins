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

package com.liferay.testpacl.service.impl;

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortalServiceUtil;
import com.liferay.portal.service.persistence.CompanyUtil;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.testpacl.service.base.FooLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class FooLocalServiceImpl extends FooLocalServiceBaseImpl {

	@Override
	public Company getCompanyPersistence_FindByPrimaryKey(long companyId)
		throws PortalException, SystemException {

		return companyPersistence.findByPrimaryKey(companyId);
	}

	@Override
	public Company getCompanyUtil_FindByPrimaryKey(long companyId)
		throws PortalException, SystemException {

		return CompanyUtil.findByPrimaryKey(companyId);
	}

	@Override
	public List<Entry> getEntryLocalServiceUtil_GetEntries(int start, int end)
		throws SystemException {

		return EntryLocalServiceUtil.getEntries(start, end);
	}

	@Override
	public Entry getEntryLocalServiceUtil_GetEntry(long entryId)
		throws PortalException, SystemException {

		return EntryLocalServiceUtil.getEntry(entryId);
	}

	@Override
	public Group getGroupPersistence_FindByPrimaryKey(long groupId)
		throws PortalException, SystemException {

		return groupPersistence.findByPrimaryKey(groupId);
	}

	@Override
	public Group getGroupUtil_FindByPrimaryKey(long groupId)
		throws PortalException, SystemException {

		return GroupUtil.findByPrimaryKey(groupId);
	}

	@Override
	public int getPortalService_GetBuildNumber() {
		return portalService.getBuildNumber();
	}

	@Override
	public int getPortalService_TestGetBuildNumber() {
		return portalService.testGetBuildNumber();
	}

	@Override
	public boolean getPortalService_TestHasClassName() throws SystemException {
		return portalService.testHasClassName();
	}

	@Override
	public int getPortalServiceUtil_GetBuildNumber() {
		return PortalServiceUtil.getBuildNumber();
	}

	@Override
	public int getPortalServiceUtil_TestGetBuildNumber() {
		return PortalServiceUtil.testGetBuildNumber();
	}

	@Override
	public boolean getPortalServiceUtil_TestHasClassName()
		throws SystemException {

		return PortalServiceUtil.testHasClassName();
	}

	@Override
	public int getReleaseInfo_GetBuildNumber() {
		return ReleaseInfo.getBuildNumber();
	}

	@Override
	public Status getStatusLocalServiceUtil_GetStatus(long statusId)
		throws PortalException, SystemException {

		return StatusLocalServiceUtil.getStatus(statusId);
	}

	@Override
	public List<Status> getStatusLocalServiceUtil_GetStatuses(
			int start, int end)
		throws SystemException {

		return StatusLocalServiceUtil.getStatuses(start, end);
	}

	@Override
	public User getUserPersistence_FindByPrimaryKey(long userId)
		throws PortalException, SystemException {

		return userPersistence.findByPrimaryKey(userId);
	}

	@Override
	public User getUserUtil_FindByPrimaryKey(long userId)
		throws PortalException, SystemException {

		return UserUtil.findByPrimaryKey(userId);
	}

}