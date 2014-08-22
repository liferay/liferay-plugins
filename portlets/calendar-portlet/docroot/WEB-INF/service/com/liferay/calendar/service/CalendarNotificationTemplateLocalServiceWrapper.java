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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CalendarNotificationTemplateLocalService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplateLocalService
 * @generated
 */
@ProviderType
public class CalendarNotificationTemplateLocalServiceWrapper
	implements CalendarNotificationTemplateLocalService,
		ServiceWrapper<CalendarNotificationTemplateLocalService> {
	public CalendarNotificationTemplateLocalServiceWrapper(
		CalendarNotificationTemplateLocalService calendarNotificationTemplateLocalService) {
		_calendarNotificationTemplateLocalService = calendarNotificationTemplateLocalService;
	}

	/**
	* Adds the calendar notification template to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was added
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate addCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate) {
		return _calendarNotificationTemplateLocalService.addCalendarNotificationTemplate(calendarNotificationTemplate);
	}

	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate addCalendarNotificationTemplate(
		long userId, long calendarId,
		com.liferay.calendar.notification.NotificationType notificationType,
		java.lang.String notificationTypeSettings,
		com.liferay.calendar.notification.NotificationTemplateType notificationTemplateType,
		java.lang.String subject, java.lang.String body,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.addCalendarNotificationTemplate(userId,
			calendarId, notificationType, notificationTypeSettings,
			notificationTemplateType, subject, body, serviceContext);
	}

	/**
	* Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	*
	* @param calendarNotificationTemplateId the primary key for the new calendar notification template
	* @return the new calendar notification template
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate createCalendarNotificationTemplate(
		long calendarNotificationTemplateId) {
		return _calendarNotificationTemplateLocalService.createCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	/**
	* Deletes the calendar notification template from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was removed
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate) {
		return _calendarNotificationTemplateLocalService.deleteCalendarNotificationTemplate(calendarNotificationTemplate);
	}

	/**
	* Deletes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template that was removed
	* @throws PortalException if a calendar notification template with the primary key could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		long calendarNotificationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.deleteCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	@Override
	public void deleteCalendarNotificationTemplates(long calendarId) {
		_calendarNotificationTemplateLocalService.deleteCalendarNotificationTemplates(calendarId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calendarNotificationTemplateLocalService.dynamicQuery();
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
		return _calendarNotificationTemplateLocalService.dynamicQuery(dynamicQuery);
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _calendarNotificationTemplateLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _calendarNotificationTemplateLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _calendarNotificationTemplateLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _calendarNotificationTemplateLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarId,
		com.liferay.calendar.notification.NotificationType notificationType,
		com.liferay.calendar.notification.NotificationTemplateType notificationTemplateType) {
		return _calendarNotificationTemplateLocalService.fetchCalendarNotificationTemplate(calendarId,
			notificationType, notificationTemplateType);
	}

	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarNotificationTemplateId) {
		return _calendarNotificationTemplateLocalService.fetchCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	/**
	* Returns the calendar notification template matching the UUID and group.
	*
	* @param uuid the calendar notification template's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _calendarNotificationTemplateLocalService.fetchCalendarNotificationTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _calendarNotificationTemplateLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _calendarNotificationTemplateLocalService.getBeanIdentifier();
	}

	/**
	* Returns the calendar notification template with the primary key.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template
	* @throws PortalException if a calendar notification template with the primary key could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplate(
		long calendarNotificationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.getCalendarNotificationTemplate(calendarNotificationTemplateId);
	}

	/**
	* Returns the calendar notification template matching the UUID and group.
	*
	* @param uuid the calendar notification template's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar notification template
	* @throws PortalException if a matching calendar notification template could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.getCalendarNotificationTemplateByUuidAndGroupId(uuid,
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
	*/
	@Override
	public java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplates(
		int start, int end) {
		return _calendarNotificationTemplateLocalService.getCalendarNotificationTemplates(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _calendarNotificationTemplateLocalService.getCalendarNotificationTemplatesByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarNotificationTemplate> orderByComparator) {
		return _calendarNotificationTemplateLocalService.getCalendarNotificationTemplatesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of calendar notification templates.
	*
	* @return the number of calendar notification templates
	*/
	@Override
	public int getCalendarNotificationTemplatesCount() {
		return _calendarNotificationTemplateLocalService.getCalendarNotificationTemplatesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return _calendarNotificationTemplateLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calendarNotificationTemplateLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarNotificationTemplateLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the calendar notification template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was updated
	*/
	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate updateCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate) {
		return _calendarNotificationTemplateLocalService.updateCalendarNotificationTemplate(calendarNotificationTemplate);
	}

	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate updateCalendarNotificationTemplate(
		long calendarNotificationTemplateId,
		java.lang.String notificationTypeSettings, java.lang.String subject,
		java.lang.String body,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarNotificationTemplateLocalService.updateCalendarNotificationTemplate(calendarNotificationTemplateId,
			notificationTypeSettings, subject, body, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public CalendarNotificationTemplateLocalService getWrappedCalendarNotificationTemplateLocalService() {
		return _calendarNotificationTemplateLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedCalendarNotificationTemplateLocalService(
		CalendarNotificationTemplateLocalService calendarNotificationTemplateLocalService) {
		_calendarNotificationTemplateLocalService = calendarNotificationTemplateLocalService;
	}

	@Override
	public CalendarNotificationTemplateLocalService getWrappedService() {
		return _calendarNotificationTemplateLocalService;
	}

	@Override
	public void setWrappedService(
		CalendarNotificationTemplateLocalService calendarNotificationTemplateLocalService) {
		_calendarNotificationTemplateLocalService = calendarNotificationTemplateLocalService;
	}

	private CalendarNotificationTemplateLocalService _calendarNotificationTemplateLocalService;
}