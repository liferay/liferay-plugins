/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.service.http;

import com.liferay.polls.service.PollsQuestionServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.polls.service.PollsQuestionServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.polls.model.PollsQuestionSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.polls.model.PollsQuestion}, that is translated to a
 * {@link com.liferay.polls.model.PollsQuestionSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Juan Fern√°ndez
 * @see       PollsQuestionServiceHttp
 * @see       com.liferay.polls.model.PollsQuestionSoap
 * @see       com.liferay.polls.service.PollsQuestionServiceUtil
 * @generated
 */
public class PollsQuestionServiceSoap {
	public static com.liferay.polls.model.PollsQuestionSoap addPollsQuestion(
		java.lang.String[] titleMapLanguageIds,
		java.lang.String[] titleMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.polls.model.PollsChoiceSoap[] pollsChoices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.addPollsQuestion(titleMap,
					descriptionMap, expirationDateMonth, expirationDateDay,
					expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire,
					com.liferay.polls.model.impl.PollsChoiceModelImpl.toModels(
						pollsChoices), serviceContext);

			return com.liferay.polls.model.PollsQuestionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.polls.model.PollsQuestionSoap deletePollsQuestion(
		long pollsQuestionId) throws RemoteException {
		try {
			com.liferay.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.deletePollsQuestion(pollsQuestionId);

			return com.liferay.polls.model.PollsQuestionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.polls.model.PollsQuestionSoap getPollsQuestion(
		long pollsQuestionId) throws RemoteException {
		try {
			com.liferay.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.getPollsQuestion(pollsQuestionId);

			return com.liferay.polls.model.PollsQuestionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.polls.model.PollsQuestionSoap updatePollsQuestion(
		long pollsQuestionId, java.lang.String[] titleMapLanguageIds,
		java.lang.String[] titleMapValues,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire,
		com.liferay.polls.model.PollsChoiceSoap[] pollsChoices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(titleMapLanguageIds,
					titleMapValues);
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.polls.model.PollsQuestion returnValue = PollsQuestionServiceUtil.updatePollsQuestion(pollsQuestionId,
					titleMap, descriptionMap, expirationDateMonth,
					expirationDateDay, expirationDateYear, expirationDateHour,
					expirationDateMinute, neverExpire,
					com.liferay.polls.model.impl.PollsChoiceModelImpl.toModels(
						pollsChoices), serviceContext);

			return com.liferay.polls.model.PollsQuestionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PollsQuestionServiceSoap.class);
}