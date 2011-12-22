<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "parentResourcePrimKey", KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

long oldParentResourcePrimKey = ParamUtil.getLong(request, "oldParentResourcePrimKey");

String orderByCol = ParamUtil.getString(request, "orderByCol", "priority");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-ui:header
	title="parent-article"
/>

<aui:form method="post" name="fm">
	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="jspPage" value='<%= jspPath + "select_article.jsp" %>' />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
			<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-articles"
			iteratorURL="<%= iteratorURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
			orderByType="<%= orderByType %>"
		>
			<liferay-ui:search-container-results
				results="<%= KBArticleServiceUtil.getSiblingKBArticles(scopeGroupId, parentResourcePrimKey, status, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= KBArticleServiceUtil.getSiblingKBArticlesCount(scopeGroupId, parentResourcePrimKey, status) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				keyProperty="resourcePrimKey"
				modelVar="curKBArticle"
			>
				<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="jspPage" value='<%= jspPath + "select_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(curKBArticle.getResourcePrimKey()) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

				<%
				if ((curKBArticle.getResourcePrimKey() == resourcePrimKey) || (KBArticleServiceUtil.getSiblingKBArticlesCount(scopeGroupId, curKBArticle.getResourcePrimKey(), status) == 0)) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="priority"
					orderable="<%= true %>"
					value="<%= BigDecimal.valueOf(curKBArticle.getPriority()).toPlainString() %>"
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
					value='<%= curKBArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(curKBArticle.getStatus())) + ")" %>'
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>">
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(curKBArticle.getResourcePrimKey()) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBArticle('" + curKBArticle.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button disabled="<%= (curKBArticle.getResourcePrimKey() == resourcePrimKey) || (curKBArticle.getResourcePrimKey() == oldParentResourcePrimKey) %>" onClick="<%= taglibOnClick %>" value="choose" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= oldParentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
				<aui:button-row>
					<%= BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(oldParentResourcePrimKey, status), "title") %>

					<liferay-util:buffer var="html">
						<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>">
							<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
						</liferay-util:include>
					</liferay-util:buffer>

					<%
					String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBArticle('" + KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
					%>

					<aui:button onClick="<%= taglibOnClick %>" value="remove" />
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<div class="kb-select-article-breadcrumbs">
				<liferay-portlet:renderURL var="breadcrumbURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="jspPage" value='<%= jspPath + "select_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
					<portlet:param name="oldParentResourcePrimKey" value="<%= String.valueOf(oldParentResourcePrimKey) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

				<aui:a href="<%= breadcrumbURL %>"><liferay-ui:message key="home" /></aui:a> &raquo;

				<c:if test="<%= parentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

					<%
					List<KBArticle> selKBArticles = new ArrayList<KBArticle>();

					long selParentResourcePrimKey = parentResourcePrimKey;

					while (selParentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
						KBArticle selKBArticle = KBArticleServiceUtil.getLatestKBArticle(selParentResourcePrimKey, status);

						selKBArticles.add(selKBArticle);

						selParentResourcePrimKey = selKBArticle.getParentResourcePrimKey();
					}

					for (int i = selKBArticles.size(); i > 0; i--) {
						KBArticle selKBArticle = selKBArticles.get(i - 1);
					%>

						<aui:a href='<%= HttpUtil.setParameter(breadcrumbURL, "parentResourcePrimKey", selKBArticle.getResourcePrimKey()) %>'><%= (i == 1) ? selKBArticle.getTitle() : StringUtil.shorten(selKBArticle.getTitle(), 30) %></aui:a> &raquo;

					<%
					}
					%>

				</c:if>
			</div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>