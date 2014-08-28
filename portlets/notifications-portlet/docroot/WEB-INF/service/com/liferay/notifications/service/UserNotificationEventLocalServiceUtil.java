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

package com.liferay.notifications.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for UserNotificationEvent. This utility wraps
 * {@link com.liferay.notifications.service.impl.UserNotificationEventLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEventLocalService
 * @see com.liferay.notifications.service.base.UserNotificationEventLocalServiceBaseImpl
 * @see com.liferay.notifications.service.impl.UserNotificationEventLocalServiceImpl
 * @generated
 */
public class UserNotificationEventLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.notifications.service.impl.UserNotificationEventLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user notification event to the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent addUserNotificationEvent(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addUserNotificationEvent(userNotificationEvent);
	}

	/**
	* Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	*
	* @param notificationEventId the primary key for the new user notification event
	* @return the new user notification event
	*/
	public static com.liferay.notifications.model.UserNotificationEvent createUserNotificationEvent(
		long notificationEventId) {
		return getService().createUserNotificationEvent(notificationEventId);
	}

	/**
	* Deletes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event that was removed
	* @throws PortalException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent deleteUserNotificationEvent(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUserNotificationEvent(notificationEventId);
	}

	/**
	* Deletes the user notification event from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent deleteUserNotificationEvent(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUserNotificationEvent(userNotificationEvent);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.notifications.model.UserNotificationEvent fetchUserNotificationEvent(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserNotificationEvent(notificationEventId);
	}

	/**
	* Returns the user notification event with the primary key.
	*
	* @param notificationEventId the primary key of the user notification event
	* @return the user notification event
	* @throws PortalException if a user notification event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent getUserNotificationEvent(
		long notificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserNotificationEvent(notificationEventId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user notification events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification events
	* @param end the upper bound of the range of user notification events (not inclusive)
	* @return the range of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> getUserNotificationEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserNotificationEvents(start, end);
	}

	/**
	* Returns the number of user notification events.
	*
	* @return the number of user notification events
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserNotificationEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserNotificationEventsCount();
	}

	/**
	* Updates the user notification event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userNotificationEvent the user notification event
	* @return the user notification event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.notifications.model.UserNotificationEvent updateUserNotificationEvent(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserNotificationEvent(userNotificationEvent);
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

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.notifications.model.UserNotificationEvent addUserNotificationEvent(
		long userNotificationEventId, long userId, long timeStamp,
		boolean actionRequired, boolean delivered, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addUserNotificationEvent(userNotificationEventId, userId,
			timeStamp, actionRequired, delivered, archived);
	}

	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> getArchivedUserNotificationEvents(
		long userId, boolean actionRequired, boolean archived, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getArchivedUserNotificationEvents(userId, actionRequired,
			archived, start, end);
	}

	public static int getArchivedUserNotificationEventsCount(long userId,
		boolean actionRequired, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getArchivedUserNotificationEventsCount(userId,
			actionRequired, archived);
	}

	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> getDeliveredUserNotificationEvents(
		long userId, boolean delivered, boolean actionRequired, int start,
		int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getDeliveredUserNotificationEvents(userId, delivered,
			actionRequired, start, end);
	}

	public static int getDeliveredUserNotificationEventsCount(long userId,
		boolean delivered, boolean actionRequired)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getDeliveredUserNotificationEventsCount(userId, delivered,
			actionRequired);
	}

	public static com.liferay.notifications.model.UserNotificationEvent getNotificationEventByUserNotificationEventId(
		long userNotificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getNotificationEventByUserNotificationEventId(userNotificationEventId);
	}

	public static java.util.List<com.liferay.notifications.model.UserNotificationEvent> getUserNotificationEvents(
		long userId, boolean actionRequired, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserNotificationEvents(userId, actionRequired, start, end);
	}

	public static int getUserNotificationEventsCount(long userId,
		boolean actionRequired)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserNotificationEventsCount(userId, actionRequired);
	}

	public static com.liferay.notifications.model.UserNotificationEvent updateUserNotificationEvent(
		long notificationEventId, long timeStamp, boolean actionRequired,
		boolean delivered, boolean archived)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateUserNotificationEvent(notificationEventId, timeStamp,
			actionRequired, delivered, archived);
	}

	public static void clearService() {
		_service = null;
	}

	public static UserNotificationEventLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					UserNotificationEventLocalService.class.getName());

			if (invokableLocalService instanceof UserNotificationEventLocalService) {
				_service = (UserNotificationEventLocalService)invokableLocalService;
			}
			else {
				_service = new UserNotificationEventLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(UserNotificationEventLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(UserNotificationEventLocalService service) {
	}

	private static UserNotificationEventLocalService _service;
}