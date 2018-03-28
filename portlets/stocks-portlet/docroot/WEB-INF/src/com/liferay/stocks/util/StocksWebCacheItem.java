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

package com.liferay.stocks.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.stocks.model.Stocks;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class StocksWebCacheItem implements WebCacheItem {

	public StocksWebCacheItem(String symbol) {
		_symbol = symbol;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		String symbol = _symbol;

		Stocks stocks = new Stocks(symbol);

		try {
			String text = HttpUtil.URLtoString(
				"http://www.google.com/finance/getprices?i=60&p=2d&" +
					"f=d,o,h,l,c,v&df=cpct&q=" + symbol);

			double dayHigh = 0.0;
			double dayLow = 0.0;
			long volume = 0;

			String[] lines = StringUtil.splitLines(text);

			for (int i = lines.length - 1;; i--) {
				String[] parts = StringUtil.split(lines[i]);

				if (i == (lines.length - 1)) {
					stocks.setLastTrade(GetterUtil.getDouble(parts[1]));
				}

				double high = GetterUtil.getDouble(parts[2]);

				if (high > dayHigh) {
					dayHigh = high;
				}

				double low = GetterUtil.getDouble(parts[3]);

				if ((dayLow == 0.0) || (low < dayLow)) {
					dayLow = low;
				}

				volume += GetterUtil.getLong(parts[5]);

				String date = parts[0];

				if (!date.startsWith("a")) {
					continue;
				}

				stocks.setDayHigh(dayHigh);
				stocks.setDayLow(dayLow);
				stocks.setVolume(volume);

				stocks.setOpen(GetterUtil.getDouble(parts[4]));

				String[] previousDayParts = StringUtil.split(lines[i - 1]);

				stocks.setPreviousClose(
					GetterUtil.getDouble(previousDayParts[1]));

				break;
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

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private String _symbol;

}