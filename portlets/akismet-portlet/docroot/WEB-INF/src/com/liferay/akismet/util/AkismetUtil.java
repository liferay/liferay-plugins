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

package com.liferay.akismet.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Amos Fong
 */
public class AkismetUtil {

	public static boolean isEnabled(long companyId) throws SystemException {
		PortletPreferences preferences =
			PrefsPortletPropsUtil.getPortletPreferences(companyId);

		return GetterUtil.getBoolean(
			preferences.getValue("enabled", String.valueOf(Boolean.TRUE)));
	}

	public static boolean isSpam(
			long companyId, String ipAddress, String userAgent, String referrer,
			String permalink, String commentType, String userName,
			String emailAddress, String content)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(5);

		sb.append(Http.HTTP_WITH_SLASH);
		sb.append(
			PrefsPortletPropsUtil.getString(
				companyId, PortletPropsKeys.AKISMET_API_KEY));
		sb.append(StringPool.PERIOD);
		sb.append(AkismetConstants.REST_URL);
		sb.append(AkismetConstants.CHECK_SPAM_PATH);

		String location = sb.toString();

		Map<String, String> parts = new HashMap<String, String>();

		parts.put("blog", _getPortalURL(companyId));
		parts.put("comment_author", userName);
		parts.put("comment_author_email", emailAddress);
		parts.put("comment_content", content);
		parts.put("comment_type", commentType);
		parts.put("permalink", permalink);
		parts.put("referrer", referrer);
		parts.put("user_agent", userAgent);
		parts.put("user_ip", ipAddress);

		String response = _sendRequest(location, parts);

		if (Validator.isNull(response) || response.equals("invalid")) {
			_log.error("There was an issue with Akismet comment validation");

			return true;
		}
		else if (response.equals("true")) {
			return true;
		}

		return false;
	}

	public static boolean verifyApiKey(long companyId, String apiKey)
		throws PortalException, SystemException {

		String location =
			Http.HTTP_WITH_SLASH + AkismetConstants.REST_URL +
				AkismetConstants.VERIFY_PATH;

		Map<String, String> parts = new HashMap<String, String>();

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

	private static String _getPortalURL(long companyId)
		throws PortalException, SystemException {

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		return PortalUtil.getPortalURL(
			company.getVirtualHostname(), PortalUtil.getPortalPort(false),
			false);
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