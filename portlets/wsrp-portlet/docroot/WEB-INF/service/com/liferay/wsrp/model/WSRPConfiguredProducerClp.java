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

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

/**
 * <a href="WSRPConfiguredProducerClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerClp extends BaseModelImpl<WSRPConfiguredProducer>
	implements WSRPConfiguredProducer {
	public WSRPConfiguredProducerClp() {
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

	public WSRPConfiguredProducer toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WSRPConfiguredProducer model = new WSRPConfiguredProducerClp();

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

	public Object clone() {
		WSRPConfiguredProducerClp clone = new WSRPConfiguredProducerClp();

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

		WSRPConfiguredProducerClp wsrpConfiguredProducer = null;

		try {
			wsrpConfiguredProducer = (WSRPConfiguredProducerClp)obj;
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
}