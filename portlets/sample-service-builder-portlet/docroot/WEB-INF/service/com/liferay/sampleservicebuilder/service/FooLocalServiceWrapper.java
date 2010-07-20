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

/**
 * <p>
 * This class is a wrapper for {@link FooLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooLocalService
 * @generated
 */
public class FooLocalServiceWrapper implements FooLocalService {
	public FooLocalServiceWrapper(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	public com.liferay.sampleservicebuilder.model.Foo addFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.addFoo(foo);
	}

	public com.liferay.sampleservicebuilder.model.Foo createFoo(long fooId) {
		return _fooLocalService.createFoo(fooId);
	}

	public void deleteFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_fooLocalService.deleteFoo(fooId);
	}

	public void deleteFoo(com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		_fooLocalService.deleteFoo(foo);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.sampleservicebuilder.model.Foo getFoo(long fooId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoo(fooId);
	}

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoos(start, end);
	}

	public int getFoosCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoosCount();
	}

	public com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.updateFoo(foo);
	}

	public com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.updateFoo(foo, merge);
	}

	public void addFoo(long userId, java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_fooLocalService.addFoo(userId, field1, field2, field3, field4, field5);
	}

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoos(obc);
	}

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _fooLocalService.getFoos(start, end, obc);
	}

	public void updateFoo(long fooId, java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_fooLocalService.updateFoo(fooId, field1, field2, field3, field4, field5);
	}

	public FooLocalService getWrappedFooLocalService() {
		return _fooLocalService;
	}

	private FooLocalService _fooLocalService;
}