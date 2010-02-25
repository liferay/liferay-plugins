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

.social-networking-portlet-meetups .add-meetup-button {
	float: right;
}

.social-networking-portlet-meetups .response {
	float: left;
	margin-right: 15px;
	position: relative;
}

.social-networking-portlet-meetups .response .comments {
	background: #E2E6E8;
	border: 1px solid #6B767B;
	bottom: 110%;
	display: none;
	left: -20px;
	padding: 5px;
	position: absolute;
	width: 200px;
}

.social-networking-portlet-meetups .response.hovering .comments {
	display: block;
}

.social-networking-portlet-meetups .response .comments .indicator {
	background: url(<%= request.getContextPath() %>/meetups/images/indicator.png) no-repeat;
	bottom: -15px;
	height: 15px;
	left: 50px;
	position: absolute;
	width: 15px;
}