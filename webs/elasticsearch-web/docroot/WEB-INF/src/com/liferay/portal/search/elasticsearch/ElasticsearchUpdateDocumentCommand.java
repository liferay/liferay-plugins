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

package com.liferay.portal.search.elasticsearch;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public interface ElasticsearchUpdateDocumentCommand {

	public void updateDocument(
			String documentType, SearchContext searchContext, Document document)
		throws SearchException;

	public void updateDocuments(
			String documentType, SearchContext searchContext,
			Collection<Document> documents)
		throws SearchException;

}