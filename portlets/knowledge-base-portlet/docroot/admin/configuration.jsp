<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/admin/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "email-from");
String tabs3 = ParamUtil.getString(request, "tabs3", "article");

String emailFromName = ParamUtil.getString(request, "emailFromName", AdminUtil.getEmailFromName(preferences));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", AdminUtil.getEmailFromAddress(preferences));

boolean emailArticleAddedEnabled = ParamUtil.getBoolean(request, "emailArticleAddedEnabled", AdminUtil.getEmailArticleAddedEnabled(preferences));
String emailArticleAddedSubject = ParamUtil.getString(request, "emailArticleAddedSubject", AdminUtil.getEmailArticleAddedSubject(preferences));
String emailArticleAddedBody = ParamUtil.getString(request, "emailArticleAddedBody", AdminUtil.getEmailArticleAddedBody(preferences));

boolean emailArticleUpdatedEnabled = ParamUtil.getBoolean(request, "emailArticleUpdatedEnabled", AdminUtil.getEmailArticleUpdatedEnabled(preferences));
String emailArticleUpdatedSubject = ParamUtil.getString(request, "emailArticleUpdatedSubject", AdminUtil.getEmailArticleUpdatedSubject(preferences));
String emailArticleUpdatedBody = ParamUtil.getString(request, "emailArticleUpdatedBody", AdminUtil.getEmailArticleUpdatedBody(preferences));

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
	names="email-from,article-added-email,article-updated-email,display-settings"
	param="tabs2"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />

	<liferay-ui:error key="emailArticleAddedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailArticleAddedSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailArticleUpdatedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailArticleUpdatedSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("email-from") %>'>
				<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" value="<%= emailFromAddress %>" />

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
					</dl>
				</div>
			</c:when>
			<c:when test='<%= tabs2.startsWith("article-") %>'>
				<c:choose>
					<c:when test='<%= tabs2.equals("article-added-email") %>'>
						<aui:input inlineLabel="left" label="enabled" name="preferences--emailArticleAddedEnabled--" type="checkbox" value="<%= emailArticleAddedEnabled %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("article-updated-email") %>'>
						<aui:input inlineLabel="left" label="enabled" name="preferences--emailArticleUpdatedEnabled--" type="checkbox" value="<%= emailArticleUpdatedEnabled %>" />
					</c:when>
				</c:choose>

				<c:choose>
					<c:when test='<%= tabs2.equals("article-added-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailArticleAddedSubject--" value="<%= emailArticleAddedSubject %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("article-updated-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailArticleUpdatedSubject--" value="<%= emailArticleUpdatedSubject %>" />
					</c:when>
				</c:choose>

				<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "preferences--".concat(editorParam).concat("--") %>' type="textarea" value="<%= editorBody %>" />

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
						<aui:select name="preferences--childArticlesDisplayStyle--">
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" />
							<aui:option label="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" selected="<%= childArticlesDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" />
						</aui:select>

						<aui:input inlineLabel="left" label="enable-description" name="preferences--enableArticleDescription--" type="checkbox" value="<%= enableArticleDescription %>" />

						<aui:input inlineLabel="left" label="enable-categories" name="preferences--enableArticleAssetCategories--" type="checkbox" value="<%= enableArticleAssetCategories %>" />

						<aui:input inlineLabel="left" label="enable-tags" name="preferences--enableArticleAssetTags--" type="checkbox" value="<%= enableArticleAssetTags %>" />

						<aui:input inlineLabel="left" label="enable-ratings" name="preferences--enableArticleRatings--" type="checkbox" value="<%= enableArticleRatings %>" />

						<aui:input inlineLabel="left" label="enable-comments" name="preferences--enableArticleComments--" type="checkbox" value="<%= enableArticleComments %>" />

						<aui:input inlineLabel="left" label="show-comments" name="preferences--showArticleComments--" type="checkbox" value="<%= showArticleComments %>" />
					</c:when>
					<c:when test='<%= tabs3.equals("template") %>'>
						<aui:input inlineLabel="left" label="enable-description" name="preferences--enableTemplateDescription--" type="checkbox" value="<%= enableTemplateDescription %>" />

						<aui:input inlineLabel="left" label="enable-comments" name="preferences--enableTemplateComments--" type="checkbox" value="<%= enableTemplateComments %>" />

						<aui:input inlineLabel="left" label="show-comments" name="preferences--showTemplateComments--" type="checkbox" value="<%= showTemplateComments %>" />
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>