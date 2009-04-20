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

package com.liferay.bi.report.service.impl;

import java.util.Date;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.portlet.RequestStatus;
import com.liferay.bi.report.service.base.RequestedReportLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.MemoryReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.model.CompanyConstants;

/**
 * <a href="RequestedReportLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Brian Wing Shun Chan
 */
public class RequestedReportLocalServiceImpl
	extends RequestedReportLocalServiceBaseImpl {

	private static long repositoryId = CompanyConstants.SYSTEM;

	public void addRequestedReport(ReportRequest request)
		throws PortalException, SystemException {

	}

	public void genrateReport(
		long companyId, long groupId, long userId, long definitionId)
		throws PortalException, SystemException {

		ReportDefinition definition =
			reportDefinitionPersistence.fetchByPrimaryKey(definitionId);

		String[] existingFiles = definition.getAttachmentsFiles();

		byte[] definitionFile =
			DLServiceUtil.getFile(
				definition.getCompanyId(), repositoryId, existingFiles[0]);

		ReportDesignRetriever retriever =
			new MemoryReportDesignRetriever(
				definition.getDefinitionName(), definitionFile);

		ReportRequest request =
			new ReportRequest(
				retriever, definition.getReportFormat(), null, null);
		Date now = new Date();

		long requestId = CounterLocalServiceUtil.increment();
		RequestedReport requestedReport =
			requestedReportLocalService.createRequestedReport(requestId);
		requestedReport.setCompanyId(companyId);
		requestedReport.setGroupId(groupId);
		requestedReport.setDefinitionId(definitionId);
		requestedReport.setUserId(userId);
		requestedReport.setIsSchedule(false);
		requestedReport.setRequestStatus(RequestStatus.PENDING.toString());
		requestedReport.setCreateDate(now);
		requestedReport.setModifiedDate(now);

		requestedReportLocalService.addRequestedReport(requestedReport);

		Message message = new Message();
		message.setPayload(request);
		message.setResponseId(String.valueOf(requestedReport.getRequestId()));
		message.setResponseDestination("liferay/report_responses");

		MessageBusUtil.sendMessage("liferay/report_requests", message);
	}

	private static Log _log =
		LogFactoryUtil.getLog(RequestedReportLocalServiceImpl.class);
}
