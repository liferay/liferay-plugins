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
 * Provides the local service utility for KaleoInstance. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoInstanceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoInstanceLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoInstanceLocalServiceImpl
 * @generated
 */
public class KaleoInstanceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoInstanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo instance to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstance the kaleo instance
	* @return the kaleo instance that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoInstance(kaleoInstance);
	}

	/**
	* Creates a new kaleo instance with the primary key. Does not add the kaleo instance to the database.
	*
	* @param kaleoInstanceId the primary key for the new kaleo instance
	* @return the new kaleo instance
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance createKaleoInstance(
		long kaleoInstanceId) {
		return getService().createKaleoInstance(kaleoInstanceId);
	}

	/**
	* Deletes the kaleo instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance that was removed
	* @throws PortalException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance deleteKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoInstance(kaleoInstanceId);
	}

	/**
	* Deletes the kaleo instance from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstance the kaleo instance
	* @return the kaleo instance that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance deleteKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoInstance(kaleoInstance);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoInstance(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance with the primary key.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance
	* @throws PortalException if a kaleo instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoInstance(kaleoInstanceId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo instances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instances
	* @param end the upper bound of the range of kaleo instances (not inclusive)
	* @return the range of kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoInstances(start, end);
	}

	/**
	* Returns the number of kaleo instances.
	*
	* @return the number of kaleo instances
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoInstancesCount();
	}

	/**
	* Updates the kaleo instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstance the kaleo instance
	* @return the kaleo instance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoInstance(kaleoInstance);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		long kaleoDefinitionId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoInstance(kaleoDefinitionId, kaleoDefinitionName,
			kaleoDefinitionVersion, workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance completeKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().completeKaleoInstance(kaleoInstanceId);
	}

	public static void deleteCompanyKaleoInstances(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoInstances(companyId);
	}

	public static void deleteKaleoDefinitionKaleoInstances(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoInstances(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.Long userId, java.lang.String assetClassName,
		java.lang.Long assetClassPK, java.lang.Boolean completed, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstances(userId, assetClassName, assetClassPK,
			completed, start, end, orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.Long userId, java.lang.String[] assetClassNames,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstances(userId, assetClassNames, completed,
			start, end, orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstances(kaleoDefinitionName,
			kaleoDefinitionVersion, completed, start, end, orderByComparator,
			serviceContext);
	}

	public static int getKaleoInstancesCount(long kaleoDefinitionId,
		boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoInstancesCount(kaleoDefinitionId, completed);
	}

	public static int getKaleoInstancesCount(java.lang.Long userId,
		java.lang.String assetClassName, java.lang.Long assetClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstancesCount(userId, assetClassName,
			assetClassPK, completed, serviceContext);
	}

	public static int getKaleoInstancesCount(java.lang.Long userId,
		java.lang.String[] assetClassNames, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstancesCount(userId, assetClassNames, completed,
			serviceContext);
	}

	public static int getKaleoInstancesCount(
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoInstancesCount(kaleoDefinitionName,
			kaleoDefinitionVersion, completed, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		long kaleoInstanceId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoInstance(kaleoInstanceId, workflowContext,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoInstanceLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoInstanceLocalService.class.getName());

			if (invokableLocalService instanceof KaleoInstanceLocalService) {
				_service = (KaleoInstanceLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoInstanceLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoInstanceLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoInstanceLocalService service) {
	}

	private static KaleoInstanceLocalService _service;
}