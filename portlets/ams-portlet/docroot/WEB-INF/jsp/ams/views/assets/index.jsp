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

<%@ include file="/WEB-INF/jsp/ams/views/init.jsp" %>

<portlet:renderURL var="editAssetURL">
	<portlet:param name="controller" value="assets" />
	<portlet:param name="action" value="edit" />
	<portlet:param name="format" value="html" />
</portlet:renderURL>

<div>
	<aui:button href="${editAssetURL}" value="add-asset" />
</div>

<c:if test="${fn:length(assets) > 0}">
	<br />
</c:if>

<table class="lfr-table">

<c:if test="${fn:length(assets) > 0}">
	<tr>
		<th>
			ID
		</th>
		<th>
			Serial Number
		</th>
	</tr>
</c:if>

<c:forEach items="${assets}" var="asset">
	<tr>
		<td>
			<portlet:renderURL var="viewAssetURL">
				<portlet:param name="controller" value="assets" />
				<portlet:param name="action" value="view" />
				<portlet:param name="id" value="${asset.assetId}" />
				<portlet:param name="format" value="html" />
			</portlet:renderURL>

			<a href="${viewAssetURL}">${asset.assetId}</a>
		</td>
		<td>
			<a href="${viewAssetURL}">${asset.serialNumber}</a>
		</td>
		<td>
			<portlet:actionURL var="deleteAssetURL">
				<portlet:param name="controller" value="assets" />
				<portlet:param name="action" value="delete" />
				<portlet:param name="id" value="${asset.assetId}" />
				<portlet:param name="format" value="html" />
			</portlet:actionURL>

			<a href="javascript:submitForm(document.hrefFm, '${deleteAssetURL}&p_p_state=normal');">X</a>
		</td>
	</tr>
</c:forEach>

</table>