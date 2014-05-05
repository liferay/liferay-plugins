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

<%@ include file="/article/init.jsp" %>

<div id="<portlet:namespace/>message-container"></div>

<c:if test="<%= Validator.equals(portletDisplay.getId(), PortletKeys.KNOWLEDGE_BASE_ARTICLE_DEFAULT_INSTANCE) && PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) %>">
	<div class="portlet-configuration alert alert-info">
		<aui:a href="<%= portletDisplay.getURLConfiguration() %>" label='<%= LanguageUtil.format(pageContext, "portlet-configuration-page-x-instance-id-x", new String[] {layout.getName(locale), portletDisplay.getInstanceId()}, false) %>' onClick="<%= portletDisplay.getURLConfigurationJS() %>" />
	</div>
</c:if>

<div class="kb-article-container">
	<liferay-util:include page="/admin/common/view_article.jsp" servletContext="<%= application %>" />
</div>

<aui:script use="aui-base,aui-io-request,aui-parse-content">
	var messageContainer = A.one('#<portlet:namespace />message-container');

	var showStatusMessage = function(type, message) {
		messageContainer.removeClass('alert-error').removeClass('alert-success');

		messageContainer.addClass('alert alert-' + type);

		messageContainer.html(message);

		messageContainer.show();
	};

	Liferay.on(
		'knowledgeBaseNavigation',
		function(event) {
			A.io.request(
				event.url,
				{
					after: {
						failure: function(event, id, obj) {
							showStatusMessage('error', '<%= UnicodeLanguageUtil.get(pageContext, "your-request-failed-to-complete") %>');
						},
						success: function(event, id, obj) {
							var instance = this;

							showStatusMessage('success', '<%= UnicodeLanguageUtil.get(pageContext, "your-request-processed-successfully") %>');

							var container = A.one('.kb-article-container');

							container.plug(A.Plugin.ParseContent);

							container.setContent(instance.get('responseData'));
						}
					}
				}
			);
		}
	);
</aui:script>