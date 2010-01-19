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

long openSocialGadgetId = ParamUtil.getLong(request, "openSocialGadgetId");

OpenSocialGadget openSocialGadget = null;

try {
	openSocialGadget = OpenSocialGadgetLocalServiceUtil.getOpenSocialGadget(openSocialGadgetId);
}
catch (NoSuchGadgetException nsce) {
}
%>

<script type="text/javascript">
	function <portlet:namespace />saveGadget() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateOpenSocialGadget"><portlet:param name="jspPage" value="/admin/edit_gadget.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveConsumer(); return false;">
<input name="<portlet:namespace />openSocialGadgetId" type="hidden" value="<%= openSocialGadgetId %>" />

<liferay-ui:error exception="<%= OpenSocialGadgetNameException.class %>" message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= OpenSocialGadgetURLException.class %>" message="url-does-not-point-to-a-valid-opensocial-gadget" />

<div class="breadcrumbs">
	<span class="first"><a href="<portlet:renderURL />"><liferay-ui:message key="gadgets" /></a></span> &raquo;

	<span class="last"><liferay-ui:message key='<%= ((openSocialGadget == null) ? Constants.ADD : Constants.UPDATE) + "-gadget" %>' /></span>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= OpenSocialGadget.class %>" bean="<%= openSocialGadget %>" field="name" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<c:choose>
			<c:when test="<%= openSocialGadget == null %>">
				<liferay-ui:input-field model="<%= OpenSocialGadget.class %>" bean="<%= openSocialGadget %>" field="url" />
			</c:when>
			<c:otherwise>
				<a href="<%= openSocialGadget.getUrl() %>" target="_blank"><%= openSocialGadget.getUrl() %></a>

				<input name="<portlet:namespace />url" type="hidden" value="<%= openSocialGadget.getUrl() %>" />
			</c:otherwise>
		</c:choose>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="xml" />
	</td>
	<td>
		<liferay-ui:input-textarea param="xml" defaultValue="<%= ((openSocialGadget == null) ? StringPool.BLANK : openSocialGadget.getXml()) %>" />
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