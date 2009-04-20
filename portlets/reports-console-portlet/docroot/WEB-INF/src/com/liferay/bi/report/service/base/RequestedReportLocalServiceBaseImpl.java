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

package com.liferay.bi.report.service.base;

import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.service.ReportDefinitionLocalService;
import com.liferay.bi.report.service.RequestedReportLocalService;
import com.liferay.bi.report.service.persistence.ReportDefinitionFinder;
import com.liferay.bi.report.service.persistence.ReportDefinitionPersistence;
import com.liferay.bi.report.service.persistence.RequestedReportPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="RequestedReportLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class RequestedReportLocalServiceBaseImpl
	implements RequestedReportLocalService {
	public RequestedReport addRequestedReport(RequestedReport requestedReport)
		throws SystemException {
		requestedReport.setNew(true);

		return requestedReportPersistence.update(requestedReport, false);
	}

	public RequestedReport createRequestedReport(long requestId) {
		return requestedReportPersistence.create(requestId);
	}

	public void deleteRequestedReport(long requestId)
		throws PortalException, SystemException {
		requestedReportPersistence.remove(requestId);
	}

	public void deleteRequestedReport(RequestedReport requestedReport)
		throws SystemException {
		requestedReportPersistence.remove(requestedReport);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return requestedReportPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return requestedReportPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public RequestedReport getRequestedReport(long requestId)
		throws PortalException, SystemException {
		return requestedReportPersistence.findByPrimaryKey(requestId);
	}

	public List<RequestedReport> getRequestedReports(int start, int end)
		throws SystemException {
		return requestedReportPersistence.findAll(start, end);
	}

	public int getRequestedReportsCount() throws SystemException {
		return requestedReportPersistence.countAll();
	}

	public RequestedReport updateRequestedReport(
		RequestedReport requestedReport) throws SystemException {
		requestedReport.setNew(false);

		return requestedReportPersistence.update(requestedReport, true);
	}

	public RequestedReport updateRequestedReport(
		RequestedReport requestedReport, boolean merge)
		throws SystemException {
		requestedReport.setNew(false);

		return requestedReportPersistence.update(requestedReport, merge);
	}

	public ReportDefinitionLocalService getReportDefinitionLocalService() {
		return reportDefinitionLocalService;
	}

	public void setReportDefinitionLocalService(
		ReportDefinitionLocalService reportDefinitionLocalService) {
		this.reportDefinitionLocalService = reportDefinitionLocalService;
	}

	public ReportDefinitionPersistence getReportDefinitionPersistence() {
		return reportDefinitionPersistence;
	}

	public void setReportDefinitionPersistence(
		ReportDefinitionPersistence reportDefinitionPersistence) {
		this.reportDefinitionPersistence = reportDefinitionPersistence;
	}

	public ReportDefinitionFinder getReportDefinitionFinder() {
		return reportDefinitionFinder;
	}

	public void setReportDefinitionFinder(
		ReportDefinitionFinder reportDefinitionFinder) {
		this.reportDefinitionFinder = reportDefinitionFinder;
	}

	public RequestedReportLocalService getRequestedReportLocalService() {
		return requestedReportLocalService;
	}

	public void setRequestedReportLocalService(
		RequestedReportLocalService requestedReportLocalService) {
		this.requestedReportLocalService = requestedReportLocalService;
	}

	public RequestedReportPersistence getRequestedReportPersistence() {
		return requestedReportPersistence;
	}

	public void setRequestedReportPersistence(
		RequestedReportPersistence requestedReportPersistence) {
		this.requestedReportPersistence = requestedReportPersistence;
	}

	@BeanReference(name = "com.liferay.bi.report.service.ReportDefinitionLocalService.impl")
	protected ReportDefinitionLocalService reportDefinitionLocalService;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionPersistence.impl")
	protected ReportDefinitionPersistence reportDefinitionPersistence;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionFinder.impl")
	protected ReportDefinitionFinder reportDefinitionFinder;
	@BeanReference(name = "com.liferay.bi.report.service.RequestedReportLocalService.impl")
	protected RequestedReportLocalService requestedReportLocalService;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.RequestedReportPersistence.impl")
	protected RequestedReportPersistence requestedReportPersistence;
}