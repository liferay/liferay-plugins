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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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

	public void doCreateActivity(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			Activity activity, SecurityToken securityToken)
		throws Exception {

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		JSONObject extraDataJSON = JSONFactoryUtil.createJSONObject();

		extraDataJSON.put("appId", activity.getAppId());
		extraDataJSON.put("body", activity.getBody());
		extraDataJSON.put("bodyId", activity.getBodyId());
		extraDataJSON.put("externalId", activity.getExternalId());
		extraDataJSON.put(
			"mediaItems", getMediaItems(activity.getMediaItems()));
		extraDataJSON.put("postedTime", activity.getPostedTime());
		extraDataJSON.put("priority", activity.getPriority());
		extraDataJSON.put("streamFaviconUrl", activity.getStreamFaviconUrl());
		extraDataJSON.put("streamSourceUrl", activity.getStreamSourceUrl());
		extraDataJSON.put("streamTitle", activity.getStreamTitle());
		extraDataJSON.put("streamUrl", activity.getStreamUrl());
		extraDataJSON.put(
			"templateParams", getTemplateParams(activity.getTemplateParams()));
		extraDataJSON.put("title", activity.getTitle());
		extraDataJSON.put("titleId", activity.getTitleId());
		extraDataJSON.put("url", activity.getUrl());

		SocialActivityLocalServiceUtil.addActivity(
			userIdLong, 0L, Activity.class.getName(),
			activity.getPostedTime().longValue(),
			activity.getAppId().hashCode(), extraDataJSON.toString(), 0L);
	}

	public void doDeleteActivities(
			UserId userId, GroupId groupId, String appId,
			Set<String> activityIds, SecurityToken securityToken)
		throws Exception {

		for (String activityId : activityIds) {
			long activityIdLong = GetterUtil.getLong(activityId);

			SocialActivityLocalServiceUtil.deleteActivity(activityIdLong);
		}
	}

	public RestfulCollection<Activity> doGetActivities(
			Set<UserId> userIds, GroupId groupId, String appId,
			Set<String> fields, CollectionOptions collectionOptions,
			SecurityToken securityToken)
		throws Exception {

		ThemeDisplay themeDisplay = getThemeDisplay(securityToken);

		List<Activity> activities = new ArrayList<Activity>();

		for (UserId userId : userIds) {
			long userIdLong = GetterUtil.getLong(
				userId.getUserId(securityToken));

			List<Activity> personActivities = getActivities(
				themeDisplay, userIdLong);

			activities.addAll(personActivities);
		}

		return new RestfulCollection<Activity>(
			activities, collectionOptions.getFirst(), activities.size(),
			collectionOptions.getMax());
	}

	public RestfulCollection<Activity> doGetActivities(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			CollectionOptions collectionOptions, Set<String> activityIds,
			SecurityToken securityToken)
		throws Exception {

		ThemeDisplay themeDisplay = getThemeDisplay(securityToken);

		long userIdLong = GetterUtil.getLong(userId.getUserId(securityToken));

		List<Activity> activities = getActivities(themeDisplay, userIdLong);

		return new RestfulCollection<Activity>(
			activities, collectionOptions.getFirst(), activities.size(),
			collectionOptions.getMax());
	}

	public Activity doGetActivity(
			UserId userId, GroupId groupId, String appId, Set<String> fields,
			String activityId, SecurityToken securityToken)
		throws Exception {

		ThemeDisplay themeDisplay = getThemeDisplay(securityToken);

		long activityIdLong = GetterUtil.getLong(activityId);

		SocialActivity socialActivity =
			SocialActivityLocalServiceUtil.getActivity(activityIdLong);

		return getActivity(themeDisplay, socialActivity);
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

	protected List<Activity> getActivities(
			ThemeDisplay themeDisplay, long userId)
		throws Exception {

		List<Activity> activities = new ArrayList<Activity>();

		List<SocialActivity> socialActivities =
			SocialActivityLocalServiceUtil.getUserActivities(userId, 0,  20);

		for (SocialActivity socialActivity : socialActivities) {
			Activity activity = getActivity(themeDisplay, socialActivity);

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
			activity.setUrl(socialActivityFeedEntry.getLink());
		}

		return activity;
	}

	protected Activity getExternalActivity(SocialActivity socialActivity)
		throws Exception {

		Activity activity = new ActivityImpl(
			String.valueOf(socialActivity.getClassPK()),
			String.valueOf(socialActivity.getUserId()));

		JSONObject extraDataJSON = JSONFactoryUtil.createJSONObject(
			socialActivity.getExtraData());

		if (extraDataJSON.has(Activity.Field.APP_ID.toString())) {
			activity.setAppId(extraDataJSON.getString("appId"));
		}

		if (extraDataJSON.has(Activity.Field.BODY.toString())) {
			activity.setBody(extraDataJSON.getString("body"));
		}

		if (extraDataJSON.has(Activity.Field.BODY_ID.toString())) {
			activity.setBodyId(extraDataJSON.getString("bodyId"));
		}

		if (extraDataJSON.has(Activity.Field.EXTERNAL_ID.toString())) {
			activity.setExternalId(extraDataJSON.getString("externalId"));
		}

		if (extraDataJSON.has(Activity.Field.MEDIA_ITEMS.toString())) {
			activity.setMediaItems(
				getMediaItems(extraDataJSON.getJSONArray("mediaItems")));
		}

		if (extraDataJSON.has(Activity.Field.POSTED_TIME.toString())) {
			activity.setPostedTime(extraDataJSON.getLong("postedTime"));
		}

		if (extraDataJSON.has(Activity.Field.PRIORITY.toString())) {
			activity.setPriority(
				Float.parseFloat(extraDataJSON.getString("priority")));
		}

		if (extraDataJSON.has(Activity.Field.STREAM_FAVICON_URL.toString())) {
			activity.setStreamFaviconUrl(
				extraDataJSON.getString("streamFaviconUrl"));
		}

		if (extraDataJSON.has(Activity.Field.STREAM_SOURCE_URL.toString())) {
			activity.setStreamSourceUrl(
				extraDataJSON.getString("streamSourceUrl"));
		}

		if (extraDataJSON.has(Activity.Field.STREAM_TITLE.toString())) {
			activity.setStreamTitle(extraDataJSON.getString("streamTitle"));
		}

		if (extraDataJSON.has(Activity.Field.STREAM_URL.toString())) {
			activity.setStreamUrl(extraDataJSON.getString("streamUrl"));
		}

		if (extraDataJSON.has(Activity.Field.TEMPLATE_PARAMS.toString())) {
			activity.setTemplateParams(getTemplateParams(
				extraDataJSON.getJSONArray("templateParams")));
		}

		if (extraDataJSON.has(Activity.Field.TITLE.toString())) {
			activity.setTitle(extraDataJSON.getString("title"));
		}

		if (extraDataJSON.has(Activity.Field.TITLE_ID.toString())) {
			activity.setTitleId(extraDataJSON.getString("titleId"));
		}

		if (extraDataJSON.has(Activity.Field.URL.toString())) {
			activity.setUrl(extraDataJSON.getString("url"));
		}

		return activity;
	}

	protected List<MediaItem> getMediaItems(JSONArray jsonArray) {
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

	protected JSONArray getMediaItems(List<MediaItem> mediaItems) {
		if (mediaItems == null) {
			return null;
		}

		JSONArray mediaItemsJSONArray = JSONFactoryUtil.createJSONArray();

		for (MediaItem mediaItem : mediaItems) {
			JSONObject mediaItemJSON = JSONFactoryUtil.createJSONObject();

			mediaItemJSON.put("mimeType", mediaItem.getMimeType());
			mediaItemJSON.put("type", mediaItem.getType().toString());
			mediaItemJSON.put("url", mediaItem.getUrl());

			mediaItemsJSONArray.put(mediaItemJSON);
		}

		return mediaItemsJSONArray;
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

		JSONArray templateParamsJSONArray = JSONFactoryUtil.createJSONArray();

		for (Entry<String, String> entry : map.entrySet()) {
			JSONObject entryJSONObject = JSONFactoryUtil.createJSONObject();

			entryJSONObject.put(entry.getKey(), entry.getValue());

			templateParamsJSONArray.put(entryJSONObject);
		}

		return templateParamsJSONArray;
	}

	protected ThemeDisplay getThemeDisplay(SecurityToken securityToken)
		throws Exception {

		long userIdLong = GetterUtil.getLong(securityToken.getViewerId());

		User user = UserLocalServiceUtil.getUserById(userIdLong);

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