<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/init.jsp" %>

<%
Facet facet = (Facet)request.getAttribute("search-search.jsp-facet");

String fieldName = facet.getFieldName();
String fieldParam = ParamUtil.getString(request, fieldName);

JSONObject data = facet.getFacetConfiguration().getData();

String[] range = null;

String dateString = StringPool.BLANK;
Calendar cal = Calendar.getInstance();

if (Validator.isNotNull(fieldParam)) {
	range = RangeParserUtil.parserRange(fieldParam);

	Date date = indexDateFormatDate.parse(range[0]);

	cal.setTime(date);

	dateString = "new Date(" + cal.get(Calendar.YEAR) + "," + cal.get(Calendar.MONTH) + "," + (cal.get(Calendar.DAY_OF_MONTH) + 1) + ")";

	if (range[1].equals(StringPool.STAR)) {
		date = new Date();
	}
	else {
		date = indexDateFormatDate.parse(range[1]);
	}

	cal.setTime(date);

	dateString = dateString + ",new Date(" + cal.get(Calendar.YEAR) + "," + cal.get(Calendar.MONTH) + "," + (cal.get(Calendar.DAY_OF_MONTH) + 1) + ")";
}
%>

<aui:input name='<%= fieldName %>' type="hidden" value="<%= fieldParam %>" />

<div class="date" id="<portlet:namespace /><%= fieldName %>PlaceHolder"></div>

<aui:script position="inline" use="aui-calendar">
	var now = new Date();

	var checkDateRange = function(event) {
		var dates = this.get('dates');
		var minDate = null;
		var maxDate = null;

		if (dates.length >= 2) {
			var firstSelected = dates[0];
			var lastSelected = dates[dates.length-1];

			if (A.DataType.DateMath.before(dates[0], dates[1])) {
				minDate = firstSelected;
				maxDate = lastSelected;
			}
			else {
				minDate = lastSelected;
				maxDate = firstSelected;
			}
		}

		this.set('minDate', minDate);
		this.set('maxDate', maxDate);
		this._syncMonthDays();
	};

	var dateSelection = new A.Calendar({
		dates: [<%= dateString %>],
		dateFormat: '%Y%m%d000000',
		setValue: true,
		allowNone: true,
		showToday: true,
		firstDayOfWeek: 0,
		minDate: A.DataType.DateMath.subtract(now, A.DataType.DateMath.YEAR, 2),
		maxDate: now,
		selectMultipleDates: true,
		after: {
			select: function(event) {
				var instance = this;
				var format = instance.get('dateFormat');

				var dates = instance.get('dates');

				if (dates.length == 0) {
					document.<portlet:namespace />fm.<portlet:namespace /><%= fieldName %>.value = null;
				}
				else if (dates.length == 1) {
					var firstSelected = dates[0];

					document.<portlet:namespace />fm.<portlet:namespace /><%= fieldName %>.value = '[' + A.DataType.Date.format(firstSelected, {format: format}) + ' TO ' + A.DataType.Date.format(now, {format: format}) + ']';
				}
				else if (dates.length > 1) {
					var firstSelected = dates[0];
					var lastSelected = dates[dates.length-1];

					document.<portlet:namespace />fm.<portlet:namespace /><%= fieldName %>.value = '[' + A.DataType.Date.format(firstSelected, {format: format}) + ' TO ' + A.DataType.Date.format(lastSelected, {format: format}) + ']';
				}

				// Checking range
				checkDateRange.call(instance, event);
			}
		}
	})
	.render('#<portlet:namespace /><%= fieldName %>PlaceHolder');
</aui:script>