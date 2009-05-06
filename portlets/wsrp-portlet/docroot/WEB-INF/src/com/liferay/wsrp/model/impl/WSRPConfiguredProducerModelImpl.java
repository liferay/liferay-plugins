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

import com.liferay.wsrp.model.WSRPConfiguredProducer;
import com.liferay.wsrp.model.WSRPConfiguredProducerSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WSRPConfiguredProducerModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerModelImpl extends BaseModelImpl<WSRPConfiguredProducer> {
	public static final String TABLE_NAME = "WSRP_WSRPConfiguredProducer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "configuredProducerId", new Integer(Types.BIGINT) },
			

			{ "name", new Integer(Types.VARCHAR) },
			

			{ "portalId", new Integer(Types.VARCHAR) },
			

			{ "namespace", new Integer(Types.VARCHAR) },
			

			{ "producerURL", new Integer(Types.VARCHAR) },
			

			{ "producerVersion", new Integer(Types.VARCHAR) },
			

			{ "producerMarkupURL", new Integer(Types.VARCHAR) },
			

			{ "status", new Integer(Types.INTEGER) },
			

			{ "registrationData", new Integer(Types.CLOB) },
			

			{ "registrationContext", new Integer(Types.CLOB) },
			

			{ "serviceDescription", new Integer(Types.CLOB) },
			

			{ "userCategoryMapping", new Integer(Types.CLOB) },
			

			{ "customUserProfile", new Integer(Types.CLOB) },
			

			{ "identityPropagationType", new Integer(Types.VARCHAR) },
			

			{ "lifetimeTerminationTime", new Integer(Types.VARCHAR) },
			

			{ "sdLastModified", new Integer(Types.BIGINT) },
			

			{ "entityVersion", new Integer(Types.INTEGER) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_WSRPConfiguredProducer (configuredProducerId LONG not null primary key,name VARCHAR(75) null,portalId VARCHAR(75) null,namespace VARCHAR(75) null,producerURL VARCHAR(256) null,producerVersion VARCHAR(75) null,producerMarkupURL VARCHAR(256) null,status INTEGER,registrationData TEXT null,registrationContext TEXT null,serviceDescription TEXT null,userCategoryMapping TEXT null,customUserProfile TEXT null,identityPropagationType VARCHAR(75) null,lifetimeTerminationTime VARCHAR(75) null,sdLastModified LONG,entityVersion INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_WSRPConfiguredProducer";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.wsrp.model.WSRPConfiguredProducer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.model.WSRPConfiguredProducer"),
			true);

	public static WSRPConfiguredProducer toModel(
		WSRPConfiguredProducerSoap soapModel) {
		WSRPConfiguredProducer model = new WSRPConfiguredProducerImpl();

		model.setConfiguredProducerId(soapModel.getConfiguredProducerId());
		model.setName(soapModel.getName());
		model.setPortalId(soapModel.getPortalId());
		model.setNamespace(soapModel.getNamespace());
		model.setProducerURL(soapModel.getProducerURL());
		model.setProducerVersion(soapModel.getProducerVersion());
		model.setProducerMarkupURL(soapModel.getProducerMarkupURL());
		model.setStatus(soapModel.getStatus());
		model.setRegistrationData(soapModel.getRegistrationData());
		model.setRegistrationContext(soapModel.getRegistrationContext());
		model.setServiceDescription(soapModel.getServiceDescription());
		model.setUserCategoryMapping(soapModel.getUserCategoryMapping());
		model.setCustomUserProfile(soapModel.getCustomUserProfile());
		model.setIdentityPropagationType(soapModel.getIdentityPropagationType());
		model.setLifetimeTerminationTime(soapModel.getLifetimeTerminationTime());
		model.setSdLastModified(soapModel.getSdLastModified());
		model.setEntityVersion(soapModel.getEntityVersion());

		return model;
	}

	public static List<WSRPConfiguredProducer> toModels(
		WSRPConfiguredProducerSoap[] soapModels) {
		List<WSRPConfiguredProducer> models = new ArrayList<WSRPConfiguredProducer>(soapModels.length);

		for (WSRPConfiguredProducerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.model.WSRPConfiguredProducer"));

	public WSRPConfiguredProducerModelImpl() {
	}

	public long getPrimaryKey() {
		return _configuredProducerId;
	}

	public void setPrimaryKey(long pk) {
		setConfiguredProducerId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_configuredProducerId);
	}

	public long getConfiguredProducerId() {
		return _configuredProducerId;
	}

	public void setConfiguredProducerId(long configuredProducerId) {
		_configuredProducerId = configuredProducerId;
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPortalId() {
		return GetterUtil.getString(_portalId);
	}

	public void setPortalId(String portalId) {
		_portalId = portalId;
	}

	public String getNamespace() {
		return GetterUtil.getString(_namespace);
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public String getProducerURL() {
		return GetterUtil.getString(_producerURL);
	}

	public void setProducerURL(String producerURL) {
		_producerURL = producerURL;
	}

	public String getProducerVersion() {
		return GetterUtil.getString(_producerVersion);
	}

	public void setProducerVersion(String producerVersion) {
		_producerVersion = producerVersion;
	}

	public String getProducerMarkupURL() {
		return GetterUtil.getString(_producerMarkupURL);
	}

	public void setProducerMarkupURL(String producerMarkupURL) {
		_producerMarkupURL = producerMarkupURL;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getRegistrationData() {
		return GetterUtil.getString(_registrationData);
	}

	public void setRegistrationData(String registrationData) {
		_registrationData = registrationData;
	}

	public String getRegistrationContext() {
		return GetterUtil.getString(_registrationContext);
	}

	public void setRegistrationContext(String registrationContext) {
		_registrationContext = registrationContext;
	}

	public String getServiceDescription() {
		return GetterUtil.getString(_serviceDescription);
	}

	public void setServiceDescription(String serviceDescription) {
		_serviceDescription = serviceDescription;
	}

	public String getUserCategoryMapping() {
		return GetterUtil.getString(_userCategoryMapping);
	}

	public void setUserCategoryMapping(String userCategoryMapping) {
		_userCategoryMapping = userCategoryMapping;
	}

	public String getCustomUserProfile() {
		return GetterUtil.getString(_customUserProfile);
	}

	public void setCustomUserProfile(String customUserProfile) {
		_customUserProfile = customUserProfile;
	}

	public String getIdentityPropagationType() {
		return GetterUtil.getString(_identityPropagationType);
	}

	public void setIdentityPropagationType(String identityPropagationType) {
		_identityPropagationType = identityPropagationType;
	}

	public String getLifetimeTerminationTime() {
		return GetterUtil.getString(_lifetimeTerminationTime);
	}

	public void setLifetimeTerminationTime(String lifetimeTerminationTime) {
		_lifetimeTerminationTime = lifetimeTerminationTime;
	}

	public long getSdLastModified() {
		return _sdLastModified;
	}

	public void setSdLastModified(long sdLastModified) {
		_sdLastModified = sdLastModified;
	}

	public int getEntityVersion() {
		return _entityVersion;
	}

	public void setEntityVersion(int entityVersion) {
		_entityVersion = entityVersion;
	}

	public WSRPConfiguredProducer toEscapedModel() {
		if (isEscapedModel()) {
			return (WSRPConfiguredProducer)this;
		}
		else {
			WSRPConfiguredProducer model = new WSRPConfiguredProducerImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setConfiguredProducerId(getConfiguredProducerId());
			model.setName(HtmlUtil.escape(getName()));
			model.setPortalId(HtmlUtil.escape(getPortalId()));
			model.setNamespace(HtmlUtil.escape(getNamespace()));
			model.setProducerURL(HtmlUtil.escape(getProducerURL()));
			model.setProducerVersion(HtmlUtil.escape(getProducerVersion()));
			model.setProducerMarkupURL(HtmlUtil.escape(getProducerMarkupURL()));
			model.setStatus(getStatus());
			model.setRegistrationData(HtmlUtil.escape(getRegistrationData()));
			model.setRegistrationContext(HtmlUtil.escape(
					getRegistrationContext()));
			model.setServiceDescription(HtmlUtil.escape(getServiceDescription()));
			model.setUserCategoryMapping(HtmlUtil.escape(
					getUserCategoryMapping()));
			model.setCustomUserProfile(HtmlUtil.escape(getCustomUserProfile()));
			model.setIdentityPropagationType(HtmlUtil.escape(
					getIdentityPropagationType()));
			model.setLifetimeTerminationTime(HtmlUtil.escape(
					getLifetimeTerminationTime()));
			model.setSdLastModified(getSdLastModified());
			model.setEntityVersion(getEntityVersion());

			model = (WSRPConfiguredProducer)Proxy.newProxyInstance(WSRPConfiguredProducer.class.getClassLoader(),
					new Class[] { WSRPConfiguredProducer.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(WSRPConfiguredProducer.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		WSRPConfiguredProducerImpl clone = new WSRPConfiguredProducerImpl();

		clone.setConfiguredProducerId(getConfiguredProducerId());
		clone.setName(getName());
		clone.setPortalId(getPortalId());
		clone.setNamespace(getNamespace());
		clone.setProducerURL(getProducerURL());
		clone.setProducerVersion(getProducerVersion());
		clone.setProducerMarkupURL(getProducerMarkupURL());
		clone.setStatus(getStatus());
		clone.setRegistrationData(getRegistrationData());
		clone.setRegistrationContext(getRegistrationContext());
		clone.setServiceDescription(getServiceDescription());
		clone.setUserCategoryMapping(getUserCategoryMapping());
		clone.setCustomUserProfile(getCustomUserProfile());
		clone.setIdentityPropagationType(getIdentityPropagationType());
		clone.setLifetimeTerminationTime(getLifetimeTerminationTime());
		clone.setSdLastModified(getSdLastModified());
		clone.setEntityVersion(getEntityVersion());

		return clone;
	}

	public int compareTo(WSRPConfiguredProducer wsrpConfiguredProducer) {
		long pk = wsrpConfiguredProducer.getPrimaryKey();

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

		WSRPConfiguredProducer wsrpConfiguredProducer = null;

		try {
			wsrpConfiguredProducer = (WSRPConfiguredProducer)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = wsrpConfiguredProducer.getPrimaryKey();

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

		sb.append("{configuredProducerId=");
		sb.append(getConfiguredProducerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", portalId=");
		sb.append(getPortalId());
		sb.append(", namespace=");
		sb.append(getNamespace());
		sb.append(", producerURL=");
		sb.append(getProducerURL());
		sb.append(", producerVersion=");
		sb.append(getProducerVersion());
		sb.append(", producerMarkupURL=");
		sb.append(getProducerMarkupURL());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", registrationData=");
		sb.append(getRegistrationData());
		sb.append(", registrationContext=");
		sb.append(getRegistrationContext());
		sb.append(", serviceDescription=");
		sb.append(getServiceDescription());
		sb.append(", userCategoryMapping=");
		sb.append(getUserCategoryMapping());
		sb.append(", customUserProfile=");
		sb.append(getCustomUserProfile());
		sb.append(", identityPropagationType=");
		sb.append(getIdentityPropagationType());
		sb.append(", lifetimeTerminationTime=");
		sb.append(getLifetimeTerminationTime());
		sb.append(", sdLastModified=");
		sb.append(getSdLastModified());
		sb.append(", entityVersion=");
		sb.append(getEntityVersion());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.wsrp.model.WSRPConfiguredProducer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>configuredProducerId</column-name><column-value><![CDATA[");
		sb.append(getConfiguredProducerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalId</column-name><column-value><![CDATA[");
		sb.append(getPortalId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>namespace</column-name><column-value><![CDATA[");
		sb.append(getNamespace());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>producerURL</column-name><column-value><![CDATA[");
		sb.append(getProducerURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>producerVersion</column-name><column-value><![CDATA[");
		sb.append(getProducerVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>producerMarkupURL</column-name><column-value><![CDATA[");
		sb.append(getProducerMarkupURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationData</column-name><column-value><![CDATA[");
		sb.append(getRegistrationData());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationContext</column-name><column-value><![CDATA[");
		sb.append(getRegistrationContext());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceDescription</column-name><column-value><![CDATA[");
		sb.append(getServiceDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userCategoryMapping</column-name><column-value><![CDATA[");
		sb.append(getUserCategoryMapping());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>customUserProfile</column-name><column-value><![CDATA[");
		sb.append(getCustomUserProfile());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>identityPropagationType</column-name><column-value><![CDATA[");
		sb.append(getIdentityPropagationType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lifetimeTerminationTime</column-name><column-value><![CDATA[");
		sb.append(getLifetimeTerminationTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sdLastModified</column-name><column-value><![CDATA[");
		sb.append(getSdLastModified());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>entityVersion</column-name><column-value><![CDATA[");
		sb.append(getEntityVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _configuredProducerId;
	private String _name;
	private String _portalId;
	private String _namespace;
	private String _producerURL;
	private String _producerVersion;
	private String _producerMarkupURL;
	private int _status;
	private String _registrationData;
	private String _registrationContext;
	private String _serviceDescription;
	private String _userCategoryMapping;
	private String _customUserProfile;
	private String _identityPropagationType;
	private String _lifetimeTerminationTime;
	private long _sdLastModified;
	private int _entityVersion;
	private transient ExpandoBridge _expandoBridge;
}