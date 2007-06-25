<%
/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/sample_struts_liferay_portlet/init.jsp" %>

<bean:define id="firstName" name="unsubscribeForm" property="firstName" type="java.lang.String" />
<bean:define id="lastName" name="unsubscribeForm" property="lastName" type="java.lang.String" />
<bean:define id="emailAddress" name="unsubscribeForm" property="emailAddress" type="java.lang.String" />

<logic:messagesPresent>
	<span class="portlet-msg-error">
	<html:errors />
	</span>
</logic:messagesPresent>

<html:form action="/sample_struts_liferay_portlet/unsubscribe?actionURL=true" method="post" focus="firstName">

<table border="0" cellpadding="0" cellspacing="0">
<tr>
	<td>
		First Name
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:text name="unsubscribeForm" property="firstName" size="23" />
	</td>
</tr>
<tr>
	<td>
		Last Name
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:text name="unsubscribeForm" property="lastName" size="23" />
	</td>
</tr>
<tr>
	<td>
		Email Address
	</td>
	<td style="padding-left: 10px;"></td>
	<td>
		<html:text name="unsubscribeForm" property="emailAddress" size="23" />
	</td>
</tr>
</table>

<br>

<html:submit>Unsubscribe</html:submit>

</html:form>