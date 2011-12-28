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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

/**
 * The persistence interface for the kaleo task instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceTokenPersistenceImpl
 * @see KaleoTaskInstanceTokenUtil
 * @generated
 */
public interface KaleoTaskInstanceTokenPersistence extends BasePersistence<KaleoTaskInstanceToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTaskInstanceTokenUtil} to access the kaleo task instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the kaleo task instance token in the entity cache if it is enabled.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken);

	/**
	* Caches the kaleo task instance tokens in the entity cache if it is enabled.
	*
	* @param kaleoTaskInstanceTokens the kaleo task instance tokens
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> kaleoTaskInstanceTokens);

	/**
	* Creates a new kaleo task instance token with the primary key. Does not add the kaleo task instance token to the database.
	*
	* @param kaleoTaskInstanceTokenId the primary key for the new kaleo task instance token
	* @return the new kaleo task instance token
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken create(
		long kaleoTaskInstanceTokenId);

	/**
	* Removes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken remove(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException} if it could not be found.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token, or <code>null</code> if a kaleo task instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken fetchByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException} if it could not be found.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the matching kaleo task instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken findByKII_KTI(
		long kaleoInstanceId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken fetchByKII_KTI(
		long kaleoInstanceId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken fetchByKII_KTI(
		long kaleoInstanceId, long kaleoTaskId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task instance tokens.
	*
	* @return the kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo task instance tokens where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo task instance tokens where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo task instance tokens where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKII_KTI(long kaleoInstanceId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Removes all the kaleo task instance tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63; and kaleoTaskId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the number of matching kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByKII_KTI(long kaleoInstanceId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task instance tokens.
	*
	* @return the number of kaleo task instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances associated with the kaleo task instance token.
	*
	* @param pk the primary key of the kaleo task instance token
	* @return the kaleo task assignment instances associated with the kaleo task instance token
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances associated with the kaleo task instance token.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo task instance token
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of kaleo task assignment instances associated with the kaleo task instance token
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances associated with the kaleo task instance token.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo task instance token
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task assignment instances associated with the kaleo task instance token
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances associated with the kaleo task instance token.
	*
	* @param pk the primary key of the kaleo task instance token
	* @return the number of kaleo task assignment instances associated with the kaleo task instance token
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoTaskAssignmentInstancesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the kaleo task assignment instance is associated with the kaleo task instance token.
	*
	* @param pk the primary key of the kaleo task instance token
	* @param kaleoTaskAssignmentInstancePK the primary key of the kaleo task assignment instance
	* @return <code>true</code> if the kaleo task assignment instance is associated with the kaleo task instance token; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsKaleoTaskAssignmentInstance(long pk,
		long kaleoTaskAssignmentInstancePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the kaleo task instance token has any kaleo task assignment instances associated with it.
	*
	* @param pk the primary key of the kaleo task instance token to check for associations with kaleo task assignment instances
	* @return <code>true</code> if the kaleo task instance token has any kaleo task assignment instances associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsKaleoTaskAssignmentInstances(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;
}