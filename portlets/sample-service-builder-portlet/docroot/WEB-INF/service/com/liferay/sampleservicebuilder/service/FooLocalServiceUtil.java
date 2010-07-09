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

package com.liferay.sampleservicebuilder.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link FooLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooLocalService
 * @generated
 */
public class FooLocalServiceUtil {
	public static com.liferay.sampleservicebuilder.model.Foo addFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFoo(foo);
	}

	public static com.liferay.sampleservicebuilder.model.Foo createFoo(
		long fooId) {
		return getService().createFoo(fooId);
	}

	public static void deleteFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFoo(fooId);
	}

	public static void deleteFoo(com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFoo(foo);
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

	public static com.liferay.sampleservicebuilder.model.Foo getFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoo(fooId);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoos(start, end);
	}

	public static int getFoosCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoosCount();
	}

	public static com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFoo(foo);
	}

	public static com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFoo(foo, merge);
	}

	public static void addFoo(long userId, java.lang.String field1,
		boolean field2, int field3, java.util.Date field4,
		java.lang.String field5)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addFoo(userId, field1, field2, field3, field4, field5);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoos(obc);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFoos(start, end, obc);
	}

	public static void updateFoo(long fooId, java.lang.String field1,
		boolean field2, int field3, java.util.Date field4,
		java.lang.String field5)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateFoo(fooId, field1, field2, field3, field4, field5);
	}

	public static void clearService() {
		_service = null;
	}

	public static FooLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					FooLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					FooLocalService.class.getName(), portletClassLoader);

			_service = new FooLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(FooLocalService service) {
		_service = service;
	}

	private static FooLocalService _service;
}