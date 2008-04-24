/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.sample.servicebuilder.service;

/**
 * <a href="FooLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FooLocalServiceUtil {
	public static com.sample.servicebuilder.model.Foo addFoo(
		com.sample.servicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.addFoo(foo);
	}

	public static void deleteFoo(long fooId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.deleteFoo(fooId);
	}

	public static void deleteFoo(com.sample.servicebuilder.model.Foo foo)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.deleteFoo(foo);
	}

	public static java.util.List<com.sample.servicebuilder.model.Foo> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.dynamicQuery(queryInitializer);
	}

	public static java.util.List<com.sample.servicebuilder.model.Foo> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.dynamicQuery(queryInitializer, begin, end);
	}

	public static com.sample.servicebuilder.model.Foo updateFoo(
		com.sample.servicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.updateFoo(foo);
	}

	public static void addFoo(java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.addFoo(field1, field2, field3, field4, field5);
	}

	public static com.sample.servicebuilder.model.Foo getFoo(long fooId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.getFoo(fooId);
	}

	public static java.util.List getFoos()
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.getFoos();
	}

	public static java.util.List getFoos(
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.getFoos(obc);
	}

	public static java.util.List getFoos(int begin, int end)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.getFoos(begin, end);
	}

	public static java.util.List getFoos(int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.getFoos(begin, end, obc);
	}

	public static void updateFoo(long fooId, java.lang.String field1,
		boolean field2, int field3, java.util.Date field4,
		java.lang.String field5)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.updateFoo(fooId, field1, field2, field3, field4, field5);
	}
}