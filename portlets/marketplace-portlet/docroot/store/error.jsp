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

<style type="text/css">
	body {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		font-size: 16px;
	}

	p {
		background: #F1D1D8;
		border: 1px solid transparent;
		border-radius: 4px;
		color: #D77C8A;
		padding: 14px;
		margin: 2em 0;
	}

	.error {
		margin: 0 auto;
		max-width: 960px;
		padding: 6em 4em;
	}
</style>

<div class="error">
	<img src="<%= PortalUtil.getPathContext(request) %>/store/images/logo.svg" />

	<p>
		<liferay-ui:message key="could-not-connect-to-the-liferay-marketplace" />
	</p>

	<a href="http://www.liferay.com/marketplace" target="_blank"><liferay-ui:message key="browse-the-marketplace-on-liferay.com" /></a>
</div>