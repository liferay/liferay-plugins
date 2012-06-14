<%@page import="com.liferay.portlet.oauth.search.OAuthApplicationUserSearch"%>
<%@page import="com.liferay.portlet.oauth.search.OAuthApplicationDisplayTerms"%>
<%@page import="com.liferay.portlet.oauth.search.OAuthApplicationSearch"%>

<%@ include file="/html/init.jsp" %>

<%
OAuthApplicationUserSearch searchContainer = (OAuthApplicationUserSearch)request.getAttribute("liferay-ui:search:searchContainer");
boolean showAddButton = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:search:showAddButton"));

OAuthApplicationDisplayTerms displayTerms = (OAuthApplicationDisplayTerms)searchContainer.getDisplayTerms();
%>

<span class="aui-search-bar lfr-display-terms-search">
	<aui:input inlineField="<%= true %>" label="" name="<%= displayTerms.NAME %>" size="30" type="text" value="<%= displayTerms.getName() %>" />

	<aui:button type="submit" value="search" />
</span>
<div class="separator"><!-- --></div>