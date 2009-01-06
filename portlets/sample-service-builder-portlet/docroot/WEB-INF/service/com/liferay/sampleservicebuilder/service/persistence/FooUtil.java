/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleservicebuilder.service.persistence;

/**
 * <a href="FooUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FooUtil {
	public static com.liferay.sampleservicebuilder.model.Foo create(long fooId) {
		return getPersistence().create(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo remove(long fooId)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().remove(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo remove(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(foo);
	}

	public static com.liferay.sampleservicebuilder.model.Foo update(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(foo);
	}

	public static com.liferay.sampleservicebuilder.model.Foo update(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(foo, merge);
	}

	public static com.liferay.sampleservicebuilder.model.Foo updateImpl(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(foo, merge);
	}

	public static com.liferay.sampleservicebuilder.model.Foo findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByPrimaryKey(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(fooId);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2) throws com.liferay.portal.SystemException {
		return getPersistence().findByField2(field2);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByField2(field2, start, end);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByField2(field2, start, end, obc);
	}

	public static com.liferay.sampleservicebuilder.model.Foo findByField2_First(
		boolean field2, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_First(field2, obc);
	}

	public static com.liferay.sampleservicebuilder.model.Foo findByField2_Last(
		boolean field2, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_Last(field2, obc);
	}

	public static com.liferay.sampleservicebuilder.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_PrevAndNext(fooId, field2, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByField2(boolean field2)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByField2(field2);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByField2(boolean field2)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByField2(field2);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static FooPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(FooPersistence persistence) {
		_persistence = persistence;
	}

	private static FooPersistence _persistence;
}