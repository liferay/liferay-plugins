<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

<%@ include file="/json_init.jsp" %>

<%
String emailAddress = ParamUtil.getString(request, "emailAddress");
boolean emailAddressSameAsUsername = ParamUtil.getBoolean(request, "emailAddressSameAsUsername");
String mailInHostName = ParamUtil.getString(request, "mailInHostName");
String mailInPort = ParamUtil.getString(request, "mailInPort");
String mailOutHostName = ParamUtil.getString(request, "mailOutHostName");
String mailOutPort = ParamUtil.getString(request, "mailOutPort");
boolean mailSecure = ParamUtil.getBoolean(request, "mailSecure");
String password = ParamUtil.getString(request, "password");
String username = ParamUtil.getString(request, "username");

if (emailAddressSameAsUsername) {
	emailAddress = username;
}

MailBoxManager mailBoxManager = new MailBoxManager(user, emailAddress, false, mailInHostName, mailInPort, mailOutHostName, mailOutPort, mailSecure, password, username);
%>

<%= mailBoxManager.storeAccountToDisk() %>