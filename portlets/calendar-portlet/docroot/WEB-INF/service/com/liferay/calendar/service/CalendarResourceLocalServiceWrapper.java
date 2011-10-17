/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * This class is a wrapper for {@link CalendarResourceLocalService}.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarResourceLocalService
 * @generated
 */
public class CalendarResourceLocalServiceWrapper
	implements CalendarResourceLocalService,
		ServiceWrapper<CalendarResourceLocalService> {
	public CalendarResourceLocalServiceWrapper(
		CalendarResourceLocalService calendarResourceLocalService) {
		_calendarResourceLocalService = calendarResourceLocalService;
	}

	/**
	* Adds the calendar resource to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.addCalendarResource(calendarResource);
	}

	/**
	* Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	*
	* @param calendarResourceId the primary key for the new calendar resource
	* @return the new calendar resource
	*/
	public com.liferay.calendar.model.CalendarResource createCalendarResource(
		long calendarResourceId) {
		return _calendarResourceLocalService.createCalendarResource(calendarResourceId);
	}

	/**
	* Deletes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @throws PortalException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCalendarResource(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarResourceLocalService.deleteCalendarResource(calendarResourceId);
	}

	/**
	* Deletes the calendar resource from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarResourceLocalService.deleteCalendarResource(calendarResource);
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
		return _calendarResourceLocalService.dynamicQuery(dynamicQuery);
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
		return _calendarResourceLocalService.dynamicQuery(dynamicQuery, start,
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
		return _calendarResourceLocalService.dynamicQuery(dynamicQuery, start,
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
		return _calendarResourceLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the calendar resource with the primary key.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource
	* @throws PortalException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResource(calendarResourceId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the calendar resource with the UUID in the group.
	*
	* @param uuid the UUID of calendar resource
	* @param groupId the group id of the calendar resource
	* @return the calendar resource
	* @throws PortalException if a calendar resource with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource getCalendarResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResourceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResources(start, end);
	}

	/**
	* Returns the number of calendar resources.
	*
	* @return the number of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public int getCalendarResourcesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResourcesCount();
	}

	/**
	* Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.updateCalendarResource(calendarResource);
	}

	/**
	* Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @param merge whether to merge the calendar resource with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the calendar resource that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.updateCalendarResource(calendarResource,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _calendarResourceLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calendarResourceLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		long userId, java.lang.String className, long classPK,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.addCalendarResource(userId,
			className, classPK, nameMap, descriptionMap, active, serviceContext);
	}

	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResource(className,
			classPK);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResources(active,
			start, end, orderByComparator);
	}

	public int getCalendarResourcesCount(boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCalendarResourcesCount(active);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getCompanyCalendarResources(
		long companyId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCompanyCalendarResources(companyId,
			name, active, start, end, orderByComparator);
	}

	public int getCompanyCalendarResourcesCount(long companyId,
		java.lang.String name, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getCompanyCalendarResourcesCount(companyId,
			name, active, start, end, orderByComparator);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getGroupCalendarResources(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getGroupCalendarResources(groupId,
			active, start, end, orderByComparator);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getGroupCalendarResources(
		long groupId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getGroupCalendarResources(groupId,
			name, active, start, end, orderByComparator);
	}

	public int getGroupCalendarResourcesCount(long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getGroupCalendarResourcesCount(groupId,
			active);
	}

	public int getGroupCalendarResourcesCount(long groupId,
		java.lang.String name, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.getGroupCalendarResourcesCount(groupId,
			name, active, start, end, orderByComparator);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, java.lang.String name,
		java.lang.String description, java.lang.Boolean active,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.search(companyId, groupIds, name,
			description, active, andOperator, start, end, orderByComparator);
	}

	public int searchCount(long companyId, long[] groupIds,
		java.lang.String name, java.lang.String description,
		java.lang.Boolean active, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.searchCount(companyId, groupIds,
			name, description, active, andOperator);
	}

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calendarResourceLocalService.updateCalendarResource(calendarResourceId,
			nameMap, descriptionMap, active, serviceContext);
	}

	public void updateCalendarResourceResources(
		com.liferay.calendar.model.CalendarResource calendarResource,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calendarResourceLocalService.updateCalendarResourceResources(calendarResource,
			communityPermissions, guestPermissions);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalendarResourceLocalService getWrappedCalendarResourceLocalService() {
		return _calendarResourceLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalendarResourceLocalService(
		CalendarResourceLocalService calendarResourceLocalService) {
		_calendarResourceLocalService = calendarResourceLocalService;
	}

	public CalendarResourceLocalService getWrappedService() {
		return _calendarResourceLocalService;
	}

	public void setWrappedService(
		CalendarResourceLocalService calendarResourceLocalService) {
		_calendarResourceLocalService = calendarResourceLocalService;
	}

	private CalendarResourceLocalService _calendarResourceLocalService;
}