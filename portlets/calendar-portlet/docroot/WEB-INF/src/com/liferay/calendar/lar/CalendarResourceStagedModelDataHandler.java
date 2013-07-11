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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Andrea Di Giorgi
 */
public class CalendarResourceStagedModelDataHandler
	extends BaseStagedModelDataHandler<CalendarResource> {

	public static final String[] CLASS_NAMES =
		{CalendarResource.class.getName()};

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(CalendarResource calendarResource) {
		return calendarResource.getNameCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			CalendarResource calendarResource)
		throws Exception {

		Element calendarResourceElement =
			portletDataContext.getExportDataElement(calendarResource);

		portletDataContext.addClassedModel(
			calendarResourceElement,
			ExportImportPathUtil.getModelPath(calendarResource),
			calendarResource, CalendarPortletDataHandler.NAMESPACE);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			CalendarResource calendarResource)
		throws Exception {

		long userId = portletDataContext.getUserId(
			calendarResource.getUserUuid());

		String className = null;
		long classPK = 0;
		String classUuid = PortalUUIDUtil.generate();

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			calendarResource, CalendarPortletDataHandler.NAMESPACE);

		CalendarResource importedCalendarResource = null;

		if (portletDataContext.isDataStrategyMirror()) {
			CalendarResource existingCalendarResource =
				CalendarResourceLocalServiceUtil.
					fetchCalendarResourceByUuidAndGroupId(
						calendarResource.getUuid(),
						portletDataContext.getScopeGroupId());

			if (existingCalendarResource == null) {
				serviceContext.setUuid(calendarResource.getUuid());

				importedCalendarResource =
					CalendarResourceLocalServiceUtil.addCalendarResource(
						userId, portletDataContext.getScopeGroupId(), className,
						classPK, classUuid, calendarResource.getCode(),
						calendarResource.getNameMap(),
						calendarResource.getDescriptionMap(),
						calendarResource.isActive(), serviceContext);
			}
			else {
				importedCalendarResource =
					CalendarResourceLocalServiceUtil.updateCalendarResource(
						existingCalendarResource.getCalendarResourceId(),
						calendarResource.getNameMap(),
						calendarResource.getDescriptionMap(),
						calendarResource.isActive(), serviceContext);
			}
		}
		else {
			importedCalendarResource =
				CalendarResourceLocalServiceUtil.addCalendarResource(
					userId, portletDataContext.getScopeGroupId(), className,
					classPK, classUuid, calendarResource.getCode(),
					calendarResource.getNameMap(),
					calendarResource.getDescriptionMap(),
					calendarResource.isActive(), serviceContext);
		}

		portletDataContext.importClassedModel(
			calendarResource, importedCalendarResource,
			CalendarPortletDataHandler.NAMESPACE);
	}

}