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

package com.liferay.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDeviceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceLocalService
 * @generated
 */
@ProviderType
public class SyncDeviceLocalServiceWrapper implements SyncDeviceLocalService,
	ServiceWrapper<SyncDeviceLocalService> {
	public SyncDeviceLocalServiceWrapper(
		SyncDeviceLocalService syncDeviceLocalService) {
		_syncDeviceLocalService = syncDeviceLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _syncDeviceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncDeviceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _syncDeviceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _syncDeviceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the sync device to the database. Also notifies the appropriate model listeners.
	*
	* @param syncDevice the sync device
	* @return the sync device that was added
	*/
	@Override
	public com.liferay.sync.model.SyncDevice addSyncDevice(
		com.liferay.sync.model.SyncDevice syncDevice) {
		return _syncDeviceLocalService.addSyncDevice(syncDevice);
	}

	@Override
	public com.liferay.sync.model.SyncDevice addSyncDevice(long userId,
		java.lang.String type, int buildNumber, int featureSet)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.addSyncDevice(userId, type, buildNumber,
			featureSet);
	}

	/**
	* Creates a new sync device with the primary key. Does not add the sync device to the database.
	*
	* @param syncDeviceId the primary key for the new sync device
	* @return the new sync device
	*/
	@Override
	public com.liferay.sync.model.SyncDevice createSyncDevice(long syncDeviceId) {
		return _syncDeviceLocalService.createSyncDevice(syncDeviceId);
	}

	/**
	* Deletes the sync device from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDevice the sync device
	* @return the sync device that was removed
	*/
	@Override
	public com.liferay.sync.model.SyncDevice deleteSyncDevice(
		com.liferay.sync.model.SyncDevice syncDevice) {
		return _syncDeviceLocalService.deleteSyncDevice(syncDevice);
	}

	/**
	* Deletes the sync device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device that was removed
	* @throws PortalException if a sync device with the primary key could not be found
	*/
	@Override
	public com.liferay.sync.model.SyncDevice deleteSyncDevice(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.deleteSyncDevice(syncDeviceId);
	}

	@Override
	public com.liferay.sync.model.SyncDevice fetchSyncDevice(long syncDeviceId) {
		return _syncDeviceLocalService.fetchSyncDevice(syncDeviceId);
	}

	/**
	* Returns the sync device with the matching UUID and company.
	*
	* @param uuid the sync device's UUID
	* @param companyId the primary key of the company
	* @return the matching sync device, or <code>null</code> if a matching sync device could not be found
	*/
	@Override
	public com.liferay.sync.model.SyncDevice fetchSyncDeviceByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _syncDeviceLocalService.fetchSyncDeviceByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the sync device with the primary key.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device
	* @throws PortalException if a sync device with the primary key could not be found
	*/
	@Override
	public com.liferay.sync.model.SyncDevice getSyncDevice(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.getSyncDevice(syncDeviceId);
	}

	/**
	* Returns the sync device with the matching UUID and company.
	*
	* @param uuid the sync device's UUID
	* @param companyId the primary key of the company
	* @return the matching sync device
	* @throws PortalException if a matching sync device could not be found
	*/
	@Override
	public com.liferay.sync.model.SyncDevice getSyncDeviceByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.getSyncDeviceByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Updates the sync device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncDevice the sync device
	* @return the sync device that was updated
	*/
	@Override
	public com.liferay.sync.model.SyncDevice updateSyncDevice(
		com.liferay.sync.model.SyncDevice syncDevice) {
		return _syncDeviceLocalService.updateSyncDevice(syncDevice);
	}

	@Override
	public com.liferay.sync.model.SyncDevice updateSyncDevice(
		long syncDeviceId, java.lang.String type, int buildNumber,
		int featureSet, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceLocalService.updateSyncDevice(syncDeviceId, type,
			buildNumber, featureSet, status);
	}

	/**
	* Returns the number of sync devices.
	*
	* @return the number of sync devices
	*/
	@Override
	public int getSyncDevicesCount() {
		return _syncDeviceLocalService.getSyncDevicesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDeviceLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _syncDeviceLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _syncDeviceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _syncDeviceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _syncDeviceLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the sync devices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync devices
	* @param end the upper bound of the range of sync devices (not inclusive)
	* @return the range of sync devices
	*/
	@Override
	public java.util.List<com.liferay.sync.model.SyncDevice> getSyncDevices(
		int start, int end) {
		return _syncDeviceLocalService.getSyncDevices(start, end);
	}

	@Override
	public java.util.List<com.liferay.sync.model.SyncDevice> search(
		long companyId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.sync.model.SyncDevice> orderByComparator) {
		return _syncDeviceLocalService.search(companyId, keywords, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _syncDeviceLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _syncDeviceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void updateStatus(long syncDeviceId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		_syncDeviceLocalService.updateStatus(syncDeviceId, status);
	}

	@Override
	public SyncDeviceLocalService getWrappedService() {
		return _syncDeviceLocalService;
	}

	@Override
	public void setWrappedService(SyncDeviceLocalService syncDeviceLocalService) {
		_syncDeviceLocalService = syncDeviceLocalService;
	}

	private SyncDeviceLocalService _syncDeviceLocalService;
}