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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PushNotificationsEntryLocalService}.
 *
 * @author Bruno Farache
 * @see PushNotificationsEntryLocalService
 * @generated
 */
public class PushNotificationsEntryLocalServiceWrapper
	implements PushNotificationsEntryLocalService,
		ServiceWrapper<PushNotificationsEntryLocalService> {
	public PushNotificationsEntryLocalServiceWrapper(
		PushNotificationsEntryLocalService pushNotificationsEntryLocalService) {
		_pushNotificationsEntryLocalService = pushNotificationsEntryLocalService;
	}

	/**
	* Adds the push notifications entry to the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsEntry the push notifications entry
	* @return the push notifications entry that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry addPushNotificationsEntry(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.addPushNotificationsEntry(pushNotificationsEntry);
	}

	/**
	* Creates a new push notifications entry with the primary key. Does not add the push notifications entry to the database.
	*
	* @param pushNotificationsEntryId the primary key for the new push notifications entry
	* @return the new push notifications entry
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry createPushNotificationsEntry(
		long pushNotificationsEntryId) {
		return _pushNotificationsEntryLocalService.createPushNotificationsEntry(pushNotificationsEntryId);
	}

	/**
	* Deletes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry that was removed
	* @throws PortalException if a push notifications entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry deletePushNotificationsEntry(
		long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.deletePushNotificationsEntry(pushNotificationsEntryId);
	}

	/**
	* Deletes the push notifications entry from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsEntry the push notifications entry
	* @return the push notifications entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry deletePushNotificationsEntry(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.deletePushNotificationsEntry(pushNotificationsEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pushNotificationsEntryLocalService.dynamicQuery();
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
		return _pushNotificationsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushNotificationsEntryLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushNotificationsEntryLocalService.dynamicQuery(dynamicQuery,
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
		return _pushNotificationsEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _pushNotificationsEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry fetchPushNotificationsEntry(
		long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.fetchPushNotificationsEntry(pushNotificationsEntryId);
	}

	/**
	* Returns the push notifications entry with the primary key.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry
	* @throws PortalException if a push notifications entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry getPushNotificationsEntry(
		long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.getPushNotificationsEntry(pushNotificationsEntryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the push notifications entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @return the range of push notifications entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> getPushNotificationsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.getPushNotificationsEntries(start,
			end);
	}

	/**
	* Returns the number of push notifications entries.
	*
	* @return the number of push notifications entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getPushNotificationsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.getPushNotificationsEntriesCount();
	}

	/**
	* Updates the push notifications entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsEntry the push notifications entry
	* @return the push notifications entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry updatePushNotificationsEntry(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.updatePushNotificationsEntry(pushNotificationsEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _pushNotificationsEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pushNotificationsEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _pushNotificationsEntryLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry addPushNotificationsEntry(
		long userId, com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.addPushNotificationsEntry(userId,
			payloadJSONObject);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry dislikePushNotificationsEntry(
		long userId, long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.dislikePushNotificationsEntry(userId,
			pushNotificationsEntryId);
	}

	@Override
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> getPushNotificationsEntries(
		long parentPushNotificationsEntryId, long lastAccessTime, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.getPushNotificationsEntries(parentPushNotificationsEntryId,
			lastAccessTime, start, end);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry likePushNotificationsEntry(
		long userId, long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.likePushNotificationsEntry(userId,
			pushNotificationsEntryId);
	}

	@Override
	public void sendPushNotification(long fromUserId,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pushNotificationsEntryLocalService.sendPushNotification(fromUserId,
			payloadJSONObject);
	}

	@Override
	public void sendPushNotification(long fromUserId, long toUserId,
		com.liferay.portal.kernel.json.JSONObject payloadJSONObject)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pushNotificationsEntryLocalService.sendPushNotification(fromUserId,
			toUserId, payloadJSONObject);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsEntry updateChildrenPushNotificationsEntriesCount(
		long parentPushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushNotificationsEntryLocalService.updateChildrenPushNotificationsEntriesCount(parentPushNotificationsEntryId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PushNotificationsEntryLocalService getWrappedPushNotificationsEntryLocalService() {
		return _pushNotificationsEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPushNotificationsEntryLocalService(
		PushNotificationsEntryLocalService pushNotificationsEntryLocalService) {
		_pushNotificationsEntryLocalService = pushNotificationsEntryLocalService;
	}

	@Override
	public PushNotificationsEntryLocalService getWrappedService() {
		return _pushNotificationsEntryLocalService;
	}

	@Override
	public void setWrappedService(
		PushNotificationsEntryLocalService pushNotificationsEntryLocalService) {
		_pushNotificationsEntryLocalService = pushNotificationsEntryLocalService;
	}

	private PushNotificationsEntryLocalService _pushNotificationsEntryLocalService;
}