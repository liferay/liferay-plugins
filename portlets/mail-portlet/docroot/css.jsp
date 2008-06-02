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

.mail-portlet #account-container {
	margin-bottom: 7px;
	width: 100%;
}

.mail-portlet #account-container div {
	float: left;
}

.mail-portlet #selection {
	text-align: left;
	width: 30%;
}

.mail-portlet #status {
	text-align: center;
	width: 40%;
}

.mail-portlet #search {
	text-align: right;
	width: 30%;
}

.mail-portlet #email-container {
	height: 400px;
	width: 100%;
}

.mail-portlet #email-container tr {
	vertical-align: top;
}

.mail-portlet #email-left-column {
	border: 1px #000 solid;
	padding: 3px;
	width: 13%;
}

.mail-portlet #email-right-column {
	border: 1px #000 solid;
	padding: 3px;
	width: 90%;
}

.mail-portlet #compose-mail {
	cursor: pointer;
	margin-bottom: 20px;
	padding: 5px;
	text-decoration: underline;
}

.mail-portlet #status {
	background-color: #FFF1A8;
	font-weight: bold;
	padding: 5px;
	text-align: center;
}

.mail-portlet .folder {
	min-width: 80px;
	padding: 5px;
}

.mail-portlet #folder {
	display: block;
	padding-bottom: 20px;
}

.mail-portlet #message-list {
	width: 100%;
}

.mail-portlet #message-list .alert {
	height: 200px;
	text-align: center;
	vertical-align: middle;
}

.mail-portlet .message:hover {
	cursor: pointer;
}

.mail-portlet .message td {
	border-bottom: 1px solid #CCC;
	padding: 2px;
}

.mail-portlet .read {
	background: #E8EEF7 none repeat scroll 0% 50%;
}

.mail-portlet .unread {
	background: #FFF none repeat scroll 0%;
}

.mail-portlet .message-from {
	font-weight: bold;
	padding-right: 5px;
}

.mail-portlet .message-subject, .mail-portlet .message-controls .status-number, .mail-portlet .message-date {
	font-weight: bold;
}

.mail-portlet .message-body-preview {
	color: grey;
}

.mail-portlet .message-col-0 {
	overflow: hidden;
	text-align: center;
	width: 25px;
}

.mail-portlet .message-col-1 {
	overflow: hidden;
	width: 225px;
}

.mail-portlet .message-col-2 {
	height: 17px;
	overflow: hidden;
}

.mail-portlet .message-col-3 {
	overflow: hidden;
	width: 130px;
}

.mail-portlet .folder-controls {
	background-color: #C3D9FF;
	padding: 3px;
	width: 100%;
}

.mail-portlet .folder-controls .actions {
	height: 45px;
}

.mail-portlet .folder-controls .actions div {
	padding: 4px;
}

.mail-portlet .folder-controls .navigation {
	padding: 5px;
	text-align: right;
}

.mail-portlet #message {
	display: none;
}

.mail-portlet #message-read {
	padding-bottom: 20px;
}

.mail-portlet #message-read .details {
	background-color: #F8F8F8;
	padding: 5px;
}

.mail-portlet #message-read .details table tr {
	vertical-align: middle;
}

.mail-portlet #message-read .label {
	color: #888;
	padding: 2px 15px;
	text-align: right;
	width: 60px;
}

.mail-portlet #message-read #body {
	padding: 15px;
}

.mail-portlet #message-send {
	background-color: #C3D9FF;
	display: none;
	padding-bottom: 20px;
}

.mail-portlet #message-send .details {
	padding: 10px;
	text-align: center;
}

.mail-portlet #message-send .label {
	font-weight: bold;
	text-align: right;
	padding: 2px 7px;
	width: 20px;
}

.mail-portlet #message-send .details table {
	margin-bottom: 10px;
	width: 100%;
}

.mail-portlet #send-from,  .mail-portlet #send-to,  .mail-portlet #send-cc,  .mail-portlet #send-bcc,  .mail-portlet #send-subject,  .mail-portlet #send-body {
	width: 95%;
}

.mail-portlet #send-body {
	height: 300px;
	overflow: auto;
}

.mail-portlet #send-to {
	height: 45px;
	overflow: auto;
}

.mail-portlet #message-send .options {
	padding: 5px;
}

.mail-portlet #message-options {
	background-color: #F7F7F7;
}

.mail-portlet #message-options td {
	cursor: pointer;
	padding: 5px;
}

.mail-portlet #message-options #reply {
}

.mail-portlet #message-options #reply-all {
}

.mail-portlet #message-options #forward {
}

.mail-portlet .message-controls {
	background-color: #C3D9FF;
}

.mail-portlet .message-controls .actions {
	float: left;
	padding: 5px;
}

.mail-portlet .message-controls .navigation {
	padding: 5px;
	text-align: right;
}

.mail-portlet .message-controls .newer,  .mail-portlet .message-controls .older, .mail-portlet .message-controls .back {
	cursor: pointer;
}