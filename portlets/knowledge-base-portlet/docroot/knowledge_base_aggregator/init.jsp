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

PortletPreferences preferences = renderRequest.getPreferences();

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

long[] groupIds = GetterUtil.getLongValues(preferences.getValues("group-ids", new String[] {"0"}));
String displayStyle = preferences.getValue("display-style", KnowledgeBaseAggregatorUtil.DISPLAY_STYLE_ABSTRACT_AND_IMAGE);
int maxItems = GetterUtil.getInteger(preferences.getValue("max-items", String.valueOf(KnowledgeBaseAggregatorUtil.MAX_ITEMS_20)));

String[] rssTypes = preferences.getValues("rss-types", new String[] {RSSUtil.RSS_2_0});
int rssMaxItems = GetterUtil.getInteger(preferences.getValue("rss-max-items", String.valueOf(RSSUtil.MAX_ITEMS_20)));
String rssDisplayStyle = preferences.getValue("rss-display-style", RSSUtil.DISPLAY_STYLE_FULL_CONTENT);
int rssAbstractLength = GetterUtil.getInteger(preferences.getValue("rss-abstract-length", String.valueOf(RSSUtil.ABSTRACT_LENGTH_200)));

// Portlet URLs

ResourceURL atom10URL = renderResponse.createResourceURL();
ResourceURL rss10URL = renderResponse.createResourceURL();
ResourceURL rss20URL = renderResponse.createResourceURL();

atom10URL.setParameter("actionName", "rss");
rss10URL.setParameter("actionName", "rss");
rss20URL.setParameter("actionName", "rss");

atom10URL.setParameter("groupIds", String.valueOf(groupIds));
rss10URL.setParameter("groupIds", String.valueOf(groupIds));
rss20URL.setParameter("groupIds", String.valueOf(groupIds));

atom10URL.setParameter("rssAbstractLength", String.valueOf(rssAbstractLength));
rss10URL.setParameter("rssAbstractLength", String.valueOf(rssAbstractLength));
rss20URL.setParameter("rssAbstractLength", String.valueOf(rssAbstractLength));

atom10URL.setParameter("rssDisplayStyle", rssDisplayStyle);
rss10URL.setParameter("rssDisplayStyle", rssDisplayStyle);
rss20URL.setParameter("rssDisplayStyle", rssDisplayStyle);

atom10URL.setParameter("rssMaxItems", String.valueOf(rssMaxItems));
rss10URL.setParameter("rssMaxItems", String.valueOf(rssMaxItems));
rss20URL.setParameter("rssMaxItems", String.valueOf(rssMaxItems));

atom10URL.setParameter("rssType", RSSUtil.ATOM);
atom10URL.setParameter("rssVersion", String.valueOf(RSSUtil.VERSION_1_0));

rss10URL.setParameter("rssType", RSSUtil.RSS);
rss10URL.setParameter("rssVersion", String.valueOf(RSSUtil.VERSION_1_0));

rss20URL.setParameter("rssType", RSSUtil.RSS);
rss20URL.setParameter("rssVersion", String.valueOf(RSSUtil.VERSION_2_0));
%>