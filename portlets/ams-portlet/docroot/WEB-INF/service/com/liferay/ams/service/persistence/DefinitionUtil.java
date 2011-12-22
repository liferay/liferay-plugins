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

import com.liferay.ams.model.Definition;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the definition service. This utility wraps {@link DefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefinitionPersistence
 * @see DefinitionPersistenceImpl
 * @generated
 */
public class DefinitionUtil {
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
	public static void clearCache(Definition definition) {
		getPersistence().clearCache(definition);
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
	public static List<Definition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Definition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Definition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Definition update(Definition definition, boolean merge)
		throws SystemException {
		return getPersistence().update(definition, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Definition update(Definition definition, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(definition, merge, serviceContext);
	}

	/**
	* Caches the definition in the entity cache if it is enabled.
	*
	* @param definition the definition
	*/
	public static void cacheResult(com.liferay.ams.model.Definition definition) {
		getPersistence().cacheResult(definition);
	}

	/**
	* Caches the definitions in the entity cache if it is enabled.
	*
	* @param definitions the definitions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.ams.model.Definition> definitions) {
		getPersistence().cacheResult(definitions);
	}

	/**
	* Creates a new definition with the primary key. Does not add the definition to the database.
	*
	* @param definitionId the primary key for the new definition
	* @return the new definition
	*/
	public static com.liferay.ams.model.Definition create(long definitionId) {
		return getPersistence().create(definitionId);
	}

	/**
	* Removes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param definitionId the primary key of the definition
	* @return the definition that was removed
	* @throws com.liferay.ams.NoSuchDefinitionException if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition remove(long definitionId)
		throws com.liferay.ams.NoSuchDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(definitionId);
	}

	public static com.liferay.ams.model.Definition updateImpl(
		com.liferay.ams.model.Definition definition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(definition, merge);
	}

	/**
	* Returns the definition with the primary key or throws a {@link com.liferay.ams.NoSuchDefinitionException} if it could not be found.
	*
	* @param definitionId the primary key of the definition
	* @return the definition
	* @throws com.liferay.ams.NoSuchDefinitionException if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition findByPrimaryKey(
		long definitionId)
		throws com.liferay.ams.NoSuchDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(definitionId);
	}

	/**
	* Returns the definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param definitionId the primary key of the definition
	* @return the definition, or <code>null</code> if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.ams.model.Definition fetchByPrimaryKey(
		long definitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(definitionId);
	}

	/**
	* Returns all the definitions.
	*
	* @return the definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Definition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of definitions
	* @param end the upper bound of the range of definitions (not inclusive)
	* @return the range of definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Definition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of definitions
	* @param end the upper bound of the range of definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.ams.model.Definition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the definitions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of definitions.
	*
	* @return the number of definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DefinitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DefinitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.getServletContextName(),
					DefinitionPersistence.class.getName());

			ReferenceRegistry.registerReference(DefinitionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(DefinitionPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(DefinitionUtil.class, "_persistence");
	}

	private static DefinitionPersistence _persistence;
}