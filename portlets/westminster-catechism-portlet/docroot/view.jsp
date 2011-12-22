<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.westminstercatechism.model.WCEntry" %>
<%@ page import="com.liferay.westminstercatechism.util.WCUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String tabs1 = ParamUtil.getString(request, "tabs1", "larger");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.MAXIMIZED);

portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:tabs
	names="larger,shorter"
	url="<%= portletURL.toString() %>"
/>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<c:choose>
		<c:when test='<%= tabs1.equals("larger") %>'>
			<table class="lfr-table">

			<%
			List<WCEntry> entries = WCUtil.getLarger();

			for (int i = 0; i < entries.size(); i++) {
				WCEntry entry = entries.get(i);
			%>

				<tr>
					<td valign="top">
						<strong><%= i + 1 %>.</strong>
					</td>
					<td valign="top">
						<a id="q<%= i + 1%>">

						<a href="#a<%= i + 1 %>"><u><%= WCUtil.translate(entry.getQuestion()) %></u></a>

						<br /><br />
					</td>
				</tr>

			<%
			}
			%>

			<tr>
				<td colspan="2">
					<br />
				</td>
			</tr>

			<%
			for (int i = 0; i < entries.size(); i++) {
				WCEntry entry = entries.get(i);
			%>

				<tr>
					<td valign="top">
						<strong><%= i + 1 %>.</strong>
					</td>
					<td valign="top">
						<a id="a<%= i + 1%>">

						<strong><%= WCUtil.translate(entry.getQuestion()) %></strong>&nbsp;&nbsp;<a href="#q<%= i + 1 %>">&laquo;</a>

						<br /><br />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<%= WCUtil.translate(entry.getAnswer()) %>

						<br /><br />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>

						<%
						int letter = (int)'a';
						String letterSuffix = "";

						String[][] proofs = entry.getProofs();

						for (int j = 0; j < proofs.length; j++) {
							String[] scriptures = proofs[j];
						%>

							[<%= (char)letter %><%= letterSuffix %>].

						<%

							for (int k = 0; k < scriptures.length; k++) {
						%>

								<%= scriptures[k] %><c:if test="<%= (k + 1) < scriptures.length %>">;</c:if>

						<%
							}
						%>


							<br />

						<%
							if ((char)letter == 'z') {
								letter = (int)'a';
								letterSuffix = String.valueOf(GetterUtil.getInteger(letterSuffix) + 1);
						%>

							<br />

						<%
							}
							else {
								letter++;
							}
						}
						%>

						<c:if test="<%= (i + 1) < entries.size() %>">
							<br /><br />
						</c:if>
					</td>
				</tr>
			<%
			}
			%>

			</table>
		</c:when>
		<c:when test='<%= tabs1.equals("shorter") %>'>
			<table class="lfr-table">

			<%
			List<WCEntry> entries = WCUtil.getShorter();

			for (int i = 0; i < entries.size(); i++) {
				WCEntry entry = entries.get(i);
			%>

				<tr>
					<td valign="top">
						<strong><%= i + 1 %>.</strong>
					</td>
					<td valign="top">
						<a id="q<%= i + 1%>">

						<a href="#a<%= i + 1 %>"><u><%= WCUtil.translate(entry.getQuestion()) %></u></a>

						<br /><br />
					</td>
				</tr>

			<%
			}
			%>

			<tr>
				<td colspan="2">
					<br />
				</td>
			</tr>

			<%
			for (int i = 0; i < entries.size(); i++) {
				WCEntry entry = entries.get(i);
			%>

				<tr>
					<td valign="top">
						<strong><%= i + 1 %>.</strong>
					</td>
					<td valign="top">
						<a id="a<%= i + 1%>">

						<strong><%= WCUtil.translate(entry.getQuestion()) %></strong>&nbsp;&nbsp;<a href="#q<%= i + 1 %>">&laquo;</a>

						<br /><br />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<%= WCUtil.translate(entry.getAnswer()) %>

						<br /><br />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>

						<%
						int letter = 97;

						String[][] proofs = entry.getProofs();

						for (int j = 0; j < proofs.length; j++) {
							String[] scriptures = proofs[j];
						%>

							[<%= (char)letter++ %>].

						<%

							for (int k = 0; k < scriptures.length; k++) {
						%>

								<%= scriptures[k] %><c:if test="<%= (k + 1) < scriptures.length %>">;</c:if>

						<%
							}
						%>


							<br />

						<%
						}
						%>

						<c:if test="<%= (i + 1) < entries.size() %>">
							<br /><br />
						</c:if>
					</td>
				</tr>
			<%
			}
			%>

			</table>
		</c:when>
	</c:choose>
</c:if>