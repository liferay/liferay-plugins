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

package com.liferay.wol.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="MeetupsRegistrationLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsRegistrationLocalServiceUtil {
	public static com.liferay.wol.model.MeetupsRegistration addMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		return getService().addMeetupsRegistration(meetupsRegistration);
	}

	public static com.liferay.wol.model.MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId) {
		return getService().createMeetupsRegistration(meetupsRegistrationId);
	}

	public static void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteMeetupsRegistration(meetupsRegistrationId);
	}

	public static void deleteMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		getService().deleteMeetupsRegistration(meetupsRegistration);
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

	public static com.liferay.wol.model.MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getMeetupsRegistration(meetupsRegistrationId);
	}

	public static java.util.List<com.liferay.wol.model.MeetupsRegistration> getMeetupsRegistrations(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getMeetupsRegistrations(start, end);
	}

	public static int getMeetupsRegistrationsCount()
		throws com.liferay.portal.SystemException {
		return getService().getMeetupsRegistrationsCount();
	}

	public static com.liferay.wol.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		return getService().updateMeetupsRegistration(meetupsRegistration);
	}

	public static com.liferay.wol.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateMeetupsRegistration(meetupsRegistration, merge);
	}

	public static java.util.List<com.liferay.wol.model.MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getMeetupsRegistrations(meetupsEntryId, status, start, end);
	}

	public static com.liferay.wol.model.MeetupsRegistration getMeetupsRegistration(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getMeetupsRegistration(userId, meetupsEntryId);
	}

	public static int getMeetupsRegistrationsCount(long meetupsEntryId,
		int status) throws com.liferay.portal.SystemException {
		return getService().getMeetupsRegistrationsCount(meetupsEntryId, status);
	}

	public static com.liferay.wol.model.MeetupsRegistration updateMeetupsRegistration(
		long userId, long meetupsEntryId, int status, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateMeetupsRegistration(userId, meetupsEntryId, status,
			comments);
	}

	public static MeetupsRegistrationLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wol-portlet",
					MeetupsRegistrationLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wol-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new MeetupsRegistrationLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(MeetupsRegistrationLocalService service) {
		_service = service;
	}

	private static MeetupsRegistrationLocalService _service;
}