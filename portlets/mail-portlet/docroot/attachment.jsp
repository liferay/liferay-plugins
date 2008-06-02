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

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.mail.util.MailBoxManager" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.util.mail.JavaMailUtil" %>
<%@ page import="com.liferay.util.servlet.ServletResponseUtil" %>

<%@ page import="javax.mail.Part" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
int accountId = ParamUtil.getInteger(request, "accountId");
String folderName = ParamUtil.getString(request, "folderName");
int messageUid = ParamUtil.getInteger(request, "messageUid");
String fileName = ParamUtil.getString(request, "fileName");
String contentPath = ParamUtil.getString(request, "contentPath");

MailBoxManager mailBoxManager = new MailBoxManager(user, accountId);

Part messagePart = mailBoxManager.getAttachment(folderName, messageUid, contentPath);

byte[] content = JavaMailUtil.getBytes(messagePart);
String contentType = messagePart.getContentType();

ServletResponseUtil.sendFile(response, fileName, content, contentType);
%>