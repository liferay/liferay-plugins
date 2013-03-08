create index IX_DAEF230F on Chat_Entry (createDate);
create index IX_AD559D93 on Chat_Entry (createDate, fromUserId);
create index IX_D9E49928 on Chat_Entry (createDate, fromUserId, toUserId);
create index IX_8BE273A4 on Chat_Entry (createDate, toUserId);
create index IX_F9966D55 on Chat_Entry (fromUserId);
create index IX_97FE1E6A on Chat_Entry (fromUserId, toUserId);
create index IX_2A17A23F on Chat_Entry (fromUserId, toUserId, content);
create index IX_16384BE6 on Chat_Entry (toUserId);

create index IX_15BD544A on Chat_Status (modifiedDate);
create index IX_B723B792 on Chat_Status (modifiedDate, online_);
create index IX_32531B3D on Chat_Status (online_);
create unique index IX_E17EBD79 on Chat_Status (userId);