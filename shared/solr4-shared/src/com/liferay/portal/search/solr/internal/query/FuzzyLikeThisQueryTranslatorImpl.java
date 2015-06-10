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

package com.liferay.portal.search.solr.internal.query;

import com.liferay.portal.kernel.search.generic.FuzzyLikeThisQuery;
import com.liferay.portal.search.solr.query.FuzzyLikeThisQueryTranslator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = FuzzyLikeThisQueryTranslator.class)
public class FuzzyLikeThisQueryTranslatorImpl
	implements FuzzyLikeThisQueryTranslator {

	@Override
	public org.apache.lucene.search.Query translate(
		FuzzyLikeThisQuery fuzzyLikeThisQuery) {

		throw new UnsupportedOperationException(
			"Fuzzy like this query not supported in Solr");
	}

}