create index IX_B57E99A6 on CalendarBooking (calendarEventId);
create index IX_B198FFC on CalendarBooking (calendarResourceId);
create index IX_3937B7B6 on CalendarBooking (classNameId, classPK);
create index IX_F6E8EE73 on CalendarBooking (uuid_);
create unique index IX_F4C61797 on CalendarBooking (uuid_, groupId);

create index IX_90DA44D2 on CalendarEvent (uuid_);
create unique index IX_4D367C18 on CalendarEvent (uuid_, groupId);

create index IX_76DDD0F7 on CalendarResource (active_);
create unique index IX_16A12327 on CalendarResource (classNameId, classPK);
create index IX_2C5184D4 on CalendarResource (companyId, name, active_);
create index IX_40678371 on CalendarResource (groupId, active_);
create index IX_B9EA8C92 on CalendarResource (groupId, name, active_);
create index IX_150E2F22 on CalendarResource (uuid_);
create unique index IX_4ABD2BC8 on CalendarResource (uuid_, groupId);