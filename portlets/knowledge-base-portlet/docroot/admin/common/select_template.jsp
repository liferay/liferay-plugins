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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

long kbTemplateId = BeanParamUtil.getLong(kbArticle, request, "kbTemplateId", KBArticleConstants.DEFAULT_KB_TEMPLATE_ID);

String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<liferay-ui:header
	title="template"
/>

<liferay-portlet:renderURL varImpl="iteratorURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="jspPage" value='<%= jspPath + "select_template.jsp" %>' />
	<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
	<portlet:param name="kbTemplateId" value="<%= String.valueOf(kbTemplateId) %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-templates"
	iteratorURL="<%= iteratorURL %>"
	orderByCol="<%= orderByCol %>"
	orderByComparator="<%= KnowledgeBaseUtil.getKBTemplateOrderByComparator(orderByCol, orderByType) %>"
	orderByType="<%= orderByType %>"
>
	<liferay-ui:search-container-results
		results="<%= KBTemplateServiceUtil.getGroupKBTemplates(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= KBTemplateServiceUtil.getGroupKBTemplatesCount(scopeGroupId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.knowledgebase.model.KBTemplate"
		keyProperty="kbTemplateId"
		modelVar="selKBTemplate"
	>
		<liferay-portlet:renderURL var="rowURL">
			<portlet:param name="jspPage" value='<%= jspPath + "print_template.jsp" %>' />
			<portlet:param name="kbTemplateId" value="<%= String.valueOf(selKBTemplate.getKbTemplateId()) %>" />
		</liferay-portlet:renderURL>

		<%
		rowURL = "javascript:var printKBTemplateWindow = window.open('" + rowURL + "', 'printKBTemplate', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); printKBTemplateWindow.focus();";
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			orderable="<%= true %>"
			property="title"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="author"
			orderable="<%= true %>"
			orderableProperty="user-name"
			property="userName"
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="create-date"
			orderable="<%= true %>"
			value='<%= dateFormatDate.format(selKBTemplate.getCreateDate()) + "<br />" + dateFormatTime.format(selKBTemplate.getCreateDate()) %>'
		/>

		<liferay-ui:search-container-column-text
			cssClass="kb-column-no-wrap"
			href="<%= rowURL %>"
			name="modified-date"
			orderable="<%= true %>"
			value='<%= dateFormatDate.format(selKBTemplate.getModifiedDate()) + "<br />" + dateFormatTime.format(selKBTemplate.getModifiedDate()) %>'
		/>

		<liferay-ui:search-container-column-text
			align="right"
		>
			<liferay-util:buffer var="html">
				<liferay-ui:icon
					cssClass="kb-selected-template"
					image="../file_system/small/xml"
					label="<%= true %>"
					message='<%= selKBTemplate.getTitle() %>'
					method="get"
				/>
			</liferay-util:buffer>

			<%
			String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBTemplate('" + selKBTemplate.getKbTemplateId() + "', '" + UnicodeFormatter.toString(html) + "'); window.close();";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="choose" />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<c:if test="<%= kbTemplateId != KBArticleConstants.DEFAULT_KB_TEMPLATE_ID %>">
		<aui:button-row>
			<%= BeanPropertiesUtil.getString(kbArticle.getKBTemplate(), "title") %>

			<%
			String taglibOnClick = "opener." + renderResponse.getNamespace() + "selectKBTemplate('" + KBArticleConstants.DEFAULT_KB_TEMPLATE_ID + "', ''); window.close();";
			%>

			<aui:button onClick="<%= taglibOnClick %>" value="remove" />
		</aui:button-row>

		<div class="separator"><!-- --></div>
	</c:if>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>