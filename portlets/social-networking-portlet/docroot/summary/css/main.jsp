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

.ie .social-networking-portlet-summary .summary-container {
	height: 1%;
}

.social-networking-portlet-summary .summary-container:after {
	clear: both;
	content: ".";
	display: block;
	height: 0;
	visibility: hidden;
}

.social-networking-portlet-summary .summary-container h2 {
	color: #3D536C;
	font-size: 16px;
	margin-bottom: 10px;
	margin-top: 0;
}

.social-networking-portlet-summary .summary-container img {
	margin: 5px 0;
	max-width: 120px;
}

.ie6 .social-networking-portlet-summary .summary-container img {
	width: 120px;
}

.social-networking-portlet-summary .summary-container p {
	margin-bottom: 10px;
}

.social-networking-portlet-summary .summary-container span {
	color: #3D536C;
	font-size: 10px;
	font-weight: bold;
	text-transform: uppercase;
}

.social-networking-portlet-summary .summary-container .user-profile-image {
	max-width: 180px;
}

.ie6 .social-networking-portlet-summary .summary-container .user-profile-image {
	width: expression(this.width > 180 : '180px' : 'auto');
}

.social-networking-portlet-summary .summary-container .user-twitter-link {
	background: url(../images/twitter.png) no-repeat 0 50%;
	padding-left: 18px;
}

.social-networking-portlet-summary .summary-container .add-as-friend {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .add-as-friend.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .friend-requests.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .join-site {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .join-site.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .join-organization {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .join-organization.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .remove-site {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .remove-organization {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .remove-friend {
	margin: 0.7em auto 1em;
}