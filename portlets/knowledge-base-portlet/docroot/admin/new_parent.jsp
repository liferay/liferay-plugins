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

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

long kbArticleClassNameId = PortalUtil.getClassNameId(KBArticleConstants.getClassName());

long resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId");
long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
double priority = KBArticleConstants.DEFAULT_PRIORITY;

if (kbArticle != null) {
	resourceClassNameId = kbArticle.getClassNameId();
	resourcePrimKey = kbArticle.getResourcePrimKey();
}

String parentTitle = null;

if (resourceClassNameId == kbArticleClassNameId) {
	if (kbArticle == null) {
		kbArticle = KBArticleServiceUtil.getLatestKBArticle(resourcePrimKey, status);
	}

	parentTitle = kbArticle.getParentTitle(locale, status);
	priority = kbArticle.getPriority();
}
else {
	KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(resourcePrimKey);

	parentTitle = kbFolder.getParentTitle(locale);
}
%>

<div class="input-append kb-new-parent">
	<aui:input label="" name="parentResource" type="resource" value="<%= parentTitle %>" />

	<aui:input cssClass="input-mini kb-priority" id="parentPriority" inlineField="<%= true %>" label="" name="priority" type="text" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />
</div>

<div class="kb-edit-link">
	<aui:a href="javascript:;" id="selectKBObjectLink"><liferay-ui:message key="select-parent" /> &raquo;</aui:a>
</div>

<aui:script use="aui-base">
	<liferay-portlet:renderURL var="selectKBObjectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
		<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
		<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(KBFolderConstants.getClassName())) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) %>" />
		<portlet:param name="status" value="<%= String.valueOf(status) %>" />
	</liferay-portlet:renderURL>

	A.one('#<portlet:namespace />selectKBObjectLink').on(
		'click',
		function(event) {
			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						destroyOnHide: true,
						modal: true
					},
					id: '<portlet:namespace />selectKBObject',
					title: '<liferay-ui:message key="select-parent" />',
					uri: getSelectKBObjectWindowURL()
				},
				function(event) {
					document.<portlet:namespace />fm.<portlet:namespace />parentPriority.value = event.priority;
					document.<portlet:namespace />fm.<portlet:namespace />parentResourceClassNameId.value = event.resourceclassnameid;
					document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = event.resourceprimkey;
					document.<portlet:namespace />fm.<portlet:namespace />parentTitle.value = event.title;
				}
			);
		}
	);

	var getSelectKBObjectWindowURL = function() {
		var oldParentResourceClassNameId = document.<portlet:namespace />fm.<portlet:namespace />parentResourceClassNameId.value;
		var oldParentResourcePrimKey = document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value;

		var selectKBObjectWindowURL = '<%= selectKBObjectURL %>';

		selectKBObjectWindowURL += '&<portlet:namespace />oldParentResourceClassNameId=' + oldParentResourceClassNameId;
		selectKBObjectWindowURL += '&<portlet:namespace />oldParentResourcePrimKey=' + oldParentResourcePrimKey;

		return selectKBObjectWindowURL;
	}
</aui:script>