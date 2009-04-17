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

import com.liferay.bi.report.model.ReportRequest;
import com.liferay.bi.report.service.ReportDefinitionLocalService;
import com.liferay.bi.report.service.ReportRequestLocalService;
import com.liferay.bi.report.service.persistence.ReportDefinitionFinder;
import com.liferay.bi.report.service.persistence.ReportDefinitionPersistence;
import com.liferay.bi.report.service.persistence.ReportRequestPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="ReportRequestLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class ReportRequestLocalServiceBaseImpl
	implements ReportRequestLocalService {
	public ReportRequest addReportRequest(ReportRequest reportRequest)
		throws SystemException {
		reportRequest.setNew(true);

		return reportRequestPersistence.update(reportRequest, false);
	}

	public ReportRequest createReportRequest(long requestId) {
		return reportRequestPersistence.create(requestId);
	}

	public void deleteReportRequest(long requestId)
		throws PortalException, SystemException {
		reportRequestPersistence.remove(requestId);
	}

	public void deleteReportRequest(ReportRequest reportRequest)
		throws SystemException {
		reportRequestPersistence.remove(reportRequest);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return reportRequestPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return reportRequestPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public ReportRequest getReportRequest(long requestId)
		throws PortalException, SystemException {
		return reportRequestPersistence.findByPrimaryKey(requestId);
	}

	public List<ReportRequest> getReportRequests(int start, int end)
		throws SystemException {
		return reportRequestPersistence.findAll(start, end);
	}

	public int getReportRequestsCount() throws SystemException {
		return reportRequestPersistence.countAll();
	}

	public ReportRequest updateReportRequest(ReportRequest reportRequest)
		throws SystemException {
		reportRequest.setNew(false);

		return reportRequestPersistence.update(reportRequest, true);
	}

	public ReportRequest updateReportRequest(ReportRequest reportRequest,
		boolean merge) throws SystemException {
		reportRequest.setNew(false);

		return reportRequestPersistence.update(reportRequest, merge);
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

	public ReportRequestLocalService getReportRequestLocalService() {
		return reportRequestLocalService;
	}

	public void setReportRequestLocalService(
		ReportRequestLocalService reportRequestLocalService) {
		this.reportRequestLocalService = reportRequestLocalService;
	}

	public ReportRequestPersistence getReportRequestPersistence() {
		return reportRequestPersistence;
	}

	public void setReportRequestPersistence(
		ReportRequestPersistence reportRequestPersistence) {
		this.reportRequestPersistence = reportRequestPersistence;
	}

	@BeanReference(name = "com.liferay.bi.report.service.ReportDefinitionLocalService.impl")
	protected ReportDefinitionLocalService reportDefinitionLocalService;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionPersistence.impl")
	protected ReportDefinitionPersistence reportDefinitionPersistence;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionFinder.impl")
	protected ReportDefinitionFinder reportDefinitionFinder;
	@BeanReference(name = "com.liferay.bi.report.service.ReportRequestLocalService.impl")
	protected ReportRequestLocalService reportRequestLocalService;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportRequestPersistence.impl")
	protected ReportRequestPersistence reportRequestPersistence;
}