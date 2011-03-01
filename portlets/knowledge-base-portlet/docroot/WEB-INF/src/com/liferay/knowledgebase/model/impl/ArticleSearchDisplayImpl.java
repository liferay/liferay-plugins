/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.model.ArticleSearchDisplay;

import java.util.List;

/**
 * @author Peter Shin
 */
public class ArticleSearchDisplayImpl implements ArticleSearchDisplay {

	public ArticleSearchDisplayImpl(
		List<Article> results, int total, int[] curStartValues) {

		_results = results;
		_total = total;
		_curStartValues = curStartValues;
	}

	public int[] getCurStartValues() {
		return _curStartValues;
	}

	public List<Article> getResults() {
		return _results;
	}

	public int getTotal() {
		return _total;
	}

	public void setCurStartValues(int[] curStartValues) {
		_curStartValues = curStartValues;
	}

	public void setResults(List<Article> results) {
		_results = results;
	}

	public void setTotal(int total) {
		_total = total;
	}

	private int[] _curStartValues;
	private List<Article> _results;
	private int _total;

}