<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

<table class="liferay-table">
<tr>
	<td>
		<liferay-ui:message key="ad-client" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />adClient" type="text" value="<%= adClient %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="ad-channel" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />adChannel" type="text" value="<%= adChannel %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="ad-type" />
	</td>
	<td>
		<select name="<portlet:namespace />adType">

			<%
			for (int i = 1; i < adTypes.length; i++) {
			%>

				<option <%= (adType == GetterUtil.getInteger(adTypes[i][0])) ? "selected" : "" %> value="<%= adTypes[i][0] %>"><%= adTypes[i][1] %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="ad-format" />
	</td>
	<td>
		<select name="<portlet:namespace />adFormat">

			<%
			for (int i = 1; i < adFormats.length; i++) {
			%>

				<option <%= (adFormat == GetterUtil.getInteger(adFormats[i][0])) ? "selected" : "" %> value="<%= adFormats[i][0] %>"><%= adFormats[i][3] %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-border" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />colorBorder" type="text"  value="<%= colorBorder %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-background" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />colorBg" type="text" value="<%= colorBg %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-link" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />colorLink" type="text" value="<%= colorLink %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-text" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />colorText" type="text" value="<%= colorText %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="color-url" />
	</td>
	<td>
		<input class="liferay-input-text" name="<portlet:namespace />colorUrl" type="text" value="<%= colorUrl %>" />
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= renderRequest.getWindowState().equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />adClient);
	</script>
</c:if>