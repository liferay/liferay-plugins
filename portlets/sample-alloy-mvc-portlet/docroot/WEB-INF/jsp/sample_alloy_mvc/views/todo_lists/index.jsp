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

<%@ include file="/WEB-INF/jsp/sample_alloy_mvc/views/init.jsp" %>

<aui:button-row>
	<portlet:renderURL var="createSAMTodoListURL">
		<portlet:param name="controller" value="todo_lists" />
		<portlet:param name="action" value="create" />
	</portlet:renderURL>

	<aui:button href="${createSAMTodoListURL}" icon="icon-plus" value="create" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="there-are-no-todo-lists" iteratorURL="${portletURL}" orderByCol="${orderByCol}" orderByType="${orderByType}">
	<liferay-ui:search-container-results
		results="${samTodoLists}"
	/>
	<liferay-ui:search-container-row
		className="com.liferay.samplealloymvc.model.SAMTodoList"
		escapedModel="${true}"
		keyProperty="samTodoListId"
		modelVar="samTodoList"
	>
		<portlet:renderURL var="viewSAMTodoListURL">
			<portlet:param name="controller" value="todo_lists" />
			<portlet:param name="action" value="view" />
			<portlet:param name="id" value="${samTodoList.samTodoListId}" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="${viewSAMTodoListURL}"
			orderable="${true}"
			property="name"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>