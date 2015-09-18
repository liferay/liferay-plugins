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

<%@ include file="/html/portlet/search/init.jsp" %>

<div class="yui3-skin-sam">
	<liferay-util:include page="/html/portlet/search/search.portal.jsp" />
</div>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" var="incrementalSearchURL">
	<portlet:param name="struts_action" value="/search/search_incremental" />
	<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
</liferay-portlet:resourceURL>

<aui:script use="autocomplete,autocomplete-highlighters">
	var node = A.one('#<portlet:namespace />searchContainer input[name="<portlet:namespace />keywords"]');

	node.plug(
		A.Plugin.AutoComplete,
		{
			requestTemplate: '&<portlet:namespace />keywords={query}',
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