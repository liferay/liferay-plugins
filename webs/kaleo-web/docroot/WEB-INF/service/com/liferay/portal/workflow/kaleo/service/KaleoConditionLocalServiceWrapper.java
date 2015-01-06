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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoConditionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoConditionLocalService
 * @generated
 */
@ProviderType
public class KaleoConditionLocalServiceWrapper
	implements KaleoConditionLocalService,
		ServiceWrapper<KaleoConditionLocalService> {
	public KaleoConditionLocalServiceWrapper(
		KaleoConditionLocalService kaleoConditionLocalService) {
		_kaleoConditionLocalService = kaleoConditionLocalService;
	}

	/**
	* Adds the kaleo condition to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoCondition the kaleo condition
	* @return the kaleo condition that was added
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition addKaleoCondition(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition) {
		return _kaleoConditionLocalService.addKaleoCondition(kaleoCondition);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition addKaleoCondition(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Condition condition,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoConditionLocalService.addKaleoCondition(kaleoDefinitionId,
			kaleoNodeId, condition, serviceContext);
	}

	/**
	* Creates a new kaleo condition with the primary key. Does not add the kaleo condition to the database.
	*
	* @param kaleoConditionId the primary key for the new kaleo condition
	* @return the new kaleo condition
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition createKaleoCondition(
		long kaleoConditionId) {
		return _kaleoConditionLocalService.createKaleoCondition(kaleoConditionId);
	}

	@Override
	public void deleteCompanyKaleoConditions(long companyId) {
		_kaleoConditionLocalService.deleteCompanyKaleoConditions(companyId);
	}

	/**
	* Deletes the kaleo condition from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoCondition the kaleo condition
	* @return the kaleo condition that was removed
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition deleteKaleoCondition(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition) {
		return _kaleoConditionLocalService.deleteKaleoCondition(kaleoCondition);
	}

	/**
	* Deletes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition that was removed
	* @throws PortalException if a kaleo condition with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition deleteKaleoCondition(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoConditionLocalService.deleteKaleoCondition(kaleoConditionId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoCondition(long kaleoDefinitionId) {
		_kaleoConditionLocalService.deleteKaleoDefinitionKaleoCondition(kaleoDefinitionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoConditionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoConditionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kaleoConditionLocalService.dynamicQuery(dynamicQuery);
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _kaleoConditionLocalService.dynamicQuery(dynamicQuery, start, end);
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _kaleoConditionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kaleoConditionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _kaleoConditionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition fetchKaleoCondition(
		long kaleoConditionId) {
		return _kaleoConditionLocalService.fetchKaleoCondition(kaleoConditionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kaleoConditionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoConditionLocalService.getBeanIdentifier();
	}

	/**
	* Returns the kaleo condition with the primary key.
	*
	* @param kaleoConditionId the primary key of the kaleo condition
	* @return the kaleo condition
	* @throws PortalException if a kaleo condition with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition getKaleoCondition(
		long kaleoConditionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoConditionLocalService.getKaleoCondition(kaleoConditionId);
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
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoCondition> getKaleoConditions(
		int start, int end) {
		return _kaleoConditionLocalService.getKaleoConditions(start, end);
	}

	/**
	* Returns the number of kaleo conditions.
	*
	* @return the number of kaleo conditions
	*/
	@Override
	public int getKaleoConditionsCount() {
		return _kaleoConditionLocalService.getKaleoConditionsCount();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition getKaleoNodeKaleoCondition(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoConditionLocalService.getKaleoNodeKaleoCondition(kaleoNodeId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoConditionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoConditionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoConditionLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the kaleo condition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoCondition the kaleo condition
	* @return the kaleo condition that was updated
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoCondition updateKaleoCondition(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition) {
		return _kaleoConditionLocalService.updateKaleoCondition(kaleoCondition);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoConditionLocalService getWrappedKaleoConditionLocalService() {
		return _kaleoConditionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoConditionLocalService(
		KaleoConditionLocalService kaleoConditionLocalService) {
		_kaleoConditionLocalService = kaleoConditionLocalService;
	}

	@Override
	public KaleoConditionLocalService getWrappedService() {
		return _kaleoConditionLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoConditionLocalService kaleoConditionLocalService) {
		_kaleoConditionLocalService = kaleoConditionLocalService;
	}

	private KaleoConditionLocalService _kaleoConditionLocalService;
}