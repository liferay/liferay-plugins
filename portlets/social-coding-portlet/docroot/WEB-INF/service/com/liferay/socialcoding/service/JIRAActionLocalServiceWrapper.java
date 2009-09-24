/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
 * <a href="JIRAActionLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAActionLocalServiceWrapper implements JIRAActionLocalService {
	public JIRAActionLocalServiceWrapper(
		JIRAActionLocalService jiraActionLocalService) {
		_jiraActionLocalService = jiraActionLocalService;
	}

	public com.liferay.socialcoding.model.JIRAAction addJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.addJIRAAction(jiraAction);
	}

	public com.liferay.socialcoding.model.JIRAAction createJIRAAction(
		long jiraActionId) {
		return _jiraActionLocalService.createJIRAAction(jiraActionId);
	}

	public void deleteJIRAAction(long jiraActionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_jiraActionLocalService.deleteJIRAAction(jiraActionId);
	}

	public void deleteJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		_jiraActionLocalService.deleteJIRAAction(jiraAction);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.socialcoding.model.JIRAAction getJIRAAction(
		long jiraActionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _jiraActionLocalService.getJIRAAction(jiraActionId);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAAction> getJIRAActions(
		int start, int end) throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.getJIRAActions(start, end);
	}

	public int getJIRAActionsCount() throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.getJIRAActionsCount();
	}

	public com.liferay.socialcoding.model.JIRAAction updateJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.updateJIRAAction(jiraAction);
	}

	public com.liferay.socialcoding.model.JIRAAction updateJIRAAction(
		com.liferay.socialcoding.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.SystemException {
		return _jiraActionLocalService.updateJIRAAction(jiraAction, merge);
	}

	private JIRAActionLocalService _jiraActionLocalService;
}