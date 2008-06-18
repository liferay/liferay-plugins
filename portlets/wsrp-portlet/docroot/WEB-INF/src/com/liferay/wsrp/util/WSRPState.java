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

import javax.portlet.WindowState;

import com.sun.portal.wsrp.common.WSRPSpecKeys;

/**
 * <a href="WSRPWindowState.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public enum WSRPState {
	MAXIMIZED(WSRPSpecKeys.WINDOW_STATE_MAXIMIZED, WindowState.MAXIMIZED),
	MINIMIZED(WSRPSpecKeys.WINDOW_STATE_MINIMIZED, WindowState.MINIMIZED),
	NORMAL(WSRPSpecKeys.WINDOW_STATE_NORMAL, WindowState.NORMAL);
	
	private WSRPState(String wsrpWindowState, WindowState windowState) {
		_wsrpState = wsrpWindowState;
		_windowState = windowState;
	}
	
	public String getWSRP() {
		return _wsrpState;
	}
	
	public WindowState getPortlet() {
		return _windowState;
	}

	public static WSRPState fromWSRP(String state) {
		return _wsrpLookup.get(state);
	}
	
	public static WSRPState fromPortlet(WindowState state) {
		return _portletLookup.get(state);
	}
	
	public static WSRPState fromPortlet(String windowState) {
		return _portletStringLookup.get(windowState);
	}

	private static Map<String, WSRPState> _wsrpLookup;
	private static Map<WindowState, WSRPState> _portletLookup;
	private static Map<String, WSRPState> _portletStringLookup;

	static {
		_wsrpLookup = new HashMap<String, WSRPState>();
		_portletLookup = new HashMap<WindowState, WSRPState>();
		_portletStringLookup = new HashMap<String, WSRPState>();

		for (WSRPState state : 
			EnumSet.allOf(WSRPState.class)) {

			_wsrpLookup.put(state.getWSRP(), state);
			_portletLookup.put(state.getPortlet(), state);
			_portletStringLookup.put(state.getPortlet().toString(), state);
		}
	}
	
	private String _wsrpState;
	private WindowState _windowState;
	
}
