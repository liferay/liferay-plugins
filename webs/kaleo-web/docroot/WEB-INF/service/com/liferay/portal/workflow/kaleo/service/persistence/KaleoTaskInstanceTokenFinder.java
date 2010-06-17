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

/**
 * <a href="KaleoTaskInstanceTokenFinder.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public interface KaleoTaskInstanceTokenFinder {
	public int countByACN_ACP_C_CI(java.lang.String assigneeClassName,
		long assigneeClassPK, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_DD_K_UR(java.lang.String taskName,
		java.lang.String assetType, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByRoles_C_CI(java.util.List<java.lang.Long> roleIds,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByACN_ACP_C_CI(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByC_DD_K_UR(
		java.lang.String taskName, java.lang.String assetType,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findByRoles_C_CI(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException;
}