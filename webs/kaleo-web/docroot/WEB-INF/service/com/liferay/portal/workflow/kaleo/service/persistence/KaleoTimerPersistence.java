/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;

/**
 * The persistence interface for the kaleo timer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerPersistenceImpl
 * @see KaleoTimerUtil
 * @generated
 */
public interface KaleoTimerPersistence extends BasePersistence<KaleoTimer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTimerUtil} to access the kaleo timer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the kaleo timer in the entity cache if it is enabled.
	*
	* @param kaleoTimer the kaleo timer
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer);

	/**
	* Caches the kaleo timers in the entity cache if it is enabled.
	*
	* @param kaleoTimers the kaleo timers
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> kaleoTimers);

	/**
	* Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	*
	* @param kaleoTimerId the primary key for the new kaleo timer
	* @return the new kaleo timer
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer create(
		long kaleoTimerId);

	/**
	* Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer remove(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo timer with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTimerException} if it could not be found.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer findByPrimaryKey(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTimerId the primary key of the kaleo timer
	* @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer fetchByPrimaryKey(
		long kaleoTimerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo timers where parentKaleoNodeId = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @return the matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByparentKaleoNodeId(
		long parentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo timers where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByparentKaleoNodeId(
		long parentKaleoNodeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo timers where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByparentKaleoNodeId(
		long parentKaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo timer in the ordered set where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer findByparentKaleoNodeId_First(
		long parentKaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns the last kaleo timer in the ordered set where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer findByparentKaleoNodeId_Last(
		long parentKaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns the kaleo timers before and after the current kaleo timer in the ordered set where parentKaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTimerId the primary key of the current kaleo timer
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer[] findByparentKaleoNodeId_PrevAndNext(
		long kaleoTimerId, long parentKaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @return the matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByPKNI_DT(
		long parentKaleoNodeId, boolean defaultTimer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByPKNI_DT(
		long parentKaleoNodeId, boolean defaultTimer, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findByPKNI_DT(
		long parentKaleoNodeId, boolean defaultTimer, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo timer in the ordered set where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer findByPKNI_DT_First(
		long parentKaleoNodeId, boolean defaultTimer,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns the last kaleo timer in the ordered set where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer findByPKNI_DT_Last(
		long parentKaleoNodeId, boolean defaultTimer,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns the kaleo timers before and after the current kaleo timer in the ordered set where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTimerId the primary key of the current kaleo timer
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer[] findByPKNI_DT_PrevAndNext(
		long kaleoTimerId, long parentKaleoNodeId, boolean defaultTimer,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTimerException;

	/**
	* Returns all the kaleo timers.
	*
	* @return the kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @return the range of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo timers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timers
	* @param end the upper bound of the range of kaleo timers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo timers where parentKaleoNodeId = &#63; from the database.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByparentKaleoNodeId(long parentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63; from the database.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPKNI_DT(long parentKaleoNodeId, boolean defaultTimer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo timers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo timers where parentKaleoNodeId = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @return the number of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public int countByparentKaleoNodeId(long parentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo timers where parentKaleoNodeId = &#63; and defaultTimer = &#63;.
	*
	* @param parentKaleoNodeId the parent kaleo node ID
	* @param defaultTimer the default timer
	* @return the number of matching kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public int countByPKNI_DT(long parentKaleoNodeId, boolean defaultTimer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo timers.
	*
	* @return the number of kaleo timers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public KaleoTimer remove(KaleoTimer kaleoTimer) throws SystemException;
}