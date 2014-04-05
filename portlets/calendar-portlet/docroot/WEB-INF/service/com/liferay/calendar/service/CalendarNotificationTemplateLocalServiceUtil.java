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

package com.liferay.calendar.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for CalendarNotificationTemplate. This utility wraps
 * {@link com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplateLocalService
 * @see com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl
 * @generated
 */
public class CalendarNotificationTemplateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the calendar notification template to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate addCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCalendarNotificationTemplate(calendarNotificationTemplate);
	}

	/**
	* Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	*
	* @param calendarNotificationTemplateId the primary key for the new calendar notification template
	* @return the new calendar notification template
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate createCalendarNotificationTemplate(
		long calendarNotificationTemplateId) {
		return getService()
				   .createCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	/**
	* Deletes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template that was removed
	* @throws PortalException if a calendar notification template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		long calendarNotificationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	/**
	* Deletes the calendar notification template from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteCalendarNotificationTemplate(calendarNotificationTemplate);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarNotificationTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	/**
	* Returns the calendar notification template with the matching UUID and company.
	*
	* @param uuid the calendar notification template's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplateByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchCalendarNotificationTemplateByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the calendar notification template matching the UUID and group.
	*
	* @param uuid the calendar notification template's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchCalendarNotificationTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the calendar notification template with the primary key.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template
	* @throws PortalException if a calendar notification template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplate(
		long calendarNotificationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the calendar notification template with the matching UUID and company.
	*
	* @param uuid the calendar notification template's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar notification template
	* @throws PortalException if a matching calendar notification template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplateByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarNotificationTemplateByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the calendar notification template matching the UUID and group.
	*
	* @param uuid the calendar notification template's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar notification template
	* @throws PortalException if a matching calendar notification template could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarNotificationTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the calendar notification templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @return the range of calendar notification templates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarNotificationTemplates(start, end);
	}

	/**
	* Returns the number of calendar notification templates.
	*
	* @return the number of calendar notification templates
	* @throws SystemException if a system exception occurred
	*/
	public static int getCalendarNotificationTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarNotificationTemplatesCount();
	}

	/**
	* Updates the calendar notification template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarNotificationTemplate updateCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendarNotificationTemplate(calendarNotificationTemplate);
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

	public static com.liferay.calendar.model.CalendarNotificationTemplate addCalendarNotificationTemplate(
		long userId, long calendarId,
		com.liferay.calendar.notification.NotificationType notificationType,
		java.lang.String notificationTypeSettings,
		com.liferay.calendar.notification.NotificationTemplateType notificationTemplateType,
		java.lang.String subject, java.lang.String body,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCalendarNotificationTemplate(userId, calendarId,
			notificationType, notificationTypeSettings,
			notificationTemplateType, subject, body, serviceContext);
	}

	public static void deleteCalendarNotificationTemplates(long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCalendarNotificationTemplates(calendarId);
	}

	public static com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarId,
		com.liferay.calendar.notification.NotificationType notificationType,
		com.liferay.calendar.notification.NotificationTemplateType notificationTemplateType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchCalendarNotificationTemplate(calendarId,
			notificationType, notificationTemplateType);
	}

	public static com.liferay.calendar.model.CalendarNotificationTemplate updateCalendarNotificationTemplate(
		long calendarNotificationTemplateId,
		java.lang.String notificationTypeSettings, java.lang.String subject,
		java.lang.String body,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendarNotificationTemplate(calendarNotificationTemplateId,
			notificationTypeSettings, subject, body, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarNotificationTemplateLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarNotificationTemplateLocalService.class.getName());

			if (invokableLocalService instanceof CalendarNotificationTemplateLocalService) {
				_service = (CalendarNotificationTemplateLocalService)invokableLocalService;
			}
			else {
				_service = new CalendarNotificationTemplateLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CalendarNotificationTemplateLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(CalendarNotificationTemplateLocalService service) {
	}

	private static CalendarNotificationTemplateLocalService _service;
}