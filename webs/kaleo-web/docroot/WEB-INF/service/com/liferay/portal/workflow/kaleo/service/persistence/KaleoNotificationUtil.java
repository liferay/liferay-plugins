/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;

import java.util.List;

/**
 * The persistence utility for the kaleo notification service. This utility wraps {@link KaleoNotificationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationPersistence
 * @see KaleoNotificationPersistenceImpl
 * @generated
 */
public class KaleoNotificationUtil {
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
	public static void clearCache(KaleoNotification kaleoNotification) {
		getPersistence().clearCache(kaleoNotification);
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
	public static List<KaleoNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoNotification remove(KaleoNotification kaleoNotification)
		throws SystemException {
		return getPersistence().remove(kaleoNotification);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoNotification update(
		KaleoNotification kaleoNotification, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoNotification, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoNotification update(
		KaleoNotification kaleoNotification, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoNotification, merge, serviceContext);
	}

	/**
	* Caches the kaleo notification in the entity cache if it is enabled.
	*
	* @param kaleoNotification the kaleo notification
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification) {
		getPersistence().cacheResult(kaleoNotification);
	}

	/**
	* Caches the kaleo notifications in the entity cache if it is enabled.
	*
	* @param kaleoNotifications the kaleo notifications
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> kaleoNotifications) {
		getPersistence().cacheResult(kaleoNotifications);
	}

	/**
	* Creates a new kaleo notification with the primary key. Does not add the kaleo notification to the database.
	*
	* @param kaleoNotificationId the primary key for the new kaleo notification
	* @return the new kaleo notification
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification create(
		long kaleoNotificationId) {
		return getPersistence().create(kaleoNotificationId);
	}

	/**
	* Removes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification remove(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence().remove(kaleoNotificationId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoNotification, merge);
	}

	/**
	* Returns the kaleo notification with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNotificationException} if it could not be found.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByPrimaryKey(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence().findByPrimaryKey(kaleoNotificationId);
	}

	/**
	* Returns the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification fetchByPrimaryKey(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoNotificationId);
	}

	/**
	* Returns all the kaleo notifications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo notifications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification[] findByCompanyId_PrevAndNext(
		long kaleoNotificationId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoNotificationId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoNotificationId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Returns all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @return the matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKCN_KCPK_ET(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType);
	}

	/**
	* Returns a range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKCN_KCPK_ET(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findByKCN_KCPK_ET(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByKCN_KCPK_ET_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_ET_First(kaleoClassName, kaleoClassPK,
			executionType, orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification findByKCN_KCPK_ET_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_ET_Last(kaleoClassName, kaleoClassPK,
			executionType, orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification[] findByKCN_KCPK_ET_PrevAndNext(
		long kaleoNotificationId, java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String executionType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_ET_PrevAndNext(kaleoNotificationId,
			kaleoClassName, kaleoClassPK, executionType, orderByComparator);
	}

	/**
	* Returns all the kaleo notifications.
	*
	* @return the kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo notifications where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the kaleo notifications where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKCN_KCPK_ET(java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType);
	}

	/**
	* Removes all the kaleo notifications from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo notifications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @return the number of matching kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKCN_KCPK_ET(java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType);
	}

	/**
	* Returns the number of kaleo notifications.
	*
	* @return the number of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the kaleo notification recipients associated with the kaleo notification.
	*
	* @param pk the primary key of the kaleo notification
	* @return the kaleo notification recipients associated with the kaleo notification
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNotificationRecipients(pk);
	}

	/**
	* Returns a range of all the kaleo notification recipients associated with the kaleo notification.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo notification
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of kaleo notification recipients associated with the kaleo notification
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNotificationRecipients(pk, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notification recipients associated with the kaleo notification.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo notification
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo notification recipients associated with the kaleo notification
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getKaleoNotificationRecipients(pk, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of kaleo notification recipients associated with the kaleo notification.
	*
	* @param pk the primary key of the kaleo notification
	* @return the number of kaleo notification recipients associated with the kaleo notification
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoNotificationRecipientsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNotificationRecipientsSize(pk);
	}

	/**
	* Returns <code>true</code> if the kaleo notification recipient is associated with the kaleo notification.
	*
	* @param pk the primary key of the kaleo notification
	* @param kaleoNotificationRecipientPK the primary key of the kaleo notification recipient
	* @return <code>true</code> if the kaleo notification recipient is associated with the kaleo notification; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsKaleoNotificationRecipient(long pk,
		long kaleoNotificationRecipientPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .containsKaleoNotificationRecipient(pk,
			kaleoNotificationRecipientPK);
	}

	/**
	* Returns <code>true</code> if the kaleo notification has any kaleo notification recipients associated with it.
	*
	* @param pk the primary key of the kaleo notification to check for associations with kaleo notification recipients
	* @return <code>true</code> if the kaleo notification has any kaleo notification recipients associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsKaleoNotificationRecipients(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoNotificationRecipients(pk);
	}

	public static KaleoNotificationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoNotificationPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoNotificationPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoNotificationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(KaleoNotificationPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(KaleoNotificationUtil.class,
			"_persistence");
	}

	private static KaleoNotificationPersistence _persistence;
}