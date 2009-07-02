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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.wsrp.model.WSRPProducer;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.types.CookieProtocol;
import oasis.names.tc.wsrp.v2.types.GetServiceDescription;
import oasis.names.tc.wsrp.v2.types.MarkupType;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;

/**
 * <a href="ServiceDescriptionServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ServiceDescriptionServiceImpl
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

		PortletDescription portletDescription = new PortletDescription();

		portletDescription.setTitle(getLocalizedString(title));

		String displayName = GetterUtil.getString(
			portlet.getDisplayName(), title);

		portletDescription.setDisplayName(getLocalizedString(displayName));

		PortletInfo portletInfo = portlet.getPortletInfo();

		String[] keywords = StringUtil.split(portletInfo.getKeywords());

		portletDescription.setKeywords(getLocalizedStrings(keywords));

		portletDescription.setMarkupTypes(getMarkupTypes(portlet));
		portletDescription.setPortletHandle(portlet.getPortletId());

		String shortTitle = portletInfo.getShortTitle();

		if (shortTitle == null) {
			shortTitle = title;
		}

		portletDescription.setShortTitle(getLocalizedString(shortTitle));

		return portletDescription;
	}

	protected PortletDescription[] getPortletDescriptions(
		WSRPProducer wsrpProducer) {

		String[] portletIds = StringUtil.split(wsrpProducer.getPortletIds());

		List<PortletDescription> portletDescriptions =
			new ArrayList<PortletDescription>();

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
		LogFactoryUtil.getLog(ServiceDescriptionServiceImpl.class);

	private static CookieProtocol _COOKIE_PROTOCOL = CookieProtocol.fromString(
		CookieProtocol._perGroup);

}