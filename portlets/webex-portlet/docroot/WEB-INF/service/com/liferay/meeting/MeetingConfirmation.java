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
public class MeetingConfirmation implements Serializable {

	public String getAttendeeURL() {
		return _attendeeURL;
	}

	public String getHostURL() {
		return _hostURL;
	}

	public long getMeetingKey() {
		return _meetingKey;
	}

	public void setAttendeeURL(String attendeeURL) {
		_attendeeURL = attendeeURL;
	}

	public void setHostURL(String hostURL) {
		_hostURL = hostURL;
	}

	public void setMeetingKey(long meetingKey) {
		_meetingKey = meetingKey;
	}

	private String _attendeeURL;
	private String _hostURL;
	private long _meetingKey;

}