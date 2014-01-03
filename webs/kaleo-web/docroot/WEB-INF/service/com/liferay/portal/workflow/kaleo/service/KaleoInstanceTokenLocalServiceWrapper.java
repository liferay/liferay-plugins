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
 * Provides a wrapper for {@link KaleoInstanceTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenLocalService
 * @generated
 */
public class KaleoInstanceTokenLocalServiceWrapper
	implements KaleoInstanceTokenLocalService,
		ServiceWrapper<KaleoInstanceTokenLocalService> {
	public KaleoInstanceTokenLocalServiceWrapper(
		KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService) {
		_kaleoInstanceTokenLocalService = kaleoInstanceTokenLocalService;
	}

	/**
	* Adds the kaleo instance token to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceToken the kaleo instance token
	* @return the kaleo instance token that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken addKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.addKaleoInstanceToken(kaleoInstanceToken);
	}

	/**
	* Creates a new kaleo instance token with the primary key. Does not add the kaleo instance token to the database.
	*
	* @param kaleoInstanceTokenId the primary key for the new kaleo instance token
	* @return the new kaleo instance token
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken createKaleoInstanceToken(
		long kaleoInstanceTokenId) {
		return _kaleoInstanceTokenLocalService.createKaleoInstanceToken(kaleoInstanceTokenId);
	}

	/**
	* Deletes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token that was removed
	* @throws PortalException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken deleteKaleoInstanceToken(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.deleteKaleoInstanceToken(kaleoInstanceTokenId);
	}

	/**
	* Deletes the kaleo instance token from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceToken the kaleo instance token
	* @return the kaleo instance token that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken deleteKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.deleteKaleoInstanceToken(kaleoInstanceToken);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoInstanceTokenLocalService.dynamicQuery();
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
		return _kaleoInstanceTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _kaleoInstanceTokenLocalService.dynamicQueryCount(dynamicQuery);
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
		return _kaleoInstanceTokenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchKaleoInstanceToken(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.fetchKaleoInstanceToken(kaleoInstanceTokenId);
	}

	/**
	* Returns the kaleo instance token with the primary key.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token
	* @return the kaleo instance token
	* @throws PortalException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceToken(kaleoInstanceTokenId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens
	* @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	* @return the range of kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getKaleoInstanceTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokens(start, end);
	}

	/**
	* Returns the number of kaleo instance tokens.
	*
	* @return the number of kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getKaleoInstanceTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokensCount();
	}

	/**
	* Updates the kaleo instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceToken the kaleo instance token
	* @return the kaleo instance token that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.updateKaleoInstanceToken(kaleoInstanceToken);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoInstanceTokenLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoInstanceTokenLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoInstanceTokenLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken addKaleoInstanceToken(
		long parentKaleoInstanceTokenId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.addKaleoInstanceToken(parentKaleoInstanceTokenId,
			workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken completeKaleoInstanceToken(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.completeKaleoInstanceToken(kaleoInstanceTokenId);
	}

	@Override
	public void deleteCompanyKaleoInstanceTokens(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteCompanyKaleoInstanceTokens(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoInstanceTokens(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteKaleoDefinitionKaleoInstanceTokens(kaleoDefinitionId);
	}

	@Override
	public void deleteKaleoInstanceKaleoInstanceTokens(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteKaleoInstanceKaleoInstanceTokens(kaleoInstanceId);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getKaleoInstanceTokens(
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokens(parentKaleoInstanceTokenId,
			completionDate, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getKaleoInstanceTokens(
		long parentKaleoInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokens(parentKaleoInstanceTokenId,
			serviceContext);
	}

	@Override
	public int getKaleoInstanceTokensCount(long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokensCount(parentKaleoInstanceTokenId,
			completionDate, serviceContext);
	}

	@Override
	public int getKaleoInstanceTokensCount(long parentKaleoInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokensCount(parentKaleoInstanceTokenId,
			serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		long kaleoInstanceId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getRootKaleoInstanceToken(kaleoInstanceId,
			workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateKaleoInstanceToken(
		long kaleoInstanceTokenId, long currentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.updateKaleoInstanceToken(kaleoInstanceTokenId,
			currentKaleoNodeId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoInstanceTokenLocalService getWrappedKaleoInstanceTokenLocalService() {
		return _kaleoInstanceTokenLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoInstanceTokenLocalService(
		KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService) {
		_kaleoInstanceTokenLocalService = kaleoInstanceTokenLocalService;
	}

	@Override
	public KaleoInstanceTokenLocalService getWrappedService() {
		return _kaleoInstanceTokenLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService) {
		_kaleoInstanceTokenLocalService = kaleoInstanceTokenLocalService;
	}

	private KaleoInstanceTokenLocalService _kaleoInstanceTokenLocalService;
}