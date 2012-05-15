<%@page import="com.liferay.portal.oauth.model.OAuthApplication"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>

<%@ include file="/html/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "jspPage");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

OAuthApplication oAuthApp = (OAuthApplication)row.getObject();
%>

<liferay-ui:icon-menu>
	<liferay-portlet:renderURL var="viewURL">
		<portlet:param name="jspPage" value="/html/admin/edit.jsp"/>
		<portlet:param name="referer" value="<%= currentURL %>"/>
		<portlet:param name="applicationId" value="<%= String.valueOf(oAuthApp.getApplicationId()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon
		image="view"
		method="get"
		url="<%= viewURL %>"
	/>

	<c:if test="<%= true %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/html/admin/edit.jsp"/>
			<portlet:param name="referer" value="<%= currentURL %>"/>
			<portlet:param name="applicationId" value="<%= String.valueOf(oAuthApp.getApplicationId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= true %>">
		<liferay-portlet:actionURL name="deleteOAuthApp" var="deleteURL">
			<portlet:param name="applicationId" value="<%= String.valueOf(oAuthApp.getApplicationId()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>