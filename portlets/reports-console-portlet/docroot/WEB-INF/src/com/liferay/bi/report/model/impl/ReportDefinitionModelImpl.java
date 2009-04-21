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

package com.liferay.bi.report.model.impl;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.ReportDefinitionSoap;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ReportDefinitionModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionModelImpl extends BaseModelImpl<ReportDefinition> {
	public static final String TABLE_NAME = "Report_ReportDefinition";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", new Integer(Types.VARCHAR) },
			

			{ "definitionId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedBy", new Integer(Types.BIGINT) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "definitionName", new Integer(Types.VARCHAR) },
			

			{ "description", new Integer(Types.VARCHAR) },
			

			{ "reportName", new Integer(Types.VARCHAR) },
			

			{ "reportParameters", new Integer(Types.VARCHAR) },
			

			{ "reportFormat", new Integer(Types.VARCHAR) },
			

			{ "dataSourceName", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Report_ReportDefinition (uuid_ VARCHAR(75) null,definitionId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,createDate DATE null,modifiedBy LONG,modifiedDate DATE null,definitionName VARCHAR(75) null,description VARCHAR(75) null,reportName VARCHAR(75) null,reportParameters VARCHAR(75) null,reportFormat VARCHAR(75) null,dataSourceName VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Report_ReportDefinition";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.bi.report.model.ReportDefinition"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.bi.report.model.ReportDefinition"),
			true);

	public static ReportDefinition toModel(ReportDefinitionSoap soapModel) {
		ReportDefinition model = new ReportDefinitionImpl();

		model.setUuid(soapModel.getUuid());
		model.setDefinitionId(soapModel.getDefinitionId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedBy(soapModel.getModifiedBy());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDefinitionName(soapModel.getDefinitionName());
		model.setDescription(soapModel.getDescription());
		model.setReportName(soapModel.getReportName());
		model.setReportParameters(soapModel.getReportParameters());
		model.setReportFormat(soapModel.getReportFormat());
		model.setDataSourceName(soapModel.getDataSourceName());

		return model;
	}

	public static List<ReportDefinition> toModels(
		ReportDefinitionSoap[] soapModels) {
		List<ReportDefinition> models = new ArrayList<ReportDefinition>(soapModels.length);

		for (ReportDefinitionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.bi.report.model.ReportDefinition"));

	public ReportDefinitionModelImpl() {
	}

	public long getPrimaryKey() {
		return _definitionId;
	}

	public void setPrimaryKey(long pk) {
		setDefinitionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_definitionId);
	}

	public String getUuid() {
		return GetterUtil.getString(_uuid);
	}

	public void setUuid(String uuid) {
		_uuid = uuid;

		if (_originalUuid == null) {
			_originalUuid = uuid;
		}
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
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

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = groupId;
		}
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
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
		return GetterUtil.getString(_definitionName);
	}

	public void setDefinitionName(String definitionName) {
		_definitionName = definitionName;
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getReportName() {
		return GetterUtil.getString(_reportName);
	}

	public void setReportName(String reportName) {
		_reportName = reportName;
	}

	public String getReportParameters() {
		return GetterUtil.getString(_reportParameters);
	}

	public void setReportParameters(String reportParameters) {
		_reportParameters = reportParameters;
	}

	public String getReportFormat() {
		return GetterUtil.getString(_reportFormat);
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

	public ReportDefinition toEscapedModel() {
		if (isEscapedModel()) {
			return (ReportDefinition)this;
		}
		else {
			ReportDefinition model = new ReportDefinitionImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setUuid(HtmlUtil.escape(getUuid()));
			model.setDefinitionId(getDefinitionId());
			model.setCompanyId(getCompanyId());
			model.setGroupId(getGroupId());
			model.setUserId(getUserId());
			model.setCreateDate(getCreateDate());
			model.setModifiedBy(getModifiedBy());
			model.setModifiedDate(getModifiedDate());
			model.setDefinitionName(HtmlUtil.escape(getDefinitionName()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setReportName(HtmlUtil.escape(getReportName()));
			model.setReportParameters(HtmlUtil.escape(getReportParameters()));
			model.setReportFormat(HtmlUtil.escape(getReportFormat()));
			model.setDataSourceName(HtmlUtil.escape(getDataSourceName()));

			model = (ReportDefinition)Proxy.newProxyInstance(ReportDefinition.class.getClassLoader(),
					new Class[] { ReportDefinition.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(ReportDefinition.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		ReportDefinitionImpl clone = new ReportDefinitionImpl();

		clone.setUuid(getUuid());
		clone.setDefinitionId(getDefinitionId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedBy(getModifiedBy());
		clone.setModifiedDate(getModifiedDate());
		clone.setDefinitionName(getDefinitionName());
		clone.setDescription(getDescription());
		clone.setReportName(getReportName());
		clone.setReportParameters(getReportParameters());
		clone.setReportFormat(getReportFormat());
		clone.setDataSourceName(getDataSourceName());

		return clone;
	}

	public int compareTo(ReportDefinition reportDefinition) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				reportDefinition.getModifiedDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		ReportDefinition reportDefinition = null;

		try {
			reportDefinition = (ReportDefinition)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = reportDefinition.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	private String _uuid;
	private String _originalUuid;
	private long _definitionId;
	private long _companyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _userId;
	private Date _createDate;
	private long _modifiedBy;
	private Date _modifiedDate;
	private String _definitionName;
	private String _description;
	private String _reportName;
	private String _reportParameters;
	private String _reportFormat;
	private String _dataSourceName;
	private transient ExpandoBridge _expandoBridge;
}