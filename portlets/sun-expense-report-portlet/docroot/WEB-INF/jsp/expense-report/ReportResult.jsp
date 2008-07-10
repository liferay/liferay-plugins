<%@ taglib prefix="a" uri="http://jmaki/v1.0/jsp" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ page import="javax.portlet.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<% if("success".equals(request.getAttribute("result"))) {%>
The Expense Report #<%=(String)renderRequest.getAttribute("ReportID")%> has been successfully created.
<% } else {%>
There was an error in creating the Report. Please try again later.

<% } %>
<a href='<portlet:renderURL/>'>Go back to Report Summary</a>
