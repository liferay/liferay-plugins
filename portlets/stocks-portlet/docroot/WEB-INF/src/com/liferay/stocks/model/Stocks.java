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

package com.liferay.stocks.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class Stocks implements Serializable {

	public Stocks(String symbol) {
		_symbol = symbol;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #Stocks(String)}
	 */
	@Deprecated
	public Stocks(
		String symbol, double lastTrade, double previousClose, double open,
		double dayHigh, double dayLow, long volume) {
	}

	public double getChange() {
		return _lastTrade - _previousClose;
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
		return _previousClose;
	}

	public String getSymbol() {
		return _symbol;
	}

	public long getVolume() {
		return _volume;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isChangeAvailable() {
		return true;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isDayHighAvailable() {
		return true;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isDayLowAvailable() {
		return true;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isLastTradeAvailable() {
		return true;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isOpenAvailable() {
		return true;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isPreviousCloseAvailable() {
		return true;
	}

	public boolean isValid() {
		if ((_dayHigh == 0) && (_dayLow == 0) && (_open == 0) &&
			(_previousClose == 0) && (_volume == 0)) {

			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public boolean isVolumeAvailable() {
		return true;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setChange(double change) {
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setChangeAvailable(boolean changeAvailable) {
	}

	public void setDayHigh(double dayHigh) {
		_dayHigh = dayHigh;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setDayHighAvailable(boolean dayHighAvailable) {
	}

	public void setDayLow(double dayLow) {
		_dayLow = dayLow;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setDayLowAvailable(boolean dayLowAvailable) {
	}

	public void setLastTrade(double lastTrade) {
		_lastTrade = lastTrade;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setLastTradeAvailable(boolean lastTradeAvailable) {
	}

	public void setOpen(double open) {
		_open = open;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setOpenAvailable(boolean openAvailable) {
	}

	public void setPreviousClose(double previousClose) {
		_previousClose = previousClose;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setPreviousCloseAvailable(boolean previousCloseAvailable) {
	}

	public void setVolume(long volume) {
		_volume = volume;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	public void setVolumeAvailable(boolean volumeAvailable) {
	}

	private double _dayHigh;
	private double _dayLow;
	private double _lastTrade;
	private double _open;
	private double _previousClose;
	private String _symbol;
	private long _volume;

}