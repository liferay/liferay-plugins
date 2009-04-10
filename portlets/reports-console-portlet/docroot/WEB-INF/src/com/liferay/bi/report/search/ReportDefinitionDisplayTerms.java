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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * <a href="ReportDefintionDisplayTerms.java.html"><b><i>View
 * Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ReportDefinitionDisplayTerms extends DisplayTerms {

	public static final String DEFINITION_ID = "definitionId";

	public static final String NAME = "name";

	public static final String DESCRIPTION = "description";

	public ReportDefinitionDisplayTerms(PortletRequest request) {
		super(request);

		_definitionId = ParamUtil.getString(request, DEFINITION_ID);
		_name = ParamUtil.getString(request, NAME);
		_description = ParamUtil.getString(request, DESCRIPTION);
	}

	public String getDefinitionId() {
		return _definitionId;
	}

	public String getDescription() {
		return _description;
	}

	public String getName() {
		return _name;
	}

	protected String _definitionId;
	protected String _name;
	protected String _description;
}