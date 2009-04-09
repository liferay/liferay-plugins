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

package com.liferay.bi.report.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ReportDefinitionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionSoap implements Serializable {
	public static ReportDefinitionSoap toSoapModel(ReportDefinition model) {
		ReportDefinitionSoap soapModel = new ReportDefinitionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDefinitionId(model.getDefinitionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDefinitionName(model.getDefinitionName());
		soapModel.setDescription(model.getDescription());
		soapModel.setReportParameters(model.getReportParameters());
		soapModel.setReportFormat(model.getReportFormat());
		soapModel.setDataSourceName(model.getDataSourceName());

		return soapModel;
	}

	public static ReportDefinitionSoap[] toSoapModels(
		List<ReportDefinition> models) {
		List<ReportDefinitionSoap> soapModels = new ArrayList<ReportDefinitionSoap>(models.size());

		for (ReportDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ReportDefinitionSoap[soapModels.size()]);
	}

	public ReportDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _definitionId;
	}

	public void setPrimaryKey(long pk) {
		setDefinitionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDefinitionId() {
		return _definitionId;
	}

	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getDefinitionName() {
		return _definitionName;
	}

	public void setDefinitionName(String definitionName) {
		_definitionName = definitionName;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getReportParameters() {
		return _reportParameters;
	}

	public void setReportParameters(String reportParameters) {
		_reportParameters = reportParameters;
	}

	public String getReportFormat() {
		return _reportFormat;
	}

	public void setReportFormat(String reportFormat) {
		_reportFormat = reportFormat;
	}

	public String getDataSourceName() {
		return _dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	private String _uuid;
	private long _definitionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private long _modifiedBy;
	private Date _modifiedDate;
	private String _definitionName;
	private String _description;
	private String _reportParameters;
	private String _reportFormat;
	private String _dataSourceName;
}