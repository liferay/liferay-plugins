<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@ include file="/init.jsp" %>

<%

String portletNamespace = "_1_WAR_mailportlet_";
		 
MailManager mailManager = MailManager.getInstance(request);

List<MailFile> mailFiles = new ArrayList<MailFile>();

UploadServletRequest uploadServletRequest =
	PortalUtil.getUploadServletRequest(request);

long accountId = ParamUtil.getLong(uploadServletRequest, portletNamespace + "accountId");
long messageId = ParamUtil.getLong(uploadServletRequest, portletNamespace + "messageId");
String to = ParamUtil.getString(uploadServletRequest, portletNamespace + "to");
String cc = ParamUtil.getString(uploadServletRequest, portletNamespace + "cc");
String bcc = ParamUtil.getString(uploadServletRequest, portletNamespace + "bcc");
String subject = ParamUtil.getString(uploadServletRequest, portletNamespace + "subject");
String body = ParamUtil.getString(uploadServletRequest, portletNamespace + "body");

int attachmentCount = ParamUtil.getInteger(
		uploadServletRequest, portletNamespace + "attachmentCount");

JSONObject responseDataJSONObject = null;

try {
	for (int i = 1; i <= attachmentCount; i++) {
		File file = uploadServletRequest.getFile(
				portletNamespace + "attachment" + i, true);
		
		String fileName = uploadServletRequest.getFileName(
				portletNamespace + "attachment" + i);

 		long size = uploadServletRequest.getSize(portletNamespace + "attachment" + i);

		if (file == null) {
			continue;
		}

		MailFile mailFile = new MailFile(file, fileName, size);

		mailFiles.add(mailFile);
	}
	if (mailManager != null) {
		responseDataJSONObject = mailManager.sendMessage(
				accountId, messageId, to, cc, bcc, subject, body, mailFiles);
	}
}
finally {
	for (MailFile mailFile : mailFiles) {
		mailFile.cleanUp();
	}
}

%>
<%= responseDataJSONObject %>

