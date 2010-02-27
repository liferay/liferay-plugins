<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/css_init.jsp" %>

.mail-portlet .account-container {
	display: none;
	margin-bottom: 7px;
	width: 100%;
}

.mail-portlet .account-container div {
	display: none;
	float: left;
}

.mail-portlet .account-container .configure-link {
	padding-left: 5px;
}

.mail-portlet .accounts-configuration .account {
	border: 1px solid black;
	margin: 4px;
	padding: 3px;
}

.mail-portlet .accounts-configuration .details {
	border: 1px;
	margin-left: 10px;
	margin-top: 4px;
}

.mail-portlet .accounts-configuration .details td {
	padding: 2px 4px;
}

.mail-portlet .accounts-configuration .title {
	background-color: #E8EEF7;
	cursor: pointer;
	font-size: 13px;
	font-weight: bold;
	padding: 4px;
}

.mail-portlet .configuration-prompt {
	display: none;
}

.mail-portlet .selection {
	text-align: left;
	width: 30%;
}

.mail-portlet .navigation .status {
	text-align: center;
	width: 40%;
}

.mail-portlet .search {
	text-align: right;
	width: 30%;
}

.mail-portlet .email-container {
	display: none;
	height: 400px;
	width: 100%;
}

.mail-portlet .email-container tr {
	vertical-align: top;
}

.mail-portlet .email-left-column {
	border: 1px #000 solid;
	padding: 3px;
	width: 13%;
}

.mail-portlet .email-right-column {
	border: 1px #000 solid;
	max-width: 1000px;
	padding: 3px;
	width: 90%;
}

.mail-portlet .compose-mail {
	cursor: pointer;
	margin-bottom: 20px;
	padding: 5px;
	text-decoration: underline;
}

.mail-portlet .status-div .status {
	background-color: #FFF1A8;
	font-weight: bold;
	padding: 5px;
	text-align: center;
}

.mail-portlet .folder, .mail-portlet .folder-status {
	min-width: 80px;
	padding: 5px;
}

.mail-portlet .folder-container {
	display: block;
	padding-bottom: 20px;
}

.mail-portlet .folder-selected {
	/* background-color: #C3D9FF; */
}

.mail-portlet .message-list {
	width: 100%;
}

.mail-portlet .message-list .alert {
	height: 250px;
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

.mail-portlet .read td {
	/* background: #E8EEF7; */
}

.mail-portlet .unread td {
	/* background: #FFF; */
	font-weight: bold;
}

.mail-portlet .message-from {
	padding-right: 5px;
}

.mail-portlet .message-controls .status-number {
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
	/* background-color: #C3D9FF; */
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

.mail-portlet .message-container {
	overflow: hidden;
}

.mail-portlet .message-read {
	padding-bottom: 20px;
}

.mail-portlet .message-read .details {
	background-color: #F8F8F8;
	padding: 5px;
}

.mail-portlet .message-read .details table tr {
	vertical-align: middle;
}

.mail-portlet .message-read .label {
	color: #888;
	padding: 2px 15px;
	text-align: right;
	width: 60px;
}

.mail-portlet .message-send {
	/* background-color: #C3D9FF; */
	padding-bottom: 20px;
}

.mail-portlet .message-send .details {
	padding: 10px;
	text-align: center;
}

.mail-portlet .message-send .details input, .mail-portlet .message-send .details select, .mail-portlet .message-send .details textarea {
	width: 95%;
}

.mail-portlet .message-send .label {
	font-weight: bold;
	text-align: right;
	padding: 2px 7px;
	width: 20px;
}

.mail-portlet .message-send .details table {
	margin-bottom: 10px;
	text-align: left;
	width: 100%;
}

.mail-portlet .send-body-span {
	height: 300px;
	overflow: auto;
}

.mail-portlet .message-send .options {
	padding: 5px;
}

.mail-portlet .message-options {
	background-color: #F7F7F7;
}

.mail-portlet .message-options .selected-response-type {
	/* background-color: #C3D9FF; */
}

.mail-portlet .message-options td {
	cursor: pointer;
	padding: 5px;
}

.mail-portlet .message-controls {
	/* background-color: #C3D9FF; */
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

.mail-portlet .summary div {
	display: none;
}