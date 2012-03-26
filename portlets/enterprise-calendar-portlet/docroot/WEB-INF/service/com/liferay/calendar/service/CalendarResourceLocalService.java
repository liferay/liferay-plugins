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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PermissionedModelLocalService;

/**
 * The interface for the calendar resource local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceLocalServiceUtil
 * @see com.liferay.calendar.service.base.CalendarResourceLocalServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CalendarResourceLocalService
	extends PermissionedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalendarResourceLocalServiceUtil} to access the calendar resource local service. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the calendar resource to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	*
	* @param calendarResourceId the primary key for the new calendar resource
	* @return the new calendar resource
	*/
	public com.liferay.calendar.model.CalendarResource createCalendarResource(
		long calendarResourceId);

	/**
	* Deletes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource that was removed
	* @throws PortalException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the calendar resource from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar resource with the primary key.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource
	* @throws PortalException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the calendar resource with the UUID in the group.
	*
	* @param uuid the UUID of calendar resource
	* @param groupId the group id of the calendar resource
	* @return the calendar resource
	* @throws PortalException if a calendar resource with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.calendar.model.CalendarResource getCalendarResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of calendar resources.
	*
	* @return the number of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCalendarResourcesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		long userId, long groupId, java.lang.String className, long classPK,
		java.lang.String classUuid, long defaultCalendarId,
		java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, java.lang.String type, boolean active,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.calendar.model.CalendarResource> searchByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, java.lang.String type, boolean active,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId, long defaultCalendarId, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.calendar.model.CalendarResource updateDefaultCalendarId(
		long calendarResourceId, long defaultCalendarId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}