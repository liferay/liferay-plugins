/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.wsrp.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.wsrp.NoSuchConsumerException;
import com.liferay.wsrp.WSRPConsumerNameException;
import com.liferay.wsrp.WSRPConsumerWSDLException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.base.WSRPConsumerLocalServiceBaseImpl;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Registration_PortType;
import oasis.names.tc.wsrp.v2.types.Lifetime;
import oasis.names.tc.wsrp.v2.types.ModifyRegistration;
import oasis.names.tc.wsrp.v2.types.Property;
import oasis.names.tc.wsrp.v2.types.PropertyDescription;
import oasis.names.tc.wsrp.v2.types.Register;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.RegistrationData;
import oasis.names.tc.wsrp.v2.types.RegistrationState;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerLocalServiceImpl
	extends WSRPConsumerLocalServiceBaseImpl {

	public WSRPConsumer addWSRPConsumer(
			long companyId, String adminPortletId, String name, String url,
			String forwardCookies, String userToken,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		long wsrpConsumerId = CounterLocalServiceUtil.increment();

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.create(
			wsrpConsumerId);

		wsrpConsumer.setUuid(serviceContext.getUuid());
		wsrpConsumer.setCompanyId(companyId);
		wsrpConsumer.setCreateDate(now);
		wsrpConsumer.setModifiedDate(now);
		wsrpConsumer.setName(name);
		wsrpConsumer.setUrl(url);
		wsrpConsumer.setWsdl(getWSDL(wsrpConsumer, forwardCookies, userToken));

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		return wsrpConsumer;
	}

	@Override
	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		deleteWSRPConsumer(wsrpConsumer);
	}

	@Override
	public void deleteWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws PortalException, SystemException {

		wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlets(
			wsrpConsumer.getWsrpConsumerId());

		wsrpConsumerPersistence.remove(wsrpConsumer);
	}

	public WSRPConsumer getWSRPConsumer(String wsrpConsumerUuid)
		throws PortalException, SystemException {

		List<WSRPConsumer> wsrpConsumers = wsrpConsumerPersistence.findByUuid(
			wsrpConsumerUuid);

		if (wsrpConsumers.isEmpty()) {
			throw new NoSuchConsumerException(
				"No WSRP consumer exists with uuid " + wsrpConsumerUuid);
		}
		else {
			return wsrpConsumers.get(0);
		}
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
			UnicodeProperties registrationProperties,
			String registrationHandle, String userToken)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		RegistrationContext registrationContext = null;

		if (registrationProperties != null) {
			try {
				registrationContext = register(
					wsrpConsumer, adminPortletId, registrationProperties,
					userToken);
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void restartConsumer(long wsrpConsumerId, String userToken)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		try {
			WSRPConsumerManager wsrpConsumerManager =
				WSRPConsumerManagerFactory.getWSRPConsumerManager(
					wsrpConsumer, userToken);

			RegistrationContext registrationContext =
				wsrpConsumer.getRegistrationContext();

			wsrpConsumerManager.updateServiceDescription(registrationContext);

			List<WSRPConsumerPortlet> wsrpConsumerPortlets =
				wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(
					wsrpConsumerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WSRPConsumerPortlet wsrpConsumerPortlet :
				wsrpConsumerPortlets) {

				long companyId = wsrpConsumerPortlet.getCompanyId();
				long wsrpConsumerPortletId =
					wsrpConsumerPortlet.getWsrpConsumerPortletId();
				String wsrpConsumerPortletUuid = wsrpConsumerPortlet.getUuid();
				String name = wsrpConsumerPortlet.getName();
				String portletHandle = wsrpConsumerPortlet.getPortletHandle();

				wsrpConsumerPortletLocalService.initWSRPConsumerPortlet(
					companyId, wsrpConsumerId, wsrpConsumerPortletId,
					wsrpConsumerPortletUuid, name, portletHandle, userToken);
			}
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public void updateServiceDescription(long wsrpConsumerId, String userToken)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		try {
			WSRPConsumerManager wsrpConsumerManager =
				WSRPConsumerManagerFactory.getWSRPConsumerManager(
					wsrpConsumer, userToken);

			RegistrationContext registrationContext =
				wsrpConsumer.getRegistrationContext();

			wsrpConsumerManager.updateServiceDescription(registrationContext);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public WSRPConsumer updateWSRPConsumer(
			long wsrpConsumerId, String adminPortletId, String name, String url,
			String forwardCookies, String userToken)
		throws PortalException, SystemException {

		validate(name);

		WSRPConsumerManagerFactory.destroyWSRPConsumerManager(url);

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);

		wsrpConsumer.setModifiedDate(new Date());
		wsrpConsumer.setName(name);
		wsrpConsumer.setUrl(url);
		wsrpConsumer.setWsdl(getWSDL(wsrpConsumer, forwardCookies, userToken));

		wsrpConsumerPersistence.update(wsrpConsumer, false);

		return wsrpConsumer;
	}

	protected String getWSDL(
			WSRPConsumer wsrpConsumer, String forwardCookies, String userToken)
		throws PortalException {

		try {

			// Must set forward cookies first so that WSRPConsumerManagerFactory
			// has access to them

			wsrpConsumer.setForwardCookies(forwardCookies);

			WSRPConsumerManager wsrpConsumerManager =
				WSRPConsumerManagerFactory.getWSRPConsumerManager(
					wsrpConsumer, userToken);

			return wsrpConsumerManager.getWsdl();
		}
		catch (Exception e) {
			throw new WSRPConsumerWSDLException(e);
		}
	}

	protected RegistrationContext register(
			WSRPConsumer wsrpConsumer, String adminPortletId,
			UnicodeProperties registrationProperties, String userToken)
		throws Exception {

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(
				wsrpConsumer, userToken);

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
		registrationData.setConsumerName(company.getVirtualHostname());
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

			byte[] registrationStateValue =
				registrationState.getRegistrationState();

			if (registrationStateValue != null) {
				registrationContext.setRegistrationState(
					registrationState.getRegistrationState());
			}

			Lifetime scheduledDestruction =
				registrationState.getScheduledDestruction();

			if (scheduledDestruction != null) {
				registrationContext.setScheduledDestruction(
					scheduledDestruction);
			}
		}

		wsrpConsumerManager.updateServiceDescription(registrationContext);

		return registrationContext;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPConsumerNameException();
		}
	}

}