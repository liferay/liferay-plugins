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

import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.WSRPProducerSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WSRPProducerModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerModelImpl extends BaseModelImpl<WSRPProducer> {
	public static final String TABLE_NAME = "WSRP_WSRPProducer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "producerId", new Integer(Types.BIGINT) },
			

			{ "portalId", new Integer(Types.VARCHAR) },
			

			{ "status", new Integer(Types.BOOLEAN) },
			

			{ "namespace", new Integer(Types.VARCHAR) },
			

			{ "instanceName", new Integer(Types.VARCHAR) },
			

			{ "requiresRegistration", new Integer(Types.BOOLEAN) },
			

			{ "supportsInbandRegistration", new Integer(Types.BOOLEAN) },
			

			{ "version", new Integer(Types.VARCHAR) },
			

			{ "offeredPortlets", new Integer(Types.VARCHAR) },
			

			{ "producerProfileMap", new Integer(Types.VARCHAR) },
			

			{ "registrationProperties", new Integer(Types.VARCHAR) },
			

			{ "registrationValidatorClass", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPProducer (producerId LONG not null primary key,portalId VARCHAR(75) null,status BOOLEAN,namespace VARCHAR(75) null,instanceName VARCHAR(75) null,requiresRegistration BOOLEAN,supportsInbandRegistration BOOLEAN,version VARCHAR(75) null,offeredPortlets STRING null,producerProfileMap VARCHAR(75) null,registrationProperties STRING null,registrationValidatorClass VARCHAR(200) null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPProducer";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPProducer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPProducer"),
			true);

	public static WSRPProducer toModel(WSRPProducerSoap soapModel) {
		WSRPProducer model = new WSRPProducerImpl();

		model.setProducerId(soapModel.getProducerId());
		model.setPortalId(soapModel.getPortalId());
		model.setStatus(soapModel.getStatus());
		model.setNamespace(soapModel.getNamespace());
		model.setInstanceName(soapModel.getInstanceName());
		model.setRequiresRegistration(soapModel.getRequiresRegistration());
		model.setSupportsInbandRegistration(soapModel.getSupportsInbandRegistration());
		model.setVersion(soapModel.getVersion());
		model.setOfferedPortlets(soapModel.getOfferedPortlets());
		model.setProducerProfileMap(soapModel.getProducerProfileMap());
		model.setRegistrationProperties(soapModel.getRegistrationProperties());
		model.setRegistrationValidatorClass(soapModel.getRegistrationValidatorClass());

		return model;
	}

	public static List<WSRPProducer> toModels(WSRPProducerSoap[] soapModels) {
		List<WSRPProducer> models = new ArrayList<WSRPProducer>(soapModels.length);

		for (WSRPProducerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPProducer"));

	public WSRPProducerModelImpl() {
	}

	public long getPrimaryKey() {
		return _producerId;
	}

	public void setPrimaryKey(long pk) {
		setProducerId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_producerId);
	}

	public long getProducerId() {
		return _producerId;
	}

	public void setProducerId(long producerId) {
		_producerId = producerId;
	}

	public String getPortalId() {
		return GetterUtil.getString(_portalId);
	}

	public void setPortalId(String portalId) {
		_portalId = portalId;
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

	public String getNamespace() {
		return GetterUtil.getString(_namespace);
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public String getInstanceName() {
		return GetterUtil.getString(_instanceName);
	}

	public void setInstanceName(String instanceName) {
		_instanceName = instanceName;

		if (_originalInstanceName == null) {
			_originalInstanceName = instanceName;
		}
	}

	public String getOriginalInstanceName() {
		return GetterUtil.getString(_originalInstanceName);
	}

	public boolean getRequiresRegistration() {
		return _requiresRegistration;
	}

	public boolean isRequiresRegistration() {
		return _requiresRegistration;
	}

	public void setRequiresRegistration(boolean requiresRegistration) {
		_requiresRegistration = requiresRegistration;
	}

	public boolean getSupportsInbandRegistration() {
		return _supportsInbandRegistration;
	}

	public boolean isSupportsInbandRegistration() {
		return _supportsInbandRegistration;
	}

	public void setSupportsInbandRegistration(
		boolean supportsInbandRegistration) {
		_supportsInbandRegistration = supportsInbandRegistration;
	}

	public String getVersion() {
		return GetterUtil.getString(_version);
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getOfferedPortlets() {
		return GetterUtil.getString(_offeredPortlets);
	}

	public void setOfferedPortlets(String offeredPortlets) {
		_offeredPortlets = offeredPortlets;
	}

	public String getProducerProfileMap() {
		return GetterUtil.getString(_producerProfileMap);
	}

	public void setProducerProfileMap(String producerProfileMap) {
		_producerProfileMap = producerProfileMap;
	}

	public String getRegistrationProperties() {
		return GetterUtil.getString(_registrationProperties);
	}

	public void setRegistrationProperties(String registrationProperties) {
		_registrationProperties = registrationProperties;
	}

	public String getRegistrationValidatorClass() {
		return GetterUtil.getString(_registrationValidatorClass);
	}

	public void setRegistrationValidatorClass(String registrationValidatorClass) {
		_registrationValidatorClass = registrationValidatorClass;
	}

	public WSRPProducer toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPProducer)this;
		}
		else {
			WSRPProducer model = new WSRPProducerImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setProducerId(getProducerId());
			model.setPortalId(HtmlUtil.escape(getPortalId()));
			model.setStatus(getStatus());
			model.setNamespace(HtmlUtil.escape(getNamespace()));
			model.setInstanceName(HtmlUtil.escape(getInstanceName()));
			model.setRequiresRegistration(getRequiresRegistration());
			model.setSupportsInbandRegistration(getSupportsInbandRegistration());
			model.setVersion(HtmlUtil.escape(getVersion()));
			model.setOfferedPortlets(HtmlUtil.escape(getOfferedPortlets()));
			model.setProducerProfileMap(HtmlUtil.escape(getProducerProfileMap()));
			model.setRegistrationProperties(HtmlUtil.escape(
					getRegistrationProperties()));
			model.setRegistrationValidatorClass(HtmlUtil.escape(
					getRegistrationValidatorClass()));

			model = (WSRPProducer)Proxy.newProxyInstance(WSRPProducer.class.getClassLoader(),
					new Class[] { WSRPProducer.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(WSRPProducer.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		WSRPProducerImpl clone = new WSRPProducerImpl();

		clone.setProducerId(getProducerId());
		clone.setPortalId(getPortalId());
		clone.setStatus(getStatus());
		clone.setNamespace(getNamespace());
		clone.setInstanceName(getInstanceName());
		clone.setRequiresRegistration(getRequiresRegistration());
		clone.setSupportsInbandRegistration(getSupportsInbandRegistration());
		clone.setVersion(getVersion());
		clone.setOfferedPortlets(getOfferedPortlets());
		clone.setProducerProfileMap(getProducerProfileMap());
		clone.setRegistrationProperties(getRegistrationProperties());
		clone.setRegistrationValidatorClass(getRegistrationValidatorClass());

		return clone;
	}

	public int compareTo(WSRPProducer wsrpProducer) {
		long pk = wsrpProducer.getPrimaryKey();

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

		WSRPProducer wsrpProducer = null;

		try {
			wsrpProducer = (WSRPProducer)obj;
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
		StringBuilder sb = new StringBuilder();

		sb.append("{producerId=");
		sb.append(getProducerId());
		sb.append(", portalId=");
		sb.append(getPortalId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", namespace=");
		sb.append(getNamespace());
		sb.append(", instanceName=");
		sb.append(getInstanceName());
		sb.append(", requiresRegistration=");
		sb.append(getRequiresRegistration());
		sb.append(", supportsInbandRegistration=");
		sb.append(getSupportsInbandRegistration());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", offeredPortlets=");
		sb.append(getOfferedPortlets());
		sb.append(", producerProfileMap=");
		sb.append(getProducerProfileMap());
		sb.append(", registrationProperties=");
		sb.append(getRegistrationProperties());
		sb.append(", registrationValidatorClass=");
		sb.append(getRegistrationValidatorClass());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPProducer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>producerId</column-name><column-value><![CDATA[");
		sb.append("getProducerId()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalId</column-name><column-value><![CDATA[");
		sb.append("getPortalId()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append("getStatus()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>namespace</column-name><column-value><![CDATA[");
		sb.append("getNamespace()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>instanceName</column-name><column-value><![CDATA[");
		sb.append("getInstanceName()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requiresRegistration</column-name><column-value><![CDATA[");
		sb.append("getRequiresRegistration()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>supportsInbandRegistration</column-name><column-value><![CDATA[");
		sb.append("getSupportsInbandRegistration()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append("getVersion()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>offeredPortlets</column-name><column-value><![CDATA[");
		sb.append("getOfferedPortlets()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>producerProfileMap</column-name><column-value><![CDATA[");
		sb.append("getProducerProfileMap()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationProperties</column-name><column-value><![CDATA[");
		sb.append("getRegistrationProperties()");
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationValidatorClass</column-name><column-value><![CDATA[");
		sb.append("getRegistrationValidatorClass()");
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _producerId;
	private String _portalId;
	private boolean _status;
	private String _namespace;
	private String _instanceName;
	private String _originalInstanceName;
	private boolean _requiresRegistration;
	private boolean _supportsInbandRegistration;
	private String _version;
	private String _offeredPortlets;
	private String _producerProfileMap;
	private String _registrationProperties;
	private String _registrationValidatorClass;
	private transient ExpandoBridge _expandoBridge;
}