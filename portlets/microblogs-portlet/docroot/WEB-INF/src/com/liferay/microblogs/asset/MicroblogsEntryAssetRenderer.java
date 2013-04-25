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

package com.liferay.microblogs.asset;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.util.WebKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Matthew Kong
 */
public class MicroblogsEntryAssetRenderer extends BaseAssetRenderer {

	public MicroblogsEntryAssetRenderer(MicroblogsEntry entry) {
		_entry = entry;
	}

	public String getClassName() {
		return MicroblogsEntry.class.getName();
	}

	public long getClassPK() {
		return _entry.getMicroblogsEntryId();
	}

	public long getGroupId() {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				_entry.getCompanyId());

			return group.getGroupId();
		}
		catch (Exception e) {
		}

		return 0;
	}

	public String getSummary(Locale locale) {
		return HtmlUtil.stripHtml(_entry.getContent());
	}

	public String getTitle(Locale locale) {
		return HtmlUtil.stripHtml(_entry.getContent());
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)liferayPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			long portletPlid = PortalUtil.getPlidFromPortletId(
				user.getGroupId(), true, "1_WAR_microblogsportlet");

			PortletURL portletURL = PortletURLFactoryUtil.create(
				liferayPortletRequest, "1_WAR_microblogsportlet", portletPlid,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/microblogs/view.jsp");

			long displayMicroblogsEntryId = _entry.getMicroblogsEntryId();

			if (_entry.getReceiverMicroblogsEntryId() > 0) {
				displayMicroblogsEntryId =_entry.getReceiverMicroblogsEntryId();
			}

			portletURL.setParameter(
				"displayMicroblogsEntryId",
				String.valueOf(displayMicroblogsEntryId));

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	public long getUserId() {
		return _entry.getUserId();
	}

	public String getUserName() {
		return _entry.getUserName();
	}

	public String getUuid() {
		return null;
	}

	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template) {

		return null;
	}

	private MicroblogsEntry _entry;

}