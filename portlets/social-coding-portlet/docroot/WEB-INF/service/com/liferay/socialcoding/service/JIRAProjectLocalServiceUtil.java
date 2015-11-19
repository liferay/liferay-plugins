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

package com.liferay.socialcoding.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for JIRAProject. This utility wraps
 * {@link com.liferay.socialcoding.service.impl.JIRAProjectLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectLocalService
 * @see com.liferay.socialcoding.service.base.JIRAProjectLocalServiceBaseImpl
 * @see com.liferay.socialcoding.service.impl.JIRAProjectLocalServiceImpl
 * @generated
 */
@ProviderType
public class JIRAProjectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.socialcoding.service.impl.JIRAProjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the j i r a project to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @return the j i r a project that was added
	*/
	public static com.liferay.socialcoding.model.JIRAProject addJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject) {
		return getService().addJIRAProject(jiraProject);
	}

	/**
	* Creates a new j i r a project with the primary key. Does not add the j i r a project to the database.
	*
	* @param jiraProjectId the primary key for the new j i r a project
	* @return the new j i r a project
	*/
	public static com.liferay.socialcoding.model.JIRAProject createJIRAProject(
		long jiraProjectId) {
		return getService().createJIRAProject(jiraProjectId);
	}

	/**
	* Deletes the j i r a project from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @return the j i r a project that was removed
	*/
	public static com.liferay.socialcoding.model.JIRAProject deleteJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject) {
		return getService().deleteJIRAProject(jiraProject);
	}

	/**
	* Deletes the j i r a project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project that was removed
	* @throws PortalException if a j i r a project with the primary key could not be found
	*/
	public static com.liferay.socialcoding.model.JIRAProject deleteJIRAProject(
		long jiraProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteJIRAProject(jiraProjectId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.socialcoding.model.JIRAProject fetchJIRAProject(
		long jiraProjectId) {
		return getService().fetchJIRAProject(jiraProjectId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the j i r a project with the primary key.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project
	* @throws PortalException if a j i r a project with the primary key could not be found
	*/
	public static com.liferay.socialcoding.model.JIRAProject getJIRAProject(
		long jiraProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJIRAProject(jiraProjectId);
	}

	/**
	* Returns a range of all the j i r a projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.JIRAProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a projects
	* @param end the upper bound of the range of j i r a projects (not inclusive)
	* @return the range of j i r a projects
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAProject> getJIRAProjects(
		int start, int end) {
		return getService().getJIRAProjects(start, end);
	}

	/**
	* Returns the number of j i r a projects.
	*
	* @return the number of j i r a projects
	*/
	public static int getJIRAProjectsCount() {
		return getService().getJIRAProjectsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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

	/**
	* Updates the j i r a project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraProject the j i r a project
	* @return the j i r a project that was updated
	*/
	public static com.liferay.socialcoding.model.JIRAProject updateJIRAProject(
		com.liferay.socialcoding.model.JIRAProject jiraProject) {
		return getService().updateJIRAProject(jiraProject);
	}

	public static void clearService() {
		_service = null;
	}

	public static JIRAProjectLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					JIRAProjectLocalService.class.getName());

			if (invokableLocalService instanceof JIRAProjectLocalService) {
				_service = (JIRAProjectLocalService)invokableLocalService;
			}
			else {
				_service = new JIRAProjectLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(JIRAProjectLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(JIRAProjectLocalService service) {
	}

	private static JIRAProjectLocalService _service;
}