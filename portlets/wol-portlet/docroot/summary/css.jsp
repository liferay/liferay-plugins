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

.ie .summary-container {
	height: 1%;
}

.summary-container:after {
	clear: both;
	content: ".";
	display: block;
	height: 0;
	visibility: hidden;
}

.summary-container h2 {
	color: #3D536C;
	font-size: 16px;
	margin-bottom: 10px;
	margin-top: 0;
}

.summary-container img {
	margin: 5px 0;
	max-width: 120px;
}

.ie6 .summary-container img {
	width: 120px;
}

.summary-container p {
	margin-bottom: 10px;
}

.summary-container span {
	color: #3D536C;
	display: block;
	font-size: 10px;
	font-weight: bold;
	text-transform: uppercase;
}

.summary-container .user-profile-image {
	max-width: 180px;
}

.ie6 .summary-container .user-profile-image {
	width: expression(this.width > 180 : '180px' : 'auto');
}

.summary-container .add-as-friend {
	margin: 0.7em auto 1em;
}

.summary-container .add-as-friend.pending {
	background-image: url(/liferay-jedi-theme/images/common/time.png);
}