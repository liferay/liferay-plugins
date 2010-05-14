<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

.digg-widget {
	font-family: Arial, sans-serif;
}

.digg-widget .digg-widget-head {
	border: 1px solid;
	height: 40px;
	overflow: hidden;
	padding: 0 43px 0 10px;
	position: relative;
	white-space: nowrap;
}

.digg-widget .digg-widget-head.premium {
	-moz-border-radius-topleft: 6px;
	-moz-border-radius-topright: 6px;
	-webkit-border-top-left-radius: 6px;
	-webkit-border-top-right-radius: 6px;
}

.digg-widget .digg-widget-head a {
	background: url(<%= request.getContextPath() %>/images/widget_bg.png) 0 -30px no-repeat;
	bottom: 12px;
	height: 16px;
	position: absolute;
	right: 10px;
	width: 30px;
}

.digg-widget .digg-widget-head h2 {
	font-size: 19px;
	font-weight: normal;
	line-height: normal;
	margin: 0;
	overflow: hidden;
	padding: 10px 0 0;
	white-space: nowrap;
}

.digg-widget .digg-widget-subhd {
	font-size: 12px;
	margin: 3px 0 3px 10px;
}

.digg-widget .digg-widget-tabs {
	height: 30px;
	overflow: hidden;
	padding: 0 10px;
}

.digg-widget .digg-widget-tabs .digg-tab {
	border: 1px solid;
	border-bottom: 0;
	cursor: pointer;
	display: inline-block;
	font-size: 11px;
	font-weight: bold;
	line-height: 28px;
	margin: 0 5px 0 0;
	padding: 0 4px;
}

.digg-widget .digg-widget-tabs .digg-tab.premium {
	-moz-border-radius-topleft: 3px;
	-moz-border-radius-topright: 3px;
	-webkit-border-top-left-radius: 3px;
	-webkit-border-top-right-radius: 3px;
}

.digg-widget .digg-widget-tabs .digg-tab-on {
	height: 30px;
}

.digg-widget .digg-widget-content {
	background: none !important;
	overflow: auto;
	position: relative;
}

.digg-widget .digg-widget-content.digg-alt {
	overflow-x: hidden;
}

.digg-widget .digg-story .digg-story {
	_height: 30px;
	border: 0;
	margin: 0;
	min-height: 30px;
	padding: 0 0 0 35px;
	position: relative;
}

.digg-widget .digg-story.countless .digg-story {
	padding: 0;
}

.digg-widget .digg-widget-col {
	clear: none;
	float: left;
	margin: 0;
	position: relative;
	width: 50%;
}

.digg-widget .digg-alt.digg-widget-col {
	margin: 0 -1px;
}

.digg-widget .digg-story.digg-alt {
	border-bottom: 0;
}

.digg-widget .digg-story a.digg-count {
	background: url(<%= request.getContextPath() %>/images/widget_bg.png) no-repeat;
	color: #93883F !important;
	display: block;
	font-size: 11px;
	font-weight: bold;
	height: 30px;
	left: 0;
	line-height: 30px;
	overflow: hidden;
	position: absolute;
	text-align: center;
	text-decoration: none;
	top: 0;
	width: 30px;
}

.digg-widget .digg-story-thumb {
	_height: 30px;
	min-height: 30px;
	padding-left: 35px;
	position: relative;
}

.digg-widget .digg-widget-description {
	color: #999;
	font-size: 11px;
	margin: 5px 0 0;
}

.digg-widget .digg-story-thumb img {
	border: 0;
	height: 30px;
	left: 1px;
	position: absolute;
	top: 0;
	width: 30px;
}

.digg-widget .digg-story {
	border-bottom: 1px solid;
	line-height: 12px;
	padding: 6px 0;
}

.digg-widget .digg-story a {
	font-size: 11px;
	font-weight: bold;
	text-decoration: none;
}

.digg-widget a.see-more {
	clear: both;
	display: block;
	font-size: 11px;
	font-weight: normal;
	margin: 10px 0 0 10px;
	outline: 0;
	text-decoration: none;
}

.digg-widget .digg-widget-error {
	color: #C00;
	margin: 0 10px;
	padding: 5px 0 10px;
}

.digg-widget br {
	line-height: 1px;
}

.digg-widget-configuration {
	color: #5C5C5C;
	font: bold 11px/normal Arial, Helvetica;
}

.digg-widget-configuration .fld,
.digg-widget-configuration .pfld {
	border-bottom: 1px solid #B8B8B8;
	padding: 15px 10px;
}

.digg-widget-configuration .fld.selected {
	background: #FFF0C5;
}

.digg-widget-configuration .fld .ffld {
	border: 0;
	padding: 5px 0 0;
}

.digg-widget-configuration .storycount {
	padding: 10px 0 0;
}

#_86_fm.digg-widget-configuration .aui-field-content,
#_86_fm.digg-widget-configuration .fallback .aui-field-input {
	margin-right: 0.1em;
}

#_86_fm.digg-widget-configuration .min-date {
	margin-right: 0.3em;
}