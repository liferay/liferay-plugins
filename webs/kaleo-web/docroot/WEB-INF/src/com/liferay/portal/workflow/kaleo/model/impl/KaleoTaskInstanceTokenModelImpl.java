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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceTokenSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoTaskInstanceTokenModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoTaskInstanceToken table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceTokenImpl
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken
 * @see       com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceTokenModel
 * @generated
 */
public class KaleoTaskInstanceTokenModelImpl extends BaseModelImpl<KaleoTaskInstanceToken> {
	public static final String TABLE_NAME = "Kaleo_KaleoTaskInstanceToken";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoTaskInstanceTokenId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "userId", new Integer(Types.BIGINT) },
			{ "userName", new Integer(Types.VARCHAR) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "kaleoInstanceId", new Integer(Types.BIGINT) },
			{ "kaleoInstanceTokenId", new Integer(Types.BIGINT) },
			{ "kaleoTaskId", new Integer(Types.BIGINT) },
			{ "completionUserId", new Integer(Types.BIGINT) },
			{ "completionDate", new Integer(Types.TIMESTAMP) },
			{ "dueDate", new Integer(Types.TIMESTAMP) },
			{ "context", new Integer(Types.CLOB) }
		};
	public static final String TABLE_SQL_CREATE = "create table Kaleo_KaleoTaskInstanceToken (kaleoTaskInstanceTokenId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoInstanceId LONG,kaleoInstanceTokenId LONG,kaleoTaskId LONG,completionUserId LONG,completionDate DATE null,dueDate DATE null,context TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table Kaleo_KaleoTaskInstanceToken";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoTaskInstanceToken.kaleoTaskInstanceTokenId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Kaleo_KaleoTaskInstanceToken.kaleoTaskInstanceTokenId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"),
			true);

	public static KaleoTaskInstanceToken toModel(
		KaleoTaskInstanceTokenSoap soapModel) {
		KaleoTaskInstanceToken model = new KaleoTaskInstanceTokenImpl();

		model.setKaleoTaskInstanceTokenId(soapModel.getKaleoTaskInstanceTokenId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setKaleoInstanceId(soapModel.getKaleoInstanceId());
		model.setKaleoInstanceTokenId(soapModel.getKaleoInstanceTokenId());
		model.setKaleoTaskId(soapModel.getKaleoTaskId());
		model.setCompletionUserId(soapModel.getCompletionUserId());
		model.setCompletionDate(soapModel.getCompletionDate());
		model.setDueDate(soapModel.getDueDate());
		model.setContext(soapModel.getContext());

		return model;
	}

	public static List<KaleoTaskInstanceToken> toModels(
		KaleoTaskInstanceTokenSoap[] soapModels) {
		List<KaleoTaskInstanceToken> models = new ArrayList<KaleoTaskInstanceToken>(soapModels.length);

		for (KaleoTaskInstanceTokenSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"));

	public KaleoTaskInstanceTokenModelImpl() {
	}

	public long getPrimaryKey() {
		return _kaleoTaskInstanceTokenId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTaskInstanceTokenId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoTaskInstanceTokenId);
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
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
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
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

	public long getKaleoTaskId() {
		return _kaleoTaskId;
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskId = kaleoTaskId;
	}

	public long getCompletionUserId() {
		return _completionUserId;
	}

	public void setCompletionUserId(long completionUserId) {
		_completionUserId = completionUserId;
	}

	public String getCompletionUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getCompletionUserId(), "uuid",
			_completionUserUuid);
	}

	public void setCompletionUserUuid(String completionUserUuid) {
		_completionUserUuid = completionUserUuid;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public String getContext() {
		if (_context == null) {
			return StringPool.BLANK;
		}
		else {
			return _context;
		}
	}

	public void setContext(String context) {
		_context = context;
	}

	public KaleoTaskInstanceToken toEscapedModel() {
		if (isEscapedModel()) {
			return (KaleoTaskInstanceToken)this;
		}
		else {
			KaleoTaskInstanceToken model = new KaleoTaskInstanceTokenImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setUserName(HtmlUtil.escape(getUserName()));
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setKaleoInstanceId(getKaleoInstanceId());
			model.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
			model.setKaleoTaskId(getKaleoTaskId());
			model.setCompletionUserId(getCompletionUserId());
			model.setCompletionDate(getCompletionDate());
			model.setDueDate(getDueDate());
			model.setContext(HtmlUtil.escape(getContext()));

			model = (KaleoTaskInstanceToken)Proxy.newProxyInstance(KaleoTaskInstanceToken.class.getClassLoader(),
					new Class[] { KaleoTaskInstanceToken.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KaleoTaskInstanceToken.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		KaleoTaskInstanceTokenImpl clone = new KaleoTaskInstanceTokenImpl();

		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setKaleoTaskId(getKaleoTaskId());
		clone.setCompletionUserId(getCompletionUserId());
		clone.setCompletionDate(getCompletionDate());
		clone.setDueDate(getDueDate());
		clone.setContext(getContext());

		return clone;
	}

	public int compareTo(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		int value = 0;

		if (getKaleoTaskInstanceTokenId() < kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()) {
			value = -1;
		}
		else if (getKaleoTaskInstanceTokenId() > kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId()) {
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

		KaleoTaskInstanceToken kaleoTaskInstanceToken = null;

		try {
			kaleoTaskInstanceToken = (KaleoTaskInstanceToken)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoTaskInstanceToken.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
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
		sb.append(", kaleoTaskId=");
		sb.append(getKaleoTaskId());
		sb.append(", completionUserId=");
		sb.append(getCompletionUserId());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append(", dueDate=");
		sb.append(getDueDate());
		sb.append(", context=");
		sb.append(getContext());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTaskInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceTokenId());
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
			"<column><column-name>kaleoTaskId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionUserId</column-name><column-value><![CDATA[");
		sb.append(getCompletionUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dueDate</column-name><column-value><![CDATA[");
		sb.append(getDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>context</column-name><column-value><![CDATA[");
		sb.append(getContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTaskInstanceTokenId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskId;
	private long _completionUserId;
	private String _completionUserUuid;
	private Date _completionDate;
	private Date _dueDate;
	private String _context;
	private transient ExpandoBridge _expandoBridge;
}