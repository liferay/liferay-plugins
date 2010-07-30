/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.model.KaleoTask;

/**
 * The persistence interface for the kaleo task service.
 *
 * <p>
 * Never modify this interface directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskPersistenceImpl
 * @see KaleoTaskUtil
 * @generated
 */
public interface KaleoTaskPersistence extends BasePersistence<KaleoTask> {
	/**
	* Caches the kaleo task in the entity cache if it is enabled.
	*
	* @param kaleoTask the kaleo task to cache
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask);

	/**
	* Caches the kaleo tasks in the entity cache if it is enabled.
	*
	* @param kaleoTasks the kaleo tasks to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> kaleoTasks);

	/**
	* Creates a new kaleo task with the primary key.
	*
	* @param kaleoTaskId the primary key for the new kaleo task
	* @return the new kaleo task
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask create(
		long kaleoTaskId);

	/**
	* Removes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskId the primary key of the kaleo task to remove
	* @return the kaleo task that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask remove(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTask updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the kaleo task with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskException} if it could not be found.
	*
	* @param kaleoTaskId the primary key of the kaleo task to find
	* @return the kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask findByPrimaryKey(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the kaleo task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskId the primary key of the kaleo task to find
	* @return the kaleo task, or <code>null</code> if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask fetchByPrimaryKey(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the kaleo tasks where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @return the range of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo task in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the last kaleo task in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the kaleo tasks before and after the current kaleo task in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the primary key of the current kaleo task
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask[] findByCompanyId_PrevAndNext(
		long kaleoTaskId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @return the matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @return the range of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the last kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the kaleo tasks before and after the current kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskId the primary key of the current kaleo task
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the kaleo task where kaleoNodeId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @return the matching kaleo task
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask findByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Finds the kaleo task where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @return the matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask fetchByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the kaleo task where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @return the matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTask fetchByKaleoNodeId(
		long kaleoNodeId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the kaleo tasks.
	*
	* @return the kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @return the range of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo tasks where companyId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo tasks where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo task where kaleoNodeId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Removes all the kaleo tasks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo tasks where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the number of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @return the number of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo tasks where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @return the number of matching kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo tasks.
	*
	* @return the number of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets all the kaleo task assignments associated with the kaleo task.
	*
	* @param pk the primary key of the kaleo task to get the associated kaleo task assignments for
	* @return the kaleo task assignments associated with the kaleo task
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets a range of all the kaleo task assignments associated with the kaleo task.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo task to get the associated kaleo task assignments for
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @return the range of kaleo task assignments associated with the kaleo task
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets an ordered range of all the kaleo task assignments associated with the kaleo task.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo task to get the associated kaleo task assignments for
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of kaleo task assignments associated with the kaleo task
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of kaleo task assignments associated with the kaleo task.
	*
	* @param pk the primary key of the kaleo task to get the number of associated kaleo task assignments for
	* @return the number of kaleo task assignments associated with the kaleo task
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoTaskAssignmentsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines whether the kaleo task assignment is associated with the kaleo task.
	*
	* @param pk the primary key of the kaleo task
	* @param kaleoTaskAssignmentPK the primary key of the kaleo task assignment
	* @return whether the kaleo task assignment is associated with the kaleo task
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsKaleoTaskAssignment(long pk,
		long kaleoTaskAssignmentPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines whether the kaleo task has any kaleo task assignments associated with it.
	*
	* @param pk the primary key of the kaleo task to check for associations with kaleo task assignments
	* @return whether the kaleo task has any kaleo task assignments associated with it
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsKaleoTaskAssignments(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;
}