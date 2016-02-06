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

package com.liferay.testpacl.service.impl;

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortalService;
import com.liferay.portal.kernel.service.PortalServiceUtil;
import com.liferay.portal.kernel.service.persistence.CompanyUtil;
import com.liferay.portal.kernel.service.persistence.GroupUtil;
import com.liferay.portal.kernel.service.persistence.UserUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.testpacl.service.base.FooLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class FooLocalServiceImpl extends FooLocalServiceBaseImpl {

	@Override
	public Company getCompanyPersistence_FindByPrimaryKey(long companyId)
		throws PortalException {

		return companyPersistence.findByPrimaryKey(companyId);
	}

	@Override
	public Company getCompanyUtil_FindByPrimaryKey(long companyId)
		throws PortalException {

		return CompanyUtil.findByPrimaryKey(companyId);
	}

	@Override
	public List<Entry> getEntryLocalServiceUtil_GetEntries(int start, int end) {
		return EntryLocalServiceUtil.getEntries(start, end);
	}

	@Override
	public Entry getEntryLocalServiceUtil_GetEntry(long entryId)
		throws PortalException {

		return EntryLocalServiceUtil.getEntry(entryId);
	}

	@Override
	public Group getGroupPersistence_FindByPrimaryKey(long groupId)
		throws PortalException {

		return groupPersistence.findByPrimaryKey(groupId);
	}

	@Override
	public Group getGroupUtil_FindByPrimaryKey(long groupId)
		throws PortalException {

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
	public boolean getPortalService_TestHasClassName() {
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
	public boolean getPortalServiceUtil_TestHasClassName() {
		return PortalServiceUtil.testHasClassName();
	}

	@Override
	public int getReleaseInfo_GetBuildNumber() {
		return ReleaseInfo.getBuildNumber();
	}

	@Override
	public Status getStatusLocalServiceUtil_GetStatus(long statusId)
		throws PortalException {

		return StatusLocalServiceUtil.getStatus(statusId);
	}

	@Override
	public List<Status> getStatusLocalServiceUtil_GetStatuses(
		int start, int end) {

		return StatusLocalServiceUtil.getStatuses(start, end);
	}

	@Override
	public User getUserPersistence_FindByPrimaryKey(long userId)
		throws PortalException {

		return userPersistence.findByPrimaryKey(userId);
	}

	@Override
	public User getUserUtil_FindByPrimaryKey(long userId)
		throws PortalException {

		return UserUtil.findByPrimaryKey(userId);
	}

	@BeanReference(type = PortalService.class)
	protected PortalService portalService;

}