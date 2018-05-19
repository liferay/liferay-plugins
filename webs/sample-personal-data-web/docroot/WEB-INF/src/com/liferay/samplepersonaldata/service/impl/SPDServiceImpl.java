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

package com.liferay.samplepersonaldata.service.impl;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.service.persistence.BlogsEntryActionableDynamicQuery;
import com.liferay.samplepersonaldata.service.base.SPDServiceBaseImpl;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dennis Ju
 */
public class SPDServiceImpl extends SPDServiceBaseImpl {

	@Override
	public void anonymizeBlogsEntries(
			final long userId, final long anonymousUserId)
		throws Exception {

		User anonymousUser = UserLocalServiceUtil.getUser(anonymousUserId);

		final String anonymousUserName = anonymousUser.getFullName();

		ActionableDynamicQuery actionableDynamicQuery =
			new BlogsEntryActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Criterion criterion = RestrictionsFactoryUtil.or(
					RestrictionsFactoryUtil.eq("statusByUserId", userId),
					RestrictionsFactoryUtil.eq("userId", userId));

				dynamicQuery.add(criterion);
			}

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				BlogsEntry blogsEntry = (BlogsEntry)object;

				if (blogsEntry.getStatusByUserId() == userId) {
					blogsEntry.setStatusByUserId(anonymousUserId);
					blogsEntry.setStatusByUserName(anonymousUserName);
				}

				if (blogsEntry.getUserId() == userId) {
					blogsEntry.setUserId(anonymousUserId);
					blogsEntry.setUserName(anonymousUserName);
				}

				BlogsEntryLocalServiceUtil.updateBlogsEntry(blogsEntry);
			}

		};

		actionableDynamicQuery.performActions();
	}

	@Override
	public void deleteBlogsEntries(final long userId) throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new BlogsEntryActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Criterion criterion = RestrictionsFactoryUtil.or(
					RestrictionsFactoryUtil.eq("statusByUserId", userId),
					RestrictionsFactoryUtil.eq("userId", userId));

				dynamicQuery.add(criterion);
			}

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				BlogsEntryLocalServiceUtil.deleteEntry((BlogsEntry)object);
			}

		};

		actionableDynamicQuery.performActions();
	}

	@Override
	public String exportBlogsEntries(final long userId) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		final String filterId = "blogsEntriesFilter";

		JacksonAnnotationIntrospector jacksonAnnotationIntrospector =
			new JacksonAnnotationIntrospector() {

			@Override
			public Object findFilterId(Annotated annotated) {
				return filterId;
			}

		};

		objectMapper.setAnnotationIntrospector(jacksonAnnotationIntrospector);

		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();

		Set<String> exportFields = new HashSet<String>(
			Arrays.asList(
				"entryId", "content", "description", "subtitle", "title"));

		simpleFilterProvider.addFilter(
			filterId,
			new SimpleBeanPropertyFilter.FilterExceptFilter(exportFields));

		final ObjectWriter objectWriter = objectMapper.writer(
			simpleFilterProvider);

		JsonFactory jsonFactory = new JsonFactory();

		Path path = Files.createTempFile("blogsEntries", ".json");

		final JsonGenerator jsonGenerator = jsonFactory.createGenerator(
			path.toFile(), JsonEncoding.UTF8);

		jsonGenerator.writeStartObject();

		jsonGenerator.writeFieldName(BlogsEntry.class.getName());

		jsonGenerator.writeStartArray();

		ActionableDynamicQuery actionableDynamicQuery =
			new BlogsEntryActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Criterion criterion = RestrictionsFactoryUtil.or(
					RestrictionsFactoryUtil.eq("statusByUserId", userId),
					RestrictionsFactoryUtil.eq("userId", userId));

				dynamicQuery.add(criterion);
			}

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				try {
					objectWriter.writeValue(jsonGenerator, object);
				}
				catch (IOException ioe) {
					_log.error(ioe.getMessage(), ioe);
				}
			}

		};

		actionableDynamicQuery.performActions();

		jsonGenerator.writeEndArray();

		jsonGenerator.writeEndObject();

		jsonGenerator.close();

		return path.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(SPDServiceImpl.class);

}