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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;

/**
 * The persistence interface for the kaleo task assignment instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstancePersistenceImpl
 * @see KaleoTaskAssignmentInstanceUtil
 * @generated
 */
public interface KaleoTaskAssignmentInstancePersistence extends BasePersistence<KaleoTaskAssignmentInstance> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTaskAssignmentInstanceUtil} to access the kaleo task assignment instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo task assignment instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findBykaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63; from the database.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBykaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countBykaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances where assigneeClassName = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByassigneeClassName(
		java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeClassName the assignee class name
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByassigneeClassName(
		java.lang.String assigneeClassName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeClassName the assignee class name
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByassigneeClassName(
		java.lang.String assigneeClassName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where assigneeClassName = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByassigneeClassName_First(
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where assigneeClassName = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByassigneeClassName_First(
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where assigneeClassName = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByassigneeClassName_Last(
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where assigneeClassName = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByassigneeClassName_Last(
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where assigneeClassName = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByassigneeClassName_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where assigneeClassName = &#63; from the database.
	*
	* @param assigneeClassName the assignee class name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByassigneeClassName(java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where assigneeClassName = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countByassigneeClassName(java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByG_ACPK(
		long groupId, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByG_ACPK(
		long groupId, long assigneeClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByG_ACPK(
		long groupId, long assigneeClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByG_ACPK_First(
		long groupId, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByG_ACPK_First(
		long groupId, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByG_ACPK_Last(
		long groupId, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByG_ACPK_Last(
		long groupId, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByG_ACPK_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long groupId, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where groupId = &#63; and assigneeClassPK = &#63; from the database.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_ACPK(long groupId, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where groupId = &#63; and assigneeClassPK = &#63;.
	*
	* @param groupId the group ID
	* @param assigneeClassPK the assignee class p k
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_ACPK(long groupId, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByACN_ACPK(
		java.lang.String assigneeClassName, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByACN_ACPK(
		java.lang.String assigneeClassName, long assigneeClassPK, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByACN_ACPK(
		java.lang.String assigneeClassName, long assigneeClassPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByACN_ACPK_First(
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the first kaleo task assignment instance in the ordered set where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByACN_ACPK_First(
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByACN_ACPK_Last(
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the last kaleo task assignment instance in the ordered set where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByACN_ACPK_Last(
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByACN_ACPK_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Removes all the kaleo task assignment instances where assigneeClassName = &#63; and assigneeClassPK = &#63; from the database.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByACN_ACPK(java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances where assigneeClassName = &#63; and assigneeClassPK = &#63;.
	*
	* @param assigneeClassName the assignee class name
	* @param assigneeClassPK the assignee class p k
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countByACN_ACPK(java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the kaleo task assignment instance in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance);

	/**
	* Caches the kaleo task assignment instances in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignmentInstances the kaleo task assignment instances
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances);

	/**
	* Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	* @return the new kaleo task assignment instance
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance create(
		long kaleoTaskAssignmentInstanceId);

	/**
	* Removes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance remove(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo task assignment instance with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException} if it could not be found.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;

	/**
	* Returns the kaleo task assignment instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance, or <code>null</code> if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo task assignment instances.
	*
	* @return the kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo task assignment instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo task assignment instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo task assignment instances from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo task assignment instances.
	*
	* @return the number of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}