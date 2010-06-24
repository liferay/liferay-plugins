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

<%@ include file="/init.jsp" %>

<%
String query = ParamUtil.getString(request, "q");
String categoryId = ParamUtil.getString(request, "cat", "all");
int start = ParamUtil.getInteger(request, "start", 0);

String url = "http://www.google.com/ig/directory?synd=open";

if (Validator.isNotNull(query)) {
	url += "&q=" + java.net.URLEncoder.encode(query) + "&btnG=Search+Google+Gadgets";
}
else {
	url += "&cat=" + categoryId;
}

url += "&start=" + start + "&sa=N";

GGData data = GGUtil.getData(url);
%>

<c:choose>
	<c:when test="<%= data != null %>">
		<aui:script>
			function <portlet:namespace />addGadgetByURL() {
				var gadgetId = document.<portlet:namespace />fm.<portlet:namespace />q.value;

				if (gadgetId.indexOf("http") == 0) {
					<portlet:namespace />chooseGadget(gadgetId);
				}
				else {
					<portlet:namespace />searchGadgets();
				}
			}

			function <portlet:namespace />chooseGadget(gadgetId) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";
				document.<portlet:namespace />fm.<portlet:namespace />gadgetId.value = gadgetId;
				submitForm(document.<portlet:namespace />fm);
			}

			function <portlet:namespace />searchGadgets() {
				var query = document.<portlet:namespace />fm.<portlet:namespace />q.value;
				submitForm(document.hrefFm, '<liferay-portlet:actionURL portletConfiguration="true"></liferay-portlet:actionURL>&q=' + query + '&start=0');
			}
		</aui:script>

		<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />addGadgetByURL(); return false;">
		<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
		<input name="<portlet:namespace />gadgetId" type="hidden" value="" />

		<table class="lfr-table" width="100%">
		<tr>
			<td valign="top">
				<liferay-ui:message key="categories" />

				<br /><br />

				<%
				List categories = data.getCategories();

				for (int i = 0; i < categories.size(); i++) {
					GGCategory category = (GGCategory)categories.get(i);
				%>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(category.getName()) %>">
							<a href="javascript:submitForm(document.hrefFm, '<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="cat" value="<%= category.getCategoryId() %>" /></liferay-portlet:actionURL>');"><%= category.getName() %></a><br />
						</c:when>
						<c:otherwise>
							<br />
						</c:otherwise>
					</c:choose>

				<%
				}
				%>

			</td>
			<td>
				<liferay-ui:message key="add-a-google-gadget-by-specifying-a-gadget-url,-or-choose-from-the-gadgets-below" />

				<br /><br />

				<input name="<portlet:namespace />q" size="50" type="text" />

				<input onclick="<portlet:namespace />searchGadgets();" type="button" value="<liferay-ui:message key="search-gadgets" />" />

				<liferay-ui:message key="or" />

				<input onclick="<portlet:namespace />addGadgetByURL();" type="button" value="<liferay-ui:message key="add-gadget-by-url" />" />

				<%
				GGPagination pagination = data.getPagination();
				%>

				<div style="text-align: right;">
					<span style="padding-left: 10px;">
						<c:if test="<%= pagination.getPrevStart() >= 0 %>">
							<a href="javascript:submitForm(document.hrefFm, '<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="cat" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(pagination.getPrevStart()) %>" /></liferay-portlet:actionURL>');">&laquo; <liferay-ui:message key="previous" /></a>
						</c:if>
					</span>

					<span style="padding-left: 10px;">
						<c:if test="<%= (pagination.getMoreStart()) > 0 %>">
							<a href="javascript:submitForm(document.hrefFm, '<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="cat" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(pagination.getMoreStart()) %>" /></liferay-portlet:actionURL>');"><liferay-ui:message key="next" /> &raquo;</a>
						</c:if>
					</span>
				</div>

				<br />

				<liferay-ui:table-iterator
					list="<%= data.getEntries() %>"
					listType="com.liferay.googlegadget.model.GGEntry"
					rowLength="4"
					width="100%"
				>
					<div style="text-align: center;">
						<%= tableIteratorObj.getName() %>

						<div style="padding: 5px;">
							<img height="60" src="<%= tableIteratorObj.getImage() %>" width="120" />
						</div>

						<input onclick="<portlet:namespace />chooseGadget('<%= tableIteratorObj.getGadgetId() %>');" type="button" value="<liferay-ui:message key="choose" />" />
					</div>

					<div style="padding-top: 20px;" />
				</liferay-ui:table-iterator>

				<div style="text-align: right;">

					<span style="padding-left: 10px;">
						<c:if test="<%= pagination.getPrevStart() >= 0 %>">
							<a href="javascript:submitForm(document.hrefFm, '<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="cat" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(pagination.getPrevStart()) %>" /></liferay-portlet:actionURL>');">&laquo; <liferay-ui:message key="previous" /></a>
						</c:if>
					</span>

					<span style="padding-left: 10px;">
						<c:if test="<%= (pagination.getMoreStart()) > 0 %>">
							<a href="javascript:submitForm(document.hrefFm, '<liferay-portlet:actionURL portletConfiguration="true"><portlet:param name="cat" value="<%= categoryId %>" /><portlet:param name="start" value="<%= String.valueOf(pagination.getMoreStart()) %>" /></liferay-portlet:actionURL>');"><liferay-ui:message key="next" /> &raquo;</a>
						</c:if>
					</span>
				</div>
			</td>
		</tr>
		</table>

		</form>
	</c:when>
	<c:otherwise>
		<span class="portlet-msg-error">
		<liferay-ui:message key="an-unexpected-error-occurred-while-connecting-to-google" />
		</span>
	</c:otherwise>
</c:choose>