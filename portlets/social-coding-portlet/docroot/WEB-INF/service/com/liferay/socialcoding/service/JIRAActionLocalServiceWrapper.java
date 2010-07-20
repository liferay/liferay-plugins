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

package com.liferay.socialcoding.service;

/**
 * <p>
 * This class is a wrapper for {@link JIRAActionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAActionLocalService
 * @generated
 */
public class JIRAActionLocalServiceWrapper implements JIRAActionLocalService {
	public JIRAActionLocalServiceWrapper(
		JIRAActionLocalService jiraActionLocalService) {
		_jiraActionLocalService = jiraActionLocalService;
	}

	public com.liferay.socialcoding.model.JIRAAction addJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.addJIRAAction(jiraAction);
	}

	public com.liferay.socialcoding.model.JIRAAction createJIRAAction(
		long jiraActionId) {
		return _jiraActionLocalService.createJIRAAction(jiraActionId);
	}

	public void deleteJIRAAction(long jiraActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_jiraActionLocalService.deleteJIRAAction(jiraActionId);
	}

	public void deleteJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraActionLocalService.deleteJIRAAction(jiraAction);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.JIRAAction getJIRAAction(
		long jiraActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getJIRAAction(jiraActionId);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAAction> getJIRAActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getJIRAActions(start, end);
	}

	public int getJIRAActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.getJIRAActionsCount();
	}

	public com.liferay.socialcoding.model.JIRAAction updateJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.updateJIRAAction(jiraAction);
	}

	public com.liferay.socialcoding.model.JIRAAction updateJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraActionLocalService.updateJIRAAction(jiraAction, merge);
	}

	public JIRAActionLocalService getWrappedJIRAActionLocalService() {
		return _jiraActionLocalService;
	}

	private JIRAActionLocalService _jiraActionLocalService;
}