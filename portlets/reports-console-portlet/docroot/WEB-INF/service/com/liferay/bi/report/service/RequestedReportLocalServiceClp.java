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
 * <a href="RequestedReportLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RequestedReportLocalServiceClp
	implements RequestedReportLocalService {
	public RequestedReportLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.bi.report.model.RequestedReport addRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(requestedReport);

		if (requestedReport == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.RequestedReport");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addRequestedReport",
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

		return (com.liferay.bi.report.model.RequestedReport)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.RequestedReport createRequestedReport(
		long requestId) {
		Object paramObj0 = new LongWrapper(requestId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createRequestedReport",
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

		return (com.liferay.bi.report.model.RequestedReport)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteRequestedReport(long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(requestId);

		try {
			_classLoaderProxy.invoke("deleteRequestedReport",
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

	public void deleteRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(requestedReport);

		if (requestedReport == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.RequestedReport");
		}

		try {
			_classLoaderProxy.invoke("deleteRequestedReport",
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

	public com.liferay.bi.report.model.RequestedReport getRequestedReport(
		long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(requestId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getRequestedReport",
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

		return (com.liferay.bi.report.model.RequestedReport)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.bi.report.model.RequestedReport> getRequestedReports(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getRequestedReports",
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

		return (java.util.List<com.liferay.bi.report.model.RequestedReport>)ClpSerializer.translateOutput(returnObj);
	}

	public int getRequestedReportsCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getRequestedReportsCount",
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

	public com.liferay.bi.report.model.RequestedReport updateRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(requestedReport);

		if (requestedReport == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.RequestedReport");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateRequestedReport",
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

		return (com.liferay.bi.report.model.RequestedReport)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.bi.report.model.RequestedReport updateRequestedReport(
		com.liferay.bi.report.model.RequestedReport requestedReport,
		boolean merge) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(requestedReport);

		if (requestedReport == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.bi.report.model.RequestedReport");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateRequestedReport",
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

		return (com.liferay.bi.report.model.RequestedReport)ClpSerializer.translateOutput(returnObj);
	}

	public void addScheduler(long definitionId, java.lang.String cronText,
		java.util.Date startDate, java.util.Date endDate,
		java.lang.String description, long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = ClpSerializer.translateInput(cronText);

		if (cronText == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(startDate);

		if (startDate == null) {
			paramObj2 = new NullWrapper("java.util.Date");
		}

		Object paramObj3 = ClpSerializer.translateInput(endDate);

		if (endDate == null) {
			paramObj3 = new NullWrapper("java.util.Date");
		}

		Object paramObj4 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new LongWrapper(userId);

		try {
			_classLoaderProxy.invoke("addScheduler",
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
	}

	public int countByDefinitionId(long definitionId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("countByDefinitionId",
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

		return ((Integer)returnObj).intValue();
	}

	public void deleteRequestAndAttachments(long requestId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(requestId);

		try {
			_classLoaderProxy.invoke("deleteRequestAndAttachments",
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

	public java.util.List<com.liferay.bi.report.model.RequestedReport> findByDefinitionId(
		long definitionId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(definitionId);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("findByDefinitionId",
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

		return (java.util.List<com.liferay.bi.report.model.RequestedReport>)ClpSerializer.translateOutput(returnObj);
	}

	public void generateReport(long companyId, long groupId, long userId,
		long definitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new LongWrapper(userId);

		Object paramObj3 = new LongWrapper(definitionId);

		try {
			_classLoaderProxy.invoke("generateReport",
				new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

	private ClassLoaderProxy _classLoaderProxy;
}