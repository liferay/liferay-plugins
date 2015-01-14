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

package com.liferay.knowledgebase.admin.importer;

import com.liferay.knowledgebase.model.KBArticle;

import java.util.Comparator;

/**
 * @author Jesse Rao
 */
public class KBArticleComparator implements Comparator<KBArticle> {

	@Override
	public int compare(KBArticle kbArticle1, KBArticle kbArticle2) {
		String urlTitle1 = kbArticle1.getUrlTitle();
		String urlTitle2 = kbArticle2.getUrlTitle();

		return urlTitle1.compareTo(urlTitle2);
	}

}