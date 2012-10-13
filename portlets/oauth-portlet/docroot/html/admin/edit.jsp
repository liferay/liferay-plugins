<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/init.jsp" %>

<%
Application app = (Application)request.getAttribute(OAuthConstants.BEAN_ID);

String actionName = "addApplication";

boolean isNew = true;

if ((null != app) && (0L != app.getApplicationId())) {
	actionName = "updateApplication";
	isNew = false;
}

String backURL = ParamUtil.getString(request, "referer");
%>

<h3><liferay-ui:message key="<%= actionName %>" /></h3>
<liferay-ui:error-marker key="errorSection" value="details" />

<aui:form method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveApplication();" %>'>
	<aui:input id="applicationId" name="applicationId" type="hidden" value='<%= isNew ? "" : app.getApplicationId()%>'/>

	<liferay-ui:error exception="<%=RequiredFieldException.class %>" message="this-field-is-required" />
	<liferay-ui:error exception="<%=MalformedURLException.class %>" message="please-enter-a-valid-url" />

	<aui:model-context bean="<%= app %>" model="<%= Application.class %>" />

	<aui:fieldset>
		<aui:input label="name" name="<%=OAuthConstants.NAME%>">
			<aui:validator name="required" />
		</aui:input>
		<aui:input cols="65" label="description" name="<%=OAuthConstants.DESCRIPTION%>" rows="5" type="textarea" />
		<aui:input label="website" name="<%=OAuthConstants.WEBSITE%>">
			<aui:validator name="required" />
			<aui:validator name="url" />
		</aui:input>
		<aui:input label="callback-url" name="<%=OAuthConstants.CALLBACK_URL%>">
			<aui:validator name="required" />
			<aui:validator name="url" />
		</aui:input>

		<c:if test="<%= isNew %>">
			<aui:select helpMessage="access-type-description" label="access-type" name="<%= OAuthConstants.ACCESS_TYPE %>">
				<aui:option label="<%= OAuthConstants.ACCESS_TYPE_OPTION.concat(Integer.toString(OAuthConstants.ACCESS_TYPE_READ)) %>" value="<%= OAuthConstants.ACCESS_TYPE_READ %>"></aui:option>
				<aui:option label="<%= OAuthConstants.ACCESS_TYPE_OPTION.concat(Integer.toString(OAuthConstants.ACCESS_TYPE_WRITE)) %>" value="<%= OAuthConstants.ACCESS_TYPE_WRITE %>"></aui:option>
			</aui:select>
		</c:if>

		<c:if test="<%= !isNew %>">
			<aui:field-wrapper helpMessage="application-credentials-description" label="application-credentials">
				<liferay-ui:message key="application-key" />: <%= app.getConsumerKey() %> <br />
				<liferay-ui:message key="application-secret" />: <%= app.getConsumerSecret() %>
			</aui:field-wrapper>
		</c:if>

		<c:if test="<%= app != null && app.getApplicationId() != 0 %>">
			<portlet:renderURL var="editApplicationLogoURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/html/admin/edit_application_logo.jsp" />
				<portlet:param name="applicationId" value="<%= StringUtil.valueOf(app.getApplicationId()) %>" />
			</portlet:renderURL>

			<h3><liferay-ui:message key="logo" /></h3>
			<liferay-ui:logo-selector
				defaultLogoURL='<%= themeDisplay.getPathImage() + "/logo?img_id=0" %>'
				editLogoURL="<%= editApplicationLogoURL %>"
				imageId="<%= app.getLogoId() %>"
				logoDisplaySelector=".application-logo" />
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />
			<aui:button href="<%= backURL %>" value="back" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<liferay-portlet:actionURL name="<%= actionName %>" var="addApplicationURL">
	<portlet:param name="mvcPath" value="/html/admin/edit.jsp" />
	<portlet:param name="referer" value="<%= backURL %>" />
</liferay-portlet:actionURL>

<aui:script>
	function <portlet:namespace />saveApplication() {
		submitForm(document.<portlet:namespace />fm, "<%= addApplicationURL %>");
	}
</aui:script>