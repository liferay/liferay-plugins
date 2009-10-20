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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="WorkflowInstanceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceClp extends BaseModelImpl<WorkflowInstance>
	implements WorkflowInstance {
	public WorkflowInstanceClp() {
	}

	public long getPrimaryKey() {
		return _workflowInstanceId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowInstanceId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_workflowInstanceId);
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSetupId() {
		return _setupId;
	}

	public void setSetupId(String setupId) {
		_setupId = setupId;
	}

	public String getFriendlyId() {
		return _friendlyId;
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

	public long getParentWorkflowInstanceId() {
		return _parentWorkflowInstanceId;
	}

	public void setParentWorkflowInstanceId(long parentWorkflowInstanceId) {
		_parentWorkflowInstanceId = parentWorkflowInstanceId;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		_workflowDefinitionName = workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		_workflowDefinitionVersion = workflowDefinitionVersion;
	}

	public String getRelationClassName() {
		return _relationClassName;
	}

	public void setRelationClassName(String relationClassName) {
		_relationClassName = relationClassName;
	}

	public long getRelationClassPK() {
		return _relationClassPK;
	}

	public void setRelationClassPK(long relationClassPK) {
		_relationClassPK = relationClassPK;
	}

	public String getAttributes() {
		return _attributes;
	}

	public void setAttributes(String attributes) {
		_attributes = attributes;
	}

	public String getNestedWorkflowDefinitionIds() {
		return _nestedWorkflowDefinitionIds;
	}

	public void setNestedWorkflowDefinitionIds(
		String nestedWorkflowDefinitionIds) {
		_nestedWorkflowDefinitionIds = nestedWorkflowDefinitionIds;
	}

	public String getNestedWorkflowDefinitionVersions() {
		return _nestedWorkflowDefinitionVersions;
	}

	public void setNestedWorkflowDefinitionVersions(
		String nestedWorkflowDefinitionVersions) {
		_nestedWorkflowDefinitionVersions = nestedWorkflowDefinitionVersions;
	}

	public String getNestedRelatedElements() {
		return _nestedRelatedElements;
	}

	public void setNestedRelatedElements(String nestedRelatedElements) {
		_nestedRelatedElements = nestedRelatedElements;
	}

	public String getCurrentElementName() {
		return _currentElementName;
	}

	public void setCurrentElementName(String currentElementName) {
		_currentElementName = currentElementName;
	}

	public String getRelatedElementName() {
		return _relatedElementName;
	}

	public void setRelatedElementName(String relatedElementName) {
		_relatedElementName = relatedElementName;
	}

	public boolean getFinished() {
		return _finished;
	}

	public boolean isFinished() {
		return _finished;
	}

	public void setFinished(boolean finished) {
		_finished = finished;
	}

	public Date getFinishedDated() {
		return _finishedDated;
	}

	public void setFinishedDated(Date finishedDated) {
		_finishedDated = finishedDated;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> getChildren() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance getParent() {
		throw new UnsupportedOperationException();
	}

	public WorkflowInstance toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WorkflowInstance model = new WorkflowInstanceClp();

			model.setEscapedModel(true);

			model.setWorkflowInstanceId(getWorkflowInstanceId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setSetupId(HtmlUtil.escape(getSetupId()));
			model.setFriendlyId(HtmlUtil.escape(getFriendlyId()));
			model.setWorkflowDefinitionId(getWorkflowDefinitionId());
			model.setParentWorkflowInstanceId(getParentWorkflowInstanceId());
			model.setWorkflowDefinitionName(HtmlUtil.escape(
					getWorkflowDefinitionName()));
			model.setWorkflowDefinitionVersion(getWorkflowDefinitionVersion());
			model.setRelationClassName(HtmlUtil.escape(getRelationClassName()));
			model.setRelationClassPK(getRelationClassPK());
			model.setAttributes(HtmlUtil.escape(getAttributes()));
			model.setNestedWorkflowDefinitionIds(HtmlUtil.escape(
					getNestedWorkflowDefinitionIds()));
			model.setNestedWorkflowDefinitionVersions(HtmlUtil.escape(
					getNestedWorkflowDefinitionVersions()));
			model.setNestedRelatedElements(HtmlUtil.escape(
					getNestedRelatedElements()));
			model.setCurrentElementName(HtmlUtil.escape(getCurrentElementName()));
			model.setRelatedElementName(HtmlUtil.escape(getRelatedElementName()));
			model.setFinished(getFinished());
			model.setFinishedDated(getFinishedDated());
			model.setActive(getActive());

			model = (WorkflowInstance)Proxy.newProxyInstance(WorkflowInstance.class.getClassLoader(),
					new Class[] { WorkflowInstance.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		WorkflowInstanceClp clone = new WorkflowInstanceClp();

		clone.setWorkflowInstanceId(getWorkflowInstanceId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setSetupId(getSetupId());
		clone.setFriendlyId(getFriendlyId());
		clone.setWorkflowDefinitionId(getWorkflowDefinitionId());
		clone.setParentWorkflowInstanceId(getParentWorkflowInstanceId());
		clone.setWorkflowDefinitionName(getWorkflowDefinitionName());
		clone.setWorkflowDefinitionVersion(getWorkflowDefinitionVersion());
		clone.setRelationClassName(getRelationClassName());
		clone.setRelationClassPK(getRelationClassPK());
		clone.setAttributes(getAttributes());
		clone.setNestedWorkflowDefinitionIds(getNestedWorkflowDefinitionIds());
		clone.setNestedWorkflowDefinitionVersions(getNestedWorkflowDefinitionVersions());
		clone.setNestedRelatedElements(getNestedRelatedElements());
		clone.setCurrentElementName(getCurrentElementName());
		clone.setRelatedElementName(getRelatedElementName());
		clone.setFinished(getFinished());
		clone.setFinishedDated(getFinishedDated());
		clone.setActive(getActive());

		return clone;
	}

	public int compareTo(WorkflowInstance workflowInstance) {
		long pk = workflowInstance.getPrimaryKey();

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

		WorkflowInstanceClp workflowInstance = null;

		try {
			workflowInstance = (WorkflowInstanceClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = workflowInstance.getPrimaryKey();

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

		sb.append("{workflowInstanceId=");
		sb.append(getWorkflowInstanceId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", setupId=");
		sb.append(getSetupId());
		sb.append(", friendlyId=");
		sb.append(getFriendlyId());
		sb.append(", workflowDefinitionId=");
		sb.append(getWorkflowDefinitionId());
		sb.append(", parentWorkflowInstanceId=");
		sb.append(getParentWorkflowInstanceId());
		sb.append(", workflowDefinitionName=");
		sb.append(getWorkflowDefinitionName());
		sb.append(", workflowDefinitionVersion=");
		sb.append(getWorkflowDefinitionVersion());
		sb.append(", relationClassName=");
		sb.append(getRelationClassName());
		sb.append(", relationClassPK=");
		sb.append(getRelationClassPK());
		sb.append(", attributes=");
		sb.append(getAttributes());
		sb.append(", nestedWorkflowDefinitionIds=");
		sb.append(getNestedWorkflowDefinitionIds());
		sb.append(", nestedWorkflowDefinitionVersions=");
		sb.append(getNestedWorkflowDefinitionVersions());
		sb.append(", nestedRelatedElements=");
		sb.append(getNestedRelatedElements());
		sb.append(", currentElementName=");
		sb.append(getCurrentElementName());
		sb.append(", relatedElementName=");
		sb.append(getRelatedElementName());
		sb.append(", finished=");
		sb.append(getFinished());
		sb.append(", finishedDated=");
		sb.append(getFinishedDated());
		sb.append(", active=");
		sb.append(getActive());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.edoras.model.WorkflowInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>workflowInstanceId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowInstanceId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>setupId</column-name><column-value><![CDATA[");
		sb.append(getSetupId());
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
			"<column><column-name>parentWorkflowInstanceId</column-name><column-value><![CDATA[");
		sb.append(getParentWorkflowInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowDefinitionName</column-name><column-value><![CDATA[");
		sb.append(getWorkflowDefinitionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowDefinitionVersion</column-name><column-value><![CDATA[");
		sb.append(getWorkflowDefinitionVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>relationClassName</column-name><column-value><![CDATA[");
		sb.append(getRelationClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>relationClassPK</column-name><column-value><![CDATA[");
		sb.append(getRelationClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>attributes</column-name><column-value><![CDATA[");
		sb.append(getAttributes());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nestedWorkflowDefinitionIds</column-name><column-value><![CDATA[");
		sb.append(getNestedWorkflowDefinitionIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nestedWorkflowDefinitionVersions</column-name><column-value><![CDATA[");
		sb.append(getNestedWorkflowDefinitionVersions());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nestedRelatedElements</column-name><column-value><![CDATA[");
		sb.append(getNestedRelatedElements());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentElementName</column-name><column-value><![CDATA[");
		sb.append(getCurrentElementName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>relatedElementName</column-name><column-value><![CDATA[");
		sb.append(getRelatedElementName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>finished</column-name><column-value><![CDATA[");
		sb.append(getFinished());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>finishedDated</column-name><column-value><![CDATA[");
		sb.append(getFinishedDated());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _workflowInstanceId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _setupId;
	private String _friendlyId;
	private long _workflowDefinitionId;
	private long _parentWorkflowInstanceId;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private String _relationClassName;
	private long _relationClassPK;
	private String _attributes;
	private String _nestedWorkflowDefinitionIds;
	private String _nestedWorkflowDefinitionVersions;
	private String _nestedRelatedElements;
	private String _currentElementName;
	private String _relatedElementName;
	private boolean _finished;
	private Date _finishedDated;
	private boolean _active;
}