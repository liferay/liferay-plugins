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

<%@ include file="/admin/init.jsp" %>

<%
Template template = (Template)request.getAttribute(WebKeys.KNOWLEDGE_BASE_TEMPLATE);
%>

<%@ include file="/admin/top_links.jspf" %>

<div class="float-container kb-entity-header">
	<div class="kb-title">
		<%= template.getTitle() %>
	</div>

	<div class="kb-tools">
		<%@ include file="/admin/template_tools.jspf" %>
	</div>
</div>

<div class="kb-entity-body">

	<%
	request.setAttribute("template_icons.jspf-selTemplate", template);
	%>

	<%@ include file="/admin/template_icons.jspf" %>

	<%= template.getContent() %>

	<%@ include file="/admin/template_comments.jspf" %>
</div>