/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.service.persistence;

import com.liferay.ams.model.Type;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the type service. This utility wraps {@link TypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TypePersistence
 * @see TypePersistenceImpl
 * @generated
 */
public class TypeUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Type type) {
		getPersistence().clearCache(type);
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
	public static List<Type> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Type> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Type> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Type update(Type type, boolean merge)
		throws SystemException {
		return getPersistence().update(type, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Type update(Type type, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(type, merge, serviceContext);
	}

	/**
	* Caches the type in the entity cache if it is enabled.
	*
	* @param type the type
	*/
	public static void cacheResult(com.liferay.ams.model.Type type) {
		getPersistence().cacheResult(type);
	}

	/**
	* Caches the types in the entity cache if it is enabled.
	*
	* @param types the types
	*/
	public static void cacheResult(
		java.util.List<com.liferay.ams.model.Type> types) {
		getPersistence().cacheResult(types);
	}

	/**
	* Creates a new type with the primary key. Does not add the type to the database.
	*
	* @param typeId the primary key for the new type
	* @return the new type
	*/
	public static com.liferay.ams.model.Type create(long typeId) {
		return getPersistence().create(typeId);
	}

	/**
	* Removes the type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param typeId the primary key of the type
	* @return the type that was removed
	* @throws com.liferay.ams.NoSuchTypeException if a type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Type remove(long typeId)
		throws com.liferay.ams.NoSuchTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(typeId);
	}

	public static com.liferay.ams.model.Type updateImpl(
		com.liferay.ams.model.Type type, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(type, merge);
	}

	/**
	* Returns the type with the primary key or throws a {@link com.liferay.ams.NoSuchTypeException} if it could not be found.
	*
	* @param typeId the primary key of the type
	* @return the type
	* @throws com.liferay.ams.NoSuchTypeException if a type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Type findByPrimaryKey(long typeId)
		throws com.liferay.ams.NoSuchTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(typeId);
	}

	/**
	* Returns the type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param typeId the primary key of the type
	* @return the type, or <code>null</code> if a type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Type fetchByPrimaryKey(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(typeId);
	}

	/**
	* Returns all the types.
	*
	* @return the types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Type> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of types
	* @param end the upper bound of the range of types (not inclusive)
	* @return the range of types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Type> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of types
	* @param end the upper bound of the range of types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Type> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of types.
	*
	* @return the number of types
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TypePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TypePersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.getServletContextName(),
					TypePersistence.class.getName());

			ReferenceRegistry.registerReference(TypeUtil.class, "_persistence");
		}

		return _persistence;
	}

	public void setPersistence(TypePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(TypeUtil.class, "_persistence");
	}

	private static TypePersistence _persistence;
}