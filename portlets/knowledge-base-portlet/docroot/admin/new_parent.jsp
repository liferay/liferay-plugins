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
Integer status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey");
double priority = BeanParamUtil.getDouble(article, request, "priority");
%>

<div class="kb-new-parent">
	<c:choose>
		<c:when test="<%= parentResourcePrimKey != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
			<%= BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(parentResourcePrimKey, status.intValue()), "title") %>
		</c:when>
		<c:otherwise>
			(<liferay-ui:message key="none" />)
		</c:otherwise>
	</c:choose>

	<aui:input cssClass="kb-priority" inlineField="<%= true %>" label="" name="priority" size="5" type="text" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />

	<portlet:renderURL var="selectArticleURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "select_article.jsp" %>' />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
		<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
	</portlet:renderURL>

	<%
	String taglibOnClick = "var selectArticleWindow = window.open('" + selectArticleURL + "&" + renderResponse.getNamespace() + "oldParentResourcePrimKey=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "parentResourcePrimKey.value, 'selectArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectArticleWindow.focus();";
	%>

	<div class="kb-edit-link">
		<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="select-article" /> &raquo;</aui:a>
	</div>
</div>