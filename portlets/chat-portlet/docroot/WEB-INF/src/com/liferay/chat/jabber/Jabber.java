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

package com.liferay.chat.jabber;

import java.util.List;

/**
 * @author Bruno Farache
 */
public interface Jabber {

	public void disconnect(long userId);

	public String getResource(String jabberId);

	public String getScreenName(String jabberId);

	public List<Object[]> getStatuses(
		long companyId, long userId, List<Object[]> buddies);

	public void login(long userId, String password);

	public void sendMessage(long fromUserId, long toUserId, String content);

	public void updatePassword(long userId, String password);

	public void updateStatus(long userId, int online);

}