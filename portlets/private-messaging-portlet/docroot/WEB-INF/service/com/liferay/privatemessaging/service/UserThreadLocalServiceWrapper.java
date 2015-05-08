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

package com.liferay.privatemessaging.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserThreadLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadLocalService
 * @generated
 */
@ProviderType
public class UserThreadLocalServiceWrapper implements UserThreadLocalService,
	ServiceWrapper<UserThreadLocalService> {
	public UserThreadLocalServiceWrapper(
		UserThreadLocalService userThreadLocalService) {
		_userThreadLocalService = userThreadLocalService;
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBMessage addPrivateMessage(
		long userId, long mbThreadId, java.lang.String to,
		java.lang.String subject, java.lang.String body,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.addPrivateMessage(userId, mbThreadId,
			to, subject, body, inputStreamOVPs, themeDisplay);
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBMessage addPrivateMessageBranch(
		long userId, long parentMBMessageId, java.lang.String body,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.addPrivateMessageBranch(userId,
			parentMBMessageId, body, inputStreamOVPs, themeDisplay);
	}

	@Override
	public void addUserThread(long userId, long mbThreadId,
		long topMBMessageId, boolean read, boolean deleted)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userThreadLocalService.addUserThread(userId, mbThreadId,
			topMBMessageId, read, deleted);
	}

	/**
	* Adds the user thread to the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @return the user thread that was added
	*/
	@Override
	public com.liferay.privatemessaging.model.UserThread addUserThread(
		com.liferay.privatemessaging.model.UserThread userThread) {
		return _userThreadLocalService.addUserThread(userThread);
	}

	/**
	* Creates a new user thread with the primary key. Does not add the user thread to the database.
	*
	* @param userThreadId the primary key for the new user thread
	* @return the new user thread
	*/
	@Override
	public com.liferay.privatemessaging.model.UserThread createUserThread(
		long userThreadId) {
		return _userThreadLocalService.createUserThread(userThreadId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteUser(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userThreadLocalService.deleteUser(userId);
	}

	@Override
	public void deleteUserThread(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userThreadLocalService.deleteUserThread(userId, mbThreadId);
	}

	/**
	* Deletes the user thread from the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @return the user thread that was removed
	*/
	@Override
	public com.liferay.privatemessaging.model.UserThread deleteUserThread(
		com.liferay.privatemessaging.model.UserThread userThread) {
		return _userThreadLocalService.deleteUserThread(userThread);
	}

	/**
	* Deletes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread that was removed
	* @throws PortalException if a user thread with the primary key could not be found
	*/
	@Override
	public com.liferay.privatemessaging.model.UserThread deleteUserThread(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.deleteUserThread(userThreadId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userThreadLocalService.dynamicQuery();
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
		return _userThreadLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.privatemessaging.model.impl.UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userThreadLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.privatemessaging.model.impl.UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _userThreadLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _userThreadLocalService.dynamicQueryCount(dynamicQuery);
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
		return _userThreadLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.privatemessaging.model.UserThread fetchUserThread(
		long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.fetchUserThread(userId, mbThreadId);
	}

	@Override
	public com.liferay.privatemessaging.model.UserThread fetchUserThread(
		long userThreadId) {
		return _userThreadLocalService.fetchUserThread(userThreadId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userThreadLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _userThreadLocalService.getBeanIdentifier();
	}

	@Override
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getMBThreadUserThreads(
		long mbThreadId) {
		return _userThreadLocalService.getMBThreadUserThreads(mbThreadId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.privatemessaging.model.UserThread getUserThread(
		long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.getUserThread(userId, mbThreadId);
	}

	/**
	* Returns the user thread with the primary key.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread
	* @throws PortalException if a user thread with the primary key could not be found
	*/
	@Override
	public com.liferay.privatemessaging.model.UserThread getUserThread(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userThreadLocalService.getUserThread(userThreadId);
	}

	/**
	* Returns a range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.privatemessaging.model.impl.UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of user threads
	*/
	@Override
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getUserThreads(
		int start, int end) {
		return _userThreadLocalService.getUserThreads(start, end);
	}

	/**
	* Returns the number of user threads.
	*
	* @return the number of user threads
	*/
	@Override
	public int getUserThreadsCount() {
		return _userThreadLocalService.getUserThreadsCount();
	}

	@Override
	public int getUserUserThreadCount(long userId, boolean deleted) {
		return _userThreadLocalService.getUserUserThreadCount(userId, deleted);
	}

	@Override
	public int getUserUserThreadCount(long userId, boolean read, boolean deleted) {
		return _userThreadLocalService.getUserUserThreadCount(userId, read,
			deleted);
	}

	@Override
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		long userId, boolean deleted) {
		return _userThreadLocalService.getUserUserThreads(userId, deleted);
	}

	@Override
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		long userId, boolean deleted, int start, int end) {
		return _userThreadLocalService.getUserUserThreads(userId, deleted,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		long userId, boolean read, boolean deleted) {
		return _userThreadLocalService.getUserUserThreads(userId, read, deleted);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userThreadLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public void markUserThreadAsRead(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userThreadLocalService.markUserThreadAsRead(userId, mbThreadId);
	}

	@Override
	public void markUserThreadAsUnread(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_userThreadLocalService.markUserThreadAsUnread(userId, mbThreadId);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userThreadLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void updateUserName(com.liferay.portal.model.User user) {
		_userThreadLocalService.updateUserName(user);
	}

	/**
	* Updates the user thread in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @return the user thread that was updated
	*/
	@Override
	public com.liferay.privatemessaging.model.UserThread updateUserThread(
		com.liferay.privatemessaging.model.UserThread userThread) {
		return _userThreadLocalService.updateUserThread(userThread);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public UserThreadLocalService getWrappedUserThreadLocalService() {
		return _userThreadLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedUserThreadLocalService(
		UserThreadLocalService userThreadLocalService) {
		_userThreadLocalService = userThreadLocalService;
	}

	@Override
	public UserThreadLocalService getWrappedService() {
		return _userThreadLocalService;
	}

	@Override
	public void setWrappedService(UserThreadLocalService userThreadLocalService) {
		_userThreadLocalService = userThreadLocalService;
	}

	private UserThreadLocalService _userThreadLocalService;
}