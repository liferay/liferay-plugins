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

package com.liferay.testpacl.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TestPACLLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TestPACLLocalService
 * @generated
 */
public class TestPACLLocalServiceWrapper implements TestPACLLocalService,
	ServiceWrapper<TestPACLLocalService> {
	public TestPACLLocalServiceWrapper(
		TestPACLLocalService testPACLLocalService) {
		_testPACLLocalService = testPACLLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _testPACLLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_testPACLLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.Company getCompanyPersistence_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getCompanyPersistence_FindByPrimaryKey(companyId);
	}

	public com.liferay.portal.model.Company getCompanyUtil_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getCompanyUtil_FindByPrimaryKey(companyId);
	}

	public java.util.List<com.liferay.chat.model.Entry> getEntryLocalServiceUtil_GetEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getEntryLocalServiceUtil_GetEntries(start,
			end);
	}

	public com.liferay.chat.model.Entry getEntryLocalServiceUtil_GetEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getEntryLocalServiceUtil_GetEntry(entryId);
	}

	public com.liferay.portal.model.Group getGroupPersistence_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getGroupPersistence_FindByPrimaryKey(groupId);
	}

	public com.liferay.portal.model.Group getGroupUtil_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getGroupUtil_FindByPrimaryKey(groupId);
	}

	public int getPortalService_GetBuildNumber() {
		return _testPACLLocalService.getPortalService_GetBuildNumber();
	}

	public int getPortalService_TestGetBuildNumber() {
		return _testPACLLocalService.getPortalService_TestGetBuildNumber();
	}

	public boolean getPortalService_TestHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getPortalService_TestHasClassName();
	}

	public int getPortalServiceUtil_GetBuildNumber() {
		return _testPACLLocalService.getPortalServiceUtil_GetBuildNumber();
	}

	public int getPortalServiceUtil_TestGetBuildNumber() {
		return _testPACLLocalService.getPortalServiceUtil_TestGetBuildNumber();
	}

	public boolean getPortalServiceUtil_TestHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getPortalServiceUtil_TestHasClassName();
	}

	public int getReleaseInfo_GetBuildNumber() {
		return _testPACLLocalService.getReleaseInfo_GetBuildNumber();
	}

	public com.liferay.chat.model.Status getStatusLocalServiceUtil_GetStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getStatusLocalServiceUtil_GetStatus(statusId);
	}

	public java.util.List<com.liferay.chat.model.Status> getStatusLocalServiceUtil_GetStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getStatusLocalServiceUtil_GetStatuses(start,
			end);
	}

	public com.liferay.portal.model.User getUserPersistence_FindByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getUserPersistence_FindByPrimaryKey(userId);
	}

	public com.liferay.portal.model.User getUserUtil_FindByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _testPACLLocalService.getUserUtil_FindByPrimaryKey(userId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TestPACLLocalService getWrappedTestPACLLocalService() {
		return _testPACLLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTestPACLLocalService(
		TestPACLLocalService testPACLLocalService) {
		_testPACLLocalService = testPACLLocalService;
	}

	public TestPACLLocalService getWrappedService() {
		return _testPACLLocalService;
	}

	public void setWrappedService(TestPACLLocalService testPACLLocalService) {
		_testPACLLocalService = testPACLLocalService;
	}

	private TestPACLLocalService _testPACLLocalService;
}