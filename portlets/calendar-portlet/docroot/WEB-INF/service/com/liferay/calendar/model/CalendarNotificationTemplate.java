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

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the CalendarNotificationTemplate service. Represents a row in the &quot;CalendarNotificationTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplateModel
 * @see com.liferay.calendar.model.impl.CalendarNotificationTemplateImpl
 * @see com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl
 * @generated
 */
public interface CalendarNotificationTemplate
	extends CalendarNotificationTemplateModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portal.kernel.util.UnicodeProperties getNotificationTypeSettingsProperties();

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties notificationTypeSettingsProperties);
}