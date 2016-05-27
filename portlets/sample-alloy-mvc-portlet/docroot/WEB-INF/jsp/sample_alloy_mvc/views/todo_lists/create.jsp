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

<portlet:actionURL var="addSAMTodoListURL">
	<portlet:param name="controller" value="todo_lists" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addSAMTodoListURL}" method="post">
	<portlet:renderURL var="SAMTodoListsURL">
		<portlet:param name="controller" value="todo_lists" />
		<portlet:param name="action" value="index" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${SAMTodoListsURL}" />

	<aui:input name="name" />

	<aui:button-row>
		<aui:button icon="icon-plus" type="submit" value="add" />

		<aui:button href="${SAMTodoListsURL}" icon="icon-remove" value="cancel" />
	</aui:button-row>
</aui:form>