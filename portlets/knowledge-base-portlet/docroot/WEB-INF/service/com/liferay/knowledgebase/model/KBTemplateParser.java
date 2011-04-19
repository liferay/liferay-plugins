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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peter Shin
 */
public interface KBTemplateParser {

	public String transform(HttpServletRequest request) throws Exception;

	public String transform(
			String id, String content, Map<String, Object> variables,
			HttpServletRequest request)
		throws Exception;

}