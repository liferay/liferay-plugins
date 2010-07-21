/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.servlet;

import com.google.inject.Inject;

import com.liferay.opensocial.shindig.config.LiferayJsonContainerConfig;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shindig.common.servlet.InjectedFilter;
import org.apache.shindig.config.ContainerConfig;

/**
 * @author Michael Young
 */
public class ShindigFilter extends InjectedFilter {

	public void destroy() {
	}

	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		StringBundler sb = new StringBundler();

		sb.append(servletRequest.getServerName());
		sb.append(":");
		sb.append(servletRequest.getServerPort());

		servletRequest.getServerPort();

		LiferayJsonContainerConfig liferayJsonContainerConfig =
			(LiferayJsonContainerConfig)_containerConfig;

		liferayJsonContainerConfig.setHost(sb.toString());

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Inject
	private ContainerConfig _containerConfig;

}