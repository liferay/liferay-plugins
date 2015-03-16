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

<%@ include file="/html/portlet/journal/article_toolbar.portal.jsp" %>

<%
String structureId = BeanParamUtil.getString(article, request, "structureId");
%>

<c:if test="<%= (article != null) && Validator.isNotNull(structureId) %>">
	<aui:script use="aui-base">
		var toolbar = A.Widget.getByNode('#<portlet:namespace />articleToolbar');

		toolbar.add(
			{
				icon: 'icon-download',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "download") %>',
				on: {
					click: function(event) {
						event.domEvent.preventDefault();

						var downloadArticleContent = Liferay.Util.openWindow(
							{
								dialog: {
									bodyContent: '<pre><%= HtmlUtil.escapeJS(HtmlUtil.escape(article.getContent())) %></pre>',
									modal: true
								},
								id: '<portlet:namespace />articleDownload',
								title: '<%= HtmlUtil.escapeJS(article.getTitle(locale)) %>'
							}
						);
					}
				}
			}
		);
	</aui:script>

	<style type="text/css">
		.portlet-journal .article-toolbar .icon-download {
			background-image: none;
		}
	</style>
</c:if>