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

<%@ include file="/init.jsp" %>

<%
String query = ParamUtil.getString(request, "query");
String sort = ParamUtil.getString(request, "sort", "recent");
int category = ParamUtil.getInteger(request, "category");
String region = ParamUtil.getString(request, "region", "all");

String htmlAttributes =
	"alt=" + alt + "\n" +
	"height-maximized=" + heightMaximized + "\n" +
	"height-normal=" + heightNormal + "\n" +
	"width=" + width + "\n";
%>

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="preferences--link--" type="hidden" />
	<aui:input name="preferences--title--" type="hidden" />
	<aui:input name="preferences--description--" type="hidden" />
	<aui:input name="preferences--thumbnail--" type="hidden" />

	<liferay-portlet:renderURL portletConfiguration="true" varImpl="iteratorURL">
		<portlet:param name="query" value="<%= query %>" />
		<portlet:param name="sort" value="<%= sort %>" />
		<portlet:param name="category" value="<%= String.valueOf(category) %>" />
		<portlet:param name="region" value="<%= region %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:search-container
		headerNames="thumbnail,description"
		iteratorURL="<%= iteratorURL %>"
	>
		<liferay-ui:search-form
			page="/widget_search.jsp"
			servletContext="<%= application %>"
		/>

		<liferay-ui:search-container-results>

			<%
			Object[] widgets = NetvibesWidgetUtil.getWidgets(query, sort, category, region, searchContainer.getCur(), searchContainer.getDelta());

			pageContext.setAttribute("results", (List<Object[]>)widgets[0]);
			pageContext.setAttribute("total", (Integer)widgets[1]);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="java.lang.Object"
			modelVar="obj"
		>

			<%
			Object[] widget = (Object[])obj;

			String curLink = (String)widget[0];
			String curTitle = (String)widget[1];
			String curDescription = (String)widget[2];
			String curThumbnail = (String)widget[3];

			String updateWidgetURL = "javascript:" + renderResponse.getNamespace() + "updateWidget('" + curLink + "','" + UnicodeFormatter.toString(curTitle) + "','" + UnicodeFormatter.toString(curDescription) + "','" + curThumbnail + "');";
			%>

			<liferay-ui:search-container-column-text
				href="<%= updateWidgetURL %>"
				name="thumbnail"
			>
				<img alt="<%= curTitle %>" src="<%= curThumbnail %>" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= updateWidgetURL %>"
				name="description"
				valign="top"
			>
				<div style="font-size: 1.2em;">
					<strong><%= curTitle %></strong>
				</div>

				<div>
					<%= curDescription %>
				</div>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<c:if test="<%= Validator.isNotNull(link) %>">
			<aui:fieldset>
				<div class="float-container">
					<img alt="<%= title %>" src="<%= thumbnail %>" style="float: left; padding-right: 10px;" />

					<div style="font-size: 1.2em;">
						<strong><%= title %></strong>
					</div>

					<%= description %>
				</div>

				<aui:input cssClass="lfr-textarea-container lfr-textarea" name="preferences--htmlAttributes--" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" value="<%= htmlAttributes %>" wrap="soft" />
			</aui:fieldset>

			<aui:button onClick="<portlet:namespace />updateWidget('<%= link %>','<%= UnicodeFormatter.toString(title) %>','<%= UnicodeFormatter.toString(description) %>','<%= UnicodeFormatter.toString(thumbnail) %>');" type="submit" />

			<div class="separator"><!-- --></div>
		</c:if>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />search() {
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateWidget(link, title, description, thumbnail) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.UPDATE %>";
		document.<portlet:namespace />fm.<portlet:namespace />link.value = link;
		document.<portlet:namespace />fm.<portlet:namespace />title.value = title;
		document.<portlet:namespace />fm.<portlet:namespace />description.value = description;
		document.<portlet:namespace />fm.<portlet:namespace />thumbnail.value = thumbnail;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>