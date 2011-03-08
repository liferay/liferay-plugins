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

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey");
%>

<liferay-ui:header
	title="parent-article"
/>

<aui:form method="post" name="fm">
	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="jspPage" value="/admin/select_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
			<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-articles"
			headerNames="priority,title"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results
				results="<%= ArticleServiceUtil.getSiblingArticles(scopeGroupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd(), new ArticlePriorityComparator(true)) %>"
				total="<%= ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, parentResourcePrimKey, WorkflowConstants.STATUS_ANY) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.Article"
				keyProperty="resourcePrimKey"
				modelVar="curArticle"
			>
				<portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="jspPage" value="/admin/select_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
				</portlet:renderURL>

				<%
				String rowHREF = null;

				if ((curArticle.getResourcePrimKey() != resourcePrimKey) && (ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, curArticle.getResourcePrimKey(), WorkflowConstants.STATUS_ANY) > 0)) {
					rowHREF = rowURL;
				}
				%>

				<liferay-ui:search-container-column-text
					href="<%= rowHREF %>"
					name="priority"
					value="<%= BigDecimal.valueOf(curArticle.getPriority()).toPlainString() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowHREF %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" portletId="<%= portletDisplay.getId() %>">
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectArticle('" + curArticle.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button disabled="<%= (curArticle.getResourcePrimKey() == resourcePrimKey) || (curArticle.getResourcePrimKey() == oldParentResourcePrimKey) %>" onClick="<%= taglibOnClick %>" value="choose" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= oldParentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
				<aui:button-row>
					<%= BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(oldParentResourcePrimKey, WorkflowConstants.STATUS_ANY), "title") %>

					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" portletId="<%= portletDisplay.getId() %>">
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectArticle('" + ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button onClick="<%= taglibOnClick %>" value="remove" />
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<div class="kb-select-article-breadcrumbs">
				<portlet:renderURL var="breadcrumbURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="jspPage" value="/admin/select_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
				</portlet:renderURL>

				<aui:a href="<%= breadcrumbURL %>"><liferay-ui:message key="home" /></aui:a> &raquo;

				<c:if test="<%= parentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

					<%
					List<Article> selArticles = new ArrayList<Article>();

					long selParentResourcePrimKey = parentResourcePrimKey;

					while (selParentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
						Article selArticle = ArticleServiceUtil.getLatestArticle(selParentResourcePrimKey, WorkflowConstants.STATUS_ANY);

						selArticles.add(selArticle);

						selParentResourcePrimKey = selArticle.getParentResourcePrimKey();
					}

					for (int i = selArticles.size(); i > 0; i--) {
						Article selArticle = selArticles.get(i - 1);
					%>

						<aui:a href='<%= HttpUtil.setParameter(breadcrumbURL, "parentResourcePrimKey", selArticle.getResourcePrimKey()) %>'><%= (i == 1) ? selArticle.getTitle() : StringUtil.shorten(selArticle.getTitle(), 30) %></aui:a> &raquo;

					<%
					}
					%>

				</c:if>
			</div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>