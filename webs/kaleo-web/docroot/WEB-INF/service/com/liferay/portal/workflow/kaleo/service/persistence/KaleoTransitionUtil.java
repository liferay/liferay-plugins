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
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;

import java.util.List;

/**
 * The persistence utility for the kaleo transition service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTransitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransitionPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoTransitionPersistenceImpl
 * @generated
 */
@ProviderType
public class KaleoTransitionUtil {
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
	public static void clearCache(KaleoTransition kaleoTransition) {
		getPersistence().clearCache(kaleoTransition);
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
	public static List<KaleoTransition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTransition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTransition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTransition update(KaleoTransition kaleoTransition) {
		return getPersistence().update(kaleoTransition);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoTransition update(KaleoTransition kaleoTransition,
		ServiceContext serviceContext) {
		return getPersistence().update(kaleoTransition, serviceContext);
	}

	/**
	* Returns all the kaleo transitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo transitions
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId, int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo transitions before and after the current kaleo transition in the ordered set where companyId = &#63;.
	*
	* @param kaleoTransitionId the primary key of the current kaleo transition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByCompanyId_PrevAndNext(
		long kaleoTransitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTransitionId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo transitions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo transitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo transitions
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo transitions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo transitions
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoTransitionId the primary key of the current kaleo transition
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTransitionId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTransitionId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo transitions where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo transitions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo transitions
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId) {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo transitions where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the matching kaleo transitions
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId) {
		return getPersistence().findByKaleoNodeId(kaleoNodeId);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end) {
		return getPersistence().findByKaleoNodeId(kaleoNodeId, start, end);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .findByKaleoNodeId(kaleoNodeId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoNodeId_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoNodeId_First(kaleoNodeId, orderByComparator);
	}

	/**
	* Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoNodeId_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoNodeId_First(kaleoNodeId, orderByComparator);
	}

	/**
	* Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoNodeId_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoNodeId_Last(kaleoNodeId, orderByComparator);
	}

	/**
	* Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKaleoNodeId_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoNodeId_Last(kaleoNodeId, orderByComparator);
	}

	/**
	* Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoNodeId = &#63;.
	*
	* @param kaleoTransitionId the primary key of the current kaleo transition
	* @param kaleoNodeId the kaleo node ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByKaleoNodeId_PrevAndNext(
		long kaleoTransitionId, long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoNodeId_PrevAndNext(kaleoTransitionId,
			kaleoNodeId, orderByComparator);
	}

	/**
	* Removes all the kaleo transitions where kaleoNodeId = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	*/
	public static void removeByKaleoNodeId(long kaleoNodeId) {
		getPersistence().removeByKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the number of kaleo transitions where kaleoNodeId = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @return the number of matching kaleo transitions
	*/
	public static int countByKaleoNodeId(long kaleoNodeId) {
		return getPersistence().countByKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTransitionException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().findByKNI_N(kaleoNodeId, name);
	}

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_N(
		long kaleoNodeId, java.lang.String name) {
		return getPersistence().fetchByKNI_N(kaleoNodeId, name);
	}

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_N(
		long kaleoNodeId, java.lang.String name, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByKNI_N(kaleoNodeId, name, retrieveFromCache);
	}

	/**
	* Removes the kaleo transition where kaleoNodeId = &#63; and name = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the kaleo transition that was removed
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition removeByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().removeByKNI_N(kaleoNodeId, name);
	}

	/**
	* Returns the number of kaleo transitions where kaleoNodeId = &#63; and name = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param name the name
	* @return the number of matching kaleo transitions
	*/
	public static int countByKNI_N(long kaleoNodeId, java.lang.String name) {
		return getPersistence().countByKNI_N(kaleoNodeId, name);
	}

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTransitionException} if it could not be found.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the matching kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().findByKNI_DT(kaleoNodeId, defaultTransition);
	}

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition) {
		return getPersistence().fetchByKNI_DT(kaleoNodeId, defaultTransition);
	}

	/**
	* Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByKNI_DT(kaleoNodeId, defaultTransition,
			retrieveFromCache);
	}

	/**
	* Removes the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the kaleo transition that was removed
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition removeByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().removeByKNI_DT(kaleoNodeId, defaultTransition);
	}

	/**
	* Returns the number of kaleo transitions where kaleoNodeId = &#63; and defaultTransition = &#63;.
	*
	* @param kaleoNodeId the kaleo node ID
	* @param defaultTransition the default transition
	* @return the number of matching kaleo transitions
	*/
	public static int countByKNI_DT(long kaleoNodeId, boolean defaultTransition) {
		return getPersistence().countByKNI_DT(kaleoNodeId, defaultTransition);
	}

	/**
	* Caches the kaleo transition in the entity cache if it is enabled.
	*
	* @param kaleoTransition the kaleo transition
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition) {
		getPersistence().cacheResult(kaleoTransition);
	}

	/**
	* Caches the kaleo transitions in the entity cache if it is enabled.
	*
	* @param kaleoTransitions the kaleo transitions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> kaleoTransitions) {
		getPersistence().cacheResult(kaleoTransitions);
	}

	/**
	* Creates a new kaleo transition with the primary key. Does not add the kaleo transition to the database.
	*
	* @param kaleoTransitionId the primary key for the new kaleo transition
	* @return the new kaleo transition
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition create(
		long kaleoTransitionId) {
		return getPersistence().create(kaleoTransitionId);
	}

	/**
	* Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition remove(
		long kaleoTransitionId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().remove(kaleoTransitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition) {
		return getPersistence().updateImpl(kaleoTransition);
	}

	/**
	* Returns the kaleo transition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTransitionException} if it could not be found.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException if a kaleo transition with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByPrimaryKey(
		long kaleoTransitionId)
		throws com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().findByPrimaryKey(kaleoTransitionId);
	}

	/**
	* Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTransitionId the primary key of the kaleo transition
	* @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByPrimaryKey(
		long kaleoTransitionId) {
		return getPersistence().fetchByPrimaryKey(kaleoTransitionId);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.portal.workflow.kaleo.model.KaleoTransition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo transitions.
	*
	* @return the kaleo transitions
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll() {
		return getPersistence().findAll();
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTransition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo transitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo transitions.
	*
	* @return the number of kaleo transitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KaleoTransitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTransitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoTransitionPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoTransitionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KaleoTransitionPersistence persistence) {
	}

	private static KaleoTransitionPersistence _persistence;
}