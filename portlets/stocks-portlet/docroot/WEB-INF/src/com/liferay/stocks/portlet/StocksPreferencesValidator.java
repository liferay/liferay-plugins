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

package com.liferay.stocks.portlet;

import com.liferay.stocks.model.Stocks;
import com.liferay.stocks.util.StocksUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class StocksPreferencesValidator implements PreferencesValidator {

	public void validate(PortletPreferences preferences)
		throws ValidatorException {

		List<String> badSymbols = new ArrayList<String>();

		String[] symbols = preferences.getValues("symbols", new String[0]);

		for (String symbol : symbols) {
			Stocks stocks = StocksUtil.getStocks(symbol);

			if (stocks == null) {
				badSymbols.add(symbol);
			}
		}

		if (badSymbols.size() > 0) {
			throw new ValidatorException(
				"Failed to retrieve symbols", badSymbols);
		}
	}

}