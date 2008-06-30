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

package com.liferay.sample.service;

import com.liferay.portlet.service.BeanLocatorUtil;

/**
 * <a href="NotepadEntryLocalServiceFactory.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NotepadEntryLocalServiceFactory {
	public static NotepadEntryLocalService getService() {
		return _getFactory()._service;
	}

	public static NotepadEntryLocalService getImpl() {
		if (_impl == null) {
			_impl = (NotepadEntryLocalService)BeanLocatorUtil.locate(_IMPL);
		}

		return _impl;
	}

	public static NotepadEntryLocalService getTxImpl() {
		if (_txImpl == null) {
			_txImpl = (NotepadEntryLocalService)BeanLocatorUtil.locate(_TX_IMPL);
		}

		return _txImpl;
	}

	public void setService(NotepadEntryLocalService service) {
		_service = service;
	}

	private static NotepadEntryLocalServiceFactory _getFactory() {
		if (_factory == null) {
			_factory = (NotepadEntryLocalServiceFactory)BeanLocatorUtil.locate(_FACTORY);
		}

		return _factory;
	}

	private static final String _FACTORY = NotepadEntryLocalServiceFactory.class.getName();
	private static final String _IMPL = NotepadEntryLocalService.class.getName() +
		".impl";
	private static final String _TX_IMPL = NotepadEntryLocalService.class.getName() +
		".transaction";
	private static NotepadEntryLocalServiceFactory _factory;
	private static NotepadEntryLocalService _impl;
	private static NotepadEntryLocalService _txImpl;
	private NotepadEntryLocalService _service;
}