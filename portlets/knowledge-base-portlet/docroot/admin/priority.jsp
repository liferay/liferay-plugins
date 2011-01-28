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
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

int maxHumanPriority = PriorityHelperUtil.getMaxHumanPriority(scopeGroupId, parentResourcePrimKey, article);

int humanPriority = ParamUtil.getInteger(request, "humanPriority", maxHumanPriority);
%>

<div class="kb-priority">
	<c:if test="<%= parentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
		<%= BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(parentResourcePrimKey, WorkflowConstants.STATUS_ANY), "title") %>
	</c:if>

	<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="" name="humanPriority">

		<%
		for (int i = 1; i <= maxHumanPriority; i++) {
		%>

			<aui:option label="<%= i %>" selected="<%= humanPriority == i %>" />

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