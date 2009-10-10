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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.edoras.model.WorkflowTask;
import com.liferay.portal.workflow.edoras.model.WorkflowTaskSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WorkflowTaskModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskModelImpl extends BaseModelImpl<WorkflowTask> {
	public static final String TABLE_NAME = "Edoras_WorkflowTask";
	public static final Object[][] TABLE_COLUMNS = {
			{ "workflowTaskId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "friendlyId", new Integer(Types.VARCHAR) },
			{ "workflowDefinitionId", new Integer(Types.BIGINT) },
			{ "workflowInstanceId", new Integer(Types.BIGINT) },
			{ "metaName", new Integer(Types.VARCHAR) },
			{ "relation", new Integer(Types.VARCHAR) },
			{ "dueDate", new Integer(Types.TIMESTAMP) },
			{ "completionDate", new Integer(Types.TIMESTAMP) },
			{ "state", new Integer(Types.INTEGER) },
			{ "priority", new Integer(Types.INTEGER) },
			{ "assigneeUserId", new Integer(Types.BIGINT) },
			{ "assigneeUserName", new Integer(Types.VARCHAR) },
			{ "roleId", new Integer(Types.BIGINT) }
		};
	public static final String TABLE_SQL_CREATE = "create table Edoras_WorkflowTask (workflowTaskId LONG not null primary key,companyId LONG,createDate DATE null,friendlyId VARCHAR(75) null,workflowDefinitionId LONG,workflowInstanceId LONG,metaName VARCHAR(75) null,relation VARCHAR(75) null,dueDate DATE null,completionDate DATE null,state INTEGER,priority INTEGER,assigneeUserId LONG,assigneeUserName VARCHAR(75) null,roleId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Edoras_WorkflowTask";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowTask"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowTask"),
			true);

	public static WorkflowTask toModel(WorkflowTaskSoap soapModel) {
		WorkflowTask model = new WorkflowTaskImpl();

		model.setWorkflowTaskId(soapModel.getWorkflowTaskId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setFriendlyId(soapModel.getFriendlyId());
		model.setWorkflowDefinitionId(soapModel.getWorkflowDefinitionId());
		model.setWorkflowInstanceId(soapModel.getWorkflowInstanceId());
		model.setMetaName(soapModel.getMetaName());
		model.setRelation(soapModel.getRelation());
		model.setDueDate(soapModel.getDueDate());
		model.setCompletionDate(soapModel.getCompletionDate());
		model.setState(soapModel.getState());
		model.setPriority(soapModel.getPriority());
		model.setAssigneeUserId(soapModel.getAssigneeUserId());
		model.setAssigneeUserName(soapModel.getAssigneeUserName());
		model.setRoleId(soapModel.getRoleId());

		return model;
	}

	public static List<WorkflowTask> toModels(WorkflowTaskSoap[] soapModels) {
		List<WorkflowTask> models = new ArrayList<WorkflowTask>(soapModels.length);

		for (WorkflowTaskSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.edoras.model.WorkflowTask"));

	public WorkflowTaskModelImpl() {
	}

	public long getPrimaryKey() {
		return _workflowTaskId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowTaskId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_workflowTaskId);
	}

	public long getWorkflowTaskId() {
		return _workflowTaskId;
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowTaskId = workflowTaskId;
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

	public String getFriendlyId() {
		return GetterUtil.getString(_friendlyId);
	}

	public void setFriendlyId(String friendlyId) {
		_friendlyId = friendlyId;
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

	public String getMetaName() {
		return GetterUtil.getString(_metaName);
	}

	public void setMetaName(String metaName) {
		_metaName = metaName;
	}

	public String getRelation() {
		return GetterUtil.getString(_relation);
	}

	public void setRelation(String relation) {
		_relation = relation;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_assigneeUserId = assigneeUserId;
	}

	public String getAssigneeUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getAssigneeUserId(), "uuid",
			_assigneeUserUuid);
	}

	public void setAssigneeUserUuid(String assigneeUserUuid) {
		_assigneeUserUuid = assigneeUserUuid;
	}

	public String getAssigneeUserName() {
		return GetterUtil.getString(_assigneeUserName);
	}

	public void setAssigneeUserName(String assigneeUserName) {
		_assigneeUserName = assigneeUserName;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public WorkflowTask toEscapedModel() {
		if (isEscapedModel()) {
			return (WorkflowTask)this;
		}
		else {
			WorkflowTask model = new WorkflowTaskImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setWorkflowTaskId(getWorkflowTaskId());
			model.setCompanyId(getCompanyId());
			model.setCreateDate(getCreateDate());
			model.setFriendlyId(HtmlUtil.escape(getFriendlyId()));
			model.setWorkflowDefinitionId(getWorkflowDefinitionId());
			model.setWorkflowInstanceId(getWorkflowInstanceId());
			model.setMetaName(HtmlUtil.escape(getMetaName()));
			model.setRelation(HtmlUtil.escape(getRelation()));
			model.setDueDate(getDueDate());
			model.setCompletionDate(getCompletionDate());
			model.setState(getState());
			model.setPriority(getPriority());
			model.setAssigneeUserId(getAssigneeUserId());
			model.setAssigneeUserName(HtmlUtil.escape(getAssigneeUserName()));
			model.setRoleId(getRoleId());

			model = (WorkflowTask)Proxy.newProxyInstance(WorkflowTask.class.getClassLoader(),
					new Class[] { WorkflowTask.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(WorkflowTask.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		WorkflowTaskImpl clone = new WorkflowTaskImpl();

		clone.setWorkflowTaskId(getWorkflowTaskId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setFriendlyId(getFriendlyId());
		clone.setWorkflowDefinitionId(getWorkflowDefinitionId());
		clone.setWorkflowInstanceId(getWorkflowInstanceId());
		clone.setMetaName(getMetaName());
		clone.setRelation(getRelation());
		clone.setDueDate(getDueDate());
		clone.setCompletionDate(getCompletionDate());
		clone.setState(getState());
		clone.setPriority(getPriority());
		clone.setAssigneeUserId(getAssigneeUserId());
		clone.setAssigneeUserName(getAssigneeUserName());
		clone.setRoleId(getRoleId());

		return clone;
	}

	public int compareTo(WorkflowTask workflowTask) {
		long pk = workflowTask.getPrimaryKey();

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

		WorkflowTask workflowTask = null;

		try {
			workflowTask = (WorkflowTask)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = workflowTask.getPrimaryKey();

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

		sb.append("{workflowTaskId=");
		sb.append(getWorkflowTaskId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", friendlyId=");
		sb.append(getFriendlyId());
		sb.append(", workflowDefinitionId=");
		sb.append(getWorkflowDefinitionId());
		sb.append(", workflowInstanceId=");
		sb.append(getWorkflowInstanceId());
		sb.append(", metaName=");
		sb.append(getMetaName());
		sb.append(", relation=");
		sb.append(getRelation());
		sb.append(", dueDate=");
		sb.append(getDueDate());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append(", state=");
		sb.append(getState());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append(", assigneeUserId=");
		sb.append(getAssigneeUserId());
		sb.append(", assigneeUserName=");
		sb.append(getAssigneeUserName());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.edoras.model.WorkflowTask");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>workflowTaskId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowTaskId());
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
			"<column><column-name>friendlyId</column-name><column-value><![CDATA[");
		sb.append(getFriendlyId());
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
			"<column><column-name>metaName</column-name><column-value><![CDATA[");
		sb.append(getMetaName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>relation</column-name><column-value><![CDATA[");
		sb.append(getRelation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dueDate</column-name><column-value><![CDATA[");
		sb.append(getDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>state</column-name><column-value><![CDATA[");
		sb.append(getState());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeUserId</column-name><column-value><![CDATA[");
		sb.append(getAssigneeUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeUserName</column-name><column-value><![CDATA[");
		sb.append(getAssigneeUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _workflowTaskId;
	private long _companyId;
	private Date _createDate;
	private String _friendlyId;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private String _metaName;
	private String _relation;
	private Date _dueDate;
	private Date _completionDate;
	private int _state;
	private int _priority;
	private long _assigneeUserId;
	private String _assigneeUserUuid;
	private String _assigneeUserName;
	private long _roleId;
	private transient ExpandoBridge _expandoBridge;
}