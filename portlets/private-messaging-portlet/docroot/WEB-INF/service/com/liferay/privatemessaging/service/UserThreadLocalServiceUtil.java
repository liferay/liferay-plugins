/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the user thread local service. This utility wraps {@link com.liferay.privatemessaging.service.impl.UserThreadLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadLocalService
 * @see com.liferay.privatemessaging.service.base.UserThreadLocalServiceBaseImpl
 * @see com.liferay.privatemessaging.service.impl.UserThreadLocalServiceImpl
 * @generated
 */
public class UserThreadLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.privatemessaging.service.impl.UserThreadLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user thread to the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @return the user thread that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.privatemessaging.model.UserThread addUserThread(
		com.liferay.privatemessaging.model.UserThread userThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addUserThread(userThread);
	}

	/**
	* Creates a new user thread with the primary key. Does not add the user thread to the database.
	*
	* @param userThreadId the primary key for the new user thread
	* @return the new user thread
	*/
	public static com.liferay.privatemessaging.model.UserThread createUserThread(
		long userThreadId) {
		return getService().createUserThread(userThreadId);
	}

	/**
	* Deletes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userThreadId the primary key of the user thread
	* @throws PortalException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserThread(long userThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserThread(userThreadId);
	}

	/**
	* Deletes the user thread from the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserThread(
		com.liferay.privatemessaging.model.UserThread userThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserThread(userThread);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.privatemessaging.model.UserThread fetchUserThread(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserThread(userThreadId);
	}

	/**
	* Returns the user thread with the primary key.
	*
	* @param userThreadId the primary key of the user thread
	* @return the user thread
	* @throws PortalException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.privatemessaging.model.UserThread getUserThread(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserThread(userThreadId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user threads
	* @param end the upper bound of the range of user threads (not inclusive)
	* @return the range of user threads
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getUserThreads(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserThreads(start, end);
	}

	/**
	* Returns the number of user threads.
	*
	* @return the number of user threads
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserThreadsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserThreadsCount();
	}

	/**
	* Updates the user thread in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @return the user thread that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.privatemessaging.model.UserThread updateUserThread(
		com.liferay.privatemessaging.model.UserThread userThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserThread(userThread);
	}

	/**
	* Updates the user thread in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread
	* @param merge whether to merge the user thread with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user thread that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.privatemessaging.model.UserThread updateUserThread(
		com.liferay.privatemessaging.model.UserThread userThread, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserThread(userThread, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portlet.messageboards.model.MBMessage addPrivateMessage(
		long userId, long mbThreadId, java.lang.String to,
		java.lang.String subject, java.lang.String body,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPrivateMessage(userId, mbThreadId, to, subject, body,
			inputStreamOVPs, themeDisplay);
	}

	public static com.liferay.portlet.messageboards.model.MBMessage addPrivateMessageBranch(
		long userId, long parentMBMessageId, java.lang.String body,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.InputStream>> inputStreamOVPs,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPrivateMessageBranch(userId, parentMBMessageId, body,
			inputStreamOVPs, themeDisplay);
	}

	public static void addUserThread(long userId, long mbThreadId,
		long topMBMessageId, boolean read, boolean deleted)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addUserThread(userId, mbThreadId, topMBMessageId, read, deleted);
	}

	public static void deleteUser(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUser(userId);
	}

	public static void deleteUserThread(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserThread(userId, mbThreadId);
	}

	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getMBThreadUserThreads(
		long mbThreadId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMBThreadUserThreads(mbThreadId);
	}

	public static com.liferay.privatemessaging.model.UserThread getUserThread(
		long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserThread(userId, mbThreadId);
	}

	public static int getUserUserThreadCount(long userId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserThreadCount(userId, deleted);
	}

	public static int getUserUserThreadCount(long userId, boolean read,
		boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserThreadCount(userId, read, deleted);
	}

	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		long userId, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserThreads(userId, deleted);
	}

	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		long userId, boolean read, boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserThreads(userId, read, deleted);
	}

	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		long userId, boolean deleted, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserUserThreads(userId, deleted, start, end);
	}

	public static void markUserThreadAsRead(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().markUserThreadAsRead(userId, mbThreadId);
	}

	public static void markUserThreadAsUnread(long userId, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().markUserThreadAsUnread(userId, mbThreadId);
	}

	public static void clearService() {
		_service = null;
	}

	public static UserThreadLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					UserThreadLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					UserThreadLocalService.class.getName(), portletClassLoader);

			_service = new UserThreadLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(UserThreadLocalServiceUtil.class,
				"_service");
			MethodCache.remove(UserThreadLocalService.class);
		}

		return _service;
	}

	public void setService(UserThreadLocalService service) {
		MethodCache.remove(UserThreadLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(UserThreadLocalServiceUtil.class,
			"_service");
		MethodCache.remove(UserThreadLocalService.class);
	}

	private static UserThreadLocalService _service;
}