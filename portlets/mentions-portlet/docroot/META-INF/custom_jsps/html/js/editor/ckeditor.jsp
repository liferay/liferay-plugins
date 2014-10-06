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

<%@ include file="/html/js/editor/ckeditor.portal.jsp" %>

<c:if test="<%= portletId.equals(PortletKeys.BLOGS) || portletId.equals(PortletKeys.BLOGS_ADMIN) || portletId.equals(PortletKeys.MESSAGE_BOARDS) || portletId.equals(PortletKeys.MESSAGE_BOARDS_ADMIN) %>">
	<liferay-portlet:resourceURL portletName="1_WAR_mentionsportlet" var="autoCompleteUserURL" />

	<aui:script>
		var editor = CKEDITOR.instances['<%= name %>'];

		var configAutocomplete = function(editor) {
			editor.config.autocomplete = {
				requestTemplate: 'query={query}',
				trigger: [
					{
						resultFilters: function(query, results) { return results; },
						resultTextLocator: 'screenName',
						source: '<%= autoCompleteUserURL.toString() + "&" + PortalUtil.getPortletNamespace("1_WAR_mentionsportlet") %>',
						term: '@',
						tplReplace: '<a href="{profileURL}">@{screenName}</a>',
						tplResults: '<div class="display-style-3 taglib-user-display" style="padding:5px;"><span><span class="user-profile-image" style="background-image: url(\'{portraitURL}\'); background-size: 32px 32px; height: 32px; margin-right:5px; width: 32px;"></span><span class="user-name">{fullName}</span><span class="user-details"> (@{screenName})</span></span></div>'
					}
				]
			};
		}

		if (editor) {
			configAutocomplete(editor);
		}
		else {
			CKEDITOR.once(
				'instanceCreated',
				function(event) {
					configAutocomplete(event.editor);
				}
			);
		}
	</aui:script>
</c:if>