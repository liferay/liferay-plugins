/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.nio.intraband.rpc.IntrabandRPCUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.resiliency.mpi.MPIHelperUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;

import java.io.Serializable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPortletConfigMessageListener extends BaseMessageListener {

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
		_countDown();
	}

	private static void _countDown() throws Exception {
		_countDownLatch.countDown();

		for (SPI spi : MPIHelperUtil.getSPIs()) {
			IntrabandRPCUtil.execute(
				spi.getRegistrationReference(), new CountDownProcessCallable());
		}
	}

	private static CountDownLatch _countDownLatch = new CountDownLatch(1);

	private static class CountDownProcessCallable
		implements ProcessCallable<Serializable> {

		@Override
		public Serializable call() throws ProcessException {
			try {
				TestPortletConfigMessageListener._countDown();
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		private static final long serialVersionUID = 1L;

	}

}