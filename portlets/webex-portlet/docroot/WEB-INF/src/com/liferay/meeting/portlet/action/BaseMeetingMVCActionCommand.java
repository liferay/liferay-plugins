/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.meeting.portlet.action;

import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.service.WebExAccountServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Daniela Zapata Riesco
 */
public abstract class BaseMeetingMVCActionCommand extends BaseMVCActionCommand {

	protected MeetingContext getMeetingContext(ActionRequest actionRequest)
		throws Exception {

		long webExAccountId = ParamUtil.getLong(
			actionRequest, "webExAccountId");

		WebExAccount webExAccount = WebExAccountServiceUtil.getWebExAccount(
			webExAccountId);

		return webExAccount.getMeetingContext();
	}

}