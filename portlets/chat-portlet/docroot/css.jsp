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

.chat-portlet {
	background: #e5e5e5;
	border-top: 1px solid #b5b5b5;
	bottom: 0;
	font: 11px 'lucida grande', 'tahoma', 'verdana', 'arial', sans-serif;
	height: 24px;
	left: 0;
	position: fixed;
	width: 100%;
	z-index: 20;
}

.chat-portlet #buddy-list {
	background: #fff;
	border: 1px solid #254588;
	padding: 10px;
	width: 120px;
}

.chat-portlet .chat {
	cursor: default;
	position: relative;
	width: 228px;
}

.chat-portlet .chat .head {
	background: #20272b;
	border: 1px solid #262626;
	border-bottom: 0;
	color: #fff;
	font-weight: bold;
	height: 24px;
	line-height: 22px;
	text-indent: 58px;
}

.chat-portlet .chat .head .close {
	background: url('<%= themeImagesPath %>/portlet/close.png') no-repeat 3px 3px;
	height: 16px;
	padding: 3px;
	position: absolute;
	right: 2px;
	top: 2px;
	width: 16px;
}

.chat-portlet .chat .head .close span {
	display: none;
}

.chat-portlet .chat .head .image {
	background: #fff;
	border-bottom: 1px solid #b5b5b5;
	border-right: 1px solid #b5b5b5;
	height: 45px;
	left: 5px;
	position: absolute;
	top: 5px;
	width: 45px;
}

.chat-portlet .chat .info {
	background: #eee;
	border: 1px solid #333;
	border-bottom-color: #ccc;
	border-top: 0;
	height: 32px;
}

.chat-portlet .chat .show {
	background: #f8f8f8;
	border: 1px solid #333;
	border-bottom-color: #333;
	border-top: 0;
	height: 206px;
	overflow-x: hidden;
	overflow-y: auto;
	position: relative;
}

.chat-portlet .chat .show p {
	margin: 0 0 10px;
	padding: 4px 5px;
	position: relative;
}

.chat-portlet .chat .show p .date {
	color: #eee;
	font-size: 10px;
	font-style: normal;
	position: absolute;
	right: 8px;
	top: 6px;
}

.chat-portlet .chat .show .not .date {
	color: #555;
}

.chat-portlet .chat .show p .name {
	display: block;
	line-height: 11px;
	padding: 2px 4px 3px;
}

.chat-portlet .chat .show p .text {
	display: block;
	line-height: 14px;
	padding: 4px 8px;
}

.chat-portlet .chat .show p.not .name {
	background: #D3DADD;
	color: #555;
}

.chat-portlet .chat .show p.you .name {
	background: #828F95;
	color: #fff;
}

.chat-portlet .chat .type {
	background: #fff;
	border: 1px solid #333;
	border-bottom-color: #545454;
	border-top-color: #369;
	position: relative;
}

.chat-portlet .chat .type .text, .chat-portlet .chat .type .prep {
	background: #fff url() no-repeat scroll 3px 3px;
	border: 0;
	font: 11px 'lucida grande', 'tahoma', 'verdana', 'arial', sans-serif;
	height: 13px;
	margin: 0;
	overflow: hidden;
	padding: 4px 8px;
	width: 210px;
	z-index: 1;
}

.chat-portlet .chat .type .prep {
	left: 0;
	position: absolute;
	top: 0;
	z-index: -1;
}

.chat-portlet > ul {
	padding-right: 10px;
	position: relative;
}

.chat-portlet > ul > li.item {
	border-left: 1px solid #b5b5b5;
	float: right;
	position: relative;
	z-index: 5;
}

.chat-portlet > ul > li.item > .popup {
	bottom: 24px;
	display: none;
	position: absolute;
	right: -1px;
	z-index: -1;
	padding: 0;
}

.chat-portlet > ul > li.item.active > .popup {
	display: block;
}

.chat-portlet > ul > li.item > a {
	border-left: 1px solid #fff;
	color: inherit;
	display: block;
	float: left;
	font-weight: bold;
	height: 14px;
	padding: 5px 10px;
	text-decoration: none;
}

.chat-portlet > ul > li.item.active > a {
	background: #fff;
	border: 1px solid #333;
	border-top-color: #fff;
	height: 13px;
	line-height: 13px;
	margin: -1px -1px 0 -1px;
	z-index: 9;
}

.chat-portlet > ul > li.item.waiting > a {
	background: url('<%= request.getContextPath() %>/images/message_waiting_indicator.gif');
}

.chat-portlet > ul, .chat-portlet > ul > li.item {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

.chat-portlet ul.chat-popup > li > a {
	border-bottom: 1px solid #eee;
	color: inherit;
	display: block;
	font-size: 14px;
	padding: 6px;
	text-decoration: none;
}

.chat-portlet ul.chat-popup, .chat-portlet ul.chat-popup > li {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

.hidden-sound {
	position: absolute;
}