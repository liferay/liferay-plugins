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

import com.liferay.portal.kernel.bean.InitializingBean;

import com.liferay.ruon.service.CommunicationLocalService;
import com.liferay.ruon.service.CommunicationLocalServiceFactory;
import com.liferay.ruon.service.PresenceLocalService;
import com.liferay.ruon.service.PresenceStatusLocalService;
import com.liferay.ruon.service.PresenceStatusLocalServiceFactory;
import com.liferay.ruon.service.PresenceUserLocalService;
import com.liferay.ruon.service.PresenceUserLocalServiceFactory;
import com.liferay.ruon.service.persistence.PresenceStatusPersistence;
import com.liferay.ruon.service.persistence.PresenceStatusUtil;
import com.liferay.ruon.service.persistence.PresenceUserPersistence;
import com.liferay.ruon.service.persistence.PresenceUserUtil;

/**
 * <a href="PresenceLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class PresenceLocalServiceBaseImpl
	implements PresenceLocalService, InitializingBean {
	public CommunicationLocalService getCommunicationLocalService() {
		return communicationLocalService;
	}

	public void setCommunicationLocalService(
		CommunicationLocalService communicationLocalService) {
		this.communicationLocalService = communicationLocalService;
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

	public PresenceUserLocalService getPresenceUserLocalService() {
		return presenceUserLocalService;
	}

	public void setPresenceUserLocalService(
		PresenceUserLocalService presenceUserLocalService) {
		this.presenceUserLocalService = presenceUserLocalService;
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

		if (presenceStatusLocalService == null) {
			presenceStatusLocalService = PresenceStatusLocalServiceFactory.getImpl();
		}

		if (presenceStatusPersistence == null) {
			presenceStatusPersistence = PresenceStatusUtil.getPersistence();
		}

		if (presenceUserLocalService == null) {
			presenceUserLocalService = PresenceUserLocalServiceFactory.getImpl();
		}

		if (presenceUserPersistence == null) {
			presenceUserPersistence = PresenceUserUtil.getPersistence();
		}
	}

	protected CommunicationLocalService communicationLocalService;
	protected PresenceStatusLocalService presenceStatusLocalService;
	protected PresenceStatusPersistence presenceStatusPersistence;
	protected PresenceUserLocalService presenceUserLocalService;
	protected PresenceUserPersistence presenceUserPersistence;
}