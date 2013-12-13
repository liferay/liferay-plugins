create index IX_A89D731 on BBBMeeting (bbbServerId);
create index IX_D41A5517 on BBBMeeting (bbbServerId, status);
create index IX_4E58622B on BBBMeeting (groupId);
create index IX_85871D45 on BBBMeeting (status);

create index IX_9D0878BF on BBBParticipant (bbbMeetingId);

create index IX_A7460C9F on BBBServer (groupId);
create index IX_5984254A on BBBServer (groupId, active_);