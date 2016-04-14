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

package com.liferay.skinny.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for Skinny. This utility wraps
 * {@link com.liferay.skinny.service.impl.SkinnyServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SkinnyService
 * @see com.liferay.skinny.service.base.SkinnyServiceBaseImpl
 * @see com.liferay.skinny.service.impl.SkinnyServiceImpl
 * @generated
 */
@ProviderType
public class SkinnyServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.skinny.service.impl.SkinnyServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.skinny.model.SkinnyDDLRecord> getSkinnyDDLRecords(
		long ddlRecordSetId) throws java.lang.Exception {
		return getService().getSkinnyDDLRecords(ddlRecordSetId);
	}

	public static java.util.List<com.liferay.skinny.model.SkinnyJournalArticle> getSkinnyJournalArticles(
		long companyId, java.lang.String groupName, long ddmStructureId,
		java.lang.String locale) throws java.lang.Exception {
		return getService()
				   .getSkinnyJournalArticles(companyId, groupName,
			ddmStructureId, locale);
	}

	public static void clearService() {
		_service = null;
	}

	public static SkinnyService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SkinnyService.class.getName());

			if (invokableService instanceof SkinnyService) {
				_service = (SkinnyService)invokableService;
			}
			else {
				_service = new SkinnyServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SkinnyServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SkinnyService _service;
}