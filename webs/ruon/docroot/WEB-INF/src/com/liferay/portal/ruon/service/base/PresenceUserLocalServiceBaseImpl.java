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

package com.liferay.portal.ruon.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.ruon.model.PresenceUser;
import com.liferay.portal.ruon.service.CommunicationLocalService;
import com.liferay.portal.ruon.service.CommunicationLocalServiceFactory;
import com.liferay.portal.ruon.service.CommunicationService;
import com.liferay.portal.ruon.service.CommunicationServiceFactory;
import com.liferay.portal.ruon.service.PresenceLocalService;
import com.liferay.portal.ruon.service.PresenceLocalServiceFactory;
import com.liferay.portal.ruon.service.PresenceService;
import com.liferay.portal.ruon.service.PresenceServiceFactory;
import com.liferay.portal.ruon.service.PresenceStatusesLocalService;
import com.liferay.portal.ruon.service.PresenceStatusesLocalServiceFactory;
import com.liferay.portal.ruon.service.PresenceStatusesService;
import com.liferay.portal.ruon.service.PresenceStatusesServiceFactory;
import com.liferay.portal.ruon.service.PresenceUserLocalService;
import com.liferay.portal.ruon.service.persistence.PresenceStatusesPersistence;
import com.liferay.portal.ruon.service.persistence.PresenceStatusesUtil;
import com.liferay.portal.ruon.service.persistence.PresenceUserPersistence;
import com.liferay.portal.ruon.service.persistence.PresenceUserUtil;

import java.util.List;

/**
 * <a href="PresenceUserLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Murali Krishna Reddy
 * @author Brian Wing Shun Chan
 *
 */
public abstract class PresenceUserLocalServiceBaseImpl
	implements PresenceUserLocalService {
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

	public PresenceUser updatePresenceUser(PresenceUser presenceUser)
		throws SystemException {
		presenceUser.setNew(false);

		return presenceUserPersistence.update(presenceUser, true);
	}

	public PresenceUserPersistence getPresenceUserPersistence() {
		return presenceUserPersistence;
	}

	public void setPresenceUserPersistence(
		PresenceUserPersistence presenceUserPersistence) {
		this.presenceUserPersistence = presenceUserPersistence;
	}

	public PresenceStatusesLocalService getPresenceStatusesLocalService() {
		return presenceStatusesLocalService;
	}

	public void setPresenceStatusesLocalService(
		PresenceStatusesLocalService presenceStatusesLocalService) {
		this.presenceStatusesLocalService = presenceStatusesLocalService;
	}

	public PresenceStatusesService getPresenceStatusesService() {
		return presenceStatusesService;
	}

	public void setPresenceStatusesService(
		PresenceStatusesService presenceStatusesService) {
		this.presenceStatusesService = presenceStatusesService;
	}

	public PresenceStatusesPersistence getPresenceStatusesPersistence() {
		return presenceStatusesPersistence;
	}

	public void setPresenceStatusesPersistence(
		PresenceStatusesPersistence presenceStatusesPersistence) {
		this.presenceStatusesPersistence = presenceStatusesPersistence;
	}

	public CommunicationLocalService getCommunicationLocalService() {
		return communicationLocalService;
	}

	public void setCommunicationLocalService(
		CommunicationLocalService communicationLocalService) {
		this.communicationLocalService = communicationLocalService;
	}

	public CommunicationService getCommunicationService() {
		return communicationService;
	}

	public void setCommunicationService(
		CommunicationService communicationService) {
		this.communicationService = communicationService;
	}

	public PresenceLocalService getPresenceLocalService() {
		return presenceLocalService;
	}

	public void setPresenceLocalService(
		PresenceLocalService presenceLocalService) {
		this.presenceLocalService = presenceLocalService;
	}

	public PresenceService getPresenceService() {
		return presenceService;
	}

	public void setPresenceService(PresenceService presenceService) {
		this.presenceService = presenceService;
	}

	protected void init() {
		if (presenceUserPersistence == null) {
			presenceUserPersistence = PresenceUserUtil.getPersistence();
		}

		if (presenceStatusesLocalService == null) {
			presenceStatusesLocalService = PresenceStatusesLocalServiceFactory.getImpl();
		}

		if (presenceStatusesService == null) {
			presenceStatusesService = PresenceStatusesServiceFactory.getImpl();
		}

		if (presenceStatusesPersistence == null) {
			presenceStatusesPersistence = PresenceStatusesUtil.getPersistence();
		}

		if (communicationLocalService == null) {
			communicationLocalService = CommunicationLocalServiceFactory.getImpl();
		}

		if (communicationService == null) {
			communicationService = CommunicationServiceFactory.getImpl();
		}

		if (presenceLocalService == null) {
			presenceLocalService = PresenceLocalServiceFactory.getImpl();
		}

		if (presenceService == null) {
			presenceService = PresenceServiceFactory.getImpl();
		}
	}

	protected PresenceUserPersistence presenceUserPersistence;
	protected PresenceStatusesLocalService presenceStatusesLocalService;
	protected PresenceStatusesService presenceStatusesService;
	protected PresenceStatusesPersistence presenceStatusesPersistence;
	protected CommunicationLocalService communicationLocalService;
	protected CommunicationService communicationService;
	protected PresenceLocalService presenceLocalService;
	protected PresenceService presenceService;
}