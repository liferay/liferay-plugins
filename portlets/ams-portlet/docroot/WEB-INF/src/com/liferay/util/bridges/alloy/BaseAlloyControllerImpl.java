/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.util.PortalUtil;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * <a href="BaseAlloyControllerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class BaseAlloyControllerImpl implements AlloyController {

	public void afterPropertiesSet() {
		initClass();
		initServletVariables();
		initPortletVariables();
		initMethods();
		initPaths();
	}

	public void execute() throws Exception {
		Method method = getMethod(actionPath);

		if (method == null) {
			if (log.isDebugEnabled()) {
				log.debug("No method found for action " + actionPath);
			}
		}

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			executeAction(method);
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			executeRender(method);
		}
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	protected void addSuccessMessage() {
		String successMessage = ParamUtil.getString(
			portletRequest, "successMessage");

		SessionMessages.add(
			portletRequest, "request_processed", successMessage);
	}

	protected String buildIncludePath(String viewPath) {
		StringBundler sb = new StringBundler(5);

		sb.append("/WEB-INF/jsp/views/");
		sb.append(controllerPath);
		sb.append(StringPool.SLASH);
		sb.append(viewPath);
		sb.append(".jsp");

		return sb.toString();
	}

	protected void executeAction(Method method) throws Exception {
		if (method != null) {
			method.invoke(this);
		}

		actionRequest.setAttribute(
			CALLED_PROCESS_ACTION, Boolean.TRUE.toString());

		if (Validator.isNotNull(viewPath)) {
			actionRequest.setAttribute(VIEW_PATH, viewPath);

			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
		}
		else if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	protected void executeRender(Method method) throws Exception {
		boolean calledProcessAction = GetterUtil.getBoolean(
			(String)request.getAttribute(CALLED_PROCESS_ACTION));

		if (!calledProcessAction) {
			if (method != null) {
				method.invoke(this);
			}
		}

		if (Validator.isNull(viewPath)) {
			viewPath = actionPath;
		}

		String includePath = buildIncludePath(viewPath);

		PortletRequestDispatcher portletRequestDispatcher =
			portletContext.getRequestDispatcher(includePath);

		if (portletRequestDispatcher == null) {
			log.error(includePath + " is not a valid include");
		}
		else {
			portletRequestDispatcher.include(
				portletRequest, portletResponse);
		}
	}

	protected Method getMethod(String methodName, Class<?>... parameterTypes) {
		String methodKey = getMethodKey(methodName, parameterTypes);

		return methodsMap.get(methodKey);
	}

	protected String getMethodKey(
		String methodName, Class<?>... parameterTypes) {

		StringBundler sb = new StringBundler(parameterTypes.length * 2 + 2);

		sb.append(methodName);
		sb.append(StringPool.POUND);

		for (Class<?> parameterType : parameterTypes) {
			sb.append(parameterType.getName());
			sb.append(StringPool.POUND);
		}

		return sb.toString();
	}

	protected void initClass() {
		classObject = getClass();
		classLoader = classObject.getClassLoader();
	}

	protected void initMethods() {
		methodsMap = new HashMap<String, Method>();

		Method[] methods = classObject.getMethods();

		for (Method method : methods) {
			String methodKey = getMethodKey(
				method.getName(), method.getParameterTypes());

			methodsMap.put(methodKey, method);
		}
	}

	protected void initPaths() {
		controllerPath = ParamUtil.getString(request, "controller");

		if (Validator.isNull(controllerPath)) {
			Map<String, String> defaultRouteParameters =
				alloyPortlet.getDefaultRouteParameters();

			controllerPath = defaultRouteParameters.get("controller");
		}

		if (log.isDebugEnabled()) {
			log.debug("Controller path " + controllerPath);
		}

		actionPath = ParamUtil.getString(request, "action");

		if (Validator.isNull(actionPath)) {
			Map<String, String> defaultRouteParameters =
				alloyPortlet.getDefaultRouteParameters();

			actionPath = defaultRouteParameters.get("action");
		}

		if (log.isDebugEnabled()) {
			log.debug("Action path " + actionPath);
		}

		viewPath = GetterUtil.getString(
			(String)request.getAttribute(VIEW_PATH));

		request.removeAttribute(VIEW_PATH);

		if (log.isDebugEnabled()) {
			log.debug("View path " + viewPath);
		}
	}

	protected void initPortletVariables() {
		liferayPortletConfig = (LiferayPortletConfig)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		portletContext = liferayPortletConfig.getPortletContext();

		portlet = liferayPortletConfig.getPortlet();

		alloyPortlet = (AlloyPortlet)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_PORTLET);

		portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
		portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		liferayPortletResponse = (LiferayPortletResponse)portletResponse;

		lifecycle = GetterUtil.getString(
			(String)request.getAttribute(PortletRequest.LIFECYCLE_PHASE));

		if (log.isDebugEnabled()) {
			log.debug("Lifecycle " + lifecycle);
		}

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			actionRequest = (ActionRequest)portletRequest;
			actionResponse = (ActionResponse)portletResponse;
		}
		else if (lifecycle.equals(PortletRequest.EVENT_PHASE)) {
			eventRequest = (EventRequest)portletRequest;
			eventResponse = (EventResponse)portletResponse;
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			renderRequest = (RenderRequest)portletRequest;
			renderResponse = (RenderResponse)portletResponse;
		}
		else if (lifecycle.equals(PortletRequest.RESOURCE_PHASE)) {
			resourceRequest = (ResourceRequest)portletRequest;
			resourceResponse = (ResourceResponse)portletResponse;
		}
	}

	protected void initServletVariables() {
		servletConfig = pageContext.getServletConfig();
		servletContext = pageContext.getServletContext();
		request = (HttpServletRequest)pageContext.getRequest();
		response = (HttpServletResponse)pageContext.getResponse();
	}

	protected void redirectTo(PortletURL portletURL) {
		redirectTo(portletURL.toString());
	}

	protected void redirectTo(String redirect) {
		if (!lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			throw new IllegalArgumentException(
				"redirectTo can only be called during the action phase");
		}

		if (Validator.isNotNull(viewPath)) {
			throw new IllegalArgumentException(
				"redirectTo cannot be called if render has been called");
		}

		this.redirect = redirect;
	}

	protected void render(String actionPath) {
		if (Validator.isNotNull(redirect)) {
			throw new IllegalArgumentException(
				"render cannot be called if redirectTo has been called");
		}

		viewPath = actionPath;
	}

	protected static final String CALLED_PROCESS_ACTION =
		"CALLED_PROCESS_ACTION";

	protected static final String VIEW_PATH = "VIEW_PATH";

	protected static Log log = LogFactoryUtil.getLog(
		BaseAlloyControllerImpl.class);

	protected String actionPath;
	protected ActionRequest actionRequest;
	protected ActionResponse actionResponse;
	protected AlloyPortlet alloyPortlet;
	protected ClassLoader classLoader;
	protected Class<?> classObject;
	protected String controllerPath;
	protected EventRequest eventRequest;
	protected EventResponse eventResponse;
	protected String lifecycle;
	protected LiferayPortletConfig liferayPortletConfig;
	protected LiferayPortletResponse liferayPortletResponse;
	protected Map<String, Method> methodsMap;
	protected PageContext pageContext;
	protected Portlet portlet;
	protected PortletContext portletContext;
	protected PortletRequest portletRequest;
	protected PortletResponse portletResponse;
	protected String redirect;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;
	protected HttpServletRequest request;
	protected ResourceRequest resourceRequest;
	protected ResourceResponse resourceResponse;
	protected HttpServletResponse response;
	protected ServletConfig servletConfig;
	protected ServletContext servletContext;
	protected String viewPath;

}