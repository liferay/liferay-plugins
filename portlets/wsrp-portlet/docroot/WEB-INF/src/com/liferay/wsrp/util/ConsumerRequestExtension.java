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

import com.liferay.portal.model.User;

import java.util.List;

import oasis.names.tc.wsrp.v2.types.UserProfile;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public interface ConsumerRequestExtension {

	public void addClientAttributes(List<MessageElement> messageElements);

	public void addUserProfileAttributes(UserProfile userProfile, User user);

}