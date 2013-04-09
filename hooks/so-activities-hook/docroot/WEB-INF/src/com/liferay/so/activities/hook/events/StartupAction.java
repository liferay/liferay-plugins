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

package com.liferay.so.activities.hook.events;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.model.impl.SocialActivityInterpreterImpl;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Lee
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun() throws Exception {
		initSocialActivityInterpreters();
	}

	protected void initSocialActivityInterpreters() {
		String[] portletIds = PropsUtil.getArray(
			"social.activity.interpreter.portlet.ids");

		for (String portletId : portletIds) {
			Filter filter = new Filter(portletId);

			String activityInterpreterClassName = PropsUtil.get(
				"social.activity.interpreter", filter);

			try {
				SocialActivityInterpreter activityInterpreter =
					(SocialActivityInterpreter)
						InstanceFactory.newInstance(
							activityInterpreterClassName);

				activityInterpreter = new SocialActivityInterpreterImpl(
					portletId, activityInterpreter);

				SocialActivityInterpreterLocalServiceUtil.
					addActivityInterpreter(activityInterpreter);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to add social activity interpreter " +
							activityInterpreterClassName);
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(StartupAction.class);

}