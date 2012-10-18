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

import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public interface ExtensionHelper {

	public void addMessageElement(
		List<MessageElement> messageElements, String name, String value);

	public Extension[] getExtensions(List<MessageElement> messageElements);

	public Extension[] getExtensions(String name, String value);

	public MessageElement[] getMessageElements(Extension[] extensions);

	public String getNameAttribute(MessageElement messageElement);

}