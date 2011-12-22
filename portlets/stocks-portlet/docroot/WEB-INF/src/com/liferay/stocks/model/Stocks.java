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

	public String getSymbol() {
		return _symbol;
	}

	public double getLastTrade() {
		return _lastTrade;
	}

	public void setLastTrade(double lastTrade) {
		_lastTrade = lastTrade;
	}

	public boolean isLastTradeAvailable() {
		return _lastTradeAvailable;
	}

	public void setLastTradeAvailable(boolean lastTradeAvailable) {
		_lastTradeAvailable = lastTradeAvailable;
	}

	public double getChange() {
		return _change;
	}

	public void setChange(double change) {
		_change = change;
	}

	public boolean isChangeAvailable() {
		return _changeAvailable;
	}

	public void setChangeAvailable(boolean changeAvailable) {
		_changeAvailable = changeAvailable;
	}

	public double getPreviousClose() {
		return _lastTrade - _change;
	}

	public boolean isPreviousCloseAvailable() {
		if (isLastTradeAvailable() && isChangeAvailable()) {
			return true;
		}
		else {
			return false;
		}
	}

	public double getOpen() {
		return _open;
	}

	public void setOpen(double open) {
		_open = open;
	}

	public boolean isOpenAvailable() {
		return _openAvailable;
	}

	public void setOpenAvailable(boolean openAvailable) {
		_openAvailable = openAvailable;
	}

	public double getDayHigh() {
		return _dayHigh;
	}

	public void setDayHigh(double dayHigh) {
		_dayHigh = dayHigh;
	}

	public boolean isDayHighAvailable() {
		return _dayHighAvailable;
	}

	public void setDayHighAvailable(boolean dayHighAvailable) {
		_dayHighAvailable = dayHighAvailable;
	}

	public double getDayLow() {
		return _dayLow;
	}

	public void setDayLow(double dayLow) {
		_dayLow = dayLow;
	}

	public boolean isDayLowAvailable() {
		return _dayLowAvailable;
	}

	public void setDayLowAvailable(boolean dayLowAvailable) {
		_dayLowAvailable = dayLowAvailable;
	}

	public long getVolume() {
		return _volume;
	}

	public void setVolume(long volume) {
		_volume = volume;
	}

	public boolean isVolumeAvailable() {
		return _volumeAvailable;
	}

	public void setVolumeAvailable(boolean volumeAvailable) {
		_volumeAvailable = volumeAvailable;
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

	private String _symbol;
	private double _lastTrade;
	private boolean _lastTradeAvailable = true;
	private double _change;
	private boolean _changeAvailable = true;
	private double _open;
	private boolean _openAvailable = true;
	private double _dayHigh;
	private boolean _dayHighAvailable = true;
	private double _dayLow;
	private boolean _dayLowAvailable = true;
	private long _volume;
	private boolean _volumeAvailable = true;

}