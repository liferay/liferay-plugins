<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%
String redirect = ParamUtil.getString(request, "redirect");

long gadgetId = ParamUtil.getLong(request, "gadgetId");

Gadget gadget = null;

try {
	gadget = GadgetLocalServiceUtil.getGadget(gadgetId);
}
catch (NoSuchGadgetException nsge) {
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (gadget != null) ? gadget.getName() : "new-gadget" %>'
/>

<form action="<portlet:actionURL name="updateGadget"><portlet:param name="jspPage" value="/admin/edit_gadget.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveGadget(); return false;">
<input name="<portlet:namespace />gadgetId" type="hidden" value="<%= gadgetId %>" />

<liferay-ui:error exception="<%= DuplicateGadgetURLException.class %>" message="url-already-points-to-an-existing-gadget" />
<liferay-ui:error exception="<%= GadgetURLException.class %>" message="url-does-not-point-to-a-valid-gadget" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= Gadget.class %>" bean="<%= gadget %>" field="url" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />saveGadget() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-gadget"), currentURL);
%>