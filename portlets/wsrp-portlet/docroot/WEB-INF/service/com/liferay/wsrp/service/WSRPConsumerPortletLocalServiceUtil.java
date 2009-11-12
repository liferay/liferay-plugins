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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="WSRPConsumerPortletLocalServiceUtil.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPortletLocalServiceUtil {
	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.SystemException {
		return getService().addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return getService().createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static void deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
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

	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWSRPConsumerPortlets(start, end);
	}

	public static int getWSRPConsumerPortletsCount()
		throws com.liferay.portal.SystemException {
		return getService().getWSRPConsumerPortletsCount();
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.SystemException {
		return getService().updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateWSRPConsumerPortlet(wsrpConsumerPortlet, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addWSRPConsumerPortlet(wsrpConsumerId, name, portletHandle);
	}

	public static void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	public static void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().destroyWSRPConsumerPortlets();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService().getWSRPConsumerPortlets(wsrpConsumerId, start, end);
	}

	public static int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws com.liferay.portal.SystemException {
		return getService().getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	public static void initWSRPConsumerPortlets()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().initWSRPConsumerPortlets();
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateWSRPConsumerPortlet(wsrpConsumerPortletId, name);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPConsumerPortletLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPortletLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WSRPConsumerPortletLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPConsumerPortletLocalService service) {
		_service = service;
	}

	private static WSRPConsumerPortletLocalService _service;
}