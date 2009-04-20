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

package com.liferay.bi.report.search;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.bi.report.model.RequestedReport;
import com.liferay.portal.kernel.dao.search.SearchContainer;

/**
 * <a href="RequestedReportSearch.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class RequestedReportSearch extends SearchContainer<RequestedReport> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("requested-report-id");
		headerNames.add("requested-report-status");
		headerNames.add("is-schedule-request");
		headerNames.add("");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-requested-report-were-found";

	public RequestedReportSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new RequestedReportDisplayTerms(portletRequest),
			new RequestedReportSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);		

		RequestedReportDisplayTerms displayTerms =
			(RequestedReportDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			RequestedReportDisplayTerms.REQUEST_ID,displayTerms.getRequestId());

	}

}
