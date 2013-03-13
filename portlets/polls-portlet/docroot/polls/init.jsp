<%--
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
--%>

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portlet.polls.QuestionChoiceException" %><%@
page import="com.liferay.portlet.polls.QuestionDescriptionException" %><%@
page import="com.liferay.portlet.polls.QuestionExpirationDateException" %><%@
page import="com.liferay.portlet.polls.QuestionTitleException" %><%@
page import="com.liferay.portlet.polls.action.EditQuestionAction" %><%@
page import="com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil" %><%@
page import="com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil" %>

<%
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/polls/init-ext.jsp" %>