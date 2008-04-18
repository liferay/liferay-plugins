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

import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.shindig.util.ShindigUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

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

/**
 * <a href="BasicActivitiesService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class LiferayActivitiesService implements ActivitiesService {

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

					List<SocialActivity> socialActivities =
						SocialActivityLocalServiceUtil.getUserActivities(
							Long.parseLong(userId), 0,  20);

					for (SocialActivity socialActivity : socialActivities) {
						Activity activity = null;

						if (socialActivity.getClassName().equals(
								Activity.class.getName())) {

							activity = ShindigUtil.getActivity(socialActivity);
						}
						else {
							activity = new Activity(
								String.valueOf(socialActivity.getClassPK()),
								String.valueOf(socialActivity.getUserId()));

							SocialActivityFeedEntry socialActivityFeedEntry =
								SocialActivityInterpreterLocalServiceUtil
									.interpret(socialActivity, themeDisplay);

							activity.setBody(socialActivityFeedEntry.getBody());
							activity.setTitle(
								socialActivityFeedEntry.getTitle());
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
			String userId, Activity activity, GadgetToken token) {

		// TODO: Validate the activity and do any template expanding
		activity.setUserId(userId);
		activity.setPostedTime(new Date().getTime());

		try {
			JSONObject extraData = new JSONObject();

			extraData.put("appId", activity.getAppId());
			extraData.put("body", activity.getBody());
			extraData.put("bodyId", activity.getBodyId());
			extraData.put("externalId", activity.getExternalId());

			extraData.put("mediaItems",
				ShindigUtil.getMediaItems(
					activity.getMediaItems()));

			extraData.put("postedTime", activity.getPostedTime());
			extraData.put("priority", activity.getPriority());
			extraData.put("streamFaviconUrl", activity.getStreamFaviconUrl());
			extraData.put("streamSourceUrl", activity.getStreamSourceUrl());
			extraData.put("streamTitle", activity.getStreamTitle());
			extraData.put("streamUrl", activity.getStreamUrl());

			extraData.put("templateParams",
				ShindigUtil.getTemplateParams(
					activity.getTemplateParams()));

			extraData.put("title", activity.getTitle());
			extraData.put("titleId", activity.getTitleId());
			extraData.put("url", activity.getUrl());

			SocialActivityLocalServiceUtil.addActivity(
				Long.parseLong(userId), 0L, Activity.class.getName(),
				activity.getPostedTime().longValue(), activity.getAppId(),
				extraData.toString(), 0L);
		}
		catch (Exception e){
	    	_log.error(e, e);
		}

		return new ResponseItem<JSONObject>(new JSONObject());
	}

	private static final Log _log = LogFactory.getLog(LiferayActivitiesService.class);

}
