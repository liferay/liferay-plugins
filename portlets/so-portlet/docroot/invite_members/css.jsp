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

<%@ include file="/css_init.jsp" %>

.so-portlet-invite-members .email-invited h2 span {
	color: #999;
	font-size: .8em;
	font-weight: normal;
	margin-left: 2em;
	position: absolute;
	right: 5px;
}

.so-portlet-invite-members .users-wrapper {
	min-height: 450px;
	position: relative;
}

.so-portlet-invite-members .uninvited{
	background: #FFF url(<%= themeImagesPath %>/forms/input_shadow.png) no-repeat;
	border-color: #BFBFBF #DEDEDE #DEDEDE #BFBFBF;
	border-style: solid;
	border-width: 1px;
	height: 300px;
	width: 205px;
	overflow-x: hidden;
	overflow-y: auto;
	position: relative;
}

.so-portlet-invite-members .user-search-wrapper {
	background: #F0F5F7;
	border: 1px solid #B0BAFF;
	float: left;
	padding: 12px;
}

.so-portlet-invite-members .invited-users-wrapper {
	margin-left: 250px;
}

.so-portlet-invite-members h2 {
	font-size: 1em;
	margin: 0 0 1em;
}

.so-portlet-invite-members .list {
	margin-bottom: 1em;
}

.so-portlet-invite-members #invite-user-search {
	width: 203px;
}

.so-portlet-invite-members .invited-users-wrapper h2 {
	border-bottom: 1px solid #CCC;
	color: #3E3639;
	font-size: 1.1em;
	margin: 0;
	padding-top: .5em;
	position: relative;
}

.so-portlet-invite-members .invited-users-wrapper .invite-actions {
	background: #EEE;
	border: 1px solid #CCC;
	margin: 2.5em 0;
	padding: 5px;
	text-align: center;
}

.so-portlet-invite-members .invited-users-wrapper .invite-actions h2 {
	margin: 0 0 .5em;
	padding: 0;
}

.so-portlet-invite-members .invited-users-wrapper .invite-actions input {
	margin-right: 12px;
}

.so-portlet-invite-members .invited h2 span {
	color: #999;
	font-size: .8em;
	font-weight: normal;
	margin-left: 2em;
	position: absolute;
	right: 5px;
}

.so-portlet-invite-members .user,
.so-portlet-invite-members .invited-user {
	background: transparent url(<%= themeImagesPath %>/arrows/01_plus.png) no-repeat 0 50%;
	border-top: 1px solid transparent;
	border-bottom: 1px solid transparent;
	cursor: pointer;
	list-style-type: none;
	padding: 2px 5px 2px 18px;
	white-space: nowrap;
}

.so-portlet-invite-members .invited .user,
.so-portlet-invite-members .email-invited .user {
	background: #DFD url(<%= themeImagesPath %>/arrows/01_minus.png) no-repeat 99% 50%;
	border-bottom: 1px solid #CCC;
	padding: .5em;
}

.so-portlet-invite-members .email-invited .user {
	background-color: #EEF;
}

.so-portlet-invite-members .invited .user span {
	display: block;
	clear: none;
}

.so-portlet-invite-members .invited .user .name {
	color: #555;
	font-weight: bold;
	float: left;
	width: 40%;
}

.so-portlet-invite-members .invited .user .email {
	color: #000;
	font-size: 1em;
	width: 50%;
}

.so-portlet-invite-members .email-invited .user .email {
	color: #555;
	font-size: 1em;
	font-weight: bold;
	margin: 0;
	width: 40%;
}

.so-portlet-invite-members .invited-user {
	background: #DFD url(<%= themeImagesPath %>/arrows/01_minus.png) no-repeat 0 50%;
	border-color: #AFA;
}

.so-portlet-invite-members span.email {
	color: #AAA;
	font-size: 0.8em;
	margin-left: 0.5em;
}