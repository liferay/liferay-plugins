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

package com.liferay.chat.servlet;

import com.liferay.chat.util.ChatRUONConverter;
import com.liferay.client.json.ruon.util.RUONUtil;
import com.liferay.portal.kernel.util.Converter;
import com.liferay.portal.kernel.util.PortalInitable;
import com.liferay.portal.kernel.util.PortalInitableUtil;
import com.liferay.portal.kernel.util.Time;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="ChatServletContextListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ChatServletContextListener
	implements PortalInitable, ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		PortalInitableUtil.init(this);
	}

	public void portalInit() {
		try {
			String name = "chat-portlet";
			long ttl = Time.SECOND * 10;
			Converter converter = new ChatRUONConverter();

			RUONUtil.updateNetwork(name, ttl, converter);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log =
		LogFactory.getLog(ChatServletContextListener.class);

}