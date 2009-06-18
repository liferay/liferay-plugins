/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.util;

import com.liferay.wsrp.model.WSRPConsumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a href="WSRPConsumerManagerFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerManagerFactory {

	public static WSRPConsumerManager getWSRPConsumerManager(String wsdl)
		throws Exception {

		WSRPConsumerManager wsrpConsumerManager =
			(WSRPConsumerManager)_wsrpConsumerManagers.get(wsdl);

		if (wsrpConsumerManager == null) {
			wsrpConsumerManager = new WSRPConsumerManager(wsdl);

			_wsrpConsumerManagers.put(wsdl, wsrpConsumerManager);
		}

		return wsrpConsumerManager;
	}

	public static WSRPConsumerManager getWSRPConsumerManager(
			WSRPConsumer wsrpConsumer)
		throws Exception {

		return getWSRPConsumerManager(wsrpConsumer.getWsdl());
	}

	private static Map<String, WSRPConsumerManager> _wsrpConsumerManagers =
		new ConcurrentHashMap<String, WSRPConsumerManager>();

}