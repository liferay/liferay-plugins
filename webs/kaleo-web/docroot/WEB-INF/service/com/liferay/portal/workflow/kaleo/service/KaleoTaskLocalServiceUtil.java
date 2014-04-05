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
 * Provides the local service utility for KaleoTask. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTaskLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl
 * @generated
 */
public class KaleoTaskLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo task to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task
	* @return the kaleo task that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask addKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoTask(kaleoTask);
	}

	/**
	* Creates a new kaleo task with the primary key. Does not add the kaleo task to the database.
	*
	* @param kaleoTaskId the primary key for the new kaleo task
	* @return the new kaleo task
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask createKaleoTask(
		long kaleoTaskId) {
		return getService().createKaleoTask(kaleoTaskId);
	}

	/**
	* Deletes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskId the primary key of the kaleo task
	* @return the kaleo task that was removed
	* @throws PortalException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask deleteKaleoTask(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoTask(kaleoTaskId);
	}

	/**
	* Deletes the kaleo task from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task
	* @return the kaleo task that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask deleteKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoTask(kaleoTask);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTask fetchKaleoTask(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoTask(kaleoTaskId);
	}

	/**
	* Returns the kaleo task with the primary key.
	*
	* @param kaleoTaskId the primary key of the kaleo task
	* @return the kaleo task
	* @throws PortalException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoTask(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTask(kaleoTaskId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo tasks
	* @param end the upper bound of the range of kaleo tasks (not inclusive)
	* @return the range of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> getKaleoTasks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTasks(start, end);
	}

	/**
	* Returns the number of kaleo tasks.
	*
	* @return the number of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoTasksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTasksCount();
	}

	/**
	* Updates the kaleo task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task
	* @return the kaleo task that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask updateKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTask(kaleoTask);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTask addKaleoTask(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Task task,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTask(kaleoDefinitionId, kaleoNodeId, task,
			serviceContext);
	}

	public static void deleteCompanyKaleoTasks(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoTasks(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTasks(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoTasks(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoNodeKaleoTask(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNodeKaleoTask(kaleoNodeId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoTaskLocalService.class.getName());

			if (invokableLocalService instanceof KaleoTaskLocalService) {
				_service = (KaleoTaskLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoTaskLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoTaskLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoTaskLocalService service) {
	}

	private static KaleoTaskLocalService _service;
}