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

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.servlet.ServletContext;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.base.ReportDefinitionLocalServiceBaseImpl;
import com.liferay.bi.report.util.ReportUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.util.PortalUtil;

/**
 * <a href="ReportDefinitionLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Brian Wing Shun Chan
 */
public class ReportDefinitionLocalServiceImpl
	extends ReportDefinitionLocalServiceBaseImpl {

	public ReportDefinition addReportDefinition(
		long companyId, long groupId, long userId, String definitionName,
		String description, String datasourceName, ReportFormat format,
		String fileName, File file, String reportParameters)
		throws PortalException, SystemException {

		long definitionId = CounterLocalServiceUtil.increment();
		ReportDefinition definition =
			reportDefinitionPersistence.create(definitionId);
		definition.setCompanyId(companyId);
		definition.setGroupId(groupId);
		definition.setUserId(userId);
		definition.setDefinitionName(definitionName);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format.toString());
		definition.setReportParameters(reportParameters);

		String portletId = CompanyConstants.SYSTEM_STRING;
		long repositoryId = 0;
		long fileEntryId = 0;
		String properties = StringPool.BLANK;
		Date modifiedDate = new Date();
		String[] tagsCategories = new String[0];
		String[] tagsEntries = new String[0];

		DLServiceUtil.addDirectory(
			companyId, repositoryId, definition.getAttachmentsDir());

		DLServiceUtil.addFile(
			companyId, portletId, groupId, repositoryId,
			definition.getAttachmentsDir() + StringPool.SLASH + fileName,
			fileEntryId, properties, modifiedDate, tagsCategories, tagsEntries,
			file);

		return addReportDefinition(definition);
	}

	public void genrateReport(PortletRequest portletRequest, long definitionId)
		throws PortalException, SystemException {
		ServletContext servletContext =
			PortalUtil.getHttpServletRequest(portletRequest).getSession().getServletContext();
		ReportDefinition deportDefinition = getReportDefinition(definitionId);
		ReportRequest reportRequest =
			ReportUtil.getReportRequest(servletContext, deportDefinition);

		MessageBusUtil.sendMessage("liferay/report_request", reportRequest);

	}

	public List<ReportDefinition> getReportDefintions(
		long companyId, long groupId)
		throws SystemException {

		return reportDefinitionPersistence.findByCompanyGroupId(
			companyId, groupId);
	}

	public List<ReportDefinition> search(
		long companyId, long groupId, String keywords,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator obc)
		throws SystemException {

		return reportDefinitionFinder.findByKeywords(
			companyId, groupId, keywords, params, start, end, obc);
	}

	public List<ReportDefinition> search(
		long companyId, long groupId, String name, String description,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc)
		throws SystemException {

		return reportDefinitionFinder.findByC_G_N_D(
			companyId, groupId, new String[] {
				name
			}, new String[] {
				description
			}, andSearch, start, end, obc);
	}

	public int searchCount(
		long companyId, long groupId, String keywords, Boolean active,
		LinkedHashMap<String, Object> params)
		throws SystemException {

		return reportDefinitionFinder.countByKeywords(
			companyId, groupId, keywords, params);
	}

	public int searchCount(
		long companyId, long groupId, String name, String description,
		Boolean active, LinkedHashMap<String, Object> params, boolean andSearch)
		throws SystemException {

		return reportDefinitionFinder.countByC_G_N_D(
			companyId, groupId, new String[] {
				name
			}, new String[] {
				description
			}, andSearch);
	}

	public ReportDefinition updateReportDefinition(
		long definitionId, String definitionName, String description,
		String datasourceName, ReportFormat format, String reportParameters)
		throws PortalException, SystemException {

		return updateReportDefinition(
			definitionId, definitionName, description, datasourceName, format,
			reportParameters, null, null);
	}

	public ReportDefinition updateReportDefinition(
		long definitionId, String definitionName, String description,
		String datasourceName, ReportFormat format, String reportParameters,
		String fileName, File file)
		throws PortalException, SystemException {

		ReportDefinition definition = getReportDefinition(definitionId);
		definition.setDefinitionName(definitionName);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format.toString());
		definition.setReportParameters(reportParameters);

		if (Validator.isNotNull(fileName)) {
			String[] existingAttachments =
				DLServiceUtil.getFileNames(
					definition.getCompanyId(), CompanyConstants.SYSTEM,
					definition.getAttachmentsDir());
			for (String attachment : existingAttachments) {
				DLServiceUtil.deleteFile(
					definition.getCompanyId(), CompanyConstants.SYSTEM_STRING,
					CompanyConstants.SYSTEM, attachment);
			}

			String portletId = CompanyConstants.SYSTEM_STRING;
			long repositoryId = 0;
			long fileEntryId = 0;
			String properties = StringPool.BLANK;
			Date modifiedDate = new Date();
			String[] tagsCategories = new String[0];
			String[] tagsEntries = new String[0];

			DLServiceUtil.addFile(
				definition.getCompanyId(), portletId, definition.getGroupId(),
				repositoryId, definition.getAttachmentsDir() +
					StringPool.SLASH + fileName, fileEntryId, properties,
				modifiedDate, tagsCategories, tagsEntries, file);
		}

		return updateReportDefinition(definition);
	}

}
