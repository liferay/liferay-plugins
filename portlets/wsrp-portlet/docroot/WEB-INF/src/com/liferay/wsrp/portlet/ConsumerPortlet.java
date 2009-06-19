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
import com.liferay.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import com.liferay.wsrp.v2.types.ClientData;
import com.liferay.wsrp.v2.types.CookieProtocol;
import com.liferay.wsrp.v2.types.GetMarkup;
import com.liferay.wsrp.v2.types.InitCookie;
import com.liferay.wsrp.v2.types.MarkupParams;
import com.liferay.wsrp.v2.types.MarkupResponse;
import com.liferay.wsrp.v2.types.MarkupType;
import com.liferay.wsrp.v2.types.NavigationalContext;
import com.liferay.wsrp.v2.types.PortletContext;
import com.liferay.wsrp.v2.types.PortletDescription;
import com.liferay.wsrp.v2.types.RuntimeContext;
import com.liferay.wsrp.v2.types.ServiceDescription;
import com.liferay.wsrp.v2.types.SessionContext;
import com.liferay.wsrp.v2.types.SessionParams;
import com.liferay.wsrp.v2.types.UserContext;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import java.net.URLConnection;

import java.util.Collections;
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
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="ConsumerPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ConsumerPortlet extends GenericPortlet {

	public static final String PORTLET_NAME_PREFIX = "WSRP_";

	public void processAction(
		ActionRequest actionRequest, ActionResponse actionResponse) {
	}

	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			renderResponse.setContentType(ContentTypes.TEXT_HTML_UTF8);

			PrintWriter printWriter = renderResponse.getWriter();

			String content = getContent(renderRequest, renderResponse);

			printWriter.print(content);

			printWriter.close();
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

	protected String getContent(
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

		return sb.toString();
	}

	protected MarkupResponse getMarkupResponse(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		PortletSession portletSession = renderRequest.getPortletSession();

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			renderRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		// WSRP consumer portlet

		String portletName = getPortletConfig().getPortletName();

		long wsrpConsumerPortletId = GetterUtil.getLong(
			portletName.substring(PORTLET_NAME_PREFIX.length()));

		WSRPConsumerPortlet wsrpConsumerPortlet =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(
				wsrpConsumerPortletId);

		// WSRP consumer

		WSRPConsumer wsrpConsumer =
			WSRPConsumerLocalServiceUtil.getWSRPConsumer(
				wsrpConsumerPortlet.getWsrpConsumerId());

		// WSRP consumer manager

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		// Portlet description

		PortletDescription portletDescription =
			wsrpConsumerManager.getPortletDescription(
				wsrpConsumerPortlet.getPortletHandle());

		// Get markup

		GetMarkup getMarkup = new GetMarkup();

		// Markup parameters

		MarkupParams markupParams = new MarkupParams();

		ClientData clientData = new ClientData();

		clientData.setRequestVerb(HttpMethods.GET);
		clientData.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

		markupParams.setClientData(clientData);

		List<Locale> locales = Collections.list(renderRequest.getLocales());

		String[] localesArray = new String[locales.size()];

		for (int i = 0; i < locales.size(); i++) {
			Locale locale = locales.get(i);

			localesArray[i] = locale.toString();
		}

		markupParams.setLocales(localesArray);

		markupParams.setMarkupCharacterSets(new String[] {StringPool.UTF8});
		markupParams.setMimeTypes(new String[] {ContentTypes.TEXT_HTML});
		markupParams.setMode("wsrp:" + renderRequest.getPortletMode());
		markupParams.setWindowState("wsrp:" + renderRequest.getWindowState());

		MarkupType[] markupTypes = portletDescription.getMarkupTypes();

		for (MarkupType markupType : markupTypes) {
			if (markupType.getMimeType().equalsIgnoreCase(
					ContentTypes.TEXT_HTML)) {

				markupParams.setValidNewModes(markupType.getModes());
				markupParams.setValidNewWindowStates(
					markupType.getWindowStates());
			}
		}

		getMarkup.setMarkupParams(markupParams);

		// Navigational context

		NavigationalContext navigationalContext = new NavigationalContext();

		String navigationalState = renderRequest.getParameter(
			"wsrp-navigationalState");

		navigationalContext.setOpaqueValue(navigationalState);

		//String navigationalValues = renderRequest.getParameter(
		//	"wsrp-navigationalValues");

		//navigationalContext.setPublicValues(publicValues);

		markupParams.setNavigationalContext(navigationalContext);

		// Portlet context

		PortletContext portletContext = new PortletContext();

		portletContext.setPortletHandle(wsrpConsumerPortlet.getPortletHandle());

		getMarkup.setPortletContext(portletContext);

		// Runtime context

		RuntimeContext runtimeContext = new RuntimeContext();

		runtimeContext.setNamespacePrefix(renderResponse.getNamespace());
		runtimeContext.setPortletInstanceKey(renderResponse.getNamespace());

		SessionContext sessionContext =
			(SessionContext)portletSession.getAttribute(_SESSION_CONTEXT);

		if (sessionContext != null) {
			SessionParams sessionParams = new SessionParams();

			sessionParams.setSessionID(sessionContext.getSessionID());

			runtimeContext.setSessionParams(sessionParams);
		}

		runtimeContext.setUserAuthentication("wsrp:password");

		getMarkup.setRuntimeContext(runtimeContext);

		// User context

		UserContext userContext = new UserContext();

		userContext.setUserCategories(new String[] {RoleConstants.USER});
		userContext.setUserContextKey(String.valueOf(themeDisplay.getUserId()));

		getMarkup.setUserContext(userContext);

		// Markup service

		WSRP_v2_Markup_PortType markupService = getMarkupService(
			renderRequest, wsrpConsumerManager);

		// Markup response

		MarkupResponse markupResponse = markupService.getMarkup(getMarkup);

		sessionContext = markupResponse.getSessionContext();

		if (sessionContext != null) {
			portletSession.setAttribute(_SESSION_CONTEXT, sessionContext);
		}

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
				portletURL.setPortletMode(new PortletMode(value.substring(5)));
			}
			else if (name.equals("wsrp-resourceID")) {
				portletURL.setResourceID(value);
			}
			else if (name.equals("wsrp-urlType")) {
			}
			else if (name.equals("wsrp-windowState")) {
				portletURL.setWindowState(new WindowState(value.substring(5)));
			}
			else {
				portletURL.setParameter(name, value);
			}
		}

		return portletURL.toString();
	}

	private static final String _COOKIE = "COOKIE";

	private static final String _MARKUP_SERVICE = "MARKUP_SERVICE";

	private static final String _SESSION_CONTEXT = "SESSION_CONTEXT";

	private static Pattern _urlParametersPattern = Pattern.compile(
		"(?:([^&]+)=([^&]+))(?:(?:&amp;|&))?");
	private static Pattern _urlPattern = Pattern.compile(
		"wsrp_rewrite\\?(.*)/wsrp_rewrite");

}