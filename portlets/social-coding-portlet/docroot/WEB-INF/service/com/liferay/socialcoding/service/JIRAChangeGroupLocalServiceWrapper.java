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
 * <a href="JIRAChangeGroupLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
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

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _jiraChangeGroupLocalService.dynamicQuery(dynamicQuery, start,
			end);
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