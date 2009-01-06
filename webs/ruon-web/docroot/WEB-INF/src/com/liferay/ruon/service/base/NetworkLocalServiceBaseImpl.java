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

package com.liferay.ruon.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.ruon.model.Network;
import com.liferay.ruon.service.NetworkLocalService;
import com.liferay.ruon.service.PresenceLocalService;
import com.liferay.ruon.service.persistence.NetworkPersistence;
import com.liferay.ruon.service.persistence.PresencePersistence;

import java.util.List;

/**
 * <a href="NetworkLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class NetworkLocalServiceBaseImpl implements NetworkLocalService {
	public Network addNetwork(Network network) throws SystemException {
		network.setNew(true);

		return networkPersistence.update(network, false);
	}

	public Network createNetwork(long networkId) {
		return networkPersistence.create(networkId);
	}

	public void deleteNetwork(long networkId)
		throws PortalException, SystemException {
		networkPersistence.remove(networkId);
	}

	public void deleteNetwork(Network network) throws SystemException {
		networkPersistence.remove(network);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return networkPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return networkPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public Network getNetwork(long networkId)
		throws PortalException, SystemException {
		return networkPersistence.findByPrimaryKey(networkId);
	}

	public List<Network> getNetworks(int start, int end)
		throws SystemException {
		return networkPersistence.findAll(start, end);
	}

	public int getNetworksCount() throws SystemException {
		return networkPersistence.countAll();
	}

	public Network updateNetwork(Network network) throws SystemException {
		network.setNew(false);

		return networkPersistence.update(network, true);
	}

	public NetworkLocalService getNetworkLocalService() {
		return networkLocalService;
	}

	public void setNetworkLocalService(NetworkLocalService networkLocalService) {
		this.networkLocalService = networkLocalService;
	}

	public NetworkPersistence getNetworkPersistence() {
		return networkPersistence;
	}

	public void setNetworkPersistence(NetworkPersistence networkPersistence) {
		this.networkPersistence = networkPersistence;
	}

	public PresenceLocalService getPresenceLocalService() {
		return presenceLocalService;
	}

	public void setPresenceLocalService(
		PresenceLocalService presenceLocalService) {
		this.presenceLocalService = presenceLocalService;
	}

	public PresencePersistence getPresencePersistence() {
		return presencePersistence;
	}

	public void setPresencePersistence(PresencePersistence presencePersistence) {
		this.presencePersistence = presencePersistence;
	}

	@com.liferay.portal.kernel.annotation.BeanReference(name = "com.liferay.ruon.service.NetworkLocalService.impl")
	protected NetworkLocalService networkLocalService;
	@com.liferay.portal.kernel.annotation.BeanReference(name = "com.liferay.ruon.service.persistence.NetworkPersistence.impl")
	protected NetworkPersistence networkPersistence;
	@com.liferay.portal.kernel.annotation.BeanReference(name = "com.liferay.ruon.service.PresenceLocalService.impl")
	protected PresenceLocalService presenceLocalService;
	@com.liferay.portal.kernel.annotation.BeanReference(name = "com.liferay.ruon.service.persistence.PresencePersistence.impl")
	protected PresencePersistence presencePersistence;
}