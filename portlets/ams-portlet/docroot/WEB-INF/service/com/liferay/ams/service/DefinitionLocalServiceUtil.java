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
 * <p>
 * This class provides static methods for the
 * {@link DefinitionLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DefinitionLocalService
 * @generated
 */
public class DefinitionLocalServiceUtil {
	public static com.liferay.ams.model.Definition addDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDefinition(definition);
	}

	public static com.liferay.ams.model.Definition createDefinition(
		long definitionId) {
		return getService().createDefinition(definitionId);
	}

	public static void deleteDefinition(long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteDefinition(definitionId);
	}

	public static void deleteDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteDefinition(definition);
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

	public static com.liferay.ams.model.Definition getDefinition(
		long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefinition(definitionId);
	}

	public static java.util.List<com.liferay.ams.model.Definition> getDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefinitions(start, end);
	}

	public static int getDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDefinitionsCount();
	}

	public static com.liferay.ams.model.Definition updateDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDefinition(definition);
	}

	public static com.liferay.ams.model.Definition updateDefinition(
		com.liferay.ams.model.Definition definition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDefinition(definition, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static DefinitionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					DefinitionLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					DefinitionLocalService.class.getName(), portletClassLoader);

			_service = new DefinitionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(DefinitionLocalService service) {
		_service = service;
	}

	private static DefinitionLocalService _service;
}