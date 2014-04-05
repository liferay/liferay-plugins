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

<p>
	<liferay-ui:message key="enter-javascript-code-that-returns-true-or-false-to-validate-the-field.-the-following-implicit-variables-are-available"></liferay-ui:message>
</p>

<ul>
	<li>
		<strong>currentFieldValue</strong>: <liferay-ui:message key="the-value-being-validated" />
	</li>
	<li>
		<strong>fieldsMap</strong>: <liferay-ui:message key="the-array-of-all-form-values-indexed-by-name" />
	</li>
</ul>