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

package com.liferay.meeting;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetingOptions implements Serializable {

	public boolean isAllowApplicationSharing() {
		return _allowApplicationSharing;
	}

	public boolean isAllowDesktopSharing() {
		return _allowDesktopSharing;
	}

	public boolean isEnableAudioVideo() {
		return _enableAudioVideo;
	}

	public boolean isEnableChat() {
		return _enableChat;
	}

	public boolean isEnableChatBetweenAttendees() {
		return _enableChatBetweenAttendees;
	}

	public boolean isEnableChatWithHost() {
		return _enableChatWithHost;
	}

	public boolean isEnableChatWithPresenter() {
		return _enableChatWithPresenter;
	}

	public boolean isEnableNotes() {
		return _enableNotes;
	}

	public boolean isEnablePoll() {
		return _enablePoll;
	}

	public boolean isEnableRegistration() {
		return _enableRegistration;
	}

	public boolean isSupportFeedback() {
		return _supportFeedback;
	}

	public boolean isSupportPanelists() {
		return _supportPanelists;
	}

	public boolean isSupportPresentation() {
		return _supportPresentation;
	}

	public boolean isSupportQAndA() {
		return _supportQAndA;
	}

	public boolean isSupportRecordMeetingByAttendee() {
		return _supportRecordMeetingByAttendee;
	}

	public boolean isSupportVoip() {
		return _supportVoip;
	}

	public void setAllowApplicationSharing(boolean allowApplicationSharing) {
		_allowApplicationSharing = allowApplicationSharing;
	}

	public void setAllowDesktopSharing(boolean allowDesktopSharing) {
		_allowDesktopSharing = allowDesktopSharing;
	}

	public void setEnableAudioVideo(boolean enableAudioVideo) {
		_enableAudioVideo = enableAudioVideo;
	}

	public void setEnableChat(boolean enableChat) {
		_enableChat = enableChat;
	}

	public void setEnableChatBetweenAttendees(
		boolean enableChatBetweenAttendees) {

		_enableChatBetweenAttendees = enableChatBetweenAttendees;
	}

	public void setEnableChatWithHost(boolean enableChatWithHost) {
		_enableChatWithHost = enableChatWithHost;
	}

	public void setEnableChatWithPresenter(boolean enableChatWithPresenter) {
		_enableChatWithPresenter = enableChatWithPresenter;
	}

	public void setEnableNotes(boolean enableNotes) {
		_enableNotes = enableNotes;
	}

	public void setEnablePoll(boolean enablePoll) {
		_enablePoll = enablePoll;
	}

	public void setEnableRegistration(boolean enableRegistration) {
		_enableRegistration = enableRegistration;
	}

	public void setSupportFeedback(boolean supportFeedback) {
		_supportFeedback = supportFeedback;
	}

	public void setSupportPanelists(boolean supportPanelists) {
		_supportPanelists = supportPanelists;
	}

	public void setSupportPresentation(boolean supportPresentation) {
		_supportPresentation = supportPresentation;
	}

	public void setSupportQAndA(boolean supportQAndA) {
		_supportQAndA = supportQAndA;
	}

	public void setSupportRecordMeetingByAttendee(
		boolean supportRecordMeetingByAttendee) {

		_supportRecordMeetingByAttendee = supportRecordMeetingByAttendee;
	}

	public void setSupportVoip(boolean supportVoip) {
		_supportVoip = supportVoip;
	}

	private boolean _allowApplicationSharing;
	private boolean _allowDesktopSharing;
	private boolean _enableAudioVideo;
	private boolean _enableChat;
	private boolean _enableChatBetweenAttendees;
	private boolean _enableChatWithHost;
	private boolean _enableChatWithPresenter;
	private boolean _enableNotes;
	private boolean _enablePoll;
	private boolean _enableRegistration;
	private boolean _supportFeedback;
	private boolean _supportPanelists;
	private boolean _supportPresentation;
	private boolean _supportQAndA;
	private boolean _supportRecordMeetingByAttendee;
	private boolean _supportVoip;

}