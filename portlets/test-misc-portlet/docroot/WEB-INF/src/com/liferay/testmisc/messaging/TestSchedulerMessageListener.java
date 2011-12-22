/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testmisc.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 */
public class TestSchedulerMessageListener extends BaseMessageListener {

	public static boolean isReceived() {
		return _received;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Execute");
		}

		_received = true;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TestSchedulerMessageListener.class);

	private static volatile boolean _received;

}