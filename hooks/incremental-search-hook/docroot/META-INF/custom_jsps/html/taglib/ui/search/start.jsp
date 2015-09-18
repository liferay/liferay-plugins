<%--
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
--%>

<%@ include file="/html/taglib/ui/search/init.jsp" %>

<div class="yui3-skin-sam" id="<%= randomNamespace %><%= namespace %>incrementalSearchContainer">

	<%
	long groupId = ParamUtil.getLong(request, namespace + "groupId");

	Group group = themeDisplay.getScopeGroup();

	String keywords = ParamUtil.getString(request, namespace + "keywords");

	PortletURL portletURL = null;

	if (portletResponse != null) {
		LiferayPortletResponse liferayPortletResponse = (LiferayPortletResponse)portletResponse;

		portletURL = liferayPortletResponse.createLiferayPortletURL(PortletKeys.SEARCH, PortletRequest.RENDER_PHASE);
	}
	else {
		portletURL = new PortletURLImpl(request, PortletKeys.SEARCH, plid, PortletRequest.RENDER_PHASE);
	}

	portletURL.setParameter("struts_action", "/search/search");
	portletURL.setParameter("redirect", currentURL);
	portletURL.setPortletMode(PortletMode.VIEW);
	portletURL.setWindowState(WindowState.MAXIMIZED);

	pageContext.setAttribute("portletURL", portletURL);
	%>

	<form action="<%= HtmlUtil.escapeAttribute(portletURL.toString()) %>" method="get" name="<%= randomNamespace %><%= namespace %>fm" onSubmit="<%= randomNamespace %><%= namespace %>search(); return false;">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<input name="<%= namespace %>keywords" placeholder="<liferay-ui:message key="search" />" size="30" title="<liferay-ui:message key="search" />" type="text" value="<%= HtmlUtil.escapeAttribute(keywords) %>" />

	<input name="<%= namespace %>groupId" type="hidden" value="<%= group.getGroupId() %>" />

	<input align="absmiddle" border="0" src="<%= themeDisplay.getPathThemeImages() %>/common/search.png" title="<liferay-ui:message key="search" />" type="image" />

	<aui:script>
		function <%= randomNamespace %><%= namespace %>search() {
			var keywords = document.<%= randomNamespace %><%= namespace %>fm.<%= namespace %>keywords.value;

			keywords = keywords.replace(/^\s+|\s+$/, '');

			if (keywords != '') {
				submitForm(document.<%= randomNamespace %><%= namespace %>fm);
			}
		}
	</aui:script>
</div>

<%
PortletURL incrementalSearchURL = null;

if (portletResponse != null) {
	LiferayPortletResponse liferayPortletResponse = (LiferayPortletResponse)portletResponse;

	incrementalSearchURL = liferayPortletResponse.createLiferayPortletURL(PortletKeys.SEARCH, PortletRequest.RESOURCE_PHASE);
}
else {
	incrementalSearchURL = new PortletURLImpl(request, PortletKeys.SEARCH, plid, PortletRequest.RESOURCE_PHASE);
}

incrementalSearchURL.setParameter("struts_action", "/search/search_incremental");
incrementalSearchURL.setParameter("redirect", currentURL);
incrementalSearchURL.setParameter("groupId", String.valueOf(scopeGroupId));
%>

<aui:script use="autocomplete,autocomplete-highlighters">
	var node = A.one('#<%= randomNamespace %><%= namespace %>incrementalSearchContainer input[name="<%= namespace %>keywords"]');

	node.plug(
		A.Plugin.AutoComplete,
		{
			requestTemplate: '&<%= namespace %>keywords={query}',
			resultHighlighter: 'phraseMatch',
			resultTextLocator: 'title',
			source: '<%= incrementalSearchURL.toString() %>'
		}
	);

	node.ac.on(
		'select',
		function(e) {
			var entry = e.result.raw;
			var entryURL = entry.url;

			document.location = entryURL;
		}
	);
</aui:script>