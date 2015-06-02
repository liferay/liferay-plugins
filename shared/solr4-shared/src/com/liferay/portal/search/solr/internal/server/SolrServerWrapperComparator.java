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

package com.liferay.portal.search.solr.internal.server;

import java.util.Comparator;

/**
 * @author Michael C. Han
 */
public class SolrServerWrapperComparator
	implements Comparator<SolrServerWrapper> {

	@Override
	public int compare(
		SolrServerWrapper solrServerWrapper1,
		SolrServerWrapper solrServerWrapper2) {

		if (solrServerWrapper1.getInvocationCount() >
				solrServerWrapper2.getInvocationCount()) {

			return 1;
		}
		else if (solrServerWrapper1.getInvocationCount() <
					solrServerWrapper2.getInvocationCount()) {

			return -1;
		}

		return 0;
	}

}