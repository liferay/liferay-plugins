/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CalendarLocalService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarLocalService
 * @generated
 */
public class CalendarLocalServiceWrapper implements CalendarLocalService,
	ServiceWrapper<CalendarLocalService> {
	public CalendarLocalServiceWrapper(
		CalendarLocalService calendarLocalService) {
		_calendarLocalService = calendarLocalService;
	}

	/**
	* Adds the calendar to the database. Also notifies the appropriate model listeners.
	*
	* @param calendar the calendar
	* @return the calendar that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar addCalendar(
		com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.addCalendar(calendar);
	}

	/**
	* Creates a new calendar with the primary key. Does not add the calendar to the database.
	*
	* @param calendarId the primary key for the new calendar
	* @return the new calendar
	*/
	@Override
	public com.liferay.calendar.model.Calendar createCalendar(long calendarId) {
		return _calendarLocalService.createCalendar(calendarId);
	}

	/**
	* Deletes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar that was removed
	* @throws PortalException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar deleteCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.deleteCalendar(calendarId);
	}

	/**
	* Deletes the calendar from the database. Also notifies the appropriate model listeners.
	*
	* @param calendar the calendar
	* @return the calendar that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar deleteCalendar(
		com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.deleteCalendar(calendar);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calendarLocalService.dynamicQuery();
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
		return _calendarLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _calendarLocalService.dynamicQueryCount(dynamicQuery);
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
		return _calendarLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.calendar.model.Calendar fetchCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.fetchCalendar(calendarId);
	}

	/**
	* Returns the calendar with the matching UUID and company.
	*
	* @param uuid the calendar's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar fetchCalendarByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.fetchCalendarByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the calendar matching the UUID and group.
	*
	* @param uuid the calendar's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar fetchCalendarByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.fetchCalendarByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the calendar with the primary key.
	*
	* @param calendarId the primary key of the calendar
	* @return the calendar
	* @throws PortalException if a calendar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar getCalendar(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendar(calendarId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the calendar with the matching UUID and company.
	*
	* @param uuid the calendar's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar
	* @throws PortalException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar getCalendarByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendarByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the calendar matching the UUID and group.
	*
	* @param uuid the calendar's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar
	* @throws PortalException if a matching calendar could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar getCalendarByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendarByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the calendars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendars
	* @param end the upper bound of the range of calendars (not inclusive)
	* @return the range of calendars
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendars(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendars(start, end);
	}

	/**
	* Returns the number of calendars.
	*
	* @return the number of calendars
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getCalendarsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendarsCount();
	}

	/**
	* Updates the calendar in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendar the calendar
	* @return the calendar that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(
		com.liferay.calendar.model.Calendar calendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.updateCalendar(calendar);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _calendarLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calendarLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.calendar.model.Calendar addCalendar(long userId,
		long groupId, long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar, boolean enableComments,
		boolean enableRatings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.addCalendar(userId, groupId,
			calendarResourceId, nameMap, descriptionMap, color,
			defaultCalendar, enableComments, enableRatings, serviceContext);
	}

	@Override
	public java.lang.String exportCalendar(long calendarId,
		java.lang.String type) throws java.lang.Exception {
		return _calendarLocalService.exportCalendar(calendarId, type);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendarResourceCalendars(groupId,
			calendarResourceId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendarResourceCalendars(
		long groupId, long calendarResourceId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.getCalendarResourceCalendars(groupId,
			calendarResourceId, defaultCalendar);
	}

	@Override
	public void importCalendar(long calendarId, java.lang.String data,
		java.lang.String type) throws java.lang.Exception {
		_calendarLocalService.importCalendar(calendarId, data, type);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.search(companyId, groupIds,
			calendarResourceIds, keywords, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.Calendar> search(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.search(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, start, end,
			orderByComparator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.searchCount(companyId, groupIds,
			calendarResourceIds, keywords, andOperator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.searchCount(companyId, groupIds,
			calendarResourceIds, name, description, andOperator);
	}

	@Override
	public void updateCalendar(long calendarId, boolean defaultCalendar)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarLocalService.updateCalendar(calendarId, defaultCalendar);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, boolean defaultCalendar, boolean enableComments,
		boolean enableRatings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, defaultCalendar, enableComments,
			enableRatings, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateCalendar(long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int color, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.updateCalendar(calendarId, nameMap,
			descriptionMap, color, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.Calendar updateColor(long calendarId,
		int color, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarLocalService.updateColor(calendarId, color,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public CalendarLocalService getWrappedCalendarLocalService() {
		return _calendarLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedCalendarLocalService(
		CalendarLocalService calendarLocalService) {
		_calendarLocalService = calendarLocalService;
	}

	@Override
	public CalendarLocalService getWrappedService() {
		return _calendarLocalService;
	}

	@Override
	public void setWrappedService(CalendarLocalService calendarLocalService) {
		_calendarLocalService = calendarLocalService;
	}

	private CalendarLocalService _calendarLocalService;
}