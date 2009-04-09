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
 * <a href="ReportDefinitionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionLocalServiceUtil {
	public static com.liferay.bi.report.model.ReportDefinition addReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		return getService().addReportDefinition(reportDefinition);
	}

	public static com.liferay.bi.report.model.ReportDefinition createReportDefinition(
		long definitionId) {
		return getService().createReportDefinition(definitionId);
	}

	public static void deleteReportDefinition(long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteReportDefinition(definitionId);
	}

	public static void deleteReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		getService().deleteReportDefinition(reportDefinition);
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

	public static com.liferay.bi.report.model.ReportDefinition getReportDefinition(
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getReportDefinition(definitionId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> getReportDefinitions(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getReportDefinitions(start, end);
	}

	public static int getReportDefinitionsCount()
		throws com.liferay.portal.SystemException {
		return getService().getReportDefinitionsCount();
	}

	public static com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		return getService().updateReportDefinition(reportDefinition);
	}

	public static com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateReportDefinition(reportDefinition, merge);
	}

	public static com.liferay.bi.report.model.ReportDefinition addReportDefinition(
		long companyId, long groupId, long userId,
		java.lang.String definitionName, java.lang.String description,
		java.lang.String datasourceName,
		com.liferay.portal.kernel.bi.reporting.ReportFormat format)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .addReportDefinition(companyId, groupId, userId,
			definitionName, description, datasourceName, format);
	}

	public static com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		long definitionId, java.lang.String description,
		java.lang.String datasourceName,
		com.liferay.portal.kernel.bi.reporting.ReportFormat format)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .updateReportDefinition(definitionId, description,
			datasourceName, format);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> getReportDefintions(
		long companyId, long groupId) throws com.liferay.portal.SystemException {
		return getService().getReportDefintions(companyId, groupId);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> search(
		long companyId, long groupId, java.lang.String keywords,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getService()
				   .search(companyId, groupId, keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> search(
		long companyId, long groupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<String, Object> params, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getService()
				   .search(companyId, groupId, name, description, params,
			andSearch, start, end, obc);
	}

	public static int searchCount(long companyId, long groupId,
		java.lang.String keywords, java.lang.Boolean active,
		java.util.LinkedHashMap<String, Object> params)
		throws com.liferay.portal.SystemException {
		return getService()
				   .searchCount(companyId, groupId, keywords, active, params);
	}

	public static int searchCount(long companyId, long groupId,
		java.lang.String name, java.lang.String description,
		java.lang.Boolean active,
		java.util.LinkedHashMap<String, Object> params, boolean andSearch)
		throws com.liferay.portal.SystemException {
		return getService()
				   .searchCount(companyId, groupId, name, description, active,
			params, andSearch);
	}

	public static ReportDefinitionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("reports-console-portlet",
					ReportDefinitionLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("reports-console-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new ReportDefinitionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(ReportDefinitionLocalService service) {
		_service = service;
	}

	private static ReportDefinitionLocalService _service;
}