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

package com.liferay.wsrp.bind;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.portletconfiguration.util.PortletConfigurationApplicationType;
import com.liferay.util.Encryptor;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.util.ExtensionHelperUtil;
import com.liferay.wsrp.util.WebKeys;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v2.types.ClientData;
import oasis.names.tc.wsrp.v2.types.Extension;
import oasis.names.tc.wsrp.v2.types.GetMarkup;
import oasis.names.tc.wsrp.v2.types.GetResource;
import oasis.names.tc.wsrp.v2.types.HandleEvents;
import oasis.names.tc.wsrp.v2.types.HandleEventsResponse;
import oasis.names.tc.wsrp.v2.types.InitCookie;
import oasis.names.tc.wsrp.v2.types.InteractionParams;
import oasis.names.tc.wsrp.v2.types.MarkupContext;
import oasis.names.tc.wsrp.v2.types.MarkupResponse;
import oasis.names.tc.wsrp.v2.types.MimeRequest;
import oasis.names.tc.wsrp.v2.types.NamedString;
import oasis.names.tc.wsrp.v2.types.NavigationalContext;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.PortletContext;
import oasis.names.tc.wsrp.v2.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.types.ResourceContext;
import oasis.names.tc.wsrp.v2.types.ResourceParams;
import oasis.names.tc.wsrp.v2.types.ResourceResponse;
import oasis.names.tc.wsrp.v2.types.RuntimeContext;
import oasis.names.tc.wsrp.v2.types.UpdateResponse;
import oasis.names.tc.wsrp.v2.types.UploadContext;

import org.apache.axis.message.MessageElement;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class V2MarkupServiceImpl
	extends BaseServiceImpl implements WSRP_v2_Markup_PortType {

	public MarkupResponse getMarkup(GetMarkup getMarkup)
		throws RemoteException {

		try {
			return doGetMarkup(getMarkup);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public ResourceResponse getResource(GetResource getResource)
		throws RemoteException {

		try {
			return doGetResource(getResource);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public HandleEventsResponse handleEvents(HandleEvents handleEvents)
		throws RemoteException {

		try {
			return doHandleEvents(handleEvents);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public Extension[] initCookie(InitCookie initCookie)
		throws RemoteException {

		try {
			return doInitCookie(initCookie);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public BlockingInteractionResponse performBlockingInteraction(
			PerformBlockingInteraction performBlockingInteraction)
		throws RemoteException {

		try {
			return doPerformBlockingInteraction(performBlockingInteraction);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public Extension[] releaseSessions(ReleaseSessions releaseSessions)
		throws RemoteException {

		try {
			return doReleaseSessions(releaseSessions);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	protected void addHeaders(
		MimeRequest mimeRequest, Http.Options httpOptions) {

		ClientData clientData = mimeRequest.getClientData();

		Extension[] extensions = clientData.getExtensions();

		MessageElement[] clientAttributes =
			ExtensionHelperUtil.getMessageElements(extensions);

		if (clientAttributes == null) {
			return;
		}

		for (MessageElement clientAttribute : clientAttributes) {
			String name = ExtensionHelperUtil.getNameAttribute(clientAttribute);
			String value = clientAttribute.getValue();

			if (StringUtil.equalsIgnoreCase(
					name, HttpHeaders.ACCEPT_ENCODING) ||
				StringUtil.equalsIgnoreCase(name, HttpHeaders.CONTENT_LENGTH) ||
				StringUtil.equalsIgnoreCase(name, HttpHeaders.CONTENT_TYPE) ||
				StringUtil.equalsIgnoreCase(name, HttpHeaders.COOKIE)) {

				continue;
			}

			httpOptions.addHeader(name, value);
		}
	}

	protected MarkupResponse doGetMarkup(GetMarkup getMarkup) throws Exception {
		WSRPProducer wsrpProducer = getWSRPProducer();

		Http.Options httpOptions = new Http.Options();

		addHeaders(getMarkup.getMarkupParams(), httpOptions);

		httpOptions.setLocation(getURL(getMarkup, wsrpProducer));

		String rawContent = getRawContent(httpOptions);

		String windowState = getWindowState(getMarkup.getMarkupParams());

		String content = getContent(rawContent, windowState);

		MarkupContext markupContext = new MarkupContext();

		markupContext.setItemString(content);
		markupContext.setMimeType(ContentTypes.TEXT_HTML_UTF8);
		markupContext.setRequiresRewriting(true);

		MarkupResponse markupResponse = new MarkupResponse();

		markupResponse.setMarkupContext(markupContext);

		return markupResponse;
	}

	protected ResourceResponse doGetResource(GetResource getResource)
		throws Exception {

		WSRPProducer wsrpProducer = getWSRPProducer();

		Http.Options httpOptions = new Http.Options();

		addHeaders(getResource.getResourceParams(), httpOptions);

		httpOptions.setLocation(getURL(getResource, wsrpProducer));

		ResourceParams resourceParams = getResource.getResourceParams();

		UploadContext[] uploadContexts = resourceParams.getUploadContexts();

		processUploadContexts(uploadContexts, httpOptions);

		NamedString[] formParameters = resourceParams.getFormParameters();

		if (formParameters != null) {
			NavigationalContext navigationalContext =
				resourceParams.getNavigationalContext();

			PortletContext portletContext = getResource.getPortletContext();

			String namespace = PortalUtil.getPortletNamespace(
				getPortletId(portletContext, navigationalContext));

			for (NamedString formParameter : formParameters) {
				httpOptions.addPart(
					namespace + formParameter.getName(),
					formParameter.getValue());
			}

			if (formParameters.length > 0) {
				httpOptions.setPost(true);
			}
		}

		httpOptions.setFollowRedirects(false);

		byte[] itemBinary = getBinaryContent(httpOptions);

		ResourceContext resourceContext = new ResourceContext();

		Http.Response response = httpOptions.getResponse();

		String contentType = response.getContentType();

		if (itemBinary != null) {
			if (Validator.isNotNull(contentType) &&
				StringUtil.toLowerCase(contentType).startsWith("text")) {

				String content = new String(itemBinary);

				resourceContext.setItemString(content);
				resourceContext.setRequiresRewriting(true);
			}
			else {
				resourceContext.setItemBinary(itemBinary);
			}
		}

		List<NamedString> clientAttributes = new ArrayList<>();

		String contentDisposition = response.getHeader(
			HttpHeaders.CONTENT_DISPOSITION);

		if (Validator.isNotNull(contentDisposition)) {
			NamedString clientAttribute = new NamedString();

			clientAttribute.setName(HttpHeaders.CONTENT_DISPOSITION);
			clientAttribute.setValue(contentDisposition);

			clientAttributes.add(clientAttribute);
		}

		if (Validator.isNotNull(contentType)) {
			resourceContext.setMimeType(contentType);

			NamedString clientAttribute = new NamedString();

			clientAttribute.setName(HttpHeaders.CONTENT_TYPE);
			clientAttribute.setValue(contentType);

			clientAttributes.add(clientAttribute);
		}

		int contentLength = response.getContentLength();

		if (contentLength >= 0) {
			NamedString clientAttribute = new NamedString();

			clientAttribute.setName(HttpHeaders.CONTENT_LENGTH);
			clientAttribute.setValue(Integer.toString(contentLength));

			clientAttributes.add(clientAttribute);
		}

		resourceContext.setClientAttributes(
			clientAttributes.toArray(new NamedString[clientAttributes.size()]));

		ResourceResponse resourceResponse = new ResourceResponse();

		resourceResponse.setResourceContext(resourceContext);

		return resourceResponse;
	}

	protected HandleEventsResponse doHandleEvents(HandleEvents handleEvents)
		throws Exception {

		HandleEventsResponse handleEventsResponse = new HandleEventsResponse();

		return handleEventsResponse;
	}

	protected Extension[] doInitCookie(InitCookie initCookie) throws Exception {
		ServletUtil.getSession();

		return null;
	}

	protected BlockingInteractionResponse doPerformBlockingInteraction(
			PerformBlockingInteraction performBlockingInteraction)
		throws Exception {

		WSRPProducer wsrpProducer = getWSRPProducer();

		Http.Options httpOptions = new Http.Options();

		addHeaders(performBlockingInteraction.getMarkupParams(), httpOptions);

		httpOptions.setLocation(
			getURL(performBlockingInteraction, wsrpProducer));

		RuntimeContext runtimeContext =
			performBlockingInteraction.getRuntimeContext();

		InteractionParams interactionParams =
			performBlockingInteraction.getInteractionParams();

		UploadContext[] uploadContexts = interactionParams.getUploadContexts();

		processUploadContexts(uploadContexts, httpOptions);

		NamedString[] formParameters = interactionParams.getFormParameters();

		if (formParameters != null) {
			for (NamedString formParameter : formParameters) {
				String name = StringUtil.replace(
					formParameter.getName(),
					runtimeContext.getNamespacePrefix(), StringPool.BLANK);

				httpOptions.addPart(name, formParameter.getValue());
			}
		}

		httpOptions.setFollowRedirects(false);
		httpOptions.setPost(true);

		String rawContent = getRawContent(httpOptions);

		Http.Response response = httpOptions.getResponse();

		String redirect = response.getRedirect();

		String widgetPath = getWidgetPath();

		BlockingInteractionResponse blockingInteractionResponse =
			new BlockingInteractionResponse();

		if (Validator.isNotNull(redirect) && !redirect.startsWith(widgetPath)) {
			int pos = redirect.indexOf("wsrp_rewrite?");

			if (pos >= 0) {
				redirect = redirect.substring(pos);
			}

			blockingInteractionResponse.setRedirectURL(redirect);
		}
		else {

			// Relative redirect

			if (Validator.isNotNull(redirect) &&
				redirect.startsWith(widgetPath)) {

				httpOptions.setLocation(redirect);

				rawContent = getRawContent(httpOptions);
			}

			String windowState = getWindowState(
				performBlockingInteraction.getMarkupParams());

			String content = getContent(rawContent, windowState);

			MarkupContext markupContext = new MarkupContext();

			markupContext.setItemString(content);
			markupContext.setMimeType(ContentTypes.TEXT_HTML_UTF8);
			markupContext.setRequiresRewriting(true);

			UpdateResponse updateResponse = new UpdateResponse();

			updateResponse.setMarkupContext(markupContext);

			blockingInteractionResponse.setUpdateResponse(updateResponse);
		}

		return blockingInteractionResponse;
	}

	protected Extension[] doReleaseSessions(ReleaseSessions releaseSessions)
		throws Exception {

		return null;
	}

	protected byte[] getBinaryContent(Http.Options httpOptions)
		throws Exception {

		HttpSession session = ServletUtil.getSession();

		Cookie[] cookies = (Cookie[])session.getAttribute(WebKeys.COOKIES);

		if (cookies != null) {
			httpOptions.setCookies(cookies);
		}

		byte[] binaryContent = HttpUtil.URLtoByteArray(httpOptions);

		cookies = HttpUtil.getCookies();

		if (cookies != null) {
			session.setAttribute(WebKeys.COOKIES, cookies);
		}

		return binaryContent;
	}

	protected String getContent(String rawContent, String windowState)
		throws Exception {

		if (windowState.equals(LiferayWindowState.EXCLUSIVE.toString())) {
			return rawContent;
		}

		String beginPortletContent = "<liferay-wsrp-portlet>";
		String endPortletContent = "</liferay-wsrp-portlet>";

		int x = rawContent.indexOf(beginPortletContent);

		if (x == -1) {
			throw new SystemException("Unable to find <liferay-wsrp-portlet>");
		}

		int offset = beginPortletContent.length();

		int y = rawContent.indexOf(endPortletContent, x);

		if (y == -1) {
			throw new SystemException("Unable to find </liferay-wsrp-portlet>");
		}

		return rawContent.substring(x + offset, y);
	}

	protected Layout getLayout(
			PortletContext portletContext, WSRPProducer wsrpProducer)
		throws Exception {

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			wsrpProducer.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, false, 0, 1);

		if (layouts.isEmpty()) {
			throw new NoSuchLayoutException();
		}

		Layout layout = layouts.get(0);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		String portletId = getPortletId(portletContext);

		if (!layoutTypePortlet.hasPortletId(portletId)) {
			layoutTypePortlet.addPortletId(0, portletId, "column-1", -1, false);

			LayoutLocalServiceUtil.updateLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), layout.getTypeSettings());

			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);
		}

		return layout;
	}

	protected String getPortletId(PortletContext portletContext)
		throws Exception {

		return portletContext.getPortletHandle();
	}

	protected String getPortletId(
			PortletContext portletContext,
			NavigationalContext navigationalContext)
		throws Exception {

		String portletId = getPortletId(portletContext);

		if (navigationalContext == null) {
			return portletId;
		}

		String opaqueValue = navigationalContext.getOpaqueValue();

		if (Validator.isNotNull(opaqueValue)) {
			opaqueValue = new String(
				Base64.decode(Base64.fromURLSafe(opaqueValue)),
				StringPool.UTF8);
		}

		Map<String, String[]> parameterMap = HttpUtil.parameterMapFromString(
			opaqueValue);

		String portletConfigurationPortletId = PortletProviderUtil.getPortletId(
			PortletConfigurationApplicationType.
				PortletConfiguration.CLASS_NAME,
			PortletProvider.Action.VIEW);

		if (parameterMap.containsKey(
				PortalUtil.getPortletNamespace(portletConfigurationPortletId) +
					"portletConfiguration")) {

			portletId = portletConfigurationPortletId;
		}

		return portletId;
	}

	protected String getPortletMode(MimeRequest mimeRequest) throws Exception {
		String portletMode = mimeRequest.getMode();

		return portletMode.substring(5);
	}

	protected String getRawContent(Http.Options httpOptions) throws Exception {
		Map<String, Cookie> cookiesMap = new HashMap<>();

		HttpSession session = ServletUtil.getSession();

		Cookie[] sessionCookies = (Cookie[])session.getAttribute(
			WebKeys.COOKIES);

		if (sessionCookies != null) {
			for (Cookie cookie : sessionCookies) {
				cookiesMap.put(cookie.getName(), cookie);
			}
		}

		HttpServletRequest request = ServletUtil.getRequest();

		Cookie[] forwardCookies = request.getCookies();

		if (forwardCookies != null) {
			for (Cookie cookie : forwardCookies) {
				String cookieName = cookie.getName();

				if (!StringUtil.equalsIgnoreCase(
						cookieName, "cookie_support") &&
					!StringUtil.equalsIgnoreCase(
						cookieName, "guest_language_id") &&
					!StringUtil.equalsIgnoreCase(cookieName, "jsessionid")) {

					if (Validator.isNull(cookie.getDomain())) {
						cookie.setDomain(request.getServerName());
					}

					if (Validator.isNull(cookie.getPath())) {
						cookie.setPath(StringPool.SLASH);
					}

					cookiesMap.put(cookieName, cookie);
				}
			}
		}

		if (!cookiesMap.isEmpty()) {
			Collection<Cookie> cookiesCollection = cookiesMap.values();

			httpOptions.setCookies(cookiesCollection.toArray(new Cookie[0]));
		}

		String rawContent = HttpUtil.URLtoString(httpOptions);

		Cookie[] cookies = HttpUtil.getCookies();

		if (cookies != null) {
			session.setAttribute(WebKeys.COOKIES, cookies);
		}

		return rawContent;
	}

	protected String getURL(GetMarkup getMarkup, WSRPProducer wsrpProducer)
		throws Exception {

		return getURL(
			"0", null, getMarkup.getMarkupParams(),
			getMarkup.getPortletContext(), wsrpProducer);
	}

	protected String getURL(GetResource getResource, WSRPProducer wsrpProducer)
		throws Exception {

		ResourceParams resourceParams = getResource.getResourceParams();

		return getURL(
			"2", resourceParams.getResourceID(), resourceParams,
			getResource.getPortletContext(), wsrpProducer);
	}

	protected String getURL(
			PerformBlockingInteraction performBlockingInteraction,
			WSRPProducer wsrpProducer)
		throws Exception {

		return getURL(
			"1", null, performBlockingInteraction.getMarkupParams(),
			performBlockingInteraction.getPortletContext(), wsrpProducer);
	}

	protected String getURL(
			String lifecycle, String resourceID, MimeRequest mimeRequest,
			PortletContext portletContext, WSRPProducer wsrpProducer)
		throws Exception {

		StringBundler sb = new StringBundler();

		String[] locales = mimeRequest.getLocales();

		if (locales.length > 0) {
			sb.append(getWidgetPath(locales[0]));
		}
		else {
			sb.append(getWidgetPath());
		}

		sb.append(StringPool.QUESTION);

		String propertiesAuthenticatonTokenSharedSecret = Encryptor.digest(
			PropsUtil.get(PropsKeys.AUTH_TOKEN_SHARED_SECRET));

		sb.append("p_auth_secret=");
		sb.append(HttpUtil.encodeURL(propertiesAuthenticatonTokenSharedSecret));

		Layout layout = getLayout(portletContext, wsrpProducer);

		sb.append("&p_l_id=");
		sb.append(layout.getPlid());

		NavigationalContext navigationalContext =
			mimeRequest.getNavigationalContext();

		String portletId = getPortletId(portletContext, navigationalContext);

		sb.append("&p_p_id=");
		sb.append(HttpUtil.encodeURL(portletId));

		sb.append("&p_p_lifecycle=");
		sb.append(lifecycle);

		String windowState = getWindowState(mimeRequest);

		sb.append("&p_p_state=");
		sb.append(HttpUtil.encodeURL(windowState));

		String portletMode = getPortletMode(mimeRequest);

		sb.append("&p_p_mode=");
		sb.append(HttpUtil.encodeURL(portletMode));

		if (lifecycle.equals("2") && Validator.isNotNull(resourceID)) {
			sb.append("&p_p_resource_id=");
			sb.append(resourceID);
		}

		sb.append("&p_p_isolated=1");

		String opaqueValue = null;

		if (navigationalContext != null) {
			opaqueValue = navigationalContext.getOpaqueValue();
		}

		if (Validator.isNotNull(opaqueValue)) {
			sb.append(StringPool.AMPERSAND);

			opaqueValue = new String(
				Base64.decode(Base64.fromURLSafe(opaqueValue)),
				StringPool.UTF8);

			sb.append(opaqueValue);
		}

		if (lifecycle.equals("0")) {
			MessageElement[] formParameters =
				ExtensionHelperUtil.getMessageElements(
					mimeRequest.getExtensions());

			if (formParameters != null) {
				String namespace = PortalUtil.getPortletNamespace(portletId);

				for (MessageElement formParameter : formParameters) {
					sb.append(StringPool.AMPERSAND);

					String name = namespace.concat(
						ExtensionHelperUtil.getNameAttribute(formParameter));

					sb.append(name);
					sb.append(StringPool.EQUAL);
					sb.append(HttpUtil.encodeURL(formParameter.getValue()));
				}
			}
		}

		if (windowState.equals(LiferayWindowState.EXCLUSIVE.toString())) {
			sb.append("&ensureContentLength=1");
		}

		sb.append("&wsrp=1");

		if (_log.isInfoEnabled()) {
			_log.info("URL " + sb.toString());
		}

		return sb.toString();
	}

	protected String getWidgetPath() {
		return getWidgetPath(null);
	}

	protected String getWidgetPath(String languageId) {
		HttpServletRequest request = ServletUtil.getRequest();

		String portalURL = PortalUtil.getPortalURL(request);

		StringBundler sb = new StringBundler(5);

		sb.append(portalURL);
		sb.append(PortalUtil.getPathContext());

		String[] localesEnabled = PropsUtil.getArray(PropsKeys.LOCALES_ENABLED);

		if (ArrayUtil.contains(localesEnabled, languageId)) {
			sb.append(StringPool.SLASH);
			sb.append(languageId);
		}

		sb.append(_PATH_WIDGET);

		return sb.toString();
	}

	protected String getWindowState(MimeRequest mimeRequest) throws Exception {
		String windowState = mimeRequest.getWindowState();

		return windowState.substring(5);
	}

	protected void processUploadContexts(
		UploadContext[] uploadContexts, Http.Options httpOptions) {

		if (uploadContexts == null) {
			return;
		}

		for (UploadContext uploadContext : uploadContexts) {
			NamedString mimeAttribute = uploadContext.getMimeAttributes(0);

			String[] mimeAttributeValues = StringUtil.split(
				mimeAttribute.getValue(), StringPool.SEMICOLON);

			String name = StringUtil.replace(
				mimeAttributeValues[1], "name=", StringPool.BLANK);

			name = StringUtil.trim(name);

			String fileName = StringUtil.replace(
				mimeAttributeValues[2], "filename=", StringPool.BLANK);

			fileName = StringUtil.trim(fileName);

			String contentType = uploadContext.getMimeType();

			String charSet = null;

			if (contentType.contains(StringPool.SEMICOLON)) {
				int pos = contentType.indexOf(StringPool.SEMICOLON);

				charSet = contentType.substring(pos + 1);
				charSet = StringUtil.trim(charSet);

				contentType = contentType.substring(0, pos);
			}

			httpOptions.addFilePart(
				name, fileName, uploadContext.getUploadData(), contentType,
				charSet);
		}
	}

	private static final String _PATH_WIDGET = "/widget/c/portal/layout";

	private static Log _log = LogFactoryUtil.getLog(V2MarkupServiceImpl.class);

}