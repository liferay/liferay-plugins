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
 * <a href="RequestedReportLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RequestedReportLocalServiceUtil {
	public static com.liferay.bi.report.model.RequestedReport addRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		return getService().addRequestedReport(requestedReport);
	}

	public static com.liferay.bi.report.model.RequestedReport createRequestedReport(
		long requestId) {
		return getService().createRequestedReport(requestId);
	}

	public static void deleteRequestedReport(long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteRequestedReport(requestId);
	}

	public static void deleteRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		getService().deleteRequestedReport(requestedReport);
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

	public static com.liferay.bi.report.model.RequestedReport getRequestedReport(
		long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getRequestedReport(requestId);
	}

	public static java.util.List<com.liferay.bi.report.model.RequestedReport> getRequestedReports(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getRequestedReports(start, end);
	}

	public static int getRequestedReportsCount()
		throws com.liferay.portal.SystemException {
		return getService().getRequestedReportsCount();
	}

	public static com.liferay.bi.report.model.RequestedReport updateRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		return getService().updateRequestedReport(requestedReport);
	}

	public static com.liferay.bi.report.model.RequestedReport updateRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateRequestedReport(requestedReport, merge);
	}

	public static void addRequestedReport(
		com.liferay.portal.kernel.bi.reporting.ReportRequest request)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().addRequestedReport(request);
	}

	public static void genrateReport(long companyId, long groupId, long userId,
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().genrateReport(companyId, groupId, userId, definitionId);
	}

	public static RequestedReportLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("reports-console-portlet",
					RequestedReportLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("reports-console-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new RequestedReportLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(RequestedReportLocalService service) {
		_service = service;
	}

	private static RequestedReportLocalService _service;
}