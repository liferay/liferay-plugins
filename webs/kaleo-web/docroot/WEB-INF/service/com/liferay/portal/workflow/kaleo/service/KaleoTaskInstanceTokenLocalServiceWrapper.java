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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoTaskInstanceTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceTokenLocalService
 * @generated
 */
public class KaleoTaskInstanceTokenLocalServiceWrapper
	implements KaleoTaskInstanceTokenLocalService,
		ServiceWrapper<KaleoTaskInstanceTokenLocalService> {
	public KaleoTaskInstanceTokenLocalServiceWrapper(
		KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService) {
		_kaleoTaskInstanceTokenLocalService = kaleoTaskInstanceTokenLocalService;
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskId,
		java.lang.String kaleoTaskName,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		java.util.Date dueDate,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(kaleoInstanceTokenId,
			kaleoTaskId, kaleoTaskName, kaleoTaskAssignments, dueDate,
			workflowContext, serviceContext);
	}

	/**
	* Adds the kaleo task instance token to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	* @return the kaleo task instance token that was added
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return _kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.assignKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK, workflowContext, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	/**
	* Creates a new kaleo task instance token with the primary key. Does not add the kaleo task instance token to the database.
	*
	* @param kaleoTaskInstanceTokenId the primary key for the new kaleo task instance token
	* @return the new kaleo task instance token
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken createKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		return _kaleoTaskInstanceTokenLocalService.createKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	@Override
	public void deleteCompanyKaleoTaskInstanceTokens(long companyId) {
		_kaleoTaskInstanceTokenLocalService.deleteCompanyKaleoTaskInstanceTokens(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoTaskInstanceTokens(
		long kaleoDefinitionId) {
		_kaleoTaskInstanceTokenLocalService.deleteKaleoDefinitionKaleoTaskInstanceTokens(kaleoDefinitionId);
	}

	@Override
	public void deleteKaleoInstanceKaleoTaskInstanceTokens(long kaleoInstanceId) {
		_kaleoTaskInstanceTokenLocalService.deleteKaleoInstanceKaleoTaskInstanceTokens(kaleoInstanceId);
	}

	/**
	* Deletes the kaleo task instance token from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	* @return the kaleo task instance token that was removed
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken deleteKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return _kaleoTaskInstanceTokenLocalService.deleteKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	/**
	* Deletes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token that was removed
	* @throws PortalException if a kaleo task instance token with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken deleteKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.deleteKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery();
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
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoTaskInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _kaleoTaskInstanceTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _kaleoTaskInstanceTokenLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken fetchKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		return _kaleoTaskInstanceTokenLocalService.fetchKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kaleoTaskInstanceTokenLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoTaskInstanceTokenLocalService.getBeanIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
		long companyId, int start, int end) {
		return _kaleoTaskInstanceTokenLocalService.getCompanyKaleoTaskInstanceTokens(companyId,
			start, end);
	}

	@Override
	public int getCompanyKaleoTaskInstanceTokensCount(long companyId) {
		return _kaleoTaskInstanceTokenLocalService.getCompanyKaleoTaskInstanceTokensCount(companyId);
	}

	/**
	* Returns the kaleo task instance token with the primary key.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token
	* @throws PortalException if a kaleo task instance token with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(assigneeClassName,
			assigneeClassPK, completed, start, end, orderByComparator,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(completed,
			start, end, orderByComparator, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		long kaleoInstanceId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(kaleoInstanceId,
			completed, start, end, orderByComparator, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceTokens(
		long kaleoInstanceId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(kaleoInstanceId,
			kaleoTaskId);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(roleIds,
			completed, start, end, orderByComparator, serviceContext);
	}

	/**
	* Returns a range of all the kaleo task instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task instance tokens
	* @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	* @return the range of kaleo task instance tokens
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		int start, int end) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(start,
			end);
	}

	/**
	* Returns the number of kaleo task instance tokens.
	*
	* @return the number of kaleo task instance tokens
	*/
	@Override
	public int getKaleoTaskInstanceTokensCount() {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount();
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(assigneeClassName,
			assigneeClassPK, completed, serviceContext);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(completed,
			serviceContext);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(long kaleoInstanceId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(kaleoInstanceId,
			completed, serviceContext);
	}

	@Override
	public int getKaleoTaskInstanceTokensCount(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokensCount(roleIds,
			completed, serviceContext);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getSubmittingUserKaleoTaskInstanceTokens(
		long userId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getSubmittingUserKaleoTaskInstanceTokens(userId,
			completed, start, end, orderByComparator, serviceContext);
	}

	@Override
	public int getSubmittingUserKaleoTaskInstanceTokensCount(long userId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.getSubmittingUserKaleoTaskInstanceTokensCount(userId,
			completed, serviceContext);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoTaskInstanceTokenLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.String[] assetTypes,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.search(keywords, assetTypes,
			completed, searchByUserRoles, start, end, orderByComparator,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.search(keywords, completed,
			searchByUserRoles, start, end, orderByComparator, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String assetType,
		java.lang.Long[] assetPrimaryKeys, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.search(taskName, assetType,
			assetPrimaryKeys, dueDateGT, dueDateLT, completed,
			searchByUserRoles, andOperator, start, end, orderByComparator,
			serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String[] assetTypes,
		java.lang.Long[] assetPrimaryKeys, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.search(taskName, assetTypes,
			assetPrimaryKeys, dueDateGT, dueDateLT, completed,
			searchByUserRoles, andOperator, start, end, orderByComparator,
			serviceContext);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.lang.String[] assetTypes, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.searchCount(keywords,
			assetTypes, completed, searchByUserRoles, serviceContext);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.searchCount(keywords,
			completed, searchByUserRoles, serviceContext);
	}

	@Override
	public int searchCount(java.lang.String taskName,
		java.lang.String assetType, java.lang.Long[] assetPrimaryKeys,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.searchCount(taskName,
			assetType, assetPrimaryKeys, dueDateGT, dueDateLT, completed,
			searchByUserRoles, andOperator, serviceContext);
	}

	@Override
	public int searchCount(java.lang.String taskName,
		java.lang.String[] assetTypes, java.lang.Long[] assetPrimaryKeys,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return _kaleoTaskInstanceTokenLocalService.searchCount(taskName,
			assetTypes, assetPrimaryKeys, dueDateGT, dueDateLT, completed,
			searchByUserRoles, andOperator, serviceContext);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoTaskInstanceTokenLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateDueDate(
		long kaleoTaskInstanceTokenId, java.util.Date dueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kaleoTaskInstanceTokenLocalService.updateDueDate(kaleoTaskInstanceTokenId,
			dueDate, serviceContext);
	}

	/**
	* Updates the kaleo task instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	* @return the kaleo task instance token that was updated
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return _kaleoTaskInstanceTokenLocalService.updateKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoTaskInstanceTokenLocalService getWrappedKaleoTaskInstanceTokenLocalService() {
		return _kaleoTaskInstanceTokenLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoTaskInstanceTokenLocalService(
		KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService) {
		_kaleoTaskInstanceTokenLocalService = kaleoTaskInstanceTokenLocalService;
	}

	@Override
	public KaleoTaskInstanceTokenLocalService getWrappedService() {
		return _kaleoTaskInstanceTokenLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoTaskInstanceTokenLocalService kaleoTaskInstanceTokenLocalService) {
		_kaleoTaskInstanceTokenLocalService = kaleoTaskInstanceTokenLocalService;
	}

	private KaleoTaskInstanceTokenLocalService _kaleoTaskInstanceTokenLocalService;
}