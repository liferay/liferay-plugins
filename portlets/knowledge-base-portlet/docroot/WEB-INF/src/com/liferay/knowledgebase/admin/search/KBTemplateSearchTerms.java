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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;
import java.util.TimeZone;

import javax.portlet.PortletRequest;

/**
 * @author Peter Shin
 */
public class KBTemplateSearchTerms extends KBTemplateDisplayTerms {

	public static final String CUR_START_VALUES = "curStartValues";

	public KBTemplateSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		curStartValues = StringUtil.split(
			ParamUtil.getString(portletRequest, CUR_START_VALUES), 0);
	}

	public int[] getCurStartValues() {
		return curStartValues;
	}

	public Date getEndDate(TimeZone timeZone) throws PortalException {
		if (anytime) {
			return null;
		}

		return PortalUtil.getDate(
			endDateMonth, endDateDay + 1, endDateYear, timeZone, null);
	}

	public Date getStartDate(TimeZone timeZone) throws PortalException {
		if (anytime) {
			return null;
		}

		return PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, timeZone, null);
	}

	public boolean hasSearchTerms() {
		if (advancedSearch && hasAdvancedSearchTerms()) {
			return true;
		}

		if (!advancedSearch && hasBasicSearchTerms()) {
			return true;
		}

		return false;
	}

	public void setCurStartValues(int[] curStartValues) {
		this.curStartValues = curStartValues;
	}

	protected boolean hasAdvancedSearchTerms() {
		if (!anytime) {
			return true;
		}

		if (Validator.isNotNull(content)) {
			return true;
		}

		if (Validator.isNotNull(title)) {
			return true;
		}

		return false;
	}

	protected boolean hasBasicSearchTerms() {
		if (Validator.isNotNull(keywords)) {
			return true;
		}

		return false;
	}

	protected int[] curStartValues;

}