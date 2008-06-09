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

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "yes");

String redirect = ParamUtil.getString(request, "redirect");

long meetupsEntryId = ParamUtil.getLong(request, "meetupsEntryId");

MeetupsEntry meetupsEntry = MeetupsEntryLocalServiceUtil.getMeetupsEntry(meetupsEntryId);

MeetupsRegistration meetupsRegistration = null;

try {
	meetupsRegistration = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistration(themeDisplay.getUserId(), meetupsEntryId);
}
catch (NoSuchMeetupsRegistrationException nsmre) {
}

int status = MeetupsConstants.STATUS_YES;

if (meetupsRegistration != null) {
	status = meetupsRegistration.getStatus();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("jspPage", "/meetups/view_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("meetupsEntryId", String.valueOf(meetupsEntryId));
%>

<h4>
	<%= meetupsEntry.getTitle() %>
</h4>

<br />

<div>
	<%= meetupsEntry.getDescription() %>
</div>

<br />

<%
int yesTotal = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrationsCount(meetupsEntryId, MeetupsConstants.STATUS_YES);
%>

<c:if test="<%= yesTotal > 1 %>">
	<div>
		<%= LanguageUtil.format(pageContext, "x-people-are-planning-to-attend-this-meetup", String.valueOf(yesTotal)) %>
	</div>

	<br />
</c:if>

<form action="<portlet:actionURL name="updateMeetupsRegistration" />" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />updateMeetupsRegistration(); return false;">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />
<input name="<portlet:namespace />meetupsEntryId" type="hidden" value="<%= meetupsEntryId %>" />

<liferay-ui:message key="will-you-attend" />

<input <%= (status == MeetupsConstants.STATUS_YES) ? "checked" : "" %> name="<portlet:namespace />status" type="radio" value="<%= MeetupsConstants.STATUS_YES %>" /> <liferay-ui:message key="yes" />

<input <%= (status == MeetupsConstants.STATUS_NO) ? "checked" : "" %> name="<portlet:namespace />status" type="radio" value="<%= MeetupsConstants.STATUS_NO %>" /> <liferay-ui:message key="no" />

<input <%= (status == MeetupsConstants.STATUS_MAYBE) ? "checked" : "" %> name="<portlet:namespace />status" type="radio" value="<%= MeetupsConstants.STATUS_MAYBE %>" /> <liferay-ui:message key="maybe" />

<br /><br />

<liferay-ui:input-field model="<%= MeetupsRegistration.class %>" bean="<%= meetupsRegistration %>" field="comments" />

<br /><br />

<input type="submit" value="<liferay-ui:message key="register" />" />

</form>

<br />

<liferay-ui:tabs
	names="yes,no,maybe"
	portletURL="<%= portletURL %>"
/>

<%
int tabs1Status = MeetupsConstants.STATUS_YES;

if (tabs1.equals("no")) {
	tabs1Status = MeetupsConstants.STATUS_NO;
}
else if (tabs1.equals("maybe")) {
	tabs1Status = MeetupsConstants.STATUS_MAYBE;
}

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

int total = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrationsCount(meetupsEntryId, tabs1Status);

searchContainer.setTotal(total);

List<MeetupsRegistration> results = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrations(meetupsEntryId, tabs1Status, searchContainer.getStart(), searchContainer.getEnd());
%>

<table class="lfr-table" width="100%">

<%
for (int i = 0; i < results.size(); i++) {
	MeetupsRegistration curMeetupsRegistration = results.get(i);
%>

	<tr>
		<td align="center" valign="top">
			<liferay-ui:user-display
				userId="<%= curMeetupsRegistration.getUserId() %>"
				userName="<%= curMeetupsRegistration.getUserName() %>"
				displayStyle="<%= 2 %>"
			/>
		</td>
		<td valign="top" width="99%">
			<%= curMeetupsRegistration.getComments() %>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div class="separator"><!-- --></div>
		</td>
	</tr>

<%
}
%>

</table>

<div class="taglib-search-iterator-page-iterator-bottom">
	<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
</div>