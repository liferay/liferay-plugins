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

package com.liferay.wsrp.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerModel;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * <p>
 * This interface is a model that represents the WSRP_WSRPConsumer table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerImpl
 * @see       com.liferay.wsrp.model.WSRPConsumer
 * @see       com.liferay.wsrp.model.WSRPConsumerModel
 * @generated
 */
public class WSRPConsumerModelImpl extends BaseModelImpl<WSRPConsumer>
	implements WSRPConsumerModel {
	public static final String TABLE_NAME = "WSRP_WSRPConsumer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "wsrpConsumerId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "name", new Integer(Types.VARCHAR) },
			{ "url", new Integer(Types.VARCHAR) },
			{ "wsdl", new Integer(Types.CLOB) },
			{ "registrationContextString", new Integer(Types.CLOB) },
			{ "registrationPropertiesString", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConsumer (wsrpConsumerId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url STRING null,wsdl TEXT null,registrationContextString TEXT null,registrationPropertiesString STRING null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConsumer";
	public static final String ORDER_BY_JPQL = " ORDER BY wsrpConsumer.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY WSRP_WSRPConsumer.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConsumer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConsumer"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPConsumer"));

	public WSRPConsumerModelImpl() {
	}

	public long getPrimaryKey() {
		return _wsrpConsumerId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpConsumerId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_wsrpConsumerId);
	}

	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerId = wsrpConsumerId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getWsdl() {
		if (_wsdl == null) {
			return StringPool.BLANK;
		}
		else {
			return _wsdl;
		}
	}

	public void setWsdl(String wsdl) {
		_wsdl = wsdl;
	}

	public String getRegistrationContextString() {
		if (_registrationContextString == null) {
			return StringPool.BLANK;
		}
		else {
			return _registrationContextString;
		}
	}

	public void setRegistrationContextString(String registrationContextString) {
		_registrationContextString = registrationContextString;
	}

	public String getRegistrationPropertiesString() {
		if (_registrationPropertiesString == null) {
			return StringPool.BLANK;
		}
		else {
			return _registrationPropertiesString;
		}
	}

	public void setRegistrationPropertiesString(
		String registrationPropertiesString) {
		_registrationPropertiesString = registrationPropertiesString;
	}

	public WSRPConsumer toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPConsumer)this;
		}
		else {
			return (WSRPConsumer)Proxy.newProxyInstance(WSRPConsumer.class.getClassLoader(),
				new Class[] { WSRPConsumer.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					WSRPConsumer.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		WSRPConsumerImpl clone = new WSRPConsumerImpl();

		clone.setWsrpConsumerId(getWsrpConsumerId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setUrl(getUrl());
		clone.setWsdl(getWsdl());
		clone.setRegistrationContextString(getRegistrationContextString());
		clone.setRegistrationPropertiesString(getRegistrationPropertiesString());

		return clone;
	}

	public int compareTo(WSRPConsumer wsrpConsumer) {
		int value = 0;

		value = getName().compareTo(wsrpConsumer.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WSRPConsumer wsrpConsumer = null;

		try {
			wsrpConsumer = (WSRPConsumer)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = wsrpConsumer.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{wsrpConsumerId=");
		sb.append(getWsrpConsumerId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", wsdl=");
		sb.append(getWsdl());
		sb.append(", registrationContextString=");
		sb.append(getRegistrationContextString());
		sb.append(", registrationPropertiesString=");
		sb.append(getRegistrationPropertiesString());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>wsrpConsumerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerId());
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
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wsdl</column-name><column-value><![CDATA[");
		sb.append(getWsdl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationContextString</column-name><column-value><![CDATA[");
		sb.append(getRegistrationContextString());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationPropertiesString</column-name><column-value><![CDATA[");
		sb.append(getRegistrationPropertiesString());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _wsrpConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _wsdl;
	private String _registrationContextString;
	private String _registrationPropertiesString;
	private transient ExpandoBridge _expandoBridge;
}