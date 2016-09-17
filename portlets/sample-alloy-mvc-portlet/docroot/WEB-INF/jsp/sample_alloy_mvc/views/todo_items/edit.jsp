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

<aui:model-context bean="${samTodoItem}" model="<%= SAMTodoItem.class %>" />

<portlet:actionURL var="updateSAMTodoItemURL">
	<portlet:param name="controller" value="todo_items" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<aui:form action="${updateSAMTodoItemURL}" method="post">
	<portlet:renderURL var="viewSAMTodoItemURL">
		<portlet:param name="controller" value="todo_items" />
		<portlet:param name="action" value="view" />
		<portlet:param name="samTodoItemId" value="${samTodoItem.samTodoItemId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewSAMTodoItemURL}" />
	<aui:input name="samTodoItemId" type="hidden" value="${samTodoItem.samTodoItemId}" />

	<aui:select label="todo-lists" name="samTodoListId">
		<c:forEach items="${samTodoLists}" var="samTodoList">
			<aui:option label="${samTodoList.name}" selected="${samTodoItem.samTodoListId == samTodoList.samTodoListId}" value="${samTodoList.samTodoListId}" />
		</c:forEach>
	</aui:select>

	<aui:select label="status" name="status" showEmptyOption="${false}">
		<aui:option label="${SAMTodoItemConstants.LABEL_COMPLETE}" value="${SAMTodoItemConstants.STATUS_COMPLETE}" />
		<aui:option label="${SAMTodoItemConstants.LABEL_INCOMPLETE}" value="${SAMTodoItemConstants.STATUS_INCOMPLETE}" />
	</aui:select>

	<aui:select label="priority" name="priority" showEmptyOption="${false}">
		<aui:option label="${SAMTodoItemConstants.LABEL_LOW}" value="${SAMTodoItemConstants.PRIORITY_LOW}" />
		<aui:option label="${SAMTodoItemConstants.LABEL_MODERATE}" value="${SAMTodoItemConstants.PRIORITY_MODERATE}" />
		<aui:option label="${SAMTodoItemConstants.LABEL_HIGH}" value="${SAMTodoItemConstants.PRIORITY_HIGH}" />
	</aui:select>

	<aui:input name="description" />

	<aui:button-row>
		<portlet:renderURL var="SAMTodoListURL">
			<portlet:param name="controller" value="todo_lists" />
			<portlet:param name="action" value="view" />
			<portlet:param name="id" value="${samTodoListId}" />
		</portlet:renderURL>

		<portlet:actionURL var="deleteSAMTodoItemURL">
			<portlet:param name="controller" value="todo_items" />
			<portlet:param name="action" value="delete" />
			<portlet:param name="samTodoItemId" value="${samTodoItem.samTodoItemId}" />
			<portlet:param name="redirect" value="${SAMTodoListURL}" />
		</portlet:actionURL>

		<aui:button href="${deleteSAMTodoItemURL}" icon="icon-trash" value="delete" />

		<aui:button icon="icon-ok" type="submit" value="update" />

		<aui:button href="${viewSAMTodoItemURL}" icon="icon-remove" value="cancel" />
	</aui:button-row>
</aui:form>