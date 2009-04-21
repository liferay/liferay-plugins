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

package com.liferay.bi.report.messaging;

import java.util.Date;

import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.portlet.RequestStatus;
import com.liferay.bi.report.service.RequestedReportLocalServiceUtil;
import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;

/**
 * <a href="ReportResponseMessageListener.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Gavin Wan
 */
public class ReportResponseMessageListener implements MessageListener {

	public void receive(Message message) {

		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message)
		throws Exception {

		long requestReportId = Long.parseLong(message.getResponseId());

		ReportResultContainer reportResultContainer =
			(ReportResultContainer) message.getPayload();
		RequestedReport requestedReport =
			RequestedReportLocalServiceUtil.getRequestedReport(requestReportId);
		Date now = new Date();
		if (reportResultContainer.hasError()) {
			requestedReport.setRequestStatus(RequestStatus.ERROR.toString());
		}
		else {
			long companyId = requestedReport.getCompanyId();
			long groupId = requestedReport.getGroupId();

			String path = requestedReport.getAttachmentsDir();
			try {
				DLServiceUtil.addDirectory(companyId, repositoryId, path);
			}
			catch (DuplicateDirectoryException dde) {
			}
			try {
				DLServiceUtil.addFile(
					companyId, portletId, groupId, repositoryId, path +
						StringPool.SLASH +
						reportResultContainer.getReportName(), fileEntryId,
					properties, now, tagsCategories, tagsEntries,
					reportResultContainer.getResults());
				requestedReport.setRequestStatus(RequestStatus.COMPLETE.toString());
			}
			catch (Exception e) {
				requestedReport.setRequestStatus(RequestStatus.ERROR.toString());
				_log.error(e);
				e.printStackTrace();
			}
		}
		requestedReport.setModifiedDate(now);

		RequestedReportLocalServiceUtil.updateRequestedReport(requestedReport);

	}

	private static final String portletId = CompanyConstants.SYSTEM_STRING;
	private static final long repositoryId = CompanyConstants.SYSTEM;
	private static final long fileEntryId = 0;
	private static final String properties = StringPool.BLANK;
	private static final String[] tagsCategories = new String[0];
	private static final String[] tagsEntries = new String[0];
	private static Log _log =
		LogFactoryUtil.getLog(ReportResponseMessageListener.class);

}
