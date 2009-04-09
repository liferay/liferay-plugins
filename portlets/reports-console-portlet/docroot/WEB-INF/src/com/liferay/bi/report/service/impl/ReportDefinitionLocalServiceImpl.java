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

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.impl.ReportDefinitionImpl;
import com.liferay.bi.report.service.base.ReportDefinitionLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <a href="ReportDefinitionLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionLocalServiceImpl
	extends ReportDefinitionLocalServiceBaseImpl {

	public ReportDefinition addReportDefinition(
		long companyId, long groupId, long userId, String definitionName,
		String description, String datasourceName, ReportFormat format)
	throws PortalException, SystemException {
		ReportDefinition definition = new ReportDefinitionImpl();
		// TBD Need to validate that name and report format are not null...
		definition.setDefinitionName(definitionName);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format.toString());

		definition.setCompanyId(companyId);
		definition.setGroupId(groupId);
		definition.setUserId(userId);
		return addReportDefinition(definition);
	}

	public ReportDefinition updateReportDefinition(
		long definitionId, String description, String datasourceName,
		ReportFormat format)
	throws PortalException, SystemException {
		ReportDefinition definition = getReportDefinition(definitionId);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format.toString());

		return updateReportDefinition(definition);
	}

	public List<ReportDefinition> getReportDefintions(
		long companyId, long groupId)
		throws SystemException {
		return reportDefinitionPersistence.findByCompanyGroupId(companyId, groupId);
	}

	public List<ReportDefinition> search(
		long companyId, long groupId, String keywords,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator obc) throws SystemException {
		return reportDefinitionFinder.findByKeywords(
			companyId, groupId, keywords, params, start, end, obc);
	}

	public List<ReportDefinition> search(
		long companyId, long groupId, String name, String description,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc) throws SystemException {

		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public int searchCount(
		long companyId, long groupId, String keywords, Boolean active,
		LinkedHashMap<String, Object> params) throws SystemException {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public int searchCount(
		long companyId, long groupId, String name,
		String description, Boolean active,
		LinkedHashMap<String, Object> params, boolean andSearch)
		throws SystemException {
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}
}