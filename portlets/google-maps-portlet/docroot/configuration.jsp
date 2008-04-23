<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<div class="portlet-msg-info">
	<a href="http://www.google.com/apis/maps/signup.html" target="_blank"><liferay-ui:message key="you-can-get-a-license-directly-from-google" /></a>
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="google-license" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />license" type="text" value="<%= license %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="map-address" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />mapAddress" type="text" value="<%= mapAddress %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="map-input-enabled" />
	</td>
	<td>
		<liferay-ui:input-checkbox param="mapInputEnabled" defaultValue="<%= mapInputEnabled %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="directions-address" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />directionsAddress" type="text" value="<%= directionsAddress %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="directions-input-enabled" />
	</td>
	<td>
		<liferay-ui:input-checkbox param="directionsInputEnabled" defaultValue="<%= directionsInputEnabled %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="height" />
	</td>
	<td>
		<input name="<portlet:namespace />height" size="4" type="text" value="<%= height %>" /> px
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />license);
	</script>
</c:if>