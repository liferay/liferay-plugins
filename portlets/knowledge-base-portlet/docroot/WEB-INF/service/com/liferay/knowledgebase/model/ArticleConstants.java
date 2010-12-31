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

package com.liferay.knowledgebase.model;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class ArticleConstants {

	public static final long DEFAULT_PARENT_RESOURCE_PRIM_KEY = 0;

	public static final int DEFAULT_PRIORITY = 0;

	public static final int DEFAULT_VERSION = 1;

	public static final String DIR_NAME_PREFIX = "knowledgebase/articles/";

	public static final int[] LATEST_ANY = new int[] {
		ArticleConstants.LATEST_APPROVED, ArticleConstants.LATEST_VERSION
	};

	public static final int LATEST_APPROVED = 2;

	public static final int LATEST_ARCHIVED = 0;

	public static final int LATEST_VERSION = 1;

}