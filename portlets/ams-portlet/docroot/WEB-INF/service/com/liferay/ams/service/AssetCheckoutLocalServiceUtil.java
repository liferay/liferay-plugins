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

package com.liferay.ams.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="AssetCheckoutLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetCheckoutLocalServiceUtil {
	public static com.liferay.ams.model.AssetCheckout addAssetCheckout(
		com.liferay.ams.model.AssetCheckout assetCheckout)
		throws com.liferay.portal.SystemException {
		return getService().addAssetCheckout(assetCheckout);
	}

	public static com.liferay.ams.model.AssetCheckout createAssetCheckout(
		long assetCheckoutId) {
		return getService().createAssetCheckout(assetCheckoutId);
	}

	public static void deleteAssetCheckout(long assetCheckoutId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteAssetCheckout(assetCheckoutId);
	}

	public static void deleteAssetCheckout(
		com.liferay.ams.model.AssetCheckout assetCheckout)
		throws com.liferay.portal.SystemException {
		getService().deleteAssetCheckout(assetCheckout);
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

	public static com.liferay.ams.model.AssetCheckout getAssetCheckout(
		long assetCheckoutId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getAssetCheckout(assetCheckoutId);
	}

	public static java.util.List<com.liferay.ams.model.AssetCheckout> getAssetCheckouts(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getAssetCheckouts(start, end);
	}

	public static int getAssetCheckoutsCount()
		throws com.liferay.portal.SystemException {
		return getService().getAssetCheckoutsCount();
	}

	public static com.liferay.ams.model.AssetCheckout updateAssetCheckout(
		com.liferay.ams.model.AssetCheckout assetCheckout)
		throws com.liferay.portal.SystemException {
		return getService().updateAssetCheckout(assetCheckout);
	}

	public static AssetCheckoutLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("ams-portlet",
					AssetCheckoutLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("ams-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new AssetCheckoutLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AssetCheckoutLocalService service) {
		_service = service;
	}

	private static AssetCheckoutLocalService _service;
}