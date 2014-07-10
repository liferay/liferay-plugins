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
 * Provides the local service utility for KaleoTaskInstanceToken. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskInstanceTokenLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceTokenLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTaskInstanceTokenLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskInstanceTokenLocalServiceImpl
 * @generated
 */
public class KaleoTaskInstanceTokenLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskInstanceTokenLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskId,
		java.lang.String kaleoTaskName,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		java.util.Date dueDate,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKaleoTaskInstanceToken(kaleoInstanceTokenId,
			kaleoTaskId, kaleoTaskName, kaleoTaskAssignments, dueDate,
			workflowContext, serviceContext);
	}

	/**
	* Adds the kaleo task instance token to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	* @return the kaleo task instance token that was added
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return getService().addKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .assignKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK, workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	/**
	* Creates a new kaleo task instance token with the primary key. Does not add the kaleo task instance token to the database.
	*
	* @param kaleoTaskInstanceTokenId the primary key for the new kaleo task instance token
	* @return the new kaleo task instance token
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken createKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		return getService()
				   .createKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public static void deleteCompanyKaleoTaskInstanceTokens(long companyId) {
		getService().deleteCompanyKaleoTaskInstanceTokens(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTaskInstanceTokens(
		long kaleoDefinitionId) {
		getService()
			.deleteKaleoDefinitionKaleoTaskInstanceTokens(kaleoDefinitionId);
	}

	public static void deleteKaleoInstanceKaleoTaskInstanceTokens(
		long kaleoInstanceId) {
		getService().deleteKaleoInstanceKaleoTaskInstanceTokens(kaleoInstanceId);
	}

	/**
	* Deletes the kaleo task instance token from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	* @return the kaleo task instance token that was removed
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken deleteKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return getService().deleteKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	/**
	* Deletes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token that was removed
	* @throws PortalException if a kaleo task instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken deleteKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken fetchKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		return getService().fetchKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
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

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
		long companyId, int start, int end) {
		return getService()
				   .getCompanyKaleoTaskInstanceTokens(companyId, start, end);
	}

	public static int getCompanyKaleoTaskInstanceTokensCount(long companyId) {
		return getService().getCompanyKaleoTaskInstanceTokensCount(companyId);
	}

	/**
	* Returns the kaleo task instance token with the primary key.
	*
	* @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	* @return the kaleo task instance token
	* @throws PortalException if a kaleo task instance token with the primary key could not be found
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokens(assigneeClassName,
			assigneeClassPK, completed, start, end, orderByComparator,
			serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokens(completed, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		long kaleoInstanceId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokens(kaleoInstanceId, completed,
			start, end, orderByComparator, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceTokens(
		long kaleoInstanceId, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getKaleoTaskInstanceTokens(kaleoInstanceId, kaleoTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokens(roleIds, completed, start, end,
			orderByComparator, serviceContext);
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
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		int start, int end) {
		return getService().getKaleoTaskInstanceTokens(start, end);
	}

	/**
	* Returns the number of kaleo task instance tokens.
	*
	* @return the number of kaleo task instance tokens
	*/
	public static int getKaleoTaskInstanceTokensCount() {
		return getService().getKaleoTaskInstanceTokensCount();
	}

	public static int getKaleoTaskInstanceTokensCount(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokensCount(assigneeClassName,
			assigneeClassPK, completed, serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokensCount(completed, serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(long kaleoInstanceId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokensCount(kaleoInstanceId, completed,
			serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getKaleoTaskInstanceTokensCount(roleIds, completed,
			serviceContext);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getSubmittingUserKaleoTaskInstanceTokens(
		long userId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getSubmittingUserKaleoTaskInstanceTokens(userId, completed,
			start, end, orderByComparator, serviceContext);
	}

	public static int getSubmittingUserKaleoTaskInstanceTokensCount(
		long userId, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .getSubmittingUserKaleoTaskInstanceTokensCount(userId,
			completed, serviceContext);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.String[] assetTypes,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .search(keywords, assetTypes, completed, searchByUserRoles,
			start, end, orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .search(keywords, completed, searchByUserRoles, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String assetType,
		java.lang.Long[] assetPrimaryKeys, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .search(taskName, assetType, assetPrimaryKeys, dueDateGT,
			dueDateLT, completed, searchByUserRoles, andOperator, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String[] assetTypes,
		java.lang.Long[] assetPrimaryKeys, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .search(taskName, assetTypes, assetPrimaryKeys, dueDateGT,
			dueDateLT, completed, searchByUserRoles, andOperator, start, end,
			orderByComparator, serviceContext);
	}

	public static int searchCount(java.lang.String keywords,
		java.lang.String[] assetTypes, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .searchCount(keywords, assetTypes, completed,
			searchByUserRoles, serviceContext);
	}

	public static int searchCount(java.lang.String keywords,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .searchCount(keywords, completed, searchByUserRoles,
			serviceContext);
	}

	public static int searchCount(java.lang.String taskName,
		java.lang.String assetType, java.lang.Long[] assetPrimaryKeys,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .searchCount(taskName, assetType, assetPrimaryKeys,
			dueDateGT, dueDateLT, completed, searchByUserRoles, andOperator,
			serviceContext);
	}

	public static int searchCount(java.lang.String taskName,
		java.lang.String[] assetTypes, java.lang.Long[] assetPrimaryKeys,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext) {
		return getService()
				   .searchCount(taskName, assetTypes, assetPrimaryKeys,
			dueDateGT, dueDateLT, completed, searchByUserRoles, andOperator,
			serviceContext);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateDueDate(
		long kaleoTaskInstanceTokenId, java.util.Date dueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDueDate(kaleoTaskInstanceTokenId, dueDate,
			serviceContext);
	}

	/**
	* Updates the kaleo task instance token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskInstanceToken the kaleo task instance token
	* @return the kaleo task instance token that was updated
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		return getService().updateKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskInstanceTokenLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoTaskInstanceTokenLocalService.class.getName());

			if (invokableLocalService instanceof KaleoTaskInstanceTokenLocalService) {
				_service = (KaleoTaskInstanceTokenLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoTaskInstanceTokenLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoTaskInstanceTokenLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(KaleoTaskInstanceTokenLocalService service) {
	}

	private static KaleoTaskInstanceTokenLocalService _service;
}