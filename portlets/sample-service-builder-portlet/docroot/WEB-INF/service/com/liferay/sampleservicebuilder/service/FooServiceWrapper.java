/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleservicebuilder.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FooService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FooService
 * @generated
 */
public class FooServiceWrapper implements FooService,
	ServiceWrapper<FooService> {
	public FooServiceWrapper(FooService fooService) {
		_fooService = fooService;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public FooService getWrappedFooService() {
		return _fooService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedFooService(FooService fooService) {
		_fooService = fooService;
	}

	public FooService getWrappedService() {
		return _fooService;
	}

	public void setWrappedService(FooService fooService) {
		_fooService = fooService;
	}

	private FooService _fooService;
}