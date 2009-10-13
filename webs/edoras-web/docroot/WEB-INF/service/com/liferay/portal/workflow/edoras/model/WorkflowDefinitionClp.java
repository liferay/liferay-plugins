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
 * <a href="WorkflowDefinitionClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionClp extends BaseModelImpl<WorkflowDefinition>
	implements WorkflowDefinition {
	public WorkflowDefinitionClp() {
	}

	public long getPrimaryKey() {
		return _workflowDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowDefinitionId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_workflowDefinitionId);
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getDesignerVersion() {
		return _designerVersion;
	}

	public void setDesignerVersion(String designerVersion) {
		_designerVersion = designerVersion;
	}

	public String getModelXml() {
		return _modelXml;
	}

	public void setModelXml(String modelXml) {
		_modelXml = modelXml;
	}

	public String getGraphicalXml() {
		return _graphicalXml;
	}

	public void setGraphicalXml(String graphicalXml) {
		_graphicalXml = graphicalXml;
	}

	public boolean getPersistent() {
		return _persistent;
	}

	public boolean isPersistent() {
		return _persistent;
	}

	public void setPersistent(boolean persistent) {
		_persistent = persistent;
	}

	public java.lang.String getGraphicalProcessModelAsXML() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getModelDesignerVersion() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getProcessModelAsXML() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getProcessModelId() {
		throw new UnsupportedOperationException();
	}

	public int getProcessModelVersion() {
		throw new UnsupportedOperationException();
	}

	public java.lang.Long getTenantId() {
		throw new UnsupportedOperationException();
	}

	public WorkflowDefinition toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WorkflowDefinition model = new WorkflowDefinitionClp();

			model.setEscapedModel(true);

			model.setWorkflowDefinitionId(getWorkflowDefinitionId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setName(HtmlUtil.escape(getName()));
			model.setVersion(getVersion());
			model.setDesignerVersion(HtmlUtil.escape(getDesignerVersion()));
			model.setModelXml(HtmlUtil.escape(getModelXml()));
			model.setGraphicalXml(HtmlUtil.escape(getGraphicalXml()));
			model.setPersistent(getPersistent());

			model = (WorkflowDefinition)Proxy.newProxyInstance(WorkflowDefinition.class.getClassLoader(),
					new Class[] { WorkflowDefinition.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		WorkflowDefinitionClp clone = new WorkflowDefinitionClp();

		clone.setWorkflowDefinitionId(getWorkflowDefinitionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setVersion(getVersion());
		clone.setDesignerVersion(getDesignerVersion());
		clone.setModelXml(getModelXml());
		clone.setGraphicalXml(getGraphicalXml());
		clone.setPersistent(getPersistent());

		return clone;
	}

	public int compareTo(WorkflowDefinition workflowDefinition) {
		int value = 0;

		value = getName().compareTo(workflowDefinition.getName());

		if (value != 0) {
			return value;
		}

		if (getVersion() < workflowDefinition.getVersion()) {
			value = -1;
		}
		else if (getVersion() > workflowDefinition.getVersion()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WorkflowDefinitionClp workflowDefinition = null;

		try {
			workflowDefinition = (WorkflowDefinitionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = workflowDefinition.getPrimaryKey();

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

		sb.append("{workflowDefinitionId=");
		sb.append(getWorkflowDefinitionId());
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
		sb.append(", name=");
		sb.append(getName());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", designerVersion=");
		sb.append(getDesignerVersion());
		sb.append(", modelXml=");
		sb.append(getModelXml());
		sb.append(", graphicalXml=");
		sb.append(getGraphicalXml());
		sb.append(", persistent=");
		sb.append(getPersistent());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.edoras.model.WorkflowDefinition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>workflowDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowDefinitionId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>designerVersion</column-name><column-value><![CDATA[");
		sb.append(getDesignerVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modelXml</column-name><column-value><![CDATA[");
		sb.append(getModelXml());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>graphicalXml</column-name><column-value><![CDATA[");
		sb.append(getGraphicalXml());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>persistent</column-name><column-value><![CDATA[");
		sb.append(getPersistent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _workflowDefinitionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private int _version;
	private String _designerVersion;
	private String _modelXml;
	private String _graphicalXml;
	private boolean _persistent;
}