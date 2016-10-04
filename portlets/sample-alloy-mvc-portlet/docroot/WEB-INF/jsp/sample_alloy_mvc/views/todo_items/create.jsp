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

<portlet:actionURL var="addSAMTodoItemURL">
	<portlet:param name="controller" value="todo_items" />
	<portlet:param name="action" value="add" />
	<portlet:param name="samTodoListId" value="${samTodoListId}" />
</portlet:actionURL>

<aui:form action="${addSAMTodoItemURL}" method="post">
	<portlet:renderURL var="SAMTodoListURL">
		<portlet:param name="controller" value="todo_lists" />
		<portlet:param name="action" value="view" />
		<portlet:param name="id" value="${samTodoListId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${SAMTodoListURL}" />

	<aui:select label="priority" name="priority">
		<aui:option label="${SAMTodoItemConstants.LABEL_LOW}" selected="${true}" value="${SAMTodoItemConstants.PRIORITY_LOW}" />
		<aui:option label="${SAMTodoItemConstants.LABEL_MODERATE}" value="${SAMTodoItemConstants.PRIORITY_MODERATE}" />
		<aui:option label="${SAMTodoItemConstants.LABEL_HIGH}" value="${SAMTodoItemConstants.PRIORITY_HIGH}" />
	</aui:select>

	<aui:input name="description" />

	<aui:button-row>
		<aui:button icon="icon-plus" type="submit" value="add" />

		<aui:button href="${SAMTodoListURL}" icon="icon-remove" value="cancel" />
	</aui:button-row>
</aui:form>