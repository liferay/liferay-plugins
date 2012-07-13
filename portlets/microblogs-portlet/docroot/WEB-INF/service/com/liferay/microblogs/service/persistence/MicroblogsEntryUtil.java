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

package com.liferay.microblogs.service.persistence;

import com.liferay.microblogs.model.MicroblogsEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the microblogs entry service. This utility wraps {@link MicroblogsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryPersistence
 * @see MicroblogsEntryPersistenceImpl
 * @generated
 */
public class MicroblogsEntryUtil {
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
	public static void clearCache(MicroblogsEntry microblogsEntry) {
		getPersistence().clearCache(microblogsEntry);
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
	public static List<MicroblogsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MicroblogsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MicroblogsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MicroblogsEntry update(MicroblogsEntry microblogsEntry,
		boolean merge) throws SystemException {
		return getPersistence().update(microblogsEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MicroblogsEntry update(MicroblogsEntry microblogsEntry,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(microblogsEntry, merge, serviceContext);
	}

	/**
	* Caches the microblogs entry in the entity cache if it is enabled.
	*
	* @param microblogsEntry the microblogs entry
	*/
	public static void cacheResult(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		getPersistence().cacheResult(microblogsEntry);
	}

	/**
	* Caches the microblogs entries in the entity cache if it is enabled.
	*
	* @param microblogsEntries the microblogs entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.microblogs.model.MicroblogsEntry> microblogsEntries) {
		getPersistence().cacheResult(microblogsEntries);
	}

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry create(
		long microblogsEntryId) {
		return getPersistence().create(microblogsEntryId);
	}

	/**
	* Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry remove(
		long microblogsEntryId)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(microblogsEntryId);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry updateImpl(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(microblogsEntry, merge);
	}

	/**
	* Returns the microblogs entry with the primary key or throws a {@link com.liferay.microblogs.NoSuchEntryException} if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByPrimaryKey(
		long microblogsEntryId)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(microblogsEntryId);
	}

	/**
	* Returns the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByPrimaryKey(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(microblogsEntryId);
	}

	/**
	* Returns all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] findByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(microblogsEntryId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByCompanyId(companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] filterFindByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(microblogsEntryId,
			companyId, orderByComparator);
	}

	/**
	* Returns all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] findByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(microblogsEntryId, userId,
			orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUserId(userId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] filterFindByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUserId_PrevAndNext(microblogsEntryId, userId,
			orderByComparator);
	}

	/**
	* Returns all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T(userId, type);
	}

	/**
	* Returns a range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_T(userId, type, start, end, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_T_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] findByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_T_PrevAndNext(microblogsEntryId, userId, type,
			orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByU_T(userId, type);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByU_T(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByU_T(userId, type, start, end, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] filterFindByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByU_T_PrevAndNext(microblogsEntryId, userId,
			type, orderByComparator);
	}

	/**
	* Returns all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_R(
		int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_R(type, receiverUserId);
	}

	/**
	* Returns a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_R(
		int type, long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_R(type, receiverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_R(
		int type, long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_R(type, receiverUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByT_R_First(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_R_First(type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByT_R_First(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_R_First(type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByT_R_Last(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_R_Last(type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByT_R_Last(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_R_Last(type, receiverUserId, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] findByT_R_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_R_PrevAndNext(microblogsEntryId, type,
			receiverUserId, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_R(
		int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByT_R(type, receiverUserId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_R(
		int type, long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByT_R(type, receiverUserId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_R(
		int type, long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByT_R(type, receiverUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] filterFindByT_R_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByT_R_PrevAndNext(microblogsEntryId, type,
			receiverUserId, orderByComparator);
	}

	/**
	* Returns all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RMEI(
		int type, long receiverMicroblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByT_RMEI(type, receiverMicroblogsEntryId);
	}

	/**
	* Returns a range of all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_RMEI(type, receiverMicroblogsEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_RMEI(type, receiverMicroblogsEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByT_RMEI_First(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_RMEI_First(type, receiverMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByT_RMEI_First(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_RMEI_First(type, receiverMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry findByT_RMEI_Last(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_RMEI_Last(type, receiverMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry fetchByT_RMEI_Last(
		int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByT_RMEI_Last(type, receiverMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] findByT_RMEI_PrevAndNext(
		long microblogsEntryId, int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByT_RMEI_PrevAndNext(microblogsEntryId, type,
			receiverMicroblogsEntryId, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RMEI(
		int type, long receiverMicroblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByT_RMEI(type, receiverMicroblogsEntryId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByT_RMEI(type, receiverMicroblogsEntryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RMEI(
		int type, long receiverMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByT_RMEI(type, receiverMicroblogsEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry[] filterFindByT_RMEI_PrevAndNext(
		long microblogsEntryId, int type, long receiverMicroblogsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByT_RMEI_PrevAndNext(microblogsEntryId, type,
			receiverMicroblogsEntryId, orderByComparator);
	}

	/**
	* Returns all the microblogs entries.
	*
	* @return the microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the microblogs entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the microblogs entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the microblogs entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_T(userId, type);
	}

	/**
	* Removes all the microblogs entries where type = &#63; and receiverUserId = &#63; from the database.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByT_R(int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_R(type, receiverUserId);
	}

	/**
	* Removes all the microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63; from the database.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByT_RMEI(int type, long receiverMicroblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByT_RMEI(type, receiverMicroblogsEntryId);
	}

	/**
	* Removes all the microblogs entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Returns the number of microblogs entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUserId(userId);
	}

	/**
	* Returns the number of microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_T(userId, type);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByU_T(userId, type);
	}

	/**
	* Returns the number of microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_R(int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_R(type, receiverUserId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type
	* @param receiverUserId the receiver user ID
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByT_R(int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByT_R(type, receiverUserId);
	}

	/**
	* Returns the number of microblogs entries where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_RMEI(int type, long receiverMicroblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_RMEI(type, receiverMicroblogsEntryId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where type = &#63; and receiverMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByT_RMEI(int type,
		long receiverMicroblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByT_RMEI(type, receiverMicroblogsEntryId);
	}

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MicroblogsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MicroblogsEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.microblogs.service.ClpSerializer.getServletContextName(),
					MicroblogsEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(MicroblogsEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(MicroblogsEntryPersistence persistence) {
	}

	private static MicroblogsEntryPersistence _persistence;
}