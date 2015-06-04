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

package com.liferay.portal.search.solr.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import org.apache.solr.client.solrj.response.SolrResponseBase;

/**
 * @author Michael C. Han
 */
public class LogUtil {

	public static void logSolrResponseBase(
			Log log, SolrResponseBase solrResponseBase)
		throws IOException {

		if (log.isInfoEnabled()) {
			return;
		}

		StringBundler sb = new StringBundler(7);

		sb.append("{elapsedTime=");
		sb.append(solrResponseBase.getElapsedTime());
		sb.append(", requestURL=");
		sb.append(solrResponseBase.getRequestUrl());
		sb.append(", response=");
		sb.append(solrResponseBase);
		sb.append("}");

		log.info(sb);
	}

}