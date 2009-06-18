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

package com.liferay.wsrp.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.WSRPConsumerPortletSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="WSRPConsumerPortletModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerPortletModelImpl extends BaseModelImpl<WSRPConsumerPortlet> {
	public static final String TABLE_NAME = "WSRP_WSRPConsumerPortlet";
	public static final Object[][] TABLE_COLUMNS = {
			{ "wsrpConsumerPortletId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "wsrpConsumerId", new Integer(Types.BIGINT) },
			

			{ "name", new Integer(Types.VARCHAR) },
			

			{ "portletHandle", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConsumerPortlet (wsrpConsumerPortletId LONG not null primary key,createDate DATE null,modifiedDate DATE null,wsrpConsumerId LONG,name VARCHAR(75) null,portletHandle STRING null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConsumerPortlet";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConsumerPortlet"),
			true);

	public static WSRPConsumerPortlet toModel(WSRPConsumerPortletSoap soapModel) {
		WSRPConsumerPortlet model = new WSRPConsumerPortletImpl();

		model.setWsrpConsumerPortletId(soapModel.getWsrpConsumerPortletId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setWsrpConsumerId(soapModel.getWsrpConsumerId());
		model.setName(soapModel.getName());
		model.setPortletHandle(soapModel.getPortletHandle());

		return model;
	}

	public static List<WSRPConsumerPortlet> toModels(
		WSRPConsumerPortletSoap[] soapModels) {
		List<WSRPConsumerPortlet> models = new ArrayList<WSRPConsumerPortlet>(soapModels.length);

		for (WSRPConsumerPortletSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

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
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPortletHandle() {
		return GetterUtil.getString(_portletHandle);
	}

	public void setPortletHandle(String portletHandle) {
		_portletHandle = portletHandle;
	}

	public WSRPConsumerPortlet toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPConsumerPortlet)this;
		}
		else {
			WSRPConsumerPortlet model = new WSRPConsumerPortletImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setWsrpConsumerPortletId(getWsrpConsumerPortletId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setWsrpConsumerId(getWsrpConsumerId());
			model.setName(HtmlUtil.escape(getName()));
			model.setPortletHandle(HtmlUtil.escape(getPortletHandle()));

			model = (WSRPConsumerPortlet)Proxy.newProxyInstance(WSRPConsumerPortlet.class.getClassLoader(),
					new Class[] { WSRPConsumerPortlet.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(WSRPConsumerPortlet.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		WSRPConsumerPortletImpl clone = new WSRPConsumerPortletImpl();

		clone.setWsrpConsumerPortletId(getWsrpConsumerPortletId());
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
		StringBuilder sb = new StringBuilder();

		sb.append("{wsrpConsumerPortletId=");
		sb.append(getWsrpConsumerPortletId());
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
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumerPortlet");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>wsrpConsumerPortletId</column-name><column-value><![CDATA[");
		sb.append(getWsrpConsumerPortletId());
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
	private Date _createDate;
	private Date _modifiedDate;
	private long _wsrpConsumerId;
	private String _name;
	private String _portletHandle;
	private transient ExpandoBridge _expandoBridge;
}