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
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;

import java.util.List;

/**
 * The persistence utility for the kaleo instance token service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoInstanceTokenPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoInstanceTokenPersistenceImpl
 * @generated
 */
@ProviderType
public class KaleoInstanceTokenUtil {
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
	public static void clearCache(KaleoInstanceToken kaleoInstanceToken) {
		getPersistence().clearCache(kaleoInstanceToken);
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
	public static List<KaleoInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoInstanceToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoInstanceToken update(
		KaleoInstanceToken kaleoInstanceToken) {
		return getPersistence().update(kaleoInstanceToken);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoInstanceToken update(
		KaleoInstanceToken kaleoInstanceToken, ServiceContext serviceContext) {
		return getPersistence().update(kaleoInstanceToken, serviceContext);
	}

	/**
	* Returns all the kaleo instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId, int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoInstanceTokenId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo instance tokens where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo instance tokens
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoInstanceTokenId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo instance tokens where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo instance tokens
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId) {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId) {
		return getPersistence().findByKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns a range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param kaleoInstanceId the kaleo instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByKaleoInstanceId_PrevAndNext(kaleoInstanceTokenId,
			kaleoInstanceId, orderByComparator);
	}

	/**
	* Removes all the kaleo instance tokens where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance ID
	*/
	public static void removeByKaleoInstanceId(long kaleoInstanceId) {
		getPersistence().removeByKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the number of kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance ID
	* @return the number of matching kaleo instance tokens
	*/
	public static int countByKaleoInstanceId(long kaleoInstanceId) {
		return getPersistence().countByKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @return the matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId) {
		return getPersistence()
				   .findByC_PKITI(companyId, parentKaleoInstanceTokenId);
	}

	/**
	* Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId, int start, int end) {
		return getPersistence()
				   .findByC_PKITI(companyId, parentKaleoInstanceTokenId, start,
			end);
	}

	/**
	* Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .findByC_PKITI(companyId, parentKaleoInstanceTokenId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_First(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_First(companyId, parentKaleoInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByC_PKITI_First(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByC_PKITI_First(companyId, parentKaleoInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_Last(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_Last(companyId, parentKaleoInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByC_PKITI_Last(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByC_PKITI_Last(companyId, parentKaleoInstanceTokenId,
			orderByComparator);
	}

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_PrevAndNext(kaleoInstanceTokenId, companyId,
			parentKaleoInstanceTokenId, orderByComparator);
	}

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	*/
	public static void removeByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId) {
		getPersistence().removeByC_PKITI(companyId, parentKaleoInstanceTokenId);
	}

	/**
	* Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @return the number of matching kaleo instance tokens
	*/
	public static int countByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId) {
		return getPersistence()
				   .countByC_PKITI(companyId, parentKaleoInstanceTokenId);
	}

	/**
	* Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @return the matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate) {
		return getPersistence()
				   .findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate);
	}

	/**
	* Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate, int start, int end) {
		return getPersistence()
				   .findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_CD_First(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_CD_First(companyId,
			parentKaleoInstanceTokenId, completionDate, orderByComparator);
	}

	/**
	* Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByC_PKITI_CD_First(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByC_PKITI_CD_First(companyId,
			parentKaleoInstanceTokenId, completionDate, orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_CD_Last(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_CD_Last(companyId,
			parentKaleoInstanceTokenId, completionDate, orderByComparator);
	}

	/**
	* Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByC_PKITI_CD_Last(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence()
				   .fetchByC_PKITI_CD_Last(companyId,
			parentKaleoInstanceTokenId, completionDate, orderByComparator);
	}

	/**
	* Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence()
				   .findByC_PKITI_CD_PrevAndNext(kaleoInstanceTokenId,
			companyId, parentKaleoInstanceTokenId, completionDate,
			orderByComparator);
	}

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	*/
	public static void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate) {
		getPersistence()
			.removeByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate);
	}

	/**
	* Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company ID
	* @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	* @param completionDate the completion date
	* @return the number of matching kaleo instance tokens
	*/
	public static int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate) {
		return getPersistence()
				   .countByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate);
	}

	/**
	* Caches the kaleo instance token in the entity cache if it is enabled.
	*
	* @param kaleoInstanceToken the kaleo instance token
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken) {
		getPersistence().cacheResult(kaleoInstanceToken);
	}

	/**
	* Caches the kaleo instance tokens in the entity cache if it is enabled.
	*
	* @param kaleoInstanceTokens the kaleo instance tokens
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> kaleoInstanceTokens) {
		getPersistence().cacheResult(kaleoInstanceTokens);
	}

	/**
	* Creates a new kaleo instance token with the primary key. Does not add the kaleo instance token to the database.
	*
	* @param kaleoInstanceTokenId the primary key for the new kaleo instance token
	* @return the new kaleo instance token
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken create(
		long kaleoInstanceTokenId) {
		return getPersistence().create(kaleoInstanceTokenId);
	}

	/**
	* Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken remove(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence().remove(kaleoInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken) {
		return getPersistence().updateImpl(kaleoInstanceToken);
	}

	/**
	* Returns the kaleo instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException} if it could not be found.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByPrimaryKey(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException {
		return getPersistence().findByPrimaryKey(kaleoInstanceTokenId);
	}

	/**
	* Returns the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByPrimaryKey(
		long kaleoInstanceTokenId) {
		return getPersistence().fetchByPrimaryKey(kaleoInstanceTokenId);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo instance tokens.
	*
	* @return the kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo instance tokens
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo instance tokens from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo instance tokens.
	*
	* @return the number of kaleo instance tokens
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KaleoInstanceTokenPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoInstanceTokenPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoInstanceTokenPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoInstanceTokenUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KaleoInstanceTokenPersistence persistence) {
	}

	private static KaleoInstanceTokenPersistence _persistence;
}