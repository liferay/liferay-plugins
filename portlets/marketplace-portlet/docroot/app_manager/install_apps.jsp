<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<div class="row">
	<div class="span6">
		<portlet:actionURL name="installLocalApp" var="installLocalAppURL" />

		<aui:form action="<%= installLocalAppURL %>" enctype="multipart/form-data" method="post" name="fm1">
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

			<aui:fieldset label="file-upload">
				<liferay-ui:success key="pluginUploaded" message="the-plugin-was-uploaded-successfully-and-is-now-being-installed" />

				<liferay-ui:error exception="<%= UploadException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />

				<aui:input label="lpkg-or-war-file" name="file" type="file" />

				<aui:button type="submit" value="install" />
			</aui:fieldset>
		</aui:form>
	</div>
	<div class="span6">
		<portlet:actionURL name="installRemoteApp" var="installRemoteAppURL" />

		<aui:form action="<%= installRemoteAppURL %>" method="post" name="fm2">
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

			<aui:fieldset label="url">
				<liferay-ui:success key="pluginDownloaded" message="the-plugin-was-downloaded-successfully-and-is-now-being-installed" />

				<liferay-ui:error key="invalidUrl" message="please-enter-a-valid-url" />

				<aui:input name="url" type="text" />

				<aui:button type="submit" value="install" />
			</aui:fieldset>
		</aui:form>
	</div>
</div>