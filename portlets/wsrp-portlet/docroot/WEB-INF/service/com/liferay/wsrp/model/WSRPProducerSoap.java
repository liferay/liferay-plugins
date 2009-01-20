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

package com.liferay.wsrp.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WSRPProducerSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerSoap implements Serializable {
	public static WSRPProducerSoap toSoapModel(WSRPProducer model) {
		WSRPProducerSoap soapModel = new WSRPProducerSoap();

		soapModel.setProducerId(model.getProducerId());
		soapModel.setPortalId(model.getPortalId());
		soapModel.setStatus(model.getStatus());
		soapModel.setNamespace(model.getNamespace());
		soapModel.setInstanceName(model.getInstanceName());
		soapModel.setRequiresRegistration(model.getRequiresRegistration());
		soapModel.setSupportsInbandRegistration(model.getSupportsInbandRegistration());
		soapModel.setVersion(model.getVersion());
		soapModel.setOfferedPortlets(model.getOfferedPortlets());
		soapModel.setProducerProfileMap(model.getProducerProfileMap());
		soapModel.setRegistrationProperties(model.getRegistrationProperties());
		soapModel.setRegistrationValidatorClass(model.getRegistrationValidatorClass());

		return soapModel;
	}

	public static WSRPProducerSoap[] toSoapModels(List<WSRPProducer> models) {
		List<WSRPProducerSoap> soapModels = new ArrayList<WSRPProducerSoap>(models.size());

		for (WSRPProducer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPProducerSoap[soapModels.size()]);
	}

	public WSRPProducerSoap() {
	}

	public long getPrimaryKey() {
		return _producerId;
	}

	public void setPrimaryKey(long pk) {
		setProducerId(pk);
	}

	public long getProducerId() {
		return _producerId;
	}

	public void setProducerId(long producerId) {
		_producerId = producerId;
	}

	public String getPortalId() {
		return _portalId;
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
		return _namespace;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public String getInstanceName() {
		return _instanceName;
	}

	public void setInstanceName(String instanceName) {
		_instanceName = instanceName;
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
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public String getOfferedPortlets() {
		return _offeredPortlets;
	}

	public void setOfferedPortlets(String offeredPortlets) {
		_offeredPortlets = offeredPortlets;
	}

	public String getProducerProfileMap() {
		return _producerProfileMap;
	}

	public void setProducerProfileMap(String producerProfileMap) {
		_producerProfileMap = producerProfileMap;
	}

	public String getRegistrationProperties() {
		return _registrationProperties;
	}

	public void setRegistrationProperties(String registrationProperties) {
		_registrationProperties = registrationProperties;
	}

	public String getRegistrationValidatorClass() {
		return _registrationValidatorClass;
	}

	public void setRegistrationValidatorClass(String registrationValidatorClass) {
		_registrationValidatorClass = registrationValidatorClass;
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
}