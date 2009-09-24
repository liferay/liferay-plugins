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
 * <a href="WSRPConsumerLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerLocalServiceWrapper implements WSRPConsumerLocalService {
	public WSRPConsumerLocalServiceWrapper(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.addWSRPConsumer(wsrpConsumer);
	}

	public com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		return _wsrpConsumerLocalService.createWSRPConsumer(wsrpConsumerId);
	}

	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumerId);
	}

	public void deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumer);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumer(wsrpConsumerId);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end) throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumers(start, end);
	}

	public int getWSRPConsumersCount()
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumersCount();
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumer);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumer, merge);
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(long companyId,
		java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.addWSRPConsumer(companyId,
			adminPortletId, name, url);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumers(companyId, start, end);
	}

	public int getWSRPConsumersCount(long companyId)
		throws com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumersCount(companyId);
	}

	public com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.registerWSRPConsumer(wsrpConsumerId,
			adminPortletId, registrationProperties, registrationHandle);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumerId,
			adminPortletId, name, url);
	}

	private WSRPConsumerLocalService _wsrpConsumerLocalService;
}