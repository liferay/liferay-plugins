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

package com.liferay.shortlink.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Time;
import com.liferay.shortlink.service.ShortLinkEntryLocalServiceUtil;
import com.liferay.shortlink.util.PortletPropsValues;

import java.util.Date;

/**
 * @author Miroslav Ligas
 */
public class CleanUpShortLinkEntryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		if (ShortLinkEntryLocalServiceUtil.getShortLinkEntriesCount() >
				PortletPropsValues.SHORT_URL_CLEAN_UP_THRESHOLD) {

			Date beforeDate = new Date(
				System.currentTimeMillis() - _SHORT_URL_LIFETIME);

			ShortLinkEntryLocalServiceUtil.deleteShortLinkEntries(beforeDate);
		}
	}

	private static final long _SHORT_URL_LIFETIME =
		PortletPropsValues.SHORT_URL_LIFETIME * Time.MONTH;

}