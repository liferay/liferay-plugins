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

package com.liferay.sampleservicebuilder.service.persistence;

/**
 * <a href="FooPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface FooPersistence {
	public com.liferay.sampleservicebuilder.model.Foo create(long fooId);

	public com.liferay.sampleservicebuilder.model.Foo remove(long fooId)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo remove(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo update(
		com.liferay.sampleservicebuilder.model.Foo foo)
		throws com.liferay.portal.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo update(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo updateImpl(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo findByField2_First(
		boolean field2, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo findByField2_Last(
		boolean field2, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByField2(boolean field2)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByField2(boolean field2)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}