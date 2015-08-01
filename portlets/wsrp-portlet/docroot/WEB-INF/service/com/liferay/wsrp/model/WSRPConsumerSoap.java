/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class WSRPConsumerSoap implements Serializable {
	public static WSRPConsumerSoap toSoapModel(WSRPConsumer model) {
		WSRPConsumerSoap soapModel = new WSRPConsumerSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setWsrpConsumerId(model.getWsrpConsumerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setWsdl(model.getWsdl());
		soapModel.setRegistrationContextString(model.getRegistrationContextString());
		soapModel.setRegistrationPropertiesString(model.getRegistrationPropertiesString());
		soapModel.setForwardCookies(model.getForwardCookies());
		soapModel.setForwardHeaders(model.getForwardHeaders());
		soapModel.setMarkupCharacterSets(model.getMarkupCharacterSets());
		soapModel.setLastPublishDate(model.getLastPublishDate());

		return soapModel;
	}

	public static WSRPConsumerSoap[] toSoapModels(WSRPConsumer[] models) {
		WSRPConsumerSoap[] soapModels = new WSRPConsumerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WSRPConsumerSoap[][] toSoapModels(WSRPConsumer[][] models) {
		WSRPConsumerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WSRPConsumerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WSRPConsumerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WSRPConsumerSoap[] toSoapModels(List<WSRPConsumer> models) {
		List<WSRPConsumerSoap> soapModels = new ArrayList<WSRPConsumerSoap>(models.size());

		for (WSRPConsumer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPConsumerSoap[soapModels.size()]);
	}

	public WSRPConsumerSoap() {
	}

	public long getPrimaryKey() {
		return _wsrpConsumerId;
	}

	public void setPrimaryKey(long pk) {
		setWsrpConsumerId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getWsdl() {
		return _wsdl;
	}

	public void setWsdl(String wsdl) {
		_wsdl = wsdl;
	}

	public String getRegistrationContextString() {
		return _registrationContextString;
	}

	public void setRegistrationContextString(String registrationContextString) {
		_registrationContextString = registrationContextString;
	}

	public String getRegistrationPropertiesString() {
		return _registrationPropertiesString;
	}

	public void setRegistrationPropertiesString(
		String registrationPropertiesString) {
		_registrationPropertiesString = registrationPropertiesString;
	}

	public String getForwardCookies() {
		return _forwardCookies;
	}

	public void setForwardCookies(String forwardCookies) {
		_forwardCookies = forwardCookies;
	}

	public String getForwardHeaders() {
		return _forwardHeaders;
	}

	public void setForwardHeaders(String forwardHeaders) {
		_forwardHeaders = forwardHeaders;
	}

	public String getMarkupCharacterSets() {
		return _markupCharacterSets;
	}

	public void setMarkupCharacterSets(String markupCharacterSets) {
		_markupCharacterSets = markupCharacterSets;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	private String _uuid;
	private long _wsrpConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _wsdl;
	private String _registrationContextString;
	private String _registrationPropertiesString;
	private String _forwardCookies;
	private String _forwardHeaders;
	private String _markupCharacterSets;
	private Date _lastPublishDate;
}