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
KBTemplate kbTemplate = (KBTemplate)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);
%>

<liferay-ui:error exception="<%= RequiredKBTemplateException.class %>">

	<%
	RequiredKBTemplateException rkbte = (RequiredKBTemplateException)errorException;

	KBTemplate selKBTemplate = rkbte.getKBTemplate();
	%>

	<liferay-portlet:renderURL var="kbTemplateKBArticlesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value='<%= jspPath + "template_articles.jsp" %>' />
		<portlet:param name="kbTemplateId" value="<%= String.valueOf(selKBTemplate.getKbTemplateId()) %>" />
	</liferay-portlet:renderURL>

	<%
	StringBundler kbArticlesSB = new StringBundler(5);

	kbArticlesSB.append("<a href=\"javascript:var kbTemplateKBArticlesWindow = window.open('");
	kbArticlesSB.append(kbTemplateKBArticlesURL);
	kbArticlesSB.append("', 'kbTemplateKBArticles', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); kbTemplateKBArticlesWindow.focus();\">");
	kbArticlesSB.append(LanguageUtil.get(pageContext, "articles"));
	kbArticlesSB.append("</a>");
	%>

	<liferay-portlet:renderURL var="viewKBTemplateURL">
		<portlet:param name="jspPage" value='<%= jspPath + "view_template.jsp" %>' />
		<portlet:param name="kbTemplateId" value="<%= String.valueOf(selKBTemplate.getKbTemplateId()) %>" />
	</liferay-portlet:renderURL>

	<%
	StringBundler kbTemplateSB = new StringBundler(5);

	kbTemplateSB.append("<a href=\"");
	kbTemplateSB.append(viewKBTemplateURL);
	kbTemplateSB.append("\">");
	kbTemplateSB.append(selKBTemplate.getTitle());
	kbTemplateSB.append("</a>");
	%>

	<%= LanguageUtil.format(pageContext, "please-update-these-x-before-deleting-x", new String[] {kbArticlesSB.toString(), kbTemplateSB.toString()}, false) %>
</liferay-ui:error>

<div class="float-container kb-entity-header">
	<div class="kb-title">
		<%= kbTemplate.getTitle() %>
	</div>

	<div class="kb-tools">
		<liferay-util:include page="/admin/template_tools.jsp" servletContext="<%= application %>" />
	</div>
</div>

<div class="kb-entity-body">

	<%
	request.setAttribute("template_icons.jsp-kb_template", kbTemplate);
	%>

	<liferay-util:include page="/admin/template_icons.jsp" servletContext="<%= application %>" />

	<pre><%= HtmlUtil.escape(kbTemplate.getContent()) %></pre>

	<liferay-util:include page="/admin/template_comments.jsp" servletContext="<%= application %>" />
</div>