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
long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId");
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey");

if (kbArticle != null) {
	resourceClassNameId = kbArticle.getClassNameId();
	resourcePrimKey = kbArticle.getResourcePrimKey();
	parentResourceClassNameId = kbArticle.getParentResourceClassNameId();
	parentResourcePrimKey = kbArticle.getParentResourcePrimKey();
}

String title = null;
String parentTitle = null;
double priority = KBArticleConstants.DEFAULT_PRIORITY;

if (resourceClassNameId == kbArticleClassNameId) {
	if (kbArticle == null) {
		kbArticle = KBArticleServiceUtil.fetchLatestKBArticle(resourcePrimKey, status);
	}

	title = kbArticle.getTitle();
	parentTitle = kbArticle.getParentTitle(locale, status);
	priority = kbArticle.getPriority();
}
else {
	KBFolder kbFolder = KBFolderServiceUtil.getKBFolder(resourcePrimKey);

	title = kbFolder.getName();
	parentTitle = kbFolder.getParentTitle(locale);
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= false %>"
	title="<%= title %>"
/>

<liferay-portlet:actionURL name="moveKBObject" var="moveKBObjectURL" />

<aui:form action="<%= moveKBObjectURL %>" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value='<%= templatePath + "move_object.jsp" %>' />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="resourceClassNameId" type="hidden" value="<%= String.valueOf(resourceClassNameId) %>" />
	<aui:input name="resourcePrimKey" type="hidden" value="<%= String.valueOf(resourcePrimKey) %>" />
	<aui:input name="parentResourceClassNameId" type="hidden" value="<%= String.valueOf(parentResourceClassNameId) %>" />
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= String.valueOf(parentResourcePrimKey) %>" />
	<aui:input name="status" type="hidden" value="<%= String.valueOf(status) %>" />

	<liferay-ui:error exception="<%= KBArticlePriorityException.class %>" message='<%= LanguageUtil.format(request, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

	<aui:fieldset>
		<aui:field-wrapper label="current-parent">
			<div class="input-append">
				<liferay-ui:input-resource url="<%= parentTitle %>" />

				<aui:input cssClass="input-mini" label="" name="priority" type="resource" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />
			</div>
		</aui:field-wrapper>

		<aui:field-wrapper label="new-parent">
			<div id="<portlet:namespace />newParent">
				<liferay-util:include page="/admin/new_parent.jsp" servletContext="<%= application %>" />
			</div>
		</aui:field-wrapper>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />selectKBObject(parentTitle, parentPriority, parentResourcePrimKey, parentResourceClassNameId) {
		document.<portlet:namespace />fm.<portlet:namespace />parentPriority.value = parentPriority;
		document.<portlet:namespace />fm.<portlet:namespace />parentResourceClassNameId.value = parentResourceClassNameId;
		document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = parentResourcePrimKey;
		document.<portlet:namespace />fm.<portlet:namespace />parentTitle.value = parentTitle;
	}
</aui:script>