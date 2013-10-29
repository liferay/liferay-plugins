/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.privatemessaging.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.comparator.MessageCreateDateComparator;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.service.base.UserThreadServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class UserThreadServiceImpl extends UserThreadServiceBaseImpl {

	public MBMessage getLastThreadMessage(long mbThreadId)
		throws PortalException, SystemException {

		List<MBMessage> mbMessages = getThreadMessages(
			mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

		MBMessage lastMBMessage = mbMessages.get(0);

		return lastMBMessage;
	}

	public List<MBMessage> getThreadMessages(
			long mbThreadId, int start, int end, boolean ascending)
		throws PortalException, SystemException {

		UserThread userThread = userThreadLocalService.getUserThread(
			getUserId(), mbThreadId);

		MBMessage topMBMessage = MBMessageLocalServiceUtil.getMBMessage(
			userThread.getTopMBMessageId());

		List<MBMessage> mbMessages =
			MBMessageLocalServiceUtil.getThreadMessages(
				mbThreadId, WorkflowConstants.STATUS_ANY,
				new MessageCreateDateComparator(ascending));

		List<MBMessage> filteredMBMessages = new ArrayList<MBMessage>();

		for (MBMessage mbMessage : mbMessages) {
			int compareTo = DateUtil.compareTo(
				topMBMessage.getCreateDate(), mbMessage.getCreateDate());

			if (compareTo <= 0) {
				filteredMBMessages.add(mbMessage);
			}
		}

		if (filteredMBMessages.isEmpty()) {
			return filteredMBMessages;
		}
		else if ((start == QueryUtil.ALL_POS) || (end == QueryUtil.ALL_POS)) {
			return filteredMBMessages;
		}
		else if (end > filteredMBMessages.size()) {
			end = filteredMBMessages.size();
		}

		return filteredMBMessages.subList(start, end);
	}

	public int getThreadMessagesCount(long mbThreadId)
		throws PortalException, SystemException {

		List<MBMessage> mbMessages = getThreadMessages(
			mbThreadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, true);

		return mbMessages.size();
	}

	public List<UserThread> getUserUserThreads(boolean deleted)
		throws PrincipalException, SystemException {

		return userThreadLocalService.getUserUserThreads(getUserId(), deleted);
	}

}