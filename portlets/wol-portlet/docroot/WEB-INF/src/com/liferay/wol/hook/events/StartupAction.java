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

package com.liferay.wol.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.wol.util.WOLConstants;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="StartupAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		setupExpando();
		setupLiferayUsers();
	}

	protected void setupExpando() throws Exception {
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(
				User.class.getName(), "WOL");
		}
		catch (DuplicateTableNameException dtne) {
			table = ExpandoTableLocalServiceUtil.getTable(
				User.class.getName(), "WOL");
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "jiraUserId",
				ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "sfUserId", ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "aboutMe", ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

	protected void setupLiferayUsers() throws Exception {
		List<User> users = UserLocalServiceUtil.getOrganizationUsers(
			WOLConstants.ORGANIZATION_LIFERAY_INC_ID);

		for (User user1 : users) {
			for (User user2 : users) {
				if (user1.getUserId() != user2.getUserId()) {
					SocialRelationLocalServiceUtil.addRelation(
						user1.getUserId(), user2.getUserId(),
						SocialRelationConstants.TYPE_BI_FRIEND);
				}
			}
		}
	}

	private static Log _log = LogFactory.getLog(StartupAction.class);

}