<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

KBTemplate kbTemplate = (KBTemplate)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_TEMPLATE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "parentResourcePrimKey", KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
String content = BeanParamUtil.getString(kbArticle, request, "content", BeanPropertiesUtil.getString(kbTemplate, "content"));
String[] sections = AdminUtil.unescapeSections(BeanPropertiesUtil.getString(kbArticle, "sections", StringUtil.merge(PortletPropsValues.ADMIN_KB_ARTICLE_DEFAULT_SECTIONS)));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= (kbArticle == null) %>"
	title='<%= (kbArticle == null) ? "new-article" : kbArticle.getTitle() %>'
/>

<liferay-portlet:actionURL name="updateKBArticle" var="updateKBArticleURL" />

<aui:form action="<%= updateKBArticleURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateKBArticle();" %>'>
	<aui:input name="mvcPath" type="hidden" value='<%= templatePath + "edit_article.jsp" %>' />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="resourcePrimKey" type="hidden" value="<%= String.valueOf(resourcePrimKey) %>" />
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= parentResourcePrimKey %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_SAVE_DRAFT %>" />

	<liferay-ui:error exception="<%= DuplicateFileException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />

	<liferay-ui:error exception="<%= FileSizeException.class %>">

		<%
		long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE);

		if (fileMaxSize == 0) {
			fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
		}

		fileMaxSize /= 1024;
		%>

		<liferay-ui:message arguments="<%= fileMaxSize %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= KBArticleContentException.class %>" message="please-enter-valid-content" />
	<liferay-ui:error exception="<%= KBArticleSourceURLException.class %>" message="please-enter-a-valid-source-url" />
	<liferay-ui:error exception="<%= KBArticleTitleException.class %>" message="please-enter-a-valid-title" />
	<liferay-ui:error exception="<%= NoSuchFileException.class %>" message="the-document-could-not-be-found" />

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<c:choose>
		<c:when test="<%= (kbArticle != null) && kbArticle.isApproved() %>">
			<div class="alert alert-info">
				<liferay-ui:message key="a-new-version-will-be-created-automatically-if-this-content-is-modified" />
			</div>
		</c:when>
		<c:when test="<%= (kbArticle != null) && kbArticle.isPending() %>">
			<div class="alert alert-info">
				<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
			</div>
		</c:when>
	</c:choose>

	<aui:model-context bean="<%= kbArticle %>" model="<%= KBArticle.class %>" />

	<c:if test="<%= kbArticle != null %>">
		<aui:workflow-status id="<%= String.valueOf(resourcePrimKey) %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= kbArticle.getStatus() %>" version="<%= String.valueOf(kbArticle.getVersion()) %>" />
	</c:if>

	<aui:fieldset>
		<aui:input name="title" />

		<aui:input disabled="<%= kbArticle != null %>" label="friendly-url" name="urlTitle" />

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor contents="<%= content %>" width="100%" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<c:if test="<%= enableKBArticleDescription %>">
			<aui:input name="description" />
		</c:if>

		<c:if test="<%= PortletPropsValues.KNOWLEDGE_BASE_SOURCE_URL_ENABLED %>">
			<aui:input label="source-url" name="sourceURL" />
		</c:if>

		<c:if test="<%= ArrayUtil.isNotEmpty(PortletPropsValues.ADMIN_KB_ARTICLE_SECTIONS) && (parentResourcePrimKey == KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) %>">
			<aui:model-context bean="<%= null %>" model="<%= KBArticle.class %>" />

			<aui:select ignoreRequestValue="<%= true %>" multiple="<%= true %>" name="sections">

				<%
				Map<String, String> sectionsMap = new TreeMap<String, String>();

				for (String section : PortletPropsValues.ADMIN_KB_ARTICLE_SECTIONS) {
					sectionsMap.put(LanguageUtil.get(request, section), section);
				}

				for (Map.Entry<String, String> entry : sectionsMap.entrySet()) {
				%>

					<aui:option label="<%= entry.getKey() %>" selected="<%= ArrayUtil.contains(sections, entry.getValue()) %>" value="<%= entry.getValue() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:model-context bean="<%= kbArticle %>" model="<%= KBArticle.class %>" />
		</c:if>

		<c:if test="<%= kbArticle == null %>">
			<aui:field-wrapper cssClass='<%= (parentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) ? "hide" : StringPool.BLANK %>' label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= KBArticle.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title="attachments">
			<aui:fieldset>
				<div id="<portlet:namespace />attachments">
					<liferay-util:include page="/admin/attachments.jsp" servletContext="<%= application %>" />
				</div>
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title="categorization">
			<aui:fieldset>
				<aui:input classPK="<%= (kbArticle != null) ? kbArticle.getClassPK() : 0 %>" name="categories" type="assetCategories" />

				<aui:input classPK="<%= (kbArticle != null) ? kbArticle.getClassPK() : 0 %>" name="tags" type="assetTags" />
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= false %>" persistState="<%= true %>" title="related-assets">
			<liferay-ui:input-asset-links
				className="<%= KBArticle.class.getName() %>"
				classPK="<%= (kbArticle == null) ? KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY : kbArticle.getClassPK() %>"
			/>
		</liferay-ui:panel>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" value='<%= ((kbArticle == null) || kbArticle.isApproved() || kbArticle.isDraft()) ? "save-as-draft" : "save" %>' />

			<c:if test="<%= (kbArticle == null) || !kbArticle.isPending() %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "publishKBArticle();" %>' value='<%= WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, KBArticle.class.getName()) ? "submit-for-publication" : "publish" %>' />
			</c:if>

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateMultipleKBArticleAttachments',
		function() {
			var A = AUI();
			var Lang = A.Lang;

			var selectedFileNameContainer = A.one('#<portlet:namespace />selectedFileNameContainer');

			var inputTpl = '<input id="<portlet:namespace />selectedFileName{0}" name="<portlet:namespace />selectedFileName" type="hidden" value="{1}" />';

			var values = A.all('input[name=<portlet:namespace />selectUploadedFile]:checked').val();

			var buffer = [];
			var dataBuffer = [];
			var length = values.length;

			for (var i = 0; i < length; i++) {
				dataBuffer[0] = i;
				dataBuffer[1] = values[i];

				buffer[i] = Lang.sub(inputTpl, dataBuffer);
			}

			selectedFileNameContainer.html(buffer.join(''));
		},
		['aui-base']
	);

	function <portlet:namespace />publishKBArticle() {
		document.<portlet:namespace />fm.<portlet:namespace />workflowAction.value = '<%= WorkflowConstants.ACTION_PUBLISH %>';
		<portlet:namespace />updateKBArticle();
	}

	function <portlet:namespace />updateKBArticle() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= (kbArticle == null) ? Constants.ADD : Constants.UPDATE %>';
		document.<portlet:namespace />fm.<portlet:namespace />content.value = window.<portlet:namespace />editor.getHTML();

		<portlet:namespace />updateMultipleKBArticleAttachments();

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>