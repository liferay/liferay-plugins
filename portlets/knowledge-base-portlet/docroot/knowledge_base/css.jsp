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

.kb-portlet-knowledge-base .knowledge-base-body pre {
	background: white;
	border: 1px dashed #2F6FAB;
	margin: 5px 0px 5px 0px;
	padding: 0.5em;
}

.kb-portlet-knowledge-base a.createarticle {
	color: #FF0000;
}

.kb-portlet-knowledge-base .toc {
	border: 1px solid #AAA;
	background-color: #F9F9F9;
	padding: 10px;
}

.kb-portlet-knowledge-base .toc h4 {
	margin-bottom: 0.7em;
}

.kb-portlet-knowledge-base .toc ul {
 	margin-top: 0px;
}
.kb-portlet-knowledge-base .toc li.toclevel-1 {
	list-style-type: none;
	margin-left: 0px;
}

.kb-portlet-knowledge-base .toc li.toclevel-2 {
	list-style-type: none;
	margin-left: 15px;
}

.kb-portlet-knowledge-base .toc li.toclevel-3 {
	list-style-type: none;
	margin-left: 30px;
}

.kb-portlet-knowledge-base .taglib-tags-summary {
	font-weight: bold;
}

.kb-portlet-knowledge-base .taglib-tags-summary a {
	font-weight: normal;
}

.kb-portlet-knowledge-base h1.article-title {
	border-bottom: 1px solid #AAA;
	margin: 0px;
	margin-bottom: 0.5em;
	padding-bottom: 5px;
}

.kb-portlet-knowledge-base h1.article-title .return-to-article {
	background: url(/html/themes/classic/images/wiki/return_to_page.png) no-repeat 0 50%;
	padding-left: 20px;
	text-decoration: none;
}

.kb-portlet-knowledge-base .preview {
	background: #FFC;
	border: 1px dotted gray;
	padding: 3px;
}

.kb-portlet-knowledge-base .child-articles {
	margin: 10px 0pt 10px;
}

.kb-portlet-knowledge-base .child-articles h3 {
	font-size: 1.2em;
	margin-bottom: 0.3em;
}

.ie .kb-portlet-knowledge-base .child-articles h3 {
	margin-bottom: 0.2em;
}

.kb-portlet-knowledge-base .child-articles ul {
	margin-top: 0;
}

.kb-portlet-knowledge-base .child-articles li {
	list-style-image: none;
	list-style-type: none;
	padding: 0pt 0pt 4px;
}

.kb-portlet-knowledge-base .child-articles-message {
	border-bottom: 1px dotted #CCC;
	font-size: 1.2em;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 20px;
	padding-bottom: 3px;
}

.kb-portlet-knowledge-base .content-body .wiki-code {
	background: #FFF;
	border: 1px solid #777;
	font-family: monospace;
	white-space: pre;
}

.kb-portlet-knowledge-base .content-body .code-lines {
	border-right: 1px solid #CCC;
	color: #000;
	margin-right: 5px;
	padding: 0px 5px 0px 5px;
}

.kb-portlet-knowledge-base .content-body a.external-link {
	background: transparent url(/html/themes/classic/images/wiki/external.png) right top no-repeat;
	text-decoration: none;
	padding-right: 10px;
}

.kb-portlet-knowledge-base .content-body a.external-link:hover {
	background: transparent url(/html/themes/classic/images/wiki/external.png) right top no-repeat;
	text-decoration: underline;
	padding-right: 11px;
}

.kb-portlet-knowledge-base .node-current {
	text-decoration: none;
	font-weight: bold;
}

.kb-portlet-knowledge-base .ctrl-holder-add-article, .kb-portlet-knowledge-base .ctrl-holder-add-template {
	margin: 15px 0pt;
}

.kb-portlet-knowledge-base .article-actions {
	margin-top: 15px;
}

.kb-portlet-knowledge-base .article-title .article-actions {
	float: right;
	margin-top: 0;
}

.kb-portlet-knowledge-base .article-title .article-actions a {
	text-decoration: none;
}

.kb-portlet-knowledge-base .article-actions a:hover {
	text-decoration: underline;
}

.kb-portlet-knowledge-base .article-info {
	width: 100%;
}

.kb-portlet-knowledge-base .article-info tr th, .kb-portlet-knowledge-base .article-info tr td {
	border: 1px solid #CCC;
	border-left: none;
	border-right: none;
	padding: 0.3em 1em;
}

.kb-portlet-knowledge-base .article-redirect {
	color: #7D7D7D;
	cursor: pointer;
	line-height: 1.2em;
	margin: -1em 0pt 1.4em 0em;
	width: auto;
}

.kb-portlet-knowledge-base .article-redirect:hover {
	text-decoration: underline;
}

.kb-portlet-knowledge-base .comments-message {
	margin: 10px 0pt;
}

.kb-portlet-knowledge-base .knowledge-base-feedback {
	margin: 20px 0pt;
}

.kb-portlet-knowledge-base .page-title {
	border-bottom: 1px solid #AAA;
	margin: 0pt 0pt 15px;
	padding-bottom: 5px;
}

.kb-portlet-knowledge-base .popup-print {
	float: right;
}

.kb-portlet-knowledge-base .ratings-label {
	padding: 0pt 20px 0pt 0pt;
}

.kb-portlet-knowledge-base .search-button-holder {
	margin-left: 5px;
}

.kb-portlet-knowledge-base .selected-top-link {
	text-decoration: none;
}

.kb-portlet-knowledge-base .side-boxes {
	padding-left: 10px;
}

.kb-portlet-knowledge-base .side-box {
	background: #EEE;
	border: 1px dashed #CCC;
	margin-bottom: 10px;
	padding: 5px;
}

.kb-portlet-knowledge-base .side-box-title {
	border-bottom: 1px dashed gray;
	font-size: 1.2em;
	font-weight: bold;
	margin-bottom: 3px;
}

.kb-portlet-knowledge-base .side-box .taglib-icon-list li {
	float: none;
}

.kb-portlet-knowledge-base .subscriptions {
	padding: 10px 0pt 0pt;
}

.kb-portlet-knowledge-base .syntax-help {
	border: 1px dotted gray;
	padding-left: 10px;
}

.kb-portlet-knowledge-base .syntax-help h4 {
	margin-bottom: 0.5em;
}

.ie .kb-portlet-knowledge-base .syntax-help h4 {
	margin-bottom: 0.3em;
}

.kb-portlet-knowledge-base .syntax-help pre {
	margin-left: 1em;
	margin-bottom: 1em;
}

.kb-portlet-knowledge-base .template-button-holder {
	margin-left: 5px;
}

.kb-portlet-knowledge-base .template-preview {
	background: #EEE;
	border: 1px solid #CCC;
	color: #555;
	display: none;
	margin-bottom: 15px;
	padding: 5px;
}

.kb-portlet-knowledge-base .top-links {
	padding-bottom: 10px;
}

.kb-portlet-knowledge-base .top-links table {
	width: 100%;
}

.kb-portlet-knowledge-base .total-votes {
	color: #555;
	font-size: xx-small;
	padding-top: 2px;
}

.kb-portlet-knowledge-base .subscription-info tr td {
	border: none;
	padding: 0.1em 10px 0.1em 0;
}