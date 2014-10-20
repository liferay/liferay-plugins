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

import com.liferay.calendar.CalendarResourceCodeException;
import com.liferay.calendar.CalendarResourceNameException;
import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.base.CalendarResourceLocalServiceBaseImpl;
import com.liferay.calendar.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Bruno Basto
 * @author Marcellus Tavares
 * @author Andrea Di Giorgi
 */
public class CalendarResourceLocalServiceImpl
	extends CalendarResourceLocalServiceBaseImpl {

	@Override
	public CalendarResource addCalendarResource(
			long userId, long groupId, long classNameId, long classPK,
			String classUuid, String code, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		// Calendar resource

		User user = userPersistence.findByPrimaryKey(userId);

		long calendarResourceId = counterLocalService.increment();

		if (classNameId ==
				classNameLocalService.getClassNameId(CalendarResource.class)) {

			classPK = calendarResourceId;
		}

		if (PortletPropsValues.CALENDAR_RESOURCE_FORCE_AUTOGENERATE_CODE ||
			Validator.isNull(code)) {

			code = String.valueOf(calendarResourceId);
		}
		else {
			code = code.trim();
			code = StringUtil.toUpperCase(code);
		}

		Date now = new Date();

		validate(groupId, classNameId, classPK, code, nameMap);

		CalendarResource calendarResource = calendarResourcePersistence.create(
			calendarResourceId);

		calendarResource.setUuid(serviceContext.getUuid());
		calendarResource.setGroupId(groupId);
		calendarResource.setCompanyId(user.getCompanyId());
		calendarResource.setUserId(user.getUserId());
		calendarResource.setUserName(user.getFullName());
		calendarResource.setCreateDate(serviceContext.getCreateDate(now));
		calendarResource.setModifiedDate(serviceContext.getModifiedDate(now));
		calendarResource.setClassNameId(classNameId);
		calendarResource.setClassPK(classPK);
		calendarResource.setClassUuid(classUuid);
		calendarResource.setCode(code);
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setActive(active);

		calendarResourcePersistence.update(calendarResource);

		// Resources

		resourceLocalService.addModelResources(
			calendarResource, serviceContext);

		// Calendar

		if (!ExportImportThreadLocal.isImportInProcess()) {
			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			calendarLocalService.addCalendar(
				userId, calendarResource.getGroupId(), calendarResourceId,
				nameMap, descriptionMap, calendarResource.getTimeZoneId(),
				PortletPropsValues.CALENDAR_COLOR_DEFAULT, true, false, false,
				serviceContext);
		}

		// Asset

		updateAsset(
			calendarResource.getUserId(), calendarResource,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return calendarResource;
	}

	@Override
	@SystemEvent(
		action = SystemEventConstants.ACTION_SKIP,
		type = SystemEventConstants.TYPE_DELETE)
	public CalendarResource deleteCalendarResource(
			CalendarResource calendarResource)
		throws PortalException {

		// Calendar resource

		calendarResourcePersistence.remove(calendarResource);

		// Resources

		resourceLocalService.deleteResource(
			calendarResource, ResourceConstants.SCOPE_INDIVIDUAL);

		// Calendars

		List<Calendar> calendars = calendarPersistence.findByG_C(
			calendarResource.getGroupId(),
			calendarResource.getCalendarResourceId());

		for (Calendar calendar : calendars) {
			calendarPersistence.remove(calendar);

			resourceLocalService.deleteResource(
				calendar, ResourceConstants.SCOPE_INDIVIDUAL);

			calendarNotificationTemplateLocalService.
				deleteCalendarNotificationTemplates(calendar.getCalendarId());
		}

		// Calendar bookings

		List<CalendarBooking> calendarBookings =
			calendarBookingPersistence.findByCalendarResourceId(
				calendarResource.getCalendarResourceId());

		for (CalendarBooking calendarBooking : calendarBookings) {
			calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
		}

		return calendarResource;
	}

	@Override
	public CalendarResource deleteCalendarResource(long calendarResourceId)
		throws PortalException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		return calendarResourceLocalService.deleteCalendarResource(
			calendarResource);
	}

	@Override
	public void deleteCalendarResources(long groupId) throws PortalException {
		List<CalendarResource> calendarResources =
			calendarResourcePersistence.findByGroupId(groupId);

		for (CalendarResource calendarResource : calendarResources) {
			calendarResourceLocalService.deleteCalendarResource(
				calendarResource);
		}
	}

	@Override
	public CalendarResource fetchCalendarResource(
		long classNameId, long classPK) {

		return calendarResourcePersistence.fetchByC_C(classNameId, classPK);
	}

	@Override
	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException {

		return calendarResourcePersistence.findByPrimaryKey(calendarResourceId);
	}

	@Override
	public List<CalendarResource> getCalendarResources(long groupId) {
		return calendarResourcePersistence.findByGroupId(groupId);
	}

	@Override
	public List<CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator,
		int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return calendarResourceFinder.findByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, code, name, description, active,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CalendarResource> searchByKeywords(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active, boolean andOperator, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return calendarResourceFinder.findByKeywords(
			companyId, groupIds, classNameIds, keywords, active, start, end,
			orderByComparator);
	}

	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active) {

		return calendarResourceFinder.countByKeywords(
			companyId, groupIds, classNameIds, keywords, active);
	}

	@Override
	public int searchCount(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator) {

		return calendarResourceFinder.countByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, code, name, description, active,
			andOperator);
	}

	@Override
	public void updateAsset(
			long userId, CalendarResource calendarResource,
			long[] assetCategoryIds, String[] assetTagNames)
		throws PortalException {

		assetEntryLocalService.updateEntry(
			userId, calendarResource.getGroupId(),
			calendarResource.getCreateDate(),
			calendarResource.getModifiedDate(),
			CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId(),
			calendarResource.getUuid(), 0, assetCategoryIds, assetTagNames,
			true, null, null, null, ContentTypes.TEXT,
			calendarResource.getName(), calendarResource.getDescription(), null,
			null, null, 0, 0, null, false);
	}

	@Override
	public CalendarResource updateCalendarResource(
			long calendarResourceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		// Calendar resource

		validate(nameMap);

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		calendarResource.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setActive(active);

		calendarResourcePersistence.update(calendarResource);

		// Asset

		updateAsset(
			calendarResource.getUserId(), calendarResource,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return calendarResource;
	}

	protected void validate(
			long groupId, long classNameId, long classPK, String code,
			Map<Locale, String> nameMap)
		throws PortalException {

		validate(nameMap);

		if (Validator.isNull(code) || (code.indexOf(CharPool.SPACE) != -1)) {
			throw new CalendarResourceCodeException();
		}

		if (calendarResourcePersistence.countByG_C(groupId, code) > 0) {
			throw new DuplicateCalendarResourceException();
		}

		CalendarResource calendarResource =
			calendarResourcePersistence.fetchByC_C(classNameId, classPK);

		if (calendarResource != null) {
			throw new DuplicateCalendarResourceException();
		}
	}

	protected void validate(Map<Locale, String> nameMap)
		throws PortalException {

		Locale locale = LocaleUtil.getDefault();

		if (nameMap.isEmpty() || Validator.isNull(nameMap.get(locale))) {
			throw new CalendarResourceNameException();
		}
	}

}