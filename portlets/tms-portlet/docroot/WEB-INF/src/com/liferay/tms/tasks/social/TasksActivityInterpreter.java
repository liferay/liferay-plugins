/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.tms.tasks.social;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.service.TasksEntryLocalServiceUtil;

/**
 * <a href="TasksActivityInterpreter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 *
 */
public class TasksActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		TasksEntry tasksEntry = TasksEntryLocalServiceUtil.getTasksEntry(
			activity.getClassPK());

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		int activityType = activity.getType();

		// Link

		String link = StringPool.BLANK;

		// Title

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			Group group = GroupLocalServiceUtil.getGroup(activity.getGroupId());

			groupName = group.getDescriptiveName();
		}

		String titlePattern = null;

		if (activityType == TasksActivityKeys.ADD_TASK) {
			titlePattern = "activity-task-add-event";
		}
		else if (activityType == TasksActivityKeys.REOPEN_TASK) {
			titlePattern = "activity-task-reopened-event";
		}
		else if (activityType == TasksActivityKeys.RESOLVE_TASK) {
			titlePattern = "activity-task-resolved-event";
		}
		else if (activityType == TasksActivityKeys.UPDATE_TASK) {
			titlePattern = "activity-task-update-event";
		}

		if (Validator.isNotNull(groupName)) {
			titlePattern += "-in";
		}

		Object[] titleArguments = new Object[] {creatorUserName, groupName};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		StringBuilder sb = new StringBuilder();

		sb.append(cleanContent(tasksEntry.getTitle()));

		String body = sb.toString();

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		TasksEntry.class.getName()
	};

}