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
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

import java.util.List;

/**
 * The persistence utility for the kaleo node service. This utility wraps {@link KaleoNodePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNodePersistence
 * @see KaleoNodePersistenceImpl
 * @generated
 */
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
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoNode> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoNode update(KaleoNode kaleoNode, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoNode, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoNode update(KaleoNode kaleoNode, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoNode, merge, serviceContext);
	}

	/**
	* Caches the kaleo node in the entity cache if it is enabled.
	*
	* @param kaleoNode the kaleo node
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode) {
		getPersistence().cacheResult(kaleoNode);
	}

	/**
	* Caches the kaleo nodes in the entity cache if it is enabled.
	*
	* @param kaleoNodes the kaleo nodes
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> kaleoNodes) {
		getPersistence().cacheResult(kaleoNodes);
	}

	/**
	* Creates a new kaleo node with the primary key. Does not add the kaleo node to the database.
	*
	* @param kaleoNodeId the primary key for the new kaleo node
	* @return the new kaleo node
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode create(
		long kaleoNodeId) {
		return getPersistence().create(kaleoNodeId);
	}

	/**
	* Removes the kaleo node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode remove(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence().remove(kaleoNodeId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNode updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoNode, merge);
	}

	/**
	* Returns the kaleo node with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNodeException} if it could not be found.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByPrimaryKey(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence().findByPrimaryKey(kaleoNodeId);
	}

	/**
	* Returns the kaleo node with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node, or <code>null</code> if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByPrimaryKey(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoNodeId);
	}

	/**
	* Returns all the kaleo nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode[] findByCompanyId_PrevAndNext(
		long kaleoNodeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoNodeId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNodeId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoNodeId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Returns all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByC_KDI(
		long companyId, long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_KDI(companyId, kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByC_KDI(
		long companyId, long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_KDI(companyId, kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByC_KDI(
		long companyId, long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_KDI(companyId, kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByC_KDI_First(
		long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByC_KDI_First(companyId, kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode findByC_KDI_Last(
		long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByC_KDI_Last(companyId, kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNode[] findByC_KDI_PrevAndNext(
		long kaleoNodeId, long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException {
		return getPersistence()
				   .findByC_KDI_PrevAndNext(kaleoNodeId, companyId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Returns all the kaleo nodes.
	*
	* @return the kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo nodes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the kaleo nodes where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_KDI(long companyId, long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_KDI(companyId, kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo nodes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_KDI(long companyId, long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_KDI(companyId, kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo nodes.
	*
	* @return the number of kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the kaleo actions associated with the kaleo node.
	*
	* @param pk the primary key of the kaleo node
	* @return the kaleo actions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoActions(pk);
	}

	/**
	* Returns a range of all the kaleo actions associated with the kaleo node.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo node
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of kaleo actions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoActions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo actions associated with the kaleo node.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo node
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo actions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getKaleoActions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of kaleo actions associated with the kaleo node.
	*
	* @param pk the primary key of the kaleo node
	* @return the number of kaleo actions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoActionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoActionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the kaleo action is associated with the kaleo node.
	*
	* @param pk the primary key of the kaleo node
	* @param kaleoActionPK the primary key of the kaleo action
	* @return <code>true</code> if the kaleo action is associated with the kaleo node; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsKaleoAction(long pk, long kaleoActionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoAction(pk, kaleoActionPK);
	}

	/**
	* Returns <code>true</code> if the kaleo node has any kaleo actions associated with it.
	*
	* @param pk the primary key of the kaleo node to check for associations with kaleo actions
	* @return <code>true</code> if the kaleo node has any kaleo actions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsKaleoActions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoActions(pk);
	}

	/**
	* Returns all the kaleo transitions associated with the kaleo node.
	*
	* @param pk the primary key of the kaleo node
	* @return the kaleo transitions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoTransitions(pk);
	}

	/**
	* Returns a range of all the kaleo transitions associated with the kaleo node.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo node
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of kaleo transitions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoTransitions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo transitions associated with the kaleo node.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the kaleo node
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo transitions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getKaleoTransitions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of kaleo transitions associated with the kaleo node.
	*
	* @param pk the primary key of the kaleo node
	* @return the number of kaleo transitions associated with the kaleo node
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoTransitionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoTransitionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the kaleo transition is associated with the kaleo node.
	*
	* @param pk the primary key of the kaleo node
	* @param kaleoTransitionPK the primary key of the kaleo transition
	* @return <code>true</code> if the kaleo transition is associated with the kaleo node; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsKaleoTransition(long pk,
		long kaleoTransitionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoTransition(pk, kaleoTransitionPK);
	}

	/**
	* Returns <code>true</code> if the kaleo node has any kaleo transitions associated with it.
	*
	* @param pk the primary key of the kaleo node to check for associations with kaleo transitions
	* @return <code>true</code> if the kaleo node has any kaleo transitions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsKaleoTransitions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoTransitions(pk);
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

	public void setPersistence(KaleoNodePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(KaleoNodeUtil.class, "_persistence");
	}

	private static KaleoNodePersistence _persistence;
}