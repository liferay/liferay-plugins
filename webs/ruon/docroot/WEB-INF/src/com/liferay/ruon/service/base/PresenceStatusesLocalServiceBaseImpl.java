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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.ruon.model.PresenceStatuses;
import com.liferay.ruon.service.CommunicationLocalService;
import com.liferay.ruon.service.CommunicationLocalServiceFactory;
import com.liferay.ruon.service.CommunicationService;
import com.liferay.ruon.service.CommunicationServiceFactory;
import com.liferay.ruon.service.PresenceLocalService;
import com.liferay.ruon.service.PresenceLocalServiceFactory;
import com.liferay.ruon.service.PresenceService;
import com.liferay.ruon.service.PresenceServiceFactory;
import com.liferay.ruon.service.PresenceStatusesLocalService;
import com.liferay.ruon.service.PresenceUserLocalService;
import com.liferay.ruon.service.PresenceUserLocalServiceFactory;
import com.liferay.ruon.service.PresenceUserService;
import com.liferay.ruon.service.PresenceUserServiceFactory;
import com.liferay.ruon.service.persistence.PresenceStatusesPersistence;
import com.liferay.ruon.service.persistence.PresenceStatusesUtil;
import com.liferay.ruon.service.persistence.PresenceUserPersistence;
import com.liferay.ruon.service.persistence.PresenceUserUtil;

import java.util.List;

/**
 * <a href="PresenceStatusesLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class PresenceStatusesLocalServiceBaseImpl
	implements PresenceStatusesLocalService {
	public PresenceStatuses addPresenceStatuses(
		PresenceStatuses presenceStatuses) throws SystemException {
		presenceStatuses.setNew(true);

		return presenceStatusesPersistence.update(presenceStatuses, false);
	}

	public void deletePresenceStatuses(long presenceStatusId)
		throws PortalException, SystemException {
		presenceStatusesPersistence.remove(presenceStatusId);
	}

	public void deletePresenceStatuses(PresenceStatuses presenceStatuses)
		throws SystemException {
		presenceStatusesPersistence.remove(presenceStatuses);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return presenceStatusesPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return presenceStatusesPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public PresenceStatuses getPresenceStatuses(long presenceStatusId)
		throws PortalException, SystemException {
		return presenceStatusesPersistence.findByPrimaryKey(presenceStatusId);
	}

	public PresenceStatuses updatePresenceStatuses(
		PresenceStatuses presenceStatuses) throws SystemException {
		presenceStatuses.setNew(false);

		return presenceStatusesPersistence.update(presenceStatuses, true);
	}

	public PresenceUserLocalService getPresenceUserLocalService() {
		return presenceUserLocalService;
	}

	public void setPresenceUserLocalService(
		PresenceUserLocalService presenceUserLocalService) {
		this.presenceUserLocalService = presenceUserLocalService;
	}

	public PresenceUserService getPresenceUserService() {
		return presenceUserService;
	}

	public void setPresenceUserService(PresenceUserService presenceUserService) {
		this.presenceUserService = presenceUserService;
	}

	public PresenceUserPersistence getPresenceUserPersistence() {
		return presenceUserPersistence;
	}

	public void setPresenceUserPersistence(
		PresenceUserPersistence presenceUserPersistence) {
		this.presenceUserPersistence = presenceUserPersistence;
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
		if (presenceUserLocalService == null) {
			presenceUserLocalService = PresenceUserLocalServiceFactory.getImpl();
		}

		if (presenceUserService == null) {
			presenceUserService = PresenceUserServiceFactory.getImpl();
		}

		if (presenceUserPersistence == null) {
			presenceUserPersistence = PresenceUserUtil.getPersistence();
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

	protected PresenceUserLocalService presenceUserLocalService;
	protected PresenceUserService presenceUserService;
	protected PresenceUserPersistence presenceUserPersistence;
	protected PresenceStatusesPersistence presenceStatusesPersistence;
	protected CommunicationLocalService communicationLocalService;
	protected CommunicationService communicationService;
	protected PresenceLocalService presenceLocalService;
	protected PresenceService presenceService;
}