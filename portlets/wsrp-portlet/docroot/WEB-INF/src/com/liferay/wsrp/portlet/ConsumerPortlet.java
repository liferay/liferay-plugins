/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.axis.SimpleHTTPSender;
import com.liferay.util.servlet.PortletResponseUtil;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.util.ExtensionUtil;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;
import com.liferay.wsrp.util.WebKeys;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
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

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v2.types.ClientData;
import oasis.names.tc.wsrp.v2.types.CookieProtocol;
import oasis.names.tc.wsrp.v2.types.Event;
import oasis.names.tc.wsrp.v2.types.EventParams;
import oasis.names.tc.wsrp.v2.types.EventPayload;
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
import oasis.names.tc.wsrp.v2.types.NamedString;
import oasis.names.tc.wsrp.v2.types.NavigationalContext;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.PortletContext;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.ResourceContext;
import oasis.names.tc.wsrp.v2.types.ResourceParams;
import oasis.names.tc.wsrp.v2.types.RuntimeContext;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;
import oasis.names.tc.wsrp.v2.types.SessionContext;
import oasis.names.tc.wsrp.v2.types.SessionParams;
import oasis.names.tc.wsrp.v2.types.StateChange;
import oasis.names.tc.wsrp.v2.types.Templates;
import oasis.names.tc.wsrp.v2.types.UpdateResponse;
import oasis.names.tc.wsrp.v2.types.UploadContext;
import oasis.names.tc.wsrp.v2.types.UserContext;

import org.apache.axis.message.MessageElement;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class ConsumerPortlet extends GenericPortlet {

	public static final String PORTLET_NAME_PREFIX = "WSRP_";

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

	public void processEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws PortletException, IOException {

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

	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		String userToken = WSRPConsumerManager.getUserToken(actionRequest);

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(
				wsrpConsumer, userToken);

		InteractionParams interactionParams = new InteractionParams();
		MarkupParams markupParams = new MarkupParams();
		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			actionRequest, actionResponse, wsrpConsumerPortlet,
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

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			actionRequest, wsrpConsumerManager, wsrpConsumer);

		BlockingInteractionResponse blockingInteractionResponse =
			markupService.performBlockingInteraction(
				performBlockingInteraction);

		processBlockingInteractionResponse(
			actionRequest, actionResponse, wsrpConsumerManager,
			blockingInteractionResponse);
	}

	protected void doProcessEvent(
			EventRequest eventRequest, EventResponse eventResponse)
		throws Exception {

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		String userToken = WSRPConsumerManager.getUserToken(eventRequest);

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(
				wsrpConsumer, userToken);

		EventParams eventParams = new EventParams();
		MarkupParams markupParams = new MarkupParams();
		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			eventRequest, eventResponse, wsrpConsumerPortlet,
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

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			eventRequest, wsrpConsumerManager, wsrpConsumer);

		HandleEventsResponse handleEventsResponse =
			markupService.handleEvents(handleEvents);

		processHandleEventsResponse(
			eventRequest, eventResponse, wsrpConsumerManager,
			handleEventsResponse);
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		PortletSession portletSession = renderRequest.getPortletSession();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

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

		renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

		String content = rewriteURLs(
			renderResponse, markupContext.getItemString());

		PortletResponseUtil.write(renderResponse, content);
	}

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

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

	protected MarkupResponse getMarkupResponse(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		String userToken = WSRPConsumerManager.getUserToken(portletRequest);

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(
				wsrpConsumer, userToken);

		MarkupParams markupParams = new MarkupParams();
		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			portletRequest, portletResponse, wsrpConsumerPortlet,
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

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			portletRequest, wsrpConsumerManager, wsrpConsumer);

		MarkupResponse markupResponse = null;

		try {
			markupResponse = markupService.getMarkup(getMarkup);
		}
		catch (InvalidCookieFault icf) {
			InitCookie initCookie = new InitCookie();

			markupService.initCookie(initCookie);

			markupResponse = markupService.getMarkup(getMarkup);
		}

		processMarkupResponse(portletRequest, portletResponse, markupResponse);

		return markupResponse;
	}

	protected WSRP_v2_Markup_PortType getMarkupService(
			PortletRequest portletRequest,
			WSRPConsumerManager wsrpConsumerManager, WSRPConsumer wsrpConsumer)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		String markupServiceKey = getSessionKey(
			WebKeys.MARKUP_SERVICE, portletRequest, wsrpConsumer);

		TransientValue<WSRP_v2_Markup_PortType> markupServiceTransientValue =
			(TransientValue<WSRP_v2_Markup_PortType>)
				portletSession.getAttribute(
					markupServiceKey, PortletSession.APPLICATION_SCOPE);

		if ((markupServiceTransientValue == null) ||
			(markupServiceTransientValue.isNull())) {

			String userToken = WSRPConsumerManager.getUserToken(portletRequest);

			WSRP_v2_Markup_PortType markupService =
				wsrpConsumerManager.getMarkupService(userToken);

			markupServiceTransientValue =
				new TransientValue<WSRP_v2_Markup_PortType>(markupService);

			ServiceDescription serviceDescription =
				wsrpConsumerManager.getServiceDescription();

			String cookieKey = getSessionKey(
				WebKeys.COOKIE, portletRequest, wsrpConsumer);

			String cookie = (String)portletSession.getAttribute(
				cookieKey, PortletSession.APPLICATION_SCOPE);

			CookieProtocol cookieProtocol =
				serviceDescription.getRequiresInitCookie();

			if ((cookie == null) &&
				(cookieProtocol != null)) {

				String cookieProtocolValue = cookieProtocol.getValue();

				if (cookieProtocolValue.equals(CookieProtocol._perGroup) ||
					cookieProtocolValue.equals(CookieProtocol._perUser)) {

					InitCookie initCookie = new InitCookie();

					initCookie.setRegistrationContext(
						wsrpConsumer.getRegistrationContext());

					markupService.initCookie(initCookie);

					cookie = SimpleHTTPSender.getCurrentCookie();

					portletSession.setAttribute(
						cookieKey, cookie, PortletSession.APPLICATION_SCOPE);
				}
			}

			portletSession.setAttribute(
				markupServiceKey, markupServiceTransientValue,
				PortletSession.APPLICATION_SCOPE);
		}

		return markupServiceTransientValue.getValue();
	}

	protected PortletMode getPortletMode(String portletMode) {
		return new PortletMode(portletMode.substring(5));
	}

	protected void getResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		PortletSession portletSession = resourceRequest.getPortletSession();

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		String userToken = WSRPConsumerManager.getUserToken(resourceRequest);

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(
				wsrpConsumer, userToken);

		PortletContext portletContext = new PortletContext();
		ResourceParams resourceParams = new ResourceParams();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();

		initContexts(
			resourceRequest, resourceResponse, wsrpConsumerPortlet,
			wsrpConsumerManager, portletContext, resourceParams, runtimeContext,
			userContext);

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

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			resourceRequest, wsrpConsumerManager, wsrpConsumer);

		oasis.names.tc.wsrp.v2.types.ResourceResponse wsrpResourceResponse =
			markupService.getResource(getResource);

		processResourceResponse(
			resourceRequest, resourceResponse, wsrpConsumerManager,
			wsrpResourceResponse);
	}

	protected String getSessionKey(
		String baseKey, PortletRequest portletRequest,
		WSRPConsumer wsrpConsumer) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return baseKey + StringPool.UNDERLINE +
			themeDisplay.getScopeGroupId() + StringPool.UNDERLINE +
			wsrpConsumer.getWsrpConsumerId();
	}

	protected WindowState getWindowState(String windowState) {
		return new WindowState(windowState.substring(5));
	}

	protected WSRPConsumerPortlet getWSRPConsumerPortlet() throws Exception {
		String portletName = getPortletConfig().getPortletName();

		int pos = portletName.indexOf(
			StringPool.UNDERLINE, PORTLET_NAME_PREFIX.length());

		long wsrpConsumerPortletId = GetterUtil.getLong(
			portletName.substring(pos + 1));

		WSRPConsumerPortlet wsrpConsumerPortlet =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(
				wsrpConsumerPortletId);

		return wsrpConsumerPortlet;
	}

	protected String getWSRPKey(String key) {
		return "wsrp:" + key;
	}

	protected String getWSRPMode(PortletMode portletMode) {
		return getWSRPKey(portletMode.toString());
	}

	protected String getWSRPWindowState(WindowState windowState) {
		return getWSRPKey(windowState.toString());
	}

	protected void initContexts(
			ActionRequest actionRequest, ActionResponse actionResponse,
			WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager,
			InteractionParams interactionParams, MarkupParams markupParams,
			PortletContext portletContext, RuntimeContext runtimeContext,
			UserContext userContext)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		initContexts(
			actionRequest, actionResponse, wsrpConsumerPortlet,
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
			WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager,
			EventParams eventParams, MarkupParams markupParams,
			PortletContext portletContext, RuntimeContext runtimeContext,
			UserContext userContext)
		throws Exception {

		initContexts(
			eventRequest, eventResponse, wsrpConsumerPortlet,
			wsrpConsumerManager, markupParams, portletContext, runtimeContext,
			userContext);

		eventParams.setPortletStateChange(StateChange.cloneBeforeWrite);

		javax.portlet.Event jxEvent = eventRequest.getEvent();

		KeyValuePair[] payloadKvps = (KeyValuePair[])jxEvent.getValue();

		NamedString[] namedStrings = new NamedString[payloadKvps.length];

		for (int i = 0; i < namedStrings.length; i++) {
			namedStrings[i] = new NamedString();

			namedStrings[i].setName(payloadKvps[i].getKey());
			namedStrings[i].setName(payloadKvps[i].getValue());
		}

		EventPayload eventPayload = new EventPayload();

		eventPayload.setNamedStringArray(namedStrings);

		Event event = new Event();

		event.setPayload(eventPayload);

		eventParams.setEvents(new Event[] {event});
	}

	protected void initContexts(
			PortletRequest portletRequest, PortletResponse portletResponse,
			WSRPConsumerPortlet wsrpConsumerPortlet,
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

		clientData.setRequestVerb(HttpMethods.GET);
		clientData.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

		User user = themeDisplay.getUser();

		List<MessageElement> clientAttributes = new ArrayList<MessageElement>();

		Enumeration<String> enu = request.getHeaderNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			String value = request.getHeader(name);

			ExtensionUtil.addMessageElement(clientAttributes, name, value);
		}

		ExtensionUtil.addMessageElement(
			clientAttributes, HttpHeaders.LIFERAY_EMAIL_ADDRESS,
			user.getEmailAddress());
		ExtensionUtil.addMessageElement(
			clientAttributes, HttpHeaders.LIFERAY_SCREEN_NAME,
			user.getScreenName());
		ExtensionUtil.addMessageElement(
			clientAttributes, HttpHeaders.LIFERAY_USER_ID,
			String.valueOf(user.getUserId()));

		clientData.setExtensions(ExtensionUtil.getExtensions(clientAttributes));

		mimeRequest.setClientData(clientData);

		List<Locale> locales = Collections.list(portletRequest.getLocales());

		String[] localesArray = new String[locales.size()];

		for (int i = 0; i < locales.size(); i++) {
			Locale locale = locales.get(i);

			localesArray[i] = locale.toString();
		}

		mimeRequest.setLocales(localesArray);

		mimeRequest.setMarkupCharacterSets(_CHAR_SETS);
		mimeRequest.setMimeTypes(_MIME_TYPES);
		mimeRequest.setMode(getWSRPMode(portletRequest.getPortletMode()));
		mimeRequest.setWindowState(
			getWSRPWindowState(portletRequest.getWindowState()));

		String[] modes = {
			getWSRPMode(PortletMode.EDIT), getWSRPMode(PortletMode.HELP),
			getWSRPMode(PortletMode.VIEW)};

		mimeRequest.setValidNewModes(modes);

		String[] windowStates = {
			getWSRPWindowState(WindowState.MAXIMIZED),
			getWSRPWindowState(WindowState.MINIMIZED),
			getWSRPWindowState(WindowState.NORMAL)};

		mimeRequest.setValidNewWindowStates(windowStates);

		// Navigational context

		NavigationalContext navigationalContext = new NavigationalContext();

		String navigationalState = portletRequest.getParameter(
			"wsrp-navigationalState");

		navigationalContext.setOpaqueValue(navigationalState);

		Map<String, String[]> publicParameterMap =
			portletRequest.getPublicParameterMap();

		List<NamedString> publicValues = new ArrayList<NamedString>();

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

		processFormParameters(portletRequest, portletResponse, mimeRequest);

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

		userContext.setUserCategories(new String[] {RoleConstants.USER});
		userContext.setUserContextKey(String.valueOf(themeDisplay.getUserId()));
	}

	protected void initContexts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager,
			PortletContext portletContext, ResourceParams resourceParams,
			RuntimeContext runtimeContext, UserContext userContext)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		initContexts(
			resourceRequest, resourceResponse, wsrpConsumerPortlet,
			wsrpConsumerManager, resourceParams, portletContext, runtimeContext,
			userContext);

		String resourceID = resourceRequest.getResourceID();

		resourceParams.setResourceID(resourceID);

		resourceParams.setPortletStateChange(StateChange.cloneBeforeWrite);

		String resourceState =
			ParamUtil.getString(resourceRequest, "wsrp-resourceState");

		if (Validator.isNotNull(resourceState)) {
			resourceParams.setResourceState(resourceState);
		}

		String resourceCacheability =
			ParamUtil.getString(resourceRequest, "wsrp-resourceCacheability");

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
			BlockingInteractionResponse blockingInteractionResponse)
		throws Exception {

		String redirectURL = blockingInteractionResponse.getRedirectURL();

		if (Validator.isNotNull(redirectURL)) {
			sendRedirect(actionResponse, redirectURL);

			return;
		}

		processUpdateResponse(
			actionRequest, actionResponse, wsrpConsumerManager,
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

		List<NamedString> formParameters = new ArrayList<NamedString>();

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

		List<MessageElement> formParameters = new ArrayList<MessageElement>();

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
				ExtensionUtil.addMessageElement(formParameters, name, value);
			}
		}

		if (!formParameters.isEmpty()) {
			mimeRequest.setExtensions(
				ExtensionUtil.getExtensions(formParameters));
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
			HandleEventsResponse handleEventsResponse)
		throws Exception {

		processUpdateResponse(
			eventRequest, eventResponse, wsrpConsumerManager,
			handleEventsResponse.getUpdateResponse());
	}

	protected void processMarkupResponse(
		PortletRequest portletRequest, PortletResponse portletResponse,
		MarkupResponse markupResponse) {

		PortletSession portletSession = portletRequest.getPortletSession();

		SessionContext sessionContext = markupResponse.getSessionContext();

		if (sessionContext != null) {
			portletSession.setAttribute(
				WebKeys.SESSION_CONTEXT, sessionContext);
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

		List<NamedString> formParameters = new ArrayList<NamedString>();
		List<UploadContext> uploadContexts = new ArrayList<UploadContext>();

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

				File file = uploadPortletRequest.getFile(name);

				byte[] bytes = FileUtil.getBytes(file);

				if (bytes == null) {
					continue;
				}

				uploadContext.setUploadData(bytes);

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

		if (sessionContext != null) {
			portletSession.setAttribute(
				WebKeys.SESSION_CONTEXT, sessionContext);
		}

		ResourceContext resourceContext =
			wsrpResourceResponse.getResourceContext();

		NamedString[] clientAttributes = resourceContext.getClientAttributes();

		if (clientAttributes != null) {
			for (NamedString clientAttribute : clientAttributes) {
				String name = clientAttribute.getName();
				String value = clientAttribute.getValue();

				if (name.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) {
					resourceResponse.setContentLength(Integer.parseInt(value));
				}
				else if (name.equalsIgnoreCase(HttpHeaders.CONTENT_TYPE)) {
					resourceResponse.setContentType(value);
				}
			}
		}

		String itemString = resourceContext.getItemString();
		byte[] itemBinary = resourceContext.getItemBinary();

		if (Validator.isNotNull(itemString)) {
			PortletResponseUtil.write(resourceResponse, itemString);
		}
		else if (itemBinary != null) {
			PortletResponseUtil.write(resourceResponse, itemBinary);
		}
	}

	protected void processUpdateResponse(
			PortletRequest portletRequest,
			StateAwareResponse stateAwareResponse,
			WSRPConsumerManager wsrpConsumerManager,
			UpdateResponse updateResponse)
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

			if (opaqueValue != null) {
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

		if (sessionContext != null) {
			portletSession.setAttribute(
				WebKeys.SESSION_CONTEXT, sessionContext);
		}

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

				EventPayload payload = event.getPayload();

				NamedString[] namedStrings =
					payload.getNamedStringArray();

				if (namedStrings == null) {
					continue;
				}

				KeyValuePair[] payloadKvps =
					new KeyValuePair[namedStrings.length];

				for (int i = 0; i < namedStrings.length; i++) {
					payloadKvps[i] = new KeyValuePair();

					payloadKvps[i].setKey(namedStrings[i].getName());
					payloadKvps[i].setValue(namedStrings[i].getValue());
				}

				stateAwareResponse.setEvent(qName, payloadKvps);

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
			Map<String, String> headers = new HashMap<String, String>();

			headers.put(HttpHeaders.COOKIE, cookie);

			options.setHeaders(headers);
		}

		byte[] bytes = HttpUtil.URLtoByteArray(options);

		Http.Response response = options.getResponse();

		int contentLength = response.getContentLength();

		if (contentLength >= 0) {
			resourceResponse.setContentLength(contentLength);
		}

		String contentType = response.getContentType();

		if (Validator.isNotNull(contentType)) {
			resourceResponse.setContentType(contentType);
		}

		PortletResponseUtil.write(resourceResponse, bytes);
	}

	protected String rewriteURL(
			PortletResponse portletResponse, Map<String, String> parameterMap)
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
		}

		boolean encodeURL = false;

		for (Map.Entry<String, String> parameter : parameterMap.entrySet()) {
			String name = parameter.getKey();
			String value = parameter.getValue();

			if (name.equals("wsrp-extensions") && value.equals("encodeURL")) {
				encodeURL = true;
			}
			else if (name.equals("wsrp-mode")) {
				try {
					liferayPortletURL.setPortletMode(getPortletMode(value));
				}
				catch (Exception e) {
					liferayPortletURL.setPortletMode(PortletMode.VIEW);
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

		if (encodeURL) {
			url = HttpUtil.encodeURL(url);
		}

		return url;
	}

	protected String rewriteURLs(
			PortletResponse portletResponse, String content)
		throws Exception {

		Matcher rewriteMatcher = _rewritePattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (rewriteMatcher.find()) {
			String namespace = rewriteMatcher.group(1);
			String url = rewriteMatcher.group(2);
			String extensionURL1 = rewriteMatcher.group(3);
			String extensionURL2 = rewriteMatcher.group(4);

			String replacement = null;

			Map<String, String> parameterMap =
				new HashMap<String, String>();

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

				rewriteMatcher.appendReplacement(
					sb, rewriteURL(portletResponse, parameterMap));
			}
			else if (Validator.isNotNull(extensionURL1)) {
				parameterMap.put("wsrp-urlType", "render");
				parameterMap.put("wsrp-windowState", "wsrp:normal");

				replacement =
					"location.href = '" +
						rewriteURL(portletResponse, parameterMap) + "'";

				rewriteMatcher.appendReplacement(sb, replacement);
			}
			else if (Validator.isNotNull(extensionURL2)) {
				parameterMap.put("wsrp-urlType", "render");
				parameterMap.put("wsrp-windowState", "wsrp:normal");

				replacement =
					"href=\"" + rewriteURL(portletResponse, parameterMap) +
						"\"";

				rewriteMatcher.appendReplacement(sb, replacement);
			}
		}

		rewriteMatcher.appendTail(sb);

		return sb.toString();
	}

	protected void sendRedirect(
			ActionResponse actionResponse, String redirectURL)
		throws Exception {

		redirectURL = rewriteURLs(actionResponse, redirectURL);

		actionResponse.sendRedirect(redirectURL);
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

	private static Pattern _navigationalValuesPattern = Pattern.compile(
		"(?:([^&=]+)(?:=([^&=]*))?)&?");
	private static Pattern _parameterPattern = Pattern.compile(
		"(?:([^&]+)=([^&]*))(?:&amp;|&)?");
	private static Pattern _rewritePattern = Pattern.compile(
		"(wsrp_rewrite_)|(?:wsrp_rewrite\\?([^\\s/]+)/wsrp_rewrite)|" +
		"(?:location\\.href\\s*=\\s*'(/widget/c/portal/layout(?:[^']+))')|" +
		"(?:href\\s*=\\s*\"(/widget/c/portal/layout(?:[^\"]+))\")");

}