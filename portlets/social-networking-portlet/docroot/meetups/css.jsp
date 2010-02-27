<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/css_init.jsp" %>

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