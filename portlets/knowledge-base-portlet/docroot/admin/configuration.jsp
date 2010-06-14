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

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />

	<liferay-ui:tabs
		names="email-from,article-added-email,article-updated-email,display-settings,rss"
		param="tabs2"
		url="<%= portletURL %>"
	/>

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("email-from") %>'>
				Placeholder
			</c:when>
			<c:when test='<%= tabs2.startsWith("article-") %>'>
				Placeholder
			</c:when>
			<c:when test='<%= tabs2.equals("display-settings") %>'>
				<liferay-ui:tabs
					names="article,template"
					param="tabs3"
					url="<%= portletURL %>"
				/>

				<c:choose>
					<c:when test='<%= tabs3.equals("article") %>'>
						<aui:select label="maximum-items-to-display" name="articlesDelta">

							<%
							int[] deltaValues = new int[] {1,2,3,4,5,10,15,20,25,30,40,50,60,70,80,90,100};

							for (int i = 0; i < deltaValues.length; i++) {
							%>

								<aui:option label="<%= deltaValues[i] %>" selected="<%= deltaValues[i] == articlesDelta %>" />

							<%
							}
							%>

						</aui:select>

						<aui:select label="display-style" name="articlesDisplayStyle">

							<%
							String[] displayStyleValues = new String[] {"full-content", "abstract", "title"};

							for (int i = 0; i < displayStyleValues.length; i++) {
							%>

								<aui:option label="<%= displayStyleValues[i] %>" selected="<%= articlesDisplayStyle.equals(displayStyleValues[i]) %>" />

							<%
							}
							%>

						</aui:select>

						<aui:select name="childArticlesDisplayStyle">

							<%
							String[] displayStyleValues = new String[] {"full-content", "abstract", "title"};

							for (int i = 0; i < displayStyleValues.length; i++) {
							%>

								<aui:option label="<%= displayStyleValues[i] %>" selected="<%= childArticlesDisplayStyle.equals(displayStyleValues[i]) %>" />

							<%
							}
							%>

						</aui:select>

						<aui:input inlineLabel="left" label="enable-description" name="enableArticleDescription" type="checkbox" value="<%= enableArticleDescription %>" />

						<aui:input inlineLabel="left" label="enable-comments" name="enableArticleComments" type="checkbox" value="<%= enableArticleComments %>" />

						<aui:input inlineLabel="left" label="enable-comment-ratings" name="enableArticleCommentRatings" type="checkbox" value="<%= enableArticleCommentRatings %>" />
					</c:when>
					<c:when test='<%= tabs3.equals("template") %>'>
						<aui:select label="maximum-items-to-display" name="templatesDelta">

							<%
							int[] deltaValues = new int[] {1,2,3,4,5,10,15,20,25,30,40,50,60,70,80,90,100};

							for (int i = 0; i < deltaValues.length; i++) {
							%>

								<aui:option label="<%= deltaValues[i] %>" selected="<%= deltaValues[i] == templatesDelta %>" />

							<%
							}
							%>

						</aui:select>

						<aui:select label="display-style" name="templatesDisplayStyle">

							<%
							String[] displayStyleValues = new String[] {"full-content", "abstract", "title"};

							for (int i = 0; i < displayStyleValues.length; i++) {
							%>

								<aui:option label="<%= displayStyleValues[i] %>" selected="<%= templatesDisplayStyle.equals(displayStyleValues[i]) %>" />

							<%
							}
							%>

						</aui:select>

						<aui:input inlineLabel="left" label="enable-description" name="enableTemplateDescription" type="checkbox" value="<%= enableTemplateDescription %>" />

						<aui:input inlineLabel="left" label="enable-comments" name="enableTemplateComments" type="checkbox" value="<%= enableTemplateComments %>" />

						<aui:input inlineLabel="left" label="enable-comment-ratings" name="enableTemplateCommentRatings" type="checkbox" value="<%= enableTemplateCommentRatings %>" />
					</c:when>
				</c:choose>
			</c:when>
			<c:when test='<%= tabs2.equals("rss") %>'>
				Placeholder
			</c:when>
		</c:choose>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>