/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.manager.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyModel;
import com.liferay.portal.model.ModelEventMessage;
import com.liferay.portal.workflow.kaleo.manager.PortalKaleoManager;

/**
 * @author Michael C. Han
 */
public class CompanyModelEventMessageListener implements MessageListener {
	public void receive(Message message) throws MessageListenerException {
		Object payload = message.getPayload();

		if (payload instanceof ModelEventMessage) {
			doReceive((ModelEventMessage)payload);
		}
	}

	public void setPortalKaleoManager(PortalKaleoManager portalKaleoManager) {
		_portalKaleoManager = portalKaleoManager;
	}

	protected void doDestroyed(Company company) {
		try {
			_portalKaleoManager.deleteKaleoData(company);
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error(
					"Unable to delete kaleo workflow data on company deletion",
					e);
			}
		}
	}

	protected void doInitialized(Company company) {
		try {
			_portalKaleoManager.deployKaleoDefaults(company);
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error(
					"Unable to initialize kaleo workflow defaults",
					e);
			}
		}
	}

	protected void doReceive(ModelEventMessage modelEventMessage) {
		BaseModel baseModel = modelEventMessage.getBaseModel();

		if (!(baseModel instanceof CompanyModel)) {
			return;
		}

		String eventType = modelEventMessage.getEventType();

		Company company = (Company)baseModel;

		if (eventType.equals(ModelEventMessage.INITIALIZED)) {
			doInitialized(company);
		}
		else if (eventType.equals(ModelEventMessage.DESTROYED)) {
			doDestroyed(company);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CompanyModelEventMessageListener.class);

	private PortalKaleoManager _portalKaleoManager;
}
