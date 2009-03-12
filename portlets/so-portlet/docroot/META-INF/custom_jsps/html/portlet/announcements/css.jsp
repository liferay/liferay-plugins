<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-announcements {
}

.portlet-announcements .edit-controls {
	margin-bottom: 1.2em;
}

.portlet-announcements .entry {
	color: #3E3639;
	margin-bottom: 1.3em;
}

.portlet-announcements .important {
	font-weight: normal;
}

.portlet-announcements .entry-title {
	background: url(<%= themeImagesPath %>/custom/priority_normal.png) no-repeat;
	border-bottom: 1px solid #CCC;
	font-size: 13px;
	font-weight: bold;
	min-height: 16px;
	margin-bottom: .2em;
	padding: 0 140px 0 20px;
	position: relative;
}

.ie6 .portlet-announcements .entry-title {
	height: 16px;
}

.portlet-announcements .important .entry-title {
	background: url(<%= themeImagesPath %>/custom/priority_important.png) no-repeat;
}

.portlet-announcements .entry-actions {
	font-size: 10px;
	font-weight: normal;
	position: absolute;
	right: 0;
	text-align: right;
	bottom: 0;
}

.portlet-announcements .entry-actions span {
	margin-left: .5em;
}

.portlet-announcements .entry-actions a {
	color: #7F80A4;
}

.portlet-announcements .entry-content {
	font-size: 11px;
}

.portlet-announcements .read-entries .entry-content {
	display: none;
}

.portlet-announcements .entry-scope {
	background: #E8EFF4;
	border: 1px solid #B0BAFF;
	font-size: 10px;
	margin-right: 5px;
	padding: 0 5px;
	text-transform: uppercase;
}