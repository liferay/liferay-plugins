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

package com.liferay.shortlink.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.shortlink.model.ShortLinkEntry;

import java.util.List;

/**
 * The persistence utility for the short link entry service. This utility wraps {@link ShortLinkEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShortLinkEntryPersistence
 * @see ShortLinkEntryPersistenceImpl
 * @generated
 */
public class ShortLinkEntryUtil {
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
	public static void clearCache(ShortLinkEntry shortLinkEntry) {
		getPersistence().clearCache(shortLinkEntry);
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
	public static List<ShortLinkEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ShortLinkEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ShortLinkEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ShortLinkEntry update(ShortLinkEntry shortLinkEntry)
		throws SystemException {
		return getPersistence().update(shortLinkEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ShortLinkEntry update(ShortLinkEntry shortLinkEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(shortLinkEntry, serviceContext);
	}

	/**
	* Returns all the short link entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the short link entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @return the range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the short link entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the short link entries before and after the current short link entry in the ordered set where uuid = &#63;.
	*
	* @param shortLinkEntryId the primary key of the current short link entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry[] findByUuid_PrevAndNext(
		long shortLinkEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(shortLinkEntryId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the short link entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of short link entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the short link entries where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByLtModifiedDate(
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the short link entries where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @return the range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByLtModifiedDate(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtModifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the short link entries where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByLtModifiedDate(
		java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByLtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByLtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByLtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByLtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the short link entries before and after the current short link entry in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param shortLinkEntryId the primary key of the current short link entry
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry[] findByLtModifiedDate_PrevAndNext(
		long shortLinkEntryId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByLtModifiedDate_PrevAndNext(shortLinkEntryId,
			modifiedDate, orderByComparator);
	}

	/**
	* Removes all the short link entries where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of short link entries where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the short link entry where shortURL = &#63; or throws a {@link com.liferay.shortlink.NoSuchEntryException} if it could not be found.
	*
	* @param shortURL the short u r l
	* @return the matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByShortURL(
		java.lang.String shortURL)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().findByShortURL(shortURL);
	}

	/**
	* Returns the short link entry where shortURL = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param shortURL the short u r l
	* @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByShortURL(
		java.lang.String shortURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByShortURL(shortURL);
	}

	/**
	* Returns the short link entry where shortURL = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param shortURL the short u r l
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByShortURL(
		java.lang.String shortURL, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByShortURL(shortURL, retrieveFromCache);
	}

	/**
	* Removes the short link entry where shortURL = &#63; from the database.
	*
	* @param shortURL the short u r l
	* @return the short link entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry removeByShortURL(
		java.lang.String shortURL)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().removeByShortURL(shortURL);
	}

	/**
	* Returns the number of short link entries where shortURL = &#63;.
	*
	* @param shortURL the short u r l
	* @return the number of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByShortURL(java.lang.String shortURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByShortURL(shortURL);
	}

	/**
	* Returns all the short link entries where autogenerated = &#63;.
	*
	* @param autogenerated the autogenerated
	* @return the matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByAutogenerated(
		boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAutogenerated(autogenerated);
	}

	/**
	* Returns a range of all the short link entries where autogenerated = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param autogenerated the autogenerated
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @return the range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByAutogenerated(
		boolean autogenerated, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAutogenerated(autogenerated, start, end);
	}

	/**
	* Returns an ordered range of all the short link entries where autogenerated = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param autogenerated the autogenerated
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByAutogenerated(
		boolean autogenerated, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAutogenerated(autogenerated, start, end,
			orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where autogenerated = &#63;.
	*
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByAutogenerated_First(
		boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByAutogenerated_First(autogenerated, orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where autogenerated = &#63;.
	*
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByAutogenerated_First(
		boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAutogenerated_First(autogenerated, orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where autogenerated = &#63;.
	*
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByAutogenerated_Last(
		boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByAutogenerated_Last(autogenerated, orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where autogenerated = &#63;.
	*
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByAutogenerated_Last(
		boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAutogenerated_Last(autogenerated, orderByComparator);
	}

	/**
	* Returns the short link entries before and after the current short link entry in the ordered set where autogenerated = &#63;.
	*
	* @param shortLinkEntryId the primary key of the current short link entry
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry[] findByAutogenerated_PrevAndNext(
		long shortLinkEntryId, boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByAutogenerated_PrevAndNext(shortLinkEntryId,
			autogenerated, orderByComparator);
	}

	/**
	* Removes all the short link entries where autogenerated = &#63; from the database.
	*
	* @param autogenerated the autogenerated
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAutogenerated(boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAutogenerated(autogenerated);
	}

	/**
	* Returns the number of short link entries where autogenerated = &#63;.
	*
	* @param autogenerated the autogenerated
	* @return the number of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAutogenerated(boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAutogenerated(autogenerated);
	}

	/**
	* Returns all the short link entries where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @return the matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByOURL_A(
		java.lang.String originalURL, boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOURL_A(originalURL, autogenerated);
	}

	/**
	* Returns a range of all the short link entries where originalURL = &#63; and autogenerated = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @return the range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByOURL_A(
		java.lang.String originalURL, boolean autogenerated, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOURL_A(originalURL, autogenerated, start, end);
	}

	/**
	* Returns an ordered range of all the short link entries where originalURL = &#63; and autogenerated = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findByOURL_A(
		java.lang.String originalURL, boolean autogenerated, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOURL_A(originalURL, autogenerated, start, end,
			orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByOURL_A_First(
		java.lang.String originalURL, boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByOURL_A_First(originalURL, autogenerated,
			orderByComparator);
	}

	/**
	* Returns the first short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByOURL_A_First(
		java.lang.String originalURL, boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOURL_A_First(originalURL, autogenerated,
			orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByOURL_A_Last(
		java.lang.String originalURL, boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByOURL_A_Last(originalURL, autogenerated,
			orderByComparator);
	}

	/**
	* Returns the last short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByOURL_A_Last(
		java.lang.String originalURL, boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOURL_A_Last(originalURL, autogenerated,
			orderByComparator);
	}

	/**
	* Returns the short link entries before and after the current short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param shortLinkEntryId the primary key of the current short link entry
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry[] findByOURL_A_PrevAndNext(
		long shortLinkEntryId, java.lang.String originalURL,
		boolean autogenerated,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence()
				   .findByOURL_A_PrevAndNext(shortLinkEntryId, originalURL,
			autogenerated, orderByComparator);
	}

	/**
	* Removes all the short link entries where originalURL = &#63; and autogenerated = &#63; from the database.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOURL_A(java.lang.String originalURL,
		boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOURL_A(originalURL, autogenerated);
	}

	/**
	* Returns the number of short link entries where originalURL = &#63; and autogenerated = &#63;.
	*
	* @param originalURL the original u r l
	* @param autogenerated the autogenerated
	* @return the number of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOURL_A(java.lang.String originalURL,
		boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOURL_A(originalURL, autogenerated);
	}

	/**
	* Returns the short link entry where shortURL = &#63; and autogenerated = &#63; or throws a {@link com.liferay.shortlink.NoSuchEntryException} if it could not be found.
	*
	* @param shortURL the short u r l
	* @param autogenerated the autogenerated
	* @return the matching short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findBySURL_A(
		java.lang.String shortURL, boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().findBySURL_A(shortURL, autogenerated);
	}

	/**
	* Returns the short link entry where shortURL = &#63; and autogenerated = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param shortURL the short u r l
	* @param autogenerated the autogenerated
	* @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchBySURL_A(
		java.lang.String shortURL, boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySURL_A(shortURL, autogenerated);
	}

	/**
	* Returns the short link entry where shortURL = &#63; and autogenerated = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param shortURL the short u r l
	* @param autogenerated the autogenerated
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchBySURL_A(
		java.lang.String shortURL, boolean autogenerated,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySURL_A(shortURL, autogenerated, retrieveFromCache);
	}

	/**
	* Removes the short link entry where shortURL = &#63; and autogenerated = &#63; from the database.
	*
	* @param shortURL the short u r l
	* @param autogenerated the autogenerated
	* @return the short link entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry removeBySURL_A(
		java.lang.String shortURL, boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().removeBySURL_A(shortURL, autogenerated);
	}

	/**
	* Returns the number of short link entries where shortURL = &#63; and autogenerated = &#63;.
	*
	* @param shortURL the short u r l
	* @param autogenerated the autogenerated
	* @return the number of matching short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySURL_A(java.lang.String shortURL,
		boolean autogenerated)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySURL_A(shortURL, autogenerated);
	}

	/**
	* Caches the short link entry in the entity cache if it is enabled.
	*
	* @param shortLinkEntry the short link entry
	*/
	public static void cacheResult(
		com.liferay.shortlink.model.ShortLinkEntry shortLinkEntry) {
		getPersistence().cacheResult(shortLinkEntry);
	}

	/**
	* Caches the short link entries in the entity cache if it is enabled.
	*
	* @param shortLinkEntries the short link entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.shortlink.model.ShortLinkEntry> shortLinkEntries) {
		getPersistence().cacheResult(shortLinkEntries);
	}

	/**
	* Creates a new short link entry with the primary key. Does not add the short link entry to the database.
	*
	* @param shortLinkEntryId the primary key for the new short link entry
	* @return the new short link entry
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry create(
		long shortLinkEntryId) {
		return getPersistence().create(shortLinkEntryId);
	}

	/**
	* Removes the short link entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param shortLinkEntryId the primary key of the short link entry
	* @return the short link entry that was removed
	* @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry remove(
		long shortLinkEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().remove(shortLinkEntryId);
	}

	public static com.liferay.shortlink.model.ShortLinkEntry updateImpl(
		com.liferay.shortlink.model.ShortLinkEntry shortLinkEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(shortLinkEntry);
	}

	/**
	* Returns the short link entry with the primary key or throws a {@link com.liferay.shortlink.NoSuchEntryException} if it could not be found.
	*
	* @param shortLinkEntryId the primary key of the short link entry
	* @return the short link entry
	* @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry findByPrimaryKey(
		long shortLinkEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.shortlink.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(shortLinkEntryId);
	}

	/**
	* Returns the short link entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param shortLinkEntryId the primary key of the short link entry
	* @return the short link entry, or <code>null</code> if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry fetchByPrimaryKey(
		long shortLinkEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(shortLinkEntryId);
	}

	/**
	* Returns all the short link entries.
	*
	* @return the short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the short link entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @return the range of short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the short link entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the short link entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of short link entries.
	*
	* @return the number of short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ShortLinkEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ShortLinkEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.shortlink.service.ClpSerializer.getServletContextName(),
					ShortLinkEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(ShortLinkEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(ShortLinkEntryPersistence persistence) {
	}

	private static ShortLinkEntryPersistence _persistence;
}