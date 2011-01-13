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

.lfr-menu-list .disabled .taglib-icon {
	color: #999;
}

.contacts-portlet-contacts-center .contact-search {
	float: right;
	margin: 0 0 .5em .5em;
}

.contacts-portlet-contacts-center .lfr-asset-column-details .lfr-asset-requests {
	background: url(<%= themeImagesPath %>/common/add_user.png) no-repeat 0 50%;
}

.contacts-portlet-contacts-center .asset-image {
	float: left;
	margin: 0 .5em .5em 0;
}

.contacts-portlet-contacts-center .asset-image img {
	width: 50px;
}

.contacts-portlet-contacts-center .asset-action,
.contacts-portlet-contacts-center .asset-data {
	margin-left: 60px;
}

.contacts-portlet-contacts-center .asset-data-name,
.contacts-portlet-contacts-center .asset-data-title {
	font-size: 1.2em;
}

.contacts-portlet-contacts-center .asset-data-job-title,
.contacts-portlet-contacts-center .asset-data-title a {
	font-weight: bold;
}

.contacts-portlet-contacts-center .asset-data-extra {
	color: #777;
}

.contacts-portlet-contacts-center .asset-action-item a {
	padding: 0 10px 0 20px ;
}

.contacts-portlet-contacts-center .asset-action-confirm a {
	background: url(<%= themeImagesPath %>/common/activate.png) no-repeat;
}

.contacts-portlet-contacts-center .asset-action-ignore a {
	background: url(<%= themeImagesPath %>/common/deactivate.png) no-repeat;
}