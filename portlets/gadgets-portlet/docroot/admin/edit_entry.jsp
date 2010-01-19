<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long gadgetsEntryId = ParamUtil.getLong(request, "gadgetsEntryId");

GadgetsEntry gadgetsEntry = null;

try {
	gadgetsEntry = GadgetsEntryLocalServiceUtil.getGadgetsEntry(gadgetsEntryId);
}
catch (NoSuchEntryException nsee) {
}
%>

<script type="text/javascript">
	function <portlet:namespace />saveEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateGadgetsEntry"><portlet:param name="jspPage" value="/admin/edit_gadget.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveEntry(); return false;">
<input name="<portlet:namespace />gadgetsEntryId" type="hidden" value="<%= gadgetsEntryId %>" />

<liferay-ui:error exception="<%= GadgetsEntryNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= GadgetsEntryURLException.class %>" message="url-does-not-point-to-a-valid-gadget" />

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="gadgets" /></a></span> &raquo;

	<span class="last"><liferay-ui:message key='<%= ((gadgetsEntry == null) ? Constants.ADD : Constants.UPDATE) + "-gadget" %>' /></span>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= GadgetsEntry.class %>" bean="<%= gadgetsEntry %>" field="name" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<c:choose>
			<c:when test="<%= gadgetsEntry == null %>">
				<liferay-ui:input-field model="<%= GadgetsEntry.class %>" bean="<%= gadgetsEntry %>" field="url" />
			</c:when>
			<c:otherwise>
				<a href="<%= gadgetsEntry.getUrl() %>" target="_blank"><%= gadgetsEntry.getUrl() %></a>

				<input name="<portlet:namespace />url" type="hidden" value="<%= gadgetsEntry.getUrl() %>" />
			</c:otherwise>
		</c:choose>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="xml" />
	</td>
	<td>
		<liferay-ui:input-textarea param="xml" defaultValue="<%= ((gadgetsEntry == null) ? StringPool.BLANK : gadgetsEntry.getXml()) %>" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<script type="text/javascript">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</script>