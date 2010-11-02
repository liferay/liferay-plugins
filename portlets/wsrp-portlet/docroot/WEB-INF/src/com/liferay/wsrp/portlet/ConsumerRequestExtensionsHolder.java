/*
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

package com.liferay.wsrp.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.util.portlet.PortletProps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oasis.names.tc.wsrp.v2.types.UserProfile;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class ConsumerRequestExtensionsHolder {
	public static void addClientAttributes(
		List<MessageElement> clientAttributes) {

		_instance._addClientAttributes(clientAttributes);
	}

	public static void addUserProfileAttributes(
		UserProfile userProfile, User user) {

		_instance._addUserProfileAttributes(userProfile, user);
	}

	private ConsumerRequestExtensionsHolder() {
		String[] consumerRequestExtensions = PortletProps.getArray(
			"consumer.request.extensions");

		try {
			if (consumerRequestExtensions.length > 0) {
				_consumerRequestExtensions =
					new ArrayList<ConsumerRequestExtension> (
						consumerRequestExtensions.length);

				for (String consumerRequestExtensionClazz :
						consumerRequestExtensions) {

					ConsumerRequestExtension consumerRequestExtension =
						(ConsumerRequestExtension)Class.forName(
							consumerRequestExtensionClazz).newInstance();

					_consumerRequestExtensions.add(consumerRequestExtension);
				}
			}
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to add extension", e);
			}
			throw new RuntimeException(e);
		}
	}


	private void _addClientAttributes(List<MessageElement> clientAttributes) {
		for (ConsumerRequestExtension consumerRequestExtension :
				_consumerRequestExtensions) {

			consumerRequestExtension.addClientAttributes(clientAttributes);
		}
	}

	private void _addUserProfileAttributes(UserProfile userProfile, User user) {
		for (ConsumerRequestExtension consumerRequestExtension :
				_consumerRequestExtensions) {

			consumerRequestExtension.addUserProfileAttributes(
				userProfile, user);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConsumerRequestExtensionsHolder.class);

	private static ConsumerRequestExtensionsHolder _instance =
		new ConsumerRequestExtensionsHolder();

	private List<ConsumerRequestExtension> _consumerRequestExtensions =
		Collections.EMPTY_LIST;
}
