<%@page import="com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil"%>
<%@page import="com.liferay.portal.oauth.model.OAuthApplications_Users"%>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil"%>
<%@page import="com.liferay.portlet.oauth.search.OAuthApplicationSearchTerms"%>
<%@page import="com.liferay.portlet.oauth.search.OAuthApplicationDisplayTerms"%>
<%@page import="com.liferay.portlet.oauth.search.OAuthApplicationUserSearch"%>

<%@page import="java.util.List"%>

<%@ include file="/html/init.jsp" %>

<c:if test="<%= SessionMessages.contains(request, OAuthConstants.WEB_APP_REQ_PROCESSED) %>">
	<liferay-ui:success key="<%= OAuthConstants.WEB_APP_REQ_PROCESSED %>" message="your-request-completed-successfully"></liferay-ui:success>
</c:if>

<portlet:actionURL var="searchActionURL">
</portlet:actionURL>

<aui:form action="<%= searchActionURL %>" name="fm">

<liferay-util:include page="/html/admin/toolbar.jsp" servletContext="<%= application %>">
		<liferay-util:param name="toolbarItem" value="view-all" />
</liferay-util:include>

<liferay-ui:search-container
	searchContainer="<%= new OAuthApplicationUserSearch(renderRequest, currentURLObj) %>"
	delta="5">
	
	<liferay-ui:search-form
				page="/html/authorization/search.jsp"
				servletContext="<%= application %>"
			/>
	
	<%
		String name = ((OAuthApplicationSearchTerms)searchContainer.getSearchTerms()).getName();
		
		List<OAuthApplications_Users> oAuthApps = null;
		int oAuthAppsCnt = 0;
		
		if (adminUser) {
			oAuthApps = OAuthApplications_UsersLocalServiceUtil.getOAuthApplications_Userses(searchContainer.getStart(), searchContainer.getEnd());
			oAuthAppsCnt = OAuthApplications_UsersLocalServiceUtil.getOAuthApplications_UsersesCount();
		} else {
			oAuthApps = OAuthApplications_UsersLocalServiceUtil.getOAuthApplications_Userses(searchContainer.getStart(), searchContainer.getEnd());
			oAuthAppsCnt = OAuthApplications_UsersLocalServiceUtil.getOAuthApplications_UsersesCount();
		}
	%>
	
	<liferay-ui:search-container-results
	results="<%= oAuthApps %>"
	total="<%= oAuthAppsCnt %>"
	 />

	<liferay-ui:search-container-row
		className="com.liferay.portal.oauth.model.OAuthApplications_Users"
		keyProperty="oaauid"
		modelVar="appAuth">
		<%
		OAuthApplication app = OAuthApplicationLocalServiceUtil.fetchOAuthApplication(appAuth.getApplicationId());
		%>
		
		<liferay-ui:search-container-column-text
					name="oaauid"
					value="<%= Long.toString(appAuth.getOaauid()) %>"
					orderable="<%= true %>"
				/>
		<liferay-ui:search-container-column-text
					name="application-id-short"
					value="<%= Long.toString(appAuth.getApplicationId()) %>"
					orderable="<%= true %>"
				/>
		<liferay-ui:search-container-column-text
					name="name"
					value="<%= app.getName() %>"
					orderable="<%= false %>"
				/>
		<liferay-ui:search-container-column-text
					name="access-token"
					value="<%= appAuth.getAccessToken() %>"
					orderable="<%= false %>"
				/>
		<liferay-ui:search-container-column-text
					name="authorized"
					value="<%= Boolean.toString(appAuth.getAuthorized()) %>"
					orderable="<%= false %>"
				/>
		<liferay-ui:search-container-column-text
					name="access-level"
					orderable="<%= false %>"
				>
				<liferay-ui:message key='<%= OAuthConstants.WEB_APP_LANG_KEY_ACCESS_TYPE_SHORT.replace("{0}", Integer.toString(app.getAccessLevel())) %>' />
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />

</liferay-ui:search-container>
</aui:form>

