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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for CalendarBooking. This utility wraps
 * {@link com.liferay.calendar.service.impl.CalendarBookingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingLocalService
 * @see com.liferay.calendar.service.base.CalendarBookingLocalServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarBookingLocalServiceImpl
 * @generated
 */
@ProviderType
public class CalendarBookingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarBookingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the calendar booking to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was added
	*/
	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		return getService().addCalendarBooking(calendarBooking);
	}

	public static com.liferay.calendar.model.CalendarBooking addCalendarBooking(
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
		return getService()
				   .addCalendarBooking(userId, calendarId, childCalendarIds,
			parentCalendarBookingId, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, firstReminder,
			firstReminderType, secondReminder, secondReminderType,
			serviceContext);
	}

	public static void checkCalendarBookings()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkCalendarBookings();
	}

	/**
	* Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	*
	* @param calendarBookingId the primary key for the new calendar booking
	* @return the new calendar booking
	*/
	public static com.liferay.calendar.model.CalendarBooking createCalendarBooking(
		long calendarBookingId) {
		return getService().createCalendarBooking(calendarBookingId);
	}

	/**
	* Deletes the calendar booking from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was removed
	* @throws PortalException
	*/
	public static com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCalendarBooking(calendarBooking);
	}

	/**
	* Deletes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking that was removed
	* @throws PortalException if a calendar booking with the primary key could not be found
	*/
	public static com.liferay.calendar.model.CalendarBooking deleteCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCalendarBooking(calendarBookingId);
	}

	public static void deleteCalendarBookingInstance(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		int instanceIndex, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCalendarBookingInstance(calendarBooking, instanceIndex,
			allFollowing);
	}

	public static void deleteCalendarBookingInstance(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		long startTime, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCalendarBookingInstance(calendarBooking, startTime,
			allFollowing);
	}

	public static void deleteCalendarBookingInstance(long calendarBookingId,
		long startTime, boolean allFollowing)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.deleteCalendarBookingInstance(calendarBookingId, startTime,
			allFollowing);
	}

	public static void deleteCalendarBookings(long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCalendarBookings(calendarId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static java.lang.String exportCalendarBooking(
		long calendarBookingId, java.lang.String type)
		throws java.lang.Exception {
		return getService().exportCalendarBooking(calendarBookingId, type);
	}

	public static com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarBookingId) {
		return getService().fetchCalendarBooking(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarId, java.lang.String vEventUid) {
		return getService().fetchCalendarBooking(calendarId, vEventUid);
	}

	public static com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		java.lang.String uuid, long groupId) {
		return getService().fetchCalendarBooking(uuid, groupId);
	}

	/**
	* Returns the calendar booking matching the UUID and group.
	*
	* @param uuid the calendar booking's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	*/
	public static com.liferay.calendar.model.CalendarBooking fetchCalendarBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchCalendarBookingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the calendar booking with the primary key.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking
	* @throws PortalException if a calendar booking with the primary key could not be found
	*/
	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendarBooking(calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarId, long parentCalendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarBooking(calendarId, parentCalendarBookingId);
	}

	/**
	* Returns the calendar booking matching the UUID and group.
	*
	* @param uuid the calendar booking's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar booking
	* @throws PortalException if a matching calendar booking could not be found
	*/
	public static com.liferay.calendar.model.CalendarBooking getCalendarBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCalendarBookingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.calendar.model.CalendarBooking getCalendarBookingInstance(
		long calendarBookingId, int instanceIndex)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCalendarBookingInstance(calendarBookingId, instanceIndex);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId) {
		return getService().getCalendarBookings(calendarId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime) {
		return getService().getCalendarBookings(calendarId, startTime, endTime);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, long startTime, long endTime, int max) {
		return getService()
				   .getCalendarBookings(calendarId, startTime, endTime, max);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		long calendarId, int[] statuses) {
		return getService().getCalendarBookings(calendarId, statuses);
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
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		int start, int end) {
		return getService().getCalendarBookings(start, end);
	}

	/**
	* Returns all the calendar bookings matching the UUID and company.
	*
	* @param uuid the UUID of the calendar bookings
	* @param companyId the primary key of the company
	* @return the matching calendar bookings, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getCalendarBookingsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of calendar bookings matching the UUID and company.
	*
	* @param uuid the UUID of the calendar bookings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching calendar bookings, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator) {
		return getService()
				   .getCalendarBookingsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of calendar bookings.
	*
	* @return the number of calendar bookings
	*/
	public static int getCalendarBookingsCount() {
		return getService().getCalendarBookingsCount();
	}

	public static int getCalendarBookingsCount(long calendarId,
		long parentCalendarBookingId) {
		return getService()
				   .getCalendarBookingsCount(calendarId, parentCalendarBookingId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long calendarBookingId) {
		return getService().getChildCalendarBookings(calendarBookingId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> getChildCalendarBookings(
		long parentCalendarBookingId, int status) {
		return getService()
				   .getChildCalendarBookings(parentCalendarBookingId, status);
	}

	public static long[] getChildCalendarIds(long calendarBookingId,
		long calendarId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getChildCalendarIds(calendarBookingId, calendarId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		long userId, com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveCalendarBookingToTrash(userId, calendarBooking);
	}

	public static com.liferay.calendar.model.CalendarBooking moveCalendarBookingToTrash(
		long userId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveCalendarBookingToTrash(userId, calendarBookingId);
	}

	public static com.liferay.calendar.model.CalendarBooking restoreCalendarBookingFromTrash(
		long userId, long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .restoreCalendarBookingFromTrash(userId, calendarBookingId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator) {
		return getService()
				   .search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, recurring, statuses, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking> orderByComparator) {
		return getService()
				   .search(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, title, description,
			location, startTime, endTime, recurring, statuses, andOperator,
			start, end, orderByComparator);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startTime, long endTime, int[] statuses) {
		return getService()
				   .searchCount(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, statuses);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startTime, long endTime, int[] statuses, boolean andOperator) {
		return getService()
				   .searchCount(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, title, description,
			location, startTime, endTime, statuses, andOperator);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void updateAsset(long userId,
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, calendarBooking, assetCategoryIds,
			assetTagNames, assetLinkEntryIds);
	}

	/**
	* Updates the calendar booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was updated
	*/
	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking) {
		return getService().updateCalendarBooking(calendarBooking);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
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
		return getService()
				   .updateCalendarBooking(userId, calendarBookingId,
			calendarId, childCalendarIds, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, firstReminder,
			firstReminderType, secondReminder, secondReminderType, status,
			serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, long calendarId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String location, long startTime, long endTime,
		boolean allDay, java.lang.String recurrence, long firstReminder,
		java.lang.String firstReminderType, long secondReminder,
		java.lang.String secondReminderType, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCalendarBooking(userId, calendarBookingId,
			calendarId, titleMap, descriptionMap, location, startTime, endTime,
			allDay, recurrence, firstReminder, firstReminderType,
			secondReminder, secondReminderType, status, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
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
		return getService()
				   .updateCalendarBookingInstance(userId, calendarBookingId,
			instanceIndex, calendarId, childCalendarIds, titleMap,
			descriptionMap, location, startTime, endTime, allDay, recurrence,
			allFollowing, firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateCalendarBookingInstance(
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
		return getService()
				   .updateCalendarBookingInstance(userId, calendarBookingId,
			instanceIndex, calendarId, titleMap, descriptionMap, location,
			startTime, endTime, allDay, recurrence, allFollowing,
			firstReminder, firstReminderType, secondReminder,
			secondReminderType, status, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateStatus(
		long userId,
		com.liferay.calendar.model.CalendarBooking calendarBooking, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, calendarBooking, status, serviceContext);
	}

	public static com.liferay.calendar.model.CalendarBooking updateStatus(
		long userId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, calendarBookingId, status,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarBookingLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarBookingLocalService.class.getName());

			if (invokableLocalService instanceof CalendarBookingLocalService) {
				_service = (CalendarBookingLocalService)invokableLocalService;
			}
			else {
				_service = new CalendarBookingLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CalendarBookingLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(CalendarBookingLocalService service) {
	}

	private static CalendarBookingLocalService _service;
}