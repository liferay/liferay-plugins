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

package com.liferay.ruon.servlet;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.ParallelDestination;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;
import com.liferay.ruon.messaging.RUONMessageListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <a href="RUONServletContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Vihang Pathak
 */
public class RUONServletContextListener
	implements PortalInitable, ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		_ruonDestination.unregister(_ruonMessageListener);

		MessageBusUtil.removeDestination(_ruonDestination.getName());

		MessageBusUtil.removeDestination(DestinationNames.RUON_RESPONSE);
	}

	public void contextInitialized(ServletContextEvent event) {
		_setContextClassLoader();

		PortalInitableUtil.init(this);
	}

	public void portalInit() {
		_ruonDestination = new ParallelDestination(DestinationNames.RUON);

		MessageBusUtil.addDestination(_ruonDestination);

		_ruonMessageListener = new RUONMessageListener();

		ClassLoader currentClassLoader =
			Thread.currentThread().getContextClassLoader();

		if (!(currentClassLoader.equals(_contextClassLoader))) {

			Thread.currentThread().setContextClassLoader(_contextClassLoader);
		}

		_ruonDestination.register(_ruonMessageListener);

		Destination ruonResponseDestination = new ParallelDestination(
			DestinationNames.RUON_RESPONSE);

		_restoreCurrentClassLoader(currentClassLoader);

		MessageBusUtil.addDestination(ruonResponseDestination);

	}

	private void _restoreCurrentClassLoader(ClassLoader currentClassLoader) {
		Thread.currentThread().setContextClassLoader(currentClassLoader);
		
	}

	private void _setContextClassLoader() {
		_contextClassLoader = Thread.currentThread().getContextClassLoader();
		
	}

	private ClassLoader	_contextClassLoader;
	private Destination _ruonDestination;
	private MessageListener _ruonMessageListener;

}