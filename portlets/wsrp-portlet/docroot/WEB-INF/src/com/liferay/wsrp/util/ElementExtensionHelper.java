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

package com.liferay.wsrp.util;

import java.util.List;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public class ElementExtensionHelper extends BaseExtensionHelper {

	public void addMessageElement(
		List<MessageElement> messageElements, String localPart, String value) {

		MessageElement messageElement = new MessageElement(
			"http://www.liferay.com/wsrp", localPart);

		messageElement.setValue(value);

		messageElements.add(messageElement);
	}

	public String getNameAttribute(MessageElement messageElement) {
		return messageElement.getName();
	}

}