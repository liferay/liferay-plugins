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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;

import java.util.List;

/**
 * The persistence utility for the kaleo task assignment instance service. This utility wraps {@link KaleoTaskAssignmentInstancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstancePersistence
 * @see KaleoTaskAssignmentInstancePersistenceImpl
 * @generated
 */
public class KaleoTaskAssignmentInstanceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		getPersistence().clearCache(kaleoTaskAssignmentInstance);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoTaskAssignmentInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTaskAssignmentInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTaskAssignmentInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTaskAssignmentInstance update(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTaskAssignmentInstance, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoTaskAssignmentInstance update(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(kaleoTaskAssignmentInstance, merge, serviceContext);
	}

	/**
	* Caches the kaleo task assignment instance in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		getPersistence().cacheResult(kaleoTaskAssignmentInstance);
	}

	/**
	* Caches the kaleo task assignment instances in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignmentInstances the kaleo task assignment instances
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances) {
		getPersistence().cacheResult(kaleoTaskAssignmentInstances);
	}

	/**
	* Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	* @return the new kaleo task assignment instance
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance create(
		long kaleoTaskAssignmentInstanceId) {
		return getPersistence().create(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Removes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance remove(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence().remove(kaleoTaskAssignmentInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskAssignmentInstance, merge);
	}

	/**
	* Returns the kaleo task assignment instance with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException} if it could not be found.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence().findByPrimaryKey(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Returns the kaleo task assignment instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	* @return the kaleo task assignment instance, or <code>null</code> if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Returns all the kaleo task assignment instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo task assignment instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignment instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

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
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

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
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns a range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

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
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoInstanceId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			kaleoInstanceId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @return the matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns a range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId_First(kaleoTaskInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBykaleoTaskInstanceTokenId_First(kaleoTaskInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId_Last(kaleoTaskInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment instance, or <code>null</code> if a matching kaleo task assignment instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBykaleoTaskInstanceTokenId_Last(kaleoTaskInstanceTokenId,
			orderByComparator);
	}

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
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findBykaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			kaleoTaskInstanceTokenId, orderByComparator);
	}

	/**
	* Returns all the kaleo task assignment instances.
	*
	* @return the kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo task assignment instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @return the range of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignment instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignment instances
	* @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignment instances where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the kaleo task assignment instances where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo task assignment instances where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Removes all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63; from the database.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Removes all the kaleo task assignment instances from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo task assignment instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo task assignment instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo task assignment instances where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the number of kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	* @return the number of matching kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns the number of kaleo task assignment instances.
	*
	* @return the number of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskAssignmentInstancePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskAssignmentInstancePersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoTaskAssignmentInstancePersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoTaskAssignmentInstanceUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(
		KaleoTaskAssignmentInstancePersistence persistence) {
	}

	private static KaleoTaskAssignmentInstancePersistence _persistence;
}