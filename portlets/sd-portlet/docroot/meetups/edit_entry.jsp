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

long meetupsEntryId = ParamUtil.getLong(request, "meetupsEntryId");

MeetupsEntry meetupsEntry = null;

try {
	meetupsEntry = MeetupsEntryLocalServiceUtil.getMeetupsEntry(meetupsEntryId);
}
catch (NoSuchMeetupsEntryException nsmee) {
}

Calendar startDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

startDate.add(Calendar.MONTH, 1);

if (meetupsEntry != null) {
	if (meetupsEntry.getStartDate() != null) {
		startDate.setTime(meetupsEntry.getStartDate());
	}
}

Calendar endDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

endDate.add(Calendar.MONTH, 1);
endDate.add(Calendar.HOUR, 3);

if (meetupsEntry != null) {
	if (meetupsEntry.getStartDate() != null) {
		endDate.setTime(meetupsEntry.getStartDate());
	}
}
%>

<script type="text/javascript">
	function <portlet:namespace />updateMeetupsEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<form action="<portlet:actionURL name="updateMeetupsEntry"><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />updateMeetupsEntry(); return false;">
<input name="<portlet:namespace />meetupsEntryId" type="hidden" value="<%= meetupsEntryId %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="title" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= MeetupsEntry.class %>" bean="<%= meetupsEntry %>" field="title" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="description" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= MeetupsEntry.class %>" bean="<%= meetupsEntry %>" field="description" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="start-date" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= MeetupsEntry.class %>" bean="<%= meetupsEntry %>" field="startDate" defaultValue="<%= startDate %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="end-date" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= MeetupsEntry.class %>" bean="<%= meetupsEntry %>" field="endDate" defaultValue="<%= endDate %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="max-attendees" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= MeetupsEntry.class %>" bean="<%= meetupsEntry %>" field="maxAttendees" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="price" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= MeetupsEntry.class %>" bean="<%= meetupsEntry %>" field="price" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="thumbnail" />
	</td>
	<td>
		<input name="<portlet:namespace />fileName" size="50" type="file" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(redirect) %>';" />

</form>