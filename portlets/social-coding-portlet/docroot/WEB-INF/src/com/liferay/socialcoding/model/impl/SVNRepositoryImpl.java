/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.model.impl;

import com.liferay.socialcoding.svn.util.SVNConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class SVNRepositoryImpl
	extends SVNRepositoryBaseImpl {

	public SVNRepositoryImpl() {
	}

	public String getName() {
		String name = "portal";

		if (getUrl().equals(SVNConstants.SVN_PLUGINS_TRUNK_URL)) {
			name = "plugins";
		}

		return name;
	}

	public String getShortURL() {
		String url = getUrl();

		return url.substring(url.indexOf("/public/") + 7);
	}

}