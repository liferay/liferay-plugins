<%@ include file="/html/init.jsp" %>

<%
OAuthApplication oAuthApplication = (OAuthApplication)request.getAttribute(OAuthConstants.WEB_APP_BEAN);

String actionName = "addOAuthApp";
if (null != oAuthApplication && (0L != oAuthApplication.getApplicationId())) {
	actionName = "editOAuthApp";
}

String backURL = ParamUtil.getString(request, "referer");
%>

<liferay-ui:error-marker key="errorSection" value="details" />

<aui:model-context bean="<%= oAuthApplication %>" model="<%= OAuthApplication.class %>"/>

<liferay-ui:error exception="<%=RequiredFieldException.class %>" message="this-field-is-required" />
<liferay-ui:error exception="<%=MalformedURLException.class %>" message="please-enter-a-valid-url" />

<liferay-portlet:actionURL name="<%= actionName %>" var="addApplicationURL">
	<portlet:param name="jspPage" value="/html/admin/edit.jsp"/>
	<portlet:param name="referer" value="<%= backURL %>"/>
</liferay-portlet:actionURL>

<aui:a href="<%= backURL %>" >back</aui:a>
<aui:form action="<%= addApplicationURL %>" method="post">
	<aui:fieldset>
		<aui:input name="applicationId" id="applicationId" type="hidden"/>
		<aui:input name="<%= OAuthConstants.WEB_APP_NAME %>" id="<%= OAuthConstants.WEB_APP_NAME_ID %>" label="name" showRequiredLabel="true"/>
		<aui:input name="<%= OAuthConstants.WEB_APP_DESCRIPTION %>" type="textarea" label="description" />
		<aui:input name="<%= OAuthConstants.WEB_APP_WEBSITE %>" id="<%= OAuthConstants.WEB_APP_WEBSITE_ID %>" label="website" showRequiredLabel="true"  />
		<aui:input name="<%= OAuthConstants.WEB_APP_CALLBACKURL %>" id="<%= OAuthConstants.WEB_APP_CALLBACKURL_ID %>" label="callback-url" showRequiredLabel="true"/>
		
		<aui:select name="<%= OAuthConstants.WEB_APP_ACCESS_TYPE %>" label="access-level" helpMessage="access-level-description">
			<aui:option label="<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_OPTION.concat(Integer.toString(OAuthConstants.ACCESS_TYPE_READ)) %>" value="<%= OAuthConstants.ACCESS_TYPE_READ %>"></aui:option>
			<aui:option label="<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_OPTION.concat(Integer.toString(OAuthConstants.ACCESS_TYPE_WRITE)) %>" value="<%= OAuthConstants.ACCESS_TYPE_WRITE %>"></aui:option>
		</aui:select>
	
		<c:if test="<%= (null != oAuthApplication) && (null != oAuthApplication.getConsumerKey()) %>">
			<aui:field-wrapper label="application-credentials" helpMessage="application-credentials-description">
				<liferay-ui:message key="application-key" />: <%= oAuthApplication.getConsumerKey() %> <br />
				<liferay-ui:message key="application-secret" />: <%= oAuthApplication.getConsumerSecret() %> 
			</aui:field-wrapper>
		</c:if>
		
		<aui:button-row>
		<aui:button type="submit" />
		<aui:button href="<%= backURL %>" value="cancel"/>
		</aui:button-row>
	</aui:fieldset>
</aui:form>
