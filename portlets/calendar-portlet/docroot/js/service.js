Liferay.Service.register("Liferay.Service.Calendar", "com.liferay.calendar.service", "calendar-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "Calendar",
	{
		addCalendar: true,
		deleteCalendar: true,
		fetchCalendar: true,
		getCalendar: true,
		search: true,
		searchCount: true,
		updateCalendar: true,
		updateColor: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarBooking",
	{
		addCalendarBooking: true,
		deleteCalendarBooking: true,
		fetchCalendarBooking: true,
		getCalendarBooking: true,
		getCalendarBookings: true,
		getChildCalendarBookings: true,
		invokeTransition: true,
		search: true,
		searchCount: true,
		updateCalendarBooking: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarResource",
	{
		addCalendarResource: true,
		deleteCalendarResource: true,
		fetchCalendarResource: true,
		getCalendarResource: true,
		search: true,
		searchCount: true,
		updateCalendarResource: true
	}
);