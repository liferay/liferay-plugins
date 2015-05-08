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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

/**
 * The persistence interface for the kaleo task instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTaskInstanceTokenPersistenceImpl
 * @see KaleoTaskInstanceTokenUtil
 * @generated
 */
@ProviderType
public interface KaleoTaskInstanceTokenPersistence extends BasePersistence<KaleoTaskInstanceToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTaskInstanceTokenUtil} to access the kaleo task instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo task instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByCompanyId(
		long companyId);

	/**
	* Returns a range of all the kaleo task instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByCompanyId(
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo task instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the first kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where companyId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Removes all the kaleo task instance tokens where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of kaleo task instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo task instance tokens
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId);

	/**
	* Returns a range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the first kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Removes all the kaleo task instance tokens where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns the number of kaleo task instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo task instance tokens
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId);

	/**
	* Returns a range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the first kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Removes all the kaleo task instance tokens where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	*/
	public void removeByKaleoInstanceId(long kaleoInstanceId);

	/**
	* Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo task instance tokens
	*/
	public int countByKaleoInstanceId(long kaleoInstanceId);

	/**
	* Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or throws a {@link NoSuchTaskInstanceTokenException} if it could not be found.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId);

	/**
	* Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId, boolean retrieveFromCache);

	/**
	* Removes the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the kaleo task instance token that was removed
	*/
	public KaleoTaskInstanceToken removeByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63; and kaleoTaskId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param kaleoTaskId the kaleo task ID
	* @return the number of matching kaleo task instance tokens
	*/
	public int countByKII_KTI(long kaleoInstanceId, long kaleoTaskId);

	/**
	* Returns all the kaleo task instance tokens where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByCN_CPK(
		java.lang.String className, long classPK);

	/**
	* Returns a range of all the kaleo task instance tokens where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByCN_CPK(
		java.lang.String className, long classPK, int start, int end);

	/**
	* Returns an ordered range of all the kaleo task instance tokens where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findByCN_CPK(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo task instance token in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByCN_CPK_First(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the first kaleo task instance token in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByCN_CPK_First(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo task instance token in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken findByCN_CPK_Last(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the last kaleo task instance token in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	*/
	public KaleoTaskInstanceToken fetchByCN_CPK_Last(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken[] findByCN_CPK_PrevAndNext(
		long kaleoTaskInstanceTokenId, java.lang.String className,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Removes all the kaleo task instance tokens where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class p k
	*/
	public void removeByCN_CPK(java.lang.String className, long classPK);

	/**
	* Returns the number of kaleo task instance tokens where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the number of matching kaleo task instance tokens
	*/
	public int countByCN_CPK(java.lang.String className, long classPK);

	/**
	* Caches the kaleo task instance token in the entity cache if it is enabled.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	*/
	public void cacheResult(KaleoTaskInstanceToken kaleoTaskInstanceToken);

	/**
	* Caches the kaleo task instance tokens in the entity cache if it is enabled.
	*
	* @param kaleoTaskInstanceTokens the kaleo task instance tokens
	*/
	public void cacheResult(
		java.util.List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens);

	/**
	* Creates a new kaleo task instance token with the primary key. Does not add the kaleo task instance token to the database.
	*
	* @param kaleoTaskInstanceTokenId the primary key for the new kaleo task instance token
	* @return the new kaleo task instance token
	*/
	public KaleoTaskInstanceToken create(long kaleoTaskInstanceTokenId);

	/**
	* Removes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token that was removed
	* @throws NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken remove(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	public KaleoTaskInstanceToken updateImpl(
		KaleoTaskInstanceToken kaleoTaskInstanceToken);

	/**
	* Returns the kaleo task instance token with the primary key or throws a {@link NoSuchTaskInstanceTokenException} if it could not be found.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token
	* @throws NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken findByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;

	/**
	* Returns the kaleo task instance token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token, or <code>null</code> if a kaleo task instance token with the primary key could not be found
	*/
	public KaleoTaskInstanceToken fetchByPrimaryKey(
		long kaleoTaskInstanceTokenId);

	@Override
	public java.util.Map<java.io.Serializable, KaleoTaskInstanceToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kaleo task instance tokens.
	*
	* @return the kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findAll();

	/**
	* Returns a range of all the kaleo task instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kaleo task instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task instance tokens
	*/
	public java.util.List<KaleoTaskInstanceToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTaskInstanceToken> orderByComparator);

	/**
	* Removes all the kaleo task instance tokens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kaleo task instance tokens.
	*
	* @return the number of kaleo task instance tokens
	*/
	public int countAll();
}