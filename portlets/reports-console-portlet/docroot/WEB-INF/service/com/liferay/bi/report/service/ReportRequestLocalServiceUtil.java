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

package com.liferay.bi.report.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="ReportRequestLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportRequestLocalServiceUtil {
	public static com.liferay.bi.report.model.ReportRequest addReportRequest(
		com.liferay.bi.report.model.ReportRequest reportRequest)
		throws com.liferay.portal.SystemException {
		return getService().addReportRequest(reportRequest);
	}

	public static com.liferay.bi.report.model.ReportRequest createReportRequest(
		long requestId) {
		return getService().createReportRequest(requestId);
	}

	public static void deleteReportRequest(long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteReportRequest(requestId);
	}

	public static void deleteReportRequest(
		com.liferay.bi.report.model.ReportRequest reportRequest)
		throws com.liferay.portal.SystemException {
		getService().deleteReportRequest(reportRequest);
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

	public static com.liferay.bi.report.model.ReportRequest getReportRequest(
		long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getReportRequest(requestId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportRequest> getReportRequests(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getReportRequests(start, end);
	}

	public static int getReportRequestsCount()
		throws com.liferay.portal.SystemException {
		return getService().getReportRequestsCount();
	}

	public static com.liferay.bi.report.model.ReportRequest updateReportRequest(
		com.liferay.bi.report.model.ReportRequest reportRequest)
		throws com.liferay.portal.SystemException {
		return getService().updateReportRequest(reportRequest);
	}

	public static com.liferay.bi.report.model.ReportRequest updateReportRequest(
		com.liferay.bi.report.model.ReportRequest reportRequest, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateReportRequest(reportRequest, merge);
	}

	public static ReportRequestLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("reports-console-portlet",
					ReportRequestLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("reports-console-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new ReportRequestLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(ReportRequestLocalService service) {
		_service = service;
	}

	private static ReportRequestLocalService _service;
}