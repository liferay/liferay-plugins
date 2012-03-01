package com.liferay.calendar.portlet;

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;


public class EnterpriseCalendarPortlet extends MVCPortlet {

	public void deleteResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");

		CalendarResourceServiceUtil.deleteCalendarResource(calendarResourceId);
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		super.processAction(actionRequest, actionResponse);

		if (!copyRequestParameters && !SessionErrors.isEmpty(actionRequest)) {
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
		}
	}

	public void updateResource(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long calendarResourceId = ParamUtil.getLong(
			actionRequest, "calendarResourceId");
		String code = ParamUtil.getString(actionRequest, "code");
		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		String type = ParamUtil.getString(actionRequest, "type");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		String cmd = ParamUtil.get(
			actionRequest, Constants.CMD, StringPool.BLANK);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalendarResource.class.getName(), actionRequest);

		if (cmd.equals(Constants.UPDATE)) {
			CalendarResourceServiceUtil.updateCalendarResource(
				calendarResourceId, code, nameMap, descriptionMap, type, active,
				serviceContext);
		} else {
			CalendarResourceServiceUtil.addCalendarResource(
				serviceContext.getScopeGroupId(), null, 0,
				PortalUUIDUtil.generate(), code, nameMap, descriptionMap, type,
				active, serviceContext);
		}
	}

	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateCalendarResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

}
