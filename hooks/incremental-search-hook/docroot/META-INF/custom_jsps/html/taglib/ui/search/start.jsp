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

	var searchResultTemplate =
		'<div class="kb-search-result">' +
			'{folders} <span class="kb-search-result-title" style="display: block; font-weight: bolder">{title}</span>' +
		'</div>';


	var folderTemplate =
		'<span class="kb-search-result-folder" style="font-size: 0.9em">' +
			'{folderName} <span style="margin: 0 0.3em 0 0">&gt;</span>' +
		'</span>';

	function searchResultFormatter(query, results) {
		return A.Array.map(
			results,
			function(result) {
				var rawResult = result.raw;

				var foldersMarkup = A.Array.reduce(
					rawResult.folderNames,
					'',
					function(markup, folderName) {
						if (!A.Lang.trim(folderName)) {
							return markup;
						}

						var folderMarkup = A.Lang.sub(
							folderTemplate,
							{ folderName : folderName }
						);

						return markup + folderMarkup;
					}
				);

				return A.Lang.sub(
					searchResultTemplate,
					{
						folders : foldersMarkup,
						title : rawResult.title
					}
				);
			}
		);
	}

	node.plug(
		A.Plugin.AutoComplete,
		{
			requestTemplate: '&<portlet:namespace />keywords={query}',
			resultFormatter: searchResultFormatter,
			resultHighlighter: 'phraseMatch',
			resultTextLocator: 'title',
			source: '<%= incrementalSearchURL %>'
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