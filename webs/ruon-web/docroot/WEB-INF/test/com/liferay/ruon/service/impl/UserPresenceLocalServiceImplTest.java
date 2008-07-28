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

 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 **/

package com.liferay.ruon.service.impl;

import com.liferay.ruon.model.UserPresence;
import com.liferay.ruon.service.BaseRUONTestCase;
import com.liferay.ruon.service.UserPresenceLocalServiceUtil;
import com.liferay.ruon.util.PresenceStatusConstants;

import java.util.List;

/**
 * <a href="UserPresenceLocalServiceImplTest.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Murali Krishna Reddy
 *
 */
public class UserPresenceLocalServiceImplTest extends BaseRUONTestCase {

	public void setUp() throws Exception {
		super.setUp();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetListOfAllUsers() throws Exception {
		List<UserPresence> puserList =
				UserPresenceLocalServiceUtil.getAllUsers();
		boolean hasUser = false;
		for (UserPresence puser : puserList) {
			if (puser.getPresenceUserId() == userId) hasUser = true;
		}
		assertEquals(true, hasUser);
	}

	public void testGetPresenceStatusOfUser() throws Exception {
		String pUserStatus =
				UserPresenceLocalServiceUtil.getPresenceStatusOfUser(userId);
		assertEquals(true, pUserStatus.contains("offline"));
	}

	public void testMakeUserOnline() throws Exception {
		UserPresenceLocalServiceUtil.setPresenceStatusOfUser(
				userId, PresenceStatusConstants.STATUS_ONLINE);
		UserPresence pUser =
				UserPresenceLocalServiceUtil.getUserPresence(userId);
		assertEquals(pUser.getPresenceStatus(), 1);
	}

	public void testMakeUserOffline() throws Exception {
		UserPresenceLocalServiceUtil.setPresenceStatusOfUser(
				userId, PresenceStatusConstants.STATUS_OFFLINE);
		UserPresence pUser =
				UserPresenceLocalServiceUtil.getUserPresence(userId);
		assertEquals(pUser.getPresenceStatus(), 2);
	}

}