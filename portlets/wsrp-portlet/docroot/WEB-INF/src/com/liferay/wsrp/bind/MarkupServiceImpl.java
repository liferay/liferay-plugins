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
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;

import java.rmi.RemoteException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v2.types.ClientData;
import oasis.names.tc.wsrp.v2.types.Extension;
import oasis.names.tc.wsrp.v2.types.GetMarkup;
import oasis.names.tc.wsrp.v2.types.GetResource;
import oasis.names.tc.wsrp.v2.types.HandleEvents;
import oasis.names.tc.wsrp.v2.types.HandleEventsResponse;
import oasis.names.tc.wsrp.v2.types.InitCookie;
import oasis.names.tc.wsrp.v2.types.MarkupContext;
import oasis.names.tc.wsrp.v2.types.MarkupParams;
import oasis.names.tc.wsrp.v2.types.MarkupResponse;
import oasis.names.tc.wsrp.v2.types.NamedString;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.PortletContext;
import oasis.names.tc.wsrp.v2.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.types.ResourceResponse;

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

	protected MarkupResponse doGetMarkup(GetMarkup getMarkup) throws Exception {
		WSRPProducer wsrpProducer = getWSRPProducer();

		String url = getURL(getMarkup, wsrpProducer);

		Http.Options httpOptions = new Http.Options();

		httpOptions.setLocation(url);

		MarkupParams markupParams = getMarkup.getMarkupParams();

		ClientData clientData = markupParams.getClientData();

		NamedString[] clientAttributes = clientData.getClientAttributes();

		for (NamedString clientAttribute : clientAttributes) {
			String name = clientAttribute.getName();
			String value = clientAttribute.getValue();

			if (name.equalsIgnoreCase(HttpHeaders.ACCEPT_ENCODING) ||
				name.equalsIgnoreCase(HttpHeaders.COOKIE)) {

				continue;
			}

			httpOptions.addHeader(name, value);
		}

		String content = HttpUtil.URLtoString(httpOptions);

		MarkupContext markupContext = new MarkupContext();

		markupContext.setItemString(content);

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
		return null;
	}

	protected BlockingInteractionResponse doPerformBlockingInteraction(
			PerformBlockingInteraction performBlockingInteraction)
		throws Exception {

		BlockingInteractionResponse blockingInteractionResponse =
			new BlockingInteractionResponse();

		return blockingInteractionResponse;
	}

	protected Extension[] doReleaseSessions(ReleaseSessions releaseSessions)
		throws Exception {

		return null;
	}

	protected Layout getLayout(GetMarkup getMarkup, WSRPProducer wsrpProducer)
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

		String portletId = getPortletId(getMarkup);

		if (!layoutTypePortlet.hasPortletId(portletId)) {
			layoutTypePortlet.addPortletId(0, portletId, "column-1", -1, false);

			LayoutLocalServiceUtil.updateLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), layout.getTypeSettings());
		}

		return layout;
	}

	protected String getPortletId(GetMarkup getMarkup) throws Exception {
		PortletContext portletContext = getMarkup.getPortletContext();

		return portletContext.getPortletHandle();
	}

	protected String getPortletMode(GetMarkup getMarkup) throws Exception {
		MarkupParams markupParams = getMarkup.getMarkupParams();

		String portletMode = markupParams.getMode();

		return portletMode.substring(5);
	}

	protected String getURL(GetMarkup getMarkup, WSRPProducer wsrpProducer)
		throws Exception {

		HttpServletRequest request = ServletUtil.getRequest();

		String portalURL = PortalUtil.getPortalURL(request);

		StringBuilder sb = new StringBuilder();

		sb.append(portalURL);
		sb.append(PortalUtil.getPathMain());
		sb.append(_PATH_RENDER_PORTLET);
		sb.append(StringPool.QUESTION);

		Layout layout = getLayout(getMarkup, wsrpProducer);

		sb.append("p_l_id=");
		sb.append(layout.getPlid());

		String portletId = getPortletId(getMarkup);

		sb.append("&p_p_id=");
		sb.append(HttpUtil.encodeURL(portletId));

		sb.append("&p_p_lifecycle=0&p_p_state=exclusive");

		String portletMode = getPortletMode(getMarkup);

		sb.append("&p_p_mode=");
		sb.append(HttpUtil.encodeURL(portletMode));

		if (_log.isInfoEnabled()) {
			_log.info("URL " + sb.toString());
		}

		return sb.toString();
	}

	private static final String _PATH_RENDER_PORTLET = "/portal/render_portlet";

	private static Log _log = LogFactoryUtil.getLog(MarkupServiceImpl.class);

}