<%@ page import="com.liferay.portlet.atlassian.jira.action.JiraPortletConstants" %>
<%@ page import="javax.portlet.PortletRequest" %>
<%@ page import="javax.portlet.PortletSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
    PortletRequest portletReq =
        (PortletRequest)request.getAttribute("javax.portlet.request");
    PortletSession portletSession = portletReq.getPortletSession();
    if (portletSession.getAttribute(JiraPortletConstants.MODE_KEY) != null) {
        out.println("SIMULATOR MODE");
    }

%>
