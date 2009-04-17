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

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.search.ReportDefinitionDisplayTerms;
import com.liferay.bi.report.search.ReportDefinitionSearch;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.simplemvc.Action;

/**
 * <a href="SearchDefinitionAction.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 */
public class SearchDefinitionAction implements Action {

	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();
		long groupId = themeDisplay.getScopeGroupId();

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
		boolean isAdvancedSearch =
			ParamUtil.getBoolean(
				actionRequest, ReportDefinitionDisplayTerms.ADVANCED_SEARCH);

		try {
			if (isAdvancedSearch) {
				performAdvancedSearch(
					actionRequest, actionResponse, themeDisplay, start, end);
			}
			else {
				performBasicSearch(
					actionRequest, actionResponse, companyId, groupId,
					start, end);
			}
			actionResponse.setRenderParameter(
				ReportDefinitionSearch.DEFAULT_CUR_PARAM, String.valueOf(cur));
			actionResponse.setRenderParameter(
				ReportDefinitionSearch.DEFAULT_DELTA_PARAM,
				String.valueOf(delta));
			actionResponse.setRenderParameter(
				ReportDefinitionDisplayTerms.ADVANCED_SEARCH,
				String.valueOf(isAdvancedSearch));

		}
		catch (SystemException e) {
			throw new PortletException(
				"Unable to retrieve report definitions", e);
		}

		return false;
	}

	private void performBasicSearch(
		ActionRequest actionRequest, ActionResponse actionResponse,
		long companyId, long groupId, int start, int end)
		throws SystemException {

		String keywords =
			ParamUtil.getString(
				actionRequest, ReportDefinitionDisplayTerms.KEYWORDS);
		actionResponse.setRenderParameter(
			ReportDefinitionDisplayTerms.KEYWORDS, keywords);

		int total =
			ReportDefinitionLocalServiceUtil.searchCount(
					companyId, groupId, keywords, true, null);
		List<ReportDefinition> definitions = EMPTY_LIST;
		if (total > 0) {
			definitions =
				ReportDefinitionLocalServiceUtil.search(
					companyId, groupId, keywords, null, start, end, null);
		}

		actionRequest.setAttribute(SEARCH_RESULTS_ATTRIBUTE, definitions);
		actionRequest.setAttribute(SEARCH_TOTAL_ATTRIBUTE, total);
	}

	private void performAdvancedSearch(
		ActionRequest actionRequest, ActionResponse actionResponse,
		ThemeDisplay themeDisplay, int start, int end)
		throws SystemException {

		String name =
			ParamUtil.getString(
				actionRequest, ReportDefinitionDisplayTerms.NAME);
		String description =
			ParamUtil.getString(
				actionRequest, ReportDefinitionDisplayTerms.DESCRIPTION);
		boolean isAndOperator =
			ParamUtil.getBoolean(
				actionRequest, ReportDefinitionDisplayTerms.AND_OPERATOR);

		actionResponse.setRenderParameter(
			ReportDefinitionDisplayTerms.NAME, name);
		actionResponse.setRenderParameter(
			ReportDefinitionDisplayTerms.DESCRIPTION, description);
		actionResponse.setRenderParameter(
			ReportDefinitionDisplayTerms.AND_OPERATOR,
			String.valueOf(isAndOperator));

		if (Validator.isNull(name)) {
			name = null;
		}
		if (Validator.isNull(description)) {
			description = null;
		}

		int total =
				ReportDefinitionLocalServiceUtil.searchCount(
					themeDisplay.getCompanyId(),
					themeDisplay.getScopeGroupId(), name, description, true,
					null, isAndOperator);
		List<ReportDefinition> definitions = EMPTY_LIST;
		if (total > 0) {
			definitions =
				ReportDefinitionLocalServiceUtil.search(
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
				name, description, null, isAndOperator, start, end, null);
		}

		actionRequest.setAttribute(SEARCH_RESULTS_ATTRIBUTE, definitions);
		actionRequest.setAttribute(SEARCH_TOTAL_ATTRIBUTE, total);
	}

	private static final String SEARCH_RESULTS_ATTRIBUTE = "searchResults";
	private static final String SEARCH_TOTAL_ATTRIBUTE = "total";
	private static final List<ReportDefinition> EMPTY_LIST =
		new ArrayList<ReportDefinition>(0);
}
