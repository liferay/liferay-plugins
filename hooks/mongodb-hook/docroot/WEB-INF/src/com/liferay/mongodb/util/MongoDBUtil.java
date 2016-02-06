/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.mongodb.util;

import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Augé
 * @author Brian Wing Shun Chan
 */
public class MongoDBUtil {

	public static boolean authenticate(long companyId) {
		return _instance._authenticate(companyId);
	}

	public static DBCollection getCollection(ExpandoTable expandoTable) {
		return _instance._getCollection(expandoTable);
	}

	public static DBCollection getCollection(
		long companyId, long classNameId, String tableName) {

		return _instance._getCollection(companyId, classNameId, tableName);
	}

	public static DBCollection getCollection(
		long companyId, String className, String tableName) {

		return _instance._getCollection(companyId, className, tableName);
	}

	public static String getCollectionName(String className, String tableName) {
		return _instance._getCollectionName(className, tableName);
	}

	public static DB getDB(long companyId) {
		return _instance._getDB(companyId);
	}

	public static Mongo getMongo() {
		return _instance._mongo;
	}

	private MongoDBUtil() {
		try {
			_mongo = new Mongo(_getServerAddresses(), _getMongoOptions());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private boolean _authenticate(long companyId) {
		if (Validator.isNull(PortletPropsValues.SERVER_USERNAME) ||
			Validator.isNull(PortletPropsValues.SERVER_PASSWORD)) {

			return true;
		}

		DB db = _getDB(companyId);

		return db.authenticate(
			PortletPropsValues.SERVER_USERNAME,
			PortletPropsValues.SERVER_PASSWORD.toCharArray());
	}

	private DBCollection _getCollection(ExpandoTable expandoTable) {
		return _instance._getCollection(
			expandoTable.getCompanyId(), expandoTable.getClassName(),
			expandoTable.getName());
	}

	private DBCollection _getCollection(
		long companyId, long classNameId, String tableName) {

		String className = PortalUtil.getClassName(classNameId);

		return _getCollection(companyId, className, tableName);
	}

	private DBCollection _getCollection(
		long companyId, String className, String tableName) {

		DB db = _getDB(companyId);

		String collectionName = _getCollectionName(className, tableName);

		return db.getCollection(collectionName);
	}

	private String _getCollectionName(String className, String tableName) {
		return className.concat(StringPool.POUND).concat(tableName);
	}

	private DB _getDB(long companyId) {
		String dbName = PortletPropsValues.SERVER_DATABASE.concat(
			StringPool.UNDERLINE).concat(String.valueOf(companyId));

		return _mongo.getDB(dbName);
	}

	private MongoOptions _getMongoOptions() {
		MongoOptions mongoOptions = new MongoOptions();

		mongoOptions.autoConnectRetry = GetterUtil.getBoolean(
			PortletPropsValues.DRIVER_AUTOCONNECT_RETRY,
			mongoOptions.autoConnectRetry);
		mongoOptions.connectTimeout = GetterUtil.getInteger(
			PortletPropsValues.DRIVER_CONNECT_TIMEOUT,
			mongoOptions.connectTimeout);
		mongoOptions.connectionsPerHost = GetterUtil.getInteger(
			PortletPropsValues.DRIVER_CONNECTIONS_PER_HOST,
			mongoOptions.connectionsPerHost);
		mongoOptions.maxWaitTime = GetterUtil.getInteger(
			PortletPropsValues.DRIVER_MAX_WAIT_TIME, mongoOptions.maxWaitTime);
		mongoOptions.socketTimeout = GetterUtil.getInteger(
			PortletPropsValues.DRIVER_SOCKET_TIMEOUT,
			mongoOptions.socketTimeout);
		mongoOptions.threadsAllowedToBlockForConnectionMultiplier =
			GetterUtil.getInteger(
				PortletPropsValues.DRIVER_THREADS_ALLOWED_TO_BLOCK,
				mongoOptions.threadsAllowedToBlockForConnectionMultiplier);

		return mongoOptions;
	}

	private List<ServerAddress> _getServerAddresses() throws Exception {
		List<ServerAddress> serverAddresses = new ArrayList<>();

		for (String hostname : PortletPropsValues.SERVER_HOSTNAMES) {
			ServerAddress serverAddress = new ServerAddress(
				hostname, PortletPropsValues.SERVER_PORT);

			serverAddresses.add(serverAddress);
		}

		return serverAddresses;
	}

	private static Log _log = LogFactoryUtil.getLog(MongoDBUtil.class);

	private static MongoDBUtil _instance = new MongoDBUtil();

	private Mongo _mongo;

}