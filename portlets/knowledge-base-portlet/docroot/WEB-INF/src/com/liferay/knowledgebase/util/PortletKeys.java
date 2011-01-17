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

package com.liferay.knowledgebase.util;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Shin
 */
public class PortletKeys extends com.liferay.portal.util.PortletKeys {

	public static final String KNOWLEDGE_BASE_AGGREGATOR =
		"2_WAR_knowledgebaseportlet";

	public static final String KNOWLEDGE_BASE_DISPLAY =
		"3_WAR_knowledgebaseportlet";

	public static final String KNOWLEDGE_BASE_LIST =
		"5_WAR_knowledgebaseportlet";

	public static final String[] KNOWLEDGE_BASE_PORTLETS = {
		PortletKeys.KNOWLEDGE_BASE_ADMIN, PortletKeys.KNOWLEDGE_BASE_AGGREGATOR,
		PortletKeys.KNOWLEDGE_BASE_DISPLAY, PortletKeys.KNOWLEDGE_BASE_LIST,
		PortletKeys.KNOWLEDGE_BASE_SEARCH,
	};

	public static final String KNOWLEDGE_BASE_SEARCH =
		"4_WAR_knowledgebaseportlet";

}