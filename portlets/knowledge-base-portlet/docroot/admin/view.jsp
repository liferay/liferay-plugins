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

<%@ include file="/admin/init.jsp" %>

<%
long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId", kbFolderClassNameId);
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="resourcePrimKeys" type="hidden" />

	<liferay-ui:error exception="<%= KBArticlePriorityException.class %>" message='<%= LanguageUtil.format(pageContext, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

	<c:if test='<%= SessionMessages.contains(renderRequest, "importedKBArticlesCount") %>'>

		<%
		int importedKBArticlesCount = GetterUtil.getInteger(SessionMessages.get(renderRequest, "importedKBArticlesCount"));
		%>

		<c:choose>
			<c:when test="<%= importedKBArticlesCount > 0 %>">
				<div class="alert alert-success">
					<liferay-ui:message key="your-request-completed-successfully" />

					<liferay-ui:message arguments="<%= importedKBArticlesCount %>" key="a-total-of-x-articles-have-been-imported" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-warning">
					<liferay-ui:message
						arguments="<%= StringUtil.merge(PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_EXTENSIONS, StringPool.COMMA_AND_SPACE) %>"
						key="nothing-was-imported-no-articles-were-found-with-one-of-the-supported-extensions-x"
					/>
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>

	<liferay-portlet:renderURL varImpl="iteratorURL">
		<portlet:param name="mvcPath" value="/admin/view.jsp" />
		<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
	</liferay-portlet:renderURL>

	<%
	boolean hasDeleteKBArticlesPermission = AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.DELETE_KB_ARTICLES);
	boolean hasUpdateKBArticlesPrioritiesPermission = AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.UPDATE_KB_ARTICLES_PRIORITIES);
	%>

	<aui:fieldset>
		<aui:nav-bar>
			<aui:nav cssClass="navbar-nav">
				<c:if test="<%= hasDeleteKBArticlesPermission || hasUpdateKBArticlesPrioritiesPermission %>">
					<aui:nav-item cssClass="hide" dropdown="<%= true %>" id="actionsButton" label="actions">
						<c:if test="<%= hasUpdateKBArticlesPrioritiesPermission %>">
							<aui:nav-item iconCssClass="icon-save" id="updateKBArticlesPriorities" label="save" />
						</c:if>

						<c:if test="<%= hasDeleteKBArticlesPermission %>">
							<aui:nav-item cssClass="item-remove" iconCssClass="icon-remove" id="deleteKBArticles" label="delete" />
						</c:if>
					</aui:nav-item>
				</c:if>

				<%
				boolean hasAddKBArticlePermission = false;
				boolean hasAddKBFolderPermission = false;

				if (parentResourceClassNameId == kbFolderClassNameId) {
					hasAddKBArticlePermission = KBFolderPermission.contains(permissionChecker, scopeGroupId, parentResourcePrimKey, ActionKeys.ADD_KB_ARTICLE);
					hasAddKBFolderPermission = KBFolderPermission.contains(permissionChecker, scopeGroupId, parentResourcePrimKey, ActionKeys.ADD_KB_FOLDER);
				}
				else {
					hasAddKBArticlePermission = AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE);
				}
				%>

				<c:if test="<%= hasAddKBArticlePermission || hasAddKBFolderPermission %>">
					<aui:nav-item dropdown="<%= true %>" label="add">
						<c:if test="<%= hasAddKBArticlePermission %>">
							<liferay-util:include page="/admin/common/add_article_button.jsp" servletContext="<%= application %>" />
						</c:if>

						<c:if test="<%= hasAddKBFolderPermission %>">
							<liferay-util:include page="/admin/common/add_folder_button.jsp" servletContext="<%= application %>" />
						</c:if>

						<c:if test="<%= (parentResourceClassNameId == kbFolderClassNameId) && hasAddKBArticlePermission %>">
							<liferay-util:include page="/admin/import_articles_button.jsp" servletContext="<%= application %>" />
						</c:if>
					</aui:nav-item>
				</c:if>

				<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
					<liferay-security:permissionsURL
						modelResource="com.liferay.knowledgebase.admin"
						modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
						resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
						var="permissionsURL"
						windowState="<%= LiferayWindowState.POP_UP.toString() %>"
					/>

					<aui:nav-item href="<%= permissionsURL %>" label="permissions" useDialog="<%= true %>" />
				</c:if>
			</aui:nav>

			<aui:nav-bar-search
				cssClass="navbar-search-advanced"
			>

				<%
				request.setAttribute("view.jsp-displayTerms", new KBArticleDisplayTerms(renderRequest));
				%>

				<liferay-ui:search-form
					page="/admin/article_search.jsp"
					servletContext="<%= application %>"
				/>
			</aui:nav-bar-search>
		</aui:nav-bar>

		<%
		KnowledgeBaseUtil.addPortletBreadcrumbEntries(parentResourceClassNameId, parentResourcePrimKey, "/admin/view.jsp", request, renderResponse);
		%>

		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showCurrentPortlet="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= false %>"
			showParentGroups="<%= false %>"
		/>

		<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
			<div class="kb-admin-tools">
				<c:choose>
					<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), scopeGroupId) %>">
						<liferay-portlet:actionURL name="unsubscribeGroupKBArticles" var="unsubscribeGroupKBArticlesURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
						</liferay-portlet:actionURL>

						<liferay-ui:icon
							iconCssClass="icon-remove-sign"
							label="<%= true %>"
							message="unsubscribe"
							url="<%= unsubscribeGroupKBArticlesURL %>"
						/>
					</c:when>
					<c:otherwise>
						<liferay-portlet:actionURL name="subscribeGroupKBArticles" var="subscribeGroupKBArticlesURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
						</liferay-portlet:actionURL>

						<liferay-ui:icon
							iconCssClass="icon-ok-sign"
							label="<%= true %>"
							message="subscribe"
							url="<%= subscribeGroupKBArticlesURL %>"
						/>
					</c:otherwise>
				</c:choose>
			</div>
		</c:if>

		<c:if test="<%= parentResourceClassNameId == kbFolderClassNameId %>">
			<liferay-ui:search-container
				curParam="cur1"
				id="kbFoldersAdminSearchContainer"
				total="<%= KBFolderServiceUtil.getKBFoldersCount(scopeGroupId, parentResourcePrimKey) %>"
			>

				<%
				searchContainer.setResults(KBFolderServiceUtil.getKBFolders(scopeGroupId, parentResourcePrimKey, searchContainer.getStart(), searchContainer.getEnd()));
				%>

				<liferay-ui:search-container-row
					className="com.liferay.knowledgebase.model.KBFolder"
					escapedModel="<%= true %>"
					keyProperty="kbFolderId"
					modelVar="kbFolder"
				>

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="mvcPath" value="/admin/view.jsp" />
						<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbFolder.getClassNameId()) %>" />
						<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbFolder.getKbFolderId()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container-column-text
						name="folder"
					>
						<a class="icon-folder-open" href="<%= rowURL %>">
							<%= kbFolder.getName() %>
						</a>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="author"
						property="userName"
					/>

					<liferay-ui:search-container-column-date
						href="<%= rowURL %>"
						name="create-date"
						property="createDate"
					/>

					<liferay-ui:search-container-column-date
						href="<%= rowURL %>"
						name="modified-date"
						property="modifiedDate"
					/>

					<liferay-ui:search-container-column-jsp
						align="right"
						path="/admin/folder_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</c:if>

		<liferay-ui:search-container
			curParam="cur2"
			id="kbArticlesAdminSearchContainer"
			rowChecker="<%= (hasDeleteKBArticlesPermission || hasUpdateKBArticlesPrioritiesPermission) ? new RowChecker(renderResponse) : null %>"
			searchContainer="<%= new KBArticleSearch(renderRequest, iteratorURL) %>"
		>

			<%
			KBArticleSearchTerms searchTerms = (KBArticleSearchTerms)searchContainer.getSearchTerms();
			%>

			<%@ include file="/admin/article_search_results.jspf" %>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBArticle"
				escapedModel="<%= true %>"
				keyProperty="resourcePrimKey"
				modelVar="kbArticle"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="mvcPath" value="/admin/view.jsp" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				</liferay-portlet:renderURL>

				<%
				if (KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_ANY) == 0) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					name="priority"
					orderable="<%= true %>"
				>
					<c:choose>
						<c:when test="<%= hasUpdateKBArticlesPrioritiesPermission %>">
							<aui:input cssClass="kb-article-priority" label="" name='<%= "priority" + kbArticle.getResourcePrimKey() %>' size="5" title="priority" type="text" value="<%= BigDecimal.valueOf(kbArticle.getPriority()).toPlainString() %>" />
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

				<liferay-ui:search-container-column-date
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value="<%= kbArticle.getCreateDate() %>"
				/>

				<liferay-ui:search-container-column-date
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="modified-date"
					orderable="<%= true %>"
					value="<%= kbArticle.getModifiedDate() %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="kb-column-no-wrap"
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
				>
					<aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>" status="<%= kbArticle.getStatus() %>" />
				</liferay-ui:search-container-column-text>

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

			<c:if test="<%= !searchTerms.hasSearchTerms() && (parentResourceClassNameId != kbFolderClassNameId) && (parentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) %>">

				<%
				searchContainer.setEmptyResultsMessage(null);
				%>

				<div class="alert alert-info">

					<%
					KBArticle parentKBArticle = KBArticleServiceUtil.getLatestKBArticle(parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

					PortletURL viewKBArticleURL = kbArticleURLHelper.createViewURL(parentKBArticle);

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"");
					sb.append(viewKBArticleURL.toString());
					sb.append("\">");
					sb.append(BeanPropertiesUtil.getString(parentKBArticle, "title"));
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

			<liferay-ui:search-iterator type='<%= searchTerms.hasSearchTerms() ? "more" : "regular" %>' />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base,liferay-util-list-fields">
	var deleteKBArticles = A.one('#<portlet:namespace />deleteKBArticles');
	var kbArticlesAdminSearchContainer = A.one('#<portlet:namespace />kbArticlesAdminSearchContainer');
	var updateKBArticlesPriorities = A.one('#<portlet:namespace />updateKBArticlesPriorities');

	if (updateKBArticlesPriorities) {
		updateKBArticlesPriorities.on(
			'click',
			function() {
				document.<portlet:namespace />fm.method = 'post';

				submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateKBArticlesPriorities"><portlet:param name="mvcPath" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>');
			}
		);
	}

	if (deleteKBArticles) {
		deleteKBArticles.on(
			'click',
			function() {
				if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-articles") %>')) {
					document.<portlet:namespace />fm.method = 'post';
					document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKeys.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

					submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="deleteKBArticles"><portlet:param name="mvcPath" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>');
				}
			}
		);
	}

	kbArticlesAdminSearchContainer.delegate(
		'click',
		function() {
			var hide = (Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace /><%= RowChecker.ALL_ROW_IDS %>').length == 0);

			var actionsButton = A.one('#<portlet:namespace />actionsButton');

			if (actionsButton) {
				actionsButton.toggle(!hide);
			}
		},
		'input[type=checkbox]'
	);

	kbArticlesAdminSearchContainer.delegate(
		'key',
		function() {
			var parentTr = this.ancestor('tr');

			var rowIdsNode = parentTr.one('input[type="checkbox"]');

			rowIdsNode.attr('checked', true);

			document.<portlet:namespace />fm.method = 'post';

			submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="updateKBArticlesPriorities"><portlet:param name="mvcPath" value="/admin/view.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></liferay-portlet:actionURL>');
		},
		'enter',
		'input[type=text]'
	);
</aui:script>