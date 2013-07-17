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
import com.liferay.calendar.model.CalendarNotificationTemplate;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarBookingExportActionableDynamicQuery;
import com.liferay.calendar.service.persistence.CalendarExportActionableDynamicQuery;
import com.liferay.calendar.service.persistence.CalendarNotificationTemplateExportActionableDynamicQuery;
import com.liferay.calendar.service.persistence.CalendarResourceExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Marcellus Tavares
 * @author Andrea Di Giorgi
 */
public class CalendarPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "calendar";

	public CalendarPortletDataHandler() {
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(Calendar.class),
			new StagedModelType(CalendarBooking.class),
			new StagedModelType(CalendarNotificationTemplate.class),
			new StagedModelType(CalendarResource.class));
		setDataLocalized(true);
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "calendars", true, false, null,
				Calendar.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "bookings", true, false, null,
				CalendarBooking.class.getName()),
			new PortletDataHandlerBoolean(
				NAMESPACE, "notification-templates", true, false,
				new PortletDataHandlerBoolean[] {
					new PortletDataHandlerBoolean(
						NAMESPACE, "referenced-content")
				},
				CalendarNotificationTemplate.class.getName())
		);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				CalendarPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		CalendarResourceLocalServiceUtil.deleteCalendarResources(
			portletDataContext.getScopeGroupId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			RESOURCE_NAME, portletDataContext.getScopeGroupId());

		Element rootElement = addExportDataRootElement(portletDataContext);

		if (portletDataContext.getBooleanParameter(NAMESPACE, "calendars")) {
			ActionableDynamicQuery resourceActionableDynamicQuery =
				new CalendarResourceExportActionableDynamicQuery(
					portletDataContext);

			resourceActionableDynamicQuery.performActions();

			ActionableDynamicQuery calendarActionableDynamicQuery =
				new CalendarExportActionableDynamicQuery(portletDataContext);

			calendarActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "bookings")) {
			ActionableDynamicQuery bookingActionableDynamicQuery =
				new CalendarBookingExportActionableDynamicQuery(
					portletDataContext);

			bookingActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "notification-templates")) {

			ActionableDynamicQuery notificationTemplateActionableDynamicQuery =
				new CalendarNotificationTemplateExportActionableDynamicQuery(
					portletDataContext);

			notificationTemplateActionableDynamicQuery.performActions();
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			RESOURCE_NAME, portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		if (portletDataContext.getBooleanParameter(NAMESPACE, "calendars")) {
			Element resourcesElement =
				portletDataContext.getImportDataGroupElement(
					CalendarResource.class);

			List<Element> resourceElements = resourcesElement.elements();

			for (Element resourceElement : resourceElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, resourceElement);
			}

			Element calendarsElement =
				portletDataContext.getImportDataGroupElement(Calendar.class);

			List<Element> calendarElements = calendarsElement.elements();

			for (Element calendarElement : calendarElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, calendarElement);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "bookings")) {
			Element bookingsElement =
				portletDataContext.getImportDataGroupElement(
					CalendarBooking.class);

			List<Element> bookingElements = bookingsElement.elements();

			for (Element bookingElement : bookingElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, bookingElement);
			}
		}

		if (portletDataContext.getBooleanParameter(
				NAMESPACE, "notification-templates")) {

			Element notificationTemplatesElement =
				portletDataContext.getImportDataGroupElement(
					CalendarNotificationTemplate.class);

			List<Element> notificationTemplateElements =
				notificationTemplatesElement.elements();

			for (Element notificationTemplateElement :
					notificationTemplateElements) {

				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, notificationTemplateElement);
			}
		}

		return portletPreferences;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery calendarActionableDynamicQuery =
			new CalendarExportActionableDynamicQuery(portletDataContext);

		calendarActionableDynamicQuery.performCount();

		ActionableDynamicQuery bookingActionableDynamicQuery =
			new CalendarBookingExportActionableDynamicQuery(portletDataContext);

		bookingActionableDynamicQuery.performCount();

		ActionableDynamicQuery notificationTemplateActionableDynamicQuery =
			new CalendarNotificationTemplateExportActionableDynamicQuery(
				portletDataContext);

		notificationTemplateActionableDynamicQuery.performCount();
	}

	protected static final String RESOURCE_NAME =
		"com.liferay.portlet.calendar";

}