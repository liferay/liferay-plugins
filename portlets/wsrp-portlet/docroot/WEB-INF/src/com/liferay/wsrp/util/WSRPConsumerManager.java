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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.PortletQNameUtil;
import com.liferay.wsrp.proxy.ServiceHandler;

import java.net.URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_PortletManagement_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Registration_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.types.EventDescription;
import oasis.names.tc.wsrp.v2.types.GetServiceDescription;
import oasis.names.tc.wsrp.v2.types.ModelDescription;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.PropertyDescription;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;
import oasis.names.tc.wsrp.v2.wsdl.WSRP_v2_Service;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerManager {

	public WSRPConsumerManager(
			String url, RegistrationContext registrationContext,
			String forwardCookies, String forwardHeaders, String userToken)
		throws Exception {

		try {
			_wsdl = HttpUtil.URLtoString(url);

			Document document = SAXReaderUtil.read(_wsdl);

			Element root = document.getRootElement();

			_wsdlNamespace = _getWsdlNamespace(root);

			List<Element> serviceElements = root.elements(
				_getWsdlQName("service"));

			ServiceHandler serviceHandler = new ServiceHandler(
				forwardCookies, forwardHeaders, userToken,
				_isV2(serviceElements));

			_service = (WSRP_v2_Service)ProxyUtil.newProxyInstance(
				WSRP_v2_Service.class.getClassLoader(),
				new Class[] {WSRP_v2_Service.class}, serviceHandler);

			_readServiceElements(serviceElements);

			updateServiceDescription(registrationContext);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			throw e;
		}
	}

	public String getDisplayName(PortletDescription portletDescription) {
		String displayName = LocalizedStringUtil.getLocalizedStringValue(
			portletDescription.getDisplayName());

		if (displayName == null) {
			displayName = LocalizedStringUtil.getLocalizedStringValue(
				portletDescription.getShortTitle());
		}

		if (displayName == null) {
			displayName = LocalizedStringUtil.getLocalizedStringValue(
				portletDescription.getTitle());
		}

		if (displayName == null) {
			displayName = portletDescription.getPortletHandle();
		}

		return displayName;
	}

	public QName getEventQName(QName qName) {
		String key = PortletQNameUtil.getKey(
			qName.getNamespaceURI(), qName.getLocalPart());

		return _events.get(key);
	}

	public WSRP_v2_Markup_PortType getMarkupService() throws Exception {
		return _service.getWSRP_v2_Markup_Service(_markupServiceURL);
	}

	public PortletDescription getPortletDescription(String portletHandle) {
		return _portletDescriptions.get(portletHandle);
	}

	public WSRP_v2_PortletManagement_PortType getPortletManagementService() {
		return _portletManagementService;
	}

	public PropertyDescription getPropertyDescription(String name) {
		return _propertyDescriptions.get(name);
	}

	public PropertyDescription[] getPropertyDescriptions() {
		PropertyDescription[] propertyDescriptions = null;

		ModelDescription modelDescription =
			_serviceDescription.getRegistrationPropertyDescription();

		if (modelDescription != null) {
			propertyDescriptions = modelDescription.getPropertyDescriptions();
		}

		if (propertyDescriptions == null) {
			propertyDescriptions = new PropertyDescription[0];
		}

		return propertyDescriptions;
	}

	public WSRP_v2_Registration_PortType getRegistrationService() {
		return _registrationService;
	}

	public ServiceDescription getServiceDescription() {
		return _serviceDescription;
	}

	public String getWsdl() {
		return _wsdl;
	}

	public void updateServiceDescription(
			RegistrationContext registrationContext)
		throws Exception {

		GetServiceDescription getServiceDescription =
			new GetServiceDescription();

		if (registrationContext != null) {
			getServiceDescription.setRegistrationContext(registrationContext);
		}

		_serviceDescription = _serviceDescriptionService.getServiceDescription(
			getServiceDescription);

		_portletDescriptions = new HashMap<String, PortletDescription>();

		PortletDescription[] portletDescriptions =
			_serviceDescription.getOfferedPortlets();

		if (portletDescriptions != null) {
			for (PortletDescription portletDescription : portletDescriptions) {
				_portletDescriptions.put(
					portletDescription.getPortletHandle(), portletDescription);
			}
		}

		_propertyDescriptions = new HashMap<String, PropertyDescription>();

		PropertyDescription[] propertyDescriptions = getPropertyDescriptions();

		if (propertyDescriptions != null) {
			for (PropertyDescription propertyDescription :
					propertyDescriptions) {

				_propertyDescriptions.put(
					propertyDescription.getName().toString(),
					propertyDescription);
			}
		}

		_events = new HashMap<String, QName>();

		EventDescription[] eventDescriptions =
			_serviceDescription.getEventDescriptions();

		if (eventDescriptions != null) {
			for (EventDescription eventDescription : eventDescriptions) {
				QName[] aliases = eventDescription.getAliases();
				QName qName = eventDescription.getName();

				String key = PortletQNameUtil.getKey(
					qName.getNamespaceURI(), qName.getLocalPart());

				_events.put(key, qName);

				if (aliases == null) {
					continue;
				}

				for (QName alias : aliases) {
					key = PortletQNameUtil.getKey(
						alias.getNamespaceURI(), alias.getLocalPart());

					_events.put(key, qName);
				}
			}
		}
	}

	private Namespace _getWsdlNamespace(Element element) {
		return element.getNamespaceForURI(_WSDL_URI);
	}

	private com.liferay.portal.kernel.xml.QName _getWsdlQName(
		String localName) {

		return SAXReaderUtil.createQName(localName, _wsdlNamespace);
	}

	private boolean _isV2(List<Element> serviceElements) {
		for (Element serviceElement : serviceElements) {
			List<Element> bindingElements = serviceElement.elements(
				_getWsdlQName("port"));

			Element firstBindingElement = bindingElements.get(0);

			String binding = firstBindingElement.attributeValue("binding");

			if (binding.contains("v2")) {
				return true;
			}
		}

		return false;
	}

	private void _readBindingElement(Element bindingElement) throws Exception {
		String binding = bindingElement.attributeValue("binding");

		int pos = binding.indexOf(StringPool.COLON);

		binding = binding.substring(pos + 1);

		Element addressElement = bindingElement.element("address");

		String bindingLocation = addressElement.attributeValue("location");

		URL bindingLocationURL = new URL(bindingLocation);

		if (binding.equals(_WSRP_V1_MARKUP_BINDING) ||
			binding.equals(_WSRP_V2_MARKUP_BINDING)) {

			_markupServiceURL = bindingLocationURL;
		}
		else if (binding.equals(_WSRP_V1_PORTLET_MANAGEMENT_BINDING) ||
				 binding.equals(_WSRP_V2_PORTLET_MANAGEMENT_BINDING)) {

			_portletManagementService =
				_service.getWSRP_v2_PortletManagement_Service(
					bindingLocationURL);
		}
		else if (binding.equals(_WSRP_V1_REGISTRATION_BINDING) ||
				 binding.equals(_WSRP_V2_REGISTRATION_BINDING)) {

			_registrationService = _service.getWSRP_v2_Registration_Service(
				bindingLocationURL);
		}
		else if (binding.equals(_WSRP_V1_SERVICE_DESCRIPTION_BINDING) ||
				 binding.equals(_WSRP_V2_SERVICE_DESCRIPTION_BINDING)) {

			_serviceDescriptionService =
				_service.getWSRP_v2_ServiceDescription_Service(
					bindingLocationURL);
		}
	}

	private void _readServiceElements(List<Element> serviceElements)
		throws Exception {

		for (Element serviceElement : serviceElements) {
			List<Element> bindingElements = serviceElement.elements(
				_getWsdlQName("port"));

			for (Element bindingElement : bindingElements) {
				_readBindingElement(bindingElement);
			}

			Element firstBindingElement = bindingElements.get(0);

			String binding = firstBindingElement.attributeValue("binding");

			if (binding.contains("v2")) {
				break;
			}
		}
	}

	private static final String _WSDL_URI = "http://schemas.xmlsoap.org/wsdl/";

	private static final String _WSRP_V1_MARKUP_BINDING =
		"WSRP_v1_Markup_Binding_SOAP";

	private static final String _WSRP_V1_PORTLET_MANAGEMENT_BINDING =
		"WSRP_v1_PortletManagement_Binding_SOAP";

	private static final String _WSRP_V1_REGISTRATION_BINDING =
		"WSRP_v1_Registration_Binding_SOAP";

	private static final String _WSRP_V1_SERVICE_DESCRIPTION_BINDING =
		"WSRP_v1_ServiceDescription_Binding_SOAP";

	private static final String _WSRP_V2_MARKUP_BINDING =
		"WSRP_v2_Markup_Binding_SOAP";

	private static final String _WSRP_V2_PORTLET_MANAGEMENT_BINDING =
		"WSRP_v2_PortletManagement_Binding_SOAP";

	private static final String _WSRP_V2_REGISTRATION_BINDING =
		"WSRP_v2_Registration_Binding_SOAP";

	private static final String _WSRP_V2_SERVICE_DESCRIPTION_BINDING =
		"WSRP_v2_ServiceDescription_Binding_SOAP";

	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerManager.class);

	private Map<String, QName> _events;
	private URL _markupServiceURL;
	private Map<String, PortletDescription> _portletDescriptions;
	private WSRP_v2_PortletManagement_PortType _portletManagementService;
	private Map<String, PropertyDescription> _propertyDescriptions;
	private WSRP_v2_Registration_PortType _registrationService;
	private WSRP_v2_Service _service;
	private ServiceDescription _serviceDescription;
	private WSRP_v2_ServiceDescription_PortType _serviceDescriptionService;
	private String _wsdl;
	private Namespace _wsdlNamespace;

}