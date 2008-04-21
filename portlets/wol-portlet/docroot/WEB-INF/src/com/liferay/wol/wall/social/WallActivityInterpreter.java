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

package com.liferay.wol.wall.social;

import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.wol.model.WallEntry;
import com.liferay.wol.service.WallEntryLocalServiceUtil;

/**
 * <a href="WallActivityInterpreter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WallActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		User receiverUser = UserLocalServiceUtil.getUserById(
			activity.getReceiverUserId());

		String activityType = activity.getType();

		// Title

		String title = StringPool.BLANK;

		if (activityType.equals(WallActivityKeys.ADD_ENTRY)) {
			title = themeDisplay.translate(
				"activity-wol-wall-add-entry",
				new Object[] {creatorUserName, receiverUserName});
		}

		// Body

		WallEntry wallEntry = WallEntryLocalServiceUtil.getWallEntry(
			activity.getClassPK());

		String wallEntryURL =
			themeDisplay.getURLPortal() +
				themeDisplay.getPathFriendlyURLPublic() + StringPool.SLASH +
					receiverUser.getScreenName() + "/home/-/wall/" +
						activity.getClassPK();

		StringMaker sm = new StringMaker();

		sm.append("<a href=\"");
		sm.append(wallEntryURL);
		sm.append("\">");
		sm.append(cleanContent(wallEntry.getComments()));
		sm.append("</a>");

		String body = sm.toString();

		return new SocialActivityFeedEntry(title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		WallEntry.class.getName()
	};

}