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

package com.liferay.compat.util.bridges.mvc;

import com.liferay.compat.portal.kernel.portlet.DynamicActionRequest;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;

/**
 * @author Shinn Lok
 * @author Peter Shin
 */
public class MVCPortlet extends com.liferay.util.bridges.mvc.MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		DynamicActionRequest dynamicActionRequest = new DynamicActionRequest(
			actionRequest);

		if (copyRequestParameters &&
			(dynamicActionRequest.getUploadPortletRequest() != null)) {

			PortalUtil.copyRequestParameters(
				dynamicActionRequest.getUploadPortletRequest(),
				dynamicActionRequest);
		}

		super.processAction(dynamicActionRequest, actionResponse);

		if (copyRequestParameters &&
			(dynamicActionRequest.getUploadPortletRequest() != null) &&
			!SessionErrors.isEmpty(dynamicActionRequest)) {

			PortalUtil.copyRequestParameters(
				dynamicActionRequest, actionResponse);
		}
	}

	@Override
	protected boolean callActionMethod(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			checkPermissions(actionRequest);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		try {
			return super.callActionMethod(actionRequest, actionResponse);
		}
		catch (PortletException pe) {
			return _callActionMethod(actionRequest, actionResponse, pe);
		}
	}

	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {
	}

	private boolean _callActionMethod(
			ActionRequest actionRequest, ActionResponse actionResponse,
			PortletException portletException)
		throws PortletException {

		Throwable throwable = portletException.getCause();

		if (!(throwable instanceof NoSuchMethodException)) {
			throw portletException;
		}

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		try {
			Method method = _getActionMethod(actionName);

			method.invoke(this, actionRequest, actionResponse);

			return true;
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
			throw portletException;
		}
	}

	private Method _getActionMethod(String actionName)
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

	private Map<String, Method> _actionMethods =
		new ConcurrentHashMap<String, Method>();

}