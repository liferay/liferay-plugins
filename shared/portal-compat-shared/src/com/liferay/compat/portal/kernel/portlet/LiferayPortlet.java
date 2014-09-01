/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.compat.portal.kernel.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.MimeResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayPortlet extends GenericPortlet {

	@Override
	public void init() throws PortletException {
		super.init();

		addProcessActionSuccessMessage = GetterUtil.getBoolean(
			getInitParameter("add-process-action-success-action"), true);
		alwaysSendRedirect = GetterUtil.getBoolean(
			getInitParameter("always-send-redirect"));
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			if (!isProcessActionRequest(actionRequest)) {
				return;
			}

			if (!callActionMethod(actionRequest, actionResponse)) {
				return;
			}

			if (!SessionErrors.isEmpty(actionRequest)) {
				return;
			}

			boolean emptySessionMessages = SessionMessages.isEmpty(
				actionRequest);

			if (emptySessionMessages) {
				addSuccessMessage(actionRequest, actionResponse);
			}

			if (emptySessionMessages || isAlwaysSendRedirect()) {
				sendRedirect(actionRequest, actionResponse);
			}
		}
		catch (PortletException pe) {
			Throwable cause = pe.getCause();

			if (isSessionErrorException(cause)) {
				SessionErrors.add(actionRequest, cause.getClass(), cause);
			}
			else {
				throw pe;
			}
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		if (!isProcessResourceRequest(resourceRequest)) {
			return;
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		if (!addProcessActionSuccessMessage) {
			return;
		}

		String successMessage = ParamUtil.getString(
			actionRequest, "successMessage");

		SessionMessages.add(actionRequest, "requestProcessed", successMessage);
	}

	protected boolean callActionMethod(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (Validator.isNull(actionName) ||
			actionName.equals("callActionMethod") ||
			actionName.equals("processAction")) {

			return false;
		}

		try {
			Method method = getActionMethod(actionName);

			method.invoke(this, actionRequest, actionResponse);

			return true;
		}
		catch (NoSuchMethodException nsme) {
			try {
				super.processAction(actionRequest, actionResponse);

				return true;
			}
			catch (Exception e) {
				throw new PortletException(nsme);
			}
		}
		catch (InvocationTargetException ite) {
			Throwable cause = ite.getCause();

			if (cause != null) {
				throw new PortletException(cause);
			}
			else {
				throw new PortletException(ite);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@SuppressWarnings("unused")
	protected void doAbout(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		throw new PortletException("doAbout method not implemented");
	}

	@SuppressWarnings("unused")
	protected void doConfig(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		throw new PortletException("doConfig method not implemented");
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (!isProcessRenderRequest(renderRequest)) {
			renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

			return;
		}

		WindowState windowState = renderRequest.getWindowState();

		if (windowState.equals(WindowState.MINIMIZED)) {
			return;
		}

		PortletMode portletMode = renderRequest.getPortletMode();

		if (portletMode.equals(PortletMode.VIEW)) {
			doView(renderRequest, renderResponse);
		}
		else if (portletMode.equals(LiferayPortletMode.ABOUT)) {
			doAbout(renderRequest, renderResponse);
		}
		else if (portletMode.equals(LiferayPortletMode.CONFIG)) {
			doConfig(renderRequest, renderResponse);
		}
		else if (portletMode.equals(PortletMode.EDIT)) {
			doEdit(renderRequest, renderResponse);
		}
		else if (portletMode.equals(LiferayPortletMode.EDIT_DEFAULTS)) {
			doEditDefaults(renderRequest, renderResponse);
		}
		else if (portletMode.equals(LiferayPortletMode.EDIT_GUEST)) {
			doEditGuest(renderRequest, renderResponse);
		}
		else if (portletMode.equals(PortletMode.HELP)) {
			doHelp(renderRequest, renderResponse);
		}
		else if (portletMode.equals(LiferayPortletMode.PREVIEW)) {
			doPreview(renderRequest, renderResponse);
		}
		else if (portletMode.equals(LiferayPortletMode.PRINT)) {
			doPrint(renderRequest, renderResponse);
		}
		else {
			throw new PortletException(portletMode.toString());
		}
	}

	@SuppressWarnings("unused")
	protected void doEditDefaults(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		throw new PortletException("doEditDefaults method not implemented");
	}

	@SuppressWarnings("unused")
	protected void doEditGuest(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		throw new PortletException("doEditGuest method not implemented");
	}

	@SuppressWarnings("unused")
	protected void doPreview(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		throw new PortletException("doPreview method not implemented");
	}

	@SuppressWarnings("unused")
	protected void doPrint(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		throw new PortletException("doPrint method not implemented");
	}

	protected Method getActionMethod(String actionName)
		throws NoSuchMethodException {

		Method method = _actionMethods.get(actionName);

		if (method != null) {
			return method;
		}

		Class<?> clazz = getClass();

		method = clazz.getMethod(
			actionName, ActionRequest.class, ActionResponse.class);

		_actionMethods.put(actionName, method);

		return method;
	}

	protected String getRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String redirect = (String)actionRequest.getAttribute(WebKeys.REDIRECT);

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(actionRequest, "redirect");
		}

		return redirect;
	}

	@Override
	protected String getTitle(RenderRequest renderRequest) {
		try {
			return PortalUtil.getPortletTitle(renderRequest);
		}
		catch (Exception e) {
			return super.getTitle(renderRequest);
		}
	}

	protected boolean isAlwaysSendRedirect() {
		return alwaysSendRedirect;
	}

	protected boolean isProcessActionRequest(ActionRequest actionRequest) {
		return isProcessPortletRequest(actionRequest);
	}

	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		return _PROCESS_PORTLET_REQUEST;
	}

	protected boolean isProcessRenderRequest(RenderRequest renderRequest) {
		return isProcessPortletRequest(renderRequest);
	}

	protected boolean isProcessResourceRequest(
		ResourceRequest resourceRequest) {

		return isProcessPortletRequest(resourceRequest);
	}

	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		if (cause instanceof PortalException) {
			return true;
		}

		return false;
	}

	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		String redirect = getRedirect(actionRequest, actionResponse);

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	protected String translate(PortletRequest portletRequest, String key) {
		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return LanguageUtil.get(portletConfig, themeDisplay.getLocale(), key);
	}

	protected String translate(
		PortletRequest portletRequest, String key, Object argument) {

		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return LanguageUtil.format(
			portletConfig, themeDisplay.getLocale(), key, argument);
	}

	protected String translate(
		PortletRequest portletRequest, String key, Object[] arguments) {

		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return LanguageUtil.format(
			portletConfig, themeDisplay.getLocale(), key, arguments);
	}

	protected void writeJSON(
			PortletRequest portletRequest, ActionResponse actionResponse,
			Object json)
		throws IOException {

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		response.setContentType(ContentTypes.APPLICATION_JSON);

		ServletResponseUtil.write(response, json.toString());

		response.flushBuffer();
	}

	protected void writeJSON(
			PortletRequest portletRequest, MimeResponse mimeResponse,
			Object json)
		throws IOException {

		mimeResponse.setContentType(ContentTypes.APPLICATION_JSON);

		PortletResponseUtil.write(mimeResponse, json.toString());

		mimeResponse.flushBuffer();
	}

	protected boolean addProcessActionSuccessMessage;
	protected boolean alwaysSendRedirect;

	private static final boolean _PROCESS_PORTLET_REQUEST = true;

	private static Log _log = LogFactoryUtil.getLog(LiferayPortlet.class);

	private Map<String, Method> _actionMethods =
		new ConcurrentHashMap<String, Method>();

}