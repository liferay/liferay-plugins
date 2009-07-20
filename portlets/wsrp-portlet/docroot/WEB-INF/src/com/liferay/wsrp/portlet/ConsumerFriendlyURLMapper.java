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

package com.liferay.wsrp.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="ConsumerFriendlyURLMapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ConsumerFriendlyURLMapper extends BaseFriendlyURLMapper {

	public String buildPath(LiferayPortletURL portletURL) {
		StringBuilder sb = new StringBuilder();

		sb.append("/consumer");

		addPathElement(sb, portletURL.getPortletId());

		portletURL.addParameterIncludedInPath("p_p_id");

		WindowState windowState = portletURL.getWindowState();

		if (windowState != null) {
			addPathElement(sb, windowState.toString());
		}
		else {
			addPathElement(sb, null);
		}

		portletURL.addParameterIncludedInPath("p_p_state");

		PortletMode portletMode = portletURL.getPortletMode();

		if (portletMode != null) {
			addPathElement(sb, portletMode.toString());
		}
		else {
			addPathElement(sb, null);
		}

		portletURL.addParameterIncludedInPath("p_p_mode");

		addPathElement(sb, portletURL.getResourceID());

		portletURL.addParameterIncludedInPath("p_p_resource_id");

		addPathElement(sb, portletURL.getCacheability());

		portletURL.addParameterIncludedInPath("p_p_cacheability");

		Map<String, String[]> parameterMap = portletURL.getParameterMap();

		String[] navigationalState = parameterMap.get("wsrp-navigationalState");

		if ((navigationalState == null) || (navigationalState.length <= 0)) {
			navigationalState = new String[] {null};
		}

		addPathElement(sb, navigationalState[0]);

		portletURL.addParameterIncludedInPath("wsrp-navigationalState");

		return sb.toString();
	}

	public String getMapping() {
		return _MAPPING;
	}

	public String getPortletId() {
		return null;
	}

	public void populateParams(
		String friendlyURLPath, Map<String, String[]> params) {

		int pos1 = friendlyURLPath.indexOf("/", 1);
		int pos2 = friendlyURLPath.indexOf("/", pos1 + 1);
		int pos3 = friendlyURLPath.indexOf("/", pos2 + 1);
		int pos4 = friendlyURLPath.indexOf("/", pos3 + 1);
		int pos5 = friendlyURLPath.indexOf("/", pos4 + 1);
		int pos6 = friendlyURLPath.indexOf("/", pos5 + 1);

		String portletId = friendlyURLPath.substring(pos1 + 1, pos2);

		addParam(params, "p_p_id", portletId);
		addParam(
			params, "p_p_state", friendlyURLPath.substring(pos2 + 1, pos3));
		addParam(params, "p_p_mode", friendlyURLPath.substring(pos3 + 1, pos4));
		addParam(
			params, "p_p_resource_id",
			friendlyURLPath.substring(pos4 + 1, pos5));
		addParam(
			params, "p_p_cacheability",
			friendlyURLPath.substring(pos5 + 1, pos6));

		String name =
			PortalUtil.getPortletNamespace(portletId) +
				"wsrp-navigationalState";

		addParam(params, name, friendlyURLPath.substring(pos6 + 1));
	}

	protected void addPathElement(StringBuilder sb, String value) {
		sb.append(StringPool.SLASH);
		sb.append(GetterUtil.get(HttpUtil.encodeURL(value), StringPool.DASH));
	}

	protected void addParam(
		Map<String, String[]> params, String name, String value) {

		if (value.equals(StringPool.DASH)) {
			return;
		}

		try {
			params.put(name, new String[] {value});
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final String _MAPPING = "consumer";

	private static Log _log = LogFactoryUtil.getLog(
		ConsumerFriendlyURLMapper.class);

}