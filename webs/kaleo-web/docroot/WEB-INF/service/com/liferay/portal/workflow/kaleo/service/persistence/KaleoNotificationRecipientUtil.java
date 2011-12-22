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
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;

import java.util.List;

/**
 * The persistence utility for the kaleo notification recipient service. This utility wraps {@link KaleoNotificationRecipientPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipientPersistence
 * @see KaleoNotificationRecipientPersistenceImpl
 * @generated
 */
public class KaleoNotificationRecipientUtil {
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
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		getPersistence().clearCache(kaleoNotificationRecipient);
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
	public static List<KaleoNotificationRecipient> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoNotificationRecipient> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoNotificationRecipient> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoNotificationRecipient update(
		KaleoNotificationRecipient kaleoNotificationRecipient, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoNotificationRecipient, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoNotificationRecipient update(
		KaleoNotificationRecipient kaleoNotificationRecipient, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(kaleoNotificationRecipient, merge, serviceContext);
	}

	/**
	* Caches the kaleo notification recipient in the entity cache if it is enabled.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient) {
		getPersistence().cacheResult(kaleoNotificationRecipient);
	}

	/**
	* Caches the kaleo notification recipients in the entity cache if it is enabled.
	*
	* @param kaleoNotificationRecipients the kaleo notification recipients
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> kaleoNotificationRecipients) {
		getPersistence().cacheResult(kaleoNotificationRecipients);
	}

	/**
	* Creates a new kaleo notification recipient with the primary key. Does not add the kaleo notification recipient to the database.
	*
	* @param kaleoNotificationRecipientId the primary key for the new kaleo notification recipient
	* @return the new kaleo notification recipient
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient create(
		long kaleoNotificationRecipientId) {
		return getPersistence().create(kaleoNotificationRecipientId);
	}

	/**
	* Removes the kaleo notification recipient with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	* @return the kaleo notification recipient that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient remove(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence().remove(kaleoNotificationRecipientId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoNotificationRecipient, merge);
	}

	/**
	* Returns the kaleo notification recipient with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException} if it could not be found.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	* @return the kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByPrimaryKey(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence().findByPrimaryKey(kaleoNotificationRecipientId);
	}

	/**
	* Returns the kaleo notification recipient with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	* @return the kaleo notification recipient, or <code>null</code> if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient fetchByPrimaryKey(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoNotificationRecipientId);
	}

	/**
	* Returns all the kaleo notification recipients where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo notification recipients where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @return the range of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notification recipients where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo notification recipient in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo notification recipient in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo notification recipients before and after the current kaleo notification recipient in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationRecipientId the primary key of the current kaleo notification recipient
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient[] findByCompanyId_PrevAndNext(
		long kaleoNotificationRecipientId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoNotificationRecipientId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the kaleo notification recipients where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo notification recipients where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @return the range of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notification recipients where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo notification recipients before and after the current kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationRecipientId the primary key of the current kaleo notification recipient
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoNotificationRecipientId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Returns all the kaleo notification recipients where kaleoNotificationId = &#63;.
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @return the matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoNotificationId(kaleoNotificationId);
	}

	/**
	* Returns a range of all the kaleo notification recipients where kaleoNotificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @return the range of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoNotificationId(kaleoNotificationId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notification recipients where kaleoNotificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoNotificationId(kaleoNotificationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByKaleoNotificationId_First(
		long kaleoNotificationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoNotificationId_First(kaleoNotificationId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient findByKaleoNotificationId_Last(
		long kaleoNotificationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoNotificationId_Last(kaleoNotificationId,
			orderByComparator);
	}

	/**
	* Returns the kaleo notification recipients before and after the current kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNotificationRecipientId the primary key of the current kaleo notification recipient
	* @param kaleoNotificationId the kaleo notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo notification recipient
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient[] findByKaleoNotificationId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoNotificationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException {
		return getPersistence()
				   .findByKaleoNotificationId_PrevAndNext(kaleoNotificationRecipientId,
			kaleoNotificationId, orderByComparator);
	}

	/**
	* Returns all the kaleo notification recipients.
	*
	* @return the kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo notification recipients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @return the range of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo notification recipients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notification recipients
	* @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo notification recipients where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the kaleo notification recipients where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo notification recipients where kaleoNotificationId = &#63; from the database.
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoNotificationId(long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoNotificationId(kaleoNotificationId);
	}

	/**
	* Removes all the kaleo notification recipients from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo notification recipients where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo notification recipients where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo notification recipients where kaleoNotificationId = &#63;.
	*
	* @param kaleoNotificationId the kaleo notification ID
	* @return the number of matching kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoNotificationId(long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoNotificationId(kaleoNotificationId);
	}

	/**
	* Returns the number of kaleo notification recipients.
	*
	* @return the number of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoNotificationRecipientPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoNotificationRecipientPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoNotificationRecipientPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoNotificationRecipientUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(
		KaleoNotificationRecipientPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(KaleoNotificationRecipientUtil.class,
			"_persistence");
	}

	private static KaleoNotificationRecipientPersistence _persistence;
}