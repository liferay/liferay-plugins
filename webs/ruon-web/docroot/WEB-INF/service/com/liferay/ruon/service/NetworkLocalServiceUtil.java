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

package com.liferay.ruon.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="NetworkLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkLocalServiceUtil {
	public static com.liferay.ruon.model.Network addNetwork(
		com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException {
		return getService().addNetwork(network);
	}

	public static com.liferay.ruon.model.Network createNetwork(long networkId) {
		return getService().createNetwork(networkId);
	}

	public static void deleteNetwork(long networkId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteNetwork(networkId);
	}

	public static void deleteNetwork(com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException {
		getService().deleteNetwork(network);
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

	public static com.liferay.ruon.model.Network getNetwork(long networkId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getNetwork(networkId);
	}

	public static java.util.List<com.liferay.ruon.model.Network> getNetworks(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getNetworks(start, end);
	}

	public static int getNetworksCount()
		throws com.liferay.portal.SystemException {
		return getService().getNetworksCount();
	}

	public static com.liferay.ruon.model.Network updateNetwork(
		com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException {
		return getService().updateNetwork(network);
	}

	public static com.liferay.ruon.model.Network updateNetwork(
		com.liferay.ruon.model.Network network, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateNetwork(network, merge);
	}

	public static com.liferay.ruon.model.Network getNetwork(
		java.lang.String name)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getNetwork(name);
	}

	public static long getNetworkId(java.lang.String name)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getNetworkId(name);
	}

	public static com.liferay.ruon.model.Network updateNetwork(
		java.lang.String name, long ttl)
		throws com.liferay.portal.SystemException {
		return getService().updateNetwork(name, ttl);
	}

	public static void clearService() {
		_service = null;
	}

	public static NetworkLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					NetworkLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new NetworkLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(NetworkLocalService service) {
		_service = service;
	}

	private static NetworkLocalService _service;
}