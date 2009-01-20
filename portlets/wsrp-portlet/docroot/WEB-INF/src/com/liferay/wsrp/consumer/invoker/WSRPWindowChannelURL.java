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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.wsrp.consumer.invoker;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portlet.PortletURLFactoryUtil;

import com.sun.portal.container.ChannelMode;
import com.sun.portal.container.ChannelState;
import com.sun.portal.container.ChannelURL;
import com.sun.portal.container.ChannelURLType;
import com.sun.portal.portletcontainer.appengine.PortletAppEngineUtils;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSecurityException;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="WSRPWindowChannelURL.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 *
 */
public class WSRPWindowChannelURL implements ChannelURL, Serializable {

	public final static String NEW_CHANNEL_MODE = "dt.window.newChannelMode";

	public final static String NEW_WINDOW_STATE = "dt.window.newWindowState";

	public final static String PORTLET_ACTION = "p_p_url_type";

	public final static String RESOURCE_CACHE_LEVEL =
		"wsrp-resourceCacheability";

	public final static String RESOURCE_ID = "wsrp-resourceID";

	public final static String RESOURCE_STATE = "wsrp-resourceState";

	public WSRPWindowChannelURL(
		HttpServletRequest request, Portlet portlet, ChannelState windowState,
		ChannelMode portletMode, long plid) {

		_portletURL = PortletURLFactoryUtil.create(
			request, portlet.getPortletId(), plid, PortletRequest.RENDER_PHASE);

		setWindowState(windowState);
		setChannelMode(portletMode);
	}

	public void addProperty(String name, String value) {
	}

	public String getCacheLevel() {
		return _portletURL.getCacheability();
	}

	public ChannelMode getChannelMode() {
		return PortletAppEngineUtils.getChannelMode(
			_portletURL.getPortletMode());
	}

	public Map<String, String[]> getParameters() {
		return _portletURL.getParameterMap();
	}

	public Map<String, List<String>> getProperties() {
		return Collections.EMPTY_MAP;
	}

	public ChannelURLType getURLType() {
		return _urlType;
	}

	public ChannelState getWindowState() {
		return PortletAppEngineUtils.getChannelState(
			_portletURL.getWindowState());
	}

	public boolean isSecure() {
		return _portletURL.isSecure();
	}

	public void setCacheLevel(String cacheLevel) {
		_portletURL.setCacheability(cacheLevel);
	}

	public void setChannelMode(ChannelMode portletMode) {
		try {
			_portletURL.setPortletMode(
				PortletAppEngineUtils.getPortletMode(portletMode));
		}
		catch (PortletModeException pme) {
			_log.error(pme, pme);
		}
	}

	public void setParameter(String name, String value) {
		_portletURL.setParameter(name, value);
	}

	public void setParameter(String name, String[] values) {
		_portletURL.setParameter(name, values);
	}

	public void setParameters(Map<String, String[]> parametersMap) {
		_portletURL.setParameters(parametersMap);
	}

	public void setProperty(String name, String value) {
	}

	public void setResourceID(String resourceID) {
		_portletURL.setResourceID(resourceID);
	}

	public void setSecure(boolean secure) {
		try {
			_portletURL.setSecure(secure);
		}
		catch (PortletSecurityException pe) {
			_log.error(pe, pe);
		}
	}

	public void setURLType(ChannelURLType urlType) {
		_urlType = urlType;

		_portletURL.setLifecycle(getLifecycle());
		_portletURL.setURLType(getURLType());
	}

	public void setWindowState(ChannelState windowState) {
		try {
			if (windowState != null) {
				_portletURL.setWindowState(
					PortletAppEngineUtils.getWindowState(windowState));
			}
		}
		catch (WindowStateException wse) {
			_log.error(wse, wse);
		}
	}

	public String toString() {
		return _portletURL.toString();
	}

	protected String getLifecycle() {
		if (ChannelURLType.ACTION.equals(getURLType())) {
			return PortletRequest.ACTION_PHASE;
		}
		else if (ChannelURLType.RENDER.equals(getURLType())) {

			// Force the portal to call executeAction

			return PortletRequest.ACTION_PHASE;
		}
		else if (ChannelURLType.RESOURCE.equals(getURLType())) {
			return PortletRequest.RESOURCE_PHASE;
		}
		else {
			return PortletRequest.RENDER_PHASE;
		}
	}

	protected String getTemplate() {
		StringBuffer sb = new StringBuffer();

		sb.append(_portletURL.toString());

		sb.append(StringPool.AMPERSAND);
		sb.append(PORTLET_ACTION);
		sb.append(StringPool.EQUAL);
		sb.append("{wsrp-urlType}");

		sb.append(StringPool.AMPERSAND);
		sb.append(NEW_WINDOW_STATE);
		sb.append(StringPool.EQUAL);
		sb.append("{wsrp-windowState}");

		sb.append(StringPool.AMPERSAND);
		sb.append(NEW_CHANNEL_MODE);
		sb.append(StringPool.EQUAL);
		sb.append("{wsrp-mode}");

		sb.append("&wsrp-navigationalState={wsrp-navigationalState}");

		if (ChannelURLType.ACTION.equals(_urlType)) {
			sb.append("&wsrp-interactionState={wsrp-interactionState}");
		}
		else if (ChannelURLType.RESOURCE.equals(_urlType)) {
			sb.append(StringPool.AMPERSAND);
			sb.append(RESOURCE_ID);
			sb.append(StringPool.EQUAL);
			sb.append("{wsrp-resourceID}");

			sb.append(StringPool.AMPERSAND);
			sb.append(RESOURCE_STATE);
			sb.append(StringPool.EQUAL);
			sb.append("{wsrp-resourceState}");

			sb.append(StringPool.AMPERSAND);
			sb.append(RESOURCE_CACHE_LEVEL);
			sb.append(StringPool.EQUAL);
			sb.append("{wsrp-resourceCacheability}");
		}

		return sb.toString();
	}

	private static Log _log = LogFactory.getLog(WSRPWindowChannelURL.class);

	private LiferayPortletURL _portletURL;
	private ChannelURLType _urlType;

}