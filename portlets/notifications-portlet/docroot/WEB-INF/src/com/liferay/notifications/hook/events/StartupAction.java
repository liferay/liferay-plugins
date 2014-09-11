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

package com.liferay.notifications.hook.events;

import com.liferay.compat.portal.util.JavaFieldsParser;
import com.liferay.notifications.util.PortletPropsKeys;
import com.liferay.notifications.util.PortletPropsValues;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Lin Cui
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void addUserNotificationDefinitions(String xml, String portletId)
		throws Exception {

		Class<?> clazz = getClass();

		xml = JavaFieldsParser.parse(clazz.getClassLoader(), xml);

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		for (Element definitionElement : rootElement.elements("definition")) {
			String modelName = definitionElement.elementText("model-name");

			long classNameId = 0;

			if (Validator.isNotNull(modelName)) {
				classNameId = PortalUtil.getClassNameId(modelName);
			}

			int notificationType = GetterUtil.getInteger(
				definitionElement.elementText("notification-type"));

			String description = GetterUtil.getString(
				definitionElement.elementText("description"));

			UserNotificationDefinition userNotificationDefinition =
				new UserNotificationDefinition(
					portletId, classNameId, notificationType, description);

			for (Element deliveryTypeElement :
					definitionElement.elements("delivery-type")) {

				String name = deliveryTypeElement.elementText("name");
				int type = GetterUtil.getInteger(
					deliveryTypeElement.elementText("type"));
				boolean defaultValue = GetterUtil.getBoolean(
					deliveryTypeElement.elementText("default"));
				boolean modifiable = GetterUtil.getBoolean(
					deliveryTypeElement.elementText("modifiable"));

				userNotificationDefinition.addUserNotificationDeliveryType(
					new UserNotificationDeliveryType(
						name, type, defaultValue, modifiable));
			}

			UserNotificationManagerUtil.addUserNotificationDefinition(
				portletId, userNotificationDefinition);
		}
	}

	protected void doRun() throws Exception {
		initUserNotificationDefinitions();
		initUserNotificationHandlers();
	}

	protected void initUserNotificationDefinitions() {
		String[] portletIds = PortletPropsValues.USER_NOTIFICATIONS_PORTLET_IDS;

		for (String portletId : portletIds) {
			UserNotificationManagerUtil.deleteUserNotificationDefinitions(
				portletId);

			Filter filter = new Filter(portletId);

			String userNotificationDefinitionsLocation = PortletProps.get(
				PortletPropsKeys.USER_NOTIFICATIONS_DEFINITIONS, filter);

			try {
				String xml = ContentUtil.get(
					userNotificationDefinitionsLocation);

				addUserNotificationDefinitions(xml, portletId);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add user notification definitions " +
							userNotificationDefinitionsLocation);
				}
			}
		}
	}

	protected void initUserNotificationHandlers() {
		String[] portletIds = PortletPropsValues.USER_NOTIFICATIONS_PORTLET_IDS;

		for (String portletId : portletIds) {
			Filter filter = new Filter(portletId);

			String userNotificationHandlerClassName = PortletProps.get(
				PortletPropsKeys.USER_NOTIFICATIONS_HANDLER, filter);

			try {
				UserNotificationHandler userNotificationHandler =
					(UserNotificationHandler)
						InstanceFactory.newInstance(
							userNotificationHandlerClassName);

				UserNotificationManagerUtil.deleteUserNotificationHandler(
					userNotificationHandler);

				UserNotificationManagerUtil.addUserNotificationHandler(
					userNotificationHandler);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add user notification handler " +
							userNotificationHandlerClassName);
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(StartupAction.class);

}