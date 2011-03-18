<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/display/init.jsp" %>

<%
String jspPage = ParamUtil.getString(request, "jspPage", "/display/view.jsp");

long categoryId = ParamUtil.getLong(request, "categoryId");
String tag = ParamUtil.getString(request, "tag");
%>

<div class="top-links-container">
	<div class="top-links">
		<div class="top-links-navigation">

			<%
			String viewURL = StringPool.BLANK;

			if (!jspPage.equals("/display/view.jsp") || (categoryId > 0) || Validator.isNotNull(tag)) {
				PortletURL portletURL = renderResponse.createRenderURL();

				portletURL.setParameter("jspPage", "/display/view.jsp");

				if ((categoryId > 0) || Validator.isNotNull(tag)) {
					portletURL.setParameter("categoryId", StringPool.BLANK);
					portletURL.setParameter("tag", StringPool.BLANK);
				}

				viewURL = portletURL.toString();
			}
			%>

			<liferay-ui:icon
				cssClass="top-link"
				image="../aui/home"
				label="<%= true %>"
				message="knowledge-base-home"
				method="get"
				url="<%= viewURL %>"
			/>

			<%
			String viewRecentArticlesURL = StringPool.BLANK;

			if (!jspPage.equals("/display/view_recent_articles.jsp")) {
				PortletURL portletURL = renderResponse.createRenderURL();

				portletURL.setParameter("jspPage", "/display/view_recent_articles.jsp");

				viewRecentArticlesURL = portletURL.toString();
			}
			%>

			<liferay-ui:icon
				cssClass='<%= (!themeDisplay.isSignedIn() && !DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR)) ? "top-link last" : "top-link" %>'
				image="../aui/clock"
				label="<%= true %>"
				message="recent-articles"
				method="get"
				url="<%= viewRecentArticlesURL %>"
			/>

			<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">

				<%
				String viewAdminPanelURL = StringPool.BLANK;

				if (!jspPage.equals("/display/view_admin_panel.jsp")) {
					PortletURL portletURL = renderResponse.createRenderURL();

					portletURL.setParameter("jspPage", "/display/view_admin_panel.jsp");
					portletURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_ANY));

					viewAdminPanelURL = portletURL.toString();
				}
				%>

				<liferay-ui:icon
					cssClass='<%= !themeDisplay.isSignedIn() ? "top-link last" : "top-link" %>'
					image="../aui/person"
					label="<%= true %>"
					message="administrator"
					method="get"
					url="<%= viewAdminPanelURL %>"
				/>
			</c:if>

			<c:if test="<%= themeDisplay.isSignedIn() %>">

				<%
				String viewSubscribedArticlesURL = StringPool.BLANK;

				if (!jspPage.equals("/display/view_subscribed_articles.jsp")) {
					PortletURL portletURL = renderResponse.createRenderURL();

					portletURL.setParameter("jspPage", "/display/view_subscribed_articles.jsp");

					viewSubscribedArticlesURL = portletURL.toString();
				}
				%>

				<liferay-ui:icon
					cssClass="top-link last"
					image="../aui/signal-diag"
					label="<%= true %>"
					message="my-subscriptions"
					method="get"
					url="<%= viewSubscribedArticlesURL %>"
				/>
			</c:if>
		</div>

		<div class="article-search">
			<liferay-portlet:renderURL varImpl="searchURL">
				<portlet:param name="jspPage" value="/display/search.jsp" />
			</liferay-portlet:renderURL>

			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<aui:fieldset>
					<span class="aui-search-bar">
						<aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-articles" type="text" />

						<aui:button type="submit" value="search" />
					</span>
				</aui:fieldset>
			</aui:form>
		</div>
	</div>
</div>

<c:if test='<%= !jspPage.equals("/display/view.jsp") && ((categoryId > 0) || Validator.isNotNull(tag)) %>'>
	<div class="portlet-msg-info">
		<portlet:renderURL var="viewPRPArticlesURL">
			<portlet:param name="jspPage" value="/display/view.jsp" />
		</portlet:renderURL>

		<aui:a href="<%= viewPRPArticlesURL %>">
			<c:choose>
				<c:when test="<%= categoryId > 0 %>">

					<%
					AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);

					AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());
					%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(tag) %>">
							<%= LanguageUtil.format(pageContext, "view-articles-with-x-x-and-tag-x", new String[] {assetVocabulary.getName(), assetCategory.getName(), tag}, false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "view-articles-with-x-x", new String[] {assetVocabulary.getName(), assetCategory.getName()}, false) %>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.format(pageContext, "view-articles-with-tag-x", tag, false) %>
				</c:otherwise>
			</c:choose>
		</aui:a>
	</div>
</c:if>