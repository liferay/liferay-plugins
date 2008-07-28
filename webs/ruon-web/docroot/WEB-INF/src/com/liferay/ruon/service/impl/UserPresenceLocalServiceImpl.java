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

import com.liferay.ruon.NoSuchUserPresenceException;
import com.liferay.ruon.model.UserPresence;
import com.liferay.ruon.model.impl.UserPresenceImpl;
import com.liferay.ruon.service.base.UserPresenceLocalServiceBaseImpl;
import com.liferay.ruon.util.PresenceStatusConstants;
import com.liferay.ruon.util.RUONException;

import java.util.List;

/**
 * <a href="UserPresenceLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class UserPresenceLocalServiceImpl
	extends UserPresenceLocalServiceBaseImpl {
	public List<UserPresence> getAllUsers() throws RUONException {
		try {
			return userPresencePersistence.findAll();
		}
		catch (Exception e) {
			throw new RUONException(e);
		}
	}

	public String getPresenceStatusOfUser(Long userId) throws RUONException {
		try {

			Long presenceStatus =
					getUserPresence(userId).
							getPresenceStatus();

			if (presenceStatus.equals(PresenceStatusConstants.STATUS_ONLINE)) {
				return "<img src=\"/ruon/images/status_online.png\"/>";
			} else {
				return "<img src=\"/ruon/images/status_offline.png\"/>";
			}
		}
		catch (NoSuchUserPresenceException ne) {
			return "<img src=\"/ruon/images/status_offline.png\"/>";
		}
		catch (Exception exception) {
			throw new RUONException(exception);
		}
	}

	public boolean isUserOnline(Long userId) throws RUONException {
		try {
			Long presenceStatus = getUserPresence(userId).getPresenceStatus();

			if (presenceStatus.equals(PresenceStatusConstants.STATUS_ONLINE)) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			throw new RUONException(e);
		}
	}

	public void makeAllUsersOffline() throws RUONException {
		for (UserPresence userPresence : getAllUsers()) {
			Long pUserId = userPresence.getPresenceUserId();
			setPresenceStatusOfUser(
							pUserId, PresenceStatusConstants.STATUS_OFFLINE);
		}
	}

	public void setPresenceStatusOfUser(Long userId, Long status)
			throws RUONException {
		try {
			UserPresence userPresence = getUserPresence(userId);

			userPresence.setPresenceStatus(status);
			updateUserPresence(userPresence);
		}
		catch (NoSuchUserPresenceException noSuchUserPresenceException) {
			UserPresence pUserNew = new UserPresenceImpl();
			pUserNew.setPresenceUserId(userId);
			pUserNew.setPresenceStatus(status);
			try {
				addUserPresence(pUserNew);
			}
			catch (Exception se) {
				throw new RUONException(se);
			}
		}
		catch (Exception e) {
			throw new RUONException(e);
		}
	}

}