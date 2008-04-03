/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.shindig.social.service;

import com.liferay.portal.kernel.util.ActivityTrackerInterpreterUtil;
import com.liferay.portal.model.ActivityFeedEntry;
import com.liferay.portal.model.ActivityTracker;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ActivityTrackerLocalServiceUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.shindig.social.util.OpenSocialUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.gadgets.GadgetToken;
import org.apache.shindig.social.ResponseItem;
import org.apache.shindig.social.opensocial.ActivitiesService;
import org.apache.shindig.social.opensocial.model.Activity;

import org.json.JSONObject;

public class BasicActivitiesService implements ActivitiesService {

	public ResponseItem<List<Activity>> getActivities(
			List<String> userIds, GadgetToken token) {

		List<Activity> activities = new ArrayList<Activity>();

		try {
			User user = UserLocalServiceUtil.getUserById(Long.parseLong(
				token.getViewerId()));
			Company company = CompanyLocalServiceUtil.getCompanyById(
				user.getCompanyId());

			ThemeDisplay themeDisplay = new ThemeDisplay();

			themeDisplay.setCompany(company);
			themeDisplay.setLocale(user.getLocale());

			for (String userId : userIds) {
				try {
					List<Activity> personActivities = new ArrayList<Activity>();

					List<ActivityTracker> activityTrackers =
						ActivityTrackerLocalServiceUtil.getUserActivityTrackers(
							Long.parseLong(userId), 0,  20);

					for (ActivityTracker activityTracker : activityTrackers) {
						Activity activity = new Activity(
							String.valueOf(activityTracker.getClassPK()),
							String.valueOf(activityTracker.getUserId()));

						if (activityTracker.getClassName().equals(
								Activity.class.getName())) {

							JSONObject extraData = new JSONObject(
								activityTracker.getExtraData());

							if (extraData.has("appId")) {
								activity.setAppId(extraData.getString("appId"));
							}

							if (extraData.has("body")) {
								activity.setBody(extraData.getString("body"));
							}

							if (extraData.has("bodyId")) {
								activity.setBodyId(extraData.getString("bodyId"));
							}

							if (extraData.has("externalId")) {
								activity.setExternalId(
									extraData.getString("externalId"));
							}

							if (extraData.has("mediaItems")) {
								activity.setMediaItems(
									OpenSocialUtil.getMediaItems(
										extraData.getJSONArray("mediaItems")));
							}

							if (extraData.has("postedTime")) {
								activity.setPostedTime(
									extraData.getLong("postedTime"));
							}

							if (extraData.has("priority")) {
								activity.setPriority(
									Float.parseFloat(
										extraData.getString("priority")));
							}

							if (extraData.has("streamFaviconUrl")) {
								activity.setStreamFaviconUrl(
									extraData.getString("streamFaviconUrl"));
							}

							if (extraData.has("streamSourceUrl")) {
								activity.setStreamSourceUrl(
									extraData.getString("streamSourceUrl"));
							}

							if (extraData.has("streamTitle")) {
								activity.setStreamTitle(
									extraData.getString("streamTitle"));
							}

							if (extraData.has("streamUrl")) {
								activity.setStreamUrl(
									extraData.getString("streamUrl"));
							}

							if (extraData.has("templateParams")) {
								activity.setTemplateParams(
									OpenSocialUtil.getTemplateParams(
										extraData.getJSONArray(
											"templateParams")));
							}

							if (extraData.has("title")) {
								activity.setTitle(
									extraData.getString("title"));
							}

							if (extraData.has("titleId")) {
								activity.setTitleId(
									extraData.getString("titleId"));
							}

							if (extraData.has("url")) {
								activity.setUrl(extraData.getString("url"));
							}
						}
						else {
							ActivityFeedEntry activityFeedEntry =
								ActivityTrackerInterpreterUtil.interpret(
									activityTracker, themeDisplay);

							activity.setBody(activityFeedEntry.getBody());
							activity.setTitle(activityFeedEntry.getTitle());
						}

						personActivities.add(activity);
					}

					activities.addAll(personActivities);
				}
				catch (Exception e){
			    	_log.error(e, e);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		// TODO: Sort them
		return new ResponseItem<List<Activity>>(activities);
	}

	public ResponseItem createActivity(
			String personId, Activity activity, GadgetToken token) {

		// TODO: Validate the activity and do any template expanding
		activity.setUserId(personId);
		activity.setPostedTime(new Date().getTime());

		try {
			JSONObject extraData = new JSONObject();

			extraData.put("body", activity.getBody());
			extraData.put("bodyId", activity.getBodyId());
			extraData.put("externalId", activity.getExternalId());

			extraData.put("mediaItems",
				OpenSocialUtil.getMediaItems(
					activity.getMediaItems()));

			extraData.put("postedTime", activity.getPostedTime());
			extraData.put("priority", activity.getPriority());
			extraData.put("streamFaviconUrl", activity.getStreamFaviconUrl());
			extraData.put("streamSourceUrl", activity.getStreamSourceUrl());
			extraData.put("streamTitle", activity.getStreamTitle());
			extraData.put("streamUrl", activity.getStreamUrl());

			extraData.put("templateParams",
				OpenSocialUtil.getTemplateParams(
					activity.getTemplateParams()));

			extraData.put("title", activity.getTitle());
			extraData.put("titleId", activity.getTitleId());
			extraData.put("url", activity.getUrl());

			ActivityTrackerLocalServiceUtil.addActivityTracker(
				Long.parseLong(personId), 0L, Activity.class.getName(),
				activity.getPostedTime().longValue(), activity.getStreamTitle(),
				extraData.toString(), 0L);
		}
		catch (Exception e){
	    	_log.error(e, e);
		}

		return new ResponseItem<JSONObject>(new JSONObject());
	}

	private static final Log _log = LogFactory.getLog(BasicActivitiesService.class);

}
