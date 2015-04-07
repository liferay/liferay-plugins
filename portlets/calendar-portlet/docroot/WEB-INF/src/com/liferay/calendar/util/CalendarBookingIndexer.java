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

package com.liferay.calendar.util;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.persistence.CalendarBookingActionableDynamicQuery;
import com.liferay.calendar.workflow.CalendarBookingWorkflowConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.trash.util.TrashUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Adam Victor Brandizzi
 * @author Eduardo Lundgren
 */
public class CalendarBookingIndexer extends BaseIndexer {

	public static final String CLASS_NAME = CalendarBooking.class.getName();

	public CalendarBookingIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.UID);
		setDefaultSelectedLocalizedFieldNames(Field.DESCRIPTION, Field.TITLE);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		CalendarBooking calendarBooking = (CalendarBooking)obj;

		deleteDocument(
			calendarBooking.getCompanyId(),
			calendarBooking.getCalendarBookingId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		CalendarBooking calendarBooking = (CalendarBooking)obj;

		Document document = getBaseModelDocument(CLASS_NAME, calendarBooking);

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		String descriptionDefaultLanguageId =
			LocalizationUtil.getDefaultLanguageId(
				calendarBooking.getDescription());

		String[] descriptionLanguageIds = getLanguageIds(
			defaultLanguageId, calendarBooking.getDescription());

		for (String descriptionLanguageId : descriptionLanguageIds) {
			String description = calendarBooking.getDescription(
				descriptionLanguageId);

			if (descriptionLanguageId.equals(descriptionDefaultLanguageId)) {
				document.addText(Field.DESCRIPTION, description);
			}

			document.addText(
				Field.DESCRIPTION.concat(StringPool.UNDERLINE).concat(
					descriptionLanguageId),
				description);
		}

		String titleDefaultLanguageId = LocalizationUtil.getDefaultLanguageId(
			calendarBooking.getTitle());

		String[] titleLanguageIds = getLanguageIds(
			defaultLanguageId, calendarBooking.getTitle());

		for (String titleLanguageId : titleLanguageIds) {
			String title = calendarBooking.getTitle(titleLanguageId);

			if (titleLanguageId.equals(titleDefaultLanguageId)) {
				document.addText(Field.TITLE, title);
			}

			document.addText(
				Field.TITLE.concat(StringPool.UNDERLINE).concat(
					titleLanguageId),
				title);
		}

		String calendarBookingId = String.valueOf(
			calendarBooking.getCalendarBookingId());

		if (calendarBooking.isInTrash()) {
			calendarBookingId = TrashUtil.getOriginalTitle(calendarBookingId);
		}

		document.addKeyword("calendarBookingId", calendarBookingId);

		document.addText("defaultLanguageId", defaultLanguageId);
		document.addNumber("endTime", calendarBooking.getEndTime());
		document.addText("location", calendarBooking.getLocation());
		document.addNumber("startTime", calendarBooking.getStartTime());

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		String calendarBookingId = document.get(Field.ENTRY_CLASS_PK);

		Summary summary = createSummary(
			document, Field.TITLE, Field.DESCRIPTION);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		CalendarBooking calendarBooking = (CalendarBooking)obj;

		int status = calendarBooking.getStatus();

		if ((status == CalendarBookingWorkflowConstants.STATUS_APPROVED) ||
			(status == CalendarBookingWorkflowConstants.STATUS_MAYBE)) {

			Document document = getDocument(calendarBooking);

			SearchEngineUtil.updateDocument(
				getSearchEngineId(), calendarBooking.getCompanyId(), document,
				isCommitImmediately());
		}
		else if ((status == CalendarBookingWorkflowConstants.STATUS_DENIED) ||
				 (status == CalendarBookingWorkflowConstants.STATUS_IN_TRASH)) {

			doDelete(calendarBooking);
		}
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CalendarBooking calendarBooking =
			CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);

		doReindex(calendarBooking);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCalendarBookings(companyId);
	}

	protected String[] getLanguageIds(
		String defaultLanguageId, String content) {

		String[] languageIds = LocalizationUtil.getAvailableLanguageIds(
			content);

		if (languageIds.length == 0) {
			languageIds = new String[] {defaultLanguageId};
		}

		return languageIds;
	}

	protected void reindexCalendarBookings(long companyId)
		throws PortalException {

		final Collection<Document> documents = new ArrayList<>();

		ActionableDynamicQuery actionableDynamicQuery =
			new CalendarBookingActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				Property statusProperty = PropertyFactoryUtil.forName("status");

				int[] statuses = {
					CalendarBookingWorkflowConstants.STATUS_APPROVED,
					CalendarBookingWorkflowConstants.STATUS_MAYBE
				};

				dynamicQuery.add(statusProperty.in(statuses));
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				CalendarBooking calendarBooking = (CalendarBooking)object;

				Document document = getDocument(calendarBooking);

				documents.add(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);

		actionableDynamicQuery.performActions();

		SearchEngineUtil.updateDocuments(
			getSearchEngineId(), companyId, documents, isCommitImmediately());
	}

}