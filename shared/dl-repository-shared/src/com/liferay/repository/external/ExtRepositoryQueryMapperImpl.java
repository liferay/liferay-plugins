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

package com.liferay.repository.external;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.repository.external.search.ExtRepositoryQueryMapper;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryQueryMapperImpl implements ExtRepositoryQueryMapper {

	public ExtRepositoryQueryMapperImpl(
		ExtRepositoryAdapter extRepositoryAdapter) {

		_extRepositoryAdapter = extRepositoryAdapter;
	}

	@Override
	public Date formatDateParameterValue(String fieldName, String fieldValue)
		throws SearchException {

		if (fieldName.equals(Field.CREATE_DATE) ||
			fieldName.equals(Field.MODIFIED_DATE)) {

			try {
				DateFormat searchSimpleDateFormat =
					DateFormatFactoryUtil.getSimpleDateFormat(
						_INDEX_DATE_FORMAT_PATTERN);

				return searchSimpleDateFormat.parse(fieldValue);
			}
			catch (ParseException pe) {
				throw new SearchException(
					"Unable to parse date " + fieldValue + " for field " +
						fieldName);
			}
		}
		else {
			throw new SearchException("Field " + fieldName + " is not a date");
		}
	}

	@Override
	public String formatParameterValue(String fieldName, String fieldValue)
		throws SearchException {

		if (fieldName.equals(Field.CREATE_DATE) ||
			fieldName.equals(Field.MODIFIED_DATE)) {

			throw new SearchException(
				"Use the method formatDateParameterValue to format the date " +
					"field " + fieldName);
		}
		else if (fieldName.equals(Field.FOLDER_ID)) {
			try {
				long folderId = GetterUtil.getLong(fieldValue);

				return _extRepositoryAdapter.getExtRepositoryObjectKey(
					folderId);
			}
			catch (PortalException pe) {
				throw new SearchException(
					"Unable to get folder folder " + fieldValue, pe);
			}
			catch (SystemException se) {
				throw new SearchException(
					"Unable to get folder folder " + fieldValue, se);
			}
		}
		else if (fieldName.equals(Field.USER_ID)) {
			try {
				long userId = GetterUtil.getLong(fieldValue);

				User user = UserLocalServiceUtil.getUserById(userId);

				return user.getScreenName();
			}
			catch (Exception e) {
				throw new SearchException(
					"Unable to get user user " + fieldValue, e);
			}
		}
		else {
			return fieldValue;
		}
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	private ExtRepositoryAdapter _extRepositoryAdapter;

}