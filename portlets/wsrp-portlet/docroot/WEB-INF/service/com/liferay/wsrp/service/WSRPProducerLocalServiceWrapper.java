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
 * <a href="WSRPProducerLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerLocalServiceWrapper implements WSRPProducerLocalService {
	public WSRPProducerLocalServiceWrapper(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(wsrpProducer);
	}

	public com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId) {
		return _wsrpProducerLocalService.createWSRPProducer(wsrpProducerId);
	}

	public void deleteWSRPProducer(long wsrpProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_wsrpProducerLocalService.deleteWSRPProducer(wsrpProducerId);
	}

	public void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		_wsrpProducerLocalService.deleteWSRPProducer(wsrpProducer);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.getWSRPProducer(wsrpProducerId);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end) throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.getWSRPProducers(start, end);
	}

	public int getWSRPProducersCount()
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.getWSRPProducersCount();
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer, merge);
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long companyId,
		java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(companyId, name,
			portletIds);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.getWSRPProducers(companyId, start, end);
	}

	public int getWSRPProducersCount(long companyId)
		throws com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.getWSRPProducersCount(companyId);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducerId,
			name, portletIds);
	}

	public WSRPProducerLocalService getWrappedWSRPProducerLocalService() {
		return _wsrpProducerLocalService;
	}

	private WSRPProducerLocalService _wsrpProducerLocalService;
}