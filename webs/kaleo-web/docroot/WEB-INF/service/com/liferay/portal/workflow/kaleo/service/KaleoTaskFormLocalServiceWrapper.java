/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * This class is a wrapper for {@link KaleoTaskFormLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskFormLocalService
 * @generated
 */
public class KaleoTaskFormLocalServiceWrapper
	implements KaleoTaskFormLocalService,
		ServiceWrapper<KaleoTaskFormLocalService> {
	public KaleoTaskFormLocalServiceWrapper(
		KaleoTaskFormLocalService kaleoTaskFormLocalService) {
		_kaleoTaskFormLocalService = kaleoTaskFormLocalService;
	}

	/**
	* Adds the kaleo task form to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskForm the kaleo task form
	* @return the kaleo task form that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm addKaleoTaskForm(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskForm kaleoTaskForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.addKaleoTaskForm(kaleoTaskForm);
	}

	/**
	* Creates a new kaleo task form with the primary key. Does not add the kaleo task form to the database.
	*
	* @param kaleoTaskFormId the primary key for the new kaleo task form
	* @return the new kaleo task form
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm createKaleoTaskForm(
		long kaleoTaskFormId) {
		return _kaleoTaskFormLocalService.createKaleoTaskForm(kaleoTaskFormId);
	}

	/**
	* Deletes the kaleo task form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskFormId the primary key of the kaleo task form
	* @throws PortalException if a kaleo task form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTaskForm(long kaleoTaskFormId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskFormLocalService.deleteKaleoTaskForm(kaleoTaskFormId);
	}

	/**
	* Deletes the kaleo task form from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskForm the kaleo task form
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTaskForm(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskForm kaleoTaskForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskFormLocalService.deleteKaleoTaskForm(kaleoTaskForm);
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
		return _kaleoTaskFormLocalService.dynamicQuery(dynamicQuery);
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
		return _kaleoTaskFormLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _kaleoTaskFormLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _kaleoTaskFormLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the kaleo task form with the primary key.
	*
	* @param kaleoTaskFormId the primary key of the kaleo task form
	* @return the kaleo task form
	* @throws PortalException if a kaleo task form with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm getKaleoTaskForm(
		long kaleoTaskFormId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.getKaleoTaskForm(kaleoTaskFormId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo task forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task forms
	* @param end the upper bound of the range of kaleo task forms (not inclusive)
	* @return the range of kaleo task forms
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskForm> getKaleoTaskForms(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.getKaleoTaskForms(start, end);
	}

	/**
	* Returns the number of kaleo task forms.
	*
	* @return the number of kaleo task forms
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoTaskFormsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.getKaleoTaskFormsCount();
	}

	/**
	* Updates the kaleo task form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskForm the kaleo task form
	* @return the kaleo task form that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm updateKaleoTaskForm(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskForm kaleoTaskForm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.updateKaleoTaskForm(kaleoTaskForm);
	}

	/**
	* Updates the kaleo task form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskForm the kaleo task form
	* @param merge whether to merge the kaleo task form with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo task form that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm updateKaleoTaskForm(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskForm kaleoTaskForm,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.updateKaleoTaskForm(kaleoTaskForm,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoTaskFormLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoTaskFormLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm addKaleoTaskForm(
		long kaleoDefintionId, long kaleoTaskId,
		com.liferay.portal.workflow.kaleo.definition.Form form,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.addKaleoTaskForm(kaleoDefintionId,
			kaleoTaskId, form, serviceContext);
	}

	public void deleteCompanyKaleoTaskForms(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskFormLocalService.deleteCompanyKaleoTaskForms(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskForms(long kaleoDefintionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskFormLocalService.deleteKaleoDefinitionKaleoTaskForms(kaleoDefintionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskForm> getKaleoTaskForms(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskFormLocalService.getKaleoTaskForms(kaleoTaskId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KaleoTaskFormLocalService getWrappedKaleoTaskFormLocalService() {
		return _kaleoTaskFormLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKaleoTaskFormLocalService(
		KaleoTaskFormLocalService kaleoTaskFormLocalService) {
		_kaleoTaskFormLocalService = kaleoTaskFormLocalService;
	}

	public KaleoTaskFormLocalService getWrappedService() {
		return _kaleoTaskFormLocalService;
	}

	public void setWrappedService(
		KaleoTaskFormLocalService kaleoTaskFormLocalService) {
		_kaleoTaskFormLocalService = kaleoTaskFormLocalService;
	}

	private KaleoTaskFormLocalService _kaleoTaskFormLocalService;
}