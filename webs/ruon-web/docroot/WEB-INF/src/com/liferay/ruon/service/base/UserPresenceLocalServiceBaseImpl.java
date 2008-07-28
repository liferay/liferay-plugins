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

package com.liferay.ruon.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.ruon.model.UserPresence;
import com.liferay.ruon.service.PresenceStatusLocalService;
import com.liferay.ruon.service.PresenceStatusLocalServiceFactory;
import com.liferay.ruon.service.UserCommunicationLocalService;
import com.liferay.ruon.service.UserCommunicationLocalServiceFactory;
import com.liferay.ruon.service.UserPresenceLocalService;
import com.liferay.ruon.service.persistence.PresenceStatusPersistence;
import com.liferay.ruon.service.persistence.PresenceStatusUtil;
import com.liferay.ruon.service.persistence.UserPresencePersistence;
import com.liferay.ruon.service.persistence.UserPresenceUtil;

import java.util.List;

/**
 * <a href="UserPresenceLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class UserPresenceLocalServiceBaseImpl
	implements UserPresenceLocalService, InitializingBean {
	public UserPresence addUserPresence(UserPresence userPresence)
		throws SystemException {
		userPresence.setNew(true);

		return userPresencePersistence.update(userPresence, false);
	}

	public void deleteUserPresence(long presenceUserId)
		throws PortalException, SystemException {
		userPresencePersistence.remove(presenceUserId);
	}

	public void deleteUserPresence(UserPresence userPresence)
		throws SystemException {
		userPresencePersistence.remove(userPresence);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return userPresencePersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return userPresencePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public UserPresence getUserPresence(long presenceUserId)
		throws PortalException, SystemException {
		return userPresencePersistence.findByPrimaryKey(presenceUserId);
	}

	public List<UserPresence> getUserPresences(int start, int end)
		throws SystemException {
		return userPresencePersistence.findAll(start, end);
	}

	public int getUserPresencesCount() throws SystemException {
		return userPresencePersistence.countAll();
	}

	public UserPresence updateUserPresence(UserPresence userPresence)
		throws SystemException {
		userPresence.setNew(false);

		return userPresencePersistence.update(userPresence, true);
	}

	public PresenceStatusLocalService getPresenceStatusLocalService() {
		return presenceStatusLocalService;
	}

	public void setPresenceStatusLocalService(
		PresenceStatusLocalService presenceStatusLocalService) {
		this.presenceStatusLocalService = presenceStatusLocalService;
	}

	public PresenceStatusPersistence getPresenceStatusPersistence() {
		return presenceStatusPersistence;
	}

	public void setPresenceStatusPersistence(
		PresenceStatusPersistence presenceStatusPersistence) {
		this.presenceStatusPersistence = presenceStatusPersistence;
	}

	public UserCommunicationLocalService getUserCommunicationLocalService() {
		return userCommunicationLocalService;
	}

	public void setUserCommunicationLocalService(
		UserCommunicationLocalService userCommunicationLocalService) {
		this.userCommunicationLocalService = userCommunicationLocalService;
	}

	public UserPresencePersistence getUserPresencePersistence() {
		return userPresencePersistence;
	}

	public void setUserPresencePersistence(
		UserPresencePersistence userPresencePersistence) {
		this.userPresencePersistence = userPresencePersistence;
	}

	public void afterPropertiesSet() {
		if (presenceStatusLocalService == null) {
			presenceStatusLocalService = PresenceStatusLocalServiceFactory.getImpl();
		}

		if (presenceStatusPersistence == null) {
			presenceStatusPersistence = PresenceStatusUtil.getPersistence();
		}

		if (userCommunicationLocalService == null) {
			userCommunicationLocalService = UserCommunicationLocalServiceFactory.getImpl();
		}

		if (userPresencePersistence == null) {
			userPresencePersistence = UserPresenceUtil.getPersistence();
		}
	}

	protected PresenceStatusLocalService presenceStatusLocalService;
	protected PresenceStatusPersistence presenceStatusPersistence;
	protected UserCommunicationLocalService userCommunicationLocalService;
	protected UserPresencePersistence userPresencePersistence;
}