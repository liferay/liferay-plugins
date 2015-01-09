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

package com.liferay.bbb.util;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.BBBParticipantLocalServiceUtil;
import com.liferay.bbb.service.BBBServerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.util.ContentUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Shinn Lok
 */
public class BBBAPIUtil {

	public static BBBMeeting endMeeting(long bbbMeetingId)
		throws PortalException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		StringBundler sb = new StringBundler(7);

		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getBbbMeetingId());
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_PASSWORD);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeURL(bbbMeeting.getModeratorPassword()));

		execute(bbbMeeting, BBBConstants.API_METHOD_END, sb.toString());

		BBBMeetingLocalServiceUtil.updateStatus(
			bbbMeetingId, BBBMeetingConstants.STATUS_COMPLETED);

		return bbbMeeting;
	}

	public static String getJoinURL(
			BBBParticipant bbbParticipant, String userName)
		throws PortalException {

		if (!userName.equals(bbbParticipant.getName())) {
			bbbParticipant = BBBParticipantLocalServiceUtil.addBBBParticipant(
				bbbParticipant.getUserId(), bbbParticipant.getGroupId(),
				bbbParticipant.getBbbMeetingId(), userName, null,
				bbbParticipant.getType(),
				BBBParticipantConstants.STATUS_INVITED, new ServiceContext());
		}

		StringBundler sb = new StringBundler(11);

		sb.append(BBBConstants.API_PARAMETER_FULL_NAME);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeURL(bbbParticipant.getName()));
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbParticipant.getBbbMeetingId());

		sb.append(bbbMeeting.getBbbMeetingId());

		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_PASSWORD);
		sb.append(StringPool.EQUAL);

		if (bbbParticipant.getType() ==
				BBBParticipantConstants.TYPE_MODERATOR) {

			sb.append(HtmlUtil.escapeURL(bbbMeeting.getModeratorPassword()));
		}
		else {
			sb.append(HtmlUtil.escapeURL(bbbMeeting.getAttendeePassword()));
		}

		BBBServer bbbServer = BBBServerLocalServiceUtil.getBBBServer(
			bbbMeeting.getBbbServerId());

		return getURL(bbbServer, BBBConstants.API_METHOD_JOIN, sb.toString());
	}

	public static Document getMeetingInfoDocument(long bbbMeetingId)
		throws PortalException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		StringBundler sb = new StringBundler(7);

		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getBbbMeetingId());
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_PASSWORD);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getModeratorPassword());

		Document document = execute(
			bbbMeeting, BBBConstants.API_METHOD_GET_MEETING_INFO,
			sb.toString());

		Element element = document.getRootElement();

		String returnCode = getText(
			element, BBBConstants.API_RESPONSE_RETURN_CODE);

		if (returnCode.equals(BBBConstants.API_RESPONSE_FAILED)) {
			throw new SystemException();
		}

		return document;
	}

	public static List<String> getMeetingRecordings(long bbbMeetingId)
		throws PortalException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		BBBServer bbbServer = BBBServerLocalServiceUtil.getBBBServer(
			bbbMeeting.getBbbServerId());

		if (!isServerActive(bbbServer)) {
			return Collections.emptyList();
		}

		List<String> meetingRecordings = new ArrayList<>();

		StringBundler sb = new StringBundler(3);

		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeetingId);

		Document document = execute(
			bbbMeeting, BBBConstants.API_METHOD_GET_RECORDINGS, sb.toString());

		Element rootElement = document.getRootElement();

		Element recordingsElement = rootElement.element(
			BBBConstants.API_RESPONSE_RECORDINGS);

		Iterator iterator = recordingsElement.elementIterator(
			BBBConstants.API_RESPONSE_RECORDING);

		while (iterator.hasNext()) {
			Element element = (Element)iterator.next();

			Element playbackElement = element.element(
				BBBConstants.API_RESPONSE_PLAYBACK);

			Element formatElement = playbackElement.element(
				BBBConstants.API_RESPONSE_FORMAT);

			meetingRecordings.add(
				getText(formatElement, BBBConstants.API_RESPONSE_URL));
		}

		return meetingRecordings;
	}

	public static boolean isMeetingRunning(long bbbMeetingId) {
		try {
			getMeetingInfoDocument(bbbMeetingId);

			return true;
		}
		catch (Exception e) {
		}

		return false;
	}

	public static boolean isServerActive(BBBServer bbbServer)
		throws PortalException {

		try {
			Document document = execute(
				bbbServer, BBBConstants.API_METHOD_GET_MEETINGS,
				StringPool.BLANK);

			Element element = document.getRootElement();

			String returnCode = getText(
				element, BBBConstants.API_RESPONSE_RETURN_CODE);

			if (returnCode.equals(BBBConstants.API_RESPONSE_SUCCESS)) {
				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	public static BBBMeeting startMeeting(
			long bbbMeetingId, boolean recordMeeting)
		throws PortalException {

		BBBMeeting bbbMeeting = BBBMeetingLocalServiceUtil.getBBBMeeting(
			bbbMeetingId);

		if (bbbMeeting.getStatus() != BBBMeetingConstants.STATUS_SCHEDULED) {
			return bbbMeeting;
		}

		StringBundler sb = new StringBundler(15);

		sb.append(BBBConstants.API_PARAMETER_MEETING_ID);
		sb.append(StringPool.EQUAL);
		sb.append(bbbMeeting.getBbbMeetingId());
		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_NAME);
		sb.append(StringPool.EQUAL);
		sb.append(HtmlUtil.escapeURL(bbbMeeting.getName()));

		if (recordMeeting) {
			sb.append(StringPool.AMPERSAND);
			sb.append(BBBConstants.API_PARAMETER_RECORD);
			sb.append(StringPool.EQUAL);
			sb.append(StringPool.TRUE);
		}

		sb.append(StringPool.AMPERSAND);
		sb.append(BBBConstants.API_PARAMETER_WELCOME);
		sb.append(StringPool.EQUAL);

		String welcomeMessage = ContentUtil.get(
			"com/liferay/bbb/dependencies/meeting_welcome_message.tmpl");

		sb.append(HtmlUtil.escapeURL(welcomeMessage));

		bbbMeeting.setBbbServerId(getBbbServerId());

		Document document = execute(
			bbbMeeting, BBBConstants.API_METHOD_CREATE, sb.toString());

		Element element = document.getRootElement();

		String returnCode = getText(
			element, BBBConstants.API_RESPONSE_RETURN_CODE);

		if (returnCode.equals(BBBConstants.API_RESPONSE_FAILED)) {
			throw new SystemException();
		}

		bbbMeeting.setAttendeePassword(
			getText(element, BBBConstants.API_PARAMETER_ATTENDEE_PW));
		bbbMeeting.setModeratorPassword(
			getText(element, BBBConstants.API_PARAMETER_MODERATOR_PW));
		bbbMeeting.setStatus(BBBMeetingConstants.STATUS_IN_PROGRESS);

		BBBMeetingLocalServiceUtil.updateBBBMeeting(bbbMeeting);

		return bbbMeeting;
	}

	protected static Document execute(
			BBBMeeting bbbMeeting, String methodName, String queryString)
		throws PortalException {

		BBBServer bbbServer = BBBServerLocalServiceUtil.getBBBServer(
			bbbMeeting.getBbbServerId());

		return execute(bbbServer, methodName, queryString);
	}

	protected static Document execute(
			BBBServer bbbServer, String methodName, String queryString)
		throws PortalException {

		try {
			String url = getURL(bbbServer, methodName, queryString);

			String xml = HttpUtil.URLtoString(url);

			return SAXReaderUtil.read(xml);
		}
		catch (DocumentException de) {
			throw new SystemException(de);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected static long getBbbServerId() throws PortalException {
		TreeMap<Integer, Long> bbbServersMap = new TreeMap<>();

		BBBServerLocalServiceUtil.checkBBBServers();

		List<BBBServer> bbbServers = BBBServerLocalServiceUtil.getBBBServers(
			true);

		if (bbbServers.isEmpty()) {
			return BBBMeetingConstants.BBB_SERVER_ID_DEFAULT;
		}

		for (BBBServer bbbServer : bbbServers) {
			int count = BBBMeetingLocalServiceUtil.getBBBMeetingsCount(
				bbbServer.getBbbServerId(),
				BBBMeetingConstants.STATUS_IN_PROGRESS);

			bbbServersMap.put(count, bbbServer.getBbbServerId());
		}

		Map.Entry<Integer, Long> entry = bbbServersMap.firstEntry();

		return entry.getValue();
	}

	protected static String getText(Element parentElement, String name) {
		Element element = parentElement.element(name);

		if (element == null) {
			return null;
		}

		return element.getText();
	}

	protected static String getURL(
			BBBServer bbbServer, String methodName, String queryString)
		throws PortalException {

		StringBundler sb = new StringBundler(6);

		sb.append(bbbServer.getUrl());
		sb.append(methodName);
		sb.append(StringPool.QUESTION);
		sb.append(queryString);
		sb.append("&checksum=");

		String checksum = DigesterUtil.digestHex(
			Digester.SHA_1, methodName + queryString + bbbServer.getSecret());

		sb.append(checksum);

		return sb.toString();
	}

}