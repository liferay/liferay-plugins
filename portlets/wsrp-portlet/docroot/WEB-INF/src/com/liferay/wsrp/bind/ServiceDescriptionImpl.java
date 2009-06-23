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

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.axis.ServletUtil;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.service.WSRPProducerLocalServiceUtil;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.types.GetServiceDescription;
import oasis.names.tc.wsrp.v2.types.LocalizedString;
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

	public ServiceDescription getServiceDescription(
			GetServiceDescription getServiceDescription)
		throws RemoteException {

		try {
			return doGetServiceDescription(getServiceDescription);
		}
		catch (RemoteException re) {
			throw re;
		}
		catch (Exception e) {
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

		return serviceDescription;
	}

	protected PortletDescription getPortletDescription(
		WSRPProducer wsrpProducer, Portlet portlet) {

		String title = PortalUtil.getPortletTitle(
			portlet, wsrpProducer.getCompanyId(), LocaleUtil.getDefault());

		PortletDescription portletDescription = new PortletDescription();

		portletDescription.setPortletHandle(portlet.getPortletId());
		portletDescription.setTitle(new LocalizedString(title, null));

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

}