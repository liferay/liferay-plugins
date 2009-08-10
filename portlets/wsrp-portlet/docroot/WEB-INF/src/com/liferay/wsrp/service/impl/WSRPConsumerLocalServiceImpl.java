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
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
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
import oasis.names.tc.wsrp.v2.types.Property;
import oasis.names.tc.wsrp.v2.types.PropertyDescription;
import oasis.names.tc.wsrp.v2.types.Register;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.RegistrationData;
import oasis.names.tc.wsrp.v2.types.RegistrationState;

/**
 * <a href="WSRPConsumerLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerLocalServiceImpl
	extends WSRPConsumerLocalServiceBaseImpl {

	public WSRPConsumer addWSRPConsumer(long companyId, String name, String url)
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

	public WSRPConsumer updateWSRPConsumer(
			long wsrpConsumerId, String name, String url)
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

		return wsrpConsumer;
	}

	public WSRPConsumer updateWSRPConsumer(
			long wsrpConsumerId, UnicodeProperties registrationProperties,
			String registrationHandle)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		RegistrationContext registrationContext = null;

		if (registrationProperties != null) {
			registrationContext = register(
				wsrpConsumer, registrationProperties);
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
			WSRPConsumer wsrpConsumer, UnicodeProperties registrationProperties)
		throws PortalException {

		RegistrationContext registrationContext =
			wsrpConsumer.getRegistrationContext();

		try {
			WSRPConsumerManager wsrpConsumerManager =
				WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

			WSRP_v2_Registration_PortType registrationService =
				wsrpConsumerManager.getRegistrationService();

			Property[] properties = new Property[registrationProperties.size()];

			List<Map.Entry<String, String>> registrationPropertiesList =
				ListUtil.fromCollection(registrationProperties.entrySet());

			for (int i = 0; i < properties.length; i++) {
				Map.Entry<String, String> entry =
					registrationPropertiesList.get(i);

				String name = entry.getKey();
				String value = entry.getValue();

				PropertyDescription propertyDescription =
					wsrpConsumerManager.getPropertyDescription(name);

				QName qName = propertyDescription.getName();

				properties[i] = new Property();

				properties[i].setName(qName);
				properties[i].setStringValue(value);
			}

			Company company =
				CompanyLocalServiceUtil.getCompany(wsrpConsumer.getCompanyId());

			RegistrationData registrationData = new RegistrationData();

			registrationData.setConsumerName(company.getVirtualHost());
			registrationData.setConsumerAgent(ReleaseInfo.getServerInfo());
			registrationData.setMethodGetSupported(true);
			registrationData.setRegistrationProperties(properties);

			if (registrationContext == null) {
				Register register = new Register();

				register.setRegistrationData(registrationData);

				registrationContext = registrationService.register(register);
			}
			else {
				ModifyRegistration modifyRegistration =
					new ModifyRegistration();

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
		}
		catch (Exception e) {
			throw new WSRPConsumerWSDLException(e);
		}

		return registrationContext;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPConsumerNameException();
		}
	}

}