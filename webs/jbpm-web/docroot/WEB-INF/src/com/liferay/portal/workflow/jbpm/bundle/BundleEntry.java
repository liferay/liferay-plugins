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

package com.liferay.portal.workflow.jbpm.bundle;

import com.liferay.portal.kernel.resource.ResourceRetriever;

/**
 * @author Shuyang Zhou
 */
public class BundleEntry {

	public BundleEntry(String name, ResourceRetriever resourceRetriever) {
		_name = name;
		_resourceRetriever = resourceRetriever;
	}

	public String getName() {
		return _name;
	}

	public ResourceRetriever getResourceRetriever() {
		return _resourceRetriever;
	}

	private String _name;
	private ResourceRetriever _resourceRetriever;

}