<%@ include file="/html/include.jsp"%>


You have created a new issue, <b><c:out value="${ticketId}" /></b>, in the project.  To view the ticket, please select the following link:
<a href = "<c:out value="${url}" />/browse/<c:out value="${ticketId}" />" target="_blank">Browse <c:out value="${ticketId}" /></a><br/>
<br/>
To create another issue, select the following link: <a href = "<portlet:renderURL var="newAction" portletMode="view">
	<portlet:param name="action" value="createTicket" />
</portlet:renderURL>">Create New Ticket</a>
