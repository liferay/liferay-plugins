/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.so.activities.util.PortletPropsKeys;
import com.liferay.so.activities.util.PortletPropsValues;
import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.social.kernel.model.impl.SocialActivityInterpreterImpl;
import com.liferay.social.kernel.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import java.util.List;

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
		List<SocialActivityInterpreter> activityInterpreters =
			SocialActivityInterpreterLocalServiceUtil.getActivityInterpreters(
				"SO");

		String[] portletIds =
			PortletPropsValues.SOCIAL_ACTIVITY_INTERPRETER_PORTLET_IDS;

		for (String portletId : portletIds) {
			Filter filter = new Filter(portletId);

			String activityInterpreterClassName = PortletProps.get(
				PortletPropsKeys.SOCIAL_ACTIVITY_INTERPRETER, filter);

			try {
				SocialActivityInterpreter activityInterpreter =
					(SocialActivityInterpreter)
						InstanceFactory.newInstance(
							activityInterpreterClassName);

				activityInterpreter = new SocialActivityInterpreterImpl(
					portletId, activityInterpreter);

				if (activityInterpreters != null) {
					for (SocialActivityInterpreter curActivityInterpreter :
							activityInterpreters) {

						if (ArrayUtil.containsAll(
								activityInterpreter.getClassNames(),
								curActivityInterpreter.getClassNames())) {

							SocialActivityInterpreterLocalServiceUtil.
								deleteActivityInterpreter(
									curActivityInterpreter);

							break;
						}
					}
				}

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