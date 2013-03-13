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

package com.liferay.polls.util;

import com.liferay.polls.NoSuchVoteException;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.polls.service.PollsVoteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Juan Fernández
 * @author Shepherd Ching
 */
public class PollsUtil {

	public static CategoryDataset getPollsVotesDataset(long pollsQuestionId)
		throws SystemException {

		DefaultCategoryDataset defaultCategoryDataset =
			new DefaultCategoryDataset();

		String seriesName = StringPool.BLANK;

		for (PollsChoice pollsChoice :
				PollsChoiceLocalServiceUtil.getPollsChoices(pollsQuestionId)) {

			Integer number = pollsChoice.getPollsVotesCount();

			defaultCategoryDataset.addValue(
				number, seriesName, pollsChoice.getName());
		}

		return defaultCategoryDataset;
	}

	public static boolean hasVoted(
			HttpServletRequest request, long pollsQuestionId)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.isSignedIn()) {
			try {
				PollsVoteLocalServiceUtil.getPollsVote(
					pollsQuestionId, themeDisplay.getUserId());
			}
			catch (NoSuchVoteException nsve) {
				return false;
			}

			return true;
		}

		String cookie = CookieKeys.getCookie(
			request, _getCookieName(pollsQuestionId));

		return GetterUtil.getBoolean(cookie);
	}

	public static void savePollsVote(
		HttpServletRequest request, HttpServletResponse response,
		long pollsQuestionId) {

		Cookie cookie = new Cookie(
			_getCookieName(pollsQuestionId), StringPool.TRUE);

		cookie.setMaxAge((int)(Time.WEEK / 1000));
		cookie.setPath(StringPool.SLASH);

		CookieKeys.addCookie(request, response, cookie);
	}

	public static void savePollsVote(
		PortletRequest portletRequest, PortletResponse portletResponse,
		long pollsQuestionId) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			portletResponse);

		savePollsVote(request, response, pollsQuestionId);
	}

	private static String _getCookieName(long pollsQuestionId) {
		return PollsQuestion.class.getName() + StringPool.POUND +
			pollsQuestionId;
	}

}