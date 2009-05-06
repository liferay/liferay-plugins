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

import com.liferay.wsrp.model.WSRPConsumerRegistration;
import com.liferay.wsrp.model.WSRPConsumerRegistrationSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WSRPConsumerRegistrationModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerRegistrationModelImpl extends BaseModelImpl<WSRPConsumerRegistration> {
	public static final String TABLE_NAME = "WSRP_WSRPConsumerRegistration";
	public static final Object[][] TABLE_COLUMNS = {
			{ "consumerRegistrationId", new Integer(Types.BIGINT) },
			

			{ "consumerName", new Integer(Types.VARCHAR) },
			

			{ "status", new Integer(Types.BOOLEAN) },
			

			{ "registrationHandle", new Integer(Types.VARCHAR) },
			

			{ "registrationData", new Integer(Types.CLOB) },
			

			{ "lifetimeTerminationTime", new Integer(Types.VARCHAR) },
			

			{ "producerKey", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConsumerRegistration (consumerRegistrationId LONG not null primary key,consumerName VARCHAR(100) null,status BOOLEAN,registrationHandle VARCHAR(75) null,registrationData TEXT null,lifetimeTerminationTime VARCHAR(75) null,producerKey VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConsumerRegistration";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConsumerRegistration"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConsumerRegistration"),
			true);

	public static WSRPConsumerRegistration toModel(
		WSRPConsumerRegistrationSoap soapModel) {
		WSRPConsumerRegistration model = new WSRPConsumerRegistrationImpl();

		model.setConsumerRegistrationId(soapModel.getConsumerRegistrationId());
		model.setConsumerName(soapModel.getConsumerName());
		model.setStatus(soapModel.getStatus());
		model.setRegistrationHandle(soapModel.getRegistrationHandle());
		model.setRegistrationData(soapModel.getRegistrationData());
		model.setLifetimeTerminationTime(soapModel.getLifetimeTerminationTime());
		model.setProducerKey(soapModel.getProducerKey());

		return model;
	}

	public static List<WSRPConsumerRegistration> toModels(
		WSRPConsumerRegistrationSoap[] soapModels) {
		List<WSRPConsumerRegistration> models = new ArrayList<WSRPConsumerRegistration>(soapModels.length);

		for (WSRPConsumerRegistrationSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPConsumerRegistration"));

	public WSRPConsumerRegistrationModelImpl() {
	}

	public long getPrimaryKey() {
		return _consumerRegistrationId;
	}

	public void setPrimaryKey(long pk) {
		setConsumerRegistrationId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_consumerRegistrationId);
	}

	public long getConsumerRegistrationId() {
		return _consumerRegistrationId;
	}

	public void setConsumerRegistrationId(long consumerRegistrationId) {
		_consumerRegistrationId = consumerRegistrationId;
	}

	public String getConsumerName() {
		return GetterUtil.getString(_consumerName);
	}

	public void setConsumerName(String consumerName) {
		_consumerName = consumerName;
	}

	public boolean getStatus() {
		return _status;
	}

	public boolean isStatus() {
		return _status;
	}

	public void setStatus(boolean status) {
		_status = status;
	}

	public String getRegistrationHandle() {
		return GetterUtil.getString(_registrationHandle);
	}

	public void setRegistrationHandle(String registrationHandle) {
		_registrationHandle = registrationHandle;

		if (_originalRegistrationHandle == null) {
			_originalRegistrationHandle = registrationHandle;
		}
	}

	public String getOriginalRegistrationHandle() {
		return GetterUtil.getString(_originalRegistrationHandle);
	}

	public String getRegistrationData() {
		return GetterUtil.getString(_registrationData);
	}

	public void setRegistrationData(String registrationData) {
		_registrationData = registrationData;
	}

	public String getLifetimeTerminationTime() {
		return GetterUtil.getString(_lifetimeTerminationTime);
	}

	public void setLifetimeTerminationTime(String lifetimeTerminationTime) {
		_lifetimeTerminationTime = lifetimeTerminationTime;
	}

	public String getProducerKey() {
		return GetterUtil.getString(_producerKey);
	}

	public void setProducerKey(String producerKey) {
		_producerKey = producerKey;

		if (_originalProducerKey == null) {
			_originalProducerKey = producerKey;
		}
	}

	public String getOriginalProducerKey() {
		return GetterUtil.getString(_originalProducerKey);
	}

	public WSRPConsumerRegistration toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPConsumerRegistration)this;
		}
		else {
			WSRPConsumerRegistration model = new WSRPConsumerRegistrationImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setConsumerRegistrationId(getConsumerRegistrationId());
			model.setConsumerName(HtmlUtil.escape(getConsumerName()));
			model.setStatus(getStatus());
			model.setRegistrationHandle(HtmlUtil.escape(getRegistrationHandle()));
			model.setRegistrationData(HtmlUtil.escape(getRegistrationData()));
			model.setLifetimeTerminationTime(HtmlUtil.escape(
					getLifetimeTerminationTime()));
			model.setProducerKey(HtmlUtil.escape(getProducerKey()));

			model = (WSRPConsumerRegistration)Proxy.newProxyInstance(WSRPConsumerRegistration.class.getClassLoader(),
					new Class[] { WSRPConsumerRegistration.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(WSRPConsumerRegistration.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		WSRPConsumerRegistrationImpl clone = new WSRPConsumerRegistrationImpl();

		clone.setConsumerRegistrationId(getConsumerRegistrationId());
		clone.setConsumerName(getConsumerName());
		clone.setStatus(getStatus());
		clone.setRegistrationHandle(getRegistrationHandle());
		clone.setRegistrationData(getRegistrationData());
		clone.setLifetimeTerminationTime(getLifetimeTerminationTime());
		clone.setProducerKey(getProducerKey());

		return clone;
	}

	public int compareTo(WSRPConsumerRegistration wsrpConsumerRegistration) {
		long pk = wsrpConsumerRegistration.getPrimaryKey();

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

		WSRPConsumerRegistration wsrpConsumerRegistration = null;

		try {
			wsrpConsumerRegistration = (WSRPConsumerRegistration)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = wsrpConsumerRegistration.getPrimaryKey();

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

		sb.append("{consumerRegistrationId=");
		sb.append(getConsumerRegistrationId());
		sb.append(", consumerName=");
		sb.append(getConsumerName());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", registrationHandle=");
		sb.append(getRegistrationHandle());
		sb.append(", registrationData=");
		sb.append(getRegistrationData());
		sb.append(", lifetimeTerminationTime=");
		sb.append(getLifetimeTerminationTime());
		sb.append(", producerKey=");
		sb.append(getProducerKey());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConsumerRegistration");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>consumerRegistrationId</column-name><column-value><![CDATA[");
		sb.append(getConsumerRegistrationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerName</column-name><column-value><![CDATA[");
		sb.append(getConsumerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationHandle</column-name><column-value><![CDATA[");
		sb.append(getRegistrationHandle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationData</column-name><column-value><![CDATA[");
		sb.append(getRegistrationData());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lifetimeTerminationTime</column-name><column-value><![CDATA[");
		sb.append(getLifetimeTerminationTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>producerKey</column-name><column-value><![CDATA[");
		sb.append(getProducerKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _consumerRegistrationId;
	private String _consumerName;
	private boolean _status;
	private String _registrationHandle;
	private String _originalRegistrationHandle;
	private String _registrationData;
	private String _lifetimeTerminationTime;
	private String _producerKey;
	private String _originalProducerKey;
	private transient ExpandoBridge _expandoBridge;
}