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

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="ReportDefinitionClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionClp extends BaseModelImpl<ReportDefinition>
	implements ReportDefinition {
	public ReportDefinitionClp() {
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
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

	public ReportDefinition toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			ReportDefinition model = new ReportDefinitionClp();

			model.setEscapedModel(true);

			model.setUuid(HtmlUtil.escape(getUuid()));
			model.setDefinitionId(getDefinitionId());
			model.setCompanyId(getCompanyId());
			model.setGroupId(getGroupId());
			model.setUserId(getUserId());
			model.setCreateDate(getCreateDate());
			model.setModifiedBy(getModifiedBy());
			model.setModifiedDate(getModifiedDate());
			model.setName(HtmlUtil.escape(getName()));
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setReportParameters(HtmlUtil.escape(getReportParameters()));
			model.setReportFormat(HtmlUtil.escape(getReportFormat()));
			model.setDataSourceName(HtmlUtil.escape(getDataSourceName()));

			model = (ReportDefinition)Proxy.newProxyInstance(ReportDefinition.class.getClassLoader(),
					new Class[] { ReportDefinition.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		ReportDefinitionClp clone = new ReportDefinitionClp();

		clone.setUuid(getUuid());
		clone.setDefinitionId(getDefinitionId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedBy(getModifiedBy());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
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

		ReportDefinitionClp reportDefinition = null;

		try {
			reportDefinition = (ReportDefinitionClp)obj;
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
	private long _definitionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private Date _createDate;
	private long _modifiedBy;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _reportParameters;
	private String _reportFormat;
	private String _dataSourceName;
}