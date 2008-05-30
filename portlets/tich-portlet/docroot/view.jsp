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

<%@ include file="/html/portlet/today_in_christian_history/init.jsp" %>

<%
List events = TICHUtil.getEvents();
%>

<c:choose>
	<c:when test="<%= events != null %>">
		<c:choose>
			<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">

				<%
				Event event = (Event)events.get(Randomizer.getInstance().nextInt(events.size()));
				%>

				<b><i><%= event.getYear() %></i></b>

				<br /><br />

				<%= event.getDescription() %>

				<br /><br />

				<a href="<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" />">
				<liferay-ui:message key="read-more" /> &raquo;
				</a>
			</c:when>
			<c:otherwise>
				<table class="lfr-table">

				<%
				for (int i = 0; i < events.size(); i++) {
					Event event = (Event)events.get(i);
				%>

					<tr>
						<td valign="top">
							<b><i><%= event.getYear() %></i></b>
						</td>
						<td>
							<%= event.getDescription() %>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<br />
						</td>
					</tr>

				<%
				}
				%>

				<tr>
					<td></td>
					<td>
						<liferay-ui:message key="source" />:

						<c:if test="<%= user.hasCompanyMx() %>">

							<%
							PortletURL mailURL = new PortletURLImpl(request, PortletKeys.MAIL, plid.longValue(), PortletRequest.ACTION_PHASE);

							mailURL.setWindowState(WindowState.MAXIMIZED);
							mailURL.setPortletMode(PortletMode.VIEW);

							mailURL.setParameter("struts_action", "/mail/compose_message_to");
							mailURL.setParameter("recipient_address", "pilgrimwb@aol.com");
							mailURL.setParameter("recipient_name", "William D. Blake");
							%>

							<a href="<%= mailURL.toString() %>">
						</c:if>

						<c:if test="<%= !user.hasCompanyMx() %>">
							<a href="mailto:pilgrimwb@aol.com" target="_blank">
						</c:if>

						William D. Blake</a>, <i>Almanac of the Christian Church</i>.
					</td>
				</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_error.jsp" />
	</c:otherwise>
</c:choose>