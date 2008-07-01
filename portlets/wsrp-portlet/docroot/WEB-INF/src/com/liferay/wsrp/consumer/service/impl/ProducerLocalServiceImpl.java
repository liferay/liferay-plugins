/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.consumer.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.wsrp.consumer.InvalidWSDLException;
import com.liferay.wsrp.consumer.NameException;
import com.liferay.wsrp.consumer.WSDLURLException;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.service.base.ProducerLocalServiceBaseImpl;
import com.liferay.wsrp.soap.v2.types.ServiceDescription;
import com.liferay.wsrp.util.ConsumerUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.wsdl.WSDLException;

/**
 * <a href="ProducerLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ProducerLocalServiceImpl extends ProducerLocalServiceBaseImpl {
	public Producer addProducer(long userId, long plid, String name, 
		String wsdlURL) throws PortalException, SystemException {

		validate(name, wsdlURL);

		User user = UserLocalServiceUtil.getUserById(userId);

		long groupId = PortalUtil.getPortletGroupId(plid);

		Date now = new Date();

		long entryId = CounterLocalServiceUtil.increment();

		Producer producer = producerPersistence.create(entryId);

		producer.setCompanyId(user.getCompanyId());
		producer.setGroupId(groupId);
		producer.setUserId(user.getUserId());
		producer.setCreateDate(now);
		producer.setModifiedDate(now);
		producer.setName(name);
		producer.setWsdlURL(wsdlURL);
		
		initProducer(producer);
		
		producerPersistence.update(producer, false);
		
		return producer;
	}
	
	public Producer getProducer(long producerId)
		throws PortalException, SystemException {

		return producerPersistence.findByPrimaryKey(producerId);
	}
	
	public List<Producer> getProducers()
		throws SystemException {
	
		return producerPersistence.findAll();
	}

	public List<Producer> getProducers(int start, int end)
		throws SystemException {

		return producerPersistence.findAll(start, end);
	}

	public int getProducerCount() throws SystemException {
		return producerPersistence.countAll();
	}
	
	public Producer updateProducer(long producerId, String name, 
			String wsdlURL) throws PortalException, SystemException {

		validate(name, wsdlURL);
	
		Producer producer = producerPersistence.findByPrimaryKey(producerId);
		
		Date now = new Date();
		
		producer.setModifiedDate(now);
		producer.setName(name);
		producer.setWsdlURL(wsdlURL);
		
		initProducer(producer);
		
		producerPersistence.update(producer, false);
		
		return producer;
	}
	
	protected void initProducer(Producer producer) throws PortalException {
		producer.setMarkupEndpoint(null);
		producer.setPortletManagementEndpoint(null);
		producer.setRegistrationEndpoint(null);
		producer.setServiceDescription(null);
		producer.setServiceDescriptionObj(null);
		producer.setRegistrationContextObj(null);
		
		// infer endpoints from the wsdl
		
		try {
			ConsumerUtil.resolveEndpoints(producer);
		}
		catch (WSDLException e) {
			throw new InvalidWSDLException(e);
		}
		
		validate(producer);

		ServiceDescription sd = 
			ConsumerUtil.getServiceDescription(producer);
		
		producer.setServiceDescriptionObj(sd);
	}
	
	protected void validate(String name, String wsdlURL) throws PortalException {
		if (Validator.isNull(name)) {
			throw new NameException();
		}
		
		if (Validator.isNull(wsdlURL)) {
			throw new WSDLURLException();
		}
		else {
			try {
				new URL(wsdlURL);
			}
			catch (MalformedURLException murle) {
				throw new WSDLURLException();
			}
		}
	}
	
	protected void validate(Producer producer) throws PortalException {

		// service description and markup are the required endpoints
		
		if (Validator.isNull(producer.getServiceDescriptionEndpoint())) {
			throw new InvalidWSDLException();
		}
		
		if (Validator.isNull(producer.getMarkupEndpoint())) {
			throw new InvalidWSDLException();			
		}
	}
	
}