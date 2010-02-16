/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.service.impl;

import com.liferay.chat.model.Status;
import com.liferay.chat.service.base.StatusLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * <a href="StatusLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StatusLocalServiceImpl extends StatusLocalServiceBaseImpl {

	public List<Object[]> getAllStatuses(
			long userId, long modifiedDate, int start, int end)
		throws SystemException {

		return statusFinder.findByModifiedDate(
			userId, modifiedDate, start, end);
	}

	public List<Object[]> getGroupStatuses(
			long userId, long modifiedDate, int start, int end)
		throws SystemException {

		return statusFinder.findByUsersGroups(userId, modifiedDate, start, end);
	}

	public List<Object[]> getSocialStatuses(
			long userId, int type, long modifiedDate, int start, int end)
		throws SystemException {

		return statusFinder.findBySocialRelationType(
			userId, type, modifiedDate, start, end);
	}

	public Status getUserStatus(long userId)
		throws PortalException, SystemException {

		Status status = statusPersistence.fetchByUserId(userId);

		if (status == null) {
			status = statusLocalService.updateStatus(
				userId, System.currentTimeMillis(), 1, 1, StringPool.BLANK,
				StringPool.BLANK, 1);
		}

		return status;
	}

	public Status updateStatus(long userId, long modifiedDate)
		throws PortalException, SystemException {

		return updateStatus(userId, modifiedDate, -1, -1, null, null, -1);
	}

	public Status updateStatus(
			long userId, long modifiedDate, int online, int awake,
			String activePanelId, String message, int playSound)
		throws PortalException, SystemException {

		Status status = statusPersistence.fetchByUserId(userId);

		if (status == null) {
			long statusId = CounterLocalServiceUtil.increment();

			status = statusPersistence.create(statusId);

			status.setUserId(userId);
		}

		if (modifiedDate != -1) {
			status.setModifiedDate(modifiedDate);
		}

		if (online != -1) {
			status.setOnline((online == 1) ? true : false);
		}

		if (awake != -1) {
			status.setAwake((awake == 1) ? true : false);
		}

		if (activePanelId != null) {
			status.setActivePanelId(activePanelId);
		}

		if (message != null) {
			status.setMessage(message);
		}

		if (playSound != -1) {
			status.setPlaySound((playSound == 1) ? true : false);
		}

		try {
			statusPersistence.update(status, false);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Add failed, fetch {userId=" + userId + "}");
			}

			status = statusPersistence.fetchByUserId(userId);

			if (status == null) {
				throw se;
			}
		}

		return status;
	}

	private static Log _log = LogFactoryUtil.getLog(
		StatusLocalServiceImpl.class);

}