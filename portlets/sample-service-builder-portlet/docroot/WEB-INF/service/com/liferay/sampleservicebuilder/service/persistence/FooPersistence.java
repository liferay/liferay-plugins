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

package com.liferay.sampleservicebuilder.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sampleservicebuilder.model.Foo;

/**
 * @author    Brian Wing Shun Chan
 * @see       FooPersistenceImpl
 * @see       FooUtil
 * @generated
 */
public interface FooPersistence extends BasePersistence<Foo> {
	public void cacheResult(com.liferay.sampleservicebuilder.model.Foo foo);

	public void cacheResult(
		java.util.List<com.liferay.sampleservicebuilder.model.Foo> foos);

	public com.liferay.sampleservicebuilder.model.Foo create(long fooId);

	public com.liferay.sampleservicebuilder.model.Foo remove(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo updateImpl(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.sampleservicebuilder.model.Foo findByField2_First(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo findByField2_Last(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public com.liferay.sampleservicebuilder.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}