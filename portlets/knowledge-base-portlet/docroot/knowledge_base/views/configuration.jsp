<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/knowledge_base/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String tabs2 = ParamUtil.getString(request, "tabs2", "email-notifications");
String tabs3 = ParamUtil.getString(request, "tabs3", "general");

String[] conversions = DocumentConversionUtil.getConversions("html");

String[] extensions = prefs.getValues("extensions", new String[] {"pdf"});
%>

<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="portletURL" portletConfiguration="true">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="tabs3" value="<%= tabs3 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />tabs2" type="hidden" value="<%= HtmlUtil.escape(tabs2) %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />

<liferay-ui:tabs
	names="email-notifications,rss,export-settings"
	param="tabs2"
	url="<%= portletURL %>"
/>

<c:choose>
	<c:when test='<%= tabs2.equals("email-notifications") %>'>
		<script type="text/javascript">

			<%
			String emailFromName = ParamUtil.getString(request, "emailFromName", PrefsPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_FROM_NAME));
			String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", PrefsPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_FROM_ADDRESS));

			String emailArticleAddedSubject = ParamUtil.getString(request, "emailArticleAddedSubject", PortletPrefsPropsUtil.getContent(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_SUBJECT, getClass().getClassLoader()));
			String emailArticleAddedBody = ParamUtil.getString(request, "emailArticleAddedBody", PortletPrefsPropsUtil.getContent(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_BODY, getClass().getClassLoader()));

			String emailArticleUpdatedSubject = ParamUtil.getString(request, "emailArticleUpdatedSubject", PortletPrefsPropsUtil.getContent(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_SUBJECT, getClass().getClassLoader()));
			String emailArticleUpdatedBody = ParamUtil.getString(request, "emailArticleUpdatedBody", PortletPrefsPropsUtil.getContent(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_BODY, getClass().getClassLoader()));

			String editorParam = "";
			String editorContent = "";

			if (tabs3.equals("article-added-notification")) {
				editorParam = "emailArticleAddedBody";
				editorContent = emailArticleAddedBody;
			}
			else if (tabs3.equals("article-updated-notification")) {
				editorParam = "emailArticleUpdatedBody";
				editorContent = emailArticleUpdatedBody;
			}
			%>

			function <portlet:namespace />initEditor() {
				return "<%= UnicodeFormatter.toString(editorContent) %>";
			}

			function <portlet:namespace />saveEmailSettings() {
				if (<%= tabs3.endsWith("-notification") %>) {
					document.<portlet:namespace />fm.<portlet:namespace /><%= editorParam %>.value = window.<portlet:namespace />editor.getHTML();
				}

				submitForm(document.<portlet:namespace />fm);
			}
		</script>

		<liferay-ui:tabs
			names="general,article-added-notification,article-updated-notification"
			param="tabs3"
			url="<%= portletURL %>"
		/>

		<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
		<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />
		<liferay-ui:error key="emailArticleAddedBody" message="please-enter-a-valid-body" />
		<liferay-ui:error key="emailArticleAddedSubject" message="please-enter-a-valid-subject" />
		<liferay-ui:error key="emailArticleUpdatedBody" message="please-enter-a-valid-body" />
		<liferay-ui:error key="emailArticleUpdatedSubject" message="please-enter-a-valid-subject" />

		<c:choose>
			<c:when test='<%= tabs3.endsWith("-notification") %>'>
				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="enabled" />
					</td>
					<td>
						<c:choose>
							<c:when test='<%= tabs3.equals("article-added-notification") %>'>
								<liferay-ui:input-checkbox param="emailArticleAddedEnabled" defaultValue="<%= PortletPrefsPropsUtil.getBoolean(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_ARTICLE_ADDED_ENABLED) %>" />
							</c:when>
							<c:when test='<%= tabs3.equals("article-updated-notification") %>'>
								<liferay-ui:input-checkbox param="emailArticleUpdatedEnabled" defaultValue="<%= PortletPrefsPropsUtil.getBoolean(company.getCompanyId(), PortletPropsKeys.ADMIN_EMAIL_ARTICLE_UPDATED_ENABLED) %>" />
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br />
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="subject" />
					</td>
					<td>
						<c:choose>
							<c:when test='<%= tabs3.equals("article-added-notification") %>'>
								<input class="lfr-input-text" name="<portlet:namespace />emailArticleAddedSubject" type="text" value="<%= emailArticleAddedSubject %>" />
							</c:when>
							<c:when test='<%= tabs3.equals("article-updated-notification") %>'>
								<input class="lfr-input-text" name="<portlet:namespace />emailArticleUpdatedSubject" type="text" value="<%= emailArticleUpdatedSubject %>" />
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br />
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="body" />
					</td>
					<td>
						<liferay-ui:input-editor editorImpl="<%= null %>" />

						<input name="<portlet:namespace /><%= editorParam %>" type="hidden" value="" />
					</td>
				</tr>
				</table>

				<br />

				<b><liferay-ui:message key="definition-of-terms" /></b>

				<br /><br />

				<table class="lfr-table">
				<tr>
					<td>
						<b>[$ARTICLE_CONTENT$]</b>
					</td>
					<td>
						The article content
					</td>
				</tr>
				<tr>
					<td>
						<b>[$ARTICLE_DESCRIPTION$]</b>
					</td>
					<td>
						The article description
					</td>
				</tr>
				<tr>
					<td>
						<b>[$ARTICLE_TITLE$]</b>
					</td>
					<td>
						The article title
					</td>
				</tr>
				<tr>
					<td>
						<b>[$ARTICLE_URL$]</b>
					</td>
					<td>
						The article URL
					</td>
				</tr>
				<tr>
					<td>
						<b>[$COMMUNITY_NAME$]</b>
					</td>
					<td>
						The community name
					</td>
				</tr>
				<tr>
					<td>
						<b>[$COMPANY_NAME$]</b>
					</td>
					<td>
						<%= company.getName() %>
					</td>
				</tr>
				<tr>
					<td>
						<b>[$FROM_ADDRESS$]</b>
					</td>
					<td>
						<%= emailFromAddress %>
					</td>
				</tr>
				<tr>
					<td>
						<b>[$FROM_NAME$]</b>
					</td>
					<td>
						<%= emailFromName %>
					</td>
				</tr>
				<tr>
					<td>
						<b>[$PORTAL_URL$]</b>
					</td>
					<td>
						<%= company.getVirtualHost() %>
					</td>
				</tr>
				<tr>
					<td>
						<b>[$PORTLET_NAME$]</b>
					</td>
					<td>
						Knowledge Base Portlet
					</td>
				</tr>
				<tr>
					<td>
						<b>[$TO_ADDRESS$]</b>
					</td>
					<td>
						The address of the email recipient
					</td>
				</tr>
				<tr>
					<td>
						<b>[$TO_NAME$]</b>
					</td>
					<td>
						The name of the email recipient
					</td>
				</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table class="lfr-table">
				<tr>
					<td>
						<liferay-ui:message key="name" />
					</td>
					<td>
						<input class="lfr-input-text" name="<portlet:namespace />emailFromName" type="text" value="<%= emailFromName %>" />
					</td>
				</tr>
				<tr>
					<td>
						<liferay-ui:message key="address" />
					</td>
					<td>
						<input class="lfr-input-text" name="<portlet:namespace />emailFromAddress" type="text" value="<%= emailFromAddress %>" />
					</td>
				</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test='<%= tabs2.equals("rss") %>'>
		<fieldset>
			<legend><liferay-ui:message key="rss" /></legend>

			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="rss" />
				</td>
				<td>
					<table class="lfr-table">
						<tr valign="middle">

							<%
							for (String rssType : RSSUtil.RSS_TYPES) {
							%>

								<td>
									<input type="checkbox" <%= ArrayUtil.contains(rssTypes, rssType) ? "checked":"" %> name="<portlet:namespace />rssTypes" value="<%= rssType %>" />
								</td>
								<td>
									<%= rssType %>
								</td>

							<%
							}
							%>

						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="maximum-items-to-display" />
				</td>
				<td>
					<select name="<portlet:namespace />rssMaxItems">
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_10) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_10 %>"><%= RSSUtil.MAX_ITEMS_10 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_20) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_20 %>"><%= RSSUtil.MAX_ITEMS_20 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_30) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_30 %>"><%= RSSUtil.MAX_ITEMS_30 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_50) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_50 %>"><%= RSSUtil.MAX_ITEMS_50 %></option>
						<option <%= (rssMaxItems == RSSUtil.MAX_ITEMS_100) ? "selected" : "" %> value="<%= RSSUtil.MAX_ITEMS_100 %>"><%= RSSUtil.MAX_ITEMS_100 %></option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="display-style" />
				</td>
				<td>
					<select name="<portlet:namespace />rssDisplayStyle">
						<option <%= (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT)) ? "selected" : "" %> value="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>"><liferay-ui:message key="full-content" /></option>
						<option <%= (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT)) ? "selected" : "" %> value="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>"><liferay-ui:message key="abstract" /></option>
						<option <%= (rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE)) ? "selected" : "" %> value="<%= RSSUtil.DISPLAY_STYLE_TITLE %>"><liferay-ui:message key="title" /></option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<liferay-ui:message key="abstract-length" />
				</td>
				<td>
					<select name="<portlet:namespace />rssAbstractLength">
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_50) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_50 %>"><%= RSSUtil.ABSTRACT_LENGTH_50 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_100) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_100 %>"><%= RSSUtil.ABSTRACT_LENGTH_100 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_200) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_200 %>"><%= RSSUtil.ABSTRACT_LENGTH_200 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_300) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_300 %>"><%= RSSUtil.ABSTRACT_LENGTH_300 %></option>
						<option <%= (rssAbstractLength == RSSUtil.ABSTRACT_LENGTH_500) ? "selected" : "" %> value="<%= RSSUtil.ABSTRACT_LENGTH_500 %>"><%= RSSUtil.ABSTRACT_LENGTH_500 %></option>
					</select>
				</td>
			</tr>
			</table>
		</fieldset>
	</c:when>
	<c:when test='<%= tabs2.equals("export-settings") %>'>
		<fieldset>
			<legend><liferay-ui:message key="document-conversion" /></legend>

			<liferay-ui:message key="enabling-openoffice-integration-provides-document-conversion-functionality" />

			<br /><br />

			<table class="lfr-table">
				<tr valign="middle">

					<%
					for (String conversion : conversions) {
					%>

						<td>
							<input type="checkbox" <%= ArrayUtil.contains(extensions, conversion) ? "checked": "" %> name="<portlet:namespace />extensions" value="<%= conversion %>" />
						</td>
						<td>
							<%= conversion.toUpperCase() %>
						</td>

					<%
					}
					%>

				</tr>
			</table>
		</fieldset>
	</c:when>
</c:choose>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick='<%= tabs2.equals("email-notifications") ? "<portlet:namespace />saveEmailSettings();" : "submitForm(document.<portlet:namespace />fm);"  %>' />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="document.location = '<%= HtmlUtil.escape(redirect) %>'" />

</form>