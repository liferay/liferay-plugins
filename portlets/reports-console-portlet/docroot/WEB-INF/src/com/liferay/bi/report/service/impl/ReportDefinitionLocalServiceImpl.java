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

import com.liferay.bi.report.DefinitionFileException;
import com.liferay.bi.report.DefinitionFormatException;
import com.liferay.bi.report.DefinitionNameException;
import com.liferay.bi.report.NoSuchDefinitionException;
import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.base.ReportDefinitionLocalServiceBaseImpl;
import com.liferay.bi.report.util.ReportUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.documentlibrary.DuplicateDirectoryException;
import com.liferay.documentlibrary.DuplicateFileException;
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

	private static String portletId = CompanyConstants.SYSTEM_STRING;
	private static long repositoryId = CompanyConstants.SYSTEM;
	private static long fileEntryId = 0;
	private static String properties = StringPool.BLANK;
	private static Date modifiedDate = new Date();
	private static String[] tagsCategories = new String[0];
	private static String[] tagsEntries = new String[0];

	public ReportDefinition addReportDefinition(
		long companyId, long groupId, long userId, String definitionName,
		String description, String datasourceName, String format,
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
		definition.setReportFormat(format);
		definition.setReportParameters(reportParameters);

		validate(definition);

		if (Validator.isNull(fileName)) {
			throw new DefinitionFileException();
		}

		try {
			DLServiceUtil.addDirectory(
				companyId, repositoryId, definition.getAttachmentsDir());
		}
		catch (DuplicateDirectoryException dde) {
		}
		try {
			DLServiceUtil.addFile(
				companyId, portletId, groupId, repositoryId,
				definition.getAttachmentsDir() + StringPool.SLASH + fileName,
				fileEntryId, properties, modifiedDate, tagsCategories,
				tagsEntries, file);
		}
		catch (PortalException e) {
			DLServiceUtil.deleteDirectory(
				companyId, portletId, repositoryId,
				definition.getAttachmentsDir());
			throw new DefinitionFileException();
		}

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
		String datasourceName, String format, String reportParameters)
		throws PortalException, SystemException {

		ReportDefinition definition = getReportDefinition(definitionId);
		definition.setDefinitionName(definitionName);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format);
		definition.setReportParameters(reportParameters);

		validate(definition);

		return updateReportDefinition(definition);
	}

	public ReportDefinition updateReportDefinition(
		long definitionId, String definitionName, String description,
		String datasourceName, String format, String reportParameters,
		String fileName, File file)
		throws PortalException, SystemException {

		ReportDefinition definition = getReportDefinition(definitionId);
		definition.setDefinitionName(definitionName);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format);
		definition.setReportParameters(reportParameters);

		validate(definition);

		if (Validator.isNull(fileName)) {
			throw new DefinitionFileException();
		}

		fileName = definition.getAttachmentsDir() + StringPool.SLASH + fileName;

		try {
			DLServiceUtil.addFile(
				definition.getCompanyId(), portletId, definition.getGroupId(),
				repositoryId, fileName, fileEntryId, properties, modifiedDate,
				tagsCategories, tagsEntries, file);
		}
		catch (DuplicateFileException e) {
		}
		catch (PortalException e) {
			throw new DefinitionFileException();
		}

		String[] existingFiles = definition.getAttachmentsFiles();
		for (String existingFile : existingFiles) {
			if (fileName != existingFile) {
				DLServiceUtil.deleteFile(
					definition.getCompanyId(), portletId, repositoryId,
					existingFile);
			}
		}

		return updateReportDefinition(definition);
	}

	private void validate(ReportDefinition definition)
		throws PortalException {

		if (definition.getDefinitionId() <= 0) {
			throw new NoSuchDefinitionException();
		}

		if (Validator.isNull(definition.getDefinitionName())) {
			throw new DefinitionNameException();
		}

		try {
			ReportFormat.parse(definition.getReportFormat());
		}
		catch (IllegalArgumentException e) {
			throw new DefinitionFormatException();
		}
	}

}
