/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.search.elasticsearch.index;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Company;
import com.liferay.portal.search.elasticsearch.io.StringOutputStream;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.io.stream.OutputStreamStreamOutput;

/**
 * @author Michael C. Han
 */
public class PerCompanyIndexFactory implements IndexFactory {

	public void createIndices(AdminClient adminClient) throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		IndicesAdminClient indicesAdminClient = adminClient.indices();

		for (Company company : companies) {
			IndicesExistsRequestBuilder indicesExistsRequestBuilder =
				indicesAdminClient.prepareExists(
					String.valueOf(company.getCompanyId()));

			ListenableActionFuture<IndicesExistsResponse>
				indicesExistsRequestFuture =
					indicesExistsRequestBuilder.execute();

			IndicesExistsResponse indicesExistsResponse =
				indicesExistsRequestFuture.get();

			if (indicesExistsResponse.isExists()) {
				continue;
			}

			CreateIndexRequestBuilder createIndexRequestBuilder =
				indicesAdminClient.prepareCreate(
					String.valueOf(company.getCompanyId()));

			for (Map.Entry<String, String> typeMappingEntry :
					_typeMappings.entrySet()) {

				String typeMapping = retrieveTypeMapping(
					typeMappingEntry.getValue());

				createIndexRequestBuilder.addMapping(
					typeMappingEntry.getKey(), typeMapping);
			}

			ListenableActionFuture<CreateIndexResponse> createIndexFuture =
				createIndexRequestBuilder.execute();

			CreateIndexResponse createIndexResponse = createIndexFuture.get();

			if (_log.isInfoEnabled()) {
				StringOutputStream stringOutputStream =
					new StringOutputStream();

				createIndexResponse.writeTo(
					new OutputStreamStreamOutput(stringOutputStream));

				_log.info(stringOutputStream);
			}
		}
	}

	public void setTypeMappings(Map<String, String> typeMappings) {
		_typeMappings = typeMappings;
	}

	protected String retrieveTypeMapping(String typeMappingPath)
		throws IOException {

		ClassLoader contextClassLoader =
			Thread.currentThread().getContextClassLoader();

		StringBundler stringBundler = new StringBundler();

		InputStream inputStream = null;

		try {
			inputStream = contextClassLoader.getResourceAsStream(
				typeMappingPath);

			BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				stringBundler.append(line);
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}

		return stringBundler.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(
		PerCompanyIndexFactory.class);

	private Map<String, String> _typeMappings = new HashMap<String, String>();

}