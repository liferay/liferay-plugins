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
%>

<liferay-ui:header
	title="article"
/>

<c:if test="<%= resourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">
	<aui:button-row cssClass="input-append">
		<c:choose>
			<c:when test="<%= resourceClassNameId == kbFolderClassNameId %>">

				<%
				KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(resourcePrimKey);
				%>

				<liferay-ui:input-resource url="<%= kbFolder.getName() %>" />
			</c:when>
			<c:otherwise>
				<liferay-ui:input-resource url='<%= BeanPropertiesUtil.getString(KBArticleServiceUtil.fetchLatestKBArticle(resourcePrimKey, WorkflowConstants.STATUS_APPROVED), "title") %>' />
			</c:otherwise>
		</c:choose>

		<%
		String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationKBObject('" + kbFolderClassNameId + "', '" + KBFolderConstants.DEFAULT_PARENT_FOLDER_ID + "', ''); window.close();";
		%>

		<aui:button onClick="<%= taglibOnClick %>" value="remove" />
	</aui:button-row>

	<div class="separator"><!-- --></div>
</c:if>

<div class="kb-select-article-breadcrumbs">
	<liferay-portlet:renderURL var="breadcrumbURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value='<%= templatePath + "select_configuration_object.jsp" %>' />
	</liferay-portlet:renderURL>

	<aui:a href="<%= breadcrumbURL %>"><liferay-ui:message key="home" /></aui:a> &raquo;

	<c:if test="<%= parentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID %>">

		<%
		List<Tuple> tuples = new ArrayList<Tuple>();

		long selParentResourcePrimKey = parentResourcePrimKey;
		long selParentResourceClassNameId = parentResourceClassNameId;

		while (selParentResourcePrimKey != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			if (selParentResourceClassNameId == kbFolderClassNameId) {
				KBFolder selKBFolder = KBFolderServiceUtil.getKBFolder(selParentResourcePrimKey);

				tuples.add(new Tuple(selKBFolder.getKbFolderId(), kbFolderClassNameId, StringUtil.shorten(selKBFolder.getName(), 30)));

				selParentResourcePrimKey = selKBFolder.getParentKBFolderId();
				selParentResourceClassNameId = selKBFolder.getClassNameId();
			}
			else {
				KBArticle selKBArticle = KBArticleServiceUtil.fetchLatestKBArticle(selParentResourcePrimKey, WorkflowConstants.STATUS_APPROVED);

				tuples.add(new Tuple(selKBArticle.getResourcePrimKey(), selKBArticle.getClassNameId(), StringUtil.shorten(selKBArticle.getTitle(), 30)));

				selParentResourcePrimKey = selKBArticle.getParentResourcePrimKey();
				selParentResourceClassNameId = selKBArticle.getParentResourceClassNameId();
			}
		}

		for (Tuple tuple: tuples) {
			breadcrumbURL = HttpUtil.setParameter(breadcrumbURL, "parentResourcePrimKey", (Long)tuple.getObject(0));
			breadcrumbURL = HttpUtil.setParameter(breadcrumbURL, "parentResourceClassName", (Long)tuple.getObject(1));
		%>

			<aui:a href="<%= breadcrumbURL %>"><%= tuple.getObject(2) %></aui:a> &raquo;

		<%
		}
		%>

	</c:if>
</div>

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
				String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationKBObject('" + kbFolderClassNameId + "', '" + kbFolder.getKbFolderId() + "', '" + UnicodeFormatter.toString(kbFolder.getName()) + "'); window.close();";
				%>

				<aui:button disabled="<%= (kbFolder.getKbFolderId() == resourcePrimKey) %>" onClick="<%= taglibOnClick %>" value="choose" />
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
			value='<%= kbArticle.getStatus() + " (" + LanguageUtil.get(request, WorkflowConstants.getStatusLabel(kbArticle.getStatus())) + ")" %>'
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
			String taglibOnClick = "opener." + PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION) + "selectConfigurationKBObject('" + kbArticle.getClassNameId() + "', '" + kbArticle.getResourcePrimKey() + "', '" + UnicodeFormatter.toString(kbArticle.getTitle()) + "'); window.close();";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="choose" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>