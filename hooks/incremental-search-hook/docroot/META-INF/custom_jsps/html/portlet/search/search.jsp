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