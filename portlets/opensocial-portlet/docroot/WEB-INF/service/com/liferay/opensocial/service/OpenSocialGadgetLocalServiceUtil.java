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

package com.liferay.opensocial.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="OpenSocialGadgetLocalServiceUtil.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetLocalServiceUtil {
	public static com.liferay.opensocial.model.OpenSocialGadget addOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget)
		throws com.liferay.portal.SystemException {
		return getService().addOpenSocialGadget(openSocialGadget);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget createOpenSocialGadget(
		long openSocialGadgetId) {
		return getService().createOpenSocialGadget(openSocialGadgetId);
	}

	public static void deleteOpenSocialGadget(long openSocialGadgetId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteOpenSocialGadget(openSocialGadgetId);
	}

	public static void deleteOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget)
		throws com.liferay.portal.SystemException {
		getService().deleteOpenSocialGadget(openSocialGadget);
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

	public static com.liferay.opensocial.model.OpenSocialGadget getOpenSocialGadget(
		long openSocialGadgetId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getOpenSocialGadget(openSocialGadgetId);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> getOpenSocialGadgets(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getOpenSocialGadgets(start, end);
	}

	public static int getOpenSocialGadgetsCount()
		throws com.liferay.portal.SystemException {
		return getService().getOpenSocialGadgetsCount();
	}

	public static com.liferay.opensocial.model.OpenSocialGadget updateOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget)
		throws com.liferay.portal.SystemException {
		return getService().updateOpenSocialGadget(openSocialGadget);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget updateOpenSocialGadget(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateOpenSocialGadget(openSocialGadget, merge);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget addOpenSocialGadget(
		long companyId, java.lang.String name, java.lang.String url,
		java.lang.String xml)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().addOpenSocialGadget(companyId, name, url, xml);
	}

	public static void deleteWSRPConsumer(long openSocialGadgetId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPConsumer(openSocialGadgetId);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> getOpenSocialGadgets(
		long companyId, int start, int end)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getOpenSocialGadgets(companyId, start, end);
	}

	public static int getOpenSocialGadgetsCount(long companyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getOpenSocialGadgetsCount(companyId);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget updateOpenSocialGadget(
		long openSocialGadgetId, java.lang.String name, java.lang.String xml)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().updateOpenSocialGadget(openSocialGadgetId, name, xml);
	}

	public static void clearService() {
		_service = null;
	}

	public static OpenSocialGadgetLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					OpenSocialGadgetLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new OpenSocialGadgetLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(OpenSocialGadgetLocalService service) {
		_service = service;
	}

	private static OpenSocialGadgetLocalService _service;
}