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

package com.liferay.wsrp.util;

import java.util.ArrayList;
import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public abstract class BaseExtensionHelper implements ExtensionHelper {

	public Extension[] getExtensions(List<MessageElement> messageElements) {
		Extension[] extensions = new Extension[messageElements.size()];

		// Wrap the extension in an extension to be compatible with Oracle's
		// producer

		for (int i = 0; i < messageElements.size(); i ++) {
			MessageElement messageElement = messageElements.get(i);

			extensions[i] = new Extension(
				new MessageElement[] {messageElement});
		}

		return extensions;
	}

	public Extension[] getExtensions(String localPart, String value) {
		List<MessageElement> messageElements = new ArrayList<MessageElement>();

		addMessageElement(messageElements, localPart, value);

		return getExtensions(messageElements);
	}

	public MessageElement[] getMessageElements(Extension[] extensions) {
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