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

package com.liferay.alloy.mvc.jsonwebservice;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.alloy.mvc.BaseAlloyControllerImpl;
import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.lang.reflect.Constructor;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author Ethan Bustad
 */
public abstract class BaseAlloyControllerInvokerImpl
	implements AlloyControllerInvoker {

	@Override
	public JSONSerializable invokeAlloyController(
			String lifecycle, Object... parameters)
		throws Exception {

		Constructor<? extends AlloyController> constructor =
			_controllerClass.getConstructor();

		AlloyController alloyController = constructor.newInstance();

		HttpServletRequest request = createRequest(lifecycle, parameters);
		HttpServletResponse response = createResponse();

		PageContext pageContext = createPageContext(request, response);

		alloyController.setPageContext(pageContext);

		alloyController.afterPropertiesSet();

		alloyController.execute();

		return getJSONSerializable(alloyController.getResponseContent());
	}

	@Override
	public void setProperties(
		ThemeDisplay themeDisplay, AlloyPortlet alloyPortlet, Portlet portlet,
		String controller, Class<? extends AlloyController> controllerClass) {

		_alloyPortlet = alloyPortlet;
		_controller = controller;
		_controllerClass = controllerClass;
		_portlet = portlet;
		_themeDisplay = themeDisplay;
	}

	protected ActionRequest createActionRequest() {
		return new AlloyMockUtil.MockActionRequest();
	}

	protected ActionResponse createActionResponse() {
		return new AlloyMockUtil.MockActionResponse();
	}

	protected PageContext createPageContext(
		final ServletRequest request, final ServletResponse response) {

		return new AlloyMockUtil.MockPageContext() {

			@Override
			public ServletRequest getRequest() {
				return request;
			}

			@Override
			public ServletResponse getResponse() {
				return response;
			}

		};
	}

	protected RenderRequest createRenderRequest() {
		return new AlloyMockUtil.MockRenderRequest();
	}

	protected RenderResponse createRenderResponse(
		final HttpServletRequest request, final String portletId,
		final long plid, final String lifecycle) {

		return new AlloyMockUtil.MockRenderResponse() {

			@Override
			public PortletURL createRenderURL() {
				return PortletURLFactoryUtil.create(
					request, portletId, plid, lifecycle);
			}

			@Override
			public String getContentType() {
				return contentType;
			}

			@Override
			public void setContentType(String type) {
				contentType = type;
			}

			protected String contentType = null;

		};
	}

	protected DynamicServletRequest createRequest(
			String lifecycle, Object... parameters)
		throws Exception {

		if ((parameters.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Parameters length is not an even number");
		}

		HttpServletRequestWrapper requestWrapper =
			new HttpServletRequestWrapper(
				new AlloyMockUtil.MockHttpServletRequest());

		DynamicServletRequest request = new DynamicServletRequest(
			requestWrapper, false);

		for (int i = 0; i < parameters.length; i += 2) {
			request.appendParameter(
				String.valueOf(parameters[i]),
				String.valueOf(parameters[i + 1]));
		}

		request.appendParameter("controller", _controller);
		request.appendParameter("format", "json");

		ThemeDisplay themeDisplay = (ThemeDisplay)_themeDisplay.clone();

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = UserLocalServiceUtil.getUser(permissionChecker.getUserId());

		themeDisplay.setUser(user);

		request.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		request.setAttribute(WebKeys.LAYOUT, themeDisplay.getLayout());

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)PortletConfigFactoryUtil.create(
				_portlet, null);

		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG, liferayPortletConfig);

		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_PORTLET, _alloyPortlet);

		PortletRequest portletRequest = null;
		PortletResponse portletResponse = null;

		if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			portletRequest = createActionRequest();
			portletResponse = createActionResponse();
		}
		else {
			portletRequest = createRenderRequest();
			portletResponse = createRenderResponse(
				request, _portlet.getRootPortletId(), themeDisplay.getPlid(),
				lifecycle);
		}

		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST, portletRequest);
		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE, portletResponse);

		request.setAttribute(PortletRequest.LIFECYCLE_PHASE, lifecycle);

		request.setAttribute(
			BaseAlloyControllerImpl.VIEW_PATH, StringPool.BLANK);

		return request;
	}

	protected HttpServletResponse createResponse() {
		return new AlloyMockUtil.MockHttpServletResponse() {

			@Override
			public String getContentType() {
				return contentType;
			}

			@Override
			public void setContentType(String type) {
				contentType = type;
			}

			protected String contentType;

		};
	}

	protected JSONSerializable getJSONSerializable(final String content) {
		return new JSONSerializable() {

			@Override
			public String toJSONString() {
				return content;
			}

		};
	}

	private AlloyPortlet _alloyPortlet;
	private String _controller;
	private Class<? extends AlloyController> _controllerClass;
	private Portlet _portlet;
	private ThemeDisplay _themeDisplay;

}