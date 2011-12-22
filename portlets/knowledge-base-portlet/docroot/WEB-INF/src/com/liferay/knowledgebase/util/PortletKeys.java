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

package com.liferay.knowledgebase.util;

import com.liferay.portal.model.PortletConstants;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Shin
 */
public class PortletKeys extends com.liferay.portal.util.PortletKeys {

	public static final String KNOWLEDGE_BASE_ARTICLE =
		"3_WAR_knowledgebaseportlet";

	public static final String KNOWLEDGE_BASE_ARTICLE_DEFAULT_INSTANCE =
		PortletKeys.KNOWLEDGE_BASE_ARTICLE +
			PortletConstants.INSTANCE_SEPARATOR + "0000";

	public static final String KNOWLEDGE_BASE_DISPLAY =
		"2_WAR_knowledgebaseportlet";

	public static final String KNOWLEDGE_BASE_SEARCH =
		"5_WAR_knowledgebaseportlet";

	public static final String KNOWLEDGE_BASE_SECTION =
		"4_WAR_knowledgebaseportlet";

}