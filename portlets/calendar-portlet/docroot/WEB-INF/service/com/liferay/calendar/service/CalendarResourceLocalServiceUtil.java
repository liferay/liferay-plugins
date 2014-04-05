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
 * Provides the local service utility for CalendarResource. This utility wraps
 * {@link com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceLocalService
 * @see com.liferay.calendar.service.base.CalendarResourceLocalServiceBaseImpl
 * @see com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl
 * @generated
 */
public class CalendarResourceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.calendar.service.impl.CalendarResourceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the calendar resource to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource addCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCalendarResource(calendarResource);
	}

	/**
	* Creates a new calendar resource with the primary key. Does not add the calendar resource to the database.
	*
	* @param calendarResourceId the primary key for the new calendar resource
	* @return the new calendar resource
	*/
	public static com.liferay.calendar.model.CalendarResource createCalendarResource(
		long calendarResourceId) {
		return getService().createCalendarResource(calendarResourceId);
	}

	/**
	* Deletes the calendar resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource that was removed
	* @throws PortalException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCalendarResource(calendarResourceId);
	}

	/**
	* Deletes the calendar resource from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCalendarResource(calendarResource);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCalendarResource(calendarResourceId);
	}

	/**
	* Returns the calendar resource with the matching UUID and company.
	*
	* @param uuid the calendar resource's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchCalendarResourceByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchCalendarResourceByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the calendar resource matching the UUID and group.
	*
	* @param uuid the calendar resource's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar resource, or <code>null</code> if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource fetchCalendarResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCalendarResourceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the calendar resource with the primary key.
	*
	* @param calendarResourceId the primary key of the calendar resource
	* @return the calendar resource
	* @throws PortalException if a calendar resource with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarResource(calendarResourceId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the calendar resource with the matching UUID and company.
	*
	* @param uuid the calendar resource's UUID
	* @param companyId the primary key of the company
	* @return the matching calendar resource
	* @throws PortalException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource getCalendarResourceByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCalendarResourceByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the calendar resource matching the UUID and group.
	*
	* @param uuid the calendar resource's UUID
	* @param groupId the primary key of the group
	* @return the matching calendar resource
	* @throws PortalException if a matching calendar resource could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource getCalendarResourceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarResourceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the calendar resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar resources
	* @param end the upper bound of the range of calendar resources (not inclusive)
	* @return the range of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarResources(start, end);
	}

	/**
	* Returns the number of calendar resources.
	*
	* @return the number of calendar resources
	* @throws SystemException if a system exception occurred
	*/
	public static int getCalendarResourcesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarResourcesCount();
	}

	/**
	* Updates the calendar resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarResource the calendar resource
	* @return the calendar resource that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.calendar.model.CalendarResource updateCalendarResource(
		com.liferay.calendar.model.CalendarResource calendarResource)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCalendarResource(calendarResource);
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

	public static com.liferay.calendar.model.CalendarResource addCalendarResource(
		long userId, long groupId, long classNameId, long classPK,
		java.lang.String classUuid, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCalendarResource(userId, groupId, classNameId, classPK,
			classUuid, code, nameMap, descriptionMap, active, serviceContext);
	}

	public static void deleteCalendarResources(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCalendarResources(groupId);
	}

	public static com.liferay.calendar.model.CalendarResource fetchCalendarResource(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCalendarResource(classNameId, classPK);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> getCalendarResources(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCalendarResources(groupId);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(companyId, groupIds, classNameIds, code, name,
			description, active, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> searchByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchByKeywords(companyId, groupIds, classNameIds,
			keywords, active, andOperator, start, end, orderByComparator);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, groupIds, classNameIds, keywords,
			active);
	}

	public static int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(companyId, groupIds, classNameIds, code, name,
			description, active, andOperator);
	}

	public static void updateAsset(long userId,
		com.liferay.calendar.model.CalendarResource calendarResource,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateAsset(userId, calendarResource, assetCategoryIds,
			assetTagNames);
	}

	public static com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCalendarResource(calendarResourceId, nameMap,
			descriptionMap, active, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CalendarResourceLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CalendarResourceLocalService.class.getName());

			if (invokableLocalService instanceof CalendarResourceLocalService) {
				_service = (CalendarResourceLocalService)invokableLocalService;
			}
			else {
				_service = new CalendarResourceLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CalendarResourceLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(CalendarResourceLocalService service) {
	}

	private static CalendarResourceLocalService _service;
}