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
import com.liferay.ruon.service.base.NetworkLocalServiceBaseImpl;

/**
 * <a href="NetworkLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkLocalServiceImpl extends NetworkLocalServiceBaseImpl {

	public Network getNetwork(String name)
		throws PortalException, SystemException {

		return networkPersistence.findByName(name);
	}

	public long getNetworkId(String name)
		throws PortalException, SystemException {

		Network network = networkPersistence.findByName(name);

		return network.getNetworkId();
	}

	public Network updateNetwork(String name, long ttl) throws SystemException {
		Network network = networkPersistence.fetchByName(name);

		if (network == null) {
			long networkId = CounterLocalServiceUtil.increment();

			network = networkPersistence.create(networkId);

			network.setName(name);
			network.setTtl(ttl);

			networkPersistence.update(network, false);
		}

		return network;
	}

}