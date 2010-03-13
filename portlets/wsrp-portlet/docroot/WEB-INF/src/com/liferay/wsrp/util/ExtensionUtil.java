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
 * <a href="ExtensionUtil.java.html"><b><i>View Source</i></b></a>
 *
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

	public static Extension getExtension(List<MessageElement> messageElements) {
		MessageElement[] messageElementsArray = messageElements.toArray(
			new MessageElement[messageElements.size()]);

		return new Extension(messageElementsArray);
	}

	public static Extension[] getExtensions(
		List<MessageElement> messageElements) {

		List<Extension> extensions = new ArrayList<Extension>();

		extensions.add(getExtension(messageElements));

		return extensions.toArray(new Extension[extensions.size()]);
	}

	public static Extension[] getExtensions(String localPart, String value) {
		MessageElement messageElement = new MessageElement(
			"liferay", localPart);

		messageElement.setValue(value);

		MessageElement[] messageElements = new MessageElement[] {
			messageElement
		};

		Extension extension = new Extension(messageElements);

		return new Extension[] {extension};
	}

	public static MessageElement[] getMessageElements(
		Extension[] extensions) {

		MessageElement[] messageElements = null;

		if ((extensions != null) && (extensions.length == 1)) {
			Extension extension = extensions[0];

			messageElements = extension.get_any();
		}

		return messageElements;
	}

}