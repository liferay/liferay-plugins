/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.messaging;

import com.liferay.marketplace.model.App;
import com.liferay.marketplace.service.AppLocalServiceUtil;
import com.liferay.marketplace.service.ModuleLocalServiceUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Properties;

/**
 * @author Ryan Park
 */
public class MarketplaceMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (!command.equals("deploy")) {
			return;
		}

		Properties properties = PropertiesUtil.load(
			message.getString("properties"));

		long remoteAppId = GetterUtil.getLong(
			properties.getProperty("remote-app-id"));
		String version = properties.getProperty("version");

		if ((remoteAppId <= 0) || Validator.isNull(version)) {
			return;
		}

		App app = AppLocalServiceUtil.fetchRemoteApp(remoteAppId);

		if (app != null) {
			return;
		}

		app = AppLocalServiceUtil.addApp(0, remoteAppId, version, null);

		String[] contextNames = StringUtil.split(
			properties.getProperty("context-names"));

		for (String contextName : contextNames) {
			ModuleLocalServiceUtil.addModule(0, app.getAppId(), contextName);
		}

		AppLocalServiceUtil.processMarketplaceProperties(properties);
	}

}