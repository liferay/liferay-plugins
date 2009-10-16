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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;
import com.liferay.portal.workflow.edoras.model.WorkflowLogSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WorkflowLogModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogModelImpl extends BaseModelImpl<WorkflowLog> {
	public static final String TABLE_NAME = "Edoras_WorkflowLog";
	public static final Object[][] TABLE_COLUMNS = {
			{ "workflowLogId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "workflowDefinitionId", new Integer(Types.BIGINT) },
			{ "workflowInstanceId", new Integer(Types.BIGINT) },
			{ "workflowTaskId", new Integer(Types.BIGINT) },
			{ "logEntityType", new Integer(Types.INTEGER) },
			{ "description", new Integer(Types.VARCHAR) },
			{ "activityName", new Integer(Types.VARCHAR) },
			{ "oldState", new Integer(Types.VARCHAR) },
			{ "newState", new Integer(Types.VARCHAR) },
			{ "type_", new Integer(Types.INTEGER) },
			{ "comment", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Edoras_WorkflowLog (workflowLogId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,workflowDefinitionId LONG,workflowInstanceId LONG,workflowTaskId LONG,logEntityType INTEGER,description VARCHAR(75) null,activityName VARCHAR(75) null,oldState VARCHAR(75) null,newState VARCHAR(75) null,type_ INTEGER,comment VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Edoras_WorkflowLog";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowLog"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowLog"),
			true);

	public static WorkflowLog toModel(WorkflowLogSoap soapModel) {
		WorkflowLog model = new WorkflowLogImpl();

		model.setWorkflowLogId(soapModel.getWorkflowLogId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setWorkflowDefinitionId(soapModel.getWorkflowDefinitionId());
		model.setWorkflowInstanceId(soapModel.getWorkflowInstanceId());
		model.setWorkflowTaskId(soapModel.getWorkflowTaskId());
		model.setLogEntityType(soapModel.getLogEntityType());
		model.setDescription(soapModel.getDescription());
		model.setActivityName(soapModel.getActivityName());
		model.setOldState(soapModel.getOldState());
		model.setNewState(soapModel.getNewState());
		model.setType(soapModel.getType());
		model.setComment(soapModel.getComment());

		return model;
	}

	public static List<WorkflowLog> toModels(WorkflowLogSoap[] soapModels) {
		List<WorkflowLog> models = new ArrayList<WorkflowLog>(soapModels.length);

		for (WorkflowLogSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.edoras.model.WorkflowLog"));

	public WorkflowLogModelImpl() {
	}

	public long getPrimaryKey() {
		return _workflowLogId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowLogId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_workflowLogId);
	}

	public long getWorkflowLogId() {
		return _workflowLogId;
	}

	public void setWorkflowLogId(long workflowLogId) {
		_workflowLogId = workflowLogId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return GetterUtil.getString(_userName);
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
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

	public long getWorkflowTaskId() {
		return _workflowTaskId;
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowTaskId = workflowTaskId;
	}

	public int getLogEntityType() {
		return _logEntityType;
	}

	public void setLogEntityType(int logEntityType) {
		_logEntityType = logEntityType;
	}

	public String getDescription() {
		return GetterUtil.getString(_description);
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getActivityName() {
		return GetterUtil.getString(_activityName);
	}

	public void setActivityName(String activityName) {
		_activityName = activityName;
	}

	public String getOldState() {
		return GetterUtil.getString(_oldState);
	}

	public void setOldState(String oldState) {
		_oldState = oldState;
	}

	public String getNewState() {
		return GetterUtil.getString(_newState);
	}

	public void setNewState(String newState) {
		_newState = newState;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getComment() {
		return GetterUtil.getString(_comment);
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public WorkflowLog toEscapedModel() {
		if (isEscapedModel()) {
			return (WorkflowLog)this;
		}
		else {
			WorkflowLog model = new WorkflowLogImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setWorkflowLogId(getWorkflowLogId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setWorkflowDefinitionId(getWorkflowDefinitionId());
			model.setWorkflowInstanceId(getWorkflowInstanceId());
			model.setWorkflowTaskId(getWorkflowTaskId());
			model.setLogEntityType(getLogEntityType());
			model.setDescription(HtmlUtil.escape(getDescription()));
			model.setActivityName(HtmlUtil.escape(getActivityName()));
			model.setOldState(HtmlUtil.escape(getOldState()));
			model.setNewState(HtmlUtil.escape(getNewState()));
			model.setType(getType());
			model.setComment(HtmlUtil.escape(getComment()));

			model = (WorkflowLog)Proxy.newProxyInstance(WorkflowLog.class.getClassLoader(),
					new Class[] { WorkflowLog.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(WorkflowLog.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		WorkflowLogImpl clone = new WorkflowLogImpl();

		clone.setWorkflowLogId(getWorkflowLogId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setWorkflowDefinitionId(getWorkflowDefinitionId());
		clone.setWorkflowInstanceId(getWorkflowInstanceId());
		clone.setWorkflowTaskId(getWorkflowTaskId());
		clone.setLogEntityType(getLogEntityType());
		clone.setDescription(getDescription());
		clone.setActivityName(getActivityName());
		clone.setOldState(getOldState());
		clone.setNewState(getNewState());
		clone.setType(getType());
		clone.setComment(getComment());

		return clone;
	}

	public int compareTo(WorkflowLog workflowLog) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), workflowLog.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WorkflowLog workflowLog = null;

		try {
			workflowLog = (WorkflowLog)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = workflowLog.getPrimaryKey();

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

		sb.append("{workflowLogId=");
		sb.append(getWorkflowLogId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", workflowDefinitionId=");
		sb.append(getWorkflowDefinitionId());
		sb.append(", workflowInstanceId=");
		sb.append(getWorkflowInstanceId());
		sb.append(", workflowTaskId=");
		sb.append(getWorkflowTaskId());
		sb.append(", logEntityType=");
		sb.append(getLogEntityType());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", activityName=");
		sb.append(getActivityName());
		sb.append(", oldState=");
		sb.append(getOldState());
		sb.append(", newState=");
		sb.append(getNewState());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.edoras.model.WorkflowLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>workflowLogId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowLogId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
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
			"<column><column-name>workflowTaskId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logEntityType</column-name><column-value><![CDATA[");
		sb.append(getLogEntityType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activityName</column-name><column-value><![CDATA[");
		sb.append(getActivityName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>oldState</column-name><column-value><![CDATA[");
		sb.append(getOldState());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>newState</column-name><column-value><![CDATA[");
		sb.append(getNewState());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _workflowLogId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private long _workflowTaskId;
	private int _logEntityType;
	private String _description;
	private String _activityName;
	private String _oldState;
	private String _newState;
	private int _type;
	private String _comment;
	private transient ExpandoBridge _expandoBridge;
}