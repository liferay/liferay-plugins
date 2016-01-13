/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.consumer.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.WebsiteLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.Encryptor;
import com.liferay.wsrp.axis.WSRPHTTPSender;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.proxy.Stub;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.servlet.ServiceHolder;
import com.liferay.wsrp.util.ConsumerRequestExtensionsHelper;
import com.liferay.wsrp.util.ExtensionHelperUtil;
import com.liferay.wsrp.util.MarkupCharacterSetsUtil;
import com.liferay.wsrp.util.PortletPropsValues;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;
import com.liferay.wsrp.util.WebKeys;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.StateAwareResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v2.types.CacheControl;
import oasis.names.tc.wsrp.v2.types.ClientData;
import oasis.names.tc.wsrp.v2.types.Contact;
import oasis.names.tc.wsrp.v2.types.CookieProtocol;
import oasis.names.tc.wsrp.v2.types.Event;
import oasis.names.tc.wsrp.v2.types.EventParams;
import oasis.names.tc.wsrp.v2.types.GetMarkup;
import oasis.names.tc.wsrp.v2.types.GetResource;
import oasis.names.tc.wsrp.v2.types.HandleEvents;
import oasis.names.tc.wsrp.v2.types.HandleEventsResponse;
import oasis.names.tc.wsrp.v2.types.InitCookie;
import oasis.names.tc.wsrp.v2.types.InteractionParams;
import oasis.names.tc.wsrp.v2.types.InvalidCookieFault;
import oasis.names.tc.wsrp.v2.types.MarkupContext;
import oasis.names.tc.wsrp.v2.types.MarkupParams;
import oasis.names.tc.wsrp.v2.types.MarkupResponse;
import oasis.names.tc.wsrp.v2.types.MimeRequest;
import oasis.names.tc.wsrp.v2.types.MimeResponse;
import oasis.names.tc.wsrp.v2.types.NamedString;
import oasis.names.tc.wsrp.v2.types.NavigationalContext;
import oasis.names.tc.wsrp.v2.types.Online;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.PersonName;
import oasis.names.tc.wsrp.v2.types.PortletContext;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.Postal;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.ResourceContext;
import oasis.names.tc.wsrp.v2.types.ResourceParams;
import oasis.names.tc.wsrp.v2.types.RuntimeContext;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;
import oasis.names.tc.wsrp.v2.types.SessionContext;
import oasis.names.tc.wsrp.v2.types.SessionParams;
import oasis.names.tc.wsrp.v2.types.StateChange;
import oasis.names.tc.wsrp.v2.types.Telecom;
import oasis.names.tc.wsrp.v2.types.TelephoneNum;
import oasis.names.tc.wsrp.v2.types.Templates;
import oasis.names.tc.wsrp.v2.types.UpdateResponse;
import oasis.names.tc.wsrp.v2.types.UploadContext;
import oasis.names.tc.wsrp.v2.types.UserContext;
import oasis.names.tc.wsrp.v2.types.UserProfile;

import org.apache.axis.message.MessageElement;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class ConsumerPortlet extends GenericPortlet {

	public static final String PORTLET_NAME_PREFIX = "WSRP_";

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			doProcessAction(actionRequest, actionResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void processEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws IOException, PortletException {

		try {
			doProcessEvent(eventRequest, eventResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			doRender(renderRequest, renderResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			doServeResource(resourceRequest, resourceResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void addFormField(
		List<NamedString> formParameters, String name, String[] values) {

		for (String value : values) {
			NamedString formParameter = new NamedString();

			formParameter.setName(name);
			formParameter.setValue(value);

			formParameters.add(formParameter);
		}
	}

	protected boolean authorize(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		String resourceID = GetterUtil.getString(
			resourceRequest.getResourceID());

		String url = GetterUtil.getString(
			resourceRequest.getParameter("wsrp-url"));
		String wsrpAuth = GetterUtil.getString(
			resourceRequest.getParameter("wsrp-auth"));

		StringBundler sb = new StringBundler(4);

		sb.append(resourceID);
		sb.append(url);
		sb.append(PortletPropsValues.SECURE_RESOURCE_URLS_SALT);

		String expectedWsrpAuth = encodeWSRPAuth(
			resourceRequest, sb.toString());

		if (wsrpAuth.equals(expectedWsrpAuth)) {
			return true;
		}

		sb.append(AuthTokenUtil.getToken(request));

		expectedWsrpAuth = encodeWSRPAuth(resourceRequest, sb.toString());

		if (wsrpAuth.equals(expectedWsrpAuth)) {
			return true;
		}

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		return false;
	}

	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		InteractionParams interactionParams = new InteractionParams();
		MarkupParams markupParams = new MarkupParams();
		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			actionRequest, actionResponse, wsrpConsumer, wsrpConsumerPortlet,
			wsrpConsumerManager, interactionParams, markupParams,
			portletContext, runtimeContext, userContext);

		PerformBlockingInteraction performBlockingInteraction =
			new PerformBlockingInteraction();

		performBlockingInteraction.setInteractionParams(interactionParams);
		performBlockingInteraction.setMarkupParams(markupParams);
		performBlockingInteraction.setPortletContext(portletContext);
		performBlockingInteraction.setRegistrationContext(
			wsrpConsumer.getRegistrationContext());
		performBlockingInteraction.setRuntimeContext(runtimeContext);
		performBlockingInteraction.setUserContext(userContext);

		ServiceHolder serviceHolder = getServiceHolder(
			actionRequest, wsrpConsumerManager, wsrpConsumer);

		WSRP_v2_Markup_PortType markupService =
			serviceHolder.getMarkupService();

		BlockingInteractionResponse blockingInteractionResponse =
			markupService.performBlockingInteraction(
				performBlockingInteraction);

		processBlockingInteractionResponse(
			actionRequest, actionResponse, wsrpConsumerManager, serviceHolder,
			blockingInteractionResponse);
	}

	protected void doProcessEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws Exception {

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		EventParams eventParams = new EventParams();
		MarkupParams markupParams = new MarkupParams();
		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			eventRequest, eventResponse, wsrpConsumer, wsrpConsumerPortlet,
			wsrpConsumerManager, eventParams, markupParams, portletContext,
			runtimeContext, userContext);

		HandleEvents handleEvents = new HandleEvents();

		handleEvents.setEventParams(eventParams);
		handleEvents.setMarkupParams(markupParams);
		handleEvents.setPortletContext(portletContext);
		handleEvents.setRegistrationContext(
			wsrpConsumer.getRegistrationContext());
		handleEvents.setRuntimeContext(runtimeContext);
		handleEvents.setUserContext(userContext);

		ServiceHolder serviceHolder = getServiceHolder(
			eventRequest, wsrpConsumerManager, wsrpConsumer);

		WSRP_v2_Markup_PortType markupService =
			serviceHolder.getMarkupService();

		HandleEventsResponse handleEventsResponse = markupService.handleEvents(
			handleEvents);

		processHandleEventsResponse(
			eventRequest, eventResponse, wsrpConsumerManager, serviceHolder,
			handleEventsResponse);
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		PortletSession portletSession = renderRequest.getPortletSession();

		MarkupContext markupContext =
			(MarkupContext)portletSession.getAttribute(WebKeys.MARKUP_CONTEXT);

		if (markupContext != null) {
			portletSession.removeAttribute(WebKeys.MARKUP_CONTEXT);
		}
		else {
			MarkupResponse markupResponse = getMarkupResponse(
				renderRequest, renderResponse);

			markupContext = markupResponse.getMarkupContext();
		}

		processMimeResponse(renderRequest, renderResponse, markupContext);
	}

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		if (PortletPropsValues.SECURE_RESOURCE_URLS_ENABLED) {
			if (!authorize(resourceRequest, resourceResponse)) {
				return;
			}
		}

		String resourceID = resourceRequest.getResourceID();

		String url = ParamUtil.getString(resourceRequest, "wsrp-url");
		boolean preferOperation = ParamUtil.getBoolean(
			resourceRequest, "wsrp-preferOperation");

		if (Validator.isNotNull(resourceID) && Validator.isNotNull(url) &&
			preferOperation) {

			getResource(resourceRequest, resourceResponse);
		}
		else if (Validator.isNotNull(url)) {
			proxyURL(resourceRequest, resourceResponse, url);
		}
		else if (Validator.isNotNull(resourceID)) {
			getResource(resourceRequest, resourceResponse);
		}
	}

	protected String encodeWSRPAuth(
			PortletRequest portletRequest, String wsrpAuth)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Company company = themeDisplay.getCompany();

		wsrpAuth = String.valueOf(wsrpAuth.hashCode());
		wsrpAuth = Encryptor.encrypt(company.getKeyObj(), wsrpAuth);
		wsrpAuth = Base64.toURLSafe(wsrpAuth);

		return wsrpAuth;
	}

	protected Calendar getBdate(User user) throws Exception {
		Calendar birthday = Calendar.getInstance();

		birthday.setTime(user.getBirthday());

		return birthday;
	}

	protected String getCharSet(String contentType) {
		if (Validator.isNotNull(contentType)) {
			int x = contentType.lastIndexOf("charset=");

			if (x >= 0) {
				return contentType.substring(x + 8).trim();
			}
		}

		return StringPool.UTF8;
	}

	protected Contact getContact(User user, String listTypeName)
		throws Exception {

		Contact contact = new Contact();

		Postal postal = getPostal(user, listTypeName);

		if (postal != null) {
			contact.setPostal(postal);
		}

		Online online = getOnline(user, listTypeName);

		contact.setOnline(online);

		Telecom telecom = getTelecom(user, listTypeName);

		contact.setTelecom(telecom);

		return contact;
	}

	protected String getGender(User user) throws Exception {
		if (user.isMale()) {
			return "M";
		}
		else {
			return "F";
		}
	}

	protected MarkupResponse getMarkupResponse(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		MarkupParams markupParams = new MarkupParams();
		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			portletRequest, portletResponse, wsrpConsumer, wsrpConsumerPortlet,
			wsrpConsumerManager, markupParams, portletContext, runtimeContext,
			userContext);

		GetMarkup getMarkup = new GetMarkup();

		getMarkup.setMarkupParams(markupParams);

		PortletContext existingPortletContext =
			(PortletContext)portletSession.getAttribute(
				WebKeys.PORTLET_CONTEXT);

		if (existingPortletContext != null) {
			getMarkup.setPortletContext(existingPortletContext);
		}
		else {
			getMarkup.setPortletContext(portletContext);
		}

		getMarkup.setRegistrationContext(wsrpConsumer.getRegistrationContext());
		getMarkup.setRuntimeContext(runtimeContext);
		getMarkup.setUserContext(userContext);

		ServiceHolder serviceHolder = getServiceHolder(
			portletRequest, wsrpConsumerManager, wsrpConsumer);

		WSRP_v2_Markup_PortType markupService =
			serviceHolder.getMarkupService();

		MarkupResponse markupResponse = null;

		try {
			markupResponse = markupService.getMarkup(getMarkup);
		}
		catch (InvalidCookieFault icf) {
			InitCookie initCookie = new InitCookie();

			markupService.initCookie(initCookie);

			markupResponse = markupService.getMarkup(getMarkup);
		}

		// There is a memory leak in Axis that caches the entire response after
		// each call. See LPS-25067.

		Stub stub = (Stub)markupService;

		stub._createCall();

		processMarkupResponse(
			portletRequest, portletResponse, serviceHolder, markupResponse);

		return markupResponse;
	}

	protected Online getOnline(User user, String listTypeName)
		throws Exception {

		Online online = new Online();

		String email = getOnlineEmail(user, "E-mail");

		if (email != null) {
			online.setEmail(email);
		}

		String uri = getOnlineUri(user, listTypeName);

		if (uri != null) {
			online.setUri(uri);
		}

		return online;
	}

	protected String getOnlineEmail(User user, String listTypeName)
		throws Exception {

		List<EmailAddress> emailAddresses =
			EmailAddressLocalServiceUtil.getEmailAddresses(
				user.getCompanyId(),
				com.liferay.portal.model.Contact.class.getName(),
				user.getContactId());

		for (EmailAddress emailAddress : emailAddresses) {
			ListType listType = emailAddress.getType();

			if (!listTypeName.equals(listType.getName())) {
				continue;
			}

			return emailAddress.getAddress();
		}

		return user.getEmailAddress();
	}

	protected String getOnlineUri(User user, String listTypeName)
		throws Exception {

		List<Website> websites = WebsiteLocalServiceUtil.getWebsites(
			user.getCompanyId(),
			com.liferay.portal.model.Contact.class.getName(),
			user.getContactId());

		for (Website website : websites) {
			ListType listType = website.getType();

			if (!listTypeName.equals(listType.getName())) {
				continue;
			}

			return website.getUrl();
		}

		return null;
	}

	protected PersonName getPersonName(User user) throws Exception {
		PersonName personName = new PersonName();

		personName.setFamily(user.getLastName());
		personName.setGiven(user.getFirstName());
		personName.setMiddle(user.getMiddleName());
		personName.setNickname(user.getScreenName());

		com.liferay.portal.model.Contact contact = user.getContact();

		try {
			ListType listType = ListTypeServiceUtil.getListType(
				contact.getPrefixId());

			personName.setPrefix(listType.getName());
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to retrieve name prefix", e);
			}
		}

		try {
			ListType listType = ListTypeServiceUtil.getListType(
				contact.getSuffixId());

			personName.setSuffix(listType.getName());
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to retrieve name suffix", e);
			}
		}

		return personName;
	}

	protected PortletMode getPortletMode(String portletMode) {
		return new PortletMode(portletMode.substring(5));
	}

	protected Postal getPostal(User user, String listTypeName)
		throws Exception {

		List<Address> addresses = AddressLocalServiceUtil.getAddresses(
			user.getCompanyId(),
			com.liferay.portal.model.Contact.class.getName(),
			user.getContactId());

		for (Address address : addresses) {
			ListType listType = address.getType();

			if (!listTypeName.equals(listType.getName())) {
				continue;
			}

			Postal postal = new Postal();

			postal.setCity(address.getCity());
			postal.setCountry(address.getCountry().getName());
			postal.setName(address.getUserName());
			postal.setPostalcode(address.getZip());
			postal.setStateprov(address.getRegion().getName());

			String street =
				address.getStreet1() + address.getStreet2() +
					address.getStreet3();

			postal.setStreet(street);

			return postal;
		}

		return null;
	}

	protected void getResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		PortletSession portletSession = resourceRequest.getPortletSession();

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		PortletContext portletContext = new PortletContext();
		ResourceParams resourceParams = new ResourceParams();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			resourceRequest, resourceResponse, wsrpConsumer,
			wsrpConsumerPortlet, wsrpConsumerManager, portletContext,
			resourceParams, runtimeContext, userContext);

		GetResource getResource = new GetResource();

		PortletContext existingPortletContext =
			(PortletContext)portletSession.getAttribute(
				WebKeys.PORTLET_CONTEXT);

		if (existingPortletContext != null) {
			getResource.setPortletContext(existingPortletContext);
		}
		else {
			getResource.setPortletContext(portletContext);
		}

		getResource.setRegistrationContext(
			wsrpConsumer.getRegistrationContext());
		getResource.setResourceParams(resourceParams);
		getResource.setRuntimeContext(runtimeContext);
		getResource.setUserContext(userContext);

		ServiceHolder serviceHolder = getServiceHolder(
			resourceRequest, wsrpConsumerManager, wsrpConsumer);

		WSRP_v2_Markup_PortType markupService =
			serviceHolder.getMarkupService();

		oasis.names.tc.wsrp.v2.types.ResourceResponse wsrpResourceResponse =
			markupService.getResource(getResource);

		processResourceResponse(
			resourceRequest, resourceResponse, wsrpConsumerManager,
			serviceHolder, wsrpResourceResponse);
	}

	protected ServiceHolder getServiceHolder(
			PortletRequest portletRequest,
			WSRPConsumerManager wsrpConsumerManager, WSRPConsumer wsrpConsumer)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		String markupServiceKey = getSessionKey(
			WebKeys.MARKUP_SERVICE, portletRequest, wsrpConsumer);

		TransientValue<ServiceHolder> serviceHolderTransientValue =
			(TransientValue<ServiceHolder>)portletSession.getAttribute(
				markupServiceKey, PortletSession.APPLICATION_SCOPE);

		if ((serviceHolderTransientValue == null) ||
			serviceHolderTransientValue.isNull()) {

			ServiceHolder serviceHolder = new ServiceHolder();

			WSRP_v2_Markup_PortType markupService =
				wsrpConsumerManager.getMarkupService();

			serviceHolder.setMarkupService(markupService);

			RegistrationContext registrationContext =
				wsrpConsumer.getRegistrationContext();

			serviceHolder.setRegistrationContext(registrationContext);

			serviceHolderTransientValue = new TransientValue<>(serviceHolder);

			portletSession.setAttribute(
				markupServiceKey, serviceHolderTransientValue,
				PortletSession.APPLICATION_SCOPE);

			ServiceDescription serviceDescription =
				wsrpConsumerManager.getServiceDescription();

			String cookieKey = getSessionKey(
				WebKeys.COOKIE, portletRequest, wsrpConsumer);

			String cookie = (String)portletSession.getAttribute(
				cookieKey, PortletSession.APPLICATION_SCOPE);

			CookieProtocol cookieProtocol =
				serviceDescription.getRequiresInitCookie();

			if ((cookie == null) && (cookieProtocol != null)) {
				String cookieProtocolValue = cookieProtocol.getValue();

				if (cookieProtocolValue.equals(CookieProtocol._perGroup) ||
					cookieProtocolValue.equals(CookieProtocol._perUser)) {

					InitCookie initCookie = new InitCookie();

					initCookie.setRegistrationContext(registrationContext);

					markupService.initCookie(initCookie);

					cookie = WSRPHTTPSender.getCurrentCookie();

					portletSession.setAttribute(
						cookieKey, cookie, PortletSession.APPLICATION_SCOPE);
				}
			}
		}

		return serviceHolderTransientValue.getValue();
	}

	protected String getSessionKey(
		String baseKey, PortletRequest portletRequest,
		WSRPConsumer wsrpConsumer) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler();

		sb.append(baseKey);
		sb.append(StringPool.UNDERLINE);
		sb.append(themeDisplay.getScopeGroupId());
		sb.append(StringPool.UNDERLINE);
		sb.append(wsrpConsumer.getWsrpConsumerId());
		sb.append(StringPool.UNDERLINE);
		sb.append(wsrpConsumer.getUrl());

		return sb.toString();
	}

	protected Telecom getTelecom(User user, String listTypeName)
		throws Exception {

		Telecom telecom = new Telecom();

		TelephoneNum faxTelephoneNum = getTelephoneNum(
			user, listTypeName + " Fax");

		if (faxTelephoneNum != null) {
			telecom.setFax(faxTelephoneNum);
		}

		TelephoneNum phoneTelephoneNum = getTelephoneNum(user, listTypeName);

		if (phoneTelephoneNum != null) {
			telecom.setTelephone(phoneTelephoneNum);
		}

		return telecom;
	}

	protected TelephoneNum getTelephoneNum(User user, String listTypeName)
		throws Exception {

		List<Phone> phones = PhoneLocalServiceUtil.getPhones(
			user.getCompanyId(),
			com.liferay.portal.model.Contact.class.getName(),
			user.getContactId());

		for (Phone phone : phones) {
			ListType listType = phone.getType();

			if (!listTypeName.equals(listType.getName())) {
				continue;
			}

			TelephoneNum telephoneNum = new TelephoneNum();

			telephoneNum.setExt(phone.getExtension());
			telephoneNum.setNumber(phone.getNumber());

			return telephoneNum;
		}

		return null;
	}

	protected UserProfile getUserProfile(User user) throws Exception {
		UserProfile userProfile = new UserProfile();

		Calendar bdate = getBdate(user);

		userProfile.setBdate(bdate);

		Contact businessInfoContact = getContact(user, "Business");

		userProfile.setBusinessInfo(businessInfoContact);

		String gender = getGender(user);

		userProfile.setGender(gender);

		Contact homeInfoContact = getContact(user, "Personal");

		userProfile.setHomeInfo(homeInfoContact);

		PersonName personName = getPersonName(user);

		userProfile.setName(personName);

		return userProfile;
	}

	protected WindowState getWindowState(String windowState) {
		return new WindowState(windowState.substring(5));
	}

	protected WSRPConsumerPortlet getWSRPConsumerPortlet() throws Exception {
		PortletConfig portletConfig = getPortletConfig();

		String portletName = portletConfig.getPortletName();

		int pos = portletName.indexOf(StringPool.UNDERLINE);

		String wsrpConsumerPortletUuid = portletName.substring(pos + 1);

		wsrpConsumerPortletUuid = PortalUUIDUtil.fromJsSafeUuid(
			wsrpConsumerPortletUuid);

		WSRPConsumerPortlet wsrpConsumerPortlet =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(
				wsrpConsumerPortletUuid);

		return wsrpConsumerPortlet;
	}

	protected String getWSRPKey(String key) {
		return "wsrp:".concat(key);
	}

	protected String getWSRPMode(PortletMode portletMode) {
		return getWSRPKey(portletMode.toString());
	}

	protected String getWSRPWindowState(WindowState windowState) {
		return getWSRPKey(windowState.toString());
	}

	protected void initContexts(
			ActionRequest actionRequest, ActionResponse actionResponse,
			WSRPConsumer wsrpConsumer, WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager,
			InteractionParams interactionParams, MarkupParams markupParams,
			PortletContext portletContext, RuntimeContext runtimeContext,
			UserContext userContext)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		initContexts(
			actionRequest, actionResponse, wsrpConsumer, wsrpConsumerPortlet,
			wsrpConsumerManager, markupParams, portletContext, runtimeContext,
			userContext);

		interactionParams.setPortletStateChange(StateChange.cloneBeforeWrite);

		String interactionState = actionRequest.getParameter(
			"wsrp-interactionState");

		interactionParams.setInteractionState(interactionState);

		String contentType = request.getContentType();

		if (Validator.isNotNull(contentType) &&
			contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {

			processMultipartForm(
				actionRequest, actionResponse, interactionParams);
		}
		else {
			processFormParameters(
				actionRequest, actionResponse, interactionParams);
		}
	}

	protected void initContexts(
			EventRequest eventRequest, EventResponse eventResponse,
			WSRPConsumer wsrpConsumer, WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager, EventParams eventParams,
			MarkupParams markupParams, PortletContext portletContext,
			RuntimeContext runtimeContext, UserContext userContext)
		throws Exception {

		initContexts(
			eventRequest, eventResponse, wsrpConsumer, wsrpConsumerPortlet,
			wsrpConsumerManager, markupParams, portletContext, runtimeContext,
			userContext);

		eventParams.setPortletStateChange(StateChange.cloneBeforeWrite);

		javax.portlet.Event jxEvent = eventRequest.getEvent();

		Event event = (Event)jxEvent.getValue();

		eventParams.setEvents(new Event[] {event});
	}

	protected void initContexts(
			PortletRequest portletRequest, PortletResponse portletResponse,
			WSRPConsumer wsrpConsumer, WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager, MimeRequest mimeRequest,
			PortletContext portletContext, RuntimeContext runtimeContext,
			UserContext userContext)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		// Markup params

		ClientData clientData = new ClientData();

		clientData.setRequestVerb(request.getMethod());
		clientData.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

		User user = themeDisplay.getUser();

		List<MessageElement> clientAttributes = new ArrayList<>();

		Enumeration<String> enu = request.getHeaderNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = request.getHeader(name);

			ExtensionHelperUtil.addMessageElement(
				clientAttributes, name, value);
		}

		ExtensionHelperUtil.addMessageElement(
			clientAttributes, HttpHeaders.LIFERAY_EMAIL_ADDRESS,
			user.getEmailAddress());
		ExtensionHelperUtil.addMessageElement(
			clientAttributes, HttpHeaders.LIFERAY_SCREEN_NAME,
			user.getScreenName());
		ExtensionHelperUtil.addMessageElement(
			clientAttributes, HttpHeaders.LIFERAY_USER_ID,
			String.valueOf(user.getUserId()));

		ConsumerRequestExtensionsHelper.addClientAttributes(clientAttributes);

		clientData.setExtensions(
			ExtensionHelperUtil.getExtensions(clientAttributes));

		mimeRequest.setClientData(clientData);

		List<Locale> locales = Collections.list(portletRequest.getLocales());

		String[] localesArray = new String[locales.size()];

		for (int i = 0; i < locales.size(); i++) {
			Locale locale = locales.get(i);

			localesArray[i] = locale.toString();
		}

		Locale locale = themeDisplay.getLocale();

		if (!locales.contains(locale)) {
			localesArray = ArrayUtil.append(
				new String[] {locale.toString(), locale.getLanguage()},
				localesArray);
		}

		mimeRequest.setLocales(localesArray);

		String[] markupCharacterSets = null;

		if (Validator.isNotNull(wsrpConsumer.getMarkupCharacterSets())) {
			String markupCharacterSetsString =
				wsrpConsumer.getMarkupCharacterSets();

			markupCharacterSetsString =
				MarkupCharacterSetsUtil.getSupportedMarkupCharacterSets(
					markupCharacterSetsString);

			markupCharacterSets = StringUtil.split(markupCharacterSetsString);
		}
		else {
			markupCharacterSets = _CHAR_SETS;
		}

		mimeRequest.setMarkupCharacterSets(markupCharacterSets);

		mimeRequest.setMimeTypes(_MIME_TYPES);
		mimeRequest.setMode(getWSRPMode(portletRequest.getPortletMode()));
		mimeRequest.setWindowState(
			getWSRPWindowState(portletRequest.getWindowState()));

		String[] modes = {
			getWSRPMode(PortletMode.EDIT), getWSRPMode(PortletMode.HELP),
			getWSRPMode(PortletMode.VIEW)
		};

		mimeRequest.setValidNewModes(modes);

		String[] windowStates = {
			getWSRPWindowState(WindowState.MAXIMIZED),
			getWSRPWindowState(WindowState.MINIMIZED),
			getWSRPWindowState(WindowState.NORMAL)
		};

		mimeRequest.setValidNewWindowStates(windowStates);

		// Navigational context

		NavigationalContext navigationalContext = new NavigationalContext();

		String navigationalState = portletRequest.getParameter(
			"wsrp-navigationalState");

		if (Validator.isNotNull(navigationalState)) {
			navigationalState = new String(
				Base64.decode(Base64.fromURLSafe(navigationalState)),
				StringPool.UTF8);

			navigationalContext.setOpaqueValue(navigationalState);
		}

		Map<String, String[]> publicParameterMap =
			portletRequest.getPublicParameterMap();

		List<NamedString> publicValues = new ArrayList<>();

		for (Map.Entry<String, String[]> entry :
				publicParameterMap.entrySet()) {

			String name = entry.getKey();
			String[] values = entry.getValue();

			for (String value : values) {
				NamedString publicValue = new NamedString();

				publicValue.setName(name);
				publicValue.setValue(value);

				publicValues.add(publicValue);
			}
		}

		navigationalContext.setPublicValues(
			publicValues.toArray(new NamedString[publicValues.size()]));

		mimeRequest.setNavigationalContext(navigationalContext);

		if (mimeRequest instanceof MarkupParams) {
			processFormParameters(portletRequest, portletResponse, mimeRequest);
		}

		// Portlet context

		portletContext.setPortletHandle(wsrpConsumerPortlet.getPortletHandle());

		// Runtime context

		runtimeContext.setNamespacePrefix(portletResponse.getNamespace());
		runtimeContext.setPortletInstanceKey(portletResponse.getNamespace());

		SessionContext sessionContext =
			(SessionContext)portletSession.getAttribute(
				WebKeys.SESSION_CONTEXT);

		if (sessionContext != null) {
			SessionParams sessionParams = new SessionParams();

			sessionParams.setSessionID(sessionContext.getSessionID());

			runtimeContext.setSessionParams(sessionParams);
		}

		runtimeContext.setUserAuthentication("wsrp:password");

		PortletDescription portletDescription =
			wsrpConsumerManager.getPortletDescription(
				wsrpConsumerPortlet.getPortletHandle());

		Boolean doesUrlTemplateProcessing =
			portletDescription.getDoesUrlTemplateProcessing();

		if ((doesUrlTemplateProcessing != null) && doesUrlTemplateProcessing) {
			Templates templates = new Templates();

			templates.setBlockingActionTemplate(_BLOCKING_ACTION_TEMPLATE);
			templates.setRenderTemplate(_RENDER_TEMPLATE);
			templates.setResourceTemplate(_RESOURCE_TEMPLATE);
			templates.setSecureBlockingActionTemplate(
				_BLOCKING_ACTION_TEMPLATE);
			templates.setSecureRenderTemplate(_RENDER_TEMPLATE);
			templates.setSecureResourceTemplate(_RESOURCE_TEMPLATE);

			runtimeContext.setTemplates(templates);
		}

		// User context

		UserProfile userProfile = getUserProfile(user);

		ConsumerRequestExtensionsHelper.addUserProfileAttributes(
			userProfile, user);

		userContext.setProfile(userProfile);

		userContext.setUserContextKey(String.valueOf(user.getUserId()));
	}

	protected void initContexts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			WSRPConsumer wsrpConsumer, WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager,
			PortletContext portletContext, ResourceParams resourceParams,
			RuntimeContext runtimeContext, UserContext userContext)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		initContexts(
			resourceRequest, resourceResponse, wsrpConsumer,
			wsrpConsumerPortlet, wsrpConsumerManager, resourceParams,
			portletContext, runtimeContext, userContext);

		String resourceID = resourceRequest.getResourceID();

		resourceParams.setResourceID(resourceID);

		resourceParams.setPortletStateChange(StateChange.cloneBeforeWrite);

		String resourceState = ParamUtil.getString(
			resourceRequest, "wsrp-resourceState");

		if (Validator.isNotNull(resourceState)) {
			resourceParams.setResourceState(resourceState);
		}

		String resourceCacheability = ParamUtil.getString(
			resourceRequest, "wsrp-resourceCacheability");

		if (Validator.isNotNull(resourceCacheability)) {
			resourceParams.setResourceState(resourceCacheability);
		}

		String contentType = request.getContentType();

		if (Validator.isNotNull(contentType) &&
			contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {

			processMultipartForm(
				resourceRequest, resourceResponse, resourceParams);
		}
		else {
			processFormParameters(
				resourceRequest, resourceResponse, resourceParams);
		}
	}

	protected boolean isReservedParameter(String name) {
		if (name.startsWith("wsrp-")) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void processBlockingInteractionResponse(
			ActionRequest actionRequest, ActionResponse actionResponse,
			WSRPConsumerManager wsrpConsumerManager,
			ServiceHolder serviceHolder,
			BlockingInteractionResponse blockingInteractionResponse)
		throws Exception {

		String redirectURL = blockingInteractionResponse.getRedirectURL();

		if (Validator.isNotNull(redirectURL)) {
			sendRedirect(actionRequest, actionResponse, redirectURL);

			return;
		}

		processUpdateResponse(
			actionRequest, actionResponse, wsrpConsumerManager, serviceHolder,
			blockingInteractionResponse.getUpdateResponse());
	}

	protected void processFormParameters(
		ActionRequest actionRequest, ActionResponse actionResponse,
		InteractionParams interactionParams) {

		List<NamedString> formParameters = processFormParameters(
			actionRequest, actionResponse);

		if (!formParameters.isEmpty()) {
			interactionParams.setFormParameters(
				formParameters.toArray(new NamedString[formParameters.size()]));
		}
	}

	protected List<NamedString> processFormParameters(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		List<NamedString> formParameters = new ArrayList<>();

		Enumeration<String> enu = portletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (isReservedParameter(name)) {
				continue;
			}

			String[] values = portletRequest.getParameterValues(name);

			if (values == null) {
				continue;
			}

			addFormField(formParameters, name, values);
		}

		return formParameters;
	}

	protected void processFormParameters(
		PortletRequest portletRequest, PortletResponse portletResponse,
		MimeRequest mimeRequest) {

		List<MessageElement> formParameters = new ArrayList<>();

		Enumeration<String> enu = portletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (isReservedParameter(name)) {
				continue;
			}

			String[] values = portletRequest.getParameterValues(name);

			if (values == null) {
				continue;
			}

			for (String value : values) {
				ExtensionHelperUtil.addMessageElement(
					formParameters, name, value);
			}
		}

		if (!formParameters.isEmpty()) {
			mimeRequest.setExtensions(
				ExtensionHelperUtil.getExtensions(formParameters));
		}
	}

	protected void processFormParameters(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse,
		ResourceParams resourceParams) {

		List<NamedString> formParameters = processFormParameters(
			resourceRequest, resourceResponse);

		if (!formParameters.isEmpty()) {
			resourceParams.setFormParameters(
				formParameters.toArray(new NamedString[formParameters.size()]));
		}
	}

	protected void processHandleEventsResponse(
			EventRequest eventRequest, EventResponse eventResponse,
			WSRPConsumerManager wsrpConsumerManager,
			ServiceHolder serviceHolder,
			HandleEventsResponse handleEventsResponse)
		throws Exception {

		processUpdateResponse(
			eventRequest, eventResponse, wsrpConsumerManager, serviceHolder,
			handleEventsResponse.getUpdateResponse());
	}

	protected void processMarkupResponse(
		PortletRequest portletRequest, PortletResponse portletResponse,
		ServiceHolder serviceHolder, MarkupResponse markupResponse) {

		PortletSession portletSession = portletRequest.getPortletSession();

		SessionContext sessionContext = markupResponse.getSessionContext();

		updateSessionContext(portletSession, serviceHolder, sessionContext);
	}

	protected void processMimeResponse(
			PortletRequest portletRequest,
			javax.portlet.MimeResponse jxMimeResponse,
			MimeResponse mimeResponse)
		throws Exception {

		String contentType = GetterUtil.get(
			mimeResponse.getMimeType(), ContentTypes.TEXT_HTML_UTF8);

		jxMimeResponse.setContentType(contentType);

		String charSet = getCharSet(contentType);

		String itemString = mimeResponse.getItemString();
		byte[] itemBinary = mimeResponse.getItemBinary();

		Boolean requiresRewriting = mimeResponse.getRequiresRewriting();

		if (requiresRewriting == null) {
			requiresRewriting = ParamUtil.getBoolean(
				portletRequest, "wsrp-requiresRewrite");
		}

		if (requiresRewriting && contentType.contains(ContentTypes.TEXT)) {
			if (itemBinary != null) {
				itemString = new String(itemBinary, charSet);
			}

			itemString = rewriteURLs(
				portletRequest, jxMimeResponse, itemString);
		}

		if (Validator.isNotNull(itemString)) {
			if (jxMimeResponse instanceof ResourceResponse) {
				ResourceResponse resourceResponse =
					(ResourceResponse)jxMimeResponse;

				resourceResponse.setContentLength(itemString.length());
			}

			PortletResponseUtil.write(jxMimeResponse, itemString);
		}
		else if (itemBinary != null) {
			if (jxMimeResponse instanceof ResourceResponse) {
				ResourceResponse resourceResponse =
					(ResourceResponse)jxMimeResponse;

				resourceResponse.setContentLength(itemBinary.length);
			}

			PortletResponseUtil.write(jxMimeResponse, itemBinary);
		}
	}

	protected void processMultipartForm(
			ActionRequest actionRequest, ActionResponse actionResponse,
			InteractionParams interactionParams)
		throws Exception {

		Object[] formData = processMultipartForm(actionRequest, actionResponse);

		List<NamedString> formParameters = (List<NamedString>)formData[0];
		List<UploadContext> uploadContexts = (List<UploadContext>)formData[1];

		if (!formParameters.isEmpty()) {
			interactionParams.setFormParameters(
				formParameters.toArray(new NamedString[formParameters.size()]));
		}

		if (!uploadContexts.isEmpty()) {
			interactionParams.setUploadContexts(
				uploadContexts.toArray(
					new UploadContext[uploadContexts.size()]));
		}
	}

	protected Object[] processMultipartForm(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		List<NamedString> formParameters = new ArrayList<>();
		List<UploadContext> uploadContexts = new ArrayList<>();

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(portletRequest);

		Enumeration<String> enu = uploadPortletRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (isReservedParameter(name) || name.startsWith("p_p_")) {
				continue;
			}

			if (uploadPortletRequest.isFormField(name)) {
				String[] values = uploadPortletRequest.getParameterValues(name);

				if (values == null) {
					continue;
				}

				addFormField(formParameters, name, values);
			}
			else {
				UploadContext uploadContext = new UploadContext();

				String contentType = uploadPortletRequest.getContentType(name);

				uploadContext.setMimeType(contentType);

				StringBuilder sb = new StringBuilder();

				sb.append("form-data; ");
				sb.append("name=");
				sb.append(name);
				sb.append("; filename=");
				sb.append(uploadPortletRequest.getFileName(name));

				NamedString mimeAttribute = new NamedString();

				mimeAttribute.setName(HttpHeaders.CONTENT_DISPOSITION);
				mimeAttribute.setValue(sb.toString());

				uploadContext.setMimeAttributes(
					new NamedString[] {mimeAttribute});

				InputStream inputStream = null;

				try {
					inputStream = uploadPortletRequest.getFileAsStream(name);

					if (inputStream == null) {
						continue;
					}

					byte[] bytes = FileUtil.getBytes(inputStream);

					if (bytes == null) {
						continue;
					}

					uploadContext.setUploadData(bytes);
				}
				finally {
					StreamUtil.cleanUp(inputStream);
				}

				uploadContexts.add(uploadContext);
			}
		}

		return new Object[] {formParameters, uploadContexts};
	}

	protected void processMultipartForm(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			ResourceParams resourceParams)
		throws Exception {

		Object[] formData = processMultipartForm(
			resourceRequest, resourceResponse);

		List<NamedString> formParameters = (List<NamedString>)formData[0];
		List<UploadContext> uploadContexts = (List<UploadContext>)formData[1];

		if (!formParameters.isEmpty()) {
			resourceParams.setFormParameters(
				formParameters.toArray(new NamedString[formParameters.size()]));
		}

		if (!uploadContexts.isEmpty()) {
			resourceParams.setUploadContexts(
				uploadContexts.toArray(
					new UploadContext[uploadContexts.size()]));
		}
	}

	protected void processResourceResponse(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			WSRPConsumerManager wsrpConsumerManager,
			ServiceHolder serviceHolder,
			oasis.names.tc.wsrp.v2.types.ResourceResponse wsrpResourceResponse)
		throws Exception {

		PortletSession portletSession = resourceRequest.getPortletSession();

		PortletContext portletContext =
			wsrpResourceResponse.getPortletContext();

		if (portletContext != null) {
			portletSession.setAttribute(
				WebKeys.PORTLET_CONTEXT, portletContext);
		}

		SessionContext sessionContext =
			wsrpResourceResponse.getSessionContext();

		updateSessionContext(portletSession, serviceHolder, sessionContext);

		ResourceContext resourceContext =
			wsrpResourceResponse.getResourceContext();

		CacheControl cacheControl = resourceContext.getCacheControl();

		if (cacheControl != null) {
			if (cacheControl.getExpires() == 0) {
				resourceResponse.setProperty(
					HttpHeaders.CACHE_CONTROL,
					HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
			}
			else if (cacheControl.getExpires() > 0) {
				resourceResponse.setProperty(
					HttpHeaders.CACHE_CONTROL,
					"max-age=" + cacheControl.getExpires());
			}
			else {
				resourceResponse.setProperty(
					HttpHeaders.CACHE_CONTROL,
					HttpHeaders.CACHE_CONTROL_DEFAULT_VALUE);
			}
		}

		NamedString[] clientAttributes = resourceContext.getClientAttributes();

		if (clientAttributes != null) {
			for (NamedString clientAttribute : clientAttributes) {
				String name = clientAttribute.getName();
				String value = clientAttribute.getValue();

				if (StringUtil.equalsIgnoreCase(
						name, HttpHeaders.CONTENT_DISPOSITION)) {

					resourceResponse.setProperty(
						HttpHeaders.CONTENT_DISPOSITION, value);

					break;
				}
			}
		}

		processMimeResponse(resourceRequest, resourceResponse, resourceContext);
	}

	protected void processUpdateResponse(
			PortletRequest portletRequest,
			StateAwareResponse stateAwareResponse,
			WSRPConsumerManager wsrpConsumerManager,
			ServiceHolder serviceHolder, UpdateResponse updateResponse)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		if (updateResponse == null) {
			return;
		}

		portletSession.setAttribute(
			WebKeys.MARKUP_CONTEXT, updateResponse.getMarkupContext());

		NavigationalContext navigationalContext =
			updateResponse.getNavigationalContext();

		if (navigationalContext != null) {
			String opaqueValue = navigationalContext.getOpaqueValue();

			if (Validator.isNotNull(opaqueValue)) {
				byte[] opaqueValueBytes = opaqueValue.getBytes(StringPool.UTF8);

				opaqueValue = Base64.toURLSafe(Base64.encode(opaqueValueBytes));

				stateAwareResponse.setRenderParameter(
					"wsrp-navigationalState", opaqueValue);
			}

			NamedString[] publicValues = navigationalContext.getPublicValues();

			if (publicValues != null) {
				for (NamedString publicValue : publicValues) {
					String name = publicValue.getName();
					String value = publicValue.getValue();

					if (Validator.isNotNull(value)) {
						stateAwareResponse.setRenderParameter(name, value);
					}
					else {
						stateAwareResponse.removePublicRenderParameter(name);
					}
				}
			}
		}

		PortletContext portletContext = updateResponse.getPortletContext();

		if (portletContext != null) {
			portletSession.setAttribute(
				WebKeys.PORTLET_CONTEXT, portletContext);
		}

		SessionContext sessionContext = updateResponse.getSessionContext();

		updateSessionContext(portletSession, serviceHolder, sessionContext);

		String portletMode = updateResponse.getNewMode();

		if (Validator.isNotNull(portletMode)) {
			stateAwareResponse.setPortletMode(getPortletMode(portletMode));
		}

		String windowState = updateResponse.getNewWindowState();

		if (Validator.isNotNull(windowState)) {
			stateAwareResponse.setWindowState(getWindowState(windowState));
		}

		Event[] events = updateResponse.getEvents();

		if (events != null) {
			for (Event event : events) {
				QName qName = wsrpConsumerManager.getEventQName(
					event.getName());

				event.setName(qName);

				stateAwareResponse.setEvent(qName, event);
			}
		}
	}

	protected void proxyURL(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			String url)
		throws Exception {

		PortletSession portletSession = resourceRequest.getPortletSession();

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		Http.Options options = new Http.Options();

		options.setLocation(url);

		String cookieKey = getSessionKey(
			WebKeys.COOKIE, resourceRequest, wsrpConsumer);

		String cookie = (String)portletSession.getAttribute(
			cookieKey, PortletSession.APPLICATION_SCOPE);

		if (Validator.isNotNull(cookie)) {
			Map<String, String> headers = new HashMap<>();

			headers.put(HttpHeaders.COOKIE, cookie);

			options.setHeaders(headers);
		}

		byte[] bytes = HttpUtil.URLtoByteArray(options);

		Http.Response response = options.getResponse();

		String contentDisposition = response.getHeader(
			HttpHeaders.CONTENT_DISPOSITION);

		if (Validator.isNotNull(contentDisposition)) {
			resourceResponse.setProperty(
				HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
		}

		int contentLength = response.getContentLength();

		if (contentLength >= 0) {
			resourceResponse.setContentLength(contentLength);
		}

		String contentType = response.getContentType();

		if (Validator.isNotNull(contentType)) {
			resourceResponse.setContentType(contentType);
		}

		String charSet = getCharSet(contentType);

		if (ParamUtil.getBoolean(resourceRequest, "wsrp-requiresRewrite")) {
			String content = rewriteURLs(
				resourceRequest, resourceResponse, new String(bytes, charSet));

			PortletResponseUtil.write(resourceResponse, content);
		}
		else {
			PortletResponseUtil.write(resourceResponse, bytes);
		}
	}

	protected String rewriteURL(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Map<String, String> parameterMap)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)portletResponse;

		String lifecycle = parameterMap.get("wsrp-urlType");

		LiferayPortletURL liferayPortletURL = null;

		if (lifecycle.equals("blockingAction")) {
			liferayPortletURL =
				(LiferayPortletURL)liferayPortletResponse.createActionURL();
		}
		else if (lifecycle.equals("render")) {
			liferayPortletURL =
				(LiferayPortletURL)liferayPortletResponse.createRenderURL();
		}
		else if (lifecycle.equals("resource")) {
			liferayPortletURL =
				(LiferayPortletURL)liferayPortletResponse.createResourceURL();

			if (PortletPropsValues.SECURE_RESOURCE_URLS_ENABLED) {
				secureResourceURL(
					portletRequest, liferayPortletURL, parameterMap);
			}
		}

		for (Map.Entry<String, String> parameter : parameterMap.entrySet()) {
			String name = parameter.getKey();
			String value = parameter.getValue();

			if (name.equals("wsrp-mode")) {
				try {
					liferayPortletURL.setPortletMode(getPortletMode(value));
				}
				catch (Exception e) {
					liferayPortletURL.setPortletMode(PortletMode.VIEW);
				}
			}
			else if (name.equals("wsrp-navigationalState")) {
				if (Validator.isNotNull(value)) {
					byte[] valueBytes = value.getBytes(StringPool.UTF8);

					value = Base64.toURLSafe(Base64.encode(valueBytes));

					liferayPortletURL.setParameter(name, value);
				}
			}
			else if (name.equals("wsrp-navigationalValues")) {
				Matcher navigationalValuesMatcher =
					_navigationalValuesPattern.matcher(value);

				while (navigationalValuesMatcher.find()) {
					String navigationalValuesName =
						navigationalValuesMatcher.group(1);
					String navigationalValuesValue =
						navigationalValuesMatcher.group(2);

					if (Validator.isNull(navigationalValuesValue)) {
						liferayPortletURL.removePublicRenderParameter(
							navigationalValuesName);
					}
					else {
						liferayPortletURL.setParameter(
							navigationalValuesName, navigationalValuesValue,
							true);
					}
				}
			}
			else if (name.equals("wsrp-resourceID")) {
				liferayPortletURL.setResourceID(value);
			}
			else if (name.equals("wsrp-urlType")) {
			}
			else if (name.equals("wsrp-windowState")) {
				try {
					liferayPortletURL.setWindowState(getWindowState(value));
				}
				catch (Exception e) {
					liferayPortletURL.setWindowState(WindowState.NORMAL);
				}
			}
			else {
				liferayPortletURL.setParameter(name, value);
			}
		}

		String url = liferayPortletURL.toString();

		return url;
	}

	protected String rewriteURLs(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String content)
		throws Exception {

		Matcher rewriteMatcher = _rewritePattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (rewriteMatcher.find()) {
			String namespace = rewriteMatcher.group(1);
			String url = rewriteMatcher.group(2);
			String extensionURL1 = rewriteMatcher.group(3);
			String extensionURL2 = rewriteMatcher.group(4);

			Map<String, String> parameterMap = new HashMap<>();

			if (Validator.isNotNull(namespace)) {
				rewriteMatcher.appendReplacement(
					sb, portletResponse.getNamespace());
			}
			else if (Validator.isNotNull(url)) {
				Matcher parameterMatcher = _parameterPattern.matcher(url);

				while (parameterMatcher.find()) {
					String name = parameterMatcher.group(1);
					String value = parameterMatcher.group(2);

					if (Validator.isNull(value) ||
						value.equals(StringPool.DOUBLE_QUOTE)) {

						continue;
					}

					parameterMap.put(name, HttpUtil.decodeURL(value));
				}

				String rewrittenURL = rewriteURL(
					portletRequest, portletResponse, parameterMap);

				rewriteMatcher.appendReplacement(sb, rewrittenURL);
			}
			else if (Validator.isNotNull(extensionURL1)) {
				parameterMap.put("wsrp-urlType", "render");
				parameterMap.put("wsrp-windowState", "wsrp:normal");

				String rewrittenURL = rewriteURL(
					portletRequest, portletResponse, parameterMap);

				String replacement = "location.href = '" + rewrittenURL + "'";

				rewriteMatcher.appendReplacement(sb, replacement);
			}
			else if (Validator.isNotNull(extensionURL2)) {
				parameterMap.put("wsrp-urlType", "render");
				parameterMap.put("wsrp-windowState", "wsrp:normal");

				String rewrittenURL = rewriteURL(
					portletRequest, portletResponse, parameterMap);

				String replacement = "href=\"" + rewrittenURL + "\"";

				rewriteMatcher.appendReplacement(sb, replacement);
			}
		}

		rewriteMatcher.appendTail(sb);

		return sb.toString();
	}

	protected void secureResourceURL(
			PortletRequest portletRequest, LiferayPortletURL liferayPortletURL,
			Map<String, String> parameterMap)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String resourceID = GetterUtil.getString(
			parameterMap.get("wsrp-resourceID"));

		String url = GetterUtil.getString(parameterMap.get("wsrp-url"));

		StringBundler sb = new StringBundler(4);

		sb.append(resourceID);
		sb.append(url);
		sb.append(PortletPropsValues.SECURE_RESOURCE_URLS_SALT);

		if (themeDisplay.isSignedIn()) {
			sb.append(AuthTokenUtil.getToken(request));
		}

		String wsrpAuth = encodeWSRPAuth(portletRequest, sb.toString());

		parameterMap.put("wsrp-auth", wsrpAuth);
	}

	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String redirectURL)
		throws Exception {

		redirectURL = rewriteURLs(actionRequest, actionResponse, redirectURL);

		actionResponse.sendRedirect(redirectURL);
	}

	protected void updateSessionContext(
		PortletSession portletSession, ServiceHolder serviceHolder,
		SessionContext sessionContext) {

		if (sessionContext == null) {
			return;
		}

		portletSession.setAttribute(WebKeys.SESSION_CONTEXT, sessionContext);

		serviceHolder.setSessionContext(sessionContext);
	}

	private static final String _BLOCKING_ACTION_TEMPLATE =
		"wsrp_rewrite?wsrp-urlType=blockingAction&" +
		"wsrp-navigationalState={wsrp-navigationalState}&" +
		"wsrp-navigationalValues={wsrp-navigationalValues}&" +
		"wsrp-interactionState={wsrp-interactionState}&" +
		"wsrp-mode={wsrp-mode}&wsrp-windowState={wsrp-windowState}" +
		"&wsrp-fragmentID={wsrp-fragmentID}/wsrp_rewrite";

	private static final String[] _CHAR_SETS = {StringPool.UTF8};

	private static final String[] _MIME_TYPES = {ContentTypes.TEXT_HTML};

	private static final String _RENDER_TEMPLATE =
		"wsrp_rewrite?wsrp-urlType=render&" +
		"wsrp-navigationalState={wsrp-navigationalState}&" +
		"wsrp-navigationalValues={wsrp-navigationalValues}&" +
		"wsrp-mode={wsrp-mode}&wsrp-windowState={wsrp-windowState}&" +
		"wsrp-fragmentID={wsrp-fragmentID}/wsrp_rewrite";

	private static final String _RESOURCE_TEMPLATE =
		"wsrp_rewrite?wsrp-urlType=resource&wsrp-url={wsrp-url}&" +
		"wsrp-resourceID={wsrp-resourceID}&" +
		"wsrp-preferOperation={wsrp-preferOperation}&" +
		"wsrp-resourceState={wsrp-resourceState}&" +
		"wsrp-requiresRewrite={wsrp-requiresRewrite}&" +
		"wsrp-resourceCacheability={wsrp-resourceCacheability}/wsrp_rewrite";

	private static Log _log = LogFactoryUtil.getLog(ConsumerPortlet.class);

	private static Pattern _navigationalValuesPattern = Pattern.compile(
		"(?:([^&=]+)(?:=([^&=]*))?)&?");
	private static Pattern _parameterPattern = Pattern.compile(
		"(?:([^&]+)=([^&]*))(?:&amp;|&)?");
	private static Pattern _rewritePattern = Pattern.compile(
		"(wsrp_rewrite_)|(?:wsrp_rewrite\\?([^\\s/]+)/wsrp_rewrite)|" +
			"(?:location\\.href\\s*=\\s*'(/widget/c/portal/layout(?:[^']+))')" +
				"|(?:href\\s*=\\s*\"(/widget/c/portal/layout(?:[^\"]+))\")");

}