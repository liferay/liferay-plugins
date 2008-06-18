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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletMode;

import com.sun.portal.wsrp.common.WSRPSpecKeys;

/**
 * <a href="WSRPMode.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public enum WSRPMode {
	EDIT(WSRPSpecKeys.MODE_EDIT, PortletMode.EDIT),
	HELP(WSRPSpecKeys.MODE_HELP, PortletMode.HELP),
	VIEW(WSRPSpecKeys.MODE_VIEW, PortletMode.VIEW);
	
	private WSRPMode(
		String wsrpMode, PortletMode portletMode) {

		_wsrpMode = wsrpMode;
		_portletMode = portletMode;
	}
	
	public String getWSRP() {
		return _wsrpMode;
	}
		
	public PortletMode getPortlet() {
		return _portletMode;
	}
	
	public static WSRPMode fromWSRP(String mode) {
		return _wsrpLookup.get(mode);
	}
	
	public static WSRPMode fromPortlet(PortletMode mode) {
		return _portletLookup.get(mode);
	}
	
	public static WSRPMode fromPortlet(String mode) {
		return _portletStringLookup.get(mode);
	}

	private static Map<String, WSRPMode> _wsrpLookup;
	private static Map<PortletMode, WSRPMode> _portletLookup;
	private static Map<String, WSRPMode> _portletStringLookup;

	static {
		_wsrpLookup = new HashMap<String, WSRPMode>();
		_portletLookup = new HashMap<PortletMode, WSRPMode>();
		_portletStringLookup = new HashMap<String, WSRPMode>();
		
		for (WSRPMode mode : EnumSet.allOf(WSRPMode.class)) {
			_wsrpLookup.put(mode.getWSRP(), mode);
			_portletLookup.put(mode.getPortlet(), mode);
			_portletStringLookup.put(mode.getPortlet().toString(), mode);
		}
	}
	
	private String _wsrpMode;
	private PortletMode _portletMode;
}
