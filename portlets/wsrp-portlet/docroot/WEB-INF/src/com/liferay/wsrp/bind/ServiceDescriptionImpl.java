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

import java.rmi.RemoteException;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v2.types.GetServiceDescription;
import oasis.names.tc.wsrp.v2.types.InvalidRegistrationFault;
import oasis.names.tc.wsrp.v2.types.LocalizedString;
import oasis.names.tc.wsrp.v2.types.ModifyRegistrationRequiredFault;
import oasis.names.tc.wsrp.v2.types.OperationFailedFault;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.ResourceSuspendedFault;
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
		throws InvalidRegistrationFault, ModifyRegistrationRequiredFault,
			   OperationFailedFault, RemoteException, ResourceSuspendedFault {

		ServiceDescription serviceDescription = new ServiceDescription();

		PortletDescription portletDescription = new PortletDescription();

		portletDescription.setPortletHandle("1");
		portletDescription.setTitle(new LocalizedString("Hello World", null));

		PortletDescription[] portletDescriptions = {
			portletDescription
		};

		serviceDescription.setOfferedPortlets(portletDescriptions);

		return serviceDescription;
	}

}