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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.base.CalendarResourceLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 */
public class CalendarResourceLocalServiceImpl
	extends CalendarResourceLocalServiceBaseImpl {

	public CalendarResource addCalendarResource(
			long userId, String className, long classPK,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			boolean active, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar resource

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		if (Validator.isNull(className)) {
			className = CalendarResource.class.getName();
		}

		long classNameId = PortalUtil.getClassNameId(className);

		if (hasCalendarResource(classNameId, classPK)) {
			throw new DuplicateCalendarResourceException();
		}

		if (className.equals(Group.class.getName()) ||
			className.equals(User.class.getName())) {

			long globalCalendarResourceUserId = getGlobalCalendarResourceUserId(
				className, classPK);

			if (globalCalendarResourceUserId > 0) {
				userId = globalCalendarResourceUserId;

				user = userPersistence.findByPrimaryKey(userId);
			}
		}

		Date now = new Date();

		long calendarResourceId = counterLocalService.increment();

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
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setActive(active);
		calendarResource.setExpandoBridgeAttributes(serviceContext);

		calendarResourcePersistence.update(calendarResource, false);

		// Resources

		resourceLocalService.addModelResources(
			calendarResource, serviceContext);

		return calendarResource;
	}

	@Override
	public void deleteCalendarResource(CalendarResource calendarResource)
		throws PortalException, SystemException {

		// Calendar resource

		calendarResourcePersistence.remove(calendarResource);

		// Resources

		resourceLocalService.deleteResource(
			calendarResource.getCompanyId(), CalendarResource.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			calendarResource.getCalendarResourceId());

		// Calendar bookings

		List<CalendarBooking> calendarBookings =
			calendarBookingPersistence.findByCalendarResourceId(
				calendarResource.getCalendarResourceId());

		for (CalendarBooking calendarBooking : calendarBookings) {
			calendarBookingLocalService.deleteCalendarBooking(calendarBooking);
		}

		// Expando

		expandoValueLocalService.deleteValues(
			CalendarResource.class.getName(),
			calendarResource.getCalendarResourceId());
	}

	@Override
	public void deleteCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		deleteCalendarResource(calendarResource);
	}

	@Override
	public CalendarResource getCalendarResource(long calendarResourceId)
		throws PortalException, SystemException {

		return calendarResourcePersistence.findByPrimaryKey(calendarResourceId);
	}

	public CalendarResource getCalendarResource(String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return calendarResourcePersistence.findByC_C(classNameId, classPK);
	}

	public List<CalendarResource> getCalendarResources(
			boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.findByActive(
			active, start, end, orderByComparator);
	}

	public int getCalendarResourcesCount(boolean active)
		throws SystemException {

		return calendarResourcePersistence.countByActive(active);
	}

	public List<CalendarResource> getCompanyCalendarResources(
			long companyId, String name, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.findByC_N_A(
			companyId, name, active, start, end, orderByComparator);
	}

	public int getCompanyCalendarResourcesCount(
			long companyId, String name, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.countByC_N_A(
			companyId, name, active);
	}

	public List<CalendarResource> getGroupCalendarResources(
			long groupId, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.findByG_A(
			groupId, active, start, end, orderByComparator);
	}

	public List<CalendarResource> getGroupCalendarResources(
			long groupId, String name, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.findByG_N_A(
			groupId, name, active, start, end, orderByComparator);
	}

	public int getGroupCalendarResourcesCount(long groupId, boolean active)
		throws SystemException {

		return calendarResourcePersistence.countByG_A(groupId, active);
	}

	public int getGroupCalendarResourcesCount(
			long groupId, String name, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return calendarResourcePersistence.countByG_N_A(groupId, name, active);
	}

	public List<CalendarResource> search(
			long companyId, long[] groupIds, String name, String description,
			Boolean active, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			companyId, groupIds, name, description, active, andOperator);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, String name, String description,
			Boolean active, boolean andOperator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			companyId, groupIds, name, description, active, andOperator);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public CalendarResource updateCalendarResource(
			long calendarResourceId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Calendar resource

		CalendarResource calendarResource =
			calendarResourcePersistence.findByPrimaryKey(calendarResourceId);

		calendarResource.setModifiedDate(serviceContext.getModifiedDate(null));
		calendarResource.setNameMap(nameMap);
		calendarResource.setDescriptionMap(descriptionMap);
		calendarResource.setActive(active);
		calendarResource.setExpandoBridgeAttributes(serviceContext);

		calendarResourcePersistence.update(calendarResource, false);

		// Resources

		resourceLocalService.updateModelResources(
			calendarResource, serviceContext);

		return calendarResource;
	}

	protected DynamicQuery buildDynamicQuery(
		long companyId, long[] groupIds, String name, String description,
		Boolean active, boolean andOperator) {

		Junction junction = null;

		if (andOperator) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		Map<String, String> terms = new HashMap<String, String>();

		if (Validator.isNotNull(name)) {
			terms.put("name", name);
		}

		if (Validator.isNotNull(description)) {
			terms.put("description", description);
		}

		for (Map.Entry<String, String> entry : terms.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			for (String keyword : CalendarUtil.splitKeywords(value)) {
				Criterion criterion = RestrictionsFactoryUtil.ilike(
					key, StringUtil.quote(keyword, StringPool.PERCENT));

				disjunction.add(criterion);
			}

			junction.add(disjunction);
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CalendarBooking.class, getClass().getClassLoader());

		if (companyId > 0) {
			Property property = PropertyFactoryUtil.forName("companyId");

			dynamicQuery.add(property.eq(companyId));
		}

		if ((groupIds != null) && (groupIds.length > 0)) {
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			Property property = PropertyFactoryUtil.forName("groupId");

			for (long groupId : groupIds) {
				disjunction.add(property.eq(groupId));
			}

			dynamicQuery.add(disjunction);
		}

		if (active != null) {
			Property property = PropertyFactoryUtil.forName("active");

			dynamicQuery.add(property.eq(active));
		}

		return dynamicQuery.add(junction);
	}

	protected long getGlobalCalendarResourceUserId(
			String className, long classPK)
		throws PortalException, SystemException {

		long userId = 0;

		if (className.equals(Group.class.getName())) {
			Group group = GroupLocalServiceUtil.getGroup(classPK);

			userId = group.getCreatorUserId();
		}
		else if (className.equals(User.class.getName())) {
			userId = classPK;
		}

		return userId;
	}

	protected boolean hasCalendarResource(long classNameId, long classPK)
		throws SystemException {

		int count = calendarResourcePersistence.countByC_C(
			classNameId, classPK);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}