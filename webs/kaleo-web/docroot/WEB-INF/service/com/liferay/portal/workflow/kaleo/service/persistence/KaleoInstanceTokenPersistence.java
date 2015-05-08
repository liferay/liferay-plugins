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
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;

/**
 * The persistence interface for the kaleo instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoInstanceTokenPersistenceImpl
 * @see KaleoInstanceTokenUtil
 * @generated
 */
@ProviderType
public interface KaleoInstanceTokenPersistence extends BasePersistence<KaleoInstanceToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoInstanceTokenUtil} to access the kaleo instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByCompanyId(long companyId);

	/**
	* Returns a range of all the kaleo instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the kaleo instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of kaleo instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo instance tokens
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId);

	/**
	* Returns a range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Removes all the kaleo instance tokens where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns the number of kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo instance tokens
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId);

	/**
	* Returns a range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByKaleoInstanceId_Last(long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Removes all the kaleo instance tokens where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	*/
	public void removeByKaleoInstanceId(long kaleoInstanceId);

	/**
	* Returns the number of kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo instance tokens
	*/
	public int countByKaleoInstanceId(long kaleoInstanceId);

	/**
	* Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @return the matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId);

	/**
	* Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	*/
	public void removeByC_PKITI(long companyId, long parentKaleoInstanceTokenId);

	/**
	* Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @return the number of matching kaleo instance tokens
	*/
	public int countByC_PKITI(long companyId, long parentKaleoInstanceTokenId);

	/**
	* Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @return the matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate);

	/**
	* Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		int start, int end);

	/**
	* Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken findByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public KaleoInstanceToken fetchByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	*/
	public void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate);

	/**
	* Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @return the number of matching kaleo instance tokens
	*/
	public int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate);

	/**
	* Caches the kaleo instance token in the entity cache if it is enabled.
	*
	* @param kaleoInstanceToken the kaleo instance token
	*/
	public void cacheResult(KaleoInstanceToken kaleoInstanceToken);

	/**
	* Caches the kaleo instance tokens in the entity cache if it is enabled.
	*
	* @param kaleoInstanceTokens the kaleo instance tokens
	*/
	public void cacheResult(
		java.util.List<KaleoInstanceToken> kaleoInstanceTokens);

	/**
	* Creates a new kaleo instance token with the primary key. Does not add the kaleo instance token to the database.
	*
	* @param kaleoInstanceTokenId the primary key for the new kaleo instance token
	* @return the new kaleo instance token
	*/
	public KaleoInstanceToken create(long kaleoInstanceTokenId);

	/**
	* Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token that was removed
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken remove(long kaleoInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	public KaleoInstanceToken updateImpl(KaleoInstanceToken kaleoInstanceToken);

	/**
	* Returns the kaleo instance token with the primary key or throws a {@link NoSuchInstanceTokenException} if it could not be found.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token
	* @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken findByPrimaryKey(long kaleoInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Returns the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	*/
	public KaleoInstanceToken fetchByPrimaryKey(long kaleoInstanceTokenId);

	@Override
	public java.util.Map<java.io.Serializable, KaleoInstanceToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kaleo instance tokens.
	*
	* @return the kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findAll();

	/**
	* Returns a range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo instance tokens
	*/
	public java.util.List<KaleoInstanceToken> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoInstanceToken> orderByComparator);

	/**
	* Removes all the kaleo instance tokens from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kaleo instance tokens.
	*
	* @return the number of kaleo instance tokens
	*/
	public int countAll();
}