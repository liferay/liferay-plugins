/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hook.expando.mongodb.events;

import com.liferay.hook.expando.mongodb.util.MongoDBUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			String[] hostnames = PortletProps.getArray("mongodb.hostnames");
			int port = GetterUtil.getInteger(PortletProps.get("mongodb.port"));
			String database = PortletProps.get("mongodb.database");
			String username = PortletProps.get("mongodb.username");
			String password = PortletProps.get("mongodb.password");

			MongoOptions options = new MongoOptions();

			options.autoConnectRetry = GetterUtil.getBoolean(
				PortletProps.get("mongodb.autoConnectRetry"),
				options.autoConnectRetry);
			options.connectionsPerHost = GetterUtil.getInteger(
				PortletProps.get("mongodb.connectionsPerHost"),
				options.connectionsPerHost);
			options.connectTimeout  = GetterUtil.getInteger(
				PortletProps.get("mongodb.connectTimeout"),
				options.connectTimeout);
			options.maxWaitTime = GetterUtil.getInteger(
				PortletProps.get("mongodb.maxWaitTime"), options.maxWaitTime);
			options.socketTimeout = GetterUtil.getInteger(
				PortletProps.get("mongodb.socketTimeout"),
				options.socketTimeout);
			options.threadsAllowedToBlockForConnectionMultiplier  =
				GetterUtil.getInteger(
					PortletProps.get(
						"mongodb.threadsAllowedToBlockForConnectionMultiplier"),
					options.threadsAllowedToBlockForConnectionMultiplier);

			Mongo mongo = null;

			if (hostnames.length > 1) {
				List<ServerAddress> serverAddresses =
					new ArrayList<ServerAddress>();

				for (String hostname : hostnames) {
					serverAddresses.add(new ServerAddress(hostname, port));
				}

				mongo = new Mongo(serverAddresses, options);
			}
			else {
				mongo = new Mongo(
					new ServerAddress(hostnames[0] , port), options);
			}

			MongoDBUtil.init(mongo, database);

			for (String id : ids) {
				try {
					doAuthenticate(GetterUtil.getLong(id), username, password);
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doAuthenticate(
			long companyId, String username, String password)
		throws Exception {

		DB db = MongoDBUtil.getDB(companyId);

		if (Validator.isNotNull(username) &&
			Validator.isNotNull(password)) {

			boolean authenticated = db.authenticate(
				username, password.toCharArray());

			if (!authenticated) {
				throw new SystemException(
					"Authentication Failed! Check the db log for details.");
			}
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(StartupAction.class);

}
