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

package com.liferay.jbpm.handler;

import com.liferay.client.portal.service.http.GroupServiceSoap;
import com.liferay.client.portal.service.http.GroupServiceSoapServiceLocator;
import com.liferay.client.portal.service.http.RoleServiceSoap;
import com.liferay.client.portal.service.http.RoleServiceSoapServiceLocator;
import com.liferay.client.portal.service.http.UserServiceSoap;
import com.liferay.client.portal.service.http.UserServiceSoapServiceLocator;
import com.liferay.jbpm.util.JbpmWebProps;
import com.liferay.util.Encryptor;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <a href="DefaultHandler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class DefaultHandler {

	protected URL getURL(String serviceName) throws MalformedURLException {
		return getURL(serviceName, true);
	}

	protected URL getURL(String serviceName, boolean authenticated)
		throws MalformedURLException {

		String url = JbpmWebProps.get("soap.url");

		if (authenticated) {
			String userId = JbpmWebProps.get("soap.user.id");
			String password = Encryptor.digest(
				JbpmWebProps.get("soap.password"));

			int pos = url.indexOf("://");

			String protocol = url.substring(0, pos + 3);
			String host = url.substring(pos + 3, url.length());

			url =
				protocol + userId + ":" + password + "@" + host +
					"/tunnel-web/secure/axis/" + serviceName;
		}
		else {
			url += "/tunnel-web/axis/" + serviceName;
		}

		return new URL(url);
	}

	protected GroupServiceSoap getGroupService() throws Exception {
		GroupServiceSoapServiceLocator locator =
			new GroupServiceSoapServiceLocator();

		GroupServiceSoap service = locator.getPortal_GroupService(
			getURL("Portal_GroupService"));

		return service;
	}

	protected RoleServiceSoap getRoleService() throws Exception {
		RoleServiceSoapServiceLocator locator =
			new RoleServiceSoapServiceLocator();

		RoleServiceSoap service = locator.getPortal_RoleService(
			getURL("Portal_RoleService"));

		return service;
	}

	protected UserServiceSoap getUserService() throws Exception {
		UserServiceSoapServiceLocator locator =
			new UserServiceSoapServiceLocator();

		UserServiceSoap service = locator.getPortal_UserService(
			getURL("Portal_UserService"));

		return service;
	}

	protected String swimlane;
	protected String msg;

}