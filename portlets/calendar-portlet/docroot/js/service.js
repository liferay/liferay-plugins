Liferay.Service.register("Liferay.Service.Calendar", "com.liferay.calendar.service", "calendar-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarBooking",
	{
		addCalendarBooking: true,
		deleteCalendarBooking: true,
		getCalendarBooking: true,
		getCalendarBookings: true,
		getCalendarBookingsCount: true,
		getCalendarEventCalendarBookings: true,
		getCalendarEventCalendarBookingsCount: true,
		getCalendarResourceCalendarBookings: true,
		getCalendarResourceCalendarBookingsCount: true,
		search: true,
		searchCount: true,
		updateCalendarBooking: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarEvent",
	{
		addCaledarEvent: true,
		deleteCalendarEvent: true,
		getCalendarEvent: true,
		updateCalendarEvent: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Calendar, "CalendarResource",
	{
		addCalendarResource: true,
		deleteCalendarResource: true,
		getCalendarResource: true,
		getGroupCalendarResources: true,
		getGroupCalendarResourcesCount: true,
		updateCalendarResource: true
	}
);