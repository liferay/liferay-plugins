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

package com.liferay.portal.workflow.kaleo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;

/**
 * The persistence interface for the kaleo timer instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTimerInstanceTokenPersistenceImpl
 * @see KaleoTimerInstanceTokenUtil
 * @generated
 */
@ProviderType
public interface KaleoTimerInstanceTokenPersistence extends BasePersistence<KaleoTimerInstanceToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTimerInstanceTokenUtil} to access the kaleo timer instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId);

	/**
	* Returns a range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @return the range of matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the first kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the last kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	*/
	public KaleoTimerInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Removes all the kaleo timer instance tokens where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	*/
	public void removeByKaleoInstanceId(long kaleoInstanceId);

	/**
	* Returns the number of kaleo timer instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo timer instance tokens
	*/
	public int countByKaleoInstanceId(long kaleoInstanceId);

	/**
	* Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or throws a {@link NoSuchTimerInstanceTokenException} if it could not be found.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param kaleoTimerId the kaleo timer ID
	* @return the matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param kaleoTimerId the kaleo timer ID
	* @return the matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId);

	/**
	* Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param kaleoTimerId the kaleo timer ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId, boolean retrieveFromCache);

	/**
	* Removes the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; from the database.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param kaleoTimerId the kaleo timer ID
	* @return the kaleo timer instance token that was removed
	*/
	public KaleoTimerInstanceToken removeByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param kaleoTimerId the kaleo timer ID
	* @return the number of matching kaleo timer instance tokens
	*/
	public int countByKITI_KTI(long kaleoInstanceTokenId, long kaleoTimerId);

	/**
	* Returns all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @return the matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed);

	/**
	* Returns a range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @return the range of matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end);

	/**
	* Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKITI_C_First(
		long kaleoInstanceTokenId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKITI_C_First(
		long kaleoInstanceTokenId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKITI_C_Last(
		long kaleoInstanceTokenId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKITI_C_Last(
		long kaleoInstanceTokenId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	*/
	public KaleoTimerInstanceToken[] findByKITI_C_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceTokenId,
		boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Removes all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; from the database.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	*/
	public void removeByKITI_C(long kaleoInstanceTokenId, boolean completed);

	/**
	* Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @return the number of matching kaleo timer instance tokens
	*/
	public int countByKITI_C(long kaleoInstanceTokenId, boolean completed);

	/**
	* Returns all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @return the matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking);

	/**
	* Returns a range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @return the range of matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end);

	/**
	* Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKITI_C_B_First(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKITI_C_B_First(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken findByKITI_C_B_Last(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	*/
	public KaleoTimerInstanceToken fetchByKITI_C_B_Last(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	*/
	public KaleoTimerInstanceToken[] findByKITI_C_B_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceTokenId,
		boolean completed, boolean blocking,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Removes all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63; from the database.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	*/
	public void removeByKITI_C_B(long kaleoInstanceTokenId, boolean completed,
		boolean blocking);

	/**
	* Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID
	* @param completed the completed
	* @param blocking the blocking
	* @return the number of matching kaleo timer instance tokens
	*/
	public int countByKITI_C_B(long kaleoInstanceTokenId, boolean completed,
		boolean blocking);

	/**
	* Caches the kaleo timer instance token in the entity cache if it is enabled.
	*
	* @param kaleoTimerInstanceToken the kaleo timer instance token
	*/
	public void cacheResult(KaleoTimerInstanceToken kaleoTimerInstanceToken);

	/**
	* Caches the kaleo timer instance tokens in the entity cache if it is enabled.
	*
	* @param kaleoTimerInstanceTokens the kaleo timer instance tokens
	*/
	public void cacheResult(
		java.util.List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens);

	/**
	* Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	*
	* @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	* @return the new kaleo timer instance token
	*/
	public KaleoTimerInstanceToken create(long kaleoTimerInstanceTokenId);

	/**
	* Removes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token that was removed
	* @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	*/
	public KaleoTimerInstanceToken remove(long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	public KaleoTimerInstanceToken updateImpl(
		KaleoTimerInstanceToken kaleoTimerInstanceToken);

	/**
	* Returns the kaleo timer instance token with the primary key or throws a {@link NoSuchTimerInstanceTokenException} if it could not be found.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token
	* @throws NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	*/
	public KaleoTimerInstanceToken findByPrimaryKey(
		long kaleoTimerInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;

	/**
	* Returns the kaleo timer instance token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	* @return the kaleo timer instance token, or <code>null</code> if a kaleo timer instance token with the primary key could not be found
	*/
	public KaleoTimerInstanceToken fetchByPrimaryKey(
		long kaleoTimerInstanceTokenId);

	@Override
	public java.util.Map<java.io.Serializable, KaleoTimerInstanceToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kaleo timer instance tokens.
	*
	* @return the kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findAll();

	/**
	* Returns a range of all the kaleo timer instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @return the range of kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kaleo timer instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo timer instance tokens
	* @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo timer instance tokens
	*/
	public java.util.List<KaleoTimerInstanceToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTimerInstanceToken> orderByComparator);

	/**
	* Removes all the kaleo timer instance tokens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kaleo timer instance tokens.
	*
	* @return the number of kaleo timer instance tokens
	*/
	public int countAll();
}