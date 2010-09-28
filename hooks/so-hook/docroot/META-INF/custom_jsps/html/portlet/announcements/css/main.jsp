<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-announcements {
}

.portlet-announcements .edit-controls {
	margin-bottom: 1.2em;
}

.portlet-announcements .entry {
	color: #3E3639;
	margin-bottom: 1.3em;
}

.portlet-announcements .important {
	font-weight: normal;
}

.portlet-announcements .entry-title {
	background: url(<%= themeImagesPath %>/custom/priority_normal.png) no-repeat;
	border-bottom: 1px solid #CCC;
	font-size: 13px;
	font-weight: bold;
	min-height: 16px;
	margin-bottom: .2em;
	padding: 0 140px 0 20px;
	position: relative;
}

.ie6 .portlet-announcements .entry-title {
	height: 16px;
}

.portlet-announcements .important .entry-title {
	background: url(<%= themeImagesPath %>/custom/priority_important.png) no-repeat;
}

.portlet-announcements .entry-actions {
	font-size: 10px;
	font-weight: normal;
	position: absolute;
	right: 0;
	text-align: right;
	bottom: 0;
}

.portlet-announcements .entry-actions span {
	margin-left: .5em;
}

.portlet-announcements .entry-actions a {
	color: #7F80A4;
}

.portlet-announcements .entry-content {
	font-size: 11px;
}

.portlet-announcements .entry-scope {
	background: #E8EFF4;
	border: 1px solid #B0BAFF;
	font-size: 10px;
	margin-right: 5px;
	padding: 0 5px;
	text-transform: uppercase;
}