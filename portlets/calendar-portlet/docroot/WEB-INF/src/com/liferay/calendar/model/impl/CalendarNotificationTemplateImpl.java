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

package com.liferay.calendar.model.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * The extended model implementation for the CalendarNotificationTemplate service. Represents a row in the &quot;CalendarNotificationTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.calendar.model.CalendarNotificationTemplate} interface.
 * </p>
 *
 * @author Eduardo Lundgren
 */
public class CalendarNotificationTemplateImpl
	extends CalendarNotificationTemplateBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a calendar notification template model instance should use the {@link com.liferay.calendar.model.CalendarNotificationTemplate} interface instead.
	 */
	public CalendarNotificationTemplateImpl() {
	}

	public Properties getNotificationSettingsProperties() {
		Properties properties = new Properties();
		
		try {
			properties.load(new StringReader(getNotificationSettings()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
}