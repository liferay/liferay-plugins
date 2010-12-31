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

/* ---------- Configuration ---------- */

.portlet-configuration .kb-submit-buttons {
	margin: 0;
	padding: 10px 0 0;
}

/* ---------- Portal ---------- */

.knowledge-base-portlet-admin .lfr-panel .lfr-panel-titlebar {
	margin-bottom: 0;
}

.knowledge-base-portlet-admin .lfr-panel-container {
	border-width: 0;
}

.knowledge-base-portlet-admin .lfr-textarea-container textarea {
	height: 100px;
	width: 100%;
}

.knowledge-base-portlet-admin .taglib-search-iterator-page-iterator-bottom {
	margin: 5px 2px 0;
	padding: 0;
}

.knowledge-base-portlet-admin .top-links-container {
	margin: 0 0 15px 0;
}

.knowledge-base-portlet-admin .top-links-container .article-search {
	float: right;
}

/* ---------- Portlet ---------- */

.knowledge-base-portlet-admin .kb-article-breadcrumbs {
	margin: 0 5px 10px;
}

.knowledge-base-portlet-admin .kb-article-icons .taglib-workflow-status span {
	margin-right: 0;
	padding-right: 7px;
}

.knowledge-base-portlet-admin .kb-article-icons .taglib-workflow-status .taglib-icon-help {
	display: none;
}

.knowledge-base-portlet-admin .kb-article-tree {
	border: 1px solid #CCC;
	margin: 10px -7px 0;
}

.knowledge-base-portlet-admin .kb-article-tree .kb-element-body {
	margin: 0 0 0 20px;
}

.knowledge-base-portlet-admin .kb-article-tree .kb-element-body .kb-article-icons {
	padding: 4px 0;
}

.knowledge-base-portlet-admin .kb-article-tree .kb-element-header {
	margin: 8px 0 0;
}

.knowledge-base-portlet-admin .kb-article-tree .kb-element-header a {
	display: block;
	font-weight: bold;
}

.knowledge-base-portlet-admin .kb-article-tree .kb-elements {
	margin: 8px;
}

.knowledge-base-portlet-admin .kb-article-tree .kb-title-only {
	margin: 4px 0 0;
}

.knowledge-base-portlet-admin .kb-edit-link {
	margin: 5px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-body {
	padding: 0 7px;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-assets {
	margin: 10px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-attachments {
	margin: 10px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments {
	margin: 10px -7px 0;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .kb-helpful-inputs,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .kb-helpful-inputs {
	margin: 4px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .kb-helpful-inputs label,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .kb-helpful-inputs label {
	font-weight: normal;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .kb-helpful-text,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .kb-helpful-text {
	margin: 0 10px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .kb-no,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .kb-no {
	color: red;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .kb-question,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .kb-question {
	color: #999;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .kb-yes,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .kb-yes {
	color: green;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-comments .lfr-panel-content,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-comments .lfr-panel-content {
	margin: 5px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-body .kb-article-icons,
.knowledge-base-portlet-admin .kb-entity-body .kb-template-icons {
	padding: 0 0 5px;
}

.knowledge-base-portlet-admin .kb-entity-footer {
	margin: 10px 0 0;
}

.knowledge-base-portlet-admin .kb-entity-header {
	border-bottom: 1px solid #CCC;
	margin-bottom: 5px;
	padding: 0 5px 3px;
}

.knowledge-base-portlet-admin .kb-entity-header .kb-title {
	float: left;
	font-size: 1.2em;
	font-weight: bold;
}

.knowledge-base-portlet-admin .kb-entity-header .kb-tools {
	float: right;
}

.knowledge-base-portlet-admin .kb-results-body {
	padding: 0 0 0 20px;
}

.knowledge-base-portlet-admin .kb-results-body .kb-article-icons,
.knowledge-base-portlet-admin .kb-results-body .kb-template-icons {
	padding: 5px 0;
}

.knowledge-base-portlet-admin .kb-results-body .kb-title a {
	font-weight: bold;
	margin: 0 0 0 -20px;
}

.knowledge-base-portlet-admin .kb-results-body .kb-title-wrapper {
	margin: 5px 0 0 0;
}

.knowledge-base-portlet-admin .kb-results-header {
	margin-bottom: 1em;
}

.knowledge-base-portlet-admin .kb-results-header .kb-buttons {
	float: left;
}

.knowledge-base-portlet-admin .kb-results-header .kb-tools {
	float: right;
	padding: 4px 2px 0 0;
}

.knowledge-base-portlet-admin .kb-select-article-breadcrumbs {
	margin: 0 0 5px;
}

.knowledge-base-portlet-admin .kb-submit-buttons {
	margin: 0;
	padding: 10px 0 0;
}