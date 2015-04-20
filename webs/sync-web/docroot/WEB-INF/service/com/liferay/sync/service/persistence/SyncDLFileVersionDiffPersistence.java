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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sync.model.SyncDLFileVersionDiff;

/**
 * The persistence interface for the sync d l file version diff service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.persistence.impl.SyncDLFileVersionDiffPersistenceImpl
 * @see SyncDLFileVersionDiffUtil
 * @generated
 */
@ProviderType
public interface SyncDLFileVersionDiffPersistence extends BasePersistence<SyncDLFileVersionDiff> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncDLFileVersionDiffUtil} to access the sync d l file version diff persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sync d l file version diffs where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @return the matching sync d l file version diffs
	*/
	public java.util.List<SyncDLFileVersionDiff> findByFileEntryId(
		long fileEntryId);

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
	public java.util.List<SyncDLFileVersionDiff> findByFileEntryId(
		long fileEntryId, int start, int end);

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
	public java.util.List<SyncDLFileVersionDiff> findByFileEntryId(
		long fileEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Returns the first sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff findByFileEntryId_First(long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the first sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff fetchByFileEntryId_First(long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Returns the last sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff findByFileEntryId_Last(long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the last sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff fetchByFileEntryId_Last(long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Returns the sync d l file version diffs before and after the current sync d l file version diff in the ordered set where fileEntryId = &#63;.
	*
	* @param syncDLFileVersionDiffId the primary key of the current sync d l file version diff
	* @param fileEntryId the file entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public SyncDLFileVersionDiff[] findByFileEntryId_PrevAndNext(
		long syncDLFileVersionDiffId, long fileEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Removes all the sync d l file version diffs where fileEntryId = &#63; from the database.
	*
	* @param fileEntryId the file entry ID
	*/
	public void removeByFileEntryId(long fileEntryId);

	/**
	* Returns the number of sync d l file version diffs where fileEntryId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @return the number of matching sync d l file version diffs
	*/
	public int countByFileEntryId(long fileEntryId);

	/**
	* Returns all the sync d l file version diffs where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @return the matching sync d l file version diffs
	*/
	public java.util.List<SyncDLFileVersionDiff> findByExpirationDate(
		java.util.Date expirationDate);

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
	public java.util.List<SyncDLFileVersionDiff> findByExpirationDate(
		java.util.Date expirationDate, int start, int end);

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
	public java.util.List<SyncDLFileVersionDiff> findByExpirationDate(
		java.util.Date expirationDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Returns the first sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff findByExpirationDate_First(
		java.util.Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the first sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff fetchByExpirationDate_First(
		java.util.Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Returns the last sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff findByExpirationDate_Last(
		java.util.Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the last sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff fetchByExpirationDate_Last(
		java.util.Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Returns the sync d l file version diffs before and after the current sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	*
	* @param syncDLFileVersionDiffId the primary key of the current sync d l file version diff
	* @param expirationDate the expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public SyncDLFileVersionDiff[] findByExpirationDate_PrevAndNext(
		long syncDLFileVersionDiffId, java.util.Date expirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Removes all the sync d l file version diffs where expirationDate &lt; &#63; from the database.
	*
	* @param expirationDate the expiration date
	*/
	public void removeByExpirationDate(java.util.Date expirationDate);

	/**
	* Returns the number of sync d l file version diffs where expirationDate &lt; &#63;.
	*
	* @param expirationDate the expiration date
	* @return the number of matching sync d l file version diffs
	*/
	public int countByExpirationDate(java.util.Date expirationDate);

	/**
	* Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or throws a {@link NoSuchDLFileVersionDiffException} if it could not be found.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the matching sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff findByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff fetchByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId);

	/**
	* Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	*/
	public SyncDLFileVersionDiff fetchByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId,
		boolean retrieveFromCache);

	/**
	* Removes the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; from the database.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the sync d l file version diff that was removed
	*/
	public SyncDLFileVersionDiff removeByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the number of sync d l file version diffs where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63;.
	*
	* @param fileEntryId the file entry ID
	* @param sourceFileVersionId the source file version ID
	* @param targetFileVersionId the target file version ID
	* @return the number of matching sync d l file version diffs
	*/
	public int countByF_S_T(long fileEntryId, long sourceFileVersionId,
		long targetFileVersionId);

	/**
	* Caches the sync d l file version diff in the entity cache if it is enabled.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	*/
	public void cacheResult(SyncDLFileVersionDiff syncDLFileVersionDiff);

	/**
	* Caches the sync d l file version diffs in the entity cache if it is enabled.
	*
	* @param syncDLFileVersionDiffs the sync d l file version diffs
	*/
	public void cacheResult(
		java.util.List<SyncDLFileVersionDiff> syncDLFileVersionDiffs);

	/**
	* Creates a new sync d l file version diff with the primary key. Does not add the sync d l file version diff to the database.
	*
	* @param syncDLFileVersionDiffId the primary key for the new sync d l file version diff
	* @return the new sync d l file version diff
	*/
	public SyncDLFileVersionDiff create(long syncDLFileVersionDiffId);

	/**
	* Removes the sync d l file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff that was removed
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public SyncDLFileVersionDiff remove(long syncDLFileVersionDiffId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	public SyncDLFileVersionDiff updateImpl(
		SyncDLFileVersionDiff syncDLFileVersionDiff);

	/**
	* Returns the sync d l file version diff with the primary key or throws a {@link NoSuchDLFileVersionDiffException} if it could not be found.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff
	* @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	*/
	public SyncDLFileVersionDiff findByPrimaryKey(long syncDLFileVersionDiffId)
		throws com.liferay.sync.NoSuchDLFileVersionDiffException;

	/**
	* Returns the sync d l file version diff with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff, or <code>null</code> if a sync d l file version diff with the primary key could not be found
	*/
	public SyncDLFileVersionDiff fetchByPrimaryKey(long syncDLFileVersionDiffId);

	@Override
	public java.util.Map<java.io.Serializable, SyncDLFileVersionDiff> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sync d l file version diffs.
	*
	* @return the sync d l file version diffs
	*/
	public java.util.List<SyncDLFileVersionDiff> findAll();

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
	public java.util.List<SyncDLFileVersionDiff> findAll(int start, int end);

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
	public java.util.List<SyncDLFileVersionDiff> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncDLFileVersionDiff> orderByComparator);

	/**
	* Removes all the sync d l file version diffs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sync d l file version diffs.
	*
	* @return the number of sync d l file version diffs
	*/
	public int countAll();
}