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
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.WorkflowInstanceSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WorkflowInstanceModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceModelImpl extends BaseModelImpl<WorkflowInstance> {
	public static final String TABLE_NAME = "Edoras_WorkflowInstance";
	public static final Object[][] TABLE_COLUMNS = {
			{ "workflowInstanceId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "setupId", new Integer(Types.VARCHAR) },
			{ "friendlyId", new Integer(Types.VARCHAR) },
			{ "workflowDefinitionId", new Integer(Types.BIGINT) },
			{ "parentWorkflowInstanceId", new Integer(Types.BIGINT) },
			{ "className", new Integer(Types.VARCHAR) },
			{ "classPK", new Integer(Types.BIGINT) },
			{ "attributes", new Integer(Types.VARCHAR) },
			{ "nestedWorkflowDefinitionIds", new Integer(Types.VARCHAR) },
			{ "nestedWorkflowDefinitionVersions", new Integer(Types.VARCHAR) },
			{ "nestedRelatedElements", new Integer(Types.VARCHAR) },
			{ "currentElementName", new Integer(Types.VARCHAR) },
			{ "relatedElementName", new Integer(Types.VARCHAR) },
			{ "finished", new Integer(Types.BOOLEAN) },
			{ "finishedDated", new Integer(Types.TIMESTAMP) },
			{ "active_", new Integer(Types.BOOLEAN) }
		};
	public static final String TABLE_SQL_CREATE = "create table Edoras_WorkflowInstance (workflowInstanceId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,setupId VARCHAR(75) null,friendlyId VARCHAR(75) null,workflowDefinitionId LONG,parentWorkflowInstanceId LONG,className VARCHAR(75) null,classPK LONG,attributes VARCHAR(75) null,nestedWorkflowDefinitionIds VARCHAR(75) null,nestedWorkflowDefinitionVersions VARCHAR(75) null,nestedRelatedElements VARCHAR(75) null,currentElementName VARCHAR(75) null,relatedElementName VARCHAR(75) null,finished BOOLEAN,finishedDated DATE null,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Edoras_WorkflowInstance";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowInstance"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.edoras.model.WorkflowInstance"),
			true);

	public static WorkflowInstance toModel(WorkflowInstanceSoap soapModel) {
		WorkflowInstance model = new WorkflowInstanceImpl();

		model.setWorkflowInstanceId(soapModel.getWorkflowInstanceId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setSetupId(soapModel.getSetupId());
		model.setFriendlyId(soapModel.getFriendlyId());
		model.setWorkflowDefinitionId(soapModel.getWorkflowDefinitionId());
		model.setParentWorkflowInstanceId(soapModel.getParentWorkflowInstanceId());
		model.setClassName(soapModel.getClassName());
		model.setClassPK(soapModel.getClassPK());
		model.setAttributes(soapModel.getAttributes());
		model.setNestedWorkflowDefinitionIds(soapModel.getNestedWorkflowDefinitionIds());
		model.setNestedWorkflowDefinitionVersions(soapModel.getNestedWorkflowDefinitionVersions());
		model.setNestedRelatedElements(soapModel.getNestedRelatedElements());
		model.setCurrentElementName(soapModel.getCurrentElementName());
		model.setRelatedElementName(soapModel.getRelatedElementName());
		model.setFinished(soapModel.getFinished());
		model.setFinishedDated(soapModel.getFinishedDated());
		model.setActive(soapModel.getActive());

		return model;
	}

	public static List<WorkflowInstance> toModels(
		WorkflowInstanceSoap[] soapModels) {
		List<WorkflowInstance> models = new ArrayList<WorkflowInstance>(soapModels.length);

		for (WorkflowInstanceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.edoras.model.WorkflowInstance"));

	public WorkflowInstanceModelImpl() {
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

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = companyId;
		}
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSetupId() {
		return GetterUtil.getString(_setupId);
	}

	public void setSetupId(String setupId) {
		_setupId = setupId;
	}

	public String getFriendlyId() {
		return GetterUtil.getString(_friendlyId);
	}

	public void setFriendlyId(String friendlyId) {
		_friendlyId = friendlyId;

		if (_originalFriendlyId == null) {
			_originalFriendlyId = friendlyId;
		}
	}

	public String getOriginalFriendlyId() {
		return GetterUtil.getString(_originalFriendlyId);
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

	public String getClassName() {
		return GetterUtil.getString(_className);
	}

	public void setClassName(String className) {
		_className = className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getAttributes() {
		return GetterUtil.getString(_attributes);
	}

	public void setAttributes(String attributes) {
		_attributes = attributes;
	}

	public String getNestedWorkflowDefinitionIds() {
		return GetterUtil.getString(_nestedWorkflowDefinitionIds);
	}

	public void setNestedWorkflowDefinitionIds(
		String nestedWorkflowDefinitionIds) {
		_nestedWorkflowDefinitionIds = nestedWorkflowDefinitionIds;
	}

	public String getNestedWorkflowDefinitionVersions() {
		return GetterUtil.getString(_nestedWorkflowDefinitionVersions);
	}

	public void setNestedWorkflowDefinitionVersions(
		String nestedWorkflowDefinitionVersions) {
		_nestedWorkflowDefinitionVersions = nestedWorkflowDefinitionVersions;
	}

	public String getNestedRelatedElements() {
		return GetterUtil.getString(_nestedRelatedElements);
	}

	public void setNestedRelatedElements(String nestedRelatedElements) {
		_nestedRelatedElements = nestedRelatedElements;
	}

	public String getCurrentElementName() {
		return GetterUtil.getString(_currentElementName);
	}

	public void setCurrentElementName(String currentElementName) {
		_currentElementName = currentElementName;
	}

	public String getRelatedElementName() {
		return GetterUtil.getString(_relatedElementName);
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

	public WorkflowInstance toEscapedModel() {
		if (isEscapedModel()) {
			return (WorkflowInstance)this;
		}
		else {
			WorkflowInstance model = new WorkflowInstanceImpl();

			model.setNew(isNew());
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
			model.setClassName(HtmlUtil.escape(getClassName()));
			model.setClassPK(getClassPK());
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

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(WorkflowInstance.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		WorkflowInstanceImpl clone = new WorkflowInstanceImpl();

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
		clone.setClassName(getClassName());
		clone.setClassPK(getClassPK());
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

		WorkflowInstance workflowInstance = null;

		try {
			workflowInstance = (WorkflowInstance)obj;
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
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", classPK=");
		sb.append(getClassPK());
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
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
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
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _setupId;
	private String _friendlyId;
	private String _originalFriendlyId;
	private long _workflowDefinitionId;
	private long _parentWorkflowInstanceId;
	private String _className;
	private long _classPK;
	private String _attributes;
	private String _nestedWorkflowDefinitionIds;
	private String _nestedWorkflowDefinitionVersions;
	private String _nestedRelatedElements;
	private String _currentElementName;
	private String _relatedElementName;
	private boolean _finished;
	private Date _finishedDated;
	private boolean _active;
	private transient ExpandoBridge _expandoBridge;
}