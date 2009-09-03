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

package com.liferay.so.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="MemberRequestLocalServiceUtil.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class MemberRequestLocalServiceUtil {
	public static com.liferay.so.model.MemberRequest addMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException {
		return getService().addMemberRequest(memberRequest);
	}

	public static com.liferay.so.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return getService().createMemberRequest(memberRequestId);
	}

	public static void deleteMemberRequest(long memberRequestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteMemberRequest(memberRequestId);
	}

	public static void deleteMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException {
		getService().deleteMemberRequest(memberRequest);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.so.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getMemberRequest(memberRequestId);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> getMemberRequests(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getMemberRequests(start, end);
	}

	public static int getMemberRequestsCount()
		throws com.liferay.portal.SystemException {
		return getService().getMemberRequestsCount();
	}

	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.SystemException {
		return getService().updateMemberRequest(memberRequest);
	}

	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateMemberRequest(memberRequest, merge);
	}

	public static com.liferay.so.model.MemberRequest addMemberRequest(
		long userId, long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addMemberRequest(userId, groupId, receiverUserId,
			receiverEmailAddress, themeDisplay);
	}

	public static void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService()
			.addMemberRequests(userId, groupId, receiverUserIds, themeDisplay);
	}

	public static void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService()
			.addMemberRequests(userId, groupId, emailAddresses, themeDisplay);
	}

	public static com.liferay.so.model.MemberRequest getMemberRequest(
		long groupId, long receiverUserId, int status)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getMemberRequest(groupId, receiverUserId, status);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getReceiverMemberRequest(receiverUserId, start, end);
	}

	public static int getReceiverMemberRequestCount(long receiverUserId)
		throws com.liferay.portal.SystemException {
		return getService().getReceiverMemberRequestCount(receiverUserId);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReceiverStatusMemberRequest(receiverUserId, status,
			start, end);
	}

	public static int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) throws com.liferay.portal.SystemException {
		return getService()
				   .getReceiverStatusMemberRequestCount(receiverUserId, status);
	}

	public static boolean hasPendingMemberRequest(long groupId,
		long receiverUserId) throws com.liferay.portal.SystemException {
		return getService().hasPendingMemberRequest(groupId, receiverUserId);
	}

	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		long userId, long memberRequestId, int status)
		throws java.lang.Exception {
		return getService().updateMemberRequest(userId, memberRequestId, status);
	}

	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		java.lang.String key, long receiverUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().updateMemberRequest(key, receiverUserId);
	}

	public static void clearService() {
		_service = null;
	}

	public static MemberRequestLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					MemberRequestLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new MemberRequestLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(MemberRequestLocalService service) {
		_service = service;
	}

	private static MemberRequestLocalService _service;
}