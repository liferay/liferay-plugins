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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;
import com.liferay.stocks.model.Stocks;

/**
 * @author Brian Wing Shun Chan
 */
public class StocksUtil {

	public static Stocks getStocks(String symbol) {
		WebCacheItem wci = new StocksWebCacheItem(symbol);

		String key = StocksUtil.class.getName() + StringPool.PERIOD + symbol;

		try {
			return (Stocks)WebCachePoolUtil.get(key, wci);
		}
		catch (ClassCastException cce) {
			WebCachePoolUtil.remove(key);

			return (Stocks)WebCachePoolUtil.get(key, wci);
		}
	}

}