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

package com.liferay.compat.portal.kernel.search;

import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 */
public class DocumentImpl
	extends com.liferay.portal.kernel.search.DocumentImpl {

	public static String getSortFieldName(Sort sort, String scoreFieldName) {
		if (sort.getType() == Sort.SCORE_TYPE) {
			return scoreFieldName;
		}

		String fieldName = sort.getFieldName();

		if (fieldName.endsWith(_SORTABLE_FIELD_SUFFIX)) {
			return fieldName;
		}

		String sortFieldName = null;

		if (DocumentImpl.isSortableTextField(fieldName) ||
			(sort.getType() != Sort.STRING_TYPE)) {

			sortFieldName = DocumentImpl.getSortableFieldName(fieldName);
		}

		if (Validator.isNull(sortFieldName)) {
			sortFieldName = scoreFieldName;
		}

		return sortFieldName;
	}

	private static final String _SORTABLE_FIELD_SUFFIX = "sortable";

}