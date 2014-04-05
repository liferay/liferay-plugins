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

package com.liferay.cdi.portlet.bridge;

import java.util.Iterator;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

/**
 * @author Neil Griffin
 */
public class CDIBeanManagerUtil {

	public static BeanManager getBeanManager() {
		return _beanManager;
	}

	public static Object getManagedBeanReference(Class<?> clazz) {
		Set<Bean<?>> beans = _beanManager.getBeans(clazz);

		Iterator<Bean<?>> iterator = beans.iterator();

		Bean<?> bean = iterator.next();

		CreationalContext<?> creationalContext =
			_beanManager.createCreationalContext(bean);

		return _beanManager.getReference(bean, clazz, creationalContext);
	}

	public static void setBeanManager(BeanManager beanManager) {
		_beanManager = beanManager;
	}

	private static BeanManager _beanManager;

}