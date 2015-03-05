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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;

import java.util.List;

/**
 * The persistence utility for the kaleo instance service. This utility wraps {@link KaleoInstancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstancePersistence
 * @see KaleoInstancePersistenceImpl
 * @generated
 */
public class KaleoInstanceUtil {
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
	public static void clearCache(KaleoInstance kaleoInstance) {
		getPersistence().clearCache(kaleoInstance);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoInstance update(KaleoInstance kaleoInstance)
		throws SystemException {
		return getPersistence().update(kaleoInstance);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoInstance update(KaleoInstance kaleoInstance,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoInstance, serviceContext);
	}

	/**
	* Returns all the kaleo instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance[] findByCompanyId_PrevAndNext(
		long kaleoInstanceId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoInstanceId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instances where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo instances where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoInstanceId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo instances where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo instances where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByC_U(
		long companyId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_U(companyId, userId);
	}

	/**
	* Returns a range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByC_U(
		long companyId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_U(companyId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByC_U(
		long companyId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_U(companyId, userId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByC_U_First(
		long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByC_U_First(companyId, userId, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByC_U_First(
		long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_U_First(companyId, userId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByC_U_Last(
		long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByC_U_Last(companyId, userId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByC_U_Last(
		long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_U_Last(companyId, userId, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param companyId the company ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance[] findByC_U_PrevAndNext(
		long kaleoInstanceId, long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByC_U_PrevAndNext(kaleoInstanceId, companyId, userId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instances where companyId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_U(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_U(companyId, userId);
	}

	/**
	* Returns the number of kaleo instances where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the number of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_U(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_U(companyId, userId);
	}

	/**
	* Returns all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @return the matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByKDI_C(
		long kaleoDefinitionId, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKDI_C(kaleoDefinitionId, completed);
	}

	/**
	* Returns a range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByKDI_C(
		long kaleoDefinitionId, boolean completed, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKDI_C(kaleoDefinitionId, completed, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByKDI_C(
		long kaleoDefinitionId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKDI_C(kaleoDefinitionId, completed, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByKDI_C_First(
		long kaleoDefinitionId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByKDI_C_First(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByKDI_C_First(
		long kaleoDefinitionId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKDI_C_First(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByKDI_C_Last(
		long kaleoDefinitionId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByKDI_C_Last(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByKDI_C_Last(
		long kaleoDefinitionId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKDI_C_Last(kaleoDefinitionId, completed,
			orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance[] findByKDI_C_PrevAndNext(
		long kaleoInstanceId, long kaleoDefinitionId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByKDI_C_PrevAndNext(kaleoInstanceId, kaleoDefinitionId,
			completed, orderByComparator);
	}

	/**
	* Removes all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKDI_C(long kaleoDefinitionId, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKDI_C(kaleoDefinitionId, completed);
	}

	/**
	* Returns the number of kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param completed the completed
	* @return the number of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKDI_C(long kaleoDefinitionId, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKDI_C(kaleoDefinitionId, completed);
	}

	/**
	* Returns all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByCN_CPK(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCN_CPK(className, classPK);
	}

	/**
	* Returns a range of all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByCN_CPK(
		java.lang.String className, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCN_CPK(className, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where className = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param classPK the class p k
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByCN_CPK(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCN_CPK(className, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByCN_CPK_First(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByCN_CPK_First(className, classPK, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByCN_CPK_First(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCN_CPK_First(className, classPK, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByCN_CPK_Last(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByCN_CPK_Last(className, classPK, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByCN_CPK_Last(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCN_CPK_Last(className, classPK, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param className the class name
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance[] findByCN_CPK_PrevAndNext(
		long kaleoInstanceId, java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByCN_CPK_PrevAndNext(kaleoInstanceId, className,
			classPK, orderByComparator);
	}

	/**
	* Removes all the kaleo instances where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCN_CPK(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCN_CPK(className, classPK);
	}

	/**
	* Returns the number of kaleo instances where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the number of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCN_CPK(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCN_CPK(className, classPK);
	}

	/**
	* Returns all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @return the matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByC_KDN_KDV_CD(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate);
	}

	/**
	* Returns a range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByC_KDN_KDV_CD(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findByC_KDN_KDV_CD(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByC_KDN_KDV_CD_First(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByC_KDN_KDV_CD_First(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the first kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByC_KDN_KDV_CD_First(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_KDN_KDV_CD_First(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByC_KDN_KDV_CD_Last(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByC_KDN_KDV_CD_Last(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the last kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByC_KDN_KDV_CD_Last(
		long companyId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_KDN_KDV_CD_Last(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, orderByComparator);
	}

	/**
	* Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param kaleoInstanceId the primary key of the current kaleo instance
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance[] findByC_KDN_KDV_CD_PrevAndNext(
		long kaleoInstanceId, long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence()
				   .findByC_KDN_KDV_CD_PrevAndNext(kaleoInstanceId, companyId,
			kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate);
	}

	/**
	* Returns the number of kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionName the kaleo definition name
	* @param kaleoDefinitionVersion the kaleo definition version
	* @param completionDate the completion date
	* @return the number of matching kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_KDN_KDV_CD(long companyId,
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate);
	}

	/**
	* Caches the kaleo instance in the entity cache if it is enabled.
	*
	* @param kaleoInstance the kaleo instance
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		getPersistence().cacheResult(kaleoInstance);
	}

	/**
	* Caches the kaleo instances in the entity cache if it is enabled.
	*
	* @param kaleoInstances the kaleo instances
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> kaleoInstances) {
		getPersistence().cacheResult(kaleoInstances);
	}

	/**
	* Creates a new kaleo instance with the primary key. Does not add the kaleo instance to the database.
	*
	* @param kaleoInstanceId the primary key for the new kaleo instance
	* @return the new kaleo instance
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance create(
		long kaleoInstanceId) {
		return getPersistence().create(kaleoInstanceId);
	}

	/**
	* Removes the kaleo instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance remove(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence().remove(kaleoInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoInstance);
	}

	/**
	* Returns the kaleo instance with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchInstanceException} if it could not be found.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance findByPrimaryKey(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceException {
		return getPersistence().findByPrimaryKey(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance, or <code>null</code> if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchByPrimaryKey(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoInstanceId);
	}

	/**
	* Returns all the kaleo instances.
	*
	* @return the kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo instances from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo instances.
	*
	* @return the number of kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoInstancePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoInstancePersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoInstancePersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoInstanceUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(KaleoInstancePersistence persistence) {
	}

	private static KaleoInstancePersistence _persistence;
}