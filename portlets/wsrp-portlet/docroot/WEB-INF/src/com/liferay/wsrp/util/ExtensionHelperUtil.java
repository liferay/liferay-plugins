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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class ExtensionHelperUtil {

	public static void initialize() {

		String extensionHelperClassName = PortletProps.get(
			_EXTENSION_HELPER_IMPL_PROPERTY);

		try {
			if (Validator.isNotNull(extensionHelperClassName)) {
				_extensionHelper = (ExtensionHelper)InstanceFactory.newInstance(
					extensionHelperClassName);
			}
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to initialize ExtensionHelper", e);
			}
		}

		if (_extensionHelper == null) {
			_extensionHelper = new AttributeBasedExtensionHelper();
		}

	}

	public static void addMessageElement(
		List<MessageElement> messageElements, String name, String value) {

		_extensionHelper.addMessageElement(messageElements, name, value);
	}

	public static Extension[] getExtensions(
		List<MessageElement> messageElements) {
		return _extensionHelper.getExtensions(messageElements);
	}

	public static Extension[] getExtensions(String name, String value) {
		return _extensionHelper.getExtensions(name, value);
	}

	public static MessageElement[] getMessageElements(Extension[] extensions) {
		return _extensionHelper.getMessageElements(extensions);
	}

	public static String getNameAttribute(MessageElement messageElement) {
		return _extensionHelper.getNameAttribute(messageElement);
	}

	private static final String _EXTENSION_HELPER_IMPL_PROPERTY =
		"extension.helper.impl";

	private static ExtensionHelper _extensionHelper;

	private static Log _log = LogFactoryUtil.getLog(ExtensionHelperUtil.class);

}