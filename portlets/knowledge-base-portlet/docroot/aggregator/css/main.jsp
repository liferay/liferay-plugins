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

<%@ include file="/css_init.jsp" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/admin/css/main.jsp" servletContext="<%= application %>" />
</liferay-util:buffer>

<%= StringUtil.replace(html, "knowledge-base-portlet-admin", "knowledge-base-portlet-aggregator") %>

/* ---------- Configuration ---------- */

.portlet-configuration .aui-field-wrapper-content {
	margin: 0;
}

.portlet-configuration .kb-asset-selector-wrapper {
	margin: 5px 0 0;
}

.portlet-configuration .kb-edit-link {
	margin-top: 5px;
}

.portlet-configuration .kb-field-wrapper {
	margin: 10px 0;
}

.portlet-configuration .kb-selected-entries span {
	display: block;
}

/* ---------- Portlet ---------- */

.knowledge-base-portlet-aggregator .kb-results-body .kb-title-wrapper-first {
	margin-top: 0;
}

.knowledge-base-portlet-aggregator .kb-select-box select {
	min-width: 150px;
}