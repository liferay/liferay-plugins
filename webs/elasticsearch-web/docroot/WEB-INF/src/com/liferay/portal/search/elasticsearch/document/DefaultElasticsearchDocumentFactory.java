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

package com.liferay.portal.search.elasticsearch.document;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * @author Michael C. Han
 * @author Milen Dyankov
 */
public class DefaultElasticsearchDocumentFactory
	implements ElasticsearchDocumentFactory {

	@Override
	public String getElasticsearchDocument(Document document)
		throws IOException {

		XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();

		xContentBuilder.startObject();

		Map<String, Field> fields = document.getFields();

		for (Field field : fields.values()) {
			String name = field.getName();

			if (!field.isLocalized()) {
				for (String value : field.getValues()) {
					if (Validator.isNull(value)) {
						continue;
					}

					xContentBuilder.field(name, value.trim());
				}
			}
			else {
				Map<Locale, String> localizedValues =
					field.getLocalizedValues();

				for (Map.Entry<Locale, String> entry :
						localizedValues.entrySet()) {

					String value = entry.getValue();

					if (Validator.isNull(value)) {
						continue;
					}

					Locale locale = entry.getKey();

					String languageId = LocaleUtil.toLanguageId(locale);

					String defaultLanguageId = LocaleUtil.toLanguageId(
						LocaleUtil.getDefault());

					if (languageId.equals(defaultLanguageId)) {
						xContentBuilder.field(name, value.trim());
					}

					String localizedName = DocumentImpl.getLocalizedName(
						languageId, name);

					xContentBuilder.field(localizedName, value.trim());
				}
			}
		}

		xContentBuilder.endObject();

		return xContentBuilder.string();
	}

}