<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
String tabs2 = ParamUtil.getString(request, "tabs2", "general");
String tabs3 = ParamUtil.getString(request, "tabs3", "article");

String emailFromName = ParamUtil.getString(request, "emailFromName", AdminUtil.getEmailFromName(portletPreferences, company.getCompanyId()));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", AdminUtil.getEmailFromAddress(portletPreferences, company.getCompanyId()));

boolean emailKBArticleAddedEnabled = ParamUtil.getBoolean(request, "emailKBArticleAddedEnabled", AdminUtil.getEmailKBArticleAddedEnabled(portletPreferences));
String emailKBArticleAddedSubject = ParamUtil.getString(request, "emailKBArticleAddedSubject", AdminUtil.getEmailKBArticleAddedSubject(portletPreferences));
String emailKBArticleAddedBody = ParamUtil.getString(request, "emailKBArticleAddedBody", AdminUtil.getEmailKBArticleAddedBody(portletPreferences));

boolean emailKBArticleSuggestionInProgressEnabled = ParamUtil.getBoolean(request, "emailKBArticleSuggestionInProgressEnabled", AdminUtil.getEmailKBArticleSuggestionInProgressEnabled(portletPreferences));
String emailKBArticleSuggestionInProgressSubject = ParamUtil.getString(request, "emailKBArticleSuggestionInProgressSubject", AdminUtil.getEmailKBArticleSuggestionInProgressSubject(portletPreferences));
String emailKBArticleSuggestionInProgressBody = ParamUtil.getString(request, "emailKBArticleSuggestionInProgressBody", AdminUtil.getEmailKBArticleSuggestionInProgressBody(portletPreferences));

boolean emailKBArticleSuggestionReceivedEnabled = ParamUtil.getBoolean(request, "emailKBArticleSuggestionReceivedEnabled", AdminUtil.getEmailKBArticleSuggestionReceivedEnabled(portletPreferences));
String emailKBArticleSuggestionReceivedSubject = ParamUtil.getString(request, "emailKBArticleSuggestionReceivedSubject", AdminUtil.getEmailKBArticleSuggestionReceivedSubject(portletPreferences));
String emailKBArticleSuggestionReceivedBody = ParamUtil.getString(request, "emailKBArticleSuggestionReceivedBody", AdminUtil.getEmailKBArticleSuggestionReceivedBody(portletPreferences));

boolean emailKBArticleSuggestionResolvedEnabled = ParamUtil.getBoolean(request, "emailKBArticleSuggestionResolvedEnabled", AdminUtil.getEmailKBArticleSuggestionResolvedEnabled(portletPreferences));
String emailKBArticleSuggestionResolvedSubject = ParamUtil.getString(request, "emailKBArticleSuggestionResolvedSubject", AdminUtil.getEmailKBArticleSuggestionResolvedSubject(portletPreferences));
String emailKBArticleSuggestionResolvedBody = ParamUtil.getString(request, "emailKBArticleSuggestionResolvedBody", AdminUtil.getEmailKBArticleSuggestionResolvedBody(portletPreferences));

boolean emailKBArticleUpdatedEnabled = ParamUtil.getBoolean(request, "emailKBArticleUpdatedEnabled", AdminUtil.getEmailKBArticleUpdatedEnabled(portletPreferences));
String emailKBArticleUpdatedSubject = ParamUtil.getString(request, "emailKBArticleUpdatedSubject", AdminUtil.getEmailKBArticleUpdatedSubject(portletPreferences));
String emailKBArticleUpdatedBody = ParamUtil.getString(request, "emailKBArticleUpdatedBody", AdminUtil.getEmailKBArticleUpdatedBody(portletPreferences));

String editorParam = StringPool.BLANK;
String editorBody = StringPool.BLANK;

if (tabs2.equals("article-added-email")) {
	editorParam = "emailKBArticleAddedBody";
	editorBody = emailKBArticleAddedBody;
}
else if (tabs2.equals("article-updated-email")) {
	editorParam = "emailKBArticleUpdatedBody";
	editorBody = emailKBArticleUpdatedBody;
}
else if (tabs2.equals("suggestion-in-progress-email")) {
	editorParam = "emailKBArticleSuggestionInProgressBody";
	editorBody = emailKBArticleSuggestionInProgressBody;
}
else if (tabs2.equals("suggestion-received-email")) {
	editorParam = "emailKBArticleSuggestionReceivedBody";
	editorBody = emailKBArticleSuggestionReceivedBody;
}
else if (tabs2.equals("suggestion-resolved-email")) {
	editorParam = "emailKBArticleSuggestionResolvedBody";
	editorBody = emailKBArticleSuggestionResolvedBody;
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="<%= tabs3 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="general,email-from,article-added-email,article-updated-email,suggestion-received-email,suggestion-in-progress-email,suggestion-resolved-email,display-settings"
	param="tabs2"
	url="<%= configurationRenderURL %>"
/>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="tabs3" type="hidden" value="<%= tabs3 %>" />

	<liferay-ui:error key="emailKBArticleAddedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailKBArticleAddedSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailKBArticleUpdatedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailKBArticleUpdatedSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("general") %>'>
				<div class="kb-field-wrapper">
					<aui:field-wrapper label="order-by">
						<aui:select inlineField="<%= true %>" label="" name="preferences--kbArticlesOrderByCol--">
							<aui:option label="author" selected='<%= kbArticlesOrderByCol.equals("user-name") %>' value="user-name" />
							<aui:option label="create-date" selected='<%= kbArticlesOrderByCol.equals("create-date") %>' />
							<aui:option label="modified-date" selected='<%= kbArticlesOrderByCol.equals("modified-date") %>' />
							<aui:option label="priority" selected='<%= kbArticlesOrderByCol.equals("priority") %>' />
							<aui:option label="status" selected='<%= kbArticlesOrderByCol.equals("status") %>' />
							<aui:option label="title" selected='<%= kbArticlesOrderByCol.equals("title") %>' />
							<aui:option label="view-count" selected='<%= kbArticlesOrderByCol.equals("view-count") %>' />
						</aui:select>

						<aui:select inlineField="<%= true %>" label="" name="preferences--kbArticlesOrderByType--">
							<aui:option label="ascending" selected='<%= kbArticlesOrderByType.equals("asc") %>' value="asc" />
							<aui:option label="descending" selected='<%= kbArticlesOrderByType.equals("desc") %>' value="desc" />
						</aui:select>
					</aui:field-wrapper>
				</div>
			</c:when>
			<c:when test='<%= tabs2.equals("email-from") %>'>
				<aui:input label="name" name="preferences--emailFromName--" value="<%= emailFromName %>" wrapperCssClass="lfr-input-text-container" />

				<aui:input label="address" name="preferences--emailFromAddress--" value="<%= emailFromAddress %>" wrapperCssClass="lfr-input-text-container" />

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
							[$SITE_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-site-name-associated-with-the-article" />
						</dd>
					</dl>
				</div>
			</c:when>
			<c:when test='<%= tabs2.startsWith("article-") %>'>
				<c:choose>
					<c:when test='<%= tabs2.equals("article-added-email") %>'>
						<aui:input label="enabled" name="preferences--emailKBArticleAddedEnabled--" type="checkbox" value="<%= emailKBArticleAddedEnabled %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("article-updated-email") %>'>
						<aui:input label="enabled" name="preferences--emailKBArticleUpdatedEnabled--" type="checkbox" value="<%= emailKBArticleUpdatedEnabled %>" />
					</c:when>
				</c:choose>

				<c:choose>
					<c:when test='<%= tabs2.equals("article-added-email") %>'>
						<aui:input label="subject" name="preferences--emailKBArticleAddedSubject--" value="<%= emailKBArticleAddedSubject %>" wrapperCssClass="lfr-input-text-container" />
					</c:when>
					<c:when test='<%= tabs2.equals("article-updated-email") %>'>
						<aui:input label="subject" name="preferences--emailKBArticleUpdatedSubject--" value="<%= emailKBArticleUpdatedSubject %>" wrapperCssClass="lfr-input-text-container" />
					</c:when>
				</c:choose>

				<aui:input label="body" name='<%= "preferences--".concat(editorParam).concat("--") %>' type="textarea" value="<%= editorBody %>" wrapperCssClass="lfr-textarea-container" />

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
							[$SITE_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-site-name-associated-with-the-article" />
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
			<c:when test='<%= tabs2.startsWith("suggestion-") %>'>
				<c:choose>
					<c:when test='<%= tabs2.equals("suggestion-in-progress-email") %>'>
						<aui:input label="enabled" name="preferences--emailKBArticleSuggestionInProgressEnabled--" type="checkbox" value="<%= emailKBArticleSuggestionInProgressEnabled %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("suggestion-received-email") %>'>
						<aui:input label="enabled" name="preferences--emailKBArticleSuggestionReceivedEnabled--" type="checkbox" value="<%= emailKBArticleSuggestionReceivedEnabled %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("suggestion-resolved-email") %>'>
						<aui:input label="enabled" name="preferences--emailKBArticleSuggestionResolvedEnabled--" type="checkbox" value="<%= emailKBArticleSuggestionResolvedEnabled %>" />
					</c:when>
				</c:choose>

				<c:choose>
					<c:when test='<%= tabs2.equals("suggestion-in-progress-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailKBArticleSuggestionInProgressSubject--" value="<%= emailKBArticleSuggestionInProgressSubject %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("suggestion-received-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailKBArticleSuggestionReceivedSubject--" value="<%= emailKBArticleSuggestionReceivedSubject %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("suggestion-resolved-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailKBArticleSuggestionResolvedSubject--" value="<%= emailKBArticleSuggestionResolvedSubject %>" />
					</c:when>
				</c:choose>

				<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "preferences--".concat(editorParam).concat("--") %>' type="textarea" value="<%= editorBody %>" />

				<div class="definition-of-terms">
					<h4><liferay-ui:message key="definition-of-terms" /></h4>

					<dl>
						<dt>
							[$ARTICLE_CONTENT$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-content" />
						</dd>
						<dt>
							[$ARTICLE_TITLE$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-title" />
						</dd>
						<dt>
							[$ARTICLE_URL$]
						</dt>
						<dd>
							<liferay-ui:message key="the-article-url" />
						</dd>
						<dt>
							[$COMMENT_CONTENT$]
						</dt>
						<dd>
							<liferay-ui:message key="the-comment-content" />
						</dd>
						<dt>
							[$COMMENT_CREATE_DATE$]
						</dt>
						<dd>
							<liferay-ui:message key="the-comment-create-date" />
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
					url="<%= configurationRenderURL %>"
				/>

				<c:choose>
					<c:when test='<%= tabs3.equals("article") %>'>
						<aui:input label="enable-description" name="preferences--enableKBArticleDescription--" type="checkbox" value="<%= enableKBArticleDescription %>" />

						<aui:input label="enable-ratings" name="preferences--enableKBArticleRatings--" type="checkbox" value="<%= enableKBArticleRatings %>" />

						<div class="kb-ratings-type" id="<portlet:namespace />ratingsType">
							<aui:input checked='<%= kbArticleRatingsType.equals("stars") %>' label="use-star-ratings" name="preferences--kbArticleRatingsType--" type="radio" value="stars" />
							<aui:input checked='<%= kbArticleRatingsType.equals("thumbs") %>' label="use-thumbs-up-thumbs-down" name="preferences--kbArticleRatingsType--" type="radio" value="thumbs" />
						</div>

						<aui:input label="show-asset-entries" name="preferences--showKBArticleAssetEntries--" type="checkbox" value="<%= showKBArticleAssetEntries %>" />

						<aui:input label="enable-related-assets" name="preferences--enableKBArticleAssetLinks--" type="checkbox" value="<%= enableKBArticleAssetLinks %>" />

						<aui:input label="enable-view-count-increment" name="preferences--enableKBArticleViewCountIncrement--" type="checkbox" value="<%= enableKBArticleViewCountIncrement %>" />
					</c:when>
					<c:when test='<%= tabs3.equals("template") %>'>
						<aui:input label="enable-suggestions" name="preferences--enableKBTemplateKBComments--" type="checkbox" value="<%= enableKBTemplateKBComments %>" />

						<aui:input label="show-suggestions" name="preferences--showKBTemplateKBComments--" type="checkbox" value="<%= showKBTemplateKBComments %>" />
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<c:if test='<%= tabs2.equals("display-settings") && tabs3.equals("article") %>'>
	<aui:script>
		Liferay.Util.toggleBoxes('<portlet:namespace />enableKBArticleRatingsCheckbox', '<portlet:namespace />ratingsType');
	</aui:script>
</c:if>