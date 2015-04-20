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

package com.liferay.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.sync.model.SyncDLFileVersionDiff;

import java.util.List;

/**
 * The persistence utility for the sync d l file version diff service. This utility wraps {@link com.liferay.sync.service.persistence.impl.SyncDLFileVersionDiffPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffPersistence
 * @see com.liferay.sync.service.persistence.impl.SyncDLFileVersionDiffPersistenceImpl
 * @generated
 */
@ProviderType
public class SyncDLFileVersionDiffUtil {
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
	public static void clearCache(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		getPersistence().clearCache(syncDLFileVersionDiff);
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
	public static List<SyncDLFileVersionDiff> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SyncDLFileVersionDiff> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SyncDLFileVersionDiff> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SyncDLFileVersionDiff update(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		return getPersistence().update(syncDLFileVersionDiff);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SyncDLFileVersionDiff update(
		SyncDLFileVersionDiff syncDLFileVersionDiff,
		ServiceContext serviceContext) {
		return getPersistence().update(syncDLFileVersionDiff, serviceContext);
	}

	/**
	* Returns all the sync d l file version diffs where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @return the matching sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findByFileEntryId(
		long fileEntryId) {
		return getPersistence().findByFileEntryId(fileEntryId);
	}

	/**
	* Returns a range of all the sync d l file version diffs where fileEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fileEntryId the file entry ID
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @return the range of matching sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findByFileEntryId(
		long fileEntryId, int start, int end) {
		return getPersistence().findByFileEntryId(fileEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l file version diffs where fileEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fileEntryId the file entry ID
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .findByFileEntryId(fileEntryId, start, end, orderByComparator);
	}

	/**
	* Returns the first sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff findByFileEntryId_First(
		long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByFileEntryId_First(fileEntryId, orderByComparator);
	}

	/**
	* Returns the first sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff fetchByFileEntryId_First(
		long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .fetchByFileEntryId_First(fileEntryId, orderByComparator);
	}

	/**
	* Returns the last sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff findByFileEntryId_Last(
		long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByFileEntryId_Last(fileEntryId, orderByComparator);
	}

	/**
	* Returns the last sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff fetchByFileEntryId_Last(
		long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .fetchByFileEntryId_Last(fileEntryId, orderByComparator);
	}

	/**
	* Returns the sync d l file version diffs before and after the current sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param syncDLFileVersionDiffId the primary key of the current sync d l file version diff
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public static SyncDLFileVersionDiff[] findByFileEntryId_PrevAndNext(
		long syncDLFileVersionDiffId, long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByFileEntryId_PrevAndNext(syncDLFileVersionDiffId,
			fileEntryId, orderByComparator);
	}

	/**
	* Removes all the sync d l file version diffs where fileEntryId = &#63; from the database.
	*
	* @param fileEntryId the file entry ID
	*/
	public static void removeByFileEntryId(long fileEntryId) {
		getPersistence().removeByFileEntryId(fileEntryId);
	}

	/**
	* Returns the number of sync d l file version diffs where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @return the number of matching sync d l file version diffs
	*/
	public static int countByFileEntryId(long fileEntryId) {
		return getPersistence().countByFileEntryId(fileEntryId);
	}

	/**
	* Returns all the sync d l file version diffs where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @return the matching sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findByExpirationDate(
		java.util.Date expirationDate) {
		return getPersistence().findByExpirationDate(expirationDate);
	}

	/**
	* Returns a range of all the sync d l file version diffs where expirationDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expirationDate the expiration date
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @return the range of matching sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findByExpirationDate(
		java.util.Date expirationDate, int start, int end) {
		return getPersistence().findByExpirationDate(expirationDate, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l file version diffs where expirationDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expirationDate the expiration date
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findByExpirationDate(
		java.util.Date expirationDate, int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .findByExpirationDate(expirationDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff findByExpirationDate_First(
		java.util.Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByExpirationDate_First(expirationDate, orderByComparator);
	}

	/**
	* Returns the first sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff fetchByExpirationDate_First(
		java.util.Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .fetchByExpirationDate_First(expirationDate,
			orderByComparator);
	}

	/**
	* Returns the last sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff findByExpirationDate_Last(
		java.util.Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByExpirationDate_Last(expirationDate, orderByComparator);
	}

	/**
	* Returns the last sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff fetchByExpirationDate_Last(
		java.util.Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence()
				   .fetchByExpirationDate_Last(expirationDate, orderByComparator);
	}

	/**
	* Returns the sync d l file version diffs before and after the current sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param syncDLFileVersionDiffId the primary key of the current sync d l file version diff
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public static SyncDLFileVersionDiff[] findByExpirationDate_PrevAndNext(
		long syncDLFileVersionDiffId, java.util.Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByExpirationDate_PrevAndNext(syncDLFileVersionDiffId,
			expirationDate, orderByComparator);
	}

	/**
	* Removes all the sync d l file version diffs where expirationDate &lt; &#63; from the database.
	*
	* @param expirationDate the expiration date
	*/
	public static void removeByExpirationDate(java.util.Date expirationDate) {
		getPersistence().removeByExpirationDate(expirationDate);
	}

	/**
	* Returns the number of sync d l file version diffs where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @return the number of matching sync d l file version diffs
	*/
	public static int countByExpirationDate(java.util.Date expirationDate) {
		return getPersistence().countByExpirationDate(expirationDate);
	}

	/**
	* Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or throws a {@link NoSuchDLFileVersionDiffException} if it could not be found.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff findByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .findByF_S_T(fileEntryId, sourceFileVersionId,
			targetFileVersionId);
	}

	/**
	* Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff fetchByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId) {
		return getPersistence()
				   .fetchByF_S_T(fileEntryId, sourceFileVersionId,
			targetFileVersionId);
	}

	/**
	* Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public static SyncDLFileVersionDiff fetchByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_S_T(fileEntryId, sourceFileVersionId,
			targetFileVersionId, retrieveFromCache);
	}

	/**
	* Removes the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; from the database.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the sync d l file version diff that was removed
	*/
	public static SyncDLFileVersionDiff removeByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence()
				   .removeByF_S_T(fileEntryId, sourceFileVersionId,
			targetFileVersionId);
	}

	/**
	* Returns the number of sync d l file version diffs where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the number of matching sync d l file version diffs
	*/
	public static int countByF_S_T(long fileEntryId, long sourceFileVersionId,
		long targetFileVersionId) {
		return getPersistence()
				   .countByF_S_T(fileEntryId, sourceFileVersionId,
			targetFileVersionId);
	}

	/**
	* Caches the sync d l file version diff in the entity cache if it is enabled.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	*/
	public static void cacheResult(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		getPersistence().cacheResult(syncDLFileVersionDiff);
	}

	/**
	* Caches the sync d l file version diffs in the entity cache if it is enabled.
	*
	* @param syncDLFileVersionDiffs the sync d l file version diffs
	*/
	public static void cacheResult(
		List<SyncDLFileVersionDiff> syncDLFileVersionDiffs) {
		getPersistence().cacheResult(syncDLFileVersionDiffs);
	}

	/**
	* Creates a new sync d l file version diff with the primary key. Does not add the sync d l file version diff to the database.
	*
	* @param syncDLFileVersionDiffId the primary key for the new sync d l file version diff
	* @return the new sync d l file version diff
	*/
	public static SyncDLFileVersionDiff create(long syncDLFileVersionDiffId) {
		return getPersistence().create(syncDLFileVersionDiffId);
	}

	/**
	* Removes the sync d l file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff that was removed
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public static SyncDLFileVersionDiff remove(long syncDLFileVersionDiffId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence().remove(syncDLFileVersionDiffId);
	}

	public static SyncDLFileVersionDiff updateImpl(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		return getPersistence().updateImpl(syncDLFileVersionDiff);
	}

	/**
	* Returns the sync d l file version diff with the primary key or throws a {@link NoSuchDLFileVersionDiffException} if it could not be found.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public static SyncDLFileVersionDiff findByPrimaryKey(
		long syncDLFileVersionDiffId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException {
		return getPersistence().findByPrimaryKey(syncDLFileVersionDiffId);
	}

	/**
	* Returns the sync d l file version diff with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff, or <code>null</code> if a sync d l file version diff with the primary key could not be found
	*/
	public static SyncDLFileVersionDiff fetchByPrimaryKey(
		long syncDLFileVersionDiffId) {
		return getPersistence().fetchByPrimaryKey(syncDLFileVersionDiffId);
	}

	public static java.util.Map<java.io.Serializable, SyncDLFileVersionDiff> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sync d l file version diffs.
	*
	* @return the sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sync d l file version diffs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @return the range of sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sync d l file version diffs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync d l file version diffs
	*/
	public static List<SyncDLFileVersionDiff> findAll(int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the sync d l file version diffs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sync d l file version diffs.
	*
	* @return the number of sync d l file version diffs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SyncDLFileVersionDiffPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SyncDLFileVersionDiffPersistence)PortletBeanLocatorUtil.locate(com.liferay.sync.service.ClpSerializer.getServletContextName(),
					SyncDLFileVersionDiffPersistence.class.getName());

			ReferenceRegistry.registerReference(SyncDLFileVersionDiffUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(SyncDLFileVersionDiffPersistence persistence) {
	}

	private static SyncDLFileVersionDiffPersistence _persistence;
}