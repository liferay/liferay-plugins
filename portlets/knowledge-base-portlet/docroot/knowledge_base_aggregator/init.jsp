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
String portletResource = ParamUtil.getString(request, "portletResource");

PortletPreferences prefs = renderRequest.getPreferences();

if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

long groupId = GetterUtil.getLong(prefs.getValue("group-id", "0"));
String displayStyle = prefs.getValue("display-style", KnowledgeBaseAggregatorUtil.DISPLAY_STYLE_ABSTRACT_AND_IMAGE);
int maxItems = GetterUtil.getInteger(prefs.getValue("max-items", String.valueOf(KnowledgeBaseAggregatorUtil.MAX_ITEMS_20)));

String[] rssTypes = prefs.getValues("rss-types", new String[] {RSSUtil.RSS_2_0});
int rssMaxItems = GetterUtil.getInteger(prefs.getValue("rss-max-items", String.valueOf(RSSUtil.MAX_ITEMS_20)));
String rssDisplayStyle = prefs.getValue("rss-display-style", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
int rssAbstractLength = GetterUtil.getInteger(prefs.getValue("rss-abstract-length", String.valueOf(RSSUtil.ABSTRACT_LENGTH_200)));
%>