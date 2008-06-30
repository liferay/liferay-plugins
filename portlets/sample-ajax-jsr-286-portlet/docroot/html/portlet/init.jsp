<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %> 
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.sample.model.NotepadEntry" %>
<%@ page import="com.liferay.sample.service.NotepadEntryLocalServiceUtil" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
DateFormat dateDisplayDate = new SimpleDateFormat("MMM d", locale);
DateFormat dateDisplayTime = new SimpleDateFormat("h:mm a", locale);

DateFormat dateFormatDate = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);

Date now = new Date();
Date today = dateFormatDate.parse(dateFormatDate.format(now));

String notepadImagePath = request.getContextPath() + "/html/portlet/notepad/images/";
%>