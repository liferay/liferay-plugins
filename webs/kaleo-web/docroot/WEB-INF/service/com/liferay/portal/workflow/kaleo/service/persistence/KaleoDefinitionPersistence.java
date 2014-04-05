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
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

/**
 * The persistence interface for the kaleo definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionPersistenceImpl
 * @see KaleoDefinitionUtil
 * @generated
 */
public interface KaleoDefinitionPersistence extends BasePersistence<KaleoDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KaleoDefinitionUtil} to access the kaleo definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the kaleo definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDefinitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Removes all the kaleo definitions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo definitions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByC_N_PrevAndNext(
		long kaleoDefinitionId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Removes all the kaleo definitions where companyId = &#63; and name = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and name = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_A(
		long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_A_First(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_A_First(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_A_Last(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_A_Last(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByC_A_PrevAndNext(
		long kaleoDefinitionId, long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Removes all the kaleo definitions where companyId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchDefinitionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_V(
		long companyId, java.lang.String name, int version,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the kaleo definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition removeByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and version = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param version the version
	* @return the number of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N_V(long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @return the matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N_A(
		long companyId, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N_A(
		long companyId, java.lang.String name, boolean active, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N_A(
		long companyId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_A_First(
		long companyId, java.lang.String name, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_A_First(
		long companyId, java.lang.String name, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_A_Last(
		long companyId, java.lang.String name, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_A_Last(
		long companyId, java.lang.String name, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param kaleoDefinitionId the primary key of the current kaleo definition
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByC_N_A_PrevAndNext(
		long kaleoDefinitionId, long companyId, java.lang.String name,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Removes all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_N_A(long companyId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param active the active
	* @return the number of matching kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N_A(long companyId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the kaleo definition in the entity cache if it is enabled.
	*
	* @param kaleoDefinition the kaleo definition
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition);

	/**
	* Caches the kaleo definitions in the entity cache if it is enabled.
	*
	* @param kaleoDefinitions the kaleo definitions
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> kaleoDefinitions);

	/**
	* Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	*
	* @param kaleoDefinitionId the primary key for the new kaleo definition
	* @return the new kaleo definition
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition create(
		long kaleoDefinitionId);

	/**
	* Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition remove(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the kaleo definition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchDefinitionException} if it could not be found.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition
	* @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByPrimaryKey(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;

	/**
	* Returns the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition
	* @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByPrimaryKey(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the kaleo definitions.
	*
	* @return the kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the kaleo definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @return the range of kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the kaleo definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo definitions
	* @param end the upper bound of the range of kaleo definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo definitions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of kaleo definitions.
	*
	* @return the number of kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}