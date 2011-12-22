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

package com.liferay.wsrp.bind;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.util.ExtensionUtil;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.types.CookieProtocol;
import oasis.names.tc.wsrp.v2.types.GetServiceDescription;
import oasis.names.tc.wsrp.v2.types.MarkupType;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;

import org.apache.axis.message.MessageElement;

/**
 * @author Brian Wing Shun Chan
 */
public class V2ServiceDescriptionServiceImpl
	extends BaseServiceImpl implements WSRP_v2_ServiceDescription_PortType {

	public ServiceDescription getServiceDescription(
			GetServiceDescription getServiceDescription)
		throws RemoteException {

		try {
			return doGetServiceDescription(getServiceDescription);
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

	protected ServiceDescription doGetServiceDescription(
			GetServiceDescription getServiceDescription)
		throws Exception {

		WSRPProducer wsrpProducer = getWSRPProducer();

		ServiceDescription serviceDescription = new ServiceDescription();

		serviceDescription.setOfferedPortlets(
			getPortletDescriptions(wsrpProducer));
		serviceDescription.setRequiresInitCookie(_COOKIE_PROTOCOL);

		return serviceDescription;
	}

	protected MarkupType[] getMarkupTypes(Portlet portlet) {
		Map<String, Set<String>> portletModes = portlet.getPortletModes();
		Map<String, Set<String>> windowStates = portlet.getWindowStates();

		Set<String> mimeTypes = new HashSet<String>();

		mimeTypes.addAll(portletModes.keySet());
		mimeTypes.addAll(windowStates.keySet());

		List<MarkupType> markupTypes = new ArrayList<MarkupType>();

		for (String mimeType : mimeTypes) {
			Set<String> mimeTypePortletModes = portletModes.get(mimeType);

			if (mimeTypePortletModes == null) {
				mimeTypePortletModes = Collections.emptySet();
			}

			Set<String> mimeTypeWindowStates = windowStates.get(mimeType);

			if (mimeTypeWindowStates == null) {
				mimeTypeWindowStates = Collections.emptySet();
			}

			MarkupType markupType = new MarkupType();

			markupType.setMimeType(mimeType);
			markupType.setModes(getWSRPKeys(mimeTypePortletModes));
			markupType.setWindowStates(getWSRPKeys(mimeTypeWindowStates));

			markupTypes.add(markupType);
		}

		return markupTypes.toArray(new MarkupType[markupTypes.size()]);
	}

	protected PortletDescription getPortletDescription(
		WSRPProducer wsrpProducer, Portlet portlet, String portletId) {

		PortletDescription portletDescription = new PortletDescription();

		portletDescription.setPortletHandle(portletId);
		portletDescription.setMarkupTypes(getMarkupTypes(portlet));

		HttpServletRequest request = ServletUtil.getRequest();

		ServletContext servletContext =
			request.getSession().getServletContext();

		String title = PortalUtil.getPortletTitle(
			portlet, servletContext, LocaleUtil.getDefault());

		portletDescription.setTitle(getLocalizedString(title));

		PortletInfo portletInfo = portlet.getPortletInfo();

		String shortTitle = portletInfo.getShortTitle();

		if (shortTitle == null) {
			shortTitle = title;
		}

		portletDescription.setShortTitle(getLocalizedString(shortTitle));

		String[] keywords = StringUtil.split(portletInfo.getKeywords());

		portletDescription.setKeywords(getLocalizedStrings(keywords));

		String displayName = GetterUtil.getString(
			portlet.getDisplayName(), title);

		portletDescription.setDisplayName(getLocalizedString(displayName));

		setExtensions(portletDescription, portlet);

		return portletDescription;
	}

	protected PortletDescription[] getPortletDescriptions(
		WSRPProducer wsrpProducer) {

		String[] portletIds = StringUtil.split(wsrpProducer.getPortletIds());

		List<PortletDescription> portletDescriptions =
			new ArrayList<PortletDescription>();

		for (String portletId : portletIds) {
			String rootPortletId = PortletConstants.getRootPortletId(portletId);

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				rootPortletId);

			if (portlet == null) {
				continue;
			}

			PortletDescription portletDescription = getPortletDescription(
				wsrpProducer, portlet, portletId);

			portletDescriptions.add(portletDescription);
		}

		return portletDescriptions.toArray(
			new PortletDescription[portletDescriptions.size()]);
	}

	protected String[] getWSRPKeys(Set<String> keys) {
		String[] wsrpKeys = new String[keys.size()];

		int i = 0;

		for (String key : keys) {
			wsrpKeys[i++] = "wsrp:".concat(key);
		}

		return wsrpKeys;
	}

	protected void setExtensions(
		PortletDescription portletDescription, Portlet portlet) {

		List<MessageElement> messageElements = new ArrayList<MessageElement>();

		ExtensionUtil.addMessageElement(
			messageElements, "css-class-wrapper",
			portlet.getCssClassWrapper());

		HttpServletRequest request = ServletUtil.getRequest();

		String portalURL = PortalUtil.getPortalURL(request);

		String portalPath = portalURL + PortalUtil.getPathContext();
		String portletPath = portalURL + portlet.getContextPath();

		Portlet rootPortlet = portlet.getRootPortlet();

		long timestamp = rootPortlet.getTimestamp();

		for (String footerPortalCss : portlet.getHeaderPortalCss()) {
			if (!HttpUtil.hasProtocol(footerPortalCss)) {
				footerPortalCss =
					portalPath + footerPortalCss + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "footer-portal-css", footerPortalCss);
		}

		for (String footerPortalJavaScript :
				portlet.getHeaderPortalJavaScript()) {

			if (!HttpUtil.hasProtocol(footerPortalJavaScript)) {
				footerPortalJavaScript =
					portalPath + footerPortalJavaScript + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "footer-portal-javascript",
				footerPortalJavaScript);
		}

		for (String footerPortletCss : portlet.getHeaderPortletCss()) {
			if (!HttpUtil.hasProtocol(footerPortletCss)) {
				footerPortletCss =
					portletPath + footerPortletCss + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "footer-portlet-css", footerPortletCss);
		}

		for (String footerPortletJavaScript :
				portlet.getHeaderPortletJavaScript()) {

			if (!HttpUtil.hasProtocol(footerPortletJavaScript)) {
				footerPortletJavaScript =
					portletPath + footerPortletJavaScript + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "footer-portlet-javascript",
				footerPortletJavaScript);
		}

		for (String headerPortalCss : portlet.getHeaderPortalCss()) {
			if (!HttpUtil.hasProtocol(headerPortalCss)) {
				headerPortalCss =
					portalPath + headerPortalCss + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "header-portal-css", headerPortalCss);
		}

		for (String headerPortalJavaScript :
				portlet.getHeaderPortalJavaScript()) {

			if (!HttpUtil.hasProtocol(headerPortalJavaScript)) {
				headerPortalJavaScript =
					portalPath + headerPortalJavaScript + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "header-portal-javascript",
				headerPortalJavaScript);
		}

		for (String headerPortletCss : portlet.getHeaderPortletCss()) {
			if (!HttpUtil.hasProtocol(headerPortletCss)) {
				headerPortletCss =
					portletPath + headerPortletCss + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "header-portlet-css", headerPortletCss);
		}

		for (String headerPortletJavaScript :
				portlet.getHeaderPortletJavaScript()) {

			if (!HttpUtil.hasProtocol(headerPortletJavaScript)) {
				headerPortletJavaScript =
					portletPath + headerPortletJavaScript + "?t=" + timestamp;
			}

			ExtensionUtil.addMessageElement(
				messageElements, "header-portlet-javascript",
				headerPortletJavaScript);
		}

		portletDescription.setExtensions(
			ExtensionUtil.getExtensions(messageElements));
	}

	private static Log _log = LogFactoryUtil.getLog(
		V2ServiceDescriptionServiceImpl.class);

	private static CookieProtocol _COOKIE_PROTOCOL = CookieProtocol.fromString(
		CookieProtocol._perGroup);

}