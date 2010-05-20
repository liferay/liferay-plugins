/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.service;

import com.liferay.portal.json.JSONArrayImpl;
import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.core.model.ActivityImpl;
import org.apache.shindig.social.core.model.MediaItemImpl;
import org.apache.shindig.social.opensocial.model.Activity;
import org.apache.shindig.social.opensocial.model.MediaItem.Type;
import org.apache.shindig.social.opensocial.model.MediaItem;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * <a href="LiferayActivityService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class LiferayActivityService implements ActivityService {

	public Future<Void> createActivity(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			Activity activity, SecurityToken securityToken)
		throws ProtocolException {

		try {
			doCreateActivity(
				userId, groupId, appId, fields, activity, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Void> deleteActivities(
			UserId userId, GroupId groupId, String appId,
			Set<String> activityIds, SecurityToken securityToken)
		throws ProtocolException {

		try {
			doDeleteActivities(
				userId, groupId, appId, activityIds, securityToken);

			return ImmediateFuture.newInstance(null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<Activity>> getActivities(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<Activity> activities = doGetActivities(
				userIds, groupId, appId, fields, collectionOptions,
				securityToken);

			return ImmediateFuture.newInstance(activities);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<RestfulCollection<Activity>> getActivities(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			CollectionOptions collectionOptions, Set<String> activityIds,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<Activity> activities = doGetActivities(
				userId, groupId, appId, fields, collectionOptions, activityIds,
				securityToken);

			return ImmediateFuture.newInstance(activities);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Activity> getActivity(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			String activityId, SecurityToken securityToken)
		throws ProtocolException {

		try {
			Activity activity = doGetActivity(
				userId, groupId, appId, fields, activityId, securityToken);

			return ImmediateFuture.newInstance(activity);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public void doCreateActivity(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			Activity activity, SecurityToken securityToken)
		throws Exception {

		JSONObject extraData = new JSONObjectImpl();

		extraData.put("appId", activity.getAppId());
		extraData.put("body", activity.getBody());
		extraData.put("bodyId", activity.getBodyId());
		extraData.put("externalId", activity.getExternalId());

		extraData.put("mediaItems",getMediaItems(activity.getMediaItems()));

		extraData.put("postedTime", activity.getPostedTime());
		extraData.put("priority", activity.getPriority());
		extraData.put("streamFaviconUrl", activity.getStreamFaviconUrl());
		extraData.put("streamSourceUrl", activity.getStreamSourceUrl());
		extraData.put("streamTitle", activity.getStreamTitle());
		extraData.put("streamUrl", activity.getStreamUrl());

		extraData.put("templateParams",
			getTemplateParams(activity.getTemplateParams()));

		extraData.put("title", activity.getTitle());
		extraData.put("titleId", activity.getTitleId());
		extraData.put("url", activity.getUrl());

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		SocialActivityLocalServiceUtil.addActivity(
			userIdLong, 0L, Activity.class.getName(),
			activity.getPostedTime().longValue(),
			activity.getAppId().hashCode(), extraData.toString(), 0L);

		return;
	}

	public void doDeleteActivities(
			UserId userId, GroupId groupId, String appId,
			Set<String> activityIds, SecurityToken securityToken)
		throws Exception {

		for (String activityId : activityIds) {
			long activityIdLong = GetterUtil.getLong(activityId);

			SocialActivityLocalServiceUtil.deleteActivity(activityIdLong);
		}

		return;
	}

	public RestfulCollection<Activity> doGetActivities(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionsOptions,
			SecurityToken securityToken)
		throws Exception {

		List<Activity> activities = new ArrayList<Activity>();

		ThemeDisplay themeDisplay = getThemeDisplay(securityToken);

		for (UserId userId : userIds) {
			long userIdLong = GetterUtil.getLong(
				userId.getUserId(securityToken));

			List<Activity> personActivities =
				getActivities(themeDisplay, userIdLong);

			activities.addAll(personActivities);
		}

		return new RestfulCollection<Activity>(
			activities, collectionsOptions.getFirst(), activities.size(),
			collectionsOptions.getMax());
	}

	public RestfulCollection<Activity> doGetActivities(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			CollectionOptions collectionsOptions, Set<String> activityIds,
			SecurityToken securityToken)
		throws Exception {

		ThemeDisplay themeDisplay = getThemeDisplay(securityToken);

		long userIdLong = GetterUtil.getLong(
			userId.getUserId(securityToken));

		List<Activity> activities = getActivities(themeDisplay, userIdLong);

		return new RestfulCollection<Activity>(
			activities, collectionsOptions.getFirst(), activities.size(),
			collectionsOptions.getMax());
	}

	public Activity doGetActivity(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			String activityId, SecurityToken securityToken)
		throws Exception {

		SocialActivity socialActivity =
			SocialActivityLocalServiceUtil.getActivity(
				GetterUtil.getLong(activityId));

		ThemeDisplay themeDisplay = getThemeDisplay(securityToken);

		Activity activity = getActivity(themeDisplay, socialActivity);

		return activity;
	}

	protected List<Activity> getActivities(
			ThemeDisplay themeDisplay, long userId)
		throws Exception {

		List<Activity> activities = new ArrayList<Activity>();

		List<SocialActivity> socialActivities =
			SocialActivityLocalServiceUtil.getUserActivities(userId, 0,  20);

		for (SocialActivity socialActivity : socialActivities) {
			Activity activity = null;

			if (socialActivity.getClassName().equals(
				Activity.class.getName())) {

				activity = getExternalActivity(socialActivity);
			}
			else {
				activity = new ActivityImpl(
					String.valueOf(socialActivity.getClassPK()),
					String.valueOf(socialActivity.getUserId()));

				SocialActivityFeedEntry socialActivityFeedEntry =
					SocialActivityInterpreterLocalServiceUtil.interpret(
						socialActivity, themeDisplay);

				activity.setBody(socialActivityFeedEntry.getBody());
				activity.setTitle(socialActivityFeedEntry.getTitle());
			}

			activities.add(activity);
		}

		return activities;
	}

	protected Activity getActivity(
			ThemeDisplay themeDisplay, SocialActivity socialActivity)
		throws Exception {

		Activity activity = null;

		if (socialActivity.getClassName().equals(
			Activity.class.getName())) {

			activity = getExternalActivity(socialActivity);
		}
		else {
			activity = new ActivityImpl(
				String.valueOf(socialActivity.getClassPK()),
				String.valueOf(socialActivity.getUserId()));

			SocialActivityFeedEntry socialActivityFeedEntry =
				SocialActivityInterpreterLocalServiceUtil.interpret(
					socialActivity, themeDisplay);

			activity.setBody(socialActivityFeedEntry.getBody());
			activity.setTitle(socialActivityFeedEntry.getTitle());
		}

		return activity;
	}

	protected Activity getExternalActivity(SocialActivity socialActivity)
		throws Exception {

		Activity activity = new ActivityImpl(
			String.valueOf(socialActivity.getClassPK()),
			String.valueOf(socialActivity.getUserId()));

		JSONObject extraData = new JSONObjectImpl(
			socialActivity.getExtraData());

		if (extraData.has(Activity.Field.APP_ID.toString())) {
			activity.setAppId(extraData.getString("appId"));
		}

		if (extraData.has(Activity.Field.BODY.toString())) {
			activity.setBody(extraData.getString("body"));
		}

		if (extraData.has(Activity.Field.BODY_ID.toString())) {
			activity.setBodyId(extraData.getString("bodyId"));
		}

		if (extraData.has(Activity.Field.EXTERNAL_ID.toString())) {
			activity.setExternalId(extraData.getString("externalId"));
		}

		if (extraData.has(Activity.Field.MEDIA_ITEMS.toString())) {
			activity.setMediaItems(
				getMediaItems(extraData.getJSONArray("mediaItems")));
		}

		if (extraData.has(Activity.Field.POSTED_TIME.toString())) {
			activity.setPostedTime(extraData.getLong("postedTime"));
		}

		if (extraData.has(Activity.Field.PRIORITY.toString())) {
			activity.setPriority(
				Float.parseFloat(extraData.getString("priority")));
		}

		if (extraData.has(Activity.Field.STREAM_FAVICON_URL.toString())) {
			activity.setStreamFaviconUrl(
				extraData.getString("streamFaviconUrl"));
		}

		if (extraData.has(Activity.Field.STREAM_SOURCE_URL.toString())) {
			activity.setStreamSourceUrl(extraData.getString("streamSourceUrl"));
		}

		if (extraData.has(Activity.Field.STREAM_TITLE.toString())) {
			activity.setStreamTitle(extraData.getString("streamTitle"));
		}

		if (extraData.has(Activity.Field.STREAM_URL.toString())) {
			activity.setStreamUrl(extraData.getString("streamUrl"));
		}

		if (extraData.has(Activity.Field.TEMPLATE_PARAMS.toString())) {
			activity.setTemplateParams(getTemplateParams(
				extraData.getJSONArray("templateParams")));
		}

		if (extraData.has(Activity.Field.TITLE.toString())) {
			activity.setTitle(extraData.getString("title"));
		}

		if (extraData.has(Activity.Field.TITLE_ID.toString())) {
			activity.setTitleId(extraData.getString("titleId"));
		}

		if (extraData.has(Activity.Field.URL.toString())) {
			activity.setUrl(extraData.getString("url"));
		}

		return activity;
	}

	public List<MediaItem> getMediaItems(JSONArray jsonArray) {
		if (jsonArray == null) {
			return null;
		}

		List<MediaItem> mediaItems = new ArrayList<MediaItem>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			MediaItem mediaItem = new MediaItemImpl(
				jsonObject.getString("mimeType"),
				Type.valueOf(jsonObject.getString("type")),
				jsonObject.getString("url"));

			mediaItems.add(mediaItem);
		}

		return mediaItems;
	}

	public static JSONArray getMediaItems(List<MediaItem> list) {
		if (list == null) {
			return null;
		}

		JSONArray mediaItems = new JSONArrayImpl();

		for (MediaItem mediaItem : list) {
			JSONObject mediaItemJson = new JSONObjectImpl();

			mediaItemJson.put("mimeType", mediaItem.getMimeType());
			mediaItemJson.put("type", mediaItem.getType().toString());
			mediaItemJson.put("url", mediaItem.getUrl());

			mediaItems.put(mediaItemJson);
		}

		return mediaItems;
	}

	protected Map<String, String> getTemplateParams(JSONArray jsonArray) {
		if (jsonArray == null) {
			return null;
		}

		Map<String, String> templateParams = new HashMap<String, String>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			JSONArray names = jsonObject.names();

			for (int j = 0; j < names.length(); j++) {
				String name = names.getString(j);

				templateParams.put(name, jsonObject.getString(name));
			}
		}

		return templateParams;
	}

	protected JSONArray getTemplateParams(Map<String, String> map) {
		if (map == null) {
			return null;
		}

		JSONArray templateParams = new JSONArrayImpl();

		for (Entry<String,String> entry : map.entrySet()) {
			JSONObject jsonEntry = new JSONObjectImpl();

			jsonEntry.put(entry.getKey(), entry.getValue());

			templateParams.put(jsonEntry);
		}

		return templateParams;
	}

	protected ThemeDisplay getThemeDisplay(SecurityToken securityToken)
		throws Exception {

		User user = UserLocalServiceUtil.getUserById(
			GetterUtil.getLong(securityToken.getViewerId()));

		Company company = CompanyLocalServiceUtil.getCompanyById(
			user.getCompanyId());

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(company);
		themeDisplay.setLocale(user.getLocale());

		return themeDisplay;
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayActivityService.class);

}