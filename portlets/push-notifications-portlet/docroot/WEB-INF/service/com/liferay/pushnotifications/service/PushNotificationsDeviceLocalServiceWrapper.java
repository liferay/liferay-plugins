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

package com.liferay.pushnotifications.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PushNotificationsDeviceLocalService}.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceLocalService
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceLocalServiceWrapper
	implements PushNotificationsDeviceLocalService,
		ServiceWrapper<PushNotificationsDeviceLocalService> {
	public PushNotificationsDeviceLocalServiceWrapper(
		PushNotificationsDeviceLocalService pushNotificationsDeviceLocalService) {
		_pushNotificationsDeviceLocalService = pushNotificationsDeviceLocalService;
	}

	/**
	* Adds the push notifications device to the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was added
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice addPushNotificationsDevice(
		com.liferay.pushnotifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return _pushNotificationsDeviceLocalService.addPushNotificationsDevice(pushNotificationsDevice);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice addPushNotificationsDevice(
		long userId, java.lang.String platform, java.lang.String token) {
		return _pushNotificationsDeviceLocalService.addPushNotificationsDevice(userId,
			platform, token);
	}

	/**
	* Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	*
	* @param pushNotificationsDeviceId the primary key for the new push notifications device
	* @return the new push notifications device
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice createPushNotificationsDevice(
		long pushNotificationsDeviceId) {
		return _pushNotificationsDeviceLocalService.createPushNotificationsDevice(pushNotificationsDeviceId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the push notifications device from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was removed
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		com.liferay.pushnotifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return _pushNotificationsDeviceLocalService.deletePushNotificationsDevice(pushNotificationsDevice);
	}

	/**
	* Deletes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device that was removed
	* @throws PortalException if a push notifications device with the primary key could not be found
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		long pushNotificationsDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceLocalService.deletePushNotificationsDevice(pushNotificationsDeviceId);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceLocalService.deletePushNotificationsDevice(token);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pushNotificationsDeviceLocalService.dynamicQuery();
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
		return _pushNotificationsDeviceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushNotificationsDeviceLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushNotificationsDeviceLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _pushNotificationsDeviceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _pushNotificationsDeviceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice fetchPushNotificationsDevice(
		long pushNotificationsDeviceId) {
		return _pushNotificationsDeviceLocalService.fetchPushNotificationsDevice(pushNotificationsDeviceId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _pushNotificationsDeviceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _pushNotificationsDeviceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _pushNotificationsDeviceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the push notifications device with the primary key.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device
	* @throws PortalException if a push notifications device with the primary key could not be found
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice getPushNotificationsDevice(
		long pushNotificationsDeviceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceLocalService.getPushNotificationsDevice(pushNotificationsDeviceId);
	}

	/**
	* Returns a range of all the push notifications devices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications devices
	* @param end the upper bound of the range of push notifications devices (not inclusive)
	* @return the range of push notifications devices
	*/
	@Override
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end) {
		return _pushNotificationsDeviceLocalService.getPushNotificationsDevices(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _pushNotificationsDeviceLocalService.getPushNotificationsDevices(start,
			end, orderByComparator);
	}

	/**
	* Returns the number of push notifications devices.
	*
	* @return the number of push notifications devices
	*/
	@Override
	public int getPushNotificationsDevicesCount() {
		return _pushNotificationsDeviceLocalService.getPushNotificationsDevicesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _pushNotificationsDeviceLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public void resetPushNotificationSenders() {
		_pushNotificationsDeviceLocalService.resetPushNotificationSenders();
	}

	@Override
	public void sendPushNotification(java.lang.String platform,
		java.util.List<java.lang.String> tokens,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceLocalService.sendPushNotification(platform,
			tokens, payloadJSONObject);
	}

	@Override
	public void sendPushNotification(java.lang.String platform,
		java.util.List<java.lang.String> tokens,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject,
		java.util.Map<java.lang.String, java.lang.Object> configuration)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceLocalService.sendPushNotification(platform,
			tokens, payloadJSONObject, configuration);
	}

	@Override
	public void sendPushNotification(long[] toUserIds,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceLocalService.sendPushNotification(toUserIds,
			payloadJSONObject);
	}

	/**
	* Updates the push notifications device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was updated
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice updatePushNotificationsDevice(
		com.liferay.pushnotifications.model.PushNotificationsDevice pushNotificationsDevice) {
		return _pushNotificationsDeviceLocalService.updatePushNotificationsDevice(pushNotificationsDevice);
	}

	@Override
	public void updateToken(java.lang.String oldToken, java.lang.String newToken)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceLocalService.updateToken(oldToken, newToken);
	}

	@Override
	public PushNotificationsDeviceLocalService getWrappedService() {
		return _pushNotificationsDeviceLocalService;
	}

	@Override
	public void setWrappedService(
		PushNotificationsDeviceLocalService pushNotificationsDeviceLocalService) {
		_pushNotificationsDeviceLocalService = pushNotificationsDeviceLocalService;
	}

	private PushNotificationsDeviceLocalService _pushNotificationsDeviceLocalService;
}