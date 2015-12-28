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

package com.liferay.chat.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.chat.model.Status;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the status service. This utility wraps {@link com.liferay.chat.service.persistence.impl.StatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StatusPersistence
 * @see com.liferay.chat.service.persistence.impl.StatusPersistenceImpl
 * @generated
 */
@ProviderType
public class StatusUtil {
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
	public static void clearCache(Status status) {
		getPersistence().clearCache(status);
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
	public static List<Status> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Status> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Status> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Status update(Status status) {
		return getPersistence().update(status);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Status update(Status status, ServiceContext serviceContext) {
		return getPersistence().update(status, serviceContext);
	}

	/**
	* Returns the status where userId = &#63; or throws a {@link NoSuchStatusException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns the status where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByUserId(long userId) {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	* Returns the status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByUserId(long userId, boolean retrieveFromCache) {
		return getPersistence().fetchByUserId(userId, retrieveFromCache);
	}

	/**
	* Removes the status where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the status that was removed
	*/
	public static Status removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of statuses where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching statuses
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the statuses where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching statuses
	*/
	public static List<Status> findByModifiedDate(long modifiedDate) {
		return getPersistence().findByModifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of matching statuses
	*/
	public static List<Status> findByModifiedDate(long modifiedDate, int start,
		int end) {
		return getPersistence().findByModifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching statuses
	*/
	public static List<Status> findByModifiedDate(long modifiedDate, int start,
		int end, OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .findByModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching statuses
	*/
	public static List<Status> findByModifiedDate(long modifiedDate, int start,
		int end, OrderByComparator<Status> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByModifiedDate(modifiedDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByModifiedDate_First(long modifiedDate,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByModifiedDate_First(long modifiedDate,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .fetchByModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByModifiedDate_Last(long modifiedDate,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByModifiedDate_Last(long modifiedDate,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .fetchByModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the statuses before and after the current status in the ordered set where modifiedDate = &#63;.
	*
	* @param statusId the primary key of the current status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public static Status[] findByModifiedDate_PrevAndNext(long statusId,
		long modifiedDate, OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByModifiedDate_PrevAndNext(statusId, modifiedDate,
			orderByComparator);
	}

	/**
	* Removes all the statuses where modifiedDate = &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public static void removeByModifiedDate(long modifiedDate) {
		getPersistence().removeByModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of statuses where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching statuses
	*/
	public static int countByModifiedDate(long modifiedDate) {
		return getPersistence().countByModifiedDate(modifiedDate);
	}

	/**
	* Returns all the statuses where online = &#63;.
	*
	* @param online the online
	* @return the matching statuses
	*/
	public static List<Status> findByOnline(boolean online) {
		return getPersistence().findByOnline(online);
	}

	/**
	* Returns a range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of matching statuses
	*/
	public static List<Status> findByOnline(boolean online, int start, int end) {
		return getPersistence().findByOnline(online, start, end);
	}

	/**
	* Returns an ordered range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching statuses
	*/
	public static List<Status> findByOnline(boolean online, int start, int end,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .findByOnline(online, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching statuses
	*/
	public static List<Status> findByOnline(boolean online, int start, int end,
		OrderByComparator<Status> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByOnline(online, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByOnline_First(boolean online,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence().findByOnline_First(online, orderByComparator);
	}

	/**
	* Returns the first status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByOnline_First(boolean online,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence().fetchByOnline_First(online, orderByComparator);
	}

	/**
	* Returns the last status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByOnline_Last(boolean online,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence().findByOnline_Last(online, orderByComparator);
	}

	/**
	* Returns the last status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByOnline_Last(boolean online,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence().fetchByOnline_Last(online, orderByComparator);
	}

	/**
	* Returns the statuses before and after the current status in the ordered set where online = &#63;.
	*
	* @param statusId the primary key of the current status
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public static Status[] findByOnline_PrevAndNext(long statusId,
		boolean online, OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByOnline_PrevAndNext(statusId, online, orderByComparator);
	}

	/**
	* Removes all the statuses where online = &#63; from the database.
	*
	* @param online the online
	*/
	public static void removeByOnline(boolean online) {
		getPersistence().removeByOnline(online);
	}

	/**
	* Returns the number of statuses where online = &#63;.
	*
	* @param online the online
	* @return the number of matching statuses
	*/
	public static int countByOnline(boolean online) {
		return getPersistence().countByOnline(online);
	}

	/**
	* Returns all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @return the matching statuses
	*/
	public static List<Status> findByM_O(long modifiedDate, boolean online) {
		return getPersistence().findByM_O(modifiedDate, online);
	}

	/**
	* Returns a range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of matching statuses
	*/
	public static List<Status> findByM_O(long modifiedDate, boolean online,
		int start, int end) {
		return getPersistence().findByM_O(modifiedDate, online, start, end);
	}

	/**
	* Returns an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching statuses
	*/
	public static List<Status> findByM_O(long modifiedDate, boolean online,
		int start, int end, OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .findByM_O(modifiedDate, online, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching statuses
	*/
	public static List<Status> findByM_O(long modifiedDate, boolean online,
		int start, int end, OrderByComparator<Status> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByM_O(modifiedDate, online, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByM_O_First(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByM_O_First(modifiedDate, online, orderByComparator);
	}

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByM_O_First(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .fetchByM_O_First(modifiedDate, online, orderByComparator);
	}

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public static Status findByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByM_O_Last(modifiedDate, online, orderByComparator);
	}

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status, or <code>null</code> if a matching status could not be found
	*/
	public static Status fetchByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence()
				   .fetchByM_O_Last(modifiedDate, online, orderByComparator);
	}

	/**
	* Returns the statuses before and after the current status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param statusId the primary key of the current status
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public static Status[] findByM_O_PrevAndNext(long statusId,
		long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence()
				   .findByM_O_PrevAndNext(statusId, modifiedDate, online,
			orderByComparator);
	}

	/**
	* Removes all the statuses where modifiedDate = &#63; and online = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param online the online
	*/
	public static void removeByM_O(long modifiedDate, boolean online) {
		getPersistence().removeByM_O(modifiedDate, online);
	}

	/**
	* Returns the number of statuses where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @return the number of matching statuses
	*/
	public static int countByM_O(long modifiedDate, boolean online) {
		return getPersistence().countByM_O(modifiedDate, online);
	}

	/**
	* Caches the status in the entity cache if it is enabled.
	*
	* @param status the status
	*/
	public static void cacheResult(Status status) {
		getPersistence().cacheResult(status);
	}

	/**
	* Caches the statuses in the entity cache if it is enabled.
	*
	* @param statuses the statuses
	*/
	public static void cacheResult(List<Status> statuses) {
		getPersistence().cacheResult(statuses);
	}

	/**
	* Creates a new status with the primary key. Does not add the status to the database.
	*
	* @param statusId the primary key for the new status
	* @return the new status
	*/
	public static Status create(long statusId) {
		return getPersistence().create(statusId);
	}

	/**
	* Removes the status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the status
	* @return the status that was removed
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public static Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence().remove(statusId);
	}

	public static Status updateImpl(Status status) {
		return getPersistence().updateImpl(status);
	}

	/**
	* Returns the status with the primary key or throws a {@link NoSuchStatusException} if it could not be found.
	*
	* @param statusId the primary key of the status
	* @return the status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public static Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException {
		return getPersistence().findByPrimaryKey(statusId);
	}

	/**
	* Returns the status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param statusId the primary key of the status
	* @return the status, or <code>null</code> if a status with the primary key could not be found
	*/
	public static Status fetchByPrimaryKey(long statusId) {
		return getPersistence().fetchByPrimaryKey(statusId);
	}

	public static java.util.Map<java.io.Serializable, Status> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the statuses.
	*
	* @return the statuses
	*/
	public static List<Status> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of statuses
	*/
	public static List<Status> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of statuses
	*/
	public static List<Status> findAll(int start, int end,
		OrderByComparator<Status> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of statuses
	*/
	public static List<Status> findAll(int start, int end,
		OrderByComparator<Status> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the statuses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of statuses.
	*
	* @return the number of statuses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StatusPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (StatusPersistence)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.getServletContextName(),
					StatusPersistence.class.getName());

			ReferenceRegistry.registerReference(StatusUtil.class, "_persistence");
		}

		return _persistence;
	}

	private static StatusPersistence _persistence;
}