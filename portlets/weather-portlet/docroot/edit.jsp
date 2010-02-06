<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
String zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);

zips = StringUtil.split(ParamUtil.getString(request, "zips", zipsString), StringPool.NEW_LINE);

zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);
%>

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<liferay-ui:error exception="<%= ValidatorException.class %>">

	<%
	ValidatorException ve = (ValidatorException)errorException;
	%>

	<liferay-ui:message key="the-following-are-invalid-cities-or-zip-codes" />

	<%
	Enumeration enu = ve.getFailedKeys();

	while (enu.hasMoreElements()) {
		String zip = (String)enu.nextElement();
	%>

		<strong><%= zip %></strong><%= (enu.hasMoreElements()) ? ", " : "." %>

	<%
	}
	%>

</liferay-ui:error>

<liferay-ui:message key="enter-one-city-or-zip-code-per-line" />

<br /><br />

<textarea class="lfr-textarea" name="<portlet:namespace />zips" wrap="soft"><%= zipsString %></textarea>

<br /><br />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="temperature-format" />
	</td>
	<td>
		<select name="<portlet:namespace />fahrenheit">
			<option <%= fahrenheit ? "selected" : "" %> value="1"><liferay-ui:message key="fahrenheit" /></option>
			<option <%= !fahrenheit ? "selected" : "" %> value="0"><liferay-ui:message key="celsius" /></option>
		</select>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />zips);
	</script>
</c:if>