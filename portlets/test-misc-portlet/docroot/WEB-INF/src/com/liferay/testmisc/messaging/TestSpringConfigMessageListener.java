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

package com.liferay.testmisc.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Tina Tian
 */
public class TestSpringConfigMessageListener extends BaseMessageListener {

	public static boolean isReceived() {
		try {
			return _countDownLatch.await(60000, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException ie) {
			return false;
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		_countDownLatch.countDown();
	}

	private static CountDownLatch _countDownLatch = new CountDownLatch(1);

}