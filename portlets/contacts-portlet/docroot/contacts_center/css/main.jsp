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

.contacts-portlet-contacts-center .lfr-user-grid {
	list-style: none;
	margin: 0;
}

.contacts-portlet-contacts-center .lfr-user-grid-item {
	display: inline-block;
	height: 50px;
	margin: 0 5px 5px 0;
	position: relative;
	width: 50px;
}

.contacts-portlet-contacts-center .lfr-user-grid-item.hover {
	z-index: 99;
}

.contacts-portlet-contacts-center .lfr-user-grid-item .lfr-user-thumb {
	background: #C8C9CA;
	border: 1px solid #C8C9CA;
	height: 50px;
	width: 50px;
}

.contacts-portlet-contacts-center .lfr-user-grid-item a {
	clip: rect(0 50px 50px 0);
	position: absolute;
}

.contacts-portlet-contacts-center .lfr-user-grid-item img {
	position: relative;
	width: 50px;
}

.contacts-portlet-contacts-center .lfr-user-grid-item.hover img {
	z-index: 100;
}

.contacts-portlet-contacts-center .lfr-user-grid-item .lfr-user-info {
	background: #D7F1FF;
	border: 1px solid #88C5D9;
	display: none;
	left: -5px;
	padding: 5px 5px 5px 60px;
	position: absolute;
	top: -5px;
	width: 175px;
}

.contacts-portlet-contacts-center .lfr-user-grid-item.hover .lfr-user-info {
	display: block;
}

.contacts-portlet-contacts-center .lfr-user-portrait {
	float: left;
	height: 50px;
	margin: 0 .5em .5em 0;
	overflow: hidden;
}

.contacts-portlet-contacts-center .lfr-user-portrait img {
	width: 50px;
}

.contacts-portlet-contacts-center .lfr-user-action,
.contacts-portlet-contacts-center .lfr-user-data {
	margin-left: 60px;
}

.contacts-portlet-contacts-center .lfr-user-data-name,
.contacts-portlet-contacts-center .lfr-user-data-title {
	font-size: 1.2em;
}

.contacts-portlet-contacts-center .lfr-user-data-job-title,
.contacts-portlet-contacts-center .lfr-user-data-title a {
	font-weight: bold;
}

.contacts-portlet-contacts-center .lfr-user-data-extra {
	color: #777;
}

.contacts-portlet-contacts-center .lfr-user-action-item a {
	padding: 0 10px 0 20px ;
}

.contacts-portlet-contacts-center .lfr-user-action-confirm a {
	background: url(<%= themeImagesPath %>/common/activate.png) no-repeat;
}

.contacts-portlet-contacts-center .lfr-user-action-ignore a {
	background: url(<%= themeImagesPath %>/common/deactivate.png) no-repeat;
}