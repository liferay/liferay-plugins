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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

MBCategory category = messageDisplay.getCategory();

String displayStyle = BeanPropertiesUtil.getString(category, "displayStyle", MBCategoryConstants.DEFAULT_DISPLAY_STYLE);

if (Validator.isNull(displayStyle)) {
	displayStyle = MBCategoryConstants.DEFAULT_DISPLAY_STYLE;
}
%>

<div class="displayStyle-<%= displayStyle %>">
	<liferay-util:include page='<%= "/html/seven_cogs/portlet/message_boards/view_message_" + displayStyle + ".jsp" %>' />
</div>