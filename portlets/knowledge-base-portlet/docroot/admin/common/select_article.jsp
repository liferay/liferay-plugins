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
Integer status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey");

String orderByCol = ParamUtil.getString(request, "orderByCol", "priority");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");
%>

<liferay-ui:header
	title="parent-article"
/>

<aui:form method="post" name="fm">
	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "select_article.jsp" %>' />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
			<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-articles"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
		>
			<liferay-ui:search-container-results
				results="<%= ArticleServiceUtil.getSiblingArticles(scopeGroupId, parentResourcePrimKey, status.intValue(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, parentResourcePrimKey, status.intValue()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.Article"
				keyProperty="resourcePrimKey"
				modelVar="curArticle"
			>
				<portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "select_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
				</portlet:renderURL>

				<%
				if ((curArticle.getResourcePrimKey() == resourcePrimKey) || (ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, curArticle.getResourcePrimKey(), status.intValue()) == 0)) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="priority"
					orderable="<%= true %>"
					value="<%= BigDecimal.valueOf(curArticle.getPriority()).toPlainString() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= curArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(curArticle.getStatus())) + ")" %>'
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>">
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
					<%= BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(oldParentResourcePrimKey, status.intValue()), "title") %>

					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>">
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
					<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "select_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
				</portlet:renderURL>

				<aui:a href="<%= breadcrumbURL %>"><liferay-ui:message key="home" /></aui:a> &raquo;

				<c:if test="<%= parentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

					<%
					List<Article> selArticles = new ArrayList<Article>();

					long selParentResourcePrimKey = parentResourcePrimKey;

					while (selParentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
						Article selArticle = ArticleServiceUtil.getLatestArticle(selParentResourcePrimKey, status.intValue());

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