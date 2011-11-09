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

html body {
	height: auto;
	padding-bottom: 24px;
}

/* ---------- Bottom Dock ---------- */

.chat-bar {
	background: url(<%= request.getContextPath() %>/images/btm-dock_bg.png);
	border: 1px solid #b5b5b5;
	border-bottom: none;
	bottom: 0;
	height: 24px;
	left: 15px;
	position: fixed;
	right: 15px;
	z-index: 1000;
}

.ie6 .chat-bar {
	position: absolute;
}

.ie6 .lfr-shim {
	bottom: 0;
	border: 0;
	position: absolute;
	z-index: 100;
}

.chat-sound {
	position: absolute;
}

.chat-status {
	float: left;
}

.chat-tabs-container {
}

/* ---------- Bottom Dock Tabs ---------- */

.chat-bar div ul, .chat-bar div ul li {
	list-style-type: none;
	margin: 0;
	padding: 0;
	position: relative;
}

.chat-bar .chat-tabs li {
	float: right;
}

.chat-bar div ul li .panel-trigger {
	cursor: pointer;
	float: left;
	border-left: 1px solid #b5b5b5;
	border-right: 1px solid #e0e0e0;
	color: #111;
	font: bold 11px/24px Tahoma, Geneva, sans-serif;
	text-decoration: none;
	padding: 0 6px;
}

.chat-bar div ul li .panel-trigger:hover {
	background: #fff;
}

.chat-bar div ul li.selected .panel-trigger {
	background: #fff;
	border: 0 1px 1px solid #262626;
	border-radius: 0 4px;
	-moz-border-radius: 0 4px;
	-o-border-radius: 0 4px;
	-webkit-border-radius: 0 4px;
	line-height: 27px;
	height: 25px;
	position: relative;
	z-index: 30;
	margin: -2px 0 -1px;
}
/*
.chat-bar div ul li.buddy-list .panel-trigger {
	border-right: 0;
}*/

/* ---------- Unread Messages ---------- */

.chat-bar div ul li.waiting .panel-trigger {
	background-image: url(<%= request.getContextPath() %>/images/message_waiting_indicator.gif);
}

.chat-bar div ul li .panel-trigger .unread {
	background: url(<%= request.getContextPath() %>/images/unread-messages.png);
	color: #fff;
	font: bold 10px/16px Arial, Helvetica, sans-serif;
	height: 16px;
	position: absolute;
	right: -4px;
	text-align: center;
	top: -6px;
	width: 16px;
	z-index: 30;
}

.chat-bar div ul li.selected .panel-trigger .unread {
	right: -5px;
	top: -4px;
}

/* ---------- Typing status ---------- */

.typing-status {
	float: left;
	width: 16px;
	height: 16px;
	background: url(<%= request.getContextPath() %>/images/typing.png) no-repeat 0 0;
	display: none;
	position: absolute;
	top: 5px;
	left: 4px;
}

.selected .typing-status {
	top: 6px;
}

.typing .typing-status {
	display: block;
}

.chat-bar div ul li.typing .panel-trigger {
	padding-left: 24px;
}

/* ---------- Bottom Dock Popups ---------- */

.chat-bar .chat-panel {
	bottom: 24px;
	display: none;
	position: absolute;
	right: 0;
	z-index: 20;
}

.chat-bar .selected .chat-panel {
	display: block;
}

/* ---------- Online Users ---------- */

.buddy-list .search-buddies .aui-field-content {
	padding: 2px;
}

.buddy-list .search-buddies-field {
	background-image: url(<%= themeImagesPath %>/common/search.png);
	background-repeat: no-repeat;
	background-position: 5px 50%;
	padding-left: 25px;
	width: 100%;
}

.buddy-list .search-buddies-field {
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

.ie6 .buddy-list .search-buddies-field, .ie7 .buddy-list .search-buddies-field {
	width: 88%;
}

.buddy-list .panel-content {
	list-style-type: none;
	margin: 0;
	max-height: 400px;
	overflow-y: scroll;
	overflow-x: hidden;
	padding: 2px 0;
}

.buddy-list .panel-content .online-users li {
	float: left;
	margin: 0 2px;
	padding: 2px;
	height: 24px;
	list-style-type: none;
	cursor: pointer;
	border: 1px solid #fff;
	width: 212px;
}

.buddy-list .panel-content .online-users li {
	margin: 0;
}

.buddy-list .panel-content li.idle {
	height: 14px;
}

.buddy-list .panel-content li:hover {
	background: #f3f3f3;
	border-color: #ddd;
}

.buddy-list .panel-content div {
	float: left;
	font: 12px/24px Arial, Helvetica, sans-serif;
}

.buddy-list .panel-content .idle div {
	line-height: 14px;
}

.buddy-list .panel-content img {
	background: #333;
	display: block;
	float: left;
	height: 24px;
	margin: 0 6px 0 0;
	width: 24px;
}

.buddy-list .panel-content .idle img {
	display: none;
}

/* ---------- Popup Window ---------- */

.chat-portlet .chat-settings .settings {
	float: none;
}

.chat-portlet .chat-settings li {
	border-bottom: 1px solid #999;
	clear: both;
	float: none;
	padding: 10px;
	position: static;
}

.chat-settings label {
	font-weight: bold;
}

.chat-settings input {
	vertical-align: middle;
}

.chat-settings .ctrl-holder {
	padding: 10px;
}

.chat-settings.saved .panel-trigger .trigger-name {
	background: url(<%= themeImagesPath %>/messages/success.png) no-repeat 0 0;
	padding-left: 20px;
	padding-bottom: 2px;
	padding-top: 2px;
}

.chat-status .status-message {
	margin: 5px;
	padding-left: 10px;
}

.panel-window {
	border-radius: 4px 4px 0 4px;
	-moz-border-radius: 4px 4px 0 4px;
	-o-border-radius: 4px 4px 0 4px;
	-webkit-border-radius: 4px 4px 0 4px;
	background: #fff;
	border: 1px solid #262626;
	font: 11px Tahoma, Geneva, sans-serif;
	min-width: 226px;
	position: relative;
}

.panel-icon {
	background: #333;
	border: 1px solid #000;
	height: 48px;
	position: absolute;
	top: 4px;
	left: 6px;
	display: block;
	width: 48px;
}

.panel-title {
	background: #20272b;
	color: #f2f2f2;
	font-weight: bold;
	height: 24px;
	line-height: 24px;
	text-indent: 60px;
}

.buddy-list .panel-title, .chat-settings .panel-title {
	text-indent: 6px;
}

.panel-button {
	cursor: pointer;
	height: 12px;
	width: 12px;
}

.panel-button.close {
	background: url(<%= request.getContextPath() %>/images/chat-close_button.png) left bottom;
	position: absolute;
	right: 4px;
	top: 6px;
}

.panel-button.close:hover {
	background-position: left top;
}

.panel-button.minimize {
	background: url(<%= request.getContextPath() %>/images/chat-minimize_button.png) left bottom;
	position: absolute;
	right: 20px;
	top: 6px;
}

.buddy-list .panel-button.minimize, .chat-settings .panel-button.minimize {
	right: 4px;
}

.panel-button.minimize:hover {
	background-position: left top;
}

.panel-profile {
	background: #eee;
	border-bottom: 1px solid #ccc;
	height: 27px;
	padding-top: 5px;
	text-indent: 60px;
}

.panel-output {
	border-bottom: 1px solid #000;
	height: 192px;
	overflow-y: scroll;
	padding: 2px;
}

.ie .panel-output {
	position: relative;
}

.ie6 .panel-window, .ie7 .panel-window {
	min-width: auto;
	width: 226px;
}

.panel-output .blurb {
	margin: 0;
	position: relative;
}

.panel-output .name {
	background: #828F95;
	color: #fff;
	display: block;
	line-height: 11px;
	padding: 2px 4px 3px;
}

.panel-output .incoming .name {
	background: #D3DADD;
	color: #555;
}

.panel-output .date {
	color: #eee;
	font-size: 10px;
	font-style: normal;
	position: absolute;
	right: 8px;
	top: 2px;
}

.panel-output .incoming .date {
	color: #555;
}

.panel-output .text {
	display: block;
	line-height: 14px;
	padding: 4px 8px 8px;
}

.panel-input {
	border-top: 1px solid #369;
	position: relative;
	height: 19px;
}

.panel-input textarea {
	background: none;
	border: 0;
	bottom: 0;
	display: block;
	float: left;
	height: 14px;
	overflow: hidden;
	padding: 2px;
	position: absolute;
	width: 222px;
}

.panel-input textarea, .chat-height-monitor {
	font: 11px/14px Tahoma, Geneva, sans-serif;
}

.panel-input textarea.focus, .ie6 .panel-input textarea.focus {
	background: #fff;
	border-width: 0;
}

.chat-height-monitor {
	left: -10000em;
	padding: 0;
	position: absolute;
	top: -10000em;
	white-space: pre-wrap;
	white-space: -moz-pre-wrap;
	white-space: -pre-wrap;
	white-space: -o-pre-wrap;
}

.show-notifications-setting {
	display: none;
}

.desktop-notifications .show-notifications-setting {
	display: block;
}

.ie .chat-height-monitor {
	word-wrap: break-word;
	zoom: 1;
}

/* ---------- Buddy service extensions ---------- */

.chat-bar .online-users .buddy-services {
	display: none;
	float: right;
	margin-top: 4px;
}

.chat-bar .panel-content li:hover .buddy-services {
	display: block;
}

.chat-bar .buddy-services div {
	background: transparent no-repeat;
	float: left;
	height: 16px;
	margin-right: 3px;
	width: 16px;
}