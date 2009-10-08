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

package com.liferay.wsrp.service;

/**
 * <a href="WSRPConsumerPortletLocalServiceWrapper.java.html"><b><i>View Source
 * </i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPortletLocalServiceWrapper
	implements WSRPConsumerPortletLocalService {
	public WSRPConsumerPortletLocalServiceWrapper(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		_wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return _wsrpConsumerPortletLocalService.createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public void deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end) throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(start,
			end);
	}

	public int getWSRPConsumerPortletsCount()
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletsCount();
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortlet,
			merge);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerId,
			name, portletHandle);
	}

	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	public void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerPortletLocalService.destroyWSRPConsumerPortlets();
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(wsrpConsumerId,
			start, end);
	}

	public int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	public void initWSRPConsumerPortlets()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerPortletLocalService.initWSRPConsumerPortlets();
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortletId,
			name);
	}

	public WSRPConsumerPortletLocalService getWrappedWSRPConsumerPortletLocalService() {
		return _wsrpConsumerPortletLocalService;
	}

	private WSRPConsumerPortletLocalService _wsrpConsumerPortletLocalService;
}