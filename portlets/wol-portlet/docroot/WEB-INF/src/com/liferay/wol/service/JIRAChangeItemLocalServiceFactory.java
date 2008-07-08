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

package com.liferay.wol.service;

/**
 * <a href="JIRAChangeItemLocalServiceFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemLocalServiceFactory {
	public static JIRAChangeItemLocalService getService() {
		return _getFactory()._service;
	}

	public static JIRAChangeItemLocalService getImpl() {
		if (_impl == null) {
			_impl = (JIRAChangeItemLocalService)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_IMPL);
		}

		return _impl;
	}

	public static JIRAChangeItemLocalService getTxImpl() {
		if (_txImpl == null) {
			_txImpl = (JIRAChangeItemLocalService)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_TX_IMPL);
		}

		return _txImpl;
	}

	public void setService(JIRAChangeItemLocalService service) {
		_service = service;
	}

	private static JIRAChangeItemLocalServiceFactory _getFactory() {
		if (_factory == null) {
			_factory = (JIRAChangeItemLocalServiceFactory)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_FACTORY);
		}

		return _factory;
	}

	private static final String _FACTORY = JIRAChangeItemLocalServiceFactory.class.getName();
	private static final String _IMPL = JIRAChangeItemLocalService.class.getName() +
		".impl";
	private static final String _TX_IMPL = JIRAChangeItemLocalService.class.getName() +
		".transaction";
	private static JIRAChangeItemLocalServiceFactory _factory;
	private static JIRAChangeItemLocalService _impl;
	private static JIRAChangeItemLocalService _txImpl;
	private JIRAChangeItemLocalService _service;
}