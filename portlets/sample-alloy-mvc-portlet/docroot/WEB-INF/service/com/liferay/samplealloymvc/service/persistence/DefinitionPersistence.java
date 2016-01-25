/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplealloymvc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.samplealloymvc.exception.NoSuchDefinitionException;
import com.liferay.samplealloymvc.model.Definition;

/**
 * The persistence interface for the definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.samplealloymvc.service.persistence.impl.DefinitionPersistenceImpl
 * @see DefinitionUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(Definition definition);

	/**
	* Caches the definitions in the entity cache if it is enabled.
	*
	* @param definitions the definitions
	*/
	public void cacheResult(java.util.List<Definition> definitions);

	/**
	* Creates a new definition with the primary key. Does not add the definition to the database.
	*
	* @param definitionId the primary key for the new definition
	* @return the new definition
	*/
	public Definition create(long definitionId);

	/**
	* Removes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param definitionId the primary key of the definition
	* @return the definition that was removed
	* @throws NoSuchDefinitionException if a definition with the primary key could not be found
	*/
	public Definition remove(long definitionId)
		throws NoSuchDefinitionException;

	public Definition updateImpl(Definition definition);

	/**
	* Returns the definition with the primary key or throws a {@link NoSuchDefinitionException} if it could not be found.
	*
	* @param definitionId the primary key of the definition
	* @return the definition
	* @throws NoSuchDefinitionException if a definition with the primary key could not be found
	*/
	public Definition findByPrimaryKey(long definitionId)
		throws NoSuchDefinitionException;

	/**
	* Returns the definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param definitionId the primary key of the definition
	* @return the definition, or <code>null</code> if a definition with the primary key could not be found
	*/
	public Definition fetchByPrimaryKey(long definitionId);

	@Override
	public java.util.Map<java.io.Serializable, Definition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the definitions.
	*
	* @return the definitions
	*/
	public java.util.List<Definition> findAll();

	/**
	* Returns a range of all the definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of definitions
	* @param end the upper bound of the range of definitions (not inclusive)
	* @return the range of definitions
	*/
	public java.util.List<Definition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of definitions
	* @param end the upper bound of the range of definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of definitions
	*/
	public java.util.List<Definition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Definition> orderByComparator);

	/**
	* Returns an ordered range of all the definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of definitions
	* @param end the upper bound of the range of definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of definitions
	*/
	public java.util.List<Definition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Definition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of definitions.
	*
	* @return the number of definitions
	*/
	public int countAll();
}