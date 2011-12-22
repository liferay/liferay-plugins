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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoActionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoActionLocalService
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
	public com.liferay.portal.workflow.kaleo.model.KaleoAction createKaleoAction(
		long kaleoActionId) {
		return _kaleoActionLocalService.createKaleoAction(kaleoActionId);
	}

	/**
	* Deletes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoActionId the primary key of the kaleo action
	* @throws PortalException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoAction(long kaleoActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoAction(kaleoActionId);
	}

	/**
	* Deletes the kaleo action from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoAction the kaleo action
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoAction(kaleoAction);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQueryCount(dynamicQuery);
	}

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
	public com.liferay.portal.workflow.kaleo.model.KaleoAction getKaleoAction(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoAction(kaleoActionId);
	}

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo actions
	* @param end the upper bound of the range of kaleo actions (not inclusive)
	* @return the range of kaleo actions
	* @throws SystemException if a system exception occurred
	*/
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
	public com.liferay.portal.workflow.kaleo.model.KaleoAction updateKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.updateKaleoAction(kaleoAction);
	}

	/**
	* Updates the kaleo action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoAction the kaleo action
	* @param merge whether to merge the kaleo action with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo action that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoAction updateKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.updateKaleoAction(kaleoAction, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoActionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoActionLocalService.setBeanIdentifier(beanIdentifier);
	}

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

	public void deleteCompanyKaleoActions(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteCompanyKaleoActions(companyId);
	}

	public void deleteKaleoDefinitionKaleoActions(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoDefinitionKaleoActions(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActions(kaleoClassName,
			kaleoClassPK, executionType);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KaleoActionLocalService getWrappedKaleoActionLocalService() {
		return _kaleoActionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKaleoActionLocalService(
		KaleoActionLocalService kaleoActionLocalService) {
		_kaleoActionLocalService = kaleoActionLocalService;
	}

	public KaleoActionLocalService getWrappedService() {
		return _kaleoActionLocalService;
	}

	public void setWrappedService(
		KaleoActionLocalService kaleoActionLocalService) {
		_kaleoActionLocalService = kaleoActionLocalService;
	}

	private KaleoActionLocalService _kaleoActionLocalService;
}