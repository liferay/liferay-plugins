<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");

String parentArticleTitle = ParamUtil.getString(request, "parentArticleTitle");
int priority = ParamUtil.getInteger(request, "priority");
int priorityMax = ParamUtil.getInteger(request, "priorityMax");
%>

<div class="kb-priority">
	<c:if test="<%= Validator.isNotNull(parentArticleTitle) %>">
		<%= parentArticleTitle %>
	</c:if>

	<aui:select inlineField="<%= true %>" label="" name="priority">

		<%
		for (int i = 0; i < priorityMax; i++) {
		%>

			<aui:option label="<%= i + 1 %>" selected="<%= priority == i %>" value="<%= i %>" />

		<%
		}
		%>

	</aui:select>

	<portlet:renderURL var="selectArticleURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/admin/select_article.jsp" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
	</portlet:renderURL>

	<%
	String taglibOnClick = "var selectArticleWindow = window.open('" + selectArticleURL + "&" + renderResponse.getNamespace() + "oldParentResourcePrimKey=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "parentResourcePrimKey.value, 'selectArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectArticleWindow.focus();";
	%>

	<div class="kb-edit-link">
		<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="select-parent-article" /> &raquo;</aui:a>
	</div>
</div>