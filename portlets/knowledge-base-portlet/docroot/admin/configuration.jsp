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

<%@ include file="/admin/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "email-from");
String tabs3 = ParamUtil.getString(request, "tabs3", "article");
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="<%= tabs3 %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<form action="<%= configurationURL %>" method="post" name="fm">
<input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
<input name="tabs2" type="hidden" value="<%= HtmlUtil.escapeAttribute(tabs2) %>" />
<input name="tabs3" type="hidden" value="<%= HtmlUtil.escapeAttribute(tabs3) %>" />

<%
String tabs2Names = "Email From,Article Added Email,Article Updated Email,Display Settings,RSS";
String tabs2Values = StringUtil.replace(tabs2Names.toLowerCase(), " ", "-");
%>

<liferay-ui:tabs
	names="<%= tabs2Names %>"
	param="tabs2"
	tabsValues="<%= tabs2Values %>"
	url="<%= portletURL %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("email-from") %>'>
		Placeholder

		<br /><br />
	</c:when>
	<c:when test='<%= tabs2.startsWith("article-") %>'>
		Placeholder

		<br /><br />
	</c:when>
	<c:when test='<%= tabs2.equals("display-settings") %>'>
		<liferay-ui:tabs
			names="article,template"
			param="tabs3"
			url="<%= portletURL %>"
		/>

		<c:choose>
			<c:when test='<%= tabs3.equals("article") %>'>
				<strong><liferay-ui:message key="maximum-items-to-display" /></strong><br />

				<%
				int[] deltaValues = new int[] {1,2,3,4,5,10,15,20,25,30,40,50,60,70,80,90,100};
				%>

				<select name="<portlet:namespace />articlesDelta">

					<%
					for (int i = 0; i < deltaValues.length; i++) {
					%>

						<option <%= (articlesDelta == deltaValues[i]) ? "selected" : StringPool.BLANK %> value="<%= deltaValues[i] %>"><%= deltaValues[i] %></option>

					<%
					}
					%>

				</select>

				<br /><br />

				<strong><liferay-ui:message key="display-style" /></strong><br />

				<%
				String[] displayStyleValues = new String[] {"full-content", "abstract", "title"};
				%>

				<select name="<portlet:namespace />articlesDisplayStyle">

					<%
					for (int i = 0; i < displayStyleValues.length; i++) {
					%>

						<option <%= articlesDisplayStyle.equals(displayStyleValues[i]) ? "selected" : StringPool.BLANK %> value="<%= displayStyleValues[i] %>"><liferay-ui:message key="<%= displayStyleValues[i] %>" /></option>

					<%
					}
					%>

				</select>

				<br /><br />

				<input <%= enableArticleDescription ? "checked" : StringPool.BLANK %> name="<portlet:namespace />enableArticleDescription" type="checkbox" />

				<strong>Enable Description</strong>

				<br /><br />

				<input <%= enableArticleComments ? "checked" : StringPool.BLANK %> name="<portlet:namespace />enableArticleComments" type="checkbox" />

				<strong>Enable Comments</strong>

				<br /><br />

				<input <%= enableArticleCommentRatings ? "checked" : StringPool.BLANK %> name="<portlet:namespace />enableArticleCommentRatings" type="checkbox" />

				<strong>Enable Comment Ratings</strong>

				<br /><br />
			</c:when>
			<c:when test='<%= tabs3.equals("template") %>'>
				<strong><liferay-ui:message key="maximum-items-to-display" /></strong><br />

				<%
				int[] deltaValues = new int[] {1,2,3,4,5,10,15,20,25,30,40,50,60,70,80,90,100};
				%>

				<select name="<portlet:namespace />templatesDelta">

					<%
					for (int i = 0; i < deltaValues.length; i++) {
					%>

						<option <%= (templatesDelta == deltaValues[i]) ? "selected" : StringPool.BLANK %> value="<%= deltaValues[i] %>"><%= deltaValues[i] %></option>

					<%
					}
					%>

				</select>

				<br /><br />

				<strong><liferay-ui:message key="display-style" /></strong><br />

				<%
				String[] displayStyleValues = new String[] {"full-content", "abstract", "title"};
				%>

				<select name="<portlet:namespace />templatesDisplayStyle">

					<%
					for (int i = 0; i < displayStyleValues.length; i++) {
					%>

						<option <%= templatesDisplayStyle.equals(displayStyleValues[i]) ? "selected" : StringPool.BLANK %> value="<%= displayStyleValues[i] %>"><liferay-ui:message key="<%= displayStyleValues[i] %>" /></option>

					<%
					}
					%>

				</select>

				<br /><br />

				<input <%= enableTemplateDescription ? "checked" : StringPool.BLANK %> name="<portlet:namespace />enableTemplateDescription" type="checkbox" />

				<strong>Enable Description</strong>

				<br /><br />

				<input <%= enableTemplateComments ? "checked" : StringPool.BLANK %> name="<portlet:namespace />enableTemplateComments" type="checkbox" />

				<strong>Enable Comments</strong>

				<br /><br />

				<input <%= enableTemplateCommentRatings ? "checked" : StringPool.BLANK %> name="<portlet:namespace />enableTemplateCommentRatings" type="checkbox" />

				<strong>Enable Comment Ratings</strong>

				<br /><br />
			</c:when>
		</c:choose>
	</c:when>
	<c:when test='<%= tabs2.equals("rss") %>'>
		Placeholder

		<br /><br />
	</c:when>
</c:choose>

<input type="submit" value="<liferay-ui:message key="save" />" />

</form>