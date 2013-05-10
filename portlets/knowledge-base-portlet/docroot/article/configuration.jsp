<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/article/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", Validator.equals(portletResource, PortletKeys.KNOWLEDGE_BASE_ARTICLE_DEFAULT_INSTANCE) ? "display-settings" : "general");
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<%
String tabs2Names = Validator.equals(portletResource, PortletKeys.KNOWLEDGE_BASE_ARTICLE_DEFAULT_INSTANCE) ? "display-settings" : "general,display-settings";

if (PortalUtil.isRSSFeedsEnabled()) {
	tabs2Names += ",rss";
}
%>

<liferay-ui:tabs
	names="<%= tabs2Names %>"
	param="tabs2"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="preferences--resourcePrimKey--" type="hidden" value="<%= resourcePrimKey %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("general") %>'>
				<div class="kb-field-wrapper">
					<aui:field-wrapper label="article">

						<%
						KBArticle kbArticle = null;

						try {
							kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(resourcePrimKey, WorkflowConstants.STATUS_APPROVED);
						}
						catch (NoSuchArticleException nsae) {
						}
						%>

						<div id="<portlet:namespace />configurationKBArticle">
							<%= (kbArticle != null) ? kbArticle.getTitle() : StringPool.BLANK %>
						</div>

						<liferay-portlet:renderURL portletName="<%= portletResource %>" var="selectConfigurationKBArticleURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="mvcPath" value="/article/select_configuration_article.jsp" />
						</liferay-portlet:renderURL>

						<%
						String taglibOnClick = "var selectConfigurationKBArticleWindow = window.open('" + selectConfigurationKBArticleURL + "&" + HtmlUtil.escapeJS(PortalUtil.getPortletNamespace(portletResource)) + "&selResourcePrimKey=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "resourcePrimKey.value, 'selectConfigurationKBArticle', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectConfigurationKBArticleWindow.focus();";
						%>

						<div class="kb-edit-link">
							<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key="select-article" /> &raquo;</aui:a>
						</div>
					</aui:field-wrapper>
				</div>
			</c:when>
			<c:when test='<%= tabs2.equals("display-settings") %>'>
				<aui:input label="enable-description" name="preferences--enableKBArticleDescription--" type="checkbox" value="<%= enableKBArticleDescription %>" />

				<aui:input label="enable-ratings" name="preferences--enableKBArticleRatings--" type="checkbox" value="<%= enableKBArticleRatings %>" />

				<aui:input label="show-asset-entries" name="preferences--showKBArticleAssetEntries--" type="checkbox" value="<%= showKBArticleAssetEntries %>" />

				<aui:input label="enable-comments" name="preferences--enableKBArticleKBComments--" type="checkbox" value="<%= enableKBArticleKBComments %>" />

				<aui:input label="show-comments" name="preferences--showKBArticleKBComments--" type="checkbox" value="<%= showKBArticleKBComments %>" />

				<aui:input label="enable-view-count-increment" name="preferences--enableKBArticleViewCountIncrement--" type="checkbox" value="<%= enableKBArticleViewCountIncrement %>" />
			</c:when>
			<c:when test='<%= tabs2.equals("rss") %>'>
				<liferay-ui:rss-settings
					delta="<%= rssDelta %>"
					displayStyle="<%= rssDisplayStyle %>"
					enabled="<%= enableRSS %>"
					feedType="<%= rssFeedType %>"
				/>
			</c:when>
		</c:choose>

		<aui:button-row cssClass="kb-submit-buttons">
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<c:if test='<%= tabs2.equals("general") %>'>
	<aui:script>
		function <portlet:namespace />selectConfigurationKBArticle(resourcePrimKey, title) {
			document.<portlet:namespace />fm.<portlet:namespace />resourcePrimKey.value = resourcePrimKey;
			document.getElementById("<portlet:namespace />configurationKBArticle").innerHTML = title;
		}
	</aui:script>
</c:if>