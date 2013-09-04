<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
String mvcPath = ParamUtil.getString(request, "mvcPath", "/display/view.jsp");

long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");
%>

<aui:nav-bar>
	<aui:nav>

		<%
		String label = "knowledge-base-home";

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/display/view.jsp");

		if ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) {
			portletURL.setParameter("categoryId", StringPool.BLANK);
			portletURL.setParameter("tag", StringPool.BLANK);
		}

		boolean selected = false;

		if (mvcPath.equals("/display/view.jsp") && (assetCategoryId == 0) && Validator.isNull(assetTagName)) {
			selected = true;
		}
		%>

		<aui:nav-item
			cssClass='<%= selected ? "active" : StringPool.BLANK %>'
			href="<%= portletURL.toString() %>"
			label="<%= label %>"
			selected="<%= selected %>"
		/>

		<%
		label = "recent-articles";

		portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/display/view_recent_articles.jsp");

		if ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) {
			portletURL.setParameter("categoryId", StringPool.BLANK);
			portletURL.setParameter("tag", StringPool.BLANK);
		}

		selected = mvcPath.equals("/display/view_recent_articles.jsp");
		%>

		<aui:nav-item
			cssClass='<%= selected ? "active" : StringPool.BLANK %>'
			href="<%= portletURL.toString() %>"
			label="<%= label %>"
			selected="<%= selected %>"
		/>

		<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) %>">

			<%
			label = "administrator";

			portletURL = renderResponse.createRenderURL();

			portletURL.setParameter("mvcPath", "/display/view_admin_panel.jsp");
			portletURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_ANY));

			if ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) {
				portletURL.setParameter("categoryId", StringPool.BLANK);
				portletURL.setParameter("tag", StringPool.BLANK);
			}

			selected = mvcPath.equals("/display/view_admin_panel.jsp");
			%>

			<aui:nav-item
				cssClass='<%= selected ? "active" : StringPool.BLANK %>'
				href="<%= portletURL.toString() %>"
				label="<%= label %>"
				selected="<%= selected %>"
			/>
		</c:if>

		<c:if test="<%= themeDisplay.isSignedIn() %>">

			<%
			label = "my-subscriptions";

			portletURL = renderResponse.createRenderURL();

			portletURL.setParameter("mvcPath", "/display/view_subscribed_articles.jsp");

			if ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) {
				portletURL.setParameter("categoryId", StringPool.BLANK);
				portletURL.setParameter("tag", StringPool.BLANK);
			}

			selected = mvcPath.equals("/display/view_subscribed_articles.jsp");
			%>

			<aui:nav-item
				cssClass='<%= selected ? "active" : StringPool.BLANK %>'
				href="<%= portletURL.toString() %>"
				label="<%= label %>"
				selected="<%= selected %>"
			/>
		</c:if>
	</aui:nav>

	<div class="navbar-search pull-right">
		<div class="form-search">
			<liferay-portlet:renderURL varImpl="searchURL">
				<portlet:param name="mvcPath" value="/display/search.jsp" />
			</liferay-portlet:renderURL>

			<aui:form action="<%= searchURL %>" method="get" name="searchFm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<liferay-ui:input-search />
			</aui:form>
		</div>
	</div>
</aui:nav-bar>

<c:if test='<%= !mvcPath.equals("/display/view.jsp") && ((assetCategoryId > 0) || Validator.isNotNull(assetTagName)) %>'>
	<div class="alert alert-info">
		<liferay-portlet:renderURL var="viewPRPKBArticlesURL">
			<portlet:param name="mvcPath" value="/display/view.jsp" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= viewPRPKBArticlesURL %>">
			<c:choose>
				<c:when test="<%= assetCategoryId > 0 %>">

					<%
					AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(assetCategoryId);

					assetCategory = assetCategory.toEscapedModel();

					AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getAssetVocabulary(assetCategory.getVocabularyId());

					assetVocabulary = assetVocabulary.toEscapedModel();
					%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(assetTagName) %>">
							<%= LanguageUtil.format(pageContext, "view-articles-with-x-x-and-tag-x", new String[] {assetVocabulary.getTitle(locale), assetCategory.getTitle(locale), assetTagName}, false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "view-articles-with-x-x", new String[] {assetVocabulary.getTitle(locale), assetCategory.getTitle(locale)}, false) %>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.format(pageContext, "view-articles-with-tag-x", assetTagName, false) %>
				</c:otherwise>
			</c:choose>
		</aui:a>
	</div>
</c:if>