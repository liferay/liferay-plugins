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

package com.liferay.so.activities.model.impl;

import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.so.activities.model.SocialActivity;
import com.liferay.so.activities.model.SocialActivityInterpreter;
import com.liferay.so.activities.model.SocialActivitySet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityInterpreterImpl
	implements SocialActivityInterpreter {

	public SocialActivityInterpreterImpl(
		String portletId, SocialActivityInterpreter activityInterpreter) {

		_portletId = portletId;
		_activityInterpreter = activityInterpreter;

		String[] classNames = _activityInterpreter.getClassNames();

		for (String className : classNames) {
			_classNames.add(className);
		}
	}

	public String[] getClassNames() {
		return _activityInterpreter.getClassNames();
	}

	public String getPortletId() {
		return _portletId;
	}

	public String getSelector() {
		return _activityInterpreter.getSelector();
	}

	public boolean hasClassName(String className) {
		if (_classNames.contains(className)) {
			return true;
		}
		else {
			return false;
		}
	}

	public SocialActivityFeedEntry interpret(
		SocialActivity activity, ServiceContext serviceContext) {

		return _activityInterpreter.interpret(activity, serviceContext);
	}

	public SocialActivityFeedEntry interpret(
		SocialActivitySet activitySet, ServiceContext serviceContext) {

		return _activityInterpreter.interpret(activitySet, serviceContext);
	}

	private SocialActivityInterpreter _activityInterpreter;
	private Set<String> _classNames = new HashSet<String>();
	private String _portletId;

}