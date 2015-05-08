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
import com.liferay.portal.workflow.kaleo.model.KaleoTask;

/**
 * The persistence interface for the kaleo task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTaskPersistenceImpl
 * @see KaleoTaskUtil
 * @generated
 */
@ProviderType
public interface KaleoTaskPersistence extends BasePersistence<KaleoTask> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoTaskUtil} to access the kaleo task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo tasks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo tasks
	*/
	public java.util.List<KaleoTask> findByCompanyId(long companyId);

	/**
	* Returns a range of all the kaleo tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @return the range of matching kaleo tasks
	*/
	public java.util.List<KaleoTask> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the kaleo tasks where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo tasks
	*/
	public java.util.List<KaleoTask> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Returns the first kaleo task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task
	* @throws NoSuchTaskException if a matching kaleo task could not be found
	*/
	public KaleoTask findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the first kaleo task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	*/
	public KaleoTask fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Returns the last kaleo task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task
	* @throws NoSuchTaskException if a matching kaleo task could not be found
	*/
	public KaleoTask findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the last kaleo task in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	*/
	public KaleoTask fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Returns the kaleo tasks before and after the current kaleo task in the ordered set where companyId = &#63;.
	*
	* @param kaleoTaskId the primary key of the current kaleo task
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task
	* @throws NoSuchTaskException if a kaleo task with the primary key could not be found
	*/
	public KaleoTask[] findByCompanyId_PrevAndNext(long kaleoTaskId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Removes all the kaleo tasks where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of kaleo tasks where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo tasks
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo tasks
	*/
	public java.util.List<KaleoTask> findByKaleoDefinitionId(
		long kaleoDefinitionId);

	/**
	* Returns a range of all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @return the range of matching kaleo tasks
	*/
	public java.util.List<KaleoTask> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end);

	/**
	* Returns an ordered range of all the kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo tasks
	*/
	public java.util.List<KaleoTask> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Returns the first kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task
	* @throws NoSuchTaskException if a matching kaleo task could not be found
	*/
	public KaleoTask findByKaleoDefinitionId_First(long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the first kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	*/
	public KaleoTask fetchByKaleoDefinitionId_First(long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Returns the last kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task
	* @throws NoSuchTaskException if a matching kaleo task could not be found
	*/
	public KaleoTask findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the last kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	*/
	public KaleoTask fetchByKaleoDefinitionId_Last(long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Returns the kaleo tasks before and after the current kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoTaskId the primary key of the current kaleo task
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task
	* @throws NoSuchTaskException if a kaleo task with the primary key could not be found
	*/
	public KaleoTask[] findByKaleoDefinitionId_PrevAndNext(long kaleoTaskId,
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Removes all the kaleo tasks where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns the number of kaleo tasks where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo tasks
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId);

	/**
	* Returns the kaleo task where kaleoNodeId = &#63; or throws a {@link NoSuchTaskException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo task
	* @throws NoSuchTaskException if a matching kaleo task could not be found
	*/
	public KaleoTask findByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the kaleo task where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	*/
	public KaleoTask fetchByKaleoNodeId(long kaleoNodeId);

	/**
	* Returns the kaleo task where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	*/
	public KaleoTask fetchByKaleoNodeId(long kaleoNodeId,
		boolean retrieveFromCache);

	/**
	* Removes the kaleo task where kaleoNodeId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the kaleo task that was removed
	*/
	public KaleoTask removeByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the number of kaleo tasks where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the number of matching kaleo tasks
	*/
	public int countByKaleoNodeId(long kaleoNodeId);

	/**
	* Caches the kaleo task in the entity cache if it is enabled.
	*
	* @param kaleoTask the kaleo task
	*/
	public void cacheResult(KaleoTask kaleoTask);

	/**
	* Caches the kaleo tasks in the entity cache if it is enabled.
	*
	* @param kaleoTasks the kaleo tasks
	*/
	public void cacheResult(java.util.List<KaleoTask> kaleoTasks);

	/**
	* Creates a new kaleo task with the primary key. Does not add the kaleo task to the database.
	*
	* @param kaleoTaskId the primary key for the new kaleo task
	* @return the new kaleo task
	*/
	public KaleoTask create(long kaleoTaskId);

	/**
	* Removes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskId the primary key of the kaleo task
	* @return the kaleo task that was removed
	* @throws NoSuchTaskException if a kaleo task with the primary key could not be found
	*/
	public KaleoTask remove(long kaleoTaskId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	public KaleoTask updateImpl(KaleoTask kaleoTask);

	/**
	* Returns the kaleo task with the primary key or throws a {@link NoSuchTaskException} if it could not be found.
	*
	* @param kaleoTaskId the primary key of the kaleo task
	* @return the kaleo task
	* @throws NoSuchTaskException if a kaleo task with the primary key could not be found
	*/
	public KaleoTask findByPrimaryKey(long kaleoTaskId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTaskException;

	/**
	* Returns the kaleo task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskId the primary key of the kaleo task
	* @return the kaleo task, or <code>null</code> if a kaleo task with the primary key could not be found
	*/
	public KaleoTask fetchByPrimaryKey(long kaleoTaskId);

	@Override
	public java.util.Map<java.io.Serializable, KaleoTask> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kaleo tasks.
	*
	* @return the kaleo tasks
	*/
	public java.util.List<KaleoTask> findAll();

	/**
	* Returns a range of all the kaleo tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @return the range of kaleo tasks
	*/
	public java.util.List<KaleoTask> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kaleo tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo tasks
	*/
	public java.util.List<KaleoTask> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KaleoTask> orderByComparator);

	/**
	* Removes all the kaleo tasks from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kaleo tasks.
	*
	* @return the number of kaleo tasks
	*/
	public int countAll();
}