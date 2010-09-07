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

String emailFromName = ParamUtil.getString(request, "emailFromName", preferences.getValue("email-from-name", PortletProps.get("admin.email.from.name")));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", preferences.getValue("email-from-address", PortletProps.get("admin.email.from.address")));

boolean emailArticleAddedEnabled = ParamUtil.getBoolean(request, "emailArticleAddedEnabled", GetterUtil.getBoolean(preferences.getValue("email-article-added-enabled", PortletProps.get("admin.email.article.added.enabled"))));
String emailArticleAddedSubject = ParamUtil.getString(request, "emailArticleAddedSubject", preferences.getValue("email-article-added-subject", StringUtil.read(getClass().getClassLoader(), PortletProps.get("admin.email.article.added.subject"))));
String emailArticleAddedBody = ParamUtil.getString(request, "emailArticleAddedBody", preferences.getValue("email-article-added-body", StringUtil.read(getClass().getClassLoader(), PortletProps.get("admin.email.article.added.body"))));

boolean emailArticleUpdatedEnabled = ParamUtil.getBoolean(request, "emailArticleUpdatedEnabled", GetterUtil.getBoolean(preferences.getValue("email-article-updated-enabled", PortletProps.get("admin.email.article.updated.enabled"))));
String emailArticleUpdatedSubject = ParamUtil.getString(request, "emailArticleUpdatedSubject", preferences.getValue("email-article-updated-subject", StringUtil.read(getClass().getClassLoader(), PortletProps.get("admin.email.article.updated.subject"))));
String emailArticleUpdatedBody = ParamUtil.getString(request, "emailArticleUpdatedBody", preferences.getValue("email-article-updated-body", StringUtil.read(getClass().getClassLoader(), PortletProps.get("admin.email.article.updated.body"))));

String editorParam = StringPool.BLANK;
String editorBody = StringPool.BLANK;

if (tabs2.equals("article-added-email")) {
	editorParam = "emailArticleAddedBody";
	editorBody = emailArticleAddedBody;
}
else if (tabs2.equals("article-updated-email")) {
	editorParam = "emailArticleUpdatedBody";
	editorBody = emailArticleUpdatedBody;
}
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="<%= tabs3 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="email-from,article-added-email,article-updated-email,display-settings,rss"
	param="tabs2"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />

	<!--
	SessionErrors are not propagated for plugins. See portlet_configuration\edit_configuration.jsp.
	-->

	<liferay-ui:error key="emailArticleAddedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailArticleAddedSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailArticleUpdatedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailArticleUpdatedSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("email-from") %>'>
				<aui:input cssClass="lfr-input-text-container" label="name" name="emailFromName" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="emailFromAddress" value="<%= emailFromAddress %>" />

				<div class="definition-of-terms">
					<h4><liferay-ui:message key="definition-of-terms" /></h4>

					<dl>
						<dt>
							[$ARTICLE_USER_ADDRESS$]
						</dt>
						<dd>
							<liferay-ui:message key="the-email-address-of-the-user-who-added-the-article" />
						</dd>
						<dt>
							[$ARTICLE_USER_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-user-who-added-the-article" />
						</dd>
						<dt>
							[$CATEGORY_TITLE$]
						</dt>
						<dd>
							<liferay-ui:message key="category.kb" />
						</dd>
						<dt>
							[$COMMUNITY_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-community-name-associated-with-the-article" />
						</dd>
						<dt>
							[$COMPANY_ID$]
						</dt>
						<dd>
							<liferay-ui:message key="the-company-id-associated-with-the-article" />
						</dd>
						<dt>
							[$COMPANY_MX$]
						</dt>
						<dd>
							<liferay-ui:message key="the-company-mx-associated-with-the-article" />
						</dd>
						<dt>
							[$COMPANY_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-company-name-associated-with-the-article" />
						</dd>
						<dt>
							[$PORTLET_NAME$]
						</dt>
						<dd>
							<%= PortalUtil.getPortletTitle(renderResponse) %>
						</dd>
					</dl>
				</div>
			</c:when>
			<c:when test='<%= tabs2.startsWith("article-") %>'>
				<c:choose>
					<c:when test='<%= tabs2.equals("article-added-email") %>'>
						<aui:input inlineLabel="left" label="enabled" name="emailArticleAddedEnabled" type="checkbox" value="<%= emailArticleAddedEnabled %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("article-updated-email") %>'>
						<aui:input inlineLabel="left" label="enabled" name="emailArticleUpdatedEnabled" type="checkbox" value="<%= emailArticleUpdatedEnabled %>" />
					</c:when>
				</c:choose>

				<c:choose>
					<c:when test='<%= tabs2.equals("article-added-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="emailArticleAddedSubject" value="<%= emailArticleAddedSubject %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("article-updated-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="emailArticleUpdatedSubject" value="<%= emailArticleUpdatedSubject %>" />
					</c:when>
				</c:choose>

				<aui:input cssClass="lfr-textarea-container" label="body" name="<%= editorParam %>" type="textarea" value="<%= editorBody %>" />

				<div class="definition-of-terms">
					<h4><liferay-ui:message key="definition-of-terms" /></h4>

					<dl>
						<dt>
							[$ARTICLE_ATTACHMENTS$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-attachments-file-names" />
						</dd>
						<dt>
							[$ARTICLE_CONTENT$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-content" />
						</dd>
						<dt>
							[$ARTICLE_CONTENT_DIFF$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-content-diff" />
						</dd>
						<dt>
							[$ARTICLE_TITLE$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-title" />
						</dd>
						<dt>
							[$ARTICLE_TITLE_DIFF$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-title-diff" />
						</dd>
						<dt>
							[$ARTICLE_URL$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-url" />
						</dd>
						<dt>
							[$ARTICLE_USER_ADDRESS$]
						</dt>
						<dd>
							<liferay-ui:message key="the-email-address-of-the-user-who-added-the-article" />
						</dd>
						<dt>
							[$ARTICLE_USER_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-user-who-added-the-article" />
						</dd>
						<dt>
							[$ARTICLE_VERSION$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-version" />
						</dd>
						<dt>
							[$CATEGORY_TITLE$]
						</dt>
						<dd>
							<liferay-ui:message key="category.kb" />
						</dd>
						<dt>
							[$COMMUNITY_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-community-name-associated-with-the-article" />
						</dd>
						<dt>
							[$COMPANY_ID$]
						</dt>
						<dd>
							<liferay-ui:message key="the-company-id-associated-with-the-article" />
						</dd>
						<dt>
							[$COMPANY_MX$]
						</dt>
						<dd>
							<liferay-ui:message key="the-company-mx-associated-with-the-article" />
						</dd>
						<dt>
							[$COMPANY_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-company-name-associated-with-the-article" />
						</dd>
						<dt>
							[$FROM_ADDRESS$]
						</dt>
						<dd>
							<%= HtmlUtil.escape(emailFromAddress) %>
						</dd>
						<dt>
							[$FROM_NAME$]
						</dt>
						<dd>
							<%= HtmlUtil.escape(emailFromName) %>
						</dd>
						<dt>
							[$PORTAL_URL$]
						</dt>
						<dd>
							<%= PortalUtil.getPortalURL(themeDisplay) %>
						</dd>
						<dt>
							[$PORTLET_NAME$]
						</dt>
						<dd>
							<%= PortalUtil.getPortletTitle(renderResponse) %>
						</dd>
						<dt>
							[$TO_ADDRESS$]
						</dt>
						<dd>
							<liferay-ui:message key="the-address-of-the-email-recipient" />
						</dd>
						<dt>
							[$TO_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-name-of-the-email-recipient" />
						</dd>
					</dl>
				</div>
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
							int[] pageDeltaValues = GetterUtil.getIntegerValues(PropsUtil.getArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES));

							Arrays.sort(pageDeltaValues);

							for (int pageDeltaValue : pageDeltaValues) {
								if (pageDeltaValue > SearchContainer.MAX_DELTA) {
									break;
								}
							%>

								<aui:option label="<%= pageDeltaValue %>" selected="<%= articlesDelta == pageDeltaValue %>" />

							<%
							}
							%>

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

						<aui:input inlineLabel="left" label="enable-categories" name="enableArticleAssetCategories" type="checkbox" value="<%= enableArticleAssetCategories %>" />

						<aui:input inlineLabel="left" label="enable-tags" name="enableArticleAssetTags" type="checkbox" value="<%= enableArticleAssetTags %>" />

						<aui:input inlineLabel="left" label="enable-ratings" name="enableArticleRatings" type="checkbox" value="<%= enableArticleRatings %>" />

						<aui:input inlineLabel="left" label="enable-comments" name="enableArticleComments" type="checkbox" value="<%= enableArticleComments %>" />

						<aui:input inlineLabel="left" label="enable-comment-ratings" name="enableArticleCommentRatings" type="checkbox" value="<%= enableArticleCommentRatings %>" />
					</c:when>
					<c:when test='<%= tabs3.equals("template") %>'>
						<aui:select label="maximum-items-to-display" name="templatesDelta">

							<%
							int[] pageDeltaValues = GetterUtil.getIntegerValues(PropsUtil.getArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES));

							Arrays.sort(pageDeltaValues);

							for (int pageDeltaValue : pageDeltaValues) {
								if (pageDeltaValue > SearchContainer.MAX_DELTA) {
									break;
								}
							%>

								<aui:option label="<%= pageDeltaValue %>" selected="<%= templatesDelta == pageDeltaValue %>" />

							<%
							}
							%>

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
				<aui:select label="maximum-items-to-display" name="rssDelta">
					<aui:option label="1" selected="<%= rssDelta == 1 %>" />
					<aui:option label="2" selected="<%= rssDelta == 2 %>" />
					<aui:option label="3" selected="<%= rssDelta == 3 %>" />
					<aui:option label="4" selected="<%= rssDelta == 4 %>" />
					<aui:option label="5" selected="<%= rssDelta == 5 %>" />
					<aui:option label="10" selected="<%= rssDelta == 10 %>" />
					<aui:option label="15" selected="<%= rssDelta == 15 %>" />
					<aui:option label="20" selected="<%= rssDelta == 20 %>" />
					<aui:option label="25" selected="<%= rssDelta == 25 %>" />
					<aui:option label="30" selected="<%= rssDelta == 30 %>" />
					<aui:option label="40" selected="<%= rssDelta == 40 %>" />
					<aui:option label="50" selected="<%= rssDelta == 50 %>" />
					<aui:option label="60" selected="<%= rssDelta == 60 %>" />
					<aui:option label="70" selected="<%= rssDelta == 70 %>" />
					<aui:option label="80" selected="<%= rssDelta == 80 %>" />
					<aui:option label="90" selected="<%= rssDelta == 90 %>" />
					<aui:option label="100" selected="<%= rssDelta == 100 %>" />
				</aui:select>

				<aui:select label="display-style" name="rssDisplayStyle">
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
					<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
				</aui:select>

				<aui:select label="format" name="rssFormat">
					<aui:option label="RSS 1.0" selected='<%= rssFormat.equals("rss10") %>' value="rss10" />
					<aui:option label="RSS 2.0" selected='<%= rssFormat.equals("rss20") %>' value="rss20" />
					<aui:option label="Atom 1.0" selected='<%= rssFormat.equals("atom10") %>' value="atom10" />
				</aui:select>
			</c:when>
		</c:choose>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>