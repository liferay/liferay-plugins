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

<aui:fieldset>
	<aui:field-wrapper name="name">
		${samTodoList.name}
	</aui:field-wrapper>
</aui:fieldset>

<aui:button-row>
	<portlet:renderURL var="SAMTodoListsURL">
		<portlet:param name="controller" value="todo_lists" />
		<portlet:param name="action" value="index" />
	</portlet:renderURL>

	<aui:button href="${SAMTodoListsURL}" icon="icon-arrow-left" value="back" />

	<portlet:renderURL var="editSAMTodoListURL">
		<portlet:param name="controller" value="todo_lists" />
		<portlet:param name="action" value="edit" />
		<portlet:param name="id" value="${samTodoList.samTodoListId}" />
	</portlet:renderURL>

	<aui:button href="${editSAMTodoListURL}" icon="icon-pencil" value="edit" />

	<portlet:renderURL var="createSAMTodoItemURL">
		<portlet:param name="controller" value="todo_items" />
		<portlet:param name="action" value="create" />
		<portlet:param name="samTodoListId" value="${samTodoList.samTodoListId}" />
	</portlet:renderURL>

	<aui:button href="${createSAMTodoItemURL}" icon="icon-plus" value="create-todo-item" />
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="there-are-no-items-for-this-list" iteratorURL="${portletURL}" orderByCol="${samTodoItemsOrderByCol}" orderByColParam="samTodoItemsOrderByCol" orderByType="${samTodoItemsOrderByType}" orderByTypeParam="samTodoItemsOrderByType">
	<liferay-ui:search-container-results
		results="${samTodoItems}"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.samplealloymvc.model.SAMTodoItem"
		escapedModel="${true}"
		keyProperty="samTodoItemId"
		modelVar="samTodoItem"
	>
		<portlet:renderURL var="viewSAMTodoItemURL">
			<portlet:param name="controller" value="todo_items" />
			<portlet:param name="action" value="view" />
			<portlet:param name="id" value="${samTodoItem.samTodoItemId}" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="${viewSAMTodoItemURL}"
			name="priority"
			orderable="${true}"
			orderableProperty="priority"
			translate="${true}"
			value="${SAMTodoItemConstantsMethods.getPriorityLabel(samTodoItem.priority)}"
		/>

		<liferay-ui:search-container-column-text
			href="${viewSAMTodoItemURL}"
			name="description"
			orderable="${true}"
			orderableProperty="priority"
			value="${samTodoItem.description}"
		/>

		<liferay-ui:search-container-column-text
			href="${viewSAMTodoItemURL}"
			name="status"
			orderable="${true}"
			orderableProperty="status"
			translate="${true}"
			value="${SAMTodoItemConstantsMethods.getStatusLabel(samTodoItem.status)}"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>