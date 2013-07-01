<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/display/init.jsp" %>

<c:if test="<%= QqConnectUtil.isEnabled(company.getCompanyId()) %>">

	<%
	String qqAuthURL = QqConnectUtil.getFullAuthURL(company.getCompanyId(), request);
	%>

	<c:choose>
		<c:when test="<%= themeDisplay.isSignedIn() %>">

			<%
			long userId = user.getUserId();

			String qqOpenId = ExpandoValueLocalServiceUtil.getData(company.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, QqConstants.QQ_OPEN_ID, userId, StringPool.BLANK);
			%>

			<c:choose>
				<c:when test="<%= Validator.isNull(qqOpenId) %>">

					<%
					session.setAttribute(WebKeys.SOCIAL_LOGIN_USER_ID, userId);
					%>

					<aui:a href="<%= qqAuthURL %>" label="bind-your-qq-account" />
				</c:when>
				<c:otherwise>
					<portlet:actionURL name="unbindQqAccount" var="unbindQqAccountURL" />

					<liferay-ui:message key="your-have-bound-your-qq-accout" /><aui:a href="<%= unbindQqAccountURL.toString() %>" label="unbind-your-qq-account" />
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<aui:a href="<%= qqAuthURL %>" label="login-by-qq-account" />
		</c:otherwise>
	</c:choose>
</c:if>