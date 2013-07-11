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

package com.liferay.calendar.lar;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import java.util.Map;

/**
 * @author Andrea Di Giorgi
 */
public class CalendarBookingStagedModelDataHandler
	extends BaseStagedModelDataHandler<CalendarBooking> {

	public static final String[] CLASS_NAMES = {
		CalendarBooking.class.getName()};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(CalendarBooking calendarBooking) {
		return calendarBooking.getTitleCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, calendarBooking.getCalendar());

		Element calendarBookingElement =
			portletDataContext.getExportDataElement(calendarBooking);

		if (!calendarBooking.isMasterBooking()) {
			CalendarBooking parentCalendarBooking =
				calendarBooking.getParentCalendarBooking();

			boolean parentCalendarBookingInGlobalScope = false;

			if (parentCalendarBooking.getGroupId() ==
					portletDataContext.getCompanyGroupId()) {

				parentCalendarBookingInGlobalScope = true;
			}

			calendarBookingElement.addAttribute(
				"parent-calendar-booking-uuid",
				parentCalendarBooking.getUuid());
			calendarBookingElement.addAttribute(
				"parent-calendar-booking-in-global-scope",
				String.valueOf(parentCalendarBookingInGlobalScope));

			portletDataContext.addReferenceElement(
				calendarBooking, calendarBookingElement, parentCalendarBooking,
				PortletDataContext.REFERENCE_TYPE_PARENT, true);
		}

		portletDataContext.addClassedModel(
			calendarBookingElement,
			ExportImportPathUtil.getModelPath(calendarBooking), calendarBooking,
			CalendarPortletDataHandler.NAMESPACE);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			CalendarBooking calendarBooking)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarBooking.getUserUuid());

		long[] childCalendarIds = new long[0];
		long parentCalendarBookingId = 0;

		if (!calendarBooking.isMasterBooking()) {
			Element calendarBookingElement =
				portletDataContext.getImportDataStagedModelElement(
					calendarBooking);

			Element parentCalendarBookingElement =
				portletDataContext.getReferenceDataElement(
					calendarBookingElement, CalendarBooking.class, 0,
					calendarBooking.getParentCalendarBookingId());

			if (parentCalendarBookingElement != null) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, parentCalendarBookingElement);

				Map<Long, Long> calendarBookingIds =
					(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
						CalendarBooking.class);

				parentCalendarBookingId = MapUtil.getLong(
					calendarBookingIds,
					calendarBooking.getParentCalendarBookingId());
			}
			else {
				String parentCalendarBookingUuid =
					calendarBookingElement.attributeValue(
						"parent-calendar-booking-uuid");
				boolean parentCalendarBookingInGlobalScope =
					GetterUtil.getBoolean(
						calendarBookingElement.attributeValue(
							"parent-calendar-booking-in-global-scope"));

				long parentCalendarBookingGroupId;

				if (parentCalendarBookingInGlobalScope) {
					parentCalendarBookingGroupId =
						portletDataContext.getCompanyGroupId();
				}
				else {
					parentCalendarBookingGroupId =
						portletDataContext.getScopeGroupId();
				}

				CalendarBooking parentCalendarBooking =
					CalendarBookingLocalServiceUtil.
						fetchCalendarBookingByUuidAndGroupId(
							parentCalendarBookingUuid,
							parentCalendarBookingGroupId);

				if (parentCalendarBooking != null) {
					parentCalendarBookingId =
						parentCalendarBooking.getCalendarBookingId();
				}
			}
		}

		String calendarPath = ExportImportPathUtil.getModelPath(
			portletDataContext, Calendar.class.getName(),
			calendarBooking.getCalendarId());

		Calendar calendar = (Calendar)portletDataContext.getZipEntryAsObject(
			calendarPath);

		StagedModelDataHandlerUtil.importStagedModel(
			portletDataContext, calendar);

		Map<Long, Long> calendarIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Calendar.class);

		long calendarId = MapUtil.getLong(
			calendarIds, calendarBooking.getCalendarId(),
			calendarBooking.getCalendarId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarBooking, CalendarPortletDataHandler.NAMESPACE);

		CalendarBooking importedCalendarBooking = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarBooking existingCalendarBooking =
				CalendarBookingLocalServiceUtil.
					fetchCalendarBookingByUuidAndGroupId(
						calendarBooking.getUuid(),
						portletDataContext.getScopeGroupId());

			if (existingCalendarBooking == null) {
				serviceContext.setUuid(calendarBooking.getUuid());

				importedCalendarBooking =
					CalendarBookingLocalServiceUtil.addCalendarBooking(
						userId, calendarId, childCalendarIds,
						parentCalendarBookingId, calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.isAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						serviceContext);
			}
			else {
				importedCalendarBooking =
					CalendarBookingLocalServiceUtil.updateCalendarBooking(
						userId, existingCalendarBooking.getCalendarBookingId(),
						calendarId, calendarBooking.getTitleMap(),
						calendarBooking.getDescriptionMap(),
						calendarBooking.getLocation(),
						calendarBooking.getStartTime(),
						calendarBooking.getEndTime(),
						calendarBooking.isAllDay(),
						calendarBooking.getRecurrence(),
						calendarBooking.getFirstReminder(),
						calendarBooking.getFirstReminderType(),
						calendarBooking.getSecondReminder(),
						calendarBooking.getSecondReminderType(),
						calendarBooking.getStatus(), serviceContext);
			}
		}
		else {
			importedCalendarBooking =
				CalendarBookingLocalServiceUtil.addCalendarBooking(
					userId, calendarId, childCalendarIds,
					parentCalendarBookingId, calendarBooking.getTitleMap(),
					calendarBooking.getDescriptionMap(),
					calendarBooking.getLocation(),
					calendarBooking.getStartTime(),
					calendarBooking.getEndTime(), calendarBooking.isAllDay(),
					calendarBooking.getRecurrence(),
					calendarBooking.getFirstReminder(),
					calendarBooking.getFirstReminderType(),
					calendarBooking.getSecondReminder(),
					calendarBooking.getSecondReminderType(), serviceContext);
		}

		portletDataContext.importClassedModel(
			calendarBooking, importedCalendarBooking,
			CalendarPortletDataHandler.NAMESPACE);
	}

}