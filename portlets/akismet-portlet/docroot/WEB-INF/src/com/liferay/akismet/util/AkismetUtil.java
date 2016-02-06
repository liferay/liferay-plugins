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

package com.liferay.akismet.util;

import com.liferay.akismet.model.AkismetData;
import com.liferay.akismet.service.AkismetDataLocalServiceUtil;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.wiki.util.comparator.PageVersionComparator;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Peter Shin
 */
public class AkismetUtil {

	public static Date getReportableTime(long companyId) {
		int reportableTime = PrefsPortletPropsUtil.getInteger(
			companyId, PortletPropsKeys.AKISMET_REPORTABLE_TIME);

		return new Date(
			System.currentTimeMillis() - (reportableTime * Time.DAY));
	}

	public static Date getRetainSpamTime() {
		return new Date(
			System.currentTimeMillis() -
				(PortletPropsValues.AKISMET_RETAIN_SPAM_TIME * Time.DAY));
	}

	public static WikiPage getWikiPage(
		long nodeId, String title, double version, boolean previous) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			WikiPage.class);

		Property summaryProperty = PropertyFactoryUtil.forName("summary");

		dynamicQuery.add(
			summaryProperty.ne(AkismetConstants.WIKI_PAGE_MARKED_AS_SPAM));
		dynamicQuery.add(
			summaryProperty.ne(AkismetConstants.WIKI_PAGE_PENDING_APPROVAL));

		Property nodeIdProperty = PropertyFactoryUtil.forName("nodeId");

		dynamicQuery.add(nodeIdProperty.eq(nodeId));

		Property titleProperty = PropertyFactoryUtil.forName("title");

		dynamicQuery.add(titleProperty.eq(title));

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(statusProperty.eq(WorkflowConstants.STATUS_APPROVED));

		Property versionProperty = PropertyFactoryUtil.forName("version");

		if (previous) {
			dynamicQuery.add(versionProperty.lt(version));
		}
		else {
			dynamicQuery.add(versionProperty.ge(version));
		}

		OrderFactoryUtil.addOrderByComparator(
			dynamicQuery, new PageVersionComparator());

		List<WikiPage> wikiPages = WikiPageLocalServiceUtil.dynamicQuery(
			dynamicQuery, 0, 1);

		if (wikiPages.isEmpty()) {
			return null;
		}

		return wikiPages.get(0);
	}

	public static boolean hasRequiredInfo(ServiceContext serviceContext) {
		Map<String, String> headers = serviceContext.getHeaders();

		if (headers == null) {
			return false;
		}

		String userAgent = headers.get(
			StringUtil.toLowerCase(HttpHeaders.USER_AGENT));

		if (Validator.isNull(userAgent)) {
			return false;
		}

		String userIP = serviceContext.getRemoteAddr();

		if (Validator.isNull(userIP)) {
			return false;
		}

		return true;
	}

	public static boolean isDiscussionsEnabled(long companyId) {
		String apiKey = PrefsPortletPropsUtil.getString(
			companyId, PortletPropsKeys.AKISMET_API_KEY);

		if (Validator.isNull(apiKey)) {
			return false;
		}

		return PrefsPortletPropsUtil.getBoolean(
			companyId, PortletPropsKeys.AKISMET_DISCUSSIONS_CHECK_ENABLED);
	}

	public static boolean isMessageBoardsEnabled(long companyId) {
		String apiKey = PrefsPortletPropsUtil.getString(
			companyId, PortletPropsKeys.AKISMET_API_KEY);

		if (Validator.isNull(apiKey)) {
			return false;
		}

		return PrefsPortletPropsUtil.getBoolean(
			companyId, PortletPropsKeys.AKISMET_MESSAGE_BOARDS_CHECK_ENABLED);
	}

	public static boolean isSpam(
			long userId, String content, AkismetData akismetData)
		throws PortalException {

		User user = UserLocalServiceUtil.getUser(userId);

		StringBundler sb = new StringBundler(5);

		sb.append(Http.HTTP_WITH_SLASH);
		sb.append(
			PrefsPortletPropsUtil.getString(
				user.getCompanyId(), PortletPropsKeys.AKISMET_API_KEY));
		sb.append(StringPool.PERIOD);
		sb.append(AkismetConstants.URL_REST);
		sb.append(AkismetConstants.PATH_CHECK_SPAM);

		String location = sb.toString();

		String response = _sendRequest(
			location, user.getCompanyId(), akismetData.getUserIP(),
			akismetData.getUserAgent(), akismetData.getReferrer(),
			akismetData.getPermalink(), akismetData.getType(),
			user.getFullName(), user.getEmailAddress(), content);

		if (Validator.isNull(response) || response.equals("invalid")) {
			_log.error("There was an issue with Akismet comment validation");

			return false;
		}
		else if (response.equals("true")) {
			if (_log.isDebugEnabled()) {
				_log.debug("Spam detected: " + akismetData.getPermalink());
			}

			return true;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Passed: " + akismetData.getPermalink());
		}

		return false;
	}

	public static boolean isWikiEnabled(long companyId) {
		String apiKey = PrefsPortletPropsUtil.getString(
			companyId, PortletPropsKeys.AKISMET_API_KEY);

		if (Validator.isNull(apiKey)) {
			return false;
		}

		return PrefsPortletPropsUtil.getBoolean(
			companyId, PortletPropsKeys.AKISMET_WIKI_CHECK_ENABLED);
	}

	public static void submitHam(
			long companyId, String ipAddress, String userAgent, String referrer,
			String permalink, String commentType, String userName,
			String emailAddress, String content)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug("Submitting message as ham: " + permalink);
		}

		StringBundler sb = new StringBundler(5);

		sb.append(Http.HTTP_WITH_SLASH);
		sb.append(
			PrefsPortletPropsUtil.getString(
				companyId, PortletPropsKeys.AKISMET_API_KEY));
		sb.append(StringPool.PERIOD);
		sb.append(AkismetConstants.URL_REST);
		sb.append(AkismetConstants.PATH_SUBMIT_HAM);

		String location = sb.toString();

		String response = _sendRequest(
			location, companyId, ipAddress, userAgent, referrer, permalink,
			commentType, userName, emailAddress, content);

		if (Validator.isNull(response)) {
			_log.error("There was an issue submitting message as ham");
		}
	}

	public static void submitHam(MBMessage mbMessage) throws PortalException {
		AkismetData akismetData = AkismetDataLocalServiceUtil.fetchAkismetData(
			MBMessage.class.getName(), mbMessage.getMessageId());

		if (akismetData == null) {
			return;
		}

		User user = UserLocalServiceUtil.getUser(mbMessage.getUserId());

		String content = mbMessage.getSubject() + "\n\n" + mbMessage.getBody();

		submitHam(
			mbMessage.getCompanyId(), akismetData.getUserIP(),
			akismetData.getUserAgent(), akismetData.getReferrer(),
			akismetData.getPermalink(), akismetData.getType(),
			user.getFullName(), user.getEmailAddress(), content);
	}

	public static void submitHam(WikiPage wikiPage) throws PortalException {
		AkismetData akismetData = AkismetDataLocalServiceUtil.fetchAkismetData(
			WikiPage.class.getName(), wikiPage.getPageId());

		if (akismetData == null) {
			return;
		}

		User user = UserLocalServiceUtil.getUser(wikiPage.getUserId());

		String content = wikiPage.getTitle() + "\n\n" + wikiPage.getContent();

		submitHam(
			wikiPage.getCompanyId(), akismetData.getUserIP(),
			akismetData.getUserAgent(), akismetData.getReferrer(),
			akismetData.getPermalink(), akismetData.getType(),
			user.getFullName(), user.getEmailAddress(), content);
	}

	public static void submitSpam(
			long companyId, String ipAddress, String userAgent, String referrer,
			String permalink, String commentType, String userName,
			String emailAddress, String content)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug("Submitting message as spam: " + permalink);
		}

		StringBundler sb = new StringBundler(5);

		sb.append(Http.HTTP_WITH_SLASH);
		sb.append(
			PrefsPortletPropsUtil.getString(
				companyId, PortletPropsKeys.AKISMET_API_KEY));
		sb.append(StringPool.PERIOD);
		sb.append(AkismetConstants.URL_REST);
		sb.append(AkismetConstants.PATH_SUBMIT_SPAM);

		String location = sb.toString();

		String response = _sendRequest(
			location, companyId, ipAddress, userAgent, referrer, permalink,
			commentType, userName, emailAddress, content);

		if (Validator.isNull(response)) {
			_log.error("There was an issue submitting message as spam");
		}
	}

	public static void submitSpam(MBMessage mbMessage) throws PortalException {
		AkismetData akismetData = AkismetDataLocalServiceUtil.fetchAkismetData(
			MBMessage.class.getName(), mbMessage.getMessageId());

		if (akismetData == null) {
			return;
		}

		User user = UserLocalServiceUtil.getUser(mbMessage.getUserId());

		String content = mbMessage.getSubject() + "\n\n" + mbMessage.getBody();

		submitSpam(
			mbMessage.getCompanyId(), akismetData.getUserIP(),
			akismetData.getUserAgent(), akismetData.getReferrer(),
			akismetData.getPermalink(), akismetData.getType(),
			user.getFullName(), user.getEmailAddress(), content);
	}

	public static void submitSpam(WikiPage wikiPage) throws PortalException {
		AkismetData akismetData = AkismetDataLocalServiceUtil.fetchAkismetData(
			WikiPage.class.getName(), wikiPage.getPageId());

		if (akismetData == null) {
			return;
		}

		User user = UserLocalServiceUtil.getUser(wikiPage.getUserId());

		String content = wikiPage.getTitle() + "\n\n" + wikiPage.getContent();

		submitSpam(
			wikiPage.getCompanyId(), akismetData.getUserIP(),
			akismetData.getUserAgent(), akismetData.getReferrer(),
			akismetData.getPermalink(), akismetData.getType(),
			user.getFullName(), user.getEmailAddress(), content);
	}

	public static boolean verifyApiKey(long companyId, String apiKey)
		throws PortalException {

		String location =
			Http.HTTP_WITH_SLASH + AkismetConstants.URL_REST +
				AkismetConstants.PATH_VERIFY;

		Map<String, String> parts = new HashMap<>();

		parts.put("blog", _getPortalURL(companyId));
		parts.put("key", apiKey);

		String response = _sendRequest(location, parts);

		if (response.equals("valid")) {
			return true;
		}
		else {
			return false;
		}
	}

	private static String _getPortalURL(long companyId) throws PortalException {
		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		return PortalUtil.getPortalURL(
			company.getVirtualHostname(), PortalUtil.getPortalPort(false),
			false);
	}

	private static String _sendRequest(
			String location, long companyId, String ipAddress, String userAgent,
			String referrer, String permalink, String commentType,
			String userName, String emailAddress, String content)
		throws PortalException {

		Map<String, String> parts = new HashMap<>();

		parts.put("blog", _getPortalURL(companyId));
		parts.put("comment_author", userName);
		parts.put("comment_author_email", emailAddress);
		parts.put("comment_content", content);
		parts.put("comment_type", commentType);
		parts.put("permalink", permalink);
		parts.put("referrer", referrer);
		parts.put("user_agent", userAgent);
		parts.put("user_ip", ipAddress);

		return _sendRequest(location, parts);
	}

	private static String _sendRequest(
		String location, Map<String, String> parts) {

		Http.Options options = new Http.Options();

		options.addHeader(HttpHeaders.USER_AGENT, "Akismet/2.5.3");
		options.setLocation(location);
		options.setParts(parts);
		options.setPost(true);

		try {
			return HttpUtil.URLtoString(options);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return StringPool.BLANK;
	}

	private static Log _log = LogFactoryUtil.getLog(AkismetUtil.class);

}