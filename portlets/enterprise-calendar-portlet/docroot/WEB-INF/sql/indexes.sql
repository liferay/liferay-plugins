create index IX_B53EB0E1 on Calendar (groupId, calendarResourceId);
create index IX_97FC174E on Calendar (groupId, calendarResourceId, defaultCalendar);
create index IX_F0FAF226 on Calendar (resourceBlockId);
create index IX_96C8590 on Calendar (uuid_);
create unique index IX_3AE311A on Calendar (uuid_, groupId);

create index IX_D300DFCE on CalendarBooking (calendarId);
create index IX_C4D67607 on CalendarBooking (calendarId, startDate, endDate);
create index IX_B198FFC on CalendarBooking (calendarResourceId);
create index IX_97B612E2 on CalendarBooking (calendarResourceId, status);
create index IX_F6E8EE73 on CalendarBooking (uuid_);
create unique index IX_F4C61797 on CalendarBooking (uuid_, groupId);

create index IX_76DDD0F7 on CalendarResource (active_);
create unique index IX_16A12327 on CalendarResource (classNameId, classPK);
create index IX_4470A59D on CalendarResource (companyId, code_, active_);
create index IX_2C5184D4 on CalendarResource (companyId, name, active_);
create index IX_40678371 on CalendarResource (groupId, active_);
create index IX_69F8959F on CalendarResource (groupId, code_, active_);
create index IX_B9EA8C92 on CalendarResource (groupId, name, active_);
create index IX_8BCB4D38 on CalendarResource (resourceBlockId);
create index IX_150E2F22 on CalendarResource (uuid_);
create unique index IX_4ABD2BC8 on CalendarResource (uuid_, groupId);