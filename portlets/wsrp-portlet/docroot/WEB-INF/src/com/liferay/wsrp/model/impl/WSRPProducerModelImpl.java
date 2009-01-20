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
public class WSRPProducerModelImpl extends BaseModelImpl {
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
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
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
		if (producerId != _producerId) {
			_producerId = producerId;
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

	public boolean getStatus() {
		return _status;
	}

	public boolean isStatus() {
		return _status;
	}

	public void setStatus(boolean status) {
		if (status != _status) {
			_status = status;
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

	public String getInstanceName() {
		return GetterUtil.getString(_instanceName);
	}

	public void setInstanceName(String instanceName) {
		if (((instanceName == null) && (_instanceName != null)) ||
				((instanceName != null) && (_instanceName == null)) ||
				((instanceName != null) && (_instanceName != null) &&
				!instanceName.equals(_instanceName))) {
			_instanceName = instanceName;
		}
	}

	public boolean getRequiresRegistration() {
		return _requiresRegistration;
	}

	public boolean isRequiresRegistration() {
		return _requiresRegistration;
	}

	public void setRequiresRegistration(boolean requiresRegistration) {
		if (requiresRegistration != _requiresRegistration) {
			_requiresRegistration = requiresRegistration;
		}
	}

	public boolean getSupportsInbandRegistration() {
		return _supportsInbandRegistration;
	}

	public boolean isSupportsInbandRegistration() {
		return _supportsInbandRegistration;
	}

	public void setSupportsInbandRegistration(
		boolean supportsInbandRegistration) {
		if (supportsInbandRegistration != _supportsInbandRegistration) {
			_supportsInbandRegistration = supportsInbandRegistration;
		}
	}

	public String getVersion() {
		return GetterUtil.getString(_version);
	}

	public void setVersion(String version) {
		if (((version == null) && (_version != null)) ||
				((version != null) && (_version == null)) ||
				((version != null) && (_version != null) &&
				!version.equals(_version))) {
			_version = version;
		}
	}

	public String getOfferedPortlets() {
		return GetterUtil.getString(_offeredPortlets);
	}

	public void setOfferedPortlets(String offeredPortlets) {
		if (((offeredPortlets == null) && (_offeredPortlets != null)) ||
				((offeredPortlets != null) && (_offeredPortlets == null)) ||
				((offeredPortlets != null) && (_offeredPortlets != null) &&
				!offeredPortlets.equals(_offeredPortlets))) {
			_offeredPortlets = offeredPortlets;
		}
	}

	public String getProducerProfileMap() {
		return GetterUtil.getString(_producerProfileMap);
	}

	public void setProducerProfileMap(String producerProfileMap) {
		if (((producerProfileMap == null) && (_producerProfileMap != null)) ||
				((producerProfileMap != null) && (_producerProfileMap == null)) ||
				((producerProfileMap != null) && (_producerProfileMap != null) &&
				!producerProfileMap.equals(_producerProfileMap))) {
			_producerProfileMap = producerProfileMap;
		}
	}

	public String getRegistrationProperties() {
		return GetterUtil.getString(_registrationProperties);
	}

	public void setRegistrationProperties(String registrationProperties) {
		if (((registrationProperties == null) &&
				(_registrationProperties != null)) ||
				((registrationProperties != null) &&
				(_registrationProperties == null)) ||
				((registrationProperties != null) &&
				(_registrationProperties != null) &&
				!registrationProperties.equals(_registrationProperties))) {
			_registrationProperties = registrationProperties;
		}
	}

	public String getRegistrationValidatorClass() {
		return GetterUtil.getString(_registrationValidatorClass);
	}

	public void setRegistrationValidatorClass(String registrationValidatorClass) {
		if (((registrationValidatorClass == null) &&
				(_registrationValidatorClass != null)) ||
				((registrationValidatorClass != null) &&
				(_registrationValidatorClass == null)) ||
				((registrationValidatorClass != null) &&
				(_registrationValidatorClass != null) &&
				!registrationValidatorClass.equals(_registrationValidatorClass))) {
			_registrationValidatorClass = registrationValidatorClass;
		}
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

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		WSRPProducerImpl wsrpProducer = (WSRPProducerImpl)obj;

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

		WSRPProducerImpl wsrpProducer = null;

		try {
			wsrpProducer = (WSRPProducerImpl)obj;
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

	private long _producerId;
	private String _portalId;
	private boolean _status;
	private String _namespace;
	private String _instanceName;
	private boolean _requiresRegistration;
	private boolean _supportsInbandRegistration;
	private String _version;
	private String _offeredPortlets;
	private String _producerProfileMap;
	private String _registrationProperties;
	private String _registrationValidatorClass;
	private transient ExpandoBridge _expandoBridge;
}