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

package com.liferay.wsrp.consumer.producermanager;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;
import com.liferay.wsrp.consumer.admin.WSRPChannelManagerImpl;
import com.liferay.wsrp.model.WSRPConfiguredProducer;
import com.liferay.wsrp.model.WSRPPortlet;
import com.liferay.wsrp.service.WSRPConfiguredProducerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPPortletLocalServiceUtil;

import com.sun.portal.wsrp.common.LeaseTime;
import com.sun.portal.wsrp.common.WSRPVersion;
import com.sun.portal.wsrp.common.stubs.v2.RegistrationContext;
import com.sun.portal.wsrp.common.stubs.v2.RegistrationData;
import com.sun.portal.wsrp.common.stubs.v2.ServiceDescription;
import com.sun.portal.wsrp.consumer.common.WSRPConsumerException;
import com.sun.portal.wsrp.consumer.producermanager.ProducerEntity;
import com.sun.portal.wsrp.consumer.producermanager.ProducerEntityManager;
import com.sun.portal.wsrp.consumer.producermanager.ProducerEntityStatus;
import com.sun.portal.wsrp.consumer.producermanager.impl.AbstractProducerEntityManager;
import com.sun.portal.wsrp.consumer.producermanager.impl.ProducerEntityImpl;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <a href="ProducerEntityManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 *
 */
public class ProducerEntityManagerImpl
	extends AbstractProducerEntityManager implements ProducerEntityManager {

	public ProducerEntityManagerImpl() {
		super();

		_consumerAgent = GetterUtil.getString(
			PortletProps.get(_CONSUMER_AGENT_KEY), _DEFAULT_CONSUMER_AGENT);
		_consumerName = GetterUtil.getString(
			PortletProps.get(_CONSUMER_NAME_KEY), _DEFAULT_CONSUMER_NAME);
	}

	public String getConsumerName() {
		return _consumerName;
	}

	public RegistrationData getDefaultRegistrationData()
		throws WSRPConsumerException {

		String globalXML = getGlobalRegistrationDataXML();

		if (globalXML == null) {
			globalXML = DEFAULT_REGISTRATION_DATA;
		}

		RegistrationData defaultRegistrationData = getRegistrationDataFromXML(
			globalXML);

		String consumerName = getConsumerName();

		if (Validator.isNull(consumerName)) {
			consumerName = defaultRegistrationData.getConsumerName();
		}

		RegistrationData registrationData = new RegistrationData();

		registrationData.setConsumerName(consumerName);
		registrationData.setConsumerAgent(_consumerAgent);

		registrationData.setMethodGetSupported(
			defaultRegistrationData.isMethodGetSupported());

		registrationData.getConsumerModes().addAll(
			defaultRegistrationData.getConsumerModes());

		registrationData.getConsumerWindowStates().addAll(
			defaultRegistrationData.getConsumerWindowStates());

		registrationData.getConsumerUserScopes().addAll(
			defaultRegistrationData.getConsumerUserScopes());

		registrationData.getRegistrationProperties().addAll(
			defaultRegistrationData.getRegistrationProperties());

		registrationData.getExtensions().addAll(
			defaultRegistrationData.getExtensions());

		return registrationData;
	}

	public ProducerEntity getProducerEntity(String producerEntityId)
		throws WSRPConsumerException {

		WSRPConfiguredProducer configuredProducer = getConfiguredProducer(
			producerEntityId);

		if (configuredProducer == null) {
			throw new WSRPConsumerException(
				"No configured producer exists for producer entity id " +
					producerEntityId);
		}

		return populateConfiguredProducer(configuredProducer);
	}

	public Set<String> getProducerEntityIds() throws WSRPConsumerException {
		Set<String> producerEntityIds = new HashSet<String>();

		try {
			List<WSRPConfiguredProducer> configuredProducers =
				WSRPConfiguredProducerLocalServiceUtil.getConfiguredProducers(
					_portalId, _namespace);

			for (WSRPConfiguredProducer configuredProducer :
					configuredProducers) {

				producerEntityIds.add(
					String.valueOf(
						configuredProducer.getConfiguredProducerId()));
			}
		}
		catch (SystemException se) {
			throw new WSRPConsumerException(se.getMessage());
		}

		return producerEntityIds;
	}

	public Map<String, String> getStandardUserProfileMapping() {
		return Collections.EMPTY_MAP;
	}

	public void init(String portalId, String namespace) {
		_portalId = portalId;
		_namespace = namespace;
	}

	public boolean isActivated() {
		return true;
	}

	public void purgeProducerEntity(String producerEntityId)
		throws WSRPConsumerException {

		try {
			List<String> channelNames = new ArrayList<String>();

			List<WSRPPortlet> wsrpPortlets =
				WSRPPortletLocalServiceUtil.getPortlets(producerEntityId);

			for (WSRPPortlet wsrpPortlet : wsrpPortlets) {
				channelNames.add(wsrpPortlet.getChannelName());
			}

			if (!channelNames.isEmpty()) {
				WSRPChannelManagerImpl wsrpChannelManagerImpl =
					new WSRPChannelManagerImpl();

				wsrpChannelManagerImpl.removeWSRPChannels(channelNames);
			}

			WSRPConfiguredProducerLocalServiceUtil.deleteWSRPConfiguredProducer(
				GetterUtil.getLong(producerEntityId));
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage());
		}
	}

	public void setActivated(boolean status) {
	}

	public void setAllowedUserProfileMapping(
		String producerEntityId, Map<String, String> allowedUserProfileMap) {

		throw new UnsupportedOperationException();
	}

	public void setConsumerName(String consumerName) {
	}

	public void setCustomUserProfileMapping(
		String producerEntityId, Map<String, String> customUserProfileMap) {

		throw new UnsupportedOperationException();
	}

	public void setDefaultRegistrationData(RegistrationData registrationData) {
	}

	public void setIdentityPropagationType(
		String producerEntityId, String type) {

		throw new UnsupportedOperationException();
	}

	public void setName(String producerEntityId, String name) {
		throw new UnsupportedOperationException();
	}

	public void setStandardUserProfileMapping(
		Map<String, String> standardUserProfileMap) {
	}

	public void setStatus(String producerEntityId, ProducerEntityStatus status)
		throws WSRPConsumerException {

		WSRPConfiguredProducer configuredProducer =	getConfiguredProducer(
			producerEntityId);

		configuredProducer.setStatus(status.getValue());

		try {
			WSRPConfiguredProducerLocalServiceUtil.updateWSRPConfiguredProducer(
				configuredProducer);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage());
		}
	}

	public void setUserCategoryMapping(
		String producerEntityId, Map<String, String> userCategoryMap) {

		throw new UnsupportedOperationException();
	}

	public void storeModifiedRegistration(
			String producerEntityId, String registrationData,
			String registrationContext)
		throws WSRPConsumerException {

		WSRPConfiguredProducer configuredProducer = getConfiguredProducer(
			producerEntityId);

		configuredProducer.setRegistrationData(registrationData);
		configuredProducer.setRegistrationContext(registrationContext);

		try {
			WSRPConfiguredProducerLocalServiceUtil.updateWSRPConfiguredProducer(
				configuredProducer);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage());
		}
	}

	public void storeProducerEntity(ProducerEntity producerEntity)
		throws WSRPConsumerException {

		String registrationData = getRegistrationDataXMLFromRD(
			producerEntity.getRegistrationData());
		String registrationContext = getRegistrationContextXMLFromRC(
			producerEntity.getRegistrationContext());
		String serviceDescription = getServiceDescriptionXMLFromSD(
			producerEntity.getServiceDescription());

		String lifetimeTerminationTime = null;

		LeaseTime lifetime = producerEntity.getLifetime();

		if ((lifetime != null) && (lifetime.getTerminationTime() != null)) {
			lifetimeTerminationTime = lifetime.getTerminationTime().toString();
		}

		try {
			WSRPConfiguredProducerLocalServiceUtil.addConfiguredProducer(
				producerEntity.getName(), _portalId, _namespace,
				producerEntity.getURL().toString(),
				producerEntity.getVersion().value(),
				producerEntity.getMarkupEndpoint(),
				producerEntity.getStatus().getValue(), registrationData,
				registrationContext, serviceDescription, null, null,
				producerEntity.getIdentityPropagationType(),
				lifetimeTerminationTime,
				producerEntity.getServiceDescriptionLastModified(), 1);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage());
		}
	}

	public void storeUpdatedServiceDescription(
			String producerEntityId, String serviceDescription)
		throws WSRPConsumerException {

		WSRPConfiguredProducer configuredProducer =	getConfiguredProducer(
			producerEntityId);

		configuredProducer.setServiceDescription(serviceDescription);

		try {
			WSRPConfiguredProducerLocalServiceUtil.updateWSRPConfiguredProducer(
				configuredProducer);
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage());
		}
	}

	protected WSRPConfiguredProducer getConfiguredProducer(
			String configuredProducerId)
		throws WSRPConsumerException {

		try {
			WSRPConfiguredProducer configuredProducer =
				WSRPConfiguredProducerLocalServiceUtil.getConfiguredProducer(
					GetterUtil.getLong(configuredProducerId));

			return configuredProducer;
		}
		catch (Exception e) {
			throw new WSRPConsumerException(e.getMessage());
		}
	}

	protected String getGlobalRegistrationDataXML() {
		return null;
	}

	protected ProducerEntity populateConfiguredProducer(
			WSRPConfiguredProducer producer)
		throws WSRPConsumerException {

		String id = String.valueOf(producer.getConfiguredProducerId());
		String name = producer.getName();

		URL url = null;

		try {
			url = new URL(producer.getProducerURL());
		}
		catch (MalformedURLException mfue) {
			throw new WSRPConsumerException(mfue.getMessage());
		}

		WSRPVersion producerVersion = WSRPVersion.fromValue(
			producer.getProducerVersion());
		String producerMarkupURL = producer.getProducerMarkupURL();

		short status = (short)producer.getStatus();

		ProducerEntityStatus entityStatus =
			ProducerEntityStatus.getProducerEntityStatus(status);

		RegistrationData registrationData = getRegistrationDataFromXML(
			producer.getRegistrationData());
		RegistrationContext registrationContext = getRegistrationContextFromXML(
			producer.getRegistrationContext());
		ServiceDescription serviceDescription = getServiceDesctionFromXML(
			producer.getServiceDescription());
		Map userCategoryMap = getConsumerObjectFactory().getMap(
			producer.getUserCategoryMapping());
		long sdLastModified = producer.getSdLastModified();
		String lastModified = String.valueOf(sdLastModified);
		String identityPropagationType = producer.getIdentityPropagationType();

		LeaseTime leaseTime = null;

		if (Validator.isNotNull(producer.getLifetimeTerminationTime())) {
			try {
				XMLGregorianCalendar terminationTime =
					DatatypeFactory.newInstance().newXMLGregorianCalendar(
						producer.getLifetimeTerminationTime());

				leaseTime = new LeaseTime();

				leaseTime.setTerminationTime(terminationTime);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		ProducerEntity producerEntity = new ProducerEntityImpl(
			id, name, url, producerVersion, producerMarkupURL, entityStatus,
			registrationData, registrationContext, serviceDescription,
			userCategoryMap, null, null, sdLastModified, lastModified,
			producer.getEntityVersion(), identityPropagationType, leaseTime);

		return producerEntity;
	}

	private static final String _CONSUMER_AGENT_KEY =
		"com.sun.portal.wsrp.consumer.consumeragent";

	private static final String _CONSUMER_NAME_KEY =
		"com.sun.portal.wsrp.consumer.consumername";

	private static final String _DEFAULT_CONSUMER_AGENT =
		"Liferay Portal Consumer";

	private static final String _DEFAULT_CONSUMER_NAME =
		"Liferay WSRP Consumer";

	private static Log _log = LogFactoryUtil.getLog(
		ProducerEntityManagerImpl.class);

	private String _portalId;
	private String _namespace;
	private String _consumerAgent;
	private String _consumerName;

}