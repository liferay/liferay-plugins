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

<%
String className = StringPool.BLANK;

if (tabs1.equals("day")) {
	className += " day-view";
}
%>

<form method="post" name="<portlet:namespace />fm1">

<div class="export-import-calendar<%= className %>">
	<input type="hidden" name="exportFileName" type="text" value="<%= layout.getGroup().getName() %>.ics">

	<c:if test="<%= CalendarPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_EVENT) %>">
		<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/calendar/view" /><portlet:param name="tabs1" value="export-import" /></portlet:renderURL>"><liferay-ui:message key="import" /></a>

		<c:if test="<%= CalendarPermission.contains(permissionChecker, scopeGroupId, ActionKeys.EXPORT_ALL_EVENTS) %>">
			<a class="export-events" href="javascript: ;" onClick="document.<portlet:namespace />fm1.action = '<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/calendar/export_events" /></portlet:actionURL>'; document.<portlet:namespace />fm1.submit();"><liferay-ui:message key="export" /></a>
		</c:if>
	</c:if>
</div>

</form>