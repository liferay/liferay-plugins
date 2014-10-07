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

<%@ include file="/html/taglib/ui/discussion/page.portal.jsp" %>

<%@ page import="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" %>

<%@ page import="java.lang.reflect.Method" %>

<c:if test="<%= _isMentionsEnabled(themeDisplay.getSiteGroupId()) %>">

	<%
	long cssLastModified = ServletContextUtil.getLastModified(application, "/html/css/", true);
	%>

	<liferay-util:html-top outputKey="mentions_autocomplete_css">
		<link href="<%= PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNDynamicResourcesHost() + themeDisplay.getPathContext() + "/html/css/portal/mentions.css", cssLastModified) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>

	<%
	long javaScriptLastModified = ServletContextUtil.getLastModified(application, "/html/js/", true);
	%>

	<liferay-util:html-bottom outputKey="mentions_autocomplete_js">
		<script defer="defer" src="<%= PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNDynamicResourcesHost() + themeDisplay.getPathJavaScript() + "/liferay/modules-ext.js", "", javaScriptLastModified) %>" type="text/javascript"></script>
	</liferay-util:html-bottom>

	<liferay-portlet:resourceURL portletName="1_WAR_mentionsportlet" var="autoCompleteUserURL" />

	<aui:script use="liferay-autocomplete-textarea">
		var autocompleteConfig = {
			requestTemplate: 'query={query}',
			trigger: [
				{
					resultFilters: function(query, results) {
						return results;
					},
					resultTextLocator: 'screenName',
					source: '<%= autoCompleteUserURL.toString() + "&" + PortalUtil.getPortletNamespace("1_WAR_mentionsportlet") %>',
					term: '@',
					tplResults: '<div class="display-style-3 taglib-user-display"><span><span class="user-profile-image" style="background-image: url(\'{portraitURL}\'); background-size: 32px 32px; height: 32px; width: 32px;"></span><span class="user-name">{fullName}</span><span class="user-details">@{screenName}</span></span></div>'
				}
			]
		};

		new Liferay.AutoCompleteTextarea(
			A.merge(
				autocompleteConfig,
				{
					inputNode: '#<portlet:namespace /><%= randomNamespace + "postReplyBody" + "0" %>'
				}
			)
		).render();

		A.one('#<portlet:namespace />discussion-container').delegate(
			'click',
			function(event) {
				var currentTarget = event.currentTarget;

				var discussionNode = currentTarget.ancestor('.lfr-discussion');

				var actionClass = currentTarget.hasClass('lfr-discussion-reply-to') ? '.lfr-discussion-form-reply' : '.lfr-discussion-form-edit';

				new Liferay.AutoCompleteTextarea(
					A.merge(
						autocompleteConfig,
						{
							inputNode: discussionNode.one(actionClass + ' textarea')
						}
					)
				).render();
			},
			'.lfr-discussion-reply-to, .lfr-discussion-edit'
		);
	</aui:script>
</c:if>

<%!
private boolean _isMentionsEnabled(long siteGroupId) throws Exception {
	ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader("1_WAR_mentionsportlet");

	Class<?> clazz = classLoader.loadClass("com.liferay.mentions.util.MentionsUtil");

	Method method = clazz.getMethod("isMentionsEnabled", long.class);

	return (Boolean)method.invoke(null, siteGroupId);
}
%>