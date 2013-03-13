/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.so.activities.model.SocialActivitySet;

/**
 * The persistence interface for the social activity set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetPersistenceImpl
 * @see SocialActivitySetUtil
 * @generated
 */
public interface SocialActivitySetPersistence extends BasePersistence<SocialActivitySet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialActivitySetUtil} to access the social activity set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the social activity set in the entity cache if it is enabled.
	*
	* @param socialActivitySet the social activity set
	*/
	public void cacheResult(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet);

	/**
	* Caches the social activity sets in the entity cache if it is enabled.
	*
	* @param socialActivitySets the social activity sets
	*/
	public void cacheResult(
		java.util.List<com.liferay.so.activities.model.SocialActivitySet> socialActivitySets);

	/**
	* Creates a new social activity set with the primary key. Does not add the social activity set to the database.
	*
	* @param activitySetId the primary key for the new social activity set
	* @return the new social activity set
	*/
	public com.liferay.so.activities.model.SocialActivitySet create(
		long activitySetId);

	/**
	* Removes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set that was removed
	* @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet remove(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivitySetException;

	public com.liferay.so.activities.model.SocialActivitySet updateImpl(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity set with the primary key or throws a {@link com.liferay.so.activities.NoSuchActivitySetException} if it could not be found.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set
	* @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet findByPrimaryKey(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivitySetException;

	/**
	* Returns the social activity set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set, or <code>null</code> if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet fetchByPrimaryKey(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activity sets.
	*
	* @return the social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivitySet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivitySet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivitySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the social activity sets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activity sets.
	*
	* @return the number of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}