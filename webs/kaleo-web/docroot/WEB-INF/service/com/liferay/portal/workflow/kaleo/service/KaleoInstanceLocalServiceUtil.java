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
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		long kaleoDefinitionId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKaleoInstance(kaleoDefinitionId, kaleoDefinitionName,
			kaleoDefinitionVersion, workflowContext, serviceContext);
	}

	/**
	* Adds the kaleo instance to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstance the kaleo instance
	* @return the kaleo instance that was added
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		return getService().addKaleoInstance(kaleoInstance);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance completeKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().completeKaleoInstance(kaleoInstanceId);
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

	public static void deleteCompanyKaleoInstances(long companyId) {
		getService().deleteCompanyKaleoInstances(companyId);
	}

	public static void deleteKaleoDefinitionKaleoInstances(
		long kaleoDefinitionId) {
		getService().deleteKaleoDefinitionKaleoInstances(kaleoDefinitionId);
	}

	/**
	* Deletes the kaleo instance from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstance the kaleo instance
	* @return the kaleo instance that was removed
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance deleteKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		return getService().deleteKaleoInstance(kaleoInstance);
	}

	/**
	* Deletes the kaleo instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance that was removed
	* @throws PortalException if a kaleo instance with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance deleteKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKaleoInstance(kaleoInstanceId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance fetchKaleoInstance(
		long kaleoInstanceId) {
		return getService().fetchKaleoInstance(kaleoInstanceId);
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

	/**
	* Returns the kaleo instance with the primary key.
	*
	* @param kaleoInstanceId the primary key of the kaleo instance
	* @return the kaleo instance
	* @throws PortalException if a kaleo instance with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKaleoInstance(kaleoInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstance> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoInstances(kaleoDefinitionName,
			kaleoDefinitionVersion, completed, start, end, orderByComparator,
			serviceContext);
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
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		int start, int end) {
		return getService().getKaleoInstances(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.Long userId, java.lang.String assetClassName,
		java.lang.Long assetClassPK, java.lang.Boolean completed, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstance> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoInstances(userId, assetClassName, assetClassPK,
			completed, start, end, orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.Long userId, java.lang.String[] assetClassNames,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoInstance> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoInstances(userId, assetClassNames, completed,
			start, end, orderByComparator, serviceContext);
	}

	/**
	* Returns the number of kaleo instances.
	*
	* @return the number of kaleo instances
	*/
	public static int getKaleoInstancesCount() {
		return getService().getKaleoInstancesCount();
	}

	public static int getKaleoInstancesCount(long kaleoDefinitionId,
		boolean completed) {
		return getService().getKaleoInstancesCount(kaleoDefinitionId, completed);
	}

	public static int getKaleoInstancesCount(
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoInstancesCount(kaleoDefinitionName,
			kaleoDefinitionVersion, completed, serviceContext);
	}

	public static int getKaleoInstancesCount(java.lang.Long userId,
		java.lang.String assetClassName, java.lang.Long assetClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoInstancesCount(userId, assetClassName,
			assetClassPK, completed, serviceContext);
	}

	public static int getKaleoInstancesCount(java.lang.Long userId,
		java.lang.String[] assetClassNames, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoInstancesCount(userId, assetClassNames, completed,
			serviceContext);
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
	* Updates the kaleo instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstance the kaleo instance
	* @return the kaleo instance that was updated
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		return getService().updateKaleoInstance(kaleoInstance);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		long kaleoInstanceId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
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
	@Deprecated
	public void setService(KaleoInstanceLocalService service) {
	}

	private static KaleoInstanceLocalService _service;
}