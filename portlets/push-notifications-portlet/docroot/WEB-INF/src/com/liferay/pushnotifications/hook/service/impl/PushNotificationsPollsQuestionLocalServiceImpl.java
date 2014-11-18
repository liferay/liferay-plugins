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

package com.liferay.pushnotifications.hook.service.impl;

import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.polls.service.PollsQuestionLocalService;
import com.liferay.polls.service.PollsQuestionLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.pushnotifications.service.PushNotificationsEntryLocalServiceUtil;
import com.liferay.pushnotifications.util.PushNotificationsConstants;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Bruno Farache
 */
public class PushNotificationsPollsQuestionLocalServiceImpl
	extends PollsQuestionLocalServiceWrapper {

	public PushNotificationsPollsQuestionLocalServiceImpl(
		PollsQuestionLocalService pollsQuestionLocalService) {

		super(pollsQuestionLocalService);
	}

	@Override
	public PollsQuestion addQuestion(
			long userId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, List<PollsChoice> pollsChoices,
			ServiceContext serviceContext)
		throws PortalException {

		PollsQuestion pollsQuestion = super.addQuestion(
			userId, titleMap, descriptionMap, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, pollsChoices, serviceContext);

		sendPushNotification(pollsQuestion);

		return pollsQuestion;
	}

	public void sendPushNotification(PollsQuestion pollsQuestion) {
		try {
			JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject();

			payloadJSONObject.put(
				PushNotificationsConstants.KEY_MESSAGE,
				pollsQuestion.getDescription(Locale.US));

			JSONObject pollsQuestionJSONObject =
				JSONFactoryUtil.createJSONObject();

			pollsQuestionJSONObject.put(
				PushNotificationsConstants.KEY_POLLS_QUESTION_ID,
				pollsQuestion.getQuestionId());

			JSONArray pollsChoicesJSONArray = JSONFactoryUtil.createJSONArray();

			List<PollsChoice> pollsChoices =
				PollsChoiceLocalServiceUtil.getChoices(
					pollsQuestion.getQuestionId());

			for (PollsChoice pollsChoice : pollsChoices) {
				JSONObject pollsChoiceJSONObject =
					JSONFactoryUtil.createJSONObject();

				pollsChoiceJSONObject.put(
					PushNotificationsConstants.KEY_POLLS_CHOICE_ID,
					pollsChoice.getChoiceId());
				pollsChoiceJSONObject.put(
					PushNotificationsConstants.KEY_POLLS_CHOICE_DESCRIPTION,
					pollsChoice.getDescription(Locale.US));

				pollsChoicesJSONArray.put(pollsChoiceJSONObject);
			}

			pollsQuestionJSONObject.put(
				PushNotificationsConstants.KEY_POLLS_CHOICES,
				pollsChoicesJSONArray);

			payloadJSONObject.put(
				PushNotificationsConstants.KEY_POLLS_QUESTION,
				pollsQuestionJSONObject);

			payloadJSONObject.put(
				PushNotificationsConstants.KEY_TYPE,
				PushNotificationsConstants.VALUE_TYPE_POLLS);

			PushNotificationsEntryLocalServiceUtil.sendPushNotification(
				pollsQuestion.getUserId(), payloadJSONObject);
		}
		catch (Exception e) {
			_log.error(
				"Unable to send push notification for polls question", e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsPollsQuestionLocalServiceImpl.class);

}