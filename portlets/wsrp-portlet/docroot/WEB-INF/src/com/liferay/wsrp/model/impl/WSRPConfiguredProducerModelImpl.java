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
public class WSRPConfiguredProducerModelImpl extends BaseModelImpl {
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
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
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
		if (configuredProducerId != _configuredProducerId) {
			_configuredProducerId = configuredProducerId;
		}
	}

	public String getName() {
		return GetterUtil.getString(_name);
	}

	public void setName(String name) {
		if (((name == null) && (_name != null)) ||
				((name != null) && (_name == null)) ||
				((name != null) && (_name != null) && !name.equals(_name))) {
			_name = name;
		}
	}

	public String getPortalId() {
		return GetterUtil.getString(_portalId);
	}

	public void setPortalId(String portalId) {
		if (((portalId == null) && (_portalId != null)) ||
				((portalId != null) && (_portalId == null)) ||
				((portalId != null) && (_portalId != null) &&
				!portalId.equals(_portalId))) {
			_portalId = portalId;
		}
	}

	public String getNamespace() {
		return GetterUtil.getString(_namespace);
	}

	public void setNamespace(String namespace) {
		if (((namespace == null) && (_namespace != null)) ||
				((namespace != null) && (_namespace == null)) ||
				((namespace != null) && (_namespace != null) &&
				!namespace.equals(_namespace))) {
			_namespace = namespace;
		}
	}

	public String getProducerURL() {
		return GetterUtil.getString(_producerURL);
	}

	public void setProducerURL(String producerURL) {
		if (((producerURL == null) && (_producerURL != null)) ||
				((producerURL != null) && (_producerURL == null)) ||
				((producerURL != null) && (_producerURL != null) &&
				!producerURL.equals(_producerURL))) {
			_producerURL = producerURL;
		}
	}

	public String getProducerVersion() {
		return GetterUtil.getString(_producerVersion);
	}

	public void setProducerVersion(String producerVersion) {
		if (((producerVersion == null) && (_producerVersion != null)) ||
				((producerVersion != null) && (_producerVersion == null)) ||
				((producerVersion != null) && (_producerVersion != null) &&
				!producerVersion.equals(_producerVersion))) {
			_producerVersion = producerVersion;
		}
	}

	public String getProducerMarkupURL() {
		return GetterUtil.getString(_producerMarkupURL);
	}

	public void setProducerMarkupURL(String producerMarkupURL) {
		if (((producerMarkupURL == null) && (_producerMarkupURL != null)) ||
				((producerMarkupURL != null) && (_producerMarkupURL == null)) ||
				((producerMarkupURL != null) && (_producerMarkupURL != null) &&
				!producerMarkupURL.equals(_producerMarkupURL))) {
			_producerMarkupURL = producerMarkupURL;
		}
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		if (status != _status) {
			_status = status;
		}
	}

	public String getRegistrationData() {
		return GetterUtil.getString(_registrationData);
	}

	public void setRegistrationData(String registrationData) {
		if (((registrationData == null) && (_registrationData != null)) ||
				((registrationData != null) && (_registrationData == null)) ||
				((registrationData != null) && (_registrationData != null) &&
				!registrationData.equals(_registrationData))) {
			_registrationData = registrationData;
		}
	}

	public String getRegistrationContext() {
		return GetterUtil.getString(_registrationContext);
	}

	public void setRegistrationContext(String registrationContext) {
		if (((registrationContext == null) && (_registrationContext != null)) ||
				((registrationContext != null) &&
				(_registrationContext == null)) ||
				((registrationContext != null) &&
				(_registrationContext != null) &&
				!registrationContext.equals(_registrationContext))) {
			_registrationContext = registrationContext;
		}
	}

	public String getServiceDescription() {
		return GetterUtil.getString(_serviceDescription);
	}

	public void setServiceDescription(String serviceDescription) {
		if (((serviceDescription == null) && (_serviceDescription != null)) ||
				((serviceDescription != null) && (_serviceDescription == null)) ||
				((serviceDescription != null) && (_serviceDescription != null) &&
				!serviceDescription.equals(_serviceDescription))) {
			_serviceDescription = serviceDescription;
		}
	}

	public String getUserCategoryMapping() {
		return GetterUtil.getString(_userCategoryMapping);
	}

	public void setUserCategoryMapping(String userCategoryMapping) {
		if (((userCategoryMapping == null) && (_userCategoryMapping != null)) ||
				((userCategoryMapping != null) &&
				(_userCategoryMapping == null)) ||
				((userCategoryMapping != null) &&
				(_userCategoryMapping != null) &&
				!userCategoryMapping.equals(_userCategoryMapping))) {
			_userCategoryMapping = userCategoryMapping;
		}
	}

	public String getCustomUserProfile() {
		return GetterUtil.getString(_customUserProfile);
	}

	public void setCustomUserProfile(String customUserProfile) {
		if (((customUserProfile == null) && (_customUserProfile != null)) ||
				((customUserProfile != null) && (_customUserProfile == null)) ||
				((customUserProfile != null) && (_customUserProfile != null) &&
				!customUserProfile.equals(_customUserProfile))) {
			_customUserProfile = customUserProfile;
		}
	}

	public String getIdentityPropagationType() {
		return GetterUtil.getString(_identityPropagationType);
	}

	public void setIdentityPropagationType(String identityPropagationType) {
		if (((identityPropagationType == null) &&
				(_identityPropagationType != null)) ||
				((identityPropagationType != null) &&
				(_identityPropagationType == null)) ||
				((identityPropagationType != null) &&
				(_identityPropagationType != null) &&
				!identityPropagationType.equals(_identityPropagationType))) {
			_identityPropagationType = identityPropagationType;
		}
	}

	public String getLifetimeTerminationTime() {
		return GetterUtil.getString(_lifetimeTerminationTime);
	}

	public void setLifetimeTerminationTime(String lifetimeTerminationTime) {
		if (((lifetimeTerminationTime == null) &&
				(_lifetimeTerminationTime != null)) ||
				((lifetimeTerminationTime != null) &&
				(_lifetimeTerminationTime == null)) ||
				((lifetimeTerminationTime != null) &&
				(_lifetimeTerminationTime != null) &&
				!lifetimeTerminationTime.equals(_lifetimeTerminationTime))) {
			_lifetimeTerminationTime = lifetimeTerminationTime;
		}
	}

	public long getSdLastModified() {
		return _sdLastModified;
	}

	public void setSdLastModified(long sdLastModified) {
		if (sdLastModified != _sdLastModified) {
			_sdLastModified = sdLastModified;
		}
	}

	public int getEntityVersion() {
		return _entityVersion;
	}

	public void setEntityVersion(int entityVersion) {
		if (entityVersion != _entityVersion) {
			_entityVersion = entityVersion;
		}
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

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		WSRPConfiguredProducerImpl wsrpConfiguredProducer = (WSRPConfiguredProducerImpl)obj;

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

		WSRPConfiguredProducerImpl wsrpConfiguredProducer = null;

		try {
			wsrpConfiguredProducer = (WSRPConfiguredProducerImpl)obj;
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