/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link KaleoTaskInstanceTokenLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceTokenLocalService
 * @generated
 */
public class KaleoTaskInstanceTokenLocalServiceUtil {
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken createKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		return getService()
				   .createKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public static void deleteKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public static void deleteKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskInstanceToken(kaleoTaskInstanceTokenId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskInstanceTokens(start, end);
	}

	public static int getKaleoTaskInstanceTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskInstanceTokensCount();
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTaskInstanceToken(kaleoTaskInstanceToken);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoTaskInstanceToken(kaleoTaskInstanceToken, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskId,
		java.lang.String kaleoTaskName,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		java.util.Date dueDate,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskInstanceToken(kaleoInstanceTokenId,
			kaleoTaskId, kaleoTaskName, kaleoTaskAssignments, dueDate,
			workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .assignKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK, workflowContext, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	public static void deleteCompanyKaleoTaskInstanceTokens(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoTaskInstanceTokens(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTaskInstanceTokens(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoDefinitionKaleoTaskInstanceTokens(kaleoDefinitionId);
	}

	public static void deleteKaleoInstanceKaleoTaskInstanceTokens(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoInstanceKaleoTaskInstanceTokens(kaleoInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCompanyKaleoTaskInstanceTokens(companyId, start, end);
	}

	public static int getCompanyKaleoTaskInstanceTokensCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyKaleoTaskInstanceTokensCount(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokens(completed, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokens(roleIds, completed, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		long kaleoInstanceId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokens(kaleoInstanceId, completed,
			start, end, orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokens(assigneeClassName,
			assigneeClassPK, completed, start, end, orderByComparator,
			serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokensCount(completed, serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokensCount(roleIds, completed,
			serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(long kaleoInstanceId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokensCount(kaleoInstanceId, completed,
			serviceContext);
	}

	public static int getKaleoTaskInstanceTokensCount(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceTokensCount(assigneeClassName,
			assigneeClassPK, completed, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getSubmittingUserKaleoTaskInstanceTokens(
		long userId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSubmittingUserKaleoTaskInstanceTokens(userId, completed,
			start, end, orderByComparator, serviceContext);
	}

	public static int getSubmittingUserKaleoTaskInstanceTokensCount(
		long userId, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSubmittingUserKaleoTaskInstanceTokensCount(userId,
			completed, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(keywords, completed, searchByUserRoles, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String assetType,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(taskName, assetType, dueDateGT, dueDateLT,
			completed, searchByUserRoles, andOperator, start, end,
			orderByComparator, serviceContext);
	}

	public static int searchCount(java.lang.String keywords,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(keywords, completed, searchByUserRoles,
			serviceContext);
	}

	public static int searchCount(java.lang.String taskName,
		java.lang.String assetType, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(taskName, assetType, dueDateGT, dueDateLT,
			completed, searchByUserRoles, andOperator, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateDueDate(
		long kaleoTaskInstanceTokenId, java.util.Date dueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateDueDate(kaleoTaskInstanceTokenId, dueDate,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskInstanceTokenLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskInstanceTokenLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					KaleoTaskInstanceTokenLocalService.class.getName(),
					portletClassLoader);

			_service = new KaleoTaskInstanceTokenLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KaleoTaskInstanceTokenLocalService service) {
		_service = service;
	}

	private static KaleoTaskInstanceTokenLocalService _service;
}