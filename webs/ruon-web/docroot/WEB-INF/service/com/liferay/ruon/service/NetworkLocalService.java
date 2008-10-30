/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.ruon.service;

/**
 * <a href="NetworkLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface NetworkLocalService {
	public com.liferay.ruon.model.Network addNetwork(
		com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Network createNetwork(long networkId);

	public void deleteNetwork(long networkId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteNetwork(com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Network getNetwork(long networkId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.ruon.model.Network> getNetworks(
		int start, int end) throws com.liferay.portal.SystemException;

	public int getNetworksCount() throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Network updateNetwork(
		com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Network getNetwork(java.lang.String name)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public long getNetworkId(java.lang.String name)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.ruon.model.Network updateNetwork(java.lang.String name,
		long ttl) throws com.liferay.portal.SystemException;
}