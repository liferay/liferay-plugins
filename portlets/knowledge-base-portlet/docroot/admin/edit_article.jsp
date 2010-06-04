<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

String content = BeanParamUtil.getString(article, request, "content");
%>

<script type="text/javascript">
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />updateArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<portlet:actionURL name="updateArticle" var="updateArticleURL">
	<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<form action="<%= updateArticleURL %>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />updateArticle(); return false;">
<input name="<portlet:namespace />resourcePrimKey" type="hidden" value="<%= resourcePrimKey %>" />

<liferay-ui:tabs
	backURL="<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>"
	names="article"
/>

<liferay-ui:error exception="<%= ArticleContentException.class %>" message="please-enter-valid-content" />
<liferay-ui:error exception="<%= ArticleTitleException.class %>" message="please-enter-a-valid-title" />

<strong><liferay-ui:message key="title" /></strong><br />

<liferay-ui:input-field model="<%= Article.class %>" bean="<%= article %>" field="title" />

<br /><br />

<strong><liferay-ui:message key="content" /></strong><br />

<liferay-ui:input-editor width="100%" /><br />

<input name="<portlet:namespace />content" type="hidden" />

<c:if test="<%= enableArticleDescription %>">
	<strong><liferay-ui:message key="description" /></strong><br />

	<liferay-ui:input-field model="<%= Article.class %>" bean="<%= article %>" field="description" />

	<br /><br />
</c:if>

<c:if test="<%= article == null %>">
	<strong><liferay-ui:message key="permissions" /></strong><br />

	<liferay-ui:input-permissions
		modelName="<%= Article.class.getName() %>"
	/>

	<br />
</c:if>

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>