/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.socialcoding.service;

/**
 * <a href="JIRAChangeItemLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
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

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeItemLocalService.dynamicQuery(dynamicQuery, start, end);
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