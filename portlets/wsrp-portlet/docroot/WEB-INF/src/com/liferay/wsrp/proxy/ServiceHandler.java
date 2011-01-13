/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.proxy;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.axis.WSRPHTTPSender;
import com.liferay.wsrp.client.PasswordCallback;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.net.URL;

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_Markup_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_Markup_PortType;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_PortletManagement_PortType;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_Registration_PortType;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v1.wsdl.WSRPServiceLocator;
import oasis.names.tc.wsrp.v2.bind.WSRP_v2_Markup_Binding_SOAPStub;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_PortletManagement_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Registration_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.wsdl.WSRP_v2_ServiceLocator;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.Handler;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.Service;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import org.apache.ws.axis.security.WSDoAllSender;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.message.token.UsernameToken;

/**
 * @author Michael Young
 */
public class ServiceHandler implements InvocationHandler {

	public ServiceHandler(String forwardCookies, String userToken) {
		_engineConfiguration = getEngineConfiguration(
			forwardCookies, userToken);

		_v1ServiceLocator = new WSRPServiceLocator(_engineConfiguration);

		_v1ServiceLocator.setMaintainSession(true);

		_v2ServiceLocator = new WSRP_v2_ServiceLocator(_engineConfiguration);

		_v2ServiceLocator.setMaintainSession(true);
	}

	public WSRP_v1_Markup_PortType getV1MarkupService(URL url)
		throws Exception {

		Service service = getService();

		WSRP_v1_Markup_Binding_SOAPStub markupService =
			new WSRP_v1_Markup_Binding_SOAPStub(url, service);

		markupService.setPortName(
			_v1ServiceLocator.getWSRP_v1_Markup_ServiceWSDDServiceName());

		return markupService;
	}

	public WSRP_v2_Markup_PortType getV2MarkupService(URL url)
		throws Exception {

		Service service = getService();

		WSRP_v2_Markup_Binding_SOAPStub markupService =
			new WSRP_v2_Markup_Binding_SOAPStub(url, service);

		markupService.setPortName(
			_v2ServiceLocator.getWSRP_v2_Markup_ServiceWSDDServiceName());

		return markupService;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		try {
			return doInvoke(proxy, method, args);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	public Object doInvoke(Object proxy, Method method, Object[] args)
		throws Exception {

		String methodName = method.getName();

		URL bindingURL = (URL)args[0];

		if (_v2) {
			if (methodName.equals(_GET_WSRP_V2_MARKUP_SERVICE_METHOD)) {
				return getV2MarkupService(bindingURL);
			}
			else {
				return method.invoke(_v2ServiceLocator, args);
			}
		}

		Class<?> proxyInterface = null;
		InvocationHandler invocationHandler = null;

		if (methodName.equals(_GET_WSRP_V2_MARKUP_SERVICE_METHOD)) {
			proxyInterface = WSRP_v2_Markup_PortType.class;

			WSRP_v1_Markup_PortType markupService =
				getV1MarkupService(bindingURL);

			invocationHandler = new MarkupServiceHandler(markupService);
		}
		else if (methodName.equals(
			_GET_WSRP_V2_PORTLET_MANAGEMENT_SERVICE_METHOD)) {

			proxyInterface = WSRP_v2_PortletManagement_PortType.class;

			WSRP_v1_PortletManagement_PortType portletManagementService =
				_v1ServiceLocator.getWSRP_v1_PortletManagement_Service(
					bindingURL);

			invocationHandler = new PortletManagementServiceHandler(
				portletManagementService);
		}
		else if (methodName.equals(
					_GET_WSRP_V2_REGISTRATION_SERVICE_METHOD)) {

			proxyInterface = WSRP_v2_Registration_PortType.class;

			WSRP_v1_Registration_PortType registrationService =
				_v1ServiceLocator.getWSRP_v1_Registration_Service(bindingURL);

			invocationHandler = new RegistrationServiceHandler(
				registrationService);
		}
		else if (methodName.equals(
					_GET_WSRP_V2_SERVICE_DESCRIPTION_SERVICE_METHOD)) {

			proxyInterface = WSRP_v2_ServiceDescription_PortType.class;

			WSRP_v1_ServiceDescription_PortType serviceDescriptionService =
				_v1ServiceLocator.getWSRP_v1_ServiceDescription_Service(
					bindingURL);

			invocationHandler = new ServiceDescriptionServiceHandler(
				serviceDescriptionService);
		}

		return Proxy.newProxyInstance(
			ServiceHandler.class.getClassLoader(),
			new Class[] {proxyInterface}, invocationHandler);
	}

	public void setV2(boolean v2) {
		_v2 = v2;
	}

	protected EngineConfiguration getEngineConfiguration(
		String forwardCookies, String userToken) {

		SimpleChain simpleChain = new SimpleChain();

		if (Validator.isNotNull(userToken)) {
			Handler handler = new WSDoAllSender();

			handler.setOption(WSHandlerConstants.ACTION, "UsernameToken");
			handler.setOption(WSHandlerConstants.MUST_UNDERSTAND, "false");
			handler.setOption(UsernameToken.PASSWORD_TYPE, WSConstants.PW_NONE);
			handler.setOption(
				WSHandlerConstants.PW_CALLBACK_CLASS,
				PasswordCallback.class.getName());
			handler.setOption(WSHandlerConstants.USER, userToken);

			simpleChain.addHandler(handler);
		}

		SimpleProvider simpleProvider = new SimpleProvider();

		HTTPSender httpSender = new WSRPHTTPSender(forwardCookies);

		simpleProvider.deployTransport(
			HTTPTransport.DEFAULT_TRANSPORT_NAME,
			new SimpleTargetedChain(simpleChain, httpSender, null));

		return simpleProvider;
	}

	protected Service getService() {
		Service service = new Service(_engineConfiguration);

		service.setMaintainSession(true);

		return service;
	}

	private static final String _GET_WSRP_V2_MARKUP_SERVICE_METHOD =
		"getWSRP_v2_Markup_Service";

	private static final String _GET_WSRP_V2_PORTLET_MANAGEMENT_SERVICE_METHOD =
		"getWSRP_v2_PortletManagement_Service";

	private static final String _GET_WSRP_V2_REGISTRATION_SERVICE_METHOD =
		"getWSRP_v2_Registration_Service";

	private static final String
		_GET_WSRP_V2_SERVICE_DESCRIPTION_SERVICE_METHOD =
			"getWSRP_v2_ServiceDescription_Service";

	private EngineConfiguration _engineConfiguration;
	private WSRPServiceLocator _v1ServiceLocator;
	private boolean _v2;
	private WSRP_v2_ServiceLocator _v2ServiceLocator;

}