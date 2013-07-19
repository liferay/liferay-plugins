/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.contacts.contactscenter.social;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialRequestInterpreter;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;

/**
 * @author Ryan Park
 */
public class ContactsCenterRequestInterpreter
	extends BaseSocialRequestInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialRequestFeedEntry doInterpret(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		int requestType = request.getType();

		// Title

		String title = StringPool.BLANK;

		if (requestType == SocialRelationConstants.TYPE_BI_CONNECTION) {
			String creatorUserName = getUserNameLink(
				request.getUserId(), themeDisplay);

			title = themeDisplay.translate(
				"request-social-networking-summary-add-connection",
				new Object[] {creatorUserName});
		}

		// Body

		String body = StringPool.BLANK;

		return new SocialRequestFeedEntry(title, body);
	}

	@Override
	protected boolean doProcessConfirmation(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			SocialRelationLocalServiceUtil.addRelation(
				request.getUserId(), request.getReceiverUserId(),
				request.getType());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return true;
	}

	private static final String[] _CLASS_NAMES = new String[] {
		User.class.getName()
	};

	private static Log _log = LogFactoryUtil.getLog(
		ContactsCenterRequestInterpreter.class);

}