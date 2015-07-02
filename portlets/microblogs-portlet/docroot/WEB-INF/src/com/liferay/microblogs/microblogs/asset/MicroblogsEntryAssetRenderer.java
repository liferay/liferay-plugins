/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.microblogs.asset;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.permission.MicroblogsEntryPermission;
import com.liferay.microblogs.util.WebKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.BaseJSPAssetRenderer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Matthew Kong
 */
public class MicroblogsEntryAssetRenderer extends BaseJSPAssetRenderer {

	public MicroblogsEntryAssetRenderer(MicroblogsEntry entry) {
		_entry = entry;
	}

	@Override
	public String getClassName() {
		return MicroblogsEntry.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getMicroblogsEntryId();
	}

	@Override
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

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/microblogs/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return _entry.getContent();
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getContent();
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

			long microblogsEntryId = _entry.getMicroblogsEntryId();

			if (_entry.getParentMicroblogsEntryId() > 0) {
				microblogsEntryId =_entry.getParentMicroblogsEntryId();
			}

			portletURL.setParameter(
				"parentMicroblogsEntryId", String.valueOf(microblogsEntryId));

			return portletURL.toString();
		}
		catch (Exception e) {
		}

		return null;
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		try {
			return MicroblogsEntryPermission.contains(
				permissionChecker, _entry, ActionKeys.VIEW);
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean include(
			HttpServletRequest request, HttpServletResponse response,
			String template)
		throws Exception {

		request.setAttribute(WebKeys.MICROBLOGS_ENTRY, _entry);

		return super.include(request, response, template);
	}

	private MicroblogsEntry _entry;

}