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

package com.liferay.wsrp.util;

import java.util.ArrayList;
import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael Young
 *
 */
public class ExtensionUtil {

	public static void addMessageElement(
		List<MessageElement> messageElements, String localPart, String value) {

		MessageElement messageElement = new MessageElement(
			"http://www.liferay.com/wsrp", localPart);

		messageElement.setValue(value);

		messageElements.add(messageElement);
	}

	public static Extension[] getExtensions(
		List<MessageElement> messageElements) {

		int size = messageElements.size();

		Extension[] extensions = new Extension[size];

		// Wrap the extension in an extension to be compatible with Oracle's
		// producer

		for (int i = 0; i < size; i ++) {
			MessageElement messageElement = messageElements.get(i);

			extensions[i] =
				new Extension(new MessageElement[] {messageElement});
		}

		return extensions;
	}

	public static Extension[] getExtensions(String localPart, String value) {
		List<MessageElement> messageElements = new ArrayList<MessageElement>();

		addMessageElement(messageElements, localPart, value);

		return getExtensions(messageElements);
	}

	public static MessageElement[] getMessageElements(
		Extension[] extensions) {

		if ((extensions == null) || (extensions.length <= 0)) {
			return null;
		}

		MessageElement[] messageElements =
			new MessageElement[extensions.length];

		for (int i = 0; i < extensions.length; i++) {
			 MessageElement[] messageElementsWrapper = extensions[i].get_any();

			 messageElements[i] = messageElementsWrapper[0];
		}

		return messageElements;
	}

}