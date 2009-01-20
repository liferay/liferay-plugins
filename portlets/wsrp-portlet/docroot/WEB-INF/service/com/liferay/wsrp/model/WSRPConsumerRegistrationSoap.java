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
 * <a href="WSRPConsumerRegistrationSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerRegistrationSoap implements Serializable {
	public static WSRPConsumerRegistrationSoap toSoapModel(
		WSRPConsumerRegistration model) {
		WSRPConsumerRegistrationSoap soapModel = new WSRPConsumerRegistrationSoap();

		soapModel.setConsumerRegistrationId(model.getConsumerRegistrationId());
		soapModel.setConsumerName(model.getConsumerName());
		soapModel.setStatus(model.getStatus());
		soapModel.setRegistrationHandle(model.getRegistrationHandle());
		soapModel.setRegistrationData(model.getRegistrationData());
		soapModel.setLifetimeTerminationTime(model.getLifetimeTerminationTime());
		soapModel.setProducerKey(model.getProducerKey());

		return soapModel;
	}

	public static WSRPConsumerRegistrationSoap[] toSoapModels(
		List<WSRPConsumerRegistration> models) {
		List<WSRPConsumerRegistrationSoap> soapModels = new ArrayList<WSRPConsumerRegistrationSoap>(models.size());

		for (WSRPConsumerRegistration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WSRPConsumerRegistrationSoap[soapModels.size()]);
	}

	public WSRPConsumerRegistrationSoap() {
	}

	public long getPrimaryKey() {
		return _consumerRegistrationId;
	}

	public void setPrimaryKey(long pk) {
		setConsumerRegistrationId(pk);
	}

	public long getConsumerRegistrationId() {
		return _consumerRegistrationId;
	}

	public void setConsumerRegistrationId(long consumerRegistrationId) {
		_consumerRegistrationId = consumerRegistrationId;
	}

	public String getConsumerName() {
		return _consumerName;
	}

	public void setConsumerName(String consumerName) {
		_consumerName = consumerName;
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

	public String getRegistrationHandle() {
		return _registrationHandle;
	}

	public void setRegistrationHandle(String registrationHandle) {
		_registrationHandle = registrationHandle;
	}

	public String getRegistrationData() {
		return _registrationData;
	}

	public void setRegistrationData(String registrationData) {
		_registrationData = registrationData;
	}

	public String getLifetimeTerminationTime() {
		return _lifetimeTerminationTime;
	}

	public void setLifetimeTerminationTime(String lifetimeTerminationTime) {
		_lifetimeTerminationTime = lifetimeTerminationTime;
	}

	public String getProducerKey() {
		return _producerKey;
	}

	public void setProducerKey(String producerKey) {
		_producerKey = producerKey;
	}

	private long _consumerRegistrationId;
	private String _consumerName;
	private boolean _status;
	private String _registrationHandle;
	private String _registrationData;
	private String _lifetimeTerminationTime;
	private String _producerKey;
}