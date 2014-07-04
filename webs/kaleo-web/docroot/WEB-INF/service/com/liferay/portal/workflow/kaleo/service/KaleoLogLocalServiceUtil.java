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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KaleoLog. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoLogLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
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
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addActionExecutionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		long startTime, long endTime, java.lang.String comment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addActionExecutionKaleoLog(kaleoInstanceToken, kaleoAction,
			startTime, endTime, comment, serviceContext);
	}

	/**
	* Adds the kaleo log to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was added
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return getService().addKaleoLog(kaleoLog);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeEntryKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addNodeEntryKaleoLog(kaleoInstanceToken, sourceKaleoNode,
			targetKaleoNode, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeExitKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode departingKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
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
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTaskAssignmentKaleoLog(previousKaleoTaskAssignmentInstances,
			kaleoTaskInstanceToken, comment, workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskCompletionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTaskCompletionKaleoLog(kaleoTaskInstanceToken, comment,
			workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskUpdateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTaskUpdateKaleoLog(kaleoTaskInstanceToken, comment,
			workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceEndKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addWorkflowInstanceEndKaleoLog(kaleoInstanceToken,
			serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceStartKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addWorkflowInstanceStartKaleoLog(kaleoInstanceToken,
			serviceContext);
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

	public static void deleteCompanyKaleoLogs(long companyId) {
		getService().deleteCompanyKaleoLogs(companyId);
	}

	public static void deleteKaleoDefinitionKaleoLogs(long kaleoDefinitionId) {
		getService().deleteKaleoDefinitionKaleoLogs(kaleoDefinitionId);
	}

	public static void deleteKaleoInstanceKaleoLogs(long kaleoInstanceId) {
		getService().deleteKaleoInstanceKaleoLogs(kaleoInstanceId);
	}

	/**
	* Deletes the kaleo log from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was removed
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog deleteKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return getService().deleteKaleoLog(kaleoLog);
	}

	/**
	* Deletes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @return the kaleo log that was removed
	* @throws PortalException if a kaleo log with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog deleteKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKaleoLog(kaleoLogId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoLog fetchKaleoLog(
		long kaleoLogId) {
		return getService().fetchKaleoLog(kaleoLogId);
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

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoInstanceKaleoLogs(
		long kaleoInstanceId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .getKaleoInstanceKaleoLogs(kaleoInstanceId, logTypes, start,
			end, orderByComparator);
	}

	public static int getKaleoInstanceKaleoLogsCount(long kaleoInstanceId,
		java.util.List<java.lang.Integer> logTypes) {
		return getService()
				   .getKaleoInstanceKaleoLogsCount(kaleoInstanceId, logTypes);
	}

	/**
	* Returns the kaleo log with the primary key.
	*
	* @param kaleoLogId the primary key of the kaleo log
	* @return the kaleo log
	* @throws PortalException if a kaleo log with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog getKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKaleoLog(kaleoLogId);
	}

	/**
	* Returns a range of all the kaleo logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo logs
	* @param end the upper bound of the range of kaleo logs (not inclusive)
	* @return the range of kaleo logs
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoLogs(
		int start, int end) {
		return getService().getKaleoLogs(start, end);
	}

	/**
	* Returns the number of kaleo logs.
	*
	* @return the number of kaleo logs
	*/
	public static int getKaleoLogsCount() {
		return getService().getKaleoLogsCount();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoTaskInstanceTokenKaleoLogs(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .getKaleoTaskInstanceTokenKaleoLogs(kaleoTaskInstanceTokenId,
			logTypes, start, end, orderByComparator);
	}

	public static int getKaleoTaskInstanceTokenKaleoLogsCount(
		long kaleoTaskInstanceTokenId,
		java.util.List<java.lang.Integer> logTypes) {
		return getService()
				   .getKaleoTaskInstanceTokenKaleoLogsCount(kaleoTaskInstanceTokenId,
			logTypes);
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
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the kaleo log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoLog the kaleo log
	* @return the kaleo log that was updated
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return getService().updateKaleoLog(kaleoLog);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoLogLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoLogLocalService.class.getName());

			if (invokableLocalService instanceof KaleoLogLocalService) {
				_service = (KaleoLogLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoLogLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoLogLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(KaleoLogLocalService service) {
	}

	private static KaleoLogLocalService _service;
}