<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "home");

String path = GetterUtil.getString(request.getPathInfo());
%>

<c:if test="<%= portletName.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN) %>">
	<div class="top-links-container">
		<div class="top-links">
			<div class="top-links-navigation">
				<portlet:renderURL var="homeURL">
					<portlet:param name="jspPage" value="/admin/view.jsp" />
					<portlet:param name="topLink" value="home" />
				</portlet:renderURL>

				<%
				String taglibURL = (path.endsWith("/admin/view.jsp") && topLink.equals("home")) ? StringPool.BLANK : homeURL;
				%>

				<liferay-ui:icon
					cssClass="top-link"
					image="../aui/home"
					label="<%= true %>"
					message="home"
					method="get"
					url="<%= taglibURL %>"
				/>

				<c:if test='<%= AdminPermission.contains(permissionChecker, scopeGroupId, "VIEW_TEMPLATES") %>'>
					<portlet:renderURL var="templatesURL">
						<portlet:param name="jspPage" value="/admin/view_templates.jsp" />
						<portlet:param name="topLink" value="templates" />
					</portlet:renderURL>

					<%
					taglibURL = topLink.equals("templates") ? StringPool.BLANK : templatesURL;
					%>

					<liferay-ui:icon
						cssClass="top-link"
						image="../aui/bookmark"
						label="<%= true %>"
						message="templates"
						method="get"
						url="<%= taglibURL %>"
					/>
				</c:if>

				<portlet:renderURL var="allArticlesURL">
					<portlet:param name="jspPage" value="/admin/view_all_articles.jsp" />
					<portlet:param name="topLink" value="all-articles" />
				</portlet:renderURL>

				<%
				taglibURL = topLink.equals("all-articles") ? StringPool.BLANK : allArticlesURL;
				%>

				<liferay-ui:icon
					cssClass="top-link last"
					image="view"
					label="<%= true %>"
					message="all-articles"
					method="get"
					url="<%= taglibURL %>"
				/>
			</div>

			<div class="article-search">
				<liferay-portlet:renderURL varImpl="searchURL">
					<portlet:param name="jspPage" value="/admin/search.jsp" />
				</liferay-portlet:renderURL>

				<aui:form action="<%= searchURL %>" method="get" name="searchFm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />

					<span class="aui-search-bar">
						<aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-articles" type="text" />

						<aui:button type="submit" value="search" />
					</span>
				</aui:form>
			</div>
		</div>
	</div>
</c:if>