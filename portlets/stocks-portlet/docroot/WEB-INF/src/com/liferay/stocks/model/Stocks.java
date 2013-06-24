/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.stocks.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class Stocks implements Serializable {

	public Stocks(
		String symbol, double lastTrade, double change, double open,
		double dayHigh, double dayLow, long volume) {

		_symbol = symbol;
		_lastTrade = lastTrade;
		_change = change;
		_open = open;
		_dayHigh = dayHigh;
		_dayLow = dayLow;
		_volume = volume;
	}

	public double getChange() {
		return _change;
	}

	public double getDayHigh() {
		return _dayHigh;
	}

	public double getDayLow() {
		return _dayLow;
	}

	public double getLastTrade() {
		return _lastTrade;
	}

	public double getOpen() {
		return _open;
	}

	public double getPreviousClose() {
		return _lastTrade - _change;
	}

	public String getSymbol() {
		return _symbol;
	}

	public long getVolume() {
		return _volume;
	}

	public boolean isChangeAvailable() {
		return _changeAvailable;
	}

	public boolean isDayHighAvailable() {
		return _dayHighAvailable;
	}

	public boolean isDayLowAvailable() {
		return _dayLowAvailable;
	}

	public boolean isLastTradeAvailable() {
		return _lastTradeAvailable;
	}

	public boolean isOpenAvailable() {
		return _openAvailable;
	}

	public boolean isPreviousCloseAvailable() {
		if (isLastTradeAvailable() && isChangeAvailable()) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isValid() {
		if ((_change == 0) && (_open == 0) && (_dayHigh == 0) &&
			(_dayLow == 0) && (_volume == 0)) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean isVolumeAvailable() {
		return _volumeAvailable;
	}

	public void setChange(double change) {
		_change = change;
	}

	public void setChangeAvailable(boolean changeAvailable) {
		_changeAvailable = changeAvailable;
	}

	public void setDayHigh(double dayHigh) {
		_dayHigh = dayHigh;
	}

	public void setDayHighAvailable(boolean dayHighAvailable) {
		_dayHighAvailable = dayHighAvailable;
	}

	public void setDayLow(double dayLow) {
		_dayLow = dayLow;
	}

	public void setDayLowAvailable(boolean dayLowAvailable) {
		_dayLowAvailable = dayLowAvailable;
	}

	public void setLastTrade(double lastTrade) {
		_lastTrade = lastTrade;
	}

	public void setLastTradeAvailable(boolean lastTradeAvailable) {
		_lastTradeAvailable = lastTradeAvailable;
	}

	public void setOpen(double open) {
		_open = open;
	}

	public void setOpenAvailable(boolean openAvailable) {
		_openAvailable = openAvailable;
	}

	public void setVolume(long volume) {
		_volume = volume;
	}

	public void setVolumeAvailable(boolean volumeAvailable) {
		_volumeAvailable = volumeAvailable;
	}

	private double _change;
	private boolean _changeAvailable = true;
	private double _dayHigh;
	private boolean _dayHighAvailable = true;
	private double _dayLow;
	private boolean _dayLowAvailable = true;
	private double _lastTrade;
	private boolean _lastTradeAvailable = true;
	private double _open;
	private boolean _openAvailable = true;
	private String _symbol;
	private long _volume;
	private boolean _volumeAvailable = true;

}