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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the test p a c l local service. This utility wraps {@link com.liferay.testpacl.service.impl.TestPACLLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestPACLLocalService
 * @see com.liferay.testpacl.service.base.TestPACLLocalServiceBaseImpl
 * @see com.liferay.testpacl.service.impl.TestPACLLocalServiceImpl
 * @generated
 */
public class TestPACLLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.testpacl.service.impl.TestPACLLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portal.model.Company getCompanyPersistence_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyPersistence_FindByPrimaryKey(companyId);
	}

	public static com.liferay.portal.model.Company getCompanyUtil_FindByPrimaryKey(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyUtil_FindByPrimaryKey(companyId);
	}

	public static java.util.List<com.liferay.chat.model.Entry> getEntryLocalServiceUtil_GetEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getEntryLocalServiceUtil_GetEntries(start, end);
	}

	public static com.liferay.chat.model.Entry getEntryLocalServiceUtil_GetEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getEntryLocalServiceUtil_GetEntry(entryId);
	}

	public static com.liferay.portal.model.Group getGroupPersistence_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupPersistence_FindByPrimaryKey(groupId);
	}

	public static com.liferay.portal.model.Group getGroupUtil_FindByPrimaryKey(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupUtil_FindByPrimaryKey(groupId);
	}

	public static int getPortalService_GetBuildNumber() {
		return getService().getPortalService_GetBuildNumber();
	}

	public static int getPortalService_TestGetBuildNumber() {
		return getService().getPortalService_TestGetBuildNumber();
	}

	public static boolean getPortalService_TestHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalService_TestHasClassName();
	}

	public static int getPortalServiceUtil_GetBuildNumber() {
		return getService().getPortalServiceUtil_GetBuildNumber();
	}

	public static int getPortalServiceUtil_TestGetBuildNumber() {
		return getService().getPortalServiceUtil_TestGetBuildNumber();
	}

	public static boolean getPortalServiceUtil_TestHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortalServiceUtil_TestHasClassName();
	}

	public static int getReleaseInfo_GetBuildNumber() {
		return getService().getReleaseInfo_GetBuildNumber();
	}

	public static com.liferay.chat.model.Status getStatusLocalServiceUtil_GetStatus(
		long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatusLocalServiceUtil_GetStatus(statusId);
	}

	public static java.util.List<com.liferay.chat.model.Status> getStatusLocalServiceUtil_GetStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatusLocalServiceUtil_GetStatuses(start, end);
	}

	public static com.liferay.portal.model.User getUserPersistence_FindByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserPersistence_FindByPrimaryKey(userId);
	}

	public static com.liferay.portal.model.User getUserUtil_FindByPrimaryKey(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUtil_FindByPrimaryKey(userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TestPACLLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TestPACLLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					TestPACLLocalService.class.getName(), portletClassLoader);

			_service = new TestPACLLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(TestPACLLocalServiceUtil.class,
				"_service");
			MethodCache.remove(TestPACLLocalService.class);
		}

		return _service;
	}

	public void setService(TestPACLLocalService service) {
		MethodCache.remove(TestPACLLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(TestPACLLocalServiceUtil.class,
			"_service");
		MethodCache.remove(TestPACLLocalService.class);
	}

	private static TestPACLLocalService _service;
}