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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
ResultRow row2 = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

FileEntry fileEntry2 = (FileEntry)row2.getObject();

fileEntry2 = _wrap(fileEntry2);

row2.setObject(fileEntry2);

request.setAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW, row2);
%>

<liferay-util:include page="/html/portlet/message_boards/deleted_message_attachment_action.jsp" useCustomPage="<%= false %>" />