/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.sampleservicebuilder.service;

/**
 * <a href="FooLocalServiceWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class FooLocalServiceWrapper implements FooLocalService {
	public FooLocalServiceWrapper(FooLocalService fooLocalService) {
		_fooLocalService = fooLocalService;
	}

	public com.liferay.sampleservicebuilder.model.Foo addFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		return _fooLocalService.addFoo(foo);
	}

	public com.liferay.sampleservicebuilder.model.Foo createFoo(long fooId) {
		return _fooLocalService.createFoo(fooId);
	}

	public void deleteFoo(long fooId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_fooLocalService.deleteFoo(fooId);
	}

	public void deleteFoo(com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		_fooLocalService.deleteFoo(foo);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _fooLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _fooLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.sampleservicebuilder.model.Foo getFoo(long fooId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _fooLocalService.getFoo(fooId);
	}

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end) throws com.liferay.portal.SystemException {
		return _fooLocalService.getFoos(start, end);
	}

	public int getFoosCount() throws com.liferay.portal.SystemException {
		return _fooLocalService.getFoosCount();
	}

	public com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		return _fooLocalService.updateFoo(foo);
	}

	public com.liferay.sampleservicebuilder.model.Foo updateFoo(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.SystemException {
		return _fooLocalService.updateFoo(foo, merge);
	}

	public void addFoo(long userId, java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_fooLocalService.addFoo(userId, field1, field2, field3, field4, field5);
	}

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return _fooLocalService.getFoos(obc);
	}

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> getFoos(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return _fooLocalService.getFoos(start, end, obc);
	}

	public void updateFoo(long fooId, java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_fooLocalService.updateFoo(fooId, field1, field2, field3, field4, field5);
	}

	public FooLocalService getWrappedFooLocalService() {
		return _fooLocalService;
	}

	private FooLocalService _fooLocalService;
}