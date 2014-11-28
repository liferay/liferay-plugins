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

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.service.permission.CalendarPermission;
import com.liferay.calendar.service.persistence.CalendarActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Adam Brandizzi
 */
public class CalendarIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {Calendar.class.getName()};

	public static final String PORTLET_ID = PortletKeys.CALENDAR;

	public CalendarIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return CalendarPermission.contains(
			permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, true);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.NAME, true);
		addSearchLocalizedTerm(
			searchQuery, searchContext, "resourceName", true);
	}

	@Override
	protected void doDelete(Object object) throws Exception {
		Calendar calendar = (Calendar)object;

		deleteDocument(calendar.getCompanyId(), calendar.getCalendarId());
	}

	@Override
	protected Document doGetDocument(Object object) throws Exception {
		Calendar calendar = (Calendar)object;

		Document document = getBaseModelDocument(PORTLET_ID, calendar);

		document.addLocalizedText(
			Field.DESCRIPTION, calendar.getDescriptionMap());
		document.addLocalizedText(Field.NAME, calendar.getNameMap());
		document.addKeyword("calendarId", calendar.getCalendarId());

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		document.addText("defaultLanguageId", defaultLanguageId);

		CalendarResource calendarResource = calendar.getCalendarResource();

		document.addLocalizedText(
			"resourceName", calendarResource.getNameMap());

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String calendarId = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter("mvcPath", "/edit_calendar.jsp");
		portletURL.setParameter("calendarId", calendarId);

		Summary summary = createSummary(
			document, Field.NAME, Field.DESCRIPTION);

		summary.setMaxContentLength(200);
		summary.setPortletURL(portletURL);

		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		Calendar calendar = (Calendar)obj;

		Document document = getDocument(calendar);

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), calendar.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Calendar calendar = CalendarLocalServiceUtil.getCalendar(classPK);

		doReindex(calendar);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCalendars(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindexCalendars(long companyId)
		throws PortalException, SystemException {

		ActionableDynamicQuery actionableDynamicQuery =
			new CalendarActionableDynamicQuery() {

			@Override
			public void performAction(Object object) throws PortalException {
				Calendar calendar = (Calendar)object;

				Document document = getDocument(calendar);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}