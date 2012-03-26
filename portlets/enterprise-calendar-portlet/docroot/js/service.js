Liferay.Service.register("Liferay.Service.Calendar", "com.liferay.calendar.service", "enterprise-calendar-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "Calendar",
	{
		addCalendar: true,
		deleteCalendar: true,
		getCalendar: true,
		updateCalendar: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarBooking",
	{
		addCalendarBooking: true,
		deleteCalendarBooking: true,
		getCalendarBooking: true,
		getCalendarBookings: true,
		updateCalendarBooking: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarResource",
	{
		addCalendarResource: true,
		deleteCalendarResource: true,
		getCalendarResource: true,
		search: true,
		searchCount: true,
		updateCalendarResource: true
	}
);