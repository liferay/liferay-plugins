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

<%@ include file="/display/init.jsp" %>

<%
long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId", kbFolderClassNameId);
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

String eventName = PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationKBObject";
%>

<div class="kb-select-article-search-containers">
	<c:if test="<%= resourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">
		<aui:button-row cssClass="input-append">
			<c:choose>
				<c:when test="<%= resourceClassNameId == kbFolderClassNameId %>">
					<liferay-ui:input-resource url='<%= BeanPropertiesUtil.getString(KBFolderLocalServiceUtil.fetchKBFolder(resourcePrimKey), "name") %>' />
				</c:when>
				<c:otherwise>
					<liferay-ui:input-resource url='<%= BeanPropertiesUtil.getString(KBArticleLocalServiceUtil.fetchLatestKBArticle(resourcePrimKey, WorkflowConstants.STATUS_APPROVED), "title") %>' />
				</c:otherwise>
			</c:choose>

			<%
			Map<String, Object> data = new HashMap<String, Object>();

			data.put("resourceClassNameId", kbFolderClassNameId);
			data.put("resourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);
			data.put("title", StringPool.BLANK);
			%>

			<aui:button cssClass="selector-button" data="<%= data %>" value="remove" />
		</aui:button-row>

		<div class="separator"><!-- --></div>
	</c:if>

	<%
	KnowledgeBaseUtil.addPortletBreadcrumbEntries(parentResourceClassNameId, parentResourcePrimKey, "/display/select_configuration_object.jsp", request, renderResponse);
	%>

	<liferay-ui:breadcrumb
		showCurrentGroup="<%= false %>"
		showCurrentPortlet="<%= false %>"
		showGuestGroup="<%= false %>"
		showLayout="<%= false %>"
		showParentGroups="<%= false %>"
	/>

	<c:if test="<%= parentResourceClassNameId == kbFolderClassNameId %>">
		<liferay-ui:search-container
			curParam="cur1"
			id="kbFoldersAdminSearchContainer"
			total="<%= KBFolderServiceUtil.getKBFoldersCount(scopeGroupId, parentResourcePrimKey) %>"
		>

			<liferay-ui:search-container-results
				results="<%= KBFolderServiceUtil.getKBFolders(scopeGroupId, parentResourcePrimKey, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.knowledgebase.model.KBFolder"
				escapedModel="<%= true %>"
				keyProperty="kbFolderId"
				modelVar="kbFolder"
			>

				<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/display/select_configuration_object.jsp" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbFolderClassNameId) %>" />
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

				<liferay-ui:search-container-column-text
					align="right"
				>

					<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("priority", KBArticleConstants.DEFAULT_PRIORITY);
					data.put("resourceClassNameId", kbFolder.getClassNameId());
					data.put("resourcePrimKey", kbFolder.getKbFolderId());
					data.put("title", kbFolder.getName());
					%>

					<aui:button
						cssClass="selector-button"
						data="<%= data %>"
						disabled="<%= (kbFolder.getKbFolderId() == resourcePrimKey) %>"
						value="choose"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</c:if>

	<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/display/select_configuration_object.jsp" />
		<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:search-container
		curParam="cur2"
		emptyResultsMessage="there-are-no-articles"
		iteratorURL="<%= iteratorURL %>"
		orderByCol="<%= orderByCol %>"
		orderByComparator="<%= KnowledgeBaseUtil.getKBArticleOrderByComparator(orderByCol, orderByType) %>"
		orderByType="<%= orderByType %>"
		total="<%= KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, parentResourcePrimKey, WorkflowConstants.STATUS_APPROVED) %>"
	>
		<liferay-ui:search-container-results
			results="<%= KBArticleServiceUtil.getKBArticles(scopeGroupId, parentResourcePrimKey, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.knowledgebase.model.KBArticle"
			escapedModel="<%= true %>"
			keyProperty="resourcePrimKey"
			modelVar="kbArticle"
		>
			<liferay-portlet:renderURL var="rowURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/display/select_configuration_object.jsp" />
				<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
				<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			</liferay-portlet:renderURL>

			<%
			if (KBArticleServiceUtil.getKBArticlesCount(scopeGroupId, kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED) == 0) {
				rowURL = null;
			}
			%>

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
				value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(pageContext, WorkflowConstants.getStatusLabel(kbArticle.getStatus())) + ")" %>'
			/>

			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="views"
				orderable="<%= true %>"
				orderableProperty="view-count"
				property="viewCount"
			/>

			<liferay-ui:search-container-column-text
				align="right"
			>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("resourceClassNameId", kbArticle.getClassNameId());
				data.put("resourcePrimKey", kbArticle.getResourcePrimKey());
				data.put("title", kbArticle.getTitle());
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('.kb-select-article-search-containers').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>