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
import java.util.List;

import com.liferay.bi.report.NoSuchDefinitionException;
import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.portlet.RequestStatus;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.bi.report.service.base.RequestedReportLocalServiceBaseImpl;
import com.liferay.bi.report.util.ReportUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;

/**
 * <a href="RequestedReportLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Brian Wing Shun Chan
 */
public class RequestedReportLocalServiceImpl
	extends RequestedReportLocalServiceBaseImpl {

	public static final String REPORT_REQUEST = "liferay/report_requests";
	public static final String REPORT_RESPONSES = "liferay/report_responses";

	public void addScheduler(
		long definitionId, String cronText, Date startDate, Date endDate,
		String description, long userId)
		throws PortalException, SystemException {

		if (definitionId <= 0) {
			throw new NoSuchDefinitionException();
		}
		if (Validator.isNull(description)) {
			throw new PortalException();
		}

		ReportDefinition definition =
			ReportDefinitionLocalServiceUtil.getReportDefinition(definitionId);

		Date now = new Date();
		long requestId = CounterLocalServiceUtil.increment();
		RequestedReport requestedReport =
			requestedReportLocalService.createRequestedReport(requestId);
		requestedReport.setCompanyId(definition.getCompanyId());
		requestedReport.setGroupId(definition.getGroupId());
		requestedReport.setDefinitionId(definitionId);
		requestedReport.setUserId(userId);
		requestedReport.setScheduleRequest(true);
		requestedReport.setRequestStatus(RequestStatus.PENDING.toString());
		requestedReport.setCreateDate(now);
		requestedReport.setModifiedDate(now);

		try {
			ReportRequest reportRequest =
				ReportUtil.getReportRequest(definition);
			String groupName =
				ReportUtil.getSchedulerRequestName(requestId);

			SchedulerEngineUtil.schedule(
				groupName, cronText, startDate, endDate, description,
				REPORT_REQUEST, JSONFactoryUtil.serialize(reportRequest));
		}
		catch (Exception e) {
			requestedReport.setRequestStatus(RequestStatus.ERROR.toString());
		}

		requestedReportLocalService.addRequestedReport(requestedReport);

	}

	public int countByDefinitionId(long definitionId)
		throws SystemException {

		return requestedReportPersistence.countByDefinitionId(definitionId);
	}

	public void deleteRequestAndAttachments(long requestId)
		throws PortalException, SystemException {

		RequestedReport requestedReport = getRequestedReport(requestId);
		String[] existingFiles = requestedReport.getAttachmentsFiles();
		for (String existingFile : existingFiles) {
			DLServiceUtil.deleteFile(
				requestedReport.getCompanyId(), CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM, existingFile);
		}
		requestedReportLocalService.deleteRequestedReport(requestId);
	}

	public List<RequestedReport> findByDefinitionId(
		long definitionId, int start, int end)
		throws SystemException {

		return requestedReportPersistence.findByDefinitionId(
			definitionId, start, end);
	}

	public void generateReport(
		long companyId, long groupId, long userId, long definitionId)
		throws PortalException, SystemException {

		ReportDefinition definition =
			reportDefinitionPersistence.fetchByPrimaryKey(definitionId);

		ReportRequest request = ReportUtil.getReportRequest(definition);
		Date now = new Date();

		long requestId = CounterLocalServiceUtil.increment();
		RequestedReport requestedReport =
			requestedReportLocalService.createRequestedReport(requestId);
		requestedReport.setCompanyId(companyId);
		requestedReport.setGroupId(groupId);
		requestedReport.setDefinitionId(definitionId);
		requestedReport.setUserId(userId);
		requestedReport.setScheduleRequest(false);
		requestedReport.setRequestStatus(RequestStatus.PENDING.toString());
		requestedReport.setCreateDate(now);
		requestedReport.setModifiedDate(now);

		requestedReportLocalService.addRequestedReport(requestedReport);

		Message message = new Message();
		message.setPayload(request);
		message.setResponseId(String.valueOf(requestId));
		message.setResponseDestination(REPORT_RESPONSES);

		MessageBusUtil.sendMessage(REPORT_REQUEST, message);
	}

}
