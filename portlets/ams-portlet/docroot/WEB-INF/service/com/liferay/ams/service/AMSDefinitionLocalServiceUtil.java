/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="AMSDefinitionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link AMSDefinitionLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSDefinitionLocalService
 * @generated
 */
public class AMSDefinitionLocalServiceUtil {
	public static com.liferay.ams.model.AMSDefinition addAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAMSDefinition(amsDefinition);
	}

	public static com.liferay.ams.model.AMSDefinition createAMSDefinition(
		long assetDefinitionId) {
		return getService().createAMSDefinition(assetDefinitionId);
	}

	public static void deleteAMSDefinition(long assetDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSDefinition(assetDefinitionId);
	}

	public static void deleteAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSDefinition(amsDefinition);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.ams.model.AMSDefinition getAMSDefinition(
		long assetDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSDefinition(assetDefinitionId);
	}

	public static java.util.List<com.liferay.ams.model.AMSDefinition> getAMSDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSDefinitions(start, end);
	}

	public static int getAMSDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSDefinitionsCount();
	}

	public static com.liferay.ams.model.AMSDefinition updateAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSDefinition(amsDefinition);
	}

	public static com.liferay.ams.model.AMSDefinition updateAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSDefinition(amsDefinition, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static AMSDefinitionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSDefinitionLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					AMSDefinitionLocalService.class.getName(),
					portletClassLoader);

			_service = new AMSDefinitionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AMSDefinitionLocalService service) {
		_service = service;
	}

	private static AMSDefinitionLocalService _service;
}