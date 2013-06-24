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

import com.liferay.so.activities.model.SocialActivity;

/**
 * The persistence interface for the social activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityPersistenceImpl
 * @see SocialActivityUtil
 * @generated
 */
public interface SocialActivityPersistence extends BasePersistence<SocialActivity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialActivityUtil} to access the social activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the social activity in the entity cache if it is enabled.
	*
	* @param socialActivity the social activity
	*/
	public void cacheResult(
		com.liferay.so.activities.model.SocialActivity socialActivity);

	/**
	* Caches the social activities in the entity cache if it is enabled.
	*
	* @param socialActivities the social activities
	*/
	public void cacheResult(
		java.util.List<com.liferay.so.activities.model.SocialActivity> socialActivities);

	/**
	* Creates a new social activity with the primary key. Does not add the social activity to the database.
	*
	* @param activityId the primary key for the new social activity
	* @return the new social activity
	*/
	public com.liferay.so.activities.model.SocialActivity create(
		long activityId);

	/**
	* Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity that was removed
	* @throws com.liferay.so.activities.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity remove(
		long activityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivityException;

	public com.liferay.so.activities.model.SocialActivity updateImpl(
		com.liferay.so.activities.model.SocialActivity socialActivity,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activity with the primary key or throws a {@link com.liferay.so.activities.NoSuchActivityException} if it could not be found.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity
	* @throws com.liferay.so.activities.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity findByPrimaryKey(
		long activityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivityException;

	/**
	* Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityId the primary key of the social activity
	* @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity fetchByPrimaryKey(
		long activityId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the social activities where activitySetId = &#63;.
	*
	* @param activitySetId the activity set ID
	* @return the matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivity> findByActivitySetId(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activities where activitySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activitySetId the activity set ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivity> findByActivitySetId(
		long activitySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activities where activitySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param activitySetId the activity set ID
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivity> findByActivitySetId(
		long activitySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first social activity in the ordered set where activitySetId = &#63;.
	*
	* @param activitySetId the activity set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity
	* @throws com.liferay.so.activities.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity findByActivitySetId_First(
		long activitySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivityException;

	/**
	* Returns the first social activity in the ordered set where activitySetId = &#63;.
	*
	* @param activitySetId the activity set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching social activity, or <code>null</code> if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity fetchByActivitySetId_First(
		long activitySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last social activity in the ordered set where activitySetId = &#63;.
	*
	* @param activitySetId the activity set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity
	* @throws com.liferay.so.activities.NoSuchActivityException if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity findByActivitySetId_Last(
		long activitySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivityException;

	/**
	* Returns the last social activity in the ordered set where activitySetId = &#63;.
	*
	* @param activitySetId the activity set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching social activity, or <code>null</code> if a matching social activity could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity fetchByActivitySetId_Last(
		long activitySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the social activities before and after the current social activity in the ordered set where activitySetId = &#63;.
	*
	* @param activityId the primary key of the current social activity
	* @param activitySetId the activity set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next social activity
	* @throws com.liferay.so.activities.NoSuchActivityException if a social activity with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivity[] findByActivitySetId_PrevAndNext(
		long activityId, long activitySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivityException;

	/**
	* Returns all the social activities.
	*
	* @return the social activities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivity> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the social activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @return the range of social activities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivity> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the social activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activities
	* @param end the upper bound of the range of social activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activities
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivity> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the social activities where activitySetId = &#63; from the database.
	*
	* @param activitySetId the activity set ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByActivitySetId(long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the social activities from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activities where activitySetId = &#63;.
	*
	* @param activitySetId the activity set ID
	* @return the number of matching social activities
	* @throws SystemException if a system exception occurred
	*/
	public int countByActivitySetId(long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of social activities.
	*
	* @return the number of social activities
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}