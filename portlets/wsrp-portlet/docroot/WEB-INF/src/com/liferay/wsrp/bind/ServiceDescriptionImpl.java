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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.types.CookieProtocol;
import oasis.names.tc.wsrp.v2.types.GetServiceDescription;
import oasis.names.tc.wsrp.v2.types.LocalizedString;
import oasis.names.tc.wsrp.v2.types.MarkupType;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;

/**
 * <a href="ServiceDescriptionImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ServiceDescriptionImpl
	implements WSRP_v2_ServiceDescription_PortType {

	public ServiceDescriptionImpl() {
	}

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

		HttpServletRequest request = ServletUtil.getRequest();

		long wsrpProducerId = ParamUtil.getLong(request, "wsrpProducerId");

		WSRPProducer wsrpProducer =
			WSRPProducerLocalServiceUtil.getWSRPProducer(wsrpProducerId);

		ServiceDescription serviceDescription = new ServiceDescription();

		serviceDescription.setOfferedPortlets(
			getPortletDescriptions(wsrpProducer));
		serviceDescription.setRequiresInitCookie(_COOKIE_PROTOCOL);

		return serviceDescription;
	}

	protected LocalizedString getLocalizedString(String value) {
		return new LocalizedString(value, null);
	}

	protected LocalizedString[] getLocalizedStrings(String[] values) {
		LocalizedString[] localizedStrings = new LocalizedString[values.length];

		for (int i = 0; i < values.length; i++) {
			String value = values[i];

			localizedStrings[i] = getLocalizedString(value);
		}

		return localizedStrings;
	}

	protected MarkupType[] getMarkupTypes(Portlet portlet) {
		List<MarkupType> markupTypes = new ArrayList<MarkupType>();

		Map<String, Set<String>> portletModes = portlet.getPortletModes();
		Map<String, Set<String>> windowStates = portlet.getWindowStates();

		Set<String> mimeTypes = new HashSet<String>();

		mimeTypes.addAll(portletModes.keySet());
		mimeTypes.addAll(windowStates.keySet());

		for (String mimeType : mimeTypes) {
			Set<String> mimeTypePortletModes = portletModes.get(mimeType);

			if (mimeTypePortletModes == null) {
				mimeTypePortletModes = Collections.EMPTY_SET;
			}

			Set<String> mimeTypeWindowStates = windowStates.get(mimeType);

			if (mimeTypeWindowStates == null) {
				mimeTypeWindowStates = Collections.EMPTY_SET;
			}

			MarkupType markupType = new MarkupType();

			markupType.setMimeType(mimeType);
			markupType.setModes(
				mimeTypePortletModes.toArray(
					new String[mimeTypePortletModes.size()]));
			markupType.setWindowStates(
				mimeTypeWindowStates.toArray(
					new String[mimeTypeWindowStates.size()]));

			markupTypes.add(markupType);
		}

		return markupTypes.toArray(new MarkupType[markupTypes.size()]);
	}

	protected PortletDescription getPortletDescription(
		WSRPProducer wsrpProducer, Portlet portlet) {

		String title = PortalUtil.getPortletTitle(
			portlet, wsrpProducer.getCompanyId(), LocaleUtil.getDefault());

		String displayName = portlet.getDisplayName();

		if (displayName == null) {
			displayName = title;
		}

		PortletInfo portletInfo = portlet.getPortletInfo();

		String[] keywords = StringUtil.split(portletInfo.getKeywords());

		String shortTitle = portletInfo.getShortTitle();

		if (shortTitle == null) {
			shortTitle = title;
		}

		PortletDescription portletDescription = new PortletDescription();

		portletDescription.setDisplayName(getLocalizedString(displayName));
		portletDescription.setKeywords(getLocalizedStrings(keywords));
		portletDescription.setMarkupTypes(getMarkupTypes(portlet));
		portletDescription.setPortletHandle(portlet.getPortletId());
		portletDescription.setShortTitle(getLocalizedString(shortTitle));
		portletDescription.setTitle(getLocalizedString(title));

		return portletDescription;
	}

	protected PortletDescription[] getPortletDescriptions(
		WSRPProducer wsrpProducer) {

		List<PortletDescription> portletDescriptions =
			new ArrayList<PortletDescription>();

		String[] portletIds = StringUtil.split(wsrpProducer.getPortletIds());

		for (String portletId : portletIds) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);

			if (portlet == null) {
				continue;
			}

			PortletDescription portletDescription = getPortletDescription(
				wsrpProducer, portlet);

			portletDescriptions.add(portletDescription);
		}

		return portletDescriptions.toArray(
			new PortletDescription[portletDescriptions.size()]);
	}

	private static Log _log =
		LogFactoryUtil.getLog(ServiceDescriptionImpl.class);

	private static CookieProtocol _COOKIE_PROTOCOL = CookieProtocol.fromString(
		CookieProtocol._perUser);

}