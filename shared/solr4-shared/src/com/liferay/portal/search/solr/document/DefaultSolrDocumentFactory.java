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

package com.liferay.portal.search.solr.document;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Map;

import org.apache.solr.common.SolrInputDocument;

/**
 * @author Michael C. Han
 */
public class DefaultSolrDocumentFactory implements SolrDocumentFactory {

	@Override
	public SolrInputDocument getSolrInputDocument(Document document) {
		SolrInputDocument solrInputDocument = new SolrInputDocument();

		Map<String, Field> fields = document.getFields();

		for (Field field : fields.values()) {
			String name = field.getName();
			float boost = field.getBoost();

			if (ArrayUtil.contains(Field.UNSCORED_FIELD_NAMES, name)) {
				boost = _UNSCORED_FIELDS_BOOST;
			}

			if (!field.isLocalized()) {
				for (String value : field.getValues()) {
					if (Validator.isNull(value)) {
						continue;
					}

					value = value.trim();

					addField(solrInputDocument, field, boost, value, name);
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

					value = value.trim();

					Locale locale = entry.getKey();

					String languageId = LocaleUtil.toLanguageId(locale);

					String defaultLanguageId = LocaleUtil.toLanguageId(
						LocaleUtil.getDefault());

					if (languageId.equals(defaultLanguageId)) {
						solrInputDocument.addField(name, value, boost);
					}

					String localizedName = DocumentImpl.getLocalizedName(
						locale, name);

					addField(
						solrInputDocument, field, boost, value, localizedName);
				}
			}
		}

		return solrInputDocument;
	}

	protected void addField(
		SolrInputDocument solrInputDocument, Field field, float boost,
		String value, String localizedName) {

		solrInputDocument.addField(localizedName, value, boost);

		if (field.isSortable()) {
			String sortableFieldName = DocumentImpl.getSortableFieldName(
				localizedName);

			solrInputDocument.addField(sortableFieldName, value, boost);
		}
	}

	private static final float _UNSCORED_FIELDS_BOOST = 1;

}