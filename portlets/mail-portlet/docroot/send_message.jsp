<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
MailManager mailManager = MailManager.getInstance(request);

if (mailManager == null) {
	return;
}

UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(liferayPortletRequest);

long accountId = ParamUtil.getLong(uploadPortletRequest, "accountId");
long messageId = ParamUtil.getLong(uploadPortletRequest, "messageId");
String to = ParamUtil.getString(uploadPortletRequest, "to");
String cc = ParamUtil.getString(uploadPortletRequest, "cc");
String bcc = ParamUtil.getString(uploadPortletRequest, "bcc");
String subject = ParamUtil.getString(uploadPortletRequest, "subject");
String body = ParamUtil.getString(uploadPortletRequest, "body");

int attachmentCount = ParamUtil.getInteger(uploadPortletRequest, "attachmentCount");

List<MailFile> mailFiles = new ArrayList<MailFile>();

try {
	for (int i = 1; i <= attachmentCount; i++) {
		File file = uploadPortletRequest.getFile("attachment" + i, true);

		String fileName = uploadPortletRequest.getFileName("attachment" + i);

		long size = uploadPortletRequest.getSize("attachment" + i);

		if (Validator.isNull(fileName)) {
			continue;
		}

		MailFile mailFile = new MailFile(file, fileName, size);

		mailFiles.add(mailFile);
	}
%>

	<%= mailManager.sendMessage(accountId, messageId, to, cc, bcc, subject, body, mailFiles) %>

<%
}
finally {
	for (MailFile mailFile : mailFiles) {
		mailFile.cleanUp();
	}
}
%>