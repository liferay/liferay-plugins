/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.KaleoLogSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoLogModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoLog table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLogImpl
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoLog
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoLogModel
 * @generated
 */
public class KaleoLogModelImpl extends BaseModelImpl<KaleoLog> {
	public static final String TABLE_NAME = "Kaleo_KaleoLog";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoLogId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "kaleoInstanceId", new Integer(Types.BIGINT) },
			{ "kaleoInstanceTokenId", new Integer(Types.BIGINT) },
			{ "kaleoTaskInstanceTokenId", new Integer(Types.BIGINT) },
			{ "kaleoNodeId", new Integer(Types.BIGINT) },
			{ "kaleoNodeName", new Integer(Types.VARCHAR) },
			{ "terminalKaleoNode", new Integer(Types.BOOLEAN) },
			{ "kaleoActionId", new Integer(Types.BIGINT) },
			{ "kaleoActionName", new Integer(Types.VARCHAR) },
			{ "kaleoActionDescription", new Integer(Types.VARCHAR) },
			{ "previousKaleoNodeId", new Integer(Types.BIGINT) },
			{ "previousKaleoNodeName", new Integer(Types.VARCHAR) },
			{ "previousAssigneeClassName", new Integer(Types.VARCHAR) },
			{ "previousAssigneeClassPK", new Integer(Types.BIGINT) },
			{ "currentAssigneeClassName", new Integer(Types.VARCHAR) },
			{ "currentAssigneeClassPK", new Integer(Types.BIGINT) },
			{ "type_", new Integer(Types.VARCHAR) },
			{ "comment", new Integer(Types.VARCHAR) },
			{ "startDate", new Integer(Types.TIMESTAMP) },
			{ "endDate", new Integer(Types.TIMESTAMP) },
			{ "duration", new Integer(Types.BIGINT) },
			{ "context", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table Kaleo_KaleoLog (kaleoLogId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,kaleoInstanceId LONG,kaleoInstanceTokenId LONG,kaleoTaskInstanceTokenId LONG,kaleoNodeId LONG,kaleoNodeName VARCHAR(75) null,terminalKaleoNode BOOLEAN,kaleoActionId LONG,kaleoActionName VARCHAR(75) null,kaleoActionDescription VARCHAR(75) null,previousKaleoNodeId LONG,previousKaleoNodeName VARCHAR(75) null,previousAssigneeClassName VARCHAR(75) null,previousAssigneeClassPK LONG,currentAssigneeClassName VARCHAR(75) null,currentAssigneeClassPK LONG,type_ VARCHAR(75) null,comment VARCHAR(75) null,startDate DATE null,endDate DATE null,duration LONG,context VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Kaleo_KaleoLog";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoLog.kaleoLogId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Kaleo_KaleoLog.kaleoLogId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoLog"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoLog"),
			true);

	public static KaleoLog toModel(KaleoLogSoap soapModel) {
		KaleoLog model = new KaleoLogImpl();

		model.setKaleoLogId(soapModel.getKaleoLogId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKaleoInstanceId(soapModel.getKaleoInstanceId());
		model.setKaleoInstanceTokenId(soapModel.getKaleoInstanceTokenId());
		model.setKaleoTaskInstanceTokenId(soapModel.getKaleoTaskInstanceTokenId());
		model.setKaleoNodeId(soapModel.getKaleoNodeId());
		model.setKaleoNodeName(soapModel.getKaleoNodeName());
		model.setTerminalKaleoNode(soapModel.getTerminalKaleoNode());
		model.setKaleoActionId(soapModel.getKaleoActionId());
		model.setKaleoActionName(soapModel.getKaleoActionName());
		model.setKaleoActionDescription(soapModel.getKaleoActionDescription());
		model.setPreviousKaleoNodeId(soapModel.getPreviousKaleoNodeId());
		model.setPreviousKaleoNodeName(soapModel.getPreviousKaleoNodeName());
		model.setPreviousAssigneeClassName(soapModel.getPreviousAssigneeClassName());
		model.setPreviousAssigneeClassPK(soapModel.getPreviousAssigneeClassPK());
		model.setCurrentAssigneeClassName(soapModel.getCurrentAssigneeClassName());
		model.setCurrentAssigneeClassPK(soapModel.getCurrentAssigneeClassPK());
		model.setType(soapModel.getType());
		model.setComment(soapModel.getComment());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setDuration(soapModel.getDuration());
		model.setContext(soapModel.getContext());

		return model;
	}

	public static List<KaleoLog> toModels(KaleoLogSoap[] soapModels) {
		List<KaleoLog> models = new ArrayList<KaleoLog>(soapModels.length);

		for (KaleoLogSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoLog"));

	public KaleoLogModelImpl() {
	}

	public long getPrimaryKey() {
		return _kaleoLogId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoLogId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoLogId);
	}

	public long getKaleoLogId() {
		return _kaleoLogId;
	}

	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLogId = kaleoLogId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
	}

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
	}

	public String getKaleoNodeName() {
		return GetterUtil.getString(_kaleoNodeName);
	}

	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	public boolean getTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	public boolean isTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_terminalKaleoNode = terminalKaleoNode;
	}

	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;
	}

	public String getKaleoActionName() {
		return GetterUtil.getString(_kaleoActionName);
	}

	public void setKaleoActionName(String kaleoActionName) {
		_kaleoActionName = kaleoActionName;
	}

	public String getKaleoActionDescription() {
		return GetterUtil.getString(_kaleoActionDescription);
	}

	public void setKaleoActionDescription(String kaleoActionDescription) {
		_kaleoActionDescription = kaleoActionDescription;
	}

	public long getPreviousKaleoNodeId() {
		return _previousKaleoNodeId;
	}

	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_previousKaleoNodeId = previousKaleoNodeId;
	}

	public String getPreviousKaleoNodeName() {
		return GetterUtil.getString(_previousKaleoNodeName);
	}

	public void setPreviousKaleoNodeName(String previousKaleoNodeName) {
		_previousKaleoNodeName = previousKaleoNodeName;
	}

	public String getPreviousAssigneeClassName() {
		return GetterUtil.getString(_previousAssigneeClassName);
	}

	public void setPreviousAssigneeClassName(String previousAssigneeClassName) {
		_previousAssigneeClassName = previousAssigneeClassName;
	}

	public long getPreviousAssigneeClassPK() {
		return _previousAssigneeClassPK;
	}

	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_previousAssigneeClassPK = previousAssigneeClassPK;
	}

	public String getCurrentAssigneeClassName() {
		return GetterUtil.getString(_currentAssigneeClassName);
	}

	public void setCurrentAssigneeClassName(String currentAssigneeClassName) {
		_currentAssigneeClassName = currentAssigneeClassName;
	}

	public long getCurrentAssigneeClassPK() {
		return _currentAssigneeClassPK;
	}

	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_currentAssigneeClassPK = currentAssigneeClassPK;
	}

	public String getType() {
		return GetterUtil.getString(_type);
	}

	public void setType(String type) {
		_type = type;
	}

	public String getComment() {
		return GetterUtil.getString(_comment);
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getDuration() {
		return _duration;
	}

	public void setDuration(long duration) {
		_duration = duration;
	}

	public String getContext() {
		return GetterUtil.getString(_context);
	}

	public void setContext(String context) {
		_context = context;
	}

	public KaleoLog toEscapedModel() {
		if (isEscapedModel()) {
			return (KaleoLog)this;
		}
		else {
			KaleoLog model = new KaleoLogImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setKaleoLogId(getKaleoLogId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setKaleoInstanceId(getKaleoInstanceId());
			model.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
			model.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
			model.setKaleoNodeId(getKaleoNodeId());
			model.setKaleoNodeName(HtmlUtil.escape(getKaleoNodeName()));
			model.setTerminalKaleoNode(getTerminalKaleoNode());
			model.setKaleoActionId(getKaleoActionId());
			model.setKaleoActionName(HtmlUtil.escape(getKaleoActionName()));
			model.setKaleoActionDescription(HtmlUtil.escape(
					getKaleoActionDescription()));
			model.setPreviousKaleoNodeId(getPreviousKaleoNodeId());
			model.setPreviousKaleoNodeName(HtmlUtil.escape(
					getPreviousKaleoNodeName()));
			model.setPreviousAssigneeClassName(HtmlUtil.escape(
					getPreviousAssigneeClassName()));
			model.setPreviousAssigneeClassPK(getPreviousAssigneeClassPK());
			model.setCurrentAssigneeClassName(HtmlUtil.escape(
					getCurrentAssigneeClassName()));
			model.setCurrentAssigneeClassPK(getCurrentAssigneeClassPK());
			model.setType(HtmlUtil.escape(getType()));
			model.setComment(HtmlUtil.escape(getComment()));
			model.setStartDate(getStartDate());
			model.setEndDate(getEndDate());
			model.setDuration(getDuration());
			model.setContext(HtmlUtil.escape(getContext()));

			model = (KaleoLog)Proxy.newProxyInstance(KaleoLog.class.getClassLoader(),
					new Class[] { KaleoLog.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KaleoLog.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		KaleoLogImpl clone = new KaleoLogImpl();

		clone.setKaleoLogId(getKaleoLogId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setKaleoNodeId(getKaleoNodeId());
		clone.setKaleoNodeName(getKaleoNodeName());
		clone.setTerminalKaleoNode(getTerminalKaleoNode());
		clone.setKaleoActionId(getKaleoActionId());
		clone.setKaleoActionName(getKaleoActionName());
		clone.setKaleoActionDescription(getKaleoActionDescription());
		clone.setPreviousKaleoNodeId(getPreviousKaleoNodeId());
		clone.setPreviousKaleoNodeName(getPreviousKaleoNodeName());
		clone.setPreviousAssigneeClassName(getPreviousAssigneeClassName());
		clone.setPreviousAssigneeClassPK(getPreviousAssigneeClassPK());
		clone.setCurrentAssigneeClassName(getCurrentAssigneeClassName());
		clone.setCurrentAssigneeClassPK(getCurrentAssigneeClassPK());
		clone.setType(getType());
		clone.setComment(getComment());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setDuration(getDuration());
		clone.setContext(getContext());

		return clone;
	}

	public int compareTo(KaleoLog kaleoLog) {
		int value = 0;

		if (getKaleoLogId() < kaleoLog.getKaleoLogId()) {
			value = -1;
		}
		else if (getKaleoLogId() > kaleoLog.getKaleoLogId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KaleoLog kaleoLog = null;

		try {
			kaleoLog = (KaleoLog)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoLog.getPrimaryKey();

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
		StringBundler sb = new StringBundler(55);

		sb.append("{kaleoLogId=");
		sb.append(getKaleoLogId());
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
		sb.append(", kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
		sb.append(", kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", kaleoNodeName=");
		sb.append(getKaleoNodeName());
		sb.append(", terminalKaleoNode=");
		sb.append(getTerminalKaleoNode());
		sb.append(", kaleoActionId=");
		sb.append(getKaleoActionId());
		sb.append(", kaleoActionName=");
		sb.append(getKaleoActionName());
		sb.append(", kaleoActionDescription=");
		sb.append(getKaleoActionDescription());
		sb.append(", previousKaleoNodeId=");
		sb.append(getPreviousKaleoNodeId());
		sb.append(", previousKaleoNodeName=");
		sb.append(getPreviousKaleoNodeName());
		sb.append(", previousAssigneeClassName=");
		sb.append(getPreviousAssigneeClassName());
		sb.append(", previousAssigneeClassPK=");
		sb.append(getPreviousAssigneeClassPK());
		sb.append(", currentAssigneeClassName=");
		sb.append(getCurrentAssigneeClassName());
		sb.append(", currentAssigneeClassPK=");
		sb.append(getCurrentAssigneeClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", duration=");
		sb.append(getDuration());
		sb.append(", context=");
		sb.append(getContext());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(85);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoLogId</column-name><column-value><![CDATA[");
		sb.append(getKaleoLogId());
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
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>terminalKaleoNode</column-name><column-value><![CDATA[");
		sb.append(getTerminalKaleoNode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionName</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionDescription</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getPreviousKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getPreviousKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousAssigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getPreviousAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousAssigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getPreviousAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentAssigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getCurrentAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentAssigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getCurrentAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>duration</column-name><column-value><![CDATA[");
		sb.append(getDuration());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>context</column-name><column-value><![CDATA[");
		sb.append(getContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoLogId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private long _kaleoNodeId;
	private String _kaleoNodeName;
	private boolean _terminalKaleoNode;
	private long _kaleoActionId;
	private String _kaleoActionName;
	private String _kaleoActionDescription;
	private long _previousKaleoNodeId;
	private String _previousKaleoNodeName;
	private String _previousAssigneeClassName;
	private long _previousAssigneeClassPK;
	private String _currentAssigneeClassName;
	private long _currentAssigneeClassPK;
	private String _type;
	private String _comment;
	private Date _startDate;
	private Date _endDate;
	private long _duration;
	private String _context;
	private transient ExpandoBridge _expandoBridge;
}