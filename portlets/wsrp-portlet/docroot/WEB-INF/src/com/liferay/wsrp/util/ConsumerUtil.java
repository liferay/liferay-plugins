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

import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.soap.v2.intf.InvalidRegistration;
import com.liferay.wsrp.soap.v2.intf.MissingParameters;
import com.liferay.wsrp.soap.v2.intf.ModifyRegistrationRequired;
import com.liferay.wsrp.soap.v2.intf.OperationFailed;
import com.liferay.wsrp.soap.v2.intf.OperationNotSupported;
import com.liferay.wsrp.soap.v2.intf.ResourceSuspended;
import com.liferay.wsrp.soap.v2.intf.WSRPV2RegistrationPortType;
import com.liferay.wsrp.soap.v2.intf.WSRPV2ServiceDescriptionPortType;
import com.liferay.wsrp.soap.v2.types.GetServiceDescription;
import com.liferay.wsrp.soap.v2.types.ModelDescription;
import com.liferay.wsrp.soap.v2.types.PropertyDescription;
import com.liferay.wsrp.soap.v2.types.Register;
import com.liferay.wsrp.soap.v2.types.RegistrationContext;
import com.liferay.wsrp.soap.v2.types.RegistrationData;
import com.liferay.wsrp.soap.v2.types.ServiceDescription;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="WSRPConsumerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ConsumerUtil {

	public static final String WSRP_V2_BIND_NAMESPACE = "urn:oasis:names:tc:wsrp:v2:bind";
	
	public static ServiceDescription getServiceDescription(Producer producer) {

		WSRPV2ServiceDescriptionPortType sdService = 
			ServiceFactory.createServiceDescriptionService(producer);
		
		GetServiceDescription getServiceDescription = 
			new GetServiceDescription();
		
		ServiceDescription sd = null;
		
		try {
			sd = sdService.getServiceDescription(getServiceDescription);
		} 
		catch (InvalidRegistration e) {
			_log.error(e.getMessage(), e);
		}
		catch (ModifyRegistrationRequired e) {
			_log.error(e.getMessage(), e);
		}
		catch (OperationFailed e) {
			_log.error(e.getMessage(), e);
		}
		catch (ResourceSuspended e) {
			_log.error(e.getMessage(), e);			
		}

		if (sd.isRequiresRegistration()) {
			ModelDescription regPropDescription = 
				sd.getRegistrationPropertyDescription();
			
			List<PropertyDescription> propDescriptions = 
				regPropDescription.getPropertyDescriptions();
			
			if (propDescriptions.size() <= 0) {
				RegistrationContext registrationContext = register(producer);
				
				// get service description again after performing the required
				// registration to the producer
				
				getServiceDescription.setRegistrationContext(
						registrationContext);
				try {
					sd = sdService.getServiceDescription(getServiceDescription);
				} 
				catch (ResourceSuspended e) {
					_log.error(e.getMessage(), e);
				} 
				catch (InvalidRegistration e) {
					_log.error(e.getMessage(), e);
				} 
				catch (ModifyRegistrationRequired e) {
					_log.error(e.getMessage(), e);
				} 
				catch (OperationFailed e) {
					_log.error(e.getMessage(), e);
				}
			}
		}
		
		return sd;
	}
	
	public static RegistrationContext register(Producer producer) {
		WSRPV2RegistrationPortType registrationService = 
			ServiceFactory.createRegistrationService(producer);

		Register register = new Register();
		
		RegistrationData registrationData = new RegistrationData();
		
		registrationData.setConsumerAgent(ReleaseInfo.getReleaseInfo());
		registrationData.setConsumerName(ReleaseInfo.getServerInfo());
		registrationData.setMethodGetSupported(false);
		
		register.setRegistrationData(registrationData);				

		RegistrationContext registrationContext = null;

		try {
			registrationContext = 
				registrationService.register(register);
		}
		catch (OperationNotSupported e) {
			_log.error(e.getMessage(), e);
		}
		catch (MissingParameters e) {
			_log.error(e.getMessage(), e);
		}
		catch (OperationFailed e) {
			_log.error(e.getMessage(), e);
		}

		producer.setRegistrationContextObj(registrationContext);
		
		return registrationContext;
	}
	
	public static void resolveEndpoints(Producer producer) 
		throws WSDLException {

		WSDLFactory factory = WSDLFactory.newInstance();      
		WSDLReader reader = factory.newWSDLReader();		

		reader.setFeature("javax.wsdl.importDocuments", false); 

		Definition definition = reader.readWSDL(producer.getWsdlURL());
		
		Map<String, Service> servicesMap = definition.getServices();
		
		Service wsrpV2Service = null;
		
		Collection<Service> services = servicesMap.values();
		
		// finds the WSRP v2 service based on the namespace of one of the ports
		
		for (Service service : services) {
			Collection<Port> ports = service.getPorts().values();
			
			if (ports.size() <= 0) {
				continue;
			}
			
			Port port = ports.iterator().next();
			
			Binding binding = port.getBinding();
			
			String namespace = binding.getQName().getNamespaceURI(); 
			
			if (namespace.equals(WSRP_V2_BIND_NAMESPACE)) {
				wsrpV2Service = service;
				break;
			}
		}
				
		Collection<Port> ports = wsrpV2Service.getPorts().values();
		
		for (Port port : ports) {
			Binding binding = port.getBinding();
			
			String bindingName = 
				binding.getQName().getLocalPart().toLowerCase();
			
			if(!bindingName.contains("v2")) {
				continue;
			}

			if (bindingName.contains("markup")) {
				producer.setMarkupEndpoint(_getEndpointURL(port));
			}
			else if (bindingName.contains("portletmanagement")) {
				producer.setPortletManagementEndpoint(_getEndpointURL(port));
			}
			else if (bindingName.contains("registration")) {
				producer.setRegistrationEndpoint(_getEndpointURL(port));
			}
			else if (bindingName.contains("servicedescription")) {
				producer.setServiceDescriptionEndpoint(_getEndpointURL(port));
			}
		}
	}

	private static String _getEndpointURL(Port port) {
		List<ExtensibilityElement> extElements = 
			port.getExtensibilityElements();
		
		SOAPAddress address = (SOAPAddress)extElements.get(0);
		String endpoint = address.getLocationURI();
		
		return endpoint;
	}

	private static Log _log = LogFactory.getLog(ConsumerUtil.class);

}
