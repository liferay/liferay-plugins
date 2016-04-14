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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.pushnotifications.model.PushNotificationsDevice;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for PushNotificationsDevice. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceLocalServiceUtil
 * @see com.liferay.pushnotifications.service.base.PushNotificationsDeviceLocalServiceBaseImpl
 * @see com.liferay.pushnotifications.service.impl.PushNotificationsDeviceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PushNotificationsDeviceLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushNotificationsDeviceLocalServiceUtil} to access the push notifications device local service. Add custom service methods to {@link com.liferay.pushnotifications.service.impl.PushNotificationsDeviceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the push notifications device to the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PushNotificationsDevice addPushNotificationsDevice(
		PushNotificationsDevice pushNotificationsDevice);

	public PushNotificationsDevice addPushNotificationsDevice(long userId,
		java.lang.String platform, java.lang.String token);

	/**
	* Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	*
	* @param pushNotificationsDeviceId the primary key for the new push notifications device
	* @return the new push notifications device
	*/
	public PushNotificationsDevice createPushNotificationsDevice(
		long pushNotificationsDeviceId);

	/**
	* Deletes the push notifications device from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PushNotificationsDevice deletePushNotificationsDevice(
		PushNotificationsDevice pushNotificationsDevice);

	public PushNotificationsDevice deletePushNotificationsDevice(
		java.lang.String token) throws PortalException;

	/**
	* Deletes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device that was removed
	* @throws PortalException if a push notifications device with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PushNotificationsDevice deletePushNotificationsDevice(
		long pushNotificationsDeviceId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushNotificationsDevice fetchPushNotificationsDevice(
		long pushNotificationsDeviceId);

	/**
	* Returns the push notifications device with the primary key.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device
	* @throws PortalException if a push notifications device with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushNotificationsDevice getPushNotificationsDevice(
		long pushNotificationsDeviceId) throws PortalException;

	/**
	* Updates the push notifications device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDevice the push notifications device
	* @return the push notifications device that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PushNotificationsDevice updatePushNotificationsDevice(
		PushNotificationsDevice pushNotificationsDevice);

	/**
	* Returns the number of push notifications devices.
	*
	* @return the number of push notifications devices
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPushNotificationsDevicesCount();

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushNotificationsDevice> getPushNotificationsDevices(
		int start, int end, OrderByComparator orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void resetPushNotificationSenders();

	public void sendPushNotification(java.lang.String platform,
		List<java.lang.String> tokens, JSONObject payloadJSONObject)
		throws PortalException;

	public void sendPushNotification(java.lang.String platform,
		List<java.lang.String> tokens, JSONObject payloadJSONObject,
		Map<java.lang.String, java.lang.Object> configuration)
		throws PortalException;

	public void sendPushNotification(long[] toUserIds,
		JSONObject payloadJSONObject) throws PortalException;

	public void updateToken(java.lang.String oldToken, java.lang.String newToken)
		throws PortalException;
}