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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for CalendarNotificationTemplate. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplateLocalServiceUtil
 * @see com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CalendarNotificationTemplateLocalService
	extends BaseLocalService, InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarNotificationTemplateLocalServiceUtil} to access the calendar notification template local service. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarNotificationTemplateLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the calendar notification template to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was added
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.calendar.model.CalendarNotificationTemplate addCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate);

	public com.liferay.calendar.model.CalendarNotificationTemplate addCalendarNotificationTemplate(
		long userId, long calendarId,
		com.liferay.calendar.notification.NotificationType notificationType,
		java.lang.String notificationTypeSettings,
		com.liferay.calendar.notification.NotificationTemplateType notificationTemplateType,
		java.lang.String subject, java.lang.String body,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new calendar notification template with the primary key. Does not add the calendar notification template to the database.
	*
	* @param calendarNotificationTemplateId the primary key for the new calendar notification template
	* @return the new calendar notification template
	*/
	public com.liferay.calendar.model.CalendarNotificationTemplate createCalendarNotificationTemplate(
		long calendarNotificationTemplateId);

	/**
	* Deletes the calendar notification template from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was removed
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	@com.liferay.portal.kernel.systemevent.SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public com.liferay.calendar.model.CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate);

	/**
	* Deletes the calendar notification template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template that was removed
	* @throws PortalException if a calendar notification template with the primary key could not be found
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.liferay.calendar.model.CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		long calendarNotificationTemplateId) throws PortalException;

	public void deleteCalendarNotificationTemplates(long calendarId);

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws PortalException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery);

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
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarId,
		com.liferay.calendar.notification.NotificationType notificationType,
		com.liferay.calendar.notification.NotificationTemplateType notificationTemplateType);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarNotificationTemplateId);

	/**
	* Returns the calendar notification template matching the UUID and group.
	*
	* @param uuid the calendar notification template's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar notification template, or <code>null</code> if a matching calendar notification template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarNotificationTemplate fetchCalendarNotificationTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Returns the calendar notification template with the primary key.
	*
	* @param calendarNotificationTemplateId the primary key of the calendar notification template
	* @return the calendar notification template
	* @throws PortalException if a calendar notification template with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplate(
		long calendarNotificationTemplateId) throws PortalException;

	/**
	* Returns the calendar notification template matching the UUID and group.
	*
	* @param uuid the calendar notification template's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar notification template
	* @throws PortalException if a matching calendar notification template could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarNotificationTemplate getCalendarNotificationTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplates(
		int start, int end);

	/**
	* Returns all the calendar notification templates matching the UUID and company.
	*
	* @param uuid the UUID of the calendar notification templates
	* @param companyId the primary key of the company
	* @return the matching calendar notification templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of calendar notification templates matching the UUID and company.
	*
	* @param uuid the UUID of the calendar notification templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of calendar notification templates
	* @param end the upper bound of the range of calendar notification templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching calendar notification templates, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.calendar.model.CalendarNotificationTemplate> getCalendarNotificationTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarNotificationTemplate> orderByComparator);

	/**
	* Returns the number of calendar notification templates.
	*
	* @return the number of calendar notification templates
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCalendarNotificationTemplatesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext);

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj) throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	/**
	* Updates the calendar notification template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarNotificationTemplate the calendar notification template
	* @return the calendar notification template that was updated
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.calendar.model.CalendarNotificationTemplate updateCalendarNotificationTemplate(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate);

	public com.liferay.calendar.model.CalendarNotificationTemplate updateCalendarNotificationTemplate(
		long calendarNotificationTemplateId,
		java.lang.String notificationTypeSettings, java.lang.String subject,
		java.lang.String body,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;
}