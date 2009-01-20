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
 * <a href="WSRPProducerClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerClp extends BaseModelImpl implements WSRPProducer {
	public WSRPProducerClp() {
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

	public WSRPProducer toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WSRPProducer model = new WSRPProducerClp();

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

	public Object clone() {
		WSRPProducerClp clone = new WSRPProducerClp();

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

		WSRPProducerClp wsrpProducer = (WSRPProducerClp)obj;

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

		WSRPProducerClp wsrpProducer = null;

		try {
			wsrpProducer = (WSRPProducerClp)obj;
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
}