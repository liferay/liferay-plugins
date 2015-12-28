/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.words.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for Words. This utility wraps
 * {@link com.liferay.words.service.impl.WordsServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see WordsService
 * @see com.liferay.words.service.base.WordsServiceBaseImpl
 * @see com.liferay.words.service.impl.WordsServiceImpl
 * @generated
 */
@ProviderType
public class WordsServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.words.service.impl.WordsServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.util.List<java.lang.String> checkSpelling(
		java.lang.String text) throws java.lang.Exception {
		return getService().checkSpelling(text);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<java.lang.String> getSuggestions(
		java.lang.String word) throws java.lang.Exception {
		return getService().getSuggestions(word);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void clearService() {
		_service = null;
	}

	public static WordsService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WordsService.class.getName());

			if (invokableService instanceof WordsService) {
				_service = (WordsService)invokableService;
			}
			else {
				_service = new WordsServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(WordsServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static WordsService _service;
}