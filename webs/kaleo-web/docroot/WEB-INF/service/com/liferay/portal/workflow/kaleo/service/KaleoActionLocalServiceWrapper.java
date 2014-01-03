/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoActionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoActionLocalService
 * @generated
 */
public class KaleoActionLocalServiceWrapper implements KaleoActionLocalService,
	ServiceWrapper<KaleoActionLocalService> {
	public KaleoActionLocalServiceWrapper(
		KaleoActionLocalService kaleoActionLocalService) {
		_kaleoActionLocalService = kaleoActionLocalService;
	}

	/**
	* Adds the kaleo action to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoAction the kaleo action
	* @return the kaleo action that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction addKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.addKaleoAction(kaleoAction);
	}

	/**
	* Creates a new kaleo action with the primary key. Does not add the kaleo action to the database.
	*
	* @param kaleoActionId the primary key for the new kaleo action
	* @return the new kaleo action
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction createKaleoAction(
		long kaleoActionId) {
		return _kaleoActionLocalService.createKaleoAction(kaleoActionId);
	}

	/**
	* Deletes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoActionId the primary key of the kaleo action
	* @return the kaleo action that was removed
	* @throws PortalException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction deleteKaleoAction(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.deleteKaleoAction(kaleoActionId);
	}

	/**
	* Deletes the kaleo action from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoAction the kaleo action
	* @return the kaleo action that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction deleteKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.deleteKaleoAction(kaleoAction);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoActionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction fetchKaleoAction(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.fetchKaleoAction(kaleoActionId);
	}

	/**
	* Returns the kaleo action with the primary key.
	*
	* @param kaleoActionId the primary key of the kaleo action
	* @return the kaleo action
	* @throws PortalException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction getKaleoAction(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoAction(kaleoActionId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo actions
	* @param end the upper bound of the range of kaleo actions (not inclusive)
	* @return the range of kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActions(start, end);
	}

	/**
	* Returns the number of kaleo actions.
	*
	* @return the number of kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getKaleoActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActionsCount();
	}

	/**
	* Updates the kaleo action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoAction the kaleo action
	* @return the kaleo action that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction updateKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.updateKaleoAction(kaleoAction);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoActionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoActionLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoActionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoAction addKaleoAction(
		java.lang.String kaleoClassName, long kaleoClassPK,
		long kaleoDefinitionId, java.lang.String kaleoNodeName,
		com.liferay.portal.workflow.kaleo.definition.Action action,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.addKaleoAction(kaleoClassName,
			kaleoClassPK, kaleoDefinitionId, kaleoNodeName, action,
			serviceContext);
	}

	@Override
	public void deleteCompanyKaleoActions(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteCompanyKaleoActions(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoActions(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoDefinitionKaleoActions(kaleoDefinitionId);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActions(kaleoClassName,
			kaleoClassPK);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActions(kaleoClassName,
			kaleoClassPK, executionType);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoActionLocalService getWrappedKaleoActionLocalService() {
		return _kaleoActionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoActionLocalService(
		KaleoActionLocalService kaleoActionLocalService) {
		_kaleoActionLocalService = kaleoActionLocalService;
	}

	@Override
	public KaleoActionLocalService getWrappedService() {
		return _kaleoActionLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoActionLocalService kaleoActionLocalService) {
		_kaleoActionLocalService = kaleoActionLocalService;
	}

	private KaleoActionLocalService _kaleoActionLocalService;
}