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
 * This class is a wrapper for {@link JIRAChangeItemLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeItemLocalService
 * @generated
 */
public class JIRAChangeItemLocalServiceWrapper
	implements JIRAChangeItemLocalService {
	public JIRAChangeItemLocalServiceWrapper(
		JIRAChangeItemLocalService jiraChangeItemLocalService) {
		_jiraChangeItemLocalService = jiraChangeItemLocalService;
	}

	public com.liferay.socialcoding.model.JIRAChangeItem addJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.addJIRAChangeItem(jiraChangeItem);
	}

	public com.liferay.socialcoding.model.JIRAChangeItem createJIRAChangeItem(
		long jiraChangeItemId) {
		return _jiraChangeItemLocalService.createJIRAChangeItem(jiraChangeItemId);
	}

	public void deleteJIRAChangeItem(long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeItemLocalService.deleteJIRAChangeItem(jiraChangeItemId);
	}

	public void deleteJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		_jiraChangeItemLocalService.deleteJIRAChangeItem(jiraChangeItem);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.JIRAChangeItem getJIRAChangeItem(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.getJIRAChangeItem(jiraChangeItemId);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> getJIRAChangeItems(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.getJIRAChangeItems(start, end);
	}

	public int getJIRAChangeItemsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.getJIRAChangeItemsCount();
	}

	public com.liferay.socialcoding.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.updateJIRAChangeItem(jiraChangeItem);
	}

	public com.liferay.socialcoding.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.updateJIRAChangeItem(jiraChangeItem,
			merge);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> getJIRAChangeItems(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.getJIRAChangeItems(jiraChangeGroupId);
	}

	public JIRAChangeItemLocalService getWrappedJIRAChangeItemLocalService() {
		return _jiraChangeItemLocalService;
	}

	private JIRAChangeItemLocalService _jiraChangeItemLocalService;
}