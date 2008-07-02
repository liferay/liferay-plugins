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

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wsrp.consumer.NoSuchProducerException;
import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.service.ProducerServiceUtil;
import com.liferay.wsrp.soap.v2.intf.WSRPV2MarkupPortType;
import com.liferay.wsrp.soap.v2.types.BlockingInteractionResponse;
import com.liferay.wsrp.soap.v2.types.ClientData;
import com.liferay.wsrp.soap.v2.types.CookieProtocol;
import com.liferay.wsrp.soap.v2.types.GetMarkup;
import com.liferay.wsrp.soap.v2.types.InitCookie;
import com.liferay.wsrp.soap.v2.types.InteractionParams;
import com.liferay.wsrp.soap.v2.types.MarkupContext;
import com.liferay.wsrp.soap.v2.types.MarkupParams;
import com.liferay.wsrp.soap.v2.types.MarkupResponse;
import com.liferay.wsrp.soap.v2.types.NamedString;
import com.liferay.wsrp.soap.v2.types.NavigationalContext;
import com.liferay.wsrp.soap.v2.types.PerformBlockingInteraction;
import com.liferay.wsrp.soap.v2.types.PersonName;
import com.liferay.wsrp.soap.v2.types.PortletContext;
import com.liferay.wsrp.soap.v2.types.RuntimeContext;
import com.liferay.wsrp.soap.v2.types.ServiceDescription;
import com.liferay.wsrp.soap.v2.types.SessionContext;
import com.liferay.wsrp.soap.v2.types.SessionParams;
import com.liferay.wsrp.soap.v2.types.StateChange;
import com.liferay.wsrp.soap.v2.types.UpdateResponse;
import com.liferay.wsrp.soap.v2.types.UploadContext;
import com.liferay.wsrp.soap.v2.types.UserContext;
import com.liferay.wsrp.soap.v2.types.UserProfile;
import com.liferay.wsrp.util.ProducerModelUtil;
import com.liferay.wsrp.util.ServiceFactory;
import com.liferay.wsrp.util.URLRewriter;
import com.liferay.wsrp.util.WSRPMode;
import com.liferay.wsrp.util.WSRPState;
import com.sun.portal.wsrp.common.WSRPSpecKeys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="ConsumerPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ConsumerPortlet extends JSPPortlet {
	public void processAction(ActionRequest req, ActionResponse res)
		throws PortletException {

		WSRPV2MarkupPortType markupService = null;

		try {
			markupService = getMarkupService(req);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			return;
		}

		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		MarkupParams markupParams = new MarkupParams();
		UserContext userContext = new UserContext();
		InteractionParams interactionParams = new InteractionParams();

		initPerformBlockingInteraction(
			req, portletContext, runtimeContext, markupParams,
			userContext, interactionParams);

		PerformBlockingInteraction pbi = new PerformBlockingInteraction();

		pbi.setPortletContext(portletContext);
		pbi.setRuntimeContext(runtimeContext);
		pbi.setMarkupParams(markupParams);
		pbi.setUserContext(userContext);
		pbi.setInteractionParams(interactionParams);

		BlockingInteractionResponse bir = null;

		try {
			bir = markupService.performBlockingInteraction(pbi);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
			
			return;
		}

		processBlockingInteractionResponse(req, res, bir);
	}

	public void render(RenderRequest req, RenderResponse res)
		throws PortletException {

		PortletSession ses = req.getPortletSession();

		MarkupContext cachedMarkup =
			(MarkupContext)ses.getAttribute("markupContext");

		// Optimization for markup from previous blocking interaction

		if (cachedMarkup != null) {
			processMarkupContext(req, res, cachedMarkup);

			ses.removeAttribute("markupContext");

			return;
		}

		WSRPV2MarkupPortType markupService = null;

		try {
			markupService = getMarkupService(req);
		}
		catch (NoSuchProducerException e) {
			include(null, "configure", req, res);
			
			return;
		}
		catch (Exception e) {
			include(e, null, req, res);
			
			_log.error(e.getMessage(), e);

			return;			
		}

		PortletContext portletContext = new PortletContext();
		RuntimeContext runtimeContext = new RuntimeContext();
		UserContext userContext = new UserContext();
		MarkupParams markupParams = new MarkupParams();

		initGetMarkup(
			req, portletContext, runtimeContext, markupParams,
			userContext);

		GetMarkup getMarkup = new GetMarkup();

		getMarkup.setPortletContext(portletContext);
		getMarkup.setRuntimeContext(runtimeContext);
		getMarkup.setMarkupParams(markupParams);
		getMarkup.setUserContext(userContext);

		MarkupResponse markupResponse = null;

		try {
			markupResponse = markupService.getMarkup(getMarkup);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			return;
		}

		processMarkupResponse(req, res, markupResponse);
	}

	public void serveResource(ResourceRequest req,
		ResourceResponse res) throws IOException, 
		PortletException {

		URL url = new URL(req.getResourceID());
		URLConnection con = url.openConnection();

		// Pass-thru existing cookie

		//cookie = Encryptor.decryptRaw(key, Base64.decode(cookie));

		//con.setRequestProperty("Cookie", cookie);

		con.connect();

		res.setContentType(con.getContentType());
		res.setContentLength(con.getContentLength());

		InputStream in = con.getInputStream();
		OutputStream out = res.getPortletOutputStream();

		int next;

		while ((next = in.read()) != -1) {
			out.write(next);
		}

		out.close();
	}

	protected void addFormField(
		List<NamedString> formParams, String name, String values[]) {

		for (int i = 0; i < values.length; i++) {
			NamedString paramPair = new NamedString();

			paramPair.setName(name);
			paramPair.setValue(values[i]);

			formParams.add(paramPair);
		}

	}

	protected void addFormFields(
		PortletRequest req, List<NamedString> params) {

		Enumeration<String> paramNames = req.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();
			
			if (isReservedParam(name)) {
				continue;
			}

			String[] values = req.getParameterValues(name);

			if (values == null) {
				continue;
			}

			addFormField(params, name, values);
		}
	}

	protected WSRPV2MarkupPortType createMarkupService(PortletRequest req)
		throws Exception {

		PortletPreferences prefs = req.getPreferences();

		long producerId = GetterUtil.getLong(
			prefs.getValue("producerId", StringPool.BLANK));

		Producer producer = ProducerServiceUtil.getProducer(producerId);

		WSRPV2MarkupPortType markupService =
			ServiceFactory.createMarkupService(producer);

		ServiceDescription sd =
			ProducerModelUtil.getServiceDescription(producer);

		// cookie support

		if (sd.getRequiresInitCookie() != CookieProtocol.NONE) {
			BindingProvider bindingProvider = (BindingProvider)markupService;

			bindingProvider.getRequestContext().put(
				BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

			markupService.initCookie(new InitCookie());
		}

		return markupService;

	}

	protected WSRPV2MarkupPortType getMarkupService(PortletRequest req)
		throws Exception {

		PortletSession ses = req.getPortletSession();

		WSRPV2MarkupPortType markupService =
			(WSRPV2MarkupPortType)ses.getAttribute("markupService");

		if (markupService == null) {
			markupService = createMarkupService(req);

			ses.setAttribute("markupService", markupService);
		}

		return markupService;
	}

	protected SessionContext getSessionContext(PortletRequest req) {
		PortletSession ses = req.getPortletSession();

		return (SessionContext)ses.getAttribute("sessionContext");
	}

	protected void include(Exception e, String key, PortletRequest req, 
		PortletResponse res) throws PortletException {

		if (e != null) {
			SessionErrors.add(req, e.getClass().getName(), e);
		}
		
		if (Validator.isNotNull(key)) {
			SessionMessages.add(req, key);
		}
		
		try {
			include("/consumer/view.jsp", req, res);
		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage(), ioe);
		}
	}

	protected void initFormParams(
		ActionRequest req, List<NamedString> formParams,
		List<UploadContext> uploadContexts) {

		HttpServletRequest httpReq =
			PortalUtil.getHttpServletRequest(req);

		String contentType = httpReq.getContentType();

		if (Validator.isNotNull(contentType) &&
				contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {

			initUploadContexts(req, uploadContexts, formParams);
		}
		else {
			addFormFields(req, formParams);
		}
	}

	protected void initGetMarkup(
		PortletRequest req, PortletContext pc, RuntimeContext rc,
		MarkupParams mp, UserContext uc) {

		// PortletContext

		PortletPreferences prefs = req.getPreferences();

		String portletHandle = prefs.getValue(
				"portletHandle", StringPool.BLANK);

		if (Validator.isNull(portletHandle)) {
			return;
		}

		pc.setPortletHandle(portletHandle);

		PortletSession ses = req.getPortletSession();

		byte[] portletState = (byte[])ses.getAttribute("portletState");

		pc.setPortletState(portletState);

		// RuntimeContext

		rc.setUserAuthentication(WSRPSpecKeys.AUTH_PASSWORD);

		SessionContext sessionContext = getSessionContext(req);

		if (sessionContext != null) {
			SessionParams sessionParams = new SessionParams();

			sessionParams.setSessionID(sessionContext.getSessionID());

			rc.setSessionParams(sessionParams);
		}

		// MarkupParams

		mp.setSecureClientCommunication(false);

		PortletMode portletMode = req.getPortletMode();

		mp.setMode(WSRPMode.fromPortlet(portletMode).getWSRP());

		WindowState windowState = req.getWindowState();

		mp.setWindowState(
			WSRPState.fromPortlet(windowState).getWSRP());

		mp.getLocales().add(req.getLocale().toString());

		mp.getMimeTypes().add(ContentTypes.TEXT_HTML_UTF8);

		String navState = req.getParameter(WSRPSpecKeys.NAVIGATIONAL_STATE);

		NavigationalContext navigationalContext = new NavigationalContext();

		navigationalContext.setOpaqueValue(navState);

		mp.setNavigationalContext(navigationalContext);

		LiferayPortletRequest lrReq = (LiferayPortletRequest) req;

		HttpServletRequest httpReq = lrReq.getHttpServletRequest();

		ClientData clientData = new ClientData();

		clientData.setUserAgent(httpReq.getHeader("User-Agent"));

		// UserContext

		String userId = req.getRemoteUser();

		try {
			User user =
				UserLocalServiceUtil.getUser(GetterUtil.getLong(userId, -1));

			if (user != null) {

				uc.setUserContextKey(userId);

				UserProfile userProfile = new UserProfile();

				PersonName personName = new PersonName();

				personName.setFamily(user.getLastName());
				personName.setMiddle(user.getMiddleName());
				personName.setGiven(user.getFirstName());
				personName.setNickname(user.getScreenName());

				userProfile.setName(personName);

				if (user.isMale()) {
					userProfile.setGender("M");
				}
				else {
					userProfile.setGender("F");
				}

				uc.setProfile(userProfile);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("UserContext not set");
			}
		}
	}

	protected void initPerformBlockingInteraction(
		ActionRequest req, PortletContext pc, RuntimeContext rc,
		MarkupParams mp, UserContext uc, InteractionParams ip) {

		initGetMarkup(req, pc, rc, mp, uc);

		// InteractionParams

		ip.setPortletStateChange(StateChange.CLONE_BEFORE_WRITE);

		String interactionState =
			req.getParameter(WSRPSpecKeys.INTERACTION_STATE);

		ip.setInteractionState(interactionState);

		initFormParams(req, ip.getFormParameters(), ip.getUploadContexts());
	}

	protected void initUploadContexts(
		ActionRequest req, List<UploadContext> uploadContexts,
		List<NamedString> params) {

		UploadPortletRequest upr =
			PortalUtil.getUploadPortletRequest(req);

		Enumeration keys = upr.getParameterNames();

		while (keys.hasMoreElements()) {
			String name = (String)keys.nextElement();

			if (isReservedParam(name)) {
				continue;
			}

			if (upr.isFormField(name)) {
				addFormField(
					params, name, req.getParameterValues(name));
			}
			else {
				UploadContext uploadContext = new UploadContext();

				String partContentType = upr.getContentType(name);

				uploadContext.setMimeType(partContentType);

				StringBuilder sb = new StringBuilder();

				sb.append("form-data; ");
				sb.append("name=");
				sb.append(name);
				sb.append("; filename=");
				sb.append(upr.getFileName(name));

				NamedString mimeAttr = new NamedString();

				mimeAttr.setName("Content-Disposition");
				mimeAttr.setValue(sb.toString());

				List<NamedString> mimeAttrs = uploadContext.getMimeAttributes();

				mimeAttrs.add(mimeAttr);

				File file = upr.getFile(name);

				byte[] fileBytes = null;

				try {
					fileBytes = FileUtil.getBytes(file);
				}
				catch (IOException e) {
					throw new IllegalStateException(
						"Error reading multi-part file");
				}

				if (fileBytes == null) {
					continue;
				}

				uploadContext.setUploadData(fileBytes);

				uploadContexts.add(uploadContext);
			}
		}
	}

	protected boolean isReservedParam(String name) {
		if (name.startsWith("wsrp-")) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void processBlockingInteractionResponse(
		ActionRequest req, ActionResponse res, BlockingInteractionResponse bir)
	{
		UpdateResponse updateResponse = bir.getUpdateResponse();

		// The producer can either send a update response or a redirect

		String redirectURL = bir.getRedirectURL();

		if (Validator.isNotNull(redirectURL)) {
			try {
				res.sendRedirect(redirectURL);
			}
			catch (IOException e) {
				_log.error(e.getMessage(), e);
			}

			return;
		}

		// Cache MarkupContext in session so that render does not need to
		// make an additional remote invocation

		MarkupContext markupContext = updateResponse.getMarkupContext();

		if (markupContext != null) {
			processMarkupContext(req, markupContext);
		}

		PortletContext portletContext = updateResponse.getPortletContext();

		if (portletContext != null) {
			processPortletContext(req, portletContext);
		}

		// Pass navState to next getMarkup by using the render params

		String navState =
			updateResponse.getNavigationalContext().getOpaqueValue();

		if (Validator.isNotNull(navState)) {
			res.setRenderParameter(WSRPSpecKeys.NAVIGATIONAL_STATE, navState);
		}

		String mode = updateResponse.getNewMode();

		if (Validator.isNotNull(mode)) {
			try {
				res.setPortletMode(WSRPMode.fromWSRP(mode).getPortlet());
			}
			catch (PortletModeException e) {
				_log.error(e.getMessage(), e);
			}
		}

		String windowState = updateResponse.getNewWindowState();

		if (Validator.isNotNull(windowState)) {
			try {
				res.setWindowState(
					WSRPState.fromWSRP(windowState).getPortlet());
			}
			catch (WindowStateException e) {
				_log.error(e.getMessage(), e);
			}
		}

		processSessionContext(req, updateResponse.getSessionContext());
	}

	protected void processMarkupContext(
		ActionRequest req, MarkupContext markupContext) {

		PortletSession ses = req.getPortletSession();

		ses.setAttribute("markupContext", markupContext);
	}

	protected void processMarkupContext(
		RenderRequest req, RenderResponse res, MarkupContext markupContext) {

		String title = markupContext.getPreferredTitle();

		if (Validator.isNotNull(title)) {
			res.setTitle(title);
		}

		String markup = markupContext.getItemString();

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

	protected void processMarkupResponse(
		RenderRequest req, RenderResponse res, MarkupResponse markupResponse) {

		MarkupContext markupContext = markupResponse.getMarkupContext();

		if (markupContext != null) {
			processMarkupContext(req, res, markupContext);
		}

		// Subsequent getMarkup request need to include this session context

		processSessionContext(req, markupResponse.getSessionContext());
	}

	protected void processPortletContext(
		ActionRequest req, PortletContext portletContext) {

		PortletPreferences prefs = req.getPreferences();

		String portletHandle = prefs.getValue(
			"portletHandle", StringPool.BLANK);

		// Producer cloned this portlet

		String newPortletHandle = portletContext.getPortletHandle();

		if (!portletHandle.equals(newPortletHandle)) {
			try {
				prefs.setValue(newPortletHandle, portletHandle);
			}
			catch (ReadOnlyException e) {
				_log.error(e.getMessage(), e);
			}
		}

		PortletSession ses = req.getPortletSession();

		ses.setAttribute("portletState", portletContext.getPortletState());
	}

	protected void processSessionContext(
		PortletRequest req, SessionContext sessionContext) {

		if (sessionContext != null) {
			PortletSession ses = req.getPortletSession();

			ses.setAttribute("sessionContext", sessionContext);
		}
	}

	private static Log _log = LogFactory.getLog(ConsumerPortlet.class);

}