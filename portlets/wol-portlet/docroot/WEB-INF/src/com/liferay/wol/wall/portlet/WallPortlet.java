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

package com.liferay.wol.wall.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wol.model.WallEntry;
import com.liferay.wol.service.WallEntryLocalServiceUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * <a href="WallPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WallPortlet extends JSPPortlet {

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				req, ActionRequest.ACTION_NAME);

			if (actionName.equals("addWallEntry")) {
				addWallEntry(req);
			}
			else if (actionName.equals("deleteWallEntry")) {
				deleteWallEntry(req);
			}

			if (Validator.isNull(actionName)) {
				return;
			}

			if (SessionErrors.isEmpty(req)) {
				SessionMessages.add(req, "request_processed");
			}

			String redirect = ParamUtil.getString(req, "redirect");

			res.sendRedirect(redirect);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void addWallEntry(ActionRequest req) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getPortletGroupId());

		User user = null;

		if (group.isUser()) {
			user = UserLocalServiceUtil.getUserById(group.getClassPK());
		}
		else {
			return;
		}

		if ((themeDisplay.getUserId() != user.getUserId()) &&
			(!SocialRelationLocalServiceUtil.hasRelation(
				themeDisplay.getUserId(), user.getUserId(),
				SocialRelationConstants.TYPE_BI_FRIEND))) {

			return;
		}

		String comments = ParamUtil.getString(req, "comments");

		WallEntryLocalServiceUtil.addWallEntry(
			themeDisplay.getPortletGroupId(), themeDisplay.getUserId(),
			comments, themeDisplay);
	}

	protected void deleteWallEntry(ActionRequest req) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long wallEntryId = ParamUtil.getLong(req, "wallEntryId");

		WallEntry wallEntry = null;

		try {
			wallEntry = WallEntryLocalServiceUtil.getWallEntry(wallEntryId);
		}
		catch (Exception e) {
			return;
		}

		if (wallEntry.getGroupId() != themeDisplay.getPortletGroupId()) {
			return;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			themeDisplay.getPortletGroupId());

		User user = null;

		if (group.isUser()) {
			user = UserLocalServiceUtil.getUserById(group.getClassPK());
		}
		else {
			return;
		}

		if (!UserPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), user.getUserId(),
				ActionKeys.UPDATE)) {

			return;
		}

		try {
			WallEntryLocalServiceUtil.deleteWallEntry(wallEntryId);
		}
		catch (Exception e) {
		}
	}

}