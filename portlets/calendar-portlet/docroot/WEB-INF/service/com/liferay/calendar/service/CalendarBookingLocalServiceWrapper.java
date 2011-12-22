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

package com.liferay.calendar.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalendarBookingLocalService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarBookingLocalService
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.addCalendarBooking(calendarBooking);
	}

	/**
	* Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	*
	* @param calendarBookingId the primary key for the new calendar booking
	* @return the new calendar booking
	*/
	public com.liferay.calendar.model.CalendarBooking createCalendarBooking(
		long calendarBookingId) {
		return _calendarBookingLocalService.createCalendarBooking(calendarBookingId);
	}

	/**
	* Deletes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @throws PortalException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCalendarBooking(long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarBookingLocalService.deleteCalendarBooking(calendarBookingId);
	}

	/**
	* Deletes the calendar booking from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.SystemException {
		_calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.calendar.model.CalendarBooking fetchCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.fetchCalendarBooking(calendarBookingId);
	}

	/**
	* Returns the calendar booking with the primary key.
	*
	* @param calendarBookingId the primary key of the calendar booking
	* @return the calendar booking
	* @throws PortalException if a calendar booking with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking getCalendarBooking(
		long calendarBookingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarBooking(calendarBookingId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the calendar booking with the UUID in the group.
	*
	* @param uuid the UUID of calendar booking
	* @param groupId the group id of the calendar booking
	* @return the calendar booking
	* @throws PortalException if a calendar booking with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking getCalendarBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarBookingByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the calendar bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendar bookings
	* @param end the upper bound of the range of calendar bookings (not inclusive)
	* @return the range of calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarBookings(start, end);
	}

	/**
	* Returns the number of calendar bookings.
	*
	* @return the number of calendar bookings
	* @throws SystemException if a system exception occurred
	*/
	public int getCalendarBookingsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarBookingsCount();
	}

	/**
	* Updates the calendar booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @return the calendar booking that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.updateCalendarBooking(calendarBooking);
	}

	/**
	* Updates the calendar booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarBooking the calendar booking
	* @param merge whether to merge the calendar booking with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the calendar booking that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.updateCalendarBooking(calendarBooking,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _calendarBookingLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarBookingLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.calendar.model.CalendarBooking addCalendarBooking(
		long userId, long calendarEventId, long calendarResourceId,
		boolean required,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.addCalendarBooking(userId,
			calendarEventId, calendarResourceId, required, serviceContext);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarBookings(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarBookings(className,
			classPK, start, end, orderByComparator);
	}

	public int getCalendarBookingsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarBookingsCount(className,
			classPK);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarEventCalendarBookings(
		long calendarEventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarEventCalendarBookings(calendarEventId,
			start, end, orderByComparator);
	}

	public int getCalendarEventCalendarBookingsCount(long calendarEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarEventCalendarBookingsCount(calendarEventId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> getCalendarResourceCalendarBookings(
		long calendarResourceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarResourceCalendarBookings(calendarResourceId,
			start, end, orderByComparator);
	}

	public int getCalendarResourceCalendarBookingsCount(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.getCalendarResourceCalendarBookingsCount(calendarResourceId);
	}

	public java.util.List<com.liferay.calendar.model.CalendarBooking> search(
		long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.search(calendarResourceId, title,
			description, type, andOperator, start, end, orderByComparator);
	}

	public int searchCount(long calendarResourceId, java.lang.String title,
		java.lang.String description, java.lang.String type, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.searchCount(calendarResourceId,
			title, description, type, andOperator);
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, boolean required,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.updateCalendarBooking(calendarBookingId,
			required, serviceContext);
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long userId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.updateCalendarBooking(userId,
			calendarBookingId, status, serviceContext);
	}

	public com.liferay.calendar.model.CalendarBooking updateCalendarBooking(
		long calendarBookingId, long calendarEventId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.updateCalendarBooking(calendarBookingId,
			calendarEventId, serviceContext);
	}

	public void updateCalendarBookings(long calendarEventId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarBookingLocalService.updateCalendarBookings(calendarEventId,
			serviceContext);
	}

	public com.liferay.calendar.model.CalendarBooking updateStatus(
		long userId, long calendarBookingId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarBookingLocalService.updateStatus(userId,
			calendarBookingId, status, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarBookingLocalService getWrappedCalendarBookingLocalService() {
		return _calendarBookingLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarBookingLocalService(
		CalendarBookingLocalService calendarBookingLocalService) {
		_calendarBookingLocalService = calendarBookingLocalService;
	}

	public CalendarBookingLocalService getWrappedService() {
		return _calendarBookingLocalService;
	}

	public void setWrappedService(
		CalendarBookingLocalService calendarBookingLocalService) {
		_calendarBookingLocalService = calendarBookingLocalService;
	}

	private CalendarBookingLocalService _calendarBookingLocalService;
}