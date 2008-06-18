/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.util;

import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.soap.v2.intf.WSRPV2MarkupPortType;
import com.liferay.wsrp.soap.v2.intf.WSRPV2PortletManagementPortType;
import com.liferay.wsrp.soap.v2.intf.WSRPV2RegistrationPortType;
import com.liferay.wsrp.soap.v2.intf.WSRPV2ServiceDescriptionPortType;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * <a href="WSRPServiceFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ServiceFactory {
	public static WSRPV2MarkupPortType createMarkupService(Producer producer) {
		return createService(WSRPV2MarkupPortType.class, 
			producer.getMarkupEndpoint());
	}
	
	public static WSRPV2PortletManagementPortType 
		createPortletManagementService(Producer producer) {

		return createService(WSRPV2PortletManagementPortType.class, 
			producer.getPortletManagementEndpoint());
	}

	public static WSRPV2RegistrationPortType createRegistrationService(
		Producer producer) {
	
		return createService(WSRPV2RegistrationPortType.class, 
			producer.getRegistrationEndpoint());
	}

	public static WSRPV2ServiceDescriptionPortType 
		createServiceDescriptionService(Producer producer) {
		
		return createService(WSRPV2ServiceDescriptionPortType.class, 
			producer.getServiceDescriptionEndpoint());
	}
	
	public static <T> T createService(Class<T> clazz, String endpoint) {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		
		proxyFactory.setServiceClass(clazz);
		
		proxyFactory.setAddress(endpoint);

		return (T)proxyFactory.create();
	}

}
