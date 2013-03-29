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

package com.liferay.so.activities.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.so.activities.model.SocialActivity;
import com.liferay.so.activities.model.SocialActivityInterpreter;
import com.liferay.so.activities.model.SocialActivitySet;
import com.liferay.so.activities.model.impl.SocialActivityInterpreterImpl;
import com.liferay.so.activities.service.SocialActivityLocalServiceUtil;
import com.liferay.so.activities.service.base.SocialActivityInterpreterLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * The social activity interpreter local service. Activity interpreters are
 * classes responsible for translating activity records into human readable
 * form. This service holds a list of interpreters and provides methods to add
 * or remove items from this list.
 *
 * <p>
 * Activity interpreters use the language files to get text fragments based on
 * the activity's type and the type of asset on which the activity was done.
 * Interpreters are created for specific asset types and are only capable of
 * translating activities done on assets of those types. As an example, there is
 * an interpreter BlogsActivityInterpreter that can only translate activity
 * records for blog entries.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class SocialActivityInterpreterLocalServiceImpl
	extends SocialActivityInterpreterLocalServiceBaseImpl {

	/**
	 * Adds the activity interpreter to the list of available interpreters.
	 *
	 * @param activityInterpreter the activity interpreter
	 */
	public void addActivityInterpreter(
		SocialActivityInterpreter activityInterpreter) {

		List<SocialActivityInterpreter> activityInterpreters =
			new ArrayList<SocialActivityInterpreter>();

		activityInterpreters.add(activityInterpreter);

		_activityInterpreters.put(
			activityInterpreter.getSelector(), activityInterpreters);
	}

	/**
	 * Removes the activity interpreter from the list of available interpreters.
	 *
	 * @param activityInterpreter the activity interpreter
	 */
	public void deleteActivityInterpreter(
		SocialActivityInterpreter activityInterpreter) {

		List<SocialActivityInterpreter> activityInterpreters =
			_activityInterpreters.get(activityInterpreter.getSelector());

		if (activityInterpreters == null) {
			return;
		}

		activityInterpreters.remove(activityInterpreter);
	}

	/**
	 * Creates a human readable activity feed entry for the activity using an
	 * available compatible activity interpreter.
	 *
	 * <p>
	 * This method finds the appropriate interpreter for the activity by going
	 * through the available interpreters and asking them if they can handle the
	 * asset type of the activity.
	 * </p>
	 *
	 * @param  activity the activity to be translated to human readable form
	 * @return the activity feed that is a human readable form of the activity
	 *         record or <code>null</code> if a compatible interpreter is not
	 *         found
	 */
	public SocialActivityFeedEntry interpret(
			String selector, SocialActivity activity,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		HttpServletRequest request = serviceContext.getRequest();

		if (request == null) {
			return null;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (activity.getUserId() == themeDisplay.getDefaultUserId()) {
				return null;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (activity.getMirrorActivityId() > 0) {
			SocialActivity mirrorActivity = null;

			try {
				mirrorActivity = SocialActivityLocalServiceUtil.getActivity(
					activity.getMirrorActivityId());
			}
			catch (Exception e) {
			}

			if (mirrorActivity != null) {
				activity = mirrorActivity;
			}
		}

		List<SocialActivityInterpreter> activityInterpreters =
			_activityInterpreters.get(selector);

		if (activityInterpreters == null) {
			return null;
		}

		String className = PortalUtil.getClassName(activity.getClassNameId());

		for (int i = 0; i < activityInterpreters.size(); i++) {
			SocialActivityInterpreterImpl activityInterpreter =
				(SocialActivityInterpreterImpl)activityInterpreters.get(i);

			if (activityInterpreter.hasClassName(className)) {
				SocialActivityFeedEntry activityFeedEntry =
					activityInterpreter.interpret(activity, serviceContext);

				if (activityFeedEntry != null) {
					activityFeedEntry.setPortletId(
						activityInterpreter.getPortletId());

					return activityFeedEntry;
				}
			}
		}

		return null;
	}

	public SocialActivityFeedEntry interpret(
		String selector, SocialActivitySet activitySet,
		ServiceContext serviceContext) {

		HttpServletRequest request = serviceContext.getRequest();

		if (request == null) {
			return null;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (activitySet.getUserId() == themeDisplay.getDefaultUserId()) {
				return null;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		List<SocialActivityInterpreter> activityInterpreters =
			_activityInterpreters.get(selector);

		if (activityInterpreters == null) {
			return null;
		}

		String className = PortalUtil.getClassName(
			activitySet.getClassNameId());

		for (int i = 0; i < activityInterpreters.size(); i++) {
			SocialActivityInterpreterImpl activityInterpreter =
				(SocialActivityInterpreterImpl)activityInterpreters.get(i);

			if (activityInterpreter.hasClassName(className)) {
				SocialActivityFeedEntry activityFeedEntry =
					activityInterpreter.interpret(activitySet, serviceContext);

				if (activityFeedEntry != null) {
					activityFeedEntry.setPortletId(
						activityInterpreter.getPortletId());

					return activityFeedEntry;
				}
			}
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SocialActivityInterpreterLocalServiceImpl.class);

	private Map<String, List<SocialActivityInterpreter>> _activityInterpreters =
		new HashMap<String, List<SocialActivityInterpreter>>();

}