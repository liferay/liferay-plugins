<%/**
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
 */%>

<%@ page import="com.liferay.portal.kernel.servlet.HttpHeaders"%>
<%@ page import="com.liferay.portal.kernel.util.ContentTypes"%>

<%
response.addHeader(HttpHeaders.CACHE_CONTROL, HttpHeaders.CACHE_CONTROL_DEFAULT_VALUE);
response.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.TEXT_CSS);
response.addHeader(HttpHeaders.EXPIRES, HttpHeaders.EXPIRES_DEFAULT_VALUE);
%>

.portlet-wiki-navigation-menu h1 {
	margin: 0px;
	padding: 4px;
	font-size: 11px;
	font-weight: bold;
	color: #003366;
	background-color: #eee;
}

.portlet-wiki-navigation-menu h1 a {
	color: #003366;
	text-decoration: none;
}

.portlet-wiki-navigation-menu h1 a:hover {
	color: #003366;
	text-decoration: underline;
}

.portlet-wiki-navigation-menu h5 {
	margin: 0px;
	padding: 0px;
	font-size: 11px;
	font-weight: bold;
	border-bottom: 1px solid #CCC;
	border-top: 1px solid white;
}

.portlet-wiki-navigation-menu h5 a {
	text-decoration: none;
	display: block;
	border: 0;
	padding: 5px;
}

.portlet-wiki-navigation-menu h5 a:hover {
	text-decoration: none;
	display: block;
	border: 0;
	background-color: white;
}

.portlet-wiki-navigation-menu ul, .portlet-wiki-navigation-menu ol {
	display: inline;
	margin: 0px;
	padding: 0px;
	list-style: none;
}

.portlet-wiki-navigation-menu ul li, .portlet-wiki-navigation-menu ol li {
	display: inline;
	margin: 0px;
	padding: 0px;
}

.portlet-wiki-navigation-menu ul li a, .portlet-wiki-navigation-menu ol li a {
	text-decoration: none;
	margin: 0px;
	display: block;
	padding: 2px;
	padding-left: 5px;
}

.portlet-wiki-navigation-menu ul li a {
	color: #2C5E8F;
	background:#C2D9EF none repeat scroll 0 0;
	border-top: 1px solid #999999;
}

.portlet-wiki-navigation-menu ul li a.current {
	background-color: #003366;
	color: white;
}

.portlet-wiki-navigation-menu ul li a:active {
	color: #2C5E8F;
}

.portlet-wiki-navigation-menu ul li a:visited {
	color: #2C5E8F;
}

.portlet-wiki-navigation-menu ul li a:hover {
	background-color: #003366;
	color: white;
}

.portlet-wiki-navigation-menu ul li.current {
	background-color: #487bb7;
}

.portlet-wiki-navigation-menu ul li.current a {
	color: #3c78b5;
}

.portlet-wiki-navigation-menu ol li a {
	color: white;
	background-color: #3c78b5;
	border-top: 1px solid #ccc;
}

.portlet-wiki-navigation-menu ol li a.current {
	background-color: #3c78b5;
	color: white;
}

.portlet-wiki-navigation-menu ol li a:active {
	color: white;
}

.portlet-wiki-navigation-menu ol li a:visited {
	color: white;
}

.portlet-wiki-navigation-menu ol li a:hover {
	background-color: white;
	color: #003366;
}

.portlet-wiki-navigation-menu ol li.current {
	background-color: #487bb7;
}

.portlet-wiki-navigation-menu ol li.current a {
	color: #3c78b5;
}