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

package com.liferay.knowledgebase.admin.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Calendar;

import javax.portlet.PortletRequest;

/**
 * @author Peter Shin
 */
public class KBArticleDisplayTerms extends DisplayTerms {

	public static final String ANYTIME = "anytime";

	public static final String CONTENT = "content";

	public static final String END_DATE_DAY = "endDateDay";

	public static final String END_DATE_MONTH = "endDateMonth";

	public static final String END_DATE_YEAR = "endDateYear";

	public static final String START_DATE_DAY = "startDateDay";

	public static final String START_DATE_MONTH = "startDateMonth";

	public static final String START_DATE_YEAR = "startDateYear";

	public static final String STATUS = "status";

	public static final String TITLE = "title";

	public KBArticleDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Calendar today = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		calendar.add(Calendar.MONTH, -1);

		anytime = ParamUtil.getBoolean(portletRequest, ANYTIME, true);
		content = ParamUtil.getString(portletRequest, CONTENT);
		endDateDay = ParamUtil.getInteger(
			portletRequest, END_DATE_DAY, today.get(Calendar.DATE));
		endDateMonth = ParamUtil.getInteger(
			portletRequest, END_DATE_MONTH, today.get(Calendar.MONTH));
		endDateYear = ParamUtil.getInteger(
			portletRequest, END_DATE_YEAR, today.get(Calendar.YEAR));
		startDateDay = ParamUtil.getInteger(
			portletRequest, START_DATE_DAY, calendar.get(Calendar.DATE));
		startDateMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_MONTH, calendar.get(Calendar.MONTH));
		startDateYear = ParamUtil.getInteger(
			portletRequest, START_DATE_YEAR, calendar.get(Calendar.YEAR));
		status = ParamUtil.getInteger(
			portletRequest, STATUS, WorkflowConstants.STATUS_ANY);
		title = ParamUtil.getString(portletRequest, TITLE);

		firstDayOfWeek = today.getFirstDayOfWeek() - 1;
		yearRangeEnd = today.get(Calendar.YEAR);
		yearRangeStart = today.get(Calendar.YEAR) - 100;
	}

	public String getContent() {
		return content;
	}

	public int getEndDateDay() {
		return endDateDay;
	}

	public int getEndDateMonth() {
		return endDateMonth;
	}

	public int getEndDateYear() {
		return endDateYear;
	}

	public int getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	public int getStartDateDay() {
		return startDateDay;
	}

	public int getStartDateMonth() {
		return startDateMonth;
	}

	public int getStartDateYear() {
		return startDateYear;
	}

	public int getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public int getYearRangeEnd() {
		return yearRangeEnd;
	}

	public int getYearRangeStart() {
		return yearRangeStart;
	}

	public boolean isAnytime() {
		return anytime;
	}

	protected boolean anytime;
	protected String content;
	protected int endDateDay;
	protected int endDateMonth;
	protected int endDateYear;
	protected int firstDayOfWeek;
	protected int startDateDay;
	protected int startDateMonth;
	protected int startDateYear;
	protected int status;
	protected String title;
	protected int yearRangeEnd;
	protected int yearRangeStart;

}