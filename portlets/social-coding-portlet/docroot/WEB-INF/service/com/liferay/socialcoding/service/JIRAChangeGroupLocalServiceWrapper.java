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
 * This class is a wrapper for {@link JIRAChangeGroupLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeGroupLocalService
 * @generated
 */
public class JIRAChangeGroupLocalServiceWrapper
	implements JIRAChangeGroupLocalService {
	public JIRAChangeGroupLocalServiceWrapper(
		JIRAChangeGroupLocalService jiraChangeGroupLocalService) {
		_jiraChangeGroupLocalService = jiraChangeGroupLocalService;
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup addJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.addJIRAChangeGroup(jiraChangeGroup);
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup createJIRAChangeGroup(
		long jiraChangeGroupId) {
		return _jiraChangeGroupLocalService.createJIRAChangeGroup(jiraChangeGroupId);
	}

	public void deleteJIRAChangeGroup(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeGroupLocalService.deleteJIRAChangeGroup(jiraChangeGroupId);
	}

	public void deleteJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeGroupLocalService.deleteJIRAChangeGroup(jiraChangeGroup);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup getJIRAChangeGroup(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getJIRAChangeGroup(jiraChangeGroupId);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> getJIRAChangeGroups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getJIRAChangeGroups(start, end);
	}

	public int getJIRAChangeGroupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.getJIRAChangeGroupsCount();
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup updateJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.updateJIRAChangeGroup(jiraChangeGroup);
	}

	public com.liferay.socialcoding.model.JIRAChangeGroup updateJIRAChangeGroup(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.updateJIRAChangeGroup(jiraChangeGroup,
			merge);
	}

	public JIRAChangeGroupLocalService getWrappedJIRAChangeGroupLocalService() {
		return _jiraChangeGroupLocalService;
	}

	private JIRAChangeGroupLocalService _jiraChangeGroupLocalService;
}