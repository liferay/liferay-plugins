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

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;

/**
 * The persistence interface for the kaleo condition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoConditionPersistenceImpl
 * @see KaleoConditionUtil
 * @generated
 */
public interface KaleoConditionPersistence extends BasePersistence<KaleoCondition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoConditionUtil} to access the kaleo condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo conditions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo conditions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo conditions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the first kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the last kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo conditions before and after the current kaleo condition in the ordered set where companyId = &#63;.
	*
	* @param kaleoConditionId the primary key of the current kaleo condition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition[] findByCompanyId_PrevAndNext(
		long kaleoConditionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Removes all the kaleo conditions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo conditions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the first kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the last kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo conditions before and after the current kaleo condition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoConditionId the primary key of the current kaleo condition
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoConditionId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Removes all the kaleo conditions where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo conditions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo condition where kaleoNodeId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchConditionException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition findByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the kaleo condition where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo condition where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByKaleoNodeId(
		long kaleoNodeId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo condition where kaleoNodeId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the kaleo condition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition removeByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the number of kaleo conditions where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the number of matching kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the kaleo condition in the entity cache if it is enabled.
	*
	* @param kaleoCondition the kaleo condition
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition);

	/**
	* Caches the kaleo conditions in the entity cache if it is enabled.
	*
	* @param kaleoConditions the kaleo conditions
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> kaleoConditions);

	/**
	* Creates a new kaleo condition with the primary key. Does not add the kaleo condition to the database.
	*
	* @param kaleoConditionId the primary key for the new kaleo condition
	* @return the new kaleo condition
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition create(
		long kaleoConditionId);

	/**
	* Removes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition remove(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	public com.liferay.portal.workflow.kaleo.model.KaleoCondition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo condition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchConditionException} if it could not be found.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition findByPrimaryKey(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchConditionException;

	/**
	* Returns the kaleo condition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition, or <code>null</code> if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchByPrimaryKey(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo conditions.
	*
	* @return the kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo conditions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo conditions.
	*
	* @return the number of kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}