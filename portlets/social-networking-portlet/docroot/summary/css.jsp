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

.ie .social-networking-portlet-summary .summary-container {
	height: 1%;
}

.social-networking-portlet-summary .summary-container:after {
	clear: both;
	content: ".";
	display: block;
	height: 0;
	visibility: hidden;
}

.social-networking-portlet-summary .summary-container h2 {
	color: #3D536C;
	font-size: 16px;
	margin-bottom: 10px;
	margin-top: 0;
}

.social-networking-portlet-summary .summary-container img {
	margin: 5px 0;
	max-width: 120px;
}

.ie6 .social-networking-portlet-summary .summary-container img {
	width: 120px;
}

.social-networking-portlet-summary .summary-container p {
	margin-bottom: 10px;
}

.social-networking-portlet-summary .summary-container span {
	color: #3D536C;
	display: block;
	font-size: 10px;
	font-weight: bold;
	text-transform: uppercase;
}

.social-networking-portlet-summary .summary-container .user-profile-image {
	max-width: 180px;
}

.ie6 .social-networking-portlet-summary .summary-container .user-profile-image {
	width: expression(this.width > 180 : '180px' : 'auto');
}

.social-networking-portlet-summary .summary-container .user-twitter-link {
	background: url(<%= request.getContextPath() %>/summary/images/twitter.png) no-repeat 0 50%;
	padding-left: 18px;
}

.social-networking-portlet-summary .summary-container .add-as-friend {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .add-as-friend.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .join-community {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .join-community.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .join-organization {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .join-organization.pending {
	background-image: url(<%= themeImagesPath %>/common/time.png);
}

.social-networking-portlet-summary .summary-container .remove-community {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .remove-organization {
	margin: 0.7em auto 1em;
}

.social-networking-portlet-summary .summary-container .remove-friend {
	margin: 0.7em auto 1em;
}