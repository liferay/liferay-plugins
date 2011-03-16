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

/* ---------- All Tasks Toggle ---------- */

.tasks-portlet .taglib-search-iterator-page-iterator-bottom {
	padding: 0;
}

.tasks-portlet .taglib-search-iterator-page-iterator-bottom .search-results,
.tasks-portlet .taglib-search-iterator-page-iterator-bottom .delta-selector {
	display: none;
}

.tasks-portlet .taglib-search-iterator-page-iterator-bottom .search-pages {
	padding-top: 5px;
}

.tasks-portlet .tasks-options {
	height: 1em;
	margin-top: 5px;
	opacity: .6;
}

.tasks-portlet .tasks-options table {
	float: right;
}

.tasks-portlet .tasks-options td {
	padding-left: 5px;
}

/* ---------- Comments ---------- */

.tasks-dialog {
	font-size: 11px;
}

.tasks-dialog .comment-form {
	height: 50px;
	margin-bottom: .5em;
	width: 400px;
}

.tasks-dialog .comment-wrapper {
	margin-bottom: 1em;
}

.tasks-dialog .edit-notice {
	font-style: italic;
	margin-right: 2px;
	padding: 0 2px;
}

.tasks-dialog .comment-info {
	color: #777;
}

.tasks-dialog .comment-info span {
	margin-right: .5em;
}

/* ---------- Controls ---------- */

.tasks-portlet .control-wrapper {
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	border: 1px solid #C8C9CA;
	border-radius: 4px;
	margin: .5em 0;
	padding: 6px;
}

.tasks-portlet .control-wrapper a {
	background: no-repeat 0% 50%;
	padding: 1px 10px 1px 20px;
}

.tasks-portlet .control-wrapper .add-task {
	background-image: url(<%= themeImagesPath %>/common/add.png);
}

.tasks-portlet .control-wrapper .filter-tasks {
	background-image: url(<%= themeImagesPath %>/common/tag.png);
	padding-right: 0;
	float: right;
}

.tasks-portlet .control-wrapper .permission-tasks {
	background-image: url(<%= themeImagesPath %>/common/permissions.png);
}

/* ---------- Filter ---------- */

.tasks-portlet .filter-wrapper {
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	background: #DFF4FF;
	border: 1px solid #A7CEDF;
	border-radius: 4px;
	position: relative;
	margin: 0 0 1em;
}

.tasks-portlet .filter-wrapper:after {
	border: 10px solid;
	border-color: transparent transparent #A7CEDF;
	content: "";
	position: absolute;
	top: -20px;
	right: 10px;
}

.tasks-portlet .filter-wrapper h3 {
	background: #A7CEDF;
	font-size: 11px;
	margin: 0;
	padding: 2px 5px;
}

.tasks-portlet .filter-container {
	padding: 0.2em 0.5em;
}

.tasks-portlet .filter-control-container {
	padding-top: 0;
	text-align: right;
}

.tasks-portlet .asset-tag-filter .asset-tag {
	background: url(<%= request.getContextPath() %>/images/unselected.png) no-repeat 0% 50%;
	padding-left: 16px;
	margin-right: 0.5em;
}

.tasks-portlet .asset-tag-filter .asset-tag.selected {
	background-image: url(<%= request.getContextPath() %>/images/selected.png);
	font-weight: bold;
}

.tasks-portlet .group-filter select {
	min-width: 175px;
}

/* ---------- Priority ---------- */

.tasks-portlet .tasks-title {
	padding: 1px 0 1px 20px;
}

.tasks-portlet .tasks-title.high {
	background: url(<%= request.getContextPath() %>/images/priority_high.png) no-repeat;
}

.tasks-portlet .tasks-title.low {
	background: url(<%= request.getContextPath() %>/images/priority_low.png) no-repeat;
}

.tasks-portlet .tasks-title.normal {
	background: url(<%= request.getContextPath() %>/images/priority_normal.png) no-repeat;
}

/* ---------- Progress ---------- */

.tasks-portlet .progress-wrapper {
	background: #FFF;
	border: 1px solid #CCC;
	font-size: 11px;
	height: 15px;
	position: relative;
	width: 100px;
}

.tasks-portlet .progress {
	background: #E8EFF4;
	height: 100%;
}

.tasks-portlet .progress-wrapper div {
	height: 15px;
	width: 100px;
}

.tasks-portlet .progress-wrapper .new-progress {
	background: #E8EFF4;
	left: 0;
	position: absolute;
	top: 0;
	width: 0%;
	z-index: 9;
}

.tasks-portlet .due-date {
	left: 0;
	position: absolute;
	text-align: center;
	top: 0;
	z-index: 10;
}

.tasks-portlet .progress-indicator {
	left: 0;
	position: absolute;
	text-align: center;
	top: 0;
	z-index: 11;
}

.tasks-portlet .progress-selector {
	background: url(<%= themeImagesPath %>/spacer.png);
	left: 0;
	position: absolute;
	top: 0;
	z-index: 12;
}

.tasks-portlet .progress-selector a {
	display: block;
	float: left;
	height: 100%;
	width: 20%;
}

/* ---------- Result Style ---------- */

.tasks-portlet .result-data {
	color: #777;
}

.tasks-portlet .result-data span {
	margin-right: 1em;
	white-space: nowrap;
}

/* ---------- Table Style ---------- */

.tasks-portlet .list-wrapper .col-2 {
	width: 105px;
}

.tasks-portlet .list-wrapper .col-3 {
	width: 16px;
}

/* ---------- Tags ---------- */

.tasks-portlet .tags-wrapper {
	position: relative;
	width: 16px;
}

.tasks-portlet .tags-wrapper .icon {
	background: url(<%= themeImagesPath %>/common/tag.png) no-repeat;
	height: 16px;
	width: 16px;
}

.tasks-portlet .tags {
	background: #E5F6FF;
	border: 1px solid #B5D7E5;
	max-width: 350px;
	min-width: 150px;
	padding: 0 4px;
	position: absolute;
	right: 20px;
	top: 0;
	z-index:20;
}

.ie6 .tasks-portlet .tags {
	widht: 250px;
}

/* ---------- Dialog ---------- */

.tasks-dialog .lfr-table .lfr-label {
	font-weight: bold;
	vertical-align: top;
}

.tasks-dialog .task-data-container {
	color: #777;
	margin-bottom: 1em;
}

.tasks-dialog .task-data-container .task-data {
	border-right: 1px solid #777;
	display: inline;
	margin-right: .5em;
	padding: 1px .5em 1px 20px;
}

.tasks-dialog .task-data-container .reporter {
	background: url(<%= themeImagesPath %>/common/add_user.png) no-repeat 0 50%;
}

.tasks-dialog .task-data-container .assignee {
	background: url(<%= themeImagesPath %>/common/assign.png) no-repeat 0 50%;
}

.tasks-dialog .task-data-container .modified-date {
	background: url(<%= themeImagesPath %>/common/calendar.png) no-repeat 0 50%;
	border-right: none;
}

.tasks-dialog .aui-layout {
	margin-bottom: 1em;
}

.tasks-dialog .task-data-table .task-data {
	display: block;
	padding: 1px 0 1px 20px;
}

.tasks-dialog .task-data-table .high {
	background: url(<%= request.getContextPath() %>/images/priority_high.png) no-repeat 0 50%;
}

.tasks-dialog .task-data-table .normal {
	background: url(<%= request.getContextPath() %>/images/priority_normal.png) no-repeat 0 50%;
}

.tasks-dialog .task-data-table .low {
	background: url(<%= request.getContextPath() %>/images/priority_low.png) no-repeat 0 50%;
}

.tasks-dialog .task-data-table .due-date {
	background: url(<%= themeImagesPath %>/common/date.png) no-repeat 0 50%;
}

.tasks-dialog .task-action {
	background: #EEE;
	border-top: 1px solid #BFBFBF;
	padding: .5em;
	margin: 1em 0;
}

.tasks-dialog .task-action-close {
	float: right;
}