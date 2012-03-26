/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.portlet;

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.NoSuchResourceException;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.util.WebKeys;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 */
public class CalendarPortlet extends MVCPortlet {

	public void deleteCalendarResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		CalendarResourceServiceUtil.deleteCalendarResource(calendarResourceId);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			CalendarResource calendarResource = null;

			long calendarResourceId = ParamUtil.getLong(
				renderRequest, "calendarResourceId");

			if (calendarResourceId > 0) {
				calendarResource =
					CalendarResourceServiceUtil.getCalendarResource(
						calendarResourceId);
			}

			renderRequest.setAttribute(
				WebKeys.CALENDAR_RESOURCE, calendarResource);
		}
		catch (Exception e) {
			if (e instanceof NoSuchResourceException) {
				SessionErrors.add(renderRequest, e.getClass().getName());
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void updateCalendarResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		long defaultCalendarId = ParamUtil.getLong(
			actionRequest, "defaultCalendarId");
		String code = ParamUtil.getString(actionRequest, "code");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String type = ParamUtil.getString(actionRequest, "type");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarResource.class.getName(), actionRequest);

		if (calendarResourceId <= 0) {
			CalendarResourceServiceUtil.addCalendarResource(
				serviceContext.getScopeGroupId(), null, 0,
				PortalUUIDUtil.generate(), defaultCalendarId, code, nameMap,
				descriptionMap, type, active, serviceContext);
		}
		else {
			CalendarResourceServiceUtil.updateCalendarResource(
				calendarResourceId, defaultCalendarId, code, nameMap,
				descriptionMap, type, active, serviceContext);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateCalendarResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

}