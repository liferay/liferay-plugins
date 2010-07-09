/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.service;

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.DoubleWrapper;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryLocalServiceClp implements MeetupsEntryLocalService {
	public MeetupsEntryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.socialnetworking.model.MeetupsEntry");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMeetupsEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createMeetupsEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteMeetupsEntry(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		try {
			_classLoaderProxy.invoke("deleteMeetupsEntry",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.socialnetworking.model.MeetupsEntry");
		}

		try {
			_classLoaderProxy.invoke("deleteMeetupsEntry",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object paramObj3 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQueryCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	public com.liferay.socialnetworking.model.MeetupsEntry getMeetupsEntry(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntries",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialnetworking.model.MeetupsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getMeetupsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntriesCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.socialnetworking.model.MeetupsEntry");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.socialnetworking.model.MeetupsEntry");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsEntry",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		long userId, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		int totalAttendees, int maxAttendees, double price, byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(startDateMonth);

		Object paramObj4 = new IntegerWrapper(startDateDay);

		Object paramObj5 = new IntegerWrapper(startDateYear);

		Object paramObj6 = new IntegerWrapper(startDateHour);

		Object paramObj7 = new IntegerWrapper(startDateMinute);

		Object paramObj8 = new IntegerWrapper(endDateMonth);

		Object paramObj9 = new IntegerWrapper(endDateDay);

		Object paramObj10 = new IntegerWrapper(endDateYear);

		Object paramObj11 = new IntegerWrapper(endDateHour);

		Object paramObj12 = new IntegerWrapper(endDateMinute);

		Object paramObj13 = new IntegerWrapper(totalAttendees);

		Object paramObj14 = new IntegerWrapper(maxAttendees);

		Object paramObj15 = new DoubleWrapper(price);

		Object paramObj16 = ClpSerializer.translateInput(thumbnail);

		if (thumbnail == null) {
			paramObj16 = new NullWrapper("[B");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMeetupsEntry",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13,
						paramObj14, paramObj15, paramObj16
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntriesByCompany(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntriesByCompany",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialnetworking.model.MeetupsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntriesByUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntriesByUser",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialnetworking.model.MeetupsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		long userId, long meetupsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int totalAttendees, int maxAttendees, double price,
		byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(meetupsEntryId);

		Object paramObj2 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new IntegerWrapper(startDateMonth);

		Object paramObj5 = new IntegerWrapper(startDateDay);

		Object paramObj6 = new IntegerWrapper(startDateYear);

		Object paramObj7 = new IntegerWrapper(startDateHour);

		Object paramObj8 = new IntegerWrapper(startDateMinute);

		Object paramObj9 = new IntegerWrapper(endDateMonth);

		Object paramObj10 = new IntegerWrapper(endDateDay);

		Object paramObj11 = new IntegerWrapper(endDateYear);

		Object paramObj12 = new IntegerWrapper(endDateHour);

		Object paramObj13 = new IntegerWrapper(endDateMinute);

		Object paramObj14 = new IntegerWrapper(totalAttendees);

		Object paramObj15 = new IntegerWrapper(maxAttendees);

		Object paramObj16 = new DoubleWrapper(price);

		Object paramObj17 = ClpSerializer.translateInput(thumbnail);

		if (thumbnail == null) {
			paramObj17 = new NullWrapper("[B");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsEntry",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13,
						paramObj14, paramObj15, paramObj16, paramObj17
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
}