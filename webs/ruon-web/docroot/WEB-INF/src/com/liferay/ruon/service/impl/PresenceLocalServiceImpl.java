/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.ruon.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.ruon.model.Network;
import com.liferay.ruon.model.Presence;
import com.liferay.ruon.service.base.PresenceLocalServiceBaseImpl;

import java.util.List;

/**
 * <a href="PresenceLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceLocalServiceImpl extends PresenceLocalServiceBaseImpl {

	public Presence getPresence(long userId, String networkName)
		throws PortalException, SystemException {

		long networkId = networkLocalService.getNetworkId(networkName);

		Presence presence = presencePersistence.fetchByU_N(userId, networkId);

		if (presence != null) {
			checkPresence(presence);

			return presence;
		}

		long presenceId = CounterLocalServiceUtil.increment();

		presence = presencePersistence.create(presenceId);

		presence.setUserId(userId);
		presence.setModifiedDate(System.currentTimeMillis());
		presence.setNetworkId(networkId);
		presence.setOnline(false);

		presencePersistence.update(presence, false);

		return presence;
	}

	public List<Presence> getPresences(long userId, boolean online)
		throws PortalException, SystemException {

		List<Presence> presences = presencePersistence.findByU_O(
			userId, online);

		for (Presence presence : presences) {
			checkPresence(presence);
		}

		return presences;
	}

	public Presence updatePresence(
			long userId, String networkName, boolean online)
		throws PortalException, SystemException {

		long networkId = networkLocalService.getNetworkId(networkName);

		Presence presence = presencePersistence.fetchByU_N(userId, networkId);

		if (presence == null) {
			long presenceId = CounterLocalServiceUtil.increment();

			presence = presencePersistence.create(presenceId);

			presence.setUserId(userId);
			presence.setNetworkId(networkId);
		}

		presence.setModifiedDate(System.currentTimeMillis());
		presence.setOnline(online);

		presencePersistence.update(presence, false);

		return presence;
	}

	protected void checkPresence(Presence presence)
		throws PortalException, SystemException {

		if (presence.isOnline()) {
			Network network = networkLocalService.getNetwork(
				presence.getNetworkId());

			long ttl = network.getTtl();
			long now = System.currentTimeMillis();

			if ((presence.getModifiedDate() + ttl) < now) {
				presence.setOnline(false);

				presencePersistence.update(presence, false);
			}
		}
	}

}