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
 * Provides the local service utility for KaleoCondition. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoConditionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoConditionLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoConditionLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoConditionLocalServiceImpl
 * @generated
 */
public class KaleoConditionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoConditionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo condition to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoCondition the kaleo condition
	* @return the kaleo condition that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition addKaleoCondition(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoCondition(kaleoCondition);
	}

	/**
	* Creates a new kaleo condition with the primary key. Does not add the kaleo condition to the database.
	*
	* @param kaleoConditionId the primary key for the new kaleo condition
	* @return the new kaleo condition
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition createKaleoCondition(
		long kaleoConditionId) {
		return getService().createKaleoCondition(kaleoConditionId);
	}

	/**
	* Deletes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition that was removed
	* @throws PortalException if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition deleteKaleoCondition(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoCondition(kaleoConditionId);
	}

	/**
	* Deletes the kaleo condition from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoCondition the kaleo condition
	* @return the kaleo condition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition deleteKaleoCondition(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoCondition(kaleoCondition);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchKaleoCondition(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoCondition(kaleoConditionId);
	}

	/**
	* Returns the kaleo condition with the primary key.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition
	* @throws PortalException if a kaleo condition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition getKaleoCondition(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoCondition(kaleoConditionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo conditions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo conditions
	* @param end the upper bound of the range of kaleo conditions (not inclusive)
	* @return the range of kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> getKaleoConditions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoConditions(start, end);
	}

	/**
	* Returns the number of kaleo conditions.
	*
	* @return the number of kaleo conditions
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoConditionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoConditionsCount();
	}

	/**
	* Updates the kaleo condition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoCondition the kaleo condition
	* @return the kaleo condition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition updateKaleoCondition(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoCondition(kaleoCondition);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition addKaleoCondition(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Condition condition,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoCondition(kaleoDefinitionId, kaleoNodeId,
			condition, serviceContext);
	}

	public static void deleteCompanyKaleoConditions(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoConditions(companyId);
	}

	public static void deleteKaleoDefinitionKaleoCondition(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoCondition(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoCondition getKaleoNodeKaleoCondition(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNodeKaleoCondition(kaleoNodeId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoConditionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoConditionLocalService.class.getName());

			if (invokableLocalService instanceof KaleoConditionLocalService) {
				_service = (KaleoConditionLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoConditionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoConditionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoConditionLocalService service) {
	}

	private static KaleoConditionLocalService _service;
}