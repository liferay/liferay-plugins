create index IX_B53EB0E1 on Calendar (groupId, calendarResourceId);
create index IX_97FC174E on Calendar (groupId, calendarResourceId, defaultCalendar);
create index IX_F0FAF226 on Calendar (resourceBlockId);
create index IX_96C8590 on Calendar (uuid_);
create index IX_97656498 on Calendar (uuid_, companyId);
create unique index IX_3AE311A on Calendar (uuid_, groupId);

create index IX_D300DFCE on CalendarBooking (calendarId);
create unique index IX_113A264E on CalendarBooking (calendarId, parentCalendarBookingId);
create index IX_470170B4 on CalendarBooking (calendarId, status);
create unique index IX_8B23DA0E on CalendarBooking (calendarId, vEventUid);
create index IX_B198FFC on CalendarBooking (calendarResourceId);
create index IX_57EBF55B on CalendarBooking (parentCalendarBookingId);
create index IX_F7B8A941 on CalendarBooking (parentCalendarBookingId, status);
create index IX_22DFDB49 on CalendarBooking (resourceBlockId);
create index IX_F6E8EE73 on CalendarBooking (uuid_);
create index IX_A21D9FD5 on CalendarBooking (uuid_, companyId);
create unique index IX_F4C61797 on CalendarBooking (uuid_, groupId);

create index IX_A412E5B6 on CalendarNotificationTemplate (calendarId);
create index IX_7727A482 on CalendarNotificationTemplate (calendarId, notificationType, notificationTemplateType);
create index IX_A2D4D78B on CalendarNotificationTemplate (uuid_);
create index IX_4D7D97BD on CalendarNotificationTemplate (uuid_, companyId);
create unique index IX_4012E97F on CalendarNotificationTemplate (uuid_, groupId);

create index IX_76DDD0F7 on CalendarResource (active_);
create unique index IX_16A12327 on CalendarResource (classNameId, classPK);
create index IX_4470A59D on CalendarResource (companyId, code_, active_);
create index IX_1243D698 on CalendarResource (groupId);
create index IX_40678371 on CalendarResource (groupId, active_);
create index IX_55C2F8AA on CalendarResource (groupId, code_);
create index IX_8BCB4D38 on CalendarResource (resourceBlockId);
create index IX_150E2F22 on CalendarResource (uuid_);
create index IX_56A06BC6 on CalendarResource (uuid_, companyId);
create unique index IX_4ABD2BC8 on CalendarResource (uuid_, groupId);