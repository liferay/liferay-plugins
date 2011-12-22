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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefinitionPersistenceImpl
 * @see DefinitionUtil
 * @generated
 */
public interface DefinitionPersistence extends BasePersistence<Definition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DefinitionUtil} to access the definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the definition in the entity cache if it is enabled.
	*
	* @param definition the definition
	*/
	public void cacheResult(com.liferay.ams.model.Definition definition);

	/**
	* Caches the definitions in the entity cache if it is enabled.
	*
	* @param definitions the definitions
	*/
	public void cacheResult(
		java.util.List<com.liferay.ams.model.Definition> definitions);

	/**
	* Creates a new definition with the primary key. Does not add the definition to the database.
	*
	* @param definitionId the primary key for the new definition
	* @return the new definition
	*/
	public com.liferay.ams.model.Definition create(long definitionId);

	/**
	* Removes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param definitionId the primary key of the definition
	* @return the definition that was removed
	* @throws com.liferay.ams.NoSuchDefinitionException if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Definition remove(long definitionId)
		throws com.liferay.ams.NoSuchDefinitionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Definition updateImpl(
		com.liferay.ams.model.Definition definition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the definition with the primary key or throws a {@link com.liferay.ams.NoSuchDefinitionException} if it could not be found.
	*
	* @param definitionId the primary key of the definition
	* @return the definition
	* @throws com.liferay.ams.NoSuchDefinitionException if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Definition findByPrimaryKey(long definitionId)
		throws com.liferay.ams.NoSuchDefinitionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param definitionId the primary key of the definition
	* @return the definition, or <code>null</code> if a definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.ams.model.Definition fetchByPrimaryKey(long definitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the definitions.
	*
	* @return the definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.ams.model.Definition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.ams.model.Definition> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.ams.model.Definition> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the definitions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of definitions.
	*
	* @return the number of definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}