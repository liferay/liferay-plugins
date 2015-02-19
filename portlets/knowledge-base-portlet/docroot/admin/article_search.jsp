<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<%@ include file="/admin/init.jsp" %>

<%
KBArticleDisplayTerms displayTerms = (KBArticleDisplayTerms)request.getAttribute("view.jsp-displayTerms");
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_knowledge_base_article_search"
>
	<aui:fieldset cssClass="kb-block-labels kb-search-toggle">
		<div class="kb-field-wrapper">
			<aui:input inlineField="<%= true %>" name="<%= displayTerms.TITLE %>" size="40" value="<%= displayTerms.getTitle() %>" />

			<aui:input inlineField="<%= true %>" name="<%= displayTerms.CONTENT %>" size="40" value="<%= displayTerms.getContent() %>" />

			<aui:select ignoreRequestValue="<%= true %>" inlineField="<%= true %>" name="<%= displayTerms.STATUS %>">
				<aui:option label="<%= WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_ANY) %>" selected="<%= displayTerms.getStatus() == WorkflowConstants.STATUS_ANY %>" value="<%= WorkflowConstants.STATUS_ANY %>" />
				<aui:option label="<%= WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_APPROVED) %>" selected="<%= displayTerms.getStatus() == WorkflowConstants.STATUS_APPROVED %>" value="<%= WorkflowConstants.STATUS_APPROVED %>" />
				<aui:option label="<%= WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_DRAFT) %>" selected="<%= displayTerms.getStatus() == WorkflowConstants.STATUS_DRAFT %>" value="<%= WorkflowConstants.STATUS_DRAFT %>" />
				<aui:option label="<%= WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_PENDING) %>" selected="<%= displayTerms.getStatus() == WorkflowConstants.STATUS_PENDING %>" value="<%= WorkflowConstants.STATUS_PENDING %>" />
			</aui:select>
		</div>

		<aui:field-wrapper cssClass="float-container kb-field-wrapper" label="date">
			<aui:select cssClass="kb-date-field" ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="" name="<%= displayTerms.ANYTIME %>">
				<aui:option label="anytime" selected="<%= displayTerms.isAnytime() %>" value="<%= true %>" />
				<aui:option label="between" selected="<%= !displayTerms.isAnytime() %>" value="<%= false %>" />
			</aui:select>

			<span class='kb-date-field <%= displayTerms.isAnytime() ? "hide" : "" %>' id="<portlet:namespace />datesOptions">
				<liferay-ui:input-date
					dayParam="<%= displayTerms.START_DATE_DAY %>"
					dayValue="<%= displayTerms.getStartDateDay() %>"
					firstDayOfWeek="<%= displayTerms.getFirstDayOfWeek() %>"
					monthParam="<%= displayTerms.START_DATE_MONTH %>"
					monthValue="<%= displayTerms.getStartDateMonth() %>"
					yearParam="<%= displayTerms.START_DATE_YEAR %>"
					yearValue="<%= displayTerms.getStartDateYear() %>"
				/>

				<liferay-ui:input-date
					dayParam="<%= displayTerms.END_DATE_DAY %>"
					dayValue="<%= displayTerms.getEndDateDay() %>"
					firstDayOfWeek="<%= displayTerms.getFirstDayOfWeek() %>"
					monthParam="<%= displayTerms.END_DATE_MONTH %>"
					monthValue="<%= displayTerms.getEndDateMonth() %>"
					yearParam="<%= displayTerms.END_DATE_YEAR %>"
					yearValue="<%= displayTerms.getEndDateYear() %>"
				/>
			</span>
		</aui:field-wrapper>
	</aui:fieldset>
</liferay-ui:search-toggle>

<aui:script>
	Liferay.Util.toggleSelectBox('<portlet:namespace /><%= displayTerms.ANYTIME %>', '<%= false %>', '<portlet:namespace />datesOptions');
</aui:script>