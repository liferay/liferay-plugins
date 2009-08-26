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
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.wsrp.WSRPConsumerNameException;
import com.liferay.wsrp.WSRPConsumerWSDLException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.service.base.WSRPConsumerLocalServiceBaseImpl;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Registration_PortType;
import oasis.names.tc.wsrp.v2.types.ModifyRegistration;
import oasis.names.tc.wsrp.v2.types.ParameterDescription;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.Property;
import oasis.names.tc.wsrp.v2.types.PropertyDescription;
import oasis.names.tc.wsrp.v2.types.Register;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.RegistrationData;
import oasis.names.tc.wsrp.v2.types.RegistrationState;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;

/**
 * <a href="WSRPConsumerLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerLocalServiceImpl
	extends WSRPConsumerLocalServiceBaseImpl {

	public WSRPConsumer addWSRPConsumer(long companyId, String adminPortletId,
			String name, String url)
		throws PortalException, SystemException {

		String wsdl = getWSDL(url);
		Date now = new Date();

		validate(name);

		long wsrpConsumerId = CounterLocalServiceUtil.increment();

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.create(
			wsrpConsumerId);

		wsrpConsumer.setCompanyId(companyId);
		wsrpConsumer.setCreateDate(now);
		wsrpConsumer.setModifiedDate(now);
		wsrpConsumer.setName(name);
		wsrpConsumer.setUrl(url);
		wsrpConsumer.setWsdl(wsdl);

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		try {
			updatePublicRenderParameters(adminPortletId, wsdl);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new WSRPConsumerWSDLException(e);
		}

		return wsrpConsumer;
	}

	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		deleteWSRPConsumer(wsrpConsumer);
	}

	public void deleteWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws PortalException, SystemException {

		wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlets(
			wsrpConsumer.getWsrpConsumerId());

		wsrpConsumerPersistence.remove(wsrpConsumer);
	}

	public List<WSRPConsumer> getWSRPConsumers(
			long companyId, int start, int end)
		throws SystemException {

		return wsrpConsumerPersistence.findByCompanyId(companyId, start, end);
	}

	public int getWSRPConsumersCount(long companyId) throws SystemException {
		return wsrpConsumerPersistence.countByCompanyId(companyId);
	}

	public WSRPConsumer registerWSRPConsumer(
			long wsrpConsumerId, String adminPortletId,
			UnicodeProperties registrationProperties, String registrationHandle)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		RegistrationContext registrationContext = null;

		if (registrationProperties != null) {
			try {
				registrationContext = register(
					wsrpConsumer, adminPortletId, registrationProperties);
			}
			catch (PortalException pe) {
				throw pe;
			}
			catch (SystemException se) {
				throw se;
			}
			catch (Exception e) {
				throw new WSRPConsumerWSDLException(e);
			}
		}
		else if (Validator.isNotNull(registrationHandle)) {
			registrationContext = new RegistrationContext();

			registrationContext.setRegistrationHandle(registrationHandle);
		}

		wsrpConsumer.setModifiedDate(new Date());
		wsrpConsumer.setRegistrationContext(registrationContext);
		wsrpConsumer.setRegistrationProperties(registrationProperties);

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		return wsrpConsumer;
	}

	public WSRPConsumer updateWSRPConsumer(
			long wsrpConsumerId, String adminPortletId, String name, String url)
		throws PortalException, SystemException {

		String wsdl = getWSDL(url);

		validate(name);

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		wsrpConsumer.setModifiedDate(new Date());
		wsrpConsumer.setName(name);
		wsrpConsumer.setUrl(url);
		wsrpConsumer.setWsdl(wsdl);

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		try {
			updatePublicRenderParameters(adminPortletId, wsdl);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new WSRPConsumerWSDLException(e);
		}

		return wsrpConsumer;
	}

	protected String getWSDL(String url) throws PortalException {
		try {
			String wsdl = HttpUtil.URLtoString(url);

			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsdl);

			return wsdl;
		}
		catch (Exception e) {
			throw new WSRPConsumerWSDLException(e);
		}
	}

	protected RegistrationContext register(
			WSRPConsumer wsrpConsumer, String adminPortletId,
			UnicodeProperties registrationProperties)
		throws Exception {

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		WSRP_v2_Registration_PortType registrationService =
			wsrpConsumerManager.getRegistrationService();

		RegistrationContext registrationContext =
			wsrpConsumer.getRegistrationContext();

		Property[] properties = new Property[registrationProperties.size()];

		List<Map.Entry<String, String>> registrationPropertiesList =
			ListUtil.fromCollection(registrationProperties.entrySet());

		for (int i = 0; i < properties.length; i++) {
			Map.Entry<String, String> entry = registrationPropertiesList.get(i);

			String name = entry.getKey();
			String value = entry.getValue();

			PropertyDescription propertyDescription =
				wsrpConsumerManager.getPropertyDescription(name);

			QName qName = propertyDescription.getName();

			Property property = new Property();

			property.setName(qName);
			property.setStringValue(value);

			properties[i] = property;
		}

		Company company = CompanyLocalServiceUtil.getCompany(
			wsrpConsumer.getCompanyId());

		RegistrationData registrationData = new RegistrationData();

		registrationData.setConsumerAgent(ReleaseInfo.getServerInfo());
		registrationData.setConsumerName(company.getVirtualHost());
		registrationData.setMethodGetSupported(true);
		registrationData.setRegistrationProperties(properties);

		if (registrationContext == null) {
			Register register = new Register();

			register.setRegistrationData(registrationData);

			registrationContext = registrationService.register(register);
		}
		else {
			ModifyRegistration modifyRegistration = new ModifyRegistration();

			modifyRegistration.setRegistrationContext(registrationContext);
			modifyRegistration.setRegistrationData(registrationData);

			RegistrationState registrationState =
				registrationService.modifyRegistration(modifyRegistration);

			registrationContext.setRegistrationState(
				registrationState.getRegistrationState());
			registrationContext.setScheduledDestruction(
				registrationState.getScheduledDestruction());
		}

		wsrpConsumerManager.updateServiceDescription(registrationContext);

		updatePublicRenderParameters(adminPortletId, wsrpConsumerManager);

		return registrationContext;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPConsumerNameException();
		}
	}

	protected void updatePublicRenderParameters(
		String adminPortletId, String wsdl) throws Exception {

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsdl);

		updatePublicRenderParameters(adminPortletId, wsrpConsumerManager);
	}

	protected void updatePublicRenderParameters(
			String adminPortletId, WSRPConsumerManager wsrpConsumerManager)
		throws Exception {

		Portlet adminPortlet = PortletLocalServiceUtil.getPortletById(
				adminPortletId);

		ServiceDescription serviceDescription =
			wsrpConsumerManager.getServiceDescription();

		PortletDescription[] portletDescriptions =
			serviceDescription.getOfferedPortlets();

		if (portletDescriptions == null) {
			return;
		}

		for (PortletDescription portletDescription : portletDescriptions) {
			ParameterDescription[] parameterDescriptions =
				portletDescription.getNavigationalPublicValueDescriptions();

			if (parameterDescriptions == null) {
				continue;
			}

			for (ParameterDescription parameterDescription :
				parameterDescriptions) {

				QName[] wsrpQNames =
					parameterDescription.getNames();

				if (wsrpQNames == null && wsrpQNames.length >= 0) {
					continue;
				}

				String localPart = wsrpQNames[0].getLocalPart();
				String namespaceURI = wsrpQNames[0].getNamespaceURI();
				String prefix = wsrpQNames[0].getPrefix();

				Namespace namespace =
					SAXReaderUtil.createNamespace(prefix, namespaceURI);

				com.liferay.portal.kernel.xml.QName qName =
					SAXReaderUtil.createQName(localPart, namespace);

				PortletApp portletApp = adminPortlet.getPortletApp();

				portletApp.addPublicRenderParameter(
					parameterDescription.getIdentifier(), qName);
			}
		}
	}

}