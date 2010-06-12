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

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
String content = BeanParamUtil.getString(article, request, "content");
int priority = BeanParamUtil.getInteger(article, request, "priority", ArticleConstants.DEFAULT_PRIORITY);
%>

<portlet:actionURL name="updateArticle" var="updateArticleURL">
	<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= updateArticleURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateArticle();" %>'>
	<aui:input name="resourcePrimKey" type="hidden" value="<%= resourcePrimKey %>" />
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= parentResourcePrimKey %>" />

	<liferay-ui:tabs
		backURL="<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>"
		names="article"
	/>

	<liferay-ui:error exception="<%= ArticleContentException.class %>" message="please-enter-valid-content" />
	<liferay-ui:error exception="<%= ArticleTitleException.class %>" message="please-enter-a-valid-title" />

	<aui:model-context bean="<%= article %>" model="<%= Article.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor width="100%" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<c:if test="<%= enableArticleDescription %>">
			<aui:input name="description" />
		</c:if>

		<aui:field-wrapper label="display-order">
			<div id="<portlet:namespace />priority">
				<liferay-util:include page="/admin/article_priority.jsp" servletContext="<%= application %>">
					<liferay-util:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<liferay-util:param name="parentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
					<liferay-util:param name="priority" value="<%= String.valueOf(priority) %>" />
				</liferay-util:include>
			</div>

			<c:if test="<%= (article == null) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && ArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE)) %>">
				<portlet:renderURL var="selectArticleURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="jspPage" value="/admin/select_article.jsp" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>" />
				</portlet:renderURL>

				<%
				String selectArticleOnClick = "var selectArticleWindow = window.open('" + selectArticleURL + "&" + renderResponse.getNamespace() + "oldParentResourcePrimKey=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "parentResourcePrimKey.value,'selectArticle','directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectArticleWindow.focus();";
				%>

				<div class="kb-edit-link">
					<aui:a href="javascript:;" onClick="<%= selectArticleOnClick %>"><liferay-ui:message key="select-parent-article" /> &raquo;</aui:a>
				</div>
			</c:if>
		</aui:field-wrapper>

		<c:if test="<%= article == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= Article.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" value="publish" />

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />selectArticle(parentResourcePrimKey, html) {
		document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = parentResourcePrimKey;
		document.getElementById("<portlet:namespace />priority").innerHTML = html;
	}

	function <portlet:namespace />updateArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>