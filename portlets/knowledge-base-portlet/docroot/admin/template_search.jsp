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
KBTemplateSearch searchContainer = (KBTemplateSearch)request.getAttribute("liferay-ui:search:searchContainer");

KBTemplateDisplayTerms displayTerms = (KBTemplateDisplayTerms)searchContainer.getDisplayTerms();
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_knowledge_base_template_search"
>
	<aui:fieldset cssClass="kb-block-labels kb-search-toggle">
		<div class="kb-field-wrapper">
			<aui:input inlineField="<%= true %>" name="<%= KBTemplateDisplayTerms.TITLE %>" size="40" value="<%= displayTerms.getTitle() %>" />

			<aui:input inlineField="<%= true %>" name="<%= KBTemplateDisplayTerms.CONTENT %>" size="40" value="<%= displayTerms.getContent() %>" />
		</div>

		<aui:field-wrapper cssClass="float-container kb-field-wrapper" label="date">
			<aui:select cssClass="kb-date-field" ignoreRequestValue="<%= true %>" inlineField="<%= true %>" label="" name="<%= KBTemplateDisplayTerms.ANYTIME %>">
				<aui:option label="anytime" selected="<%= displayTerms.isAnytime() %>" value="<%= true %>" />
				<aui:option label="between" selected="<%= !displayTerms.isAnytime() %>" value="<%= false %>" />
			</aui:select>

			<span class="<%= displayTerms.isAnytime() ? "hide kb-date-field" : "kb-date-field" %>" id="<portlet:namespace />datesOptions">
				<liferay-ui:input-date
					dayParam="<%= KBTemplateDisplayTerms.START_DATE_DAY %>"
					dayValue="<%= displayTerms.getStartDateDay() %>"
					firstDayOfWeek="<%= displayTerms.getFirstDayOfWeek() %>"
					monthParam="<%= KBTemplateDisplayTerms.START_DATE_MONTH %>"
					monthValue="<%= displayTerms.getStartDateMonth() %>"
					yearParam="<%= KBTemplateDisplayTerms.START_DATE_YEAR %>"
					yearValue="<%= displayTerms.getStartDateYear() %>"
				/>

				<liferay-ui:input-date
					dayParam="<%= KBTemplateDisplayTerms.END_DATE_DAY %>"
					dayValue="<%= displayTerms.getEndDateDay() %>"
					firstDayOfWeek="<%= displayTerms.getFirstDayOfWeek() %>"
					monthParam="<%= KBTemplateDisplayTerms.END_DATE_MONTH %>"
					monthValue="<%= displayTerms.getEndDateMonth() %>"
					yearParam="<%= KBTemplateDisplayTerms.END_DATE_YEAR %>"
					yearValue="<%= displayTerms.getEndDateYear() %>"
				/>
			</span>
		</aui:field-wrapper>
	</aui:fieldset>
</liferay-ui:search-toggle>

<aui:script>
	Liferay.Util.toggleSelectBox('<portlet:namespace /><%= KBTemplateDisplayTerms.ANYTIME %>', '<%= false %>', '<portlet:namespace />datesOptions');
</aui:script>