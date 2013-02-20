/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.polls.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.polls.NoSuchVoteException;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Brian Wing Shun Chan
 * @author Shepherd Ching
 */
public class PollsUtil {

	public static CategoryDataset getVotesDataset(long questionId)
		throws SystemException {

		DefaultCategoryDataset defaultCategoryDataset =
			new DefaultCategoryDataset();

		String seriesName = StringPool.BLANK;

		for (PollsChoice choice :
				PollsChoiceLocalServiceUtil.getChoices(questionId)) {

			Integer number = choice.getVotesCount();

			defaultCategoryDataset.addValue(
				number, seriesName, choice.getName());
		}

		return defaultCategoryDataset;
	}

	public static boolean hasVoted(HttpServletRequest request, long questionId)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.isSignedIn()) {
			try {
				PollsVoteLocalServiceUtil.getVote(
					questionId, themeDisplay.getUserId());
			}
			catch (NoSuchVoteException nsve) {
				return false;
			}

			return true;
		}

		String cookie = CookieKeys.getCookie(
			request, _getCookieName(questionId));

		return GetterUtil.getBoolean(cookie);
	}

	public static void saveVote(
		HttpServletRequest request, HttpServletResponse response,
		long questionId) {

		Cookie cookie = new Cookie(_getCookieName(questionId), StringPool.TRUE);

		cookie.setMaxAge((int)(Time.WEEK / 1000));
		cookie.setPath(StringPool.SLASH);

		CookieKeys.addCookie(request, response, cookie);
	}

	public static void saveVote(
		PortletRequest portletRequest, PortletResponse portletResponse,
		long questionId) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			portletResponse);

		saveVote(request, response, questionId);
	}

	private static String _getCookieName(long questionId) {
		return PollsQuestion.class.getName() + StringPool.POUND + questionId;
	}

}