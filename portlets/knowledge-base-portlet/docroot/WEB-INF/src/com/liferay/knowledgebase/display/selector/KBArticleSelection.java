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

package com.liferay.knowledgebase.display.selector;

import com.liferay.knowledgebase.model.KBArticle;

/**
 * @author Adolfo Pérez
 */
public class KBArticleSelection {

	public KBArticleSelection(KBArticle kbArticle, boolean exactMatch) {
		_kbArticle = kbArticle;
		_exactMatch = exactMatch;
		_keywords = null;
	}

	public KBArticleSelection(KBArticle kbArticle, String[] keywords) {
		_kbArticle = kbArticle;
		_exactMatch = false;
		_keywords = keywords;
	}

	public KBArticle getKBArticle() {
		return _kbArticle;
	}

	public String[] getKeywords() {
		return _keywords;
	}

	public boolean isExactMatch() {
		return _exactMatch;
	}

	protected void setExactMatch(boolean exactMatch) {
		_exactMatch = exactMatch;
	}

	private boolean _exactMatch;
	private final KBArticle _kbArticle;
	private final String[] _keywords;

}