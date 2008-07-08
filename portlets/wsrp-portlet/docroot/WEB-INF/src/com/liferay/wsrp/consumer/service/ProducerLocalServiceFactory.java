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

package com.liferay.wsrp.consumer.service;

/**
 * <a href="ProducerLocalServiceFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProducerLocalServiceFactory {
	public static ProducerLocalService getService() {
		return _getFactory()._service;
	}

	public static ProducerLocalService getImpl() {
		if (_impl == null) {
			_impl = (ProducerLocalService)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_IMPL);
		}

		return _impl;
	}

	public static ProducerLocalService getTxImpl() {
		if (_txImpl == null) {
			_txImpl = (ProducerLocalService)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_TX_IMPL);
		}

		return _txImpl;
	}

	public void setService(ProducerLocalService service) {
		_service = service;
	}

	private static ProducerLocalServiceFactory _getFactory() {
		if (_factory == null) {
			_factory = (ProducerLocalServiceFactory)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_FACTORY);
		}

		return _factory;
	}

	private static final String _FACTORY = ProducerLocalServiceFactory.class.getName();
	private static final String _IMPL = ProducerLocalService.class.getName() +
		".impl";
	private static final String _TX_IMPL = ProducerLocalService.class.getName() +
		".transaction";
	private static ProducerLocalServiceFactory _factory;
	private static ProducerLocalService _impl;
	private static ProducerLocalService _txImpl;
	private ProducerLocalService _service;
}