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

<liferay-util:html-bottom>
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment-with-locales.min.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<aui:row id="assetCardEntries"></aui:row>

<aui:script use="liferay-asset-feed">
	var AssetFeed = new Liferay.AssetFeed(
		{
			contentNode: 'assetCardEntries',
			locale: '<%= locale %>',
			namespace: '<portlet:namespace />',
			timeZoneOffset: '<%= (timeZone.getRawOffset() / Time.HOUR) * -1 %>'
		}
	).render();

	Liferay.on(
		'assetFeedNewPost',
		function(event) {
			AssetFeed.renderPosts([event]);
		}
	);
</aui:script>