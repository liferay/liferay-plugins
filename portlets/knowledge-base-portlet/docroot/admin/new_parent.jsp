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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

long kbArticleClassNameId = PortalUtil.getClassNameId(KBArticleConstants.getClassName());

long resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId");
long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
long parentResourceClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());
long parentResourcePrimKey = KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;

double priority = KBArticleConstants.DEFAULT_PRIORITY;

String parentTitle = null;

if (resourceClassNameId == kbArticleClassNameId) {
	KBArticle kbArticle = KBArticleServiceUtil.getLatestKBArticle(resourcePrimKey, status);

	parentResourceClassNameId = kbArticle.getParentResourceClassNameId();
	parentResourcePrimKey = kbArticle.getParentResourcePrimKey();
	parentTitle = kbArticle.getParentTitle(locale, status);
	priority = kbArticle.getPriority();
}
else {
	KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(resourcePrimKey);

	parentResourcePrimKey = kbFolder.getParentKBFolderId();
	parentTitle = kbFolder.getParentTitle(locale);
}
%>

<aui:field-wrapper label="new-parent">
	<div id="<portlet:namespace />newParent">
		<aui:input label="" name="parentTitle" readonly="<%= true %>" value="<%= parentTitle %>" />

		<aui:input cssClass="input-mini" id="parentPriority" label="priority" name="priority" type="text" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />
	</div>

	<aui:button name="selectKBObjectButton" value="select" />
</aui:field-wrapper>

<aui:script use="aui-base">
	<liferay-portlet:renderURL var="selectKBObjectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value='<%= templatePath + "select_parent.jsp" %>' />
		<portlet:param name="resourceClassNameId" value="<%= String.valueOf(resourceClassNameId) %>" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
		<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(KBFolderConstants.getClassName())) %>" />
		<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) %>" />
		<portlet:param name="originalParentResourceClassNameId" value="<%= String.valueOf(parentResourceClassNameId) %>" />
		<portlet:param name="originalParentResourcePrimKey" value="<%= String.valueOf(parentResourcePrimKey) %>" />
		<portlet:param name="status" value="<%= String.valueOf(status) %>" />
	</liferay-portlet:renderURL>

	A.one('#<portlet:namespace />selectKBObjectButton').on(
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
					uri: '<%= selectKBObjectURL %>'
				},
				function(event) {
					document.<portlet:namespace />fm.<portlet:namespace />parentPriority.value = event.priority;
					document.<portlet:namespace />fm.<portlet:namespace />parentResourceClassNameId.value = event.resourceclassnameid;
					document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = event.resourceprimkey;
					document.<portlet:namespace />fm.<portlet:namespace />parentTitle.value = A.Lang.String.unescapeEntities(event.title);
				}
			);
		}
	);
</aui:script>