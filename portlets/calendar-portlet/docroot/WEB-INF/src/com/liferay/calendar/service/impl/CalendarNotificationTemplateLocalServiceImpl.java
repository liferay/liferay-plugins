/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the calendar notification template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.calendar.service.CalendarNotificationTemplateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl
 * @see com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil
 */
public class CalendarNotificationTemplateLocalServiceImpl
	extends CalendarNotificationTemplateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.calendar.service.CalendarNotificationTemplateLocalServiceUtil} to access the calendar notification template local service.
	 */

	public CalendarNotificationTemplate createCalendarNotificationTemplate(
			long calendarId, NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			String subject, String body, String notificationSettings) {

		CalendarNotificationTemplate template =
				calendarNotificationTemplateLocalService.
				createCalendarNotificationTemplate(0);

		template.setCalendarId(calendarId);
		template.setNotificationType(notificationType.getValue());
		template.setNotificationTemplateType(notificationTemplateType.getValue());
		template.setSubject(subject);
		template.setBody(body);
		template.setNotificationSettings(notificationSettings);

		return template;
	}

	public CalendarNotificationTemplate getCalendarNotificationTemplate(
			long calendarId, NotificationType notificationType,
			NotificationTemplateType notificationTemplateType)
		throws SystemException {

		return calendarNotificationTemplatePersistence.fetchByC_NT_NTT(
				calendarId, notificationType.getValue(),
				notificationTemplateType.getValue());
	}

	public CalendarNotificationTemplate addCalendarNotificationTemplate(
			long calendarId, NotificationType notificationType,
			NotificationTemplateType notificationTemplateType,
			String subject, String body, String notificationSettings)
		throws SystemException {

		long calendarNotificationTemplateId = counterLocalService.increment();

		CalendarNotificationTemplate calendarNotificationTemplate =
				createCalendarNotificationTemplate(calendarId,
						notificationType, notificationTemplateType,
						subject, body, notificationSettings);

		calendarNotificationTemplate.setCalendarNotificationTemplateId(
				calendarNotificationTemplateId);
		addCalendarNotificationTemplate(calendarNotificationTemplate);

		return calendarNotificationTemplate;

	}

}