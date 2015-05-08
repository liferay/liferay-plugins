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
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

import java.util.List;

/**
 * The persistence utility for the kaleo node service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoNodePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNodePersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.impl.KaleoNodePersistenceImpl
 * @generated
 */
@ProviderType
public class KaleoNodeUtil {
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
	public static void clearCache(KaleoNode kaleoNode) {
		getPersistence().clearCache(kaleoNode);
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
	public static List<KaleoNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoNode update(KaleoNode kaleoNode) {
		return getPersistence().update(kaleoNode);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoNode update(KaleoNode kaleoNode,
		ServiceContext serviceContext) {
		return getPersistence().update(kaleoNode, serviceContext);
	}

	/**
	* Returns all the kaleo nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo nodes
	*/
	public static List<KaleoNode> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	*/
	public static List<KaleoNode> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	*/
	public static List<KaleoNode> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws NoSuchNodeException if a matching kaleo node could not be found
	*/
	public static KaleoNode findByCompanyId_First(long companyId,
		OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	*/
	public static KaleoNode fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws NoSuchNodeException if a matching kaleo node could not be found
	*/
	public static KaleoNode findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	*/
	public static KaleoNode fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63;.
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws NoSuchNodeException if a kaleo node with the primary key could not be found
	*/
	public static KaleoNode[] findByCompanyId_PrevAndNext(long kaleoNodeId,
		long companyId, OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoNodeId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the kaleo nodes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo nodes
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo nodes
	*/
	public static List<KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	*/
	public static List<KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	*/
	public static List<KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws NoSuchNodeException if a matching kaleo node could not be found
	*/
	public static KaleoNode findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	*/
	public static KaleoNode fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws NoSuchNodeException if a matching kaleo node could not be found
	*/
	public static KaleoNode findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	*/
	public static KaleoNode fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws NoSuchNodeException if a kaleo node with the primary key could not be found
	*/
	public static KaleoNode[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNodeId, long kaleoDefinitionId,
		OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoNodeId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo nodes where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo nodes
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId) {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo nodes
	*/
	public static List<KaleoNode> findByC_KDI(long companyId,
		long kaleoDefinitionId) {
		return getPersistence().findByC_KDI(companyId, kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	*/
	public static List<KaleoNode> findByC_KDI(long companyId,
		long kaleoDefinitionId, int start, int end) {
		return getPersistence()
				   .findByC_KDI(companyId, kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	*/
	public static List<KaleoNode> findByC_KDI(long companyId,
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .findByC_KDI(companyId, kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws NoSuchNodeException if a matching kaleo node could not be found
	*/
	public static KaleoNode findByC_KDI_First(long companyId,
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByC_KDI_First(companyId, kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	*/
	public static KaleoNode fetchByC_KDI_First(long companyId,
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .fetchByC_KDI_First(companyId, kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws NoSuchNodeException if a matching kaleo node could not be found
	*/
	public static KaleoNode findByC_KDI_Last(long companyId,
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByC_KDI_Last(companyId, kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	*/
	public static KaleoNode fetchByC_KDI_Last(long companyId,
		long kaleoDefinitionId, OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence()
				   .fetchByC_KDI_Last(companyId, kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws NoSuchNodeException if a kaleo node with the primary key could not be found
	*/
	public static KaleoNode[] findByC_KDI_PrevAndNext(long kaleoNodeId,
		long companyId, long kaleoDefinitionId,
		OrderByComparator<KaleoNode> orderByComparator)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByC_KDI_PrevAndNext(kaleoNodeId, companyId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	*/
	public static void removeByC_KDI(long companyId, long kaleoDefinitionId) {
		getPersistence().removeByC_KDI(companyId, kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo nodes
	*/
	public static int countByC_KDI(long companyId, long kaleoDefinitionId) {
		return getPersistence().countByC_KDI(companyId, kaleoDefinitionId);
	}

	/**
	* Caches the kaleo node in the entity cache if it is enabled.
	*
	* @param kaleoNode the kaleo node
	*/
	public static void cacheResult(KaleoNode kaleoNode) {
		getPersistence().cacheResult(kaleoNode);
	}

	/**
	* Caches the kaleo nodes in the entity cache if it is enabled.
	*
	* @param kaleoNodes the kaleo nodes
	*/
	public static void cacheResult(List<KaleoNode> kaleoNodes) {
		getPersistence().cacheResult(kaleoNodes);
	}

	/**
	* Creates a new kaleo node with the primary key. Does not add the kaleo node to the database.
	*
	* @param kaleoNodeId the primary key for the new kaleo node
	* @return the new kaleo node
	*/
	public static KaleoNode create(long kaleoNodeId) {
		return getPersistence().create(kaleoNodeId);
	}

	/**
	* Removes the kaleo node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node that was removed
	* @throws NoSuchNodeException if a kaleo node with the primary key could not be found
	*/
	public static KaleoNode remove(long kaleoNodeId)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence().remove(kaleoNodeId);
	}

	public static KaleoNode updateImpl(KaleoNode kaleoNode) {
		return getPersistence().updateImpl(kaleoNode);
	}

	/**
	* Returns the kaleo node with the primary key or throws a {@link NoSuchNodeException} if it could not be found.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node
	* @throws NoSuchNodeException if a kaleo node with the primary key could not be found
	*/
	public static KaleoNode findByPrimaryKey(long kaleoNodeId)
		throws com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence().findByPrimaryKey(kaleoNodeId);
	}

	/**
	* Returns the kaleo node with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node, or <code>null</code> if a kaleo node with the primary key could not be found
	*/
	public static KaleoNode fetchByPrimaryKey(long kaleoNodeId) {
		return getPersistence().fetchByPrimaryKey(kaleoNodeId);
	}

	public static java.util.Map<java.io.Serializable, KaleoNode> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kaleo nodes.
	*
	* @return the kaleo nodes
	*/
	public static List<KaleoNode> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of kaleo nodes
	*/
	public static List<KaleoNode> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo nodes
	*/
	public static List<KaleoNode> findAll(int start, int end,
		OrderByComparator<KaleoNode> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo nodes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo nodes.
	*
	* @return the number of kaleo nodes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KaleoNodePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoNodePersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoNodePersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoNodeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(KaleoNodePersistence persistence) {
	}

	private static KaleoNodePersistence _persistence;
}