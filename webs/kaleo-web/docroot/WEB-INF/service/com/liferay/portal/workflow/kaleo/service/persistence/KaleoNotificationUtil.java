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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;

import java.util.List;

/**
 * The persistence utility for the kaleo notification service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoNotificationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoNotificationPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoNotification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoNotification update(KaleoNotification kaleoNotification) {
		return getPersistence().update(kaleoNotification);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoNotification update(
		KaleoNotification kaleoNotification, ServiceContext serviceContext) {
		return getPersistence().update(kaleoNotification, serviceContext);
	}

	/**
	* Returns all the kaleo notifications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo notifications
	*/
	public static List<KaleoNotification> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo notifications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByCompanyId_First(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where companyId = &#63;.
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification[] findByCompanyId_PrevAndNext(
		long kaleoNotificationId, long companyId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoNotificationId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo notifications where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo notifications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo notifications
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationId, long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoNotificationId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo notifications where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo notifications where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo notifications
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId) {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK) {
		return getPersistence().findByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns a range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end) {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_First(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_First(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_Last(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_Last(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification[] findByKCN_KCPK_PrevAndNext(
		long kaleoNotificationId, java.lang.String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_PrevAndNext(kaleoNotificationId,
			kaleoClassName, kaleoClassPK, orderByComparator);
	}

	/**
	* Removes all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	*/
	public static void removeByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK) {
		getPersistence().removeByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns the number of kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the number of matching kaleo notifications
	*/
	public static int countByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK) {
		return getPersistence().countByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @return the matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKCN_KCPK_ET(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType) {
		return getPersistence()
				   .findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType);
	}

	/**
	* Returns a range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKCN_KCPK_ET(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType, int start, int end) {
		return getPersistence()
				   .findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notifications
	*/
	public static List<KaleoNotification> findByKCN_KCPK_ET(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByKCN_KCPK_ET_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_ET_First(kaleoClassName, kaleoClassPK,
			executionType, orderByComparator);
	}

	/**
	* Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByKCN_KCPK_ET_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_ET_First(kaleoClassName, kaleoClassPK,
			executionType, orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification
	* @throws NoSuchNotificationException if a matching kaleo notification could not be found
	*/
	public static KaleoNotification findByKCN_KCPK_ET_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_ET_Last(kaleoClassName, kaleoClassPK,
			executionType, orderByComparator);
	}

	/**
	* Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	*/
	public static KaleoNotification fetchByKCN_KCPK_ET_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence()
				   .fetchByKCN_KCPK_ET_Last(kaleoClassName, kaleoClassPK,
			executionType, orderByComparator);
	}

	/**
	* Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoNotificationId the primary key of the current kaleo notification
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification
	* @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification[] findByKCN_KCPK_ET_PrevAndNext(
		long kaleoNotificationId, java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String executionType,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence()
				   .findByKCN_KCPK_ET_PrevAndNext(kaleoNotificationId,
			kaleoClassName, kaleoClassPK, executionType, orderByComparator);
	}

	/**
	* Removes all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	*/
	public static void removeByKCN_KCPK_ET(java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String executionType) {
		getPersistence()
			.removeByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType);
	}

	/**
	* Returns the number of kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param executionType the execution type
	* @return the number of matching kaleo notifications
	*/
	public static int countByKCN_KCPK_ET(java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String executionType) {
		return getPersistence()
				   .countByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
			executionType);
	}

	/**
	* Caches the kaleo notification in the entity cache if it is enabled.
	*
	* @param kaleoNotification the kaleo notification
	*/
	public static void cacheResult(KaleoNotification kaleoNotification) {
		getPersistence().cacheResult(kaleoNotification);
	}

	/**
	* Caches the kaleo notifications in the entity cache if it is enabled.
	*
	* @param kaleoNotifications the kaleo notifications
	*/
	public static void cacheResult(List<KaleoNotification> kaleoNotifications) {
		getPersistence().cacheResult(kaleoNotifications);
	}

	/**
	* Creates a new kaleo notification with the primary key. Does not add the kaleo notification to the database.
	*
	* @param kaleoNotificationId the primary key for the new kaleo notification
	* @return the new kaleo notification
	*/
	public static KaleoNotification create(long kaleoNotificationId) {
		return getPersistence().create(kaleoNotificationId);
	}

	/**
	* Removes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification that was removed
	* @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification remove(long kaleoNotificationId)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence().remove(kaleoNotificationId);
	}

	public static KaleoNotification updateImpl(
		KaleoNotification kaleoNotification) {
		return getPersistence().updateImpl(kaleoNotification);
	}

	/**
	* Returns the kaleo notification with the primary key or throws a {@link NoSuchNotificationException} if it could not be found.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification
	* @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification findByPrimaryKey(long kaleoNotificationId)
		throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException {
		return getPersistence().findByPrimaryKey(kaleoNotificationId);
	}

	/**
	* Returns the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	*/
	public static KaleoNotification fetchByPrimaryKey(long kaleoNotificationId) {
		return getPersistence().fetchByPrimaryKey(kaleoNotificationId);
	}

	public static java.util.Map<java.io.Serializable, KaleoNotification> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo notifications.
	*
	* @return the kaleo notifications
	*/
	public static List<KaleoNotification> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of kaleo notifications
	*/
	public static List<KaleoNotification> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo notifications
	*/
	public static List<KaleoNotification> findAll(int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo notifications from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo notifications.
	*
	* @return the number of kaleo notifications
	*/
	public static int countAll() {
		return getPersistence().countAll();
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

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KaleoNotificationPersistence persistence) {
	}

	private static KaleoNotificationPersistence _persistence;
}