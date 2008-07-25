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

package com.liferay.ruon.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.ruon.model.PresenceUser;
import com.liferay.ruon.service.CommunicationLocalService;
import com.liferay.ruon.service.CommunicationLocalServiceFactory;
import com.liferay.ruon.service.PresenceLocalService;
import com.liferay.ruon.service.PresenceLocalServiceFactory;
import com.liferay.ruon.service.PresenceStatusLocalService;
import com.liferay.ruon.service.PresenceStatusLocalServiceFactory;
import com.liferay.ruon.service.PresenceUserLocalService;
import com.liferay.ruon.service.persistence.PresenceStatusPersistence;
import com.liferay.ruon.service.persistence.PresenceStatusUtil;
import com.liferay.ruon.service.persistence.PresenceUserPersistence;
import com.liferay.ruon.service.persistence.PresenceUserUtil;

import java.util.List;

/**
 * <a href="PresenceUserLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class PresenceUserLocalServiceBaseImpl
	implements PresenceUserLocalService, InitializingBean {
	public PresenceUser addPresenceUser(PresenceUser presenceUser)
		throws SystemException {
		presenceUser.setNew(true);

		return presenceUserPersistence.update(presenceUser, false);
	}

	public void deletePresenceUser(long presenceUserId)
		throws PortalException, SystemException {
		presenceUserPersistence.remove(presenceUserId);
	}

	public void deletePresenceUser(PresenceUser presenceUser)
		throws SystemException {
		presenceUserPersistence.remove(presenceUser);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return presenceUserPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return presenceUserPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public PresenceUser getPresenceUser(long presenceUserId)
		throws PortalException, SystemException {
		return presenceUserPersistence.findByPrimaryKey(presenceUserId);
	}

	public List<PresenceUser> getPresenceUsers(int start, int end)
		throws SystemException {
		return presenceUserPersistence.findAll(start, end);
	}

	public int getPresenceUsersCount() throws SystemException {
		return presenceUserPersistence.countAll();
	}

	public PresenceUser updatePresenceUser(PresenceUser presenceUser)
		throws SystemException {
		presenceUser.setNew(false);

		return presenceUserPersistence.update(presenceUser, true);
	}

	public CommunicationLocalService getCommunicationLocalService() {
		return communicationLocalService;
	}

	public void setCommunicationLocalService(
		CommunicationLocalService communicationLocalService) {
		this.communicationLocalService = communicationLocalService;
	}

	public PresenceLocalService getPresenceLocalService() {
		return presenceLocalService;
	}

	public void setPresenceLocalService(
		PresenceLocalService presenceLocalService) {
		this.presenceLocalService = presenceLocalService;
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

	public PresenceUserPersistence getPresenceUserPersistence() {
		return presenceUserPersistence;
	}

	public void setPresenceUserPersistence(
		PresenceUserPersistence presenceUserPersistence) {
		this.presenceUserPersistence = presenceUserPersistence;
	}

	public void afterPropertiesSet() {
		if (communicationLocalService == null) {
			communicationLocalService = CommunicationLocalServiceFactory.getImpl();
		}

		if (presenceLocalService == null) {
			presenceLocalService = PresenceLocalServiceFactory.getImpl();
		}

		if (presenceStatusLocalService == null) {
			presenceStatusLocalService = PresenceStatusLocalServiceFactory.getImpl();
		}

		if (presenceStatusPersistence == null) {
			presenceStatusPersistence = PresenceStatusUtil.getPersistence();
		}

		if (presenceUserPersistence == null) {
			presenceUserPersistence = PresenceUserUtil.getPersistence();
		}
	}

	protected CommunicationLocalService communicationLocalService;
	protected PresenceLocalService presenceLocalService;
	protected PresenceStatusLocalService presenceStatusLocalService;
	protected PresenceStatusPersistence presenceStatusPersistence;
	protected PresenceUserPersistence presenceUserPersistence;
}