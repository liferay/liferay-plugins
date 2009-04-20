/*
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

package com.liferay.bi.report.portlet.action;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.search.ReportDefinitionSearch;
import com.liferay.bi.report.service.RequestedReportLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.simplemvc.Action;

/**
 * <a href="SearchRequestAction.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class SearchRequestAction implements Action {

	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		actionResponse.setRenderParameter(
			TABS, actionRequest.getParameter(TABS));

		int cur =
			ParamUtil.getInteger(
				actionRequest, ReportDefinitionSearch.DEFAULT_CUR_PARAM,
				ReportDefinitionSearch.DEFAULT_CUR);

		int delta =
			ParamUtil.getInteger(
				actionRequest, ReportDefinitionSearch.DEFAULT_DELTA_PARAM,
				ReportDefinitionSearch.DEFAULT_DELTA);

		int start = (cur - 1) * delta;
		int end = start + delta;

		int total = 0;
		List<RequestedReport> requestedReports = EMPTY_LIST;
		long requestId = ParamUtil.getLong(actionRequest, "requestId", -1);
		try {

			if (requestId > 0) {
				requestedReports.add(RequestedReportLocalServiceUtil.getRequestedReport(requestId));
				total = 1;
			}
			else {
				total =
					RequestedReportLocalServiceUtil.getRequestedReportsCount();
				if (total > 0) {
					requestedReports =
						RequestedReportLocalServiceUtil.getRequestedReports(
							start, end);
				}
			}

			actionRequest.setAttribute(
				SEARCH_RESULTS_ATTRIBUTE, requestedReports);
			actionRequest.setAttribute(SEARCH_TOTAL_ATTRIBUTE, total);

			actionResponse.setRenderParameter(
				ReportDefinitionSearch.DEFAULT_CUR_PARAM, String.valueOf(cur));
			actionResponse.setRenderParameter(
				ReportDefinitionSearch.DEFAULT_DELTA_PARAM,
				String.valueOf(delta));

		}
		catch (PortalException e) {
			throw new PortletException("Unable to retrieve rquested report", e);
		}
		catch (SystemException e) {
			throw new PortletException("Unable to retrieve rquested report", e);
		}

		return false;
	}

	private static final String SEARCH_RESULTS_ATTRIBUTE = "searchResults";
	private static final String SEARCH_TOTAL_ATTRIBUTE = "total";
	private static final String TABS = "tabs1";
	private static final List<RequestedReport> EMPTY_LIST =
		new ArrayList<RequestedReport>(0);
}
