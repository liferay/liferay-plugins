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
import com.liferay.wsrp.model.WSRPConfiguredProducer;
import com.liferay.wsrp.service.base.WSRPConfiguredProducerLocalServiceBaseImpl;

import java.util.List;

/**
 * <a href="WSRPConfiguredProducerLocalServiceImpl.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Manish Gupta
 *
 */
public class WSRPConfiguredProducerLocalServiceImpl
	extends WSRPConfiguredProducerLocalServiceBaseImpl {

	public void addConfiguredProducer(
			String name, String portalId, String namespace, String producerURL,
			String producerVersion,	String producerMarkupURL, int status,
			String registrationData, String registrationContext,
			String serviceDescription, String userCategoryMapping,
			String customUserProfile, String identityPropagationType,
			String lifetimeTerminationTime, long sdLastModified,
			int entityVersion)
		throws SystemException {

		long configuredProducerId = CounterLocalServiceUtil.increment();

		WSRPConfiguredProducer configuredProducer =
			wsrpConfiguredProducerPersistence.create(configuredProducerId);

		configuredProducer.setName(name);
		configuredProducer.setPortalId(portalId);
		configuredProducer.setNamespace(namespace);
		configuredProducer.setProducerURL(producerURL);
		configuredProducer.setProducerVersion(producerVersion);
		configuredProducer.setProducerMarkupURL(producerMarkupURL);
		configuredProducer.setStatus(status);
		configuredProducer.setRegistrationData(registrationData);
		configuredProducer.setRegistrationContext(registrationContext);
		configuredProducer.setServiceDescription(serviceDescription);
		configuredProducer.setUserCategoryMapping(userCategoryMapping);
		configuredProducer.setCustomUserProfile(customUserProfile);
		configuredProducer.setIdentityPropagationType(identityPropagationType);
		configuredProducer.setLifetimeTerminationTime(lifetimeTerminationTime);
		configuredProducer.setSdLastModified(sdLastModified);
		configuredProducer.setEntityVersion(entityVersion);

		wsrpConfiguredProducerPersistence.update(configuredProducer, false);
	}

	public WSRPConfiguredProducer getConfiguredProducer(long configuredProducer)
		throws PortalException, SystemException {

		return wsrpConfiguredProducerPersistence.findByPrimaryKey(
			configuredProducer);
	}

	public List<WSRPConfiguredProducer> getConfiguredProducers(
			String portalId, String namespace)
		throws SystemException {

		return wsrpConfiguredProducerPersistence.findByP_N(portalId, namespace);
	}

}