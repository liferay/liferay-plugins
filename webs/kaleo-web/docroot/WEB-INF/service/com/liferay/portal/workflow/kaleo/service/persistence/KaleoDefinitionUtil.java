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
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

import java.util.List;

/**
 * The persistence utility for the kaleo definition service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class KaleoDefinitionUtil {
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
	public static void clearCache(KaleoDefinition kaleoDefinition) {
		getPersistence().clearCache(kaleoDefinition);
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
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoDefinition update(KaleoDefinition kaleoDefinition) {
		return getPersistence().update(kaleoDefinition);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoDefinition update(KaleoDefinition kaleoDefinition,
		ServiceContext serviceContext) {
		return getPersistence().update(kaleoDefinition, serviceContext);
	}

	/**
	* Returns all the kaleo definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByCompanyId_First(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDefinitionId, long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoDefinitionId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo definitions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo definitions
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_N(long companyId,
		java.lang.String name) {
		return getPersistence().findByC_N(companyId, name);
	}

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_N(long companyId,
		java.lang.String name, int start, int end) {
		return getPersistence().findByC_N(companyId, name, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_N(long companyId,
		java.lang.String name, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .findByC_N(companyId, name, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_N_First(long companyId,
		java.lang.String name,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_N_First(long companyId,
		java.lang.String name,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_First(companyId, name, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_N_Last(long companyId,
		java.lang.String name,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_Last(companyId, name, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_N_Last(long companyId,
		java.lang.String name,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_Last(companyId, name, orderByComparator);
	}

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition[] findByC_N_PrevAndNext(
		long kaleoDefinitionId, long companyId, java.lang.String name,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_PrevAndNext(kaleoDefinitionId, companyId, name,
			orderByComparator);
	}

	/**
	* Removes all the kaleo definitions where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	*/
	public static void removeByC_N(long companyId, java.lang.String name) {
		getPersistence().removeByC_N(companyId, name);
	}

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching kaleo definitions
	*/
	public static int countByC_N(long companyId, java.lang.String name) {
		return getPersistence().countByC_N(companyId, name);
	}

	/**
	* Returns all the kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_A(long companyId, boolean active) {
		return getPersistence().findByC_A(companyId, active);
	}

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_A(long companyId,
		boolean active, int start, int end) {
		return getPersistence().findByC_A(companyId, active, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_A(long companyId,
		boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .findByC_A(companyId, active, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_A_First(long companyId,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_A_First(companyId, active, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_A_First(long companyId,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByC_A_First(companyId, active, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_A_Last(long companyId,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_A_Last(companyId, active, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_A_Last(long companyId,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByC_A_Last(companyId, active, orderByComparator);
	}

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition[] findByC_A_PrevAndNext(
		long kaleoDefinitionId, long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_A_PrevAndNext(kaleoDefinitionId, companyId, active,
			orderByComparator);
	}

	/**
	* Removes all the kaleo definitions where companyId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param active the active
	*/
	public static void removeByC_A(long companyId, boolean active) {
		getPersistence().removeByC_A(companyId, active);
	}

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching kaleo definitions
	*/
	public static int countByC_A(long companyId, boolean active) {
		return getPersistence().countByC_A(companyId, active);
	}

	/**
	* Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or throws a {@link NoSuchDefinitionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_N_V(long companyId,
		java.lang.String name, int version)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().findByC_N_V(companyId, name, version);
	}

	/**
	* Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_N_V(long companyId,
		java.lang.String name, int version) {
		return getPersistence().fetchByC_N_V(companyId, name, version);
	}

	/**
	* Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_N_V(long companyId,
		java.lang.String name, int version, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByC_N_V(companyId, name, version, retrieveFromCache);
	}

	/**
	* Removes the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the kaleo definition that was removed
	*/
	public static KaleoDefinition removeByC_N_V(long companyId,
		java.lang.String name, int version)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().removeByC_N_V(companyId, name, version);
	}

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the number of matching kaleo definitions
	*/
	public static int countByC_N_V(long companyId, java.lang.String name,
		int version) {
		return getPersistence().countByC_N_V(companyId, name, version);
	}

	/**
	* Returns all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @return the matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_N_A(long companyId,
		java.lang.String name, boolean active) {
		return getPersistence().findByC_N_A(companyId, name, active);
	}

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_N_A(long companyId,
		java.lang.String name, boolean active, int start, int end) {
		return getPersistence().findByC_N_A(companyId, name, active, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	*/
	public static List<KaleoDefinition> findByC_N_A(long companyId,
		java.lang.String name, boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .findByC_N_A(companyId, name, active, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_N_A_First(long companyId,
		java.lang.String name, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_A_First(companyId, name, active, orderByComparator);
	}

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_N_A_First(long companyId,
		java.lang.String name, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_A_First(companyId, name, active,
			orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition findByC_N_A_Last(long companyId,
		java.lang.String name, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_A_Last(companyId, name, active, orderByComparator);
	}

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	*/
	public static KaleoDefinition fetchByC_N_A_Last(long companyId,
		java.lang.String name, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence()
				   .fetchByC_N_A_Last(companyId, name, active, orderByComparator);
	}

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition[] findByC_N_A_PrevAndNext(
		long kaleoDefinitionId, long companyId, java.lang.String name,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_A_PrevAndNext(kaleoDefinitionId, companyId, name,
			active, orderByComparator);
	}

	/**
	* Removes all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	*/
	public static void removeByC_N_A(long companyId, java.lang.String name,
		boolean active) {
		getPersistence().removeByC_N_A(companyId, name, active);
	}

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @return the number of matching kaleo definitions
	*/
	public static int countByC_N_A(long companyId, java.lang.String name,
		boolean active) {
		return getPersistence().countByC_N_A(companyId, name, active);
	}

	/**
	* Caches the kaleo definition in the entity cache if it is enabled.
	*
	* @param kaleoDefinition the kaleo definition
	*/
	public static void cacheResult(KaleoDefinition kaleoDefinition) {
		getPersistence().cacheResult(kaleoDefinition);
	}

	/**
	* Caches the kaleo definitions in the entity cache if it is enabled.
	*
	* @param kaleoDefinitions the kaleo definitions
	*/
	public static void cacheResult(List<KaleoDefinition> kaleoDefinitions) {
		getPersistence().cacheResult(kaleoDefinitions);
	}

	/**
	* Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	*
	* @param kaleoDefinitionId the primary key for the new kaleo definition
	* @return the new kaleo definition
	*/
	public static KaleoDefinition create(long kaleoDefinitionId) {
		return getPersistence().create(kaleoDefinitionId);
	}

	/**
	* Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition that was removed
	* @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition remove(long kaleoDefinitionId)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().remove(kaleoDefinitionId);
	}

	public static KaleoDefinition updateImpl(KaleoDefinition kaleoDefinition) {
		return getPersistence().updateImpl(kaleoDefinition);
	}

	/**
	* Returns the kaleo definition with the primary key or throws a {@link NoSuchDefinitionException} if it could not be found.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition
	* @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition findByPrimaryKey(long kaleoDefinitionId)
		throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().findByPrimaryKey(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	*/
	public static KaleoDefinition fetchByPrimaryKey(long kaleoDefinitionId) {
		return getPersistence().fetchByPrimaryKey(kaleoDefinitionId);
	}

	public static java.util.Map<java.io.Serializable, KaleoDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo definitions.
	*
	* @return the kaleo definitions
	*/
	public static List<KaleoDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of kaleo definitions
	*/
	public static List<KaleoDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo definitions
	*/
	public static List<KaleoDefinition> findAll(int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo definitions.
	*
	* @return the number of kaleo definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KaleoDefinitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoDefinitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoDefinitionPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoDefinitionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KaleoDefinitionPersistence persistence) {
	}

	private static KaleoDefinitionPersistence _persistence;
}