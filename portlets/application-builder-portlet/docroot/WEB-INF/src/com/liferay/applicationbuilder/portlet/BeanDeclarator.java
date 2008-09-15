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

package com.liferay.applicationbuilder.portlet;

import java.util.Map;

import javax.portlet.*;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * <a href="ScriptingPortletBeanDeclarator.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Alberto Montero
 *
 */
public class BeanDeclarator {

	public static void declareBSFBeans(
			BSFManager bsfManager, PortletConfig portletConfig,
			PortletContext portletContext, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws BSFException {

		PortletPreferences preferences = portletRequest.getPreferences();
		Map<String, String> userInfo =
			(Map<String, String>)portletRequest.getAttribute(
				PortletRequest.USER_INFO);

		bsfManager.declareBean(
			"portletConfig", portletConfig, PortletConfig.class);
		bsfManager.declareBean(
			"portletContext", portletContext, PortletContext.class);
		bsfManager.declareBean(
			"portletRequest", portletRequest, PortletRequest.class);
		bsfManager.declareBean(
			"portletResponse", portletResponse, PortletResponse.class);
		bsfManager.declareBean(
			"preferences", preferences, PortletPreferences.class);
		bsfManager.declareBean("userInfo", userInfo, Map.class);
	}

	public static void declareRhinoBeans(
			Scriptable scope, PortletConfig portletConfig,
			PortletContext portletContext, PortletRequest portletRequest,
			PortletResponse portletResponse) {

		PortletPreferences preferences = portletRequest.getPreferences();
		Map<String, String> userInfo =
				(Map<String, String>)portletRequest.getAttribute(
				PortletRequest.USER_INFO);

		Object out = Context.javaToJS(System.out, scope);

		ScriptableObject.putProperty(scope, "out", out);

		ScriptableObject.putProperty(
			scope, "portletConfig", Context.javaToJS(portletConfig, scope));
		ScriptableObject.putProperty(
			scope, "portletContext",
			Context.javaToJS(portletContext, scope));
		ScriptableObject.putProperty(
			scope, "portletRequest", Context.javaToJS(portletRequest, scope));
		ScriptableObject.putProperty(
			scope, "portletResponse", Context.javaToJS(portletResponse, scope));
		ScriptableObject.putProperty(
			scope, "preferences", Context.javaToJS(preferences, scope));
		ScriptableObject.putProperty(
			scope, "userInfo", Context.javaToJS(userInfo, scope));
	}

}