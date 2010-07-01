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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.sampleservicebuilder.model.Foo;

import java.util.List;

/**
 * <a href="FooUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooPersistence
 * @see       FooPersistenceImpl
 * @generated
 */
public class FooUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Foo foo) {
		getPersistence().clearCache(foo);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Foo> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Foo> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Foo> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Foo remove(Foo foo) throws SystemException {
		return getPersistence().remove(foo);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Foo update(Foo foo, boolean merge) throws SystemException {
		return getPersistence().update(foo, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Foo update(Foo foo, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(foo, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.sampleservicebuilder.model.Foo foo) {
		getPersistence().cacheResult(foo);
	}

	public static void cacheResult(
		java.util.List<com.liferay.sampleservicebuilder.model.Foo> foos) {
		getPersistence().cacheResult(foos);
	}

	public static com.liferay.sampleservicebuilder.model.Foo create(long fooId) {
		return getPersistence().create(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo remove(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().remove(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo updateImpl(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(foo, merge);
	}

	public static com.liferay.sampleservicebuilder.model.Foo findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByPrimaryKey(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(fooId);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByField2(field2);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByField2(field2, start, end);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByField2(field2, start, end, orderByComparator);
	}

	public static com.liferay.sampleservicebuilder.model.Foo findByField2_First(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_First(field2, orderByComparator);
	}

	public static com.liferay.sampleservicebuilder.model.Foo findByField2_Last(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_Last(field2, orderByComparator);
	}

	public static com.liferay.sampleservicebuilder.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence()
				   .findByField2_PrevAndNext(fooId, field2, orderByComparator);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByField2(field2);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByField2(field2);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static FooPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FooPersistence)PortletBeanLocatorUtil.locate(com.liferay.sampleservicebuilder.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					FooPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(FooPersistence persistence) {
		_persistence = persistence;
	}

	private static FooPersistence _persistence;
}