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

package com.liferay.sync.servlet;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.scheduler.CronText;
import com.liferay.portal.kernel.scheduler.CronTrigger;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil;
import com.liferay.sync.messaging.SyncDLFileVersionDiffMessageListener;
import com.liferay.sync.messaging.SyncDLObjectMessageListener;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.service.SyncPreferencesLocalServiceUtil;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 * @author Dennis Ju
 */
public class SyncServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	protected void consumeDLSyncEvents() {
		try {
			long latestModifiedTime =
				SyncDLObjectLocalServiceUtil.getLatestModifiedTime();

			List<DLSyncEvent> dlSyncEvents = null;

			if (latestModifiedTime == 0) {
				dlSyncEvents =
					DLSyncEventLocalServiceUtil.getLatestDLSyncEvents();
			}
			else {
				dlSyncEvents = DLSyncEventLocalServiceUtil.getDLSyncEvents(
					latestModifiedTime);
			}

			for (DLSyncEvent dlSyncEvent : dlSyncEvents) {
				Message message = new Message();

				Map<String, Object> values = new HashMap<>(4);

				values.put("event", dlSyncEvent.getEvent());
				values.put("modifiedTime", dlSyncEvent.getModifiedTime());
				values.put("type", dlSyncEvent.getType());
				values.put("typePK", dlSyncEvent.getTypePK());

				message.setValues(values);

				MessageBusUtil.sendMessage(
					DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR,
					message);
			}

			DLSyncEventLocalServiceUtil.deleteDLSyncEvents();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		DLSyncEventLocalServiceUtil.deleteDLSyncEvents();

		MessageBusUtil.unregisterMessageListener(
			DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR,
			_syncDLObjectMessageListener);

		if (PortletPropsValues.SYNC_FILE_DIFF_CACHE_ENABLED) {
			MessageBusUtil.unregisterMessageListener(
				SyncDLFileVersionDiffMessageListener.DESTINATION_NAME,
				_syncDLFileVersionDiffMessageListener);
		}
	}

	@Override
	protected void doPortalInit() {
		try {
			List<Company> companies = CompanyLocalServiceUtil.getCompanies();

			for (Company company : companies) {
				boolean oAuthEnabled = PrefsPropsUtil.getBoolean(
					company.getCompanyId(), PortletPropsKeys.SYNC_OAUTH_ENABLED,
					PortletPropsValues.SYNC_OAUTH_ENABLED);

				if (!oAuthEnabled) {
					continue;
				}

				ServiceContext serviceContext = new ServiceContext();

				User user = UserLocalServiceUtil.getDefaultUser(
					company.getCompanyId());

				serviceContext.setUserId(user.getUserId());

				OAuthApplication oAuthApplication =
					SyncPreferencesLocalServiceUtil.enableOAuth(
						company.getCompanyId(), serviceContext);

				PortletPreferences portletPreferences =
					PrefsPropsUtil.getPreferences(company.getCompanyId());

				portletPreferences.setValue(
					PortletPropsKeys.SYNC_OAUTH_APPLICATION_ID,
					String.valueOf(oAuthApplication.getOAuthApplicationId()));
				portletPreferences.setValue(
					PortletPropsKeys.SYNC_OAUTH_CONSUMER_KEY,
					oAuthApplication.getConsumerKey());
				portletPreferences.setValue(
					PortletPropsKeys.SYNC_OAUTH_CONSUMER_SECRET,
					oAuthApplication.getConsumerSecret());

				portletPreferences.store();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_syncDLObjectMessageListener = new SyncDLObjectMessageListener();

		registerMessageListener(
			_syncDLObjectMessageListener,
			DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR);

		if (PortletPropsValues.SYNC_FILE_DIFF_CACHE_ENABLED) {
			_syncDLFileVersionDiffMessageListener =
				new SyncDLFileVersionDiffMessageListener();

			registerMessageListener(
				_syncDLFileVersionDiffMessageListener,
				SyncDLFileVersionDiffMessageListener.DESTINATION_NAME);

			scheduleDLFileVersionDiffMessageListener();
		}

		consumeDLSyncEvents();
	}

	protected void registerMessageListener(
		MessageListener messageListener, String destinationName) {

		SerialDestination serialDestination = new SerialDestination();

		serialDestination.setName(destinationName);

		serialDestination.afterPropertiesSet();

		MessageBusUtil.addDestination(serialDestination);

		MessageBusUtil.registerMessageListener(
			destinationName, messageListener);
	}

	protected void scheduleDLFileVersionDiffMessageListener() {
		try {
			Calendar calendar = CalendarFactoryUtil.getCalendar();

			CronText cronText = new CronText(
				calendar, CronText.HOURLY_FREQUENCY,
				PortletPropsValues.SYNC_FILE_DIFF_CACHE_DELETE_INTERVAL);

			Trigger trigger = new CronTrigger(
				SyncDLFileVersionDiffMessageListener.class.getName(),
				SyncDLFileVersionDiffMessageListener.class.getName(),
				cronText.toString());

			SchedulerEngineHelperUtil.schedule(
				trigger, StorageType.MEMORY_CLUSTERED, null,
				SyncDLFileVersionDiffMessageListener.DESTINATION_NAME, null, 0);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncServletContextListener.class);

	private MessageListener _syncDLFileVersionDiffMessageListener;
	private MessageListener _syncDLObjectMessageListener;

}