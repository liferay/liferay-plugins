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

		<%
		Object[][] params = new Object[5][2];

		if (tabs3.equals("article")) {
			params[0] = new Object[] {"articlesDelta", articlesDelta};
			params[1] = new Object[] {"articlesDisplayStyle", articlesDisplayStyle};
			params[2] = new Object[] {"enableArticleDescription", enableArticleDescription};
			params[3] = new Object[] {"enableArticleComments", enableArticleComments};
			params[4] = new Object[] {"enableArticleCommentRatings", enableArticleCommentRatings};
		}
		else if (tabs3.equals("template")) {
			params[0] = new Object[] {"templatesDelta", templatesDelta};
			params[1] = new Object[] {"templatesDisplayStyle", templatesDisplayStyle};
			params[2] = new Object[] {"enableTemplateDescription", enableTemplateDescription};
			params[3] = new Object[] {"enableTemplateComments", enableTemplateComments};
			params[4] = new Object[] {"enableTemplateCommentRatings", enableTemplateCommentRatings};
		}
		%>

		<strong><liferay-ui:message key="maximum-items-to-display" /></strong><br />

		<%
		int[] deltaValues = new int[] {1,2,3,4,5,10,15,20,25,30,40,50,60,70,80,90,100};
		%>

		<select name="<portlet:namespace /><%= (String)params[0][0] %>">

			<%
			for (int i = 0; i < deltaValues.length; i++) {
			%>

				<option <%= ((Integer)params[0][1] == deltaValues[i]) ? "selected" : StringPool.BLANK %> value="<%= deltaValues[i] %>"><%= deltaValues[i] %></option>

			<%
			}
			%>

		</select>

		<br /><br />

		<strong><liferay-ui:message key="display-style" /></strong><br />

		<%
		String[] displayStyleValues = new String[] {"full-content", "abstract", "title"};
		%>

		<select name="<portlet:namespace /><%= (String)params[1][0] %>">

			<%
			for (int i = 0; i < displayStyleValues.length; i++) {
			%>

				<option <%= ((String)params[1][1] == displayStyleValues[i]) ? "selected" : StringPool.BLANK %> value="<%= displayStyleValues[i] %>"><liferay-ui:message key="<%= displayStyleValues[i] %>" /></option>

			<%
			}
			%>

		</select>

		<br /><br />

		<input <%= (Boolean)params[2][1] ? "checked" : StringPool.BLANK %> name="<portlet:namespace /><%= (String)params[2][0] %>" type="checkbox" />

		<strong>Enable Description</strong>

		<br /><br />

		<input <%= (Boolean)params[3][1] ? "checked" : StringPool.BLANK %> name="<portlet:namespace /><%= (String)params[3][0] %>" type="checkbox" />

		<strong>Enable Comments</strong>

		<br /><br />

		<input <%= (Boolean)params[4][1] ? "checked" : StringPool.BLANK %> name="<portlet:namespace /><%= (String)params[4][0] %>" type="checkbox" />

		<strong>Enable Comment Ratings</strong>

		<br /><br />
	</c:when>
	<c:when test='<%= tabs2.equals("rss") %>'>
		Placeholder

		<br /><br />
	</c:when>
</c:choose>

<input type="submit" value="<liferay-ui:message key="save" />" />

</form>