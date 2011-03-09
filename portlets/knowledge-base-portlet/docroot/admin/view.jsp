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
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
%>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<portlet:renderURL var="searchURL">
	<portlet:param name="jspPage" value="/admin/view.jsp" />
</portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="resourcePrimKeys" type="hidden" />

	<liferay-ui:error exception="<%= ArticlePriorityException.class %>" message='<%= LanguageUtil.format(pageContext, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view.jsp" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			rowChecker="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_ARTICLES) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new ArticleSearch(renderRequest, iteratorURL) %>"
		>
			<liferay-ui:search-form
				page="/admin/article_search.jsp"
				servletContext="<%= application %>"
			/>

			<%
			ArticleSearchTerms searchTerms = (ArticleSearchTerms)searchContainer.getSearchTerms();
			%>

			<liferay-ui:search-container-results>
				<%@ include file="/admin/article_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.Article"
				keyProperty="resourcePrimKey"
				modelVar="article"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="jspPage" value="/admin/view.jsp" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
				</liferay-portlet:renderURL>

				<%
				if (ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, article.getResourcePrimKey(), WorkflowConstants.STATUS_ANY) == 0) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					name="priority"
					orderable="<%= true %>"
				>
					<c:choose>
						<c:when test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_ARTICLES_PRIORITIES) %>">
							<aui:input label="" name='<%= "priority" + article.getResourcePrimKey() %>' size="5" type="text" value="<%= BigDecimal.valueOf(article.getPriority()).toPlainString() %>" />
						</c:when>
						<c:otherwise>
							<%= BigDecimal.valueOf(article.getPriority()).toPlainString() %>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="title"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="author"
					orderable="<%= true %>"
					orderableProperty="user-name"
					property="userName"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value="<%= dateFormatDateTime.format(article.getCreateDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= true %>"
					value="<%= dateFormatDateTime.format(article.getModifiedDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= article.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(article.getStatus())) + ")" %>'
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/article_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) || AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE)) %>">
				<aui:button-row cssClass="float-container">
					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
						<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) %>">
							<portlet:renderURL var="addArticleURL">
								<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
							</portlet:renderURL>

							<aui:button onClick="<%= addArticleURL %>" value="add-article" />
						</c:if>

						<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
							<liferay-security:permissionsURL
								modelResource="com.liferay.knowledgebase.admin"
								modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
								resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
								var="permissionsURL"
							/>

							<aui:button onClick="<%= permissionsURL %>" value="permissions" />
						</c:if>
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
						<div class="kb-admin-tools">
							<c:choose>
								<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), scopeGroupId) %>">
									<portlet:actionURL name="unsubscribeGroupArticles" var="unsubscribeGroupArticlesURL">
										<portlet:param name="redirect" value="<%= currentURL %>" />
									</portlet:actionURL>

									<liferay-ui:icon
										image="unsubscribe"
										label="<%= true %>"
										url="<%= unsubscribeGroupArticlesURL %>"
									/>
								</c:when>
								<c:otherwise>
									<portlet:actionURL name="subscribeGroupArticles" var="subscribeGroupArticlesURL">
										<portlet:param name="redirect" value="<%= currentURL %>" />
									</portlet:actionURL>

									<liferay-ui:icon
										image="subscribe"
										label="<%= true %>"
										url="<%= subscribeGroupArticlesURL %>"
									/>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<c:if test="<%= parentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

				<%
				searchContainer.setEmptyResultsMessage(null);
				%>

				<div class="portlet-msg-info">
					<portlet:renderURL var="viewArticleURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					</portlet:renderURL>

					<%
					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"");
					sb.append(viewArticleURL);
					sb.append("\">");
					sb.append(BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(parentResourcePrimKey, WorkflowConstants.STATUS_ANY), "title"));
					sb.append("</a>");
					%>

					<c:choose>
						<c:when test="<%= !searchContainer.getResultRows().isEmpty() %>">
							<%= LanguageUtil.format(pageContext, "showing-child-articles-for-x", sb.toString(), false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "there-are-no-child-articles-for-x", sb.toString(), false) %>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

			<c:if test="<%= !searchContainer.getResultRows().isEmpty() && (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_ARTICLES) || AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_ARTICLES_PRIORITIES)) %>">
				<aui:button-row>
					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_ARTICLES) %>">
						<aui:button onClick='<%= renderResponse.getNamespace() + "deleteArticles();" %>' value="delete" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_ARTICLES_PRIORITIES) %>">
						<aui:button onClick='<%= renderResponse.getNamespace() + "updatePriorities();" %>' value="save" />
					</c:if>
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator type='<%= searchTerms.hasSearchTerms() ? "more" : "regular" %>' />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />updatePriorities() {
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updatePriorities"><portlet:param name="jspPage" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
	}

	Liferay.provide(
		window,
		'<portlet:namespace />deleteArticles',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-articles") %>')) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeys.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="deleteArticles"><portlet:param name="jspPage" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>