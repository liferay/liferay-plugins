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

package com.liferay.wsrp.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerClp extends BaseModelImpl<WSRPProducer>
	implements WSRPProducer {
	public WSRPProducerClp() {
	}

	public long getPrimaryKey() {
		return _wsrpProducerId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpProducerId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_wsrpProducerId);
	}

	public long getWsrpProducerId() {
		return _wsrpProducerId;
	}

	public void setWsrpProducerId(long wsrpProducerId) {
		_wsrpProducerId = wsrpProducerId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPortletIds() {
		return _portletIds;
	}

	public void setPortletIds(String portletIds) {
		_portletIds = portletIds;
	}

	public WSRPProducer toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (WSRPProducer)Proxy.newProxyInstance(WSRPProducer.class.getClassLoader(),
				new Class[] { WSRPProducer.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		WSRPProducerClp clone = new WSRPProducerClp();

		clone.setWsrpProducerId(getWsrpProducerId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setPortletIds(getPortletIds());

		return clone;
	}

	public int compareTo(WSRPProducer wsrpProducer) {
		int value = 0;

		value = getName().compareTo(wsrpProducer.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WSRPProducerClp wsrpProducer = null;

		try {
			wsrpProducer = (WSRPProducerClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = wsrpProducer.getPrimaryKey();

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

		sb.append("{wsrpProducerId=");
		sb.append(getWsrpProducerId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", portletIds=");
		sb.append(getPortletIds());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPProducer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>wsrpProducerId</column-name><column-value><![CDATA[");
		sb.append(getWsrpProducerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>portletIds</column-name><column-value><![CDATA[");
		sb.append(getPortletIds());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _wsrpProducerId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _portletIds;
}