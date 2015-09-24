create index IX_D9E49928 on Chat_Entry (createDate, fromUserId, toUserId);
create index IX_8BE273A4 on Chat_Entry (createDate, toUserId);
create index IX_2A17A23F on Chat_Entry (fromUserId, toUserId, content[$COLUMN_LENGTH:1000$]);
create index IX_16384BE6 on Chat_Entry (toUserId);

create index IX_B723B792 on Chat_Status (modifiedDate, online_);
create index IX_32531B3D on Chat_Status (online_);
create unique index IX_E17EBD79 on Chat_Status (userId);