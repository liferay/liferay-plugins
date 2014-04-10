create index IX_4C3FA27E on SampleLARBooking (groupId);
create index IX_3337BC88 on SampleLARBooking (uuid_);
create index IX_DDB4A2A0 on SampleLARBooking (uuid_, companyId);
create unique index IX_D3455122 on SampleLARBooking (uuid_, groupId);