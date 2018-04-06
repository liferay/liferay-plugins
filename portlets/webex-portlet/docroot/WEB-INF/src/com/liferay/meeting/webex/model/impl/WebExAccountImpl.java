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

package com.liferay.meeting.webex.model.impl;

import com.liferay.meeting.MeetingContext;
import com.liferay.meeting.webex.model.WebExSite;
import com.liferay.meeting.webex.service.WebExSiteLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class WebExAccountImpl extends WebExAccountBaseImpl {

	public WebExAccountImpl() {
	}

	public MeetingContext getMeetingContext() throws PortalException {
		MeetingContext meetingContext = new MeetingContext();

		WebExSite webExSite = getWebExSite();

		meetingContext.setApiURL(webExSite.getApiURL());

		meetingContext.setLogin(getLogin());
		meetingContext.setName(webExSite.getName());
		meetingContext.setPartnerKey(webExSite.getPartnerKey());
		meetingContext.setPassword(getPassword());
		meetingContext.setSiteKey(webExSite.getSiteKey());

		return meetingContext;
	}

	public WebExSite getWebExSite() throws PortalException {
		return WebExSiteLocalServiceUtil.getWebExSite(getWebExSiteId());
	}

}