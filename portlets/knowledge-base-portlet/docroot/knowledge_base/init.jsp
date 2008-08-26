<%
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
%>

<%@ include file="/init.jsp" %>

<%
PortletPreferences prefs = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String layoutFriendlyURL = PortalUtil.getLayoutFriendlyURL(layout, themeDisplay);

String atom = "Atom 1.0";
String rss10 = "RSS 1.0";
String rss20 = "RSS 2.0";

int rssDelta = GetterUtil.getInteger(prefs.getValue("rss-delta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = prefs.getValue("rss-display-style", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
int abstractLength = GetterUtil.getInteger(prefs.getValue("abstract-length", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);

ResourceURL rssAtomURL = _getRSSURL(renderResponse, rssDelta, rssDisplayStyle, abstractLength);
rssAtomURL.setParameter("type", RSSUtil.ATOM);
rssAtomURL.setParameter("version", "1.0");

ResourceURL rssRSS10URL = _getRSSURL(renderResponse, rssDelta, rssDisplayStyle, abstractLength);
rssRSS10URL.setParameter("type", RSSUtil.RSS);
rssRSS10URL.setParameter("version", "1.0");

ResourceURL rssRSS20URL = _getRSSURL(renderResponse, rssDelta, rssDisplayStyle, abstractLength);
rssRSS20URL.setParameter("type", RSSUtil.RSS);
rssRSS20URL.setParameter("version", "2.0");
%>

<%!
	private ResourceURL _getRSSURL(RenderResponse renderResponse, int rssDelta, String rssDisplayStyle, int abstractLength) {
		ResourceURL rssURL = renderResponse.createResourceURL();
		rssURL.setParameter("actionName", "rss");

		if (rssDelta != SearchContainer.DEFAULT_DELTA) {
			rssURL.setParameter("max", String.valueOf(rssDelta));
		}

		if (!rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) {
			rssURL.setParameter("displayStyle", rssDisplayStyle);
		}

		if (abstractLength != SearchContainer.DEFAULT_DELTA) {
			rssURL.setParameter("abstractLength", String.valueOf(abstractLength));
		}

		return rssURL;
	}
%>