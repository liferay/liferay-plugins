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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * <a href="KaleoTaskInstanceTokenFinderUtil.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceTokenFinderUtil {
	public static int countByACN_ACP_C_CI(java.lang.String assigneeClassName,
		long assigneeClassPK, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByACN_ACP_C_CI(assigneeClassName, assigneeClassPK,
			completed, serviceContext);
	}

	public static int countByC_DD_K_UR(java.lang.String taskName,
		java.lang.String assetType, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_DD_K_UR(taskName, assetType, dueDateGT, dueDateLT,
			completed, searchByUserRoles, andOperator, serviceContext);
	}

	public static int countByRoles_C_CI(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByRoles_C_CI(roleIds, completed, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByACN_ACP_C_CI(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByACN_ACP_C_CI(assigneeClassName, assigneeClassPK,
			completed, start, end, orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByC_DD_K_UR(
		java.lang.String taskName, java.lang.String assetType,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_DD_K_UR(taskName, assetType, dueDateGT, dueDateLT,
			completed, searchByUserRoles, andOperator, start, end,
			orderByComparator, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByRoles_C_CI(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByRoles_C_CI(roleIds, completed, start, end,
			orderByComparator, serviceContext);
	}

	public static KaleoTaskInstanceTokenFinder getFinder() {
		if (_finder == null) {
			_finder = (KaleoTaskInstanceTokenFinder)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskInstanceTokenFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(KaleoTaskInstanceTokenFinder finder) {
		_finder = finder;
	}

	private static KaleoTaskInstanceTokenFinder _finder;
}