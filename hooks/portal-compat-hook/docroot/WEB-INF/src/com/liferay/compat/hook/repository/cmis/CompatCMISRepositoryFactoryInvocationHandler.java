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

package com.liferay.compat.hook.repository.cmis;

import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.repository.proxy.BaseRepositoryProxyBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatCMISRepositoryFactoryInvocationHandler
	implements InvocationHandler {

	public CompatCMISRepositoryFactoryInvocationHandler(
		Object repositoryFactory) {

		_repositoryFactory = repositoryFactory;
	}

	public Object getRepositoryFactory() {
		return _repositoryFactory;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			Object value = method.invoke(_repositoryFactory, arguments);

			if (methodName.equals("getInstance")) {
				ClassLoader classLoader =
					PortalClassLoaderUtil.getClassLoader();

				BaseRepository baseRepository =
					(BaseRepository)ProxyUtil.newProxyInstance(
						classLoader, new Class<?>[] {BaseRepository.class},
						new CompatCMISRepositoryInvocationHandler(
							value, classLoader));

				value = new BaseRepositoryProxyBean(
					baseRepository, classLoader);
			}

			return value;
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	private Object _repositoryFactory;

}