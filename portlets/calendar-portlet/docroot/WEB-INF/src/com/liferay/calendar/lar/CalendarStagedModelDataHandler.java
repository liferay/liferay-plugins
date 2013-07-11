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
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

import java.util.Map;

/**
 * @author Andrea Di Giorgi
 */
public class CalendarStagedModelDataHandler
	extends BaseStagedModelDataHandler<Calendar> {

	public static final String[] CLASS_NAMES = {Calendar.class.getName()};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Calendar calendar) {
		return calendar.getNameCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Calendar calendar)
		throws Exception {

		CalendarResource calendarResource = calendar.getCalendarResource();

		StagedModelDataHandlerUtil.exportStagedModel(
			portletDataContext, calendarResource);

		Element calendarElement = portletDataContext.getExportDataElement(
			calendar);

		portletDataContext.addClassedModel(
			calendarElement, ExportImportPathUtil.getModelPath(calendar),
			calendar, CalendarPortletDataHandler.NAMESPACE);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Calendar calendar)
		throws Exception {

		long userId = portletDataContext.getUserId(calendar.getUserUuid());

		String calendarResourcePath = ExportImportPathUtil.getModelPath(
			calendar.getGroupId(), CalendarResource.class.getName(),
			calendar.getCalendarResourceId());

		CalendarResource calendarResource =
			(CalendarResource)portletDataContext.getZipEntryAsObject(
				calendarResourcePath);

		StagedModelDataHandlerUtil.importStagedModel(
			portletDataContext, calendarResource);

		Map<Long, Long> calendarResourceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				CalendarResource.class);

		long calendarResourceId = MapUtil.getLong(
			calendarResourceIds, calendar.getCalendarResourceId(),
			calendar.getCalendarResourceId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendar, CalendarPortletDataHandler.NAMESPACE);

		Calendar importedCalendar = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Calendar existingCalendar =
				CalendarLocalServiceUtil.fetchCalendarByUuidAndGroupId(
					calendar.getUuid(), portletDataContext.getScopeGroupId());

			if (existingCalendar == null) {
				serviceContext.setUuid(calendar.getUuid());

				importedCalendar = CalendarLocalServiceUtil.addCalendar(
					userId, portletDataContext.getScopeGroupId(),
					calendarResourceId, calendar.getNameMap(),
					calendar.getDescriptionMap(), calendar.getColor(),
					calendar.isDefaultCalendar(), calendar.isEnableComments(),
					calendar.isEnableRatings(), serviceContext);
			}
			else {
				importedCalendar = CalendarLocalServiceUtil.updateCalendar(
					existingCalendar.getCalendarId(), calendar.getNameMap(),
					calendar.getDescriptionMap(), calendar.getColor(),
					calendar.isDefaultCalendar(), calendar.isEnableComments(),
					calendar.isEnableRatings(), serviceContext);
			}
		}
		else {
			importedCalendar = CalendarLocalServiceUtil.addCalendar(
				userId, portletDataContext.getScopeGroupId(),
				calendarResourceId, calendar.getNameMap(),
				calendar.getDescriptionMap(), calendar.getColor(),
				calendar.isDefaultCalendar(), calendar.isEnableComments(),
				calendar.isEnableRatings(), serviceContext);
		}

		portletDataContext.importClassedModel(
			calendar, importedCalendar, CalendarPortletDataHandler.NAMESPACE);
	}

}