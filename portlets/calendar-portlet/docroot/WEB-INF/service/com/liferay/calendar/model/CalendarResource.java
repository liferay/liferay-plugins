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

package com.liferay.calendar.model;

import com.liferay.portal.model.PermissionedModel;

/**
 * The extended model interface for the CalendarResource service. Represents a row in the &quot;CalendarResource&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eduardo Lundgren
 * @see CalendarResourceModel
 * @see com.liferay.calendar.model.impl.CalendarResourceImpl
 * @see com.liferay.calendar.model.impl.CalendarResourceModelImpl
 * @generated
 */
public interface CalendarResource extends CalendarResourceModel,
	PermissionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.calendar.model.impl.CalendarResourceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.calendar.model.Calendar> getCalendars()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.calendar.model.Calendar getDefaultCalendar()
		throws com.liferay.portal.kernel.exception.SystemException;

	public long getDefaultCalendarId()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.TimeZone getTimeZone()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getTimeZoneId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isGroup();

	public boolean isUser();
}