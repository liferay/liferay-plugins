<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@page import="com.liferay.portal.security.permission.PermissionChecker"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>

<%@ include file="/html/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-all");
%>

<div class="lfr-portlet-toolbar">
	<portlet:renderURL var="viewAppsURL">
		<portlet:param name="jspPage" value="/html/admin/view.jsp" />
	</portlet:renderURL>

	<span class="lfr-toolbar-button view-button <%= toolbarItem.equals("view-all") ? "current" : "" %>">
		<a href="<%= viewAppsURL %>"><liferay-ui:message key='<%= adminUser ? "view-all":"my-applications" %>' /></a>
	</span>
<c:if test='<%= permissionChecker.hasPermission(layout.getGroupId(), "com.liferay.portlet.oauth", layout.getGroupId(), ActionKeys.ADD_ENTRY) %>'>
	<portlet:renderURL var="addApplicationURL">
		<portlet:param name="jspPage" value="/html/admin/edit.jsp"/>
		<portlet:param name="referer" value="<%= currentURL %>"/>
	</portlet:renderURL>
	
	<span class="lfr-toolbar-button add-button <%= toolbarItem.equals("add") ? "current" : "" %>">
		<a href="<%= addApplicationURL %>"><liferay-ui:message key="add" /></a>
	</span>
</c:if>
</div>