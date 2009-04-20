<%--
  ~ Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
  ~
  ~ Permission is hereby granted, free of charge, to any person obtaining a copy
  ~ of this software and associated documentation files (the "Software"), to deal
  ~ in the Software without restriction, including without limitation the rights
  ~ to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  ~ copies of the Software, and to permit persons to whom the Software is
  ~ furnished to do so, subject to the following conditions:
  ~
  ~ The above copyright notice and this permission notice shall be included in
  ~ all copies or substantial portions of the Software.
  ~
  ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  ~ IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  ~ FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  ~ AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  ~ LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  ~ OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  ~ SOFTWARE.
  --%>

<%@ include file="/init.jsp"%>

<%
	ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

	RequestedReport requestedReport = (RequestedReport) row.getObject();

	String requestId = String.valueOf(requestedReport.getRequestId());
%>
<%@page import="com.liferay.bi.report.portlet.RequestStatus"%>
<liferay-ui:icon-menu cssClass="">

	<c:if test="<%=RequestStatus.COMPLETE.toString().equals(requestedReport.getRequestStatus()) %>">
		<portlet:actionURL
			windowState="<%= WindowState.MAXIMIZED.toString() %>"
			var="reportDownloadURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="requestId" value="<%= requestId %>" />
		</portlet:actionURL>
		
		<liferay-ui:icon image="download" message="download-report" url="<%=reportDownloadURL %>" />
	</c:if>


	<c:if test="<%= RequestedReportPermission.contains(permissionChecker, requestedReport.getRequestId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= RequestedReport.class.getName() %>"
			modelResourceDescription='<%= String.valueOf(requestedReport.getRequestId()) %>'
			resourcePrimKey="<%= requestId %>" var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<c:if test="<%= requestedReport.isIsSchedule() %>">
		<portlet:actionURL
			windowState="<%= WindowState.MAXIMIZED.toString() %>"
			var="viewScheduleURL">
			<portlet:param name="jspPage" value="/request/view_request_schedule.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="requestId" value="<%= requestId %>" />
		</portlet:actionURL>
		
		<liferay-ui:icon image="manage_task" message="view-schedule"
			url="<%= viewScheduleURL %>" />
	</c:if>
	
	<portlet:actionURL
		windowState="<%= WindowState.MAXIMIZED.toString() %>"
		name="deleterequestedReport" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="requestId" value="<%= requestId %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>

