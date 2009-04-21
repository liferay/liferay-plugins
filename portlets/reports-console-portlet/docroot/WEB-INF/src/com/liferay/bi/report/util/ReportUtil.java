
package com.liferay.bi.report.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.portlet.ActionRequest;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.MemoryReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.RecurrenceSerializer;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

public class ReportUtil {

	public static final String REPORT_REQUEST = "liferay/report_request";

	public static ReportRequest getReportRequest(ReportDefinition definition)
		throws PortalException, SystemException {

		String[] existingFiles = definition.getAttachmentsFiles();

		byte[] definitionFile =
			DLServiceUtil.getFile(
				definition.getCompanyId(), CompanyConstants.SYSTEM,
				existingFiles[0]);

		ReportDesignRetriever retriever =
			new MemoryReportDesignRetriever(
				definition.getReportName() + StringPool.PERIOD +
					definition.getReportFormat(), definitionFile);

		return new ReportRequest(
			retriever, definition.getReportFormat(), null, null);

	}

	public static String getSchedulerGroupName(long groupId) {

		StringBuilder sb = new StringBuilder();

		sb.append(REPORT_REQUEST);
		sb.append(StringPool.SLASH);
		sb.append(groupId);

		return sb.toString();
	}

	private static void _addWeeklyDayPos(
		ActionRequest actionRequest, List<DayAndPosition> list, int day) {

		if (ParamUtil.getBoolean(actionRequest, "weeklyDayPos" + day)) {
			list.add(new DayAndPosition(day, 0));
		}
	}

	public static String getCronText(
		ActionRequest actionRequest, Calendar startDate,
		boolean timeZoneSensitive, int recurrenceType)
		throws PortalException {

		Calendar startCal = null;

		if (timeZoneSensitive) {
			startCal = CalendarFactoryUtil.getCalendar();

			startCal.setTime(startDate.getTime());
		}
		else {
			startCal = (Calendar) startDate.clone();
		}

		Recurrence recurrence =
			new Recurrence(startCal, new Duration(1, 0, 0, 0), recurrenceType);

		recurrence.setWeekStart(Calendar.SUNDAY);

		if (recurrenceType == Recurrence.DAILY) {
			int dailyType = ParamUtil.getInteger(actionRequest, "dailyType");

			if (dailyType == 0) {
				int dailyInterval =
					ParamUtil.getInteger(actionRequest, "dailyInterval", 1);

				recurrence.setInterval(dailyInterval);
			}
			else {
				DayAndPosition[] dayPos =
					{
						new DayAndPosition(Calendar.MONDAY, 0),
						new DayAndPosition(Calendar.TUESDAY, 0),
						new DayAndPosition(Calendar.WEDNESDAY, 0),
						new DayAndPosition(Calendar.THURSDAY, 0),
						new DayAndPosition(Calendar.FRIDAY, 0)
					};

				recurrence.setByDay(dayPos);
			}
		}
		else if (recurrenceType == Recurrence.WEEKLY) {
			int weeklyInterval =
				ParamUtil.getInteger(actionRequest, "weeklyInterval", 1);

			recurrence.setInterval(weeklyInterval);

			List<DayAndPosition> dayPos = new ArrayList<DayAndPosition>();

			_addWeeklyDayPos(actionRequest, dayPos, Calendar.SUNDAY);
			_addWeeklyDayPos(actionRequest, dayPos, Calendar.MONDAY);
			_addWeeklyDayPos(actionRequest, dayPos, Calendar.TUESDAY);
			_addWeeklyDayPos(actionRequest, dayPos, Calendar.WEDNESDAY);
			_addWeeklyDayPos(actionRequest, dayPos, Calendar.THURSDAY);
			_addWeeklyDayPos(actionRequest, dayPos, Calendar.FRIDAY);
			_addWeeklyDayPos(actionRequest, dayPos, Calendar.SATURDAY);

			if (dayPos.size() == 0) {
				dayPos.add(new DayAndPosition(Calendar.MONDAY, 0));
			}

			recurrence.setByDay(dayPos.toArray(new DayAndPosition[0]));
		}
		else if (recurrenceType == Recurrence.MONTHLY) {
			int monthlyType =
				ParamUtil.getInteger(actionRequest, "monthlyType");

			if (monthlyType == 0) {
				int monthlyDay =
					ParamUtil.getInteger(actionRequest, "monthlyDay0", 1);

				recurrence.setByMonthDay(new int[] {
					monthlyDay
				});

				int monthlyInterval =
					ParamUtil.getInteger(actionRequest, "monthlyInterval0", 1);

				recurrence.setInterval(monthlyInterval);
			}
			else {
				int monthlyPos =
					ParamUtil.getInteger(actionRequest, "monthlyPos");
				int monthlyDay =
					ParamUtil.getInteger(actionRequest, "monthlyDay1");

				DayAndPosition[] dayPos = {
					new DayAndPosition(monthlyDay, monthlyPos)
				};

				recurrence.setByDay(dayPos);

				int monthlyInterval =
					ParamUtil.getInteger(actionRequest, "monthlyInterval1", 1);

				recurrence.setInterval(monthlyInterval);
			}
		}
		else if (recurrenceType == Recurrence.YEARLY) {
			int yearlyType = ParamUtil.getInteger(actionRequest, "yearlyType");

			if (yearlyType == 0) {
				int yearlyMonth =
					ParamUtil.getInteger(actionRequest, "yearlyMonth0");
				int yearlyDay =
					ParamUtil.getInteger(actionRequest, "yearlyDay0", 1);

				recurrence.setByMonth(new int[] {
					yearlyMonth
				});
				recurrence.setByMonthDay(new int[] {
					yearlyDay
				});

				int yearlyInterval =
					ParamUtil.getInteger(actionRequest, "yearlyInterval0", 1);

				recurrence.setInterval(yearlyInterval);
			}
			else {
				int yearlyPos =
					ParamUtil.getInteger(actionRequest, "yearlyPos");
				int yearlyDay =
					ParamUtil.getInteger(actionRequest, "yearlyDay1");
				int yearlyMonth =
					ParamUtil.getInteger(actionRequest, "yearlyMonth1");

				DayAndPosition[] dayPos = {
					new DayAndPosition(yearlyDay, yearlyPos)
				};

				recurrence.setByDay(dayPos);

				recurrence.setByMonth(new int[] {
					yearlyMonth
				});

				int yearlyInterval =
					ParamUtil.getInteger(actionRequest, "yearlyInterval1", 1);

				recurrence.setInterval(yearlyInterval);
			}
		}

		return RecurrenceSerializer.toCronText(recurrence);
	}

	public static Calendar getDate(
		ActionRequest actionRequest, String paramPrefix,
		boolean timeZoneSensitive)
		throws PortalException {

		int dateMonth =
			ParamUtil.getInteger(actionRequest, paramPrefix + "Month");
		int dateDay = ParamUtil.getInteger(actionRequest, paramPrefix + "Day");
		int dateYear =
			ParamUtil.getInteger(actionRequest, paramPrefix + "Year");
		int dateHour =
			ParamUtil.getInteger(actionRequest, paramPrefix + "Hour");
		int dateMinute =
			ParamUtil.getInteger(actionRequest, paramPrefix + "Minute");
		int dateAmPm =
			ParamUtil.getInteger(actionRequest, paramPrefix + "AmPm");

		if (dateAmPm == Calendar.PM) {
			dateHour += 12;
		}

		Locale locale = null;
		TimeZone timeZone = null;

		if (timeZoneSensitive) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			locale = themeDisplay.getLocale();
			timeZone = themeDisplay.getTimeZone();
		}
		else {
			locale = LocaleUtil.getDefault();
			timeZone = TimeZoneUtil.getDefault();
		}

		Calendar cal = CalendarFactoryUtil.getCalendar(timeZone, locale);

		cal.set(Calendar.MONTH, dateMonth);
		cal.set(Calendar.DATE, dateDay);
		cal.set(Calendar.YEAR, dateYear);
		cal.set(Calendar.HOUR_OF_DAY, dateHour);
		cal.set(Calendar.MINUTE, dateMinute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}
}
