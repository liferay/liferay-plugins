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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the kaleo log local service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoLogLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoLogLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoLogLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoLogLocalServiceImpl
 * @generated
 */
public class KaleoLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo log to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoLog(kaleoLog);
	}

	/**
	* Creates a new kaleo log with the primary key. Does not add the kaleo log to the database.
	*
	* @param kaleoLogId the primary key for the new kaleo log
	* @return the new kaleo log
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog createKaleoLog(
		long kaleoLogId) {
		return getService().createKaleoLog(kaleoLogId);
	}

	/**
	* Deletes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @throws PortalException if a kaleo log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKaleoLog(long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoLog(kaleoLogId);
	}

	/**
	* Deletes the kaleo log from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoLog(kaleoLog);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog fetchKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoLog(kaleoLogId);
	}

	/**
	* Returns the kaleo log with the primary key.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @return the kaleo log
	* @throws PortalException if a kaleo log with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog getKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoLog(kaleoLogId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo logs
	* @param end the upper bound of the range of kaleo logs (not inclusive)
	* @return the range of kaleo logs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoLogs(start, end);
	}

	/**
	* Returns the number of kaleo logs.
	*
	* @return the number of kaleo logs
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoLogsCount();
	}

	/**
	* Updates the kaleo log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoLog(kaleoLog);
	}

	/**
	* Updates the kaleo log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @param merge whether to merge the kaleo log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo log that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoLog(kaleoLog, merge);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addActionExecutionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		long startTime, long endTime, java.lang.String comment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addActionExecutionKaleoLog(kaleoInstanceToken, kaleoAction,
			startTime, endTime, comment, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeEntryKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addNodeEntryKaleoLog(kaleoInstanceToken, sourceKaleoNode,
			targetKaleoNode, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeExitKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode departingKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addNodeExitKaleoLog(kaleoInstanceToken, departingKaleoNode,
			serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskAssignmentKaleoLog(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> previousKaleoTaskAssignmentInstances,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTaskAssignmentKaleoLog(previousKaleoTaskAssignmentInstances,
			kaleoTaskInstanceToken, comment, workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskCompletionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTaskCompletionKaleoLog(kaleoTaskInstanceToken, comment,
			workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskUpdateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTaskUpdateKaleoLog(kaleoTaskInstanceToken, comment,
			workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceEndKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addWorkflowInstanceEndKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceStartKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addWorkflowInstanceStartKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	public static void deleteCompanyKaleoLogs(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoLogs(companyId);
	}

	public static void deleteKaleoDefinitionKaleoLogs(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoLogs(kaleoDefinitionId);
	}

	public static void deleteKaleoInstanceKaleoLogs(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoInstanceKaleoLogs(kaleoInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoInstanceKaleoLogs(
		long kaleoInstanceId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstanceKaleoLogs(kaleoInstanceId, logTypes, start,
			end, orderByComparator);
	}

	public static int getKaleoInstanceKaleoLogsCount(long kaleoInstanceId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstanceKaleoLogsCount(kaleoInstanceId, logTypes);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoTaskInstanceTokenKaleoLogs(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokenKaleoLogs(kaleoTaskInstanceTokenId,
			logTypes, start, end, orderByComparator);
	}

	public static int getKaleoTaskInstanceTokenKaleoLogsCount(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokenKaleoLogsCount(kaleoTaskInstanceTokenId,
			logTypes);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoLogLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoLogLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					KaleoLogLocalService.class.getName(), portletClassLoader);

			_service = new KaleoLogLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(KaleoLogLocalServiceUtil.class,
				"_service");
			MethodCache.remove(KaleoLogLocalService.class);
		}

		return _service;
	}

	public void setService(KaleoLogLocalService service) {
		MethodCache.remove(KaleoLogLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(KaleoLogLocalServiceUtil.class,
			"_service");
		MethodCache.remove(KaleoLogLocalService.class);
	}

	private static KaleoLogLocalService _service;
}