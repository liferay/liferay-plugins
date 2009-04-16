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

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="ReportDefinitionLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionLocalServiceClp
	implements ReportDefinitionLocalService {
	public ReportDefinitionLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.bi.report.model.ReportDefinition addReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(reportDefinition);

		if (reportDefinition == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.ReportDefinition");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addReportDefinition",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.ReportDefinition createReportDefinition(
		long definitionId) {
		Object paramObj0 = new LongWrapper(definitionId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createReportDefinition",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteReportDefinition(long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		try {
			_classLoaderProxy.invoke("deleteReportDefinition",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(reportDefinition);

		if (reportDefinition == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.ReportDefinition");
		}

		try {
			_classLoaderProxy.invoke("deleteReportDefinition",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.ReportDefinition getReportDefinition(
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReportDefinition",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> getReportDefinitions(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReportDefinitions",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.bi.report.model.ReportDefinition>)ClpSerializer.translateOutput(returnObj);
	}

	public int getReportDefinitionsCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReportDefinitionsCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(reportDefinition);

		if (reportDefinition == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.ReportDefinition");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateReportDefinition",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		com.liferay.bi.report.model.ReportDefinition reportDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(reportDefinition);

		if (reportDefinition == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.ReportDefinition");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateReportDefinition",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.ReportDefinition addReportDefinition(
		long companyId, long groupId, long userId,
		java.lang.String definitionName, java.lang.String description,
		java.lang.String datasourceName,
		com.liferay.portal.kernel.bi.reporting.ReportFormat format,
		java.lang.String fileName, java.io.File file,
		java.lang.String reportParameters)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new LongWrapper(userId);

		Object paramObj3 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(datasourceName);

		if (datasourceName == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(format);

		if (format == null) {
			paramObj6 = new NullWrapper(
					"com.liferay.portal.kernel.bi.reporting.ReportFormat");
		}

		Object paramObj7 = ClpSerializer.translateInput(fileName);

		if (fileName == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(file);

		if (file == null) {
			paramObj8 = new NullWrapper("java.io.File");
		}

		Object paramObj9 = ClpSerializer.translateInput(reportParameters);

		if (reportParameters == null) {
			paramObj9 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addReportDefinition",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public void genrateReport(javax.portlet.PortletRequest portletRequest,
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(portletRequest);

		if (portletRequest == null) {
			paramObj0 = new NullWrapper("javax.portlet.PortletRequest");
		}

		Object paramObj1 = new LongWrapper(definitionId);

		try {
			_classLoaderProxy.invoke("genrateReport",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> getReportDefintions(
		long companyId, long groupId) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReportDefintions",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.bi.report.model.ReportDefinition>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> search(
		long companyId, long groupId, java.lang.String keywords,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = ClpSerializer.translateInput(keywords);

		if (keywords == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(params);

		if (params == null) {
			paramObj3 = new NullWrapper("java.util.LinkedHashMap");
		}

		Object paramObj4 = new IntegerWrapper(start);

		Object paramObj5 = new IntegerWrapper(end);

		Object paramObj6 = ClpSerializer.translateInput(obc);

		if (obc == null) {
			paramObj6 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("search",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.bi.report.model.ReportDefinition>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.bi.report.model.ReportDefinition> search(
		long companyId, long groupId, java.lang.String name,
		java.lang.String description,
		java.util.LinkedHashMap<String, Object> params, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(params);

		if (params == null) {
			paramObj4 = new NullWrapper("java.util.LinkedHashMap");
		}

		Object paramObj5 = new BooleanWrapper(andSearch);

		Object paramObj6 = new IntegerWrapper(start);

		Object paramObj7 = new IntegerWrapper(end);

		Object paramObj8 = ClpSerializer.translateInput(obc);

		if (obc == null) {
			paramObj8 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("search",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.bi.report.model.ReportDefinition>)ClpSerializer.translateOutput(returnObj);
	}

	public int searchCount(long companyId, long groupId,
		java.lang.String keywords, java.lang.Boolean active,
		java.util.LinkedHashMap<String, Object> params)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = ClpSerializer.translateInput(keywords);

		if (keywords == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(active);

		if (active == null) {
			paramObj3 = new NullWrapper("java.lang.Boolean");
		}

		Object paramObj4 = ClpSerializer.translateInput(params);

		if (params == null) {
			paramObj4 = new NullWrapper("java.util.LinkedHashMap");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("searchCount",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int searchCount(long companyId, long groupId, java.lang.String name,
		java.lang.String description, java.lang.Boolean active,
		java.util.LinkedHashMap<String, Object> params, boolean andSearch)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(active);

		if (active == null) {
			paramObj4 = new NullWrapper("java.lang.Boolean");
		}

		Object paramObj5 = ClpSerializer.translateInput(params);

		if (params == null) {
			paramObj5 = new NullWrapper("java.util.LinkedHashMap");
		}

		Object paramObj6 = new BooleanWrapper(andSearch);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("searchCount",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		long definitionId, java.lang.String definitionName,
		java.lang.String description, java.lang.String datasourceName,
		com.liferay.portal.kernel.bi.reporting.ReportFormat format,
		java.lang.String reportParameters)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(datasourceName);

		if (datasourceName == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(format);

		if (format == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.bi.reporting.ReportFormat");
		}

		Object paramObj5 = ClpSerializer.translateInput(reportParameters);

		if (reportParameters == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateReportDefinition",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.ReportDefinition updateReportDefinition(
		long definitionId, java.lang.String definitionName,
		java.lang.String description, java.lang.String datasourceName,
		com.liferay.portal.kernel.bi.reporting.ReportFormat format,
		java.lang.String reportParameters, java.lang.String fileName,
		java.io.File file)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = ClpSerializer.translateInput(definitionName);

		if (definitionName == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(datasourceName);

		if (datasourceName == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(format);

		if (format == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.bi.reporting.ReportFormat");
		}

		Object paramObj5 = ClpSerializer.translateInput(reportParameters);

		if (reportParameters == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(fileName);

		if (fileName == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(file);

		if (file == null) {
			paramObj7 = new NullWrapper("java.io.File");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateReportDefinition",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.bi.report.model.ReportDefinition)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}