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

package com.liferay.socialcoding.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the j i r a issue local service. This utility wraps {@link com.liferay.socialcoding.service.impl.JIRAIssueLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssueLocalService
 * @see com.liferay.socialcoding.service.base.JIRAIssueLocalServiceBaseImpl
 * @see com.liferay.socialcoding.service.impl.JIRAIssueLocalServiceImpl
 * @generated
 */
public class JIRAIssueLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.socialcoding.service.impl.JIRAIssueLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the j i r a issue to the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue addJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addJIRAIssue(jiraIssue);
	}

	/**
	* Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	*
	* @param jiraIssueId the primary key for the new j i r a issue
	* @return the new j i r a issue
	*/
	public static com.liferay.socialcoding.model.JIRAIssue createJIRAIssue(
		long jiraIssueId) {
		return getService().createJIRAIssue(jiraIssueId);
	}

	/**
	* Deletes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @throws PortalException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAIssue(jiraIssueId);
	}

	/**
	* Deletes the j i r a issue from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAIssue(jiraIssue);
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

	public static com.liferay.socialcoding.model.JIRAIssue fetchJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchJIRAIssue(jiraIssueId);
	}

	/**
	* Returns the j i r a issue with the primary key.
	*
	* @param jiraIssueId the primary key of the j i r a issue
	* @return the j i r a issue
	* @throws PortalException if a j i r a issue with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssue(jiraIssueId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the j i r a issues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a issues
	* @param end the upper bound of the range of j i r a issues (not inclusive)
	* @return the range of j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getJIRAIssues(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssues(start, end);
	}

	/**
	* Returns the number of j i r a issues.
	*
	* @return the number of j i r a issues
	* @throws SystemException if a system exception occurred
	*/
	public static int getJIRAIssuesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssuesCount();
	}

	/**
	* Updates the j i r a issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @return the j i r a issue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAIssue(jiraIssue);
	}

	/**
	* Updates the j i r a issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jiraIssue the j i r a issue
	* @param merge whether to merge the j i r a issue with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the j i r a issue that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAIssue(jiraIssue, merge);
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

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(projectId, assigneeJiraUserId, start,
			end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(modifiedDate, projectId,
			assigneeJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(projectId, assigneeJiraUserId,
			status, start, end);
	}

	public static int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(projectId, assigneeJiraUserId);
	}

	public static int getAssigneeJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(modifiedDate, projectId,
			assigneeJiraUserId);
	}

	public static int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(projectId, assigneeJiraUserId,
			status);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFirstAssigneeJIRAIssue(projectId, assigneeJiraUserId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFirstReporterJIRAIssue(projectId, reporterJiraUserId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssue(key);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLastAssigneeJIRAIssue(projectId, assigneeJiraUserId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLastreporterJIRAIssue(projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssues(projectId, reporterJiraUserId, start,
			end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssues(modifiedDate, projectId,
			reporterJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssues(projectId, reporterJiraUserId,
			status, start, end);
	}

	public static int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(projectId, reporterJiraUserId);
	}

	public static int getReporterJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(modifiedDate, projectId,
			reporterJiraUserId);
	}

	public static int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(projectId, reporterJiraUserId,
			status);
	}

	public static void updateJIRAIssues(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateJIRAIssues(projectId);
	}

	public static void clearService() {
		_service = null;
	}

	public static JIRAIssueLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					JIRAIssueLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					JIRAIssueLocalService.class.getName(), portletClassLoader);

			_service = new JIRAIssueLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(JIRAIssueLocalServiceUtil.class,
				"_service");
			MethodCache.remove(JIRAIssueLocalService.class);
		}

		return _service;
	}

	public void setService(JIRAIssueLocalService service) {
		MethodCache.remove(JIRAIssueLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(JIRAIssueLocalServiceUtil.class,
			"_service");
		MethodCache.remove(JIRAIssueLocalService.class);
	}

	private static JIRAIssueLocalService _service;
}