/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.service.scheduling.util;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.service.scheduling.SchedulingEngine;
import com.liferay.portal.service.scheduling.SchedulingException;

/**
 * <a href="SchedulerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Bruno Farache
 *
 */
public class SchedulerUtil {

	public static void init(SchedulingEngine engine)
	throws SchedulingException {

		_instance._init(engine);
	}

	public static void destroy() throws SchedulingException {
		_instance._destroy();
	}

	public static SchedulingEngine getSchedulingEngine() {
		return _instance._engine;
	}

	private SchedulerUtil() {
	}

	private void _init(SchedulingEngine engine)
		throws SchedulingException {

		_engine = engine;

		Destination destination = new SerialDestination(
			DestinationNames.SCHEDULER);

		MessageBusUtil.addDestination(destination);

		MessageBusUtil.registerMessageListener(
			destination.getName(), new SchedulingRequestListener());

		_engine.start();
	}

	private void _destroy() throws SchedulingException {
		_engine.shutdown();
	}

	private static SchedulerUtil _instance = new SchedulerUtil();

	private SchedulingEngine _engine;

}