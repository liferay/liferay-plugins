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

package com.liferay.wsrp.bind;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.util.WebKeys;

import java.rmi.RemoteException;

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
import oasis.names.tc.wsrp.v2.types.MarkupParams;
import oasis.names.tc.wsrp.v2.types.MarkupResponse;
import oasis.names.tc.wsrp.v2.types.NamedString;
import oasis.names.tc.wsrp.v2.types.NavigationalContext;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.PortletContext;
import oasis.names.tc.wsrp.v2.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.types.ResourceResponse;
import oasis.names.tc.wsrp.v2.types.UpdateResponse;

/**
 * <a href="MarkupServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MarkupServiceImpl
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
		MarkupParams markupParams, Http.Options httpOptions) {

		ClientData clientData = markupParams.getClientData();

		NamedString[] clientAttributes = clientData.getClientAttributes();

		for (NamedString clientAttribute : clientAttributes) {
			String name = clientAttribute.getName();
			String value = clientAttribute.getValue();

			if (name.equalsIgnoreCase(HttpHeaders.ACCEPT_ENCODING) ||
				name.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH) ||
				name.equalsIgnoreCase(HttpHeaders.COOKIE)) {

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

		String content = getContent(httpOptions);

		MarkupContext markupContext = new MarkupContext();

		markupContext.setItemString(content);
		markupContext.setRequiresRewriting(true);

		MarkupResponse markupResponse = new MarkupResponse();

		markupResponse.setMarkupContext(markupContext);

		return markupResponse;
	}

	protected ResourceResponse doGetResource(GetResource getResource)
		throws Exception {

		ResourceResponse resourceResponse = new ResourceResponse();

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

		PortletContext portletContext =
			performBlockingInteraction.getPortletContext();

		InteractionParams interactionParams =
			performBlockingInteraction.getInteractionParams();

		NamedString[] formParameters  = interactionParams.getFormParameters();

		for (NamedString formParameter : formParameters) {
			String name =
				PortalUtil.getPortletNamespace(getPortletId(portletContext)) +
					formParameter.getName();

			httpOptions.addPart(name, formParameter.getValue());
		}

		httpOptions.setPost(true);

		String content = getContent(httpOptions);

		MarkupContext markupContext = new MarkupContext();

		markupContext.setItemString(content);
		markupContext.setRequiresRewriting(true);

		BlockingInteractionResponse blockingInteractionResponse =
			new BlockingInteractionResponse();

		UpdateResponse updateResponse = new UpdateResponse();

		updateResponse.setMarkupContext(markupContext);

		blockingInteractionResponse.setUpdateResponse(updateResponse);

		return blockingInteractionResponse;
	}

	protected Extension[] doReleaseSessions(ReleaseSessions releaseSessions)
		throws Exception {

		return null;
	}

	protected String getContent(Http.Options httpOptions)
		throws Exception {

		HttpSession session = ServletUtil.getSession();

		Cookie[] cookies = (Cookie[])session.getAttribute(WebKeys.COOKIES);

		if (cookies != null) {
			httpOptions.setCookies(cookies);
		}

		String content = HttpUtil.URLtoString(httpOptions);

		cookies = HttpUtil.getCookies();

		if (cookies != null) {
			session.setAttribute(WebKeys.COOKIES, cookies);
		}

		int x = content.indexOf("portlet-content-container");

		if (x == -1) {
			return content;
		}

		x = content.indexOf("<div>", x);

		if (x == -1) {
			return content;
		}

		int y = content.indexOf("</div></div></div></div>", x);

		if (y == -1) {
			return content;
		}

		return content.substring(x + 5, y);
	}

	protected Layout getLayout(
			PortletContext portletContext, WSRPProducer wsrpProducer)
		throws Exception {

		Group group = GroupLocalServiceUtil.getGroup(
			wsrpProducer.getCompanyId(), GroupConstants.GUEST);

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			group.getGroupId(), false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			0, 1);

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
		}

		return layout;
	}

	protected String getPortletId(PortletContext portletContext)
		throws Exception {

		return portletContext.getPortletHandle();
	}

	protected String getPortletMode(MarkupParams markupParams)
		throws Exception {

		String portletMode = markupParams.getMode();

		return portletMode.substring(5);
	}

	protected String getURL(GetMarkup getMarkup, WSRPProducer wsrpProducer)
		throws Exception {

		return getURL(
			"0", getMarkup.getMarkupParams(), getMarkup.getPortletContext(),
			wsrpProducer);
	}

	protected String getURL(
			PerformBlockingInteraction performBlockingInteraction,
			WSRPProducer wsrpProducer)
		throws Exception {

		return getURL(
			"1", performBlockingInteraction.getMarkupParams(),
			performBlockingInteraction.getPortletContext(), wsrpProducer);
	}

	protected String getURL(
			String lifecycle, MarkupParams markupParams,
			PortletContext portletContext, WSRPProducer wsrpProducer)
		throws Exception {

		HttpServletRequest request = ServletUtil.getRequest();

		String portalURL = PortalUtil.getPortalURL(request);

		StringBuilder sb = new StringBuilder();

		sb.append(portalURL);
		sb.append(PortalUtil.getPathContext());
		sb.append(_PATH_WIDGET);

		Layout layout = getLayout(portletContext, wsrpProducer);

		sb.append("p_l_id=");
		sb.append(layout.getPlid());

		String portletId = getPortletId(portletContext);

		NavigationalContext navigationalContext =
			markupParams.getNavigationalContext();

		String opaqueValue = null;

		if (navigationalContext != null) {
			opaqueValue = navigationalContext.getOpaqueValue();

			Map<String, String[]> parameterMap =
				HttpUtil.parameterMapFromString(opaqueValue);

			if (parameterMap.containsKey(
					_STRUTS_ACTION_PORTLET_CONFIGURATION)) {

				portletId = PortletKeys.PORTLET_CONFIGURATION;
			}
		}

		sb.append("&p_p_id=");
		sb.append(HttpUtil.encodeURL(portletId));

		sb.append("&p_p_lifecycle=");
		sb.append(lifecycle);

		String windowState = getWindowState(markupParams);

		sb.append("&p_p_state=");
		sb.append(HttpUtil.encodeURL(windowState));

		String portletMode = getPortletMode(markupParams);

		sb.append("&p_p_mode=");
		sb.append(HttpUtil.encodeURL(portletMode));

		sb.append("&wsrp=1");

		if (Validator.isNotNull(opaqueValue)) {
			sb.append(StringPool.AMPERSAND);
			sb.append(opaqueValue);
		}

		if (_log.isInfoEnabled()) {
			_log.info("URL " + sb.toString());
		}

		return sb.toString();
	}

	protected String getWindowState(MarkupParams markupParams)
		throws Exception {

		String windowState = markupParams.getWindowState();

		return windowState.substring(5);
	}

	private static final String _PATH_WIDGET = "/widget/c/portal/layout?";

	private static final String _STRUTS_ACTION_PORTLET_CONFIGURATION =
		PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) +
			"struts_action";

	private static Log _log = LogFactoryUtil.getLog(MarkupServiceImpl.class);

}