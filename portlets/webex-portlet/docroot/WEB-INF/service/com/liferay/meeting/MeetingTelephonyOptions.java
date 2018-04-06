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
public class MeetingTelephonyOptions implements Serializable {

	public int getPersonalAccountIndex() {
		return _personalAccountIndex;
	}

	public String getTeleconferenceLocation() {
		return _teleconferenceLocation;
	}

	public String getTelephonyDescription() {
		return _telephonyDescription;
	}

	public String getTelephonyURL() {
		return _telephonyURL;
	}

	public int getTspAccountIndex() {
		return _tspAccountIndex;
	}

	public boolean isEnableTSP() {
		return _enableTSP;
	}

	public void setEnableTSP(boolean enableTSP) {
		_enableTSP = enableTSP;
	}

	public void setPersonalAccountIndex(int personalAccountIndex) {
		_personalAccountIndex = personalAccountIndex;
	}

	public void setTeleconferenceLocation(String teleconferenceLocation) {
		_teleconferenceLocation = teleconferenceLocation;
	}

	public void setTelephonyDescription(String telephonyDescription) {
		_telephonyDescription = telephonyDescription;
	}

	public void setTelephonyURL(String telephonyURL) {
		_telephonyURL = telephonyURL;
	}

	public void setTspAccountIndex(int tspAccountIndex) {
		_tspAccountIndex = tspAccountIndex;
	}

	private boolean _enableTSP;
	private int _personalAccountIndex;
	private String _teleconferenceLocation;
	private String _telephonyDescription;
	private String _telephonyURL;
	private int _tspAccountIndex;

}