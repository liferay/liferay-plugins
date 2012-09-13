/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.akismet.messaging;

import com.liferay.akismet.service.AkismetDataLocalServiceUtil;
import com.liferay.akismet.util.PortletPropsKeys;
import com.liferay.akismet.util.PrefsPortletPropsUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class ClearAkismetDataMessageListener extends BaseMessageListener {

	protected void clearAkismetData(long companyId) throws SystemException {
		int allowReportMessageTime = PrefsPortletPropsUtil.getInteger(
			companyId, PortletPropsKeys.AKISMET_ALLOW_REPORT_MESSAGE_TIME);

		Date clearBeforeDate = new Date(
			System.currentTimeMillis() - (allowReportMessageTime * Time.DAY));

		AkismetDataLocalServiceUtil.clearAkismetData(clearBeforeDate);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long[] companyIds = PortalUtil.getCompanyIds();

		for (long companyId : companyIds) {
			clearAkismetData(companyId);
		}
	}

}