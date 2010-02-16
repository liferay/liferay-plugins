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

package com.liferay.gadgets.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="GadgetsEntryLocalServiceUtil.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryLocalServiceUtil {
	public static com.liferay.gadgets.model.GadgetsEntry addGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addGadgetsEntry(gadgetsEntry);
	}

	public static com.liferay.gadgets.model.GadgetsEntry createGadgetsEntry(
		long gadgetsEntryId) {
		return getService().createGadgetsEntry(gadgetsEntryId);
	}

	public static void deleteGadgetsEntry(long gadgetsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGadgetsEntry(gadgetsEntryId);
	}

	public static void deleteGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGadgetsEntry(gadgetsEntry);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.gadgets.model.GadgetsEntry getGadgetsEntry(
		long gadgetsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsEntry(gadgetsEntryId);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> getGadgetsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsEntries(start, end);
	}

	public static int getGadgetsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsEntriesCount();
	}

	public static com.liferay.gadgets.model.GadgetsEntry updateGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGadgetsEntry(gadgetsEntry);
	}

	public static com.liferay.gadgets.model.GadgetsEntry updateGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGadgetsEntry(gadgetsEntry, merge);
	}

	public static com.liferay.gadgets.model.GadgetsEntry addGadgetsEntry(
		long companyId, java.lang.String name, java.lang.String url,
		java.lang.String xml)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addGadgetsEntry(companyId, name, url, xml);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> getGadgetsEntries(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsEntries(companyId, start, end);
	}

	public static int getGadgetsEntriesCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsEntriesCount(companyId);
	}

	public static com.liferay.gadgets.model.GadgetsEntry updateGadgetsEntry(
		long gadgetsEntryId, java.lang.String name, java.lang.String xml)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGadgetsEntry(gadgetsEntryId, name, xml);
	}

	public static void clearService() {
		_service = null;
	}

	public static GadgetsEntryLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					GadgetsEntryLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new GadgetsEntryLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(GadgetsEntryLocalService service) {
		_service = service;
	}

	private static GadgetsEntryLocalService _service;
}