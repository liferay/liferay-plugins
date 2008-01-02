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
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.sample.servicebuilder.service.FooLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * <p>
 * <code>com.sample.servicebuilder.service.FooLocalServiceFactory</code>
 * is responsible for the lookup of the bean.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.sample.servicebuilder.service.FooLocalService
 * @see com.sample.servicebuilder.service.FooLocalServiceFactory
 *
 */
public class FooLocalServiceUtil {
	public static com.sample.servicebuilder.model.Foo addFoo(
		com.sample.servicebuilder.model.Foo model)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.addFoo(model);
	}

	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.dynamicQuery(queryInitializer);
	}

	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.dynamicQuery(queryInitializer, begin, end);
	}

	public static com.sample.servicebuilder.model.Foo updateFoo(
		com.sample.servicebuilder.model.Foo model)
		throws com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.updateFoo(model);
	}

	public static com.sample.servicebuilder.service.persistence.FooPersistence getFooPersistence() {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		return fooLocalService.getFooPersistence();
	}

	public static void setFooPersistence(
		com.sample.servicebuilder.service.persistence.FooPersistence fooPersistence) {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.setFooPersistence(fooPersistence);
	}

	public static void afterPropertiesSet() {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.afterPropertiesSet();
	}

	public static void addFoo(java.lang.String field1, boolean field2,
		int field3, java.util.Date field4, java.lang.String field5)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.addFoo(field1, field2, field3, field4, field5);
	}

	public static void deleteFoo(long fooId)
		throws com.liferay.portal.PortalException, 
			com.liferay.portal.SystemException {
		FooLocalService fooLocalService = FooLocalServiceFactory.getService();

		fooLocalService.deleteFoo(fooId);
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