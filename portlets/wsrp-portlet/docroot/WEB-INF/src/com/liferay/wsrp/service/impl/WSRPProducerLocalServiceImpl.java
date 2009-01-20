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

package com.liferay.wsrp.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.base.WSRPProducerLocalServiceBaseImpl;

import java.util.List;

/**
 * <a href="WSRPProducerLocalServiceImpl.java.html">
 * <b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Manish Gupta
 *
 */
public class WSRPProducerLocalServiceImpl
	extends WSRPProducerLocalServiceBaseImpl {

	public void addProducer(
			String portalId, boolean status, String namespace,
			String instanceName, boolean requiresRegistration,
			boolean supportsInbandRegistration, String version,
			String offeredPortlets,	String producerProfileMap,
			String registrationProperties, String registrationValidatorClass)
		throws SystemException {

		long configuredProducerId = CounterLocalServiceUtil.increment();

		WSRPProducer producer =	wsrpProducerPersistence.create(
			configuredProducerId);

		producer.setPortalId(portalId);
		producer.setStatus(status);
		producer.setNamespace(namespace);
		producer.setInstanceName(instanceName);
		producer.setRequiresRegistration(requiresRegistration);
		producer.setSupportsInbandRegistration(supportsInbandRegistration);
		producer.setVersion(version);
		producer.setOfferedPortlets(offeredPortlets);
		producer.setProducerProfileMap(producerProfileMap);
		producer.setRegistrationProperties(registrationProperties);
		producer.setRegistrationValidatorClass(registrationValidatorClass);

		wsrpProducerPersistence.update(producer, false);
	}

	public void deleteProducer(WSRPProducer producer) throws SystemException {
		wsrpConsumerRegistrationPersistence.removeByProducerKey(
			producer.getInstanceName());

		wsrpProducerPersistence.remove(producer);
	}

	public WSRPProducer getProducer(String instanceName)
		throws PortalException, SystemException {

		return wsrpProducerPersistence.findByInstanceName(instanceName);
	}

	public List<WSRPProducer> getProducers(String portalId, String namespace)
		throws SystemException {

		return wsrpProducerPersistence.findByP_N(portalId, namespace);
	}

}