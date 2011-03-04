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

Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey");
long priority = BeanParamUtil.getLong(article, request, "priority");

int humanPriority = ParamUtil.getInteger(request, "humanPriority", PriorityHelper.getHumanPriority(scopeGroupId, resourcePrimKey, parentResourcePrimKey, priority));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="<%= article.getTitle() %>"
/>

<portlet:actionURL name="moveArticle" var="moveArticleURL">
	<portlet:param name="jspPage" value="/admin/move_article.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= moveArticleURL %>" method="post" name="fm">
	<aui:input name="resourcePrimKey" type="hidden" value="<%= resourcePrimKey %>" />
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= parentResourcePrimKey %>" />

	<aui:fieldset>
		<aui:field-wrapper label="current-parent">
			<c:choose>
				<c:when test="<%= !article.isRoot() %>">
					<%= BeanPropertiesUtil.getString(ArticleServiceUtil.getLatestArticle(article.getParentResourcePrimKey(), WorkflowConstants.STATUS_ANY), "title") %>
				</c:when>
				<c:otherwise>
					(<liferay-ui:message key="none" />)
				</c:otherwise>
			</c:choose>
		</aui:field-wrapper>

		<aui:field-wrapper label="new-parent">
			<div id="<portlet:namespace />newParent">
				<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>">
					<liferay-util:param name="humanPriority" value="<%= String.valueOf(humanPriority) %>" />
				</liferay-util:include>
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