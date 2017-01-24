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

package com.liferay.sync.messaging;

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.persistence.DLSyncEventActionableDynamicQuery;
import com.liferay.sync.service.SyncDLFileVersionDiffLocalServiceUtil;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.PortletPropsValues;

/**
 * @author Dennis Ju
 */
public class SyncMaintenanceMessageListener extends BaseMessageListener {

	public static final String DESTINATION_NAME =
		"liferay/sync_maintenance_processor";

	@Override
	protected void doReceive(Message message) throws Exception {
		if (PortletPropsValues.SYNC_FILE_DIFF_CACHE_ENABLED) {
			try {
				SyncDLFileVersionDiffLocalServiceUtil.
					deleteExpiredSyncDLFileVersionDiffs();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		try {
			final long latestModifiedTime =
				SyncDLObjectLocalServiceUtil.getLatestModifiedTime();

			ActionableDynamicQuery actionableDynamicQuery =
				new DLSyncEventActionableDynamicQuery() {

					@Override
					protected void addCriteria(DynamicQuery dynamicQuery) {
						Property modifiedTimeProperty =
							PropertyFactoryUtil.forName("modifiedTime");

						dynamicQuery.add(
							modifiedTimeProperty.le(
								latestModifiedTime - Time.HOUR));
					}

					@Override
					protected void performAction(Object object)
						throws PortalException, SystemException {

						DLSyncEvent dlSyncEvent = (DLSyncEvent)object;

						DLSyncEventLocalServiceUtil.deleteDLSyncEvent(
							dlSyncEvent);
					}
				};

			actionableDynamicQuery.performActions();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncMaintenanceMessageListener.class);

}