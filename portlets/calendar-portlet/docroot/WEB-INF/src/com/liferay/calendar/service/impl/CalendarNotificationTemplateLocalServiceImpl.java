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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.notification.NotificationTemplateType;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.service.base.CalendarNotificationTemplateLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Adam Brandizzi
 * @author Marcellus Tavares
 */
public class CalendarNotificationTemplateLocalServiceImpl
	extends CalendarNotificationTemplateLocalServiceBaseImpl {

	@Override
	public CalendarNotificationTemplate addCalendarNotificationTemplate(
			long userId, long calendarId, NotificationType notificationType,
			String notificationTypeSettings,
			NotificationTemplateType notificationTemplateType, String subject,
			String body, ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Calendar calendar = calendarPersistence.findByPrimaryKey(calendarId);
		Date now = new Date();

		long calendarNotificationTemplateId = counterLocalService.increment();

		CalendarNotificationTemplate calendarNotificationTemplate =
			calendarNotificationTemplatePersistence.create(
				calendarNotificationTemplateId);

		calendarNotificationTemplate.setUuid(serviceContext.getUuid());
		calendarNotificationTemplate.setGroupId(calendar.getGroupId());
		calendarNotificationTemplate.setCompanyId(user.getCompanyId());
		calendarNotificationTemplate.setUserId(user.getUserId());
		calendarNotificationTemplate.setUserName(user.getFullName());
		calendarNotificationTemplate.setCreateDate(
			serviceContext.getCreateDate(now));
		calendarNotificationTemplate.setModifiedDate(
			serviceContext.getModifiedDate(now));
		calendarNotificationTemplate.setCalendarId(calendarId);
		calendarNotificationTemplate.setNotificationType(
			notificationType.getValue());
		calendarNotificationTemplate.setNotificationTypeSettings(
			notificationTypeSettings);
		calendarNotificationTemplate.setNotificationTemplateType(
			notificationTemplateType.getValue());
		calendarNotificationTemplate.setSubject(subject);
		calendarNotificationTemplate.setBody(body);

		return calendarNotificationTemplatePersistence.update(
			calendarNotificationTemplate);
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CalendarNotificationTemplate deleteCalendarNotificationTemplate(
		CalendarNotificationTemplate calendarNotificationTemplate) {

		return calendarNotificationTemplatePersistence.remove(
			calendarNotificationTemplate);
	}

	@Override
	public void deleteCalendarNotificationTemplates(long calendarId) {
		List<CalendarNotificationTemplate> calendarNotificationTemplates =
			calendarNotificationTemplatePersistence.findByCalendarId(
				calendarId);

		for (CalendarNotificationTemplate calendarNotificationTemplate :
				calendarNotificationTemplates) {

			calendarNotificationTemplateLocalService.
				deleteCalendarNotificationTemplate(
					calendarNotificationTemplate);
		}
	}

	@Override
	public CalendarNotificationTemplate fetchCalendarNotificationTemplate(
		long calendarId, NotificationType notificationType,
		NotificationTemplateType notificationTemplateType) {

		return calendarNotificationTemplatePersistence.fetchByC_NT_NTT(
			calendarId, notificationType.getValue(),
			notificationTemplateType.getValue());
	}

	@Override
	public CalendarNotificationTemplate updateCalendarNotificationTemplate(
			long calendarNotificationTemplateId,
			String notificationTypeSettings, String subject, String body,
			ServiceContext serviceContext)
		throws PortalException {

		CalendarNotificationTemplate calendarNotificationTemplate =
			calendarNotificationTemplatePersistence.findByPrimaryKey(
				calendarNotificationTemplateId);

		calendarNotificationTemplate.setModifiedDate(
			serviceContext.getModifiedDate(null));
		calendarNotificationTemplate.setSubject(subject);
		calendarNotificationTemplate.setBody(body);
		calendarNotificationTemplate.setNotificationTypeSettings(
			notificationTypeSettings);

		return calendarNotificationTemplatePersistence.update(
			calendarNotificationTemplate);
	}

}