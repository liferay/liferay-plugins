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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerType;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.persistence.DLSyncEventActionableDynamicQuery;
import com.liferay.sync.messaging.DLSyncEventMessageListener;
import com.liferay.sync.messaging.SyncMaintenanceMessageListener;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.service.SyncPreferencesLocalServiceUtil;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;
import com.liferay.sync.util.SyncUtil;
import com.liferay.sync.util.VerifyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	protected void consumeDLSyncEvents() throws Exception {
		final long latestModifiedTime =
			SyncDLObjectLocalServiceUtil.getLatestModifiedTime();

		ActionableDynamicQuery actionableDynamicQuery =
			new DLSyncEventActionableDynamicQuery() {

				@Override
				protected void addCriteria(DynamicQuery dynamicQuery) {
					Property modifiedTime = PropertyFactoryUtil.forName(
						"modifiedTime");

					dynamicQuery.add(modifiedTime.gt(latestModifiedTime));
				}

				@Override
				protected void performAction(Object object)
					throws PortalException, SystemException {

					Message message = new Message();

					Map<String, Object> values = new HashMap<String, Object>();

					DLSyncEvent dlSyncEvent = (DLSyncEvent)object;

					values.put("event", dlSyncEvent.getEvent());
					values.put("modifiedTime", dlSyncEvent.getModifiedTime());
					values.put("type", dlSyncEvent.getType());
					values.put("typePK", dlSyncEvent.getTypePK());

					message.setValues(values);

					MessageBusUtil.sendMessage(
						DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR,
						message);
				}
			};

		actionableDynamicQuery.performActions();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		MessageBusUtil.unregisterMessageListener(
			DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR,
			_dlSyncEventMessageListener);

		MessageBusUtil.unregisterMessageListener(
			SyncMaintenanceMessageListener.DESTINATION_NAME,
			_syncMaintenanceMessageListener);

		SchedulerEngineHelperUtil.unschedule(
			SyncMaintenanceMessageListener.class.getName(),
			StorageType.MEMORY_CLUSTERED);
	}

	@Override
	protected void doPortalInit() {
		try {
			if (PortletPropsValues.SYNC_VERIFY) {
				VerifyUtil.verify();
			}

			List<Company> companies = CompanyLocalServiceUtil.getCompanies();

			for (Company company : companies) {
				boolean lanEnabled = PrefsPropsUtil.getBoolean(
					company.getCompanyId(), PortletPropsKeys.SYNC_LAN_ENABLED,
					PortletPropsValues.SYNC_LAN_ENABLED);

				if (lanEnabled) {
					try {
						SyncUtil.enableLanSync(company.getCompanyId());
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

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

				SyncPreferencesLocalServiceUtil.enableOAuth(
					company.getCompanyId(), serviceContext);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_dlSyncEventMessageListener = new DLSyncEventMessageListener();

		registerMessageListener(
			_dlSyncEventMessageListener,
			DestinationNames.DOCUMENT_LIBRARY_SYNC_EVENT_PROCESSOR);

		_syncMaintenanceMessageListener = new SyncMaintenanceMessageListener();

		registerMessageListener(
			_syncMaintenanceMessageListener,
			SyncMaintenanceMessageListener.DESTINATION_NAME);

		scheduleSyncMaintenanceMessageListener();

		try {
			consumeDLSyncEvents();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
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

	protected void scheduleSyncMaintenanceMessageListener() {
		try {
			SchedulerEntry schedulerEntry = new SchedulerEntryImpl();

			schedulerEntry.setEventListenerClass(
				SyncMaintenanceMessageListener.class.getName());
			schedulerEntry.setTimeUnit(TimeUnit.HOUR);
			schedulerEntry.setTriggerType(TriggerType.SIMPLE);
			schedulerEntry.setTriggerValue(1);

			SchedulerEngineHelperUtil.schedule(
				schedulerEntry.getTrigger(), StorageType.MEMORY_CLUSTERED, null,
				SyncMaintenanceMessageListener.DESTINATION_NAME, null, 0);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncServletContextListener.class);

	private MessageListener _dlSyncEventMessageListener;
	private MessageListener _syncMaintenanceMessageListener;

}