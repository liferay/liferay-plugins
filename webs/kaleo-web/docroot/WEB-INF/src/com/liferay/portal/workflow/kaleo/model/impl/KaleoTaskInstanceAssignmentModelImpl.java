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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignmentSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoTaskInstanceAssignmentModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoTaskInstanceAssignment table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentImpl
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignmentModel
 * @generated
 */
public class KaleoTaskInstanceAssignmentModelImpl extends BaseModelImpl<KaleoTaskInstanceAssignment> {
	public static final String TABLE_NAME = "Kaleo_KaleoTaskInstanceAssignment";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoTaskInstanceAssignmentId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "kaleoTaskInstanceTokenId", new Integer(Types.BIGINT) },
			{ "kaleoTaskId", new Integer(Types.BIGINT) },
			{ "assigneeClassName", new Integer(Types.VARCHAR) },
			{ "assigneeClassPK", new Integer(Types.BIGINT) },
			{ "completionDate", new Integer(Types.TIMESTAMP) },
			{ "context", new Integer(Types.CLOB) }
		};
	public static final String TABLE_SQL_CREATE = "create table Kaleo_KaleoTaskInstanceAssignment (kaleoTaskInstanceAssignmentId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,kaleoTaskInstanceTokenId LONG,kaleoTaskId LONG,assigneeClassName VARCHAR(200) null,assigneeClassPK LONG,completionDate DATE null,context TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table Kaleo_KaleoTaskInstanceAssignment";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoTaskInstanceAssignment.kaleoTaskInstanceTokenId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Kaleo_KaleoTaskInstanceAssignment.kaleoTaskInstanceTokenId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment"),
			true);

	public static KaleoTaskInstanceAssignment toModel(
		KaleoTaskInstanceAssignmentSoap soapModel) {
		KaleoTaskInstanceAssignment model = new KaleoTaskInstanceAssignmentImpl();

		model.setKaleoTaskInstanceAssignmentId(soapModel.getKaleoTaskInstanceAssignmentId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKaleoTaskInstanceTokenId(soapModel.getKaleoTaskInstanceTokenId());
		model.setKaleoTaskId(soapModel.getKaleoTaskId());
		model.setAssigneeClassName(soapModel.getAssigneeClassName());
		model.setAssigneeClassPK(soapModel.getAssigneeClassPK());
		model.setCompletionDate(soapModel.getCompletionDate());
		model.setContext(soapModel.getContext());

		return model;
	}

	public static List<KaleoTaskInstanceAssignment> toModels(
		KaleoTaskInstanceAssignmentSoap[] soapModels) {
		List<KaleoTaskInstanceAssignment> models = new ArrayList<KaleoTaskInstanceAssignment>(soapModels.length);

		for (KaleoTaskInstanceAssignmentSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment"));

	public KaleoTaskInstanceAssignmentModelImpl() {
	}

	public long getPrimaryKey() {
		return _kaleoTaskInstanceAssignmentId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTaskInstanceAssignmentId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoTaskInstanceAssignmentId);
	}

	public long getKaleoTaskInstanceAssignmentId() {
		return _kaleoTaskInstanceAssignmentId;
	}

	public void setKaleoTaskInstanceAssignmentId(
		long kaleoTaskInstanceAssignmentId) {
		_kaleoTaskInstanceAssignmentId = kaleoTaskInstanceAssignmentId;
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

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;

		if (!_setOriginalKaleoTaskInstanceTokenId) {
			_setOriginalKaleoTaskInstanceTokenId = true;

			_originalKaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
		}
	}

	public long getOriginalKaleoTaskInstanceTokenId() {
		return _originalKaleoTaskInstanceTokenId;
	}

	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskId = kaleoTaskId;
	}

	public String getAssigneeClassName() {
		return GetterUtil.getString(_assigneeClassName);
	}

	public void setAssigneeClassName(String assigneeClassName) {
		_assigneeClassName = assigneeClassName;

		if (_originalAssigneeClassName == null) {
			_originalAssigneeClassName = assigneeClassName;
		}
	}

	public String getOriginalAssigneeClassName() {
		return GetterUtil.getString(_originalAssigneeClassName);
	}

	public long getAssigneeClassPK() {
		return _assigneeClassPK;
	}

	public void setAssigneeClassPK(long assigneeClassPK) {
		_assigneeClassPK = assigneeClassPK;

		if (!_setOriginalAssigneeClassPK) {
			_setOriginalAssigneeClassPK = true;

			_originalAssigneeClassPK = assigneeClassPK;
		}
	}

	public long getOriginalAssigneeClassPK() {
		return _originalAssigneeClassPK;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public String getContext() {
		return GetterUtil.getString(_context);
	}

	public void setContext(String context) {
		_context = context;
	}

	public KaleoTaskInstanceAssignment toEscapedModel() {
		if (isEscapedModel()) {
			return (KaleoTaskInstanceAssignment)this;
		}
		else {
			KaleoTaskInstanceAssignment model = new KaleoTaskInstanceAssignmentImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setKaleoTaskInstanceAssignmentId(getKaleoTaskInstanceAssignmentId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
			model.setKaleoTaskId(getKaleoTaskId());
			model.setAssigneeClassName(HtmlUtil.escape(getAssigneeClassName()));
			model.setAssigneeClassPK(getAssigneeClassPK());
			model.setCompletionDate(getCompletionDate());
			model.setContext(HtmlUtil.escape(getContext()));

			model = (KaleoTaskInstanceAssignment)Proxy.newProxyInstance(KaleoTaskInstanceAssignment.class.getClassLoader(),
					new Class[] { KaleoTaskInstanceAssignment.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KaleoTaskInstanceAssignment.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		KaleoTaskInstanceAssignmentImpl clone = new KaleoTaskInstanceAssignmentImpl();

		clone.setKaleoTaskInstanceAssignmentId(getKaleoTaskInstanceAssignmentId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setKaleoTaskId(getKaleoTaskId());
		clone.setAssigneeClassName(getAssigneeClassName());
		clone.setAssigneeClassPK(getAssigneeClassPK());
		clone.setCompletionDate(getCompletionDate());
		clone.setContext(getContext());

		return clone;
	}

	public int compareTo(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment) {
		int value = 0;

		if (getKaleoTaskInstanceTokenId() < kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId()) {
			value = -1;
		}
		else if (getKaleoTaskInstanceTokenId() > kaleoTaskInstanceAssignment.getKaleoTaskInstanceTokenId()) {
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

		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment = null;

		try {
			kaleoTaskInstanceAssignment = (KaleoTaskInstanceAssignment)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoTaskInstanceAssignment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{kaleoTaskInstanceAssignmentId=");
		sb.append(getKaleoTaskInstanceAssignmentId());
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
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append(", kaleoTaskId=");
		sb.append(getKaleoTaskId());
		sb.append(", assigneeClassName=");
		sb.append(getAssigneeClassName());
		sb.append(", assigneeClassPK=");
		sb.append(getAssigneeClassPK());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append(", context=");
		sb.append(getContext());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTaskInstanceAssignmentId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceAssignmentId());
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
			"<column><column-name>kaleoTaskInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>context</column-name><column-value><![CDATA[");
		sb.append(getContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTaskInstanceAssignmentId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoTaskInstanceTokenId;
	private long _originalKaleoTaskInstanceTokenId;
	private boolean _setOriginalKaleoTaskInstanceTokenId;
	private long _kaleoTaskId;
	private String _assigneeClassName;
	private String _originalAssigneeClassName;
	private long _assigneeClassPK;
	private long _originalAssigneeClassPK;
	private boolean _setOriginalAssigneeClassPK;
	private Date _completionDate;
	private String _context;
	private transient ExpandoBridge _expandoBridge;
}