<%@ include file="/html/include.jsp" %>

<portlet:actionURL var="formAction">
    <portlet:param name="action" value="createTicket"/>
    <portlet:param name="_page">
        <jsp:attribute name="value">0</jsp:attribute>
    </portlet:param>
</portlet:actionURL>

<form:form method="post" action="${formAction}">
    <form:errors path="*" cssStyle="color:red"/>
    <table>
        <tr>
            <td>Project:</td>

            <td>
                <select name="project">
                    <c:forEach items="${projects}" var="project">
                        <option
                            value="<c:out value="${project.text}" />">
                            <c:out value="${project.name}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr colspan="2">
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>IssueType:</td>
            <td>
                <select name="issueType">
                    <c:forEach items="${issueTypes}" var="issueType">
                        <option
                            value="<c:out value="${issueType.text}" />">
                            <c:out value="${issueType.issueTypeName}"/></option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr colspan="2">
            <td>&nbsp;</td>
        </tr>
    </table>
    <br/>
    <input type="submit" name="_target1" value="Next">
</form:form>
