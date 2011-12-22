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
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
%>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="jspPage" value="/admin/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="resourcePrimKeys" type="hidden" />

	<liferay-ui:error exception="<%= KBArticlePriorityException.class %>" message='<%= LanguageUtil.format(pageContext, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

	<aui:fieldset>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view.jsp" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			rowChecker="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_ARTICLES) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new KBArticleSearch(renderRequest, iteratorURL) %>"
		>
			<liferay-ui:search-form
				page="/admin/article_search.jsp"
				servletContext="<%= application %>"
			/>

			<%
			KBArticleSearchTerms searchTerms = (KBArticleSearchTerms)searchContainer.getSearchTerms();
			%>

			<liferay-ui:search-container-results>
				<%@ include file="/admin/article_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<%
			boolean updateArticlesPriorities = AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_KB_ARTICLES_PRIORITIES);
			%>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				keyProperty="resourcePrimKey"
				modelVar="kbArticle"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="jspPage" value="/admin/view.jsp" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				</liferay-portlet:renderURL>

				<%
				if (KBArticleServiceUtil.getSiblingKBArticlesCount(scopeGroupId, kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_ANY) == 0) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					name="priority"
					orderable="<%= true %>"
				>
					<c:choose>
						<c:when test="<%= updateArticlesPriorities %>">
							<aui:input label="" name='<%= "priority" + kbArticle.getResourcePrimKey() %>' size="5" type="text" value="<%= BigDecimal.valueOf(kbArticle.getPriority()).toPlainString() %>" />
						</c:when>
						<c:otherwise>
							<%= BigDecimal.valueOf(kbArticle.getPriority()).toPlainString() %>
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
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbArticle.getCreateDate()) + "<br />" + dateFormatTime.format(kbArticle.getCreateDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= true %>"
					value='<%= dateFormatDate.format(kbArticle.getModifiedDate()) + "<br />" + dateFormatTime.format(kbArticle.getModifiedDate()) %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.toLabel(kbArticle.getStatus())) + ")" %>'
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="views"
					orderable="<%= true %>"
					orderableProperty="view-count"
					property="viewCount"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/article_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) || AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE)) %>">
				<aui:button-row cssClass="float-container">
					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) %>">
						<liferay-portlet:renderURL var="addKBArticleURL">
							<portlet:param name="jspPage" value='<%= jspPath + "edit_article.jsp" %>' />
							<portlet:param name="redirect" value="<%= redirect %>" />
						</liferay-portlet:renderURL>

						<aui:button href="<%= addKBArticleURL %>" value="add-article" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
						<liferay-security:permissionsURL
							modelResource="com.liferay.knowledgebase.admin"
							modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
							resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
							var="permissionsURL"
						/>

						<aui:button href="<%= permissionsURL %>" value="permissions" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
						<div class="kb-admin-tools">
							<c:choose>
								<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), scopeGroupId) %>">
									<liferay-portlet:actionURL name="unsubscribeGroupKBArticles" var="unsubscribeGroupKBArticlesURL">
										<portlet:param name="redirect" value="<%= redirect %>" />
									</liferay-portlet:actionURL>

									<liferay-ui:icon
										image="unsubscribe"
										label="<%= true %>"
										url="<%= unsubscribeGroupKBArticlesURL %>"
									/>
								</c:when>
								<c:otherwise>
									<liferay-portlet:actionURL name="subscribeGroupKBArticles" var="subscribeGroupKBArticlesURL">
										<portlet:param name="redirect" value="<%= redirect %>" />
									</liferay-portlet:actionURL>

									<liferay-ui:icon
										image="subscribe"
										label="<%= true %>"
										url="<%= subscribeGroupKBArticlesURL %>"
									/>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</aui:button-row>

				<div class="separator"><!-- --></div>
			</c:if>

			<c:if test="<%= !searchTerms.hasSearchTerms() && (parentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>">

				<%
				searchContainer.setEmptyResultsMessage(null);
				%>

				<div class="portlet-msg-info">
					<liferay-portlet:renderURL var="viewKBArticleURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					</liferay-portlet:renderURL>

					<%
					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"");
					sb.append(viewKBArticleURL);
					sb.append("\">");
					sb.append(BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(parentResourcePrimKey, WorkflowConstants.STATUS_ANY), "title"));
					sb.append("</a>");
					%>

					<c:choose>
						<c:when test="<%= total > 0 %>">
							<%= LanguageUtil.format(pageContext, "child-articles-for-x", sb.toString(), false) %>
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, "there-are-no-child-articles-for-x", sb.toString(), false) %>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

			<c:if test="<%= (total > 0) && (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_ARTICLES) || AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_KB_ARTICLES_PRIORITIES)) %>">
				<aui:button-row>
					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_ARTICLES) %>">
						<aui:button onClick='<%= renderResponse.getNamespace() + "deleteKBArticles();" %>' value="delete" />
					</c:if>

					<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_KB_ARTICLES_PRIORITIES) %>">
						<aui:button onClick='<%= renderResponse.getNamespace() + "updateKBArticlesPriorities();" %>' value="save" />
					</c:if>
				</aui:button-row>
			</c:if>

			<liferay-ui:search-iterator type='<%= searchTerms.hasSearchTerms() ? "more" : "regular" %>' />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />updateKBArticlesPriorities() {
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="updateKBArticlesPriorities"><portlet:param name="jspPage" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>");
	}

	Liferay.provide(
		window,
		'<portlet:namespace />deleteKBArticles',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-articles") %>')) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeys.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<liferay-portlet:actionURL name="deleteKBArticles"><portlet:param name="jspPage" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>