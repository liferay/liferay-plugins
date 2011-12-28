<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/sample_struts_portlet/init.jsp" %>

<tiles:useAttribute id="tilesPortletContent" name="portlet_content" classname="java.lang.String" ignore="true" />

<div>
	<jsp:include page='<%= "/html" + tilesPortletContent %>' flush="true" />
</div>

<div class="separator"></div>

<div>
	<jsp:include page="/html/portlet/sample_struts_portlet/nav.jsp" flush="true" />
</div>

<br />

<img hspace="0" src="<%= request.getContextPath() %>/html/image/struts-power.gif" vspace="0">