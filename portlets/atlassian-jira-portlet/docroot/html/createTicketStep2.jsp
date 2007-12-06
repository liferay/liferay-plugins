<%@ include file="/html/include.jsp" %>


<portlet:actionURL var="formAction">
    <portlet:param name="action" value="createTicket"/>
    <portlet:param name="_page">
        <jsp:attribute name="value">1</jsp:attribute>
    </portlet:param>
</portlet:actionURL>

<form:form method="post" commandName="createIssue" action="${formAction}">
<form:errors path="*" cssStyle="color:red"/>
<table>
<tr>
    <td>
        Project:
    </td>
    <td>
        <font color="red"><c:out value="${selectedProject.name}"/> </font>
    </td>
</tr>
<tr>
    <td>
        IssueType:
    </td>
    <td>
        <font color="red"><c:out value="${selectedIssueType.issueTypeName}"/> </font>
    </td>
</tr>
<tr>
    <td>
        <i>Summary: *</i>
    </td>
    <td>
        <form:textarea path="summary"/><form:errors path="summary"
                                                    cssStyle="color:red"/>
    </td>
</tr>
<tr>
    <td>
        Priority:
    </td>
    <td>
        <form:select path="priority">
            <c:forEach items="${priorities}" var="priority">
                <option value="<c:out value="${priority.text}" />">
                    <c:out value="${priority.priorityName}"/>
                </option>
            </c:forEach>
        </form:select>
    </td>
</tr>
<tr>
    <td>
        Due Date [11-20-2007]:
    </td>
    <td>
        <form:input path="dueDate" size="20"/>
    </td>
</tr>
<tr>
    <td>
        Component:
    </td>
    <td>
        <form:select path="components" multiple="true" size="3">
            <c:forEach items="${components}" var="component">
                <option value="Unknown,-1">
                    Unknown
                </option>
                <option value="<c:out value="${component.text}"/>">
                    <c:out value="${component.name}"/>
                </option>
            </c:forEach>
        </form:select>
    </td>
</tr>
<tr>
    <td>
        Affects Version:
    </td>
    <td>

        <form:select path="versions" multiple="true" size="6">

            <option value="-1,Unknown">
                Unknown
            </option>

            <optgroup label="Affected Versions">
                <c:forEach items="${versions}" var="version">
                    <option value="<c:out value="${version.text}" />,<c:out value="${version.versionName}"/>">
                        <c:out value="${version.versionName}"/>
                    </option>
                </c:forEach>
            </optgroup>
        </form:select>
    </td>
</tr>
<tr>
    <td>
        Fix Version:
    </td>
    <td>
        <form:select path="fixedVersions" multiple="true" size="6">
            <option value="-1,Unknown">
                Unknown
            </option>
            <optgroup label="Fixed Versions">

                <c:forEach items="${versions}" var="version">
                    <option value="<c:out value="${version.text}" />,<c:out value="${version.versionName}"/>">
                        <c:out value="${version.versionName}"/>
                    </option>
                </c:forEach>
            </optgroup>
        </form:select>
    </td>
</tr>

<tr>
    <td>
        Assignee:
    </td>
    <td>

        <form:select path="assigneeName">
            <option value="">
                Unassigned
            </option>
            <option value="-1" selected>
                - Automatic-
            </option>
            <optgroup label="---------------">

                <c:forEach items="${assignee}" var="assignee">
                    <option value="<c:out value="${assignee.name}" />">
                        <c:out value="${assignee.fullName}"/>
                    </option>
                </c:forEach>
            </optgroup>
        </form:select>
        <a title="Assign this issue to me"
           onclick="document.getElementById('assignee').value = '<c:out value="${reporter}" />';return false;"
           href="#">Assign to me</a>
    </td>
</tr>
<tr>
    <td>
        Reporter:
    </td>
    <td>
        <c:out value="${reporter}" />
    </td>
</tr>
<tr>
<td>
    Environment:
</td>
<td>
<form:textarea path="environment"/>
</td>
</tr>
<tr>
    <td>
        Description:
    </td>
    <td>
        <form:textarea path="description"/>
    </td>
</tr>
</table>
<br/>
<input type="submit" name="_target0" value="Previous">
<input type="submit" name="_finish" value="Finish"/>

</form:form>

