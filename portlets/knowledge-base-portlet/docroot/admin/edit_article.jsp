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

Template template = (Template)request.getAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE);

long resourcePrimKey = BeanParamUtil.getLong(article, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(article, request, "parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
int version = BeanParamUtil.getInteger(article, request, "version", ArticleConstants.DEFAULT_VERSION);
String content = BeanParamUtil.getString(article, request, "content", BeanPropertiesUtil.getString(template, "content"));
int priority = BeanParamUtil.getInteger(article, request, "priority", ArticleConstants.DEFAULT_PRIORITY);
int status = BeanParamUtil.getInteger(article, request, "status", WorkflowConstants.STATUS_DRAFT);

String dirName = ParamUtil.getString(request, "dirName");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (article != null) ? article.getTitle() : "new-article" %>'
/>

<portlet:actionURL name="updateArticle" var="updateArticleURL">
	<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= updateArticleURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateArticle();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="resourcePrimKey" type="hidden" value="<%= resourcePrimKey %>" />
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= parentResourcePrimKey %>" />
	<aui:input name="dirName" type="hidden" value="<%= dirName %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_SAVE_DRAFT %>" />

	<liferay-ui:error exception="<%= ArticleContentException.class %>" message="please-enter-valid-content" />
	<liferay-ui:error exception="<%= ArticleTitleException.class %>" message="please-enter-a-valid-title" />
	<liferay-ui:asset-tags-error />

	<c:choose>
		<c:when test="<%= status == WorkflowConstants.STATUS_APPROVED %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="a-new-version-will-be-created-automatically-if-this-content-is-modified" />
			</div>
		</c:when>
		<c:when test="<%= status == WorkflowConstants.STATUS_PENDING %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
			</div>
		</c:when>
	</c:choose>

	<aui:model-context bean="<%= article %>" model="<%= Article.class %>" />

	<c:if test="<%= article != null %>">
		<aui:workflow-status id="<%= String.valueOf(resourcePrimKey) %>" status="<%= status %>" version="<%= String.valueOf(version) %>" />
	</c:if>

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
				<liferay-util:include page="/admin/article_priority.jsp" servletContext="<%= application %>" />
			</div>

			<c:if test="<%= (article == null) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && ArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE)) %>">
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
			</c:if>
		</aui:field-wrapper>

		<aui:field-wrapper label="attachments">
			<div id="<portlet:namespace />attachments">
				<liferay-util:include page="/admin/article_attachments.jsp" servletContext="<%= application %>" />
			</div>

			<portlet:renderURL var="attachmentsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="jspPage" value="/admin/attachments.jsp" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			</portlet:renderURL>

			<portlet:actionURL name="updateAttachments" var="updateAttachmentsURL">
				<portlet:param name="redirect" value="<%= attachmentsURL %>" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
			</portlet:actionURL>

			<%
			String taglibOnClick = "var editAttachmentsWindow = window.open('" + updateAttachmentsURL + "&" + renderResponse.getNamespace() + "dirName=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "dirName.value, 'editAttachments', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); editAttachmentsWindow.focus();";
			%>

			<div class="kb-edit-link">
				<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="edit-attachments" /> &raquo;</aui:a>
			</div>
		</aui:field-wrapper>

		<c:if test="<%= enableArticleAssetCategories %>">
			<aui:input classPK="<%= (article != null) ? article.getClassPK() : 0 %>" name="categories" type="assetCategories" />
		</c:if>

		<c:if test="<%= enableArticleAssetTags %>">
			<aui:input classPK="<%= (article != null) ? article.getClassPK() : 0 %>" name="tags" type="assetTags" />
		</c:if>

		<c:if test="<%= article == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= Article.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" value='<%= ((status == WorkflowConstants.STATUS_APPROVED) || (status == WorkflowConstants.STATUS_DRAFT)) ? "save-as-draft" : "save" %>' />

			<c:if test="<%= status != WorkflowConstants.STATUS_PENDING %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "publishArticle();" %>' value='<%= WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, Article.class.getName()) ? "submit-for-publication" : "publish" %>' />
			</c:if>

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	function <portlet:namespace />publishArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />workflowAction.value = "<%= WorkflowConstants.ACTION_PUBLISH %>";
		<portlet:namespace />updateArticle();
	}

	function <portlet:namespace />selectArticle(parentResourcePrimKey, html) {
		document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = parentResourcePrimKey;
		document.getElementById("<portlet:namespace />priority").innerHTML = html;
	}

	function <portlet:namespace />updateArticle() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (article == null) ? Constants.ADD : Constants.UPDATE %>";
		document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateAttachments(dirName, html) {
		document.<portlet:namespace />fm.<portlet:namespace />dirName.value = dirName;
		document.getElementById("<portlet:namespace />attachments").innerHTML = html;
	}
</aui:script>