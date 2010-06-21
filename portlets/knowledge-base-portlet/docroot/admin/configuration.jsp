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
							<aui:option label="1" selected="<%= articlesDelta == 1 %>" />
							<aui:option label="2" selected="<%= articlesDelta == 2 %>" />
							<aui:option label="3" selected="<%= articlesDelta == 3 %>" />
							<aui:option label="4" selected="<%= articlesDelta == 4 %>" />
							<aui:option label="5" selected="<%= articlesDelta == 5 %>" />
							<aui:option label="10" selected="<%= articlesDelta == 10 %>" />
							<aui:option label="15" selected="<%= articlesDelta == 15 %>" />
							<aui:option label="20" selected="<%= articlesDelta == 20 %>" />
							<aui:option label="25" selected="<%= articlesDelta == 25 %>" />
							<aui:option label="30" selected="<%= articlesDelta == 30 %>" />
							<aui:option label="40" selected="<%= articlesDelta == 40 %>" />
							<aui:option label="50" selected="<%= articlesDelta == 50 %>" />
							<aui:option label="60" selected="<%= articlesDelta == 60 %>" />
							<aui:option label="70" selected="<%= articlesDelta == 70 %>" />
							<aui:option label="80" selected="<%= articlesDelta == 80 %>" />
							<aui:option label="90" selected="<%= articlesDelta == 90 %>" />
							<aui:option label="100" selected="<%= articlesDelta == 100 %>" />
						</aui:select>

						<aui:select label="display-style" name="articlesDisplayStyle">
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= articlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= articlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= articlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
						</aui:select>

						<aui:select name="childArticlesDisplayStyle">
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
						</aui:select>

						<aui:input inlineLabel="left" label="enable-description" name="enableArticleDescription" type="checkbox" value="<%= enableArticleDescription %>" />

						<aui:input inlineLabel="left" label="enable-comments" name="enableArticleComments" type="checkbox" value="<%= enableArticleComments %>" />

						<aui:input inlineLabel="left" label="enable-comment-ratings" name="enableArticleCommentRatings" type="checkbox" value="<%= enableArticleCommentRatings %>" />
					</c:when>
					<c:when test='<%= tabs3.equals("template") %>'>
						<aui:select label="maximum-items-to-display" name="templatesDelta">
							<aui:option label="1" selected="<%= templatesDelta == 1 %>" />
							<aui:option label="2" selected="<%= templatesDelta == 2 %>" />
							<aui:option label="3" selected="<%= templatesDelta == 3 %>" />
							<aui:option label="4" selected="<%= templatesDelta == 4 %>" />
							<aui:option label="5" selected="<%= templatesDelta == 5 %>" />
							<aui:option label="10" selected="<%= templatesDelta == 10 %>" />
							<aui:option label="15" selected="<%= templatesDelta == 15 %>" />
							<aui:option label="20" selected="<%= templatesDelta == 20 %>" />
							<aui:option label="25" selected="<%= templatesDelta == 25 %>" />
							<aui:option label="30" selected="<%= templatesDelta == 30 %>" />
							<aui:option label="40" selected="<%= templatesDelta == 40 %>" />
							<aui:option label="50" selected="<%= templatesDelta == 50 %>" />
							<aui:option label="60" selected="<%= templatesDelta == 60 %>" />
							<aui:option label="70" selected="<%= templatesDelta == 70 %>" />
							<aui:option label="80" selected="<%= templatesDelta == 80 %>" />
							<aui:option label="90" selected="<%= templatesDelta == 90 %>" />
							<aui:option label="100" selected="<%= templatesDelta == 100 %>" />
						</aui:select>

						<aui:select label="display-style" name="templatesDisplayStyle">
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= templatesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= templatesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= templatesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
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