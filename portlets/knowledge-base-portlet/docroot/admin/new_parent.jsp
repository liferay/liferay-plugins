<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "parentResourcePrimKey");
double priority = BeanParamUtil.getDouble(kbArticle, request, "priority");
%>

<div class="kb-new-parent">
	<c:choose>
		<c:when test="<%= parentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
			<%= BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(parentResourcePrimKey, status), "title") %>
		</c:when>
		<c:otherwise>
			(<liferay-ui:message key="none" />)
		</c:otherwise>
	</c:choose>

	<aui:input cssClass="kb-priority" inlineField="<%= true %>" label="" name="priority" size="5" type="text" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />

	<liferay-portlet:renderURL var="selectKBArticleURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value='<%= jspPath + "select_article.jsp" %>' />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
		<portlet:param name="status" value="<%= String.valueOf(status) %>" />
	</liferay-portlet:renderURL>

	<%
	String taglibOnClick = "var selectKBArticleWindow = window.open('" + selectKBArticleURL + "&" + renderResponse.getNamespace() + "oldParentResourcePrimKey=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "parentResourcePrimKey.value, 'selectKBArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectKBArticleWindow.focus();";
	%>

	<div class="kb-edit-link">
		<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="select-article" /> &raquo;</aui:a>
	</div>
</div>