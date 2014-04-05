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
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;

/**
 * The persistence interface for the kaleo transition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransitionPersistenceImpl
 * @see KaleoTransitionUtil
 * @generated
 */
public interface KaleoTransitionPersistence extends BasePersistence<KaleoTransition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTransitionUtil} to access the kaleo transition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo transitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo transitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @return the range of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo transitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the first kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the last kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transitions before and after the current kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param kaleoTransitionId the primary key of the current kaleo transition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByCompanyId_PrevAndNext(
		long kaleoTransitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Removes all the kaleo transitions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo transitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo transitions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @return the range of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoTransitionId the primary key of the current kaleo transition
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTransitionId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Removes all the kaleo transitions where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo transitions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo transitions where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo transitions where kaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoNodeId the kaleo node ID
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @return the range of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo transitions where kaleoNodeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoNodeId the kaleo node ID
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoNodeId_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoNodeId_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoNodeId_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoNodeId_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoTransitionId the primary key of the current kaleo transition
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByKaleoNodeId_PrevAndNext(
		long kaleoTransitionId, long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Removes all the kaleo transitions where kaleoNodeId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo transitions where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the number of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTransitionException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_N(
		long kaleoNodeId, java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo transition where kaleoNodeId = &#63; and name = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the kaleo transition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition removeByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the number of kaleo transitions where kaleoNodeId = &#63; and name = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the number of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKNI_N(long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTransitionException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the kaleo transition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition removeByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the number of kaleo transitions where kaleoNodeId = &#63; and defaultTransition = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the number of matching kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByKNI_DT(long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the kaleo transition in the entity cache if it is enabled.
	*
	* @param kaleoTransition the kaleo transition
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition);

	/**
	* Caches the kaleo transitions in the entity cache if it is enabled.
	*
	* @param kaleoTransitions the kaleo transitions
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> kaleoTransitions);

	/**
	* Creates a new kaleo transition with the primary key. Does not add the kaleo transition to the database.
	*
	* @param kaleoTransitionId the primary key for the new kaleo transition
	* @return the new kaleo transition
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition create(
		long kaleoTransitionId);

	/**
	* Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition remove(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo transition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTransitionException} if it could not be found.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition findByPrimaryKey(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException;

	/**
	* Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByPrimaryKey(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo transitions.
	*
	* @return the kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo transitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @return the range of kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo transitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo transitions
	* @param end the upper bound of the range of kaleo transitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo transitions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo transitions.
	*
	* @return the number of kaleo transitions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}