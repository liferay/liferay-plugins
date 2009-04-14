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

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
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
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		long groupId = themeDisplay.getScopeGroupId();		
		
		//TBD Need to implement search features and functionality...
	
		int start = ParamUtil.getInteger(actionRequest, "start");
		int end = ParamUtil.getInteger(actionRequest, "end");

		int count = 0;
		List<ReportDefinition> definitions = null;
		
		try {
		    if(ParamUtil.getBoolean(actionRequest, "advancedSearch")){
			definitions =
				ReportDefinitionLocalServiceUtil.getReportDefintions(companyId, groupId);
		    }else{
			String keywords = ParamUtil.getString(request, "keywords");

			count = ReportDefinitionLocalServiceUtil
				.searchCount(companyId, groupId, keywords, true, null);
			
			if(count > SearchContainer.DEFAULT_DELTA){
			    end = SearchContainer.DEFAULT_DELTA;
			}else{
			    end = count;
			}
			
			definitions = ReportDefinitionLocalServiceUtil
				.search(companyId, groupId, keywords, null, start, end, null);
		    }
		    
		actionRequest.setAttribute("searchResults", definitions);

		}
		catch (SystemException e) {
			throw new PortletException(
				"Unable to retrieve report definitions", e);
		}


		return false;
	}
}
