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

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

long parentResourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "parentResourcePrimKey");
double priority = BeanParamUtil.getDouble(kbArticle, request, "priority");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= false %>"
	title="<%= kbArticle.getTitle() %>"
/>

<liferay-portlet:actionURL name="moveKBArticle" var="moveKBArticleURL" />

<aui:form action="<%= moveKBArticleURL %>" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value='<%= templatePath + "move_article.jsp" %>' />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="resourcePrimKey" type="hidden" value="<%= String.valueOf(resourcePrimKey) %>" />
	<aui:input name="parentResourcePrimKey" type="hidden" value="<%= parentResourcePrimKey %>" />
	<aui:input name="status" type="hidden" value="<%= String.valueOf(status) %>" />

	<liferay-ui:error exception="<%= KBArticlePriorityException.class %>" message='<%= LanguageUtil.format(request, "please-enter-a-priority-that-is-greater-than-x", "0", false) %>' translateMessage="<%= false %>" />

	<aui:fieldset>
		<div class="form-group">
			<aui:input name="currentParent" type="resource" value='<%= !kbArticle.isRoot() ? BeanPropertiesUtil.getString(KBArticleServiceUtil.getLatestKBArticle(kbArticle.getParentResourcePrimKey(), status), "title") : "(" + LanguageUtil.get(request, "none") + ")" %>' />

			<aui:input cssClass="input-mini" label="" name="priority" type="resource" value="<%= BigDecimal.valueOf(priority).toPlainString() %>" />
		</div>

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
	function <portlet:namespace />selectKBArticle(parentResourcePrimKey, html) {
		document.<portlet:namespace />fm.<portlet:namespace />parentResourcePrimKey.value = parentResourcePrimKey;
		document.getElementById('<portlet:namespace />newParent').innerHTML = html;
	}
</aui:script>