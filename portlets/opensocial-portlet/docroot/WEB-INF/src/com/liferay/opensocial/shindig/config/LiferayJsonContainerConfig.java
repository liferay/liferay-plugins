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

package com.liferay.opensocial.shindig.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.util.Validator;

import org.apache.shindig.config.ContainerConfigException;
import org.apache.shindig.config.JsonContainerConfig;
import org.apache.shindig.expressions.Expressions;

/**
 * @author Michael Young
 */
@Singleton
public class LiferayJsonContainerConfig extends JsonContainerConfig {

	@Inject
	public LiferayJsonContainerConfig(
			@Named("shindig.containers.default") String containers,
			Expressions expressions)
		throws ContainerConfigException {

		super(containers, null, null, expressions);
	}

	@Override
	public String getString(String container, String property) {
		String value = super.getString(container, property);

		if (Validator.isNotNull(value)) {
			value = ShindigUtil.transformURL(value);
		}

		return value;
	}

}