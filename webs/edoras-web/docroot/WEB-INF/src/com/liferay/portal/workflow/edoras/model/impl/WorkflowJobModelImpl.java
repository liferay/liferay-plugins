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

package com.liferay.portal.workflow.edoras.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.edoras.model.WorkflowJob;
import com.liferay.portal.workflow.edoras.model.WorkflowJobSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WorkflowJobModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobModelImpl extends BaseModelImpl<WorkflowJob> {
	public static final String TABLE_NAME = "Edoras_WorkflowJob";
	public static final Object[][] TABLE_COLUMNS = {
			{ "workflowJobId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "setupId", new Integer(Types.VARCHAR) },
			{ "workflowDefinitionId", new Integer(Types.BIGINT) },
			{ "workflowInstanceId", new Integer(Types.BIGINT) },
			{ "elementName", new Integer(Types.VARCHAR) },
			{ "cause", new Integer(Types.VARCHAR) },
			{ "dueDate", new Integer(Types.TIMESTAMP) },
			{ "notBeforeDate", new Integer(Types.TIMESTAMP) },
			{ "exceptionCount", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table Edoras_WorkflowJob (workflowJobId LONG not null primary key,companyId LONG,createDate DATE null,setupId VARCHAR(75) null,workflowDefinitionId LONG,workflowInstanceId LONG,elementName VARCHAR(75) null,cause VARCHAR(75) null,dueDate DATE null,notBeforeDate DATE null,exceptionCount INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table Edoras_WorkflowJob";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowJob"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowJob"),
			true);

	public static WorkflowJob toModel(WorkflowJobSoap soapModel) {
		WorkflowJob model = new WorkflowJobImpl();

		model.setWorkflowJobId(soapModel.getWorkflowJobId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setSetupId(soapModel.getSetupId());
		model.setWorkflowDefinitionId(soapModel.getWorkflowDefinitionId());
		model.setWorkflowInstanceId(soapModel.getWorkflowInstanceId());
		model.setElementName(soapModel.getElementName());
		model.setCause(soapModel.getCause());
		model.setDueDate(soapModel.getDueDate());
		model.setNotBeforeDate(soapModel.getNotBeforeDate());
		model.setExceptionCount(soapModel.getExceptionCount());

		return model;
	}

	public static List<WorkflowJob> toModels(WorkflowJobSoap[] soapModels) {
		List<WorkflowJob> models = new ArrayList<WorkflowJob>(soapModels.length);

		for (WorkflowJobSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.edoras.model.WorkflowJob"));

	public WorkflowJobModelImpl() {
	}

	public long getPrimaryKey() {
		return _workflowJobId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowJobId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_workflowJobId);
	}

	public long getWorkflowJobId() {
		return _workflowJobId;
	}

	public void setWorkflowJobId(long workflowJobId) {
		_workflowJobId = workflowJobId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getSetupId() {
		return GetterUtil.getString(_setupId);
	}

	public void setSetupId(String setupId) {
		_setupId = setupId;
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
	}

	public String getElementName() {
		return GetterUtil.getString(_elementName);
	}

	public void setElementName(String elementName) {
		_elementName = elementName;
	}

	public String getCause() {
		return GetterUtil.getString(_cause);
	}

	public void setCause(String cause) {
		_cause = cause;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getNotBeforeDate() {
		return _notBeforeDate;
	}

	public void setNotBeforeDate(Date notBeforeDate) {
		_notBeforeDate = notBeforeDate;
	}

	public int getExceptionCount() {
		return _exceptionCount;
	}

	public void setExceptionCount(int exceptionCount) {
		_exceptionCount = exceptionCount;
	}

	public WorkflowJob toEscapedModel() {
		if (isEscapedModel()) {
			return (WorkflowJob)this;
		}
		else {
			WorkflowJob model = new WorkflowJobImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setWorkflowJobId(getWorkflowJobId());
			model.setCompanyId(getCompanyId());
			model.setCreateDate(getCreateDate());
			model.setSetupId(HtmlUtil.escape(getSetupId()));
			model.setWorkflowDefinitionId(getWorkflowDefinitionId());
			model.setWorkflowInstanceId(getWorkflowInstanceId());
			model.setElementName(HtmlUtil.escape(getElementName()));
			model.setCause(HtmlUtil.escape(getCause()));
			model.setDueDate(getDueDate());
			model.setNotBeforeDate(getNotBeforeDate());
			model.setExceptionCount(getExceptionCount());

			model = (WorkflowJob)Proxy.newProxyInstance(WorkflowJob.class.getClassLoader(),
					new Class[] { WorkflowJob.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(WorkflowJob.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		WorkflowJobImpl clone = new WorkflowJobImpl();

		clone.setWorkflowJobId(getWorkflowJobId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setSetupId(getSetupId());
		clone.setWorkflowDefinitionId(getWorkflowDefinitionId());
		clone.setWorkflowInstanceId(getWorkflowInstanceId());
		clone.setElementName(getElementName());
		clone.setCause(getCause());
		clone.setDueDate(getDueDate());
		clone.setNotBeforeDate(getNotBeforeDate());
		clone.setExceptionCount(getExceptionCount());

		return clone;
	}

	public int compareTo(WorkflowJob workflowJob) {
		long pk = workflowJob.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WorkflowJob workflowJob = null;

		try {
			workflowJob = (WorkflowJob)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = workflowJob.getPrimaryKey();

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

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{workflowJobId=");
		sb.append(getWorkflowJobId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", setupId=");
		sb.append(getSetupId());
		sb.append(", workflowDefinitionId=");
		sb.append(getWorkflowDefinitionId());
		sb.append(", workflowInstanceId=");
		sb.append(getWorkflowInstanceId());
		sb.append(", elementName=");
		sb.append(getElementName());
		sb.append(", cause=");
		sb.append(getCause());
		sb.append(", dueDate=");
		sb.append(getDueDate());
		sb.append(", notBeforeDate=");
		sb.append(getNotBeforeDate());
		sb.append(", exceptionCount=");
		sb.append(getExceptionCount());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.edoras.model.WorkflowJob");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>workflowJobId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowJobId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>setupId</column-name><column-value><![CDATA[");
		sb.append(getSetupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowInstanceId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>elementName</column-name><column-value><![CDATA[");
		sb.append(getElementName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cause</column-name><column-value><![CDATA[");
		sb.append(getCause());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dueDate</column-name><column-value><![CDATA[");
		sb.append(getDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>notBeforeDate</column-name><column-value><![CDATA[");
		sb.append(getNotBeforeDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>exceptionCount</column-name><column-value><![CDATA[");
		sb.append(getExceptionCount());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _workflowJobId;
	private long _companyId;
	private Date _createDate;
	private String _setupId;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private String _elementName;
	private String _cause;
	private Date _dueDate;
	private Date _notBeforeDate;
	private int _exceptionCount;
	private transient ExpandoBridge _expandoBridge;
}