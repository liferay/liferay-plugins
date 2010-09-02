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
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey", parentResourcePrimKey);
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
			headerNames="title"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				Map<String, Object> params = new HashMap<String, Object>();

				params.put("groupId", scopeGroupId);
				params.put("parentResourcePrimKey", parentResourcePrimKey);

				pageContext.setAttribute("results", ArticleServiceUtil.getArticles(params, false, searchContainer.getStart(), searchContainer.getEnd(), new ArticlePriorityComparator(true)));
				pageContext.setAttribute("total", ArticleServiceUtil.getArticlesCount(params, false));
				%>

			</liferay-ui:search-container-results>

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
				Map<String, Object> params = new HashMap<String, Object>();

				params.put("groupId", scopeGroupId);
				params.put("parentResourcePrimKey", curArticle.getResourcePrimKey());

				List<Article> articles = ArticleServiceUtil.getArticles(params, false, 0, 1, null);

				String titleHREF = null;

				if ((curArticle.getResourcePrimKey() != resourcePrimKey) && !articles.isEmpty()) {
					titleHREF = rowURL;
				}
				%>

				<liferay-ui:search-container-column-text
					href="<%= titleHREF %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/article_priority.jsp" servletContext="<%= application %>">
							<liferay-util:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
							<liferay-util:param name="priority" value="<%= String.valueOf(ArticleConstants.DEFAULT_PRIORITY) %>" />
							<liferay-util:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectArticle('" + curArticle.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button disabled="<%= (curArticle.getResourcePrimKey() == resourcePrimKey) || (curArticle.getResourcePrimKey() == oldParentResourcePrimKey) || ((curArticle.getStatus() != WorkflowConstants.STATUS_APPROVED) && (curArticle.getVersion() == ArticleConstants.DEFAULT_VERSION)) %>" onClick="<%= taglibOnClick %>" value="choose" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= oldParentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
				<aui:button-row>

					<%
					Article oldParentArticle = ArticleServiceUtil.getLatestArticle(oldParentResourcePrimKey, WorkflowConstants.STATUS_ANY);
					%>

					<%= oldParentArticle.getTitle() %>

					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/article_priority.jsp" servletContext="<%= application %>">
							<liferay-util:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
							<liferay-util:param name="priority" value="<%= String.valueOf(ArticleConstants.DEFAULT_PRIORITY) %>" />
							<liferay-util:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectArticle('" + ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button onClick="<%= taglibOnClick %>" value="remove" />
				</aui:button-row>
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
					List<Article> articles = new ArrayList<Article>();

					articles.add(ArticleServiceUtil.getLatestArticle(parentResourcePrimKey, WorkflowConstants.STATUS_ANY));

					int index = -1;

					while ((index = index + 1) < articles.size()) {
						Article curArticle = articles.get(index);

						if (curArticle.getParentResourcePrimKey() != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
							articles.add(ArticleServiceUtil.getLatestArticle(curArticle.getParentResourcePrimKey(), WorkflowConstants.STATUS_ANY));
						}
					}

					for (int i = articles.size(); i > 0; i--) {
						Article curArticle = articles.get(i - 1);
					%>

						<aui:a href='<%= HttpUtil.setParameter(breadcrumbURL, "parentResourcePrimKey", curArticle.getResourcePrimKey()) %>'><%= (i == 1) ? curArticle.getTitle() : StringUtil.shorten(curArticle.getTitle(), 30) %></aui:a> &raquo;

					<%
					}
					%>

				</c:if>
			</div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>