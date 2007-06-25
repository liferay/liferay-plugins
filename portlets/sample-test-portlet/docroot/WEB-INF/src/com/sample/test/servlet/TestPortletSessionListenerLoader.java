/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.test.servlet;

import com.liferay.portal.kernel.servlet.PortletSessionListenerLoader;

/**
 * <a href="TestPortletSessionListenerLoader.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * <p>
 * This is a <code>javax.servlet.ServletContextListener</code> that loads a
 * <code>javax.servlet.http.HttpSessionListener</code> and ensures the hot
 * deployed WAR's session events are triggered along with the portal's session
 * events. This is only needed for certain application servers under certain
 * configurations. Otherwise, you can just load the the
 * <code>HttpSessionListener</code> directly in WEB-INF/web.xml.
 * </p>
 *
 * <p>
 * See http://support.liferay.com/browse/LEP-2299.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.sample.test.servlet.TestPortletSessionListener
 *
 */
public class TestPortletSessionListenerLoader
	extends PortletSessionListenerLoader {

	public TestPortletSessionListenerLoader() {
		super(new TestPortletSessionListener());
	}

}