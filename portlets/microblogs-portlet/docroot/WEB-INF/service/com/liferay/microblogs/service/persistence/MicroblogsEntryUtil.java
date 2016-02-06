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

package com.liferay.microblogs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.microblogs.model.MicroblogsEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the microblogs entry service. This utility wraps {@link com.liferay.microblogs.service.persistence.impl.MicroblogsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryPersistence
 * @see com.liferay.microblogs.service.persistence.impl.MicroblogsEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class MicroblogsEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MicroblogsEntry microblogsEntry) {
		getPersistence().clearCache(microblogsEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MicroblogsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MicroblogsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MicroblogsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MicroblogsEntry update(MicroblogsEntry microblogsEntry) {
		return getPersistence().update(microblogsEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MicroblogsEntry update(MicroblogsEntry microblogsEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(microblogsEntry, serviceContext);
	}

	/**
	* Returns all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCompanyId_First(long companyId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCompanyId_First(long companyId,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCompanyId_Last(long companyId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCompanyId_Last(long companyId,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
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
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(microblogsEntryId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCompanyId(long companyId) {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCompanyId(long companyId,
		int start, int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
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
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(microblogsEntryId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the microblogs entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching microblogs entries
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByCompanyId(long companyId) {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Returns all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByUserId_First(long userId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByUserId_First(long userId,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByUserId_Last(long userId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByUserId_Last(long userId,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(microblogsEntryId, userId,
			orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByUserId(long userId) {
		return getPersistence().filterFindByUserId(userId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByUserId(long userId,
		int start, int end) {
		return getPersistence().filterFindByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByUserId(long userId,
		int start, int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
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
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByUserId_PrevAndNext(microblogsEntryId, userId,
			orderByComparator);
	}

	/**
	* Removes all the microblogs entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of microblogs entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching microblogs entries
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByUserId(long userId) {
		return getPersistence().filterCountByUserId(userId);
	}

	/**
	* Returns all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_T(long userId, int type) {
		return getPersistence().findByU_T(userId, type);
	}

	/**
	* Returns a range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_T(long userId, int type,
		int start, int end) {
		return getPersistence().findByU_T(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_T(long userId, int type,
		int start, int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByU_T(userId, type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_T(long userId, int type,
		int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_T(userId, type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByU_T_First(long userId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence().findByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByU_T_First(long userId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence().fetchByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByU_T_Last(long userId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence().findByU_T_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByU_T_Last(long userId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
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
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
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
	*/
	public static List<MicroblogsEntry> filterFindByU_T(long userId, int type) {
		return getPersistence().filterFindByU_T(userId, type);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByU_T(long userId, int type,
		int start, int end) {
		return getPersistence().filterFindByU_T(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByU_T(long userId, int type,
		int start, int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
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
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByU_T_PrevAndNext(microblogsEntryId, userId,
			type, orderByComparator);
	}

	/**
	* Removes all the microblogs entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	*/
	public static void removeByU_T(long userId, int type) {
		getPersistence().removeByU_T(userId, type);
	}

	/**
	* Returns the number of microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public static int countByU_T(long userId, int type) {
		return getPersistence().countByU_T(userId, type);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByU_T(long userId, int type) {
		return getPersistence().filterCountByU_T(userId, type);
	}

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPK, start,
			end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPK, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPK, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCCNI_CCPK_First(
		long creatorClassNameId, long creatorClassPK,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_CCPK_First(creatorClassNameId, creatorClassPK,
			orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCCNI_CCPK_First(
		long creatorClassNameId, long creatorClassPK,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCCNI_CCPK_First(creatorClassNameId, creatorClassPK,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCCNI_CCPK_Last(
		long creatorClassNameId, long creatorClassPK,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_CCPK_Last(creatorClassNameId, creatorClassPK,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCCNI_CCPK_Last(
		long creatorClassNameId, long creatorClassPK,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCCNI_CCPK_Last(creatorClassNameId, creatorClassPK,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByCCNI_CCPK_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_CCPK_PrevAndNext(microblogsEntryId,
			creatorClassNameId, creatorClassPK, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK) {
		return getPersistence()
				   .filterFindByCCNI_CCPK(creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end) {
		return getPersistence()
				   .filterFindByCCNI_CCPK(creatorClassNameId, creatorClassPK,
			start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long creatorClassPK, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByCCNI_CCPK(creatorClassNameId, creatorClassPK,
			start, end, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByCCNI_CCPK_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByCCNI_CCPK_PrevAndNext(microblogsEntryId,
			creatorClassNameId, creatorClassPK, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs) {
		return getPersistence()
				   .filterFindByCCNI_CCPK(creatorClassNameId, creatorClassPKs);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end) {
		return getPersistence()
				   .filterFindByCCNI_CCPK(creatorClassNameId, creatorClassPKs,
			start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByCCNI_CCPK(creatorClassNameId, creatorClassPKs,
			start, end, orderByComparator);
	}

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPKs);
	}

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPKs, start,
			end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPKs, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK(
		long creatorClassNameId, long[] creatorClassPKs, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCCNI_CCPK(creatorClassNameId, creatorClassPKs, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; from the database.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	*/
	public static void removeByCCNI_CCPK(long creatorClassNameId,
		long creatorClassPK) {
		getPersistence().removeByCCNI_CCPK(creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the number of matching microblogs entries
	*/
	public static int countByCCNI_CCPK(long creatorClassNameId,
		long creatorClassPK) {
		return getPersistence()
				   .countByCCNI_CCPK(creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the number of matching microblogs entries
	*/
	public static int countByCCNI_CCPK(long creatorClassNameId,
		long[] creatorClassPKs) {
		return getPersistence()
				   .countByCCNI_CCPK(creatorClassNameId, creatorClassPKs);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByCCNI_CCPK(long creatorClassNameId,
		long creatorClassPK) {
		return getPersistence()
				   .filterCountByCCNI_CCPK(creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByCCNI_CCPK(long creatorClassNameId,
		long[] creatorClassPKs) {
		return getPersistence()
				   .filterCountByCCNI_CCPK(creatorClassNameId, creatorClassPKs);
	}

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_T(long creatorClassNameId,
		int type) {
		return getPersistence().findByCCNI_T(creatorClassNameId, type);
	}

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_T(long creatorClassNameId,
		int type, int start, int end) {
		return getPersistence()
				   .findByCCNI_T(creatorClassNameId, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_T(long creatorClassNameId,
		int type, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByCCNI_T(creatorClassNameId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_T(long creatorClassNameId,
		int type, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCCNI_T(creatorClassNameId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCCNI_T_First(long creatorClassNameId,
		int type, OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_T_First(creatorClassNameId, type,
			orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCCNI_T_First(long creatorClassNameId,
		int type, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCCNI_T_First(creatorClassNameId, type,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCCNI_T_Last(long creatorClassNameId,
		int type, OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_T_Last(creatorClassNameId, type,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCCNI_T_Last(long creatorClassNameId,
		int type, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCCNI_T_Last(creatorClassNameId, type,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByCCNI_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_T_PrevAndNext(microblogsEntryId,
			creatorClassNameId, type, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_T(
		long creatorClassNameId, int type) {
		return getPersistence().filterFindByCCNI_T(creatorClassNameId, type);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_T(
		long creatorClassNameId, int type, int start, int end) {
		return getPersistence()
				   .filterFindByCCNI_T(creatorClassNameId, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_T(
		long creatorClassNameId, int type, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByCCNI_T(creatorClassNameId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByCCNI_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByCCNI_T_PrevAndNext(microblogsEntryId,
			creatorClassNameId, type, orderByComparator);
	}

	/**
	* Removes all the microblogs entries where creatorClassNameId = &#63; and type = &#63; from the database.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	*/
	public static void removeByCCNI_T(long creatorClassNameId, int type) {
		getPersistence().removeByCCNI_T(creatorClassNameId, type);
	}

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public static int countByCCNI_T(long creatorClassNameId, int type) {
		return getPersistence().countByCCNI_T(creatorClassNameId, type);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByCCNI_T(long creatorClassNameId, int type) {
		return getPersistence().filterCountByCCNI_T(creatorClassNameId, type);
	}

	/**
	* Returns all the microblogs entries where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByT_P(int type,
		long parentMicroblogsEntryId) {
		return getPersistence().findByT_P(type, parentMicroblogsEntryId);
	}

	/**
	* Returns a range of all the microblogs entries where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByT_P(int type,
		long parentMicroblogsEntryId, int start, int end) {
		return getPersistence()
				   .findByT_P(type, parentMicroblogsEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByT_P(int type,
		long parentMicroblogsEntryId, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByT_P(type, parentMicroblogsEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByT_P(int type,
		long parentMicroblogsEntryId, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByT_P(type, parentMicroblogsEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByT_P_First(int type,
		long parentMicroblogsEntryId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByT_P_First(type, parentMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByT_P_First(int type,
		long parentMicroblogsEntryId,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByT_P_First(type, parentMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByT_P_Last(int type,
		long parentMicroblogsEntryId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByT_P_Last(type, parentMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByT_P_Last(int type,
		long parentMicroblogsEntryId,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByT_P_Last(type, parentMicroblogsEntryId,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByT_P_PrevAndNext(
		long microblogsEntryId, int type, long parentMicroblogsEntryId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByT_P_PrevAndNext(microblogsEntryId, type,
			parentMicroblogsEntryId, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByT_P(int type,
		long parentMicroblogsEntryId) {
		return getPersistence().filterFindByT_P(type, parentMicroblogsEntryId);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByT_P(int type,
		long parentMicroblogsEntryId, int start, int end) {
		return getPersistence()
				   .filterFindByT_P(type, parentMicroblogsEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByT_P(int type,
		long parentMicroblogsEntryId, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByT_P(type, parentMicroblogsEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByT_P_PrevAndNext(
		long microblogsEntryId, int type, long parentMicroblogsEntryId,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByT_P_PrevAndNext(microblogsEntryId, type,
			parentMicroblogsEntryId, orderByComparator);
	}

	/**
	* Removes all the microblogs entries where type = &#63; and parentMicroblogsEntryId = &#63; from the database.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	*/
	public static void removeByT_P(int type, long parentMicroblogsEntryId) {
		getPersistence().removeByT_P(type, parentMicroblogsEntryId);
	}

	/**
	* Returns the number of microblogs entries where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @return the number of matching microblogs entries
	*/
	public static int countByT_P(int type, long parentMicroblogsEntryId) {
		return getPersistence().countByT_P(type, parentMicroblogsEntryId);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where type = &#63; and parentMicroblogsEntryId = &#63;.
	*
	* @param type the type
	* @param parentMicroblogsEntryId the parent microblogs entry ID
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByT_P(int type, long parentMicroblogsEntryId) {
		return getPersistence().filterCountByT_P(type, parentMicroblogsEntryId);
	}

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPK, type);
	}

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPK, type,
			start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPK, type,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPK, type,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCCNI_CCPK_T_First(
		long creatorClassNameId, long creatorClassPK, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_CCPK_T_First(creatorClassNameId, creatorClassPK,
			type, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCCNI_CCPK_T_First(
		long creatorClassNameId, long creatorClassPK, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCCNI_CCPK_T_First(creatorClassNameId,
			creatorClassPK, type, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByCCNI_CCPK_T_Last(
		long creatorClassNameId, long creatorClassPK, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_CCPK_T_Last(creatorClassNameId, creatorClassPK,
			type, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByCCNI_CCPK_T_Last(
		long creatorClassNameId, long creatorClassPK, int type,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCCNI_CCPK_T_Last(creatorClassNameId, creatorClassPK,
			type, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByCCNI_CCPK_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		int type, OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByCCNI_CCPK_T_PrevAndNext(microblogsEntryId,
			creatorClassNameId, creatorClassPK, type, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type) {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T(creatorClassNameId, creatorClassPK,
			type);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end) {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T(creatorClassNameId, creatorClassPK,
			type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T(creatorClassNameId, creatorClassPK,
			type, start, end, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByCCNI_CCPK_T_PrevAndNext(
		long microblogsEntryId, long creatorClassNameId, long creatorClassPK,
		int type, OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T_PrevAndNext(microblogsEntryId,
			creatorClassNameId, creatorClassPK, type, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type) {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T(creatorClassNameId,
			creatorClassPKs, type);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end) {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T(creatorClassNameId,
			creatorClassPKs, type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByCCNI_CCPK_T(creatorClassNameId,
			creatorClassPKs, type, start, end, orderByComparator);
	}

	/**
	* Returns all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPKs, type);
	}

	/**
	* Returns a range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPKs,
			type, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPKs,
			type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByCCNI_CCPK_T(
		long creatorClassNameId, long[] creatorClassPKs, int type, int start,
		int end, OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCCNI_CCPK_T(creatorClassNameId, creatorClassPKs,
			type, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63; from the database.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	*/
	public static void removeByCCNI_CCPK_T(long creatorClassNameId,
		long creatorClassPK, int type) {
		getPersistence()
			.removeByCCNI_CCPK_T(creatorClassNameId, creatorClassPK, type);
	}

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public static int countByCCNI_CCPK_T(long creatorClassNameId,
		long creatorClassPK, int type) {
		return getPersistence()
				   .countByCCNI_CCPK_T(creatorClassNameId, creatorClassPK, type);
	}

	/**
	* Returns the number of microblogs entries where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the number of matching microblogs entries
	*/
	public static int countByCCNI_CCPK_T(long creatorClassNameId,
		long[] creatorClassPKs, int type) {
		return getPersistence()
				   .countByCCNI_CCPK_T(creatorClassNameId, creatorClassPKs, type);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByCCNI_CCPK_T(long creatorClassNameId,
		long creatorClassPK, int type) {
		return getPersistence()
				   .filterCountByCCNI_CCPK_T(creatorClassNameId,
			creatorClassPK, type);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where creatorClassNameId = &#63; and creatorClassPK = any &#63; and type = &#63;.
	*
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPKs the creator class p ks
	* @param type the type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByCCNI_CCPK_T(long creatorClassNameId,
		long[] creatorClassPKs, int type) {
		return getPersistence()
				   .filterCountByCCNI_CCPK_T(creatorClassNameId,
			creatorClassPKs, type);
	}

	/**
	* Returns all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType) {
		return getPersistence()
				   .findByU_C_T_S(userId, createDate, type, socialRelationType);
	}

	/**
	* Returns a range of all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType, int start, int end) {
		return getPersistence()
				   .findByU_C_T_S(userId, createDate, type, socialRelationType,
			start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .findByU_C_T_S(userId, createDate, type, socialRelationType,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching microblogs entries
	*/
	public static List<MicroblogsEntry> findByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_C_T_S(userId, createDate, type, socialRelationType,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByU_C_T_S_First(long userId,
		Date createDate, int type, int socialRelationType,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByU_C_T_S_First(userId, createDate, type,
			socialRelationType, orderByComparator);
	}

	/**
	* Returns the first microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByU_C_T_S_First(long userId,
		Date createDate, int type, int socialRelationType,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByU_C_T_S_First(userId, createDate, type,
			socialRelationType, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry
	* @throws NoSuchEntryException if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry findByU_C_T_S_Last(long userId,
		Date createDate, int type, int socialRelationType,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByU_C_T_S_Last(userId, createDate, type,
			socialRelationType, orderByComparator);
	}

	/**
	* Returns the last microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching microblogs entry, or <code>null</code> if a matching microblogs entry could not be found
	*/
	public static MicroblogsEntry fetchByU_C_T_S_Last(long userId,
		Date createDate, int type, int socialRelationType,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByU_C_T_S_Last(userId, createDate, type,
			socialRelationType, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] findByU_C_T_S_PrevAndNext(
		long microblogsEntryId, long userId, Date createDate, int type,
		int socialRelationType,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .findByU_C_T_S_PrevAndNext(microblogsEntryId, userId,
			createDate, type, socialRelationType, orderByComparator);
	}

	/**
	* Returns all the microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType) {
		return getPersistence()
				   .filterFindByU_C_T_S(userId, createDate, type,
			socialRelationType);
	}

	/**
	* Returns a range of all the microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType, int start, int end) {
		return getPersistence()
				   .filterFindByU_C_T_S(userId, createDate, type,
			socialRelationType, start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries that the user has permissions to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching microblogs entries that the user has permission to view
	*/
	public static List<MicroblogsEntry> filterFindByU_C_T_S(long userId,
		Date createDate, int type, int socialRelationType, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence()
				   .filterFindByU_C_T_S(userId, createDate, type,
			socialRelationType, start, end, orderByComparator);
	}

	/**
	* Returns the microblogs entries before and after the current microblogs entry in the ordered set of microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry[] filterFindByU_C_T_S_PrevAndNext(
		long microblogsEntryId, long userId, Date createDate, int type,
		int socialRelationType,
		OrderByComparator<MicroblogsEntry> orderByComparator)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence()
				   .filterFindByU_C_T_S_PrevAndNext(microblogsEntryId, userId,
			createDate, type, socialRelationType, orderByComparator);
	}

	/**
	* Removes all the microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63; from the database.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	*/
	public static void removeByU_C_T_S(long userId, Date createDate, int type,
		int socialRelationType) {
		getPersistence()
			.removeByU_C_T_S(userId, createDate, type, socialRelationType);
	}

	/**
	* Returns the number of microblogs entries where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the number of matching microblogs entries
	*/
	public static int countByU_C_T_S(long userId, Date createDate, int type,
		int socialRelationType) {
		return getPersistence()
				   .countByU_C_T_S(userId, createDate, type, socialRelationType);
	}

	/**
	* Returns the number of microblogs entries that the user has permission to view where userId = &#63; and createDate = &#63; and type = &#63; and socialRelationType = &#63;.
	*
	* @param userId the user ID
	* @param createDate the create date
	* @param type the type
	* @param socialRelationType the social relation type
	* @return the number of matching microblogs entries that the user has permission to view
	*/
	public static int filterCountByU_C_T_S(long userId, Date createDate,
		int type, int socialRelationType) {
		return getPersistence()
				   .filterCountByU_C_T_S(userId, createDate, type,
			socialRelationType);
	}

	/**
	* Caches the microblogs entry in the entity cache if it is enabled.
	*
	* @param microblogsEntry the microblogs entry
	*/
	public static void cacheResult(MicroblogsEntry microblogsEntry) {
		getPersistence().cacheResult(microblogsEntry);
	}

	/**
	* Caches the microblogs entries in the entity cache if it is enabled.
	*
	* @param microblogsEntries the microblogs entries
	*/
	public static void cacheResult(List<MicroblogsEntry> microblogsEntries) {
		getPersistence().cacheResult(microblogsEntries);
	}

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public static MicroblogsEntry create(long microblogsEntryId) {
		return getPersistence().create(microblogsEntryId);
	}

	/**
	* Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry remove(long microblogsEntryId)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence().remove(microblogsEntryId);
	}

	public static MicroblogsEntry updateImpl(MicroblogsEntry microblogsEntry) {
		return getPersistence().updateImpl(microblogsEntry);
	}

	/**
	* Returns the microblogs entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws NoSuchEntryException if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry findByPrimaryKey(long microblogsEntryId)
		throws com.liferay.microblogs.exception.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(microblogsEntryId);
	}

	/**
	* Returns the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	*/
	public static MicroblogsEntry fetchByPrimaryKey(long microblogsEntryId) {
		return getPersistence().fetchByPrimaryKey(microblogsEntryId);
	}

	public static java.util.Map<java.io.Serializable, MicroblogsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the microblogs entries.
	*
	* @return the microblogs entries
	*/
	public static List<MicroblogsEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of microblogs entries
	*/
	public static List<MicroblogsEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of microblogs entries
	*/
	public static List<MicroblogsEntry> findAll(int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of microblogs entries
	*/
	public static List<MicroblogsEntry> findAll(int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the microblogs entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static MicroblogsEntryPersistence _persistence;
}