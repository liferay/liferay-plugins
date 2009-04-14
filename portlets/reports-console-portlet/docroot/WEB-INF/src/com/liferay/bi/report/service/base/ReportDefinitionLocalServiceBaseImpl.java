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

package com.liferay.bi.report.service.base;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.ReportDefinitionLocalService;
import com.liferay.bi.report.service.persistence.ReportDefinitionFinder;
import com.liferay.bi.report.service.persistence.ReportDefinitionPersistence;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="ReportDefinitionLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class ReportDefinitionLocalServiceBaseImpl
	implements ReportDefinitionLocalService {
	public ReportDefinition addReportDefinition(
		ReportDefinition reportDefinition) throws SystemException {
		reportDefinition.setNew(true);

		return reportDefinitionPersistence.update(reportDefinition, false);
	}

	public ReportDefinition createReportDefinition(long definitionId) {
		return reportDefinitionPersistence.create(definitionId);
	}

	public void deleteReportDefinition(long definitionId)
		throws PortalException, SystemException {
		reportDefinitionPersistence.remove(definitionId);
	}

	public void deleteReportDefinition(ReportDefinition reportDefinition)
		throws SystemException {
		reportDefinitionPersistence.remove(reportDefinition);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return reportDefinitionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return reportDefinitionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public ReportDefinition getReportDefinition(long definitionId)
		throws PortalException, SystemException {
		return reportDefinitionPersistence.findByPrimaryKey(definitionId);
	}

	public List<ReportDefinition> getReportDefinitions(int start, int end)
		throws SystemException {
		return reportDefinitionPersistence.findAll(start, end);
	}

	public int getReportDefinitionsCount() throws SystemException {
		return reportDefinitionPersistence.countAll();
	}

	public ReportDefinition updateReportDefinition(
		ReportDefinition reportDefinition) throws SystemException {
		reportDefinition.setNew(false);

		return reportDefinitionPersistence.update(reportDefinition, true);
	}

	public ReportDefinition updateReportDefinition(
		ReportDefinition reportDefinition, boolean merge)
		throws SystemException {
		reportDefinition.setNew(false);

		return reportDefinitionPersistence.update(reportDefinition, merge);
	}

	public ReportDefinitionLocalService getReportDefinitionLocalService() {
		return reportDefinitionLocalService;
	}

	public void setReportDefinitionLocalService(
		ReportDefinitionLocalService reportDefinitionLocalService) {
		this.reportDefinitionLocalService = reportDefinitionLocalService;
	}

	public ReportDefinitionPersistence getReportDefinitionPersistence() {
		return reportDefinitionPersistence;
	}

	public void setReportDefinitionPersistence(
		ReportDefinitionPersistence reportDefinitionPersistence) {
		this.reportDefinitionPersistence = reportDefinitionPersistence;
	}

	public ReportDefinitionFinder getReportDefinitionFinder() {
		return reportDefinitionFinder;
	}

	public void setReportDefinitionFinder(
		ReportDefinitionFinder reportDefinitionFinder) {
		this.reportDefinitionFinder = reportDefinitionFinder;
	}

	@BeanReference(name = "com.liferay.bi.report.service.ReportDefinitionLocalService.impl")
	protected ReportDefinitionLocalService reportDefinitionLocalService;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionPersistence.impl")
	protected ReportDefinitionPersistence reportDefinitionPersistence;
	@BeanReference(name = "com.liferay.bi.report.service.persistence.ReportDefinitionFinder.impl")
	protected ReportDefinitionFinder reportDefinitionFinder;
}