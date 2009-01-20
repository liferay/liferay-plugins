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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.wsrp.producer.impl;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.SetUtil;
import com.liferay.wsrp.model.WSRPConsumerRegistration;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPConsumerRegistrationLocalServiceUtil;

import com.sun.portal.wsrp.common.KeyGenerator;
import com.sun.portal.wsrp.common.LeaseTime;
import com.sun.portal.wsrp.common.WSRPFactory;
import com.sun.portal.wsrp.common.stubs.v2.PropertyDescription;
import com.sun.portal.wsrp.common.stubs.v2.RegistrationData;
import com.sun.portal.wsrp.producer.AbstractProducer;
import com.sun.portal.wsrp.producer.ProducerException;
import com.sun.portal.wsrp.producer.ProducerVersion;
import com.sun.portal.wsrp.producer.ProfileMapManager;
import com.sun.portal.wsrp.producer.impl.file.RegistrationPropertiesHelper;
import com.sun.portal.wsrp.producer.registration.RegistrationRecord;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <a href="ProducerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 * @author Brian Wing Shun Chan
 *
 */
public class ProducerImpl extends AbstractProducer {

	public ProducerImpl(WSRPProducer wsrpProducer) {
		_producerModel = wsrpProducer;
	}

	public String addRegistration(RegistrationRecord registrationRecord)
		throws ProducerException {

		try {
			String registrationHandle = KeyGenerator.generateKey();
			String registrationData =
				WSRPFactory.getInstance().getRegistrationDataXML(
					registrationRecord.getRegistrationData());
			String lifetime =
				getLifeTimeString(registrationRecord.getLifetime());

			WSRPConsumerRegistrationLocalServiceUtil.addConsumerRegistration(
				registrationRecord.getConsumerName(), true, registrationHandle,
				registrationData, lifetime, _producerModel.getInstanceName());

			return registrationHandle;
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public void addRegistrationPropertyDescription(
		PropertyDescription propertyDescription) {

		String registrationProperties =
			RegistrationPropertiesHelper.addRegistrationProperty(
				_producerModel.getRegistrationProperties(),
				propertyDescription);

		_producerModel.setRegistrationProperties(registrationProperties);
	}

	public String getNamespace() {
		return _producerModel.getNamespace();
	}

	public Set<String> getOfferedPortletNames() {
		return SetUtil.fromArray(
			StringUtil.split(_producerModel.getOfferedPortlets()));
	}

	public String getPortalId() {
		return _producerModel.getPortalId();
	}

	public String getProducerKey() {
		return _producerModel.getInstanceName();
	}

	public ProfileMapManager getProfileMapManager() {
		return new ProfileMapManagerImpl();
	}

	public LeaseTime getRegistrationLifetime(String registrationHandle)
		throws ProducerException {

		LeaseTime leaseTime = null;

		WSRPConsumerRegistration consumerRegistration =
			getConsumerRegistrationByHandle(registrationHandle);

		String terminationTime =
			consumerRegistration.getLifetimeTerminationTime();

		if ((terminationTime != null) && Validator.isNotNull(terminationTime)) {
			try {
				DatatypeFactory datetypeFactory = DatatypeFactory.newInstance();

				leaseTime = new LeaseTime();

				leaseTime.setTerminationTime(
					datetypeFactory.newXMLGregorianCalendar(terminationTime));
			}
			catch (DatatypeConfigurationException dce) {
				throw new ProducerException(dce);
			}
		}

		return leaseTime;
	}

	public Set<PropertyDescription> getRegistrationPropertyDescriptions(
		Set<String> desiredLocales) {

		return RegistrationPropertiesHelper.getRegistrationPropertiesFromXML(
			_producerModel.getRegistrationProperties());
	}

	public RegistrationRecord getRegistrationRecord(String registrationHandle)
		throws ProducerException {

		WSRPConsumerRegistration consumerRegistration =
			getConsumerRegistrationByHandle(registrationHandle);

		return getRegistrationRecord(consumerRegistration);
	}

	public Set<RegistrationRecord> getRegistrationRecords()
		throws ProducerException {

		try{
			List<WSRPConsumerRegistration> consumerRegistrations =
				WSRPConsumerRegistrationLocalServiceUtil.
					getConsumerRegistrations(_producerModel.getInstanceName());

			Set<RegistrationRecord> registrationRecords =
				new HashSet<RegistrationRecord>();

			for (WSRPConsumerRegistration consumerRegistration :
					consumerRegistrations) {

				RegistrationRecord registrationRecord = getRegistrationRecord(
					consumerRegistration);

				registrationRecords.add(registrationRecord);
			}

			return registrationRecords;
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public boolean getRegistrationStatus(String registrationHandle)
		throws ProducerException {

		WSRPConsumerRegistration consumerRegistration =
			getConsumerRegistrationByHandle(registrationHandle);

		return consumerRegistration.getStatus();
	}

	public String getRegistrationValidatorClassName() {
		return _producerModel.getRegistrationValidatorClass();
	}

	public ProducerVersion getVersion() {
		return ProducerVersion.fromValue(_producerModel.getVersion());
	}

	public boolean inbandRegistrationSupported() {
		return _producerModel.isSupportsInbandRegistration();
	}

	public boolean isEnabled() {
		return _producerModel.getStatus();
	}

	public boolean isValidRegistration(String registrationHandle) {
		boolean validRegistration = true;

		try {
			getConsumerRegistrationByHandle(registrationHandle);
		}
		catch (Exception e) {
			validRegistration = false;
		}

		return validRegistration;
	}

	public void modifyRegistration(RegistrationRecord registrationRecord)
		throws ProducerException {

		WSRPConsumerRegistration consumerRegistration =
			getConsumerRegistrationByHandle(
				registrationRecord.getRegistrationHandle());

		try {
			consumerRegistration.setRegistrationData(
				WSRPFactory.getInstance().getRegistrationDataXML(
					registrationRecord.getRegistrationData()));
			consumerRegistration.setLifetimeTerminationTime(
				getLifeTimeString(registrationRecord.getLifetime()));

			WSRPConsumerRegistrationLocalServiceUtil.
				updateWSRPConsumerRegistration(consumerRegistration);
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public void removeRegistrationPropertyDescriptions(
		Set<String> propertyNames) {

		String registrationProperties =
			RegistrationPropertiesHelper.removeRegistrationProperties(
				_producerModel.getRegistrationProperties(), propertyNames);

		_producerModel.setRegistrationProperties(registrationProperties);
	}

	public void removeRegistrations(Set<String> registrationHandles)
		throws ProducerException {

		try {
			for (String registrationHandle : registrationHandles) {
				WSRPConsumerRegistration consumerRegistration =
					getConsumerRegistrationByHandle(registrationHandle);

				WSRPConsumerRegistrationLocalServiceUtil.
					deleteWSRPConsumerRegistration(consumerRegistration);
			}
		}
		catch (Exception e) {
			throw new ProducerException(
				"Unable to delete consumer registration", e);
		}
	}

	public boolean requiresRegistration() {
		return _producerModel.getRequiresRegistration();
	}

	public void setInbandRegistrationSupported(boolean value) {
		_producerModel.setSupportsInbandRegistration(value);
	}

	public void setIsEnabled(boolean status) {
		_producerModel.setStatus(status);
	}

	public void setOfferedPortletNames(Set<String> offeredPortlets) {
		String offeredPortletsString = StringUtil.merge(offeredPortlets);

		_producerModel.setOfferedPortlets(offeredPortletsString);
	}

	public void setPortalId(String portalId) {
		_producerModel.setPortalId(portalId);
	}

	public void setRegistrationLifetime(
			String registrationHandle, LeaseTime leaseTime)
		throws ProducerException {

		String lifetime = getLifeTimeString(leaseTime);

		try {
			WSRPConsumerRegistration consumerRegistration =
				getConsumerRegistrationByHandle(registrationHandle);

			consumerRegistration.setLifetimeTerminationTime(lifetime);

			WSRPConsumerRegistrationLocalServiceUtil.
				updateWSRPConsumerRegistration(consumerRegistration);
		}
		catch (Exception e) {
			throw new ProducerException(e.getMessage());
		}
	}

	public void setRegistrationStatus(String registrationHandle, boolean value)
		throws ProducerException {

		try {
			WSRPConsumerRegistration consumerRegistration =
				getConsumerRegistrationByHandle(registrationHandle);

			if (value != consumerRegistration.getStatus()) {
				consumerRegistration.setStatus(value);

				WSRPConsumerRegistrationLocalServiceUtil.
					updateWSRPConsumerRegistration(consumerRegistration);
			}
		}
		catch (Exception e) {
			throw new ProducerException(e.getMessage());
		}
	}

	public void setRegistrationValidatorClassName(String validatorClassName) {
		_producerModel.setRegistrationValidatorClass(validatorClassName);
	}

	public void setRequiresRegistration(boolean value) {
		_producerModel.setRequiresRegistration(value);
	}

	public void setVersion(ProducerVersion version) {
		_producerModel.setVersion(version.value());
	}

	protected WSRPConsumerRegistration getConsumerRegistrationByHandle(
			String registrationHandle)
		throws ProducerException {

		try {
			return WSRPConsumerRegistrationLocalServiceUtil.
				getConsumerRegistration(
					registrationHandle, _producerModel.getInstanceName());
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	protected String getLifeTimeString(LeaseTime leaseTime) {
		String lifetime = null;

		if (leaseTime != null) {
			XMLGregorianCalendar terminationTime =
				leaseTime.getTerminationTime();

			if (terminationTime != null) {
				lifetime = terminationTime.toXMLFormat();
			}
		}

		return lifetime;
	}

	protected WSRPProducer getProducerModel() {
		return _producerModel;
	}

	protected RegistrationRecord getRegistrationRecord(
			WSRPConsumerRegistration consumerRegistration)
		throws ProducerException {

		try {
			RegistrationData registrationData =
				WSRPFactory.getInstance().getRegistrationData(
					consumerRegistration.getRegistrationData());

			RegistrationRecord registrationRecord = new RegistrationRecord(
				consumerRegistration.getRegistrationHandle(),
				consumerRegistration.getStatus(), registrationData, null);

			return registrationRecord;
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	private WSRPProducer _producerModel;

}