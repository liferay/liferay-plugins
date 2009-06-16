<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
%>

<link href="<%= request.getContextPath() %>/meetups/css.jsp?themeId=<%= themeDisplay.getTheme().getThemeId() %>&amp;colorSchemeId=<%= themeDisplay.getColorScheme().getColorSchemeId() %>&amp;t=<%= portlet.getTimestamp() %>" rel="stylesheet" type="text/css" />

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "attending");

String redirect = ParamUtil.getString(request, "redirect");

long meetupsEntryId = ParamUtil.getLong(request, "meetupsEntryId");

MeetupsEntry meetupsEntry = null;

try {
	meetupsEntry = MeetupsEntryLocalServiceUtil.getMeetupsEntry(meetupsEntryId);
}
catch (NoSuchMeetupsEntryException nsmee) {
%>

	<span class="portlet-msg-error">
		The meetup could not be found.
	</span>

<%
	return;
}

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
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("meetupsEntryId", String.valueOf(meetupsEntryId));
%>

<img alt="" src="<%= themeDisplay.getPathImage() %>?img_id=<%= meetupsEntry.getThumbnailId() %>&t=<%= ImageServletTokenUtil.getToken(meetupsEntry.getThumbnailId()) %>" style="float: left; margin-right: 10px;" />

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

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
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
			names="attending,not-attending,maybe-attending"
			portletURL="<%= portletURL %>"
		/>

		<%
		int tabs1Status = MeetupsConstants.STATUS_YES;

		if (tabs1.equals("not-attending")) {
			tabs1Status = MeetupsConstants.STATUS_NO;
		}
		else if (tabs1.equals("maybe-attending")) {
			tabs1Status = MeetupsConstants.STATUS_MAYBE;
		}

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 10, portletURL, null, null);

		int total = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrationsCount(meetupsEntryId, tabs1Status);

		searchContainer.setTotal(total);

		List<MeetupsRegistration> results = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrations(meetupsEntryId, tabs1Status, searchContainer.getStart(), searchContainer.getEnd());

		for (MeetupsRegistration curMeetupsRegistration : results) {
		%>

			<div class="response">
				<liferay-ui:user-display
					userId="<%= curMeetupsRegistration.getUserId() %>"
					userName="<%= curMeetupsRegistration.getUserName() %>"
					displayStyle="<%= 2 %>"
				/>

				<c:if test="<%= Validator.isNotNull(curMeetupsRegistration.getComments()) %>">
					<div class="comments">
						<%= curMeetupsRegistration.getComments() %>

						<span class="indicator"></span>
					</div>
				</c:if>
			</div>

		<%
		}
		%>

		<script type="text/javascript">
			jQuery(
				function () {
					jQuery('.wol-portlet-meetups .response').hover(
						function() {
							jQuery(this).addClass('hovering');
						},
						function() {
							jQuery(this).removeClass('hovering');
						}
					);
				}
			);
		</script>

		<div class="taglib-search-iterator-page-iterator-bottom">
			<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
		</div>
	</c:when>
	<c:otherwise>
		<div>
			You have to be signed in to register for this meetup. Please <a href="<%= themeDisplay.getURLSignIn() %>">sign in</a> or <a href="<%= themeDisplay.getURLCreateAccount() %>">create an account</a> if you do not already have one.
		</div>
	</c:otherwise>
</c:choose>