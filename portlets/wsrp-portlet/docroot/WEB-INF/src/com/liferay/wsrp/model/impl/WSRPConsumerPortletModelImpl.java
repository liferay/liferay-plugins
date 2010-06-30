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

import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * <a href="WSRPConsumerPortletModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the WSRP_WSRPConsumerPortlet table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPortletImpl
 * @see       com.liferay.wsrp.model.WSRPConsumerPortlet
 * @see       com.liferay.wsrp.model.WSRPConsumerPortletModel
 * @generated
 */
public class WSRPConsumerPortletModelImpl extends BaseModelImpl<WSRPConsumerPortlet> {
	public static final String TABLE_NAME = "WSRP_WSRPConsumerPortlet";
	public static final Object[][] TABLE_COLUMNS = {
			{ "wsrpConsumerPortletId", new Integer(Types.BIGINT) },
			{ "companyId", new Integer(Types.BIGINT) },
			{ "createDate", new Integer(Types.TIMESTAMP) },
			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			{ "wsrpConsumerId", new Integer(Types.BIGINT) },
			{ "name", new Integer(Types.VARCHAR) },
			{ "portletHandle", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConsumerPortlet (wsrpConsumerPortletId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,wsrpConsumerId LONG,name VARCHAR(75) null,portletHandle STRING null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConsumerPortlet";
	public static final String ORDER_BY_JPQL = " ORDER BY wsrpConsumerPortlet.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY WSRP_WSRPConsumerPortlet.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPConsumerPortlet"));

	public WSRPConsumerPortletModelImpl() {
	}

	public long getPrimaryKey() {
		return _wsrpConsumerPortletId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpConsumerPortletId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_wsrpConsumerPortletId);
	}

	public long getWsrpConsumerPortletId() {
		return _wsrpConsumerPortletId;
	}

	public void setWsrpConsumerPortletId(long wsrpConsumerPortletId) {
		_wsrpConsumerPortletId = wsrpConsumerPortletId;
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

	public long getWsrpConsumerId() {
		return _wsrpConsumerId;
	}

	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumerId = wsrpConsumerId;

		if (!_setOriginalWsrpConsumerId) {
			_setOriginalWsrpConsumerId = true;

			_originalWsrpConsumerId = wsrpConsumerId;
		}
	}

	public long getOriginalWsrpConsumerId() {
		return _originalWsrpConsumerId;
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

	public String getPortletHandle() {
		if (_portletHandle == null) {
			return StringPool.BLANK;
		}
		else {
			return _portletHandle;
		}
	}

	public void setPortletHandle(String portletHandle) {
		_portletHandle = portletHandle;

		if (_originalPortletHandle == null) {
			_originalPortletHandle = portletHandle;
		}
	}

	public String getOriginalPortletHandle() {
		return GetterUtil.getString(_originalPortletHandle);
	}

	public WSRPConsumerPortlet toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPConsumerPortlet)this;
		}
		else {
			return (WSRPConsumerPortlet)Proxy.newProxyInstance(WSRPConsumerPortlet.class.getClassLoader(),
				new Class[] { WSRPConsumerPortlet.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					WSRPConsumerPortlet.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	public Object clone() {
		WSRPConsumerPortletImpl clone = new WSRPConsumerPortletImpl();

		clone.setWsrpConsumerPortletId(getWsrpConsumerPortletId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setWsrpConsumerId(getWsrpConsumerId());
		clone.setName(getName());
		clone.setPortletHandle(getPortletHandle());

		return clone;
	}

	public int compareTo(WSRPConsumerPortlet wsrpConsumerPortlet) {
		int value = 0;

		value = getName().compareTo(wsrpConsumerPortlet.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WSRPConsumerPortlet wsrpConsumerPortlet = null;

		try {
			wsrpConsumerPortlet = (WSRPConsumerPortlet)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = wsrpConsumerPortlet.getPrimaryKey();

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
		StringBundler sb = new StringBundler(15);

		sb.append("{wsrpConsumerPortletId=");
		sb.append(getWsrpConsumerPortletId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", wsrpConsumerId=");
		sb.append(getWsrpConsumerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", portletHandle=");
		sb.append(getPortletHandle());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumerPortlet");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>wsrpConsumerPortletId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerPortletId());
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
			"<column><column-name>wsrpConsumerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletHandle</column-name><column-value><![CDATA[");
		sb.append(getPortletHandle());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _wsrpConsumerPortletId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _wsrpConsumerId;
	private long _originalWsrpConsumerId;
	private boolean _setOriginalWsrpConsumerId;
	private String _name;
	private String _portletHandle;
	private String _originalPortletHandle;
	private transient ExpandoBridge _expandoBridge;
}