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

package com.liferay.portal.workflow.kaleo.upgrade.hook.service.impl;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.ServiceComponent;
import com.liferay.portal.service.ServiceComponentLocalService;
import com.liferay.portal.service.ServiceComponentLocalServiceWrapper;
import com.liferay.portal.service.persistence.ServiceComponentUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoServiceComponentLocalServiceImpl
	extends ServiceComponentLocalServiceWrapper {

	public KaleoServiceComponentLocalServiceImpl(
		ServiceComponentLocalService serviceComponentLocalService) {

		super(serviceComponentLocalService);
	}

	@Override
	public ServiceComponent initServiceComponent(
			ServletContext servletContext, ClassLoader classLoader,
			String buildNamespace, long buildNumber, long buildDate,
			boolean buildAutoUpgrade)
		throws PortalException, SystemException {

		if (!isFixAutoUpgrade(
				servletContext, buildNamespace, buildAutoUpgrade)) {

			return super.initServiceComponent(
				servletContext, classLoader, buildNamespace, buildNumber,
				buildDate, buildAutoUpgrade);
		}

		Map<Long, Long> kaleoActionKeyValueMap = getKeyValueMap(
			"KaleoAction", "kaleoActionId", "kaleoNodeId");

		Map<Long, Long> kaleoLogKeyValueMap = getKeyValueMap(
			"KaleoLog", "kaleoLogId", "kaleoNodeId");

		Map<Long, Long> kaleoNotificationKeyValueMap = getKeyValueMap(
			"KaleoNotification", "kaleoNotificationId", "kaleoNodeId");

		Map<Long, Long> kaleoTaskAssignmentKeyValueMap = getKeyValueMap(
			"KaleoTaskAssignment", "kaleoTaskAssignmentId", "kaleoTaskId");

		ServiceComponent serviceComponent = super.initServiceComponent(
			servletContext, classLoader, buildNamespace, buildNumber, buildDate,
			buildAutoUpgrade);

		updateKeyValueMap(
			kaleoActionKeyValueMap,
			"com.liferay.portal.workflow.kaleo.model.KaleoNode", "KaleoAction",
			"kaleoActionId");

		updateKeyValueMap(
			kaleoLogKeyValueMap,
			"com.liferay.portal.workflow.kaleo.model.KaleoNode", "KaleoLog",
			"kaleoLogId");

		updateKeyValueMap(
			kaleoNotificationKeyValueMap,
			"com.liferay.portal.workflow.kaleo.model.KaleoNode",
			"KaleoNotification", "kaleoNotificationId");

		updateKeyValueMap(
			kaleoTaskAssignmentKeyValueMap,
			"com.liferay.portal.workflow.kaleo.model.KaleoTask",
			"KaleoTaskAssignment", "kaleoTaskAssignmentId");

		return serviceComponent;
	}

	protected Map<Long, Long> getKeyValueMap(
			String tableName, String keyColumnName, String valueColumnName)
		throws SystemException {

		Map<Long, Long> keyValueMap = new HashMap<Long, Long>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select " + keyColumnName + ", " + valueColumnName + " from " +
					tableName);

			rs = ps.executeQuery();

			while (rs.next()) {
				long key = rs.getLong(keyColumnName);
				long value = rs.getLong(valueColumnName);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"{" + keyColumnName + "=" + key + ", " +
							valueColumnName + "=" + value + "}");
				}

				keyValueMap.put(key, value);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return keyValueMap;
	}

	protected boolean isFixAutoUpgrade(
			ServletContext servletContext, String buildNamespace,
			boolean buildAutoUpgrade)
		throws SystemException {

		String servletContextName = servletContext.getServletContextName();

		if (!servletContextName.equals("kaleo-web") || !buildAutoUpgrade) {
			return false;
		}

		List<ServiceComponent> serviceComponents =
			ServiceComponentUtil.findByBuildNamespace(buildNamespace, 0, 1);

		if (serviceComponents.isEmpty()) {
			return false;
		}

		ServiceComponent serviceComponent = serviceComponents.get(0);

		if (serviceComponent.getBuildNumber() >= 4) {
			return false;
		}

		return true;
	}

	protected void runSQL(String template) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(template);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected void updateKeyValueMap(
			Map<Long, Long> keyValueMap, String kaleoClassName,
			String tableName, String keyColumnName)
		throws SystemException {

		for (Map.Entry<Long, Long> entry : keyValueMap.entrySet()) {
			StringBundler sb = new StringBundler(10);

			sb.append("update ");
			sb.append(tableName);
			sb.append(" set kaleoClassName = '");
			sb.append(kaleoClassName);
			sb.append("', kaleoClassPK = ");
			sb.append(entry.getValue());
			sb.append(" where ");
			sb.append(keyColumnName);
			sb.append(" = ");
			sb.append(entry.getKey());

			runSQL(sb.toString());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		KaleoServiceComponentLocalServiceImpl.class);

}