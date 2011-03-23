create index IX_F6E8EE73 on CalendarBooking (uuid_);
create unique index IX_F4C61797 on CalendarBooking (uuid_, groupId);

create index IX_90DA44D2 on CalendarEvent (uuid_);
create unique index IX_4D367C18 on CalendarEvent (uuid_, groupId);

create index IX_150E2F22 on CalendarResource (uuid_);
create unique index IX_4ABD2BC8 on CalendarResource (uuid_, groupId);