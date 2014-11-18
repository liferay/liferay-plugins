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
			boolean neverExpire, List<PollsChoice> choices,
			ServiceContext serviceContext)
		throws PortalException {

		PollsQuestion question = super.addQuestion(
			userId, titleMap, descriptionMap, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, choices, serviceContext);

		sendPushNotification(question);

		return question;
	}

	public void sendPushNotification(PollsQuestion question) {
		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject();

		String description = question.getDescription(Locale.US);

		payloadJSONObject.put(
			PushNotificationsConstants.KEY_MESSAGE, description);

		payloadJSONObject.put(
			PushNotificationsConstants.KEY_TYPE,
			PushNotificationsConstants.VALUE_POLLS_TYPE);

		JSONObject questionJSONObject = JSONFactoryUtil.createJSONObject();

		long questionId = question.getQuestionId();

		questionJSONObject.put(
			PushNotificationsConstants.KEY_POLLS_QUESTION_ID, questionId);

		payloadJSONObject.put(
			PushNotificationsConstants.KEY_POLLS_QUESTION, questionJSONObject);

		JSONArray choicesJSONArray = JSONFactoryUtil.createJSONArray();

		try {
			List<PollsChoice> choices = PollsChoiceLocalServiceUtil.getChoices(
				questionId);

			for (PollsChoice choice : choices) {
				JSONObject choiceJSONObject =
					JSONFactoryUtil.createJSONObject();

				choiceJSONObject.put(
					PushNotificationsConstants.KEY_POLLS_CHOICE_ID,
					choice.getChoiceId());

				choiceJSONObject.put(
					PushNotificationsConstants.KEY_POLLS_CHOICE_DESCRIPTION,
					choice.getDescription(Locale.US));

				choicesJSONArray.put(choiceJSONObject);
			}

			questionJSONObject.put(
				PushNotificationsConstants.KEY_POLLS_CHOICES, choicesJSONArray);

			PushNotificationsEntryLocalServiceUtil.sendPushNotification(
				question.getUserId(), payloadJSONObject);
		}
		catch (Exception e) {
			_log.error(
				"Unable to send push notification for polls question", e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PushNotificationsPollsQuestionLocalServiceImpl.class);

}