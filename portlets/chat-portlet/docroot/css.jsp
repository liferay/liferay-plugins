<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/css_init.jsp" %>

body {
	height: auto;
	padding-bottom: 24px;
}

/* ---------- Bottom Dock ---------- */

#btm-dock {
	background: url(<%= request.getContextPath() %>/images/btm-dock_bg.png);
	border: 1px solid #b5b5b5;
	border-bottom: none;
	bottom: 0;
	height: 24px;
	left: 15px;
	position: fixed;
	right: 15px;
	z-index: 10;
}

#btm-dock-sound {
	position: absolute;
}

#btm-dock-left {
	float: left;
}

#btm-dock-right {
	float: right;
}

/* ---------- Bottom Dock Tabs ---------- */

#btm-dock > div > ul, #btm-dock > div > ul > li {
	float: left;
	list-style-type: none;
	margin: 0;
	padding: 0;
	position: relative;
}

#btm-dock #chat-tabs > li {
	float: right;
}

#btm-dock > div > ul > li > .link {
	cursor: pointer;
	float: left;
	border-left: 1px solid #b5b5b5;
	border-right: 1px solid #e0e0e0;
	color: #111;
	font: bold 11px/24px Tahoma, Geneva, sans-serif;
	text-decoration: none;
	padding: 0 6px;
}

#btm-dock > div > ul > li > .link:hover {
	background: #fff;
}

#btm-dock > div > ul > li.selected > .link {
	background: #fff;
	border: 1px solid #262626;
	border-top: none;
	-moz-border-radius-bottomleft: 4px;
	-webkit-border-radius-bottomleft: 4px;
	-moz-border-radius-bottomright: 4px;
	-webkit-border-radius-bottomright: 4px;
	line-height: 27px;
	height: 25px;
	position: relative;
	z-index: 30;
	margin: -2px 0 -1px;
}

#btm-dock > div > ul > li.buddy-list > .link {
	border-right: 0;
}

/* ---------- Unread Messages ---------- */

#btm-dock > div > ul > li.waiting > .link {
	background-image: url(<%= request.getContextPath() %>/images/message_waiting_indicator.gif);
}

#btm-dock > div > ul > li > .link > .unread {
	background: url(<%= request.getContextPath() %>/images/unread-messages.png);
	color: #fff;
	display: none;
	font: bold 10px/16px Arial, Helvetica, sans-serif;
	height: 16px;
	position: absolute;
	right: -4px;
	text-align: center;
	top: -6px;
	width: 16px;
	z-index: 30;
}

#btm-dock > div > ul > li.selected > .link > .unread {
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

#btm-dock > div > ul > li.typing > .link {
	padding-left: 24px;
}

/* ---------- Bottom Dock Popups ---------- */

#btm-dock .popup {
	bottom: 24px;
	display: none;
	position: absolute;
	right: 0;
	z-index: 20;
}

#btm-dock .selected .popup {
	display: block;
}

/* ---------- Online Users ---------- */

.buddylist .popup-content {
	list-style-type: none;
	margin: 0;
	padding: 2px 0;
}

.buddylist .popup-content .online-users li {
	float: left;
	margin: 0 2px;
	padding: 2px;
	height: 24px;
	list-style-type: none;
	cursor: pointer;
	border: 1px solid #fff;
	width: 212px;
}

.buddylist .popup-content li.idle {
	height: 14px;
}

.buddylist .popup-content li:hover {
	background: #f3f3f3;
	border-color: #ddd;
}

.buddylist .popup-content div {
	float: left;
	font: 12px/24px Arial, Helvetica, sans-serif;
}

.buddylist .popup-content .idle div {
	line-height: 14px;
}

.buddylist .popup-content img {
	background: #333;
	display: block;
	float: left;
	height: 24px;
	margin: 0 6px 0 0;
	width: 24px;
}

.buddylist .popup-content .idle img {
	display: none;
}

/* ---------- Popup Window ---------- */

.settings li {
	padding: 10px;
	border-bottom: 1px solid #999;
}

.settings label {
	font-weight: bold;
}

.settings input {
	vertical-align: middle;
}

.settings .ctrl-holder {
	padding: 10px;
}

.settings.saved .link .trigger-name {
	background: url(<%= themeImagesPath %>/messages/success.png) no-repeat 0 0;
	padding-left: 20px;
	padding-bottom: 2px;
	padding-top: 2px;
}

#chatMyStatus {
	margin: 5px;
	padding-left: 10px;
}

.popup-window {
	-moz-border-radius: 4px;
	-moz-border-radius-bottomright: 0;
	-webkit-border-radius: 4px;
	-webkit-border-radius-bottomright: 0;
	background: #fff;
	border: 1px solid #262626;
	font: 11px Tahoma, Geneva, sans-serif;
	position: relative;
	width: 226px;
	height: 274px;
}

.popup-icon {
	background: #333;
	border: 1px solid #000;
	height: 48px;
	position: absolute;
	top: 4px;
	left: 6px;
	display: block;
	width: 48px;
}

.popup-title {
	background: #20272b;
	color: #f2f2f2;
	font-weight: bold;
	height: 24px;
	line-height: 24px;
	text-indent: 60px;
}

.buddylist .popup-title, .settings .popup-title {
	text-indent: 6px;
}

.popup-button {
	cursor: pointer;
	height: 12px;
	width: 12px;
}

.popup-button.close {
	background: url(<%= request.getContextPath() %>/images/chat-close_button.png) left bottom;
	position: absolute;
	right: 4px;
	top: 6px;
}

.popup-button.close:hover {
	background-position: left top;
}

.popup-button.minimize {
	background: url(<%= request.getContextPath() %>/images/chat-minimize_button.png) left bottom;
	position: absolute;
	right: 20px;
	top: 6px;
}

.buddylist .popup-button.minimize, .settings .popup-button.minimize {
	right: 4px;
}

.popup-button.minimize:hover {
	background-position: left top;
}

.popup-profile {
	background: #eee;
	border-bottom: 1px solid #ccc;
	height: 27px;
	padding-top: 5px;
	text-indent: 60px;
}

.popup-output {
	border-bottom: 1px solid #000;
	height: 192px;
	overflow-y: scroll;
	padding: 2px;
}

.ie .popup-output {
	position: relative;
}

.popup-output .blurb {
	margin: 0;
	position: relative;
}

.popup-output .name {
	background: #828F95;
	color: #fff;
	display: block;
	line-height: 11px;
	padding: 2px 4px 3px;
}

.popup-output .incoming .name {
	background: #D3DADD;
	color: #555;
}

.popup-output .date {
	color: #eee;
	font-size: 10px;
	font-style: normal;
	position: absolute;
	right: 8px;
	top: 2px;
}

.popup-output .incoming .date {
	color: #555;
}

.popup-output .text {
	display: block;
	line-height: 14px;
	padding: 4px 8px 8px;
}

.popup-input {
	border-top: 1px solid #369;
	position: relative;
	height: 19px;
}

.popup-input textarea {
	background: none;
	border: 0;
	bottom: 0;
	display: block;
	float: left;
	font: 11px/14px Tahoma, Geneva, sans-serif;
	height: 14px;
	overflow: hidden;
	padding: 2px;
	position: absolute;
	width: 222px;
}