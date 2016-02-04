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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDeviceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceLocalService
 * @generated
 */
public class SyncDeviceLocalServiceWrapper implements SyncDeviceLocalService,
	ServiceWrapper<SyncDeviceLocalService> {
	public SyncDeviceLocalServiceWrapper(
		SyncDeviceLocalService syncDeviceLocalService) {
		_syncDeviceLocalService = syncDeviceLocalService;
	}

	/**
	* Adds the sync device to the database. Also notifies the appropriate model listeners.
	*
	* @param syncDevice the sync device
	* @return the sync device that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice addSyncDevice(
		com.liferay.sync.model.SyncDevice syncDevice)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.addSyncDevice(syncDevice);
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
	* Deletes the sync device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device that was removed
	* @throws PortalException if a sync device with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice deleteSyncDevice(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.deleteSyncDevice(syncDeviceId);
	}

	/**
	* Deletes the sync device from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDevice the sync device
	* @return the sync device that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice deleteSyncDevice(
		com.liferay.sync.model.SyncDevice syncDevice)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.deleteSyncDevice(syncDevice);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncDeviceLocalService.dynamicQuery();
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _syncDeviceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _syncDeviceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.sync.model.SyncDevice fetchSyncDevice(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.fetchSyncDevice(syncDeviceId);
	}

	/**
	* Returns the sync device with the matching UUID and company.
	*
	* @param uuid the sync device's UUID
	* @param companyId the primary key of the company
	* @return the matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice fetchSyncDeviceByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.fetchSyncDeviceByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the sync device with the primary key.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device
	* @throws PortalException if a sync device with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice getSyncDevice(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.getSyncDevice(syncDeviceId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sync device with the matching UUID and company.
	*
	* @param uuid the sync device's UUID
	* @param companyId the primary key of the company
	* @return the matching sync device
	* @throws PortalException if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice getSyncDeviceByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.getSyncDeviceByUuidAndCompanyId(uuid,
			companyId);
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.sync.model.SyncDevice> getSyncDevices(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.getSyncDevices(start, end);
	}

	/**
	* Returns the number of sync devices.
	*
	* @return the number of sync devices
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSyncDevicesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.getSyncDevicesCount();
	}

	/**
	* Updates the sync device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncDevice the sync device
	* @return the sync device that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.sync.model.SyncDevice updateSyncDevice(
		com.liferay.sync.model.SyncDevice syncDevice)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.updateSyncDevice(syncDevice);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _syncDeviceLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_syncDeviceLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDeviceLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.sync.model.SyncDevice addSyncDevice(long userId,
		java.lang.String type, int buildNumber, int featureSet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.addSyncDevice(userId, type, buildNumber,
			featureSet);
	}

	@Override
	public java.util.List<com.liferay.sync.model.SyncDevice> search(
		long companyId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.search(companyId, keywords, start, end,
			orderByComparator);
	}

	@Override
	public void updateStatus(long syncDeviceId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_syncDeviceLocalService.updateStatus(syncDeviceId, status);
	}

	@Override
	public com.liferay.sync.model.SyncDevice updateSyncDevice(
		long syncDeviceId, java.lang.String type, int buildNumber,
		int featureSet, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceLocalService.updateSyncDevice(syncDeviceId, type,
			buildNumber, featureSet, status);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SyncDeviceLocalService getWrappedSyncDeviceLocalService() {
		return _syncDeviceLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSyncDeviceLocalService(
		SyncDeviceLocalService syncDeviceLocalService) {
		_syncDeviceLocalService = syncDeviceLocalService;
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