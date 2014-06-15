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

<%@ include file="/init.jsp" %>

<%
long bbbMeetingId = ParamUtil.getLong(request, "bbbMeetingId");

List<BBBParticipant> bbbParticipants = Collections.emptyList();

int[] bbbParticipantIndexes = null;

String bbbParticipantIndexesParam = ParamUtil.getString(request, "bbbParticipantIndexes");

if (Validator.isNotNull(bbbParticipantIndexesParam)) {
	bbbParticipants = new ArrayList<BBBParticipant>();

	bbbParticipantIndexes = StringUtil.split(bbbParticipantIndexesParam, 0);

	for (int i = 0; i < bbbParticipantIndexes.length; i++) {
		bbbParticipants.add(new BBBParticipantImpl());
	}
}
else {
	if (bbbMeetingId > 0) {
		bbbParticipants = BBBParticipantLocalServiceUtil.getBBBParticipants(bbbMeetingId);

		bbbParticipantIndexes = new int[bbbParticipants.size()];

		for (int i = 0; i < bbbParticipants.size() ; i++) {
			bbbParticipantIndexes[i] = i;
		}
	}

	if (bbbParticipants.isEmpty()) {
		bbbParticipants = new ArrayList<BBBParticipant>();

		bbbParticipants.add(new BBBParticipantImpl());

		bbbParticipantIndexes = new int[] {0};
	}

	if (bbbParticipantIndexes == null) {
		bbbParticipantIndexes = new int[0];
	}
}
%>

<h3><liferay-ui:message key="participants" /></h3>

<aui:fieldset>

	<%
	for (int i = 0; i < bbbParticipantIndexes.length; i++) {
		int bbbParticipantsIndex = bbbParticipantIndexes[i];

		BBBParticipant bbbParticipant = bbbParticipants.get(i);
	%>

		<aui:model-context bean="<%= bbbParticipant %>" model="<%= BBBParticipant.class %>" />

		<div class="lfr-form-row lfr-form-row-inline">
			<div class="row-fields">
				<aui:input name='<%= "bbbParticipantId" + bbbParticipantsIndex %>' type="hidden" value="<%= bbbParticipant.getBbbParticipantId() %>" />

				<aui:input fieldParam='<%= "bbbParticipantName" + bbbParticipantsIndex %>' id='<%= "bbbParticipantName" + bbbParticipantsIndex %>' inlineField="<%= true %>" name="name" />

				<aui:input fieldParam='<%= "bbbParticipantEmailAddress" + bbbParticipantsIndex %>' id='<%= "bbbParticipantEmailAddress" + bbbParticipantsIndex %>' inlineField="<%= true %>" name="emailAddress" />

				<aui:select inlineField="<%= true %>" label="type" name='<%= "bbbParticipantType" + bbbParticipantsIndex %>'>
					<aui:option label="attendee" value="<%= BBBParticipantConstants.TYPE_ATTENDEE %>" />
					<aui:option label="moderator" selected="<%= bbbParticipant.getType() == BBBParticipantConstants.TYPE_MODERATOR %>" value="<%= BBBParticipantConstants.TYPE_MODERATOR %>" />
				</aui:select>
			</div>
		</div>

	<%
	}
	%>

	<aui:input name="bbbParticipantIndexes" type="hidden" value="<%= StringUtil.merge(bbbParticipantIndexes) %>" />
</aui:fieldset>

<aui:script use="liferay-auto-fields">
	new Liferay.AutoFields(
		{
			contentBox: 'fieldset',
			fieldIndexes: '<portlet:namespace />bbbParticipantIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();
</aui:script>