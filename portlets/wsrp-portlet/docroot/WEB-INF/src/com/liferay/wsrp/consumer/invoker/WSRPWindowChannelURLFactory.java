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

import com.liferay.portal.model.Portlet;

import com.sun.portal.container.ChannelMode;
import com.sun.portal.container.ChannelState;
import com.sun.portal.container.ChannelURL;
import com.sun.portal.container.ChannelURLFactory;
import com.sun.portal.container.ChannelURLType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="WSRPWindowChannelURLFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Manish Gupta
 *
 */
public class WSRPWindowChannelURLFactory implements ChannelURLFactory {

	public WSRPWindowChannelURLFactory(
		HttpServletRequest request, Portlet portlet, ChannelState windowState,
		ChannelMode portletMode, long plid) {

		_request = request;
		_portlet = portlet;
		_windowState = windowState;
		_portletMode = portletMode;
		_plid = plid;
	}

	public ChannelURL createChannelURL() {
		return getWSRPWindowChannelURL();
	}

	public String encodeURL(
		HttpServletRequest request, HttpServletResponse response, String url) {

		return null;
	}

	public String getActionTemplate() {
		WSRPWindowChannelURL channelURL = getWSRPWindowChannelURL();

		channelURL.setURLType(ChannelURLType.ACTION);

		return channelURL.getTemplate();
	}

	public String getProviderDesktopURLPrefix() {
		return null;
	}

	public String getRenderTemplate() {
		WSRPWindowChannelURL channelURL = getWSRPWindowChannelURL();

		channelURL.setURLType(ChannelURLType.RENDER);

		return channelURL.getTemplate();
	}

	public String getResourceTemplate() {
		WSRPWindowChannelURL channelURL = getWSRPWindowChannelURL();

		channelURL.setURLType(ChannelURLType.RESOURCE);

		return channelURL.getTemplate();
	}

	public String getSecurityErrorURL() {
		return null;
	}

	protected WSRPWindowChannelURL getWSRPWindowChannelURL() {
		return new WSRPWindowChannelURL(
			_request, _portlet, _windowState, _portletMode, _plid);
	}

	private HttpServletRequest _request;
	private Portlet _portlet;
	private ChannelState _windowState;
	private ChannelMode _portletMode;
	private long _plid;

}