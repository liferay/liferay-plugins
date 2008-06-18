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

package com.liferay.wsrp.consumer.portlet;

import java.util.List;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.service.ProducerServiceUtil;
import com.liferay.wsrp.soap.v2.intf.WSRPV2MarkupPortType;
import com.liferay.wsrp.soap.v2.intf.WSRPV2RegistrationPortType;
import com.liferay.wsrp.soap.v2.types.CookieProtocol;
import com.liferay.wsrp.soap.v2.types.GetMarkup;
import com.liferay.wsrp.soap.v2.types.InitCookie;
import com.liferay.wsrp.soap.v2.types.MarkupParams;
import com.liferay.wsrp.soap.v2.types.MarkupResponse;
import com.liferay.wsrp.soap.v2.types.PortletContext;
import com.liferay.wsrp.soap.v2.types.Property;
import com.liferay.wsrp.soap.v2.types.Register;
import com.liferay.wsrp.soap.v2.types.RegistrationContext;
import com.liferay.wsrp.soap.v2.types.RegistrationData;
import com.liferay.wsrp.soap.v2.types.RuntimeContext;
import com.liferay.wsrp.soap.v2.types.ServiceDescription;
import com.liferay.wsrp.util.ProducerModelUtil;
import com.liferay.wsrp.util.ServiceFactory;
import com.liferay.wsrp.util.URLRewriter;
import com.liferay.wsrp.util.WSRPMode;
import com.liferay.wsrp.util.WSRPState;
import com.sun.portal.wsrp.common.WSRPSpecKeys;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="ConsumerPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ConsumerPortlet extends GenericPortlet {
	public void render(RenderRequest req, RenderResponse res)
		throws PortletException {

		PortletSession ses = req.getPortletSession();

		WSRPV2MarkupPortType markupService = 
			(WSRPV2MarkupPortType)ses.getAttribute("markupService");
		
		if (markupService == null) {
			try {
				markupService = getMarkupService(req);
			}
			catch (Exception e) {
				_log.error(e.getMessage(), e);
				
				return;
			}
						
			ses.setAttribute("markupService", markupService);
		}
		
		GetMarkup getMarkup = new GetMarkup();		

		// PortletContext
		
		PortletContext portletContext = new PortletContext();

		PortletPreferences prefs = req.getPreferences();

		String portletHandle = prefs.getValue(
				"portletHandle", StringPool.BLANK);

		if (Validator.isNull(portletHandle)) {
			return;			
		}
		
		portletContext.setPortletHandle(portletHandle);
		
		getMarkup.setPortletContext(portletContext);
		
		// RuntimeContext
		
		RuntimeContext runtimeContext = new RuntimeContext();
		
		runtimeContext.setUserAuthentication(WSRPSpecKeys.AUTH_PASSWORD);
		
		getMarkup.setRuntimeContext(runtimeContext);
		
		// MarkupParams
		
		MarkupParams markupParams = new MarkupParams();
		
		markupParams.setSecureClientCommunication(false);
	
		PortletMode portletMode = req.getPortletMode();
		
		markupParams.setMode(WSRPMode.fromPortlet(portletMode).getWSRP());
		
		WindowState windowState = req.getWindowState();

		markupParams.setWindowState(
			WSRPState.fromPortlet(windowState).getWSRP());

		markupParams.getLocales().add(req.getLocale().toString());
		
		markupParams.getMimeTypes().add(ContentTypes.TEXT_HTML_UTF8);
		
		getMarkup.setMarkupParams(markupParams);
		
		MarkupResponse markupResponse = null;
		
		try {
			markupResponse = markupService.getMarkup(getMarkup);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			return;
		}

		String markup =
			markupResponse.getMarkupContext().getItemString();
		
		markup = URLRewriter.rewrite(markup, res);
		
		res.setContentType(ContentTypes.TEXT_HTML_UTF8);
		
		try {
			res.getWriter().write(markup);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			return;
		}
	}
	
	protected WSRPV2MarkupPortType getMarkupService(PortletRequest req) 
		throws Exception {
		
		PortletPreferences prefs = req.getPreferences();
		
		long producerId = GetterUtil.getLong(
			prefs.getValue("producerId", StringPool.BLANK));
		
		if (producerId <= 0) {
			return null;
		}

		Producer producer = ProducerServiceUtil.getProducer(producerId);
		
		WSRPV2MarkupPortType markupService = 
			ServiceFactory.createMarkupService(producer);
		
		ServiceDescription sd = 
			ProducerModelUtil.getServiceDescription(producer);
		
		// cookie support

		if (!(sd.getRequiresInitCookie() == CookieProtocol.NONE)) {
			BindingProvider bindingProvider = (BindingProvider)markupService;
			
			bindingProvider.getRequestContext().put(
				BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

			markupService.initCookie(new InitCookie());			
		}
		
		return markupService;
	}
	
	protected RegistrationContext register(Producer producer) throws Exception {
		Register register = new Register();
		
		RegistrationData registrationData = new RegistrationData();
		
		registrationData.setConsumerAgent("Liferay Portal");
		registrationData.setConsumerName("Liferay");
		registrationData.setMethodGetSupported(false);
		
		List<Property> registrationProperties = 
			registrationData.getRegistrationProperties();
		
		Property property = null;
		QName propertyName = null;
	
		property = new Property();
		propertyName = 
			new QName("urn:oasis:names:tc:wsrp:v2:types",
				"ConsumerRegistrationState");
		property.setName(propertyName);
		property.setStringValue("LiferayConsumerRegistrationState");
	
		property = new Property();
		propertyName = new QName("urn:oasis:names:tc:wsrp:v2:types",
			"ProducerRegistrationState");
		property.setName(propertyName);
		property.setStringValue("LiferayProducerRegistrationState");
	
		property = new Property();
		propertyName = new QName("urn:oasis:names:tc:wsrp:v2:types",
			"ThrowOperationFailed");
		property.setName(propertyName);
		property.setStringValue("No");
		
		registrationProperties.add(property);
		
		register.setRegistrationData(registrationData);
	
		WSRPV2RegistrationPortType registrationService = 
			ServiceFactory.createRegistrationService(producer);
	
		RegistrationContext registrationContext = 
			registrationService.register(register);
		
		return registrationContext;
	}

	private static Log _log = LogFactory.getLog(ConsumerPortlet.class);

}