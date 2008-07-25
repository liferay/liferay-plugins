/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.service;

/**
 * <a href="JIRAActionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActionLocalServiceUtil {
	public static com.liferay.wol.model.JIRAAction addJIRAAction(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.addJIRAAction(jiraAction);
	}

	public static void deleteJIRAAction(long jiraActionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		jiraActionLocalService.deleteJIRAAction(jiraActionId);
	}

	public static void deleteJIRAAction(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		jiraActionLocalService.deleteJIRAAction(jiraAction);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.wol.model.JIRAAction getJIRAAction(
		long jiraActionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.getJIRAAction(jiraActionId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> getJIRAActions(
		int start, int end) throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.getJIRAActions(start, end);
	}

	public static int getJIRAActionsCount()
		throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.getJIRAActionsCount();
	}

	public static com.liferay.wol.model.JIRAAction updateJIRAAction(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		JIRAActionLocalService jiraActionLocalService = JIRAActionLocalServiceFactory.getService();

		return jiraActionLocalService.updateJIRAAction(jiraAction);
	}
}