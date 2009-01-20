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
 * <a href="WSRPConfiguredProducerSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerSoap implements Serializable {
	public static WSRPConfiguredProducerSoap toSoapModel(
		WSRPConfiguredProducer model) {
		WSRPConfiguredProducerSoap soapModel = new WSRPConfiguredProducerSoap();

		soapModel.setConfiguredProducerId(model.getConfiguredProducerId());
		soapModel.setName(model.getName());
		soapModel.setPortalId(model.getPortalId());
		soapModel.setNamespace(model.getNamespace());
		soapModel.setProducerURL(model.getProducerURL());
		soapModel.setProducerVersion(model.getProducerVersion());
		soapModel.setProducerMarkupURL(model.getProducerMarkupURL());
		soapModel.setStatus(model.getStatus());
		soapModel.setRegistrationData(model.getRegistrationData());
		soapModel.setRegistrationContext(model.getRegistrationContext());
		soapModel.setServiceDescription(model.getServiceDescription());
		soapModel.setUserCategoryMapping(model.getUserCategoryMapping());
		soapModel.setCustomUserProfile(model.getCustomUserProfile());
		soapModel.setIdentityPropagationType(model.getIdentityPropagationType());
		soapModel.setLifetimeTerminationTime(model.getLifetimeTerminationTime());
		soapModel.setSdLastModified(model.getSdLastModified());
		soapModel.setEntityVersion(model.getEntityVersion());

		return soapModel;
	}

	public static WSRPConfiguredProducerSoap[] toSoapModels(
		List<WSRPConfiguredProducer> models) {
		List<WSRPConfiguredProducerSoap> soapModels = new ArrayList<WSRPConfiguredProducerSoap>(models.size());

		for (WSRPConfiguredProducer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPConfiguredProducerSoap[soapModels.size()]);
	}

	public WSRPConfiguredProducerSoap() {
	}

	public long getPrimaryKey() {
		return _configuredProducerId;
	}

	public void setPrimaryKey(long pk) {
		setConfiguredProducerId(pk);
	}

	public long getConfiguredProducerId() {
		return _configuredProducerId;
	}

	public void setConfiguredProducerId(long configuredProducerId) {
		_configuredProducerId = configuredProducerId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPortalId() {
		return _portalId;
	}

	public void setPortalId(String portalId) {
		_portalId = portalId;
	}

	public String getNamespace() {
		return _namespace;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public String getProducerURL() {
		return _producerURL;
	}

	public void setProducerURL(String producerURL) {
		_producerURL = producerURL;
	}

	public String getProducerVersion() {
		return _producerVersion;
	}

	public void setProducerVersion(String producerVersion) {
		_producerVersion = producerVersion;
	}

	public String getProducerMarkupURL() {
		return _producerMarkupURL;
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
		return _registrationData;
	}

	public void setRegistrationData(String registrationData) {
		_registrationData = registrationData;
	}

	public String getRegistrationContext() {
		return _registrationContext;
	}

	public void setRegistrationContext(String registrationContext) {
		_registrationContext = registrationContext;
	}

	public String getServiceDescription() {
		return _serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		_serviceDescription = serviceDescription;
	}

	public String getUserCategoryMapping() {
		return _userCategoryMapping;
	}

	public void setUserCategoryMapping(String userCategoryMapping) {
		_userCategoryMapping = userCategoryMapping;
	}

	public String getCustomUserProfile() {
		return _customUserProfile;
	}

	public void setCustomUserProfile(String customUserProfile) {
		_customUserProfile = customUserProfile;
	}

	public String getIdentityPropagationType() {
		return _identityPropagationType;
	}

	public void setIdentityPropagationType(String identityPropagationType) {
		_identityPropagationType = identityPropagationType;
	}

	public String getLifetimeTerminationTime() {
		return _lifetimeTerminationTime;
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
}