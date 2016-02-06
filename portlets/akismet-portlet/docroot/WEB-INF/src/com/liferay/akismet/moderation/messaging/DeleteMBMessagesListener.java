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

package com.liferay.akismet.moderation.messaging;

import com.liferay.akismet.util.AkismetUtil;
import com.liferay.message.boards.kernel.exception.NoSuchMessageException;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Amos Fong
 */
public class DeleteMBMessagesListener extends BaseMessageListener {

	protected void deleteSpam(long companyId) throws PortalException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			MBMessage.class, PortalClassLoaderUtil.getClassLoader());

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(statusProperty.eq(WorkflowConstants.STATUS_DENIED));

		Property statusDateProperty = PropertyFactoryUtil.forName("statusDate");

		dynamicQuery.add(
			statusDateProperty.lt(AkismetUtil.getRetainSpamTime()));

		List<MBMessage> mbMessages = MBMessageLocalServiceUtil.dynamicQuery(
			dynamicQuery);

		for (MBMessage mbMessage : mbMessages) {
			try {
				MBMessageLocalServiceUtil.deleteMBMessage(
					mbMessage.getMessageId());
			}
			catch (NoSuchMessageException nsme) {
			}
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long[] companyIds = PortalUtil.getCompanyIds();

		for (long companyId : companyIds) {
			deleteSpam(companyId);
		}
	}

}