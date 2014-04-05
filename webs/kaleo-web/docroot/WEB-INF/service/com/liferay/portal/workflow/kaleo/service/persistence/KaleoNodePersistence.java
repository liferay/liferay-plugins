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

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

/**
 * The persistence interface for the kaleo node service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNodePersistenceImpl
 * @see KaleoNodeUtil
 * @generated
 */
public interface KaleoNodePersistence extends BasePersistence<KaleoNode> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoNodeUtil} to access the kaleo node persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo nodes where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63;.
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode[] findByCompanyId_PrevAndNext(
		long kaleoNodeId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Removes all the kaleo nodes where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo nodes where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the first kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the last kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNodeId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Removes all the kaleo nodes where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo nodes where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByC_KDI(
		long companyId, long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByC_KDI(
		long companyId, long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findByC_KDI(
		long companyId, long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByC_KDI_First(
		long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the first kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByC_KDI_First(
		long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByC_KDI_Last(
		long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the last kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo node, or <code>null</code> if a matching kaleo node could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByC_KDI_Last(
		long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param kaleoNodeId the primary key of the current kaleo node
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode[] findByC_KDI_PrevAndNext(
		long kaleoNodeId, long companyId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Removes all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_KDI(long companyId, long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	*
	* @param companyId the company ID
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_KDI(long companyId, long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the kaleo node in the entity cache if it is enabled.
	*
	* @param kaleoNode the kaleo node
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode);

	/**
	* Caches the kaleo nodes in the entity cache if it is enabled.
	*
	* @param kaleoNodes the kaleo nodes
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> kaleoNodes);

	/**
	* Creates a new kaleo node with the primary key. Does not add the kaleo node to the database.
	*
	* @param kaleoNodeId the primary key for the new kaleo node
	* @return the new kaleo node
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode create(
		long kaleoNodeId);

	/**
	* Removes the kaleo node with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode remove(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNode updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo node with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNodeException} if it could not be found.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node
	* @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode findByPrimaryKey(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchNodeException;

	/**
	* Returns the kaleo node with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoNodeId the primary key of the kaleo node
	* @return the kaleo node, or <code>null</code> if a kaleo node with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNode fetchByPrimaryKey(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo nodes.
	*
	* @return the kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @return the range of kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo nodes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo nodes
	* @param end the upper bound of the range of kaleo nodes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo nodes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo nodes.
	*
	* @return the number of kaleo nodes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}