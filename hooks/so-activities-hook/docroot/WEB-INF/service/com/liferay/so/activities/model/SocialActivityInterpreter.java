/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.model;

import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * @author Brian Wing Shun Chan
 */
public interface SocialActivityInterpreter {

	public String[] getClassNames();

	public String getSelector();

	public SocialActivityFeedEntry interpret(
		SocialActivity activity, ServiceContext serviceContext);

	public SocialActivityFeedEntry interpret(
		SocialActivitySet activitySet, ServiceContext serviceContext);

}