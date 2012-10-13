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

String logoURL = themeDisplay.getPathImage() + "/logo?img_id=" + app.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(company.getLogoId());
%>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "request_processed") %>'>
		<aui:script>
			opener.<portlet:namespace />changeLogo('<%= logoURL %>');

			window.close();
		</aui:script>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="saveTempImageFile" var="editApplicationLogoURL">
			<portlet:param name="mvcPath" value="/html/admin/edit_application_logo.jsp" />
			<portlet:param name="applicationId" value="<%= StringUtil.valueOf(app.getApplicationId()) %>" />
		</portlet:actionURL>

		<aui:form action="<%= editApplicationLogoURL %>" enctype="multipart/form-data" method="post" name="fm">
			<aui:input name="cropRegion" type="hidden" />

			<liferay-ui:error exception="<%= NoSuchFileException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />
			<liferay-ui:error exception="<%= UploadException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />

			<aui:fieldset>
				<aui:input label="" name="fileName" size="50" type="file" />

				<div class="lfr-change-logo lfr-portrait-preview" id="<portlet:namespace />portraitPreview">
					<img class="lfr-portrait-preview-img" id="<portlet:namespace />portraitPreviewImg" src="<%= HtmlUtil.escape(logoURL) %>" />
				</div>

				<aui:button-row>
					<aui:button name="submitButton" type="submit" />

					<aui:button onClick="window.close();" type="cancel" value="close" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />fileName);
			</aui:script>
		</c:if>

		<aui:script use="liferay-logo-editor">
			new Liferay.LogoEditor(
				{
					maxFileSize: '<%= PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE) / 1024 %>',
					namespace: '<portlet:namespace />',
					previewURL: '<portlet:resourceURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.GET_TEMP %>" /><portlet:param name="applicationId" value="<%= StringUtil.valueOf(app.getApplicationId()) %>" /></portlet:resourceURL>',
					uploadURL: '<portlet:actionURL name="addTempImageFile"><portlet:param name="applicationId" value="<%= StringUtil.valueOf(app.getApplicationId()) %>" /></portlet:actionURL>'
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>