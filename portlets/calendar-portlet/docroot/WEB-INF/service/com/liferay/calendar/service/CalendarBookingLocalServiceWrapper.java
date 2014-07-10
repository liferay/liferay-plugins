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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CalendarBookingLocalService}.
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingLocalService
 * @generated
 */
public class CalendarBookingLocalServiceWrapper
	implements CalendarBookingLocalService,
		ServiceWrapper<CalendarBookingLocalService> {
	public CalendarBookingLocalServiceWrapper(
		CalendarBookingLocalService calendarBookingLocalService) {
		_calendarBookingLocalService = calendarBookingLocalService;
	}

	/**
	* Adds the calendar booking to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was added
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		return _calendarBookingLocalService.addCalendarBooking(calendarBooking);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long userId, long calendarId, long[] childCalendarIds,
		long parentCalendarBookingId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.addCalendarBooking(userId,
			calendarId, childCalendarIds, parentCalendarBookingId, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	}

	@Override
	public void checkCalendarBookings()
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingLocalService.checkCalendarBookings();
	}

	/**
	* Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	*
	* @param calendarBookingId the primary key for the new calendar booking
	* @return the new calendar booking
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking createCalendarBooking(
		long calendarBookingId) {
		return _calendarBookingLocalService.createCalendarBooking(calendarBookingId);
	}

	/**
	* Deletes the calendar booking from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
	}

	/**
	* Deletes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking that was removed
	* @throws PortalException if a calendar booking with the primary key could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.deleteCalendarBooking(calendarBookingId);
	}

	@Override
	public void deleteCalendarBookingInstance(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		int instanceIndex, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingLocalService.deleteCalendarBookingInstance(calendarBooking,
			instanceIndex, allFollowing);
	}

	@Override
	public void deleteCalendarBookingInstance(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		long startTime, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingLocalService.deleteCalendarBookingInstance(calendarBooking,
			startTime, allFollowing);
	}

	@Override
	public void deleteCalendarBookingInstance(long calendarBookingId,
		long startTime, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingLocalService.deleteCalendarBookingInstance(calendarBookingId,
			startTime, allFollowing);
	}

	@Override
	public void deleteCalendarBookings(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingLocalService.deleteCalendarBookings(calendarId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calendarBookingLocalService.dynamicQuery();
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
		return _calendarBookingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarBookingLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarBookingLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _calendarBookingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _calendarBookingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public java.lang.String exportCalendarBooking(long calendarBookingId,
		java.lang.String type) throws java.lang.Exception {
		return _calendarBookingLocalService.exportCalendarBooking(calendarBookingId,
			type);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarBookingId) {
		return _calendarBookingLocalService.fetchCalendarBooking(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		java.lang.String uuid, long groupId) {
		return _calendarBookingLocalService.fetchCalendarBooking(uuid, groupId);
	}

	/**
	* Returns the calendar booking with the matching UUID and company.
	*
	* @param uuid the calendar booking's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking fetchCalendarBookingByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _calendarBookingLocalService.fetchCalendarBookingByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the calendar booking matching the UUID and group.
	*
	* @param uuid the calendar booking's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking fetchCalendarBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _calendarBookingLocalService.fetchCalendarBookingByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _calendarBookingLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _calendarBookingLocalService.getBeanIdentifier();
	}

	/**
	* Returns the calendar booking with the primary key.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking
	* @throws PortalException if a calendar booking with the primary key could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.getCalendarBooking(calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarId, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.getCalendarBooking(calendarId,
			parentCalendarBookingId);
	}

	/**
	* Returns the calendar booking with the matching UUID and company.
	*
	* @param uuid the calendar booking's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar booking
	* @throws PortalException if a matching calendar booking could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBookingByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.getCalendarBookingByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the calendar booking matching the UUID and group.
	*
	* @param uuid the calendar booking's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar booking
	* @throws PortalException if a matching calendar booking could not be found
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking getCalendarBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.getCalendarBookingByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId) {
		return _calendarBookingLocalService.getCalendarBookings(calendarId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime) {
		return _calendarBookingLocalService.getCalendarBookings(calendarId,
			startTime, endTime);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime, int max) {
		return _calendarBookingLocalService.getCalendarBookings(calendarId,
			startTime, endTime, max);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, int[] statuses) {
		return _calendarBookingLocalService.getCalendarBookings(calendarId,
			statuses);
	}

	/**
	* Returns a range of all the calendar bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of calendar bookings
	*/
	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		int start, int end) {
		return _calendarBookingLocalService.getCalendarBookings(start, end);
	}

	/**
	* Returns the number of calendar bookings.
	*
	* @return the number of calendar bookings
	*/
	@Override
	public int getCalendarBookingsCount() {
		return _calendarBookingLocalService.getCalendarBookingsCount();
	}

	@Override
	public int getCalendarBookingsCount(long calendarId,
		long parentCalendarBookingId) {
		return _calendarBookingLocalService.getCalendarBookingsCount(calendarId,
			parentCalendarBookingId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long calendarBookingId) {
		return _calendarBookingLocalService.getChildCalendarBookings(calendarBookingId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId, int status) {
		return _calendarBookingLocalService.getChildCalendarBookings(parentCalendarBookingId,
			status);
	}

	@Override
	public long[] getChildCalendarIds(long calendarBookingId, long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.getChildCalendarIds(calendarBookingId,
			calendarId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return _calendarBookingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calendarBookingLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		long userId, com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.moveCalendarBookingToTrash(userId,
			calendarBooking);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		long userId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.moveCalendarBookingToTrash(userId,
			calendarBookingId);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking restoreCalendarBookingFromTrash(
		long userId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.restoreCalendarBookingFromTrash(userId,
			calendarBookingId);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator) {
		return _calendarBookingLocalService.search(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId,
			keywords, startTime, endTime, recurring, statuses, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator) {
		return _calendarBookingLocalService.search(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime, int[] statuses) {
		return _calendarBookingLocalService.searchCount(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId,
			keywords, startTime, endTime, statuses);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		int[] statuses, boolean andOperator) {
		return _calendarBookingLocalService.searchCount(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, statuses, andOperator);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarBookingLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_calendarBookingLocalService.updateAsset(userId, calendarBooking,
			assetCategoryIds, assetTagNames, assetLinkEntryIds);
	}

	/**
	* Updates the calendar booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was updated
	*/
	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		return _calendarBookingLocalService.updateCalendarBooking(calendarBooking);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, long calendarId,
		long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.updateCalendarBooking(userId,
			calendarBookingId, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.updateCalendarBooking(userId,
			calendarBookingId, calendarId, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, firstReminder,
			firstReminderType, secondReminder, secondReminderType, status,
			serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		long userId, long calendarBookingId, int instanceIndex,
		long calendarId, long[] childCalendarIds,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.updateCalendarBookingInstance(userId,
			calendarBookingId, instanceIndex, calendarId, childCalendarIds,
			titleMap, descriptionMap, location, startTime, endTime, allDay,
			recurrence, allFollowing, firstReminder, firstReminderType,
			secondReminder, secondReminderType, status, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
		long userId, long calendarBookingId, int instanceIndex,
		long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, boolean allFollowing,
		long firstReminder, java.lang.String firstReminderType,
		long secondReminder, java.lang.String secondReminderType, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.updateCalendarBookingInstance(userId,
			calendarBookingId, instanceIndex, calendarId, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateStatus(
		long userId,
		com.liferay.calendar.model.CalendarBooking calendarBooking, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.updateStatus(userId,
			calendarBooking, status, serviceContext);
	}

	@Override
	public com.liferay.calendar.model.CalendarBooking updateStatus(
		long userId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarBookingLocalService.updateStatus(userId,
			calendarBookingId, status, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public CalendarBookingLocalService getWrappedCalendarBookingLocalService() {
		return _calendarBookingLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {
		_calendarBookingLocalService = calendarBookingLocalService;
	}

	@Override
	public CalendarBookingLocalService getWrappedService() {
		return _calendarBookingLocalService;
	}

	@Override
	public void setWrappedService(
		CalendarBookingLocalService calendarBookingLocalService) {
		_calendarBookingLocalService = calendarBookingLocalService;
	}

	private CalendarBookingLocalService _calendarBookingLocalService;
}