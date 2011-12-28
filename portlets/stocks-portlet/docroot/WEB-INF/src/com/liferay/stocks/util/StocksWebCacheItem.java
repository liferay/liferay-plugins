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

package com.liferay.stocks.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.stocks.model.Stocks;

import java.util.StringTokenizer;

/**
 * @author Brian Wing Shun Chan
 */
public class StocksWebCacheItem implements WebCacheItem {

	public StocksWebCacheItem(String symbol) {
		_symbol = symbol;
	}

	public Object convert(String key) throws WebCacheException {
		String symbol = _symbol;
		double lastTrade = 0.0;
		double change = 0.0;
		double open = 0.0;
		double dayHigh = 0.0;
		double dayLow = 0.0;
		long volume = 0;

		Stocks stocks = new Stocks(
			symbol, lastTrade, change, open, dayHigh, dayLow, volume);

		try {
			String text = HttpUtil.URLtoString(
				"http://finance.yahoo.com/d/quotes.csv?s=" +
					symbol + "&f=sl1d1t1c1ohgv&e=.csv");

			StringTokenizer st = new StringTokenizer(text, StringPool.COMMA);

			// Skip symbol

			st.nextToken();

			try {
				lastTrade = GetterUtil.getDouble(
					st.nextToken().replace('"', ' ').trim());

				stocks.setLastTrade(lastTrade);
			}
			catch (NumberFormatException nfe) {
				stocks.setLastTradeAvailable(false);
			}

			// Skip date and time

			st.nextToken();
			st.nextToken();

			try {
				change = GetterUtil.getDouble(
					st.nextToken().replace('"', ' ').trim());

				stocks.setChange(change);
			}
			catch (NumberFormatException nfe) {
				stocks.setChangeAvailable(false);
			}

			try {
				open = GetterUtil.getDouble(
					st.nextToken().replace('"', ' ').trim());

				stocks.setOpen(open);
			}
			catch (NumberFormatException nfe) {
				stocks.setOpenAvailable(false);
			}

			try {
				dayHigh = GetterUtil.getDouble(
					st.nextToken().replace('"', ' ').trim());

				stocks.setDayHigh(dayHigh);
			}
			catch (NumberFormatException nfe) {
				stocks.setDayHighAvailable(false);
			}

			try {
				dayLow = GetterUtil.getDouble(
					st.nextToken().replace('"', ' ').trim());

				stocks.setDayLow(dayLow);
			}
			catch (NumberFormatException nfe) {
				stocks.setDayLowAvailable(false);
			}

			try {
				volume = GetterUtil.getLong(
					st.nextToken().replace('"', ' ').trim());

				stocks.setVolume(volume);
			}
			catch (NumberFormatException nfe) {
				stocks.setVolumeAvailable(false);
			}

			if (!stocks.isValid()) {
				throw new WebCacheException(symbol);
			}
		}
		catch (Exception e) {
			throw new WebCacheException(symbol + " " + e.toString());
		}

		return stocks;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private String _symbol;

}