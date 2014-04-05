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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

import java.util.List;

/**
 * The persistence utility for the kaleo task assignment service. This utility wraps {@link KaleoTaskAssignmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentPersistence
 * @see KaleoTaskAssignmentPersistenceImpl
 * @generated
 */
public class KaleoTaskAssignmentUtil {
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
	public static void clearCache(KaleoTaskAssignment kaleoTaskAssignment) {
		getPersistence().clearCache(kaleoTaskAssignment);
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
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTaskAssignment update(
		KaleoTaskAssignment kaleoTaskAssignment) throws SystemException {
		return getPersistence().update(kaleoTaskAssignment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoTaskAssignment update(
		KaleoTaskAssignment kaleoTaskAssignment, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(kaleoTaskAssignment, serviceContext);
	}

	/**
	* Returns all the kaleo task assignments where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the kaleo task assignments where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where companyId = &#63;.
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTaskAssignmentId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignments where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of kaleo task assignments where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoDefinitionId the kaleo definition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTaskAssignmentId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignments where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition ID
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKCN_KCPK(
		java.lang.String kaleoClassName, long kaleoClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKCN_KCPK_First(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKCN_KCPK_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKCN_KCPK_First(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKCN_KCPK_Last(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKCN_KCPK_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKCN_KCPK_Last(kaleoClassName, kaleoClassPK,
			orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKCN_KCPK_PrevAndNext(
		long kaleoTaskAssignmentId, java.lang.String kaleoClassName,
		long kaleoClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKCN_KCPK_PrevAndNext(kaleoTaskAssignmentId,
			kaleoClassName, kaleoClassPK, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKCN_KCPK(java.lang.String kaleoClassName,
		long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKCN_KCPK(kaleoClassName, kaleoClassPK);
	}

	/**
	* Returns all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @return the matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKCN_KCPK_ACN(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName);
	}

	/**
	* Returns a range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKCN_KCPK_ACN(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKCN_KCPK_ACN(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName, start, end, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKCN_KCPK_ACN_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKCN_KCPK_ACN_First(kaleoClassName, kaleoClassPK,
			assigneeClassName, orderByComparator);
	}

	/**
	* Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKCN_KCPK_ACN_First(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKCN_KCPK_ACN_First(kaleoClassName, kaleoClassPK,
			assigneeClassName, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKCN_KCPK_ACN_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKCN_KCPK_ACN_Last(kaleoClassName, kaleoClassPK,
			assigneeClassName, orderByComparator);
	}

	/**
	* Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKCN_KCPK_ACN_Last(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKCN_KCPK_ACN_Last(kaleoClassName, kaleoClassPK,
			assigneeClassName, orderByComparator);
	}

	/**
	* Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKCN_KCPK_ACN_PrevAndNext(
		long kaleoTaskAssignmentId, java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String assigneeClassName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKCN_KCPK_ACN_PrevAndNext(kaleoTaskAssignmentId,
			kaleoClassName, kaleoClassPK, assigneeClassName, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63; from the database.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKCN_KCPK_ACN(java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName);
	}

	/**
	* Returns the number of kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	*
	* @param kaleoClassName the kaleo class name
	* @param kaleoClassPK the kaleo class p k
	* @param assigneeClassName the assignee class name
	* @return the number of matching kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKCN_KCPK_ACN(java.lang.String kaleoClassName,
		long kaleoClassPK, java.lang.String assigneeClassName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName);
	}

	/**
	* Caches the kaleo task assignment in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignment the kaleo task assignment
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment) {
		getPersistence().cacheResult(kaleoTaskAssignment);
	}

	/**
	* Caches the kaleo task assignments in the entity cache if it is enabled.
	*
	* @param kaleoTaskAssignments the kaleo task assignments
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments) {
		getPersistence().cacheResult(kaleoTaskAssignments);
	}

	/**
	* Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	*
	* @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	* @return the new kaleo task assignment
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment create(
		long kaleoTaskAssignmentId) {
		return getPersistence().create(kaleoTaskAssignmentId);
	}

	/**
	* Removes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment remove(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence().remove(kaleoTaskAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskAssignment);
	}

	/**
	* Returns the kaleo task assignment with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException} if it could not be found.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment
	* @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence().findByPrimaryKey(kaleoTaskAssignmentId);
	}

	/**
	* Returns the kaleo task assignment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	* @return the kaleo task assignment, or <code>null</code> if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskAssignmentId);
	}

	/**
	* Returns all the kaleo task assignments.
	*
	* @return the kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo task assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @return the range of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo task assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignments
	* @param end the upper bound of the range of kaleo task assignments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo task assignments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo task assignments.
	*
	* @return the number of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskAssignmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskAssignmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoTaskAssignmentPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoTaskAssignmentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(KaleoTaskAssignmentPersistence persistence) {
	}

	private static KaleoTaskAssignmentPersistence _persistence;
}