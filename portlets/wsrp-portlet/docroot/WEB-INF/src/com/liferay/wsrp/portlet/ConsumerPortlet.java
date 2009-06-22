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

package com.liferay.wsrp.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.axis.SimpleHTTPSender;
import com.liferay.util.servlet.PortletResponseUtil;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

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
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v2.types.ClientData;
import oasis.names.tc.wsrp.v2.types.CookieProtocol;
import oasis.names.tc.wsrp.v2.types.GetMarkup;
import oasis.names.tc.wsrp.v2.types.InitCookie;
import oasis.names.tc.wsrp.v2.types.InteractionParams;
import oasis.names.tc.wsrp.v2.types.MarkupParams;
import oasis.names.tc.wsrp.v2.types.MarkupResponse;
import oasis.names.tc.wsrp.v2.types.MarkupType;
import oasis.names.tc.wsrp.v2.types.NamedString;
import oasis.names.tc.wsrp.v2.types.NavigationalContext;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.PortletContext;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.RuntimeContext;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;
import oasis.names.tc.wsrp.v2.types.SessionContext;
import oasis.names.tc.wsrp.v2.types.SessionParams;
import oasis.names.tc.wsrp.v2.types.StateChange;
import oasis.names.tc.wsrp.v2.types.UpdateResponse;
import oasis.names.tc.wsrp.v2.types.UserContext;

/**
 * <a href="ConsumerPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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
			String url = resourceRequest.getParameter("wsrp-url");

			proxyURL(resourceRequest, resourceResponse, new URL(url));
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

	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumerManager wsrpConsumerManager = getWSRPConsumerManager(
			wsrpConsumerPortlet);

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
		performBlockingInteraction.setRuntimeContext(runtimeContext);
		performBlockingInteraction.setUserContext(userContext);

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			actionRequest, wsrpConsumerManager);

		BlockingInteractionResponse blockingInteractionResponse =
			markupService.performBlockingInteraction(
				performBlockingInteraction);

		blockingInteractionResponse.getUpdateResponse();

		processBlockingInteractionResponse(
			actionRequest, actionResponse, blockingInteractionResponse);
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		MarkupResponse markupResponse = getMarkupResponse(
			renderRequest, renderResponse);

		String content = markupResponse.getMarkupContext().getItemString();

		Matcher urlMatcher = _urlPattern.matcher(content);

		StringBuffer sb = new StringBuffer();

		while (urlMatcher.find()) {
			String url = urlMatcher.group(1);

			Map<String, String> parameterMap = new HashMap<String, String>();

			Matcher parameterMatcher = _urlParametersPattern.matcher(url);

			while (parameterMatcher.find()) {
				String name = parameterMatcher.group(1);
				String value = parameterMatcher.group(2);

				parameterMap.put(name, HttpUtil.decodeURL(value));
			}

			urlMatcher.appendReplacement(
				sb, rewriteURL(renderResponse, parameterMap));
		}

		urlMatcher.appendTail(sb);

		renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

		PortletResponseUtil.write(renderResponse, sb.toString());
	}

	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String url = resourceRequest.getParameter("wsrp-url");

		proxyURL(resourceRequest, resourceResponse, new URL(url));
	}

	protected MarkupResponse getMarkupResponse(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		WSRPConsumerPortlet wsrpConsumerPortlet = getWSRPConsumerPortlet();

		WSRPConsumerManager wsrpConsumerManager = getWSRPConsumerManager(
			wsrpConsumerPortlet);

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
			(PortletContext)portletSession.getAttribute(_PORTLET_CONTEXT);

		if (existingPortletContext != null) {
			getMarkup.setPortletContext(existingPortletContext);
		}
		else {
			getMarkup.setPortletContext(portletContext);
		}

		getMarkup.setRuntimeContext(runtimeContext);
		getMarkup.setUserContext(userContext);

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			portletRequest, wsrpConsumerManager);

		MarkupResponse markupResponse = markupService.getMarkup(getMarkup);

		processMarkupResponse(portletRequest, portletResponse, markupResponse);

		return markupResponse;
	}

	protected WSRP_v2_Markup_PortType getMarkupService(
			PortletRequest portletRequest,
			WSRPConsumerManager wsrpConsumerManager)
		throws Exception {

		PortletSession portletSession = portletRequest.getPortletSession();

		WSRP_v2_Markup_PortType markupService =
			(WSRP_v2_Markup_PortType)portletSession.getAttribute(
				_MARKUP_SERVICE);

		if (markupService == null) {
			markupService = wsrpConsumerManager.getMarkupService();

			ServiceDescription serviceDescription =
				wsrpConsumerManager.getServiceDescription();

			String cookie = (String)portletSession.getAttribute(_COOKIE);

			if (cookie == null) {
				CookieProtocol cookieProtocol =
					serviceDescription.getRequiresInitCookie();

				String cookieProtocolValue = cookieProtocol.getValue();

				if (cookieProtocolValue.equals(CookieProtocol._perGroup) ||
					cookieProtocolValue.equals(CookieProtocol._perUser)) {

					InitCookie initCookie = new InitCookie();

					markupService.initCookie(initCookie);

					cookie = SimpleHTTPSender.getCurrentCookie();

					portletSession.setAttribute(_COOKIE, cookie);
				}
			}

			portletSession.setAttribute(_MARKUP_SERVICE, markupService);
		}

		return markupService;
	}

	protected PortletMode getPortletMode(String portletMode) {
		return new PortletMode(portletMode.substring(_WSRP_PREFIX.length()));
	}

	protected WindowState getWindowState(String windowState) {
		return new WindowState(windowState.substring(_WSRP_PREFIX.length()));
	}

	protected WSRPConsumerManager getWSRPConsumerManager(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws Exception {

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		return wsrpConsumerManager;
	}

	protected WSRPConsumerPortlet getWSRPConsumerPortlet() throws Exception {
		String portletName = getPortletConfig().getPortletName();

		long wsrpConsumerPortletId = GetterUtil.getLong(
			portletName.substring(PORTLET_NAME_PREFIX.length()));

		WSRPConsumerPortlet wsrpConsumerPortlet =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(
				wsrpConsumerPortletId);

		return wsrpConsumerPortlet;
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

			//processUpload(request, interactionParams);
		}
		else {
			processFormParameters(
				actionRequest, actionResponse, interactionParams);
		}
	}

	protected void initContexts(
			PortletRequest portletRequest, PortletResponse portletResponse,
			WSRPConsumerPortlet wsrpConsumerPortlet,
			WSRPConsumerManager wsrpConsumerManager, MarkupParams markupParams,
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

		markupParams.setClientData(clientData);

		List<Locale> locales = Collections.list(portletRequest.getLocales());

		String[] localesArray = new String[locales.size()];

		for (int i = 0; i < locales.size(); i++) {
			Locale locale = locales.get(i);

			localesArray[i] = locale.toString();
		}

		markupParams.setLocales(localesArray);

		markupParams.setMarkupCharacterSets(_CHAR_SETS);
		markupParams.setMimeTypes(_MIME_TYPES);
		markupParams.setMode("wsrp:" + portletRequest.getPortletMode());
		markupParams.setWindowState("wsrp:" + portletRequest.getWindowState());

		PortletDescription portletDescription =
			wsrpConsumerManager.getPortletDescription(
				wsrpConsumerPortlet.getPortletHandle());

		MarkupType[] markupTypes = portletDescription.getMarkupTypes();

		for (MarkupType markupType : markupTypes) {
			if (markupType.getMimeType().equalsIgnoreCase(
					ContentTypes.TEXT_HTML)) {

				markupParams.setValidNewModes(markupType.getModes());
				markupParams.setValidNewWindowStates(
					markupType.getWindowStates());
			}
		}

		// Navigational context

		NavigationalContext navigationalContext =
			(NavigationalContext)portletSession.getAttribute(
				_NAVIGATIONAL_CONTEXT);

		if (navigationalContext == null) {
			navigationalContext = new NavigationalContext();

			String navigationalState = portletRequest.getParameter(
				"wsrp-navigationalState");

			navigationalContext.setOpaqueValue(navigationalState);

			//String navigationalValues = renderRequest.getParameter(
			//	"wsrp-navigationalValues");

			//navigationalContext.setPublicValues(publicValues);
		}

		markupParams.setNavigationalContext(navigationalContext);

		// Portlet context

		portletContext.setPortletHandle(wsrpConsumerPortlet.getPortletHandle());

		// Runtime context

		runtimeContext.setNamespacePrefix(portletResponse.getNamespace());
		runtimeContext.setPortletInstanceKey(portletResponse.getNamespace());

		SessionContext sessionContext =
			(SessionContext)portletSession.getAttribute(_SESSION_CONTEXT);

		if (sessionContext != null) {
			SessionParams sessionParams = new SessionParams();

			sessionParams.setSessionID(sessionContext.getSessionID());

			runtimeContext.setSessionParams(sessionParams);
		}

		runtimeContext.setUserAuthentication("wsrp:password");

		// User context

		userContext.setUserCategories(new String[] {RoleConstants.USER});
		userContext.setUserContextKey(String.valueOf(themeDisplay.getUserId()));
	}

	protected boolean isReservedParameter(String name) {
		if (name.startsWith(_WSRP_PREFIX)) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void processBlockingInteractionResponse(
			ActionRequest actionRequest, ActionResponse actionResponse,
			BlockingInteractionResponse blockingInteractionResponse)
		throws Exception {

		PortletSession portletSession = actionRequest.getPortletSession();

		UpdateResponse updateResponse =
			blockingInteractionResponse.getUpdateResponse();

		String redirectURL = blockingInteractionResponse.getRedirectURL();

		if (Validator.isNotNull(redirectURL)) {
			actionResponse.sendRedirect(redirectURL);

			return;
		}

		portletSession.setAttribute(
			_MARKUP_CONTEXT, updateResponse.getMarkupContext());
		portletSession.setAttribute(
			_NAVIGATIONAL_CONTEXT, updateResponse.getNavigationalContext());
		portletSession.setAttribute(
			_PORTLET_CONTEXT, updateResponse.getPortletContext());

		String portletMode = updateResponse.getNewMode();

		if (Validator.isNotNull(portletMode)) {
			actionResponse.setPortletMode(getPortletMode(portletMode));
		}

		String windowState = updateResponse.getNewWindowState();

		if (Validator.isNotNull(windowState)) {
			actionResponse.setWindowState(getWindowState(windowState));
		}

		portletSession.setAttribute(
			_SESSION_CONTEXT, updateResponse.getSessionContext());
	}

	protected void processFormParameters(
		ActionRequest actionRequest, ActionResponse actionResponse,
		InteractionParams interactionParams) {

		Enumeration<String> enu = actionRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (isReservedParameter(name)) {
				continue;
			}

			String[] values = actionRequest.getParameterValues(name);

			if (values == null) {
				continue;
			}

			List<NamedString> formParameters = new ArrayList<NamedString>();

			for (String value : values) {
				NamedString formParameter = new NamedString();

				formParameter.setName(name);
				formParameter.setValue(value);

				formParameters.add(formParameter);
			}

			if (!formParameters.isEmpty()) {
				interactionParams.setFormParameters(
					formParameters.toArray(
						new NamedString[formParameters.size()]));
			}
		}
	}

	protected void processMarkupResponse(
		PortletRequest portletRequest, PortletResponse portletResponse,
		MarkupResponse markupResponse) {

		PortletSession portletSession = portletRequest.getPortletSession();

		portletSession.setAttribute(
			_SESSION_CONTEXT, markupResponse.getSessionContext());

		portletSession.removeAttribute(_MARKUP_CONTEXT);
		portletSession.removeAttribute(_MARKUP_SERVICE);
		portletSession.removeAttribute(_NAVIGATIONAL_CONTEXT);
		portletSession.removeAttribute(_PORTLET_CONTEXT);
	}

	protected void proxyURL(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			URL url)
		throws Exception {

		PortletSession portletSession = resourceRequest.getPortletSession();

		String cookie = (String)portletSession.getAttribute(_COOKIE);

		URLConnection urlConnection = url.openConnection();

		urlConnection.setRequestProperty("Cookie", cookie);

		urlConnection.connect();

		resourceResponse.setContentLength(urlConnection.getContentLength());
		resourceResponse.setContentType(urlConnection.getContentType());

		PortletResponseUtil.write(
			resourceResponse, urlConnection.getInputStream());
	}

	protected String rewriteURL(
			RenderResponse renderResponse, Map<String, String> parameterMap)
		throws Exception {

		String lifecycle = parameterMap.get("wsrp-urlType");

		LiferayPortletURL portletURL = null;

		if (lifecycle.equals("blockingAction")) {
			portletURL = (LiferayPortletURL)renderResponse.createActionURL();
		}
		else if (lifecycle.equals("render")) {
			portletURL = (LiferayPortletURL)renderResponse.createRenderURL();
		}
		else if (lifecycle.equals("resource")) {
			portletURL = (LiferayPortletURL)renderResponse.createResourceURL();
		}

		for (Map.Entry<String, String> parameter : parameterMap.entrySet()) {
			String name = parameter.getKey();
			String value = parameter.getValue();

			if (name.equals("wsrp-mode")) {
				portletURL.setPortletMode(getPortletMode(value));
			}
			else if (name.equals("wsrp-resourceID")) {
				portletURL.setResourceID(value);
			}
			else if (name.equals("wsrp-urlType")) {
			}
			else if (name.equals("wsrp-windowState")) {
				portletURL.setWindowState(getWindowState(value));
			}
			else {
				portletURL.setParameter(name, value);
			}
		}

		return portletURL.toString();
	}

	private static final String[] _CHAR_SETS = {StringPool.UTF8};

	private static final String _COOKIE = "COOKIE";

	private static final String _MARKUP_CONTEXT = "MARKUP_CONTEXT";

	private static final String _MARKUP_SERVICE = "MARKUP_SERVICE";

	private static final String[] _MIME_TYPES = {ContentTypes.TEXT_HTML};

	private static final String _NAVIGATIONAL_CONTEXT = "MARKUP_SERVICE";

	private static final String _PORTLET_CONTEXT = "PORTLET_CONTEXT";

	private static final String _SESSION_CONTEXT = "SESSION_CONTEXT";

	private static final String _WSRP_PREFIX = "wsrp-";

	private static Pattern _urlParametersPattern = Pattern.compile(
		"(?:([^&]+)=([^&]+))(?:(?:&amp;|&))?");
	private static Pattern _urlPattern = Pattern.compile(
		"wsrp_rewrite\\?(.*)/wsrp_rewrite");

}