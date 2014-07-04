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
		CKEDITOR.on(
			'instanceCreated',
			function(event) {
				var ckEditor = event.editor;

				ckEditor.config.autocomplete = {
					requestTemplate: function(query) {
						return 'query=' + query;
					},
					trigger: [
						{
							resultFilters: function(query, results) { return results; },
							resultTextLocator: 'screenName',
							term: '@',
							tplReplace: '<a href="{profileURL}">@{screenName}</a>',
							tplResults: '<div class="taglib-user-display display-style-3" style="padding:5px;"><span><span class="user-profile-image" style="background-image: url(\'{portraitURL}\'); background-size: 32px 32px; height: 32px; width: 32px;margin-right:5px;"></span><span class="user-name">{fullName}</span><span class="user-details"> (@{screenName})</span></span></div>',
							source: '<%= autoCompleteUserURL.toString() %>'
						}
					]
				};
			}
		);
	</aui:script>
</c:if>