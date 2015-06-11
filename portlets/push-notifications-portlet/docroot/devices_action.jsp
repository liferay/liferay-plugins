<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.service.permission.UserPermissionUtil" %>
<%@ page import="com.liferay.pushnotifications.model.PushNotificationsDevice" %>

<%@ include file="init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

PushNotificationsDevice pushObject = (PushNotificationsDevice)row.getObject();

long pushid = pushObject.getPushNotificationsDeviceId();
long userId = pushObject.getUserId();
%>

<liferay-ui:icon-menu>

	<c:if test="<%= UserPermissionUtil.contains(permissionChecker, userId, ActionKeys.DELETE) %>">

		<liferay-portlet:actionURL name="deleteDevice" var="deleteDeviceUrl">
			<portlet:param name="pushId" value="<%= String.valueOf(pushid) %>" />
			<portlet:param name="currentTab" value="devices" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete confirmation="are-you-sure-you-want-to-delete-the-device" url="<%= deleteDeviceUrl %>"  />
	</c:if>
</liferay-ui:icon-menu>