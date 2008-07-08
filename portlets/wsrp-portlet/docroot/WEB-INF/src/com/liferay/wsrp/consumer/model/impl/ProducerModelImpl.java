/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.consumer.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.model.ProducerSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ProducerModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProducerModelImpl extends BaseModelImpl {
	public static final String TABLE_NAME = "WSRP_Producer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", new Integer(Types.VARCHAR) },
			

			{ "producerId", new Integer(Types.BIGINT) },
			

			{ "groupId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "userId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "name", new Integer(Types.VARCHAR) },
			

			{ "wsdlURL", new Integer(Types.VARCHAR) },
			

			{ "markupEndpoint", new Integer(Types.VARCHAR) },
			

			{ "portletManagementEndpoint", new Integer(Types.VARCHAR) },
			

			{ "registrationEndpoint", new Integer(Types.VARCHAR) },
			

			{ "serviceDescriptionEndpoint", new Integer(Types.VARCHAR) },
			

			{ "registrationContext", new Integer(Types.CLOB) },
			

			{ "serviceDescription", new Integer(Types.CLOB) }
		};
	public static final String TABLE_SQL_CREATE = "create table WSRP_Producer (uuid_ VARCHAR(75) null,producerId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,wsdlURL VARCHAR(300) null,markupEndpoint VARCHAR(300) null,portletManagementEndpoint VARCHAR(300) null,registrationEndpoint VARCHAR(300) null,serviceDescriptionEndpoint VARCHAR(300) null,registrationContext TEXT null,serviceDescription TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table WSRP_Producer";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.wsrp.consumer.model.Producer"),
			true);

	public static Producer toModel(ProducerSoap soapModel) {
		Producer model = new ProducerImpl();

		model.setUuid(soapModel.getUuid());
		model.setProducerId(soapModel.getProducerId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setWsdlURL(soapModel.getWsdlURL());
		model.setMarkupEndpoint(soapModel.getMarkupEndpoint());
		model.setPortletManagementEndpoint(soapModel.getPortletManagementEndpoint());
		model.setRegistrationEndpoint(soapModel.getRegistrationEndpoint());
		model.setServiceDescriptionEndpoint(soapModel.getServiceDescriptionEndpoint());
		model.setRegistrationContext(soapModel.getRegistrationContext());
		model.setServiceDescription(soapModel.getServiceDescription());

		return model;
	}

	public static List<Producer> toModels(ProducerSoap[] soapModels) {
		List<Producer> models = new ArrayList<Producer>(soapModels.length);

		for (ProducerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.wsrp.consumer.model.Producer"));

	public ProducerModelImpl() {
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

	public String getUuid() {
		return GetterUtil.getString(_uuid);
	}

	public void setUuid(String uuid) {
		if ((uuid != null) && (uuid != _uuid)) {
			_uuid = uuid;
		}
	}

	public long getProducerId() {
		return _producerId;
	}

	public void setProducerId(long producerId) {
		if (producerId != _producerId) {
			_producerId = producerId;
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		if (groupId != _groupId) {
			_groupId = groupId;
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		if (companyId != _companyId) {
			_companyId = companyId;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		if (userId != _userId) {
			_userId = userId;
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		if (((createDate == null) && (_createDate != null)) ||
				((createDate != null) && (_createDate == null)) ||
				((createDate != null) && (_createDate != null) &&
				!createDate.equals(_createDate))) {
			_createDate = createDate;
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (((modifiedDate == null) && (_modifiedDate != null)) ||
				((modifiedDate != null) && (_modifiedDate == null)) ||
				((modifiedDate != null) && (_modifiedDate != null) &&
				!modifiedDate.equals(_modifiedDate))) {
			_modifiedDate = modifiedDate;
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

	public String getWsdlURL() {
		return GetterUtil.getString(_wsdlURL);
	}

	public void setWsdlURL(String wsdlURL) {
		if (((wsdlURL == null) && (_wsdlURL != null)) ||
				((wsdlURL != null) && (_wsdlURL == null)) ||
				((wsdlURL != null) && (_wsdlURL != null) &&
				!wsdlURL.equals(_wsdlURL))) {
			_wsdlURL = wsdlURL;
		}
	}

	public String getMarkupEndpoint() {
		return GetterUtil.getString(_markupEndpoint);
	}

	public void setMarkupEndpoint(String markupEndpoint) {
		if (((markupEndpoint == null) && (_markupEndpoint != null)) ||
				((markupEndpoint != null) && (_markupEndpoint == null)) ||
				((markupEndpoint != null) && (_markupEndpoint != null) &&
				!markupEndpoint.equals(_markupEndpoint))) {
			_markupEndpoint = markupEndpoint;
		}
	}

	public String getPortletManagementEndpoint() {
		return GetterUtil.getString(_portletManagementEndpoint);
	}

	public void setPortletManagementEndpoint(String portletManagementEndpoint) {
		if (((portletManagementEndpoint == null) &&
				(_portletManagementEndpoint != null)) ||
				((portletManagementEndpoint != null) &&
				(_portletManagementEndpoint == null)) ||
				((portletManagementEndpoint != null) &&
				(_portletManagementEndpoint != null) &&
				!portletManagementEndpoint.equals(_portletManagementEndpoint))) {
			_portletManagementEndpoint = portletManagementEndpoint;
		}
	}

	public String getRegistrationEndpoint() {
		return GetterUtil.getString(_registrationEndpoint);
	}

	public void setRegistrationEndpoint(String registrationEndpoint) {
		if (((registrationEndpoint == null) && (_registrationEndpoint != null)) ||
				((registrationEndpoint != null) &&
				(_registrationEndpoint == null)) ||
				((registrationEndpoint != null) &&
				(_registrationEndpoint != null) &&
				!registrationEndpoint.equals(_registrationEndpoint))) {
			_registrationEndpoint = registrationEndpoint;
		}
	}

	public String getServiceDescriptionEndpoint() {
		return GetterUtil.getString(_serviceDescriptionEndpoint);
	}

	public void setServiceDescriptionEndpoint(String serviceDescriptionEndpoint) {
		if (((serviceDescriptionEndpoint == null) &&
				(_serviceDescriptionEndpoint != null)) ||
				((serviceDescriptionEndpoint != null) &&
				(_serviceDescriptionEndpoint == null)) ||
				((serviceDescriptionEndpoint != null) &&
				(_serviceDescriptionEndpoint != null) &&
				!serviceDescriptionEndpoint.equals(_serviceDescriptionEndpoint))) {
			_serviceDescriptionEndpoint = serviceDescriptionEndpoint;
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

	public Producer toEscapedModel() {
		if (isEscapedModel()) {
			return (Producer)this;
		}
		else {
			Producer model = new ProducerImpl();

			model.setEscapedModel(true);

			model.setUuid(HtmlUtil.escape(getUuid()));
			model.setProducerId(getProducerId());
			model.setGroupId(getGroupId());
			model.setCompanyId(getCompanyId());
			model.setUserId(getUserId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setName(HtmlUtil.escape(getName()));
			model.setWsdlURL(HtmlUtil.escape(getWsdlURL()));
			model.setMarkupEndpoint(HtmlUtil.escape(getMarkupEndpoint()));
			model.setPortletManagementEndpoint(HtmlUtil.escape(
					getPortletManagementEndpoint()));
			model.setRegistrationEndpoint(HtmlUtil.escape(
					getRegistrationEndpoint()));
			model.setServiceDescriptionEndpoint(HtmlUtil.escape(
					getServiceDescriptionEndpoint()));
			model.setRegistrationContext(HtmlUtil.escape(
					getRegistrationContext()));
			model.setServiceDescription(HtmlUtil.escape(getServiceDescription()));

			model = (Producer)Proxy.newProxyInstance(Producer.class.getClassLoader(),
					new Class[] { Producer.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		ProducerImpl clone = new ProducerImpl();

		clone.setUuid(getUuid());
		clone.setProducerId(getProducerId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setWsdlURL(getWsdlURL());
		clone.setMarkupEndpoint(getMarkupEndpoint());
		clone.setPortletManagementEndpoint(getPortletManagementEndpoint());
		clone.setRegistrationEndpoint(getRegistrationEndpoint());
		clone.setServiceDescriptionEndpoint(getServiceDescriptionEndpoint());
		clone.setRegistrationContext(getRegistrationContext());
		clone.setServiceDescription(getServiceDescription());

		return clone;
	}

	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}

		ProducerImpl producer = (ProducerImpl)obj;

		int value = 0;

		value = getName().toLowerCase()
					.compareTo(producer.getName().toLowerCase());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		ProducerImpl producer = null;

		try {
			producer = (ProducerImpl)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = producer.getPrimaryKey();

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

	private String _uuid;
	private long _producerId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _wsdlURL;
	private String _markupEndpoint;
	private String _portletManagementEndpoint;
	private String _registrationEndpoint;
	private String _serviceDescriptionEndpoint;
	private String _registrationContext;
	private String _serviceDescription;
}