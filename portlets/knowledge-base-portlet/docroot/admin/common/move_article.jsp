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
String redirect = ParamUtil.getString(request, "redirect");

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey");
double priority = BeanParamUtil.getDouble(article, request, "priority");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="<%= article.getTitle() %>"
/>

<liferay-portlet:actionURL name="moveArticle" var="moveArticleURL">
	<portlet:param name="jspPage" value='<%= jspPath + "move_article.jsp" %>' />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
	<portlet:param name="status" value="<%= String.valueOf(status) %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= moveArticleURL %>" method="post" name="fm">
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= parentResourcePrimKey %>" />

	<liferay-ui:error exception="<%= ArticlePriorityException.class %>" message='<%= LanguageUtil.format(pageContext, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

	<aui:fieldset>
		<aui:field-wrapper label="current-parent">
			<c:choose>
				<c:when test="<%= !article.isRoot() %>">
					<%= BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(article.getParentResourcePrimKey(), status), "title") %>
				</c:when>
				<c:otherwise>
					(<liferay-ui:message key="none" />)
				</c:otherwise>
			</c:choose>

			<span class="kb-priority"><%= BigDecimal.valueOf(priority).toPlainString() %></span>
		</aui:field-wrapper>

		<aui:field-wrapper label="new-parent">
			<div id="<portlet:namespace />newParent">
				<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>" />
			</div>
		</aui:field-wrapper>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />selectArticle(parentResourcePrimKey, html) {
		document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = parentResourcePrimKey;
		document.getElementById("<portlet:namespace />newParent").innerHTML = html;
	}
</aui:script>