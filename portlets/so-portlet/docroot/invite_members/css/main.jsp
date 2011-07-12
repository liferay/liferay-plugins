<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/css_init.jsp" %>

.so-portlet-invite-members .email-invited h2 span {
	color: #999;
	font-size: .8em;
	font-weight: normal;
	margin-left: 2em;
	position: absolute;
	right: 5px;
}

.so-portlet-invite-members .users-wrapper {
	min-height: 450px;
	position: relative;
}

.so-portlet-invite-members .search {
	background: #FFF url(<%= themeImagesPath %>/forms/input_shadow.png) no-repeat;
	border-color: #BFBFBF #DEDEDE #DEDEDE #BFBFBF;
	border-style: solid;
	border-width: 1px;
	height: 300px;
	width: 205px;
	overflow-x: hidden;
	overflow-y: auto;
}

.ie6 .so-portlet-invite-members .search {
	background-image: none;
}

.so-portlet-invite-members .user-search-wrapper {
	background: #F0F5F7;
	border: 1px solid #B0BAFF;
	float: left;
	padding: 12px;
}

.so-portlet-invite-members .invited-users-wrapper {
	margin-left: 250px;
}

.so-portlet-invite-members h2 {
	font-size: 1em;
	margin: 0 0 1em;
}

.so-portlet-invite-members .list {
	margin-bottom: 1em;
}

.so-portlet-invite-members #invite-user-search {
	width: 203px;
}

.so-portlet-invite-members .invited-users-wrapper h2 {
	border-bottom: 1px solid #CCC;
	color: #3E3639;
	font-size: 1.1em;
	margin: 0;
	padding-top: .5em;
	position: relative;
}

.so-portlet-invite-members .invited-users-wrapper .invite-actions {
	background: #EEE;
	border: 1px solid #CCC;
	margin: 2.5em 0;
	padding: 5px;
	text-align: center;
}

.so-portlet-invite-members .invited-users-wrapper .invite-actions h2 {
	margin: 0 0 .5em;
	padding: 0;
}

.so-portlet-invite-members .invited-users-wrapper .invite-actions input {
	margin-right: 12px;
}

.so-portlet-invite-members .user-invited h2 span {
	color: #999;
	font-size: .8em;
	font-weight: normal;
	margin-left: 2em;
	position: absolute;
	right: 5px;
}

.so-portlet-invite-members .user,
.so-portlet-invite-members .invited {
	background: transparent url(<%= themeImagesPath %>/arrows/01_plus.png) no-repeat 0 50%;
	border-top: 1px solid transparent;
	border-bottom: 1px solid transparent;
	cursor: pointer;
	list-style-type: none;
	padding: 2px 5px 2px 18px;
	white-space: nowrap;
}

.ie6 .so-portlet-invite-members .user,
.ie6 .so-portlet-invite-members .invited {
	border-top: 1px solid #FFF;
	border-bottom: 1px solid #FFF;
}

.so-portlet-invite-members .user-invited .user,
.so-portlet-invite-members .email-invited .user {
	background: #DFD url(<%= themeImagesPath %>/arrows/01_minus.png) no-repeat 99% 50%;
	border-bottom: 1px solid #CCC;
	padding: .5em;
}

.so-portlet-invite-members .email-invited .user {
	background-color: #EEF;
}

.so-portlet-invite-members .user-invited .user span {
	display: block;
	clear: none;
}

.so-portlet-invite-members .user-invited .user .name {
	color: #555;
	font-weight: bold;
	float: left;
	width: 40%;
}

.so-portlet-invite-members .user-invited .user .email {
	color: #000;
	font-size: 1em;
	width: 50%;
}

.so-portlet-invite-members .email-invited .user .email {
	color: #555;
	font-size: 1em;
	font-weight: bold;
	margin: 0;
	width: 40%;
}

.so-portlet-invite-members .controls a {
	background: url("<%= themeImagesPath %>/custom/bullet.png") no-repeat 3px 50%;
	padding-left: 10px;
}

.so-portlet-invite-members .invited {
	background: #DFD url(<%= themeImagesPath %>/arrows/01_minus.png) no-repeat 0 50%;
	border-color: #AFA;
}

.so-portlet-invite-members .invite-to select {
	margin-top: 1em;
	min-width: 300px;
}

.ie .so-portlet-invite-members .invite-to select {
	width: 300px;
}

.so-portlet-invite-members span.email {
	color: #AAA;
	font-size: 0.8em;
	margin-left: 0.5em;
}