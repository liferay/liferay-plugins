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

import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import com.sun.portal.wsrp.common.stubs.v2.ModelDescription;
import com.sun.portal.wsrp.producer.Producer;
import com.sun.portal.wsrp.producer.ProducerException;
import com.sun.portal.wsrp.producer.ProducerManager;
import com.sun.portal.wsrp.producer.registration.validator.impl.DefaultRegistrationValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="ProducerManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 *
 */
public class ProducerManagerImpl implements ProducerManager {

	public ProducerManagerImpl(String portalId) {
		_portalId = portalId;
	}

	public void addProducer(
			String namespace, String instanceName, boolean status,
			boolean requiresRegistration, boolean supportsInBandRegistration,
			String version, ModelDescription registrationPropertyDescription)
		throws ProducerException {

		try {
			WSRPProducerLocalServiceUtil.addProducer(
				_portalId, status, namespace, instanceName,
				requiresRegistration, supportsInBandRegistration, version, null,
				null, null, _DEFAULT_REGISTRATION_VALIDATOR);
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public boolean areAllProducersDisabled() {
		return false;
	}

	public List<Producer> getAllProducers(String namespace)
		throws ProducerException {

		List<Producer> producers = new ArrayList<Producer>();

		if (namespace == null) {
			namespace = _DEFAULT_NAMESPACE;
		}

		try {
			List<WSRPProducer> producerModels =
				WSRPProducerLocalServiceUtil.getProducers(_portalId, namespace);

			for (WSRPProducer producerModel : producerModels) {
				Producer producer = new ProducerImpl(producerModel);

				producers.add(producer);
			}
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}

		return producers;
	}

	public Producer getProducer(String producerKey) throws ProducerException {
		try {
			WSRPProducer producerModel =
				WSRPProducerLocalServiceUtil.getProducer(producerKey);

			return new ProducerImpl(producerModel);
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public Producer getProducer(String namespace, String instanceName)
		throws ProducerException {

		return getProducer(instanceName);
	}

	public void removeProducers(String namespace, String[] instanceNames)
		throws ProducerException {

		try {
			for (String instanceName : instanceNames) {
				WSRPProducer producerModel =
					WSRPProducerLocalServiceUtil.getProducer(instanceName);

				WSRPProducerLocalServiceUtil.deleteProducer(producerModel);
			}
		}
		catch (Exception e) {
			throw new ProducerException(e);
		}
	}

	public void setAllProducersDisabled(boolean value) {
	}

	public void updateProducer(Producer producer) throws ProducerException {
		if (producer instanceof ProducerImpl) {
			WSRPProducer producerModel =
				((ProducerImpl)producer).getProducerModel();

			try {
				WSRPProducerLocalServiceUtil.updateWSRPProducer(producerModel);
			}
			catch (Exception e) {
				throw new ProducerException(e.getMessage());
			}
		}
	}

	private static final String _DEFAULT_NAMESPACE = "dummyNamespace";

	private static final String _DEFAULT_REGISTRATION_VALIDATOR =
		DefaultRegistrationValidator.class.getName();

	private String _portalId;

}