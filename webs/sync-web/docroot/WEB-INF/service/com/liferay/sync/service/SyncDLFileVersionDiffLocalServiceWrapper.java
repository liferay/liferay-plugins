/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sync.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDLFileVersionDiffLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffLocalService
 * @generated
 */
public class SyncDLFileVersionDiffLocalServiceWrapper
	implements SyncDLFileVersionDiffLocalService,
		ServiceWrapper<SyncDLFileVersionDiffLocalService> {
	public SyncDLFileVersionDiffLocalServiceWrapper(
		SyncDLFileVersionDiffLocalService syncDLFileVersionDiffLocalService) {
		_syncDLFileVersionDiffLocalService = syncDLFileVersionDiffLocalService;
	}

	/**
	* Adds the sync d l file version diff to the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	* @return the sync d l file version diff that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff addSyncDLFileVersionDiff(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.addSyncDLFileVersionDiff(syncDLFileVersionDiff);
	}

	/**
	* Creates a new sync d l file version diff with the primary key. Does not add the sync d l file version diff to the database.
	*
	* @param syncDLFileVersionDiffId the primary key for the new sync d l file version diff
	* @return the new sync d l file version diff
	*/
	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff createSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId) {
		return _syncDLFileVersionDiffLocalService.createSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	/**
	* Deletes the sync d l file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff that was removed
	* @throws PortalException if a sync d l file version diff with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.deleteSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	/**
	* Deletes the sync d l file version diff from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	* @return the sync d l file version diff that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff deleteSyncDLFileVersionDiff(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.deleteSyncDLFileVersionDiff(syncDLFileVersionDiff);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncDLFileVersionDiffLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff fetchSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.fetchSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	/**
	* Returns the sync d l file version diff with the primary key.
	*
	* @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	* @return the sync d l file version diff
	* @throws PortalException if a sync d l file version diff with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff getSyncDLFileVersionDiff(
		long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.getSyncDLFileVersionDiff(syncDLFileVersionDiffId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the sync d l file version diffs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l file version diffs
	* @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	* @return the range of sync d l file version diffs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.sync.model.SyncDLFileVersionDiff> getSyncDLFileVersionDiffs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.getSyncDLFileVersionDiffs(start,
			end);
	}

	/**
	* Returns the number of sync d l file version diffs.
	*
	* @return the number of sync d l file version diffs
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSyncDLFileVersionDiffsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.getSyncDLFileVersionDiffsCount();
	}

	/**
	* Updates the sync d l file version diff in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncDLFileVersionDiff the sync d l file version diff
	* @return the sync d l file version diff that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff updateSyncDLFileVersionDiff(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.updateSyncDLFileVersionDiff(syncDLFileVersionDiff);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _syncDLFileVersionDiffLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_syncDLFileVersionDiffLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDLFileVersionDiffLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff addSyncDLFileVersionDiff(
		long fileEntryId, long sourceFileVersionId,
		long destinationFileVersionId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.addSyncDLFileVersionDiff(fileEntryId,
			sourceFileVersionId, destinationFileVersionId, file);
	}

	@Override
	public void deleteExpiredSyncDLFileVersionDiffs()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_syncDLFileVersionDiffLocalService.deleteExpiredSyncDLFileVersionDiffs();
	}

	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff fetchSyncDLFileVersionDiff(
		long fileEntryId, long sourceFileVersionId,
		long destinationFileVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLFileVersionDiffLocalService.fetchSyncDLFileVersionDiff(fileEntryId,
			sourceFileVersionId, destinationFileVersionId);
	}

	@Override
	public void refreshExpirationDate(long syncDLFileVersionDiffId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_syncDLFileVersionDiffLocalService.refreshExpirationDate(syncDLFileVersionDiffId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SyncDLFileVersionDiffLocalService getWrappedSyncDLFileVersionDiffLocalService() {
		return _syncDLFileVersionDiffLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSyncDLFileVersionDiffLocalService(
		SyncDLFileVersionDiffLocalService syncDLFileVersionDiffLocalService) {
		_syncDLFileVersionDiffLocalService = syncDLFileVersionDiffLocalService;
	}

	@Override
	public SyncDLFileVersionDiffLocalService getWrappedService() {
		return _syncDLFileVersionDiffLocalService;
	}

	@Override
	public void setWrappedService(
		SyncDLFileVersionDiffLocalService syncDLFileVersionDiffLocalService) {
		_syncDLFileVersionDiffLocalService = syncDLFileVersionDiffLocalService;
	}

	private SyncDLFileVersionDiffLocalService _syncDLFileVersionDiffLocalService;
}