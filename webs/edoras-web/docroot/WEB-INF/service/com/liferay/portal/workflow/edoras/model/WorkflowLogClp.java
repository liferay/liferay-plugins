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

package com.liferay.portal.workflow.edoras.model;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="WorkflowLogClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogClp extends BaseModelImpl<WorkflowLog>
	implements WorkflowLog {
	public WorkflowLogClp() {
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
		return _userName;
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
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getActivityName() {
		return _activityName;
	}

	public void setActivityName(String activityName) {
		_activityName = activityName;
	}

	public String getOldState() {
		return _oldState;
	}

	public void setOldState(String oldState) {
		_oldState = oldState;
	}

	public String getNewState() {
		return _newState;
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
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowTask getTaskInstance() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance getWorkflowInstance() {
		throw new UnsupportedOperationException();
	}

	public WorkflowLog toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WorkflowLog model = new WorkflowLogClp();

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

	public Object clone() {
		WorkflowLogClp clone = new WorkflowLogClp();

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

		WorkflowLogClp workflowLog = null;

		try {
			workflowLog = (WorkflowLogClp)obj;
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
}