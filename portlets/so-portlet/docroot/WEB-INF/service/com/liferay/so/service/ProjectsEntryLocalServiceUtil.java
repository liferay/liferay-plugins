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

package com.liferay.so.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the projects entry local service. This utility wraps {@link com.liferay.so.service.impl.ProjectsEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntryLocalService
 * @see com.liferay.so.service.base.ProjectsEntryLocalServiceBaseImpl
 * @see com.liferay.so.service.impl.ProjectsEntryLocalServiceImpl
 * @generated
 */
public class ProjectsEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.so.service.impl.ProjectsEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the projects entry to the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @return the projects entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.ProjectsEntry addProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addProjectsEntry(projectsEntry);
	}

	/**
	* Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	*
	* @param projectsEntryId the primary key for the new projects entry
	* @return the new projects entry
	*/
	public static com.liferay.so.model.ProjectsEntry createProjectsEntry(
		long projectsEntryId) {
		return getService().createProjectsEntry(projectsEntryId);
	}

	/**
	* Deletes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @throws PortalException if a projects entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteProjectsEntry(long projectsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteProjectsEntry(projectsEntryId);
	}

	/**
	* Deletes the projects entry from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteProjectsEntry(projectsEntry);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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

	public static com.liferay.so.model.ProjectsEntry fetchProjectsEntry(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchProjectsEntry(projectsEntryId);
	}

	/**
	* Returns the projects entry with the primary key.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry
	* @throws PortalException if a projects entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.ProjectsEntry getProjectsEntry(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getProjectsEntry(projectsEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of projects entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.ProjectsEntry> getProjectsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getProjectsEntries(start, end);
	}

	/**
	* Returns the number of projects entries.
	*
	* @return the number of projects entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getProjectsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getProjectsEntriesCount();
	}

	/**
	* Updates the projects entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @return the projects entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateProjectsEntry(projectsEntry);
	}

	/**
	* Updates the projects entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param projectsEntry the projects entry
	* @param merge whether to merge the projects entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the projects entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateProjectsEntry(projectsEntry, merge);
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

	public static com.liferay.so.model.ProjectsEntry addProjectsEntry(
		long userId, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int endDateMonth, int endDateDay, int endDateYear, boolean current,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addProjectsEntry(userId, title, description,
			startDateMonth, startDateDay, startDateYear, endDateMonth,
			endDateDay, endDateYear, current, data);
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> getUserProjectsEntries(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserProjectsEntries(userId);
	}

	public static int getUserProjectsEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserProjectsEntriesCount(userId);
	}

	public static com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		long projectsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int endDateMonth, int endDateDay, int endDateYear,
		boolean current, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateProjectsEntry(projectsEntryId, title, description,
			startDateMonth, startDateDay, startDateYear, endDateMonth,
			endDateDay, endDateYear, current, data);
	}

	public static void clearService() {
		_service = null;
	}

	public static ProjectsEntryLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ProjectsEntryLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					ProjectsEntryLocalService.class.getName(),
					portletClassLoader);

			_service = new ProjectsEntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(ProjectsEntryLocalServiceUtil.class,
				"_service");
			MethodCache.remove(ProjectsEntryLocalService.class);
		}

		return _service;
	}

	public void setService(ProjectsEntryLocalService service) {
		MethodCache.remove(ProjectsEntryLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(ProjectsEntryLocalServiceUtil.class,
			"_service");
		MethodCache.remove(ProjectsEntryLocalService.class);
	}

	private static ProjectsEntryLocalService _service;
}