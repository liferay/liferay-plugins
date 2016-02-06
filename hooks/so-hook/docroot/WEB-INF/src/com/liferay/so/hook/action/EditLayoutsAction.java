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

package com.liferay.so.hook.action;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.so.util.LayoutUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Jonathan Lee
 */
public class EditLayoutsAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		boolean addUserDefaults = ParamUtil.getBoolean(
			actionRequest, "addUserDefaults");

		if (cmd.equals(Constants.ADD) && addUserDefaults) {
			addLayout(actionRequest);
		}
		else {
			originalStrutsPortletAction.processAction(
				portletConfig, actionRequest, actionResponse);
		}
	}

	protected void addLayout(ActionRequest actionRequest) throws Exception {
		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long parentLayoutId = ParamUtil.getLong(
			actionRequest, "parentLayoutId");
		String pageTitle = ParamUtil.getString(actionRequest, "pageTitle");

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Layout layout = LayoutUtil.addLayout(
			group, true, parentLayoutId, pageTitle, null, "1_column");

		LayoutUtil.updatePermissions(layout, false);
	}

}