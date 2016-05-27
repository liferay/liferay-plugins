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

<aui:model-context bean="${samTodoList}" model="<%= SAMTodoList.class %>" />

<portlet:actionURL var="updateSAMTodoListURL">
	<portlet:param name="controller" value="todo_lists" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<aui:form action="${updateSAMTodoListURL}" method="post">
	<portlet:renderURL var="viewSAMTodoListURL">
		<portlet:param name="controller" value="todo_lists" />
		<portlet:param name="action" value="view" />
		<portlet:param name="id" value="${samTodoList.samTodoListId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewSAMTodoListURL}" />
	<aui:input name="id" type="hidden" value="${samTodoList.samTodoListId}" />

	<aui:input name="name" />

	<aui:button-row>
		<portlet:renderURL var="SAMTodoListsURL">
			<portlet:param name="controller" value="todo_lists" />
			<portlet:param name="action" value="index" />
		</portlet:renderURL>

		<portlet:actionURL var="deleteSAMTodoListURL">
			<portlet:param name="controller" value="todo_lists" />
			<portlet:param name="action" value="delete" />
			<portlet:param name="id" value="${samTodoList.samTodoListId}" />
			<portlet:param name="redirect" value="${SAMTodoListsURL}" />
		</portlet:actionURL>

		<aui:button href="${deleteSAMTodoListURL}" icon="icon-trash" value="delete" />

		<aui:button icon="icon-ok" type="submit" value="update" />

		<aui:button href="${viewSAMTodoListURL}" icon="icon-remove" value="cancel" />
	</aui:button-row>
</aui:form>