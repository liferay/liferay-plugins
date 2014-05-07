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
		This is the <strong>Sample LAR portlet</strong>. It demonstrates exporting, importing, and staging capabilities that can be implemented in a portlet.
	</div>

	<div class="sample-lar-paragraph">
		The portlet's sample data can be exported to a LAR file. And data from LAR files can be imported into the portlet. To export or import portlet data, click on the gear icon of the portlet and select <em>Export/Import</em>.
	</div>

	<div class="sample-lar-paragraph">
		In addition, the portlet supports staging. It's easy to enable staging for its content. First, navigate to the site's <em>Configuration</em> page, click on <em>Site Settings</em> in the side menu, and click on <em>Staging</em> in the Advanced section. Then select either <em>Remote Live</em> or <em>Local Live</em>, and select <em>Sample LAR</em> in the Content Type section. Any changes to the portlet's content will be staged for publishing.
	</div>
</aui:fieldset>

<aui:fieldset label="sample-bookings">
	<portlet:actionURL name="addSampleLARBooking" var="addSampleLARBookingURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
	</portlet:actionURL>

	<aui:button href="<%= addSampleLARBookingURL %>" value="add-sample-booking" />

	<%@ include file="/view_sample_lar_bookings.jsp" %>
</aui:fieldset>