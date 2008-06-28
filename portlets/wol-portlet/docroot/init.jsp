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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@ page import="com.liferay.portal.kernel.dao.search.TextSearchEntry" %>
<%@ page import="com.liferay.portal.kernel.servlet.ImageServletTokenUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.log.Log" %>
<%@ page import="com.liferay.portal.kernel.log.LogFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.messaging.DestinationNames" %>
<%@ page import="com.liferay.portal.kernel.messaging.MessageBusUtil" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ContentTypes" %>
<%@ page import="com.liferay.portal.kernel.util.DateFormats" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.Contact" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.model.Organization" %>
<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.OrganizationLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.permission.UserPermissionUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portal.util.comparator.UserLoginDateComparator" %>
<%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.messageboards.service.MBStatsUserLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.social.model.SocialActivity" %>
<%@ page import="com.liferay.portlet.social.model.SocialRelationConstants" %>
<%@ page import="com.liferay.portlet.social.model.SocialRequestConstants" %>
<%@ page import="com.liferay.portlet.social.service.SocialActivityLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.social.service.SocialRelationLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.social.service.SocialRequestLocalServiceUtil" %>
<%@ page import="com.liferay.util.RSSUtil" %>
<%@ page import="com.liferay.util.portlet.PortletProps" %>
<%@ page import="com.liferay.wol.NoSuchMeetupsEntryException" %>
<%@ page import="com.liferay.wol.NoSuchMeetupsRegistrationException" %>
<%@ page import="com.liferay.wol.NoSuchSVNRepositoryException" %>
<%@ page import="com.liferay.wol.friends.social.FriendsRequestKeys" %>
<%@ page import="com.liferay.wol.jira.util.JIRAConstants" %>
<%@ page import="com.liferay.wol.jira.util.JIRAUtil" %>
<%@ page import="com.liferay.wol.members.social.MembersRequestKeys" %>
<%@ page import="com.liferay.wol.meetups.util.MeetupsConstants" %>
<%@ page import="com.liferay.wol.model.JIRAIssue" %>
<%@ page import="com.liferay.wol.model.MeetupsEntry" %>
<%@ page import="com.liferay.wol.model.MeetupsRegistration" %>
<%@ page import="com.liferay.wol.model.SVNRepository" %>
<%@ page import="com.liferay.wol.model.SVNRevision" %>
<%@ page import="com.liferay.wol.model.WallEntry" %>
<%@ page import="com.liferay.wol.service.JIRAIssueLocalServiceUtil" %>
<%@ page import="com.liferay.wol.service.MeetupsEntryLocalServiceUtil" %>
<%@ page import="com.liferay.wol.service.MeetupsRegistrationLocalServiceUtil" %>
<%@ page import="com.liferay.wol.service.SVNRepositoryLocalServiceUtil" %>
<%@ page import="com.liferay.wol.service.SVNRevisionLocalServiceUtil" %>
<%@ page import="com.liferay.wol.service.WallEntryLocalServiceUtil" %>
<%@ page import="com.liferay.wol.svn.util.SVNConstants" %>
<%@ page import="com.liferay.wol.util.WOLConstants" %>

<%@ page import="com.sun.syndication.feed.synd.SyndContent" %>
<%@ page import="com.sun.syndication.feed.synd.SyndContentImpl" %>
<%@ page import="com.sun.syndication.feed.synd.SyndEntry" %>
<%@ page import="com.sun.syndication.feed.synd.SyndEntryImpl" %>
<%@ page import="com.sun.syndication.feed.synd.SyndFeed" %>
<%@ page import="com.sun.syndication.feed.synd.SyndFeedImpl" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.ActionRequest" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>

<%@ page import="org.json.JSONObject" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();

String currentURL = PortalUtil.getCurrentURL(request);

Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getPortletGroupId());

Organization organization = null;
User user2 = null;

if (group.isOrganization()) {
	organization = OrganizationLocalServiceUtil.getOrganization(group.getClassPK());
}
else if (group.isUser()) {
	user2 = UserLocalServiceUtil.getUserById(group.getClassPK());
}

DateFormat dateFormatDate = DateFormat.getDateInstance(DateFormat.LONG, locale);

dateFormatDate.setTimeZone(timeZone);

DateFormat dateFormatDateTime = DateFormats.getDateTime(locale, timeZone);

NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);

NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);
%>