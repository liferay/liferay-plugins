create index IX_D41A5517 on BBBMeeting (bbbServerId, status);
create index IX_4E58622B on BBBMeeting (groupId);
create index IX_85871D45 on BBBMeeting (status);

create unique index IX_9F11BB2B on BBBParticipant (bbbMeetingId, emailAddress);

create index IX_BE006FE on BBBServer (active_);