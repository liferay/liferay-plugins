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

package com.liferay.mobilewidgets.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for MobileWidgetsDDLRecord. This utility wraps
 * {@link com.liferay.mobilewidgets.service.impl.MobileWidgetsDDLRecordServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author José Manuel Navarro
 * @see MobileWidgetsDDLRecordService
 * @see com.liferay.mobilewidgets.service.base.MobileWidgetsDDLRecordServiceBaseImpl
 * @see com.liferay.mobilewidgets.service.impl.MobileWidgetsDDLRecordServiceImpl
 * @generated
 */
public class MobileWidgetsDDLRecordServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.mobilewidgets.service.impl.MobileWidgetsDDLRecordServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.portal.kernel.json.JSONObject getDDLRecord(
		long ddlRecordId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDDLRecord(ddlRecordId, locale);
	}

	public static com.liferay.portal.kernel.json.JSONArray getDDLRecords(
		long ddlRecordSetId, long userId, java.util.Locale locale, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getDDLRecords(ddlRecordSetId, userId, locale, start, end);
	}

	public static int getDDLRecordsCount(long ddlRecordSetId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDDLRecordsCount(ddlRecordSetId, userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static MobileWidgetsDDLRecordService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MobileWidgetsDDLRecordService.class.getName());

			if (invokableService instanceof MobileWidgetsDDLRecordService) {
				_service = (MobileWidgetsDDLRecordService)invokableService;
			}
			else {
				_service = new MobileWidgetsDDLRecordServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(MobileWidgetsDDLRecordServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(MobileWidgetsDDLRecordService service) {
	}

	private static MobileWidgetsDDLRecordService _service;
}