<%@ include file="/html/include.jsp"%>

<portlet:actionURL var="formAction" portletMode="edit">
	<portlet:param name="action" value="urlPreferencesController" />
</portlet:actionURL>



<form:form method="post" action="${formAction}">
	<table cellspacing="5" cellpadding="5">
		<tr>
			<td><b>URL (e.g. http://support.liferay.com):</b></td>
			<td><input type="text" name="url" size="30" maxlength="40"
				value="<%=request.getAttribute("url")%>" /></td>
		</tr>
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td><b>Jira User Name</b></td>
            <td><input type="text" name="userName" size="30" maxlength="40"
                value="<%=request.getAttribute("userName")%>" /></td>
        </tr>
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td><b>Jira User Password</b></td>
            <td><input type="password" name="password" size="30" maxlength="40"
                value="<%=request.getAttribute("password")%>" /></td>
        </tr>
	</table>
	<button type="submit">Save</button>

</form:form>
