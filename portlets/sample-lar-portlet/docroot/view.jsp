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

<%@ include file="/init.jsp" %>

<aui:fieldset label="sample-lar-portlet-information">
	<div class="sample-lar-paragraph">
		This is the <strong>Sample LAR Portlet</strong>. This was made to demonstrate the portlet's export/import and staging capabilities.
	</div>

	<div class="sample-lar-paragraph">
		For trying out the export/import you can click on the wrench icon, and then select the Export/Import menu.
	</div>

	<div class="sample-lar-paragraph">
		The content of this portlet can be staged, you can find the option for this in the site configuration - staging settings. Once the content of this portlet is staged, it can be selected for publishing on the related screen.
	</div>
</aui:fieldset>

<aui:fieldset label="sample-data">
	<portlet:actionURL name="addSampleData" var="addSampleDataURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
	</portlet:actionURL>

	<aui:button href="<%= addSampleDataURL %>" value="add-sample-data" />

	<%@ include file="/view_sample_data.jsp" %>
</aui:fieldset>